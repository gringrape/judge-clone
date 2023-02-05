package hello.judgecode;

import hello.judgecode.judgment.domain.Challenge;
import hello.judgecode.judgment.domain.ChallengeRepository;
import org.springframework.stereotype.Component;

/**
 * 존재하지 않는 챌린지를 추가하기 위한 초기화 과정입니다. 상용 애플리케이션에서는 사용하지 않습니다.
 */
@Component
public class DataLoader {

  private ChallengeRepository challengeRepository;

  public DataLoader(ChallengeRepository challengeRepository) {
    this.challengeRepository = challengeRepository;
  }

  public void loadData() {
    challengeRepository.save(new Challenge("Hello World!",
        """
            `Hello World!`를 출력해주세요!
            ### 입력
            ```
            입력이 없습니다.
            ```
            ### 출력
            ```
            Hello World!
            ```""",
        "Hello World!"));
    challengeRepository.save(new Challenge("출력해보자!",
        """
            `Hi`를 출력해주세요!
            ### 입력
            ```
            입력이 없습니다.
            ```
            ### 출력
            ```
            Hi
            ```""",
        "Hi"));
  }

}

