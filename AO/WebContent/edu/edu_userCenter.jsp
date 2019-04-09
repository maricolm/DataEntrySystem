<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>个人中心</title>
  <meta name="description" content="这是一个form页面">
  <meta name="keywords" content="form">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="icon" type="image/png" href="assets/i/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="Amaze UI" />
  <link rel="stylesheet" href="assets/css/amazeui.min.css"/>
  <link rel="stylesheet" href="assets/css/admin.css">
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
      <li><a href='<c:url value="/edu/findPro.do?currentPage=1"/>'><span class="am-icon-envelope-o"></span> 待处理 <span class="am-badge am-badge-warning">${dclMsg }</span></a></li>
      <li><a href="<c:url value='/edu/logout.do'/>"><span class="am-icon-power-off"></span> 退出 </a></li>
      <li class="am-dropdown" data-am-dropdown>
        <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
          <span class="am-icon-users"></span> ${edu.eName } <span class="am-icon-caret-down"></span>
        </a>
        <ul class="am-dropdown-content">
          <li><a href="edu_userCenter.jsp"><span class="am-icon-user"></span> 个人中心</a></li>
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
      <li style="background: #0e90d2;"><a href="${pageContext.request.contextPath }/edu/edu_index.jsp" style="color:white;"><span class="am-icon-home"></span>首页</a></li>
      <li class="admin-parent">
        <a class="am-cf" data-am-collapse="{target: '#collapse-nav'}"><span class="am-icon-file"></span>&nbsp;项目管理 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
        <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav">
          <li><a href='<c:url value="/edu/findPro.do?currentPage=1"/>'><span class="am-icon-th"></span>&nbsp;项目列表<span class="am-badge am-badge-secondary am-margin-right am-fr">${proCount }</span></a></li>
          <li><a href="<c:url value="/edu/edu_addPro.jsp"/>"  class="am-cf"><span class="am-icon-upload"></span>&nbsp;添加项目<span class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span></a></li>
          <li><a href="#"><span class="am-icon-puzzle-piece"></span>&nbsp;帮助</a></li>
          <!--<li><a href="admin-404.html"><span class="am-icon-bug"></span>&nbsp;404</a></li>-->
        </ul>
      </li>
      <li><a href="<c:url value='/edu/findUsers.do?currentPage=1' />"><span class="am-icon-table"></span>&nbsp;用户列表</a></li>
      <li><a href="edu_addSch.jsp"><span class="am-icon-pencil-square-o"></span>&nbsp;添加用户</a></li>
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
    <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">个人资料</strong> / <small>Personal information</small></div>
  </div>

  <div class="am-tabs am-margin" data-am-tabs>
    <ul class="am-tabs-nav am-nav am-nav-tabs">
      <li class="am-active"><a href="#tab1">个人中心</a></li>
      <li><a href="#tab3">修改密码</a></li>
    </ul>

    <div class="am-tabs-bd">
      <div class="am-tab-panel am-fade am-in am-active" id="tab1">
        <form action="<c:url value='/edu/modifyinfo.do'/>"  class="am-form am-form-horizontal">
        <input name="eId" type="hidden" value="${edu.eId }">
        	<div class="am-form-group">
            <label for="user-name" class="am-u-sm-3 am-form-label">账号 / Account</label>
            <div class="am-u-sm-4 am-u-end">
              <input  name="eAccount" type="text" id="user-name" disabled="disabled" value="${edu.eAccount}">
              <small>账号为统一分配，同时为登录凭证，不可修改。</small>
            </div>
          </div>
          <div class="am-form-group">
            <label for="user-name" class="am-u-sm-3 am-form-label">姓名 / Name</label>
            <div class="am-u-sm-4 am-u-end">
              <input name="eName" type="text" id="user-name" value="${edu.eName}">
              <small>输入你的名字，以便他人查看。</small>
            </div>
          </div>

          <div class="am-form-group">
            <label for="user-email" class="am-u-sm-3 am-form-label">电子邮件 / Email</label>
            <div class="am-u-sm-4 am-u-end">
              <input name="eEmail" type="email" id="user-email" value="${edu.eEmail}">
              <small>输入你常用的邮箱地址</small>
            </div>
          </div>

          <div class="am-form-group">
            <label for="user-phone" class="am-u-sm-3 am-form-label">电话 / Telephone</label>
            <div class="am-u-sm-4 am-u-end">
              <input name="ePhone" type="tel" id="user-phone" value="${edu.ePhone}">
              <small>输入你常用的电话号码</small>
            </div>
          </div>

          <div class="am-form-group">
            <label for="user-QQ" class="am-u-sm-3 am-form-label">部门</label>
            <div class="am-u-sm-4 am-u-end">
              <input name="eDepartment" type="text" id="user-QQ" value="${edu.eDepartment}">
              <small>输入你所在的部门</small>
            </div>
          </div>

          <div class="am-form-group">
            <div class="am-u-sm-9 am-u-sm-push-3">
              <button type="submit" class="am-btn am-btn-primary">保存修改</button>
            </div>
          </div>
        </form>

      </div>
			<!-- end 个人中心-->

      <div class="am-tab-panel am-fade" id="tab3">
        <form action="<c:url value='/edu/modifypswd.do'/>"  class="am-form">
          <div class="am-g am-margin-top-sm">
            <div class="am-u-sm-2 am-text-right">
              	新密码
            </div>
            <div class="am-u-sm-4 am-u-end">
              <input type="password" class="am-input-sm" id='newpswd'><small id='npswdError' style="color: red"></small>
            </div>
          </div>

          <div class="am-g am-margin-top-sm">
            <div class="am-u-sm-2 am-text-right">
              	确认密码
            </div>
            <div class="am-u-sm-4 am-u-end">
              <input name='pwsd' type="password" class="am-input-sm" id='rnpswd'><small id='rnpswdError' style="color: red"></small>
            </div>
          </div>
          <div class="am-g am-margin-top-sm">
            <div class="am-u-sm-2 am-text-right" style="color: white;">确认修改
            </div>
            <div class="am-u-sm-4 am-u-end">
              <button type="submit" class="am-btn am-btn-primary am-btn-xs" id='subNP'>确认修改</button>
            </div>
          </div>
        </form>
      </div>

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
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/amazeui.min.js"></script>
<script src="assets/js/modifyPswd.js"></script>
<!--<![endif]-->
<script src="assets/js/app.js"></script>
</body>
</html>
