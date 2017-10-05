package com.tz.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tz.pojo.PictrueResult;
import com.tz.service.PictrueService;
import com.tz.util.FastdfsClient;

@Service
public class PictrueServiceImpl implements PictrueService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PictrueService.class);
	
	@Value("${PICTRUE_SERVER_URL}")
	String serverUrl;

	@Override
	public PictrueResult uploadPic(MultipartFile file) {
		
		String fileName = file.getOriginalFilename();
		String extName = fileName.substring(fileName.lastIndexOf(".") + 1);
		FastdfsClient client;
		PictrueResult result = new PictrueResult();
		try {
			client = new FastdfsClient("classpath:properties/client.conf");
			String url = client.upload(file.getBytes(), extName);
			result.setError(0);
			result.setUrl(serverUrl + url);
		} catch (Exception e) {
			LOGGER.error("图片上传发生异常", e);
			result.setError(1);
			result.setMessage("图片上传失败！");
		}
		return result;
	}

}
