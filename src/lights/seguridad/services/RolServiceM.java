package lights.seguridad.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import lights.core.services.FachadaService;
import lights.seguridad.dao.RolDAO;
import lights.seguridad.dao.UsuarioDAO;
import lights.seguridad.dao.UsuarioRolDAO;
import lights.seguridad.dto.Rol;
import lights.seguridad.dto.Usuario;
import lights.seguridad.dto.UsuarioRol;
import lights.seguridad.enums.AccionEnum;
import lights.seguridad.payload.request.PayloadRolRequest;

@Path("/RolService")
public class RolServiceM extends FachadaService<Rol> {
	
	@GET
	@Path("/consultarDeUnUsuario/{idSesion}/{accessToken}/{idUsuario}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String pathConsultarDeUnUsuario(@PathParam("idSesion") Integer idSesion, @PathParam("accessToken") String accessToken,
			@PathParam("idUsuario") Integer idUsuario) {
		try {
			if (validarSesion(idSesion, accessToken)) {
				List<Rol> roles = new RolDAO().findByUsuario(idUsuario);
				
				return buildAnswerSuccess(roles, SUCCESS_2);
			}
		} catch (Exception e) {
			return buildAnswerError(e);
		}
		
		return buildAnswerError(new Exception(ERROR_UNKNOWN));
	}
	
	@GET
	@Path("/consultarDeUnNodoMenuEnPermiso/{idSesion}/{accessToken}/{idNodoMenu}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String consultarDeUnNodoMenuEnPermiso(@PathParam("idSesion") Integer idSesion, @PathParam("accessToken") String accessToken,
			@PathParam("idNodoMenu") Integer idNodoMenu) {
		try {
			if (validarSesion(idSesion, accessToken)) {
				List<Rol> roles = new RolDAO().findByNodoMenuInPermiso(idNodoMenu);
				
				return buildAnswerSuccess(roles, SUCCESS_2);
			}
		} catch (Exception e) {
			return buildAnswerError(e);
		}
		
		return buildAnswerError(new Exception(ERROR_UNKNOWN));
	}
	
	@POST
	@Path("/incluirConUsuarios")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String pathIncluirConUsuarios(String data) {
		Gson gson = new Gson();
		
		PayloadRolRequest request = (PayloadRolRequest) gson.fromJson(data, PayloadRolRequest.class);
		
		try {
			if (validarSesion(request.getIdSesion(), request.getAccessToken())) {
				UsuarioDAO usuarioDAO = new UsuarioDAO();
				RolDAO rolDAO = new RolDAO();
				UsuarioRolDAO usuarioRolDAO = new UsuarioRolDAO();
				
				String ids = (String) request.getParametro("idUsuarios");
				
				if (ids == null) {
					ids = "";
				}
				
				String[] idUsuarios = (ids).split(",");
				
				List<Usuario> usuarios = new ArrayList<Usuario>();
				//Validar que existan los usuarios suministrados
				for (String idUsuario : idUsuarios) {
					if (idUsuario == null || idUsuario.trim().length() == 0) {
						continue;
					}
					Usuario usuario = usuarioDAO.find(Integer.parseInt(idUsuario));
					
					if (usuario == null) {
						throw new Exception("Error Code: 101-No se encontró un usuario con id " + idUsuario);
					}
					
					usuarios.add(usuario);
				}
				//Guardando el rol
				Rol rol = rolDAO.save(request.getObjeto());
				
				for (Usuario usuario : usuarios) {
					UsuarioRol usuarioRol = new UsuarioRol(usuario, rol);
					
					usuarioRolDAO.save(usuarioRol);
				}
				
				String datos = getDataFromObjectToAuditoria(rol) + SEPARATOR + "idUsuarios=" + request.getParametro("idUsuarios");
				
				Map<String, Object> mapa = new HashMap<String, Object>();
				
				mapa.put("id", rol.getIdRol());
				
				auditar(request.getIdSesion(), getTable() , AccionEnum.INCLUIR.ordinal(),
						getPath().substring(1) + ".INCLUIR", rol.getIdRol(),
						datos);
				
				return buildAnswerSuccess(SUCCESS_4, mapa);
			}
		} catch (Exception e) {
			return buildAnswerError(e);
		}
		
		return buildAnswerError(new Exception(ERROR_UNKNOWN));
	}
	
