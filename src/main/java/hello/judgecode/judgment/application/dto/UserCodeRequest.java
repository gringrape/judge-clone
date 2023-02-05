package hello.judgecode.judgment.application.dto;

import hello.judgecode.judgment.domain.CodeType;

public record UserCodeRequest(
    CodeType codeType,
    String code
) {

}
