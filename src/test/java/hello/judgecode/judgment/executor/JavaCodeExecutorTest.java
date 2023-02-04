package hello.judgecode.judgment.executor;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class JavaCodeExecutorTest {

  @ParameterizedTest
  @ValueSource(strings = {"Hello", "Hello\nMy name is this is spear", "1\n2\n3\n4\n5"})
  @DisplayName("사용자는 자바 코드를 실행할 수 있다.")
  void executeJavaCode(String actual) {
    var javaCodeExecutor = new JavaCodeExecutor();
    String code = actual.replaceAll("\n", "\\\\n");
    String expected = javaCodeExecutor.executeCode("System.out.println(\"" + code + "\");");
    Assertions.assertThat(actual).isEqualTo(expected.trim());
  }
}
