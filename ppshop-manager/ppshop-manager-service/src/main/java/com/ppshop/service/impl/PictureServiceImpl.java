package com.ppshop.service.impl;


import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ppshop.common.utils.FtpUtil;
import com.ppshop.common.utils.IDUtils;
import com.ppshop.service.PictureService;

/**
 * 上传文件service
 * <pre>
 * 程序的中文名称。
 * </pre>
 * @author pangkaiguang
 * @version 1.00.00
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容: 
 * </pre>
 */
@Service
public class PictureServiceImpl implements PictureService{
	
	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRESS;
	@Value("${FTP_PORT}")
	private Integer FTP_PORT;
	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;
	@Value("${FTP_BASEPATH}")
	private String FTP_BASEPATH;
	@Value("${IMAGE_BASE_URL}")
	private String IMAGE_BASE_URL;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map uploadPicture(MultipartFile uploadFile) {
		Map resultMap = new HashMap<>();
		try {
			//生产一个新文件名
			//取原始文件名
			String oldName = uploadFile.getOriginalFilename();
			//生成新文件名
			String newName = IDUtils.genImageName();
			newName = newName + oldName.substring(oldName.lastIndexOf("."));
			String imagePath = new DateTime().toString("/yyyy/MM/dd");
			boolean result = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASEPATH, imagePath, 
					newName, uploadFile.getInputStream());
			//返回结果
			if (!result){
				resultMap.put("error", 1);
				resultMap.put("message", "文件上次失败");
				return resultMap;
			}
			resultMap.put("error", 0);
			resultMap.put("url", IMAGE_BASE_URL+ imagePath + "/" +newName);
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("error", 1);
			resultMap.put("message", "文件上次异常："+e.getMessage());
			return resultMap;
		}
	}

}
