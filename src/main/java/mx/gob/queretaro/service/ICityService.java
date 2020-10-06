package mx.gob.queretaro.service;

import java.util.List;

import org.springframework.data.domain.Page;

import mx.gob.queretaro.exception.InternalException;
import mx.gob.queretaro.model.City;
import mx.gob.queretaro.request.CityRequest;

public interface ICityService {

	List<City> obtenerTodos() throws InternalException;

	City obtenerPorId(short id) throws InternalException;

	Page<City> obtenerPaginacion(int limit, int offset, String order, String sort, String search) throws InternalException;

	void guardar(CityRequest cityRequest) throws InternalException;

}
