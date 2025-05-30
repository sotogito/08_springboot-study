package com.sotogito.rest.section04.swagger;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Objects;

/**
 * ## @ControllerAdvice
 * 1. 애플리케이션 내의 모든 컨트롤러에서 발생되는 예외를 처리할 수 있음
 * 2. Advice 이므로 AOP의 기술이 적용된 어노테이션
 * 3. 컨트롤러엣 포함되어야 할 예외 처리 코드를 따로 분리해서 관리할 수 있음
 *      코드의 중복 문제를 해결하고, 서로의 관심사를 분리할 수 있음
 *
 * @RestControllerAdvice -> REST 방식인 경우 자주 사용됨
 *
 */
//@ControllerAdvice
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ResponseErrorMessage> handleUserNotFoundException(UserNotFoundException e) {
        ResponseErrorMessage responseErrorMessage = ResponseErrorMessage.builder()
                .code("00")
                .message("회원 조회 실패")
                .description(e.getMessage())
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(responseErrorMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) //DTO에 설정한 유효성 검사를 통과하지 못할 경우
    public ResponseEntity<ResponseErrorMessage> handleUserInValidInputException(MethodArgumentNotValidException e) {
        String code = null;
        String message = null;
        String description = null;

        BindingResult bindingResult = e.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();

        if (fieldError != null) {
            switch (Objects.requireNonNull(fieldError.getCode())) {
                case "NotNull", "NotBlank" -> {
                    code = "01";
                    message = "필수 입력 값 누락 또는 공백 입력";
                }
                case "Size" -> {
                    code = "02";
                    message = "입력 가능한 크기를 벗어난 값 입력";
                }
            }
            description = bindingResult.getFieldError().getDefaultMessage();
        }

        return ResponseEntity
                .badRequest()
                .body(ResponseErrorMessage.builder()
                        .code(code)
                        .message(message)
                        .description(description)
                        .build()
                );
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseErrorMessage> handlePathVariableException(MethodArgumentTypeMismatchException e) {
        return ResponseEntity
                .badRequest()
                .body(ResponseErrorMessage.builder()
                        .code("03")
                        .message("경로 변수 오류")
                        .description("잘못된 타입의 데이터가 입력되었습니다.")
                        .build()
                );
    }

}
