package ve.smile.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lights.core.services.FachadaService;
import ve.smile.dao.ConfiguracionDAO;
import ve.smile.dto.Configuracion;

@Path("/ConfiguracionService")
public class ConfiguracionServiceM extends FachadaService<Configuracion> {
	
	@GET
	@Path("/consultarConfiguracionPropiedad/{propiedad}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String consultarConfiguracionPropiedad(@PathParam("propiedad") Integer propiedad) {
		try {

			List<Configuracion> configuracion = new ConfiguracionDAO().findConfiguracionPropiedad(propiedad);

			return buildAnswerSuccess(configuracion, SUCCESS_2);

		} catch (Exception e) {
			return buildAnswerError(e);
		}

	}

}
