<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Demo</title>
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
			邮件内容详情
		</div>
		<#if ( mail ??)>
		<table class="table">
			<tr>
				<td class="label">
					发件人：
				</td>
				<td>
					train@goldgov.com
				</td>
				<td class="label">
					收件人：
				</td>
				<td>
					${mail.to}
				</td>
			</tr>
			<tr>
				<td class="label">
					标题：
				</td>
				<td colspan="3">
					${mail.subject}
				</td>
			</tr>
		</table>
		<div class="content">
			<br />
			<br />
			<br />
			<br />
			${mail.content}
		</div>
		<#else>
			<div class="info">
				当前未有注册邮件！
			</div>
		</#if>
	</div>
</div>

</body>
</html>

