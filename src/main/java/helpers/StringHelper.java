package helpers;

import lombok.NoArgsConstructor;
import models.PostModel;

@NoArgsConstructor
public class StringHelper {

    public static PostModel removeNewLinesFromBody(PostModel post) {
        post.setBody(post.getBody().replace("\n", ""));
        return post;
    }
}
