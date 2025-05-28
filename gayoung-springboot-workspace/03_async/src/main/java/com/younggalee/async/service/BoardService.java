package com.younggalee.async.service;

import com.younggalee.async.dto.BoardDto;

import java.util.Map;

public interface BoardService {

    Map<String, Object> getBoardsAndPaging(int page);
    BoardDto getBoard(int id);
    Map<String, Object> registBoard(BoardDto boardDto);



}
