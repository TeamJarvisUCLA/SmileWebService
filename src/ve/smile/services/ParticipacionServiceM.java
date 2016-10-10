package ve.smile.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lights.core.services.FachadaService;
import ve.smile.dao.ParticipacionDAO;
import ve.smile.dao.RequisitoDAO;
import ve.smile.dto.Participacion;
import ve.smile.dto.Requisito;

@Path("/ParticipacionService")
public class ParticipacionServiceM extends FachadaService<Participacion> {
	
	@GET
	@Path("/consultaAllParticipacion")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String consultaAllParticipacion() {
		try {

			List<Participacion> participaciones = new ParticipacionDAO().findAll();

			for (Participacion participacion : participaciones) {
				List<Requisito> requisitos = new RequisitoDAO().findByParticipacion(participacion.getIdParticipacion());
				participacion.setRequisitos(requisitos);
			}
			return buildAnswerSuccess(participaciones, SUCCESS_2);

		} catch (Exception e) {
			return buildAnswerError(e);
		}

	}
	
	@GET
	@Path("/consultaCantidadParticipacion/{cantidad}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String consultaCantidadParticipacion(@PathParam("cantidad") Integer
			cantidad) {
		try {

			List<Participacion> participaciones = new ParticipacionDAO().findParticipacionLimt(cantidad);

			return buildAnswerSuccess(participaciones, SUCCESS_2);

		} catch (Exception e) {
			return buildAnswerError(e);
		}

	}

}
