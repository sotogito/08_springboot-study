package com.ibe6.async.mapper;

import com.ibe6.async.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {

    int selectBoardCount();
    List<BoardDto> selectBoardList(Map<String, Object> map);
    BoardDto selectBoardById(int boardId);
    int insertBoard(BoardDto board);

}