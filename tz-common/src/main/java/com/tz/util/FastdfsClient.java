package com.tz.util;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

public class FastdfsClient {

	private TrackerClient trackerClient = null;
	private TrackerServer trackerServer = null;
	private StorageServer storageServer = null;
	private StorageClient storageClient = null;
	
	public FastdfsClient(String path) throws Exception{
		if(path.contains("classpath:")){
			path = path.replace("classpath:", this.getClass().getResource("/").getPath());
		}
		ClientGlobal.init(path);
		trackerClient = new TrackerClient();
		trackerServer = trackerClient.getConnection();
		storageClient = new StorageClient(trackerServer, storageServer);
	}
	
	public String upload(byte[] fileName, String extName, NameValuePair[] metas) throws Exception{
		String[] result = storageClient.upload_file(fileName, extName, metas);
		StringBuffer url = new StringBuffer();
		url.append(result[0]+"/");
		for(int i=1;i<result.length;i++){
			url.append(result[i]);
		}
		return url.toString();
	}
	
	public String upload(byte[] file, String extName) throws Exception{
		String result = upload(file, extName, null);
		return result;
	}
	
	public String upload(byte[] file) throws Exception{
		String result = upload(file, null, null);
		return result;
	}
	
}
