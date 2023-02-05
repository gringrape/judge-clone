package hello.judgecode.judgment.domain;

import java.util.List;
import java.util.Optional;

public interface ChallengeRepository {

  Challenge save(Challenge challenge);

  Optional<Challenge> findById(Long id);

  List<Challenge> findAll();
}
