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
                <c:if test="${currentUser.userType == 'ROLE_ADMIN'}">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="#"/> ">Tài khoản</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="#"/> ">Tài khoản</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="#"/> ">Tài khoản</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
                            Thống kê
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="<c:url value="#"/>">Thống kê chỉ tiêu các nhân</a>
                            <a class="dropdown-item" href="<c:url value="#"/>">Thống kê chi tiêu nhóm</a>
                        </div>
                    </li>
                </c:if>
                <c:if test="${currentUser.userType == 'ROLE_USER' && currentUser.active == 1}">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="#"/>">
                            Thêm chi tiêu cá nhân
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="#"/>">
                            Tạo nhóm chi tiêu
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="#"/>">
                            Thời gian rãnh
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="#"/>">
                            Tìm kiếm
                        </a>
                    </li>
                </c:if>
            </ul>
        </div>
        <c:if test="${currentUser != null}">
            <ul class="nav navbar-nav navbar-right">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="<c:url value="/me/view" />"
                       role="button" aria-haspopup="true" aria-expanded="false">
                        <span>
                            <i class="fa-solid fa-user"></i>
                        </span>
                        <c:if test="${!(currentUser.userType == 'ROLE_USER')}">
                            ${currentUser.lastName}
                        </c:if>

                    </a>
                </li>
            </ul>
        </c:if>   

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