package ve.smile.services;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import lights.core.services.FachadaService;
import ve.smile.dao.FrecuenciaAporteDAO;
import ve.smile.dto.FrecuenciaAporte;

@Path("/FrecuenciaAporteService")
public class FrecuenciaAporteServiceM extends FachadaService<FrecuenciaAporte> {
	
	@GET
	@Path("/consultaFrecuenciaAporteSinSession")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String consultaFrecuenciaAporteSinSession() {
		try {

			List<FrecuenciaAporte> frecuenciaAportes = new FrecuenciaAporteDAO().findAll();

			return buildAnswerSuccess(frecuenciaAportes, SUCCESS_2);

		} catch (Exception e) {
			return buildAnswerError(e);
		}

	}


}
