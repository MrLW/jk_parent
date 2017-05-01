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
<li id="save"><a href="#" onclick="formSubmit('invoiceAction_insert','_self');this.blur();">保存</a></li>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png" />
   新增发票
  </div> 
  

 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">发票号：</td>
	            <td class="tableContent"><input type="text" name="scNo" value=""/></td>
	            <td class="columnTitle">提单号：</td>
	            <td class="tableContent"><input type="text" name="blNo" value=""/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">贸易条款：</td>
	            <td class="tableContent"><input type="text" name="tradeTerms" value=""/></td>
	        </tr>	
	       
		</table>
	</div>
	<br/><br/><br/>
 <div class="eXtremeTable">
				<table id="ec_table" class="tableRegion" width="98%">
					<thead>
						<tr>
							<td class="tableHeader"><input type="checkbox" name="selid"
								onclick="checkAll('id',this)"></td>
							<td class="tableHeader">序号</td>
							<td class="tableHeader">海运/空运</td>
							<td class="tableHeader">货主</td>
							<td class="tableHeader">提单抬头</td>
							<td class="tableHeader">正本通知人</td>
							<td class="tableHeader">信用证</td>
							<td class="tableHeader">装运港</td>
							<td class="tableHeader">转船港</td>
							<td class="tableHeader">卸货港</td>
							<td class="tableHeader">装期</td>
							<td class="tableHeader">效期</td>
							<td class="tableHeader">是否分批</td>
							<td class="tableHeader">是否转船</td>
							<td class="tableHeader">份数</td>
							<td class="tableHeader">扼要说明</td>
							<td class="tableHeader">运输要求</td>
							<td class="tableHeader">运输说明</td>
							<td class="tableHeader">复核人</td>
							<td class="tableHeader">状态</td>

						</tr>
					</thead>
					<tbody class="tableBody">

						<c:forEach items="${shippingOrderList}" var="o" varStatus="status">
							<tr class="odd" onmouseover="this.className='highlight'"
								onmouseout="this.className='odd'">
								<td><input type="checkbox" name="shippingOrderListId" value="${o.id}" /></td>
								<td>${status.index+1}</td>
								<td>
									<!-- 细节：在大括号后面不可以有空格,否则不会输出 --> <c:if
										test="${o.orderType == 1}">海运</c:if> <c:if
										test="${o.orderType == 0}">空运</c:if>
								</td>
								<td>${o.shipper}</td>
								<td>${o.consignee}</td>
								<td>${o.notifyParty}</td>
								<td>${o.lcNo}</td>
								<td>${o.portOfLoading}</td>
								<td>${o.portOfTrans}</td>
								<td>${o.portOfDischarge}</td>
								<td>${o.loadingDate}</td>
								<td>${o.limitDate}</td>
								<td><c:if test="${o.isBatch == 1}">分批</c:if> <c:if
										test="${o.isBatch == 0}">不分批</c:if></td>
								<td><c:if test="${o.isTrans == 1}">装船</c:if> <c:if
										test="${o.isTrans == 0}">不装船</c:if></td>
								<td>${o.copyNum}</td>
								<td>${o.remark}</td>
								<td>${o.specialCondition}</td>
								<td>${o.freight}</td>
								<td>${o.checkBy}</td>
								<td><c:if test="${o.state == 0 }">草稿</c:if> <c:if
										test="${o.state == 1 }">
										<b><font color="green">已委托</font></b>
									</c:if></td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>

		</div>

 
 
</form>
</body>
</html>

