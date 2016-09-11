package ve.smile.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lights.core.services.FachadaService;
import ve.smile.dao.RequisitoParticipacionDAO;
import ve.smile.dto.RequisitoParticipacion;

@Path("/RequisitoParticipacionService")
public class RequisitoParticipacionServiceM extends FachadaService<RequisitoParticipacion> {
	@GET
	@Path("/consultarPorParticipacion/{idSesion}/{accessToken}/{idParticipacion}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String pathConsultarPorParticipacion(@PathParam("idSesion") Integer idSesion, @PathParam("accessToken") String accessToken,
			@PathParam("idParticipacion") Integer idParticipacion) {
		try {
			if (validarSesion(idSesion, accessToken)) {
				return buildAnswerSuccess(new RequisitoParticipacionDAO().findByParticipacion(idParticipacion), SUCCESS_2); 
			}
		} catch (Exception e) {
			return buildAnswerError(e);
		}
		
		return buildAnswerError(new Exception(ERROR_UNKNOWN));
	}
}
