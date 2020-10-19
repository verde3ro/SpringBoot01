package mx.gob.queretaro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import mx.gob.queretaro.exception.InternalException;
import mx.gob.queretaro.model.Actor;
import mx.gob.queretaro.repository.IActorRepository;
import mx.gob.queretaro.service.IActorService;

@Service
@Transactional
@Slf4j
public class ActorServiceImpl implements IActorService {

	private final IActorRepository actorRepository;

	@Autowired
	public ActorServiceImpl(IActorRepository actorRepository) {
		this.actorRepository = actorRepository;
	}

	@Override
	public List<Actor> obtenerTodos() throws InternalException {
		try {
			return actorRepository.obtenerTodos();
		} catch (Exception ex) {
			log.error("Ócurrio un error al obtener los actores", ex);
			throw new InternalException("Ocurrio un eror al obtener los actores");
		}
	}

}
