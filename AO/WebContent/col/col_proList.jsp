<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>待接收项目列表</title>
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
      <li><a href="${pageContext.request.contextPath}/col/logout.do "><span class="am-icon-power-off"></span> 退出 </a></li>
      <li class="am-dropdown" data-am-dropdown>
        <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
          <span class="am-icon-users"></span> ${col.cUname } <span class="am-icon-caret-down"></span>
        </a>
        <ul class="am-dropdown-content">
          <li><a href="${pageContext.request.contextPath}/col/col_userCenter.jsp"><span class="am-icon-user"></span> 个人中心</a></li>
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
      <li><a href="admin-index.html"><span class="am-icon-home"></span>首页</a></li>
      <li class="admin-parent">
        <a class="am-cf" data-am-collapse="{target: '#collapse-nav'}"><span class="am-icon-file"></span>&nbsp;项目管理 </a>
        <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav">
          <li  style="background: #0e90d2;"><a href="${pageContext.request.contextPath}/col/proList.do?currentPage=1"  style="color:white;"><span class="am-icon-th"></span>&nbsp;项目列表</a></li>
          <li><a href="admin-help.html"><span class="am-icon-puzzle-piece"></span>&nbsp;帮助</a></li>
          <!--<li><a href="admin-404.html"><span class="am-icon-bug"></span>&nbsp;404</a></li>-->
        </ul>
      </li>
      <li><a href="${pageContext.request.contextPath}/col/col_userCenter.jsp"><span class="am-icon-pencil-square-o"></span>&nbsp;个人中心</a></li>
    </ul>

    <div class="am-panel am-panel-default admin-sidebar-panel">
      <div class="am-panel-bd">
        <p><span class="am-icon-bookmark"></span> 公告</p>
        <p>请注意保护好数据</p>
      </div>
    </div>
  </div>
  <!-- sidebar end -->

  <!-- content start -->
  <div class="admin-content">

    <div class="am-cf am-padding">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">待接收项目列表</strong> / <small>proList</small></div>
    </div>

    <div class="am-g">
      <div class="am-u-sm-12">
        <form class="am-form">
          <table class="am-table am-table-striped am-table-hover table-main">
            <thead>
              <tr>
                <th class="table-title">项目名</th><th class="table-title">创建人</th><th class="table-title">创建时间</th><th class="table-type">项目状态</th><th class="table-set">操作</th><th class="table-set">模板</th>
              </tr>
          </thead>
          <tbody>
          <c:forEach items="${pb.beanList }" var="task">
          	<tr>
              <td>${task.pName }</td>
              <td>${task.eEname }</td>
              <td>
              	<fmt:formatDate value="${task.pDate }" type="date" pattern="yyyy-MM-dd HH:mm"/>
              </td>
              <td>
              	<c:choose>
              		<c:when test="${task.tState==1 }">
              				待接收
              		</c:when>
              		<c:when test="${task.tState==2 }">
              				待完成
              		</c:when>
              		<c:when test="${task.tState==3 }">
              				待确认
              		</c:when>
              		<c:when test="${task.tState==5 }">
              				被驳回:${task.backMsg }
              		</c:when>
              		<c:when test="${task.tState==4 }">
              				已完成
              		</c:when>
              	</c:choose>
              </td>
              <td>
                <div class="am-btn-toolbar">
                  <div class="am-btn-group am-btn-group-xs">
                  <c:choose>
                  	<c:when test="${task.tState==1 }">
                  		<a href="${pageContext.request.contextPath }/col/acceptTask.do?tId=${task.tId }" class="am-btn am-btn-default am-btn-xs am-text-danger" onclick="return confirm('确认接收项目？')"><span class="am-icon-arrow-down"></span>&nbsp;接收项目</a>
                  	</c:when>
                  	<c:when test="${task.tState==2 }">
	                  	<a href="${pageContext.request.contextPath }/col/findDataByProId.do?&currentPage=1&pId=${task.pId }" class="am-btn am-btn-default am-btn-xs am-text-danger">&nbsp;查看</a>
	                  	<a href="${pageContext.request.contextPath }/col/submitData.do?colid=${col.cId }&pId=${task.pId }&tId=${task.tId }" class="am-btn am-btn-default am-btn-xs am-text-secondary">&nbsp;提交</a>
	                  	<a href="${pageContext.request.contextPath }/col/callBackData.do?colid=${col.cId }&pId=${task.pId }" onclick="return confirm('您的本次操作将清空您当前的所有数据。确认撤销？')" title="删除所有数据" class="am-btn am-btn-default am-btn-xs am-text-danger">&nbsp;撤销</a>
                  	</c:when>
                  	<c:when test="${task.tState==3 }">
	                  	<a href="JavaScript:void(0)" class="am-btn am-btn-default am-btn-xs am-text-secondary">&nbsp;待学校审核</a>
                  	</c:when>
                  	<c:when test="${task.tState==4 }">
	                  	<a href="JavaScript:void(0)" class="am-btn am-btn-default am-btn-xs am-text-secondary">&nbsp;学校审核通过</a>
                  	</c:when>
                  	<c:when test="${task.tState==5 }">
	                  	<a href="${pageContext.request.contextPath }/col/findDataByProId.do?&currentPage=1&pId=${task.pId }" class="am-btn am-btn-default am-btn-xs am-text-danger">&nbsp;查看</a>
	                  	<a href="${pageContext.request.contextPath }/col/submitData.do?colid=${col.cId }&pId=${task.pId }&tId=${task.tId }" class="am-btn am-btn-default am-btn-xs am-text-secondary">&nbsp;提交</a>
	                  	<a href="${pageContext.request.contextPath }/col/callBackData.do?colid=${col.cId }&pId=${task.pId }" onclick="return confirm('您的本次操作将清空您当前的所有数据。确认撤销？')" title="删除所有数据" class="am-btn am-btn-default am-btn-xs am-text-danger">&nbsp;撤销</a>
                  	</c:when>
                  </c:choose>
                   </div>
                </div>
              </td>
              <td>
              <c:if test="${task.tState !=1 }">
              	<a href="${pageContext.request.contextPath }/edu/download.do?path=${task.pUrl }&name=${task.pName }">下载</a>
              </c:if>
              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
        
       <div class="am-cf">
  			共${pb.totalRecords}条记录 ,${pb.currentPage}页/共${pb.totalPage}页
		  <div class="am-fr">
		    <ul class="am-pagination">
		      <li>
		      	<c:choose>
			    	<c:when test="${pb.currentPage==1}">
			    		<a href="javascript:void(0)" onclick="alert('已经是首页')">
					        «
					     </a>
			    	</c:when>
			    	<c:otherwise>
			    		<a href="${pageContext.request.contextPath }/col/proList.do?currentPage=${pb.currentPage-1}" >
					        «
					    </a>
			    	</c:otherwise>
			    </c:choose>
		      </li>
		     <c:forEach begin="${pb.startPage}" end="${pb.endPage}" var="num" step="1">
		          <li ${pb.currentPage==num?"class=' am-active'":"" }><a href="${pageContext.request.contextPath }/col/proList.do?currentPage=${num}">${num}</a></li>
		      </c:forEach>
		     	 <li>
		      <c:choose>
			    	<c:when test="${pb.currentPage==pb.totalPage}">
			    		<a href="javascript:void(0)" onclick="alert('已经是最后一页')">
					        »
					      </a>
			    	</c:when>
			    	<c:otherwise>
			    		 <a href="${pageContext.request.contextPath }/col/proList.do?currentPage=${pb.currentPage+1}">
					       »
					      </a>
			    	</c:otherwise>
			    </c:choose>
		      </li>
		    </ul>
		  </div>
		</div>
		
          <hr />
          <p>注：学校账号列表</p>
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
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/amazeui.min.js"></script>
<!--<![endif]-->
<script src="assets/js/app.js"></script>
</body>
</html>
