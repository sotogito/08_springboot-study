package com.podoseee.app.mapper;

import com.podoseee.app.dto.AttachDto;
import com.podoseee.app.dto.BoardDto;

import java.util.List;
import java.util.Map;

public interface BoardMapper {
    int selectBoardListCount();
    List<BoardDto> selectBoardList(Map<String, Object> paramMap);

    int insertBoard(BoardDto board);
    int insertAttach(AttachDto attach);

    BoardDto selectBoardByNo(int no);
    List<AttachDto> selectAttachByBoardNo(int no);
}
