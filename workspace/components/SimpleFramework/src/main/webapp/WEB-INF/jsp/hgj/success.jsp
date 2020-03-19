<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
<style>
	body{
		margin: 0;
		position: relative;
		font-size: 12px;
	}
	
	.main{
		position: fixed;
		top:120px;
		left:50%;
		border: 2px #00e solid;
		background-color:#eee;
		border-radius: 5px;
		width:500px;
		height:500px;
		margin-left: -250px;
		overflow: hidden;
	}
	
	.title{
		text-align:center;
		font-size: 20px;
		font-style: bold;
		color: #FFF;
		background-color:#00e;
		border: 2px;
		height:30px;
	}
	
	.table{
		width:100%;
		border-collapse:collapse;
		border: 1px  #00f solid;
	}
	
	tr,td{
		height:30px;
		border-collapse:collapse;
		border: 1px  #00f solid;
	}
	
	.label{
		background-color:#AAE;
		text-align:right;
		font-style: bold;
	}
	
	.content{
		padding:5px;
		height:100%;
		background-color:#FFF;
	}
	.info{
		position:relative;
		top:200px;
		text-align:center;
		font-size: 15px;
		color: #F00;
	}
</style>
</head>
<body>
<div>
	<div class="main">
		<div class="title">
			登录成功
		</div>
			<div class="content">
				<br>
				<br>
				<br>
				<br>
				<p>当前登录用户：${loginUser.loginName }</p>
				<p>邮箱：${loginUser.userMail }</p>
				<p>登录时间：${loginUser.loginSuccessTime }</p>
			</div>
	</div>
</div>

</body>
</html>

