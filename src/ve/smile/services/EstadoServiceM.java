package ve.smile.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import lights.core.services.FachadaService;
import ve.smile.dao.EstadoDAO;
import ve.smile.dto.Estado;

@Path("/EstadoService")
public class EstadoServiceM extends FachadaService<Estado> {
	
	@GET
	@Path("/consultaEstadoSinSession")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String consultaEstadoSinSession() {
		try {

			List<Estado> estados = new EstadoDAO().findAll();

			return buildAnswerSuccess(estados, SUCCESS_2);

		} catch (Exception e) {
			return buildAnswerError(e);
		}

	}

}
