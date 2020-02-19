/** 
 * @Title: Collect.java 
 * @Package com.wenqi.cms.pojo 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 文琪 
 * @date 2020年2月18日 
 * @version V1.0 
 */ 

package com.wenqi.cms.pojo;

import java.io.Serializable;
import java.util.Date;

/** 
 * @Title: Collect.java 
 * @Package com.wenqi.cms.pojo 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 文琪 
 * @date 2020年2月18日 
 * @version V1.0 
 */

public class Collect implements Serializable{

	private Integer id;
	private String text;
	private String url;
	private Date created;
	private Integer user_id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		return "Collect [id=" + id + ", text=" + text + ", url=" + url + ", created=" + created + ", user_id=" + user_id
				+ "]";
	}
	public Collect() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Collect(Integer id, String text, String url, Date created, Integer user_id) {
		super();
		this.id = id;
		this.text = text;
		this.url = url;
		this.created = created;
		this.user_id = user_id;
	}
	
	
}
