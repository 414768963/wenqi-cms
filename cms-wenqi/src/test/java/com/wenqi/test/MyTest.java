/** 
 * @Title: MyTest.java 
 * @Package com.wenqi.test 
 * @Description: TODO(��һ�仰�������ļ���ʲô) 
 * @author ���� 
 * @date 2019��12��25�� 
 * @version V1.0 
 */ 

package com.wenqi.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bawei.wenqi.utils.DateUtil;
import com.wenqi.cms.pojo.Complain;
import com.wenqi.cms.service.ComplainService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class MyTest {

	@Autowired
	private ComplainService complainService;
	
	@Test
	public void addTest() {
		Complain complain = new Complain();
		
		String time = DateUtil.dateTimeFormat.format(new Date());
		complain.setCreatTime(time);
		complain.setId(null);
		complain.setArticle_id(1);
		complain.setUser_id(1);
		complain.setComplaintype("F");
		complain.setUrlip("https://www.baidu.com");
		complainService.add(complain);
	}
}
