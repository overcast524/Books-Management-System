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
<title>多项修改分类</title>
 
<script type="text/javascript" src="js/ajax.js"></script>
<script type="text/javascript">
 
	 
	$(function(){

		 ajax({
				method:"POST",
				url:"FenleiServlet",
				params:"action=showOne2",
				type:"json",
				success:function(content){ 				

					for(var i=0;i<content.length;i++){
						 
						     var name=content[i];
			 
							 var opt=document.createElement("option");
							 opt.value=name.name;
							 opt.innerHTML=name.name;
							 oldname.appendChild(opt);
							 
						}
						
						
				}
			});
	 
	 
		$(".form-horizontal").bootstrapValidator({
			feedbackIcons:{
				 valid: 'glyphicon glyphicon-ok',
		            invalid: 'glyphicon glyphicon-remove',
		            validating: 'glyphicon glyphicon-refresh'
				
			}, 
			fields: {
				oldname : {

					validators : {

						callback : {

							callback : function(value, validator) {

								if (oldname.value == "----请选择----") {
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
				 name : {
					validators : {

						notEmpty : {

							message : '分类名不能为空'
						},
			 
						// threshold :  6 , 有6字符以上才发送ajax请求，（input中输入一个字符，插件会向服务器发送一次，设置限制，6字符以上才开始）
						remote : {//ajax验证。server result:{"valid",true or false} 向服务发送当前input name值，获得一个json数据。例表示正确：{"valid",true}  
							url : "FenleiServlet",//验证地址
							 //提示消息
							delay : 2000,//每输入一个字符，就发ajax请求，服务器压力还是太大，设置2秒发送一次ajax（默认输入一个字符，提交一次，服务器压力太大）
							type : 'POST',//请求方式

							//自定义提交数据，默认值提交当前input value
							data : function(validator) {
								return {
									action : "yanzheng",
									 name : $(
											"input[name=name]")
											.val() 
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
#div1 {
 
	margin-top: 20px;
	
}

form {
	margin-top:30px;
 margin-left:40px;
	width: 900px;
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
width: 600px;
}
option{
  text-align: center;
}
#div9{
	background-image: url("tu/t6.jpg");
	background-size: cover;
	height: 840px;
}
</style>
</head>
<body  >
	<div class="container-fluid"  id="div9">
		<c:if test="${!empty mag }">
			<script>
				alert("${mag}");
			</script>
		</c:if>
		<c:remove var="mag" />
		<!--  <marquee align="texttop" behavior="slide" scrollamount="60"
			direction="up">-->
		<div class="col col-md-8 col-md-offset-1" id="div1">
	<h1 class="text-center text-info">修改分类</h1>
          <hr >
	<table  >
	 
		<tr>
			<td  > 
				<form name="register"class="form-horizontal"  action="FenleiServlet?action=update2" method="post">
					 
						<div class="form-group f1">
								<label class="col-sm-5  control-label text-info">请选择要修改的分类的名字:</label>
								<div class="col-sm-3">
								<select name="oldname"  id="oldname"  class="form-control input-sm">					     
									<option>----请选择----</option> 								 
                          </select>
                         	</div>
							</div>
                          <div class="form-group f1">
								<label class="col-sm-5  control-label text-info">请输入新的分类的名字:</label>
								<div class="col-sm-3"><input type="text" name="name" class="form-control input-sm" /> 
                          </div>
							</div>

					   <div class="form-group">
				<div class="col-sm-2 col-sm-offset-4 ">
					<button type="submit" class="btn btn-success">
							 修改    <span class="glyphicon glyphicon-cog"></span>
					</button>
				</div>
				
				<div class="col-sm-2  ">
				 
						<a href="FenleiServlet?action=showPasgefl&pageNew=${pageNew}"  class="btn btn-info ">返回     <span class="glyphicon glyphicon-repeat"></span></a>
					 
				</div>
				</div>
				 
				</form></td>
		</tr>
	</table>
	</div>
				</div>
</body>
	 
				
</html>