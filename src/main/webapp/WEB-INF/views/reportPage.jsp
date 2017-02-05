<%@include file="/WEB-INF/views/includes/header.jsp" %>
<fmt:setBundle basename="i18n.formPage"/>

<div class="row">
    <div class="col-md-8 col-md-offset-2">
        <h3 class="alert alert-${(importResult.success == 'true') ? 'success' : 'danger'}" role="alert">
            <fmt:message key="${importResult.message}"/></h3>
        <c:if test="${importResult.success == 'true'}">
            <p><fmt:message key="formPage.results.number"/>: ${importResult.savedVideos}</p>
        </c:if>
    </div>
</div>

<%@include file="/WEB-INF/views/includes/footer.jsp" %>

