package ve.smile.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lights.core.services.FachadaService;
import ve.smile.dao.ReconocimientoPersonaDAO;
import ve.smile.dto.ReconocimientoPersona;

@Path("/ReconocimientoPersonaService")
public class ReconocimientoPersonaServiceM extends FachadaService<ReconocimientoPersona> {

	@GET
	@Path("/consultaReconocimientoPersonasParametrizado/{sql}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String consultaReconocimientoPersonasParametrizado(@PathParam("sql") String
			sql) {
		try {

			List<ReconocimientoPersona> reconocimientos = new ReconocimientoPersonaDAO().consultaReconocimientoPersonasParametrizado(sql);
			return buildAnswerSuccess(reconocimientos, SUCCESS_2);

		} catch (Exception e) {
			return buildAnswerError(e);
		}
	}
	
}
