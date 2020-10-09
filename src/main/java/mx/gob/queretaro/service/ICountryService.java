package mx.gob.queretaro.service;

import java.util.List;

import org.springframework.data.domain.Page;

import mx.gob.queretaro.exception.InternalException;
import mx.gob.queretaro.model.Country;

public interface ICountryService {

	List<Country> obtenerTodos() throws InternalException;

	List<Country> obtenerTodosOrdenadosPorId() throws InternalException;

	Country obtenerPorIdYPais(Short id, String country) throws InternalException;

	Country obtenerPorId(Short id) throws InternalException;

	Page<Country> obtenerPaginacion(int limit, int offset, String order, String sort, String search) throws InternalException;

	Long obtenerSuma() throws InternalException;

}
