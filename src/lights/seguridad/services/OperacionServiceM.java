package lights.seguridad.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import lights.core.services.FachadaService;
import lights.seguridad.dao.PermisoSeguridadDAO;
import lights.seguridad.dao.VistaOperacionBasicoDAO;
import lights.seguridad.dao.VistaOperacionCustomDAO;
import lights.seguridad.dto.Operacion;
import lights.seguridad.dto.PermisoSeguridad;
import lights.seguridad.dto.Vista;
import lights.seguridad.dto.VistaOperacionBasico;
import lights.seguridad.dto.VistaOperacionCustom;
import lights.seguridad.enums.AccionEnum;
import lights.seguridad.enums.helper.OperacionHelper;
import lights.seguridad.payload.request.PayloadOperacionRequest;

@Path("/OperacionService")
public class OperacionServiceM extends FachadaService<Operacion> {
	
	@GET
	@Path("/consultarByNodoMenuAndRoles/{idSesion}/{accessToken}/{idNodoMenu}/{idRoles}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String consultarByNodoMenuAndRoles(@PathParam("idSesion") Integer idSesion, @PathParam("accessToken") String accessToken,
			@PathParam("idNodoMenu") Integer idNodoMenu,
			@PathParam("idRoles") String idRoles) {
		try {
			if (validarSesion(idSesion, accessToken)) {
				return getByNodoMenuAndRoles(idNodoMenu, idRoles);
			}
		} catch (Exception e) {
			return buildAnswerError(e);
		}
		
		return buildAnswerError(new Exception(ERROR_UNKNOWN));
	}
	
	@GET
	@Path("/consultarByVista/{idSesion}/{accessToken}/{idVista}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String consultarByVista(@PathParam("idSesion") Integer idSesion, @PathParam("accessToken") String accessToken,
			@PathParam("idVista") Integer idVista) {
		try {
			if (validarSesion(idSesion, accessToken)) {
				VistaOperacionCustomDAO vistaOperacionCustomDAO = new VistaOperacionCustomDAO();
				VistaOperacionBasicoDAO vistaOperacionBasicoDAO = new VistaOperacionBasicoDAO();
				
				List<Operacion> operaciones = new ArrayList<Operacion>();
				
				List<VistaOperacionBasico> vistasOperacionesBasico = 
						vistaOperacionBasicoDAO.findByVista(idVista);
				
				for (VistaOperacionBasico vistaOperacionBasico : vistasOperacionesBasico) {
					Operacion operacion = new Operacion(vistaOperacionBasico.getOperacion(),
							vistaOperacionBasico.getNombre(), 
							vistaOperacionBasico.getFkIconSclass(), 
							vistaOperacionBasico.getFkSclass(), 
							vistaOperacionBasico.getTooltiptext());
					
					operaciones.add(operacion);
				}
				
				List<VistaOperacionCustom> vistasOperacionesCustom = 
						vistaOperacionCustomDAO.findByVista(idVista);
				
				for (VistaOperacionCustom vistaOperacionCustom : vistasOperacionesCustom) {
					Operacion operacion = new Operacion(vistaOperacionCustom.getOperacion(),
							vistaOperacionCustom.getNombre(), 
							vistaOperacionCustom.getFkIconSclass(), 
							vistaOperacionCustom.getFkSclass(), 
							vistaOperacionCustom.getTooltiptext());
					
					operaciones.add(operacion);
				}
				
				return buildAnswerSuccess(operaciones, SUCCESS_2);
			}
		} catch (Exception e) {
			return buildAnswerError(e);
		}
		
		return buildAnswerError(new Exception(ERROR_UNKNOWN));
	}
	
	@GET
	@Path("/consultarBotonesComunes/{idSesion}/{accessToken}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String consultarBotonesComunes(@PathParam("idSesion") Integer idSesion, 
			@PathParam("accessToken") String accessToken) {
		try {
			if (validarSesion(idSesion, accessToken)) {
				return getBotonesComunes();
			}
		} catch (Exception e) {
			return buildAnswerError(e);
		}
		
		return buildAnswerError(new Exception(ERROR_UNKNOWN));
	}
	
	@POST
	@Path("/updateCommonButtons")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String pathModificarBotonesComunes(String data) {
		Gson gson = new Gson();
		
		PayloadOperacionRequest request = (PayloadOperacionRequest) gson.fromJson(data, getClassPayloadRequest());
		
		try {
			if (validarSesion(request.getIdSesion(), request.getAccessToken())) {
				return modificarBotonesComunes(request.getIdSesion(), (Operacion) request.getObjeto());
			}
		} catch (Exception e) {
			return buildAnswerError(e);
		}
		
		return buildAnswerError(new Exception(ERROR_UNKNOWN));
	}
	
