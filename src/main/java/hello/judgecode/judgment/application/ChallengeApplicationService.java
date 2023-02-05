package hello.judgecode.judgment.application;

import hello.judgecode.judgment.application.dto.ChallengeResponse;
import hello.judgecode.judgment.application.dto.ChallengeResponses;
import hello.judgecode.judgment.domain.ChallengeService;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChallengeApplicationService {

  private final ChallengeService challengeService;

  public ChallengeResponse getChallenge(Long challengeId) {
    var challenge = challengeService.getChallenge(challengeId);
    return new ChallengeResponse(challenge.id(), challenge.title(), challenge.description());
  }

  public ChallengeResponses getChallenges() {
    var challenges = challengeService.getChallenges();
    return new ChallengeResponses(challenges.stream()
        .map(challenge -> new ChallengeResponse(challenge.id(), challenge.title(), null))
        .collect(Collectors.toList()));
  }

}

