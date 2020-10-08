package mx.gob.queretaro.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mx.gob.queretaro.model.Country;

public interface ICountryRepository extends JpaRepository<Country, Short> {


	@Query("SELECT NEW Country(c.countryId, c.country) FROM Country c ORDER BY c.countryId ASC")
	List<Country> obtenerTodos();

	Country findByCountryIdAndCountryOrderByLastUpdate(Short id, String country);

	@Query("SELECT NEW Country(c.countryId, c.country) FROM Country c WHERE c.countryId = :id")
	Country obtenerPorId(@Param("id") Short id);

	@Query("SELECT NEW Country(c.countryId, c.country) FROM Country c WHERE "
			+ "CAST(c.countryId as string) LIKE CONCAT('%',:search,'%') OR UPPER(c.country) LIKE CONCAT('%',:search,'%')")
	Page<Country> obtenerPaginacion(@Param("search") String search, Pageable pageable);

}
