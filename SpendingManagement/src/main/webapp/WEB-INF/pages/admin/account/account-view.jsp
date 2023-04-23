<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
    <br/>
    <h1 class="text-center text-info" style="padding: 20px 30px">THÔNG TIN TÀI KHOẢN ID: ${userDetail.id}</h1>
    <section class="section about-section gray-bg" id="about">
        <div class="container m-0">
            <div class="row flex-row-reverse">
                <div class="col-lg-8">
                    <div class="about-text go-to">
                        <h3 class=" mb-4">
                            ${userDetail.fullname}
                        </h3>
                        <div class="row mb-2">
                            <div class="col-md-5">
                                <h5>Email</h5>
                            </div>
                            <div class="col-md-7">
                                <p>${userDetail.email}</p>
                            </div>
                        </div>
                        <div class="row mb-2">
                            <div class="col-md-5">
                                <h5>Số điện thoại</h5>
                            </div>
                            <div class="col-md-7">
                                <p>${userDetail.phone}</p>
                            </div>
                        </div>
                            <div class="row mb-2">
                            <div class="col-md-5">
                                <h5>Username</h5>
                            </div>
                            <div class="col-md-7">
                                <p>${userDetail.username}</p>
                            </div>
                        </div>  
                            <div class="row mb-2">
                            <div class="col-md-5">
                                <h5>Password</h5>
                            </div>
                            <div class="col-md-7">
                                <p>${userDetail.password}</p>
                            </div>
                        </div>  
                            <div class="row mb-2">
                            <div class="col-md-5">
                                <h5>Trạng thái</h5>
                            </div>
                            <div class="col-md-7">
                                <p>${userDetail.active.statusName}</p>
                            </div>
                        </div>  
                            <div class="row mb-2">
                            <div class="col-md-5">
                                <h5>Loại tài khoản</h5>
                            </div>
                            <div class="col-md-7">
                                <p>${userDetail.userRole.role}</p>
                            </div>
                        </div>  
                            <div class="row mb-2">
                            <div class="col-md-5">
                                <h5>Ngày tạo</h5>
                            </div>
                            <div class="col-md-7">
                                <p>${userDetail.joinedDate}</p>
                            </div>
                        </div>  
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="about-avatar d-flex justify-content-center">
                        <c:if test="${userDetail.avatar.startsWith('https')}">
                            <img src="<c:url value="${userDetail.avatar}"/>" class="rounded">
                        </c:if>
                        <c:if test="${!userDetail.avatar.startsWith('https')}">
                            <img src="<c:url value="/resources/images/none.png"/>" class="rounded">
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <br/>
</div>


<style>
    .gray-bg {
        background-color: #f5f5f5;
    }

    img {
        max-width: 90%;
    }

    img {
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
