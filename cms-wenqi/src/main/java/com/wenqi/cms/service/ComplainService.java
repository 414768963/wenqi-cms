/** 
 * @Title: ComplainService.java 
 * @Package com.wenqi.cms.service 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 文琪 
 * @date 2019年12月25日 
 * @version V1.0 
 */ 

package com.wenqi.cms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bawei.wenqi.utils.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.wenqi.cms.dao.ComplainDao;
import com.wenqi.cms.pojo.Complain;

/** 
 * @Title: ComplainService.java 
 * @Package com.wenqi.cms.service 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 文琪 
 * @date 2019年12月25日 
 * @version V1.0 
 */
@Service
public class ComplainService {

	@Autowired
	private ComplainDao complainDao;
	
	@Autowired
	private ArticleService articleService;

	/**  
	* @Title: add  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param @param complain    设定文件  
	* @return void    返回类型  
	*/
	
	public boolean add(Complain complain) {
		String time = DateUtil.dateTimeFormat.format(new Date());
		complain.setCreatTime(time);
		int i = complainDao.add(complain);
		if(i>0) {
			i = articleService.addComplain(complain.getArticle_id());
		}
		return i>0;
	}

	/**  
	* @Title: complainList  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param @param pageNum
	* @param @param pageSize
	* @param @param complain
	* @param @return    设定文件  
	* @return PageInfo<Complain>    返回类型  
	*/
	
	public PageInfo<Complain> complainList(Integer pageNum, Integer pageSize, Complain complain) {
		PageMethod.startPage(pageNum, pageSize);
		
		List<Complain> list = complainDao.complainList(complain);
		
		
		return new PageInfo<Complain>(list);
	}

	/**  
	* @Title: xiangQing  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param @param id
	* @param @return    设定文件  
	* @return Complain    返回类型  
	*/
	
	public Complain xiangQing(Integer id) {
		return complainDao.xiangQing(id);
	}

	/**  
	* @Title: complainLists  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param @return    设定文件  
	* @return Complain    返回类型  
	*/
	
	public List<Complain> complainLists() {
		return complainDao.complainLists();
	}
	
	
}
