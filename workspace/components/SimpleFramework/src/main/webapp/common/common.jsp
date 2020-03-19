<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<script type="text/javascript" src="${ctx }/skin/js/jquery.min.js"></script>

<script type="text/javascript">
	Date.prototype.format = function(format) {
		var o = {
			"M+" : this.getMonth() + 1, //month 
			"d+" : this.getDate(), //day 
			"h+" : this.getHours(), //hour 
			"m+" : this.getMinutes(), //minute 
			"s+" : this.getSeconds(), //second 
			"q+" : Math.floor((this.getMonth() + 3) / 3), //quarter 
			"S" : this.getMilliseconds()
		//millisecond 
		}

		if (/(y+)/.test(format)) {
			format = format.replace(RegExp.$1, (this.getFullYear() + "")
					.substr(4 - RegExp.$1.length));
		}

		for ( var k in o) {
			if (new RegExp("(" + k + ")").test(format)) {
				format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
						: ("00" + o[k]).substr(("" + o[k]).length));
			}
		}
		return format;
	}
</script>

<style type="text/css">
body ul li a span div table tr td  {
	margin: 0px;
	padding: 0px;
	font-size: 14px;
}
tr{
	height: 40px; 
}
#bg {
	position: absolute;
	top: 0%;
	left: 0%;
	width: 100%;
	height: 100%;
	/* background-image: url("${ctx}/img/login.jpg"); */
	z-index: 1001;
}

.float {
	position: absolute;
	top: 25%;
	left: 25%;
	width: 600px;
	height: 350px;
	padding: 3px;
	 border: 2px solid #E8E9F7; 
	background-color: white;
	z-index: 1002;
	overflow: auto;
	-moz-opacity: 0.9;
	opacity: .70;
	filter: alpha(opacity = 90);
}
</style>
