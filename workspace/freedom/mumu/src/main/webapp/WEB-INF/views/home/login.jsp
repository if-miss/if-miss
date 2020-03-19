<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
<script type="text/javascript">
	$(function() {
		//默认隐藏注册信息
		$("#register").hide();
		//去注册页面
		$("#to_register").click(function() {
			$("#login").hide();
			$("#register").show();
			$("#reminder").html("");
		});
		//去登陆页面
		$("#to_login").click(function() {
			$("#register").hide();
			$("#login").show();
			$("#reminder").html("");
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
			if ($("#userName").val() == "") {
				$("#reminder").html("账号不能为空");
				return false;
			}
			$("#reg_form").submit();
		});
		//让div居中
		$("#bg").height(document.body.scrollHeight);
		$("#float").css("top",document.body.scrollHeight/2+"px");
	});
</script>
</head>
<body>
	<div id="bg"></div>
	<div id="float" align="center">
		<br /> <br /> <br />
		<!-- 错误信息 -->
		<div id="reminder"></div>
		<!-- 登录 -->
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
						<td><form:password path="passWord" /></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="checkbox"
							name="state" value="1" />记住我</td>
					</tr>
					<tr>
						<td align="center" colspan="2"><input type="button"
							value="登录" id="sub_log" />&nbsp;&nbsp;<input type="reset"
							value="重填" /></td>
					</tr>
					<tr>
						<td colspan="2"><a href="javascript:void(0);" id="to_register">去注册</a></td>
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
						<td><form:password path="passWord" /></td>
					</tr>
					<tr>
						<td>确认密码:</td>
						<td><input type="password" id="twoPW" /></td>
					</tr>
					<tr>
						<td align="center" colspan="2"><input type="button"
							value="注册" id="sub_reg" />&nbsp;&nbsp;<input type="reset"
							value="重填" /></td>
					</tr>
					<tr>
						<td colspan="2"><a href="javascript:void(0);" id="to_login">去登录</a></td>
					</tr>
				</table>
			</form:form>
		</div>
	</div>
</body>
</html>