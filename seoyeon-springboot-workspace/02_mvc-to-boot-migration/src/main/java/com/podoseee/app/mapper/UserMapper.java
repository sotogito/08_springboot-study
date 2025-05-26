package com.podoseee.app.mapper;

import com.podoseee.app.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    int insertUser(UserDto user);
    int selectUserCountById(@Param("checkId") String checkId);
    UserDto selectUserById(@Param("userId") String userId);
    int updateProfileImg(UserDto user);

}
