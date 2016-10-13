package ve.smile.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lights.core.services.FachadaService;
import ve.smile.dao.EventoPlanificadoDAO;
import ve.smile.dto.EventoPlanificado;

@Path("/EventoPlanificadoService")
public class EventoPlanificadoServiceM extends FachadaService<EventoPlanificado> {
	
	@GET
	@Path("/consultaEventoPlanificadoPublicable/{publico}/{estatusEvento}/{fechaDesde}/{cant}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String consultaEventoPlanificadoPublicable(@PathParam("publico") Boolean
			publico, @PathParam("estatusEvento") Integer estatusEvento, 
			@PathParam("fechaDesde") Long fechaDesde, @PathParam("cant") Integer cant) {
		try {

			List<EventoPlanificado> evento = new EventoPlanificadoDAO().findEventosPlanificadoPublico(publico, estatusEvento, fechaDesde, cant);

			return buildAnswerSuccess(evento, SUCCESS_2);

		} catch (Exception e) {
			return buildAnswerError(e);
		}

	}
	
	@GET
	@Path("/consultaEventoPlanificadoAlbum/{album}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String consultaEventoPlanificadoAlbum(@PathParam("album") Integer album) {
		try {

			List<EventoPlanificado> evento = new EventoPlanificadoDAO().findEventosPlanificadoAlbum(album);

			return buildAnswerSuccess(evento, SUCCESS_2);

		} catch (Exception e) {
			return buildAnswerError(e);
		}

	}

}
