package com.tz.pagehelper;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tz.mapper.TbItemMapper;
import com.tz.pojo.TbItem;
import com.tz.pojo.TbItemExample;

public class TestPageHelper {

	@Test
	public void test() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		TbItemMapper itemMapper = context.getBean(TbItemMapper.class);
		PageHelper.startPage(1, 30);
		TbItemExample example = new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(example);
		PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
		long total = pageInfo.getTotal();
		int pages = pageInfo.getPages();
		int pageSize = pageInfo.getPageSize();
		System.out.println(total + "   " + pages + "   " + pageSize);
		
	}
}
