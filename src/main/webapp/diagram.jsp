<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
	<head>
		<title>流程状态</title>
		<%@ include file="/common/meta.jsp"%>
		<link rel="stylesheet" href="https://static.qiejf.com/better/l/svgDrawer/css/style.css" type="text/css" media="all" />
		<link rel="stylesheet" href="https://static.qiejf.com/better/l/svgDrawer/css/snaker.css" type="text/css" media="all" />
		<script src="https://static.qiejf.com/better/l/svgDrawer/js/raphael-min.js" type="text/javascript"></script>
		<script src="https://static.qiejf.com/better/l/svgDrawer/js/jquery-ui-1.8.4.custom/js/jquery.min.js" type="text/javascript"></script>
		<script src="https://static.qiejf.com/better/l/svgDrawer/js/jquery-ui-1.8.4.custom/js/jquery-ui.min.js" type="text/javascript"></script>
		<script src="https://static.qiejf.com/better/l/svgDrawer/js/snaker/snaker.designer.js" type="text/javascript"></script>
		<script src="https://static.qiejf.com/better/l/svgDrawer/js/snaker/snaker.model.js" type="text/javascript"></script>
		<script src="https://static.qiejf.com/better/l/svgDrawer/js/snaker/snaker.editors.js" type="text/javascript"></script>

<script type="text/javascript">
	function display(process, state) {
		/** view*/
		/* console.log(eval("(" + process + ")"));
		console.log(eval("(" + state + ")")); */
		
		$('#snakerflow').snakerflow($.extend(true,{
			basePath : "https://static.qiejf.com/better/l/svgDrawer/js/snaker/",
            ctxPath : "${ctx}",
            businessId : "${businessId}",
			restore : eval("(" + process + ")"),
			editable : false
			},eval("(" + state + ")")
		));
	}
</script>
</head>
	<body>
		<table class="properties_all" align="center" border="0" cellpadding="0" cellspacing="0" style="margin-top: 0px">
			<div id="snakerflow" style="border: 0px solid #d2dde2; margin-top:10px; margin-left:10px; margin-bottom:10px; width:98%;">
			</div>
		</table>
		<script type="text/javascript">
		$.ajax({
				type:'GET',
				url:"${ctx}/WorkFlow/webFindFlowJson",
				data:"processId=${processId}&businessId=${businessId}&orderId=${orderId}",
				dataType:'json',
				async: false,
				globle:false,
				error: function(){
					alert('数据处理错误！');
					return false;
				},
				success: function(result){
					display(result.data.process, result.data.state);
				}
			});
		</script>
	</body>
</html>
