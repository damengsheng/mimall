<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 
 
<title>个人中心-收货地址页面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login2.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>


</head>
<body>
<%@ include file="header.jsp"%>
<!--网站中间内容开始-->
<div id="dingdanxiangqing_body">
    <div id="dingdanxiangqing_body_big">
        <div id="big_left">
           	   <p style="font-size:18px;margin-top: 15px">订单中心</p>
               <a id="big_left_a" href="dingdanxiangqing.html">我的订单</a><br/>
               <a id="big_left_a" href="">意外保</a><br/>
               <a id="big_left_a" href="">团购订单</a><br/>
               <a id="big_left_a" href="">评价晒单</a><br/>
               <p id="big_left_a" >个人中心</p>
               <a style="font-size:18px">我的个人中心</a><br/>
               <a id="big_left_a" href="">消息通知</a><br/>
               <a id="big_left_a" href="">优惠券</a><br/>
               <a id="big_left_a" href="">收货地址</a><br/>
        </div>
     <div id="big_right" style="height: 500px;overflow: scroll;">
     
         <div style="margin:0 20px;">
	         <h3>个人中心</h3>
	         <hr>
	         <table class="table table-striped table-hover table-bordered">
				<tr>
					<td>
						姓名:
					</td>
					<td>
					${user.username }
					</td>
				</tr>
				
				<tr>
					<td>
						邮箱:
					</td>
					<td>
					${user.email }
					</td>
				</tr>
				<tr>
					<td>
						性别:
					</td>
					<td>
					${user.gender }
					</td>
				</tr>
				<tr>
					<td>
						状态:
					</td>
					<td>
					普通用户
					</td>
				</tr>
				
					<tr>
						
						<td colspan="2">
							
							<button class="btn btn-default btn-sm" data-toggle="modal" data-target="#myModal${user.id}">修改密码</button>&nbsp;&nbsp;
							<span style="color:red">${msg}</span>
							<!-- 弹出模态框 -->
							
							<div class="modal fade" tabindex="-1" role="dialog" id="myModal${user.id}">
							  <div class="modal-dialog" role="document">
							    <div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">
											<span>&times;</span>
										</button>
										<h4 class="modal-title">修改密码 </h4>
									</div>
									<form action="${pageContext.request.contextPath}/user/updatepassword.action" method="post" class="form-horizontal">
										<div class="motal-body">
											
											<div class="form-group">
												<label class="col-sm-2 control-label">原始密码</label>
												<div class="col-sm-10">
													<input type="password" name="password" class="form-control" value="">
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-2 control-label">新密码</label>
												<div class="col-sm-10">
													<input type="password" name="newpassword" class="form-control" value="">
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-2 control-label">确认密码</label>
												<div class="col-sm-10">
													<input type="password" name="repassword" class="form-control" value="">
												</div>
											</div>
											
										</div>
										<div class="motal-footer">
											<button type="submit" class="btn btn-primary">修改</button>
											
										</div>
									</form>
								</div>
							</div>
							</div>
							
							
							
							
						</td>
					</tr>
			
			</table>
		</div>
		<br>
		
       </div>
    </div>
</div>
	
<!-- 底部 -->
<%@ include file="footer.jsp"%>

</body>
</html>