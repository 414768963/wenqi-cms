/** 
 * @Title: TousuController.java 
 * @Package com.wenqi.cms.controller 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 文琪 
 * @date 2019年12月23日 
 * @version V1.0 
 */ 

package com.wenqi.cms.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wenqi.cms.common.CmsConstant;
import com.wenqi.cms.common.JsonResult;
import com.wenqi.cms.pojo.Tousu;
import com.wenqi.cms.pojo.User;
import com.wenqi.cms.service.TousuService;

/** 
 * @Title: TousuController.java 
 * @Package com.wenqi.cms.controller 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 文琪 
 * @date 2019年12月23日 
 * @version V1.0 
 */
@Controller
@RequestMapping("/tousu/")
public class TousuController {

	@Autowired
	private TousuService tousuService;
	
	@RequestMapping("add")
	public @ResponseBody JsonResult add(Tousu tousu,HttpSession session) {
		
		User user = (User) session.getAttribute(CmsConstant.UserSessionKey);
		
		tousu.setUserId(user.getId());
		
		tousuService.add(tousu);
		
		return JsonResult.sucess();
	}
}
