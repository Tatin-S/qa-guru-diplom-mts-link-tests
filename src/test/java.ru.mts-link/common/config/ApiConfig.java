package common.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/api.properties"
})

public interface ApiConfig extends Config {

    @DefaultValue("https://my.mts-link.ru")
    String baseURI();

    @DefaultValue("/api")
    String basePath();
}
