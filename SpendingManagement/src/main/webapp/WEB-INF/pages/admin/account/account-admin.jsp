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
            <th class="th-sort"><a href="javascript:;" onclick="sortColumn(this)">Full Name<span class="sort-icon"></span></a></th>
            <th class="th-sort"><a href="javascript:;" onclick="sortColumn(this)">Email<span class="sort-icon"></span></a></th>
            <th class="th-sort"><a href="javascript:;" onclick="sortColumn(this)">Phone<span class="sort-icon"></span></a></th>
            <th class="th-sort"><a href="javascript:;" onclick="sortColumn(this)">Username<span class="sort-icon"></span></a></th>
            <th class="th-sort"><a href="javascript:;" onclick="sortColumn(this)">Password<span class="sort-icon"></span></a></th>
            <th class="th-sort"><a href="javascript:;" onclick="sortColumn(this)">Active<span class="sort-icon"></span></a></th>
            <th class="th-sort"><a href="javascript:;" onclick="sortColumn(this)">User Role<span class="sort-icon"></span></a></th>
            <th class="th-sort"><a href="javascript:;" onclick="sortColumn(this)">Created Date<span class="sort-icon"></span></a></th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${users}" var="u">
            <tr id="user${u.id}">
                <td>
                    <img class="rounded-circle" src="${u.avatar}" width="50" />
                </td>
                <td>${u.id}</td>
                <td>${u.fullname}</td>
                <td>${u.email}</td>
                <td>${u.phone}</td>
                <td>${u.username}</td>
                <td>${u.password}</td>
                <td>${u.active.statusName}</td>
                <td>${u.userRole.role}</td>
                <td>${u.joinedDate}</td>
                <td>
                    <a href="<c:url value="/admin/account-admin/${u.id}" />" style="color: #00bbb3; margin-right: 5px;"><i class="fas fa-pen"></i></a>
                    <div id="spinner${u.id}" style="display:none" class="spinner-border spinner-border-sm"></div>
                    <c:url value="/api/users/${u.id}" var="endpoint" />
                    <a id="delete${u.id}" href="javascript:;" style="color: red;" onclick="deleteUser('${endpoint}', ${u.id})"><i class="fas fa-trash"></i></a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<hr/>
<c:if test="${errMsg != null}">
    <div class="alert alert-danger">${errMsg}</div>
</c:if>

<c:url value="/admin/account-admin" var="action" />
<form:form method="POST" action="${action}"
           modelAttribute="user" enctype="multipart/form-data">

    <form:errors path="*" element="div" cssClass="alert alert-danger" />
    <div class="row">
        <div class="col-md-6">
            <div class="form-floating mb-3 mt-3">
                <label for="fullname">Họ và Tên</label>
                <form:input class="form-control" id="fullname" placeholder="Full Name" path="fullname" name="fullname" />
            </div>
            <div class="form-floating mb-3 mt-3">
                <label for="email">Email</label>
                <form:input class="form-control" id="email" placeholder="Email" path="email" name="email" />
            </div>
            <div class="form-floating mb-3 mt-3">
                <label for="phone">Số điện thoại</label>
                <form:input class="form-control" id="phone" placeholder="Phone" path="phone" name="phone" />
            </div>
            <div class="form-floating mb-3 mt-3">
                <label for="file">Avatar</label>
                <form:input type="file" class="form-control" id="file" path="file" name="file" onchange="previewImage(this);" />
            </div>
            <div class="form-floating mb-3 mt-3" id="preview-container" style="display: none;">
                <img id="preview" src="" width="120" />
            </div>
            <c:if test="${user.avatar != null && user.avatar != ''}">
                <div class="form-floating mb-3 mt-3" id="avatar-old">
                    <img src="${user.avatar}" width="120" />
                </div>
            </c:if>
        </div>
        <div class="col-md-6">
            <div class="form-floating mb-3 mt-3">
                <label for="username">Tên đăng nhập</label>
                <form:input class="form-control" id="username" placeholder="Username" path="username" name="username" />
            </div>
            <div class="form-floating mb-3 mt-3">
                <label for="password">Mật khẩu</label>
                <form:password class="form-control" id="password" placeholder="Password" path="password" name="password" />
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

    <div class="row d-flex justify-content-center align-items-center">
        <c:choose>
            <c:when test="${user.id > 0}">
                <form:hidden path="id" />
                <form:hidden path="avatar" />
                <input type="submit" value="Cập nhật thông tin" class="btn btn-info" />
            </c:when>
            <c:otherwise>
                <input type="submit" value="Thêm người dùng" class="btn btn-success" />
            </c:otherwise>
        </c:choose>

    </div>
</form:form>