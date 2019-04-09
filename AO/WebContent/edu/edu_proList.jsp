<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>项目列表</title>
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
          <li style="background: #0e90d2;"><a href='<c:url value="/edu/findPro.do?currentPage=1"/>'  style="color:white;"><span class="am-icon-th"></span>&nbsp;项目列表<span class="am-badge am-badge-secondary am-margin-right am-fr">${proCount }</span></a></li>
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
        <p>数据无价，请注意保护。</p>
      </div>
    </div>
  </div>
  <!-- sidebar end -->

  <!-- content start -->
  <div class="admin-content">

    <div class="am-cf am-padding">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">项目列表</strong> / <small>proList</small></div>
    </div>

    <div class="am-g">
      <div class="am-u-md-6 am-cf">
        <div class="am-fl am-cf">
          <div class="am-btn-toolbar am-fl">
            <div class="am-btn-group am-btn-group-xs">
              <a href="edu_addPro.jsp" type="button" class="am-btn am-btn-default"><span class="am-icon-plus"></span> 新增项目</a>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="am-g">
      <div class="am-u-sm-12">
        <form class="am-form">
          <table class="am-table am-table-striped am-table-hover table-main">
            <thead>
              <tr>
                <th class="table-title">项目名</th><th class="table-title">创建时间</th><th class="table-type">项目状态</th><th class="table-set">操作</th><th class="table-set">备注</th>
              </tr>
          </thead>
          <tbody>
	           <c:forEach items="${pb.beanList }" var="pro">
	            <tr>
	              <td>${pro.pName }</td>
	              <td>
	              	<fmt:formatDate value="${pro.pDate }" type="date" pattern="yyyy-MM-dd HH:mm"/>
	              </td>
	              <td>
	              	<c:choose>
	              		<c:when test="${pro.pState==1 }">
	              		未发布
	              		</c:when>
	              		<c:when test="${pro.pState==2 }">
	              		进行中
	              		</c:when>
	              		<c:when test="${pro.pState==3 }">
	              		已完成
	              		</c:when>
	              	</c:choose>
				  </td>
				  <td>
				  <c:choose>
				  	<c:when test="${pro.pState==1 }">
				  	<div class="am-btn-toolbar">
	                  <div class="am-btn-group am-btn-group-xs">
	                    <a href='<c:url value="/edu/publishPro.do?pId=${pro.pId }"/>' class="am-btn am-btn-default am-btn-xs am-text-success"><span class="am-icon-upload"></span>&nbsp;发布项目</a>
	                    <a href='<c:url value="/edu/findProAttrByPid.do?aPid=${pro.pId }" />' class="am-btn am-btn-default am-btn-xs am-text-default"><span class="am-icon-pencil"></span>&nbsp;编辑项目</a>
	                    <a href='<c:url value="/edu/deleteProByPid.do?aPid=${pro.pId }" />' class="am-btn am-btn-default am-btn-xs am-text-danger" onclick="return confirm('确认删除？')"><span class="am-icon-trash-o"></span>&nbsp;删除</a>
	                  </div>
	                </div>
				  	</c:when>
				  	<c:when test="${pro.pState==2 }">
		                <div class="am-btn-toolbar">
		                  <div class="am-btn-group am-btn-group-xs">
		                    <a href="${pageContext.request.contextPath }/edu/seeSchedule.do?currentPage=1&pId=${pro.pId}" class="am-btn am-btn-default am-btn-xs am-text-secondary"><span class="am-icon-eye"></span>&nbsp;查看进度</a>
		                    <a href="${pageContext.request.contextPath }/edu/dispensePro.do?currentPage=1&pId=${pro.pId}" class="am-btn am-btn-default am-btn-xs am-text-success"><span class="am-icon-adn "></span>&nbsp;分发任务</a>
		                    <a href="${pageContext.request.contextPath }/edu/endPro.do?pId=${pro.pId}" class="am-btn am-btn-default am-btn-xs am-text-danger" onclick="return confirm('确认结束项目？')"><span class="am-icon-trash-o"></span>&nbsp;结束项目</a>
		                  </div>
		                </div>
				  	</c:when>
				  	<c:when test="${pro.pState==3 }">
		                <div class="am-btn-toolbar">
		                  <div class="am-btn-group am-btn-group-xs">
		                   已完成
		                  </div>
		                </div>
				  	</c:when>
				  </c:choose>
				  </td>
				  <td>
				  <c:if test="${pro.pState !=1 }">
				 	 <a href="${pageContext.request.contextPath }/edu/seeDataAllOfProject.do?pid=${pro.pId}&currentPage=1"  class="am-btn am-btn-default am-btn-xs am-text-secondary">查看所有数据</a>
				 	<a href="${pageContext.request.contextPath }/edu/exportData.do?pid=${pro.pId}" class="am-btn am-btn-default am-btn-xs am-text-success">导出数据</a>
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
			    		<a href="${pageContext.request.contextPath }/edu/findPro.do?currentPage=${pb.currentPage-1}" >
					        «
					    </a>
			    	</c:otherwise>
			    </c:choose>
		      </li>
		     <c:forEach begin="${pb.startPage}" end="${pb.endPage}" var="num" step="1">
		          <li ${pb.currentPage==num?"class=' am-active'":"" }><a href="${pageContext.request.contextPath }/edu/findPro.do?currentPage=${num}">${num}</a></li>
		      </c:forEach>
		     	 <li>
		      <c:choose>
			    	<c:when test="${pb.currentPage==pb.totalPage}">
			    		<a href="javascript:void(0)" onclick="alert('已经是最后一页')">
					        »
					      </a>
			    	</c:when>
			    	<c:otherwise>
			    		 <a href="${pageContext.request.contextPath }/edu/findPro.do?currentPage=${pb.currentPage+1}">
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
