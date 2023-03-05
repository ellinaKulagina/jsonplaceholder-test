package helpers;

import lombok.NoArgsConstructor;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@NoArgsConstructor
public class StringHelper {

    public static String removeNewLinesFromBody(String body) {
        return body.replace("\n", "");
    }

    public static String urlEncode(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }
}