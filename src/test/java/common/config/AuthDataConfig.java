package common.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.FIRST)
@Config.Sources({
        "system:properties",
        "classpath:config/authData.properties",
        "file:~/authData.properties",
        "file:./authData.properties"
})

public interface AuthDataConfig extends Config {
    String email();

    String password();
    Boolean rememberMe();
}