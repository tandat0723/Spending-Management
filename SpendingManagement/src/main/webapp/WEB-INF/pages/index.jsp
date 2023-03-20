<%-- 
    Document   : index
    Created on : Mar 19, 2023, 3:01:24 PM
    Author     : trant
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trang chủ</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous"><!-- comment -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </head>
    <body>
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
                                <a class="nav-link" href="#">Trang chủ</a>
                            </li>
                            <c:forEach items="${categories}" var="c">
                                <li class="nav-item">
                                    <a class="nav-link" href="#">${c.name}</a>
                                </li>
                            </c:forEach>
                        </ul>
                        <c:url value="/" var="action"/>
                        <form class="d-flex" action="${action}">
                            <input class="form-control me-2" type="text" name="kw" placeholder="Nhập thông tin">
                            <button class="btn btn-primary" type="button" style="width: 8rem;">Tìm kiếm</button>
                        </form>
                    </div>
                </div>
            </nav>
        </header>
        <section class="container">
            <div class="row">
                <c:forEach items="${subcategories}" var="s">
                    <div class="col-md-3 col-xs-12" style="padding: 1rem;">
                        <div class="card" >
                            <img class="card-img-top" src="${s.image}" alt="${s.name}">
                            <div class="card-body">
                                <h4 class="card-title">${s.name}</h4>
                            </div>
                        </div>
                    </div>
                </c:forEach>

            </div>
        </section>
        <footer>
            <div class="mt-4 p-5 bg-primary text-white rounded">
                <h1>QUẢN LÝ CHI TIÊU</h1>
                <p>Tấn Đạt & Phú An &copy; 2023</p>
            </div>
        </footer>
    </body>
</html>
