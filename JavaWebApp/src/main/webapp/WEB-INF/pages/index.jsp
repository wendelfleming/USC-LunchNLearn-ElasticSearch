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

    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-ui.min.js"></script>
    <script type="text/javascript" src="${contextPath}/js/jquery/swfobject.js"></script>

    <script type="text/javascript" src="<%=request.getContextPath()%>/js/LunchnLearn.js"></script>

    <c:set var="jsPath" value="${contextPath}/js/jbreadcrumb/"/>
    <c:set var="cssPath" value="${contextPath}/css/jbreadcrumb/"/>

    <style type='text/css' media='screen'>@import "${contextPath}/css/lunchnlearn.css";</style>

    <style type='text/css' media='screen'>@import "${contextPath}/css/jquery/jquery-ui.min.css";</style>
    <style type='text/css' media='screen'>@import "${contextPath}/css/jquery/jquery-ui.structure.min.css";</style>
    <style type='text/css' media='screen'>@import "${contextPath}/css/jquery/jquery-ui.theme.min.css";</style>

    <script type="text/javascript">
        <!--
        var page_ajaxmapping = "${contextPath}/spring/suggest/";
        //-->
    </script>
</head>

<body>

<div id="main_section">


    <h1>Elastic WoW</h1>

    <div id="content_section">
        <h3>Search</h3>


        <form action="${contextPath}/spring/simplesearch.html" method="get">
            <input type="hidden" name="x" value="${x}"/>
            <input type="hidden" name="currentPage" value="1"/>
            Search: <input type="text" name="searchQuery" id="wow_search"/><br/>
            <input type="submit" value="Search"/>
        </form>

    </div>
</div>

</body>

</html>