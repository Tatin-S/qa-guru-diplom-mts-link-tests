package common.helpers;

public class ConverterUrl {
    public static String getEventSessionIdFromUrl(String url) {
        return url.split("/")[7];
    }

    public static String getEventSessionIdFromUrlForEdit(String url) {
        return url.split("/")[6];
    }
}
