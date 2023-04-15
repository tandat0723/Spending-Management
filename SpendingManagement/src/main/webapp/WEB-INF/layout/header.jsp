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
                <a class="navbar-brand" href="<c:url value="/user" />">Job Searching</a>
            </c:when>
            <c:otherwise>
                <a class="navbar-brand" href="<c:url value="/" />">Job Searching</a>
            </c:otherwise>
        </c:choose>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav">
                <c:if test="${currentUser.userRole == 'ROLE_ADMIN'}">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="#"/> ">Tài khoản</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="#"/> ">Duyệt NTD</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="#"/> ">Bài viết</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="#"/> ">Loại việc làm</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button"
                           aria-haspopup="true" aria-expanded="false">
                            Thống kê
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="<c:url value="#"/>">Thống kê loại tài khoản</a>
                            <a class="dropdown-item" href="<c:url value="#"/>">Thống kê bài viết theo tháng</a>
                            <a class="dropdown-item" href="<c:url value="#"/>">Thống kê</a>
                        </div>
                    </li>
                </c:if>
            </ul>
        </div>
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
        <c:if test="${currentUser != null}">
            <ul class="nav navbar-nav navbar-right">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="<c:url value="/me/view" />"
                       role="button" aria-haspopup="true" aria-expanded="false">
                        <span>
                            <i class="fa-solid fa-user"></i>
                        </span>
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
        </c:if>
    </nav>
</header>