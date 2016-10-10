package ve.smile.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lights.core.services.FachadaService;
import ve.smile.dao.TsPlanDAO;
import ve.smile.dto.TsPlan;

@Path("/TsPlanService")
public class TsPlanServiceM extends FachadaService<TsPlan> {

	@GET
	@Path("/consultaTSPlanificadoAlbum/{album}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String consultaTSPlanificadoAlbum(@PathParam("album") Integer album) {
		try {

			List<TsPlan> ts = new TsPlanDAO().findTSPlanificadoAlbum(album);

			return buildAnswerSuccess(ts, SUCCESS_2);

		} catch (Exception e) {
			return buildAnswerError(e);
		}

	}
}
