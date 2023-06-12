package com.ssafy.ssafit.model.dao;

import java.util.List;
import com.ssafy.ssafit.model.dto.SearchCondition;
import com.ssafy.ssafit.model.dto.User;

public interface UserDao {
	
	int insert(User user);
	
	int delete(String id);
	
	int update(User user);
	
	User selectById(String id);
	
	List<User> selectAll();
	
	List<User> searchByCondition(SearchCondition con);
}
