<%-- 
    Document   : register
    Created on : Apr 4, 2023, 4:04:16 PM
    Author     : trant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

