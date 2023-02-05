package hello.judgecode.judgment.domain;

public record Challenge(Long id, String description, String result) {

  public Challenge(String description, String result) {
    this(null, description, result);
  }
}
