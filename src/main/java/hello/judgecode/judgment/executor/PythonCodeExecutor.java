package hello.judgecode.judgment.executor;

public final class PythonCodeExecutor extends CodeExecutor {

  private static final String EXTENSION = "py";
  private static final String CODE_FORMAT = """
      try:
          %s
      except Exception as e:
          print(e.__class__.__name__)""";

  @Override
  protected String getCommand() {
    return String.format("cd %s;python3 %s", DIRECTORY, getFileName());
  }

  @Override
  protected String getFileName() {
    return String.format("%s.%s", FILE_NAME, EXTENSION);
  }

  @Override
  protected String getCodeFormat() {
    return CODE_FORMAT;
  }
}
