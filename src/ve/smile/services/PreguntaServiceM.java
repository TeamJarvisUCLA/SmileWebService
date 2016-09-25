package ve.smile.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lights.core.services.FachadaService;
import ve.smile.dao.PreguntaDAO;
import ve.smile.dto.Pregunta;

@Path("/PreguntaService")
public class PreguntaServiceM extends FachadaService<Pregunta> {
	
	@GET
	@Path("/consultarPorClasificadorPregunta/{idSesion}/{accessToken}/{idClasificadorPregunta}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String pathConsultarPorClasificadorPregunta(
			@PathParam("idSesion") Integer idSesion,
			@PathParam("accessToken") String accessToken,
			@PathParam("idClasificadorPregunta") Integer idClasificadorPregunta) {
		try {
			if (validarSesion(idSesion, accessToken)) {
				return buildAnswerSuccess(
						new PreguntaDAO()
								.findByClasificadorPregunta(idClasificadorPregunta),
						SUCCESS_2);
			}
		} catch (Exception e) {
			return buildAnswerError(e);
		}

		return buildAnswerError(new Exception(ERROR_UNKNOWN));
	}
	
}
