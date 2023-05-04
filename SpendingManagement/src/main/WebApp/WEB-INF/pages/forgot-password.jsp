<%-- 
    Document   : forgot-password
    Created on : Apr 5, 2023, 8:59:50 PM
    Author     : trant
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="custom-wrapper">
    <div class="container m-auto" style="background: none">
        <div class="row justify-content-center">
            <div class="col-xl-10 col-lg-12 col-md-9">
                <div class="card o-hidden border-0 shadow-lg my-5">
                    <div class="card-body p-0">
                        <div class="row">
                            <div class="col-lg-6 d-none d-lg-block bg-forgot-password-image"></div>
                            <div class="col-lg-6">
                                <div class="p-5">
                                    <div class="text-center">
                                        <h1 class="h4 text-gray-900 mb-2">Lấy lại mật khẩu</h1>
                                        <p class="mb-4">Nhập địa chỉ email đã dùng đăng ký tài khoản của bạn vào đây. Chúng tôi sẽ hỗ trợ bạn đổi lại mật khẩu!</p>
                                    </div>
                                    <form class="user">
                                        <div class="form-group">
                                            <input type="email" class="form-control form-control-user"
                                                   id="exampleInputEmail" aria-describedby="emailHelp"
                                                   placeholder="Nhập email">
                                        </div>
                                        <a href="<c:url value="/login" />" class="btn btn-primary btn-user btn-block">
                                            Đổi mật khẩu
                                        </a>
                                    </form>
                                    <hr>
                                    <div class="text-center">
                                        <a class="small" href="<c:url value="/register" />">Tạo mới tài khoản</a>
                                    </div>
                                    <div class="text-center">
                                        Đã có tài khoản!
                                        <a class="small" href="<c:url value="/login" />">Đăng nhập!</a>
                                    </div>
                                </div>
                            </div>
                        </div>
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
<script src="<c:url value="/resources/js/change-password.js"/>"></script>