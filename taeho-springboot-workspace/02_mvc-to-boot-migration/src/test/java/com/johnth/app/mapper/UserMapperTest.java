package com.johnth.app.mapper;

import com.johnth.app.dto.UserDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void insertUser() {

        UserDto userDto = UserDto.builder()
                .userId("junitTest")
                .userPwd("junitTest")
                .userName("제이유닛")
                .email("junit@gmail.com")
                .gender("M")
                .address("킹")
                .phone("010-1111-2222")
                .build();

        int result = userMapper.insertUser(userDto);

        Assertions.assertThat(result).isEqualTo(1);
    }

    @Test
    void selectUserCountById() {

        String userId = "junitTest";
        int result = userMapper.selectUserCountById(userId);
        Assertions.assertThat(result).isEqualTo(1);
    }

    @Test
    void selectUserById() {
        String userId = "junitTest";
        UserDto userDto = userMapper.selectUserById(userId);
        Assertions.assertThat(userDto).isNotNull();
    }
}