package file.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.vo.BoardVo;

public class UploadFileUtils {
	private String uploadPath = "C:\\uploadfiletest\\upload\\" ;
	private File uploadDir;
	
	public UploadFileUtils() {
		uploadDir = new File(uploadPath);
		if(!uploadDir.exists()) {
			uploadDir.mkdir();
		}
	}
	
	public List<Map<String, Object>> fileInfo(BoardVo vo, MultipartHttpServletRequest mpReq) throws Exception{
		Iterator<String> it = mpReq.getFileNames();
		
		MultipartFile multipartFile = null;
		String fileName = null;
		String extName = null;
		String saveName = null;
		long fileSize = 0;
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = null;
		
		while(it.hasNext()) {
			multipartFile = mpReq.getFile(it.next());
			if(multipartFile.isEmpty()) {
				uploadPath = calcPath(uploadPath);
				fileName = multipartFile.getOriginalFilename();
				extName = fileName.substring(fileName.lastIndexOf("."));
				UUID uuid = UUID.randomUUID();
				saveName = uuid.toString()+"_"+extName;
				fileSize = multipartFile.getSize();
				
				uploadDir = new File(uploadPath);
				if(!uploadDir.exists()) {
					uploadDir.mkdir();
				}
				multipartFile.transferTo(uploadDir);
				map.put("boardVo", vo);
				map.put("fileName", fileName);
				map.put("saveName", saveName);
				map.put("fileSize", fileSize);
				list.add(map);
			}
		}
		return list;
	}

	private static String calcPath(String uploadPath) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date today = new Date();
		String datePath = sdf.format(today);
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
