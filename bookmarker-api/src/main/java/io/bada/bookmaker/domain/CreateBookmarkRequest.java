package io.bada.bookmaker.domain;
//입력을 위해서 요청을 처리해주는 객체 + 유효성 검증

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBookmarkRequest {
    @NotEmpty(message = "제목은 필수 입력 값입니다.")
    private String title;

    @NotEmpty(message = "URL은 필수 입력 값입니다.")
    private String url;
}
