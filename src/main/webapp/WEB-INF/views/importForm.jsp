<%@include file="/WEB-INF/views/includes/header.jsp" %>
<fmt:setBundle basename="i18n.formPage"/>

<div class="row">
    <div class="col-md-8 col-md-offset-2">

        <form class="form-horizontal" role="form"
              method="post"
              action="/videos">

            <div class="form-group">
                <h3><fmt:message key="formPage.title.top"/></h3>
            </div>

            <div class="form-group">
                <label for="${ApplicationResources.SEARCH_TEXT_PARAM}" class="col-sm-3 control-label">
                    <fmt:message key="formPage.searchText"/></label>
                <div class="col-sm-9">
                    <input id="${ApplicationResources.SEARCH_TEXT_PARAM}" type="text" class="form-control"
                           name="${ApplicationResources.SEARCH_TEXT_PARAM}" required
                           placeholder="<fmt:message key="formPage.searchText"/>"/>
                </div>
            </div>

            <div class="form-group">
                <label for="${ApplicationResources.SEARCH_YEAR_PARAM}" class="col-sm-3 control-label">
                    <fmt:message key="formPage.searchYear"/></label>
                <div class="col-sm-9">
                    <input id="${ApplicationResources.SEARCH_YEAR_PARAM}" type="number"
                           class="form-control" value="0"
                           name="${ApplicationResources.SEARCH_YEAR_PARAM}"
                           placeholder="<fmt:message key="formPage.searchYear"/>"/>
                </div>
            </div>

            <div class="form-group">
                <label for="${ApplicationResources.SEARCH_VIDEO_TYPE_PARAM}" class="col-sm-3 control-label">
                    <fmt:message key="formPage.searchVideoType"/></label>
                <div class="col-sm-9">
                    <select id="${ApplicationResources.SEARCH_VIDEO_TYPE_PARAM}" class="form-control"
                            name="${ApplicationResources.SEARCH_VIDEO_TYPE_PARAM}" required>
                        <c:forEach items="${videoTypes}" var="videoType">
                            <option value="${videoType}">
                                <fmt:message key="${videoType.messageKey}"/>
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="row text-center">
                <button type="submit" class="btn btn-primary">
                    <fmt:message key="formPage.importBtn.label"/>
                </button>
            </div>


        </form>
    </div>
</div>

<%@include file="/WEB-INF/views/includes/footer.jsp" %>

