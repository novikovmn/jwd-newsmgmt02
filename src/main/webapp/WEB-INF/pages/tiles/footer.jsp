<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

	<%-- ------------LOCALIZATION------------ --%>
	<fmt:setLocale value="${sessionScope.theLocale}"/> 
	<fmt:setBundle basename="localization.locale" var="locale"/> 
	<%-- messages --%>
	<fmt:message bundle="${locale}" key="footer" var="footer" />
	<%-- ------------------------------------ --%>

	<center>
		${footer}
	</center>
