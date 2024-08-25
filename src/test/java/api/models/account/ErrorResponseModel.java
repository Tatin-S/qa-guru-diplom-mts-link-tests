package api.models.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ErrorResponseModel {
    private ErrorData error;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class ErrorData {
        private String message;
    }

}