package com.ssafy.ssafit.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.SearchCondition;
import com.ssafy.ssafit.model.dto.User;
import com.ssafy.ssafit.model.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/ssafit")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET , RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class UserRestController {

	@Autowired
	UserService us;

	@GetMapping("/user")
	@ApiOperation(value = "등록된 모든 사용자 정보를 반환한다.", response = User.class)
	public ResponseEntity<?> selectAll() {
		try {
			List<User> users = us.selectAll();
			System.out.println(users);
			if (users != null && users.size() > 0) {
				return new ResponseEntity<List<User>>(users, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/user/{id}")
	@ApiOperation(value = "{id}에 해당하는 사용자 정보를 반환한다.", response = User.class)
	public ResponseEntity<?> select(@PathVariable String id) {
		System.out.println(us.selectById(id));
		try {
			User user = us.selectById(id);
			if (user != null) {
				return new ResponseEntity<User>(user, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/user/search")
	@ApiOperation(value = "SearchCondition 에 부합하는 조건을 가진 사용자 목록을 반환한다.", response = User.class)
	public ResponseEntity<?> search(SearchCondition con) {
		System.out.println(con);
		try {
			List<User> users = us.searchByCondition(con);
			if (users != null && users.size() > 0) {
				System.out.println(users);
				return new ResponseEntity<List<User>>(users, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@PostMapping("/user")
	@ApiOperation(value = "사용자 정보를 삽입한다.", response = Integer.class)
	public ResponseEntity<?> insert(@RequestBody User user) {
		try {
			System.out.println(user);
			User u=us.selectById(user.getId());
			return new ResponseEntity<User>(u, HttpStatus.CREATED);

		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@PutMapping("/user")
	@ApiOperation(value = "사용자 정보를 수정한다.", response = Integer.class)
	public ResponseEntity<?> update(@RequestBody User user) {
		try {
			int result = us.update(user);
			return new ResponseEntity<Integer>(result, HttpStatus.OK);

		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@DeleteMapping("/user/{id}")
	@ApiOperation(value = "{id} 에 해당하는 사용자 정보를 삭제한다.", response = Integer.class)
	public ResponseEntity<?> delete(@PathVariable String id) {
		try {
			int result = us.delete(id);
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
