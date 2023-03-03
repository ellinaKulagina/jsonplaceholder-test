package utils;

import org.aeonbits.owner.Config;

public interface ApplicationConfiguration extends Config {

  @Key("base.url")
  @DefaultValue("https://jsonplaceholder.typicode.com")
  String baseUrl();

  @Key("posts.url")
  @DefaultValue("${base.url}/posts")
  String postsUrl();

  @Key("comments.url")
  @DefaultValue("${base.url}/comments")
  String commentsUrl();
}