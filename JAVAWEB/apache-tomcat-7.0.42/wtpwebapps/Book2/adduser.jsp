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
<script type="text/javascript" src="bootstrap/js/bootstrapValidator.js"></script>

<title>添加用户</title>

<script type="text/javascript" src="js/ajax.js"></script>
<script type="text/javascript">
 						
 
	$(function(){
		$(".form-horizontal").bootstrapValidator({
			feedbackIcons:{
				 valid: 'glyphicon glyphicon-ok',
		            invalid: 'glyphicon glyphicon-remove',
		            validating: 'glyphicon glyphicon-refresh'
				
			}, 
			fields: {
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
							url : "UserServlet",//验证地址
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
				},  
				regtime: {
	        validators: {
	            date: {
	                format: 'YYYY/MM/DD',
	                message: 'The birthday is not valid'
	            }
	        }
	    }
			
		}
		
			 
		})
		
	});



</script>
<style>
#div1 {
 
	margin-top: 20px;
	
}
#div9{
	background-image: url("tu/t6.jpg");
	background-size: cover;
	height: 840px;
}
form {
	margin-top: 20px;
}

.container {
	margin-top: 100px;
}

.btn {
	margin-top: 30px;
}
h3{
margin-top: 30px;
}
label{
   font-size: 16px;
}
hr{
border: 1px solid #337AB7;
}
</style>
</head>
<body >

	<div class="container-fluid"  id="div9">
		<c:if test="${!empty mag }">
			<script>
				alert("${mag}");
			</script>
		</c:if>
		<c:remove var="mag" />
		<!--  <marquee align="texttop" behavior="slide" scrollamount="60"
			direction="up">-->
		<div class="col col-md-5 col-md-offset-2" id="div1">
	<h1 class="text-center text-info">添加用户</h1>
             <hr >
			<table    >
				<tr>
					<td valign="top" width="60%">
						<form name="register" action="UserServlet?action=add"
							method="post"  
							enctype="multipart/form-data" class="form-horizontal">
							<div class="form-group">
								<label class="col-sm-3 col-sm-offset-2 control-label text-info">真实姓名:</label>
								<div class="col-sm-4">
									<input type="text" name="name"   class="form-control input-sm" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 col-sm-offset-2 control-label text-info">用户名:</label>
								<div class="col-sm-4">
									<input type="text" name="username"   class="form-control input-sm"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 col-sm-offset-2 control-label text-info">密码:</label>
								<div class="col-sm-4">
									<input type="text" name="password"   class="form-control input-sm"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 col-sm-offset-2 control-label text-info">手机号:</label>
								<div class="col-sm-4">
									<input type="text" name="phone"   class="form-control input-sm"/>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 col-sm-offset-2 control-label text-info">添加头像:</label>
								<div class="col-sm-4">
									<input type="file" name='touxiang' class="form-control input-sm" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label col-sm-offset-2 text-info">注册时间:</label>
								<div class="col-sm-4">
									<input type="date" name="regtime"   class="form-control input-sm" />
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-2 col-sm-offset-4 ">
									<button type="submit" class="btn btn-success">
										添加 <span class="glyphicon glyphicon-ok"></span>
									</button>
								</div>
								<div class="col-sm-2  col-sm-offset-1">
									<button type="reset" class="btn btn-info">
										重置 <span class="glyphicon glyphicon-repeat"></span>
									</button>
								</div>
							</div>
							<br>
						</form>
					</td>

				</tr>

			</table>
		</div>

		<!-- </marquee>-->
	</div>
</body>
</html>