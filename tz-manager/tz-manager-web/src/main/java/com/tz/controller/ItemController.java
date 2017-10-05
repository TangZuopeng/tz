package com.tz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tz.pojo.EasyUIDataGridResult;
import com.tz.pojo.TbItem;
import com.tz.pojo.TzResult;
import com.tz.service.TbItemService;

@Controller
public class ItemController {
	
	@Autowired
	TbItemService tbItemService;
	
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIDataGridResult getTbItemList(Integer page, Integer rows){
//		System.out.println(page + " " + rows);
		EasyUIDataGridResult result = tbItemService.getTbItemList(page, rows);
		return result;
	}
	
	@RequestMapping(value = "/item/save", method = RequestMethod.POST)
	@ResponseBody
	public TzResult createItem(TbItem item, String desc){
		return tbItemService.createItem(item, desc);
	}

}
