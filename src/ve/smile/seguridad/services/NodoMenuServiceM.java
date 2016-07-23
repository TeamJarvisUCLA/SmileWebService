package ve.smile.seguridad.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lights.core.services.FachadaService;
import ve.smile.seguridad.dao.NodoMenuDAO;
import ve.smile.seguridad.dao.PermisoSeguridadDAO;
import ve.smile.seguridad.dao.VistaOperacionBasicoDAO;
import ve.smile.seguridad.dao.VistaOperacionCustomDAO;
import ve.smile.seguridad.dto.NodoMenu;
import ve.smile.seguridad.dto.Operacion;
import ve.smile.seguridad.dto.PermisoSeguridad;
import ve.smile.seguridad.dto.Vista;
import ve.smile.seguridad.dto.VistaOperacionBasico;
import ve.smile.seguridad.dto.VistaOperacionCustom;
import ve.smile.seguridad.enums.AccionEnum;
import ve.smile.seguridad.enums.TipoNodoMenuEnum;
import ve.smile.seguridad.enums.helper.OperacionHelper;

@Path("/NodoMenuService")
public class NodoMenuServiceM extends FachadaService<NodoMenu> {
	
	@GET
	@Path("/consultarNodoMenuPorRoles/{idSesion}/{accessToken}/{idRoles}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String consultarNodoMenuPorRoles(@PathParam("idSesion") Integer idSesion, @PathParam("accessToken") String accessToken,
			@PathParam("idRoles") String idRoles) {
		try {
			if (validarSesion(idSesion, accessToken)) {
				NodoMenuDAO nodoMenuDAO = new NodoMenuDAO();
				
				List<NodoMenu> listasDeNodosMenues = new ArrayList<NodoMenu>();
				
				List<NodoMenu> nodosHijos = nodoMenuDAO.findByRolInPermisoSeguridad(idRoles);
				
				for (NodoMenu nodoMenuHoja : nodosHijos) {
					Stack<NodoMenu> pila = new Stack<NodoMenu>();
					
					pila.push(new NodoMenu(nodoMenuHoja));
					
					Integer padre = nodoMenuHoja.getFkNodoMenu().getIdNodoMenu();
					
					NodoMenu nodoMenuPadre = nodoMenuHoja.getFkNodoMenu();
					
					while(padre != 0) {
						if (nodoMenuPadre == null) {
							throw new Exception("Error Code: 555-Hay un error en la base de datos. Permisos inaccesibles (Servicios Web)");
						}
						
						pila.push(new NodoMenu(nodoMenuPadre));
						
						padre = nodoMenuPadre.getFkNodoMenu().getIdNodoMenu();
						
						nodoMenuPadre = nodoMenuPadre.getFkNodoMenu();
					}
					
					NodoMenu nodoMenuRoot = new NodoMenu();
					nodoMenuRoot.setNombre("Raiz");
					nodoMenuRoot.setIdNodoMenu(0);
					nodoMenuRoot.setTipoNodoMenuEnum(TipoNodoMenuEnum.RAIZ);
					
					nodoMenuPadre = nodoMenuRoot;
					
					while(!pila.isEmpty()) {
						List<NodoMenu> hAux = new ArrayList<NodoMenu>();
						
						NodoMenu nodoMenuHijo = pila.pop();
						
						hAux.add(nodoMenuHijo);
						
						nodoMenuPadre.setHijos(hAux);
						
						nodoMenuPadre = nodoMenuHijo;
					}
					
					listasDeNodosMenues.add(nodoMenuRoot);
				}
				
				if (listasDeNodosMenues.size() == 0) {
					return buildAnswerWarning("Warning Code: 666-No tiene habilitado funciones dentro del sistema.");
				}
				
				NodoMenu nodoMenuPrincipal = listasDeNodosMenues.get(0);
				
				listasDeNodosMenues.remove(0);
				
				for (NodoMenu nodoMenuAUnir: listasDeNodosMenues) {
					nodoMenuPrincipal = unirListas(nodoMenuPrincipal, nodoMenuAUnir);
				}
				
				auditar(idSesion, getTable() , AccionEnum.CONSULTAR.ordinal(), getPath().substring(1) + ".CONSULTAR_CRITERIOS", 0, idRoles);
				
				return buildAnswerSuccess(nodoMenuPrincipal, SUCCESS_1);
			}
		} catch (Exception e) {
			return buildAnswerError(e);
		}
		
		return buildAnswerError(new Exception(ERROR_UNKNOWN));
	}
	
