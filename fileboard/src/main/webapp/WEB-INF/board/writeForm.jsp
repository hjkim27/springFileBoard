<%@page import="org.vo.BoardVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   
<jsp:include page="boardHeader.jsp" flush="false"/>
	
<article>
	<div>
		<h4>Write</h4>
	</div>
	<div>
	<form action="write.board" method="post" enctype="multipart/form-data">
	<c:if test="${num!=0 }">
		<input type="hidden" name="num" value="${num }">
	</c:if>
		<table border="1" >
			<tr>
				<td id="head">작성자: </td>
				<td><input id="inputbox" type="text" name="writer" required></td>
				<td id="head">비밀번호: </td>
				<td><input id="inputbox" type="password" name="password" required></td>
			</tr>
			<tr>
				<td id="head">제목: </td>
				<td colspan="4">
					<c:choose>
						<c:when test="${num eq null}">
							<input id="inputbox" type="text" name="title" required>
						</c:when>
						<c:otherwise>
							<input id="inputbox" type="text" name="title" placeholder="RE:${detail.title }" required>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td colspan="4"><textarea style="width: 99%; font-size: 12" rows="20" name="content" required></textarea></td>
			</tr>
			<tr>
			<c:if test="${num eq null }">
				<td id="head">첨부파일</td>
				<td colspan="3"><input id="inputbox" type="file" name="file"></td>
			</c:if>
			</tr>
		</table>
		<input type="button" onclick="location.href='list.board'" value="취소">
		<input type="submit" value="저장">
	</form>
	</div>
</article>


<jsp:include page="boardFooter.jsp" flush="false"/></html>