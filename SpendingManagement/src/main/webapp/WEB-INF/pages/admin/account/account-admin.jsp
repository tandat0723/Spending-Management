<%-- 
    Document   : userManagement
    Created on : Apr 7, 2023, 5:10:36 PM
    Author     : phuan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1 class="text-info text-center">Quản lý người dùng</h1>

<table class="table">
    <thead>
        <tr>
            <th></th>
            <th class="th-sort"><a href="javascript:;" onclick="sortColumn(this)">User ID<span class="sort-icon"></span></a></th>
            <th class="th-sort"><a href="javascript:;" onclick="sortColumn(this)">Họ và tên<span class="sort-icon"></span></a></th>
            <th class="th-sort"><a href="javascript:;" onclick="sortColumn(this)">Email<span class="sort-icon"></span></a></th>
            <th class="th-sort"><a href="javascript:;" onclick="sortColumn(this)">Số điện thoại<span class="sort-icon"></span></a></th>
            <th class="th-sort"><a href="javascript:;" onclick="sortColumn(this)">Tên đăng nhập<span class="sort-icon"></span></a></th>
            <th class="th-sort"><a href="javascript:;" onclick="sortColumn(this)">Mật khẩu<span class="sort-icon"></span></a></th>
            <th class="th-sort"><a href="javascript:;" onclick="sortColumn(this)">Trạng thái<span class="sort-icon"></span></a></th>
            <th class="th-sort"><a href="javascript:;" onclick="sortColumn(this)">Loại tài khoản<span class="sort-icon"></span></a></th>
            <th class="th-sort"><a href="javascript:;" onclick="sortColumn(this)">Ngày tạo<span class="sort-icon"></span></a></th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${users}" var="u">
            <tr id="user${u.id}">
                <td class="overflow-hidden">
                    <img class="rounded-circle" src="${u.avatar}" width="55px" height="55px" />
                </td>
                <td class="overflow-hidden">${u.id}</td>
                <td class="overflow-hidden">${u.fullname}</td>
                <td class="overflow-hidden">${u.email}</td>
                <td class="overflow-hidden">${u.phone}</td>
                <td class="overflow-hidden">${u.username}</td>
                <td class="overflow-hidden">${u.password}</td>
                <td class="overflow-hidden">${u.active.statusName}</td>
                <td class="overflow-hidden">${u.userRole.role}</td>
                <td class="overflow-hidden">${u.joinedDate}</td>
                <td>
                    <c:url value="/api/account-admin/view/${u.id}" var="endpointview" />
                    <a href="javascript:;" onclick="adminAccountView('${endpointview}')" class="btn-spending js-view-account" style="color: blue; margin-right: 5px;"><i class="fas fa-eye"></i></a>
                    <a href="<c:url value="/admin/account-admin/${u.id}" />" class="btn-spending js-edit-account" style="color: #00bbb3; margin-right: 5px;"><i class="fas fa-pen"></i></a>
                    <div id="spinner${u.id}" style="display:none" class="spinner-border spinner-border-sm"></div>
                    <c:url value="/api/users/${u.id}" var="endpoint" />
                    <a id="delete${u.id}" href="javascript:;" style="color: red;" onclick="deleteUser('${endpoint}', ${u.id})"><i class="fas fa-trash"></i></a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<c:url value="/admin/account-admin" var="action" />
