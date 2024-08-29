package api.models.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginRequestModel {
    Boolean rememberMe;
    private String email, password;

    public String loginBodyModel() {
        return "email=" + email + "&password=" + password + "&rememberMe=" + rememberMe;
    }
}
