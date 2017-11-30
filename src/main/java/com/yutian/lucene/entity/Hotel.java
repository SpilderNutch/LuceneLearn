package com.yutian.lucene.entity;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Hotel {
	private Integer id;
	private String lkName;
	private String lkSex;
	private String lkIdCode;
	private String lkAddress;
	private String lgHName;
	private String lgHAddress;
	private String lgTelphone;
	private String lkNoroom;
	private Date lkLtime;
	private Date lkEtime;
	private Date lkCreatetime;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLkName() {
		return lkName;
	}

	public void setLkName(String lkName) {
		this.lkName = lkName;
	}

	public String getLkSex() {
		return lkSex;
	}

	public void setLkSex(String lkSex) {
		this.lkSex = lkSex;
	}

	public String getLkIdCode() {
		return lkIdCode;
	}

	public void setLkIdCode(String lkIdCode) {
		this.lkIdCode = lkIdCode;
	}

	public String getLkAddress() {
		return lkAddress;
	}

	public void setLkAddress(String lkAddress) {
		this.lkAddress = lkAddress;
	}

	public String getLgHName() {
		return lgHName;
	}

	public void setLgHName(String lgHName) {
		this.lgHName = lgHName;
	}

	public String getLgHAddress() {
		return lgHAddress;
	}

	public void setLgHAddress(String lgHAddress) {
		this.lgHAddress = lgHAddress;
	}

	public String getLgTelphone() {
		return lgTelphone;
	}

	public void setLgTelphone(String lgTelphone) {
		this.lgTelphone = lgTelphone;
	}

	public String getLkNoroom() {
		return lkNoroom;
	}

	public void setLkNoroom(String lkNoroom) {
		this.lkNoroom = lkNoroom;
	}

	public Date getLkLtime() {
		return lkLtime;
	}

	public void setLkLtime(Date lkLtime) {
		this.lkLtime = lkLtime;
	}

	public Date getLkEtime() {
		return lkEtime;
	}

	public void setLkEtime(Date lkEtime) {
		this.lkEtime = lkEtime;
	}

	public Date getLkCreatetime() {
		return lkCreatetime;
	}

	public void setLkCreatetime(Date lkCreatetime) {
		this.lkCreatetime = lkCreatetime;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
				.append("id:"+getId())
				.append("lkName:",getLkName())
				.append("lkIdCode",getLkIdCode())
				.append("lkLtime:",getLkLtime())
				.append("lkEtime:",getLkEtime())
				.append("lkNoroom:",getLkNoroom())
				.append("lgHName:",getLgHName())
				.append("lkCreatetime:",getLkCreatetime())
				.toString();
	}
	
	
}