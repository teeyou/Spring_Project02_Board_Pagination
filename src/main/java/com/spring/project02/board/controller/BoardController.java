package com.spring.project02.board.controller;

import com.spring.project02.board.dto.BoardDTO;
import com.spring.project02.board.dto.CommentDTO;
import com.spring.project02.board.dto.PageDTO;
import com.spring.project02.board.service.BoardService;
import com.spring.project02.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    private final CommentService commentService;

    @GetMapping("/save")
    public String saveForm() {
        return "save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO) {
        int saveResult = boardService.save(boardDTO);
        if(saveResult > 0) {
            return "redirect:/board/paging";
        } else {
            return "save";
        }
    }

    @GetMapping("/")
    public String findAll(Model model) {
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        return "list";
    }

    @GetMapping
    public String findById(@RequestParam("id") Long id,
                           @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                           Model model) {
        boardService.updateHits(id);
        BoardDTO boardDTO = boardService.findById(id);
        System.out.println(boardDTO.toString());
        model.addAttribute("board",boardDTO);
        model.addAttribute("page",page);

        List<CommentDTO> commentList = commentService.findAll(id);
        model.addAttribute("commentList",commentList);
        return "detail";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        boardService.delete(id);
        return "redirect:/board/";
    }

    @GetMapping("/update")
    public String updateForm(@RequestParam("id") Long id, Model model) {
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board",boardDTO);
        return "update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute BoardDTO boardDTO, Model model) {
        boardService.update(boardDTO);
//        return "redirect:/board/"; // 게시판 메인으로 이동

//        return "redirect:/board?id=" + boardDTO.getId(); // findById가 실행되면서 조회수가 증가됨

//        model.addAttribute("board",boardDTO); //boardDTO에 date, hits의 정보가 없음

        boardDTO = boardService.findById(boardDTO.getId()); //DB에서 데이터 다시 가져옴
        model.addAttribute("board", boardDTO);

        return "detail"; //조회수 증가없이 detail화면으로 이동
    }

    //경로 /board/paging?page=n
    //처음 페이지 요청은 1페이지. 쿼리스트링없으면 1페이지 보여줌
    @GetMapping("/paging")
    public String paging(Model model,
                         @RequestParam(
                                 value = "page",
                                 required = false,
                                 defaultValue = "1")
                         int page) {

        List<BoardDTO> pagingList = boardService.pagingList(page);
        System.out.println(pagingList.toString());

        PageDTO pageDTO = boardService.pagingParam(page);

        model.addAttribute("boardList", pagingList);
        model.addAttribute("paging", pageDTO);
        return "paging";
    }

}
