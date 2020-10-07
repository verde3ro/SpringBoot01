package mx.gob.queretaro.service;

import java.util.List;

import mx.gob.queretaro.exception.InternalException;
import mx.gob.queretaro.model.Country;

public interface ICountryService {

	List<Country> obtenerTodos() throws InternalException;

	List<Country> obtenerTodosOrdenadosPorId() throws InternalException;

	Country obtenerPorIdYPais(Short id, String country) throws InternalException;

}
