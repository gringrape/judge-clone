package hello.judgecode.judgment.executor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public abstract class CodeExecutor {

  protected static final String DIRECTORY = "/Users/keonchanglee/Desktop/codingtest/";
  protected static final String FILE_NAME = "Solution";
  protected final Path path = Paths.get(DIRECTORY, getFileName());

  /**
   * @return 사용자에게 제공할 메시지
   */
  public synchronized String executeCode(String code) {
    try {
      initializeFile(code);
    } catch (IOException e) {
      throw new RuntimeException("명령어를 실행하기 위해 초기화하는 과정이 실패했습니다.");
    }
    return executeCommand();
  }

  private void initializeFile(String code) throws IOException {
//    1. 파일을 생성합니다.
    Files.deleteIfExists(path);
    Files.createFile(path);
//    2. 코드를 작성합니다.
    try (var writer = Files.newBufferedWriter(path)) {
      writer.write(String.format(getCodeFormat(), code));
    }
  }

  private String executeCommand() {
//    1. 클래스 파일을 실행합니다.
    var commands = new String[]{"/bin/sh", "-c", getCommand()};
    Process exec;
    try {
      exec = Runtime.getRuntime().exec(commands);
    } catch (IOException e) {
      throw new RuntimeException("프로그램 인스턴스화 실패했습니다.");
    }

//    2. 결과를 반환합니다.
    Objects.requireNonNull(exec);

    var sb = new StringBuilder();
    var in = new InputStreamReader(exec.getInputStream());
    try (BufferedReader reader = new BufferedReader(in)) {
      String line;

      while ((line = reader.readLine()) != null) {
        sb.append(line).append('\n');
      }
    } catch (IOException e) {
      throw new RuntimeException("출력 결과를 읽기 데 실패했습니다.");
    }

    return sb.toString().trim();
  }

  protected abstract String getCommand();

  protected abstract String getFileName();

  protected abstract String getCodeFormat();

}
