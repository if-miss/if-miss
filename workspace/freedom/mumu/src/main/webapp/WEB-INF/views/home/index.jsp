<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>木木私厨</title>
<style type="text/css">
body, div, a, iframe, h1, h2, h3, h4, h5, ul, li, span, label {
	margin: 0px;
	padding: 0px;
}

a { /*去掉下划线  */
	text-decoration: none;
}

a:HOVER {
	color: red; /*设置鼠标悬停的效果*/
}

li {
	list-style: none; /*去掉默认前面的原点*/
	/* text-align: center; */
	margin: 4px 5px;
	float: left;
}

#top {
	height: 40px;
	width: 100%;
	background-color: orange;
}

#toptitle {
	text-align: center;
}

#loginInfo {
	text-align: left;
	width: 50%;
}

#title {
	height: 20px;
	width: 100%;
	background-color: Beige;
}

#body {
	width: 100%;
	height: 100%;
	text-align: center;
}

#iframe {
	height: 100%;
	width: 100%;
	border: 0px;
	text-align: center;
}

#foot {
	height: 50px;
	width: 100%;
	text-align: center;
	width: 100%;
}
</style>
<script type="text/javascript">
	$(function() {

		$("body").height(document.body.clientHeight);

		$("#clickMe").click(function() {

			alert(window.screen.height + "-----" + document.body.clientHeight);

		});

	});
</script>
</head>
<body>
	<div id="top">
		<div id="loginInfo">
			<a href="${ctx }/user/login">登录</a>
		</div>
		<div id="toptitle">
			<h4>依依舞蹈工作室</h4>
		</div>
	</div>
	<div id="title">
		<ul>
			<li><a target="bodys" href="${ctx }/function/html">html5展示</a></li>
			<li><a href="javascript:void(0)"
				onclick="window.location.href='${ctx }/'" target="bodys">啥啥</a></li>
			<%-- <li><a href="javascript:void(0)"
			onclick="window.location.href='${ctx }/user/exprotExcel'">下载excel</a></li> --%>
			<li><a target="bodys" href="${ctx }/function/echarts">echarts页面</a></li>
			<li><a href="javascript:void(0)" id="clickMe">单击呀</a></li>
		</ul>

	</div>
	<div id="body">
		<iframe id="iframe" name="bodys" src="${ctx }/function/home">
		</iframe>
	</div>
	<div id="foot">
		COPYRIGHT&nbsp;&copy;&nbsp;2014-2025&nbsp;make_a_difference有限公司&nbsp;ALL&nbsp;RIGHT&nbsp;RESERVED<br />
		热线：110-119-120&nbsp;Email:alongsmile@126.com<br /> ICP:泸ICP备05021104号<br />
		<br /> <br />
	</div>
</body>
</html>