	private NodoMenu unirListas(NodoMenu nodoMenuPrincipal, NodoMenu nodoMenuAUnir) {
		List<NodoMenu> nodosMenuHijosDePrincipal = nodoMenuPrincipal.getHijos();
		
		nodoMenuAUnir = nodoMenuAUnir.getHijos().get(0);
		//Arbol izquierdo sin hijos? Entonces coloquele como hijo, el arbol derecho.
		if (nodosMenuHijosDePrincipal == null || nodosMenuHijosDePrincipal.isEmpty()) {
			List<NodoMenu> nodoMenuRestantes = new ArrayList<NodoMenu>();
			
			nodoMenuRestantes.add(nodoMenuAUnir);
			
			nodoMenuPrincipal.setHijos(nodoMenuRestantes);
			
			return nodoMenuPrincipal;
		}
		//Arbol izquierdo tiene como hijo al derecho? Adentrarse al arbol recursivamente.
		for (NodoMenu nodoMenuHija : nodosMenuHijosDePrincipal) {	
			if (nodoMenuHija.getIdNodoMenu().equals(nodoMenuAUnir.getIdNodoMenu())) {
				
				nodoMenuPrincipal.getHijos().remove(nodoMenuHija);
				
				nodoMenuHija = unirListas(nodoMenuHija, nodoMenuAUnir);
				
				nodoMenuPrincipal.getHijos().add(nodoMenuHija);
				
				return nodoMenuPrincipal;
			}
		}
		
		//Arbol izquierdo NO tiene como hijo al derecho? Entonces unalo al arbol izquierdo. 
		nodoMenuPrincipal.getHijos().add(nodoMenuAUnir);
		
		return nodoMenuPrincipal;
	}
	
	@GET
	@Path("/eliminarEnCascada/{idSesion}/{accessToken}/{id}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String pathEliminarEnCascada(@PathParam("idSesion") Integer idSesion, @PathParam("accessToken") String accessToken, @PathParam("id") Integer id) {
		try {
			if (validarSesion(idSesion, accessToken)) {
				return eliminarEnCascada(idSesion, id);
			}
		} catch (Exception e) {
			return buildAnswerError(e);
		}
		
		return buildAnswerError(new Exception(ERROR_UNKNOWN));
	}
	
	public String eliminarEnCascada(Integer idSesion, Integer id) throws Exception {
		auditar(idSesion, getTable() , AccionEnum.CONSULTAR.ordinal(), getPath().substring(1) + ".ELIMINAR", id, "");
		
		NodoMenuDAO nodoMenuDAO = new NodoMenuDAO();
		PermisoSeguridadDAO permisoSeguridadDAO = new PermisoSeguridadDAO();
		
		List<PermisoSeguridad> permisosSeguridad =
				permisoSeguridadDAO.findByNodoMenu(id);
		
		for (PermisoSeguridad permisoSeguridad : permisosSeguridad) {
			permisoSeguridadDAO.removeById(permisoSeguridad.getIdPermisoSeguridad());
		}
		
		if (nodoMenuDAO.removeById(id)) {
			return buildAnswerSuccess(SUCCESS_3);
		}
		
		return buildAnswerError(new Exception(ERROR_4));
	};
	
	@GET
	@Path("/consultarArbol/{idSesion}/{accessToken}/{idRol}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String consultarArbol(@PathParam("idSesion") Integer idSesion, 
			@PathParam("accessToken") String accessToken, @PathParam("idRol") Integer idRol) {
		try {
			if (validarSesion(idSesion, accessToken)) {
				
				NodoMenu nodoMenuPrincipal =
						new NodoMenuDAO().find(0);
				
				nodoMenuPrincipal.setHijos(buscarTodosLosHijos(nodoMenuPrincipal.getIdNodoMenu(), idRol));
				
				auditar(idSesion, getTable() , AccionEnum.CONSULTAR.ordinal(), getPath().substring(1) + ".CONSULTAR_TODOS", 0, String.valueOf(idRol));
				
				return buildAnswerSuccess(nodoMenuPrincipal, SUCCESS_1);
			}
		} catch (Exception e) {
			return buildAnswerError(e);
		}
		
		return buildAnswerError(new Exception(ERROR_UNKNOWN));
	}
	
