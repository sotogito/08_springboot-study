package com.sotogito.security2.service;

import com.sotogito.security2.dto.UserDto;
import com.sotogito.security2.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserServiceImpl {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public Map<String, String> registUser(UserDto user) { /// 매개변수의 비번은 암호화 전 상태
        user.setUserPwd(passwordEncoder.encode(user.getUserPwd())); ///암호화된 비번으로 다시 set

        try {
            userMapper.insertUser(user);
        } catch (DuplicateKeyException e) {
            e.printStackTrace();
            return Map.of(
                    "message", "중복된 아이디로 인한 회원가입 실패" ,
                    "path", "/signup");
        }catch (Exception e) {
            e.printStackTrace();
            return Map.of(
                    "message", "서버 내부로 인한 오류" ,
                    "path", "/signup");
        }

        return Map.of(
                "message", "회원가입성공" ,
                "path", "/login");

    }

}
