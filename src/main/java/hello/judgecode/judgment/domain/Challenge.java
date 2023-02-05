package hello.judgecode.judgment.domain;

public record Challenge(Long id, String title, String description, String result) {

  public Challenge(String title, String description, String result) {
    this(null, title, description, result);
  }
}
