package utils;

import lombok.Getter;
import lombok.Setter;
import models.PostModel;

@Getter
@Setter
public class Context {
    private PostModel post;

    public Context() {
        this.post = new PostModel();
    }
}
