package ve.smile.services;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lights.core.services.FachadaService;
import ve.smile.dao.OrganizacionDAO;
import ve.smile.dto.Organizacion;

@Path("/OrganizacionService")
public class OrganizacionServiceM extends FachadaService<Organizacion> {

	@GET
	@Path("/buscarOrganizacion")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String buscarOrganizacion() {
		try {
			Organizacion organizacion = new OrganizacionDAO()
					.findOrganizacion();

			return buildAnswerSuccess(organizacion, SUCCESS_2);

		} catch (Exception e) {
			return buildAnswerError(e);
		}

	}

}
