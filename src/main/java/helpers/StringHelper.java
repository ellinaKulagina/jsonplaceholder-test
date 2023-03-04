package helpers;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class StringHelper {

    public static String removeNewLinesFromBody(String body) {
        return body.replace("\n", "");
    }
}