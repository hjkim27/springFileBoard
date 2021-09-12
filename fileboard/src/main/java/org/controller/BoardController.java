package org.controller;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.vo.BoardVo;

import common.SearchCommand;
import common.UpdateCommand;
import common.DeleteCommand;
import common.PageCommand;
import common.RegistCommand;

@Controller // 컨트롤러 : 프로젝트 레이어, 웹 요청과 응답 처리
public class BoardController {
	@Autowired
	BoardService boardService;

	@RequestMapping(value = "/writeForm.board", method = RequestMethod.GET)
	public String WriteView(Integer num, Model model) throws Exception {
		if(num!=null && num!=0) {
			model.addAttribute("num", num);
			System.out.println(num);
		}
		return "writeForm";
	}

	@RequestMapping(value = "/write.board", method = RequestMethod.POST)
	public String write(RegistCommand cmd) throws Exception {
		System.out.println(cmd);
		BoardVo vo = new BoardVo();
		vo.setWriter(cmd.getWriter());
		vo.setTitle(cmd.getTitle());
		vo.setContent(cmd.getContent());
		vo.setPassword(cmd.getPassword());
		if(cmd.isAnswer()) {
			vo.setRef(cmd.getNum());
			vo.setDepth(boardService.read(cmd.getNum()).getDepth()+1);
		}else {
			vo.setDepth(0);
		}
		vo.setRegdate(new Timestamp(System.currentTimeMillis()));
		System.out.println(vo);
		boardService.insert(vo);
		return "redirect:/list.board";
	}

	@RequestMapping(value = "/list.board")
	public String listView(Integer pageNum, Model model) throws Exception {
		if (pageNum == null) {
			pageNum = 1;
		}
		int pageSize = 10;
		int currentPage = pageNum;
		int start = (currentPage - 1) * pageSize + 1;
		int end = currentPage * pageSize;
		Integer count = boardService.listSize();
		int number = 0;

		List<BoardVo> result = null;
		if (count > 0) {
			result = boardService.list(start, end);
		} else {
			result = Collections.emptyList();
		}

		number = count - (currentPage - 1) * pageSize;
		Map<Object, Object> reCount = new HashMap<Object, Object>();
		
		PageCommand cmd = new PageCommand(currentPage, start, end, count, pageSize, number);
		
		model.addAttribute("list", result);
		model.addAttribute("cmd", cmd);
		model.addAttribute("number", number);
		return "list";
	}
	
	@RequestMapping("/search.board")
	public String searchView(SearchCommand search, Model model) throws Exception{
		Integer pageNum = search.getPageNum();
		if(pageNum==null) {
			pageNum = 1;
		}
		int pageSize = 10;
		int currentPage = pageNum;
		int start = (currentPage - 1) * pageSize + 1;
		int end = currentPage * pageSize;
		String type = search.getType();
		String str = search.getStr();
		Integer count = boardService.searchSize(type, str);
		int number = 0;
		
		List<BoardVo> result = null;
		if (count > 0) {
			result = boardService.search(type, str, start, end);
		} else {
			result = Collections.emptyList();
		}

		number = count - (currentPage - 1) * pageSize;
		Map<Object, Object> reCount = new HashMap<Object, Object>();
		
		PageCommand pageCmd = new PageCommand(currentPage, start, end, count, pageSize, number);
		
		model.addAttribute("search", result);
		model.addAttribute("cmd", pageCmd);
		model.addAttribute("number", number);
		
		return "search";
	}
	
	@RequestMapping(value = "/detail.board")
	public String read(BoardVo vo, Model model) throws Exception{
		int num = vo.getNum();
		model.addAttribute("detail", boardService.read(num));
		boardService.readCount(num);
		model.addAttribute("answer", boardService.answer(num));
		return "detail";
	}
	
	@RequestMapping(value = "/updateForm.board")
	public String updateView(BoardVo vo, Model model) throws Exception{
		System.out.println(vo.getNum());
		model.addAttribute("update", boardService.read(vo.getNum()));
		return "updateForm";
	}
	
	@RequestMapping(value = "/update.board", method = RequestMethod.POST)
	public String update(UpdateCommand cmd) throws Exception{
		BoardVo vo = new BoardVo();
		vo.setNum(cmd.getNum());
		vo.setTitle(cmd.getTitle());
		vo.setContent(cmd.getContent());
		vo.setModdate(new Timestamp(System.currentTimeMillis()));
		System.out.println(vo);
		boardService.edit(vo);
		return "redirect:/detail.board?num="+cmd.getNum();
	}
	
	@RequestMapping(value = "/deleteForm.board")
	public String deleteView(Integer num, Model model) throws Exception{
		model.addAttribute("delete", boardService.read(num));
		return "deleteForm";
	}
	
	@RequestMapping(value = "/delete.board", method = RequestMethod.POST)
	public String  delete(DeleteCommand cmd) throws Exception{
		if(cmd.checkPassword()) {
			boardService.delete(cmd.getNum());
		}
		return "redirect:/list.board";
	}
}
