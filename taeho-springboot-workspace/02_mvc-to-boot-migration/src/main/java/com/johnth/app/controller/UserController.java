package com.johnth.app.controller;

import com.johnth.app.dto.UserDto;
import com.johnth.app.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/user")
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/signup.page")
    public void signupPage(){} // 회원가입페이지 이동용

    @GetMapping("/myinfo.page")
    public void myinfoPage(){} // 마이페이지 이동용

    @ResponseBody
    @GetMapping("/idcheck.do")
    public String idcheck(String checkId){
        return userService.getUserCount(checkId) == 0 ? "NOTUSED" : "USED";
    }

    @PostMapping("/signup.do")
    public String signup(UserDto user, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message", userService.registUser(user).get("message"));
        return "redirect:/"; // redirect라서 302응답코드
        /*
            Network 탭을 확인
            - /signup.do   응답코드 302
            - localhost    응답코드 200
         */
    }

    @PostMapping("/signin.do")
    public String signin(UserDto user
            , RedirectAttributes redirectAttributes
            , HttpSession session){

        Map<String, Object> map = userService.loginUser(user);
        redirectAttributes.addFlashAttribute("message", map.get("message"));

        if(map.get("user") != null){ // 로그인 성공일 경우
            session.setAttribute("loginUser", map.get("user"));
        }

        return "redirect:/"; // 응답코드 302
    }

    @GetMapping("/signout.do")
    public String signout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    @ResponseBody
    @PostMapping(value="/modifyProfile.do", produces="text/html; charset=UTF-8")
    public String modifyProfile(MultipartFile uploadFile, HttpSession session){
        UserDto loginUser = (UserDto)session.getAttribute("loginUser");
        Map<String, Object> map = userService.modifyUserProfile(loginUser.getUserId(), uploadFile);
        if(map.get("profileURL") != null){ // 프로필변경성공
            loginUser.setProfileURL((String)map.get("profileURL"));
        }
        return (String)map.get("message");
    }

}
