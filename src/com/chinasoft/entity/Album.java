package com.chinasoft.entity;

import java.util.Date;

/**
 * 专辑类
 * 
 * @author Administrarors
 *
 */
public class Album {
	/**
	 * 专辑ID
	 */
	private int albumId;
	/**
	 * 专辑名
	 */
	private String albumName;
	/**
	 * 语种ID
	 */
	private int languageId;
	/**
	 * 发行时间
	 */
	private Date releaseTime;
	/**
	 * 发行公司
	 */
	private String releaseCompany;
	/**
	 * 专辑类型
	 */
	private int typeid;

	public Album() {
		super();
	}

	public Album(String albumName, int languageId, Date releaseTime, String releaseCompany, int typeid) {
		super();
		this.albumName = albumName;
		this.languageId = languageId;
		this.releaseTime = releaseTime;
		this.releaseCompany = releaseCompany;
		this.typeid = typeid;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public int getLanguageId() {
		return languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	public String getReleaseCompany() {
		return releaseCompany;
	}

	public void setReleaseCompany(String releaseCompany) {
		this.releaseCompany = releaseCompany;
	}

	public int getTypeid() {
		return typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}

	public int getAlbumId() {
		return albumId;
	}
	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}

	@Override
	public String toString() {
		return "Album [albumId=" + albumId + ", albumName=" + albumName + ", languageId=" + languageId
				+ ", releaseTime=" + releaseTime + ", releaseCompany=" + releaseCompany + ", typeid=" + typeid + "]";
	}

}
