package mx.gob.queretaro.request;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(name = "Country", description = "Recibe los datos para crear un nuevo país en base de datos")
public class CountryRequest implements Serializable {

	private static final long serialVersionUID = 5810830049321230892L;

	@NotNull
	@NotEmpty(message = "El nombre del país no debe ser vacío")
	@Size(min = 5, max = 20)
	private String country;

}
