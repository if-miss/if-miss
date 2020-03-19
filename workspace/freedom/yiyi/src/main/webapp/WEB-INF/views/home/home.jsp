<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>项目主页</title>
</head>
<body>
	hello欢迎来到依依工作室
	<a href="${ctx }/home/cache">获取缓存数据</a>
	<h3>缓存数据为：${parm }</h3> 
</body>
</html>