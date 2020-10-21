package mx.gob.queretaro.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import mx.gob.queretaro.exception.InternalException;
import mx.gob.queretaro.model.City;

@SpringBootTest
public class CityServiceImplTest {

	@Autowired
	private ICityService cityService;

	@Test
	public void obtenerPorId_basico() throws InternalException {
		City city = cityService.obtenerPorId( (short) 2);

		assertThat(city.getCity()).containsSequence("Abha");
	}

}
