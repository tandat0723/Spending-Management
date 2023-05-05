<%-- 
    Document   : base-admin
    Created on : Apr 9, 2023, 8:05:36 PM
    Author     : trant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
            <tiles:insertAttribute name="title"/>
        </title>

        <link href="<c:url value="/resources/css/sb-admin-2.min.css" />" rel="stylesheet"/>
        <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.2/css/all.min.css"/>
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet"/>
        <script src="<c:url value="/resources/js/sb-admin-2.min.js" />"></script>
        <script src="<c:url value="/resources/vendor/chart.js/Chart.min.js" />"></script>
        <script src="<c:url value="/resources/js/chart-area-demo.js" />"></script>
        <script src="<c:url value="/resources/js/chart-pie-demo.js" />"></script>
        <script src="<c:url value="/resources/js/main.js" />"></script>
    </head>
    <body id="page-top">
        <div id="wrapper">
            <tiles:insertAttribute name="sidebar"/>
            <div id="content-wrapper" class="d-flex flex-column">
                <div id="content">
                    <tiles:insertAttribute name="topbar"/>
                    <div class="container-fluid">
                        <tiles:insertAttribute name="content"/>
                    </div>
                    <tiles:insertAttribute name="footer"/>
                </div>
            </div>
        </div>
        <script src="<c:url value="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js" />"></script>
        <script src="<c:url value="/resources/vendor/jquery-easing/jquery.easing.min.js" />"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.3/moment-with-locales.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script> 
        <script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
        <script src="https://unpkg.com/xml2js"></script>
        <script src="<c:url value="/resources/js/admin/admin-js.js" />"></script>
    </body>
</html>

