package ve.smile.services;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lights.core.services.FachadaService;
import ve.smile.dao.ComentarioCarteleraDAO;
import ve.smile.dao.ComunidadDAO;
import ve.smile.dto.ComentarioCartelera;
import ve.smile.dto.Comunidad;
import ve.smile.enums.EstatusComentarioCarteleraEnum;
import ve.smile.payload.request.PayloadComentarioCarteleraRequest;

import com.google.gson.Gson;

@Path("/ComentarioCarteleraService")
public class ComentarioCarteleraServiceM extends
		FachadaService<ComentarioCartelera> {

	@POST
	@Path("/incluirComentarioCartelera")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String incluirComentarioCartelera(String data) {
		Gson gson = new Gson();
		PayloadComentarioCarteleraRequest request = gson.fromJson(data,
				PayloadComentarioCarteleraRequest.class);
		Map<String, Object> mapa = new HashMap<String, Object>();
		try {
			ComunidadDAO comunidadDAO = new ComunidadDAO();
			ComentarioCarteleraDAO comentarioCarteleraDAO = new ComentarioCarteleraDAO();
			Comunidad comundidad = comunidadDAO.findByCorreo(request
					.getObjeto().getFkComunidad().getCorreo());
			if (comundidad == null) {
				comunidadDAO.save(request.getObjeto().getFkComunidad());
			} else {
				comundidad.setApellido(request.getObjeto().getFkComunidad()
						.getApellido());
				comundidad.setNombre(request.getObjeto().getFkComunidad()
						.getNombre());
				comunidadDAO.save(comundidad);
				request.getObjeto().setFkComunidad(comundidad);
			}

			request.getObjeto().setEstatusComentario(
					EstatusComentarioCarteleraEnum.PENDIENTE.ordinal());
			ComentarioCartelera comentarioCartelera = comentarioCarteleraDAO
					.save(request.getObjeto());
			mapa.put("id", comentarioCartelera.getIdComentarioCartelera());
			return buildAnswerSuccess(SUCCESS_4, mapa);
		} catch (Exception e) {
			return buildAnswerError(e);
		}

	}

}
