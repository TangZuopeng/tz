package com.tz.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tz.mapper.TbItemDescMapper;
import com.tz.mapper.TbItemMapper;
import com.tz.pojo.EasyUIDataGridResult;
import com.tz.pojo.TbItem;
import com.tz.pojo.TbItemDesc;
import com.tz.pojo.TbItemExample;
import com.tz.pojo.TzResult;
import com.tz.service.TbItemService;
import com.tz.util.IDUtils;

@Service
public class TbItemServiceImpl implements TbItemService {

	@Autowired
	TbItemMapper tbItemMapper;
	
	@Autowired
	TbItemDescMapper tbItemDescMapper;
	
	@Override
	public EasyUIDataGridResult getTbItemList(int page, int rows) {
		PageHelper.startPage(page, rows);
		TbItemExample example = new TbItemExample();
		List<TbItem> list = tbItemMapper.selectByExample(example);
		PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		System.out.println("结果：" + result.getTotal() + "  " + list.size());
		return result;
	}

	@Override
	public TzResult createItem(TbItem item, String desc) {
		/*
		 * 由于在配置文件applicationContext-trans.xml中已经配置了事务，故此没有体现事务操作
		 * 但是若没有配置增删改操作需加上事务，以防止出现异常，而发生数据异常
		*/
		long itemId = IDUtils.genItemId();
		Date nowTime = new Date();
		item.setId(itemId);
		item.setCreated(nowTime);
		item.setUpdated(nowTime);
		//商品状态，1-正常，2-下架，3-删除
		item.setStatus((byte) 1);
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(nowTime);
		itemDesc.setUpdated(nowTime);
		itemDesc.setItemId(itemId);
		tbItemMapper.insert(item);
		tbItemDescMapper.insert(itemDesc);
		return TzResult.ok();
	}

}
