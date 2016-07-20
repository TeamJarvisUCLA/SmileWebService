package lights.seguridad.services;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import lights.core.services.FachadaService;
import lights.seguridad.dao.VistaOperacionCustomDAO;
import lights.seguridad.dto.VistaOperacionCustom;
import lights.seguridad.enums.AccionEnum;
import lights.seguridad.payload.request.PayloadVistaOperacionCustomRequest;

@Path("/VistaOperacionCustomService")
public class VistaOperacionCustomServiceM extends FachadaService<VistaOperacionCustom> {

	@POST
	@Path("/updateWithoutId")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String pathModificarSinId(String data) {
		Gson gson = new Gson();
		
		PayloadVistaOperacionCustomRequest request = (PayloadVistaOperacionCustomRequest) gson.fromJson(data, PayloadVistaOperacionCustomRequest.class);
		
		try {
			if (validarSesion(request.getIdSesion(), request.getAccessToken())) {
				return modificarSinId(request.getIdSesion(), (VistaOperacionCustom) request.getObjeto());
			}
		} catch (Exception e) {
			return buildAnswerError(e);
		}
		
		return buildAnswerError(new Exception(ERROR_UNKNOWN));
	}
	
	public String modificarSinId(Integer idSesion, VistaOperacionCustom vistaOperacionCustom) throws Exception {
		VistaOperacionCustomDAO vistaOperacionCustomDAO = new VistaOperacionCustomDAO();
		
		VistaOperacionCustom o = vistaOperacionCustomDAO.findByVistaAndOperacion(
				vistaOperacionCustom.getFkVista().getIdVista(), vistaOperacionCustom.getOperacion());
		
		o.setFkIconSclass(vistaOperacionCustom.getFkIconSclass());
		o.setFkSclass(vistaOperacionCustom.getFkSclass());
		o.setNombre(vistaOperacionCustom.getNombre());
		o.setTooltiptext(vistaOperacionCustom.getTooltiptext());
		
		o = vistaOperacionCustomDAO.save(o);
		
		String datos = getDataFromObjectToAuditoria(o);
		
		auditar(idSesion, getTable() , AccionEnum.MODIFICAR.ordinal(), getPath().substring(1) + ".MODIFICAR", getMetadataUtil().getId(o), datos);
		
		return buildAnswerSuccess(SUCCESS_5);
	};
}
