/** 
 * @Title: LinksService.java 
 * @Package com.wenqi.cms.service 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 文琪 
 * @date 2019年12月20日 
 * @version V1.0 
 */ 

package com.wenqi.cms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wenqi.cms.dao.LinksDao;
import com.wenqi.cms.pojo.Link;

/** 
 * @Title: LinksService.java 
 * @Package com.wenqi.cms.service 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 文琪 
 * @date 2019年12月20日 
 * @version V1.0 
 */
@Service
public class LinksService {

	@Autowired
	private LinksDao linksDao;

	/**  
	* @Title: getPageInfo  
	* @Description: 查询友情链接
	* @param @param link
	* @param @param pageNum
	* @param @param pageSize
	* @param @return    设定文件  
	* @return PageInfo<Link>    返回类型  
	*/
	
	public PageInfo<Link> getPageInfo(Link link, Integer pageNum, Integer pageSize) {
		
		PageHelper.startPage(pageNum, pageSize);
		
		List<Link> list = linksDao.select(link);
		
		return new PageInfo<Link>(list);
	}

	/**  
	* @Title: selectById  
	* @Description: 查询一条数据，返回link对象
	* @param @param id
	* @param @return    设定文件  
	* @return Link    返回类型  
	*/
	
	public Link selectById(Integer id) {
		return linksDao.selectById(id);
	}

	/**  
	* @Title: save  
	* @Description: 保存查询或添加
	* @param @param link    设定文件  
	* @return void    返回类型  
	*/
	
	public boolean save(Link link) {
		link.setCreated(new Date());
		if(link.getId()!=null) {
			return linksDao.updateLinks(link)>0;
		}
		return linksDao.addLinks(link)>0;
		
	}

	/**  
	* @Title: del  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param @param ids    设定文件  
	* @return void    返回类型  
	*/
	
	public boolean del(Integer[] ids) {

		return linksDao.del(ids)>0;
	}
	
	
}
