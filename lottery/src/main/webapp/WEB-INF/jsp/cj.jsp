<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="target-densitydpi=device-dpi, width=640px, user-scalable=no">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/dzp/cj.css">
<title>抽奖活动</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dzp/jquery.easing.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dzp/jQueryRotate.2.2.js"></script>
</head>
<body>
<div class="b_box">
  	<div class="ly-plate">
     	<div class="rotate-bg">
     		<div class="lottery-star"><img src="${pageContext.request.contextPath}/images/dzp/rotate-static.png" id="lotteryBtn"></div>
     	</div>
	</div>
	<div class="h_text">
		<ul>
			<p>活动规则:</p>
			<li>1. 没个用户只可抽奖1次！</li>
			<li>2. 抽中奖的客户，根据提示信息领取相对应的奖品。</li>
			<c:if test="${!empty  startdate }">
			  <li>3. 转盘${startdate}开始，${enddate}结束。</li>
			</c:if>
			
			<li id="priceNum"></li>
		</ul>
	</div>
	<div class="prompt">
		<div class="winning">
			<div class="close"></div>
			<dl>
				<dt></dt>
				<dd>恭喜你！<span id="awards"></span></dd>
				<a href="#" class="receive">点击领取奖励</a>
			</dl>
		</div>
		<div class="loser">
			<div class="close"></div>
			<dl>
				<dt></dt>
				<dd><span id="lawards"></span></dd>
<!-- 				<a href="#" class="share"> -->
<!-- 				分享至朋友圈，<br/> 可再获取一次领奖机会！</a> -->
			</dl>
		</div>
		<div class="reminder"><!--抽取过一次并没有得到1,2,3大奖-->
			<div class="close"></div>
			<dl>
				<dt></dt>
				<dd>您已抽取过一次！</dd>
<!-- 				<a href="#" class="share"> -->
<!-- 				分享至朋友圈，<br/> 可再获取一次领奖机会！</a> -->
			</dl>
		</div>
		<div class="reminderwin"><!--抽取过一次并得到1,2,3大奖-->
			<div class="close"></div>
			<dl>
				<dt></dt>
				<dd>您已得过奖！</dd>
			</dl>
		</div>
	</div>
</div>
<script>
$(function(){
	var firstc=true;//判断是不是第一次抽奖;
	var firwin=false;//判断是不是已经得过奖励;
// 	var timeOut = function(){  //超时函数
// 		$("#lotteryBtn").rotate({
// 			angle:0, 
// 			duration: 10000, 
// 			animateTo: 2160, //这里是设置请求超时后返回的角度，所以应该还是回到最原始的位置，2160是因为我要让它转6圈，就是360*6得来的
// 			callback:function(){
// 				$('.prompt').delay(5000).show();
// 				$('.reminder').show();
// 			}
// 		}); 
// 	}; 
	var rotateFunc = function(awards,angle,text){  //awards:奖项，angle:奖项对应的角度
		$('#lotteryBtn').stopRotate();
		$("#lotteryBtn").rotate({
			duration: 5000, 
			animateTo: angle+1440, //angle是图片上各奖项对应的角度，1440是我要让指针旋转4圈。所以最后的结束的角度就是这样子^^
			callback:function(){
				$('.prompt').delay(5000).show();
				if(awards==1||awards==2||awards==3){
					$('.winning').show();
					$("#awards").html(text);
					firwin=true;
				}else{
					$('.loser').show();
					$("#lawards").html(text);
				}
			}
		}); 
	};
	

	$("#lotteryBtn").rotate({
			bind : {
				click : function() {
					if (firstc && !firwin) {
						// 					var data = [7,6,5,8,0,4,0,0,0,1,0,0]; //返回的数组
						// 					data= data[Math.floor(Math.random()*data.length)];
						$.ajax({
									type : "POST",
									url : "${pageContext.request.contextPath}/user/choujiang",
									data : {},
									dataType : "json",
									success : function(data) {
										var datas = data.data;
										var status = data.status;
										if(status==200){
											afterAjax(datas);
										}else{
											var msg =data.msg;
											alert(msg);
										}
									}
								});

					} else {
						$('.prompt').delay(5000).show();
						if (!firwin) {
							$('.reminder').show();
						} else {
							$('.reminderwin').show();
						}
					}
				}
			}
		});
		function afterAjax(data) {
			if (data == 1) {
				rotateFunc(1, 112, '获得特等奖');
			}if (data == 2) {
				rotateFunc(2, 157, '获得一等奖');
			} else if (data == 3) {
				rotateFunc(3, 247, '获得二等奖');
			} else if (data == 4) {
				rotateFunc(4, 22, '获得三等奖');
			} else if (data == 5) {
				var angle = [ 67, 202, 292, 337 ];
				angle = angle[Math.floor(Math.random() * angle.length)]
				rotateFunc(5, angle, '获得纪念奖');
			}
// 			else if (data != 0) {
// 				var angle = [ 67, 112, 202, 292, 337 ];
// 				angle = angle[Math.floor(Math.random() * angle.length)]
// 				rotateFunc(0, angle, '恭喜你！获得代金卷');
// 				firwin = true;
// 			} 
			else {
				var angle = [ 112, 292 ];//扩大基数量 使得都是0参数，因此已112度和292度为两个角度的奖励最低
				angle = angle[Math.floor(Math.random() * angle.length)]
				rotateFunc(4, angle, '很遗憾这次您未抽中奖');
				firwin = false;
			}
			firstc = false;
			$("#priceNum").html(data);
		}
		$('.close').click(function() {
			$(this).parent().hide().parent().hide();
		})
// 		$('.share').click(function() {
// 			firstc = true;
// 			if (!firwin) {
// 				$(this).parent().parent().hide().parent().hide();
// 				alert('分享至朋友圈，成功！获得一次抽奖机会');
// 				location.reload();
// 			}
// 		})
	})
</script>
</body>
</html>
