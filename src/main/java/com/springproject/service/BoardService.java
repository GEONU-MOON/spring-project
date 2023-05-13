package com.springproject.service;

import com.springproject.domain.Board;
import com.springproject.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;

    public List<Board> findList(String userid){
        return boardRepository.findListByID(userid);
    }


}
