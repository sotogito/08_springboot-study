package app.migration.mapper;

import app.migration.dto.UserDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/*
    ## @SpringBootTest ##
    1. Spring Boot 어플리케이션을 실제로 실행하는 것과 유사한 환경에서 테스트 할 수 있도록 하는 어노테이션
    2. 어플리케이션의 컨텍스트를 전체 로딩하고 모든 빌들을 등록해줌 => 객체 주입을 통해 사용 가능
 */

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void 회원_등록_테스트() {
        // given (테스트시 필요한 데이터 준비)
        UserDto user = UserDto.builder()
                .userId("JunitTest")
                .userPwd("JunitTest")
                .userName("제이유닛")
                .email("junit@gmail.com")
                .gender("M")
                .address("서울")
                .phone("010-1000-1010")
                .build();

        // when (테스트 실행)
        int result = userMapper.insertUser(user);

        // then (실행 결과와 예측값 비교를 통해 검증)
        Assertions.assertThat(result).isEqualTo(1);
    }

    @Test
    void 회원_아이디수_조회_테스트() {

    }

    @Test
    void 아이디로_회원_조회_테스트() {
        // given
        String userId = "JunitTest";
        // when
        UserDto user = userMapper.selectUserById(userId);
        // then
        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.getUserId()).isEqualTo("제이유닛");
    }
}