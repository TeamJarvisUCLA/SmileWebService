package ve.smile.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lights.core.services.FachadaService;
import ve.smile.dao.PreguntaClasificadaDAO;
import ve.smile.dto.PreguntaClasificada;

@Path("/PreguntaClasificadaService")
public class PreguntaClasificadaServiceM extends FachadaService<PreguntaClasificada> {
	@GET
	@Path("/consultarPorClasificador/{idSesion}/{accessToken}/{idClasificadorPregunta}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String pathConsultarPorClasificador(@PathParam("idSesion") Integer idSesion, @PathParam("accessToken") String accessToken,
			@PathParam("idClasificadorPregunta") Integer idClasificadorPregunta) {
		try {
			if (validarSesion(idSesion, accessToken)) {
				return buildAnswerSuccess(new PreguntaClasificadaDAO().findByClasificadorPregunta(idClasificadorPregunta), SUCCESS_2); 
			}
		} catch (Exception e) {
			return buildAnswerError(e);
		}
		
		return buildAnswerError(new Exception(ERROR_UNKNOWN));
	}
	
	@GET
	@Path("/consultarPreguntaClasificador/{clasificador}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String consultarPreguntaClasificador(@PathParam("clasificador") Integer clasificador) {
		try {

			List<PreguntaClasificada> preguntaClasificada = new PreguntaClasificadaDAO().findByClasificadorPregunta(clasificador);

			return buildAnswerSuccess(preguntaClasificada, SUCCESS_2);

		} catch (Exception e) {
			return buildAnswerError(e);
		}

	}
}
