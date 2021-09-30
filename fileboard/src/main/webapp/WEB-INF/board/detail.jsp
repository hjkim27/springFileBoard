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
<script>
	const reply = () => {
	    const box = document.getElementById("reply");
	    box.innerHTML = "<textarea rows='3' style='width: 90%; resize: none;' name='content'></textarea> <input type='button' value='저장' onclick='btn(this)'>";
	}
   const btn = (obj) => {
       document.getElementById('insert').submit();
   }
</script>

<jsp:include page="boardHeader.jsp" flush="false" />

<article>
	<div>
		<h4>detail</h4>
	</div>
	<div>
		<table>
			<tr>
				<td align="center" colspan="2">
					<h2>
						<b>${detail.title }</b>
					</h2>
				</td>
			</tr>
			<tr>
				<td>
					<c:if test="${nextPage.AFTER != null }">
						<a href="detail.board?num=${nextPage.AFTER}">이전글</a>
					</c:if>
				</td>
				<td align="right">
					 <c:if test="${nextPage.BEFORE != null }">
						<a href="detail.board?num=${nextPage.BEFORE}">다음글</a>
					</c:if>
				</td>
			</tr>
			<tr style="text-align: right">
				<td  colspan="2">
					작성일: <fmt:formatDate value="${detail.regdate }" type="both" pattern="yyyy-MM-dd" /> &nbsp;|&nbsp; 
					수정일: <fmt:formatDate value="${detail.moddate }" type="both" pattern="yyyy-MM-dd" /> &nbsp;|&nbsp; 
					작성자: ${detail.writer } &nbsp;|&nbsp; 
					조회수: ${detail.readCount }
				</td>
			</tr>
			<tr>
				<td colspan="2" >${fn:replace(detail.content, replaceChar, "<br/>")}</td>
			</tr>
			<tr>
				<td colspan="2">
					첨부파일
					<c:forEach var="file" items="${files}">
						<li><a href="<c:url value="/download"/>?num=${file.NUM}">${file.FILENAME }</a>(${file.FILESIZE}kb) <br></li>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" onclick="location.href='list.board'" value="목록"> 
					<input type="button" onclick="location.href='deleteForm.board?num=${detail.num}'" value="삭제"> 
					<input type="button" onclick="location.href='updateForm.board?num=${detail.num}'" value="수정">
				</td>
			</tr>
		</table>

		<br>

		<div style="padding-left: 0%;">
			<div>
				작성자 : ${detail.writer } <input type="button" value="답글달기" onclick="reply()">
			</div>
			<form action="writeRef.board" id="insert" method="post">
				<input type="hidden" name="bNum" value="${detail.num }" />
				<input type="hidden" name="writer" value="${detail.writer }" />
				<div id="reply"></div>
			</form>
		</div>

		<c:forEach var="re" items="${answer }">
			<div style="background: #F6F5F5; padding-left: ${2*re.depth}%; border-bottom: solid 1px black;">
				작성자: ${re.writer } &nbsp;|&nbsp;
				작성일: <fmt:formatDate value="${re.regdate }" type="both" pattern="yyyy-MM-dd" />
					<input type="button" onclick="location.href='deleteForm.board'" value="삭제"> 
					<input type="button" onclick="location.href='updateForm.board'" value="수정">
					<input type="button" value="답변" onclick="add_reply${re.num}()">
				<div>
					${fn:replace(re.content, replaceChar, "<br/>")}
				</div>
				<form action="writeRef.board" id="insert" method="post">
					<input type="hidden" name="bNum" value="${re.bNum }" />
					<input type="hidden" name="writer" value="${detail.writer }" />
					<input type="hidden" name="ref" value="${re.ref}" />
					<div id="add_reply${re.num}"></div>
				</form>
			</div>
			<script type="text/javascript">
			const add_reply${re.num} = () => {
				var name = '<c:out value="add_reply${re.num}"/>';
				alert(name);
			    const box = document.getElementById(name);
			    box.innerHTML = "<textarea rows='3' style='width: 90%; resize: none;' name='content'></textarea> <input type='button' value='저장' onclick='btn(this)'>";
			}
			</script>
			</c:forEach>
	</div>
</article>


<jsp:include page="boardFooter.jsp" flush="false" />