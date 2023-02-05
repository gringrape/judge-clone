package hello.judgecode.judgment.application;

import hello.judgecode.judgment.application.dto.UserCode;
import hello.judgecode.judgment.domain.ChallengeService;
import hello.judgecode.judgment.domain.CodeType;
import hello.judgecode.judgment.executor.CodeExecutor;
import hello.judgecode.judgment.executor.JavaCodeExecutor;
import hello.judgecode.judgment.executor.PythonCodeExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JudgmentApplicationService {

  private final ChallengeService challengeService;

  public String judgeCode(Long challengeId, UserCode userCode) {
    var challenge = challengeService.getChallenge(challengeId);
    var executor = getExecutor(userCode);
    var message = executor.executeCode(userCode.code());
    return judge(message, challenge.result());
  }

  private String judge(String result, String answer) {
    if (result.contains("Exception") || result.contains("Error")) {
      return "오류 발생";
    }
    if (answer.equals(result)) {
      return "테스트 성공";
    }
    return "테스트 실패";
  }

  private CodeExecutor getExecutor(UserCode userCode) {
    return userCode.codeType().equals(CodeType.JAVA)
        ? new JavaCodeExecutor()
        : new PythonCodeExecutor();
  }
}
