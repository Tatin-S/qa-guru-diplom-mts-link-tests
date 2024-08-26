package api.models.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetEventResponseModel {
    Integer id;
    String name;
    String status;
    AccessSettingsModel accessSettings;

    @AllArgsConstructor
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AccessSettingsModel {
        Boolean isPasswordRequired, isRegistrationRequired, isModerationRequired;
    }
}

