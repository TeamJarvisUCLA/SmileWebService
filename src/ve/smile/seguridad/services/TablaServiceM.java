package ve.smile.seguridad.services;
import java.util.ArrayList;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import lights.core.services.FachadaService;
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
		
		PayloadTablaRequest request =  (PayloadTablaRequest) gson.fromJson(data, getClassPayloadRequest());
		
		System.out.println(((ArrayList<Tabla>)request.getParametros().get("lista")).size());
		try {
			if (validarSesion(request.getIdSesion(), request.getAccessToken())) {
				return respaldarTablas(request.getIdSesion(), (Tabla) request.getObjeto());
			}
		} catch (Exception e) {
			return buildAnswerError(e);
		}
		
		return buildAnswerError(new Exception(ERROR_UNKNOWN));
	}
	public String respaldarTablas(Integer idSesion, Tabla operacion) throws Exception {
		TablaDAO tablaDAO = new TablaDAO();
		tablaDAO.versionPostgreSQL();
		System.out.println(tablaDAO.versionPostgreSQL());
//		VistaOperacionBasicoDAO vistaOperacionBasicoDAO =
//				new VistaOperacionBasicoDAO();
//		
//		List<VistaOperacionBasico> vistaOperacionBasicos =
//				vistaOperacionBasicoDAO.findByOperacion(operacion.getIdOperacion());
//
//		for (VistaOperacionBasico vistaOperacionBasico : vistaOperacionBasicos) {
//			vistaOperacionBasico.setNombre(operacion.getNombre());
//			vistaOperacionBasico.setTooltiptext(operacion.getTooltiptext());
//			vistaOperacionBasico.setFkIconSclass(operacion.getFkIconSclass());
//			vistaOperacionBasico.setFkSclass(operacion.getFkSclass());
//			
//			vistaOperacionBasicoDAO.save(vistaOperacionBasico);
//		}
//		
//		String datos = getDataFromObjectToAuditoria(operacion);
//		
//		auditar(idSesion, "tb_vista_operacion_basico" , AccionEnum.MODIFICAR.ordinal(), 
//				"VistaOperacionBasicoService.MODIFICAR", 0, datos);
		
		return buildAnswerSuccess(SUCCESS_5);
	};

}