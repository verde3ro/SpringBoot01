package mx.gob.queretaro.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mx.gob.queretaro.model.Country;
import mx.gob.queretaro.response.CountryResponse;

public interface ICountryRepository extends JpaRepository<Country, Short> {


	@Query("SELECT NEW Country(c.countryId, c.country) FROM Country c ORDER BY c.countryId ASC")
	List<Country> obtenerTodos();

	Country findByCountryIdAndCountryOrderByLastUpdate(Short id, String country);

	@Query("SELECT NEW Country(c.countryId, c.country) FROM Country c WHERE c.countryId = :id")
	Country obtenerPorId(@Param("id") Short id);

	@Query("SELECT NEW Country(c.countryId, c.country) FROM Country c WHERE "
			+ "CAST(c.countryId as string) LIKE CONCAT('%',:search,'%') OR UPPER(c.country) LIKE CONCAT('%',:search,'%')")
	Page<Country> obtenerPaginacion(@Param("search") String search, Pageable pageable);

	@Query("SELECT SUM(c.countryId) FROM Country c")
	Long obtenerSuma();

	@Query("SELECT NEW mx.gob.queretaro.response.CountryResponse(c1.countryId, c1.country, c2.cityId, c2.city) FROM Country c1 "
			+ "JOIN c1.cities c2 WHERE c1.countryId = :countryId AND c2.cityId = :cityId")
	CountryResponse obtenerPaisCiudadPorIdPaisYIdCiudad(@Param("countryId") Short countryId, @Param("cityId") Short cityId);

	@Query(value="SELECT country FROM country WHERE (country LIKE :country%) ORDER BY country_id DESC", nativeQuery = true) // Query nativo con el nombe de la tabla y campo
	List<String> obtenerNombrePaisPorPais(@Param("country") String country);

}
