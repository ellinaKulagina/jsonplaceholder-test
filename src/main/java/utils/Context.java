package utils;

import lombok.Getter;
import lombok.Setter;
import models.CommentModel;
import models.PostModel;
import models.UserModel;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class Context {
    private PostModel post;
    private PostModel actualPost;
    private List<PostModel> posts;
    private List<CommentModel> comments;
    private CommentModel actualComment;
    private List<UserModel> users;
    private UserModel actualUser;
    private int responseCode;
    private int userId;
    private String partialUpdate;

    public Context() {
        this.post = new PostModel();
        this.actualPost = new PostModel();
        this.posts = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.actualComment = new CommentModel();
        this.users = new ArrayList<>();
        this.actualUser = new UserModel();
    }
}
