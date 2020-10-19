package mx.gob.queretaro.repository;

import java.util.List;

import mx.gob.queretaro.exception.InternalException;
import mx.gob.queretaro.model.Actor;

public interface IActorRepository {

	List<Actor> obtenerTodos() throws InternalException;

}
