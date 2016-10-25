package ve.smile.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lights.core.googlecode.genericdao.search.Search;
import lights.core.services.FachadaService;
import ve.smile.dao.PadrinoDAO;
import ve.smile.dao.PersonaDAO;
import ve.smile.dao.VoluntarioDAO;
import ve.smile.dto.Padrino;
import ve.smile.dto.Persona;
import ve.smile.dto.Voluntario;
import ve.smile.enums.EstatusPadrinoEnum;
import ve.smile.enums.EstatusVoluntarioEnum;
import ve.smile.payload.request.PayloadPersonaRequest;

import com.google.gson.Gson;

@Path("/PersonaService")
public class PersonaServiceM extends FachadaService<Persona> {

	@POST
	@Path("/incluirVoluntario")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String incluirVoluntario(String data) {
		Gson gson = new Gson();
		PayloadPersonaRequest request = gson.fromJson(data,
				PayloadPersonaRequest.class);
		Map<String, Object> mapa = new HashMap<String, Object>();
		try {
			PersonaDAO personaDAO = new PersonaDAO();
			VoluntarioDAO voluntarioDAO = new VoluntarioDAO();
			Persona persona = personaDAO.save(request.getObjeto());
			Voluntario voluntario = new Voluntario();
			voluntario.setFkPersona(persona);
			voluntario.setEstatusVoluntario(EstatusVoluntarioEnum.POSTULADO
					.ordinal());
			voluntario = voluntarioDAO.save(voluntario);
			mapa.put("id", voluntario.getIdVoluntario());
			return buildAnswerSuccess(SUCCESS_4, mapa);
		} catch (Exception e) {
			return buildAnswerError(e);
		}

	}

	@POST
	@Path("/incluirPadrino")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String incluirPadrino(String data) {
		Gson gson = new Gson();
		PayloadPersonaRequest request = gson.fromJson(data,
				PayloadPersonaRequest.class);
		Map<String, Object> mapa = new HashMap<String, Object>();
		try {
			PersonaDAO personaDAO = new PersonaDAO();
			PadrinoDAO padrinoDAO = new PadrinoDAO();
			Persona persona = personaDAO.save(request.getObjeto());
			Padrino padrino = new Padrino();
			padrino.setFkPersona(persona);
			padrino.setEstatusPadrino(EstatusPadrinoEnum.POSTULADO.ordinal());
			padrino = padrinoDAO.save(padrino);
			mapa.put("id", padrino.getIdPadrino());
			return buildAnswerSuccess(SUCCESS_4, mapa);
		} catch (Exception e) {
			return buildAnswerError(e);
		}

	}

	@GET
	@Path("/consultaPersonasSinUsuario")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String consultaPersonasSinUsuario() {
		try {

			List<Persona> personas = new PersonaDAO().findSinUsuario();

			return buildAnswerSuccess(personas, SUCCESS_2);

		} catch (Exception e) {
			return buildAnswerError(e);
		}

	}

	@GET
	@Path("/find/pagination/sinUsuario{orderby:(/orderby/[^/]+?)?}/{idSesion}/{accessToken}/{count}/{page}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String pathConsultarPersonasPaginacion(
			@PathParam("idSesion") Integer idSesion,
			@PathParam("accessToken") String accessToken,
			@PathParam("count") Integer count, @PathParam("page") Integer page,
			@PathParam("orderby") String orderBy) {

		try {
			if (validarSesion(idSesion, accessToken)) {

				Search search = new Search();

				search.addFilterNull("fkUsuario");

				return consultarPaginacion(idSesion, count, page, search,
						orderBy);
			}
		} catch (Exception e) {
			return buildAnswerError(e);
		}

		return buildAnswerError(new Exception(ERROR_UNKNOWN));
	}

}
