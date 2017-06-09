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
	<img src="${ctx }/skin/default/images/icon/currency_yen.png" /> 修改产品
   浏览产品
  </div>
  

 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">编号：</td>
	            <td class="tableContent">${productNo}</td>
	            <td class="columnTitle">照片：</td>
	            <td class="tableContent">${productImage}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">描述：</td>
	            <td class="tableContent">${description}</td>
	            <td class="columnTitle">厂家：</td>
	            <td class="tableContent">
	            	<c:forEach items="${factoryList }" var="factory">
	            		<c:if test="${factory.id == factoryId}">
	            			${factory.fullName }
	            		</c:if>
	            	</c:forEach>
	            </td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">厂家简称：</td>
	            <td class="tableContent">
	            	<c:forEach items="${factoryList }" var="factory">
	            		<c:if test="${factoryId == factory.id }">
	            			${factory.factoryName }
	            		</c:if>
	            	</c:forEach>
	            </td>
	            <td class="columnTitle">市场价：</td>
	            <td class="tableContent">${price}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">尺寸长：</td>
	            <td class="tableContent">${sizeLenght}</td>
	            <td class="columnTitle">尺寸宽：</td>
	            <td class="tableContent">${sizeWidth}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">尺寸高：</td>
	            <td class="tableContent">${sizeHeight}</td>
	            <td class="columnTitle">颜色：</td>
	            <td class="tableContent">${color}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">包装：</td>
	            <td class="tableContent">${packing}</td>
	            <td class="columnTitle">包装单位：</td>
	            <td class="tableContent">${packingUnit}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">集装箱类别20：</td>
	            <td class="tableContent">${type20}</td>
	            <td class="columnTitle">集装箱类别40：</td>
	            <td class="tableContent">${type40}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">集装箱类别40HC：</td>
	            <td class="tableContent">${type40hc}</td>
	            <td class="columnTitle">数量：</td>
	            <td class="tableContent">${qty}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">体积：</td>
	            <td class="tableContent">${cbm}</td>
	            <td class="columnTitle">大箱尺寸长：</td>
	            <td class="tableContent">${mpsizeLenght}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">大箱尺寸宽：</td>
	            <td class="tableContent">${mpsizeWidth}</td>
	            <td class="columnTitle">大箱尺寸高：</td>
	            <td class="tableContent">${mpsizeHeight}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">备注：</td>
	            <td class="tableContent">${remark}</td>
	        </tr>	
		</table>
	</div>
 
 
</form>
</body>
</html>

