<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
<style type="text/css">
#bg {
	position: absolute;
	top: 0%;
	left: 0%;
	width: 100%;
	height: 100%;
	background-image: url("${ctx}/img/login.jpg");
	z-index: 1001;
}

.float {
	position: absolute;
	top: 25%;
	left: 25%;
	width: 600px;
	height: 300px;
	padding: 3px;
	border: 2px solid #E8E9F7;
	background-color: white;
	z-index: 1002;
	overflow: auto;
	-moz-opacity: 0.9;
	opacity: .70;
	filter: alpha(opacity = 90);
}

.inputText {
	width: 160px;
}
</style>
<script type="text/javascript">
	$(function() {
		//默认隐藏注册信息
		$("#register").hide();
		//去注册页面
		$("#to_register").click(function() {
			$("#login").hide();
			$("#register").show();
		});
		//去登陆页面
		$("#to_login").click(function() {
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
		$("#sub_reg").click(function(){
			
			$("#userName").val();
			alert($("#userName").val());
			
			$("#reg_form").submit();
			
		});

	});
</script>
</head>
<body>
	<div id="bg"></div>
	<!-- 登录 -->
	<div class="float" align="center">
		<div id="reminder"></div>
		<div id="login">
			<form:form action="${ctx }/user/user" method="post"
				modelAttribute="user" id="log_form">
				<table>
					<tr>
						<td>用户名:</td>
						<td><form:input path="userName" /></td>
					</tr>
					<tr>
						<td>密&nbsp;&nbsp;&nbsp;&nbsp;码:</td>
						<td><form:input path="passWord" /></td>
					</tr>
					<tr>
						<td align="center" colspan="2"><input type="checkbox"
							name="state" value="1" />记住我</td>
					</tr>
					<tr>
						<td align="center" colspan="2"><input type="button"
							value="登录" id="sub_log" /></td>
					</tr>
					<tr>
						<td colspan="2"><a href="#" id="to_register">去注册</a></td>
					</tr>
				</table>
			</form:form>
		</div>
		<!-- 注册 -->
		<div id="register" align="center">
			<form:form action="${ctx }/user/insert" method="post"
				modelAttribute="user" id="reg_form">
				<table>
					<tr>
						<td>用户名:</td>
						<td><form:input path="userName" /></td>
					</tr>
					<tr>
						<td>设置密码:</td>
						<td><form:input path="passWord" /></td>
					</tr>
					<tr>
						<td>确认密码:</td>
						<td><input type="text" id="twoPW"/></td>
					</tr>
					<tr>
						<td align="center" colspan="2"><input type="button"
							value="注册" id="sub_reg" /></td>
					</tr>
					<tr>
						<td colspan="2"><a href="#" id="to_login">去登录</a></td>
					</tr>
				</table>
			</form:form>
		</div>
	</div>
</body>
</html>