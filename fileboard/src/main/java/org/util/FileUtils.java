package org.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.vo.AttachVo;
import org.vo.BoardVo;

@Component("fileUtils")
public class FileUtils {
	private String uploadPath = "C:\\uploadfiletest\\upload\\" ;
	private File uploadDir;
	
	public FileUtils() {
		uploadDir = new File(uploadPath);
		if(!uploadDir.exists()) {
			uploadDir.mkdir();
		}
	}
	
	public List<AttachVo> fileInfo(BoardVo vo, MultipartHttpServletRequest mpReq) throws Exception{
		Iterator<String> it = mpReq.getFileNames();
		
		MultipartFile multipartFile = null;
		String fileName = null;
		String extName = null;
		String saveName = null;
		long fileSize = 0;
		
		List<AttachVo> list = new ArrayList<AttachVo>();
		AttachVo attach = new AttachVo();
		
		while(it.hasNext()) {
			multipartFile = mpReq.getFile(it.next());
			if(!multipartFile.isEmpty()) {
				uploadPath += calcPath(uploadPath);
				fileName = multipartFile.getOriginalFilename();
				UUID uuid = UUID.randomUUID();
				saveName = uuid.toString()+"_"+fileName;
				fileSize = multipartFile.getSize();
				File file = new File(uploadPath+saveName);
				multipartFile.transferTo(file);				
				if(vo.getRef()!=0) {
					attach.setbNum(vo.getRef());
				}
				attach.setFileName(fileName);
				attach.setSaveName(saveName);
				attach.setFileSize(fileSize);
				list.add(attach);
			}
		}
		return list;
	}

	private static String calcPath(String uploadPath) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date today = new Date();
		String datePath = sdf.format(today)+"\\";
		mkDir(uploadPath, datePath);
		return datePath;
	}

	private static void mkDir(String uploadPath, String datePath) {
		if(new File(uploadPath+datePath).exists()) {
			return;
		}
		File dirPath = new File(uploadPath+datePath);
		if(!dirPath.exists()) {
			dirPath.mkdir();
		}
	}
}
