package app.migration.service;

import app.migration.dto.UserDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface UserService {
    //회원아이디수조회
    int getUserCount(String checkId);
    // 회원가입
    Map<String, Object> registUser(UserDto user);
    // 로그인
    Map<String, Object> loginUser(UserDto user);
    // 프로필 수정
    Map<String, Object> modifyUserProfile(String userId, MultipartFile file);
}
