package ve.smile.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lights.core.services.FachadaService;
import ve.smile.dao.CarteleraDAO;
import ve.smile.dto.Cartelera;

@Path("/CarteleraService")
public class CarteleraServiceM extends FachadaService<Cartelera> {
	
	@GET
	@Path("/consultaCarteleraPorParametro/{cantidad}/{tipoCartelera}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String consultaCarteleraPorParametro(@PathParam("cantidad") Integer
			cantidad, @PathParam("tipoCartelera") Integer tipoCartelera) {
		try {

			List<Cartelera> carteleras = new CarteleraDAO().findOrganizacion(cantidad, tipoCartelera);

			return buildAnswerSuccess(carteleras, SUCCESS_2);

		} catch (Exception e) {
			return buildAnswerError(e);
		}

	}

}
