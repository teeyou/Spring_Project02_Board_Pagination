package com.spring.project02.board.service;

import com.spring.project02.board.dto.BoardDTO;
import com.spring.project02.board.dto.PageDTO;
import com.spring.project02.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private int pageLimit = 4;  //페이지마다 보여줄 게시글 수
    private int blockLimit = 5; //하단에 보여줄 페이지 번호 수

    public int save(BoardDTO boardDTO) {
        return boardRepository.save(boardDTO);
    }

    public List<BoardDTO> findAll() {
        return boardRepository.findAll();
    }

    public BoardDTO findById(Long id) {
        return boardRepository.findById(id);
    }

    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    public void delete(Long id) {
        boardRepository.delete(id);
    }

    public void update(BoardDTO boardDTO) {
        boardRepository.update(boardDTO);
    }

    public List<BoardDTO> pagingList(int page) {
        //페이지 당 3개의 게시글 보여줌
//        int pageLimit = 3;
        int pageStart = (page - 1) * pageLimit;
        Map<String, Integer> pagingParams = new HashMap<>();
        pagingParams.put("start", pageStart);
        pagingParams.put("limit", pageLimit);
        List<BoardDTO> pagingList = boardRepository.pagingList(pagingParams);
        return pagingList;
    }

    public PageDTO pagingParam(int page) {
        int boardCount = boardRepository.boardCount();
        int maxPage = boardCount / pageLimit;
        if(boardCount % pageLimit != 0)
            maxPage++;

        int startPage = page / blockLimit;
        if(page % blockLimit == 0)
            startPage--;
        startPage = startPage * blockLimit + 1;

        int endPage = startPage + blockLimit - 1;

        if(startPage + blockLimit - 1 > maxPage)
            endPage = maxPage;

        PageDTO pageDTO = new PageDTO(page,maxPage,startPage,endPage);
        return pageDTO;
    }
}
