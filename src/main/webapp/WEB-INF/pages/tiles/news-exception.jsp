<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="by.htp.ex.service.exception.NewsServiceException" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

	<%-- ------------LOCALIZATION------------ --%>
	<fmt:setLocale value="${sessionScope.theLocale}"/> 
	<fmt:setBundle basename="localization.locale" var="locale"/> 
	<%-- messages --%>
	<fmt:message bundle="${locale}" key="newsExceptionPage.retry" var="newsExceptionPage_retry" />
	<fmt:message bundle="${locale}" key="newsExceptionPage.back" var="newsExceptionPage_back" />
	<%-- ------------------------------------ --%>	
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>

	<%
		NewsServiceException exception = (NewsServiceException)request.getAttribute("exception");
		String returnTo = (String)request.getAttribute("returnTo");
	%>
	
	
	<br><br>
		<%=  exception.getMessage()%>
	<br><br>
	
	 <%-- if exception happen while we are opening the app from scratch --%>
	<c:if test="${requestScope.exceptionFromWelcomePage eq 'ok'}">
		<div>
			<a href="index.jsp">${newsExceptionPage_retry}</a>
		</div>
	</c:if>
	
	<%-- if exception happen while we are opening the app from scratch --%>
	<c:if test="${not (requestScope.exceptionFromWelcomePage eq 'ok')}">
		<div>
			<a href="<%=returnTo%>">${newsExceptionPage_back}</a>
		</div>
	</c:if>
	
	<%-- if not work command "go_to_news_list" we suggest to user push "Back" -> means sing out and try again --%>
	<c:if test="${not (requestScope.goToNewsList eq null)}">
		<div>
			<form action="controller" method="post">
				<input type="hidden" name="command" value="do_sign_out" /> 
				<input type="submit" value="${newsExceptionPage_back}" /><br />
			</form>
		</div>
	</c:if>
	
	
	

	
		
</body>
</html> 