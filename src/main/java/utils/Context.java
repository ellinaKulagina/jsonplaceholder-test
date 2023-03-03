package utils;

import lombok.Getter;
import lombok.Setter;
import models.PostModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Getter
@Setter
public class Context {
    private PostModel post;
    private PostModel actualPost;
    private List<PostModel> posts;
    private int responseCode;

    public Context() {
        this.post = new PostModel();
        this.actualPost = new PostModel();
        this.posts = new ArrayList<>();
        this.responseCode = getResponseCode();
    }
}
