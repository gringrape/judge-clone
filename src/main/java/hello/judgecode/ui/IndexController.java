package hello.judgecode.ui;

import hello.judgecode.judgment.application.ChallengeApplicationService;
import hello.judgecode.judgment.application.dto.ChallengeResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class IndexController {

  private final ChallengeApplicationService challengeApplicationService;

  @GetMapping("challenges")
  public String index(Model model) {
    ChallengeResponses challenges = challengeApplicationService.getChallenges();
    model.addAttribute("challenges", challenges.challengeResponses());
    return "challenges";
  }

  @GetMapping("challenges/{challengeId}")
  public String getChallengePage(@PathVariable Long challengeId, Model model) {
    model.addAttribute("challenge", challengeApplicationService.getChallenge(challengeId));
    return "challenge";
  }
}
