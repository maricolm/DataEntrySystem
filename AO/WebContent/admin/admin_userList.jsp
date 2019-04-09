<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Dashboard">
    <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
    <title>用户列表</title>
    <!-- Bootstrap core CSS -->
    <link href="assets/css/bootstrap.css" rel="stylesheet">
    <!--external css-->
    <link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="assets/css/zabuto_calendar.css">
    <link rel="stylesheet" type="text/css" href="assets/js/gritter/css/jquery.gritter.css" />
    <link rel="stylesheet" type="text/css" href="assets/lineicons/style.css">    
    <!-- Custom styles for this template -->
    <link href="assets/css/style.css" rel="stylesheet">
    <link href="assets/css/style-responsive.css" rel="stylesheet">
    <script src="assets/js/chart-master/Chart.js"></script>
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

  <section id="container" >
      <!-- **********************************************************************************************************************************************************
      TOP BAR CONTENT & NOTIFICATIONS
      *********************************************************************************************************************************************************** -->
      <!--header start-->
      <header class="header black-bg">
              <div class="sidebar-toggle-box">
                  <div class="fa fa-bars tooltips" data-placement="right" data-original-title="Toggle Navigation"></div>
              </div>
            <!--logo start-->
            <a href="JavaScript:void(0)" class="logo"><b>管理员界面</b></a>
            <!--logo end-->
           
            <div class="top-menu">
            	<ul class="nav pull-right top-menu">
                    <li><a class="logout" href="<c:url  value='/admin/logout.do'/>">退出登录</a></li>
            	</ul>
            </div>
      </header>
      <!--header end-->
      
      <!-- **********************************************************************************************************************************************************
      MAIN SIDEBAR MENU
      *********************************************************************************************************************************************************** -->
      <!--sidebar start-->
      <aside>
          <div id="sidebar"  class="nav-collapse ">
              <!-- sidebar menu start-->
              <ul class="sidebar-menu" id="nav-accordion">
              	  <p class="centered"><a href="profile.html"><img src="assets/img/ui-sam.jpg" class="img-circle" width="60"></a></p>
              	  <h5 class="centered"><a href="${admin.aId }"> ${admin.aAccount }</h5>
              	  	
                  <li class="mt">
                      <a href="<c:url value='admin_addUser.jsp'/>">
                          <i class="fa fa-dashboard"></i>
                          <span>分配账号</span>
                      </a>
                  </li>
                  <li class="mt">
                      <a class="active" href="javaScript:void(0)">
                          <i class="fa fa-th"></i>
                          <span>用户列表</span>
                      </a>
                  </li>
              </ul>
              <!-- sidebar menu end-->
          </div>
      </aside>
      <!--sidebar end-->
      
      <!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->
      <!--main content start-->
      <section id="main-content">
        <section class="wrapper">
        	<div class="row mt">
                  <div class="col-md-12">
                      <div class="content-panel">
                      <c:choose>
                      	<c:when test="${empty edu }">
                      		还没有用户额
                      	</c:when>
                      	<c:otherwise>
                      		 <table class="table table-striped table-advance table-hover">
	                  	  	  <h4><i class="fa fa-angle-right"></i>用户列表</h4>
	                  	  	  <hr>
                              <thead>
                              <tr>
                                  <th>姓名</th>
                                  <th>账号</th>
                                  <th>处室名称</th>
                                  <th>联系电话</th>
                                  <th>邮箱</th>
                                  <th>操作</th>
                              </tr>
                              </thead>
                              <tbody>
                              <c:forEach items="${edu }" var="edu">
                              	<tr>
                                  <td><a href="basic_table.html#"><span class="label label-info label-mini">${edu.eName }</span></a></td>
                                  <td class="hidden-phone">${edu.eAccount }</td>
                                  <td>${edu.eDepartment }</td>
                                  <td>${edu.ePhone }</td>
                                  <td>${edu.eEmail }</td>
                                  <td>
                                      <a  href="<c:url value='/admin/resetPswd.do?eId=${edu.eId }'/>"  title="重置密码" class="btn btn-primary btn-xs" ><i class="fa fa-pencil"></i></a>
                                      <a  href="<c:url value='/admin/deleteEdu.do?eId=${edu.eId }'/>"  title="删除用户" class="btn btn-danger btn-xs" onclick="return confirm('确认删除？');"><i class="fa fa-trash-o "></i></a>
                                  </td>
                               </tr>
                              </c:forEach>
                              </tbody>
                          </table>
                      	</c:otherwise>
                      </c:choose>
                      </div><!-- /content-panel -->
                  </div><!-- /col-md-12 -->
              </div><!-- /row -->
        </section>
      </section><!-- /MAIN CONTENT -->
      <!--main content end-->
  </section>

    <!-- js placed at the end of the document so the pages load faster -->
    <script src="assets/js/jquery.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script src="assets/js/jquery-ui-1.9.2.custom.min.js"></script>
    <script src="assets/js/jquery.ui.touch-punch.min.js"></script>
    <script class="include" type="text/javascript" src="assets/js/jquery.dcjqaccordion.2.7.js"></script>
    <script src="assets/js/jquery.scrollTo.min.js"></script>
    <script src="assets/js/jquery.nicescroll.js" type="text/javascript"></script>

    <!--common script for all pages-->
    <script src="assets/js/common-scripts.js"></script>
    <!--script for this page-->
  <script>
      //custom select box

      $(function(){
          $('select.styled').customSelect();
      });

  </script>

  </body>
</html>
    