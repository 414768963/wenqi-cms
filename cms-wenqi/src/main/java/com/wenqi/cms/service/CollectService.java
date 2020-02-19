/** 
 * @Title: CollectService.java 
 * @Package com.wenqi.cms.service 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 文琪 
 * @date 2020年2月18日 
 * @version V1.0 
 */ 

package com.wenqi.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenqi.cms.dao.CollectDao;
import com.wenqi.cms.pojo.Collect;

/** 
 * @Title: CollectService.java 
 * @Package com.wenqi.cms.service 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 文琪 
 * @date 2020年2月18日 
 * @version V1.0 
 */
@Service
public class CollectService {

	@Autowired
	private CollectDao collectDao;
	
	public void add(Collect collect) {
		collectDao.add(collect);
	}
	
	public List<Collect> selectByUserId(Integer userId){
		List<Collect> list = collectDao.selectByUserId(userId);
		return list;
	}

	/**  
	* @Title: del  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param @param collectId    设定文件  
	* @return void    返回类型  
	*/
	
	public void del(Integer collectId) {
		collectDao.del(collectId);
	}
}
