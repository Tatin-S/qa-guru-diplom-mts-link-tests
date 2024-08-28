package common.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.FIRST)
@Config.Sources({
        "system:properties",
        "classpath:config/${env}.properties",
        "file:~/${env}.properties",
        "file:./${env}.properties"
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

    @DefaultValue("false")
    boolean isRemote();

    String selenoidUser();

    String selenoidPass();

    String remoteUrl();
}