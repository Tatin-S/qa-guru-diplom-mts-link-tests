package common.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/${env}.properties"
})
public interface WebConfig extends Config {
    @DefaultValue("https://my.mts-link.ru")
    String baseUrl();

    @DefaultValue("chrome")
    String browser();

    @DefaultValue("121")
    String browserVersion();

    @DefaultValue("1920x1080")
    String browserSize();
    @DefaultValue("https://my.mts-link.ru")
    String remoteUrl();
    @DefaultValue("false")
    Boolean isRemote();
}