package com.kyungbae.app.mapper;

import com.kyungbae.app.dto.AttachDto;
import com.kyungbae.app.dto.BoardDto;

import java.util.List;
import java.util.Map;

public interface BoardMapper {
    int selectBoardListCount();
    List<BoardDto> selectBoardList(Map<String, Object> map);
    int insertBoard(BoardDto board);
    int insertAttach(AttachDto attachDto);
    BoardDto selectBoardByNo(int no);
    List<AttachDto> selectAttachByBoardNo(int no);
}
