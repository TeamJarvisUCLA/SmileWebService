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
import ve.smile.dao.ComentarioAlbumDAO;
import ve.smile.dao.ComunidadDAO;
import ve.smile.dto.ComentarioAlbum;
import ve.smile.dto.Comunidad;
import ve.smile.enums.EstatusComentarioAlbumEnum;
import ve.smile.payload.request.PayloadComentarioAlbumRequest;

import com.google.gson.Gson;

@Path("/ComentarioAlbumService")
public class ComentarioAlbumServiceM extends FachadaService<ComentarioAlbum> {

	@GET
	@Path("/consultaComentariosAlbum/{album}/{estatusComentario}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String consultaComentariosAlbum(@PathParam("album") Integer album,
			@PathParam("estatusComentario") Integer estatusComentario) {
		try {

			List<ComentarioAlbum> comentarios = new ComentarioAlbumDAO()
					.findComentariosAlbum(album, estatusComentario);

			return buildAnswerSuccess(comentarios, SUCCESS_2);

		} catch (Exception e) {
			return buildAnswerError(e);
		}

	}

	@POST
	@Path("/incluirComentarioAlbum")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String incluirComentarioAlbum(String data) {
		Gson gson = new Gson();
		PayloadComentarioAlbumRequest request = gson.fromJson(data,
				PayloadComentarioAlbumRequest.class);
		Map<String, Object> mapa = new HashMap<String, Object>();
		try {
			ComunidadDAO comunidadDAO = new ComunidadDAO();
			ComentarioAlbumDAO comentarioAlbumDAO = new ComentarioAlbumDAO();
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
					EstatusComentarioAlbumEnum.PENDIENTE.ordinal());
			ComentarioAlbum comentarioAlbum = comentarioAlbumDAO.save(request
					.getObjeto());
			mapa.put("id", comentarioAlbum.getIdComentarioAlbum());
			return buildAnswerSuccess(SUCCESS_4, mapa);
		} catch (Exception e) {
			return buildAnswerError(e);
		}

	}
}
