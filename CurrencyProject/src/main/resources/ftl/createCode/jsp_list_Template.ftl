<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
<!-- 页面顶部¨ -->
<%@ include file="../../top.jsp"%>
<!-- Sweet Alert -->
<link href="static/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
<style>
html, body {
	margin: 0px;
	padding: 0px;
}

.wrapper-content {
	padding: 20px 25px 40px;
}

.page-heading {
	padding: 0 25px 20px;
}
</style>
</head>
<body class="gray-bg">
	<!-- <div class="row wrapper border-bottom white-bg page-heading ">
		<div class="col-lg-10">
			<h2>用户管理</h2>
			<ol class="breadcrumb">
				<li><a href="index.html">首页</a></li>
				<li class="active"><strong>系统用户</strong></li>
			</ol>
		</div>
		<div class="col-lg-2"></div>
	</div> -->
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-lg-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>列表</h5>
						<div class="ibox-tools">
							<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content">
						<c:if test="${r"${QX.add == 1 }"}">
						<div class="">
			            	<button type="button" class="btn btn-primary" onclick="add();">新增</button>
			            </div>
			            </c:if>
						<div class="table-responsive">
							<table id="editable" class="table table-striped table-bordered table-hover dataTables-example">
								<thead>
									<tr>
										<th>序号</th>
										<#list commentList as var>
										<th>
										<if test=" ${var[0]}!=null and ${var[0]}!='' " >
											${var[0]}
										</if>
										</th>
										</#list>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
								
								<c:choose>
								<c:when test="${r"${not empty varList}"}">
									<c:if test="${r"${QX.cha == 1 }"}">
									<c:forEach items="${r"${varList}"}" var="var" varStatus="vs">
									
									<tr class="gradeX">
										<td>${r"${vs.index+1}"}</td>
										<#list fieldList as var>
										<td class='center'>${r"${var."}${var[0]}${r"}"}</td>
										</#list>
										<td class="center">
											<c:if test="${r"${QX.edit == 1 }"}">
											<a class="btn btn-xs btn-outline btn-primary" title="编辑" onclick="edit('${r"${var."}${priStr}${r"}"}');">
												<i class="fa fa-edit" title="编辑"></i>
											</a>
											</c:if>
											<c:if test="${r"${QX.del == 1 }"}">
											<a class="btn btn-xs btn-outline btn-danger" title="删除" onclick="del('${r"${var."}${priStr}${r"}"}');">
												<i class="fa fa-trash" title="删除"></i>
											</a>
											</c:if>
										</td>
									</tr>
									
									</c:forEach>
									</c:if>
									<c:if test="${r"${QX.cha == 0 }"}">
										<tr>
											<td>您无权查看</td>
										</tr>
									</c:if>
								</c:when>
								<c:otherwise>
									<tr>
										<td colspan="10">没有相关数据</td>
									</tr>
								</c:otherwise>
								</c:choose>
								
								</tbody>
								<tfoot>
				                    <tr>
				                        <th>序号</th>
										<#list commentList as var>
										<th>${var[0]}</th>
										</#list>
										<th>操作</th>
				                    </tr>
			                    </tfoot>
							</table>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Mainly scripts -->
	<script src="static/js/jquery-2.1.1.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/plugins/metisMenu/jquery.metisMenu.js"></script>
	<script src="static/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

	<!-- <script src="static/js/plugins/dataTables/datatables.min.js"></script>  -->
    <script src="static/js/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="static/js/plugins/dataTables/dataTables.bootstrap.js"></script>

	<!-- Custom and plugin javascript -->
	<script src="static/js/inspinia.js"></script>
	<!-- Sweet alert -->
    <script src="static/js/plugins/sweetalert/sweetalert.min.js"></script>
</body>

<!-- Page-Level Scripts -->
<script>
	$(document).ready(function() {
		$('#editable').DataTable();
	});
	
	//新增
	function add(){
		window.location.href='<%=basePath%>${objectNameLower}/goAdd.do';
	}
	//修改
	function edit(Id){
		window.location.href='<%=basePath%>${objectNameLower}/goEdit.do?${priStr}='+Id;
	}
	//删除
	function del(Id){
            swal({
                        title: "确定要删除吗?",
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "确定删除",
                        cancelButtonText: "取消",
                        closeOnConfirm: false},
                    function (isConfirm) {
                        if (isConfirm) {
                        	var url = "<%=basePath%>${objectNameLower}/delete.do?${priStr}="+Id+"&tm="+new Date().getTime();
            				$.get(url,function(data){
	                            swal("删除成功", "", "success");
	                            window.location.href='<%=basePath%>${objectNameLower}/list.do';
            				});
                        }
                    });
	}
</script>
</html>