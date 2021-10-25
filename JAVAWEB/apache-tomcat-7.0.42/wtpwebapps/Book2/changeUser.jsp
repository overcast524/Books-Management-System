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
<title>修改用户信息</title>
<script type="text/javascript" src="js/ajax.js"></script>
<script >
	$(function() {
   
		$("#f2").bootstrapValidator({
			       
	 
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
								password : {
									validators : {
										notEmpty : {

											message : '密码不能为空'
										},
										callback : {

											callback : function(value, validator) {

												if ( $('input[name=password]').val()==$('input[name=username]').val()) {
													return {
														valid : false,

														message : '密码不能和用户名一样',
													}

												}
												return true;

											}
										} ,
										regexp : {
											regexp : /^(?=.*\d)(?=.*[A-z])[A-z\d]{6,15}$/,
											message : '密码是6-15位，必须含有字母和数字'
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
								regtime : {
									validators : {
										notEmpty : {

											message : '日期不能为空'
										},
									 
										date : {
											format : 'YYYY-MM-DD',
											message : '日期格式不正确'
										}
									}
								}

							}

						});

		$("#f1")
				.bootstrapValidator(
						{
							feedbackIcons : {
								valid : 'glyphicon glyphicon-ok',
								invalid : 'glyphicon glyphicon-remove',
								validating : 'glyphicon glyphicon-refresh'

							},
							fields : {

								touxiang : {

									validators : {
										notEmpty : {
									 

										message : '请选择文件'
									},
										file : {
											extension : 'pdf,jpg,gif,png,bmp,jpeg',
											type : 'image/pdf,image/jpg,image/gif,image/png,image/bmp,image/jpeg',
											message : '头像不合法.'
										}
									}
								}
							}

						}); 

	});
</script>
<style>
#div1 {
	margin-top: 20px;
}

#div9 {
	background-image: url("tu/t6.jpg");
	background-size: cover;
	height: 840px;
}

 
.container {
	margin-top: 100px;
}

.btn {
	margin-top: 30px;
}

h3 {
	margin-top: 30px;
}

label {
	font-size: 16px;
}

hr {
	border: 1px solid #337AB7;
}

 
.f1input1 {
 margin-top: 10px;
}
</style>
</head>
<body>

	<div class="container-fluid" id="div9">
		<c:if test="${!empty mag }">
			<script>
				alert("${mag}");
			</script>
		</c:if>
		<c:remove var="mag" />
		<!--  <marquee align="texttop" behavior="slide" scrollamount="60"
			direction="up">-->
		<div class="col col-md-5 col-md-offset-2" id="div1">
  
				<h1 class="text-center text-info">修改用户信息</h1>
				<hr>
				<form id="f1" action="UserServlet?action=updateTouxaing"
					method="post" enctype="multipart/form-data"
					class="form-horizontal f1">
					<div class="form-group">
						<label class="col-sm-3 col-sm-offset-2 control-label text-info">
							<img src="/img${s.touxiang }" width="90" height="90">
						</label>
						<div class="col-sm-4 f1input1">
							<input type="file" name="touxiang" class="form-control input-sm" />
						</div>
					 
						<div class="col-sm-4  f1input2">
						 
						  <button class="btn  " type="submit">修改头像</button>					 
						</div>
						</div>
					 
				</form>
		  
				<form id="f2" action="UserServlet?action=updateUser"  method="post" class="form-horizontal">	
				 <input type="hidden" name="id" value="${s.id }">  				 
					<div class="form-group">
						<label class="col-sm-3 col-sm-offset-2 control-label text-info">真实姓名:</label>
						<div class="col-sm-4">
							<input type="text" name="name" value="${s.name }" class="form-control input-sm" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 col-sm-offset-2 control-label text-info">用户名:</label>
						<div class="col-sm-4">
							<input type="text" name="username" value="${s.username }"
								class="form-control input-sm" disabled />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 col-sm-offset-2 control-label text-info">密码:</label>
						<div class="col-sm-4">
							<input type="text" name="password" value="${s.password }"
								class="form-control input-sm" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 col-sm-offset-2 control-label text-info">手机号:</label>
						<div class="col-sm-4">
							<input type="text" name="phone" value="${s.phone }"
								class="form-control input-sm" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 col-sm-offset-2 control-label text-info">注册时间:</label>
						<div class="col-sm-4">
							<input type="text" name="regtime" value="${s.regtime }"
								class="form-control input-sm" />
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-2 col-sm-offset-4">
							<button type="submit" class="btn   btn-success">
								修改 <span class="glyphicon glyphicon-cog"></span>
							</button>
						</div>

						<div class="col-sm-2  col-sm-offset-1">

							<a href="UserServlet?action=showPasgeUser&pageNew=${pageNew }"
								class="btn btn-info">返回 <span
								class="glyphicon glyphicon-repeat"></span></a>

						</div>
					</div>

				</form>
		 
		</div>
	</div>