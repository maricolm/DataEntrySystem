<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>添加用户</title>
  <meta name="description" content="这是一个 user 页面">
  <meta name="keywords" content="user">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="icon" type="image/png" href="${pageContext.request.contextPath }/sch/assets/i/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="${pageContext.request.contextPath }/sch/assets/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="Amaze UI" />
  <link rel="stylesheet" href="${pageContext.request.contextPath }/sch/assets/css/amazeui.min.css"/>
  <link rel="stylesheet" href="${pageContext.request.contextPath }/sch/assets/css/admin.css">
</head>
<body>
<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，Amaze UI 暂不支持。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
  以获得更好的体验！</p>
<![endif]-->

<header class="am-topbar admin-header">
  <div class="am-topbar-brand">
    <strong>四川省教育厅高等教育处</strong> <small>数据录入系统</small>
  </div>
  <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only" data-am-collapse="{target: '#topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span></button>
  <div class="am-collapse am-topbar-collapse" id="topbar-collapse">
    <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
      <li><a href="<c:url value='/sch/logout.do'/>"><span class="am-icon-power-off"></span> 退出 </a></li>
      <li class="am-dropdown" data-am-dropdown>
        <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
          <span class="am-icon-users"></span>${sch.sUname }<span class="am-icon-caret-down"></span>
        </a>
        <ul class="am-dropdown-content">
          <li><a href='<c:url value='/sch/sch_userCenter.jsp'/>'><span class="am-icon-user"></span> 个人中心</a></li>
        </ul>
      </li>
      <li><a href="javascript:;" id="admin-fullscreen"><span class="am-icon-arrows-alt"></span> <span class="admin-fullText">开启全屏</span></a></li>
    </ul>
  </div>
</header>

<div class="am-cf admin-main">
  <!-- sidebar start -->
  <div class="admin-sidebar">
    <ul class="am-list admin-sidebar-list">
      <li  style="background: #0e90d2;"><a href="admin-index.html" style="color:white;"><span class="am-icon-home"></span>首页</a></li>
      <li class="admin-parent">
        <a class="am-cf" data-am-collapse="{target: '#collapse-nav'}"><span class="am-icon-file"></span>&nbsp;项目管理 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
        <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav">
          <li><a href="${pageContext.request.contextPath }/sch/queryProOfBeCompleted.do?currentPage=1"><span class="am-icon-th"></span>&nbsp;项目列表<span class="am-badge am-badge-secondary am-margin-right am-fr"></span></a></li>
          <li><a href="admin-help.html"><span class="am-icon-puzzle-piece"></span>&nbsp;帮助</a></li>
          <!--<li><a href="admin-404.html"><span class="am-icon-bug"></span>&nbsp;404</a></li>-->
        </ul>
      </li>
      <li><a href="<c:url value='/sch/findUser.do?currentPage=1'/>"><span class="am-icon-table"></span>&nbsp;用户列表</a></li>
      <li><a href="sch_addCol.jsp"><span class="am-icon-pencil-square-o"></span>&nbsp;添加用户</a></li>
    </ul>

    <div class="am-panel am-panel-default admin-sidebar-panel">
      <div class="am-panel-bd">
        <p><span class="am-icon-bookmark"></span> 公告</p>
        <p>时光静好，与君语；细水流年，与君同。—— Amaze</p>
      </div>
    </div>
  </div>
  <!-- sidebar end -->

  <!-- content start -->
  <div class="admin-content">
    <div class="am-cf am-padding">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">添加学校账号</strong> / <small>add account</small></div>
    </div>

    <hr/>

    <div class="am-g">

      <div class="am-u-sm-12 am-u-md-4 am-u-md-push-8">
				说明：<br /><br />
				1.账号为用户登录凭证，一旦分配，不可改变。<br /><br />
				2.如用户密码遗失，请去用户列表为其重置。<br /><br />
				3.添加用户后，系统默认初试密码为：000000；若帮用户重置密码后，重置密码为：000000。<br /><br />
				4.每个学院限分配一个账号
      </div>

      <div class="am-u-sm-12 am-u-md-8 am-u-md-pull-4">
        <form action="<c:url value='/sch/addCol.do'/>" method="post" class="am-form am-form-horizontal">
        	<div class="am-form-group">
            <label for="user-name" class="am-u-sm-3 am-form-label">账号 / Account</label>
            <div class="am-u-sm-9">
              <input  id="inAccount" name="cAccount" type="text" id=inAccount placeholder="账号 / Account">
              <small id="inAccountError">账号为用户登录凭证，初试密码为：0000。</small>
            </div>
          </div>
          <div class="am-form-group">
            <label for="user-name" class="am-u-sm-3 am-form-label">姓名 / Name</label>
            <div class="am-u-sm-9">
              <input class="test" type="text" id="cUname" name="cUname" placeholder="姓名 / Name">
              <small>输入用户的名字，以便他人查看。</small>
            </div>
          </div>

          <div class="am-form-group">
            <label for="user-email" class="am-u-sm-3 am-form-label">电子邮件 / Email</label>
            <div class="am-u-sm-9">
              <input class="test"  type="email" id="cEmail" name="cEmail" placeholder="输入你的电子邮件 / Email">
              <small>输入用户常用的邮箱地址</small>
            </div>
          </div>

          <div class="am-form-group">
            <label for="user-phone" class="am-u-sm-3 am-form-label">电话 / Telephone</label>
            <div class="am-u-sm-9">
              <input class="test"  type="text" id="cPhone" name="cPhone" placeholder="输入你的电话号码 / Telephone">
              <small>输入用户常用的电话号码</small>
            </div>
          </div>

          <div class="am-form-group">
            <label for="user-QQ" class="am-u-sm-3 am-form-label">院系 / College</label>
            <div class="am-u-sm-9">
              <input class="test"  type="text" id="cDepartment" name="cDepartment" placeholder="院系 / Department">
              <small>输入用户所在的院系</small>
            </div>
          </div>

          <div class="am-form-group">
            <div class="am-u-sm-9 am-u-sm-push-3">
              <button id="addQR" type="submit" class="am-btn am-btn-primary">确认添加</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
  <!-- content end -->
</div>

<footer style="text-align: center;">
  <hr>
  <p class="am-padding-left">© 2018 四川省教育厅高等教育处 数据收集系统.</p>
</footer>

<!--[if lt IE 9]>
<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="assets/js/polyfill/rem.min.js"></script>
<script src="assets/js/polyfill/respond.min.js"></script>
<script src="assets/js/amazeui.legacy.js"></script>
<![endif]-->

<!--[if (gte IE 9)|!(IE)]><!-->
<script src="${pageContext.request.contextPath }/sch/assets/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath }/sch/assets/js/amazeui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/sch/assets/js/addUser.js"></script>
<!--<![endif]-->
<script src="${pageContext.request.contextPath }/sch/assets/js/app.js"></script>
</body>
</html>
