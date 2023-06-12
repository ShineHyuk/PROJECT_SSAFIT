package com.ssafy.ssafit.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.Board;
import com.ssafy.ssafit.model.service.BoardService;

@RestController
@RequestMapping("/ssafit")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
public class BoardRestController {

	@Autowired
	BoardService bs;

	@GetMapping("/board")
	public ResponseEntity<List<Board>> list(@RequestParam(defaultValue = "") String mode,
			@RequestParam(defaultValue = "") String keyword) {
		HashMap<String, String> params = new HashMap<String, String>();

		params.put("mode", mode);
		params.put("keyword", keyword);

		return new ResponseEntity<List<Board>>(bs.getBoardList(params), HttpStatus.OK);
	}

	// 내가 쓴 글을 읽을 때는 조회수가 올라가게 하지 않기 위해서 파라미터를 두개 받았다
	// 만약 dao까지 두개의 똑같은 파라미터를 받으면 @Param 을 통해서 파라미터를 구분 시켜줘야한다.
	// controller단에서는 @Pathvariable을 통해서 구분해준다.
	@GetMapping("/board/{boardId}/{id}")
	public ResponseEntity<?> detail(@PathVariable String boardId, @PathVariable String id) {
		try {
			Board board = bs.boardDetail(Integer.parseInt(boardId));
			System.out.println(board);
			if (board != null) {
				// 내가 작성한 글을 읽을 때는 조회수가 올라가게 하지않는다.
//				if (!board.getWriter().equals(id)) {
//					board = bs.readBoard(Integer.parseInt(boardId));
//				}
				board = bs.readBoard(Integer.parseInt(boardId));
				return new ResponseEntity<Board>(board, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@PostMapping("/board")
	public ResponseEntity<?> write(Board board) {
		try {
			bs.writeBoard(board);
			Board b = bs.boardDetail(board.getBoardId());
			return new ResponseEntity<Board>(b, HttpStatus.CREATED);

		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	// 게시글 수정
	@PutMapping("/board")
	public ResponseEntity<?> update(Board board) {
		try {
			System.out.println(board);
			bs.updateBoard(board);
			Board b = bs.boardDetail(board.getBoardId());
			return new ResponseEntity<Board>(b, HttpStatus.OK);

		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@DeleteMapping("/board/{boardId}")
	public ResponseEntity<?> delete(@PathVariable int boardId) {
		try {
			int result = bs.deleteBoard(boardId);
			return new ResponseEntity<Integer>(result, HttpStatus.OK);

		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Sorry: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
