/** 
 * @Title: TestImportToMysqlByKafka.java 
 * @Package com.wenqi.test.kafka 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 文琪 
 * @date 2020年2月13日 
 * @version V1.0 
 */ 

package com.wenqi.test.kafka;

import java.io.File;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.bawei.wenqi.utils.RandomUtil;
import com.bawei.wenqi.utils.StreamUtil;
import com.wenqi.cms.pojo.Article;

/** 
 * @Title: TestImportToMysqlByKafka.java 
 * @Package com.wenqi.test.kafka 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 文琪 
 * @date 2020年2月13日 
 * @version V1.0 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-beans.xml")
public class TestImportToMysqlByKafka {

	@Resource
	private KafkaTemplate<String, String> kafkaTemplate;
	@Autowired
	private RedisTemplate<String, Article> redisTemplate;
	
	@Test
	public void test1() {
		File file = new File("D:\\1709DJsoup");
		File[] listFiles = file.listFiles();
		for (File f2 : listFiles) {
			Article article = new Article();
//		(1)	将文件名作为Article对象的title属性值。文本内容作为Article对象的content属性值。（2分）
			article.setTitle(f2.getName().replace(".txt", ""));
			String textFile = StreamUtil.readTextFile(f2);
			article.setContent(textFile);
//		(2)	在文本内容中截取前140个字作为摘要。（2分）
			if(textFile.length()>140) {
				article.setAbs(textFile.substring(0, 140));
			}else {
				article.setAbs(textFile);
			}
//		(3)	“点击量”和“是否热门”、“频道”字段要使用随机值。（2分）
			int r1 = RandomUtil.random(0, 100);
			article.setHits(r1);
			int r2 = RandomUtil.random(0, 100);
			article.setHot(r2);
			int c1 = RandomUtil.random(1, 9);
			article.setChannelId(c1);
//		(4)	文章发布日期从2019年1月1日模拟到今天。（2分）
			article.setCreated(new Date());
//		(5)	其它的字段随便模拟。
			article.setStatus(0);
			article.setDeleted(0);
			article.setCommentcnt(0);
			article.setTousuCnt(0);
			article.setStatus(1);
			article.setUserId(180);
			article.setCreated(new Date());
			article.setUpdated(new Date());
			article.setId(null);
//		(6)	编写Kafka生产者，然后将生成Article对象通过Kafka发送到消费端。（6分）
//			String jsonString = JSON.toJSONString(article);
//			kafkaTemplate.sendDefault("article_add", jsonString);
			//把文章存到redis
			String reidsKey = article.getTitle();
			redisTemplate.opsForValue().set(reidsKey, article);
			//往kafka发送redis的ID
			kafkaTemplate.sendDefault("redis_article_add", reidsKey);
		}

	}
	

	
}
