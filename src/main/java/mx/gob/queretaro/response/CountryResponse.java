package mx.gob.queretaro.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CountryResponse implements Serializable {

	private static final long serialVersionUID = 3914188625738707962L;
	private Short countryId;
	private String country;
	private Short cityId;
	private String city;

}
