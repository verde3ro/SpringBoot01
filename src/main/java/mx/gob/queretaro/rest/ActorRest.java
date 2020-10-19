package mx.gob.queretaro.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.gob.queretaro.exception.InternalException;
import mx.gob.queretaro.service.IActorService;

@RestController
@RequestMapping("api/actor")
@Tag(name="Actor End Point", description="Realiza operaciones obre la tabla Actores")
public class ActorRest {

	private final IActorService actorService;

	@Autowired
	public ActorRest(IActorService actorService) {
		this.actorService = actorService;
	}

	@Operation(summary = "Obtener Todos", description = "Obtiene todos los actores registrados en base de datos")
	@GetMapping(path="obtenerTodos", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> obtenerTodos() {
		Map<String, Object> resultado = new HashMap<>();

		try {
			resultado.put("estado", "exito");
			resultado.put("datos", actorService.obtenerTodos());
		} catch (InternalException ex) {
			resultado.put("estado", "error");
			resultado.put("datos", ex.getMessage());
		}

		return resultado;
	}

}