<form:form method="POST" action="${action}"
           modelAttribute="user" enctype="multipart/form-data">
    <c:choose>
            <c:when test="${user.id > 0}">
                <h3 class="text-center text-primary">Cập nhật thông tin</h3>
            </c:when>
            <c:otherwise>
                <h3 class="text-center text-success">Thêm người dùng</h3>
            </c:otherwise>
        </c:choose>
    <form:errors path="*" element="div" cssClass="alert alert-danger" />
    <div class="row">
        <div class="col-md-6">
            <form:hidden path="id" />
            <form:hidden path="avatar" />
            <div class="form-floating mb-3 mt-3">
                <label for="fullname">Họ và Tên</label>
                <form:input class="form-control" id="fullname" placeholder="Họ và Tên" path="fullname" name="fullname" />
            </div>
            <div class="form-floating mb-3 mt-3">
                <label for="email">Email</label>
                <form:input class="form-control" id="email" placeholder="Email" path="email" name="email" />
            </div>
            <div class="form-floating mb-3 mt-3">
                <label for="phone">Số điện thoại</label>
                <form:input class="form-control" id="phone" placeholder="Số điện thoại" path="phone" name="phone" />
            </div>
            <div class="form-floating mb-3 mt-3">
                <label for="file">Avatar</label>
                <form:input type="file" class="form-control" id="file" path="file" name="file" onchange="previewImage(this);" />
            </div>
            <div class="form-floating mb-3 mt-3" id="preview-container" style="display: none;">
                <img id="preview" src="" width="150" />
            </div>
            <c:if test="${user.avatar != null && user.avatar != ''}">
                <div class="form-floating mb-3 mt-3" id="avatar-old">
                    <img src="${user.avatar}" width="150" />
                </div>
            </c:if>
        </div>
        <div class="col-md-6">
            <div class="form-floating mb-3 mt-3">
                <label for="username">Tên đăng nhập</label>
                <form:input class="form-control" id="username" placeholder="Tên đăng nhập" path="username" name="username" />
            </div>
            <div class="form-floating mb-3 mt-3">
                <label for="password">Mật khẩu</label>
                <form:password class="form-control" id="password" placeholder="Mật khẩu" path="password" name="password" />
            </div>
            <div class="form-floating mb-3 mt-3">
                <label for="active">Trạng thái</label>
                <form:select class="form-control" id="active" name="active" path="active">
                    <c:forEach items="${status}" var="t">
                        <c:choose>
                            <c:when test="${user.active.id == t.id}">
                                <option value="${t.id}" selected>${t.statusName}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${t.id}">${t.statusName}</option>
                            </c:otherwise>
                        </c:choose>

                    </c:forEach>
                </form:select>
            </div>
            <div class="form-floating mb-3 mt-3">
                <label for="userRole">Loại tài khoản</label>
                <form:select class="form-control" id="userRole" name="userRole" path="userRole">
                    <c:forEach items="${roles}" var="r">
                        <c:choose>
                            <c:when test="${user.userRole.id == r.id}">
                                <option value="${r.id}" selected>${r.role}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${r.id}">${r.role}</option>
                            </c:otherwise>
                        </c:choose>

                    </c:forEach>
                </form:select>
            </div>
        </div>
    </div>

    <div class="row d-flex justify-content-center align-items-center p-md-3">
        <c:choose>
            <c:when test="${user.id > 0}">
                <input type="submit" value="Cập nhật thông tin" class="btn btn-info" />
            </c:when>
            <c:otherwise>
                <input type="submit" value="Thêm người dùng" class="btn btn-success" />
            </c:otherwise>
        </c:choose>

    </div>
</form:form>

<div class="js-modal-view">
    <div class="modal-view-container js-modal-view-container">
        <div class="js-modal-view-close">
            <i class="fa-solid fa-xmark"></i>
        </div>
        <header class="modal-view-header">
            <h1 class="text-center" style="padding: 20px 30px">THÔNG TIN TÀI KHOẢN</h1>
        </header>
        <div class="modal-view-body">
            <section class="section about-section gray-bg" id="about">
                <div class="container m-0">
                    <div class="row flex-row-reverse"  id="js-modal-view">
                        
                    </div>
                </div>
            </section>
        </div>
    </div>
</div>
<style>
    .gray-bg {
        background-color: #f5f5f5;
    }

    .img-view {
        max-width: 90%;
    }

    .img-view {
        vertical-align: middle;
        border-style: none;
    }

    /* About Me
    ---------------------*/
    .about-text h3 {
        font-size: 45px;
        font-weight: 700;
        margin: 0 0 6px;
    }

    @media (max-width: 767px) {
        .about-text h3 {
            font-size: 35px;
        }
    }

    .about-text h6 {
        font-weight: 600;
        margin-bottom: 15px;
    }

    @media (max-width: 767px) {
        .about-text h6 {
            font-size: 18px;
        }
    }

    .about-text p {
        font-size: 18px;
        max-width: 450px;
    }

    .about-text p mark {
        font-weight: 600;
        color: #20247b;
    }

    .about-list {
        padding-top: 10px;
    }

    .about-list .media {
        padding: 5px 0;
    }

    .about-list label {
        color: #20247b;
        font-weight: 600;
        width: 88px;
        margin: 0;
        position: relative;
    }

    .about-list label:after {
        content: "";
        position: absolute;
        top: 0;
        bottom: 0;
        right: 11px;
        width: 1px;
        height: 12px;
        background: #20247b;
        -moz-transform: rotate(15deg);
        -o-transform: rotate(15deg);
        -ms-transform: rotate(15deg);
        -webkit-transform: rotate(15deg);
        transform: rotate(15deg);
        margin: auto;
        opacity: 0.5;
    }

    .about-list p {
        margin: 0;
        font-size: 15px;
    }

    @media (max-width: 991px) {
        .about-avatar {
            margin-top: 30px;
        }
    }

    .about-section .counter {
        margin-top: 30px;
        padding: 22px 20px;
        background: #ffffff;
        border-radius: 10px;
        box-shadow: 0 0 30px rgba(31, 45, 61, 0.125);
    }

    .about-section .counter .count-data {
        margin-top: 10px;
        margin-bottom: 10px;
    }

    .about-section .counter .count {
        font-weight: 700;
        color: #20247b;
        margin: 0 0 5px;
    }

    .about-section .counter p {
        font-weight: 600;
        margin: 0;
    }

    mark {
        background-image: linear-gradient(rgba(252, 83, 86, 0.6), rgba(252, 83, 86, 0.6));
        background-size: 100% 3px;
        background-repeat: no-repeat;
        background-position: 0 bottom;
        background-color: transparent;
        padding: 0;
        color: currentColor;
    }

    .theme-color {
        color: #fc5356;
    }

    .dark-color {
        color: #20247b;
    }
</style>