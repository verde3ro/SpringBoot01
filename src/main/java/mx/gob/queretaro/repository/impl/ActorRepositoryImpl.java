package mx.gob.queretaro.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import mx.gob.queretaro.exception.InternalException;
import mx.gob.queretaro.model.Actor;
import mx.gob.queretaro.repository.IActorRepository;

@Repository
@Slf4j
public class ActorRepositoryImpl implements IActorRepository {

	@PersistenceContext(unitName = "default")
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Actor> obtenerTodos() throws InternalException {
		try {
			return em.createNamedQuery("Actor.findAll").getResultList();
		} catch (Exception ex) {
			log.error("Ócurrio un error al obtener los actores.", ex);
			throw new InternalException("Ocurrio un eror al obtener los actores");
		}
	}

}
