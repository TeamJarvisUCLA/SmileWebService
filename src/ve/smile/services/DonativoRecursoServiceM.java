package ve.smile.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lights.core.services.FachadaService;
import ve.smile.dao.DonativoRecursoDAO;
import ve.smile.dto.DonativoRecurso;

@Path("/DonativoRecursoService")
public class DonativoRecursoServiceM extends FachadaService<DonativoRecurso> {

	@GET
	@Path("/consultaDonativosParametrizado/{sql}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String consultaDonativosParametrizado(@PathParam("sql") String sql) {
		try {
			List<DonativoRecurso> padrinos = new DonativoRecursoDAO()
					.consultaDonativosParametrizado(sql);
			return buildAnswerSuccess(padrinos, SUCCESS_2);
		} catch (Exception e) {
			return buildAnswerError(e);
		}

	}

}
