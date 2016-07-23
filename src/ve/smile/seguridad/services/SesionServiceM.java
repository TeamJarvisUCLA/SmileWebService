package ve.smile.seguridad.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import com.google.gson.Gson;

import lights.core.googlecode.genericdao.search.Filter;
import lights.core.googlecode.genericdao.search.Search;
import lights.core.services.FachadaService;
import ve.smile.seguridad.dao.AuditoriaDAO;
import ve.smile.seguridad.dao.SesionDAO;
import ve.smile.seguridad.dto.Auditoria;
import ve.smile.seguridad.dto.Sesion;
import ve.smile.seguridad.enums.AccionEnum;
import ve.smile.seguridad.payload.request.PayloadSesionRequest;

@Path("/SesionService")
public class SesionServiceM extends FachadaService<Sesion> {

	@POST
	@Path("/cerrarSesionWeb")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String pathCerrarSesionWeb(String data) {
		Gson gson = new Gson();
		
		PayloadSesionRequest request = (PayloadSesionRequest) gson.fromJson(data, PayloadSesionRequest.class);
		
		try {
			Sesion sesion = request.getObjeto();
			
			if (validarSesion(sesion.getIdSesion(), request.getAccessToken())) {
				sesion = new SesionDAO().find(sesion.getIdSesion());
				
				sesion.setEstado(Sesion.INACTIVO);
				sesion.setFechaFin(Calendar.getInstance().getTimeInMillis());
				
				return modificar(request.getIdSesion(), sesion);
			}
		} catch (Exception e) {
			return buildAnswerError(e);
		}
		
		return buildAnswerError(new Exception(ERROR_UNKNOWN));
	}
	
	@GET
	@Path("/find/paginationWithLastAuditoria/{idSesion}/{accessToken}/{count}/{page}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String pathConsultarPorCriteriosPaginacionWithLastAuditoria(@PathParam("idSesion") Integer idSesion, @PathParam("accessToken") String accessToken,
			@PathParam("count") Integer count, @PathParam("page") Integer page, @Context UriInfo info) {
		MultivaluedMap<String, String> multivaluesMapFilters = info.getQueryParameters();
		
		try {
			if (validarSesion(idSesion, accessToken)) {
				if (multivaluesMapFilters.size() <= 1) {
					return consultarPaginacion(idSesion, count, page, new Search(), "");
				}
				
				TypeQuery typeQuery;
				
				try {
					typeQuery = TypeQuery.valueOf(multivaluesMapFilters.get("typeQueryToFind").get(0));
				} catch (Exception e) {
					typeQuery = TypeQuery.LIKE;
				}
				
				Map<String, Object> mapaFiltros = buildMapFromMultiValuesMap(multivaluesMapFilters);
				
				return consultarPaginacionWithLastAuditoria(idSesion, count, page, buildSearchFromMap(typeQuery, mapaFiltros));
			}
		} catch (Exception e) {
			return buildAnswerError(e);
		}
		
		return buildAnswerError(new Exception(ERROR_UNKNOWN));
	}
	
	public String consultarPaginacionWithLastAuditoria(Integer idSesion, Integer totalElementsByPage, Integer page, Search search) throws Exception {
		SesionDAO sesionDAO = new SesionDAO();
		AuditoriaDAO auditoriaDAO = new AuditoriaDAO();
		
		Integer totalElementsInTable = sesionDAO.count(search);
		
		boolean firstPage = true;
		boolean lastPage = true;
		
		Integer totalPaginas = totalElementsInTable / totalElementsByPage;
		
		if (totalElementsInTable % totalElementsByPage > 0) {
			totalPaginas++;
		}
		
		if (page > totalPaginas) {
			if (totalPaginas == 0) {
				page = 0;
			} else {
				page = 1;
			}
		}
		
		if (page > 1) {
			firstPage = false;
		}
		
		if (page != totalPaginas) {
			lastPage = false;
		}
		
		Integer firstResult = page * totalElementsByPage - totalElementsByPage;
		Integer maxResults = totalElementsByPage;
		
		if (firstResult + totalElementsByPage > totalElementsInTable) {
			maxResults = totalElementsInTable - firstResult;
		}
		
		search.setFirstResult(firstResult);
		search.setMaxResults(maxResults);
		search.addSort("id" + getClassParameterizedType().getSimpleName(), false);
		
		List<Sesion> sesiones = sesionDAO.search(search);
		
		for (Sesion sesion : sesiones) {
			List<Auditoria> auditorias = new ArrayList<Auditoria>();
			
			Auditoria auditoria = auditoriaDAO.findLastAuditoriaOfSesion(sesion.getIdSesion());
			
			if (auditoria != null) {
				auditoria.setFkSesion(null);
				auditorias.add(auditoria);
			}
			
			sesion.setAuditorias(auditorias);
		}
		
		Map<String, Object> mapaPaginacion = new HashMap<String, Object>();
		
		mapaPaginacion.put("page", page);
		mapaPaginacion.put("totalElementsByPage", totalElementsByPage);
		mapaPaginacion.put("totalElementsInTable", totalElementsInTable);
		mapaPaginacion.put("totalPaginas", totalPaginas);
		mapaPaginacion.put("totalElementsReturned", sesiones.size());
		mapaPaginacion.put("firstPage", firstPage);
		mapaPaginacion.put("lastPage", lastPage);
		
		String datos = "totalElementsByPage=" + totalElementsByPage + SEPARATOR + "page=" + page;
		
		for (Filter filter : search.getFilters()) {
			datos += SEPARATOR + filter.getProperty() + " " + getOperator(filter.getOperator()) + " " + filter.getValue();
		}
		
		auditar(idSesion, getTable() , AccionEnum.CONSULTAR.ordinal(), getPath().substring(1) + ".CONSULTAR_PAGINACION_CRITERIOS", 0, datos);
		
		if (sesiones.size() == 0) {
			return buildAnswerInformation(sesiones, INFORMATION_2, mapaPaginacion);
		}

		return buildAnswerSuccess(sesiones, SUCCESS_2, mapaPaginacion);
	};
}