	@POST
	@Path("/modificarConUsuarios")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String pathModificarConUsuarios(String data) {
		Gson gson = new Gson();
		
		PayloadRolRequest request = (PayloadRolRequest) gson.fromJson(data, PayloadRolRequest.class);
		
		try {
			if (validarSesion(request.getIdSesion(), request.getAccessToken())) {
				UsuarioDAO usuarioDAO = new UsuarioDAO();
				RolDAO rolDAO = new RolDAO();
				UsuarioRolDAO usuarioRolDAO = new UsuarioRolDAO();
				
				String ids = (String) request.getParametro("idUsuarios");
				
				if (ids == null) {
					ids = "";
				}
				
				String[] idUsuarios = (ids).split(",");
				
				List<Usuario> usuariosARemover = usuarioDAO.findByRoles(String.valueOf(request.getObjeto().getIdRol()));
				
				List<Usuario> usuariosAIncluir = new ArrayList<Usuario>();
				
				//Son eliminados de los arreglos aquellos usuarios que coincidan. (Se mantendrán en la base de datos).
				for(int i = idUsuarios.length - 1; i >= 0; i--) {
					if (idUsuarios[i] == null || idUsuarios[i].trim().length() == 0) {
						continue;
					}
					Usuario usuario = new Usuario(Integer.parseInt(idUsuarios[i]));
					
					if (usuariosARemover.contains(usuario)) {
						usuariosARemover.remove(usuario);
						idUsuarios[i] = null;
					}
				}
				
				//Validar que existan los usuarios suministrados
				for (String idUsuario : idUsuarios) {
					if (idUsuario == null || idUsuario.trim().length() == 0) {
						continue;
					}
					Usuario usuario = usuarioDAO.find(Integer.parseInt(idUsuario));
					
					if (usuario == null) {
						throw new Exception("Error Code: 101-No se encontró un usuario con id " + idUsuario);
					}
					
					usuariosAIncluir.add(usuario);
				}
				//Modificando el rol
				Rol rol = rolDAO.save(request.getObjeto());
				
				if (usuariosARemover.size() > 0) {//'Removiendo usuarios no asociados'.
					String idUsuariosARemover = "";
					
					for (Usuario usuario : usuariosARemover) {
						if (idUsuariosARemover.length() > 0) {
							idUsuariosARemover += ",";
						}
						idUsuariosARemover += usuario.getIdUsuario();
					}
					
					List<UsuarioRol> usuarioRolAEliminar = 
							usuarioRolDAO.findByRolAndUsuarios(rol.getIdRol(), idUsuariosARemover);
					
					for (UsuarioRol usuarioRol : usuarioRolAEliminar) {
						usuarioRolDAO.removeById(usuarioRol.getIdUsuarioRol());
					}
				}//Fin 'Removiendo usuarios no asociados'.
				
				for (Usuario usuario : usuariosAIncluir) {//'Incluyendo nuevos usuarios asociados'.
					UsuarioRol usuarioRol = new UsuarioRol(usuario, rol);
					
					usuarioRolDAO.save(usuarioRol);
				}//Fin 'Incluyendo nuevos usuarios asociados'.
				
				String datos = getDataFromObjectToAuditoria(rol) + SEPARATOR + "idUsuarios=" + request.getParametro("idUsuarios");
				
				Map<String, Object> mapa = new HashMap<String, Object>();
				
				mapa.put("id", rol.getIdRol());
				
				auditar(request.getIdSesion(), getTable() , AccionEnum.MODIFICAR.ordinal(),
						getPath().substring(1) + ".MODIFICAR", rol.getIdRol(),
						datos);
				
				return buildAnswerSuccess(SUCCESS_5);
			}
		} catch (Exception e) {
			return buildAnswerError(e);
		}
		
		return buildAnswerError(new Exception(ERROR_UNKNOWN));
	}
	
	@GET
	@Path("/eliminarConUsuarios/{idSesion}/{accessToken}/{id}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String pathEliminarConUsuarios(@PathParam("idSesion") Integer idSesion, @PathParam("accessToken") String accessToken, @PathParam("id") Integer id) {
		try {
			if (validarSesion(idSesion, accessToken)) {
				RolDAO rolDAO = new RolDAO();
				UsuarioRolDAO usuarioRolDAO = new UsuarioRolDAO();
				
				List<UsuarioRol> usuarioRolAEliminar = usuarioRolDAO.findByRol(id);
				
				for (UsuarioRol usuarioRol : usuarioRolAEliminar) {
					usuarioRolDAO.removeById(usuarioRol.getIdUsuarioRol());
				}
				
				auditar(idSesion, getTable() , AccionEnum.ELIMINAR.ordinal(), getPath().substring(1) + ".ELIMINAR", id, "");
				
				if (rolDAO.removeById(id)) {
					return buildAnswerSuccess(SUCCESS_3);
				}
				return buildAnswerError(new Exception(ERROR_4));
			}
		} catch (Exception e) {
			return buildAnswerError(e);
		}
		
		return buildAnswerError(new Exception(ERROR_UNKNOWN));
	}
}
