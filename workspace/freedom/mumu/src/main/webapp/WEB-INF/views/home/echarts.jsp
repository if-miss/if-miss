<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>echarts演示</title>
<script src="${ctx }/js/dist/echarts.js"></script>
<script type="text/javascript">
	// 路径配置
	require.config({
		paths : {
			echarts : '${ctx}/js/dist'
		}
	});
	//地图数显示
	require([ 'echarts', 'echarts/chart/map' // 使用柱状图就加载bar模块，按需加载
	      	], function(ec) {
	      	
	var option = {
		    title : {
		        text: 'iphone销量',
		        subtext: '纯属虚构',
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item'
		    },
		    legend: {
		        orient: 'vertical',
		        x:'left',
		        data:['iphone3','iphone4','iphone5']
		    },
		    dataRange: {
		        min: 0,
		        max: 2500,
		        x: 'left',
		        y: 'bottom',
		        text:['高','低'],           // 文本，默认为数值文本
		        calculable : true
		    },
		    toolbox: {
		        show: true,
		        orient : 'vertical',
		        x: 'right',
		        y: 'center',
		        feature : {
		            mark : {show: true},
		            dataView : {show: true, readOnly: false},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    roamController: {
		        show: true,
		        x: 'right',
		        mapTypeControl: {
		            'china': true
		        }
		    }, 
		    series : [
		 		        {
				            name: 'iphone3',
				            type: 'map',
				            mapType: 'china',
				            roam: false,
				            itemStyle:{
				                normal:{label:{show:true}},
				                emphasis:{label:{show:true}}
				            },
				            data:[
				                {name: '北京',value: Math.round(Math.random()*1000)},
				                {name: '天津',value: Math.round(Math.random()*1000)},
				                {name: '上海',value: Math.round(Math.random()*1000)},
				                {name: '重庆',value: Math.round(Math.random()*1000)},
				                {name: '河北',value: Math.round(Math.random()*1000)},
				                {name: '河南',value: Math.round(Math.random()*1000)},
				                {name: '云南',value: Math.round(Math.random()*1000)},
				                {name: '辽宁',value: Math.round(Math.random()*1000)},
				                {name: '黑龙江',value: Math.round(Math.random()*1000)},
				                {name: '湖南',value: Math.round(Math.random()*1000)},
				                {name: '安徽',value: Math.round(Math.random()*1000)},
				                {name: '山东',value: Math.round(Math.random()*1000)},
				                {name: '新疆',value: Math.round(Math.random()*1000)},
				                {name: '江苏',value: Math.round(Math.random()*1000)},
				                {name: '浙江',value: Math.round(Math.random()*1000)},
				                {name: '江西',value: Math.round(Math.random()*1000)},
				                {name: '湖北',value: Math.round(Math.random()*1000)},
				                {name: '广西',value: Math.round(Math.random()*1000)},
				                {name: '甘肃',value: Math.round(Math.random()*1000)},
				                {name: '山西',value: Math.round(Math.random()*1000)},
				                {name: '内蒙古',value: Math.round(Math.random()*1000)},
				                {name: '陕西',value: Math.round(Math.random()*1000)},
				                {name: '吉林',value: Math.round(Math.random()*1000)},
				                {name: '福建',value: Math.round(Math.random()*1000)},
				                {name: '贵州',value: Math.round(Math.random()*1000)},
				                {name: '广东',value: Math.round(Math.random()*1000)},
				                {name: '青海',value: Math.round(Math.random()*1000)},
				                {name: '西藏',value: Math.round(Math.random()*1000)},
				                {name: '四川',value: Math.round(Math.random()*1000)},
				                {name: '宁夏',value: Math.round(Math.random()*1000)},
				                {name: '海南',value: Math.round(Math.random()*1000)},
				                {name: '台湾',value: Math.round(Math.random()*1000)},
				                {name: '香港',value: Math.round(Math.random()*1000)},
				                {name: '澳门',value: Math.round(Math.random()*1000)}
				            ]
				        },
				        {
				            name: 'iphone4',
				            type: 'map',
				            mapType: 'china',
				            itemStyle:{
				                normal:{label:{show:true}},
				                emphasis:{label:{show:true}}
				            },
				            data:[
				                {name: '北京',value: Math.round(Math.random()*1000)},
				                {name: '天津',value: Math.round(Math.random()*1000)},
				                {name: '上海',value: Math.round(Math.random()*1000)},
				                {name: '重庆',value: Math.round(Math.random()*1000)},
				                {name: '河北',value: Math.round(Math.random()*1000)},
				                {name: '安徽',value: Math.round(Math.random()*1000)},
				                {name: '新疆',value: Math.round(Math.random()*1000)},
				                {name: '浙江',value: Math.round(Math.random()*1000)},
				                {name: '江西',value: Math.round(Math.random()*1000)},
				                {name: '山西',value: Math.round(Math.random()*1000)},
				                {name: '内蒙古',value: Math.round(Math.random()*1000)},
				                {name: '吉林',value: Math.round(Math.random()*1000)},
				                {name: '福建',value: Math.round(Math.random()*1000)},
				                {name: '广东',value: Math.round(Math.random()*1000)},
				                {name: '西藏',value: Math.round(Math.random()*1000)},
				                {name: '四川',value: Math.round(Math.random()*1000)},
				                {name: '宁夏',value: Math.round(Math.random()*1000)},
				                {name: '香港',value: Math.round(Math.random()*1000)},
				                {name: '澳门',value: Math.round(Math.random()*1000)}
				            ]
				        },
				        {
				            name: 'iphone5',
				            type: 'map',
				            mapType: 'china',
				            itemStyle:{
				                normal:{label:{show:true}},
				                emphasis:{label:{show:true}}
				            },
				            data:[
				                {name: '北京',value: Math.round(Math.random()*1000)},
				                {name: '天津',value: Math.round(Math.random()*1000)},
				                {name: '上海',value: Math.round(Math.random()*1000)},
				                {name: '广东',value: Math.round(Math.random()*1000)},
				                {name: '台湾',value: Math.round(Math.random()*1000)},
				                {name: '香港',value: Math.round(Math.random()*1000)},
				                {name: '澳门',value: Math.round(Math.random()*1000)}
				            ]
				        }
				    ]
		   
		};
	 ec.init(document.getElementById('maps')).setOption(option);
	});
	
	
	// 使用
	require([ 'echarts', 'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
	], function(ec) {
		// 基于准备好的dom，初始化echarts图表
		var myChart = ec.init(document.getElementById('main'));

		var option = {
			/* backgroundColor:'#ff7f50', */
			color : [ '#ff7f50', '#87cefa', '#da70d6', '#32cd32', '#6495ed' ],
			title : {
				show : true,
				text : "人寿第一季度销售额度"
			},
			tooltip : {
				show : true
			},
			legend : {
				data : [ '实时', '非实时' ]
			},
			xAxis : [ {
				type : 'category',
				data : [ "574", "473", "752", "756", "886", "745" ]
			}/* , {
				type : 'category',
				data : [ "白酒", "红酒", "黄酒", "啤酒", "鸡尾酒", "葡萄酒" ]
			}  */],
			yAxis : [ {
				type : 'value'
			} ],
			series : [ {
				"name" : "实时",
				"type" : "bar",
				"data" : [ 5, 20, 40, 10, 10, 20 ]
			}, {
				"name" : "非实时",
				"type" : "bar",
				"data" : [ 10, 20, 40, 5, 10, 20 ]
			} ]
		};

		// 为echarts对象加载数据 
		myChart.setOption(option);
	});
</script>
</head>
<body>
	数据图表文件
	<div id="main" style="height: 400px"></div>
	<div id="maps" style="height: 400px"></div>
</body>
</html>