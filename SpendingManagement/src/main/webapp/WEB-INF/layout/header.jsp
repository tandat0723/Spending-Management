<%-- 
    Document   : header
    Created on : Mar 20, 2023, 12:04:57 AM
    Author     : trant
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
    <nav class="navbar sticky-top navbar-expand-md bg-dark navbar-dark">
        <c:choose>
            <c:when test="${currentUser.userType == 'ROLE_ADMIN'}">
                <a class="navbar-brand" href="<c:url value="/admin" />">Job Searching</a>
            </c:when>

            <c:when test="${currentUser.userType == 'ROLE_USER'}">
                <a class="navbar-brand" href="<c:url value="/" />">Job Searching</a>
            </c:when>
            <c:otherwise>
                <a class="navbar-brand" href="<c:url value="/" />">Job Searching</a>
            </c:otherwise>
        </c:choose>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>

        <c:if test="${currentUser == null}">
            <ul class="nav navbar-nav navbar-right">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/login" />">Đăng nhập</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/register" />">Đăng ký</a>
                </li>
            </ul>
        </c:if>
    </nav>
</header>