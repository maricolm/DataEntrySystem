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
    <title>添加用户</title>
    <!-- Bootstrap core CSS -->src="<c:url value='/commons/verifyCode.js'/>"
    <link href="<c:url value='/admin/assets/css/bootstrap.css'/>"  rel="stylesheet">
    <!--external css-->
    <!-- Custom styles for this template -->
    <link href="<c:url value='/admin/assets/css/style.css"  rel="stylesheet'/>">
    <link href="<c:url value='/admin/assets/css/style-responsive.css '/>" rel="stylesheet">
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
              	  <h5 class="centered"><a href="${admin.aId }"> ${admin.aAccount } </h5>
              	  	
                  <li class="mt">
                      <a class="active" href="javaScript:void(0)">
                          <i class="fa fa-dashboard"></i>
                          <span>分配账号</span>
                      </a>
                  </li>
                  <li class="mt">
                      <a href="<c:url value='/admin/findEdu.do' />">
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
          	<!-- BASIC FORM ELELEMNTS -->
          	<div class="row mt">
          		<div class="col-lg-12">
                  <div class="form-panel">
                  	  <h4 class="mb"><i class="fa fa-angle-right"></i> 添加用户</h4>
                      <form action="<c:url value='/admin/addEdu.do' />" class="form-horizontal style-form" method="post">
                          <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">账号</label>
                              <div class="col-sm-10">
                                  <input id="inAccount" type="text" class="form-control" name="eAccount" style="width: 50%" ><span id="inAccountError"></span>
                              </div>
                          </div>
                          <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">姓名</label>
                              <div class="col-sm-10">
                                  <input type="text" class="form-control test" name="eName"  style="width: 50%"><span></span>
                                 <!-- <span class="help-block">提示</span>-->
                              </div>
                          </div>
                          <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">处室名称</label>
                              <div class="col-sm-10">
                                  <input type="text" class="form-control test" name="eDepartment"  style="width: 50%"><span></span>
                              </div>
                          </div>
                          <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">电话号码</label>
                              <div class="col-sm-10">
                                  <input class="form-control test" type="text" name="ePhone"  style="width: 50%"><span></span>
                              </div>
                          </div>
                          <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">邮箱</label>
                              <div class="col-sm-10">
                                  <input type="text" class="form-control test" placeholder="xxx@163.com" name="eEmail"  style="width: 50%"><span></span>
                              </div>
                          </div>
                          <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">密码</label>
                              <div class="col-sm-10">
                                  <input class="form-control" id="disabledInput" type="text" placeholder="系统默认初试密码为： 000000" disabled  style="width: 50%">
                              </div>
                          </div>
                          
                          <div class="form-group">
                              <label class="col-lg-2 col-sm-2 control-label"></label>
                              <div class="col-lg-10">
                                  <input id="addQR" style="background-color: #68dff0;width: 50%;" class="form-control" type="submit" value="提交">
                              </div>
                          </div>
                      </form>
                  </div>
          		</div><!-- col-lg-12-->      	
          	</div><!-- /row -->
					</section><! --/wrapper -->
      </section><!-- /MAIN CONTENT -->

      <!--main content end-->
      <!--footer start
      <footer class="site-footer">
          <div class="text-center">
          	数据录入系统  @2018 四川省教育厅
              <a href="admin_index.html#" class="go-top">
                  <i class="fa fa-angle-up"></i>
              </a>
          </div>
      </footer>
      --footer end-->
  </section>

    <!-- js placed at the end of the document so the pages load faster -->
    <script src="assets/js/jquery.js"></script>
    <script src="assets/js/addUser.js"></script>
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

      

  </script>

  </body>
</html>