package com.johnth.thymeleaf.dto;

import java.time.LocalDateTime;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserDto {

    private int no;
    private String name;
    private int age;
    private LocalDateTime registDate;

}