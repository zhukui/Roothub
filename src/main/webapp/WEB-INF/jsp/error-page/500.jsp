<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String appPath = request.getContextPath(); %>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"></c:set>
<html>
<head>
  <title>roothub-出错啦</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <!-- 引入 Bootstrap -->
  <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
  <link href="/resources/css/app.css" rel="stylesheet" type="text/css">
  <link rel="shortcut icon" href="/resources/images/favicon.ico">
</head>
<body>
<div class="container">
  <br>
  <br>
  <div class="panel panel-default">
    <div class="panel-body">
      <!-- <h1>: (</h1> -->
      <h3>╮(╯▽╰)╭</h3>
      <p>这个页面登录之后才能操作哦~点击去<a href="/login">登录</a></p>
    </div>
  </div>
</div>
</body>
</html>