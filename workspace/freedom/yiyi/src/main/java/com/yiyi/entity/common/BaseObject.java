package com.yiyi.entity.common;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @ClassName IdEntity
 * @Description: TODO(entity公共属性)
 * @author HuGuangJun
 * @date 2016年4月19日 上午9:52:07
 *
 */
public class BaseObject implements Serializable {
	/**
	 * @Fields serialVersionUID: TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	/** id属性 */
	protected Long id;
	/** 版本信息 */
	protected int version;
	/** 删除标识 */
	protected String delFlag;
	/** 创建人 */
	protected String crtOperator;
	/** 创建时间 */
	protected Date crtTime;
	/** 修改人 */
	protected String updOperator;
	/** 修改时间 */
	protected Date updTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public void setCrtTime(Date crtTime) {
		this.crtTime = crtTime;
	}

	public Date getUpdTime() {
		return updTime;
	}

	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}

	public String getCrtOperator() {
		return crtOperator;
	}

	public void setCrtOperator(String crtOperator) {
		this.crtOperator = crtOperator;
	}

	public String getUpdOperator() {
		return updOperator;
	}

	public void setUpdOperator(String updOperator) {
		this.updOperator = updOperator;
	}

	public Date getCrtTime() {
		return crtTime;
	}

}
