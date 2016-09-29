package ve.smile.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lights.core.services.FachadaService;
import ve.smile.dao.CiudadDAO;
import ve.smile.dao.EstadoDAO;
import ve.smile.dto.Ciudad;
import ve.smile.dto.Estado;

@Path("/CiudadService")
public class CiudadServiceM extends FachadaService<Ciudad> {
	
	@GET
	@Path("/consultaCiudadPorEstadoSinSession/{idEstado}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String consultaCiudadPorEstadoSinSession(@PathParam("idEstado") Integer
			id) {
		try {

			List<Ciudad> ciudades = new CiudadDAO().consultaCiudadPorEstado(id);

			return buildAnswerSuccess(ciudades, SUCCESS_2);

		} catch (Exception e) {
			return buildAnswerError(e);
		}

	}


}
