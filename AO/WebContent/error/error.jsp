<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>error </title>
  <meta name="description" content="这是一个404页面">
  <meta name="keywords" content="404">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="icon" type="image/png" href="${pageContext.request.contextPath }/edu/assets/i/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="${pageContext.request.contextPath }/edu/assets/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="Amaze UI" />
  <link rel="stylesheet" href="${pageContext.request.contextPath }/edu/assets/css/amazeui.min.css"/>
  <link rel="stylesheet" href="${pageContext.request.contextPath }/edu/assets/css/admin.css">
</head>
<body>
<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，Amaze UI 暂不支持。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
  以获得更好的体验！</p>
<![endif]-->
<div class="am-cf admin-main">
  <!-- content start -->
  <div class="admin-content">
    <div class="am-cf am-padding">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">error</strong> / <small>That’s an error</small></div>
    </div>
    <div class="am-g">
      <div class="am-u-sm-12">
        <h2 class="am-text-center am-text-xxxl am-margin-top-lg">亲，发生了一个错误！</h2>
        <p class="am-text-center" style="color: red"> ${msg } </p>
        <pre class="page-404">
          .----.
       _.'__    `.
   .--($)($$)---/#\
 .' @          /###\
 :         ,   #####
  `-..__.-' _.-\###/
        `;_:    `"'
      .'"""""`.
     /,  ya ,\\
    //  出错啦!  \\
    `-._______.-'
    ___`. | .'___
   (______|______)
        </pre>
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
<script src="${pageContext.request.contextPath }/edu/assets/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath }/edu/assets/js/amazeui.min.js"></script>
<!--<![endif]-->
<script src="${pageContext.request.contextPath }/edu/assets/js/app.js"></script>
</body>
</html>
