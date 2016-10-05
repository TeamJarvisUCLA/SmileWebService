package ve.smile.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lights.core.services.FachadaService;
import ve.smile.dao.ClasificadorPreguntaDAO;
import ve.smile.dto.ClasificadorPregunta;

@Path("/ClasificadorPreguntaService")
public class ClasificadorPreguntaServiceM extends FachadaService<ClasificadorPregunta> {
	
	@GET
	@Path("/consultarClasificadorPregunta")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String consultarClasificadorPregunta() {
		try {

			List<ClasificadorPregunta> configuracion = new ClasificadorPreguntaDAO().findAll();

			return buildAnswerSuccess(configuracion, SUCCESS_2);

		} catch (Exception e) {
			return buildAnswerError(e);
		}

	}

}
