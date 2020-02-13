/** 
 * @Title: ComplainDao.java 
 * @Package com.wenqi.cms.dao 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 文琪 
 * @date 2019年12月25日 
 * @version V1.0 
 */ 

package com.wenqi.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wenqi.cms.pojo.Complain;

/** 
 * @Title: ComplainDao.java 
 * @Package com.wenqi.cms.dao 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 文琪 
 * @date 2019年12月25日 
 * @version V1.0 
 */

public interface ComplainDao {

	/**  
	* @Title: add  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param @param complain
	* @param @return    设定文件  
	* @return int    返回类型  
	*/
	
	int add(@Param("complain")Complain complain);

	
	List<Complain> complainList(@Param("complain")Complain complain);

	
	
	Complain xiangQing(@Param("id")Integer id);


	/**  
	* @Title: complainLists  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param @return    设定文件  
	* @return List<Complain>    返回类型  
	*/
	
	List<Complain> complainLists();

}
