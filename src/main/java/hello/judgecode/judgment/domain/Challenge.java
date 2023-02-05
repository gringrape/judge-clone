package hello.judgecode.judgment.domain;

import java.util.Objects;

public record Challenge(Long id, String title, String description, String result) {

  public Challenge {
    Objects.requireNonNull(title);
    Objects.requireNonNull(description);
    Objects.requireNonNull(result);

    if (title.isBlank()) {
      throw new RuntimeException();
    }

    if (description.isBlank()) {
      throw new RuntimeException();
    }

    if (result.isBlank()) {
      throw new RuntimeException();
    }
  }

  public Challenge(String title, String description, String result) {
    this(null, title, description, result);
  }
}
