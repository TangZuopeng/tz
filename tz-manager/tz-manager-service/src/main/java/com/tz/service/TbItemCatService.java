package com.tz.service;

import java.util.List;

import com.tz.pojo.EasyUIDataTreeResult;

public interface TbItemCatService {
	
	public List<EasyUIDataTreeResult> getTbItemCatList(long parentId);
}
