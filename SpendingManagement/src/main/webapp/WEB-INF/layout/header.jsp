<%-- 
    Document   : header
    Created on : Mar 20, 2023, 12:04:57 AM
    Author     : trant
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="se" uri="http://www.springframework.org/security/tags"%>
<header>
    <nav class="navbar sticky-top navbar-expand-md bg-dark navbar-dark">
        <c:choose>
            <c:when test="${currentUser.userRole == 'ROLE_ADMIN'}">
                <a class="navbar-brand" href="<c:url value="/admin" />">Job Searching</a>
            </c:when>
            <c:when test="${currentUser.userRole == 'ROLE_USER'}">
                <a class="navbar-brand" href="<c:url value="/home" />">Job Searching</a>
            </c:when>
            <c:otherwise>
                <a class="navbar-brand" href="<c:url value="/" />">Job Searching</a>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${currentUser == null}">
                <ul class="nav navbar-nav navbar-right">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/login" />">Đăng nhập</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/register" />">Đăng ký</a>
                    </li>
                </ul>
            </c:when>
            <c:when test="${currentUser != null}">
                <ul class="nav navbar-nav navbar-right">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="<c:url value="/me/view" />"
                           role="button" aria-haspopup="true" aria-expanded="false">
                            <span>
                                <i class="fa-solid fa-user"></i>
                            </span>
                            <c:if test="${!(currentUser.userRole == 'ROLE_USER')}">
                                ${currentUser.fullname}
                            </c:if>
                            <c:if test="${currentUser.userRole == 'ROLE_USER'}">
                                ${userService.getUserById(currentUser.id).name}
                            </c:if>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/" />">${pageContext.request.userPrincipal.name}</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/logout" />">Đăng xuất</a>
                    </li>
                </ul>
            </c:when>
        </c:choose>
        <se:authorize access="hasRole('ROLE_ADMIN')">
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/admin/**" />">Quản lý</a>
            </li>
        </se:authorize>
    </nav>
</header>