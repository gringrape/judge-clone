package hello.judgecode.judgment.infra;

import hello.judgecode.judgment.domain.Challenge;
import hello.judgecode.judgment.domain.ChallengeRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryChallengeRepository implements ChallengeRepository {

  private static final Map<Long, Challenge> challenges = new HashMap<>();
  private Long sequence = 0L;

  @Override
  public Challenge save(Challenge challenge) {
    var id = ++sequence;
    Challenge savedChallenge = new Challenge(id, challenge.title(), challenge.description(), challenge.result());
    challenges.put(id, savedChallenge);
    return savedChallenge;
  }

  @Override
  public Optional<Challenge> findById(Long id) {
    return Optional.ofNullable(challenges.get(id));
  }

  @Override
  public List<Challenge> findAll() {
    return List.copyOf(challenges.values());
  }
}
