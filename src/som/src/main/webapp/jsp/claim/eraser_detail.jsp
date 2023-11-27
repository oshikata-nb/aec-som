<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taginclude.jsf"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http//www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<%@ include file="/jsp/common/head.jsf"%>
<%@ include file="/jsp/common/head_main.jsf"%>
<%@ include file="/jsp/common/clearText.jsf" %>
<%@ include file="/jsp/common/searchNameForMaster.jsf" %>

<!-- Style Sheet -->
<link href='<%= request.getContextPath() + "/stylesheets/tab.css"%>'
	media="screen" rel="Stylesheet" type="text/css" />
<!-- Style Sheet(zippy用) -->
<link href='<%= request.getContextPath() + "/stylesheets/mypage.css"%>'
	media="screen" rel="Stylesheet" type="text/css" />
<!-- Javascript(minmax用) -->
<script
	src='<%= request.getContextPath() + "/javascripts/minmax.js"%>'
	type="text/javascript"></script>


<%-- ▼業務固有css ここから --%>
<style type="text/css">
}

</style>
<%-- ▲業務固有css ここまで --%>

<%-- ▼業務固有javaScript ここから --%>
<script language="JavaScript" type="text/javascript">

	<%--onload時の処理--%>
	Event.observe(window, 'load', function() {

		<%-- フォーム設定の初期化 --%>
		initializeFormState();

		storeInitValues(/^srh.*/, 'dirtyFlg');
		refreshLabel();

	  new Ajax.Updater(
		"credit_data",
		{
		  method: 'post',
		  evalScripts: true
		});
	
		<%-- 最小化するガジェット --%>
		minimize("credit_data");
	}, false);

	<%-- 確認メッセージ --%>
	function putConfirm() {
		if (!noCheck()) {
		  return false;
		}

		alertMsg = new Array();
		alertMsg[0] = "登録してよろしいですか？";
	
		return confirm(alertMsg[0]);
	}

	<%-- 変更フラグセット --%>
	function setDirtyFlg() {
		$("dirtyFlg").value = "true";
	}
	
	<%-- 変更確認メッセージ --%>
	function putDirtyConfirm() {
		var flg = $("dirtyFlg").value;
		if (flg == "true") {
			// 何か値が変更されている場合
			return continueConfirm();
		}else{
			return true;
		}
	}

	<%-- 確認メッセージ --%>
	function deleteAlert() {
		alertMsg = new Array();
		alertMsg[0] = "削除してよろしいですか？";

		return confirm(alertMsg[0]);
	}

	<%-- チェックボックス選択無しチェック --%>
	function noCheck(){
	  var count = 0;
	  var len = document.eraserDetailForm.checkFlg.length;

	  // 明細１件のみの場合
	  if (len == undefined) {
　      if(document.eraserDetailForm.checkFlg.disabled == false){
	　    if(document.eraserDetailForm.checkFlg.checked){
		    count = count + 1;
          }
		}
	  } else {
	    for (var i = 0; i < len; i++) {
	　    if(document.eraserDetailForm.checkFlg[i].disabled == false){
	　      if(document.eraserDetailForm.checkFlg[i].checked){
		      count = count + 1;
		      break;
		    }
		  }
	    }
	  }

	  if (count == 0) {
	    alert("消込データが選択されていません。");
	    return false;
	  }
	  return true;
	}

	<%-- 最小化 --%>
	function minimize(msgId) {
	  var body = $(msgId);
	  new Effect.SlideUp(body, { 'duration': 0.3});
	  var zippy = $(msgId + "_zippy");
	
	  zippy.style.backgroundImage = "url('images/plus-color.png')";
	  zippy.onclick = function() {maximize(msgId);return false;};
	
	  return false;
	}
	<%-- 最大化 --%>
	function maximize(msgId) {
	  var body = $(msgId);
	  new Effect.SlideDown(body, { 'duration': 0.3});
	
	  var zippy = $(msgId + "_zippy");
	  zippy.style.backgroundImage = "url('images/minus-color.png')";
	  zippy.onclick = function() {minimize(msgId); return false;};
	
	  return false;
	}

	<%-- 合計値の計算 --%>
	function SumAll(){
	  var seikyugokei = eval(removeComma($("seikyugokei").innerText));		// 請求合計	  var nyukingokei = eval(removeComma($("nyukingokei").innerText));		// 入金合計	  var sumgokei1 = 0;													// 消込合計(請求)
	  var sumgokei2 = 0;													// 消込残合計	  var sumgokei3 = eval(removeComma($("baseSumEraserAmount").value));	// 消込合計(入金)
	  var sumgokei4 = 0;													// 入金消込残合計
	  var kingaku = 0;														// 請求金額(明細)
	  var len = document.eraserDetailForm.checkFlg.length;

	  // 明細１件のみの場合
	  if (len == undefined) {
	　  if(document.eraserDetailForm.checkFlg.checked){
		  kingaku = eval(removeComma($(strTotalSalesAmount).innerText));
		  sumgokei1 = sumgokei1 + kingaku;
	　  }
	  } else {
	    for (var i = 0; i < len; i++) {
	　    if(document.eraserDetailForm.checkFlg[i].checked){
		    kingaku = eval(removeComma($(strTotalSalesAmount)[i].innerText));
		    sumgokei1 = sumgokei1 + kingaku;
	　    }
	    }
	  }

	  sumgokei2 = seikyugokei - sumgokei1;
	  sumgokei3 = sumgokei3 + sumgokei1;
	  sumgokei4 = nyukingokei - sumgokei3;

	  // 画面表示設定	  $("goukei1").innerText = addComma(String(sumgokei1));
	  $("goukei2").innerText = addComma(String(sumgokei2));
	  $("goukei3").innerText = addComma(String(sumgokei3));
	  $("goukei4").innerText = addComma(String(sumgokei4));

	  // hidden設定	  $("newEraserAmount").value = addComma(String(sumgokei1));
	  $("newEraserBalanceAmount").value = addComma(String(sumgokei2));
	  $("newSumEraserAmount").value = addComma(String(sumgokei3));
	  $("newSumCreditEraserAmount").value = addComma(String(sumgokei4));
	}
	<%-- カンマ編集 --%>
	function addComma(value){
	    var i;
	    for(i = 0; i < value.length/3; i++){
	        value = value.replace(/^([+-]?\d+)(\d\d\d)/,"$1,$2");
	    }
	    return value;
	}
	<%-- カンマ編集解除 --%>
	function removeComma(value) {
	    return value.split(",").join("")
	}

	<%-- 全選択 --%>
	function CheckAll(){
	  var sumgokei = eval(removeComma($("goukei4").innerText));		// 入金消込残
	  var kingaku;			// 請求金額(明細)
	  var len = document.eraserDetailForm.checkFlg.length;

	  ClearAll("checkFlg");

	  // 明細１件のみの場合
	  if (len == undefined) {
		  kingaku = eval(removeComma($(strTotalSalesAmount).innerText));
		  // 入金消込残>=消込む請求金額の場合
	  	  if (sumgokei >= kingaku){
	　      if(document.eraserDetailForm.checkFlg.disabled == false){
 		      sumgokei = sumgokei - kingaku;
	  		  Check(document.eraserDetailForm.checkFlg);
		    }
	  	  }else{
	  		  Clear(document.eraserDetailForm.checkFlg);
	  	  }
	  	
	  	  SumAll(); // 合計値の計算
	  } else {
	    for (var i = 0; i < len; i++) {
		  kingaku = eval(removeComma($(strTotalSalesAmount)[i].innerText));
	  	  if (sumgokei >= kingaku){
	　      if(document.eraserDetailForm.checkFlg[i].disabled == false){
	  		  sumgokei = sumgokei - kingaku;
	  		  Check(document.eraserDetailForm.checkFlg[i]);
		    }
	  	  }else{
	  		  Clear(document.eraserDetailForm.checkFlg[i]);
	  	  }
	  	
	  	  SumAll(); // 合計値の計算

		  if (sumgokei == 0) {
		    break;
		  }
	    }
	  }

	}
	<%-- 全解除 --%>
	function ClearAll(id){
	  var len = document.eraserDetailForm.checkFlg.length;

	  // 明細１件のみの場合
	  if (len == undefined) {
	    var checkbox = document.eraserDetailForm.checkFlg;
	    if (checkbox.id == id) {
	      Clear(checkbox);
	    }
	    
	  	SumAll(); // 合計値の計算
	  } else {
	    for (var i = 0; i < len; i++) {
	      var checkbox = document.eraserDetailForm.checkFlg[i];
	      if (checkbox.id == id) {
	        Clear(checkbox);
	      }
	    
	  	  SumAll(); // 合計値の計算
	    }
	  }

	}
	<%-- チェックボックス選択 --%>
	function Check(checkbox){
	  checkbox.checked = true;
	}
	<%-- チェックボックス選択解除 --%>
	function Clear(checkbox){
	  if(checkbox.disabled == false){
		  checkbox.checked = false;
	  }
	}

