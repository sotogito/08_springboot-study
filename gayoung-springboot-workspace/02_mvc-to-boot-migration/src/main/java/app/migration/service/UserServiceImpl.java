package app.migration.service;

import app.migration.dto.UserDto;
import app.migration.mapper.UserMapper;
import app.migration.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final FileUtil fileUtil;

    @Override
    public int getUserCount(String checkId) {
        return userMapper.selectUserCountById(checkId);
    }

    @Override
    public Map<String, Object> registUser(UserDto user) {
        try {
            int result = userMapper.insertUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return Map.of("message", "서버 내부 오류로 회원가입에 실패하였습니다.");
        }
        return Map.of("message", "성공적으로 회원가입 되었습니다.");
    }

    @Override
    public Map<String, Object> loginUser(UserDto user) {    // user: 로그인시 입력한 아이디, 비번
        UserDto selectedUser = userMapper.selectUserById(user.getUserId());

        if (selectedUser == null) {
            return Map.of("message", "존재하는 아이디가 없습니다.");
        } else if (!selectedUser.getUserPwd().equals(user.getUserPwd())) {
            return Map.of("message", "비밀번호를 잘못 입력하셨습니다.");
        }

        return Map.of("message", selectedUser.getUserName() + "님, 환영합니다!", "user", selectedUser);
    }

    @Override
    public Map<String, Object> modifyUserProfile(String userId, MultipartFile file) {
        // 1) 아이디로 기존 회원 정보 조회
        UserDto selectedUser = userMapper.selectUserById(userId);
        if(selectedUser == null) {
            return Map.of("message", "회원정보를 찾을 수 없습니다.");
        }

        // 2) 첨부파일 업로드
        Map<String, String> map = null;
        try {
            map = fileUtil.fileupload("profile", file);
        } catch (Exception e) {
            return Map.of("message", "프로필 이미지 업로드시 문제가 발생하였습니다.");
        }

        // 3) 회원 정보 db 수정
        String newProfileURL = map.get("filePath") + "/" + map.get("filesystemName");
        selectedUser.setProfileURL(newProfileURL);

        int result = userMapper.updateProfileImg(selectedUser);
        if (result != 1) { // 쿼리 실패
            new File(newProfileURL).delete(); // 저장된 파일 삭제
            return Map.of("message", "프로필 이미지 변경에 실패하였습니다.");
        }
        return Map.of("message", "성공적으로 프로필 이미지가 변경되었습니다.", "profileURL", newProfileURL);
    }
}
