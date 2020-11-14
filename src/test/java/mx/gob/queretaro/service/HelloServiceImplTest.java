package mx.gob.queretaro.service;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import mx.gob.queretaro.repository.IHelloRepository;
import mx.gob.queretaro.service.impl.HelloServiceImpl;

@SpringBootTest
public class HelloServiceImplTest {

	@Mock
	private IHelloRepository helloRepository; // Injecta objeto de forma normal en un test

	@InjectMocks
	private IHelloService helloService = new HelloServiceImpl(); // Injecta objeto creando un new en un test

	@MockBean
	private IHelloRepository repository;

	@Autowired
	private IHelloService service;

	@BeforeEach
	void setMockOutput() {
		when(helloRepository.get()).thenReturn("Hola Mockito desde Repository");
	}

	@DisplayName("Test Mock helloService + helloRepository") // Solo Junit5
	@Test
	public void prueba_saludo() {
		String hello = helloService.get();
		System.out.println("hello = " + hello);

		assertEquals("Hola Mockito desde Repository", hello);
		Assertions.assertNotNull(hello);
	}


	@Test
	@DisplayName("Test Mockito 2")
	public void prueba_mockito() {
		String saludo = "Hola";

		doReturn(saludo).when(repository).get();
		String hello = service.get();


		Assertions.assertNotNull(hello, "No se encontró el saludo");
		Assertions.assertEquals("Hola", hello, "El saludo devuelto no era el mismo que el mock");
	}

	@Test
	@DisplayName("Test Hamcrest")
	public void prueba_hamcrest() {
		String hello = helloService.get();
		List<String> list = new ArrayList<>();

		assertThat(hello, containsString("Mockito"));
		assertThat(hello, not(containsString("123456")));
		assertThat(hello, not(emptyString()));

		// numeros
		assertThat(5, lessThan(10));
		assertThat(5, lessThanOrEqualTo(5));

		assertThat(list, isA(List.class));
		assertThat(list, empty());

		list.add("Uno");
		list.add("Dos");

		assertThat(list, not(empty()));
		assertThat(list, hasSize(2));
		assertThat(list, containsInAnyOrder("Dos", "Uno"));
		assertThat(list, hasItem("Dos"));
	}
}
