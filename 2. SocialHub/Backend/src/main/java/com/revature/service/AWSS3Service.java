package com.revature.service;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.revature.model.Account;
import com.revature.model.Photo;
import com.revature.repository.PhotoRepository;

@Service
public class AWSS3Service implements FileService{
	@Autowired
	private AmazonS3Client awsS3Client;
	
	
	@Override
	public String uploadFile(MultipartFile file) {
		
		String filenameExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
		
		String key = UUID.randomUUID().toString() + "." +filenameExtension;
		
		ObjectMetadata metaData = new ObjectMetadata();
		metaData.setContentLength(file.getSize());
		metaData.setContentType(file.getContentType());
		
		try {
			awsS3Client.putObject("muhammad-socialhub", key, file.getInputStream(), metaData);
		} catch (IOException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An exception occured while uploading the file");
		}
		
		awsS3Client.setObjectAcl("muhammad-socialhub", key, CannedAccessControlList.PublicRead);
		String publicUrl = awsS3Client.getResourceUrl("muhammad-socialhub", key);
		
		return publicUrl;
	}
}