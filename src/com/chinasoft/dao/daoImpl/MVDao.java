package com.chinasoft.dao.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.chinasoft.dao.Dao;
import com.chinasoft.entity.MV;
import com.chinasoft.entity.MVInfo;
import com.chinasoft.entity.MVMusicAndSinger;
import com.chinasoft.entity.Music;
import com.chinasoft.util.PageModel;
import com.chinasoft.entity.MV;

public class MVDao {
	/**
	 * 插入MV信息
	 * 
	 * @param musicId
	 * @param mvAddress
	 * @Return: 1 插入mv成功 0 失败
	 * @Description: 插入mv author:
	 */
	public int insertMV(int musicId, String mvAddress) {
		Connection conn = Dao.Connection();
		String sql = "insert into mv values(null,?,?)";
		PreparedStatement ps = null;
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, musicId);
			ps.setString(2, mvAddress);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Dao.closeConn(null, null, ps, conn);
		}
		return result;
	}

	/**
	 * 
	 * @param musicName
	 *            歌曲名字 * @param singerId 歌手id
	 * @Return: 0:未找到 1~正无穷：歌曲ID
	 * @Description: 通过歌曲的名字和歌手id获得歌曲的id
	 */
	public int selectMusicIdByMusicNameAndSingerId(int singerId, String musicName) {
		Connection conn = Dao.Connection();
		String sql = "select musicid from music where musicname = ? and singerid = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		int musicId = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, musicName);
			ps.setInt(2, singerId);
			rs = ps.executeQuery();
			while (rs.next()) {
				musicId = rs.getInt("musicid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Dao.closeConn(rs, null, ps, conn);
		}
		return musicId;
	}

	/**
	 * 
	 * @param musicId
	 *            歌曲id
	 * @Return: 歌曲名
	 * @Description: 通过歌曲id获得歌曲名字
	 */
	public String selectMusicName(int musicId) {
		Connection conn = Dao.Connection();
		String sql = "select musicname from music where musicid=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		String musicName = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, musicId);
			rs = ps.executeQuery();
			while (rs.next()) {
				musicName = rs.getString("musicname");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Dao.closeConn(rs, null, ps, conn);
		}
		return musicName;

	}

	/**
	 * 
	 * @return mv对象的集合
	 * @Description: 从数据库中获取mv author:
	 */
	public ArrayList<MVMusicAndSinger> selectAllMV() {
		Connection conn = Dao.Connection();
		String sql = "select t1.mvid,t1.musicname, t1.address,t2.singername from "
				+ "(select a.mvid ,a.musicid, a.address,b.musicName ,b.singerid from mv a,music b where a.musicid=b.musicid)t1,"
				+ "singer t2 where t1.singerid=t2.singerid";
		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<MVMusicAndSinger> list = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				MVMusicAndSinger mms = new MVMusicAndSinger();
				mms.setMvId(rs.getInt("t1.mvid"));
				mms.setMusicName(rs.getString("t1.musicName"));
				mms.setAddress(rs.getString("t1.address"));
				mms.setSingerName(rs.getString("t2.singername"));
				list.add(mms);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Dao.closeConn(rs, null, ps, conn);
		}
		return list;
	}

	/**
	 * 根据歌手名查询歌手ID
	 * 
	 * @param singername
	 * @return 0：未查询到；1~正无穷：歌手ID
	 */
	public int selectSingerIdBySingerName(String singerName) {
		Connection conn = Dao.Connection();
		String sql = "select singerid from singer where singername = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, singerName);
			rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt("singerid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Dao.closeConn(rs, null, ps, conn);
		}
		return 0;
	}

	/**
	 * 根据歌手ID查询歌手名
	 * 
	 * @param singerID
	 * @return 歌手名,null歌手不存在
	 *//*
		 * public String selectSingerNameBySingerId(int singerId) { Connection
		 * conn = Dao.Connection(); String sql =
		 * "select singername from singer where singerid= ?"; PreparedStatement
		 * ps = null; ResultSet rs = null; String singerName =null; try { ps =
		 * conn.prepareStatement(sql); ps.setInt(1, singerId); rs =
		 * ps.executeQuery();
		 * 
		 * while (rs.next()) { singerName = rs.getString("singername");
		 * System.out.println(rs.getString("singername")); } } catch
		 * (SQLException e) { e.printStackTrace(); } finally { Dao.closeConn(rs,
		 * null, ps, conn); } return singerName; }
		 */
	/**
	 * 根据歌手ID查找歌曲名，MV存放地址
	 * 
	 * @param singerId
	 * @return 存放带有歌曲名的歌曲对象和带有MV存放地址的MV对象的map集合
	 */
	public Map<String, String> getMVBySingerId(int singerId) {
		Connection conn = Dao.Connection();
		String sql = "select a.musicname,b.address from music a ,mv b where a.musicid = b.musicid and singerid=?";
		PreparedStatement ps = null;
		ResultSet rs = null;

		Map<String, String> map = new HashMap<>();

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, singerId);
			rs = ps.executeQuery();
			while (rs.next()) {

				map.put(rs.getString("a.musicname"), rs.getString("b.address"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Dao.closeConn(rs, null, ps, conn);
		}
		return map;
	}

	public MVMusicAndSinger selectMusicNameAddSingerAddAddressNameByMvId(String mvId) {
		Connection conn = Dao.Connection();
		String sql = "select t1.mvid,t1.musicname, t1.address,t2.singername from "
				+ "(select a.mvid,a.musicid, a.address,b.musicName ,b.singerid from mv a,music b where a.musicid=b.musicid and mvid=?)t1,"
				+ "singer t2 where t1.singerid=t2.singerid";
		PreparedStatement ps = null;
		ResultSet rs = null;
		MVMusicAndSinger mms = null;

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, mvId);
			rs = ps.executeQuery();
			while (rs.next()) {
				mms = new MVMusicAndSinger();
				mms.setMvId(rs.getInt("t1.mvid"));
				mms.setMusicName(rs.getString("t1.musicName"));
				mms.setAddress(rs.getString("t1.address"));
				mms.setSingerName(rs.getString("t2.singername"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Dao.closeConn(rs, null, ps, conn);
		}
		return mms;
	}

	/**
	 * 修改MV信息
	 * 
	 * @param musicId
	 * @param mvAddress
	 * @Return: 1 修改mv成功 0 失败
	 * @Description: 插入mv author:
	 */
	public int updateMV(int musicId, String mvAddress) {
		Connection conn = Dao.Connection();
		String sql = "update mv set address=? where musicId = ?";
		PreparedStatement ps = null;
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, mvAddress);
			ps.setInt(2, musicId);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Dao.closeConn(null, null, ps, conn);
		}
		return result;
	}

	/**
	 * 
	 * @param pageNo
	 *            当前第几页
	 * @param pageSize
	 *            每页的条数
	 * @return
	 */
	public PageModel selectMvpaging(int pageNo, int pageSize) {
		Connection conn = Dao.Connection();
		String sql = "select t1.mvid,t1.musicname, t1.address,t2.singername from "
				+ "(select a.mvid,a.musicid, a.address,b.musicName ,b.singerid "
				+ "from mv a,music b where a.musicid=b.musicid)t1,"
				+ "singer t2 where t1.singerid=t2.singerid limit ?, ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<MVMusicAndSinger> list = new ArrayList<>();
		PageModel pm = new PageModel();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, (pageNo - 1) * pageSize);
			ps.setInt(2, pageSize);

			rs = ps.executeQuery();
			while (rs.next()) {
				MVMusicAndSinger mms = new MVMusicAndSinger();
				mms.setMvId(rs.getInt("t1.mvid"));
				mms.setMusicName(rs.getString("t1.musicname"));
				mms.setAddress(rs.getString("t1.address"));
				mms.setSingerName(rs.getString("t2.singername"));
				list.add(mms);
			}

			pm.setList(list);
			pm.setCount(selectMvCount());
			pm.setPageNo(pageNo);
			pm.setPageSize(pageSize);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Dao.closeConn(rs, null, ps, conn);
		}
		return pm;
	}

	/**
	 * 查询数据库中mv的信息条数
	 * 
	 * @return mv的信息总条数
	 */
	public int selectMvCount() {
		Connection conn = Dao.Connection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		String sql = "select count(*) from mv";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Dao.closeConn(rs, null, ps, conn);
		}

		return count;
	}

	/**
	 * 查询MV图片
	 * 
	 * @return map<MVid, MusicPictureAddress>
	 */
	public ArrayList<MVInfo> selectMVPicture() {
		String sql = "select mv.mvid, m.musicName, mp.address from mv mv, music m, musicpicture mp WHERE mv.musicid = m.musicid AND m.musicid = mp.musicid";
		Connection conn = Dao.Connection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<MVInfo> list = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				MVInfo info = new MVInfo();
				info.setMvId(rs.getInt(1));
				info.setMusicName(rs.getString(2));
				info.setMusicPictureAddress(rs.getString(3));
				list.add(info);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}