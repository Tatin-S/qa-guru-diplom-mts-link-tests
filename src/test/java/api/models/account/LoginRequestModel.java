package api.models.account;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginRequestModel {
    private String email, password;
    Boolean rememberMe;

   // public String loginBodyModel() {
      //  return "email=" + email + "&password=" + password + "&rememberMe=" + rememberMe;
  //  }
}
