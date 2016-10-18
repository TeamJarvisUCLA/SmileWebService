package ve.smile.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lights.core.services.FachadaService;
import ve.smile.dao.TrabajadorDAO;
import ve.smile.dto.Trabajador;

@Path("/TrabajadorService")
public class TrabajadorServiceM extends FachadaService<Trabajador> {

	@GET
	@Path("/consultaTrabajadoresParametrizado/{sql}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String consultaTrabajadoresParametrizado(@PathParam("sql") String sql) {
		try {

			List<Trabajador> trabajadors = new TrabajadorDAO()
					.consultaTrabajadoresParametrizado(sql);

			return buildAnswerSuccess(trabajadors, SUCCESS_2);

		} catch (Exception e) {
			return buildAnswerError(e);
		}

	}
}
