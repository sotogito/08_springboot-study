package com.johnth.async.mapper;

import com.johnth.async.dto.BoardDto;
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
