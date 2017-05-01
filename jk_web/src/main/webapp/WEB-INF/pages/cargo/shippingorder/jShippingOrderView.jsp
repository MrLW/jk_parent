<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
</head>

<body>
<form name="icform" method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png" /> 新增部门

   浏览委托
  </div>
  

 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">海运/空运：</td>
	            <td class="tableContent">
					<c:if test="${o.orderType == 1}">海运</c:if>
					<c:if test="${o.orderType == 0}">空运</c:if>
				</td>
	            <td class="columnTitle">货主：</td>
	            <td class="tableContent">${shipper}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">提单抬头：</td>
	            <td class="tableContent">${consignee}</td>
	            <td class="columnTitle">正本通知人：</td>
	            <td class="tableContent">${notifyParty}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">信用证：</td>
	            <td class="tableContent">${lcNo}</td>
	            <td class="columnTitle">装运港：</td>
	            <td class="tableContent">${portOfLoading}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">转船港：</td>
	            <td class="tableContent">${portOfTrans}</td>
	            <td class="columnTitle">卸货港：</td>
	            <td class="tableContent">${portOfDischarge}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">装期：</td>
	            <td class="tableContent">${loadingDate}</td>
	            <td class="columnTitle">效期：</td>
	            <td class="tableContent">${limitDate}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">是否分批：</td>
	            <td class="tableContent">
	            	<c:if test="${isBatch == 1}">分批</c:if>
					<c:if test="${isBatch == 0}">不分批</c:if>
	            </td>
	            <td class="columnTitle">是否转船：</td>
	            <td class="tableContent">
	            	<c:if test="${isTrans == 1}">装船</c:if>
					<c:if test="${isTrans == 0}">不装船</c:if>
	            </td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">份数：</td>
	            <td class="tableContent">${copyNum}</td>
	            <td class="columnTitle">扼要说明：</td>
	            <td class="tableContent">${remark}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">运输要求：</td>
	            <td class="tableContent">${specialCondition}</td>
	            <td class="columnTitle">运费说明：</td>
	            <td class="tableContent">${freight}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">复核人：</td>
	            <td class="tableContent">${checkBy}</td>
	            <td class="columnTitle">状态：</td>
	            <td class="tableContent">
	            	<c:if test="${state == 0 }">草稿</c:if>
					<c:if test="${state == 1 }"><b><font color="green">已委托</font></b></c:if>
	            </td>
	        </tr>	
		</table>
	</div>
 
 
</form>
</body>
</html>

