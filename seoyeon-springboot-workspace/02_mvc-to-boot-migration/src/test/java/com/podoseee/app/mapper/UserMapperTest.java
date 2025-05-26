package com.podoseee.app.mapper;

import com.podoseee.app.dto.UserDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

/*
    ## @SpringBootTest ##
    1. Spring Boot 애플리케이션을 실제로 실행하는 것과 유사한 환경에서 테스트 할 수 있도록 하는 어노테이션
    2. 애플리케이션의 컨텍스트를 전체 로딩하고 모든 빈들을 등록해줌 => 객체 주입ㅇ들 통해 사용 가능
 */
@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Transactional
    @Rollback
    @Test
    void 회원_등록_테스트() {

        // given (테스트시 필요한 데이터 준비)
        UserDto user = UserDto.builder()
                .userId("junitTest2")
                .userPwd("junitTest2")
                .userName("제이유닛2")
                .email("junit2@gmail.com")
                .gender("F")
                .address("경기도")
                .phone("010-3333-4444")
                .build();

        // when (테스트 실행)
        int result = userMapper.insertUser(user);

        // then (실행 결과와 예측값 비교를 통해 검증)
        Assertions.assertThat(result).isEqualTo(1);
    }

    @Test
    void 회원_아이디수_조회_테스트() {

        // given
        String checkId = "admin01";
        // when
        int count = userMapper.selectUserCountById(checkId);
        // then
        Assertions.assertThat(count).isEqualTo(1);

    }

    @Test
    void 아이디로_회원_조회_테스트() {
        //given
        String userId = "abc123";
        // when
        UserDto user = userMapper.selectUserById(userId);
        // then
        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.getUserName()).isEqualTo("에이비씨");
        Assertions.assertThat(user.getGender()).isEqualTo("F");
    }

}
