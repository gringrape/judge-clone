package hello.judgecode.judgment.domain;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChallengeService {

  private final ChallengeRepository challengeRepository;

  public Challenge getChallenge(Long challengeId) {
    return challengeRepository.findById(challengeId)
        .orElseThrow(() -> new RuntimeException("데이터를 찾을 수 없습니다."));
  }

  public List<Challenge> getChallenges() {
    return challengeRepository.findAll();
  }
}
