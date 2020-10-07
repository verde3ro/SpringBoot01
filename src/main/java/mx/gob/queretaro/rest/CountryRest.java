package mx.gob.queretaro.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.gob.queretaro.exception.InternalException;
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
}
