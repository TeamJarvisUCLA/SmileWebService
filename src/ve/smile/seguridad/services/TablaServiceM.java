package ve.smile.seguridad.services;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import lights.core.services.FachadaService;
import ve.smile.dao.Configuracion;
import ve.smile.dao.IndicadorDAO;
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
		List<Tabla> listaTabla = request.getObjetos();
		try {
			return respaldarTablas(listaTabla,parametros);
		} catch (Exception e) {
			return buildAnswerError(e);
		}
	}
	
	@GET
	@Path("/consultarPorRespaldo/{idSesion}/{accessToken}/{idRespaldo}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String pathConsultarPorRespaldo(
			@PathParam("idSesion") Integer idSesion,
			@PathParam("accessToken") String accessToken,
			@PathParam("idRespaldo") Integer idRespaldo) {
		try {
			if (validarSesion(idSesion, accessToken)) {
				return buildAnswerSuccess(
						new TablaDAO().findByRespaldo(idRespaldo), SUCCESS_2);
			}
		} catch (Exception e) {
			return buildAnswerError(e);
		}

		return buildAnswerError(new Exception(ERROR_UNKNOWN));
	}


	public String respaldarTablas(List<Tabla> tablas, HashMap<String, Object> parametros) throws Exception {
		Process p = null;
		ProcessBuilder pb = null;
		Map<String, String> propiedades1 = new HashMap<String, String>();
		Configuracion configuracion = new Configuracion();
		propiedades1 = configuracion.configuracion();
		String tablasRespaldar = "";
		String confiBackup = "C:\\Program Files\\PostgreSQL\\9.3\\bin\\pg_dump.exe,-i,-F,c,-b,-v,-f,";
		String nombreRespaldo = (String)parametros.get("nombre") ;
		nombreRespaldo = nombreRespaldo.replaceAll(" ", "");
		String direcRespaldo = "C:\\RespaldoSmile\\"+nombreRespaldo+"."+ExtensionEnum.BACKUP;
		Respaldo respaldo = new Respaldo(new Multimedia(direcRespaldo, (String)parametros.get("nombre"), (String)parametros.get("descripcion"), ExtensionEnum.BACKUP.ordinal(), TipoMultimediaEnum.RESPALDO.ordinal()), new Date().getTime());
		respaldo.setListTablas(tablas);
		RespaldoDAO respaldoDAO = new RespaldoDAO();
		 respaldo = respaldoDAO.save(respaldo);
		 boolean pendiente = (boolean)parametros.get("completo");
		 System.out.println(pendiente);
		 if(!pendiente){
			 for (int i = 0; i < tablas.size(); i++) {
					tablasRespaldar += ",-t," + tablas.get(i).getNombre();
				}	 
		 }
		
		System.out.println(tablasRespaldar);
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
		     boolean e = true;
		     try {
				
			} catch (Exception e2) {
				// TODO: handle exception
			}
	            while(e){
	            	try {
	    				p.exitValue();
	    				System.out.println(p.exitValue());
	    				e = false;
	    			} catch (Exception z) {
	    				e = true;
	    				System.out.println("Aun no termina");
	    			}	
	            }
	            System.out.println("Termino");
		} catch (Exception e) {
			p.destroy();
			p = pb.start();
			System.out.println("se viono por el otro cath");
			System.out.println(e.getMessage());
		}
		
		// Se asignan valores a las variables de PostgreSQL		

		return buildAnswerSuccess(SUCCESS_5);
	};

}