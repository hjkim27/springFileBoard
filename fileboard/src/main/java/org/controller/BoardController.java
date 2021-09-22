package org.controller;

import java.io.File;
import java.net.URLEncoder;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.service.BoardService;
import org.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.vo.BoardVo;
import org.vo.ReplyVo;

import common.SearchCommand;
import common.UpdateCommand;
import common.DeleteCommand;
import common.PageCommand;
import common.RegistCommand;
import common.RegistRefCommand;

@Controller // 컨트롤러 : 프로젝트 레이어, 웹 요청과 응답 처리
public class BoardController {
	@Autowired
	BoardService boardService;
	@Autowired
	ReplyService replyService;

	@RequestMapping(value = "/writeForm.board", method = RequestMethod.GET)
	public String WriteView(Integer num, Model model) throws Exception {
		if (num != null && num != 0) {
			model.addAttribute("num", num);
		}
		return "writeForm";
	}

	@RequestMapping(value = "/write.board", method = RequestMethod.POST)
	public String write(RegistCommand cmd, MultipartHttpServletRequest mpReq) throws Exception {
		BoardVo vo = new BoardVo();
		vo.setWriter(cmd.getWriter());
		vo.setTitle(cmd.getTitle());
		vo.setContent(cmd.getContent());
		vo.setPassword(cmd.getPassword());
		if (cmd.isAnswer()) {
			vo.setRef(cmd.getNum());
			vo.setDepth(boardService.read(cmd.getNum()).getDepth() + 1);
		} else {
			vo.setDepth(0);
		}
		vo.setRegdate(new Timestamp(System.currentTimeMillis()));
		boardService.insert(vo, mpReq);
		return "redirect:/list.board";
	}

	@RequestMapping(value = "/writeRef.board", method = RequestMethod.POST)
	public String write(RegistRefCommand cmd) throws Exception{
		ReplyVo vo = new ReplyVo();
		vo.setbNum(cmd.getbNum());
		vo.setWriter(cmd.getWriter());
		vo.setContent(cmd.getContent());
		vo.setRef(cmd.getRef());
		if(cmd.isNewReply()) {
			vo.setDepth(0);
		} else {
			vo.setDepth(cmd.getDepth()+1);
		}
		replyService.insert(vo);
		return "redirect:/detail.board?num="+cmd.getbNum();
	}
	
	@RequestMapping("/list.board")
	public String listView(SearchCommand search, Model model) throws Exception {
		Integer pageNum = search.getPageNum();
		if (pageNum == null) {
			pageNum = 1;
		}
		int pageSize = 10;
		int currentPage = pageNum;
		int start = (currentPage - 1) * pageSize + 1;
		int end = currentPage * pageSize;
		String type = null;
		String str = null;
		Integer count = null;
		List<BoardVo> result = null;

		if (search.getStr() != null) {
			type = search.getType();
			str = search.getStr();
			count = boardService.searchSize(type, str);
			if (count > 0) {
				result = boardService.search(type, str, start, end);
				model.addAttribute("op", search);
			} else {
				result = Collections.emptyList();
			}
		} else {
			count = boardService.listSize();
			if (count > 0) {
				result = boardService.list(start, end);
			} else {
				result = Collections.emptyList();
			} 
		}
		model.addAttribute("list", result);

		int number = count - (currentPage - 1) * pageSize;
		model.addAttribute("number", number);

		PageCommand pageCmd = new PageCommand(currentPage, start, end, count, pageSize, number);
		model.addAttribute("cmd", pageCmd);

		List<Map<Object, Object>> answerCount = replyService.answerCount();
		model.addAttribute("answerCount", answerCount);

		List<Map<Object, Object>> fileCount = boardService.fileCount();
		model.addAttribute("fileCount", fileCount);

		return "list";
	}

	@RequestMapping(value = "/detail.board")
	public String read(BoardVo vo, Model model) throws Exception {
		int num = vo.getNum();
		model.addAttribute("detail", boardService.read(num));
		model.addAttribute("answer", replyService.answerAll(num));
		model.addAttribute("files", boardService.fileList(num));
		boardService.readCount(num);
		return "detail";
	}

	@RequestMapping(value = "/download")
	public void fileDownload(int num, HttpServletResponse resp) throws Exception {
		Map<String, Object> result = boardService.downFile(num);

		String saveName = (String) result.get("SAVENAME");
		String fileName = (String) result.get("FILENAME");

		String regdate = (String) result.get("REGDATE") +"\\";
		byte[] fileByte = FileUtils.readFileToByteArray(new File("C:\\uploadfiletest\\upload\\" + regdate + saveName));
		
		resp.setContentType("application/octet-stream");
		resp.setContentLength(fileByte.length);
		resp.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(fileName, "utf-8") + "\";");
		resp.getOutputStream().write(fileByte);
		resp.getOutputStream().flush();
		resp.getOutputStream().close();
	}
	
	@RequestMapping(value = "/updateForm.board")
	public String updateView(BoardVo vo, Model model) throws Exception {
		model.addAttribute("update", boardService.read(vo.getNum()));
		model.addAttribute("files", boardService.fileList(vo.getNum()));
		return "updateForm";
	}

	@RequestMapping(value = "/update.board", method = RequestMethod.POST)
	public String update(UpdateCommand cmd,
						MultipartHttpServletRequest mpReq) throws Exception {
		BoardVo vo = new BoardVo();
		vo.setNum(cmd.getNum());
		vo.setTitle(cmd.getTitle());
		vo.setContent(cmd.getContent());
		vo.setModdate(new Timestamp(System.currentTimeMillis()));
		boardService.edit(vo, mpReq);
		if(cmd.getDeleteNum()!=null && cmd.getDeleteNum().length!=0) {
			for(int i:cmd.getDeleteNum()) {
				boardService.delete("num", i);
			}
		}
		return "redirect:/detail.board?num=" + cmd.getNum();
	}

	@RequestMapping(value = "/deleteForm.board")
	public String deleteView(Integer num, Model model) throws Exception {
		model.addAttribute("delete", boardService.read(num));
		return "deleteForm";
	}

	@RequestMapping(value = "/delete.board", method = RequestMethod.POST)
	public String delete(DeleteCommand cmd) throws Exception {
		if (cmd.checkPassword()) {
			boardService.delete("bNum", cmd.getNum());
		}
		return "redirect:/list.board";
	}
}
