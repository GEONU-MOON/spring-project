package com.springproject.controller;

import com.springproject.DTO.LoginDTO;
import com.springproject.DTO.RegisterDTO;
import com.springproject.domain.Board;
import com.springproject.domain.Members;
import com.springproject.service.BoardService;
import com.springproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final BoardService boardService;
    private final MemberService memberService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("LoginForm", new LoginDTO());
        model.addAttribute("registerForm", new RegisterDTO());
        return "index";
    }

    @GetMapping("home")
    public String home(Model model, HttpSession session){
        Members member = (Members) session.getAttribute("Member");
        if (member != null) {
            List<Board> boardList = boardService.recentBoard(member.getUserid());
            model.addAttribute("recentBoard", boardList);
            return "home";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/home")
    public String Login(LoginDTO form, Model model, HttpServletRequest request, HttpServletResponse response) {
        String inputID = form.getUserid();
        String inputPW = form.getPassword();

        Members result = memberService.login(inputID, inputPW);
        if(result != null){
            HttpSession session = request.getSession();
            session.setAttribute("Member", result);
            session.setMaxInactiveInterval(60*10);

            List<Board> boardList = boardService.recentBoard(inputID);
            model.addAttribute("recentBoard", boardList);
            System.out.println(boardList.isEmpty());


        }else{
            try {
                response.setContentType("text/html; charset=utf-8");
                PrintWriter w = response.getWriter();
                w.println("<script language='javascript'>");
                w.println("alert('로그인에 실패하였습니다. 다시 시도해주세요'); location.href='/';");
                w.println("</script>");

                w.flush();
                //실패시
                return "redirect:/";
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return "home";
    }

    @RequestMapping(value="logout.do", method= RequestMethod.GET)
    public String logoutMainGET(HttpServletRequest request) throws Exception{

        HttpSession session = request.getSession();
        session.invalidate();

        return "redirect:/";
    }













}
