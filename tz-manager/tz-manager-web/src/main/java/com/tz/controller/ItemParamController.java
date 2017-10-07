package com.tz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tz.pojo.EasyUIDataGridResult;
import com.tz.pojo.TzResult;
import com.tz.service.TbItemParamService;

@Controller
@RequestMapping("/item/param")
public class ItemParamController {
	
	@Autowired
	TbItemParamService tbItemParamService;

	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult getItemParamList(Integer page, Integer rows) {
		return tbItemParamService.getTbItemParamList(page, rows);
	}
	
	@RequestMapping("/query/itemcatid/{cid}")
	@ResponseBody
	public TzResult getItemParamListByCid(@PathVariable long cid){
		return tbItemParamService.getTbItemParamByCid(cid);
	}
	
	
	@RequestMapping("/save/{cid}")
	@ResponseBody
	public TzResult createItemParam(@PathVariable long cid, String paramData){
		return tbItemParamService.createTbItemParam(cid, paramData);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public TzResult deleteItemParam(String ids){
		return tbItemParamService.deleteTbItemParam(ids);
	}
}