</script>

<%-- ▲業務固有javaScriptここまで --%>

</head>

<body>

<nested:form action="/EraserDetail" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="dirtyFlg" />
	<nested:hidden property="insertFlg" styleId="insertFlg" />
	<nested:hidden property="baseSumEraserAmount" styleId="baseSumEraserAmount" />
	<!-- 再計算値 -->
	<nested:hidden property="newEraserAmount" styleId="newEraserAmount" />
	<nested:hidden property="newEraserBalanceAmount" styleId="newEraserBalanceAmount" />
	<nested:hidden property="newSumEraserAmount" styleId="newSumEraserAmount" />
	<nested:hidden property="newSumCreditEraserAmount" styleId="newSumCreditEraserAmount" />

	<table cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td class="valignTop alignLeft">
			<table border="0" cellpadding="0" cellspacing="0" width="750">
				<tr>
					<td><!-- ヘッダー部 -->
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td height="10" colspan="2"></td>
						</tr>
						<tr>
							<td class="fcTitle fb">消込入力</td>
							<td>
							<table border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td><%-- メッセージ表示 --%> <%@ include
										file="/jsp/common/disp_info_message.jsf"%>
									<%-- メッセージ表示 ここまで --%></td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td class="bcTitleLine" colspan="2"></td>
						</tr>
						<tr>
							<td height="5" colspan="2"></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td><%-- メッセージ表示 --%> <%@ include
						file="/jsp/common/disp_error_message.jsf"%>
					<%-- メッセージ表示 ここまで --%></td>
				</tr>
				<tr>
					<td><!-- 明細部(上段) -->
					<table width="" cellspacing="2" cellpadding="2" border="0">
						<tr>
							<td width="100" class="fcTitleDetail fb bcTitleDetail">
								部門
							</td>
							<td width="420">
								<nested:write property="sectionCd" />:
								<nested:write property="sectionName" />
							</td>
							<td width="100"></td>
							<td class="fcTitleDetail fb bcTitleDetail" width="90">消込番号</td>
							<td width="90">
								<nested:write property="eraserNo" />
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td><!-- 明細部(上段) -->
					<table width="" cellspacing="2" cellpadding="2" border="0">
						<tr class="fcTitleDetail fb bcTitleDetail">
							<td width="270" colspan="3">請求先</td>
							<td align="right">消込可能額</td>
							<td align="right">消込合計</td>
							<td align="right">消込残合計</td>
						</tr>
						<tr>
							<td colspan="3">
								<nested:write property="venderCd" />:
								<nested:write property="venderName" />
							</td>
							<td align="right" id="seikyugokei">
								<nested:write property="eraserCapaAmount" />
							</td>
							<td align="right" id="goukei1">
								<nested:write property="eraserAmount" />
							</td>
							<td align="right" id="goukei2">
								<nested:write property="eraserBalanceAmount" />
							</td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td class="bcTitleList fb fcTitleList" width="90" align="right">入金合計</td>
							<td class="bcTitleList fb fcTitleList" width="90" align="right">消込合計</td>
							<td class="bcTitleList fb fcTitleList" align="right">入金消込残合計</td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td align="right" id="nyukingokei">
								<nested:write property="sumCreditAmount" />
							</td>
							<td align="right" id="goukei3">
								<nested:write property="sumEraserAmount" />
							</td>
							<td align="right" id="goukei4">
								<nested:write property="sumCreditEraserAmount" />
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td height="10"></td>
				</tr>
				<tr>
					<td><!-- 明細部(中段) -->
						<%@ include file="/jsp/claim/credit.jsf"%>
					</td>
				</tr>
				<tr>
					<td class="alignLeft">
					<table cellspacing="1" cellpadding="1" border="0">
						<tr>
							<td>
								<div id="cssButton">
								<a href="#" onclick="CheckAll(); return false;" class="cssButton">
									&nbsp;&nbsp;一括消込ON&nbsp;&nbsp;
								</a>
								</div>
							</td>
							<td>
							<td>
								<div id="cssButton">
								<a href="#" onclick="ClearAll('checkFlg'); return false;"	class="cssButton"> &nbsp;&nbsp;一括消込OFF&nbsp;&nbsp;
								</a>
								</div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td><!-- 明細部(下段) -->
					<nested:notEmpty property="salesList">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<tr>
								<td>
								<table width="" border="0">
									<tr>
										<td class="alignCenter">
										<table cellspacing="2" cellpadding="1" id="tblList">
											<tr>
												<td rowspan="2" class="bcTitleList fb fcTitleList">消込</td>
												<td width="230" class="bcTitleList fb fcTitleList">得意先</td>
												<td width="80" class="bcTitleList fb fcTitleList">日付</td>
												<td width="90" class="bcTitleList fb fcTitleList">伝票番号</td>
												<td align="right" width="30" class="bcTitleList fb fcTitleList">行</td>
												<td width="3"></td>
												<td width="90" class="bcTitleList fb fcTitleList">分類</td>
												<td align="right" width="90" class="bcTitleList fb fcTitleList">請求金額</td>
												<td width="90" class="bcTitleList fb fcTitleList">消込番号</td>
											</tr>
											<tr class="bcTitleList fb fcTitleList">
												<td colspan="2">品目／摘要</td>
												<td align="right" width="90">数量</td>
												<td width="33" colspan="2">単位</td>
												<td align="right" width="90">単価</td>
												<td align="right" width="90">金額</td>
												<td align="right" width="90">消費税</td>
											</tr>
											<nested:iterate id="salesList" property="salesList" indexId="index">
												<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
													<tr class="bcList1">
												</app:modulo>
					
												<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
													<tr class="bcList2">
												</app:modulo>

												<%-- 消込 --%>
												<td rowspan="2">
													<%-- 選択した消込データで消込済 --%>
													<nested:equal property="checkKbn" value="1">
														<nested:checkbox property="checkFlg" styleId="checkFlg" onclick="SumAll();"/>
													</nested:equal>
													<%-- 他の消込データで消込済 --%>
													<nested:equal property="checkKbn" value="2">
														<nested:checkbox disabled="true" property="checkFlg" styleId="checkFlg"/>
													</nested:equal>
													<%-- 未消込 --%>
													<nested:equal property="checkKbn" value="0">
														<nested:checkbox property="checkFlg" styleId="checkFlg" onclick="SumAll();"/>
													</nested:equal>
												</td>
												<%-- 得意先 --%>
												<td>
													<nested:write property="customerCd" />:
													<nested:write property="customerName" />
												</td>
												<%-- 売上日付 --%>
												<td>
													<nested:write property="strSalesDate" />
												</td>
												<%-- 伝票番号 --%>
												<td>
													<nested:write property="slipNo" />
												</td>
												<%-- 行 --%>
												<td align="right">
													<nested:write property="rowNo" />
												</td>
												<td></td>
												<%-- 分類 --%>
												<td>
													<nested:write property="categoryName" />
												</td>
												<%-- 請求金額 --%>
												<td align="right" id="strTotalSalesAmount">
													<nested:write property="strTotalSalesAmount" />
												</td>
												<%-- 消込番号 --%>
												<td>
													<nested:write property="eraserNo" />
												</td>
												</tr>

												<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
													<tr class="bcList1">
												</app:modulo>
					
												<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
													<tr class="bcList2">
												</app:modulo>

												<%-- 品目 --%>
												<td colspan="2">
													<nested:write property="itemCd" />:
													<nested:write property="itemName" />
												</td>
												<%-- 数量 --%>
												<td align="right">
													<nested:write property="strSalesQuantity" />
												</td>
												<%-- 単位 --%>
												<td colspan="2">
													<nested:write property="operateManagementUnit" />
												</td>
												<%-- 単価 --%>
												<td align="right">
													<nested:write property="strDefineUnitprice" />
												</td>
												<%-- 金額 --%>
												<td align="right">
													<nested:write property="strSalesAmount" />
												</td>
												<%-- 消費税 --%>
												<td align="right">
													<nested:write property="strTaxAmount" />
												</td>
											</tr>											
											</nested:iterate>
										</table>
										</td>
									</tr>
								</table>
								</td>
							</tr>
						</table>
					</nested:notEmpty>
					</td>
				</tr>
				<tr>
					<td>
					<table>
						<tr>
							<td class="alignLeft">
							<div id="cssButton">
								<nested:equal property="insertFlg" value="0">
									<a href="#" onclick="if (!(putConfirm())) {return false;}else{return form_submit('op', 'update'); return false;}" class="cssButton">
									&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
									</a>
								</nested:equal>
								<nested:equal property="insertFlg" value="1">
									<a href="#" onclick="if (!(putConfirm())) {return false;}else{return form_submit('op', 'insert'); return false;}" class="cssButton">
									&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
									</a>
								</nested:equal>
							</div>
							</td>
							<td class="alignCenter">
								<div id="cssButton">
									<nested:equal property="insertFlg" value="0">
											<a href="#" onclick="if (!(deleteAlert())) {return false;}else{return form_submit('op', 'delete'); return false;}" class="cssButton">
												&nbsp;&nbsp;削&nbsp;&nbsp;除&nbsp;&nbsp;
											</a>
									</nested:equal>
								</div>
							</td>
							<%--
							<td class="alignRight">
								<div id="cssButton">
									<a href="#" onclick="if (!(putDirtyConfirm())) {return false;}else{return form_submit('op', 'back'); return false;}" class="cssButton">
										&nbsp;&nbsp;承&nbsp;&nbsp;認&nbsp;&nbsp;
									</a>
								</div>
							</td>
							--%>
							<td class="alignRight">
								<div id="cssButton">
									<a href="#" onclick="if (!(putDirtyConfirm())) {return false;}else{return form_submit('op', 'back'); return false;}" class="cssButton">
										&nbsp;&nbsp;戻&nbsp;&nbsp;る&nbsp;&nbsp;
									</a>
								</div>
							</td>
						</tr>
					</table>
					</td>
				</tr>

			</table>
			</td>
		</tr>
	</table>

</nested:form>
</body>
</html:html>
