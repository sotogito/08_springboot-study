package com.sotogito.security2.mapper;

import com.sotogito.security2.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    int insertUser(UserDto user);

    UserDto selectUserById(String userId);


}
