package mx.gob.queretaro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.gob.queretaro.model.City;

public interface ICityRepository extends JpaRepository<City, Short> {

}
