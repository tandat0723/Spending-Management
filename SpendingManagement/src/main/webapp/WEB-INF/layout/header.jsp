<%-- 
    Document   : header
    Created on : Mar 20, 2023, 12:04:57 AM
    Author     : trant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
    <nav class="navbar sticky-top navbar-expand-md bg-dark navbar-dark">
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