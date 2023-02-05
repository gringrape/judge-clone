package hello.judgecode.judgment.application;

import static org.assertj.core.api.Assertions.assertThat;

import hello.judgecode.judgment.application.dto.UserCodeRequest;
import hello.judgecode.judgment.domain.Challenge;
import hello.judgecode.judgment.domain.ChallengeRepository;
import hello.judgecode.judgment.domain.ChallengeService;
import hello.judgecode.judgment.domain.CodeType;
import hello.judgecode.judgment.infra.MemoryChallengeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JudgmentServiceTest {

  private static final String SUCCESS_MESSAGE = "테스트 성공";
  private static final String FAIL_MESSAGE = "테스트 실패";
  private static final String ERROR_MESSAGE = "오류 발생";
  JudgmentApplicationService judgmentService;
  ChallengeRepository challengeRepository;
  ChallengeService challengeService;

  @BeforeEach
  void setUp() {
    challengeRepository = new MemoryChallengeRepository();
    challengeService = new ChallengeService(challengeRepository);
    judgmentService = new JudgmentApplicationService(challengeService);
  }

  @Test
  @DisplayName("챌린지 정답과 사용자 결과가 같다면 `테스트 성공`을 반환한다.")
  void judgeSuccess() {
    String actual = judge("hello", "hello");

    assertThat(actual).isEqualTo(SUCCESS_MESSAGE);
  }


  @Test
  @DisplayName("챌린지 정답과 사용자 결과가 다르면 `테스트 실패`를 반환한다.")
  void judgeFail() {
    String actual = judge("user result", "challenge answer");

    assertThat(actual).isEqualTo(FAIL_MESSAGE);
  }

  @Test
  @DisplayName("결과에 `Exception`이 포함되면 `오류 발생`을 반환한다.")
  void judgeThrowError() {
    String actual = judge("RuntimeException", "challenge answer");

    assertThat(actual).isEqualTo(ERROR_MESSAGE);
  }

  @Test
  @DisplayName("코드를 실행해 결과 메시지를 반환합니다. 의도한 결과가 나왔다면 `테스트 성공`을 반환합니다.")
  void judgeCodeSuccess() {
    // given
    Challenge challenge = getChallenge("hello 출력하기", "`hello`를 출력하세요", "hello");

    // when
    String message = judgmentService.judgeCode(challenge.id(),
        new UserCodeRequest(CodeType.PYTHON, String.format("print(\"%s\")", "hello")));

    // then
    assertThat(message).isEqualTo(SUCCESS_MESSAGE);
  }

  @Test
  @DisplayName("코드를 실행해 결과 메시지를 반환합니다. 의도한 결과가 나오지 않았다면 `테스트 실패`를 반환합니다.")
  void judgeCodeFail() {
    // given
    String incorrectCode = "No Hello";
    Challenge challenge = getChallenge("hello 출력하기", "`hello`를 출력하세요", "hello");

    // when
    String message = judgmentService.judgeCode(challenge.id(),
        new UserCodeRequest(CodeType.PYTHON, String.format("print(\"%s\")", incorrectCode)));

    // then
    assertThat(message).isEqualTo(FAIL_MESSAGE);
  }

  @Test
  @DisplayName("코드를 실행해 결과 메시지를 반환합니다. 오류가 발생하면 `오류 발생`을 반환합니다.")
  void judgeCodeThrowError() {
    // given
    String exceptionCode = "0/0";
    Challenge challenge = getChallenge("hello 출력하기", "`hello`를 출력하세요", "hello");

    // when
    String message = judgmentService.judgeCode(challenge.id(),
        new UserCodeRequest(CodeType.PYTHON, String.format("print(%s)", exceptionCode)));

    // then
    assertThat(message).isEqualTo(ERROR_MESSAGE);
  }

  private Challenge getChallenge(String description, String title, String answer) {
    return challengeRepository.save(new Challenge(title, description, answer));
  }

  private String judge(String result, String answer) {
    try {
      var method = judgmentService.getClass()
          .getDeclaredMethod("judge", String.class, String.class);
      method.setAccessible(true);
      return (String) method.invoke(judgmentService, result, answer);
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }
}
