<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>Index</title>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="./components/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="./styles/app.css" media="all" />
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body class="kit-theme" style="height: 100%">
	<div style="margin:auto;width:960px;" class="layui-layout layui-layout-admin">

		<ul class="layui-nav">
			<li class="layui-nav-item"><a href="./manager/index">控制台<!-- <span class="layui-badge">9</span> --> </a>
			</li>
			<sec:authorize access="isAnonymous()">
			<li class="layui-nav-item"><a href="./login/index">登陆</a>
			</li>
			<li class="layui-nav-item"><a href="">注册</a>
			</li>
			</sec:authorize>
			<sec:authorize access="isAuthenticated()">
			<li class="layui-nav-item"><a href="">个人中心<span class="layui-badge-dot"></span> </a>
			</li>
			<li class="layui-nav-item" lay-unselect=""><a href="javascript:;"><img src="http://t.cn/RCzsdCq" class="layui-nav-img">${pageContext.request.userPrincipal.name}</a>
				<dl class="layui-nav-child">
					<dd>
						<a href="javascript:;">修改信息</a>
					</dd>
					<dd>
						<a href="javascript:;">安全管理</a>
					</dd>
					<dd>
						<a href="javascript:;">退出</a>
					</dd>
				</dl>
			</li>
			</sec:authorize>
		</ul>

		<div style="padding: 20px; background-color: #F2F2F2;">
			<div id="view" class="layui-row layui-col-space15">
				
			</div>
		</div>
	</div>
	<script id="gameItem" type="text/html">
	{{#  layui.each(d.data, function(index, item){ }}
  		<div class="layui-col-md7">
					<div class="layui-card">
						<div class="layui-card-header">进行中/未开始/已结束<button pid="{{ item.id }}" id="publish" style="float:right;margin-top:6px;" class="layui-btn layui-btn-sm">发布</button></div>
						<div class="layui-card-body" style="margin:auto;">
							<div class="item-vtm">
							 <img type="image/svg+xml" src="./image/nba/{{ item.awayAbbr }}_logo.svg" class="team-img clear">
							<span class="vtm-abbr">{{ item.awayName }}</span>
							</div>
							<div class="scoreboard">
								<span class="winner">{{ item.awayScore }}</span>
								<span class="boxscore-dash">-</span>
								<span>{{ item.homeScore }}</span>
							</div>
							<div class="item-htm">
								<img type="image/svg+xml" src="./image/nba/{{ item.homeAbbr }}_logo.svg" class="team-img clear">
								<span class="vtm-abbr">{{ item.homeName }}</span>
							</div>
							<div class="rule-content">
							    <ul>
							    	<li>方案：winorlose <span class="line"></span> </li>
							    	<li>方案：bigorsmall <span class="line"></span></li>
							    </ul>
							</div>
							<!--<div class="clear"></div>-->
						</div>
					</div>
				</div>
	  {{#  }); }}
 	  {{#  if(d.data.length === 0){ }}
    		无数据
  		{{#  } }} 
	</script>
	<script src="./components/layui/layui.js"></script>
	<script>
		var message;
		layui.config({
			base : './components/',
			version : '1.0.1'
		}).use([ 'app', 'message','jquery','layer','laytpl'],
				function() {
					var app = layui.app, $ = layui.jquery, layer = layui.layer,laytpl = layui.laytpl;
					$.ajax({
		             type: "GET",
		             url: "./nba/webGameList",
		             //data: {username:$("#username").val(), content:$("#content").val()},
		             dataType: "json",
		             success: function(data){
		             			var getTpl = gameItem.innerHTML,view = document.getElementById('view');
		             			//alert(JSON.stringify(data.list));
								layui.laytpl(getTpl).render(data, function(html){
								  view.innerHTML = html;
								});
								
								$('#publish').on('click', function(){
								    layer.open({
								      type: 2,
								      title: 'iframe父子操作',
								      maxmin: false,
								      shadeClose: true, //点击遮罩关闭层
								      area : ['800px' , '520px'],
								      /* offset: [ 
								          $(window).height()-300
								          ,0
								        ], */
								      content: './nba/rulePublish?id='+$(this).attr("pid")
								    });
								  });
		             			 
		                      }
		         });
				});
		
	</script>
</body>
</html>
