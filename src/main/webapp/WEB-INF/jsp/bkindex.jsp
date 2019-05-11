<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
    <title>B5 sell</title>


    <link rel="stylesheet" type="text/css" href="css/base.css">
    <link rel="stylesheet" type="text/css" href="css/frame_sys.css">
    <link rel="stylesheet" type="text/css" href="css/iconfont.css">
    <script type="text/javascript" src="framework/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="js/frame.js"></script>
    <!--分页插件-->
    <link rel="stylesheet" type="text/css" href="css/jquery.page.css">
    <script type="text/javascript" src="framework/jquery.page.js"></script>

    <!--自定义滚动条-->
    <script type="text/javascript" src="framework/jquery-ui-1.10.4.min.js"></script>
    <script type="text/javascript" src="framework/jquery.mousewheel.min.js"></script>
    <script type="text/javascript" src="framework/jquery.mCustomScrollbar.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/jquery.mCustomScrollbar.css">


</head>
<body>

<div class="top">
    <p>B5 Sell<font>系统设置</font></p>
    <span>
        <a class="but" href="bklogin"><i class="iconfont">&#xe6a3;</i>退出系统</a>
        <a class="but" href="#"><i class="iconfont">&#xe600;</i>返回首页</a>
        <a>欢迎您，张伟平</a>
    </span>
</div>

<div class="main">

    <div class="menu">
        <div class="menuList">
            <ul class="menu1">
                <li class="menuli1">
                    <a class="menua1" href="javascript:menu_ajax('tgls/index.html', '后台主页')"><i class="iconfont left">&#xe600;</i>
                        <p>后台主页</p></a>
                </li>
                <li class="menuli1">
                    <a class="menua1" href="javascript:void(0)"><i class="iconfont left">&#xe697;</i>
                        <p>分管管理</p><i
                                class="iconfont right">&#xe608;</i></a>
                    <ul class="menu2">
                        <li class="menuli2"><a class="menua2" href="javascript:menu_ajax('tgls/agent/agent_list.html', '客户管理,客户卡片')"><i
                                class="menua2_ico"><span></span></i>分管列表</a></li>
                        <li class="menuli2"><a class="menua2" href="javascript:menu_ajax('tgls/agent/agent_add.html', '客户管理,竞争对手卡片')"><i
                                class="menua2_ico"><span></span></i>添加分管</a></li>
                    </ul>
                </li>
                <li class="menuli1">
                    <a class="menua1" href="javascript:void(0)"><i class="iconfont left">&#xe713;</i>
                        <p>商品管理</p><i
                                class="iconfont right">&#xe608;</i></a>
                    <ul class="menu2">
                        <li class="menuli2"><a class="menua2" href="javascript:menu_ajax('tgls/agent/agent_list.html', '客户管理,客户卡片')"><i
                                class="menua2_ico"><span></span></i>分类列表</a></li>
                        <li class="menuli2"><a class="menua2" href="javascript:menu_ajax('tgls/agent/agent_list.html', '客户管理,客户联系人卡片')"><i
                                class="menua2_ico"><span></span></i>基本商品库</a></li>
                        <li class="menuli2"><a class="menua2" href="javascript:menu_ajax('tgls/agent/agent_add.html', '客户管理,竞争对手卡片')"><i
                                class="menua2_ico"><span></span></i>添加商品</a></li>
                    </ul>
                </li>
                <li class="menuli1">
                    <a class="menua1" href="javascript:menu_ajax('tgls/control_library.html', '控件库')"><i class="iconfont left">&#xe711;</i>
                        <p>控件库</p></a>
                </li>
                <li class="menuli1">
                    <a class="menua1" href="javascript:menu_ajax('tgls/customer_card.html', '任务卡片管理')"><i class="iconfont left">&#xe66b;</i>
                        <p>任务卡片管理</p></a>
                </li>
                <li class="menuli1">
                    <a class="menua1" href="javascript:menu_ajax('tgls/mainline_logic.html', '主线逻辑管理')"><i class="iconfont left">&#xe633;</i>
                        <p>主线逻辑管理</p></a>
                </li>
                <li class="menuli1">
                    <a class="menua1" href="javascript:void(0)"><i class="iconfont left">&#xe6da;</i>
                        <p>项目管理卡片</p><i
                                class="iconfont right">&#xe608;</i></a>
                    <ul class="menu2">
                        <li class="menuli2"><a class="menua2" href="javascript:menu_ajax('tgls/customer_card.html', '项目管理卡片,二类卡片')"><i
                                class="menua2_ico"><span></span></i>二类卡片</a></li>
                    </ul>
                </li>
                <li class="menuli1">
                    <a class="menua1" href="javascript:menu_ajax('tgls/customer_card.html', '用户和权限管理')"><i class="iconfont left">&#xe659;</i>
                        <p>用户和权限管理</p></a>
                </li>
                <li class="menuli1">
                    <a class="menua1" href="javascript:menu_ajax('tgls/customer_card.html', '用户和权限管理')"><i class="iconfont left">&#xe60d;</i>
                        <p>统计报表</p></a>
                </li>
                <li class="menuli1">
                    <a class="menua1" href="javascript:menu_ajax('tgls/customer_card.html', '用户和权限管理')"><i class="iconfont left">&#xe607;</i>
                        <p>知识管理</p></a>
                </li>
            </ul>
        </div>
    </div>

    <div class="main_body">
        <div class="main_body_title"><font>后台主页</font></div>
        <div class="main_body_con">
        	<iframe id="mainIframe" src="tgls/index.html" scrolling="no"></iframe>
        </div>
    </div>

</div>

<div class="copyright">
    Copyright©2011-2018 B5 sell
</div>


<script>
    $(".menu").mCustomScrollbar({
        scrollButtons:{
            enable:false,
            scrollType:"continuous",
            scrollSpeed:20,
            scrollAmount:40
        },
        horizontalScroll:false
    });
    $(".main_body_con").mCustomScrollbar({
        scrollButtons:{
            enable:false,
            scrollType:"continuous",
            scrollSpeed:20,
            scrollAmount:40
        },
        horizontalScroll:false
    });
</script>
</body>
</html>
