package mx.gob.queretaro.service;

import java.util.List;

import mx.gob.queretaro.exception.InternalException;
import mx.gob.queretaro.model.Actor;

public interface IActorService {

	List<Actor> obtenerTodos() throws InternalException;

}
