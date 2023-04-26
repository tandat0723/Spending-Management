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
            <c:when test="${currentUser.userRole.role == 'ROLE_ADMIN'}">
                <a class="navbar-brand" href="<c:url value="/admin" />">Job Searching</a>
            </c:when>
            <c:when test="${currentUser.userRole.role == 'ROLE_USER'}">
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
                <c:if test="${currentUser.userRole.role == 'ROLE_ADMIN'}">
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
                            Thống kê báo cáo
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="<c:url value="#"/>">Thống kê tài khoản</a>
                            <a class="dropdown-item" href="<c:url value="#"/>">Thống kê chi tiêu</a>
                        </div>
                    </li>
                </c:if>
                <c:if test="${currentUser.userRole.role == 'ROLE_USER' && currentUser.active.id == 1}">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="#" />">Chi tiêu cá nhân</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="#" />">Chi tiêu nhóm</a>
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
                        <c:choose>
                            <c:when test="${currentUser.avatar != null && currentUser.avatar != ''}">
                                <img class="rounded-circle" src="<c:url value="${currentUser.avatar}" />" width="40px;" height="40px;" />
                            </c:when>
                            <c:otherwise>
                                <img class="rounded-circle" src="<c:url value="/resources/images/none.png" />" width="40px;" height="40px;" />
                            </c:otherwise>
                        </c:choose>
                        <span style="color: #fff; text-shadow: 2px 2px 10px white;">${currentUser.fullname}</span>
                        <c:if test="${(currentUser.userRole.id == '3')}">
                            ${userService.getUserById(currentUser.id).name}
                        </c:if>
                        <c:if test="${!(currentUser.userRole.id == '3')}">
                            <span class="badge badge-secondary">${currentUser.userRole.role}</span>
                        </c:if>
                    </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="<c:url value="/me/view"/>" >Thông tin tài khoản</a>
                        <c:if test="${currentUser.userRole.id == '3'}">
                            <a class="dropdown-item" href="<c:url value="/user/user-info/add-or-update"/>">
                                Chỉnh sửa thông tin người dùng
                            </a>
                        </c:if>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link" style="line-height: 40px;" href="<c:url value="/logout" />">Đăng xuất</a>
                </li>
            </ul>
        </c:if>
    </nav>
</header>