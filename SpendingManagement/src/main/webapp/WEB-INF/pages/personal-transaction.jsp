<%-- 
    Document   : personal-transaction
    Created on : Apr 25, 2023, 4:15:37 PM
    Author     : trant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
    <h1 class="text-center dark-color">TRANG CHI TIÊU CÁ NHÂN</h1>
    <section class="d-flex justify-content-center">
        <form class="mt-3 w-50">
            <div class="form-group">
                <label for="title">Tên</label>
                <input class="form-control" name="name" id="title" value="${name}">
            </div>
            <div class="form-group">
                <label for="type">Loại chi tiêu</label>
                <input class="form-control" name="type" id="type" value="${type}">
            </div>
            <div class="form-group">
                <label for="purpose">Mục đích</label>
                <input class="form-control" name="purpose" id="purpose" value="${purpose}">
            </div>
            <div class="form-group">
                <label for="description">Thông tin chi tiết</label>
                <input class="form-control" name="description" id="description" value="${description}">
            </div>
            <div class="form-group">
                <label for="price">Tiền</label>
                <input class="form-control" name="price" id="price" value="${price}">
            </div>

            <div class="form-group">
                <label for="sort">Sắp xếp</label>
                <select class="form-control" name="sort" id="sort">
                    <option value="" selected>Không chọn</option>
                    <c:if test="${sort.equals('asc')}">
                        <option value="asc" selected>Ngày đăng cũ nhất</option>
                    </c:if>
                    <c:if test="${!sort.equals('asc')}">
                        <option value="asc">Ngày đăng cũ nhất</option>
                    </c:if>

                    <c:if test="${sort.equals('desc')}">
                        <option value="desc" selected>Ngày đăng mới nhất</option>
                    </c:if>
                    <c:if test="${!sort.equals('desc')}">
                        <option value="desc">Ngày đăng mới nhất</option>
                    </c:if>
                </select>
            </div>
        </form>
    </section>
</div>

<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body text-justify" id="modal-body"></div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
