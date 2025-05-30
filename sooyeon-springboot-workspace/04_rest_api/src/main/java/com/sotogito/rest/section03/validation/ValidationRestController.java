package com.sotogito.rest.section03.validation;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping("/valid")
@RestController
public class ValidationRestController {

    private List<UserDto> users;

    public ValidationRestController() {
        users = new ArrayList<UserDto>();
        users.add(new UserDto(1, "user01", "pass01", "홍", LocalDateTime.now()));
        users.add(new UserDto(2, "user02", "pass02", "길", LocalDateTime.now()));
        users.add(new UserDto(3, "user03", "pass03", "동", LocalDateTime.now()));
    }

    @GetMapping("/users")
    public ResponseEntity<ResponseMessage> findAllUsers() {
        if (users.isEmpty()) {
            throw new UserNotFoundException("회원들이없음");
        }

        return ResponseEntity
                .ok()
                .body(ResponseMessage.builder()
                        .status(200)
                        .message("회원들 조회 성공")
                        .result(Map.of("users", List.of(users)))
                        .build()
                );
    }

    @GetMapping("/users/{userNo}")
    public ResponseEntity<ResponseMessage> findUserByUserNo(@PathVariable Integer userNo) {
        UserDto foundedUser = users.stream()
                .filter(u -> u.getNo() == userNo)
                .findFirst()
                .orElse(null);

        if (foundedUser == null) {
            throw new UserNotFoundException("회원 없음");
        }

        return ResponseEntity
                .ok()
                .body(ResponseMessage.builder()
                        .status(200)
                        .message("회원 조회 성공")
                        .result(Map.of("user", foundedUser))
                        .build()
                );
    }

    @PostMapping("/users")
    public ResponseEntity<?> registerUser(@Validated @RequestBody UserDto registerUser) {
        int nowUserNo = (users.get(users.size() - 1).getNo()) + 1;
        registerUser.setNo(nowUserNo);
        registerUser.setEnrollDate(LocalDateTime.now());
        users.add(registerUser);

        return ResponseEntity
                .created(URI.create("/valid/users/" + registerUser.getNo()))
                .build();
    }

    @PutMapping("/users/{userNo}")
    public ResponseEntity<?> modifyUser(@PathVariable Integer userNo,
                                        @Validated @RequestBody UserDto updateUser) {

        UserDto foundedUser = users.stream()
                .filter(u -> u.getNo() == userNo)
                .findFirst()
                .orElse(null);

        if (foundedUser == null) {
            throw new UserNotFoundException("회원 없음");
        }

        foundedUser.setId(updateUser.getId());
        foundedUser.setPwd(updateUser.getPwd());
        foundedUser.setName(updateUser.getName());

        return ResponseEntity
                .created(URI.create("/valid/users" + updateUser.getNo()))
                .build();
    }

    @DeleteMapping("/users/{userNo}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userNo) {
        if(users.removeIf(u -> u.getNo() == userNo)) {
            return ResponseEntity.noContent().build();
        }
        throw new UserNotFoundException("회원 없음");
    }

}
