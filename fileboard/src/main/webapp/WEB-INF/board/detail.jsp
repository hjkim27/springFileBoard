<%@page import="java.io.File"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.vo.BoardVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% pageContext.setAttribute("replaceChar", "\n"); %>
  
<jsp:include page="boardHeader.jsp" flush="false"/>
	
<article>
	<div>
		<h4>detail</h4>
	</div>
	<div>
	<table>
		<form action="writeForm.board"  method="get">
		<input type="hidden" name="num" value=${detail.num }>
		<input type="hidden" name="depth" value=${detail.depth }>
			<tr>
				<td colspan="4" align="center"><h2><b>${detail.title }</b></h2></td>
			</tr>
			<tr style="text-align: right">
				<td colspan="4">
					작성일: <fmt:formatDate value="${detail.regdate }" type="both" pattern="yyyy-MM-dd"/> &nbsp;|&nbsp; 
					수정일: <fmt:formatDate value="${detail.moddate }" type="both" pattern="yyyy-MM-dd"/> &nbsp;|&nbsp;
					작성자: ${detail.writer } &nbsp;|&nbsp;
					조회수: ${detail.readCount }
				</td>
			</tr>
			<tr>
				<td colspan="4">${fn:replace(detail.content, replaceChar, "<br/>")}</td>
			</tr>
			<tr>
				<td>첨부파일</td>
				<td colspan="3">
					<c:forEach var="file" items="${files}">
						<a href="<c:url value="/download"/>?num=${file.NUM}"> ${file.FILENAME } </a>(${file.FILESIZE}kb) <br>
					</c:forEach>
				</td>
			</tr>
			<input type="submit" value="답글달기">
		</form>
		<input type="button" onclick="location.href='list.board'" value="목록">
		<input type="button" onclick="location.href='deleteForm.board?num=${detail.num}'" value="삭제">
		<input type="button" onclick="location.href='updateForm.board?num=${detail.num}'" value="수정">
	</table>
		<br>
	<br>
		
		
	<table>
	<c:if test="${fn:length(answer)!=0 }">
		<caption>답글</caption>
	</c:if>
	<c:forEach var="re" items="${answer }">	
		<tr style="background: #F6F5F5;">
			<td>제목: ${re.title } RE:${detail.title }</td>
			<td>작성일: <fmt:formatDate value="${re.regdate }" type="both" pattern="yyyy-MM-dd"/> &nbsp;|&nbsp; 작성자: ${re.writer }</td>
		</tr>
		<tr>
		</tr>
		<tr>
			<td colspan="2">내용: ${fn:replace(re.content, replaceChar, "<br/>")}</td>
		</tr>
		<tr>
			<td colspan="2"  style="padding-bottom: 30px; border-bottom: none;">
			<form action="deleteForm.board" method="get">
				<input type="hidden" name = "num" value="${re.num }">
				<input type="submit" value="삭제">
			</form>
			<form action="updateForm.board" method="post">
				<input type="hidden" name = "num" value="${re.num }">
				<input type="submit" value="수정">
			</form>
			</td>
		</tr>
	</c:forEach>
	</table>
	
	</div>
</article>


<jsp:include page="boardFooter.jsp" flush="false"/>