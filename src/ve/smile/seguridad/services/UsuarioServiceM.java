package ve.smile.seguridad.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lights.core.encryptor.UtilEncryptor;
import lights.core.services.FachadaService;
import ve.smile.dao.PersonaDAO;
import ve.smile.dto.Persona;
import ve.smile.seguridad.dao.PermisoSeguridadDAO;
import ve.smile.seguridad.dao.SesionDAO;
import ve.smile.seguridad.dao.UsuarioDAO;
import ve.smile.seguridad.dto.Rol;
import ve.smile.seguridad.dto.Sesion;
import ve.smile.seguridad.dto.Usuario;
import ve.smile.seguridad.payload.request.PayloadUsuarioRequest;

import com.google.gson.Gson;

@Path("/UsuarioService")
public class UsuarioServiceM extends FachadaService<Usuario> {
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String pathLogin(String data) {
		Gson gson = new Gson();

		PayloadUsuarioRequest request = gson.fromJson(data,
				PayloadUsuarioRequest.class);

		try {
			return checkLogin(request.getObjeto(),
					(String) request.getParametro("ip"));
		} catch (Exception e) {
			return buildAnswerError(e);
		}
	}

	@GET
	@Path("/consultarPorRol/{idSesion}/{accessToken}/{idRol}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String pathConsultarPorRol(@PathParam("idSesion") Integer idSesion,
			@PathParam("accessToken") String accessToken,
			@PathParam("idRol") Integer idRol) {
		try {
			if (validarSesion(idSesion, accessToken)) {
				return buildAnswerSuccess(new UsuarioDAO().findByRol(idRol),
						SUCCESS_2);
			}
		} catch (Exception e) {
			return buildAnswerError(e);
		}

		return buildAnswerError(new Exception(ERROR_UNKNOWN));
	}

	public String checkLogin(Usuario usuarioToCheck, String ip)
			throws Exception {
		Usuario usuario = new UsuarioDAO().findByCorreo(usuarioToCheck
				.getCorreo());

		if (usuario == null) {
			throw new Exception(
					"Error Code: 007-Este usuario no existe en nuestra base de datos.");
		}

		if (!UtilEncryptor.desencriptar(usuario.getClave()).equals(
				UtilEncryptor.desencriptar(usuarioToCheck.getClave()))) {
			throw new Exception(
					"Error Code: 008-La clave suministrada no coincide con la de la base de datos.");
		}

		if (!usuario.getEstatus().equals("A")) {
			throw new Exception(
					"Error Code: 009-Este usuario se encuentra deshabilitado. Comuniquese con el administrador.");
		}

		Rol rol = usuario.getFkRol();

		if (rol == null) {
			throw new Exception(
					"Error Code: 010-Este usuario no tiene asociado ningún rol dentro del sistema. Comuniquese con el administrador.");
		}

		Integer cantidadPermisos = new PermisoSeguridadDAO().contarDeUnRol(rol
				.getIdRol());

		if (cantidadPermisos == 0) {
			throw new Exception(
					"Error Code: 011-Este usuario, a través de su rol asociado, no tiene permisos dentro del sistema. Comuniquese con el administrador.");
		}

		Long time = Calendar.getInstance().getTimeInMillis();
		// Estatus is Ip
		SesionDAO sesionDAO = new SesionDAO();

		sesionDAO.cerrarSesionesByUsuario(usuario);

		Sesion sesion = new Sesion(usuario, "A", ip,
				generateAccessToken(usuario.getCorreo()), time, time);

		sesion = sesionDAO.save(sesion);

		usuario.setClave(null);

		Map<String, Object> parametros = new HashMap<String, Object>();

		parametros.put("idSesion", sesion.getIdSesion());
		parametros.put("accessToken", sesion.getAccessToken());
		parametros.put("usuario", usuario);

		return buildAnswerSuccess("Success Code: 007-Usuario válido",
				parametros);
	}

	private String generateAccessToken(String correo) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");

			messageDigest
					.update((Calendar.getInstance().getTimeInMillis() + correo)
							.getBytes());

			byte[] digest = messageDigest.digest();

			String accessToken = "";

			for (byte b : digest) {
				accessToken += Integer.toHexString(0xFF & b);
			}

			return accessToken;
		} catch (NoSuchAlgorithmException e) {
			return "ABC";
		}
	}

	@POST
	@Path("/loginAndroid")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String pathLoginAndroid(String data) {
		Gson gson = new Gson();

		PayloadUsuarioRequest request = gson.fromJson(data,
				PayloadUsuarioRequest.class);

		try {
			return checkLoginAndroid(request.getObjeto(),
					(String) request.getParametro("ip"));
		} catch (Exception e) {
			return buildAnswerError(e);
		}
	}

	public String checkLoginAndroid(Usuario usuarioToCheck, String ip)
			throws Exception {
		Usuario usuario = new UsuarioDAO().findByCorreo(usuarioToCheck
				.getCorreo());

		if (usuario == null) {
			throw new Exception(
					"Error Code: 007-Este usuario no existe en nuestra base de datos.");
		}

		if (!UtilEncryptor.desencriptar(usuario.getClave()).equals(
				usuarioToCheck.getClave())) {
			throw new Exception(
					"Error Code: 008-La clave suministrada no coincide con la de la base de datos.");
		}

		if (!usuario.getEstatus().equals("A")) {
			throw new Exception(
					"Error Code: 009-Este usuario se encuentra deshabilitado. Comuniquese con el administrador.");
		}

		Long time = Calendar.getInstance().getTimeInMillis();
		// Estatus is Ip
		SesionDAO sesionDAO = new SesionDAO();

		sesionDAO.cerrarSesionesByUsuario(usuario);

		Sesion sesion = new Sesion(usuario, "A", ip,
				generateAccessToken(usuario.getCorreo()), time, time);

		sesion = sesionDAO.save(sesion);

		Persona persona = new PersonaDAO()
				.findByUsuario(usuario.getIdUsuario());

		usuario.setClave(null);

		Map<String, Object> parametros = new HashMap<String, Object>();

		parametros.put("idSesion", sesion.getIdSesion());
		parametros.put("accessToken", sesion.getAccessToken());
		parametros.put("usuario", usuario);
		parametros.put("persona", persona);

		return buildAnswerSuccess("Success Code: 007-Usuario válido",
				parametros);
	}

}
