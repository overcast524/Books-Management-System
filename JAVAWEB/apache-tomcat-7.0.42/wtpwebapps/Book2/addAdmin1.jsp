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

<title>注册界面</title>

<script type="text/javascript" src="js/ajax.js"></script>
<script type="text/javascript">
	$(function() {

		$(".form-horizontal")
				.bootstrapValidator(
						{

							feedbackIcons : {
								valid : 'glyphicon glyphicon-ok',
								invalid : 'glyphicon glyphicon-remove',
								validating : 'glyphicon glyphicon-refresh'

							},
							fields : {

								name : {
									validators : {

										notEmpty : {

											message : '姓名不能为空'
										},
										stringLength : {
											min : 2,
											max : 15,
											message : '姓名必须是2-15个汉字'
										},
										regexp : {
											regexp : /^[\u0391-\uFFE5]{2,15}$/,
											message : '姓名必须是2-15个汉字'
										}

									}
								},
								username : {
									validators : {

										notEmpty : {

											message : '用户名不能为空'
										},
										stringLength : {
											min : 3,
											max : 15,
											message : '用户名必须是3-15个字母或数字组成'
										},

										// threshold :  6 , 有6字符以上才发送ajax请求，（input中输入一个字符，插件会向服务器发送一次，设置限制，6字符以上才开始）
										remote : {//ajax验证。server result:{"valid",true or false} 向服务发送当前input name值，获得一个json数据。例表示正确：{"valid",true}  
											url : "AdminServlet",//验证地址
											message : '该用户名已存在',//提示消息
											delay : 2000,//每输入一个字符，就发ajax请求，服务器压力还是太大，设置2秒发送一次ajax（默认输入一个字符，提交一次，服务器压力太大）
											type : 'POST',//请求方式

											//自定义提交数据，默认值提交当前input value
											data : function(validator) {
												return {
													action : "yanzheng",
													username : $(
															"input[name=username]")
															.val()

												}
											}

										}

									}

								},
								password : {
									validators : {
										notEmpty : {

											message : '密码不能为空'
										},
										different : {
											field : 'username',
											message : '密码不能和用户名一样'
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
											field : 'password',
											message : '与密码不一致，请重新输入!'
										}
									}
								},
								phone : {
									validators : {
										notEmpty : {

											message : '电话号码不能为空'
										},
										regexp : {
											regexp : /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/,
											message : '请输入正确的电话号码'
										},
									}
								},
								touxiang : {

									validators : {
										file : {
											extension : 'pdf,jpg,gif,png,bmp,jpeg',
											type : 'image/pdf,image/jpg,image/gif,image/png,image/bmp,image/jpeg',
											message : '头像不合法.'
										}
									}
								}

							}

						})
	});
</script>
<style>
td {
	color: #663300;
	font-size: 20px;
}

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
	background: url("tu/ta3.png") no-repeat center;
	background-size: cover;
	height: 760px;
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
<body>
	<div class="container-fluid">
		<div class="row header" id="div1">
			<!-- 上 -->
			<div class="col col-md-8 col-sm-10">
				<div class="col col-md-9 col-md-offset-1">
					<marquee hspace="20" align="texttop" behavior="slide"
						scrollamount="20" direction="left" width=400>
				  <svg class="icon" aria-hidden="true" style="font-size: 50px;">
              <use xlink:href="#icon-tushu" ></use>
                 </svg> 
            
						<font size="7" color="#CF1834" face=华文新魏><b>甲骨文</b></font><font
							size="4" color="#606060">图书管理系统</font><font size="4"
							color="#b2b2b2">|</font><font size="3" color="#606060">注册</font>
					</marquee>


				</div>
			</div>
		</div>

		<div class="container-fluid bg">
			<!-- 中 -->
			<div class="container">
				<div class="col col-md-5 col-md-offset-4" id="fr">
					<form name="register" action="AdminServlet?action=addadmin1" method="post" enctype="multipart/form-data" class="form-horizontal">
						<div class="form-group">
							<h2 class="col-sm-6 col-sm-offset-3">
								<font color="#EB1B3F" size="7" face="华文新魏"><b>用户注册</b></font>
							</h2>
						</div>

						<div class="form-group">
							<label for="username" class="col-sm-4 control-label "> <font
								color="#E9445C">姓名:</font></label>
							<div class="col-sm-6">
								<input type='text' name='name' class="form-control input-sm" />
							</div>
						</div>
						<div class="form-group">
							<label for="username" class="col-sm-4 control-label   ">
								<font color="#E9445C"> 电话: </font>
							</label>
							<div class="col-sm-6">
								<input type='text' name='phone' class="form-control input-sm">
							</div>
						</div>
						<div class="form-group">
							<label for="username" class="col-sm-4 control-label "> <font
								color="#E9445C"> 用户名 </font></label>
							<div class="col-sm-6">
								<input type='text' name='username' class="form-control input-sm">

							</div>
						</div>
						<div class="form-group">
							<label for="username" class="col-sm-4 control-label "> <font
								color="#E9445C"> 头像: </font></label>
							<div class="col-sm-6">
								<input type="file" name='touxiang' class="form-control input-sm">
							</div>
						</div>
						<div class="form-group">
							<label for="username" class="col-sm-4 control-label  "> <font
								color="#E9445C"> 密&nbsp;&nbsp;码: </font></label>
							<div class="col-sm-6">
								<input type="password" name='password'
									class="form-control input-sm">
							</div>
						</div>
						<div class="form-group">
							<label for="username" class="col-sm-4 control-label "><font
								color="#E9445C"> 确认密码: </font></label>
							<div class="col-sm-6">
								<input type="password" name='repassword'
									class="form-control input-sm">
							</div>
						</div>
						<div class="form-group">
							<div class="col-lg-3 col-lg-offset-4 ">
								<button type='submit' class="btn btn-danger ">
									注册 <span class="glyphicon glyphicon-plus"
										style="font-size: 2px;"></span>
										
								</button>
							</div>
							<div class="col-lg-3  ">
							 
									<a href='login.jsp' class="btn btn-danger ">返回        <span class="glyphicon glyphicon-repeat" style="font-size: 6px;">
									</span></a>
							 
							</div>
						</div>
 
						<br> <br>
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