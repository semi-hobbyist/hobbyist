package com.hobbyist.notice.model.vo;

import java.io.Serializable;

public class Notice implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2163330962719212389L;
	private int noticeNo;
	private String noticeSort;
	private String noticeTitle;
	private String noticeWriter;
	private String noticeContent;
	private String noticeDate;
	private String noticeFilenameOriginal;
	private String noticeFilenameRenamed;
	private String noticeReadcount;
	private String noticeStatus;
	
	public Notice() {
		// TODO Auto-generated constructor stub
	}

	public Notice(int noticeNo, String noticeSort, String noticeTitle, String noticeWriter, String noticeContent,
			String noticeDate, String noticeFilenameOriginal, String noticeFilenameRenamed, String noticeReadcount,
			String noticeStatus) {
		super();
		this.noticeNo = noticeNo;
		this.noticeSort = noticeSort;
		this.noticeTitle = noticeTitle;
		this.noticeWriter = noticeWriter;
		this.noticeContent = noticeContent;
		this.noticeDate = noticeDate;
		this.noticeFilenameOriginal = noticeFilenameOriginal;
		this.noticeFilenameRenamed = noticeFilenameRenamed;
		this.noticeReadcount = noticeReadcount;
		this.noticeStatus = noticeStatus;
	}

	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getNoticeSort() {
		return noticeSort;
	}

	public void setNoticeSort(String noticeSort) {
		this.noticeSort = noticeSort;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeWriter() {
		return noticeWriter;
	}

	public void setNoticeWriter(String noticeWriter) {
		this.noticeWriter = noticeWriter;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public String getNoticeDate() {
		return noticeDate;
	}

	public void setNoticeDate(String noticeDate) {
		this.noticeDate = noticeDate;
	}

	public String getNoticeFilenameOriginal() {
		return noticeFilenameOriginal;
	}

	public void setNoticeFilenameOriginal(String noticeFilenameOriginal) {
		this.noticeFilenameOriginal = noticeFilenameOriginal;
	}

	public String getNoticeFilenameRenamed() {
		return noticeFilenameRenamed;
	}

	public void setNoticeFilenameRenamed(String noticeFilenameRenamed) {
		this.noticeFilenameRenamed = noticeFilenameRenamed;
	}

	public String getNoticeReadcount() {
		return noticeReadcount;
	}

	public void setNoticeReadcount(String noticeReadcount) {
		this.noticeReadcount = noticeReadcount;
	}

	public String getNoticeStatus() {
		return noticeStatus;
	}

	public void setNoticeStatus(String noticeStatus) {
		this.noticeStatus = noticeStatus;
	}

	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", noticeSort=" + noticeSort + ", noticeTitle=" + noticeTitle
				+ ", noticeWriter=" + noticeWriter + ", noticeContent=" + noticeContent + ", noticeDate=" + noticeDate
				+ ", noticeFilenameOriginal=" + noticeFilenameOriginal + ", noticeFilenameRenamed="
				+ noticeFilenameRenamed + ", noticeReadcount=" + noticeReadcount + ", noticeStatus=" + noticeStatus
				+ "]";
	}
	
	
	
}
