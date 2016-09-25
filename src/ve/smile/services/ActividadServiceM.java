package ve.smile.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lights.core.services.FachadaService;
import ve.smile.dao.ActividadDAO;
import ve.smile.dto.Actividad;

@Path("/ActividadService")
public class ActividadServiceM extends FachadaService<Actividad> {
	@GET
	@Path("/consultarPorTrabajoSocial/{idSesion}/{accessToken}/{idTrabajoSocial}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String pathConsultarPorTrabajoSocial(
			@PathParam("idSesion") Integer idSesion,
			@PathParam("accessToken") String accessToken,
			@PathParam("idTrabajoSocial") Integer idTrabajoSocial) {
		try {
			if (validarSesion(idSesion, accessToken)) {
				return buildAnswerSuccess(
						new ActividadDAO().findByTrabajoSocial(idTrabajoSocial),
						SUCCESS_2);
			}
		} catch (Exception e) {
			return buildAnswerError(e);
		}

		return buildAnswerError(new Exception(ERROR_UNKNOWN));
	}
}
