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

import com.ssafy.ssafit.model.dto.Notice;
import com.ssafy.ssafit.model.service.NoticeService;

@RestController
@RequestMapping("/ssafit")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
public class NoticeRestController {

	@Autowired
	NoticeService ns;

	@GetMapping("/notice")
	public ResponseEntity<List<Notice>> list(@RequestParam(defaultValue = "") String mode,
			@RequestParam(defaultValue = "") String keyword) {
		HashMap<String, String> params = new HashMap<String, String>();

		params.put("mode", mode);
		params.put("keyword", keyword);

		return new ResponseEntity<List<Notice>>(ns.getNoticeList(params), HttpStatus.OK);
	}

	// 내가 쓴 글을 읽을 때는 조회수가 올라가게 하지 않기 위해서 파라미터를 두개 받았다
	// 만약 dao까지 두개의 똑같은 파라미터를 받으면 @Param 을 통해서 파라미터를 구분 시켜줘야한다.
	// controller단에서는 @Pathvariable을 통해서 구분해준다.
	@GetMapping("/notice/{noticeId}")
	public ResponseEntity<?> detail(@PathVariable int noticeId) {
		try {
			Notice notice = ns.noticeDetail(noticeId);
			System.out.println(notice);
			if (notice != null) {

				notice = ns.readNotice(noticeId);
				return new ResponseEntity<Notice>(notice, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@PostMapping("/notice")
	public ResponseEntity<?> write( Notice notice) {
		try {
			ns.writeNotice(notice);
			Notice n = ns.noticeDetail(notice.getNoticeId());
			return new ResponseEntity<Notice>(n, HttpStatus.CREATED);

		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	// 게시글 수정
	@PutMapping("/notice")
	public ResponseEntity<?> update( Notice notice) {
		try {
			System.out.println("수정해야할 공지사항"+notice);
			ns.updateNotice(notice);
			Notice n=ns.noticeDetail(notice.getNoticeId());
			System.out.println("수정완료 공지사항"+n);
			return new ResponseEntity<Notice>(n, HttpStatus.OK);

		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@DeleteMapping("/notice/{noticeId}")
	public ResponseEntity<?> delete(@PathVariable int noticeId) {
		try {
			int result = ns.deleteNotice(noticeId);
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
