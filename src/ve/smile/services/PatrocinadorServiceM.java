package ve.smile.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lights.core.services.FachadaService;
import ve.smile.dao.PatrocinadorDAO;
import ve.smile.dto.Patrocinador;

@Path("/PatrocinadorService")
public class PatrocinadorServiceM extends FachadaService<Patrocinador> {

	@GET
	@Path("/consultarAllPatrocinador")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String consultarAllPatrocinador() {
		try {

			List<Patrocinador> patrocinadores = new PatrocinadorDAO().findAll();

				return buildAnswerSuccess(patrocinadores, SUCCESS_2);

			} catch (Exception e) {
				return buildAnswerError(e);
			}

	}
}
