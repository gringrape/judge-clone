package hello.judgecode.judgment.executor;

public final class JavaCodeExecutor extends CodeExecutor {

  private static final String CODE_FORMAT = """
      public class Solution {
        public static void main(String[] args) {
          try {
            %s
          } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName());
          }
        }
      }""";
  private static final String EXTENSION = "java";

  @Override
  protected String getCommand() {
    return String.format("cd %s;javac %s;java %s", DIRECTORY,
        getFileName(),
        FILE_NAME);
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
