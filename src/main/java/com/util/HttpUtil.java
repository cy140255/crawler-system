package com.util;


import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;

public class HttpUtil {
	private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);



	public static String encodeDownloadFileName(HttpServletRequest req,
			String fileName) {
		String encodeFileName = "file." + FilenameUtils.getExtension(fileName);
		try {
			if (req.getHeader("user-agent").toLowerCase().contains("firefox")) {
				encodeFileName = new String(fileName.getBytes(), "iso8859-1");
			} else {
				encodeFileName = URLEncoder.encode(fileName, "UTF-8");
			}
		} catch (Exception e) {
			return encodeFileName;
		}
		return encodeFileName;
	}
}
