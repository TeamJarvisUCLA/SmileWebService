package ve.smile.seguridad.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lights.core.services.FachadaService;
import ve.smile.seguridad.dao.PermisoSeguridadDAO;
import ve.smile.seguridad.dto.PermisoSeguridad;
import ve.smile.seguridad.enums.AccionEnum;

@Path("/PermisoSeguridadService")
public class PermisoSeguridadServiceM extends FachadaService<PermisoSeguridad> {

	@GET
	@Path("/deleteByRolAndNodoMenuAndOperacion/{idSesion}/{accessToken}/{idRol}/{idNodoMenu}/{operacion}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String pathEliminarByRolAndNodoMenuAndOperacion(
			@PathParam("idSesion") Integer idSesion,
			@PathParam("accessToken") String accessToken,
			@PathParam("idRol") Integer idRol,
			@PathParam("idNodoMenu") Integer idNodoMenu,
			@PathParam("operacion") Integer operacion) {
		try {
			if (validarSesion(idSesion, accessToken)) {
				return eliminarByRolAndNodoMenuAndOperacion(idSesion, idRol,
						idNodoMenu, operacion);
			}
		} catch (Exception e) {
			return buildAnswerError(e);
		}

		return buildAnswerError(new Exception(ERROR_UNKNOWN));
	}

	public String eliminarByRolAndNodoMenuAndOperacion(Integer idSesion,
			Integer idRol, Integer idNodoMenu, Integer operacion)
			throws Exception {

		PermisoSeguridadDAO permisoSeguridadDAO = new PermisoSeguridadDAO();

		PermisoSeguridad permisoSeguridad = permisoSeguridadDAO
				.findByNodoMenuAndRolesAndOperacion(idNodoMenu, idRol,
						operacion);
		auditar(idSesion, getTable(), AccionEnum.CONSULTAR.ordinal(), 0, "");
		if (permisoSeguridadDAO.removeById(permisoSeguridad
				.getIdPermisoSeguridad())) {
			return buildAnswerSuccess(SUCCESS_3);
		}
		return buildAnswerError(new Exception(ERROR_4));
	};
}
