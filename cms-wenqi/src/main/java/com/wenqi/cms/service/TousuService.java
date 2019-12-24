/** 
 * @Title: TousuService.java 
 * @Package com.wenqi.cms.service 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 文琪 
 * @date 2019年12月23日 
 * @version V1.0 
 */ 

package com.wenqi.cms.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bawei.wenqi.utils.DateUtil;
import com.wenqi.cms.dao.TousuDao;
import com.wenqi.cms.pojo.Tousu;

/** 
 * @Title: TousuService.java 
 * @Package com.wenqi.cms.service 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 文琪 
 * @date 2019年12月23日 
 * @version V1.0 
 */
@Service
public class TousuService {

	@Autowired
	private TousuDao tousuDao;
	
	@Autowired 
	private ArticleService articleService;

	/**  
	* @Title: add  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param @param tousu    设定文件  
	* @return void    返回类型  
	*/
	
	public boolean add(Tousu tousu) {
		String createTime = DateUtil.dateTimeFormat.format(new Date());
		tousu.setCreated(createTime);
		int i = tousuDao.add(tousu);
		if(i>0) {
			i = articleService.addTousuCnt(tousu.getArticleId());
		}
		return i>0;
	}
	
	
	
}
