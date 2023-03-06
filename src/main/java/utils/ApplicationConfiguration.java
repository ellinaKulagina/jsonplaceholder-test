package utils;

import org.aeonbits.owner.Config;

public interface ApplicationConfiguration extends Config {

  @Key("base.url")
  @DefaultValue("https://jsonplaceholder.typicode.com")
  String baseUrl();

  @Key("posts.url")
  @DefaultValue("${base.url}/posts")
  String postsUrl();

  @Key("user.post.url")
  @DefaultValue("${posts.url}?userId=")
  String userPostUrl();

  @Key("comments.post.url")
  @DefaultValue("${posts.url}/%d/comments")
  String commentPostUrl();

  @Key("comments.url")
  @DefaultValue("${base.url}/comments")
  String commentsUrl();

  @Key("users.url")
  @DefaultValue("${base.url}/users")
  String usersUrl();
}