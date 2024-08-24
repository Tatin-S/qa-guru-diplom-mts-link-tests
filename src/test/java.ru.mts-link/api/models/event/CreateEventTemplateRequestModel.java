package api.models.event;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateEventTemplateRequestModel {

    String name;
    AccessSettingsModel accessSettings;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AccessSettingsModel {
        Boolean isPasswordRequired, isRegistrationRequired, isModerationRequired;
    }
}