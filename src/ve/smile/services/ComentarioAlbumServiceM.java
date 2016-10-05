package ve.smile.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lights.core.services.FachadaService;
import ve.smile.dao.ComentarioAlbumDAO;
import ve.smile.dto.ComentarioAlbum;

@Path("/ComentarioAlbumService")
public class ComentarioAlbumServiceM extends FachadaService<ComentarioAlbum> {

	
	@GET
	@Path("/consultaComentariosAlbum/{album}/{estatusComentario}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String consultaComentariosAlbum(@PathParam("album") Integer
			album, @PathParam("estatusComentario") Integer estatusComentario) {
		try {

			List<ComentarioAlbum> comentarios = new ComentarioAlbumDAO()
				.findComentariosAlbum(album, estatusComentario);

			return buildAnswerSuccess(comentarios, SUCCESS_2);

		} catch (Exception e) {
			return buildAnswerError(e);
		}

	}
}
