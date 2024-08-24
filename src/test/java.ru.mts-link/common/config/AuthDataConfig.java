package common.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config/authdata.properties"})

public interface AuthDataConfig extends Config {
    String email();

    String password();
    Boolean rememberMe();
}