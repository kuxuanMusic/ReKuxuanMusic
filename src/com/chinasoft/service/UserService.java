package com.chinasoft.service;

import java.util.*;

import com.chinasoft.util.PageModel;
import com.chinasoft.dao.daoImpl.UserDao;
import com.chinasoft.entity.User;

public class UserService {

	public User selectUserInfoByName(String name) {
		UserDao uDao = new UserDao();
		User user = uDao.selectUserByname(name);

		return user;
	}

	public ArrayList<User> selectUserByUserId(int id) {
		UserDao uDao = new UserDao();
		ArrayList<User> list = uDao.selectUserById(id);
		return list;
	}

	public boolean selectUserByname(String name) {
		UserDao uDao = new UserDao();
		int i = uDao.selectUserByUsername(name);

		if (i == 1) {
			return true;
		} else {
			return false;
		}
	}

	public void addUser(User user) {
		UserDao uDao = new UserDao();
		uDao.insertUser(user);
	}

	public void change(int id, String str) {
		UserDao uDao = new UserDao();
		uDao.updateUserById(id, str);
	}

	public void deleteUserById(int id) {
		UserDao uDao = new UserDao();

		uDao.deleteUserById(id);
	}

	public PageModel getUserPage(int pageNO, int pageSize) {
		UserDao uDao = new UserDao();
		PageModel pm = uDao.selectUserNew(pageNO, pageSize);

		return pm;
	}

	public int selectUserByUserNameAndPassword(User user) {
		UserDao userDao = new UserDao();
		return userDao.selectUserByUserNameAndPassword(user);
	}
}
