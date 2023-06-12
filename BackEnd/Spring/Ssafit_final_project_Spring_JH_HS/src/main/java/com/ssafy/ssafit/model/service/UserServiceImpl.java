package com.ssafy.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.ssafit.model.dao.UserDao;
import com.ssafy.ssafit.model.dto.SearchCondition;
import com.ssafy.ssafit.model.dto.User;

@Service
public class UserServiceImpl implements UserService {
	
	private UserDao userDao;
	
	@Autowired
	public void setUserRepo(UserDao userRepo) {
		this.userDao = userRepo;
	}
	
	public UserDao getUserRepo() {
		return this.userDao;
	}
	
	@Override
	@Transactional
	public int insert(User user) {
		return userDao.insert(user);
	}

	@Override
	@Transactional
	public int delete(String id) {
		return userDao.delete(id);
	}

	@Override
	@Transactional
	public int update(User user) {
		return userDao.update(user);
	}

	@Override
	public User selectById(String id) {
		return userDao.selectById(id);
	}

	@Override
	public List<User> selectAll() {
		return userDao.selectAll();
	}


	@Override
	public List<User> searchByCondition(SearchCondition con) {
		System.out.println(con);
		return userDao.searchByCondition(con);
	}

}

