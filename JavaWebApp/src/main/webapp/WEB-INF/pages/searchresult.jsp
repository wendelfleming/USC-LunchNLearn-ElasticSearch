<%--
* Copyright 2015 wendel fleming
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
--%>
<!doctype html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib prefix="usc" uri="/WEB-INF/usc-lunchnlearn-functions.tld" %>

<html>
<head>
    <meta charset="utf-8">
    <title>WoW ElasticSearch</title>

    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    <c:set var="imageDir" value="${contextPath}/images/"/>

    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="${contextPath}/js/jquery/swfobject.js"></script>

    <c:set var="jsPath" value="${contextPath}/js/jbreadcrumb/"/>
    <c:set var="cssPath" value="${contextPath}/css/jbreadcrumb/"/>
    ${usc:breadcrumbhead(jsPath,cssPath)}

    <style type='text/css' media='screen'>@import "${contextPath}/css/lunchnlearn.css";</style>

</head>

<body>

<div id="main_section">

    <h3>Search Results</h3>


    <%--${usc:breadcrumbbody(x, bCrumbs)}--%>

    <div id="content_section">

        Results for ${searchterm} (${resultsPage.totalElements}): <br/>


        <c:if test="${!empty resultsPage.content}">
            <table>
                <thead>
                <tr>
                    <th>&nbsp;</th>
                    <th>Name</th>
                    <th>Class</th>
                    <th>SubClass</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${resultsPage.content}" var="wowitem">
                    <tr>
                        <td><img src="http://media.blizzard.com/wow/icons/56/${wowitem.icon}.jpg"/></td>
                        <td>${wowitem.name}</td>
                        <td>${wowitem.itemClass}</td>
                        <td>${wowitem.itemSubClass}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <c:if test="${resultsPage.totalPages > 1}">
                ${usc:paginator(currentPage, resultsPage.totalPages, urlParams, imageDir )}
            </c:if>


        </c:if>
        <c:if test="${empty resultsPage.content}">
            No results found.
        </c:if>


    </div>
</div>


</body>
</html>
