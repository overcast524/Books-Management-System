<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="bootstrap/css/bootstrap.css" />
<script type="text/javascript" src="bootstrap/js/jquery.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
<link rel="stylesheet" href="bootstrap/css/bootstrapValidator.css" />
<script type="text/javascript" src="iconfont/iconfont.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrapValidator.js"></script>

<title>修改密码</title>

<script type="text/javascript" src="js/ajax.js"></script>
<script type="text/javascript">
 
	$(function() {
		$(".form-horizontal").bootstrapValidator({

			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'

			},
			fields : {
				newpassword : {
					validators : {
						notEmpty : {

							message : '密码不能为空'
						},
						different : {
							field : 'password2',
							message : '不能与原密码一致'
						},
						regexp : {
							regexp : /^(?=.*\d)(?=.*[A-z])[A-z\d]{6,15}$/,
							message : '密码是6-15位，必须含有字母和数字'
						},
					}
				},
				repassword : {
					validators : {
						notEmpty : {

							message : '确认密码不能为空'
						},
						identical : {
							field : 'newpassword',
							message : '与密码不一致，请重新输入!'
						}
					}
				},
				password2: {
					validators : {

						notEmpty : {

							message : '密码不能为空'
						},
						 

						// threshold :  6 , 有6字符以上才发送ajax请求，（input中输入一个字符，插件会向服务器发送一次，设置限制，6字符以上才开始）
						remote : {//ajax验证。server result:{"valid",true or false} 向服务发送当前input name值，获得一个json数据。例表示正确：{"valid",true}  
							url : "AdminServlet",//验证地址
							 //提示消息
							delay : 2000,//每输入一个字符，就发ajax请求，服务器压力还是太大，设置2秒发送一次ajax（默认输入一个字符，提交一次，服务器压力太大）
							type : 'POST',//请求方式
							message : '原密码错误',
							//自定义提交数据，默认值提交当前input value
							data : function(validator) {
								return {
									action : "yanzhengmm",
									password2:$('input[name=password2]').val() 
								}
							}

						}

					}

				}
			}
		});
		});
</script>
<style>
 
body {
	margin: 0;
}

.icon {
	width: 1em;
	height: 1em;
	vertical-align: -0.15em;
	fill: currentColor;
	overflow: hidden;
	margin-top: 10px;
}

.footer {
	color: #777;
	border-top: 1px solid #e5e5e5;
	text-align: center;
	padding: 30px 0;
}

#div1 {
	height: 80px;
}

.bg {
	background: url("tu/ta3.png") ;
	background-size: cover;
	height: 758px;
	padding-top: 90px;
}

#fr{
	border: 2px solid #E9445C;
	border-style: groove;
	 
}

h2 {
	font-size: 34px;
}

label {
 
	font-size: 16px;
}

.btn-danger {
	background-color: #E9445C;
}

a {
	text-decoration: none;
	color: #FFFFFF;
}
</style>
</head>
<body  >
 

		<c:if test="${!empty mag }">
			<script>
				alert("${mag}");
			</script>
		</c:if>
		<c:remove var="mag" />
 <!-- 上 -->
	<div class="container-fluid">
		<div class="row header" id="div1">		
			<div class="col col-md-8 col-sm-10">
				<div class="col col-md-9  ">
					<marquee hspace="20" align="texttop" behavior="slide"
						scrollamount="20" direction="left" width=400>
				     <svg class="icon" aria-hidden="true" style="font-size: 50px;">
                          <use xlink:href="#icon-tushu" ></use>
                 </svg>  <font size="7" color="#CF1834" face=华文新魏><b>甲骨文</b></font><font
							size="4" color="#606060">图书管理系统</font><font size="4"
							color="#b2b2b2">|</font><font size="3" color="#606060">登录</font>
					</marquee>


				</div>
			</div>
		</div>

		<div class="container-fluid bg">
			<!-- 中 -->
			<div class="container">
				<div class="col col-md-6 col-md-offset-3" id="fr">
	 
		<form name="register" action="AdminServlet?action=updatemm"
			method="post" class="form-horizontal">
			<div class="form-group">
							<h2 class="col-sm-6 col-sm-offset-3">
								<font color="#EB1B3F" size="7" face="华文新魏"><b>修改密码</b></font>
							</h2>
			 </div>
			<br>
			<div class="form-group">
				<label class="col-sm-3 col-sm-offset-2 control-label text-danger ">原密码：</label>
				<div class="col-sm-4">
					<input type="password" name="password2" class="form-control input-sm"/>
				</div>
			</div>
			<br>
			<div class="form-group">
				<label class="col-sm-3 col-sm-offset-2 control-label text-danger ">新密码：</label>
				<div class="col-sm-4">
					<input type="password" name="newpassword"
						 class="form-control input-sm"/>
				</div>
			</div>
			<br>
			<div class="form-group">
				<label class="col-sm-3 col-sm-offset-2 control-label text-danger">确认新密码：</label>
				<div class="col-sm-4">
					<input type="password" name="repassword" class="form-control input-sm"/>
				</div>
			</div>
			<br>
			<br>
			<div class="form-group">
				<div class="col-lg-3 col-lg-offset-4 ">
					<button type="submit" class="btn btn-danger">
						 确认修改 
					</button>
				</div>
				
				<div class="col-lg-3  ">
				 
						<a href="index.jsp" target="_top" class="btn btn-danger ">返回     <span class="glyphicon glyphicon-repeat"></span></a>
					 
				</div>
				</div>
				<br>
			<br>
		</form>
				</div>
			</div>
		</div>
		<footer class="footer">
			<!-- 下 -->

			<h4>
				<font size="3" color="#b1b1b1">甲骨文图书管理系统（王旭）版权所有&copy;2025-2030</font>
			</h4>
		</footer>
 </div>
</body>
</html>