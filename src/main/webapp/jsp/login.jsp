<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登陆</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/components/zxy/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/components/zxy/jquery.json-2.3.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/components/zxy/common-base.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/components/zxy/cert.js"></script>
	<link rel="stylesheet" href="./components/layui/css/layui.css">
  	<link rel="stylesheet" href="./styles/login.css">
  </head>
  
  <body>
    <div class="kit-login">
    <div class="kit-login-bg"></div>
    <div class="kit-login-wapper">
      <h2 class="kit-login-slogan">欢迎使用</h2>
      <div class="kit-login-form">
        <h4 class="kit-login-title">登录</h4>
        <form class="layui-form" action="/quartzMonitor/j_spring_security_check" method="post">
          <div class="kit-login-row">
            <div class="kit-login-col">
              <i class="layui-icon">&#xe612;</i>
              <span class="kit-login-input">
                <input type="text" name="j_username" lay-verify="required" placeholder="用户名/邮箱/手机号" />
              </span>
            </div>
            <div class="kit-login-col"></div>
          </div>
          <div class="kit-login-row">
            <div class="kit-login-col">
              <i class="layui-icon">&#xe64c;</i>
              <span class="kit-login-input">
                <input type="password" name="j_password" lay-verify="required" placeholder="密码" />
              </span>
            </div>
            <div class="kit-login-col"></div>
          </div>
          <div class="kit-login-row">
            <div class="kit-login-col">
              <input type="checkbox" name="rememberMe" title="记住帐号" lay-skin="primary">
            </div>
          </div>
          <div class="kit-login-row">
            <button class="layui-btn kit-login-btn" lay-submit="submit" lay-filter="login_hash">登录</button>
          </div>
          <div class="kit-login-row" style="margin-bottom:0;">
            <a href="javascript:;" style="color: rgb(153, 153, 153); text-decoration: none; font-size: 13px;" id="forgot">忘记密码</a>
          </div>
        </form>
      </div>
    </div>
  </div>

  <script src="./components/polyfill.min.js"></script>
  <script src="./components/layui/layui.js"></script>
  <script>
    layui.use(['layer', 'form'], function() {
      var form = layui.form,
        $ = layui.jquery;

      $('#forgot').on('click', function() {
        layer.msg('请联系管理员.');
      });
      //监听提交
      /*form.on('submit(login_hash)', function(data) {
        layer.msg(JSON.stringify(data.field));
        setTimeout(function(){
            location.href='/';
        },1000);

        return false;
      });
    });*/
  </script>
  </body>
</html>
