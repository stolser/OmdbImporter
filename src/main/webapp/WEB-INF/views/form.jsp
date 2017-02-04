<%@include file="/WEB-INF/views/includes/header.jsp" %>
<fmt:setBundle basename="i18n.formPage" var="langFormPage"/>

<p>Hello World!</p>
<fmt:message key="htmlHead.title" bundle="${langFormPage}"/>

<%@include file="/WEB-INF/views/includes/footer.jsp" %>

