<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=urf-8">
<title>首页</title>
<style type="text/css">@import url('${pageContext.request.contextPath}/styles/style.css');</style>
<style type="text/css">@import url('${pageContext.request.contextPath}/styles/default/icon.css');</style>

<script type="text/javascript" src='<c:url value="/components/jquery/jquery-1.7.2.min.js"/>'></script>
<script type="text/javascript" src='<c:url value="/components/jquery/jquery.form.js"/>'></script>
<script type="text/javascript" src='<c:url value="/static/js/handlebars-1.0.0.js"/>'></script>
<script type="text/javascript" src='<c:url value="/static/global.js"/>'></script>
<style type="text/css">

</style>
<script type="text/javascript">
<sec:authorize access="isAuthenticated()">
userInfo=<sec:authentication property="principal.json" htmlEscape="false"></sec:authentication> ;
</sec:authorize>
  
 $(document).ready(function(){
	 $("#side").leftHtmlParse();
 });

</script>
</head>
<body>
	<div class="wrapper">
		<%@ include file="./jsp/header.jsp"%>
		<div class="main-content">
			<div id="side" class="side notfixed login_side fixedBottom" style="height: 560px;">
				<%--<!--个人信息-->
				<sec:authorize access="isAuthenticated()">
				<div class="userInfo" style="display: block;">
					<div class="userHead">
						<img src="https://img1.cache.netease.com/f2e/products/relottery/platform/images/avatar_100.png">
						<div class="nicknameFirst">j</div>
					</div>
					<div class="nickname">
						<span>${pageContext.request.userPrincipal.name}</span>
						 <a target="_self" href="/modifyinfo.html"><div class="editorInfo"></div></a>
					</div>
					<div class="info_list" id="info_list">
						<li class="order">
							<div class="icon"></div> <a target="_self"
							href="/user.html#order"><span>我的订单</span><span class="gray"
								style="display: none;">(0)</span>
						</a></li>
						<li class="favorite">
							<div class="icon"></div> <a target="_self"
							href="/user.html#favorite"><span>我的收藏</span><span
								class="gray" style="display: none;">(0)</span>
						</a></li>
						<li class="expert">
							<div class="icon"></div> <a target="_self"
							href="/user.html#expert"><span>我关注的专家</span><span
								class="gray" style="display: none;">(0)</span>
						</a></li>
						<li class="match">
							<div class="icon"></div> <a target="_self"
							href="/user.html#match"><span>我关注的赛事</span><span class="gray"
								style="display: none;">(0)</span>
						</a></li>
						<li class="coupon">
							<div class="icon"></div> <a target="_self"
							href="/user.html#coupon"><span>我的方案</span>
						</a></li>
						<li class="hongdou">
							<div class="icon"></div> <a target="_self" href="/recharge.html"><span>我的红豆</span><span
								class="red">(0)</span>
						</a> <a target="_self" href="/recharge.html"><div class="pay">充值</div>
						</a>
							<div class="rechargeGuide" style="display: none;">
								<div ne-click="closeRechargeGuide()" class="rechargeGuide_close"></div>
								剩余红豆不多了。为避免错过竞彩赛事分析，请及时充值！
							</div></li>
					</div>
				</div>
				</sec:authorize>
				<!--未登录时状态-->
				<sec:authorize access="isAnonymous()">
				<div class="hongcaiInfo" style="display: block;">
		            <div class="info_focus">
		                <div class="info_focus-body">
		                    <div class="info_focus-list">
		                        <div class="info_focus_item">
		                            <img alt="" src="https://static.ws.126.net/f2e/products/relottery/platform/images/index_side_unlogin.jpg">
		                        </div>
		                    </div>
		                </div>
		            </div>
            <div class="info_login"><span ne-click="login()">登录</span>|<span ne-click="register()">注册</span></div>
        </div>
				</sec:authorize>
				<div class="copyright" style="display: block;">
					<span class="copyright-icon">&copy;</span> 1997-2017 公司版权所有
				</div>
			--%></div>
		</div>
		<!-- main开始 -->
		<div id="main" class="main">
			<div class="main-sider">
			<sec:authorize access="isAuthenticated()">
					  <div class="sider-box">
						<div class="sider-box-title">
							用户面板<span class="float-right"><a
								href="./j_spring_security_logout">退出</a>
							</span>
						</div>
						<div class="sider-box-content">
							<div class="User-Pannel-Avatar">
								<a href="/u/admin"> <img alt="admin"
									src="./image/avatar/large/1.png"> </a>
							</div>
							<div class="User-Pannel">
								<div class="User-Pannel-Name">
									<a href="/u/admin">${pageContext.request.userPrincipal.name}</a>
								</div>
								<ul>
									<li><a href="/favorites"> <strong>1</strong> <span>收藏的帖子</span>
									</a></li>
									<li><a href="/tags/following"> <strong>0</strong> <span>关注的话题</span>
									</a></li>
									<li><a href="/users/following"> <strong>1</strong> <span>关注的用户</span>
									</a></li>
								</ul>
							</div>
							<div class="c"></div>
						</div>
					</div>
				</sec:authorize>
			
			<sec:authorize access="isAnonymous()">
				 <div class="sider-box">
					<div class="sider-box-title">登 录</div>
					<div class="sider-box-content">
						<div style="width:226px;" class="center-align">
							<form
								onsubmit="JavaScript:this.Password.value=md5(this.Password.value);"
								method="post" action="/login">
								<input type="hidden" name="ReturnUrl" value="/"> <input
									type="hidden" value="c5ff8004" name="FormHash"> <input
									type="hidden" value="30" name="Expires">
								<p>
									<label><input type="text" onblur="CheckUserNameExist()"
										placeholder="用户名" value="" class="w200" id="UserName"
										name="UserName"> </label>
								</p>
								<p>
									<label><input type="password" placeholder="密码" value=""
										class="w200" name="Password"> </label>
								</p>
								<p>
									<label><input type="text" placeholder="验证码" value=""
										onfocus="document.getElementById('Verification_Code_Img').src='/seccode.php';document.getElementById('Verification_Code_Img').style.display='inline';"
										class="w100" name="VerifyCode"> </label> <img align="middle"
										alt="验证码" onclick="this.src+=''"
										style="cursor: pointer;display:none;"
										id="Verification_Code_Img" src="">
								</p>
								<p>
									<input type="submit" style="margin:0 78px;" class="textbtn"
										name="submit" value=" 登 录 ">
								</p>
								<p class="fs14 text-center">
									<a href="/register">现在注册</a>&nbsp;&nbsp;&nbsp;&nbsp; <a
										href="/forgot">忘记密码</a>
								</p>
							</form>
						</div>
						<p>
							<a href="/oauth-1"> <img alt="QQ  登 录 "
								src="${pageContext.request.contextPath}/static/img/oauth/qq_icon_label.png"> </a>&nbsp; <a
								href="/oauth-2"> <img alt="Weibo  登 录 "
								src="${pageContext.request.contextPath}/static/img/oauth/weibo_icon.png"> </a>&nbsp; <a
								href="/oauth-3"> <img alt="GitHub  登 录 "
								src="${pageContext.request.contextPath}/static/img/oauth/github_icon.png"> </a>&nbsp;
						</p>
					</div>
				</div>
			</sec:authorize>
				
				<div class="sider-box">
					<div class="sider-box-title">
						话题广场 <span class="float-right"><a href="/tags">查看更多</a> </span>
					</div>
					<div class="sider-box-content btn">
						<a href="/tag/%E9%97%AE%E9%A2%98">问题</a> <a
							href="/tag/%E6%B5%8B%E8%AF%95">测试</a> <a
							href="/tag/%E5%BB%BA%E8%AE%AE">建议</a> <a
							href="/tag/%E6%89%8B%E6%9C%BA">手机</a> <a
							href="/tag/%E5%9E%83%E5%9C%BE%E4%BF%A1%E6%81%AF">垃圾信息</a> <a
							href="/tag/%E5%8A%9F%E8%83%BD">功能</a> <a
							href="/tag/%E5%9B%BE%E7%89%87">图片</a> <a href="/tag/Test">Test</a>
						<a href="/tag/%E5%AE%89%E8%A3%85">安装</a> <a
							href="/tag/%E8%AF%9D%E9%A2%98">话题</a> <a href="/tag/BUG">BUG</a>
						<a href="/tag/php">php</a> <a href="/tag/%E8%AE%BA%E5%9D%9B">论坛</a>
						<a href="/tag/Android">Android</a> <a href="/tag/VPS">VPS</a> <a
							href="/tag/%E7%BD%91%E7%AB%99">网站</a> <a href="/tag/MySQL">MySQL</a>
						<a href="/tag/%E5%AE%A2%E6%88%B7%E7%AB%AF">客户端</a> <a
							href="/tag/%E8%A7%86%E9%A2%91">视频</a> <a href="/tag/Rewrite">Rewrite</a>
					</div>
				</div>
				<div class="sider-box">
					<div class="sider-box-title">信息栏</div>
					<div class="sider-box-content">
						<a target="_blank" href="http://my.henghost.com/aff.php?aff=3741"><!--<img
							alt="购买PHP虚拟主机 / VPS" src="${pageContext.request.contextPath}/upload/virtual_host.jpg">--> </a>
						<p>Carbon Forum是一个基于话题的高性能轻型PHP论坛</p>
						<ul>
							<li><a target="_blank" href="http://m.94cb.com/">手机版：为智能手机设计</a>
							</li>
							<li><a target="_blank"
								href="http://app.mi.com/detail/119536">官网Android客户端下载</a></li>
							<li><a target="_blank"
								href="https://github.com/lincanbin/Carbon-Forum">Github:
									PC版与手机版源码</a></li>

							<li><a target="_blank"
								href="https://github.com/lincanbin/Android-Carbon-Forum">GitHub:
									编译自己网站的Android客户端</a></li>
							<li><a target="_blank"
								href="https://github.com/loveuqian/iOS-Carbon-Forum">GitHub:
									编译自己网站的iOS客户端(非官方)</a></li>
							<li><a target="_blank"
								href="https://github.com/lincanbin/Carbon-Forum-API-Documentation/blob/master/README.md">GitHub:
									客户端API文档</a></li>
						</ul>

						<p>
							下载地址：<a target="_blank" href="https://www.94cb.com/t/3404">Carbon
								Forum v5.0.1</a> <br>QQ群：<a target="_blank"
								href="http://jq.qq.com/?_wv=1027&amp;k=YG7gzK">12607708</a>（QQ我不常上）
						</p>
						<!--img alt="donate" src="/upload/donate_weixin.png" /><br /><p>微信扫描上方二维码可向本项目捐款</p-->
						<!--<img src="${pageContext.request.contextPath}/upload/donate_small.png" alt="donate">--><br>
						<p>手机支付宝扫描上方二维码可向本项目捐款</p>
						<!--p>PC端用户<a href="https://shenghuo.alipay.com/send/payment/fill.htm?optEmail=517038270@qq.com&payAmount=10&title=%be%e8%d4%f9Carbon+Forum%cf%ee%c4%bf%bf%aa%d4%b4%a3%a1" target="_blank">点这里可使用支付宝对本项目捐款</a></p-->
						<p></p>
						<form target="_blank" accept-charset="gb2312"
							action="https://shenghuo.alipay.com/send/payment/fill.htm"
							method="post">
							<input type="hidden" value="517038270@qq.com" name="optEmail">
							<input type="hidden" value="30" name="payAmount"> <input
								type="hidden" value="捐赠Carbon Forum项目开源！
	用户名：
	网站："
								name="title"> <input type="submit" value="PC端用户点此用支付宝捐赠"
								class="textbtn">
						</form>
						<p></p>


						<script type="text/javascript">
							var _hmt = _hmt || [];
							(function() {
								var hm = document.createElement("script");
								hm.src = "//hm.baidu.com/hm.js?4e67f0456adf220874993f52d65bfb4d";
								var s = document.getElementsByTagName("script")[0];
								s.parentNode.insertBefore(hm, s);
							})();
						</script>

						<!--script type="text/javascript">
//Google Analytics
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-46949668-2', 'auto');
  ga('send', 'pageview');

</script-->
						<div class="c"></div>
					</div>
				</div>
				<div class="sider-box">
					<div class="sider-box-title">站内统计</div>
					<div class="sider-box-content">
						<ul>
							<li>主题数量：2514</li>
							<li>回帖数量：14517</li>
							<li>话题数量：865</li>
							<li>用户数量：3810</li>
						</ul>
					</div>
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