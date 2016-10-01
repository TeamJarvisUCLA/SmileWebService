package ve.smile.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lights.core.services.FachadaService;
import ve.smile.dao.ValoresDAO;
import ve.smile.dto.Valores;

@Path("/ValoresService")
public class ValoresServiceM extends FachadaService<Valores> {

	@GET
	@Path("/consultarAllValores")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String consultaAllValores() {
		try {

			List<Valores> valores = new ValoresDAO().findAll();

			return buildAnswerSuccess(valores, SUCCESS_2);

		} catch (Exception e) {
			return buildAnswerError(e);
		}

	}
}
