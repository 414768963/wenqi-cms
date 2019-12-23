
package com.wenqi.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wenqi.cms.pojo.Link;

/** 
 * @Title: LinksDao.java 
 * @Package com.wenqi.cms.dao 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 文琪 
 * @date 2019年12月20日 
 * @version V1.0 
 */

public interface LinksDao {

	List<Link> select(@Param("link") Link link);
	
	Link selectById(@Param("id")Integer id);

	int updateLinks(@Param("link")Link link);
	
	int addLinks(@Param("link")Link link);
	
	int del(@Param("ids")Integer[] ids);
}
