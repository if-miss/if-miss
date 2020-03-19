<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html>
<html>
	<head>
    <meta charset="UTF-8">
    <title>考试试卷</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/skin/bootstrap/css/bootstrap-3.3.5.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/skin/gtelearning/index.css"/>
     
    <!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/r29/html5.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.js"></script>
    <![endif]-->
    <!--ie兼容h5、c3文件 已经使用cdn  目前存在问题-->
    <style type="text/css">
			caption{
				text-align: center;
				font-size: 18px;
				color: #777;
				background: #f8f8f8;
				font-weight: bolder;
			}
			thead tr{
				background: #fcf8e3;
			}
			.table>tbody>tr>td,.table>thead>tr>th{
				text-align: center;
				vertical-align: middle;
			}
/* 			table tr td:first-child{ */
/* 				text-align: left; */
/* 			} */
			table{
				border: 1px solid #ccc;
			}
			li .glyphicon.glyphicon-ok{
				color: green;
				margin-left: 50px;
			}
			li .glyphicon.glyphicon-remove{
				color: red;
				margin-left: 50px;
			}
		</style>
</head>
	<body data-spy="scroll" data-target="#nav">
		<!--头部开始-->
		<input type="hidden" id="paperId" value="${paperInfo.recordDto.paperId }">
		<input type="hidden" id="studentId" value="${paperInfo.recordDto.studentId }">
		<header class="container">
			<div class="row">
			  <div class="col-sm-12">
				<div class="panel panel-default ">
				  <div class="panel-heading paper-title"><h2>考试结果统计</h2></div>
				  <div class="panel-body" id="navwidth">
					<div class="row">
						<div class="col-sm-12">
							<table class="table table-hover">
								<thead>
									<tr>
										<th></th>
										<th>做对试题</th>
										<th>做错试题</th>
										<th>正确  / 总题量</th>
										<th>正确率</th>
										<th>及格分</th>
										<th>得分  / 总分数</th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>总计</td>
										<td>${paperInfo.recordDto.examAnswerNum }</td>
										<td>${paperInfo.recordDto.examAnswerCount-paperInfo.recordDto.examAnswerNum }</td>
										<td>${paperInfo.recordDto.examAnswerNum } / ${paperInfo.recordDto.examAnswerCount}</td>
										<td><fmt:formatNumber type="number" value="${paperInfo.recordDto.examAnswerNum/paperInfo.recordDto.examAnswerCount*100 }" maxFractionDigits="0"/>%</td>
										<td>${paperInfo.recordDto.examPassScore }</td>
										<td >
										<label
											<c:if test="${paperInfo.recordDto.examGetScore>paperInfo.recordDto.examPassScore }"> style="color:green ;"</c:if>
											<c:if test="${paperInfo.recordDto.examGetScore<paperInfo.recordDto.examPassScore }">style="color: red;"</c:if>
										><fmt:formatNumber type="number" value="${paperInfo.recordDto.examGetScore}" maxFractionDigits="0"/></label> / <fmt:formatNumber type="number" value="${paperInfo.recordDto.examCountScore }" maxFractionDigits="0"/></td>
										<td><span class="btn btn-success" id="restEaxm">重新考试</span></td>
									</tr>			
								</tbody>
							</table>
						</div>
					</div>    
				    <!--试卷导航栏-->
				    <nav class="navbar navbar-default " role="navigation" id="nav">
					   <div>
					      <ul class="nav navbar-nav" style="width:100%">
					         <c:forEach items="${paperInfo.paperConfigList}" var="questionTypes">
						         <c:if test="${questionTypes.questionType==1}">
						         	  <li class="active"><a href="#radio">单选题</a></li>
						         </c:if>
						         <c:if test="${questionTypes.questionType==2}">
					         		<li class=""><a href="#checkbox">多选题</a></li>
						         </c:if>
						         <c:if test="${questionTypes.questionType==3}">
					         		<li class=""><a href="#bool" >判断题</a></li>
						         </c:if>
						         <c:if test="${questionTypes.questionType== 4}">
					         		<li class=""><a href="#chooseFills" >选题填空题</a></li>
						         </c:if>
					         </c:forEach>
					      </ul>
					   </div>
					</nav>   
				  </div>
				</div>
			  </div>
			</div>
		</header>
		<!--头部结束-->
		
		<!--主体内容开始-->
		<div class="container content" >	
			<div class="row">
				<div class="col-sm-12">
				<!--以下为题目区域-->
					<!--以下为单选题类型，每题型应该以类名row开始-->
					<c:forEach items="${paperInfo.paperConfigList}" var="questionTypes">
						<div class="row">
							<div class="col-sm-12">
								<div class="panel panel-default">
								  <div class="panel-heading" 
								  <c:if test="${questionTypes.questionType==1}">id="radio"</c:if>
								  <c:if test="${questionTypes.questionType==2}">id="checkbox"</c:if>
								  <c:if test="${questionTypes.questionType==3}">id="bool"</c:if>
								  <c:if test="${questionTypes.questionType==4}">id="chooseFills"</c:if>
								  >
								   <c:if test="${questionTypes.questionType==1}">一、单选题</c:if>
								   <c:if test="${questionTypes.questionType==2}">二、多选题</c:if>
								   <c:if test="${questionTypes.questionType==3}">三、判断题</c:if>
								   <c:if test="${questionTypes.questionType==4}">四、选题填空题</c:if>
								   (<small>每题${questionTypes.typeQuestionScore}分,共${questionTypes.typeQuestionScore*questionTypes.typeQuestionNum }分</small>)
								  </div>
								  <div class="panel-body">
								  <c:forEach items="${paperInfo.questionTypeList}" var="quTypes">
									<c:if test="${quTypes.key==questionTypes.questionType }">
										<c:forEach items="${quTypes.value}" var="questions" varStatus="index">
											 <div class="row">
											  	<dl class="question" >
								                    <dt <c:if test="${questionTypes.questionType==4}">name="chooseFill"</c:if>>
								                        <span class="num">${index.index+1}</span>${questions.description }。
								                    </dt>
								                    <dd>
								                        <ul class="items">
								                        	<c:forEach items="${questions.questionItemList }"  var="itemContents">
								                        	
								                        		<!-- 单选题 -->
								                        		<c:if test="${questionTypes.questionType==1}">
									                            	<li><a><span>${itemContents.optionNum}</span>${itemContents.option}</a>
									                            		<c:if test="${itemContents.optionNum==questions.studentAnswer}">
								                        			 		<c:if test="${questions.isCorrect==1 }">
								                        			 			<span class="glyphicon glyphicon-ok"></span>
								                        			 		</c:if>
								                        			 		<c:if test="${questions.isCorrect==2 }">
								                        			 			<span class="glyphicon glyphicon-remove"></span>
								                        			 		</c:if>
								                        			 	</c:if>
									                            	
									                            	</li>
								                        		</c:if>
								                        		<!-- 多选题 -->
								                        		<c:if test="${questionTypes.questionType==2}">
																	<li><a><span>${itemContents.optionNum}</span>${itemContents.option}</a>				                        		
									                            		<c:if test="${!empty questions.studentAnswer}">
																			<c:forEach items="${questions.studentAnswer}" var="chooseItem">
																				
																					<c:if test="${itemContents.optionNum==chooseItem}">
											                        			 		<c:if test="${questions.isCorrect==1 }">
											                        			 			<span class="glyphicon glyphicon-ok"></span>
											                        			 		</c:if>
											                        			 		<c:if test="${questions.isCorrect==2 }">
											                        			 			<span class="glyphicon glyphicon-remove"></span>
											                        			 		</c:if>
											                        			 	</c:if>
																			</c:forEach>
																		</c:if>
																	</li>	
								                        		</c:if>
								                        		
								                        		<!-- 判断题  -->
								                        		<c:if test="${questionTypes.questionType==3}">
								                        			 <li><a><span>${itemContents.optionNum}</span>${itemContents.optionNum=="A" ? "正确":"错误"}</a>
								                        			 	<c:if test="${itemContents.optionNum==questions.studentAnswer}">
								                        			 		<c:if test="${questions.isCorrect==1 }">
								                        			 			<span class="glyphicon glyphicon-ok"></span>
								                        			 		</c:if>
								                        			 		<c:if test="${questions.isCorrect==2 }">
								                        			 			<span class="glyphicon glyphicon-remove"></span>
								                        			 		</c:if>
								                        			 	</c:if>
								                        			 </li>
																</c:if>
																<c:if test="${questionTypes.questionType==4}">
																	<li><a><span>${itemContents.optionNum}</span>${itemContents.optionNum}</a></li>
																</c:if>
									                        </c:forEach>
									                        <li>正确答案是：<span>${questions.answer }</span>
									                        	<c:if test="${questionTypes.questionType==4}">
									                        		你的答案：<span>${questions.studentAnswer}</span>
									                        		<c:if test="${questions.answer==questions.studentAnswer }"><span class="glyphicon glyphicon-ok"></span></c:if>
									                        		<c:if test="${questions.answer!=questions.studentAnswer }"><span class="glyphicon glyphicon-remove"></span></span></c:if>
									                        	</c:if>
									                        </li>
								                        </ul>
								                    </dd>
								                </dl>
										     </div> 
										</c:forEach>
									</c:if>
								  </c:forEach>
								</div>
							   </div>
							</div>	
						</div>
					 </c:forEach>
				</div>
		<!--主体内容结束-->
			</div>
		</div>
	</body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jquery-1.11.3.min.js" charset="utf-8"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/bootstrap/bootstrap-3.3.5.min.js" charset="utf-8"></script>
    <script type="text/javascript">
    $(function(){
    	 var navLeft = $("#nav").offset().left;
		  var navTop = $("#nav").offset().top;
		  var navWidth = $("#nav").outerWidth();
		  var navWidthScroll = $("#navwidth").outerWidth();
		  var widthError=(navWidthScroll-navWidth)/2;
		  
		  $(window).scroll( function() {	
				var scroll_top = $(document).scrollTop()||$("body").scrollTop()
					if(scroll_top>=navTop){
						$("#nav").css({"position":"fixed","left":navLeft-widthError,"top":0,"width":navWidthScroll})
						$(".table").css({"margin-bottom": "70px"})
					}
					else{
						$("#nav").css({"position":"absolute","left":"30px","top":navTop,"width":navWidth})
						$(".panel-body>p").css({"margin-bottom": "70px"})
					}
			});
    	
    	$("dt").each(function(){
    		var str = $(this).html();
    		var after = str.replace(/\[\[\d*\]\]/g,function(word){
				word = word.replace('[[','').replace(']]','');
				return "（"+word+"）";
			});
    		$(this).html(after);
    	});
	   
	   	//重新考试
	    $("#restEaxm").click(function(){
	    	window.location.href="${pageContext.request.contextPath}/portal/exam/examBegin.do?paperId="+$("#paperId").val()+"&studentId="+$("#studentId").val();
	    });
	  
    });
    </script>
</html>