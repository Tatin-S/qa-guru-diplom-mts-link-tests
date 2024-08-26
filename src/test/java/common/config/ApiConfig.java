package common.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/api.properties",
        "file:~/api.properties",
        "file:./api.properties"
})

public interface ApiConfig extends Config {

    @DefaultValue("https://userapi.mts-link.ru")
    String baseURI();

    @DefaultValue("/v3/events")
    String basePath();
    @DefaultValue("https://my.mts-link.ru")
    String authBaseURI();

    @DefaultValue("/api")
    String authBasePath();
}
