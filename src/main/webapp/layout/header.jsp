<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${title}</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-icons.css">
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            font-size: 16px;
            color: #333;
            line-height: 1.6;
        }

        h1 {
            font-size: 2rem;
            font-weight: bold;
        }

        h2 {
            font-size: 1.75rem;
        }

        h3 {
            font-size: 1.5rem;
        }

        .dropdown-submenu {
            position: relative;
        }

        .dropdown-submenu > .dropdown-menu {
            top: 0;
            left: 100%;
            margin-top: 0;
            display: none;
            position: absolute;
        }

        .dropdown-submenu:hover > .dropdown-menu {
            display: block;
        }
    </style>
</head>
<body>
<jsp:include page="navbar.jsp" />
<div class="container mt-3 d-flex flex-column min-vh-100">