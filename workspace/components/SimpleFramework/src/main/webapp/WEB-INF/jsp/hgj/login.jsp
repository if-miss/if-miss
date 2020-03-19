<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
<style>
body {
	margin: 0;
	position: relative;
	font-size: 12px;
}

.main {
	position: fixed;
	top: 120px;
	left: 50%;
	border: 2px #00e solid;
	background-color: #eee;
	border-radius: 5px;
	width: 500px;
	height: 500px;
	margin-left: -250px;
	overflow: hidden;
}

.title {
	text-align: center;
	font-size: 20px;
	font-style: bold;
	color: #FFF;
	background-color: #00e;
	border: 2px;
	height: 30px;
}

.table {
	width: 100%;
	border-collapse: collapse;
	border: 1px #00f solid;
}

tr, td {
	height: 30px;
	border-collapse: collapse;
	/* border: 1px #00f solid; */
}

.label {
	background-color: #AAE;
	text-align: right;
	font-style: bold;
}

.content {
	padding: 5px;
	height: 100%;
	background-color: #FFF;
}

.info {
	position: relative;
	top: 200px;
	text-align: center;
	font-size: 15px;
	color: #F00;
}
</style>
<script type="text/javascript">
	$(function() {
		//默认隐藏注册信息
		$("#register").hide();
		//去注册页面
		$("#to_register").click(function() {
			$(".title").html("用户注册");
			$("#login").hide();
			$("#register").show();
		});
		//去登陆页面
		$("#to_login").click(function() {
			$(".title").html("用户登录");
			$("#register").hide();
			$("#login").show();
		});
		//登录校验
		$("#sub_log").click(function() {
			$("#reminder").html("");
			if ($("#userName").val() == "") {
				$("#reminder").html("账号密码或密码不正确");
				return false;
			}
			if ($("#passWord").val() == "") {
				$("#reminder").html("账号密码或密码不正确");
				return false;
			}
			$("#log_form").submit();
		});

		//注册校验
		$("#sub_reg").click(function() {
			$("#userName").val();
			$("#reg_form").submit();
		});
	});
</script>


</head>
<body>
	<div>
		<div class="main">
			<div class="title">用户登录</div>
			<div id="login" align="center">
				<br />
				<br />
				<br />
				<br />
				<br />
				<br />
				<br />
				<br />
				<form:form action="${ctx }/userLongin.json" method="post"
					modelAttribute="user" id="log_form">
					<table>
						<tr>
							<td>用户名:</td>
							<td><form:input path="loginName" /></td>
						</tr>
						<tr>
							<td>密&nbsp;&nbsp;码:</td>
							<td><form:input path="password" /></td>
						</tr>
						<tr>
							<td align="center" colspan="2"><input type="button"
								value="登录" id="sub_log" />&nbsp;&nbsp;&nbsp; <input
								type="button" value="注册" id="to_register" /></td>
						</tr>
					</table>
				</form:form>
			</div>
			<!-- 注册 -->
			<div id="register" align="center">
				<br /> 
				<br /> 
				<br /> 
				<br /> 
				<form:form action="${ctx }/addUser" method="post"
					modelAttribute="user" id="reg_form">
					<table>
						<tr>
							<td>登录名:</td>
							<td><form:input path="loginName" /></td>
						</tr>
						<tr>
							<td>设置密码:</td>
							<td><form:input path="password" /></td>
						</tr>
						<tr>
							<td>确认密码:</td>
							<td><input type="text" id="twoPW" /></td>
						</tr>
						<tr>
							<td>用户名:</td>
							<td><form:input path="userName" /></td>
						</tr>
						<tr>
							<td>邮箱:</td>
							<td><form:input path="userMail" /></td>
						</tr>
						<tr>
							<td align="center" colspan="2"><input type="button"
								value="注册" id="sub_reg" />&nbsp;&nbsp;&nbsp; <input
								type="button" value="登录" id="to_login" /></td>
						</tr>
					</table>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>
