<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
<li id="view"><a href="#" onclick="formSubmit('productAction_toview','_self');this.blur();">查看</a></li>
<li id="new"><a href="#" onclick="formSubmit('productAction_tocreate','_self');this.blur();">新增</a></li>
<li id="update"><a href="#" onclick="formSubmit('productAction_toupdate','_self');this.blur();">修改</a></li>
<li id="delete"><a href="#" onclick="formSubmit('productAction_delete','_self');this.blur();">删除</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png" />
    产品列表
  </div> 
  
<div>


<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
		<td class="tableHeader">序号</td>
		<td class="tableHeader">照片</td>
		<td class="tableHeader">描述</td>
		<td class="tableHeader">工厂</td>
		<td class="tableHeader">工厂简介</td>
		<td class="tableHeader">市场价</td>
		<td class="tableHeader">尺寸长</td>
		<td class="tableHeader">尺寸宽</td>
		<td class="tableHeader">尺寸高</td>
		<td class="tableHeader">颜色</td>
		<td class="tableHeader">包装</td>
		<td class="tableHeader">包装单位</td>
		<td class="tableHeader">集装箱20</td>
		<td class="tableHeader">集装箱40</td>
		<td class="tableHeader">集装箱40HC</td>
		<td class="tableHeader">数量</td>
		<td class="tableHeader">体积</td>
		<td class="tableHeader">大箱尺寸长</td>
		<td class="tableHeader">大箱尺寸宽</td>
		<td class="tableHeader">大箱尺寸高</td>
		<td class="tableHeader">备注</td>
	</tr>
	</thead>
	<tbody class="tableBody" >
${links}
	
	<c:forEach items="${results}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
		<td><input type="checkbox" name="id" value="${o.id}"/></td>
		<td>${status.index+1}</td>
		<td>${o.productImage}</td>
		<td>${o.description}</td>
		<td>${o.factoryId}</td>
		<td>${o.factoryName}</td>
		<td>${o.price}</td>
		<td>${o.sizeLenght}</td>
		<td>${o.sizeWidth}</td>
		<td>${o.sizeHeight}</td>
		<td>${o.color}</td>
		<td>${o.packing}</td>
		<td>${o.packingUnit}</td>
		<td>${o.type20}</td>
		<td>${o.type40}</td>
		<td>${o.type40hc}</td>
		<td>${o.qty}</td>
		<td>${o.cbm}</td>
		<td>${o.mpsizeLenght}</td>
		<td>${o.mpsizeWidth}</td>
		<td>${o.mpsizeHeight}</td>
		<td>${o.remark}</td>
	</tr>
	</c:forEach>
	
	</tbody>
</table>
</div>
 
</div>
 
 
</form>
</body>
</html>

