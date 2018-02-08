package com.ppshop.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface PictureService {
	/**
	 * 上传图片
	 * @param uploadFile
	 * @return
	 */
	Map uploadPicture(MultipartFile uploadFile);
}
