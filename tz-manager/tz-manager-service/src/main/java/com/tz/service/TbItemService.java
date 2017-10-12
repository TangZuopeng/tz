package com.tz.service;

import com.tz.pojo.EasyUIDataGridResult;
import com.tz.pojo.TbItem;
import com.tz.pojo.TzResult;

public interface TbItemService {

	public EasyUIDataGridResult getTbItemList(int page, int rows);
	
	public TzResult createItem(TbItem item, String desc, String itemParams);
	
	public String getItemParamItemByid(long itemId);
}
