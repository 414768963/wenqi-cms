/** 
 * @Title: ComplainController.java 
 * @Package com.wenqi.cms.controller 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 文琪 
 * @date 2019年12月25日 
 * @version V1.0 
 */ 

package com.wenqi.cms.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bawei.wenqi.utils.StringUtil;
import com.github.pagehelper.PageInfo;
import com.wenqi.cms.common.CmsConstant;
import com.wenqi.cms.common.JsonResult;
import com.wenqi.cms.pojo.Article;
import com.wenqi.cms.pojo.Complain;
import com.wenqi.cms.pojo.User;
import com.wenqi.cms.service.ArticleService;
import com.wenqi.cms.service.ComplainService;

/** 
 * @Title: ComplainController.java 
 * @Package com.wenqi.cms.controller 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 文琪 
 * @date 2019年12月25日 
 * @version V1.0 
 */
@Controller
@RequestMapping("/admin/complain/")
public class ComplainController {

	@Autowired
	private ComplainService complainService;
	
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping("add")
	public @ResponseBody JsonResult add(Complain complain,HttpSession session,@RequestParam(value = "article_id")Integer article_id) {
		User user = (User) session.getAttribute(CmsConstant.UserSessionKey);
		complain.setUser_id(user.getId());
		complain.setArticle_id(article_id);
		//查询文章
		Article article = articleService.getById(complain.getArticle_id());
		if(article.getUserId().equals(complain.getUser_id())) {
			return JsonResult.fail(1001, "不能投诉自己的文章");
		}
		//判断投诉地址是否为空
		if(complain.getUrlip()!=null || complain.getUrlip()!="") {
			boolean boo = StringUtil.isHttpUrl(complain.getUrlip());
			if(boo) {
				complainService.add(complain);
				return JsonResult.sucess();
			}else {
				return JsonResult.fail(1002, "URL路径不正确");
			}
		}
		complainService.add(complain);
		return JsonResult.sucess();
	}
	
	@RequestMapping("list")
	public String complainList(Model m,Complain complain,
									   @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
									   @RequestParam(defaultValue = "4")Integer pageSize) {
		complain.setPaix(1);
		PageInfo<Complain> pageInfo = complainService.complainList(pageNum, pageSize, complain);
		
		m.addAttribute("pageInfo", pageInfo);
		
		return "complain/list";
	}
	
	@RequestMapping("stop")
	public @ResponseBody JsonResult stopShow(Article article) {
		int status=-1;
		
		articleService.updateStatus(article.getId(), status);
		
		return JsonResult.sucess();
	}
	
	@RequestMapping("xiangQing")
	public String xiangQing(Integer id,Model model) {
		Complain complain = complainService.xiangQing(id);
		
		model.addAttribute("complain", complain);
		
		return "complain/xiangQing";
	}
	
}
