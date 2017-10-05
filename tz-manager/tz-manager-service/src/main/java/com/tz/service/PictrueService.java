package com.tz.service;

import org.springframework.web.multipart.MultipartFile;

import com.tz.pojo.PictrueResult;

public interface PictrueService {

	public PictrueResult uploadPic(MultipartFile file);
}
