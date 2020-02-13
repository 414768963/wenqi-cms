/** 
 * @Title: TestImportMysql2Es.java 
 * @Package com.wenqi.test 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 文琪 
 * @date 2020年2月12日 
 * @version V1.0 
 */ 

package com.wenqi.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wenqi.cms.dao.ArticleDao;
import com.wenqi.cms.dao.ArticleRepostory;
import com.wenqi.cms.pojo.Article;

/** 
 * @Title: TestImportMysql2Es.java 
 * @Package com.wenqi.test 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 文琪 
 * @date 2020年2月12日 
 * @version V1.0 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-beans.xml")
public class TestImportMysql2Es {

	@Autowired
	private ArticleDao articleDao;
	
	@Autowired
	private ArticleRepostory articleRepostory;
	
	@Test
	public void test1() {
		Article article = new Article();
		article.setStatus(1);
		List<Article> list = articleDao.select(article);
		articleRepostory.saveAll(list);
		System.err.println("插入到elasticsearch完毕");
	}
}
