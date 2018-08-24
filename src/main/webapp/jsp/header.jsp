<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<div class="nav-bar">
	<div class="nav-panel">
		<div class="inner-nav-panel">
			<div class="logo">
				<a href="/"><img alt="首页" src="static/img/logo.png"> </a>
			</div>
			<div class="buttons">
				<a title="个人设置" href="./system/settings"><div
						class="icon icon-settings"></div>
				<sec:authorize access="hasRole('supervisor')">
				<a title="系统管理" href="./manager/index"><div
						class="icon icon-dashboard"></div>
				</a>
				</sec:authorize>
				<sec:authorize access="isAnonymous()">
				<a href="./user/register">现在注册</a> <a href="./user/login"> 登 录 </a>
				</sec:authorize>
				<a class="buttons-active" href="./">首页</a>
				<a href="./nba/teamList">列表</a>
				<a href="./nba/gameList">赛事</a>
			</div>
			
			<div class="c"></div>
		</div>
	</div>
</div>
