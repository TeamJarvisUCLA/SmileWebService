package ve.smile.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lights.core.services.FachadaService;
import ve.smile.dao.AlbumDAO;
import ve.smile.dto.Album;

@Path("/AlbumService")
public class AlbumServiceM extends FachadaService<Album> {
	
	@GET
	@Path("/consultarAlbumCantidad/{cantidad}/{estatusAlbum}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String consultarAlbumCantidad(@PathParam("cantidad") Integer
			cantidad, @PathParam("estatusAlbum") Integer estatusAlbum) {
		try {

			List<Album> albumes = new AlbumDAO().findAlbumCantidad(cantidad, estatusAlbum);

			return buildAnswerSuccess(albumes, SUCCESS_2);

		} catch (Exception e) {
			return buildAnswerError(e);
		}

	}

}
