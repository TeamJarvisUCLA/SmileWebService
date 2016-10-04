package ve.smile.services;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lights.core.services.FachadaService;
import ve.smile.dao.ComunidadDAO;
import ve.smile.dao.ContactoPortalDAO;
import ve.smile.dto.ContactoPortal;
import ve.smile.enums.EstatusContactoEnum;
import ve.smile.payload.request.PayloadContactoPortalRequest;

import com.google.gson.Gson;

@Path("/ContactoPortalService")
public class ContactoPortalServiceM extends FachadaService<ContactoPortal> {

	@POST
	@Path("/incluirContactoPortal")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String incluirContactoPortal(String data) {
		Gson gson = new Gson();
		PayloadContactoPortalRequest request = gson.fromJson(data,
				PayloadContactoPortalRequest.class);
		Map<String, Object> mapa = new HashMap<String, Object>();
		try {
			ComunidadDAO comunidadDAO = new ComunidadDAO();
			ContactoPortalDAO contactoPortalDAO = new ContactoPortalDAO();
			// Comunidad comundidad = comunidadDAO.findByCorreo(request
			// .getObjeto().getFkComunidad().getCorreo());
			// if (co) {
			//
			// }
			comunidadDAO.save(request.getObjeto().getFkComunidad());
			request.getObjeto().setEstatusContacto(
					EstatusContactoEnum.PENDIENTE.ordinal());
			ContactoPortal contactoPortal = contactoPortalDAO.save(request
					.getObjeto());
			mapa.put("id", contactoPortal.getIdContactoPortal());
			return buildAnswerSuccess(SUCCESS_4, mapa);
		} catch (Exception e) {
			return buildAnswerError(e);
		}

	}
}
