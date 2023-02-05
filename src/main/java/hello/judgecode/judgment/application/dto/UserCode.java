package hello.judgecode.judgment.application.dto;

import hello.judgecode.judgment.domain.CodeType;

public record UserCode(
    CodeType codeType,
    String code
) {

}
