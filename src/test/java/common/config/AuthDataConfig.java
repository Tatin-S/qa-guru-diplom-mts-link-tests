package common.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/authData.properties",
})

public interface AuthDataConfig extends Config {
    String email();

    String password();

    Boolean rememberMe();

    @Key("user_token")
    String userToken();
}