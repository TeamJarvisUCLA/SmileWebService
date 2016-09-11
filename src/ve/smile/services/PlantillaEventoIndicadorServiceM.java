package ve.smile.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lights.core.services.FachadaService;
import ve.smile.dao.PlantillaEventoIndicadorDAO;
import ve.smile.dto.PlantillaEventoIndicador;

@Path("/PlantillaEventoIndicadorService")
public class PlantillaEventoIndicadorServiceM extends FachadaService<PlantillaEventoIndicador> {

	@GET
	@Path("/consultarPorEvento/{idSesion}/{accessToken}/{idEvento}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String pathConsultarPorEvento(@PathParam("idSesion") Integer idSesion, @PathParam("accessToken") String accessToken,
			@PathParam("idEvento") Integer idEvento) {
		try {
			if (validarSesion(idSesion, accessToken)) {
				return buildAnswerSuccess(new PlantillaEventoIndicadorDAO().findByEvento(idEvento), SUCCESS_2); 
			}
		} catch (Exception e) {
			return buildAnswerError(e);
		}
		
		return buildAnswerError(new Exception(ERROR_UNKNOWN));
	}
	
}
