package org.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.vo.BoardVo;

@Component("fileUtils")
public class FileUtils {
	private String uploadPath = "C:\\uploadfiletest\\upload\\";
	private File uploadDir;

	public FileUtils() {
		uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
	}

	// 파일 추가(저장)
	public List<Map<String, Object>> fileInfo(BoardVo vo, MultipartHttpServletRequest mpReq) throws Exception {
		System.out.println(uploadPath);
		List<MultipartFile> files = mpReq.getFiles("files");
		
		MultipartFile multipartFile = null;
		String fileName = null;
		String extName = null;
		String saveName = null;
		long fileSize = 0;
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> hs = null;
		
		for (MultipartFile partFile : files) {
			hs = new HashMap<String, Object>();
			uploadPath += calcPath(uploadPath);
			fileName = partFile.getOriginalFilename();
			UUID uuid = UUID.randomUUID();
			saveName = uuid.toString()+"_"+fileName;
			fileSize = partFile.getSize();
			File file = new File(uploadPath+saveName);
			partFile.transferTo(file);
			
			if(vo.getNum()!=0) {
				hs.put("bNum", vo.getNum());
			}
			hs.put("fileName", fileName);
			hs.put("saveName", saveName);
			hs.put("fileSize", fileSize);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			hs.put("regdate", sdf.format(new Date()).toString());
			list.add(hs);
		}

		return list;
	}

	private static String calcPath(String uploadPath) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date today = new Date();
		String datePath = sdf.format(today) + "\\";
		
		int len = uploadPath.length();
		String lastPath = uploadPath.substring(len-9,len);
		if(lastPath.equals(datePath)) {
			return "";
		} else {
			mkDir(uploadPath, datePath);
			return datePath;
		}
	}

	private static void mkDir(String uploadPath, String datePath) {
		if (new File(uploadPath + datePath).exists()) {
			return;
		}
		File dirPath = new File(uploadPath + datePath);
		if (!dirPath.exists()) {
			dirPath.mkdir();
		}
	}
}
