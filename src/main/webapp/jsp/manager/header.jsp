<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="nav-bar">
	<div class="nav-panel">
		<div class="inner-nav-panel">
			<!-- <div class="logo">
				<a href="/"><img alt="首页" src='<c:url value="/static/img/logo.png"/>'> </a>
			</div> -->
			<div class="buttons">
				<sec:authorize access="isAuthenticated()"><a href="#">当前登录用户：${pageContext.request.userPrincipal.name}</a></sec:authorize>
			</div>
			<div class="c"></div>
		</div>
	</div>
</div>
<ul id="navigation">
	<li><span class="active">Overview</span></li>
	<li><a href="#">News</a></li>
	<li><a href="#">Users</a></li>
	<li><a href="./nba/teamList">list</a></li>
	<li><a href="./nba/gameList">games</a></li>
</ul>
