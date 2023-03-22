<%-- 
    Document   : header
    Created on : Mar 20, 2023, 12:04:57 AM
    Author     : trant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
    <nav class="navbar navbar-expand-sm navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="">Logo</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mynavbar">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="mynavbar">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <c:url value="/" var="action"/>
                        <a class="nav-link" href="${action}">Trang chủ</a>
                    </li>
                    <c:forEach items="${categories}" var="c">
                        <c:url value="/" var="url">
                            <c:param name="categoryId" value="${c.id}"/>
                        </c:url>
                        <li class="nav-item">
                            <a class="nav-link" href="${url}">${c.name}</a>
                        </li>
                    </c:forEach>
                </ul>
                <c:url value="/" var="action"/>
                <form class="d-flex" action="${action}">
                    <input class="form-control me-2" type="text" name="kw" placeholder="Nhập thông tin" />
                    <button class="btn btn-primary" type="button" style="width: 8rem;">Tìm kiếm</button>
                </form>
            </div>
        </div>
    </nav>
</header>