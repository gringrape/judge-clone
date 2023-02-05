package hello.judgecode.judgment.domain;

public record Challenge(Long id, String result) {

  public Challenge(String result) {
    this(null, result);
  }
}
