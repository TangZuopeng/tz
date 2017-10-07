package com.tz.service;

import com.tz.pojo.EasyUIDataGridResult;
import com.tz.pojo.TzResult;

public interface TbItemParamService {

	public EasyUIDataGridResult getTbItemParamList(int page, int rows);
	
	public TzResult getTbItemParamByCid(long cid);
	
	public TzResult createTbItemParam(long cid, String paramData);
	
	public TzResult deleteTbItemParam(String ids);
}
