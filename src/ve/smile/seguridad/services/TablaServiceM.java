package ve.smile.seguridad.services;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import lights.core.services.FachadaService;
import ve.smile.dao.Configuracion;
import ve.smile.dao.MultimediaDAO;
import ve.smile.dao.RespaldoDAO;
import ve.smile.dto.Multimedia;
import ve.smile.dto.Respaldo;
import ve.smile.enums.ExtensionEnum;
import ve.smile.enums.TipoMultimediaEnum;
import ve.smile.seguridad.dao.TablaDAO;
import ve.smile.seguridad.dto.Tabla;
import ve.smile.seguridad.payload.request.PayloadTablaRequest;

@Path("/TablaService")
public class TablaServiceM extends FachadaService<Tabla> {
	@POST
	@Path("/respaldarTablas")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String respaldarTablas(String data) {
		Gson gson = new Gson();

		PayloadTablaRequest request = gson.fromJson(data,
				PayloadTablaRequest.class);
		 HashMap<String, Object> parametros = request.getParametros();
		// request.setObjetos((List<Tabla>) request.getParametro("lista"));
		// System.out.println(((ArrayList<Tabla>)request.getParametros().get("lista")).size());
		List<Tabla> listaTabla = request.getObjetos();
		
		try {
			return respaldarTablas(listaTabla,parametros);
		} catch (Exception e) {
			return buildAnswerError(e);
		}

	}

	public String respaldarTablas(List<Tabla> tablas, HashMap<String, Object> parametros) throws Exception {
		Runtime runTime;
		Process p;
		ProcessBuilder pb;
		Map<String, String> propiedades1 = new HashMap<String, String>();
		Configuracion configuracion = new Configuracion();
		propiedades1 = configuracion.configuracion();
		String tablasRespaldar = "";
		String confiBackup = "C:\\Program Files\\PostgreSQL\\9.3\\bin\\pg_dump.exe,-i,-F,c,-b,-v,-f,";
		String direcRespaldo = "C:\\RespaldoSmile\\"+parametros.get("nombre")+"."+ExtensionEnum.BACKUP;
		Respaldo respaldo = new Respaldo(new Multimedia(direcRespaldo, (String)parametros.get("nombre"), (String)parametros.get("descripcion"), ExtensionEnum.BACKUP.ordinal(), TipoMultimediaEnum.RESPALDO.ordinal()), new Date().getTime());
		respaldo.setListTablas(tablas);
		RespaldoDAO respaldoDAO = new RespaldoDAO();
		 respaldo = respaldoDAO.save(respaldo);
		for (int i = 0; i < tablas.size(); i++) {
			tablasRespaldar += ",-t," + tablas.get(i).getNombre();

		}
		String codigTemporal = confiBackup + direcRespaldo + tablasRespaldar
				+ "," + propiedades1.get("database");
		String[] atato = codigTemporal.split(",");
		try {
			pb = new ProcessBuilder(atato);
			pb.environment().put("PGHOST", propiedades1.get("ip"));
			pb.environment().put("PGPORT", propiedades1.get("puerto"));
			pb.environment().put("PGUSER", propiedades1.get("user"));
			pb.environment().put("PGPASSWORD", propiedades1.get("contrasenna"));
			pb.redirectErrorStream(true);
			p = pb.start();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		runTime = Runtime.getRuntime();
		// Se asignan valores a las variables de PostgreSQL		

		return buildAnswerSuccess(SUCCESS_5);
	};

}