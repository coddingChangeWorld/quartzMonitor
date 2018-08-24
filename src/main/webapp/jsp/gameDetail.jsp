<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'gameDetail.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<div
		ne-module="/products/relottery/platform/modules/matchdetail/main/main.js"
		class="match-area">
		<div class="match-side z-small-win">

			<div ne-if="{{!liveAnimationUrl}}" class="matchinfo">
				<div class="name-time">
					<span ne-html="{{matchData.leagueMatch.leagueName}}"
						class="matchname">NBA</span><span class="midline"> | </span><span
						ne-html="{{matchData.matchTime}}" class="matchtime">11/03
						08:00</span>
				</div>
				<div class="b-status">
					<p ne-html="{{matchData.matchStatusName}}" class="matchstatus">未开始</p>
				</div>
				<!-- 篮球 Start-->
				<div class="team-info" ne-if="{{matchData.lotteryCategoryId==2}}">
					<div class="b-team">
						<div class="b-teamicon">
							<img ne-src="{{matchData.guestTeam.teamIcon}}" class="teamicon"
								src="https://relottery.nosdn.127.net/match/basketball/2_25.jpg?imageView&amp;thumbnail=44y44&amp;quality=85">
						</div>
						<div title="勇士" class="teamname">勇士</div>
					</div>

					<div class="vsicon"
						ne-if="{{matchData.matchStatus!=2 &amp;&amp; matchData.matchStatus!=3}}">VS</div>
					<div class="b-team second">
						<div class="b-teamicon">
							<img ne-src="{{matchData.homeTeam.teamIcon}}" class="teamicon"
								src="https://nos.netease.com/relottery/match/basketball/2_26.jpg?imageView&amp;thumbnail=44y44&amp;quality=85">
						</div>
						<div title="马刺" class="teamname">马刺</div>
					</div>
				</div>
				<!-- 篮球 End-->

				<!-- 篮球 Start-->

				<!-- 篮球 End-->

			</div>
			<ul class="navlist">
				<li ne-click="handleNavItemClick()" ne-role="tab-nav"
					class=" navitem active"><span>方案<em
						ne-html="{{'('+matchData.threadCount+')'}}" class="schemenum">(1)</em>
				</span></li>
				<li ne-click="handleNavItemClick()" ne-role="tab-nav"
					class=" navitem"><span>指数<em
						ne-html="{{'('+matchData.threadCount+')'}}" class="schemenum"
						style="display: none;">(1)</em>
				</span></li>
				<li ne-click="handleNavItemClick()" ne-role="tab-nav"
					class=" navitem"><span>实况<em
						ne-html="{{'('+matchData.threadCount+')'}}" class="schemenum"
						style="display: none;">(1)</em>
				</span></li>
				<li ne-click="handleNavItemClick()" ne-role="tab-nav"
					class=" navitem"><span>统计<em
						ne-html="{{'('+matchData.threadCount+')'}}" class="schemenum"
						style="display: none;">(1)</em>
				</span></li>
				<li ne-click="handleNavItemClick()" ne-role="tab-nav"
					class=" navitem"><span>情报<em
						ne-html="{{'('+matchData.threadCount+')'}}" class="schemenum"
						style="display: none;">(1)</em>
				</span></li>
				<li ne-click="handleNavItemClick()" ne-role="tab-nav"
					class=" navitem"><span>阵容<em
						ne-html="{{'('+matchData.threadCount+')'}}" class="schemenum"
						style="display: none;">(1)</em>
				</span></li>
			</ul>
			<div ne-click="handleFollowBtnClick()"
				class=" g-big-followbtns unfollowed">
				<div class=" followbtn">
					<em ne-html="{{matchData.isFollowFetching?'关注中...':'+关注'}}">+关注</em>
				</div>
				<div class=" unfollowbtn">
					<em ne-html="{{matchData.isFollowFetching?'取消中...':'已关注'}}"
						class="isfollow">已关注</em><em
						ne-html="{{matchData.isFollowFetching?'取消中...':'取消关注'}}"
						class="unfollow">取消关注</em>
				</div>
			</div>
			<div class="copyright">
				<span class="copyright-icon">&copy;</span> 1997-2017 网易公司版权所有
			</div>
		</div>
		<div class="match-main">
			<div class="matchcontent" ne-role="tab-body" style="" ne-module="">
				<div ne-role="schemeList" ne-module="">
					<div class="schemerelist">
						<div class="cardList" style="visibility: visible; height: 232px;">
							<div class=" cardItem card_0"
								style="left: 0px; top: 0px; visibility: visible; margin-top: 0px;">

								<!--顶部头像等 begin-->
								<div ne-if="{{state.type != 'expertPerson'}}" class=" cardtop">
									<div class="expertinfo">
										<div class="experthead">
											<a href="/expertpage.html?expertId=24487"
												ne-href="/expertpage.html?expertId={{cardItem.expert.userId}}">
												<img
												onerror="javascript:this.src='https://img1.cache.netease.com/f2e/products/relottery/platform/images/avatar_60.png';"
												ne-src="{{cardItem.expert.avatar?cardItem.expert.avatar+'?imageView&amp;thumbnail=45y45&amp;quality=85':'https://img1.cache.netease.com/f2e/products/relottery/platform/images/avatar_60.png'}}"
												class="headimg"
												src="https://nos.netease.com/relottery/user/20170324/AMcQ9S.jpg?imageView&amp;thumbnail=45y45&amp;quality=85">

											</a>
										</div>
										<div class="expertintro">
											<div class="expertintroTop">
												<div class=" expertname">
													<a href="/expertpage.html?expertId=24487"
														ne-html="{{cardItem.expert.nickname}}"
														ne-href="/expertpage.html?expertId={{cardItem.expert.userId}}">九口字</a>
												</div>
												<div ne-if="!cardItem.indexExpertScheme"
													class="attentionBtn">
													<div class="unattention"
														ne-click="follow(cardItem.expert.userId,'expert',__i)">+关注</div>
													<div class="attention"
														ne-click="unfollow(cardItem.expert.userId,'expert',__i)"
														style="display: none;">
														<div class="hasfollow">已关注</div>
														<div class="hasfollow_hover">取消关注</div>
													</div>
												</div>
											</div>
											<div class="expertslogan">
												<span ne-html="{{cardItem.expert.slogan}}">资深媒体人</span>
												<div ne-if="!cardItem.indexExpertScheme"
													class="contributeinfo">
													<span
														ne-if="cardItem.expert.maxWin&gt;1&amp;&amp;!cardItem.expert.leagueMatchName"
														class="rednum">8连红</span> <span
														ne-if="cardItem.expert.bAllRate&amp;&amp;!cardItem.expert.leagueMatchName"
														class="rednum">近3场中1场</span>

												</div>
											</div>
										</div>
									</div>






								</div>
								<!--顶部头像等 end-->
								<!--命中率 只在首页展示 begin-->

								<!--命中率 只在首页展示 end-->
								<!--详细比赛 begin-->
								<div
									ne-if="{{(state.type != 'followexpertred' &amp;&amp; state.type != 'userexpert') || cardItem.hasThread}}"
									class="carddetail">
									<div class="schemetitle">
										<span ne-html="{{plockstatus[cardItem.plock]}}"
											ne-if="cardItem.plock!=3 &amp;&amp; state.type != 'index'"
											class=" status01">未开始</span><a
											ne-html="{{cardItem.threadTitle}}"
											href="/schemedetail.html?threadId=44955"
											ne-href="/schemedetail.html?threadId={{cardItem.threadId}}">西部焦点复仇大战勇士VS马刺+湖人VS开拓者让分胜负组合推荐！</a>
									</div>


								</div>
								<!--详细比赛 end-->
								<div
									ne-if="{{(state.type != 'followexpertred' &amp;&amp; state.type != 'userexpert') || cardItem.hasThread}}"
									class="cardbottom">
									<span ne-if="{{state.type != 'userfavorite'}}"
										class="publishtime">1小时前发布</span> <span class="midline"></span>
									<div class="returnBean" style="display: none;">
										已退豆
										<div class="returnInfo">购买方案异常，已退还红豆。</div>
									</div>
									<span class="midline" style="display: none;"></span> <span
										ne-html="{{cardItem.showType===3?cardItem.price+'红豆':'免费'}}"
										ne-if="{{cardItem.showType!=1 &amp;&amp; !cardItem.indexExpertScheme &amp;&amp; state.type != 'userorder'}}"
										class="redbean">88红豆</span>

								</div>
								<!--关注和分享 begin-->
								<div
									ne-if="{{!cardItem.indexExpertScheme &amp;&amp; ((state.type != 'followexpertred' &amp;&amp; state.type != 'userexpert') || cardItem.hasThread)}}"
									class="share-collect">
									<div ne-click="toggleCollect(cardItem.threadId,__i)"
										class=" b-collect nocollect collect0">
										<span class="collect-text">已收藏</span>
									</div>
									<div class="b-share">
										<div class="ne-shares-parent horizontal">
											<div
												ne-module="/products/relottery/platform/modules/shares/shares.js">
												<div ne-role="share-wrap" class="ne-shares-pop3x1wrap"
													style="visibility: hidden;">
													<div class="ne-shareswrap-connect"></div>
													<div class="ne-shareswrap-arr"></div>
													<ul class="ne-shares-pop3x1">
														<li><a class="ne-shares-weixin" target="_self"
															href="javascript:" ne-mouseover="initWeixin()"> <span
																class="inner"> <i
																	class="ep-share-icon ep-share-weixin"></i> </span>
																<div class="ne-shares-qrwrap">
																	<div class="ne-shares-qrarr"></div>
																	<div class="ne-shares-qrcode" ne-role="qrcode"></div>
																</div> </a></li>
														<li><a href="javascript:" ne-click="share('sina')">
																<span class="inner"> <i
																	class="ep-share-icon ep-share-sina"></i> </span> </a></li>
														<li class="last"><a href="javascript:"
															ne-click="share('qzone')"> <span class="inner">
																	<i class="ep-share-icon ep-share-qzone"></i> </span> </a></li>
													</ul>
												</div>
											</div>
										</div>
									</div>
								</div>
								<!--分享关注 end-->

							</div>
						</div>






						<div class="noData" ne-if="{{state.type === 'matchdetail'}}"
							style="display: none;">
							<div class="noData_bg"></div>
							<div class="noData_text">
								<span>还没有专家发布方案~</span><a target="_self" href="/index.html"><div
										class="goandsee">别处看看</div>
								</a>
							</div>
						</div>
						<div class="cardLoading" style="display: none;"></div>
						<div class="cardNomore" style="display: block;">没有更多了</div>
					</div>
				</div>
			</div>
			<div class="matchcontent" ne-role="tab-body" style="display: none;">
			</div>
			<div class="matchcontent" ne-role="tab-body" style="display: none;">
			</div>
			<div class="matchcontent" ne-role="tab-body" style="display: none;">
			</div>
			<div class="matchcontent" ne-role="tab-body" style="display: none;">
			</div>
			<div class="matchcontent" ne-role="tab-body" style="display: none;">
			</div>
		</div>
	</div>
</body>
</html>
