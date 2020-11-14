package mx.gob.queretaro.repository.impl;

import org.springframework.stereotype.Repository;

import mx.gob.queretaro.repository.IHelloRepository;

@Repository
public class HelloRepositoryImpl implements IHelloRepository{

	@Override
	public String get() {
		return "Hello JUnit 5";
	}

}
