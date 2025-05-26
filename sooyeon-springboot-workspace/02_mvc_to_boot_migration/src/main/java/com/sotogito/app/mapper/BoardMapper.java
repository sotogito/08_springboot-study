package com.sotogito.app.mapper;

import com.sotogito.app.dto.AttachDto;
import com.sotogito.app.dto.BoardDto;

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
