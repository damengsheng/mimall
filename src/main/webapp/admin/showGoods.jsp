<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
	<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/DatePicker.js"></script>
	<title>商品列表</title>

	<script type="text/javascript">

        $(function(){
            //ajax请求商品类别
            $.get("${pageContext.request.contextPath}/admin/getGoodsTypeJson.action?level=1",function(data){


                for(var i in data){
                    if(${typeid}== data[i].id){
                        $("#goodstype").append("<option value='"+data[i].id+"' selected>"+data[i].name+"</option>");
                    }else{
                        $("#goodstype").append("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
                    }
                }

            },"json");


            /* $("#goodstype").change(function(){

                var typeid=$(this).val();
                window.location="${pageContext.request.contextPath}/admin/getGoodsList?typeid="+typeid;
		}); */


        });



	</script>

</head>
<body>
<div class="row" style="width:98%;margin-left: 1%;">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				商品列表
			</div>
			<div class="panel-body">
				<form action="${pageContext.request.contextPath}/admin/getGoodsList.action" method="get">
					<div class="row">

						<div class="col-xs-3">
							<div class="form-group">
								<label for="name">商品名称</label>
								<input type="text" name="name" id="name" class="form-control" value="${name }">
							</div>
						</div>
						<div class="col-xs-3">
							<div class="form-group">
								<label for="goodstype">商品类别</label>
								<select name="typeid" id="goodstype" class="form-control" >
								</select>
							</div>
						</div>
						<div class="col-xs-3">
							<div class="form-group">
								<label for="pubdate">上架时间</label>
								<input type="text" name="pubdate" id="pubdate" class="form-control" onclick="setday(this)" value="${pubdate }">
							</div>
						</div>
						<div class="col-xs-2" style="padding-top:28px">

							<button type="submit" class="btn btn-primary" id="search"><span class="glyphicon glyphicon-search"></span></button>
						</div>

					</div>
				</form>
				<div style="height: 400px;overflow: scroll;">
					<table id="tb_list" class="table table-striped table-hover table-bordered">
						<tr>
							<td>序号</td><td>商品名称</td><td>价格</td><td>上架时间</td><td>类型</td><td>操作</td>
						</tr>
						<c:forEach items="${pageBean.data}" var="goods" varStatus="i">
							<tr>
								<td>${i.count}</td>
								<td>${goods.name}</td>
								<td>${goods.price}</td>
								<td>${goods.pubdate}</td>
								<td>${goods.typeName}</td>
								<td>删除 &nbsp;修改 &nbsp;
									<a tabindex="0" id="example${goods.id}" class="btn btn-primary btn-xs"
									   role="button" data-toggle="popover"
									   data-trigger="focus"
									   data-placement="left"
									   data-content="${goods.intro}">描述</a>
									<script type="text/javascript">
                                        $(function(){
                                            $("#example${goods.id}").popover();
                                        })
									</script>
								</td>
							</tr>
						</c:forEach>
					</table>
					<nav aria-label="..." class="text-center">
						<ul class="pagination">

							<c:if test="${pageBean.pageNum<=1 }">
								<li class="disabled"><span aria-hidden="true">«</span></li>
							</c:if>
							<c:if test="${pageBean.pageNum>1 }">
								<li><a href="${pageContext.request.contextPath }/admin/getGoodsList.action?pageNum=${pageBean.pageNum-1}&pageSize=${pageBean.pageSize}&typeId=${typeId}rname=${name}&pubdate=${pubdate}" aria-label="Previous"><span aria-hidden="true">«</span></a></li>
							</c:if>

							<c:forEach var="pn" begin="${pageBean.startPage }" end="${pageBean.endPage }" step="1">
								<c:if test="${pn==pageBean.pageNum }">
									<li class="active"><a href="#">${pn }<span class="sr-only">(current)</span></a></li>
								</c:if>
								<c:if test="${pn!=pageBean.pageNum }">
									<li ><a href="${pageContext.request.contextPath }/admin/getGoodsList.action?pageNum=${pn }&pageSize=${pageBean.pageSize}&typeId=${typeId}rname=${name}&pubdate=${pubdate}">${pn }</a></li>
								</c:if>
							</c:forEach>

							<c:if test="${pageBean.pageNum==pageBean.totalPage }">
								<li class="disabled"><span aria-hidden="true">»</span></li>
							</c:if>
							<c:if test="${pageBean.pageNum<pageBean.totalPage }">
								<li><a href="${pageContext.request.contextPath }/getGoodsList?pageNum=${pageBean.pageNum+1}&pageSize=${pageBean.pageSize}&typeId=${typeId}&name=${name}&pubdate=${pubdate}" aria-label="Next"><span aria-hidden="true">»</span></a></li>
							</c:if>

						</ul>
					</nav>
				</div>

			</div>
		</div>

	</div>
</div>
</body>
</html>