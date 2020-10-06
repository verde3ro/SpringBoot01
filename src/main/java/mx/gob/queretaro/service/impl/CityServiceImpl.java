package mx.gob.queretaro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import mx.gob.queretaro.exception.InternalException;
import mx.gob.queretaro.model.City;
import mx.gob.queretaro.model.Country;
import mx.gob.queretaro.repository.ICityRepository;
import mx.gob.queretaro.request.CityRequest;
import mx.gob.queretaro.service.ICityService;

@Service
@Transactional
@Slf4j
public class CityServiceImpl implements ICityService {

	private final ICityRepository cityRepository;

	@Autowired
	public CityServiceImpl (ICityRepository cityRepository) {
		this.cityRepository = cityRepository;
	}

	@Override
	public List<City> obtenerTodos() throws InternalException {
		try {
			return cityRepository.findAll();
		} catch (Exception ex) {
			log.error("Ocurrio un eror al obtener las ciudades", ex);
			throw new InternalException("Ocurrio un eror al obtener las ciudades");
		}
	}

	@Override
	public City obtenerPorId(short id) throws InternalException {
		if (id > 0L) {
			try {
				return cityRepository.findById(id).orElse(new City());
			} catch (Exception ex) {
				log.error(String.format("Ocurrio un eror al obtener la ciudad con el id: %d", id), ex);
				throw new InternalException(String.format("Ocurrio un eror al obtener la ciudad con el id: %d", id));
			}
		} else {
			throw new InternalException("El id de la ciudad debe ser mayor a 0");
		}
	}


	@Override
	@Transactional(rollbackFor = Exception.class)
	public void guardar(CityRequest cityRequest) throws InternalException {
		try {
			System.out.println("getLastUpdate = " + cityRequest.getLastUpdate());
			City city = new City();

			city.setCity(cityRequest.getCity().trim());
			city.setCountry(new Country(cityRequest.getCountryId()));
			city.setLastUpdate(cityRequest.getLastUpdate());
			city.setStatus("AC");

			cityRepository.save(city);
		} catch (Exception ex) {
			log.error("Ocurrio un eror al guardar la ciudad", ex);
			throw new InternalException("Ocurrio un eror al guardar la ciudad");
		}
	}

	@PostAuthorize("hasRole('ADMIN')")
	@Override
	public Page<City> obtenerPaginacion(int limit, int offset, String order, String sort, String search)
			throws InternalException {
		try {
			return cityRepository.findAll(PageRequest.of((offset / limit), limit, Sort.by(new Sort.Order((order.equals("asc")) ? Sort.Direction.ASC : Sort.Direction.DESC, sort))));
		} catch (Exception ex) {
			log.error("Ocurrio un eror al obtener la paginación de las ciudades", ex);
			throw new InternalException("Ocurrio un eror al obtener la paginación de las ciudades");
		}
	}

}
