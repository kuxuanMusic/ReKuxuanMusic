package com.chinasoft.entity;

public class MVInfo {
	private int mvId;
	private String musicName;
	private String musicPictureAddress;

	public MVInfo() {
		super();
	}

	public MVInfo(int mvId, String musicName, String musicPictureAddress) {
		this.musicName = musicName;
		this.musicPictureAddress = musicPictureAddress;
	}

	public int getMvId() {
		return mvId;
	}

	public void setMvId(int mvId) {
		this.mvId = mvId;
	}

	public String getMusicName() {
		return musicName;
	}

	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}

	public String getMusicPictureAddress() {
		return musicPictureAddress;
	}

	public void setMusicPictureAddress(String musicPictureAddress) {
		this.musicPictureAddress = musicPictureAddress;
	}

	@Override
	public String toString() {
		return "MVInfo [mvId=" + mvId + ", musicName=" + musicName + ", musicPictureAddress=" + musicPictureAddress
				+ "]";
	}
}
