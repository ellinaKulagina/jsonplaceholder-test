package utils;

import lombok.Getter;
import lombok.Setter;
import models.PostModel;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class Context {
    private PostModel post;
    private PostModel actualPost;
    private List<PostModel> posts;
    private int responseCode;
    private int userId;
    private String partialUpdate;

    public Context() {
        this.post = new PostModel();
        this.actualPost = new PostModel();
        this.posts = new ArrayList<>();
    }
}
