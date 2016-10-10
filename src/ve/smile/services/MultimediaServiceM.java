package ve.smile.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lights.core.services.FachadaService;
import ve.smile.dao.MultimediaDAO;
import ve.smile.dto.Multimedia;

@Path("/MultimediaService")
public class MultimediaServiceM extends FachadaService<Multimedia> {

	@GET
	@Path("/consultarMultimediaTipo/{cantidad}/{tipo}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String consultarMultimediaTipo(@PathParam("cantidad") Integer cantidad,
			@PathParam("tipo") Integer tipo) {
		try {

			List<Multimedia> albumes = new MultimediaDAO().findMultimediaTipo(cantidad, tipo);

			return buildAnswerSuccess(albumes, SUCCESS_2);

		} catch (Exception e) {
			return buildAnswerError(e);
		}

	}
}
