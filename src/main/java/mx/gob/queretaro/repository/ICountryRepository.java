package mx.gob.queretaro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mx.gob.queretaro.model.Country;

public interface ICountryRepository extends JpaRepository<Country, Short> {


	@Query("SELECT NEW Country(c.countryId, c.country) FROM Country c ORDER BY c.countryId ASC")
	List<Country> obtenerTodos();

	Country findByCountryIdAndCountry(Short id, String country);

}
