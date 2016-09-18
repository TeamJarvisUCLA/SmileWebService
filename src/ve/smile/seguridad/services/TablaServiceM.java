package ve.smile.seguridad.services;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.validator.constraints.Email;
import org.springframework.format.datetime.joda.MillisecondInstantPrinter;

import com.google.gson.Gson;

import lights.core.services.FachadaService;
import ve.smile.dao.Configuracion;
import ve.smile.dao.ConfiguracionDAO;
import ve.smile.dao.MultimediaDAO;
import ve.smile.dto.Multimedia;
import ve.smile.dto.RespaldoTabla;
import ve.smile.enums.ExtensionEnum;
import ve.smile.enums.TipoMultimediaEnum;
import ve.smile.seguridad.dao.TablaDAO;
import ve.smile.seguridad.dto.Operacion;
import ve.smile.seguridad.dto.Tabla;
import ve.smile.seguridad.payload.request.PayloadOperacionRequest;
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
		// HashMap<String, Object> parametro2 = request.getParametros();
		// request.setObjetos((List<Tabla>) request.getParametro("lista"));
		// System.out.println(((ArrayList<Tabla>)request.getParametros().get("lista")).size());
		List<Tabla> listaTabla = request.getObjetos();
		try {
			return respaldarTablas(listaTabla);
		} catch (Exception e) {
			return buildAnswerError(e);
		}

	}

	public String respaldarTablas(List<Tabla> tablas) throws Exception {
	    Multimedia multimedia = new Multimedia();
	    multimedia.setDescripcion("descripsion");
		multimedia.setExtensionEnum(ExtensionEnum.DOC);
		multimedia.setNombre("Will a la guerra");
		multimedia.setTipoMultimediaEnum(TipoMultimediaEnum.IMAGEN);
		multimedia.setUrl("go brother somos los mas valientes");
		MultimediaDAO multimediaDAO = new MultimediaDAO();
		 multimedia = multimediaDAO.save(multimedia);
		 System.out.println("Id de multimedia: " + multimedia.getIdMultimedia());
		 Runtime runTime;
	     Process p;
	    ProcessBuilder pb;
		TablaDAO tablaDAO = new TablaDAO();
		Tabla tabla = new Tabla();
		RespaldoTabla respaldoTabla = new RespaldoTabla();
		ConfiguracionDAO configuracionDAO = new ConfiguracionDAO();
		tablaDAO.versionPostgreSQL();
		Map<String, String> propiedades1 = new HashMap<String, String>();
		Configuracion configuracion = new Configuracion();
		propiedades1 = configuracion.configuracion();
		System.out.println(propiedades1.get("puerto"));
		System.out.println(propiedades1.get("ip"));
		System.out.println(propiedades1.get("user"));
		System.out.println(propiedades1.get("contrasenna"));
		System.out.println(propiedades1.get("database"));
		String tablasRespaldar = "";
		System.out.println("funciona gson " + tablas.size());
		for (int i = 0; i < tablas.size(); i++) {
				tablasRespaldar +=   ",-t," +tablas.get(i).getNombre()  ;
			
		}
		// para linux
		//String confiBackup = "/opt/PostgreSQL/9.3/bin/pg_dump,-i,-F,c,-b,-v,-f,";
		// para Windows
		String confiBackup = "C:/Program Files/PostgreSQL/9.3/bin/pg_dump.exe,-i,-F,c,-b,-v,-f,";
		Date date = new Date();
		// para linux
		//String direcRespaldo = "/home/willandher/BackupSmile/"+date.toString()+".backup";
		String direcRespaldo = "C:/RespaldoSmile/"+date.toString()+".backup";
		
		///opt/PostgreSQL/9.3/bin/pg_dump,-i,-F,c,-b,-v,-f,/home/backup.backup,Smile,-t,tb_ayuda
		String codigTemporal = confiBackup+direcRespaldo+","+propiedades1.get("database")+tablasRespaldar;
		String[] atato = codigTemporal.split(",");
		pb = new ProcessBuilder(atato);            

        runTime = Runtime.getRuntime();

        //Se asignan valores a las variables de PostgreSQL
       
        pb.environment().put("PGHOST", propiedades1.get("ip"));
        pb.environment().put("PGPORT", propiedades1.get("puerto"));
        pb.environment().put("PGUSER", propiedades1.get("user"));
        pb.environment().put("PGPASSWORD", propiedades1.get("contrasenna"));
        pb.redirectErrorStream(true);

        p = pb.start();

		System.out.println(direcRespaldo);
		System.out.println(tablasRespaldar);
		return buildAnswerSuccess(SUCCESS_5);
	};

}