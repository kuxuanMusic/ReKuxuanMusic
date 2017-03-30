package com.chinasoft.dao.daoImpl;

import java.sql.*;
import java.util.*;

import com.chinasoft.dao.*;
import com.chinasoft.entity.User;
import com.chinasoft.util.PageModel;

public class UserDao {
	Connection conn = Dao.Connection();
	PreparedStatement pst = null;
	ResultSet rs = null;
	Statement st = null;
	ArrayList<User> list = new ArrayList<>();
	User user;

	/**
	 * description: 通过用户名查询用户
	 * 
	 * @return user 用户存在 null 用户不存在
	 */
	public User selectUserByname(String name) {
		String sql = "select * from user where username = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, name);

			rs = pst.executeQuery();

			while (rs.next()) {
				user = new User(rs.getString("password"));
				user.setUserId(rs.getInt("userid"));
				user.setUsername(rs.getString("username"));

				user.setUserType(rs.getInt("typeid"));
				System.out.println(rs.getInt("typeid"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Dao.closeConn(rs, null, pst, conn);
		}
		return user;
	}

	/**
	 * description:通过用户名查询用户信息
	 * 
	 * @param username
	 *            用户名
	 * @return 1 用户存在 0 用户不存在
	 */
	public int selectUserByUsername(String username) {
		String sql = "select * from user where username = ?";

		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);

			rs = pst.executeQuery();

			while (rs.next()) {
				return 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Dao.closeConn(rs, null, pst, conn);
		}
		return 0;
	}

	public ArrayList<User> selectUserById(int id) {
		String sql = "select * from user where userid = ?";

		try {
			pst = conn.prepareStatement(sql);

			pst.setInt(1, id);
			rs = pst.executeQuery();

			while (rs.next()) {
				user = new User(rs.getString("password"));

				user.setUserId(rs.getInt("userid"));
				user.setUsername(rs.getString("username"));

				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Dao.closeConn(rs, pst, null, conn);
		}
		return list;
	}

	/**
	 * description:添加用户
	 * 
	 * @param user
	 *            用户个体
	 */
	public void insertUser(User user) {
		String sql = "insert into user values(null,?,?,?)";

		try {
			pst = conn.prepareStatement(sql);

			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPassword());
			pst.setInt(3, user.getUserType());

			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Dao.closeConn(null, null, pst, conn);
		}
	}

	/**
	 * description:通过用户id修改用户类型
	 * 
	 * @param id
	 *            用户id
	 * @param str
	 *            用户将成为的用户类型
	 */
	public void updateUserById(int id, String str) {
		String sql = "update user set typeid = " + "(select typeid from usertype " + "where typename=?) where userid=?";

		try {
			pst = conn.prepareStatement(sql);

			pst.setString(1, str);
			pst.setInt(2, id);

			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Dao.closeConn(null, null, pst, conn);
		}
	}

	/**
	 * description:通过id删除用户
	 * 
	 * @param id
	 *            用户id
	 */
	public void deleteUserById(int id) {
		String sql = "delete from user where userid = ?";

		try {
			pst = conn.prepareStatement(sql);

			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Dao.closeConn(null, null, pst, conn);
		}
	}

	public ArrayList<User> selectUserPage(int pageNO, int pageSize) {
		String sql = "select * from user limit ?, ?";

		ArrayList<User> list = new ArrayList<>();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, (pageNO - 1) * pageSize);
			pst.setInt(2, pageSize);

			rs = pst.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUserId(rs.getInt("userid"));
				user.setUsername(rs.getString("username"));
				user.setUserType(rs.getInt("typeid"));

				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Dao.closeConn(rs, null, pst, conn);
		}
		return list;
	}

	public int selectCountUser() {
		int count = 0;
		String sql = "select count(*) from user";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Dao.closeConn(rs, st, null, conn);
		}

		return count;
	}

	public PageModel selectUserNew(int pageNo, int pageSize) {
		String sql = "select * from user limit ?, ?";

		ArrayList<User> list = new ArrayList<>();
		PageModel pm = new PageModel();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, (pageNo - 1) * pageSize);
			pst.setInt(2, pageSize);

			rs = pst.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUserId(rs.getInt("userid"));
				user.setUsername(rs.getString("username"));
				user.setUserType(rs.getInt("typeid"));

				list.add(user);
			}

			pm.setList(list);
			pm.setCount(selectCountUser());
			pm.setPageNo(pageNo);
			pm.setPageSize(pageSize);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Dao.closeConn(rs, null, pst, conn);
		}

		return pm;
	}
	public int selectUserByUserNameAndPassword(User user) {
		String sql = "select * from user where username = ? and password = ?";
		Connection connection = Dao.Connection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			rs = ps.executeQuery();
			while(rs.next()){
				return 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;

	}
}
