/** 
 * @Title: ColletcDao.java 
 * @Package com.wenqi.cms.dao 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 文琪 
 * @date 2020年2月18日 
 * @version V1.0 
 */ 

package com.wenqi.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wenqi.cms.pojo.Collect;

/** 
 * @Title: ColletcDao.java 
 * @Package com.wenqi.cms.dao 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 文琪 
 * @date 2020年2月18日 
 * @version V1.0 
 */

public interface CollectDao {

	
	void add(@Param("collect")Collect collect);
	
	List<Collect> selectByUserId(@Param("userId")Integer userId);
	
	void del(@Param("collectId")Integer collectId);

}
