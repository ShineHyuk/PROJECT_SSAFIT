package com.ssafy.ssafit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.SearchCondition;
import com.ssafy.ssafit.model.dto.Video;
import com.ssafy.ssafit.model.service.VideoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/ssafit")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class VideoRestController {

	@Autowired
	private VideoService videoService;

	// 전체 영상 리스트를 가져오기
	@GetMapping("/video")
	@ApiOperation(value = "전체 영상 리스트 반환하기", response = Video.class)
	public ResponseEntity<?> selectAll() {
		List<Video> list = videoService.getVideoList();

		if (list == null || list.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Video>>(list, HttpStatus.OK);
	}
	
	// 부위별 영상 리스트를 가져오기
		@GetMapping("/video/part/{exerciseCode}")
		@ApiOperation(value = "전체 영상 리스트 반환하기", response = Video.class)
		public ResponseEntity<?> selectByPart(@PathVariable int exerciseCode) {
			List<Video> list = videoService.getVideoListByPart(exerciseCode);

			if (list == null || list.size() == 0) {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Video>>(list, HttpStatus.OK);
		}

	// 영상 상세보기
	@GetMapping("/video/{id}")
	@ApiOperation(value = "id에 해당하는 영상 상세보기", response = Video.class)
	public ResponseEntity<Video> detail(@PathVariable String id) {
		Video video = videoService.showDetail(id);
		return new ResponseEntity<Video>(video, HttpStatus.OK);
	}

	@GetMapping("/video/search")
	@ApiOperation(value = "SearchCondition 에 부합하는 조건을 가진 사용자 목록을 반환한다.", response = Video.class)
	public ResponseEntity<?> search(SearchCondition con) {
		try {
			List<Video> users = videoService.search(con);
			if (users != null && users.size() > 0) {
				return new ResponseEntity<List<Video>>(users, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return null;
		}
	}
}
