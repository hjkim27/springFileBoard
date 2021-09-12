<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.vo.BoardVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<jsp:include page="boardHeader.jsp" flush="false"/>
	
<article>
	<div>
		<h4>BoardList</h4>
	</div>
	<div>
	<form action="list.board">
	<table>
		<tr>
			<td style="width: 15%">
				<select id="searchSelect" name="type">
					<option value="writer">작성자</option>
					<c:choose>
						<c:when test="${op.str ne '' && op.type eq 'title'}"><option value="title" selected>제목</option></c:when>
						<c:otherwise><option value="title">제목</option></c:otherwise>
					</c:choose>
				</select>
			</td>
			<td style="width: 70%">
				<input id="search" type="text" name="str" placeholder="<c:if test='${op.str!=null}'>${op.str }</c:if>">
			</td>
			<td style="width: 15%">
				<input id="search" type="submit" value="검색">
			</td>
		</tr>
	</table>
		<table>
			<tr style="background:#FCD8D4; text-align: center;">
				<th id="num">번호</th>
				<th id="title">제목</th>
				<th id="writer">작성자</th>
				<th id="regdate">작성일</th>
				<th id="file">파일</th>
				<th id="read">조회수</th>
			</tr>
			<c:forEach var="vo" items="${list }">
				<tr>  
					<td align="center">
						<c:out value="${number }"/>
						<c:set var="number" value="${number-1 }"/>
					</td>
					<td>
						<c:set var="nbr" value="${vo.ref }"/>
						<a href="detail.board?num=${vo.num }">
							${vo.title}
							<c:forEach var="ansCount" items="${answerCount }">
								<c:if test="${ansCount.REF==vo.num and ansCount.REFCOUNT>1}">
									 [<c:out value="${ansCount.REFCOUNT-1 }"/>]
								</c:if>
							</c:forEach>
						</a>
					</td>
					<td>${vo.writer }</td>
					<td><fmt:formatDate value="${vo.regdate }" type="both" pattern="yyyy-MM-dd"/></td>
					<td align="center">
						<c:forEach var="file" items="${fileCount }">
							<c:if test="${file.BNUM==vo.num and file.FILECOUNT>0 }">
								<c:out value="${file.FILECOUNT}"></c:out>
							</c:if>
						</c:forEach>
					
					</td>
					<td align="center">${vo.readCount }</td>
				</tr>
			</c:forEach>
		</table>

		<c:if test="${cmd.count>0 }">
			<c:set var="tmp" value="${cmd.count%cmd.pageSize==0? 0:1}"/>
			<c:set var="pageCount" value="${cmd.count/cmd.pageSize + tmp }"/>
			<fmt:parseNumber var="pageCount" value="${pageCount }" integerOnly="true"/>
			
			<c:set var="pageBlock" value="${5 }"/>
			
			<fmt:parseNumber var="result" value="${(cmd.currentPage-1)/pageBlock }" integerOnly="true"/>
			<c:set var="startPage" value="${result*pageBlock + 1 }"></c:set>
			<c:set var="endPage" value="${startPage + pageBlock -1 }"></c:set>
			
			
			<c:if test="${endPage>pageCount }">
				<c:set var="endPage" value="${pageCount }"/>
			</c:if>
			<c:choose>
				<c:when test="${op!=null}">
					<c:set var="options" value="type=${op.type}&str=${op.str}&"></c:set>
				</c:when>
				<c:otherwise>
					<c:set var="options" value=""></c:set>
				</c:otherwise>
			</c:choose>
			
			<c:if test="${startPage>pageBlock }">
				<a href="list.board?${options}pageNum=${startPage-pageBlock }">[이전]</a>
			</c:if>
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<a href="list.board?${options}pageNum=${i }">[${i }]</a>
			</c:forEach>
			<c:if test="${endPage<pageCount }">
				<a href="list.board?${options}pageNum=${startPage+pageBlock }">[다음]</a>
			</c:if>
		
		</c:if>
		<input type="button" onclick="location.href='writeForm.board'" value="글쓰기">
	</form>
	</div>
</article>


<jsp:include page="boardFooter.jsp" flush="false"/>