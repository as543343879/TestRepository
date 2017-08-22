package com.mycompany.myapp.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class Exam12Member {
	private String  mid;
	private String  mname;
	private String  mpassword;
	private Date mdate;
	private String  mtel;
	private String  memail;
	private int mage;
	private String maddress;
	private String moriginalfilename;
	private String msavedfilename;
	private String mfilecontent;
	private MultipartFile mattach;
	
	public Exam12Member(){
		
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getMpassword() {
		return mpassword;
	}

	public void setMpassword(String mpassword) {
		this.mpassword = mpassword;
	}

	public Date getMdate() {
		return mdate;
	}

	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}

	public String getMtel() {
		return mtel;
	}

	public void setMtel(String mtel) {
		this.mtel = mtel;
	}


	public String getMemail() {
		return memail;
	}

	public void setMemail(String memail) {
		this.memail = memail;
	}

	public int getMage() {
		return mage;
	}

	public void setMage(int mage) {
		this.mage = mage;
	}

	public String getMaddress() {
		return maddress;
	}

	public void setMaddress(String maddress) {
		this.maddress = maddress;
	}

	public String getMoriginalfilename() {
		return moriginalfilename;
	}

	public void setMoriginalfilename(String moriginalfilename) {
		this.moriginalfilename = moriginalfilename;
	}

	public String getMsavedfilename() {
		return msavedfilename;
	}

	public void setMsavedfilename(String msavedfilename) {
		this.msavedfilename = msavedfilename;
	}

	public String getMfilecontent() {
		return mfilecontent;
	}

	public void setMfilecontent(String mfilecontent) {
		this.mfilecontent = mfilecontent;
	}

	public MultipartFile getMattach() {
		return mattach;
	}

	public void setMattach(MultipartFile mattach) {
		this.mattach = mattach;
	}


}
