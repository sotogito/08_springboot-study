package com.ibe6.app.mapper;

import com.ibe6.app.dto.AttachDto;
import com.ibe6.app.dto.BoardDto;

import java.util.List;
import java.util.Map;

public interface BoardMapper {
    int selectBoardListCount();
    List<BoardDto> selectBoardList(Map<String, Object> map);

    int insertBoard(BoardDto board);
    int insertAttach(AttachDto attach);

    BoardDto selectBoardByNo(int no);
    List<AttachDto> selectAttachByBoardNo(int no);
}