package common.config;

import org.aeonbits.owner.Config;

public interface ApiConfig extends Config {

    @DefaultValue("https://userapi.mts-link.ru")
    String baseURI();

    @DefaultValue("/v3")
    String basePath();
    @DefaultValue("https://my.mts-link.ru")
    String authBaseURI();

    @DefaultValue("/api")
    String authBasePath();
}
