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
  <title>Calssimax</title>
  
  <!-- PLUGINS CSS STYLE -->
  <link href="plugins/jquery-ui/jquery-ui.min.css" rel="stylesheet">
  <!-- Bootstrap -->
  <link href="plugins/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
  <!-- Font Awesome -->
  <link href="../../plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet">
  <!-- Owl Carousel -->
  <link href="../../plugins/slick-carousel/slick/slick.css" rel="stylesheet">
  <link href="../../plugins/slick-carousel/slick/slick-theme.css" rel="stylesheet">
  <!-- Fancy Box -->
  <link href="../../plugins/fancybox/jquery.fancybox.pack.css" rel="stylesheet">
  <link href="../../plugins/jquery-nice-select/css/nice-select.css" rel="stylesheet">
  <link href="../../plugins/seiyria-bootstrap-slider/dist/css/bootstrap-slider.min.css" rel="stylesheet">
  <!-- CUSTOM CSS -->
  <link href="../../css/style.css" rel="stylesheet">

 <script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
  <!-- FAVICON -->
  <link href="img/favicon.png" rel="shortcut icon">

  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
   <!-- JAVASCRIPTS -->
  <script src="plugins/jquery/dist/jquery.min.js"></script>
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

</head>

<body class="body-wrapper">
<c:if test="${! empty loginedClient}">
<jsp:include page="head/loginedhead.jsp"></jsp:include>
</c:if>
<c:if test="${empty loginedClient}">
<jsp:include page="head/head.jsp"></jsp:include>
</c:if>
<section class="page-search">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<!-- Advance Search -->
				<div class="advance-search">
					<form>
						<div class="form-row">
							<div class="form-group col-md-4">
								<input type="text" class="form-control" id="inputtext4" placeholder="What are you looking for">
							</div>
							<div class="form-group col-md-3">
								<input type="text" class="form-control" id="inputCategory4" placeholder="Category">
							</div>
							<div class="form-group col-md-3">
								<input type="text" class="form-control" id="inputLocation4" placeholder="Location">
							</div>
							<div class="form-group col-md-2">
								
								<button type="submit" class="btn btn-primary">Search Now</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</section>
<!--===================================
=            Store Section            =
====================================-->
<c:forEach items="${goodsinfo}" var ="g">
<section class="section bg-gray">
	<!-- Container Start -->
	<div class="container">
		<div class="row">
			<!-- Left sidebar -->
			<div class="col-md-8">
				<div class="product-details">
					<h1 class="product-title"></h1>
					<div class="product-meta">
					
					</div>
					<div id="carouselExampleIndicators" class="product-slider carousel slide" data-ride="carousel">
						
						<div class="carousel-inner">
							<div class="carousel-item active">
								<img class="" src="${g.pic}" alt="First slide" width="550px" height="590px">
							</div>
							
						</div>
						<a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
							<span class="carousel-control-prev-icon" aria-hidden="true"></span>
							<span class="sr-only">Previous</span>
						</a>
						<a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
							<span class="carousel-control-next-icon" aria-hidden="true"></span>
							<span class="sr-only">Next</span>
						</a>
					</div>
					<div class="content">
						<ul class="nav nav-pills  justify-content-center" id="pills-tab" role="tablist">
							
							<li class="nav-item">
							</li>
							<li class="nav-item">
							</li>
						</ul>
						
						<form action="/alipay/page/gotoPayPage" method="post" id="payform">
						
						<div class="tab-content" id="pills-tabContent">
									<div class="item-amount clearfix bgf5">
							<div class="item-metatit">数量：</div>
							<div class="amount-box">
								<div class="amount-widget">
									<input type="button" class="sub" value="-">
									<input class="count" value="1" title="请输入购买量" type="text" name="count" >
									<input type="button" class="add" value="+">
									<input type="hidden" value="${g.price}" name="price">
									<input type="hidden" value="${g.goodsid}" class="goodsid">
									<input type="hidden" value="${loginedClient.clientid}" class="clientid">
									<input type="button" value="加入购物车" onclick="addShopcar();">
									<input type="button" value="加入收藏" onclick="addMyFavorite();">
									<input type="button" value="立即购买" onclick="BuyNow();">
									<div class="amount-btn">
										<a class="amount-but add"></a>
										<a class="amount-but sub"></a>
									</div>
								</div>
								<script>
										$('.sub').click(function(){
											var num = $('.count').val();
											if(num>=1){
												$('.count').val(num-1);
											}
										});
										
										$('.add').click(function(){
											var num = $('.count').val();
											var num1 = parseInt(num);
											if(num>=0){
												$('.count').val(num1+1);
											}
										});
										
										function addShopcar(){
											var clientid = $('.clientid').val();
											if(clientid==null||clientid==undefined||clientid==""){
												alert("请登录账号！");
												window.location.href="/login";
											}else{
											var count = parseInt($('.count').val());
											var goodsid = $('.goodsid').val();
											var data = new FormData();
											data.append("count",count);
											data.append("goodsid",goodsid);
											data.append("clientid",clientid);
											alert("进来了！")
											$.ajax({
												
												type: "POST",
												url: "/addShopcar",
												data : data,
												processData:false,
												contentType: false,
											    success: function(){
											    	alert("成功添加至购物车");
											    }
											});
											}
										};
										
										
										function addMyFavorite(){
											var clientid = $('.clientid').val();
											if(clientid==null||clientid==undefined||clientid==""){
												alert("请登录账号！");
												window.location.href="/login";
											}else{
											var goodsid = $('.goodsid').val();
											var data = new FormData();
											data.append("goodsid",goodsid);
											data.append("clientid",clientid);
											alert("进来了！")
											$.ajax({
												type: "POST",
												url: "/addMyFavorite",
												data : data,
												processData:false,
												contentType: false,
											    success: function(){
											    	alert("成功添加收藏");
											    }
											});
											}
										};
										
										function BuyNow(){
											var clientid = $('.clientid').val();
											if(clientid==null||clientid==undefined||clientid==""){
												alert("请登录账号！");
												window.location.href="/login";
											}else{
												if(confirm("确定要购买吗？")){
													var form  =$('#payform');
													var goodsid = $('.goodsid').val();
													var data = new FormData();
													data.append("goodsid",goodsid);
													data.append("clientid",clientid);
													/* $.ajax({
														type: "POST",
														url: "/alipay/page/gotoPayPage",
														data : data,
														processData:false,
														contentType: false,
													    success: function(){
													    }
													}); */
													form.submit();
												}
												
											}
										};
										
										
										
								</script>
							</div>
							
							
							
							
							
						</div>	
						</div>
						</form>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="sidebar">
					<!-- User Profile widget -->
					<div class="widget user">
						<span style="text-align:center;"><h1>${g.goodsname}</h1></span>
					</div>
					<!-- Map Widget -->
					<div class="widget map">
					<div class="widget price text-center">
						<h4>价格</h4>
						<p>￥${g.price}</p>
					</div>
					
					</div>
					<!-- Rate Widget -->
					<div class="widget rate">
						<!-- Heading -->
						<h5 class="widget-header text-center">评价一下这款商品吧！</h5>
						<!-- Rate -->
						<div class="starrr"></div>
					</div>
					<!-- Safety tips widget -->
						
					<!-- Coupon Widget -->
					
					
				</div>
			</div>
			
		</div>
	</div>
	<!-- Container End -->
</section>
</c:forEach>
<!--============================
=            Footer            =
=============================-->
<jsp:include page="head/footer.jsp"></jsp:include>

 

</body>

</html>