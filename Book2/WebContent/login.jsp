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

<title>图书管理系统</title>
<script>
	$(function() {
		$(".form-horizontal").bootstrapValidator({

			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'

			},
			fields : {
				/*username : {
					validators : {

						notEmpty : {

							message : '用户名不能为空'
						},
						regexp : {
							regexp : /^[A-z0-9]{3,15}$/,
							message : '用户名必须是3-15个字母或数字组成'
						},
					}
				},*/
				password : {
					validators : {

						notEmpty : {

							message : '密码不能为空'
						},

						  threshold : 6 , //有6字符以上才发送ajax请求，（input中输入一个字符，插件会向服务器发送一次，设置限制，6字符以上才开始）
						remote : {//ajax验证。server result:{"valid",true or false} 向服务发送当前input name值，获得一个json数据。例表示正确：{"valid",true}  
							url : "AdminServlet",//验证地址
							message : '用户名或密码错误',//提示消息
							delay : 2000,//每输入一个字符，就发ajax请求，服务器压力还是太大，设置2秒发送一次ajax（默认输入一个字符，提交一次，服务器压力太大）
							type : 'POST',//请求方式

							//自定义提交数据，默认值提交当前input value
							data : function(validator) {
								return {
									action : "yanzhenglg",
									username : $("input[name=username]").val(),
									password : $("input[name=password]").val()
								}
							}

						}

					}

				}
			}

		})
	});
</script>
<style>
body {
	margin: 0
}

.bg {
		background: url("tu/t1.png") ;
	background-size: cover;
	height: 756px;
	padding-top: 250px;
}

.footer {
	color: #777;
	border-top: 1px solid #e5e5e5;
	text-align: center;
	 padding-top: 20px;
}

#div1 {
	height: 80px;
}

.btn-danger {
	background-color: #E9445C;
}

#form {
	border: 2px solid #EF4B68;
	border-style: groove;
	width: 400px;
	background-color: #FFFFFF;
}
label{
font-size:19px;
 
 
}
h2{
font-size:39px;
}

.icon {
	width: 1em;
	height: 1em;
	vertical-align: -0.15em;
	fill: currentColor;
	overflow: hidden;
	margin-top: 10px;
}
.gl{
margin-left: 25%;

}
</style>
</head>
<body>
	<div class="container-fluid">
		<c:if test="${!empty mag }">
			<script>
				alert("${mag}");
			</script>
		</c:if>
		<c:remove var="mag" />
	<div class="row header" id="div1">
			<!-- 上 -->
			<div class="col col-md-8 col-sm-10">
				<div class="col col-md-6  ">
					<marquee hspace="20" align="texttop" behavior="slide" scrollamount="20" direction="left" width=400>
				  <svg class="icon" aria-hidden="true" style="font-size: 50px;">
              <use xlink:href="#icon-tushu" ></use>
                 </svg>  <font size="7" color="#CF1834" face=华文新魏><b>亳州学院</b></font><font
							size="4" color="#606060">图书管理系统</font><font size="4"
							color="#b2b2b2">|</font><font size="3" color="#606060">登录</font>
					</marquee>


				</div>
			</div>
		</div>
		<div class="container-fluid bg">
			<!-- 中 -->
			<div class="col-md-4 col-sm-10 col-md-offset-3" id="form">
				<form action="AdminServlet?action=login" method="post"
					class="form-horizontal" >
					<div class="form-group">
					<div class="gl">
						<h2>
							<font color="#E72E49"   face="华文新魏"><b>管理员登录</b></font>
						</h2>
						</div>
					</div>
					<div class="form-group">
						<label for="username" class="col-md-4 control-label"><font
							color="#EC5970"  face="宋体"><b>登录账号:</b></font></label>
						<div class="col-md-6">
							<input type="text" name="username" id="username"
								class="form-control  input-sm" />
						</div>
					</div>
					<div class="form-group">
						<label for="password" class="col-md-4 control-label"> <font
							color="#EC5970"  face="宋体"><b>密码:</b></font></label>
						<div class="col-md-6">
							<input type="password" name="password" id="password"
								class="form-control  input-sm" />
						</div>
					</div>

					<div class="form-group">
						<div class="col-md-6 col-md-offset-4">
							<div class="checkbox">
								<label> <input type="checkbox" class="checkbox" checked="checked" /><font size="2"
									color="silver">两周内自动登录</font>
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-10 col-md-offset-1">
							<button class="btn btn-danger  btn-md btn-block" type="submit">登录</button>
						</div>
					</div>

					<div class="form-group">
						<a href="addAdmin1.jsp?"><font size="2" color="#F9BBC6"><center>还不是会员？立即注册</center></font></a>
					</div>
				</form>

			</div>
		</div>

		<footer class="footer">
			<!-- 下 -->

			<h4>
				<font size="3" color="#b1b1b1">亳州学院图书管理系统（王旭）版权所有&copy;2025-2030</font>
			</h4>
		</footer>
	</div>
</body>
</html>