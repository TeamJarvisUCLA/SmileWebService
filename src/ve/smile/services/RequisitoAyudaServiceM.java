package ve.smile.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lights.core.services.FachadaService;
import ve.smile.dao.RequisitoAyudaDAO;
import ve.smile.dto.RequisitoAyuda;

@Path("/RequisitoAyudaService")
public class RequisitoAyudaServiceM extends FachadaService<RequisitoAyuda> {

	@GET
	@Path("/consultarPorAyuda/{idSesion}/{accessToken}/{idRol}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String pathConsultarPorAyudal(@PathParam("idSesion") Integer idSesion, @PathParam("accessToken") String accessToken,
			@PathParam("idAyuda") Integer idAyuda) {
		try {
			if (validarSesion(idSesion, accessToken)) {
				return buildAnswerSuccess(new RequisitoAyudaDAO().findByAyuda(idAyuda), SUCCESS_2); 
			}
		} catch (Exception e) {
			return buildAnswerError(e);
		}
		
		return buildAnswerError(new Exception(ERROR_UNKNOWN));
	}
	
}
