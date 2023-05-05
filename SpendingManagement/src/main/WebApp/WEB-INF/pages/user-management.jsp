<%-- 
    Document   : user-management
    Created on : Apr 25, 2023, 4:15:37 PM
    Author     : trant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
    <h1 class="text-center dark-color">TRANG QUẢN LÝ CHI TIÊU CÁ NHÂN</h1>
    <section class="d-flex justify-content-center">
        <form class="mt-3 w-50">
            <div class="form-group">
                <label for="title">Tên</label>
                <input class="form-control" name="name" id="title" value="${name}">
            </div>
        </form>
    </section>
</div>