package api.models.event;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateEventRequestModel {
    private Chat chat;
    private Question question;
    private Eventsession eventsession;
    private Conference conference;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Chat{
        private Boolean premoderation;
        private Boolean show;
        private Boolean showMiniChat;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Question{
        private Boolean show;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Eventsession{
        private Integer vcsSizeRatio;
        private Boolean record;
        private Boolean mediaControlsShow;
        private Boolean allowManageFilesForGuest;
        private Boolean allowScreensharing;
        private Integer background;
        private String agendaEditType;
        private Boolean allowPresentationFilesForGuest;
        private Boolean autoclearChatAndQuestions;
        private Boolean liteVersion;
        private Boolean allowManageOtherPeoplesPresentation;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Conference{
        private Boolean show;
        private String mode;
        private Integer maxConferences;
    }
}
