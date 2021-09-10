package org.controller;

import java.util.Collections;
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
import common.pageCommand;

@Controller // 컨트롤러 : 프로젝트 레이어, 웹 요청과 응답 처리
public class BoardController {
	@Autowired
	BoardService boardService;

	@RequestMapping(value = "/writeForm.board", method = RequestMethod.GET)
	public String WriteView() throws Exception {
		return "writeForm";
	}

	@RequestMapping(value = "/write.board", method = RequestMethod.POST)
	public String write(BoardVo vo) throws Exception {
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
		
		pageCommand cmd = new pageCommand(currentPage, start, end, count, pageSize, number);
		
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
		
		pageCommand pageCmd = new pageCommand(currentPage, start, end, count, pageSize, number);
		
		model.addAttribute("search", result);
		model.addAttribute("cmd", pageCmd);
		model.addAttribute("number", number);
		
		return "search";
	}
}