	public String modificarBotonesComunes(Integer idSesion, Operacion operacion) throws Exception {
		VistaOperacionBasicoDAO vistaOperacionBasicoDAO =
				new VistaOperacionBasicoDAO();
		
		List<VistaOperacionBasico> vistaOperacionBasicos =
				vistaOperacionBasicoDAO.findByOperacion(operacion.getIdOperacion());

		for (VistaOperacionBasico vistaOperacionBasico : vistaOperacionBasicos) {
			vistaOperacionBasico.setNombre(operacion.getNombre());
			vistaOperacionBasico.setTooltiptext(operacion.getTooltiptext());
			vistaOperacionBasico.setFkIconSclass(operacion.getFkIconSclass());
			vistaOperacionBasico.setFkSclass(operacion.getFkSclass());
			
			vistaOperacionBasicoDAO.save(vistaOperacionBasico);
		}
		
		String datos = getDataFromObjectToAuditoria(operacion);
		
		auditar(idSesion, "tb_vista_operacion_basico" , AccionEnum.MODIFICAR.ordinal(), 
				"VistaOperacionBasicoService.MODIFICAR", 0, datos);
		
		return buildAnswerSuccess(SUCCESS_5);
	};

	private String getByNodoMenuAndRoles(Integer idNodoMenu, String idRoles) throws Exception {
		PermisoSeguridadDAO permisoSeguridadDAO = new PermisoSeguridadDAO();
		VistaOperacionCustomDAO vistaOperacionCustomDAO = new VistaOperacionCustomDAO();
		VistaOperacionBasicoDAO vistaOperacionBasicoDAO = new VistaOperacionBasicoDAO();

		List<PermisoSeguridad> permisoSeguridads = permisoSeguridadDAO.findByNodoMenuAndRoles(idNodoMenu, idRoles);
		
		Set<Operacion> operaciones = new HashSet<Operacion>();
		
		for (PermisoSeguridad permisoSeguridad : permisoSeguridads) {
			Operacion operacion = 
					OperacionHelper.getPorId(permisoSeguridad.getOperacion());
			
			Vista vista = permisoSeguridad.getFkNodoMenu().getFkVista();
			
			if (permisoSeguridad.getOperacion() > 4 || permisoSeguridad.getOperacion() == 0) {
				VistaOperacionCustom vistaOperacionCustom = 
						vistaOperacionCustomDAO.findByVistaAndOperacion(vista.getIdVista(), permisoSeguridad.getOperacion());
				
				if (vistaOperacionCustom != null) {
					operacion.setFkIconSclass(vistaOperacionCustom.getFkIconSclass());
					operacion.setFkSclass(vistaOperacionCustom.getFkSclass());
					operacion.setTooltiptext(vistaOperacionCustom.getTooltiptext());
					operacion.setNombre(vistaOperacionCustom.getNombre());
				}
			} else {
				VistaOperacionBasico vistaOperacionBasico = 
						vistaOperacionBasicoDAO.findByVistaAndOperacion(vista.getIdVista(), permisoSeguridad.getOperacion());
				
				if (vistaOperacionBasico != null) {
					operacion.setFkIconSclass(vistaOperacionBasico.getFkIconSclass());
					operacion.setFkSclass(vistaOperacionBasico.getFkSclass());
					operacion.setTooltiptext(vistaOperacionBasico.getTooltiptext());
					operacion.setNombre(vistaOperacionBasico.getNombre());
				}
			}
			
			operaciones.add(operacion);
		}
		
		return buildAnswerSuccess(new ArrayList<Operacion>(operaciones), SUCCESS_2);
	}
	
	private String getBotonesComunes() throws Exception {
		VistaOperacionBasicoDAO vistaOperacionBasicoDAO = new VistaOperacionBasicoDAO();
		List<Operacion> operaciones = new ArrayList<Operacion>();
		
		for(int i = 1; i < 5; i++) {
			Operacion operacion = new Operacion();
			operacion.setIdOperacion(i);
			
			VistaOperacionBasico vistaOperacionBasico = 
					vistaOperacionBasicoDAO.findOneByOperacion(i);
			
			if (vistaOperacionBasico != null) {
				operacion.setFkIconSclass(vistaOperacionBasico.getFkIconSclass());
				operacion.setFkSclass(vistaOperacionBasico.getFkSclass());
				operacion.setTooltiptext(vistaOperacionBasico.getTooltiptext());
				operacion.setNombre(vistaOperacionBasico.getNombre());
			}
			operaciones.add(operacion);
		}
		
		return buildAnswerSuccess(operaciones, SUCCESS_2);
	}
}
