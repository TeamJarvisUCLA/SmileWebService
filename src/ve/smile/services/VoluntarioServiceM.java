package ve.smile.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lights.core.services.FachadaService;
import ve.smile.dao.CarteleraDAO;
import ve.smile.dao.VoluntarioDAO;
import ve.smile.dto.Cartelera;
import ve.smile.dto.Voluntario;

@Path("/VoluntarioService")
public class VoluntarioServiceM extends FachadaService<Voluntario> {

	@GET
	@Path("/consultaVoluntariosParametrizado/{sql}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String consultaVoluntariosParametrizado(@PathParam("sql") String
			sql) {
		try {

			List<Voluntario> voluntarios = new VoluntarioDAO().consultaVoluntariosParametrizado(sql);

			return buildAnswerSuccess(voluntarios, SUCCESS_2);

		} catch (Exception e) {
			return buildAnswerError(e);
		}

	}
	
}
