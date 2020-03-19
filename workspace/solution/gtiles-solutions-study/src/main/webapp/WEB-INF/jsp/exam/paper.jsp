<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>考试试卷</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/skin/bootstrap/css/bootstrap-3.3.5.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/skin/gtelearning/index.css"/>
  
   <!--本页面css-->
    <!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/r29/html5.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.js"></script>
    <![endif]-->
    <!--ie兼容h5、c3文件 已经使用cdn  目前存在问题-->
</head>
	<body data-spy="scroll" data-target="#nav" onkeydown="if(event.keyCode==116){event.keyCode=0;event.returnValue=false;}">
		<input type="hidden" id="recordId" value="${paperInfo.recordDto.recordId}">
		<input type="hidden" id="examUseTimes" value="${paperInfo.recordDto.examUseTimes}">
		<input type="hidden" id="examTimes" value="${paperInfo.recordDto.examTimes}">
		
		<div id="myModal" class="modal fade" role="dialog" aria-labelledby="gridSystemModalLabel">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="gridSystemModalLabel">倒计时提示</h4>
		      </div>
		      <div class="modal-body">
		      	倒计时3分钟三分钟后自动提交试卷
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		      
		      </div>
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
		
		<div id="panjuan" class="modal fade" role="dialog" aria-labelledby="gridSystemModalLabel">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h4 class="modal-title" id="gridSystemModalLabel">温馨提示</h4>
		      </div>
		      <div class="modal-body">
		      	你已交卷，系统正在判卷，请稍等...
		      </div>
		      <div class="modal-footer">
		      </div>
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
		
		
		<!--头部开始-->
		<header class="container">
			<div class="row">
			  <div class="col-sm-12">
				<div class="panel panel-default ">
				  <div class="panel-heading paper-title"><h2>${paperInfo.title}</h2></div>
				  <div class="panel-body" id="navwidth">
				    <p>${paperInfo.description}</p>
				    <!--试卷导航栏-->
				    <nav class="navbar navbar-default " role="navigation" id="nav">
					   <div>
					      <ul class="nav navbar-nav">
					      	<c:forEach items="${paperInfo.paperConfigList}" var="questionTypes">
						         <c:if test="${questionTypes.questionType==1}">
						         	  <li class="active"><a href="#radio" name="radio">单选题</a></li>
						         </c:if>
						         <c:if test="${questionTypes.questionType==2}">
					         		<li class=""><a href="#checkbox" name="checkbox">多选题</a></li>
						         </c:if>
						         <c:if test="${questionTypes.questionType==3}">
					         		<li class=""><a href="#bool" name="bool">判断题</a></li>
						         </c:if>
						         <c:if test="${questionTypes.questionType==4}">
					         		<li class=""><a href="#chooseFill" name="chooseFill">选择填空题</a></li>
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
				<div class="col-sm-9">
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
									  <c:if test="${questionTypes.questionType==4}">id="chooseFill"</c:if>
								  >
								  	  <c:if test="${questionTypes.questionType==1}">一、单选题</c:if>
									  <c:if test="${questionTypes.questionType==2}">二、多选题</c:if>
									  <c:if test="${questionTypes.questionType==3}">三、判断题</c:if>
									  <c:if test="${questionTypes.questionType==4}">四、选择填空题</c:if>
								  </div>
								  <div class="panel-body">
								  <c:forEach items="${paperInfo.questionTypeList}" var="quTypes">
									<c:if test="${quTypes.key==questionTypes.questionType }">
										<c:forEach items="${quTypes.value}" var="questions" varStatus="index">
											 <div class="row" id="${questions.questionId }_1">
											  	<dl class="question">
											  		<c:if test="${questionTypes.questionType==4}">
											  			<input type="hidden" id="${questions.questionId }" value="${questions.studentAnswer }" />
											  		</c:if>
								                    <dt  <c:if test="${questionTypes.questionType==4}">class="chooseFill" id="${questions.questionId }"</c:if> >
								                        <span class="num">${index.index+1}</span>${questions.description }。
								                    </dt>
								                    <dd>
								                        <ul class="items">
								                        	<c:forEach items="${questions.questionItemList }"  var="itemContents">
								                        		<c:if test="${questionTypes.questionType!=3}">
									                            	<li><a><span>${itemContents.optionNum}</span>${itemContents.option}</a></li>
								                        		</c:if>
									                        </c:forEach>
								                        </ul>
								                    </dd>
								                    <dd class="answer" id="${questions.questionId }" 
								                    	<c:if test="${questionTypes.questionType==2}"> name="choose" </c:if>
								                    	<c:if test="${questionTypes.questionType==4}"> name="chooseFill" </c:if>
								                    >
								                    	<c:forEach items="${questions.questionItemList }"  var="itemContents">
									                            <!-- 单选题 -->
									                            <c:if test="${questionTypes.questionType==1}">
								                        			<label class="xiaoguo"><input name="${questions.questionId }" type="radio" class="singles"  value="${itemContents.optionNum}" <c:if test="${itemContents.optionNum==questions.studentAnswer}">checked="checked"</c:if>>${itemContents.optionNum}</label>
																</c:if>
																<!-- 多选题 -->
																<c:if test="${questionTypes.questionType==2}">
																	<label><input type="checkbox" name="${questions.questionId }"  value="${itemContents.optionNum}" 
																		<c:if test="${!empty questions.studentAnswer}">
																			<c:forEach items="${questions.studentAnswer}" var="chooseItem">
																				<c:if test="${itemContents.optionNum==chooseItem}">checked="checked"</c:if>
																			</c:forEach>
																		</c:if>	
																	/>${itemContents.optionNum}</label>
																</c:if>
																<!--判断题  -->
																<c:if test="${questionTypes.questionType==3}">
								                        			<label><input name="${questions.questionId }" type="radio" class="singles"  value="${itemContents.optionNum}" <c:if test="${itemContents.optionNum==questions.studentAnswer}">checked="checked"</c:if>>${itemContents.optionNum=="A" ? "正确":"错误"}</label>
																</c:if>
									                    </c:forEach>
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
		
		<!--侧边栏开始-->
				<div class="col-sm-3" id="examinations-state">
					<div class="exam-state">
		                <div class="panel panel-default">
		                	<!--等于0的时候没有时间限制 -->
		                	<c:if test="${paperInfo.recordDto.examTimes>0}">
			                    <div class="panel-heading">
			                        <span class="glyphicon glyphicon-time"></span>
			                        <span class="fc-red" id="shengyu">00:00:00</span>
			                    </div>
		                    </c:if>
		                    <div class="student-info">
		                        <p class="name">刘荣杰<small>(党内职务)</small></p>
		                        <p class="mb0">支部/所属党委</p>
		                    </div>
		                    <div class="question-quick-nav">
		                        <div>
		                          <c:forEach items="${paperInfo.paperConfigList}" var="questionTypes">
			                          <dl class="m10">
			                              <dt class="h6">
				                              <c:if test="${questionTypes.questionType==1}">一、单选题</c:if>
											  <c:if test="${questionTypes.questionType==2}">二、多选题</c:if>
											  <c:if test="${questionTypes.questionType==3}">三、判断题</c:if>
											  <c:if test="${questionTypes.questionType==4}">四、选择填空题</c:if>
												(<small>每题${questionTypes.typeQuestionScore}分,共${questionTypes.typeQuestionScore*questionTypes.typeQuestionNum }分</small>)</dt>
			                              <dd>
			                              		<c:forEach items="${paperInfo.questionTypeList}" var="quTypes">
													<c:if test="${quTypes.key==questionTypes.questionType }">
														<c:forEach items="${quTypes.value}" var="questions" varStatus="index">
			                                 				<a name="${questions.questionId }" <c:if test="${questions.studentAnswer!=''&&questions.studentAnswer!=null}">class="active"</c:if> ><span>${index.index+1}</span></a>
			                                 			</c:forEach>
			                                 		</c:if>
			                                 	</c:forEach>
			                              </dd>
			                          </dl>
		                        	</c:forEach>
		                        </div>
		                        <div class="m10">
		                            <button class="btn btn-block btn-default btn-sm" id="examTs">保存进度、下次继续</button>
		                            <button class="btn btn-block btn-oranger btn-sm" id="examPaper">交卷</button>
		                        </div>
		                    </div>
		                </div>
					</div>
				</div>
		<!--侧边栏结束-->
			</div>
		</div>
	</body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jquery-1.11.3.min.js" charset="utf-8"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/bootstrap/bootstrap-3.3.5.min.js" charset="utf-8"></script>
	<script type="text/javascript">
		var examUseTimes=$("#examUseTimes").val();
		var examTimes=$("#examTimes").val();
		var shengyu=examTimes-examUseTimes-1;
		var miao=60;
		function useTimes(){
			if(shengyu==3&&miao==0){
				//提示用户剩余多少时间
				//$("#examTs").html("三分钟自动提交");
				$('#myModal').modal('show')
			}
			if(miao>0){
				miao--;
				$("#shengyu").html(shengyu+":"+miao);
			}else if(miao==0&&shengyu>0){
				miao=60;
				shengyu--;
			}
		    if(shengyu==0&&miao==0){
		    	$("#examPaper").attr("disabled","disabled");
		    	$("#panjuan").modal("show");
				window.onbeforeunload = function(){}
				//最后一秒自动提交
				var singleAns=getStudentAnswer();
				//提交数据
				$.post("${pageContext.request.contextPath}/portal/exam/examPaper.do?", { recordId: $("#recordId").val(), "studentAns": singleAns },
						   function(data){
					window.location.href="${pageContext.request.contextPath}/portal/exam/findExamResult.do?recordId="+$("#recordId").val();
				}, "json");
			}
		}
		if(examTimes>0){
			setInterval("useTimes()",1000);
		}
		
		//获取学生答案
		function getStudentAnswer(){
			//答案 questionId  studentAnswer
			var singleAns="";
			var questionId="questionId";
			var studentAnswer="studentAnswer";
			var fenhao=":";
			var douhao=",";
			//单选题
			$(".singles:checked").each(function(){
				var values=$(this).val();
				var ids=$(this).attr("name");
				singleAns+="{\""+questionId+"\""+fenhao+"\""+ids+"\""+douhao+"\""+studentAnswer+"\""+fenhao+"\""+values+"\"}"+douhao;
			});
			//多选题
			$("dd[name='choose']").each(function(){
				var chooseId=$(this).attr("id");
				var chooseVal="";
				var flag=false;
				$(":checkbox[name='"+chooseId+"']:checked").each(function(){
					flag=true;
					chooseVal+=$(this).val()+",";
				});
				if(flag){
					chooseVal=chooseVal.substring(0, chooseVal.length-1)
					singleAns+="{\""+questionId+"\""+fenhao+"\""+chooseId+"\""+douhao+"\""+studentAnswer+"\""+fenhao+"\""+chooseVal+"\"}"+douhao;
				}
			});
			//选择填空题
			$("dd[name='chooseFill']").each(function(){
				var questionIds=$(this).attr("id");
				var chooseFillVal="";
				$(":text[name='"+questionIds+"']").each(function(){
					chooseFillVal+=$(this).val()+",";
				});
				chooseFillVal=chooseFillVal.substring(0, chooseFillVal.length-1)
				chooseFillVal=chooseFillVal.toUpperCase();
				singleAns+="{\""+questionId+"\""+fenhao+"\""+questionIds+"\""+douhao+"\""+studentAnswer+"\""+fenhao+"\""+chooseFillVal+"\"}"+douhao;
			});
			
			//处理最后字符串
			singleAns=singleAns.substring(0,singleAns.length-1);
			singleAns="["+singleAns+"]";
			
			return singleAns;
		}
		//窗口关闭时
		window.onbeforeunload = function(){
			return "确定离开吗？";
		}
					
		$(function(){
			//暂存下次继续考试
			$("#examTs").click(function(){
				$(this).attr("disabled","disabled");
				window.onbeforeunload = function(){}
				var singleAns=getStudentAnswer();
				$.post("${pageContext.request.contextPath}/portal/exam/examTsDetails.do?", { recordId: $("#recordId").val(), "studentAns": singleAns },
						   function(data){
							$("#examTs").removeAttr("disabled");
				}, "json");
			});
			
			//交卷
			$("#examPaper").click(function(){
				var singleAns=getStudentAnswer();
				var quanbu=$(".question-quick-nav a").length;
				var yizuo=$(".question-quick-nav a[class='active']").length;
	 			 if(quanbu!=yizuo&&!confirm("存在未答试题，是否交卷?")){
	 				return false;
	 			 }
				$("#panjuan").modal("show");
				$(this).attr("disabled","disabled");
				window.onbeforeunload = function(){}
				//提交数据
				$.post("${pageContext.request.contextPath}/portal/exam/examPaper.do?", { recordId: $("#recordId").val(), "studentAns": singleAns },
						   function(data){
						    window.location.href="${pageContext.request.contextPath}/portal/exam/findExamResult.do?recordId="+$("#recordId").val();
				}, "json");
			});
			
			//导航跟随滚动条
		  var navLeft = $("#nav").offset().left;
		  var navTop = $("#nav").offset().top;
		  var navWidth = $("#nav").outerWidth();
		  var navWidthScroll = $("#navwidth").outerWidth();
		  var examinationsTop=$("#examinations-state").offset().top;
		  var examinationsLeft=$("#examinations-state").offset().left;
		  var examinationsWidt=$("#examinations-state").outerWidth();
		  var widthError=(navWidthScroll-navWidth)/2;
		  
		  $(window).scroll( function() {	
				var scroll_top = $(document).scrollTop()||$("body").scrollTop()
					if(scroll_top>=navTop){
						$("#nav").css({"position":"fixed","left":navLeft-widthError,"top":0,"width":navWidthScroll})
						$(".panel-body>p").css({"margin-bottom": "70px"})
					}
					else{
						$("#nav").css({"position":"absolute","left":"30px","top":navTop,"width":navWidth})
						$(".panel-body>p").css({"margin-bottom": "70px"})
					}
					if(scroll_top>=(examinationsTop-50)){
						$("#examinations-state").css({"position":"fixed","left":examinationsLeft,"top":52,"width":examinationsWidt})
					}
					if(scroll_top<examinationsTop-50){
						$("#examinations-state").css({"position":"absolute","left":examinationsLeft,"top":examinationsTop,"width":examinationsWidt})
					}
			});
			//右侧选中
			$(".answer input").on("click",function(){
				var questionId=$(this).attr("name");
				$("a[name='"+questionId+"']").removeClass("active")
				//单选题和多选题判断题
				$(".answer input[name='"+questionId+"']:checked").each(function(){
					$("a[name='"+questionId+"']").addClass("active")
					return false;
				});
				
			});
			//定位当前题的位置
			$(".question-quick-nav a").click(function(){
				var questionId=$(this).attr("name");
				questionId=questionId+"_1";
				var questionIdTop=$("#"+questionId).offset().top-70;
				$(document).scrollTop(questionIdTop);
			});
			//题型定位
			$(".nav.navbar-nav a").each(function(){
				$(this).click(function(){
					var ids=$(this).attr("name");
					var questionIdTypeTop=$("#"+ids).offset().top-70;
					$(document).scrollTop(questionIdTypeTop);
				});
			});
			//替换掉占位符
			$.each($("dt[class='chooseFill']"),function(index,$this){
				var str = $($this).html();
				var questions=$($this).attr("id");
				var after = str.replace(/\[\[\d*\]\]/g,function(word){
					word = word.replace('[[','').replace(']]','');
					var holder = "填空（"+word+"）答案";
					$(".answer[id='"+questions+"']").append('<label><input type="text" class="chooseFill" name="'+questions+'"  placeholder="'+holder+'"/></label><br/>');
					return "（"+word+"）";
				});
				$($this).html(after);
				//获取本地学生已经有的答案,将答案回现给页面
				var questionAnswer=$("#"+questions+":hidden").val();
				var questionAnswers=questionAnswer.split("\,");
				$(":text[name='"+questions+"']").each(function(index){
					$(this).val(questionAnswers[index]);
				});
			});
			//选择填空题
			$(":text[class='chooseFill']").each(function(){
				$(this).change(function(){
					var values=$(this).val();
					var questionId=$(this).attr("name");
					$("a[name='"+questionId+"']").removeClass("active")
					if(values!=""){
						$("a[name='"+questionId+"']").addClass("active")
					}
				});
			});
		});
	</script>
</html>
