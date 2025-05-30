package com.sotogito.rest.section04.swagger;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Map;

@Schema(description = "API 성공 응답 메시지")

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ResponseMessage {

    @Schema(description = "결과 코드", allowableValues = {"200"})
    private int status;
    private String message;
    private Map<String, Object> result;

}
