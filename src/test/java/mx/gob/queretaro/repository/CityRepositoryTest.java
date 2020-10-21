package mx.gob.queretaro.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mx.gob.queretaro.model.City;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class CityRepositoryTest {

	@Autowired
	private ICityRepository cityRepository;

	@Test
	public void findAll_basic() { //Recordar nombrar el metodo como la acción a realizar
		List<City> cities = cityRepository.findAll();

		assertThat(!cities.isEmpty()); //Compruebo que existan datos en la tabla
		assertThat(cities.get(0).getCityId()).isEqualTo((short) 1);
	}

}
