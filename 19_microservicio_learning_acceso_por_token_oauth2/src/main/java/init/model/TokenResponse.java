package init.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

////para manejar el token en bruto, tenemos que convertir el json en una clase java, un bean.


@NoArgsConstructor
@AllArgsConstructor
@Data


public class TokenResponse {
	
	private String access_token;
	

}
