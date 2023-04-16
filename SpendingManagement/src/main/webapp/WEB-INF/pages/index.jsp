<%-- 
    Document   : index
    Created on : Mar 19, 2023, 3:01:24 PM
    Author     : trant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
    <div id="demo" class="carousel slide" data-ride="carousel" >

        <!-- Indicators -->
        <ul class="carousel-indicators">
            <li data-target="#demo" data-slide-to="0" class="active"></li>
            <li data-target="#demo" data-slide-to="1"></li>
            <li data-target="#demo" data-slide-to="2"></li>
        </ul>

        <!-- The slideshow -->
        <div class="carousel-inner">
            <div class="carousel-item active img-set">
                <img src="https://res.cloudinary.com/cloudybeauty/image/upload/v1681673408/pexels-christina-morillo-1181595_uqsjo2.jpg" alt="...">
            </div>
            <div class="carousel-item img-set">
                <img src="https://res.cloudinary.com/cloudybeauty/image/upload/v1681673408/pexels-pixabay-416405_br2555.jpg" alt="...">
            </div>
            <div class="carousel-item img-set">
                <img src="https://res.cloudinary.com/cloudybeauty/image/upload/v1681673408/pexels-ketut-subiyanto-4474033_g2faqv.jpg" alt="...">
            </div>
        </div>

        <!-- Left and right controls -->
        <a class="carousel-control-prev" href="#demo" data-slide="prev">
            <span class="carousel-control-prev-icon"></span>
        </a>
        <a class="carousel-control-next" href="#demo" data-slide="next">
            <span class="carousel-control-next-icon"></span>
        </a>
    </div>
    
    <br/>
    <a class="nav-link home-main" href="<c:url value="/login" />">
        Đăng nhập ngay thôi!
    </a>
</div>

