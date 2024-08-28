package common.config;

import org.aeonbits.owner.Config;

/*@Config.Sources({
        "system.properties"
})*/
public interface WebConfig extends Config {
    @DefaultValue("https://my.mts-link.ru")
    String baseUrl();

    @DefaultValue("chrome")
    String browser();

    @DefaultValue("121")
    String browserVersion();

    @DefaultValue("1920x1080")
    String browserSize();
}