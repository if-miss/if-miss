<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<link rel="stylesheet" href="${ctx}/css/public/divFloat.css" type="text/css" />
<script type="text/javascript" src="${ctx }/js/jquery-2.0.3.min.js" />
<script type="text/javascript" src="${ctx }/js/public/bgiframe.js" />

<!-- 自动播图片 -->
<link rel="stylesheet" href="${ctx}/css/imgplay/jquery.imgplay.css" type="text/css" />
<script type="text/javascript" src="${ctx }/js/imgplay/jquery.imgplay.js" />


<!-- 基础scripts -->
<c:if test="${not empty message}">
	<script type="text/javascript">
		/*  $(function(){
			$.weeboxs.open("${message}", {title: "友情提示"});
		});  */
		alert("${message}");
	</script>
</c:if>

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
