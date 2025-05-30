package com.sotogito.rest.section04.swagger;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "API 실패 응답 메시지")

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ResponseErrorMessage {

    @Schema(description = "응답 에러 코드", allowableValues = {"00", "01", "02", "03"})
    private String code; //에러코드
    private String message; //에러메시지
    private String description; //에러상세메시지

}
