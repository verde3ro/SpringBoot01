package mx.gob.queretaro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import mx.gob.queretaro.exception.InternalException;
import mx.gob.queretaro.model.Country;
import mx.gob.queretaro.repository.ICountryRepository;
import mx.gob.queretaro.response.CountryResponse;
import mx.gob.queretaro.service.ICountryService;

@Service
@Transactional
@Slf4j
public class CountryServiceImpl implements ICountryService {

	@Autowired
	private ICountryRepository countryRepository;

	@Override
	public List<Country> obtenerTodos() throws InternalException {
		try {
			return countryRepository.obtenerTodos();
		} catch (Exception ex) {
			log.error("Ocurrio un error al obtener los paises", ex);
			throw new InternalException("Ocurrio un error al obtener los paises");
		}
	}

	@Override
	public List<Country> obtenerTodosOrdenadosPorId() throws InternalException {
		try {
			return countryRepository.findAll(Sort.by("countryId").descending()); // Select * from country order by country_id desc
		} catch (Exception ex) {
			log.error("Ocurrio un error al obtener los paises", ex);
			throw new InternalException("Ocurrio un error al obtener los paises");
		}
	}

	@Override
	public Country obtenerPorIdYPais(Short id, String country) throws InternalException {
		if (null != id && null != country && !country.trim().isEmpty()) {
			try {
				return countryRepository.findByCountryIdAndCountryOrderByLastUpdate(id, country);
			} catch (Exception ex) {
				log.error(String.format("Ocurrio un error al obtener el país con el id y el nombre : %d - %s", id, country), ex);
				throw new InternalException(String.format("Ocurrio un error al obtener el país con el id y el nombre : %d - %s", id, country));
			}
		} else {
			throw new InternalException("El id del país y el nombre no deben ser nulos o vacíos");
		}
	}

	@Override
	public Country obtenerPorId(Short id) throws InternalException {
		if (null != id) {
			try {
				return countryRepository.obtenerPorId(id);
			} catch (Exception ex) {
				log.error(String.format("Ocurrio un error al obtener el país con el id: %d ", id), ex);
				throw new InternalException(String.format("Ocurrio un error al obtener el país con el id: %d", id));
			}
		} else {
			throw new InternalException("El id del país no debe ser nulo o vacío");
		}
	}

	@Override
	public Page<Country> obtenerPaginacion(int limit, int offset, String order, String sort, String search)
			throws InternalException {
		try {
			return countryRepository.obtenerPaginacion(search, PageRequest.of((offset / limit), limit, Sort.by(new Sort.Order((order.equals("asc")) ? Sort.Direction.ASC : Sort.Direction.DESC, sort))));
		} catch (Exception ex) {
			log.error("Ocurrio un eror al obtener la paginación de los paises", ex);
			throw new InternalException("Ocurrio un eror al obtener la paginación de los paises");
		}
	}

	@Override
	public Long obtenerSuma() throws InternalException {
		try {
			return countryRepository.obtenerSuma();
		} catch (Exception ex) {
			log.error("Ocurrio un eror al obtener la suma de los paises", ex);
			throw new InternalException("Ocurrio un eror al obtener la suma de los paises");
		}
	}

	@Override
	public CountryResponse obtenerPaisCiudadPorIdPaisYIdCiudad(Short countryId, Short cityId) throws InternalException {
		if (null != countryId && cityId != null) {
			try {
				return countryRepository.obtenerPaisCiudadPorIdPaisYIdCiudad(countryId, cityId);
			} catch (Exception ex) {
				log.error(String.format("Ocurrio un error al obtener el país con el id: %d y el id de ciudad: %d  ", countryId, cityId), ex);
				throw new InternalException(String.format("Ocurrio un error al obtener el país con el id: %d y el id de ciudad: %d  ", countryId, cityId));
			}
		} else {
			throw new InternalException("El id del país y el id de ciudad no debe ser nulos o vacíos");
		}
	}

	@Override
	public List<String> obtenerNombrePaisPorPais(String country) throws InternalException {
		if (null != country) {
			try {
				return countryRepository.obtenerNombrePaisPorPais(country.trim());
			} catch (Exception ex) {
				log.error(String.format("Ocurrio un error al obtener los paises por el nombre: %s ", country), ex);
				throw new InternalException(String.format("Ocurrio un error al obtener los paises por el nombre: %s ", country));
			}
		} else {
			throw new InternalException("El nombre del país no debe ser nulo o vacío");
		}
	}




}
