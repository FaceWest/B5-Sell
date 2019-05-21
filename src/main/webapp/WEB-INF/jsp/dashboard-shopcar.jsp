<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="CN">
<head>

  <!-- SITE TITTLE -->
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>我的购物车</title>
  
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

<body class="body-wrapper">

<c:if test="${! empty loginedClient}">
<jsp:include page="head/loginedhead.jsp"></jsp:include>
</c:if>
<c:if test="${empty loginedClient}">
<jsp:include page="head/head.jsp"></jsp:include>
</c:if>
<!--==================================
=            User Profile            =
===================================-->
<section class="dashboard section">
	<!-- Container Start -->
	<div class="container">
		<!-- Row Start -->
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
						<a href="user-profile.html" class="btn btn-main-sm">编辑资料</a>
					</div>
					<!-- Dashboard Links -->
						<div>
					<div class="widget user-dashboard-menu">
						<ul>
							<li><a href="dashboard"><i class="fa fa-user"></i> 我的订单</a></li>
							<li><a href="dashboard-favourite"><i class="fa fa-bookmark-o"></i> 我的收藏<span>5</span></a></li>
							<li class="active"><a href="dashboard-shopcar"><i class="fa fa-file-archive-o"></i>购物车<span>12</span></a></li>
							<li><a href=""><i class="fa fa-bolt"></i> 已完成订单<span>23</span></a></li>
							<li><a href=""><i class="fa fa-cog"></i> 注销</a></li>
						</ul>
					</div>
				</div>
				</div>
			</div>
			<div class="col-md-10 offset-md-1 col-lg-8 offset-lg-0">
				<!-- Recently Favorited -->
				<div class="widget dashboard-container my-adslist">
					<h3 class="widget-header">购物车</h3>
					<table class="table table-responsive product-dashboard-table">
						<thead>
							<tr>
								<th>图片</th>
								<th>商品名</th>
								<th class="text-center">详情</th>
								<th class="text-center">操作</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${goodslist}" var="g">
							<tr>
								<td class="product-thumb">
									<img width="80px" height="auto" src="${g.pic}" alt="image description"></td>
								<td class="product-details">
									<h3 class="title">${g.goodsname}</h3>
									<span><strong>添加日期 </strong><time>${g.ordertime}</time> </span>
								</td>
								<td class="product-category"><span class="categories">X${g.count}</span></td>
								<td class="action" data-title="Action">
									<div class="">
										<ul class="list-inline justify-content-center">
											<li class="list-inline-item">
												<a class="edit" href="">
													<i class="fa fa-clipboard"></i>
												</a>		
											</li>
											<li class="list-inline-item">
												<a class="delete" href="">
													<i class="fa fa-trash"></i>
												</a>
											</li>
										</ul>
									</div>
								</td>
							</tr>
							</c:forEach>
							
						</tbody>
					</table>
					
				</div>
			</div>
		</div>
		<!-- Row End -->
	</div>
	<!-- Container End -->
</section>
<!--============================
=            Footer            =
=============================-->

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