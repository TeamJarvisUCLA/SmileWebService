package ve.smile.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lights.core.services.FachadaService;
import ve.smile.dao.RequisitoDAO;
import ve.smile.dto.Requisito;

@Path("/RequisitoService")
public class RequisitoServiceM extends FachadaService<Requisito> {
	@GET
	@Path("/consultarPorParticipacion/{idSesion}/{accessToken}/{idParticipacion}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String pathConsultarPorParticipacion(
			@PathParam("idSesion") Integer idSesion,
			@PathParam("accessToken") String accessToken,
			@PathParam("idParticipacion") Integer idParticipacion) {
		try {
			if (validarSesion(idSesion, accessToken)) {
				return buildAnswerSuccess(
						new RequisitoDAO().findByParticipacion(idParticipacion),
						SUCCESS_2);
			}
		} catch (Exception e) {
			return buildAnswerError(e);
		}

		return buildAnswerError(new Exception(ERROR_UNKNOWN));
	}

	@GET
	@Path("/consultarPorAyuda/{idSesion}/{accessToken}/{idAyuda}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String pathConsultarPorAyuda(
			@PathParam("idSesion") Integer idSesion,
			@PathParam("accessToken") String accessToken,
			@PathParam("idAyuda") Integer idAyuda) {
		try {
			if (validarSesion(idSesion, accessToken)) {
				return buildAnswerSuccess(
						new RequisitoDAO().findByAyuda(idAyuda), SUCCESS_2);
			}
		} catch (Exception e) {
			return buildAnswerError(e);
		}

		return buildAnswerError(new Exception(ERROR_UNKNOWN));
	}
	@GET
	@Path("/consultarParticipacionRequisitos/{idParticipacion}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String consultarParticipacionRequisitos(@PathParam("idParticipacion") Integer idParticipacion) {
		try {

			List<Requisito> requisitos =  new RequisitoDAO().findByParticipacion(idParticipacion);

			return buildAnswerSuccess(requisitos, SUCCESS_2);

		} catch (Exception e) {
			return buildAnswerError(e);
		}

	}
	
	
	@GET
	@Path("/consultarAyudaRequisitos/{idAyuda}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String consultarAyudaRequisitos(@PathParam("idAyuda") Integer idAyuda) {
		try {

			List<Requisito> requisitos =  new RequisitoDAO().findByAyuda(idAyuda);

			return buildAnswerSuccess(requisitos, SUCCESS_2);

		} catch (Exception e) {
			return buildAnswerError(e);
		}

	}
}
