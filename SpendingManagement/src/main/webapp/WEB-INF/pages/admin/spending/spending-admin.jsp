<%-- 
    Document   : spending-admin
    Created on : Apr 19, 2023, 10:56:42 AM
    Author     : phuan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1 class="text-center text-info">Quản Lý Chi Tiêu Cá Nhân</h1>
<table class="table">
    <thead>
        <tr>
            <th class="th-sort"><a href="javascript:;" onclick="sortColumn(this)">ID<span class="sort-icon"></span></a></th>
            <th class="th-sort"><a href="javascript:;" onclick="sortColumn(this)">Người chi tiêu<span class="sort-icon"></span></a></th>
            <th class="th-sort"><a href="javascript:;" onclick="sortColumn(this)">Loại giao dịch<span class="sort-icon"></span></a></th>
            <th class="th-sort"><a href="javascript:;" onclick="sortColumn(this)">Mục đích<span class="sort-icon"></span></a></th>
            <th class="th-sort"><a href="javascript:;" onclick="sortColumn(this)">Mô tả chi tiết<span class="sort-icon"></span></a></th>
            <th class="th-sort"><a href="javascript:;" onclick="sortColumn(this)">Số tiền<span class="sort-icon"></span></a></th>
            <th class="th-sort"><a href="javascript:;" onclick="sortColumn(this)">Ngày tạo<span class="sort-icon"></span></a></th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${personalTransaction}" var="p">
            <tr id="personalTransaction${p.id}">
                <td class="overflow-hidden">${p.id}</td>
                <td class="overflow-hidden">${p.userId.fullname}</td>
                <td class="overflow-hidden">${p.transactionType.typename}</td>
                <td class="overflow-hidden">${p.purpose}</td>
                <td class="overflow-hidden">${p.description}</td>
                <td class="overflow-hidden">${p.price} VND</td>
                <td class="overflow-hidden">${p.date}</td>
                <td>
                    <a href="<c:url value="/admin/spending-admin/view/${p.id}" />" style="color: blue; margin-right: 5px;"><i class="fas fa-eye"></i></a>
                    <a href="<c:url value="/admin/spending-admin/${p.id}" />" style="color: #00bbb3; margin-right: 5px;"><i class="fas fa-pen"></i></a>
                    <div id="spinner${p.id}" style="display:none" class="spinner-border spinner-border-sm"></div>
                    <c:url value="/api/spending/${p.id}" var="endpoint" />
                    <a id="delete${u.id}" href="javascript:;" style="color: red;" onclick="deleteSpending('${endpoint}', ${p.id})"><i class="fas fa-trash"></i></a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<hr/>
<c:if test="${errMsg != null}">
    <div class="alert alert-danger">${errMsg}</div>
</c:if>
<c:if test="${sucMsg != null}">
    <div class="alert alert-success">${sucMsg}</div>
</c:if>

<c:url value="/admin/spending-admin" var="action" />
<form:form method="POST" action="${action}"
           modelAttribute="personal">
    <c:choose>
            <c:when test="${personal.id > 0}">
                <h3 class="text-center text-primary">Cập nhật chi tiêu</h3>
            </c:when>
            <c:otherwise>
                <h3 class="text-center text-success">Thêm chi tiêu mới</h3>
            </c:otherwise>
        </c:choose>
    <form:errors path="*" element="div" cssClass="alert alert-danger" />
    <div class="row">
        <div class="col-md-6">
            <form:hidden path="id" />
            <div class="form-floating mb-3 mt-3">
                <label for="userId">Người chi tiêu</label>
                <form:select class="form-control" id="userId" name="userId" path="userId">
                    <c:forEach items="${userId}" var="u">
                        <c:choose>
                            <c:when test="${personal.userId.id == u.id}">
                                <option value="${u.id}" selected>${u.fullname}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${u.id}">${u.fullname}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </form:select>
            </div>
            <div class="form-floating mb-3 mt-3">
                <label for="transactionType">Loại giao dịch</label>
                <form:select class="form-control" id="transactionType" name="transactionType" path="transactionType">
                    <c:forEach items="${transactionTypes}" var="t">
                        <c:choose>
                            <c:when test="${personal.transactionType.id == t.id}">
                                <option value="${t.id}" selected>${t.typename}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${t.id}">${t.typename}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </form:select>
            </div>
            <div class="form-floating mb-3 mt-3">
                <label for="purpose">Mục đích</label>
                <form:input class="form-control" id="purpose" placeholder="Mục đích" path="purpose" name="purpose" />
            </div>
            <div class="form-floating mb-3 mt-3">
                <label for="price">Số tiền</label>
                <input type="number" class="form-control" id="price" path="price" name="price"/>
            </div>
        </div>
        <div class="col-md-6">
            <div class="form-floating mb-3 mt-3">
                <label for="description">Mô tả chi tiết</label>
                <form:textarea class="form-control" id="description" placeholder="Mô tả chi tiết" path="description" name="description" rows="5" cols="50" />
            </div>
        </div>
    </div>

    <div class="row d-flex justify-content-center align-items-center p-md-3">
        <c:choose>
            <c:when test="${personal.id > 0}">
                <input type="submit" value="Cập nhật chi tiêu" class="btn btn-info" />
            </c:when>
            <c:otherwise>
                <input type="submit" value="Thêm chi tiêu mới" class="btn btn-success" />
            </c:otherwise>
        </c:choose>

    </div>
</form:form>