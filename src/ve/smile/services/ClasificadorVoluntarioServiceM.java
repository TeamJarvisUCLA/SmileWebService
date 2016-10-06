package ve.smile.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lights.core.services.FachadaService;
import ve.smile.dao.ClasificadorVoluntarioDAO;
import ve.smile.dto.ClasificadorVoluntario;

@Path("/ClasificadorVoluntarioService")
public class ClasificadorVoluntarioServiceM extends FachadaService<ClasificadorVoluntario> {
	
	// VOLUNTARIO
	@GET
	@Path("/consultarPorVoluntario/{idSesion}/{accessToken}/{idVoluntario}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String pathConsultarPorVoluntario(
			@PathParam("idSesion") Integer idSesion,
			@PathParam("accessToken") String accessToken,
			@PathParam("idVoluntario") Integer idVoluntario) {
		try {
			if (validarSesion(idSesion, accessToken)) {
				return buildAnswerSuccess(
						new ClasificadorVoluntarioDAO()
								.findByVoluntario(idVoluntario),
						SUCCESS_2);
			}
		} catch (Exception e) {
			return buildAnswerError(e);
		}

		return buildAnswerError(new Exception(ERROR_UNKNOWN));
	}

	@GET
	@Path("/consultarPorVoluntarioSinSession/{idVoluntario}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String pathConsultarPorVoluntarioSinSession(
			@PathParam("idVoluntario") Integer idVoluntario) {
		try {
			return buildAnswerSuccess(
					new ClasificadorVoluntarioDAO()
							.findByVoluntario(idVoluntario),
					SUCCESS_2);
		} catch (Exception e) {
			return buildAnswerError(e);
		}
	}

}
