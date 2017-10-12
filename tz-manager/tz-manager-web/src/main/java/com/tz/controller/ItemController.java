package com.tz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
		return tbItemService.getTbItemList(page, rows);
	}
	
	@RequestMapping(value = "/item/save", method = RequestMethod.POST)
	@ResponseBody
	public TzResult createItem(TbItem item, String desc, String itemParams){
		return tbItemService.createItem(item, desc, itemParams);
	}
	
	@RequestMapping("/show/itemParam/{itemId}")
	public ModelAndView showItemParam(@PathVariable long itemId){
		ModelAndView modelAndView = new ModelAndView("item-param");
		modelAndView.addObject("myHtml", tbItemService.getItemParamItemByid(itemId));
		return modelAndView;
	}

}
