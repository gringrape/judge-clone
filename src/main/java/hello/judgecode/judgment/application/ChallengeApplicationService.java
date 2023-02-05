package hello.judgecode.judgment.application;

import hello.judgecode.judgment.application.dto.ChallengeResponse;
import hello.judgecode.judgment.domain.Challenge;
import hello.judgecode.judgment.domain.ChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChallengeApplicationService {

  private final ChallengeService challengeService;

  public ChallengeResponse getChallenge(Long challengeId) {
    Challenge challenge = challengeService.getChallenge(challengeId);
    return new ChallengeResponse(challenge.id(), challenge.description());
  }
}

