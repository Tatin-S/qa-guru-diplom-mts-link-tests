package common.config;

import org.aeonbits.owner.Config;

//@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
      //  "system:properties",
        "classpath:config/authData.properties",
      //  "file:~/authData.properties",
        //"file:./authData.properties"
})

public interface AuthDataConfig extends Config {
    String email();

    String password();
    Boolean rememberMe();
    @Key("user_token")
    String userToken();

    String selenoidUrl();
}