package com.springproject.controller;

import com.springproject.domain.Board;
import com.springproject.domain.Members;
import com.springproject.repository.MembersRepository;
import com.springproject.service.BoardService;
import com.springproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OthersController {

    private final MemberService memberService;
    private final BoardService boardService;
    @GetMapping("/otheruserhome")
    public String otherhome(@RequestParam("otheruserid") String userid, Model model) {
        Members otherMember = memberService.findByID(userid);
        if (otherMember == null) {
            return "error";
        }
        List<Board> boardList = boardService.recentBoard(otherMember.getUserid(), 3);

        model.addAttribute("recentBoard", boardList);
        model.addAttribute("otherMember", otherMember);
        return "otheruser/home";
    }



    @GetMapping("otheruserboard")
    public String userBoard(Model model, @RequestParam("otherMember") String otherId) {
        Members otherMember = memberService.findByID(otherId);
        if (otherMember == null) {
            return "error";
        }
        List<Board> boardList = boardService.recentBoard(otherMember.getUserid(), 3);

        model.addAttribute("userBoards", boardList);
        model.addAttribute("otherMember", otherMember);
        return "otheruser/bloglist";
    }

    @GetMapping("otherblogpost/{id}")
    public String blogpost(@PathVariable Long id, Model model, @RequestParam("otherMember")String otherId){
        Board board = boardService.findBoardById(id);
        Members otherMember = memberService.findByID(otherId);
        if (board == null) {
            return "redirect:/";
        }
        model.addAttribute("board", board);
        model.addAttribute("otherMember", otherMember);
        return "otheruser/blogpost";
    }
}
