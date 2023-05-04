<%-- 
    Document   : personal-transaction-add
    Created on : Apr 27, 2023, 11:00:31 PM
    Author     : trant
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value="/user/personal-transaction/add-or-update" var="action"/>

<c:if test="${personalTransaction.id == 0}">
    <h1 class="text-center dark-color">THÊM MỚI THÔNG TIN CHI TIÊU CÁ NHÂN</h1>
</c:if>

<form:form action="${action}" method="post" modelAttribute="personalTransaction">
    <form:errors path="*" element="div" cssClass="alert alert-danger mt-3"/>
    <form:hidden path="id"/>
    <div class="form-group">
        <label for="title">Tên tiêu đề</label>
        <form:input path="name" class="form-control" autofocus="autofocus" />
    </div>
    <div class="form-group">
        <label for="type">Tên</label>
        <form:input path="transactionType" class="form-control" />
    </div>
    <div class="form-group">
        <label for="purpose">Mục đích</label>
        <form:input path="purpose" class="form-control" />
    </div>
    <div class="form-group">
        <label for="description">Thông tin chi tiết</label>
        <form:input path="description" class="form-control" />
    </div>
    <div class="form-group">
        <label for="price">Tiền</label>
        <form:input path="price" class="form-control" />
    </div>

</form:form>
