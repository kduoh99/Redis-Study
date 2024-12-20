package com.study.redis.board.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.study.redis.board.application.BoardService;
import com.study.redis.board.domain.Board;

@RestController
@RequestMapping("boards")
public class BoardController {

	private final BoardService boardService;

	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}

	@GetMapping()
	public List<Board> getBoards(@RequestParam(defaultValue = "1") int page,
		@RequestParam(defaultValue = "10") int size) {
		return boardService.getBoards(page, size);
	}
}
