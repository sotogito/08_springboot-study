package app.migration.mapper;

import app.migration.dto.AttachDto;
import app.migration.dto.BoardDto;

import java.util.List;
import java.util.Map;

public interface BoardMapper {
    int selectedBoardlistCount();
    List<BoardDto> selectBoardList(Map<String, Object> map);

    int insertBoard(BoardDto boardDto);
    int insertAttach(AttachDto attachDto);

    BoardDto selectBoardByNo(int no);
    List<AttachDto> selectAttachByBoardNo(int no);
}
