package com.tz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.tz.pojo.PictrueResult;
import com.tz.service.PictrueService;

@Controller
public class PictrueController {
	
	@Autowired
	PictrueService pictrueService;

	@RequestMapping("/pic/upload")
	@ResponseBody
	public PictrueResult uploadFile(MultipartFile uploadFile){
		return pictrueService.uploadPic(uploadFile);
	}
	
}
