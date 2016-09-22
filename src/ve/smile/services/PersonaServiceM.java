package ve.smile.services;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import lights.core.services.FachadaService;
import ve.smile.dao.PadrinoDAO;
import ve.smile.dao.PersonaDAO;
import ve.smile.dao.VoluntarioDAO;
import ve.smile.dto.Padrino;
import ve.smile.dto.Persona;
import ve.smile.dto.Voluntario;
import ve.smile.enums.EstatusPostuladoEnum;
import ve.smile.payload.request.PayloadPersonaRequest;

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
			voluntario.setEstatusPostulado(EstatusPostuladoEnum.POSTULADO.ordinal());
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
			padrino.setEstatusPostulado(EstatusPostuladoEnum.POSTULADO.ordinal());
			padrino = padrinoDAO.save(padrino);
			mapa.put("id", padrino.getIdPadrino());
			return buildAnswerSuccess(SUCCESS_4, mapa);
		} catch (Exception e) {
			return buildAnswerError(e);
		}

	}

}
