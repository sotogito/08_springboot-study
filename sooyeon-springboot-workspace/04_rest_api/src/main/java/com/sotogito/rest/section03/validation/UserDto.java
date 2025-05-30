package com.sotogito.rest.section03.validation;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserDto {

    private int no;

    @NotNull(message = "id는 반드시 입력되어야합니다.")
    @NotBlank(message = "id는 공백일 수 없습니다.")/// Blank는 Null도 허용하지 않지만 공백을 따로 예외처리하고싶음
    private String id;

    @NotNull(message = "비밀번호는 반드시 입력되어야합니다.")
    @Size(min = 4, max = 20, message = "비밀번호는 4~20글자로 입력해주세요.")
    private String pwd;

    @NotBlank(message = "입력은 반드시 입력되어야합니다.")
    @Size(min = 2, message = "이름은 두 글자 이상이여야합니다.")
    private String name;

    private LocalDateTime enrollDate;

}
