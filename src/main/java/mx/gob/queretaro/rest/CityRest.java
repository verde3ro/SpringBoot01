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
import mx.gob.queretaro.model.City;
import mx.gob.queretaro.service.ICityService;

@RestController
@RequestMapping("ciudad")
public class CityRest {

	private final ICityService cityService;

	@Autowired
	public CityRest(ICityService cityService) {
		this.cityService = cityService;
	}

	@GetMapping(path ="saludo", produces = MediaType.APPLICATION_JSON_VALUE)
	public String hello() {
		return "Hola mundo desde un Rest";
	}

	@GetMapping("saludo2")
	public String hello2() {
		return "Hola";
	}

	@GetMapping(path="obtenerTodos", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> obtenerTodos() {
		Map<String, Object> resultado = new HashMap<>();

		try {
			resultado.put("estado", "exito");
			resultado.put("datos", cityService.obtenerTodos());
		} catch (InternalException ex) {
			resultado.put("estado", "error");
			resultado.put("datos", ex.getMessage());
		}

		return resultado;
	}

	@GetMapping(path="obtenerPorId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> obtenerPorId(
			@PathVariable("id") short id
			) {
		Map<String, Object> resultado = new HashMap<>();

		try {
			resultado.put("estado", "exito");
			resultado.put("datos", cityService.obtenerPorId(id));
		} catch (InternalException ex) {
			resultado.put("estado", "error");
			resultado.put("datos", ex.getMessage());
		}

		return resultado;
	}

	@GetMapping(path="obtenerPorId", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> obtenerPorIdRequestParm(
			@RequestParam("id") short id,
			@RequestParam("ejemplo") String ejemplo
			) {
		Map<String, Object> resultado = new HashMap<>();

		try {
			resultado.put("estado", "exito");
			resultado.put("datos", cityService.obtenerPorId(id));
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
		Page<City> cities;
		Map<String, Object> paginacion = new HashMap<>();

		try {
			cities = cityService.obtenerPaginacion(limit, offset, order, sort, search);

			paginacion.put("total", (cities != null) ? cities.getTotalElements() : 0L); // Operador condicional ternario
			paginacion.put("rows", (cities != null) ? cities.getContent() : new ArrayList<>()); // Operador condicional ternario

			return new ResponseEntity<>(paginacion, HttpStatus.OK);
		} catch (InternalException ex) {
			return new ResponseEntity<>(paginacion, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
