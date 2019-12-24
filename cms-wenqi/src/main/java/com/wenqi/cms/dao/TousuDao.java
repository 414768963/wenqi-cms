/** 
 * @Title: TousuDao.java 
 * @Package com.wenqi.cms.dao 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 文琪 
 * @date 2019年12月23日 
 * @version V1.0 
 */ 

package com.wenqi.cms.dao;

import org.apache.ibatis.annotations.Param;

import com.wenqi.cms.pojo.Tousu;

/** 
 * @Title: TousuDao.java 
 * @Package com.wenqi.cms.dao 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 文琪 
 * @date 2019年12月23日 
 * @version V1.0 
 */

public interface TousuDao {

	int add(@Param("tousu")Tousu tousu);

}