	private List<NodoMenu> buscarTodosLosHijos(Integer idNodoMenu, Integer idRol) {
		List<NodoMenu> hijos = 
				new NodoMenuDAO().findByNodoMenu(idNodoMenu);
		
		for (NodoMenu nodoMenu : hijos) {
			setOperacionesDeVista(nodoMenu);
			setOperacionesDeNodoMenu(nodoMenu, idRol);
			
			nodoMenu.setHijos(buscarTodosLosHijos(nodoMenu.getIdNodoMenu(), idRol));
		}
		
		return hijos;
	}
	
	private void setOperacionesDeVista(NodoMenu nodoMenu) {
		if (!nodoMenu.getTipoNodoMenuEnum().equals(TipoNodoMenuEnum.TRANSACCION)) {
			return;
		}
		
		VistaOperacionCustomDAO vistaOperacionCustomDAO = new VistaOperacionCustomDAO();
		VistaOperacionBasicoDAO vistaOperacionBasicoDAO = new VistaOperacionBasicoDAO();
		
		List<Operacion> operaciones = new ArrayList<Operacion>();
		
		List<VistaOperacionBasico> vistasOperacionesBasico = 
				vistaOperacionBasicoDAO.findByVista(nodoMenu.getFkVista().getIdVista());
		
		for (VistaOperacionBasico vistaOperacionBasico : vistasOperacionesBasico) {
			Operacion operacion = new Operacion(vistaOperacionBasico.getOperacion(),
					vistaOperacionBasico.getNombre(), 
					vistaOperacionBasico.getFkIconSclass(), 
					vistaOperacionBasico.getFkSclass(), 
					vistaOperacionBasico.getTooltiptext());
			
			operaciones.add(operacion);
		}
		
		List<VistaOperacionCustom> vistasOperacionesCustom = 
				vistaOperacionCustomDAO.findByVista(nodoMenu.getFkVista().getIdVista());
		
		for (VistaOperacionCustom vistaOperacionCustom : vistasOperacionesCustom) {
			Operacion operacion = new Operacion(vistaOperacionCustom.getOperacion(),
					vistaOperacionCustom.getNombre(), 
					vistaOperacionCustom.getFkIconSclass(), 
					vistaOperacionCustom.getFkSclass(), 
					vistaOperacionCustom.getTooltiptext());
			
			operaciones.add(operacion);
		}
	
		nodoMenu.getFkVista().setOperaciones(operaciones);
	}
	
	private void setOperacionesDeNodoMenu(NodoMenu nodoMenu, Integer idRol) {
		if (!nodoMenu.getTipoNodoMenuEnum().equals(TipoNodoMenuEnum.TRANSACCION)) {
			return;
		}
		
		PermisoSeguridadDAO permisoSeguridadDAO = new PermisoSeguridadDAO();
		VistaOperacionCustomDAO vistaOperacionCustomDAO = new VistaOperacionCustomDAO();
		VistaOperacionBasicoDAO vistaOperacionBasicoDAO = new VistaOperacionBasicoDAO();

		List<PermisoSeguridad> permisoSeguridads = permisoSeguridadDAO.findByNodoMenuAndRoles(nodoMenu.getIdNodoMenu(),
				String.valueOf(idRol));
		
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
		
		nodoMenu.setOperaciones(new ArrayList<Operacion>(operaciones));
	}
	
	@GET
	@Path("/countChildrens/{idSesion}/{accessToken}/{idNodoMenuPadre}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String pathCountChildrens(@PathParam("idSesion") Integer idSesion, 
			@PathParam("accessToken") String accessToken,
			@PathParam("idNodoMenuPadre") Integer idNodoMenuPadre) {
		try {
			if (validarSesion(idSesion, accessToken)) {
				return contarHijos(idSesion, idNodoMenuPadre);
			}
		} catch (Exception e) {
			return buildAnswerError(e);
		}
		
		return buildAnswerError(new Exception(ERROR_UNKNOWN));
	}
	
	public String contarHijos(Integer idSesion, Integer idNodoMenuPadre) throws Exception {
		auditar(idSesion, getTable() , AccionEnum.CONSULTAR.ordinal(), getPath().substring(1) + ".CONTAR", 0, "");
		
		Integer count = new NodoMenuDAO().contarHijos(idNodoMenuPadre);
		
		Map<String, Object> mapa = new HashMap<String, Object>();
		
		mapa.put("count", count);
		
		return buildAnswerSuccess(SUCCESS_7, mapa);
	};
}
