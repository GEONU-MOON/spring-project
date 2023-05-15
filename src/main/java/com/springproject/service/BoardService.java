package com.springproject.service;

import com.springproject.domain.Board;
import com.springproject.domain.EmbedMember;
import com.springproject.repository.BoardRepository;
import com.springproject.repository.MembersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;
    private final MembersRepository membersRepository;

    public List<Board> findList(String userid){
        return boardRepository.findListByID(userid);
    }

    public EmbedMember setEmbedMember(String userid) {return membersRepository.setBoardMember(userid);}

    public void save(Board board){boardRepository.save(board);}
}
