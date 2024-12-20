package com.study.redis.board.application;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.study.redis.board.domain.Board;
import com.study.redis.board.domain.repository.BoardRepository;

@Service
public class BoardService {
	private final BoardRepository boardRepository;

	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}

	// Cache Aside 전략으로 캐싱 적용
	@Cacheable(cacheNames = "getBoards", key = "'boards:page:' + #page + ':size' + #size", cacheManager = "boardCacheManager")
	public List<Board> getBoards(int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		Page<Board> pageOfBoards = boardRepository.findAllByOrderByCreatedAtDesc(pageable);
		return pageOfBoards.getContent();
	}
}
