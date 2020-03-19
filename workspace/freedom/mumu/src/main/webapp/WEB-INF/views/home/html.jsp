<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>html5展示</title>
<script type="text/javascript">
	$(function() {
		//画图
		var c = document.getElementById("myCanvas");
		var cxt = c.getContext("2d");//html5内置对象
		/* cxt.fillStyle="#FF0000";
		cxt.beginPath();
		cxt.arc(70,18,15,0,Math.PI*2,true);
		cxt.closePath();
		cxt.fill(); */
		var grd = cxt.createLinearGradient(0, 0, 175, 50);
		grd.addColorStop(0, "#FF0000");
		grd.addColorStop(1, "#00FF00");
		cxt.fillStyle = grd;
		cxt.fillRect(0, 0, 175, 50);//渐变颜色

		//获取用户定位
		var x = document.getElementById("demo");
		function getLocation() {
			if (navigator.geolocation) {
				navigator.geolocation.getCurrentPosition(showPosition);
			} else {
				x.innerHTML = "Geolocation is not supported by this browser.";
			}
		}
		function showPosition(position) {
			x.innerHTML = "Latitude: " + position.coords.latitude
					+ "<br />Longitude: " + position.coords.longitude;
		}

		//本地存储
		if (localStorage.page) {
			localStorage.page = Number(localStorage.page) + 1;
		} else {
			localStorage.page = 1;
		}
		$("#demo").html(localStorage.page);

		//session级别的存储
		if (sessionStorage.pagecount) {
			sessionStorage.pagecount = Number(sessionStorage.pagecount) + 1;
		} else {
			sessionStorage.pagecount = 1;
		}
		$("#sessionID").html(sessionStorage.pagecount);

	});

	//表示何处放被拖动的元素
	function allowDrop(ev) {
		ev.preventDefault();
	}
	//拖放事件
	function drag(ev) {
		ev.dataTransfer.setData("Text", ev.target.id);//把被拖动的事件id以key-value:Text-drag1 存放
	}
	//被拖动的元元素放时发生的事件
	function drop(ev) {
		ev.preventDefault();
		var data = ev.dataTransfer.getData("Text");//获取被拖动事件的id值
		ev.target.appendChild(document.getElementById(data));//将拖动的元素添加到目标元素子节点最后一个
	}
</script>
</head>
<body>
	<h5 align="center">hello 欢迎使用html</h5>
	<label>播放视频文件</label>
	<video src="http://v.qq.com/page/l/4/7/l0160nchm47.html" id="videos"
		autoplay="autoplay" controls="controls">
		<!-- 视屏暂时无法播放 -->
	</video>
	<label>播放音频文件</label>
	<audio src="${ctsx }/files/白安-是什么让我遇见这样的你.mp3" controls="controls" autoplay="autoplay"><!-- 播放音频文件 -->
	</audio>

	<!--拖动事件  -->
	<p />
	<label>拖动事件：draggable：表示当前元素可以被拖动。ondragstart：当元素拖动时会发生事件
		拖动的元素和目标元素必须有id值如果没有则不能被拖动 </label>
	<div
		style="background-color: black; float: left; height: 100px; width: 100px;"
		id="div1" ondrop="drop(event)" ondragover="allowDrop(event)"></div>
	<div
		style="background-color: CornflowerBlue; height: 100px; wdth: 100px;"
		id="div2" ondrop="drop(event)" ondragover="allowDrop(event)"></div>
	<img id="drag1" src="${ctx }/img/biaoqing.gif" draggable="true"
		ondragstart="drag(event)" />
	<label style="color: red;" id="dt" ondragstart="drag(event)"
		draggable="true">拖动我呀</label>

	<!--画布元素  -->
	<br />
	<canvas id="myCanvas"
		style="background-color: #A52A2A; width: 200px; height: 100px;"></canvas>

	<!--svg  -->

	<svg xmlns="http://www.w3.org/2000/svg" version="1.1" height="190">
  		<polygon points="100,10 40,180 190,60 10,60 160,180"
			style="fill:lime;stroke:purple;stroke-width:5;fill-rule:evenodd;" />
	</svg>

	<label> localStorage.page是存在浏览器中的。可以是不同session。不同浏览器缓存的数据是不一样的。</label>
	<div>
		本地存储：<i id="demo"></i>
	</div>
	<div>
		session级别存储：<i id="sessionID"></i>
	</div>

</body>
</html>