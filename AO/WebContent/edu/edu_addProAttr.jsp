<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>添加属性</title>
  <meta name="description" content="这是一个 table 页面">
  <meta name="keywords" content="table">
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
      <li><a href="${pageContext.request.contextPath }/edu/edu_index.jsp"><span class="am-icon-home"></span>首页</a></li>
      <li class="admin-parent">
        <a class="am-cf" data-am-collapse="{target: '#collapse-nav'}"><span class="am-icon-file"></span>&nbsp;项目管理 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
        <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav">
          <li><a href='<c:url value="/edu/findPro.do?currentPage=1"/>'><span class="am-icon-th"></span>&nbsp;项目列表<span class="am-badge am-badge-secondary am-margin-right am-fr">${proCount }</span></a></li>
          <li  style="background: #0e90d2;"><a href="<c:url value="/edu/edu_addPro.jsp"/>" style="color:white;"  class="am-cf"><span class="am-icon-upload"></span>&nbsp;添加项目<span class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span></a></li>
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
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">添加属性</strong> / <small>addProjectAttribute</small></div>
    </div>
    <div class="am-g">
      <div class="am-u-md-6 am-cf">
        <div class="am-fl am-cf">
          <div class="am-btn-toolbar am-fl">
              ${pro.pName }
          </div>
        </div>
      </div>
    </div>

    <div class="am-g">
      <div class="am-u-sm-12">
          <table class="am-table am-table-striped am-table-hover table-main">
            <thead>
              <tr>
                <th class="table-title">项目名</th><th class="table-title">属性</th><th class="table-title">属性别名</th><th class="table-set">操作</th>
              </tr>
          </thead>
          <tbody>
          <c:choose>
          	<c:when test="${!empty attr }">
          		<c:forEach items="${attr}" var="attr">
	            	<tr>
		              <td>${attr.aPname }</td>
		              <td>${attr.aName }</td>
		              <td>${attr.aAlias }</td>
		              <td>
		                <div class="am-btn-toolbar">
		                  <div class="am-btn-group am-btn-group-xs">
		                    <a href="<c:url value="/edu/deleteAttrByaId.do?aId=${attr.aId }&&aPid=${attr.aPid }" />" class="am-btn am-btn-default am-btn-xs am-text-danger" onclick="return confirm('确认删除？')"><span class="am-icon-trash-o"></span>&nbsp;删除</a>
		                  </div>
		                </div>
		              </td>
		            </tr>
	            </c:forEach>
          	</c:when>
          	<c:otherwise>
          	<tr>
          			<td colspan="4" style="text-align: center;">当前项目还没有属性，请添加属性。</td>
          	</tr>
          	</c:otherwise>
          </c:choose>
            
          </tbody>
        </table>
          <hr />
          <p style="color: red;">添加属性</p>
        <table class="am-table am-table-striped am-table-hover table-main">
        	<thead>
              <tr>
                <th class="table-title">项目名</th><th class="table-title">属性<small id="aNameError" style="color: red;"></small></th><th class="table-title">属性别名<small id="aAliasError" style="color: red;">(英文别名)</small></th><th class="table-set">操作</th>
              </tr>
          </thead>
        <tbody>
        	<form action='<c:url value="/edu/addAttr.do" />'>
            <tr>
              <td id="pName"><input name="aPid" value="${pro.pId }" type="hidden">${pro.pName }</td>
              <input name="aPname" type="hidden" value="${pro.pName }">
              <td><input name="aName" value="" type="text" id="aName"></td>
              <td><input name="aAlias" value="" type="text" id="aAlias"></td>
              <td>
                <div class="am-btn-toolbar">
                  <div class="am-btn-group am-btn-group-xs">
                    <button type="submit" class="am-btn am-btn-default am-btn-xs am-text-success"  id="qraddargs"><span class="am-icon-upload"></span>&nbsp;添加</button>
                  </div>
                </div>
              </td>
            </tr>
          </form>
          </tbody>
        </table>
        <p style="color: red;">注：</p><small>属性别名请使用英文，英语创建数据表字段；长度5以内；不可与本项目中其他别名重复。</small>
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
<!--<![endif]-->
<script src="assets/js/app.js"></script>
<script src="assets/js/ajaxVerfifyAddArgs.js"></script>
</body>
</html>
