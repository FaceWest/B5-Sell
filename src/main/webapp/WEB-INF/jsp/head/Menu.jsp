<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
						<a href="user-profile" class="btn btn-main-sm">编辑资料</a>
					</div>
					<!-- Dashboard Links -->
					<div class="widget user-dashboard-menu">
						<ul>
							<li class="active" ><a href=""><i class="fa fa-user"></i> 我的订单</a></li>
							<li><a href="dashboard-favourite"><i class="fa fa-bookmark-o"></i> 我的收藏<span>5</span></a></li>
							<li><a href="dashboard-shopcar"><i class="fa fa-file-archive-o"></i>购物车<span>12</span></a></li>
							<li><a href=""><i class="fa fa-bolt"></i> 已完成订单<span>23</span></a></li>
							<li><a href=""><i class="fa fa-cog"></i> 注销</a></li>
						</ul>
					</div>
				</div>
			</div>