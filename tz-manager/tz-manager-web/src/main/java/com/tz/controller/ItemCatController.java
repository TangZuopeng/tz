package com.tz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tz.pojo.EasyUIDataTreeResult;
import com.tz.service.TbItemCatService;

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
	
	@Autowired
	TbItemCatService tbItemCatService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<EasyUIDataTreeResult> getTbItemCatList(@RequestParam(value="id", defaultValue="0")Long parentId){
		return tbItemCatService.getTbItemCatList(parentId);
	}

}
