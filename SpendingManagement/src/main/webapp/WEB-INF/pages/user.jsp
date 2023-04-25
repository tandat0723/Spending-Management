<%-- 
    Document   : user-index
    Created on : Apr 9, 2023, 10:31:58 PM
    Author     : trant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
    <br/> 
    <h1 class="text-center dark-color">TRANG CHỦ NGƯỜI DÙNG</h1>
    <br/>
    <c:if test="${currentUser.active.id != 1}">
        <h4 class="text-danger text-center mt-3">NGƯỜI DÙNG CHƯA ĐƯỢC KÍCH HOẠT</h4>
    </c:if>
</div>
