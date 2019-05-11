<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="CN">
<head>

  <!-- SITE TITTLE -->
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>寝室小卖部</title>
  
  <!-- PLUGINS CSS STYLE -->
  <link href="plugins/jquery-ui/jquery-ui.min.css" rel="stylesheet">
  <!-- Bootstrap -->
  <link href="plugins/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
  <!-- Font Awesome -->
  <link href="plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet">
  <!-- Owl Carousel -->
  <link href="plugins/slick-carousel/slick/slick.css" rel="stylesheet">
  <link href="plugins/slick-carousel/slick/slick-theme.css" rel="stylesheet">
  <!-- Fancy Box -->
  <link href="plugins/fancybox/jquery.fancybox.pack.css" rel="stylesheet">
  <link href="plugins/jquery-nice-select/css/nice-select.css" rel="stylesheet">
  <link href="plugins/seiyria-bootstrap-slider/dist/css/bootstrap-slider.min.css" rel="stylesheet">
  <!-- CUSTOM CSS -->
  <link href="css/style.css" rel="stylesheet">

  <!-- FAVICON -->
  <link href="img/favicon.png" rel="shortcut icon">

  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

</head>
<script type="text/javascript">
function info(){
	$('#userinfo').form('submit',{
		success: function(){
		}
	});
}

</script>

<body class="body-wrapper">


<c:if test="${! empty loginedClient}">
<jsp:include page="head/loginedhead.jsp"></jsp:include>
</c:if>
<c:if test="${ empty loginedClient}">
<jsp:include page="head/head.jsp"></jsp:include>
</c:if>
<c:if test="${! empty message}">
<script>alert("${message}")</script>
</c:if>
<!--==================================
=            User Profile            =
===================================-->

<section class="user-profile section">
	<div class="container">
		<div class="row">
			<div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0">
				<div class="sidebar">
					<!-- User Widget -->
					<div class="widget user-dashboard-profile">
						<!-- User Image -->
						<div class="profile-thumb">
							<img src="${loginedClient.headpic}" alt="" class="rounded-circle">
						</div>
						
						<!-- User Name -->
						<h5 class="text-center">${loginedClient.clientname}</h5>
					</div>
					<!-- Dashboard Links -->
					<div class="widget user-dashboard-menu">
						<ul>
							<li>
								<a href="dashboard-my-ads.html"><i class="fa fa-user"></i> My Ads</a></li>
							<li>
								<a href="dashboard-favourite-ads.html"><i class="fa fa-bookmark-o"></i> Favourite Ads <span>5</span></a>
							</li>
							<li>
								<a href="dashboard-archived-ads.html"><i class="fa fa-file-archive-o"></i>Archeved Ads <span>12</span></a>
							</li>
							<li>
								<a href="dashboard-pending-ads.html"><i class="fa fa-bolt"></i> Pending Approval<span>23</span></a>
							</li>
							<li>
								<a href="logout.html"><i class="fa fa-cog"></i> Logout</a>
							</li>
							<li>
								<a href="delete-account.html"><i class="fa fa-power-off"></i>Delete Account</a>
							</li>
						</ul>
					</div>
				</div>
			</div>
			<div class="col-md-10 offset-md-1 col-lg-8 offset-lg-0">
				<!-- Edit Personal Info -->
				<div class="widget personal-info">
					<h3 class="widget-header user">编辑个人信息</h3>
						
						<form:form action="/editinfo" method="post" id="userinfo" modelAttribute="clientinfo" enctype="multipart/form-data" >
						<!-- Last Name -->
						<form:input type="hidden" path="clientid" value="${loginedClient.clientid}" />
						<form:input path="headpic" type="hidden" value="${loginedClient.headpic}"/>
						<div class="form-group">
						    <label for="name">姓名</label>
						<form:input type="text" class="form-control" value="${loginedClient.clientname}" path="clientname"/>
						</div>
						<!-- File chooser -->
						<div class="form-group choose-file">
							<i class="fa fa-user text-center"></i>
						    <input type="file" class="form-control-file d-inline" id="file" name="file"/>
						 </div>
					
					
						<!-- Comunity Name -->
						<div class="form-group">
						    <label for="comunity-name">送货地址</label>
						    <form:input type="text" class="form-control" id="address" path="address"/>
						</div>
						<button class="btn btn-transparent" onclick="info()">保存更改</button>
						</form:form>
					</div>
				<!-- Change Password -->
				<div class="widget change-password">
					<h3 class="widget-header user">修改密码</h3>
					<form action="#" method="post" >
						<!-- Current Password -->
						<div class="form-group">
						    <label for="current-password">当前密码</label>
						    <input type="password" class="form-control" id="current-password">
						</div>
						<!-- New Password -->
						<div class="form-group">
						    <label for="new-password">新密码</label>
						    <input type="password" class="form-control" id="new-password">
						</div>
						<!-- Confirm New Password -->
						<div class="form-group">
						    <label for="confirm-password">确认新密码</label>
						    <input type="password" class="form-control" id="confirm-password">
						</div>
						<!-- Submit Button -->
						<button class="btn btn-transparent">更改密码</button>
					</form>
				</div>
				
				<!-- Change Email Address -->
				<div class="widget change-email mb-0">
					<h3 class="widget-header user">修改手机号码</h3>
					<form action="#">
						<!-- Current Password -->
						<div class="form-group">
						    <label for="current-email">当前手机号码</label>
						    <input type="email" class="form-control" id="current-email">
						</div>
						<!-- New email -->
						<div class="form-group">
						    <label for="new-email">新手机号码</label>
						    <input type="email" class="form-control" id="new-email">
						</div>
						<!-- Submit Button -->
						<button class="btn btn-transparent">修改手机号码</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</section>

