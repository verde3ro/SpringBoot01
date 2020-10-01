package mx.gob.queretaro.request;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CityRequest implements Serializable {

	private static final long serialVersionUID = -7369519744613007256L;
	@NotNull
	@NotEmpty(message ="El nombre de la ciudad no debe ser vacía")
	@Size(min = 5, max = 20)
	@Pattern(regexp = "^[a-zA-Z]+$", message = "debe contener solo letras")
	private String city;
	@NotNull
	private Short countryId;
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date lastUpdate;

}
