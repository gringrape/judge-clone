package hello.judgecode.judgment.domain;

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

}
