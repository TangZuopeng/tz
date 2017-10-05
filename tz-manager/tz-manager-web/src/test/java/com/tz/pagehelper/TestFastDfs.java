package com.tz.pagehelper;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

public class TestFastDfs {
	@Test
	public void testUpload() throws Exception {
		ClientGlobal.init("E:\\git_rep\\tz-manager\\tz-manager-web\\src\\main\\resources\\properties\\client.conf");
		TrackerClient trackerClient = new TrackerClient();
		TrackerServer trackerServer = trackerClient.getConnection();
		StorageServer storageServer = null;
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		String[] strings = storageClient.upload_file("D:\\aa.jpg", "jpg", null);
		for (String string : strings) {
			System.out.println(string);
		}
	}
}
