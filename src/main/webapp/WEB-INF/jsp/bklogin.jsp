<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
    <!-- Google Chrome Frame也可以让IE用上Chrome的引擎: -->
    <meta name="renderer" content="webkit">
    <!--国产浏览器高速模式-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 定义页面的最新版本 -->
    <meta name="description" content="网站简介"/>
    <!-- 网站简介 -->
    <meta name="keywords" content="搜索关键字，以半角英文逗号隔开"/>
    <title>穷在闹市出品</title>

    <link rel="stylesheet" type="text/css" href="css/base.css">
    <link rel="stylesheet" type="text/css" href="css/login.css">
    <script type="text/javascript" src="framework/jquery-1.11.3.min.js"></script>
</head>
<body>
<form:form action="tobklogin" method="post" id="bkloginForm" modelAttribute="Admininfo">
    <div class="login_main">
        <div class="login_title">
			B5 Sell Management
        </div>
        <div class="login_account">
        	<form:input type="text"  path="adminName"></form:input>
			<form:errors path="adminName"></form:errors>
           <!--  <input type="text" placeholder="手机/邮箱/登录帐号"/> -->
        </div>
        <div class="login_password">
        	<form:input type="password" path="adminPwd"></form:input>
			<form:errors path="adminPwd"></form:errors>
            <%-- <input type="password" placeholder="密码（8-20位，区分大小写）"/> --%>
        </div>
        <div class="login_dis">
            <input type="checkbox"/>
            <p>下次自动登录</p><a href="#" target="_blank">忘记密码?</a>
        </div>
        <div class="login_submit">
           <!--  <a href="index.html">登&nbsp;录</a> -->
           <a onclick="document:bkloginForm.submit()">登&nbsp;录</a>
          <!--  <input type="submit" value="Login"> -->
        </div>
        <div class="login_register">
            <a href="" target="_blank">没有账号，免费注册</a>
        </div>
    </div>
</form:form>
</body>
</html>
