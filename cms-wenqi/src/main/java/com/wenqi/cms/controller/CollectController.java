/** 
 * @Title: CollectController.java 
 * @Package com.wenqi.cms.controller 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 文琪 
 * @date 2020年2月18日 
 * @version V1.0 
 */ 

package com.wenqi.cms.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bawei.wenqi.utils.StringUtil;
import com.wenqi.cms.common.CmsConstant;
import com.wenqi.cms.common.JsonResult;
import com.wenqi.cms.pojo.Collect;
import com.wenqi.cms.pojo.User;
import com.wenqi.cms.service.CollectService;

/** 
 * @Title: CollectController.java 
 * @Package com.wenqi.cms.controller 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 文琪 
 * @date 2020年2月18日 
 * @version V1.0 
 */
@Controller
@RequestMapping("/collect/")
public class CollectController {

	@Autowired
	private CollectService collectService;
	
	@RequestMapping("add")
	@ResponseBody
	public JsonResult add(@RequestParam(value = "collectUrlip")String collectUrlip,
							@RequestParam(value = "collectText")String collectText,
							HttpSession session) {
		//判断链接地址不为空
		if(collectUrlip!=null || collectUrlip!="") {
			//判断是否为正确的连接地址
			if(StringUtil.isHttpUrls(collectUrlip)) {
				//添加数据
				User user = (User) session.getAttribute(CmsConstant.UserSessionKey);
				Collect collect = new Collect();
				collect.setUser_id(user.getId());
				collect.setUrl(collectUrlip);
				collect.setCreated(new Date());
				collect.setText(collectText);
				//保存到数据库
				collectService.add(collect);
			}else {
				JsonResult.fail(2020,"URL地址不正确");
			}
		}
		return JsonResult.sucess();
	}
	
	@RequestMapping("del")
	@ResponseBody
	public JsonResult del(@RequestParam(value = "collectId")Integer collectId) {
		
		collectService.del(collectId);
		
		return JsonResult.sucess();
	}
}
