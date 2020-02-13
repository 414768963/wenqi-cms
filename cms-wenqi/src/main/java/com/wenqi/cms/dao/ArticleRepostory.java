/** 
 * @Title: ArticleRepostory.java 
 * @Package com.wenqi.cms.dao 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 文琪 
 * @date 2020年2月12日 
 * @version V1.0 
 */ 

package com.wenqi.cms.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.wenqi.cms.pojo.Article;

/** 
 * @Title: ArticleRepostory.java 
 * @Package com.wenqi.cms.dao 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 文琪 
 * @date 2020年2月12日 
 * @version V1.0 
 */

public interface ArticleRepostory extends ElasticsearchRepository<Article, Integer>{

	
}
