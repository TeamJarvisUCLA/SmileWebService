package ve.smile.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lights.core.services.FachadaService;
import ve.smile.dao.SolicitudAyudaDAO;
import ve.smile.dto.SolicitudAyuda;

@Path("/SolicitudAyudaService")
public class SolicitudAyudaServiceM extends FachadaService<SolicitudAyuda> {

	@GET
	@Path("/consultaSolicitudesParametrizado/{sql}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String consultaSolicitudesParametrizado(@PathParam("sql") String sql) {
		try {

			List<SolicitudAyuda> solicitudAyudas = new SolicitudAyudaDAO()
					.consultaSolicitudesParametrizado(sql);

			return buildAnswerSuccess(solicitudAyudas, SUCCESS_2);

		} catch (Exception e) {
			return buildAnswerError(e);
		}

	}

}
