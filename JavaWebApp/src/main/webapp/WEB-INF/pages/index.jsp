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

    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="${contextPath}/js/jquery/swfobject.js"></script>

    <c:set var="jsPath" value="${contextPath}/js/jbreadcrumb/"/>
    <c:set var="cssPath" value="${contextPath}/css/jbreadcrumb/"/>
    ${usc:breadcrumbhead(jsPath,cssPath)}

    <style type='text/css' media='screen'>@import "${contextPath}/css/lunchnlearn.css";</style>

</head>

<body>

<div id="main_section">


    <h1>${message}</h1>

    ${usc:breadcrumbbody(x, bCrumbs)}

    <div id="content_section">
        <h3>Search</h3>

        <%--<form action="<c:url value="/spring/search"/>" method="post">--%>
        <form action="${contextPath}/spring/search" method="get">
            <input type="hidden" name="x" value="${x}"/>
            Search: <input type="text" name="searchTerm"/><br/>
            <input type="submit" value="Search"/>
        </form>


        <h3>Database Administration</h3>

        <%--<a href="<c:url value="/spring/db/genre/"/>">Genre</a><br />--%>
        <%--<a href="<c:url value="/spring/db/platform/"/>">Platform</a><br />--%>
        <%--<a href="<c:url value="/spring/db/studio/"/>">Studio</a><br />--%>
        <%--<a href="<c:url value="/spring/db/game/"/>">Game</a><br />--%>

        <a href="${contextPath}/spring/db/genre/?x=${x}">Genre</a><br/>
        <a href="${contextPath}/spring/db/platform/?x=${x}">Platform</a><br/>
        <a href="${contextPath}/spring/db/studio/?x=${x}">Studio</a><br/>
        <a href="${contextPath}/spring/db/game/?x=${x}">Game</a><br/>
    </div>
</div>

</body>

</html>