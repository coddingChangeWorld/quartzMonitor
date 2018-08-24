<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">@import url('${pageContext.request.contextPath}/styles/style.css');</style>
<style type="text/css">@import url('${pageContext.request.contextPath}/styles/default/icon.css');</style>

<script type="text/javascript" src='<c:url value="/components/jquery/jquery-1.7.2.min.js"/>'></script>
<script type="text/javascript" src='<c:url value="/components/jquery/jquery.form.js"/>'></script>
<script type="text/javascript" src='<c:url value="/static/js/handlebars-1.0.0.js"/>'></script>
<script type="text/javascript" src='<c:url value="/static/global.js"/>'></script>
<script type="text/javascript">
<sec:authorize access="isAuthenticated()">
login=true;
</sec:authorize>
 $(document).ready(function(){
	 $("#side").leftHtmlParse();
 });

</script>
  </head>
  
  <body>
    <div class="wrapper">
		<%@ include file="header.jsp"%>
		<div class="main-content">
			<div id="side" class="side notfixed login_side fixedBottom" style="height: 560px;">
			</div>
		</div>
		<!-- main开始 -->
		<div id="main" class="main">
			<div class="main-sider">
			
			
			
				
				<div class="sider-box">
					
				</div>
				<div class="sider-box">
					
				</div>
				<div class="sider-box">
					
				</div>
			</div>
			<div class="c"></div>
			<a id="go-to-top" href="#top" rel="nofollow"
				style="display: block; left: 884.5px;">▲</a>
		</div>
		<!-- main结束 -->
	</div>
  </body>
</html>
