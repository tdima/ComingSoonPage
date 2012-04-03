<%@ page import="com.google.appengine.api.datastore.*" %>
<%@ page import="com.tumash.usefy.objwork.EMailAddress" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List e-mail</title>
</head>
<body>
<ul>

    <c:forEach var="cur" items="${listEMails}">
        <li>
            <c:out value="${cur.email}" />
        </li>
    </c:forEach>
        <%--<c:forEach var="i" begin="2" end="10" step="2">
            <c:out value="${i}"/>
        </c:forEach>--%>
    <%--<%
        QueryResultIterable<EMailAddress> list = (QueryResultIterable<EMailAddress>)request.getAttribute("listEMails");
        if(list == null) return;
    %>
    <br><br>
    <%
        String email = null;
        for (EMailAddress e: list) {
            email = e.getEmail();
         %>

    <li>
        <%=
            email
        %>
    </li>
    <%
        }
    %>--%>
</ul>
</body>
</html>