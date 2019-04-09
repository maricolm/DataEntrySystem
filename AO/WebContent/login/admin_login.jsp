<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录页面</title>
<link href="<c:url value='/login/css/style.css'/>" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<c:url value='/login/js/jquery-2.0.3.min.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/login/js/login.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/login/js/ajaxVCode.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/commons/verifyCode.js'/>" ></script>
<link rel="icon" type="image/png" href="${pageContext.request.contextPath }/edu/assets/i/favicon.png">
</head>

<body>
<div class="top">
  <ul>
    <li><a href="javascript:void(0)" class="hover">管理员登录</a></li>
    <li><a href="${pageContext.request.contextPath }/login/edu_login.jsp">教育厅账号登录</a></li>
    <li><a href="${pageContext.request.contextPath }/login/school_login.jsp">学校账号登录</a></li>
    <li><a href="${pageContext.request.contextPath }/login/col_login.jsp">二级学院账号登录</a></li>
  </ul>
</div>

<div class="main">
  <div class="denglu">
    <div class="text" style="font-size:13px;"><strong>重要提示：</strong><br />
      一、数据录入系统账号密码为上一级进行分配。<br />
      二、数据无价，请妥善保管好自己的账号和密码，如若泄露，请及时联系账号分配单位。<br />
    <font color="red">三、请在规定的相关时间内完成数据的录入，由相关单位审核后及时提交；如若出现必须延迟提交情况，请及时报告上级。</font></div>
    <div class="dlk">
    <form action="<c:url value='/admin/login.do' />"  method="post">
      <table width="300" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="76" colspan="3" style="text-align: center;color: #000000;font-size: 18px;">管理员登录入口</td>
        </tr>
        <tr>
          <td width="65" style="font-size:14px;">登录账号</td>
          <td colspan="2"><input name="aAccount" id="account" type="text" class="dlinput" /><span id="accountError" class="tips">&nbsp;</span></td>
        </tr>
        <tr>
          <td height="16" colspan="3"></td>
        </tr>
        <tr>
          <td>登录密码</td>
          <td colspan="2"><input name="aPswd" id="pswd" type="password" class="dlinput" /><span id="pswdError" class="tips">&nbsp;</span></td>
        </tr>
        <tr>
          <td height="16" colspan="3"></td>
        </tr>
        <tr>
          <td>验证码</td>
          <td width="45"><input id="vCode" name="textfield" type="text" class="dlinput" style="width:70px;" maxlength="4" /></td>
          <td width="121" ><img id="vCodes" onclick="change()" src="<c:url value='/VerifyCodeServlet'/>" width="90" height="30" /><span id="vCodeError" class="tips"></span></td>
        </tr>
        <tr>
          <td height="16" colspan="3"></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td colspan="2"><input type="submit" value="登 录" class="loginbtn" id="submitMsg"/></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td colspan="2"><table width="224" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td height="24"><a href="javaScript:void(0)" class="mm" onclick="alert('联系管理员重置秘密,重置成功后初始秘密为：0000')">忘记登录密码？</a></td>
              <td align="right"><a href="#none" class="zc" style="font-size:13px;">${msg }</a></td>
            </tr>
          </table></td>
        </tr>
      </table>
      </form>
    </div>
  </div>
</div>
<div class="footer">Copyright &copy; 2018 xxx.com All Rights Reserved　版权所有 四川教育厅高等教育处数据录入系统</div>
</body>
</html>