<!--============================
=            Footer            =
=============================-->

<footer class="footer section section-sm">
  <!-- Container Start -->
  <div class="container">
    <div class="row">
      <div class="col-lg-3 col-md-7 offset-md-1 offset-lg-0">
        <!-- About -->
        <div class="block about">
          <!-- footer logo -->
          <img src="images/logo-footer.png" alt="">
          <!-- description -->
          <p class="alt-color">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
        </div>
      </div>
      <!-- Link list -->
      <div class="col-lg-2 offset-lg-1 col-md-3">
        <div class="block">
          <h4>Site Pages</h4>
          <ul>
            <li><a href="#">Boston</a></li>
            <li><a href="#">How It works</a></li>
            <li><a href="#">Deals & Coupons</a></li>
            <li><a href="#">Articls & Tips</a></li>
            <li><a href="#">Terms of Services</a></li>
          </ul>
        </div>
      </div>
      <!-- Link list -->
      <div class="col-lg-2 col-md-3 offset-md-1 offset-lg-0">
        <div class="block">
          <h4>Admin Pages</h4>
          <ul>
            <li><a href="#">Boston</a></li>
            <li><a href="#">How It works</a></li>
            <li><a href="#">Deals & Coupons</a></li>
            <li><a href="#">Articls & Tips</a></li>
            <li><a href="#">Terms of Services</a></li>
          </ul>
        </div>
      </div>
      <!-- Promotion -->
      <div class="col-lg-4 col-md-7">
        <!-- App promotion -->
        <div class="block-2 app-promotion">
          <a href="">
            <!-- Icon -->
            <img src="images/footer/phone-icon.png" alt="mobile-icon">
          </a>
          <p>Get the Dealsy Mobile App and Save more</p>
        </div>
      </div>
    </div>
  </div>
  <!-- Container End -->

<jsp:include page="head/footer.jsp"></jsp:include>

  <!-- JAVASCRIPTS -->
  <script src="plugins/jquery/jquery.min.js"></script>
  <script src="plugins/jquery-ui/jquery-ui.min.js"></script>
  <script src="plugins/tether/js/tether.min.js"></script>
  <script src="plugins/raty/jquery.raty-fa.js"></script>
  <script src="plugins/bootstrap/dist/js/popper.min.js"></script>
  <script src="plugins/bootstrap/dist/js/bootstrap.min.js"></script>
  <script src="plugins/seiyria-bootstrap-slider/dist/bootstrap-slider.min.js"></script>
  <script src="plugins/slick-carousel/slick/slick.min.js"></script>
  <script src="plugins/jquery-nice-select/js/jquery.nice-select.min.js"></script>
  <script src="plugins/fancybox/jquery.fancybox.pack.js"></script>
  <script src="plugins/smoothscroll/SmoothScroll.min.js"></script>
  
  <script src="js/scripts.js"></script>

</body>

</html>