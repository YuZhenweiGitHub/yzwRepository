<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<base href="<%=basePath%>">
	
	<!-- 页面顶部¨ -->
	<%@ include file="../../top.jsp"%>
	<link href="<%=basePath %>static/bootstrap.css"  rel="stylesheet" />
	
	<style>
        body{font-size: 10px;}
        fieldset{padding:.35em .625em .75em;margin:0 2px;border:1px solid silver;border-radius:4px;}
        legend{padding:.5em;border:0;width:auto;font-size: 10px;font-weight: bold;color: gray;}
        .info_01{text-align: right;font-size:1.2em;padding-top: 7px;}
        .info_02{padding:0;margin: 0;}
        .form_d_2,.form_d_3,.form_d_4{margin-top: 30px}
    </style>
	
</head>

<body>
 	<div class="ibox-title">
		<label style="font-size: 18px;">编辑</label>
	</div>
	<div class="ibox-content">	
	<div class="wrapper wrapper-content animated fadeInRight">
    <div class="col-md-12">
						<form action="${objectNameLower}/${r"${msg }"}.do" name="Form" id="Form" method="post">
							<input type="hidden" name="${priStr}" id="${priStr}" value="${r"${pd."}${priStr}${r"}"}"/>
							  <fieldset>
							<#list fieldAndNameList as var>

      <#if var_index%2==0>
<div class="row form_d_2">	
	<div class="col-md-6">
		<div class="col-md-3 info_01">
			<label>${var[1] }</label>
		</div>
		<div class="col-md-8 info_02">
			<input type="text" name="${var[0] }" id="${var[0] }"
				value="${r"${pd."}${var[0] }${r"}"}" placeholder="这里输入${var[1] }"
				itle="${var[1] }" class="form-control"  required />
		</div>
		<div class="col-md-1 info_02">
            <span style="color:red;line-height: 34px;text-align: center;font-weight: bold;">&nbsp;&nbsp;*</span>			
        </div>
     </div>
        <#if !var_has_next>
 </div>
	 </#if>
      </#if> 	
         <#if var_index%2==1>
         <div class="col-md-6">
		<div class="col-md-3 info_01">
			<label>${var[1] }</label>
		</div>
		<div class="col-md-8 info_02">
			<input type="text" name="${var[0] }" id="${var[0] }"
				value="${r"${pd."}${var[0] }${r"}"}" placeholder="这里输入${var[1] }"
				itle="${var[1] }" class="form-control"  required />
		</div>
		<div class="col-md-1 info_02">
            <span style="color:red;line-height: 34px;text-align: center;font-weight: bold;">&nbsp;&nbsp;*</span>			
        </div>
     </div>
          </div>
         </#if> 
		</#list>

        </fieldset>
        <div class="row">
            <div class="col-md-12" style="text-align: center;margin-top: 20px">
                <button type="submit" class="btn btn-sm btn-primary">&nbsp;保&nbsp;&nbsp;存&nbsp;</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <button type="reset" class="btn btn-sm btn-primary" onclick="goBack()">&nbsp;返&nbsp;&nbsp;回&nbsp;</button>
            </div>
        </div>
    </form>
    </div>
</div>
</div>
	<!-- Mainly scripts -->
    <script src="static/js/jquery-2.1.1.js"></script>
    <script src="static/js/bootstrap.min.js"></script>
    
    <!-- fileinput -->
    <link href="<%=basePath %>plugins/fileinput/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
    <script src="<%=basePath %>plugins/fileinput/js/fileinput.min.js"></script>
    <script src="<%=basePath %>plugins/fileinput/js/fileinput_locale_zh.js"></script>

    <!-- Custom and plugin javascript -->
    <script src="static/js/inspinia.js"></script>
    <script src="static/js/plugins/pace/pace.min.js"></script>
    <script src="static/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

    <!-- Chosen -->
    <script src="static/js/plugins/chosen/chosen.jquery.js"></script>

   <!-- JSKnob -->
   <script src="static/js/plugins/jsKnob/jquery.knob.js"></script>

   <!-- Input Mask-->
   <!-- <script src="static/js/plugins/jasny/jasny-bootstrap.min.js"></script> -->

   <!-- Data picker -->
   <script src="static/js/plugins/datapicker/bootstrap-datepicker.js"></script>

   <!-- NouSlider -->
   <script src="static/js/plugins/nouslider/jquery.nouislider.min.js"></script>

   <!-- Switchery -->
   <script src="static/js/plugins/switchery/switchery.js"></script>

    <!-- IonRangeSlider -->
    <script src="static/js/plugins/ionRangeSlider/ion.rangeSlider.min.js"></script>

    <!-- iCheck -->
    <script src="js/plugins/iCheck/icheck.min.js"></script>

    <!-- MENU -->
    <script src="static/js/plugins/metisMenu/jquery.metisMenu.js"></script>

    <!-- Color picker -->
    <script src="static/js/plugins/colorpicker/bootstrap-colorpicker.min.js"></script>

    <!-- Clock picker -->
    <script src="static/js/plugins/clockpicker/clockpicker.js"></script>

    <!-- Image cropper -->
    <script src="static/js/plugins/cropper/cropper.min.js"></script>

    <!-- Date range use moment.js same as full calendar plugin -->
    <script src="static/js/plugins/fullcalendar/moment.min.js"></script>

    <!-- Date range picker -->
    <script src="static/js/plugins/daterangepicker/daterangepicker.js"></script>

    <!-- Select2 -->
    <script src="static/js/plugins/select2/select2.full.min.js"></script>

    <!-- TouchSpin -->
    <script src="static/js/plugins/touchspin/jquery.bootstrap-touchspin.min.js"></script>

    <!-- Tags Input -->
    <script src="static/js/plugins/bootstrap-tagsinput/bootstrap-tagsinput.js"></script>

    <!-- Dual Listbox -->
    <script src="static/js/plugins/dualListbox/jquery.bootstrap-duallistbox.js"></script>
</body>

<!-- Page-Level Scripts -->
<script>
var basePath = '<%=basePath%>';
	//返回
	function goBack(){
		window.location.href='<%=basePath%>costchange/list.do';
	}
	$(document).ready(function(){
	    $('#auditTime').datepicker({
	    	format: 'yyyy-mm-dd',
	        todayBtn: "linked",
	        keyboardNavigation: false,
	        forceParse: false,
	        calendarWeeks: true,
	        autoclose: true
	    }); 
	    
	    $('#applicationTime').datepicker({
	    	format: 'yyyy-mm-dd',
	        todayBtn: "linked",
	        keyboardNavigation: false,
	        forceParse: false,
	        calendarWeeks: true,
	        autoclose: true
	    }); 
	    
	    $('.chosen-select').chosen({no_results_text : "未找到此选项:"});
});	
</script>
</html>