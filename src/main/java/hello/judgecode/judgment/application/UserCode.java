package hello.judgecode.judgment.application;

import hello.judgecode.judgment.domain.CodeType;

public record UserCode(
    CodeType codeType,
    String code
) {

}
