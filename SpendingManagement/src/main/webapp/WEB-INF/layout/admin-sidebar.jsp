<%-- 
    Document   : admin-sidebar
    Created on : Apr 16, 2023, 11:11:50 PM
    Author     : trant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">
    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="<c:url value="/admin" />">
        <div class="sidebar-brand-icon">
            <i class="fa-solid fa-user-secret"></i>
        </div>
        <div class="sidebar-brand-text mx-3">Admin <sup>v2</sup></div>
    </a>

    <hr class="sidebar-divider my-0">

    <hr class="sidebar-divider">
    <div class="sidebar-heading">
        Quản lý
    </div>

    <li class="nav-item">
        <a class="nav-link" href="<c:url value="/admin/account" />">
            <i class="fa-solid fa-user"></i>
            <span>Tài khoản</span></a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="#">
            <i class="fas fa-fw fa-table"></i>
            <span>Tables</span></a>
    </li>
    <hr class="sidebar-divider">
    <div class="sidebar-heading">
        Thống kê
    </div>

    <li class="nav-item">
        <a class="nav-link" href="<c:url value="#" />">
            <div class="row">
                <div class="col-1">
                    <i class="fa-solid fa-user-check"></i>
                </div>
                <div class="col">
                    <span>Thống kê tin tuyển dụng</span>
                </div>
            </div>
        </a>
    </li>

    <hr class="sidebar-divider">
    <!-- Heading -->
    <div class="sidebar-heading">
        Interface
    </div>

    <hr class="sidebar-divider d-none d-md-block">

    <!-- Sidebar Toggler (Sidebar) -->
    <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
    </div>
</ul>
