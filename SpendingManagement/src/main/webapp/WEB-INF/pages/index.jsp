<%-- 
    Document   : index
    Created on : Mar 19, 2023, 3:01:24 PM
    Author     : trant
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<section class="container">
    <div class="row">
        <c:forEach items="${subcategories}" var="s">
            <div class="col-md-3 col-xs-12" style="padding: 1rem;">
                <div class="card">
                    <img class="card-img-top" src="${s.image}" alt="${s.name}">
                    <div class="card-body">
                        <h4 class="card-title">${s.name}</h4>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</section>

