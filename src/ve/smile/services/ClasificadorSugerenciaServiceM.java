package ve.smile.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lights.core.services.FachadaService;
import ve.smile.dao.ClasificadorSugerenciaDAO;
import ve.smile.dto.ClasificadorSugerencia;

@Path("/ClasificadorSugerenciaService")
public class ClasificadorSugerenciaServiceM extends
		FachadaService<ClasificadorSugerencia> {

	@GET
	@Path("/consultaClasificadorSugerenciaSinSession")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String consultaClasificadorSugerenciSinSession() {
		try {

			List<ClasificadorSugerencia> clasificadorSugerencias = new ClasificadorSugerenciaDAO()
					.findAll();

			return buildAnswerSuccess(clasificadorSugerencias, SUCCESS_2);

		} catch (Exception e) {
			return buildAnswerError(e);
		}

	}

}
