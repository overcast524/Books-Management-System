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
<title>修改图书</title>
 
<script type="text/javascript" src="js/ajax.js"></script>
<script>
 
$(function() {
	var flname = document.register.flname;

	ajax({
		method : "POST",
		url : "FenleiServlet",
		params : "action=showOne2",
		type : "json",
		success : function(content) {

			for (var i = 0; i < content.length; i++) {

				var name = content[i];

				var opt = document.createElement("option");
				opt.value = name.name;
				opt.innerHTML = name.name;
				flname.appendChild(opt);

			}

		}
	});

		$("#fm").bootstrapValidator({

			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'

			},
			fields : {
				name : {
					validators : {

						notEmpty : {

							message : '图书名不能为空'
						},
						stringLength : {
							min : 1,
							max : 10,
							message : '书名长度小于20'
						},

						// threshold :  6 , 有6字符以上才发送ajax请求，（input中输入一个字符，插件会向服务器发送一次，设置限制，6字符以上才开始）
						remote : {//ajax验证。server result:{"valid",true or false} 向服务发送当前input name值，获得一个json数据。例表示正确：{"valid",true}  
							url : "BookServlet",//验证地址
							 //提示消息
							delay : 2000,//每输入一个字符，就发ajax请求，服务器压力还是太大，设置2秒发送一次ajax（默认输入一个字符，提交一次，服务器压力太大）
							type : 'POST',//请求方式

							//自定义提交数据，默认值提交当前input value
							data : function(validator) {
								return {
									action : "yanzhengxg",
									name : $("#name").val(),
									flname : $("#flname").val(),
									oldflname:$("#oldflname").val()
								}
							}

						}

					}

				},
				money : {

					validators : {

						notEmpty : {

							message : '价格不能为空'
						}
					}

				},
				flname : {

					validators : {

						callback : {

							callback : function(value, validator) {

								if (flname.value == "----请选择----") {
									return {
										valid : false,

										message : '必须选择',
									}

								}
								return true;

							}
						} 
					}

				},
				press : {
					validators : {

						notEmpty : {

							message : '出版社不能为空'
						}
					}
				},
				state : {
					validators : {
						notEmpty : {

							message : '请选择是否借出'
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

form {
	margin-top: 20px;
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
.zt{
 font-size: 10px;
}
hr{
border: 1px solid #337AB7;
}
#div9{
	background-image: url("tu/t6.jpg");
	background-size: cover;
	height: 840px;
}
</style>
<body  >

<div class="container-fluid"  id="div9">
		<!--  <marquee align="texttop" behavior="slide" scrollamount="60"
			direction="up">-->
		<c:if test="${!empty mag }">
		<script>
			alert("${mag}");
		</script>
	</c:if>
	<c:remove var="mag" />
	 	 
	<div class="col col-md-5 col-md-offset-2" id="div1">

		<h1 class="text-center text-info">修改图书</h1>
		<hr>

		<form action="BookServlet?action=update2" method="post" id="fm"
			name="register"class="form-horizontal">
			<input type="hidden" name="id" value="${fl.id }" /> <input
				type="hidden" name="pageNew" value="${pageNew }" />

			<div class="form-group">
				<label class="col-sm-3 col-sm-offset-2 control-label text-info">图书分类:</label>
				<div class="col-sm-4">
					<input id="oldflname" type="text" disabled="disabled" value="${fl.flname }" class="form-control input-sm"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 col-sm-offset-2 control-label text-info">选择新分类:</label>
				<div class="col-sm-4">
				<select name="flname" class="form-control input-sm" id="flname">
							<option>${fl.flname}</option>
							<option>----请选择----</option>
					</select> 
						</div>
			</div>
					<div class="form-group">
				<label class="col-sm-3 col-sm-offset-2 control-label text-info">图书名称:</label>
				<div class="col-sm-4"><input type="text" name="name"
							value="${fl.name }" class="form-control input-sm"/>	</div>
			</div>
					<div class="form-group">
				<label class="col-sm-3 col-sm-offset-2 control-label text-info">图书价格:</label>
				<div class="col-sm-4"><input type="text" name="money" value="${fl.money }" class="form-control input-sm"/>	</div>
			</div>
					<div class="form-group">
				<label class="col-sm-3 col-sm-offset-2 control-label text-info">出版社:</label>
				<div class="col-sm-4"><input type="text" name="press" value="${fl.press }" class="form-control input-sm"/>	</div>
			</div>
				 
			<div class="form-group ">
						<label class="col-sm-3 control-label col-sm-offset-2 text-info">状&nbsp;&nbsp;态:</label>
						<c:if test="${fl.state=='未借出'}"> 
						<div class="col-sm-2 ">
							<label class="zt"><input type="radio" name="state" checked="checked" value="未借出"/>未借出 </label>
						</div>
						<div class="col-sm-2 ">
							<label class="zt"><input type="radio" name="state" value="借出" />借出</label>
						</div>
						</c:if>
						<c:if test="${fl.state=='借出'}"> 
						<div class="col-sm-2 ">
							<label class="zt"><input type="radio" name="state" value="未借出"/>未借出 </label>
						</div>
						<div class="col-sm-2  ">
							<label class="zt"><input type="radio" name="state" checked="checked"  value="借出" />借出</label>
						</div>
						</c:if>
						 
						
					</div>
				<div class="form-group">
				<label class="col-sm-3 col-sm-offset-2 control-label text-info">借书人:</label>
				<div class="col-sm-4"><input type="text"   name="reader"
							value="${fl.reader }" class="form-control input-sm"/>	
							</div>
					</div>
				 
						<div class="form-group">
						<div class="col-sm-2 col-sm-offset-4">
							<button type="submit" class="btn   btn-success">
								修改 <span class="glyphicon glyphicon-cog"></span>
							</button>
						</div>

						<div class="col-sm-2  col-sm-offset-1">

							<a href="BookServlet?action=showPasgeBook&pageNew=${pageNew }"
								class="btn btn-info ">返回 <span
								class="glyphicon glyphicon-repeat"></span></a>

						</div>
					</div>
		</form>
					</div>
			</div>
</body>
</html>