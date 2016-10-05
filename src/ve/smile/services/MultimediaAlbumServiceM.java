package ve.smile.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lights.core.services.FachadaService;
import ve.smile.dao.MultimediaAlbumDAO;
import ve.smile.dto.MultimediaAlbum;

@Path("/MultimediaAlbumService")
public class MultimediaAlbumServiceM extends FachadaService<MultimediaAlbum> {
	
	@GET
	@Path("/consultaMultimediaPorAlbum/{cantidad}/{album}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String consultaMultimediaPorAlbum(@PathParam("cantidad") Integer
			cantidad, @PathParam("album") Integer album) {
		try {

			List<MultimediaAlbum> multimediaAlbum = new MultimediaAlbumDAO().findMultimediasAlbumCantidad(cantidad, album);

			return buildAnswerSuccess(multimediaAlbum, SUCCESS_2);

		} catch (Exception e) {
			return buildAnswerError(e);
		}

	}

}
