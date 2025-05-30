package com.sotogito.rest.section03.validation;

import lombok.*;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ResponseMessage {

    private int status;
    private String message;
    private Map<String, Object> result;

}
