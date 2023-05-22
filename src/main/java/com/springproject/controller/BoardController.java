package com.springproject.controller;

import com.springproject.DTO.BoardDTO;
import com.springproject.DTO.CommentDTO;
import com.springproject.domain.Board;
import com.springproject.domain.Comments;
import com.springproject.domain.Members;
import com.springproject.repository.MembersRepository;
import com.springproject.service.BoardService;
import com.springproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final CommentService commentService;
    @GetMapping("boardwrite")
    public String boardwrite(Model model){
        model.addAttribute("boardForm", new BoardDTO());

        return "boardwrite";
    }

    @PostMapping("boardwrite")
    public String boardregister(@ModelAttribute BoardDTO form, HttpSession session) throws IOException {
        Board board = new Board();
        board.setMember(boardService.setEmbedMember(((Members)session.getAttribute("Member")).getUserid()));
        board.setTitle(form.getTitle());
        board.setContent(form.getContent());
        board.setPostDate(LocalDate.now());

        MultipartFile thumbnailFile = form.getThumbnail();
        if (thumbnailFile != null && !thumbnailFile.isEmpty()) {
            String thumbnailName = StringUtils.cleanPath(thumbnailFile.getOriginalFilename());

            // 문건우용 String directory = "/Users/moongeonu/SpringProject/src/main/resources/static/assets/images/blog";
            String directory = "C:/SpringProject/src/main/resources/static/assets/images/blog";
            try {
                Files.copy(thumbnailFile.getInputStream(), Paths.get(directory, thumbnailName), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new IOException("Could not save thumbnail: " + thumbnailName);
            }
            board.setThumbnail("/assets/images/blog/" + thumbnailName);
        }
        boardService.save(board);
        return "redirect:/home";
    }



    @GetMapping("boardupdate/{id}")
    public String boardupdate(@PathVariable Long id, Model model) {
        Board board = boardService.findBoardById(id);
        if (board == null) {
            return "redirect:/";
        }
        model.addAttribute("board", board);
        return "boardupdate";
    }

    @PostMapping("boardupdate/{id}")
    public String UpdateAction(@PathVariable Long id, @RequestParam("title")String title, @RequestParam("content")String content,
                               @RequestParam("thumbnail")MultipartFile thumbnail) throws IOException {
        Board board = boardService.findBoardById(id);
        board.setTitle(title);
        board.setContent(content);

        MultipartFile thumbnailFile = thumbnail;
        if (thumbnailFile != null && !thumbnailFile.isEmpty()) {
            String thumbnailName = StringUtils.cleanPath(thumbnailFile.getOriginalFilename());

            // 문건우용 String directory = "/Users/moongeonu/SpringProject/src/main/resources/static/assets/images/updateblog";
            String directory = "C:/SpringProject/src/main/resources/static/assets/images/blog/updateblog";
            try {
                Files.copy(thumbnailFile.getInputStream(), Paths.get(directory, thumbnailName), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new IOException("Could not save thumbnail: " + thumbnailName);
            }
            board.setThumbnail("/assets/images/blog/updateblog/" + thumbnailName);
        }

        boardService.update(board);


        return "redirect:/bloglist";
    }



    @GetMapping("blogpost/{id}")
    public String blogpost(@PathVariable Long id, Model model, HttpSession session){
        Board board = boardService.findBoardById(id);
        if (board == null) {
            return "redirect:/";
        }

        String userid = ((Members)session.getAttribute("Member")).getUserid();

        Long prevBoardId = boardService.findPrevBoardId(userid, id);
        Long nextBoardId = boardService.findNextBoardId(userid, id);

        List<Comments> comments = commentService.getCommentsByBoardId(id);

        model.addAttribute("board", board);
        model.addAttribute("prevBoardId", prevBoardId);
        model.addAttribute("nextBoardId", nextBoardId);
        model.addAttribute("commentDTO", new CommentDTO());
        model.addAttribute("comments", comments);

        return "blogpost";
    }


}
