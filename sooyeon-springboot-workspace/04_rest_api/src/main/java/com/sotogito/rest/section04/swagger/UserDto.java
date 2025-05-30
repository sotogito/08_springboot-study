package com.sotogito.rest.section04.swagger;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Schema(description = "회원 DTO")

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserDto {

    private int no;

    @Schema(description = "아이디", example = "test01")
    @NotNull(message = "id는 반드시 입력되어야합니다.")
    @NotBlank(message = "id는 공백일 수 없습니다.")/// Blank는 Null도 허용하지 않지만 공백을 따로 예외처리하고싶음
    private String id;

    @Schema(description = "아이디", example = "pass123")
    @NotNull(message = "비밀번호는 반드시 입력되어야합니다.")
    @Size(min = 4, max = 20, message = "비밀번호는 4~20글자로 입력해주세요.")
    private String pwd;

    @Schema(description = "이름", example = "홍길동")
    @NotBlank(message = "입력은 반드시 입력되어야합니다.")
    @Size(min = 2, message = "이름은 두 글자 이상이여야합니다.")
    private String name;

    @Schema(description = "가입일시")
    private LocalDateTime enrollDate;

}
