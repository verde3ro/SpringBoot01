package mx.gob.queretaro.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.gob.queretaro.exception.InternalException;
import mx.gob.queretaro.model.Country;
import mx.gob.queretaro.service.ICountryService;

@RestController
@RequestMapping("api/pais")
public class CountryRest {

	private final ICountryService countryService;

	@Autowired
	public CountryRest(ICountryService countryService) {
		this.countryService = countryService;
	}

	@GetMapping(path = "obtenerTodos", produces = MediaType.APPLICATION_JSON_VALUE) // api/pais/obtenerTodos
	public Map<String, Object> obtenerTodos() {
		Map<String, Object> resultado = new HashMap<>();

		try {
			resultado.put("estado", "exito");
			resultado.put("datos", countryService.obtenerTodosOrdenadosPorId());
		} catch (InternalException ex) {
			resultado.put("estado", "error");
			resultado.put("datos", ex.getMessage());
		}

		return resultado;
	}


	@GetMapping(path = "obtenerPorIdYPais/{id}/{country}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> obtenerPorIdYPais(@PathVariable("id") Short id, @PathVariable("country") String country) {
		Map<String, Object> resultado = new HashMap<>();

		try {
			resultado.put("estado", "exito");
			resultado.put("datos", countryService.obtenerPorIdYPais(id, country));
		} catch (InternalException ex) {
			resultado.put("estado", "error");
			resultado.put("datos", ex.getMessage());
		}

		return resultado;
	}

	@GetMapping(path = "obtenerPorId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> obtenerPorId(@PathVariable("id") Short id) {
		Map<String, Object> resultado = new HashMap<>();

		try {
			resultado.put("estado", "exito");
			resultado.put("datos", countryService.obtenerPorId(id));
		} catch (InternalException ex) {
			resultado.put("estado", "error");
			resultado.put("datos", ex.getMessage());
		}

		return resultado;
	}

	@GetMapping(path = "obtenerPaginacion", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> obtenerPaginacion(
			@RequestParam(value = "limit") int limit,
			@RequestParam(value = "offset") int offset,
			@RequestParam(value = "order") String order,
			@RequestParam(value = "sort") String sort,
			@RequestParam(value = "search", required = false, defaultValue = "") String search
			) {
		Page<Country> countries;
		Map<String, Object> paginacion = new HashMap<>();

		try {
			countries = countryService.obtenerPaginacion(limit, offset, order, sort, search);

			paginacion.put("total", (countries != null) ? countries.getTotalElements() : 0L); // Operador condicional ternario
			paginacion.put("rows", (countries != null) ? countries.getContent() : new ArrayList<>()); // Operador condicional ternario

			return new ResponseEntity<>(paginacion, HttpStatus.OK);
		} catch (InternalException ex) {
			return new ResponseEntity<>(paginacion, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
