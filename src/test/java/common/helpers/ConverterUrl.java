package common.helpers;

public class ConverterUrl {
    public static String getEventSessionIdFromUrl(String url) {
        return url.split("/")[6];
    }
}
