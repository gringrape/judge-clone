package hello.judgecode.judgment.domain;

import java.util.Optional;

public interface ChallengeRepository {

  Challenge save(Challenge challenge);

  Optional<Challenge> findById(Long id);

}
