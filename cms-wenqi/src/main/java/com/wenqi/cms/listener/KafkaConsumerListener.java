/** 
 * @Title: KafkaConsumerListener.java 
 * @Package com.wenqi.cms.listener 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 文琪 
 * @date 2020年2月13日 
 * @version V1.0 
 */ 

package com.wenqi.cms.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.wenqi.cms.dao.ArticleRepostory;
import com.wenqi.cms.pojo.Article;
import com.wenqi.cms.service.ArticleService;

/** 
 * @Title: KafkaConsumerListener.java 
 * @Package com.wenqi.cms.listener 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 文琪 
 * @date 2020年2月13日 
 * @version V1.0 
 */
@Component
public class KafkaConsumerListener implements MessageListener<String, String>{

	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private RedisTemplate<String, Article> redisTemplate;
	
	@Override
	public void onMessage(ConsumerRecord<String, String> data) {
//		(7)	编写Kafka消费者，将接到的数据保存到CMS项目数据库。（6分）
		String key = data.key();
		if(key!=null && key.equals("article_add")) {
			String value = data.value();
			Article article = JSON.parseObject(value, Article.class);
			//存数据库
			articleService.kafkaSave(article);
		}
		if(key!=null && key.equals("user_view")) {
			String id = data.value();
			//根据文章Id查出文章对象
			Article article = articleService.getById(Integer.parseInt(id));
			//浏览量+1
			article.setHits(article.getHits()+1);
			//保存到数据库
			articleService.save(article);
			System.err.println("浏览量+1成功!");
		}
		if(key!=null && key.equals("redis_article_add")) {
			String redisKey = data.value();
			//取出redis中的文章
			Article article = redisTemplate.opsForValue().get(redisKey);
			//保存到数据库
			articleService.kafkaSave(article);
			redisTemplate.delete(redisKey);
			System.err.println(article.getTitle()+"已导入完毕");
		}
			
	}

}
