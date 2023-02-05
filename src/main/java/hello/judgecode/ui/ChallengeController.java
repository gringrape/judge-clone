package hello.judgecode.ui;

import hello.judgecode.judgment.application.ChallengeApplicationService;
import hello.judgecode.judgment.application.JudgmentApplicationService;
import hello.judgecode.judgment.application.dto.ChallengeResponse;
import hello.judgecode.judgment.application.dto.UserCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("challenges")
@RequiredArgsConstructor
public class ChallengeController {

  private final ChallengeApplicationService challengeApplicationService;
  private final JudgmentApplicationService judgmentApplicationService;

  @PostMapping("{challengeId}")
  public ResponseEntity<String> submitCode(@PathVariable Long challengeId,
      @RequestBody UserCode code) {
    return ResponseEntity.ok(judgmentApplicationService.judgeCode(challengeId, code));
  }
}
