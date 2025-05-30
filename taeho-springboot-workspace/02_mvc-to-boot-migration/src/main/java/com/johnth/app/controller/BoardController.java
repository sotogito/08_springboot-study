package com.johnth.app.controller;

import com.johnth.app.dto.BoardDto;
import com.johnth.app.dto.UserDto;
import com.johnth.app.service.BoardService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/board")
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list.page")
    public void boardListPage(@RequestParam(value="page", defaultValue="1") int page
                            , Model model){
        Map<String, Object> map = boardService.getBoardsAndPaging(page);

        model.addAttribute("page", map.get("page"));
        model.addAttribute("beginPage", map.get("beginPage"));
        model.addAttribute("endPage", map.get("endPage"));
        model.addAttribute("totalPage", map.get("totalPage"));
        model.addAttribute("boards", map.get("boards"));
    }

    @GetMapping("/regist.page")
    public void boardRegistPage(){}

    @PostMapping("/regist.do")
    public String boardRegist(BoardDto board, HttpSession session
                            , List<MultipartFile> uploadFiles
                            , RedirectAttributes redirectAttributes){

        int userNo = ((UserDto)session.getAttribute("loginUser")).getUserNo(); // 로그인한회원번호 == 작성자회원번호
        board.setBoardWriter(String.valueOf(userNo));

        Map<String, Object> map = boardService.registBoard(board, uploadFiles);
        redirectAttributes.addFlashAttribute("message", map.get("message"));

        return "redirect:/board/list.page";
    }

    @GetMapping("/detail.page")
    public void boardDetailPage(int no, Model model){
        Map<String, Object> map = boardService.getBoardDetail(no);
        model.addAttribute("board", map.get("board"));
        model.addAttribute("attachs", map.get("attachs"));
    }





}
