package mx.gob.queretaro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.queretaro.repository.IHelloRepository;
import mx.gob.queretaro.service.IHelloService;

@Service
public class HelloServiceImpl implements IHelloService{

	@Autowired
	private IHelloRepository helloRepository;

	@Override
	public String get() {
		return helloRepository.get();
	}

}
