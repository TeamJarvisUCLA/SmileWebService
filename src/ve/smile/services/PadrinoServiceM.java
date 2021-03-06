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

import lights.core.services.FachadaService;
import ve.smile.dao.PadrinoDAO;
import ve.smile.dao.PersonaDAO;
import ve.smile.dto.Padrino;
import ve.smile.enums.EstatusPadrinoEnum;
import ve.smile.payload.request.PayloadPadrinoRequest;

import com.google.gson.Gson;

@Path("/PadrinoService")
public class PadrinoServiceM extends FachadaService<Padrino> {
	@GET
	@Path("/consultaPadrinoParametrizado/{sql}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String consultaPadrinoParametrizado(@PathParam("sql") String sql) {
		try {
			List<Padrino> padrinos = new PadrinoDAO()
					.consultaPadrinosParametrizado(sql);
			return buildAnswerSuccess(padrinos, SUCCESS_2);
		} catch (Exception e) {
			return buildAnswerError(e);
		}

	}

	@POST
	@Path("/incluirPostulacionPadrino")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String incluirPostulacionPadrino(String data) {
		Gson gson = new Gson();
		PayloadPadrinoRequest request = gson.fromJson(data,
				PayloadPadrinoRequest.class);
		Map<String, Object> mapa = new HashMap<String, Object>();
		try {
			PersonaDAO personaDAO = new PersonaDAO();
			PadrinoDAO padrinoDAO = new PadrinoDAO();
			personaDAO.save(request.getObjeto().getFkPersona());
			request.getObjeto().setEstatusPadrino(
					EstatusPadrinoEnum.POSTULADO.ordinal());
			Padrino padrino = padrinoDAO.save(request.getObjeto());
			mapa.put("id", padrino.getIdPadrino());
			return buildAnswerSuccess(SUCCESS_4, mapa);
		} catch (Exception e) {
			return buildAnswerError(e);
		}

	}

}
