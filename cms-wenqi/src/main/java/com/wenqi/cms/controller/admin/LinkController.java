

package com.wenqi.cms.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.wenqi.cms.common.JsonResult;
import com.wenqi.cms.pojo.Link;
import com.wenqi.cms.service.LinksService;

/** 
 * @Title: LinkController.java 
 * @Package com.wenqi.cms.controller.admin 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 文琪 
 * @date 2019年12月20日 
 * @version V1.0 
 */
@Controller
@RequestMapping("/admin/link/")
public class LinkController {
	
	@Autowired
	private LinksService linksService;
	
	@RequestMapping("list")
	public String list(Model m,Link link,
			@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,@RequestParam(value = "pageSize",defaultValue = "3")Integer pageSize) {
		
		PageInfo<Link> pageInfo = linksService.getPageInfo(link,pageNum,pageSize);
		
		m.addAttribute("pageInfo", pageInfo);
		
		return "link/list";
	}
	
	@RequestMapping(value = "edit",method = RequestMethod.GET)
	public String edit(Integer id,Model m) {
		
		if(id!=null) {
			Link link = linksService.selectById(id);
			m.addAttribute("link", link);
		}
		
		return "link/edit";
	}
	
	@RequestMapping("save")
	public @ResponseBody JsonResult save(Link link) {
		
		linksService.save(link);
		
		return JsonResult.sucess();
	}
	
	@RequestMapping("del")
	public @ResponseBody JsonResult del(Integer[] ids) {
		
		if(ids!=null) {
			linksService.del(ids);
		}
		
		return JsonResult.sucess();
	}
}
