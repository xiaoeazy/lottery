<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="target-densitydpi=device-dpi, width=device-width, user-scalable=no">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/dzp/index.css">
<title>抽奖活动</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script>
	function tijiao(){
		var usernameShow = $("#usernameShow").val();
		if(usernameShow==""){
			alert("用户名不能为空");
			return ;
		}
		var idcardShow =$("#idcardShow").val();
		if(idcardShow==""){
			alert("身份证号码不能为空");
			return;
		}
		var phoneShow =$("#phoneShow").val();
		if(phoneShow==""){
			alert("手机号不能为空");
			return;
		}
		var salespersonidShow =$("#salespersonidShow").val();
		if(salespersonidShow==""){
			alert("服务专员服务代码不能为空");
			return;
		}
		$("#username").val(usernameShow);
		$("#idcard").val(idcardShow);
		$("#phone").val(phoneShow);
		$("#salespersonid").val(salespersonidShow);
		document.getElementById("form").submit();
	}

</script>
</head>
<body>
<div class="b_box">
   <div class="bg"><img src="${pageContext.request.contextPath}/images/dzp/i_banner.png" width="100%"></div>
   <div class="text_box">
   		<form action="${pageContext.request.contextPath}/user/login" id="form" method="post">
	   		<input type="hidden" value="1" name="username" id="username"/>
	   		<input type="hidden" value="1" name="idcard"  id="idcard" />
	   		<input type="hidden" value="1" name="phone"  id="phone" />
	   		<input type="hidden" value="1" name="salespersonid"  id="salespersonid" />
	   		
   		</form>
   		
   		<div class="name_div">
       		<input type="text" value="王明海" class="name" id="usernameShow" placeholder="请输入用户名">
       		<div></div>
       	</div>
       	<div class="name_div">
       		<input type="text" value="320622194108030193" class="name" id="idcardShow" placeholder="请输入身份证号码">
       		<div></div>
       	</div>
      	<div class="name_div">
       		<input type="text" value="1" class="phone" id="phoneShow" placeholder="请输入手机号">
       		<div></div>
       	</div>
       		<div class="name_div">
       		<input type="text" value="1" class="name" id="salespersonidShow" placeholder="服务专员服务代码">
       		<div></div>
       	</div>
        <a href="javascript:void(0)"  onclick='tijiao()'  class="cj">我要抽奖</a>
   </div>
</div>
<script type="text/javascript">
	$(function(){
		var errorInfo = "${errorInfo}";
		if(errorInfo!="")
			alert(errorInfo);
		$('.cj').click(function(e){
			// var e=window.event;
			$("input").each(function(){
				var value=$(this).attr('value');
				var placeholder=$(this).attr('placeholder');
				if(value==''){
					$(this).addClass('prompt');
					//$(this).next().html(placeholder);//下文提示部分;添加样式请在下IPUNT下方<div>添加class然后去写样式;
					e.preventDefault();
				}else{
					if($(this).hasClass('prompt')){
					$(this).addClass('prompt');}
				}
			})
		});
		$("input").click(function(){
			if($(this).hasClass('prompt')){
				$(this).removeClass('prompt');
			}
		});
	});
</script>
</body>
</html>
