<%-- 
    Document   : blank-base
    Created on : Apr 4, 2023, 2:53:17 PM
    Author     : trant
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>
        <tiles:insertAttribute name="title"/>
    </title>

    <link href="<c:url value="/resources/css/style.css"/> " rel="stylesheet"/>
    <link href="<c:url value="/resources/css/sb-admin-2.min.css"/>" rel="stylesheet"/>
</head>
<body>
    <tiles:insertAttribute name="content"/>
</body>
</html>
