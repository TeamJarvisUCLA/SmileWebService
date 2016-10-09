package ve.smile.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lights.core.services.FachadaService;
import ve.smile.dao.AyudaDAO;
import ve.smile.dao.ParticipacionDAO;
import ve.smile.dao.RequisitoDAO;
import ve.smile.dto.Ayuda;
import ve.smile.dto.Participacion;
import ve.smile.dto.Requisito;

@Path("/AyudaService")
public class AyudaServiceM extends FachadaService<Ayuda> {

	@GET
	@Path("/consultarAllAyuda")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String consultaAllAyuda() {
		try {

			List<Ayuda> ayudas = new AyudaDAO().findAll();


			for (Ayuda ayuda : ayudas) {
				List<Requisito> requisitos = new RequisitoDAO().findByAyuda(ayuda.getIdAyuda());
				ayuda.setRequisitos(requisitos);
			}
			return buildAnswerSuccess(ayudas, SUCCESS_2);

		} catch (Exception e) {
			return buildAnswerError(e);
		}

	}
}
