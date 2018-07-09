<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="target-densitydpi=device-dpi, width=device-width, user-scalable=no">
<link rel="stylesheet" type="text/css" href="css/index.css">
<title>抽奖活动</title>
<script type="text/javascript" src="js/jquery.min.js"></script>
</head>
<body>
<div class="b_box">
   <div class="bg"><img src="images/i_banner.png" width="100%"></div>
   <div class="text_box">
       	<div class="name_div">
       		<input type="text" value="" class="name" placeholder="请输入手机号">
       		<div></div>
       	</div>
       	<div class="name_div">
       		<input type="text" value="" class="phone" placeholder="请输入地址">
       		<div></div>
       	</div>
        <a href="cj.html" class="cj">我要抽奖</a>
   </div>
</div>
<script type="text/javascript">
	$(function(){
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
