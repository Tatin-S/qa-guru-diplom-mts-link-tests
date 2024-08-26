package api.models.event;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateEventRequestModel {
    private String description;
    private String lang;
    private Integer timezoneId;
    private Integer organizationId;
    private String type;
    private Chat chat;
    private Question question;
    private Eventsession eventsession;
    private Conference conference;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Chat{
        private Boolean show;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Question{
        private Boolean show;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Eventsession{
        private Boolean allowScreensharing;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Conference{
        private Boolean show;
        private String mode;
        private Integer maxConferences;
    }
}
