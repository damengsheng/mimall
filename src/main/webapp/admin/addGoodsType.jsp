<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="../css/bootstrap.min.css" />
	<script src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
	<title>添加商品种类</title>
	<script type="text/javascript">
        $(function(){
            var datalist;
            $.ajax({
                url:"${pageContext.request.contextPath}/admin/getGoodsTypeJson.action",
                type:"get",
                dataType:"json",
                success:function(data){
                    if(data.length>0){
                        datalist=data;
                        for(var i in data){
                            if(data[i].level==1){
                                $("#goodsTypeLevel1").append("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
                            }
                        }

                    }


                },
                error:function(xhr,statustext,err){
                    alert("失败"+xhr.status+"..."+statustext+"..."+err);
                }
            });

            $("#goodsTypeLevel1").change(function(){
                $("#goodsTypeLevel2").html("<option value='-1'>--请选择--</option>");
                if(datalist.length>0){
                    for(var i in datalist){
                        //alert(datalist[i].parent+"....."+$(this).val());
                        if(datalist[i].parent==$(this).val()){
                            $("#goodsTypeLevel2").append("<option value='"+datalist[i].id+"'>"+datalist[i].name+"</option>");
                        }
                    }
                }
            });

        })

	</script>
</head>
<body>
<div style="width:98%;margin-left: 1%;">
	<div class="panel panel-default">
		<div class="panel-heading">
			添加商品种类
		</div>
		<div class="panel-body">
			<form action="${pageContext.request.contextPath }/admin/addGoodsType.action" method="post">
				<div class="row">
					<div class="form-group col-xs-8">
						<b>所属类别</b><br/>
						&nbsp;&nbsp;一级类别:
						<select name="goodsTypeLevel1" id="goodsTypeLevel1" class="form-control">
							<option value="-1">--请选择--</option>

						</select><br/>
						&nbsp;&nbsp;二级类别:
						<select name="goodsTypeLevel2" id="goodsTypeLevel2" class="form-control">
							<option value="-1">--请选择--</option>

						</select>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-xs-8">
						<b>种类名称</b>
						<input type="text" name="typename" class="form-control">
					</div>
				</div>
				<div class="row">
					<div class="btn-group col-xs-8">
						<button type="reset" class="btn btn-default">清空</button>
						<button type="submit" class="btn btn-default">添加</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
</body>
</html>