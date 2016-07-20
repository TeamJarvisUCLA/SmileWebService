package lights.seguridad.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lights.core.services.FachadaService;
import lights.seguridad.dao.VistaDAO;
import lights.seguridad.dao.VistaOperacionCustomDAO;
import lights.seguridad.dto.Operacion;
import lights.seguridad.dto.Vista;
import lights.seguridad.dto.VistaOperacionCustom;
import lights.seguridad.enums.AccionEnum;

@Path("/VistaService")
public class VistaServiceM extends FachadaService<Vista> {

	@GET
	@Path("/find/allWithCustomButtons/{idSesion}/{accessToken}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String pathConsultarTodosConBotonesCustom(@PathParam("idSesion") Integer idSesion, 
			@PathParam("accessToken") String accessToken) {
		try {
			if (validarSesion(idSesion, accessToken)) {
				return consultarTodos(idSesion);
			}
		} catch (Exception e) {
			return buildAnswerError(e);
		}
		
		return buildAnswerError(new Exception(ERROR_UNKNOWN));
	}
	
	public String consultarTodos(Integer idSesion) throws Exception {
		VistaDAO vistaDAO = new VistaDAO();
		
		List<Vista> vistas = vistaDAO.findAll();

		for (int i = vistas.size() - 1; i >= 0; i--) {
			Vista vista = vistas.get(i);
			
			setOperacionesCustomDeVista(vista);
			
			if (vista.getOperaciones().size() == 0) {
				vistas.remove(vista);
			}
		}
		
		auditar(idSesion, getTable() , AccionEnum.CONSULTAR.ordinal(), getPath().substring(1) + ".CONSULTAR_TODOS", 0, "");
		
		if (vistas.size() == 0) {
			return buildAnswerInformation(vistas, INFORMATION_2);
		}
		
		return buildAnswerSuccess(vistas, SUCCESS_2);
	};
	
	private void setOperacionesCustomDeVista(Vista vista) {
		VistaOperacionCustomDAO vistaOperacionCustomDAO = new VistaOperacionCustomDAO();
		
		List<Operacion> operaciones = new ArrayList<Operacion>();
		
		List<VistaOperacionCustom> vistasOperacionesCustom = 
				vistaOperacionCustomDAO.findByVista(vista.getIdVista());
		
		for (VistaOperacionCustom vistaOperacionCustom : vistasOperacionesCustom) {
			Operacion operacion = new Operacion(vistaOperacionCustom.getOperacion(),
					vistaOperacionCustom.getNombre(), 
					vistaOperacionCustom.getFkIconSclass(), 
					vistaOperacionCustom.getFkSclass(), 
					vistaOperacionCustom.getTooltiptext());
			
			operaciones.add(operacion);
		}
	
		vista.setOperaciones(operaciones);
	}
}
