/** 
 * @Title: Complain.java 
 * @Package com.wenqi.cms.pojo 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 文琪 
 * @date 2019年12月25日 
 * @version V1.0 
 */ 

package com.wenqi.cms.pojo;

/** 
 * @Title: Complain.java 
 * @Package com.wenqi.cms.pojo 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 文琪 
 * @date 2019年12月25日 
 * @version V1.0 
 */

public class Complain {

	private Integer id;
	private Integer article_id;
	private Integer user_id;
	private String complaintype;
	private String urlip;
	private String creatTime;
	private String title;
	private String content;
	private Integer complainnum;
	private Integer complainnum1;
	private Integer complainnum2;
	private Integer paix;
	private String username;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getPaix() {
		return paix;
	}
	public void setPaix(Integer paix) {
		this.paix = paix;
	}
	public Integer getComplainnum() {
		return complainnum;
	}
	public void setComplainnum(Integer complainnum) {
		this.complainnum = complainnum;
	}
	public Integer getComplainnum1() {
		return complainnum1;
	}
	public void setComplainnum1(Integer complainnum1) {
		this.complainnum1 = complainnum1;
	}
	public Integer getComplainnum2() {
		return complainnum2;
	}
	public void setComplainnum2(Integer complainnum2) {
		this.complainnum2 = complainnum2;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getArticle_id() {
		return article_id;
	}
	public void setArticle_id(Integer article_id) {
		this.article_id = article_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getComplaintype() {
		return complaintype;
	}
	public void setComplaintype(String complaintype) {
		this.complaintype = complaintype;
	}
	public String getUrlip() {
		return urlip;
	}
	public void setUrlip(String urlip) {
		this.urlip = urlip;
	}
	public String getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}
	public Complain() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Complain(Integer id, Integer article_id, Integer user_id, String complaintype, String urlip,
			String creatTime) {
		super();
		this.id = id;
		this.article_id = article_id;
		this.user_id = user_id;
		this.complaintype = complaintype;
		this.urlip = urlip;
		this.creatTime = creatTime;
	}
	
	
	public Complain(Integer id, Integer article_id, Integer user_id, String complaintype, String urlip,
			String creatTime, String title, String content, Integer complainnum) {
		super();
		this.id = id;
		this.article_id = article_id;
		this.user_id = user_id;
		this.complaintype = complaintype;
		this.urlip = urlip;
		this.creatTime = creatTime;
		this.title = title;
		this.content = content;
		this.complainnum = complainnum;
	}
	@Override
	public String toString() {
		return "Complain [id=" + id + ", article_id=" + article_id + ", user_id=" + user_id + ", complaintype="
				+ complaintype + ", urlip=" + urlip + ", creatTime=" + creatTime + "]";
	}
	
}
