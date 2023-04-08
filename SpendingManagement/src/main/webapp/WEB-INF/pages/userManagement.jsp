<%-- 
    Document   : userManagement
    Created on : Apr 7, 2023, 5:10:36 PM
    Author     : phuan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
    .th-sort {
    cursor: pointer;
    text-align: center;
}
</style>
<h1 class="text-info text-center">Quản lý người dùng</h1>

<table class="table table-dark table-hover">
    <tr>
        <th></th>
        <th class="th-sort">User ID</th>
        <th class="th-sort">Last Name</th>
        <th class="th-sort">First Name</th>
        <th class="th-sort">Email</th>
        <th class="th-sort">Phone</th>
        <th class="th-sort">Username</th>
        <th class="th-sort">Password</th>
        <th class="th-sort">Active</th>
        <th class="th-sort">User Role</th>
        <th class="th-sort">Created Date</th>
        <th></th>
    </tr>
    <c:forEach items="${users}" var="u">
        <tr id="user${u.id}">
            <td>
                <img class="rounded-circle" src="${u.avatar}" width="50" />
            </td>
            <td>${u.id}</td>
            <td>${u.lastName}</td>
            <td>${u.firstName}</td>
            <td>${u.email}</td>
            <td>${u.phone}</td>
            <td>${u.username}</td>
            <td>${u.password}</td>
            <td>${u.active}</td>
            <td>${u.userRole}</td>
            <td>${u.joinedDate}</td>
            <td>
                <a href="<c:url value="/admin/users/${u.id}" />" style="color: #00bbb3; margin-right: 5px;"><i class="fas fa-pen"></i></a>
                <div id="spinner${u.id}" style="display:none" class="spinner-border spinner-border-sm"></div>
                <c:url value="/api/users/${u.id}" var="endpoint" />
                <a id="delete${u.id}" href="javascript:;" style="color: red;" onclick="deleteUser('${endpoint}', ${u.id})"><i class="fas fa-trash"></i></a>
            </td>
        </tr>
    </c:forEach>
</table>

<c:if test="${errMsg != null}">
    <div class="alert alert-danger">${errMsg}</div>
</c:if>
<c:url value="/admin/users" var="action" />
<form:form method="POST" action="${action}"
           modelAttribute="user" enctype="multipart/form-data">

    <form:errors path="*" element="div" cssClass="alert alert-danger" />
    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control" id="username" placeholder="Username" path="username" name="username" />
        <label for="name">Tên đăng nhập</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control" id="firstname" placeholder="FirstName" path="firstName" name="firstname" />
        <label for="name">Tên</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:password class="form-control" id="password" placeholder="Password" path="password" name="password" />
        <label for="price">Mật khẩu</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="file" class="form-control" id="avatar" path="avatar" name="avatar" />
        <label for="avatar">Avatar</label>
    </div>
    <c:if test="${user.avatar != null && user.avatar != ''}">
        <div class="form-floating mb-3 mt-3">
            <img src="${user.avatar}" width="120" />
        </div>
    </c:if>
    <div class="form-floating mt-2">
        <c:choose>
            <c:when test="${user.id > 0}">
                <form:hidden path="id" />
                <form:hidden path="avatar" />
                <input type="submit" value="Cập nhật thông tin" class="btn btn-success" />
            </c:when>
            <c:otherwise>
                <input type="submit" value="Thêm người dùng" class="btn btn-success" />
            </c:otherwise>
        </c:choose>

    </div>
</form:form>

<script src="<c:url value="/js/userManagement.js" />"></script>
<script>
                    $(document).ready(function () {
                        // Bắt sự kiện click trên tiêu đề cột để sắp xếp
                        $('.th-sort').click(function () {
                            var table = $(this).parents('table').eq(0)
                            var rows = table.find('tr:gt(0)').toArray().sort(compare($(this).index()))
                            this.asc = !this.asc
                            // Xóa kí hiệu lên hoặc xuống trên các tiêu đề cột khác và cột hiện tại
                            $(this).siblings().addBack().each(function () {
                                $(this).html($(this).html().replace(/ <i class="fas fa-arrow-up"><\/i>| <i class="fas fa-arrow-down"><\/i>/g, ''))
                            })
                            if (!this.asc) {
                                rows = rows.reverse()
                                $(this).html($(this).html() + ' <i class="fas fa-arrow-down"></i>')
                            } else {
                                $(this).html($(this).html() + ' <i class="fas fa-arrow-up"></i>')
                            }
                            for (var i = 0; i < rows.length; i++) {
                                table.append(rows[i])
                            }
                        })

                    })

                    function compare(index) {
                        return function (a, b) {
                            var valA = getCellValue(a, index)
                            var valB = getCellValue(b, index)
                            return $.isNumeric(valA) && $.isNumeric(valB) ? valA - valB : valA.toString().localeCompare(valB)
                        }
                    }

                    function getCellValue(row, index) {
                        return $(row).children('td').eq(index).text()
                    }

</script>