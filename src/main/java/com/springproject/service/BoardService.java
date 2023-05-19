package com.springproject.service;

import com.springproject.domain.Board;
import com.springproject.domain.EmbedMember;
import com.springproject.repository.BoardRepository;
import com.springproject.repository.MembersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;
    private final MembersRepository membersRepository;

    public List<Board> findList(String userid, int currentPage, int pageSize){
        int offset = (currentPage - 1) * pageSize;
        return boardRepository.findListByID(userid, offset, pageSize);
    }

    public Board findBoardById(Long id) {
        return boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid board Id:" + id));
    }

    public EmbedMember setEmbedMember(String userid) {return membersRepository.setBoardMember(userid);}

    public void save(Board board){boardRepository.save(board);}

    public List<Board> recentBoard(String userid, int limit){
        return boardRepository.recentBoard(userid, limit);
    }

    public Page<Board> findPagedUserBoards(String userid, int currentPage, int pageSize) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(currentPage - 1, pageSize, sort);
        return boardRepository.findPagedUserBoards(userid, pageable);
    }

    public Long findPrevBoardId(String userid, Long currentId) {
        return boardRepository.findPrevBoardId(userid, currentId);
    }

    public Long findNextBoardId(String userid, Long currentId) {
        return boardRepository.findNextBoardId(userid, currentId);
    }

    public Board findPrevBoard(String userid, Long currentId) {
        Long prevBoardId = findPrevBoardId(userid, currentId);
        return prevBoardId != null ? findBoardById(prevBoardId) : null;
    }

    public Board findNextBoard(String userid, Long currentId) {
        Long nextBoardId = findNextBoardId(userid, currentId);
        return nextBoardId != null ? findBoardById(nextBoardId) : null;
    }

    public Page<Board> findBoardsByUserid(String userid, Pageable pageable) {
        return boardRepository.findBoardsByUserid(userid, pageable);
    }

}

