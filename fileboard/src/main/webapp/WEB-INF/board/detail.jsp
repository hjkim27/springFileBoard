<%@page import="java.io.File"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.vo.BoardVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	pageContext.setAttribute("replaceChar", "\n");
%>

<jsp:include page="boardHeader.jsp" flush="false" />

<article>
	<div>
		<h4>detail</h4>
	</div>
	<div>
	<p>
		<table>
			<tr>
				<td align="center">
					<h2><b>${detail.title }</b></h2>
				</td>
			</tr>
			<tr style="text-align: right">
				<td>
					작성일: <fmt:formatDate value="${detail.regdate }" type="both" pattern="yyyy-MM-dd" /> &nbsp;|&nbsp; 
					수정일: <fmt:formatDate value="${detail.moddate }" type="both" pattern="yyyy-MM-dd" /> &nbsp;|&nbsp; 
					작성자: ${detail.writer } &nbsp;|&nbsp; 
					조회수: ${detail.readCount }
				</td>
			</tr>
			<tr>
				<td>${fn:replace(detail.content, replaceChar, "<br/>")}</td>
			</tr>
			<tr>
				<td>
					첨부파일
					<c:forEach var="file" items="${files}">
						<li><a href="<c:url value="/download"/>?num=${file.NUM}">${file.FILENAME } </a>(${file.FILESIZE}kb) <br></li>
					</c:forEach>
				</td>
			</tr>
			<input type="button" onclick="location.href='list.board'" value="목록">
			<input type="button" onclick="location.href='deleteForm.board?num=${detail.num}'" value="삭제">
			<input type="button" onclick="location.href='updateForm.board?num=${detail.num}'" value="수정">
		</table>
	</p>
	<br>
	<p>
		답글쓰기
		<form action="<c:url value='writeRef.board'/>" method="post">
			<input type="hidden" name="bNum" value=${detail.num }> 
			<table>
				<tr>
					<td>
						작성자 : ${detail.writer }<br>
						<textarea rows="3" style="width: 99%; font-size: 12; resize: none;" name="content"></textarea>
						<input type="submit" value="답글달기"></td>
				</tr>
			</table>
		</form>

			<c:forEach var="re" items="${answer }">
		<table style="background: #F6F5F5; margin-bottom: 3px">
				<tr>
					<td style="border: none;">
						작성자: ${re.writer } &nbsp;|&nbsp;
						작성일: <fmt:formatDate value="${re.regdate }" type="both" pattern="yyyy-MM-dd" /> 
					</td>
					<td rowspan="2" style="padding-bottom: 30px; border-bottom: none;">
						<form action="deleteForm.board" method="get">
							<input type="hidden" name="num" value="${re.num }"> 
							<input type="submit" value="삭제">
						</form>
						<form action="updateForm.board" method="post">
							<input type="hidden" name="num" value="${re.num }"> 
							<input type="submit" value="수정">
						</form>
					</td>
				</tr>
				<tr>
					<td colspan="2">내용: ${fn:replace(re.content, replaceChar, "<br/>")}</td>
				</tr>
		</table>
			</c:forEach>
	</p>
	</div>
</article>


<jsp:include page="boardFooter.jsp" flush="false" />