package com.wenqi.cms.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageInfo;
import com.wenqi.cms.pojo.Article;
import com.wenqi.cms.pojo.Category;
import com.wenqi.cms.pojo.Channel;
import com.wenqi.cms.pojo.Slide;
import com.wenqi.cms.pojo.User;
import com.wenqi.cms.service.ArticleService;
import com.wenqi.cms.service.SlideService;
import com.wenqi.cms.service.UserService;

@Controller
public class IndexController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private UserService userService;
	@Autowired
	private SlideService slideService;
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private ThreadPoolTaskExecutor executor;

	
	@RequestMapping(value="/")
	public String index(Model model) {
		return index(1, model);
	}
	
	@RequestMapping(value="/hot/{pageNum}.html")
	public String index(@PathVariable Integer pageNum, Model model) {
		/** 频道 */
		List<Channel> channelList = articleService.getChannelList();
		model.addAttribute("channelList", channelList);
		/** 轮播图 */
		List<Slide> slideList = slideService.getAll();
		model.addAttribute("slideList", slideList);
		/** 最新文章 **/
		List<Article> newArticleList = articleService.getNewList(6);
		model.addAttribute("newArticleList", newArticleList);
		/** 热点文章 **/
		if(pageNum==null) {
			pageNum=1;
		}
		PageInfo<Article> pageInfo =  articleService.getHotList(pageNum);
		model.addAttribute("pageInfo", pageInfo);
		return "index";
	}
	
	
	@RequestMapping("/{channelId}/{cateId}/{pageNo}.html")
	public String channel(@PathVariable Integer channelId, Model model,
			@PathVariable Integer cateId,@PathVariable Integer pageNo) {
		/** 频道 */
		List<Channel> channelList = articleService.getChannelList();
		model.addAttribute("channelList", channelList);
		/** 最新文章 **/
		List<Article> newArticleList = articleService.getNewList(6);
		model.addAttribute("newArticleList", newArticleList);
		/** 分类 */
		List<Category> cateList = articleService.getCateListByChannelId(channelId);
		model.addAttribute("cateList", cateList);
		PageInfo<Article> pageInfo =  articleService.getListByChannelIdAndCateId(channelId,cateId,pageNo);
		model.addAttribute("pageInfo", pageInfo);
		return "index";
	}
	
	@RequestMapping("article/{id}.html")
	public String articleDetail(@PathVariable Integer id,Model model,HttpServletRequest request) {
		//kafka生产者发送文章id到消费者
//		kafkaTemplate.sendDefault("user_view", id.toString());
		
		//获取用户IP
		String user_ip= request.getRemoteAddr();
		//准备redisKey
		String key="Hits_"+id+"_"+user_ip;
		//查询redis中是否有这个key
		String redisKey = (String) redisTemplate.opsForValue().get(key);
		if(redisKey==null) {
			executor.execute(new Runnable() {
				
				@Override
				public void run() {
					//线程池创建出来的线程
					//根据文章ID查询文章
					Article article = articleService.getById(id);
					//点击量+1
					article.setHits(article.getHits()+1);
					//保存到数据库
					articleService.save(article);
					//在保存到redis，并设置市场5分钟
					redisTemplate.opsForValue().set(key, "", 5, TimeUnit.MINUTES);
					System.err.println(key+"保存到redis成功!");
				}
			});
		}
		
		
		/** 查询文章 **/
		Article article = articleService.getById(id);
		if(article.getStatus()==3) {
			return "article/jin";
		}
		model.addAttribute("article", article);
		/** 查询用户 **/
		User user = userService.getById(article.getUserId());
		model.addAttribute("user", user);
		/** 查询相关文章 **/
		List<Article> articleList = articleService.getListByChannelId(article.getChannelId(),id,10);
		model.addAttribute("articleList", articleList);
		return "article/detail";
	}
	
}
