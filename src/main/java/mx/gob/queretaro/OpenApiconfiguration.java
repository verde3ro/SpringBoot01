package mx.gob.queretaro;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiconfiguration {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().
				info(new Info()
						.title("Sakila RESTful")
						.version("1.0")
						.description("Proyecto de Spring Boot con Web services RESTful de la base de datos Sakila")
						.contact(new Contact()
								.name("Raúl Alejandro Verde Martínez")
								.email("verde3ro@gmail.com")));
	}

	@Bean
	public GroupedOpenApi cityOpenApi() {
		String[] paths = {"/ciudad/**"};

		return GroupedOpenApi
				.builder()
				.group("ciudad")
				.pathsToExclude(paths)
				.build();
	}

}
