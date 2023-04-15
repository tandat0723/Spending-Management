<%-- 
    Document   : register
    Created on : Apr 4, 2023, 4:04:16 PM
    Author     : trant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:url value="/register" var="action"/>

<div class="container m-auto" style="background: none">
    <div class="card o-hidden border-0 shadow-lg my-5">
        <div class="card-body p-0">
            <div class="row">
                <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                <div class="col-lg-7">
                    <div class="p-5">
                        <div class="text-center">
                            <h1 class="h4 text-gray-900 mb-4">Đăng ký</h1>
                        </div>
                        <form:form action="${action}" method="post" class="user" enctype="multipart/form-data"
                                   modelAttribute="user">
                            <form:errors path="*" element="div" class="alert alert-danger mt-3"/>
                            <form:hidden path="id"/>
                            <form:hidden path="avatar"/>
                            <div class="form-group">
                                <form:input path="fullname" class="form-control form-control-user"
                                            placeholder="Tên của bạn" required="required"/>
                            </div>
                            <div class="form-group">
                                <form:input path="username" class="form-control form-control-user"
                                            placeholder="Tên đăng nhập" required="required"/>
                            </div>
                            <div class="form-group">
                                <form:input path="password" class="form-control form-control-user"
                                            id="password" type="password"
                                            placeholder="Mật khẩu" required="required"/>
                            </div>
                            <div class="form-group">
                                <form:input path="confirmPassword" class="form-control form-control-user"
                                            id="confirmPassword" type="password"
                                            placeholder="Nhập lại mật khẩu" required="required"/>
                            </div>
                            <div class="form-group">
                                <form:input path="email" class="form-control form-control-user"
                                            type="email"
                                            placeholder="Email" required="required"/>
                            </div>
                            <div class="form-group">
                                <form:input path="phone" class="form-control form-control-user"
                                            placeholder="Số điện thoại" required="required"/>
                            </div>
                            <div class="form-group">
                                <label>Loại tài khoản</label>
                                <form:select path="userRole" class="custom-select">
                                    <form:option value="ROLE_USER" label="Người dùng"
                                                 selected="${user.userRole.equals('ROLE_USER') ? true : ''}"/>
                                </form:select>
                            </div>
                            <div class="form-group row align-items-center">
                                <div class="col">
                                    <a onclick="AvatarBrowse()" class="btn btn-primary btn-user btn-block">
                                        Chọn ảnh đại diện
                                    </a>
                                    <form:input type="file" id="avatarBrowse" path="file"
                                                onchange="showPreviewDiv(event);"
                                                accept="image/*" class="form-control" cssStyle="display: none"/>
                                </div>
                                <div class="col text-center">
                                    <div class="rounded-circle m-auto" id="img-preview"
                                         style="background-image: url('<c:url value="/resources/images/none.png"/>');
                                         width: 150px; height: 150px;
                                         background-position: center;
                                         background-size: contain;
                                         background-repeat: no-repeat;
                                         border-radius: .35rem;
                                         border: 1px solid lightgray">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group mt-4">
                                <button type="submit" class="btn btn-primary btn-user btn-block">
                                    Đăng ký
                                </button>
                            </div>
                            <div class="text-center">
                                <a href="<c:url value="/forgot-password" />">Quên mật khẩu</a>
                            </div>
                            <div class="text-center">
                                Đã có tài khoản 
                                <a href="<c:url value="/login" />">Đăng nhập</a>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="<c:url value="/resources/vendor/jquery/jquery.min.js"/>"></script>
<script src="<c:url value="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"/>"></script>
<script src="<c:url value="/resources/vendor/jquery-easing/jquery.easing.min.js"/>"></script>
<script src="<c:url value="/resources/js/sb-admin-2.min.js"/>"></script>
<script src="<c:url value="/resources/js/register.js"/>"></script>

