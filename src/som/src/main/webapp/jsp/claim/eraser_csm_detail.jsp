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

		<%-- 必須フィールド定義 --%>
		defineAsRequiredField("strEraserDate");

		<%-- 日付型フィールド定義 --%>
		defineAsDateField("strEraserDate");

		var tblList = $('tblList');
		if (tblList != null) {
		    var len = document.eraserCsmDetailForm.checkFlg.length;
			if (0 < tblList.rows.length) {
				<%-- 数値型フィールド定義 --%>
				for (i = 0; null != $("strEraserAmount" + i);　i++) {
					defineAsNumberField("strEraserAmount" + i);
				    // 明細１件のみの場合
				    if (len == undefined) {
				　    if(document.eraserCsmDetailForm.checkFlg.checked){
						  $("strEraserAmount" + i).readOnly = false;
					  }
				    } else {
				　    if(document.eraserCsmDetailForm.checkFlg[i].checked){
						  $("strEraserAmount" + i).readOnly = false;
					  }
				    }
				}
			}
		}

		storeInitValues(/^srh.*/, 'dirtyFlg');
		refreshLabel();
	
		<%-- 最小化するガジェット --%>
		minimize("credit_data");
	}, false);

	<%-- 確認メッセージ --%>
	function putConfirm() {
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

	<%-- 承認依頼メッセージ --%>
	function putApprovalRequestConfirm() {
		var flg = $("dirtyFlg").value;
		alertMsg = new Array();

		if (flg == "true") {
			/* 何か値が変更されている場合 */
			alertMsg[0] = "画面の編集内容は破棄して承認依頼されます。よろしいですか？";
		} else {
			alertMsg[0] = "承認依頼を行います。よろしいですか？";
		}

		return confirm(alertMsg[0]);
	}

	<%-- 承認メッセージ --%>
	function putApprovalConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "承認します。よろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- 否認メッセージ --%>
	function putNegationConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "否認します。よろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- 承認取消メッセージ --%>
	function putApprovalCancelConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "承認取消します。よろしいですか？";
		return confirm(alertMsg[0]);
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
	  var len = document.eraserCsmDetailForm.checkFlg.length;

	  // 明細１件のみの場合
	  if (len == undefined) {
	　  if(document.eraserCsmDetailForm.checkFlg.checked){
	      kingaku = parseFloat(removeComma($("strEraserAmount0").value));
		  if (!isNaN(kingaku)) {
		    sumgokei1 = sumgokei1 + eval(kingaku);
		  }
	　  }
	  } else {
	    for (var i = 0; i < len; i++) {
	　    if(document.eraserCsmDetailForm.checkFlg[i].checked){
		    kingaku = parseFloat(removeComma($("strEraserAmount" + i).value));
			if (!isNaN(kingaku)) {
		      sumgokei1 = sumgokei1 + eval(kingaku);
			}
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
		var len = document.eraserCsmDetailForm.checkFlg.length;

		ClearAll("checkFlg");

		// 明細１件のみの場合
		if (len == undefined) {
			kingaku = parseFloat(removeComma($(strTotalSalesAmount).innerText));

			if (!isNaN(kingaku)) {
				// 入金消込残>=消込む請求金額の場合
				if (sumgokei >= kingaku){
					if(document.eraserCsmDetailForm.checkFlg.disabled == false){
						sumgokei = sumgokei - kingaku;
						Check(document.eraserCsmDetailForm.checkFlg);
					}
				}else{
					// 入金消込残合計以上に消し込まないように調整
					if (sumgokei == 0) {
						Clear(document.eraserCsmDetailForm.checkFlg);
					} else {
						sumgokei = 0;
						Check(document.eraserCsmDetailForm.checkFlg);
					}
				}

				setKingaku(0);
				SumAll(); // 合計値の計算
			}
		} else {
			for (var i = 0; i < len; i++) {
				kingaku = parseFloat(removeComma($(strTotalSalesAmount)[i].innerText));

				if (!isNaN(kingaku)) {
					if (sumgokei >= kingaku){
						if(document.eraserCsmDetailForm.checkFlg[i].disabled == false){
							sumgokei = sumgokei - kingaku;
							Check(document.eraserCsmDetailForm.checkFlg[i]);
						}
					}else{
//						Clear(document.eraserCsmDetailForm.checkFlg[i]);
						if(document.eraserCsmDetailForm.checkFlg[i].disabled == false){
							// 入金消込残合計以上に消し込まないように調整
							sumgokei = 0;
							Check(document.eraserCsmDetailForm.checkFlg[i]);
						}
					}

					setKingaku(i);
					SumAll(); // 合計値の計算

					if (sumgokei == 0) {
						break;
					}
				}
			}

			for (var i = 0; i < len; i++) {
				kingaku = parseFloat(removeComma($(strTotalSalesAmount)[i].innerText));

				if (!isNaN(kingaku)) {
					if (document.eraserCsmDetailForm.checkFlg[i].disabled == false){
						if (document.eraserCsmDetailForm.checkFlg[i].checked == false) {
							if (sumgokei < kingaku){
								// 入金消込残合計以上に消し込まないように調整
								if (sumgokei == 0) {
									Clear(document.eraserCsmDetailForm.checkFlg[i]);
								} else {
									sumgokei = 0;
									Check(document.eraserCsmDetailForm.checkFlg[i]);
								}

								setKingaku(i);
								SumAll(); // 合計値の計算
			
								if (sumgokei == 0) {
									break;
								}
							}
						}
					}
				}
			}
		}
	}

	<%-- 全解除 --%>
	function ClearAll(id){
	  var len = document.eraserCsmDetailForm.checkFlg.length;

	  // 明細１件のみの場合
	  if (len == undefined) {
	    var checkbox = document.eraserCsmDetailForm.checkFlg;
	    if (checkbox.id == id) {
	      Clear(checkbox);
	    }
	    
		setKingaku(0);
	  	SumAll(); // 合計値の計算
	  } else {
	    for (var i = 0; i < len; i++) {
	      var checkbox = document.eraserCsmDetailForm.checkFlg[i];
	      if (checkbox.id == id) {
	        Clear(checkbox);
	      }
	    
	  	  setKingaku(i);
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

	<%-- 消込金額設定 --%>
	function setKingaku(index){
		var kingaku;			// 請求金額(明細)
		var len = document.eraserCsmDetailForm.checkFlg.length;
		var sumgokei = eval(removeComma($("goukei4").innerText)); // 入金消込残合計

		// 明細１件のみの場合
		if (len == undefined) {
			if(document.eraserCsmDetailForm.checkFlg.checked){
				var seikyu =  eval(removeComma($(strTotalSalesAmount).innerText)); // 請求金額

				// 入金消込残合計以上に消し込まないように調整
				if (sumgokei - seikyu < 0) {
					kingaku = sumgokei;
				} else {
					kingaku = seikyu;
				}

				$("strEraserAmount" + index).value = addComma(String(kingaku));
				$("strEraserAmount" + index).readOnly = false;
			} else {
				$("strEraserAmount" + index).value = 0;
				$("strEraserAmount" + index).readOnly = true;
			}
		} else {
			if(document.eraserCsmDetailForm.checkFlg[index].checked){
				var seikyu =  eval(removeComma($(strTotalSalesAmount)[index].innerText)); // 請求金額

				// 入金消込残合計以上に消し込まないように調整
				if (sumgokei - seikyu < 0) {
					kingaku = sumgokei;
				} else {
					kingaku = seikyu;
				}

				$("strEraserAmount" + index).value = addComma(String(kingaku));
				$("strEraserAmount" + index).readOnly = false;
			} else {
				$("strEraserAmount" + index).value = 0;
				$("strEraserAmount" + index).readOnly = true;
			}
		}
	}
	
	<%-- 消込額によりチェックのON,OFF切り替え --%>
	function setCheck(i) {
		var eraserAmount = strToNum($("strEraserAmount" + i).value, 0);
		var len = document.eraserCsmDetailForm.checkFlg.length;
		var chacked = false;
		var disabled = false;

		if (len == undefined) {
			<%-- 明細1件のみの場合 --%>
			if (eraserAmount == 0) {
				<%-- 消込額が0の場合はチェックOFF --%>
				if(document.eraserCsmDetailForm.checkFlg.disabled == false){
					document.eraserCsmDetailForm.checkFlg.checked = false;
				}
			} else {
				<%-- 消込額が0以外の場合はチェックON --%>
				document.eraserCsmDetailForm.checkFlg.checked = true;
			}
		} else {
			<%-- 明細複数件の場合 --%>
			if (eraserAmount == 0) {
				<%-- 消込額が0の場合はチェックOFF --%>
				if(document.eraserCsmDetailForm.checkFlg[i].disabled == false){
					document.eraserCsmDetailForm.checkFlg[i].checked = false;
				}
			} else {
				<%-- 消込額が0以外の場合はチェックON --%>
				document.eraserCsmDetailForm.checkFlg[i].checked = true;
			}
		}
	}

	<%-- 数値変換(不正な数値の場合defを返す) --%>
	function strToNum(str, def) {
		var val = parseFloat(str.replace(/,/g, ""));

		if(isNaN(val)){
			val = def;
		}

		return val;
	}
</script>

<%-- ▲業務固有javaScriptここまで --%>

</head>

<body>

<nested:form action="/EraserCsmDetail" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="dirtyFlg" />
	<nested:hidden property="insertFlg" styleId="insertFlg" />
	<nested:hidden property="kbn" styleId="kbn" />
	<nested:hidden property="baseSumEraserAmount" styleId="baseSumEraserAmount" />
	<nested:hidden property="approvalStatus" styleId="approvalStatus" />
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
								部署
							</td>
							<td width="420">
								<nested:write property="organizationCd" />:
								<nested:write property="organizationName" />
							</td>
						</tr>

						<tr>
							<td class="fcTitleDetail fb bcTitleDetail">
								消込日付
							</td>
							<td>
								<nested:text property="strEraserDate" maxlength="10" size="10" styleId="strEraserDate" style="ime-mode:disabled" onchange="setDirtyFlg();" />
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td><!-- 明細部(上段) -->
					<table width="100%" cellspacing="2" cellpadding="2" border="0">
						<tr class="fcTitleDetail fb bcTitleDetail">
							<td width="270" colspan="3">請求先</td>
							<td align="right">消込可能額</td>
							<td align="right">消込合計</td>
							<td align="right">消込残合計</td>
						</tr>
						<tr>
							<td colspan="3">
								<nested:write property="venderCd" />:
								<nested:write property="venderShortedName" />
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
						<%@ include file="/jsp/claim/credit_csm.jsf"%>
					</td>
				</tr>
				<tr>
					<td class="alignLeft">
					<table cellspacing="1" cellpadding="1" border="0">
						<tr>
							<nested:equal property="insertFlg" value="1">
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
								<a href="#" onclick="ClearAll('checkFlg'); return false;"	class="cssButton">
								 &nbsp;&nbsp;一括消込OFF&nbsp;&nbsp;
								</a>
								</div>
							</td>
							</nested:equal>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td><!-- 明細部(下段) -->
					<nested:notEmpty property="eraserCsmList">
						<table border="0" cellpadding="0" cellspacing="0" width="750">
							<tr>
								<td>
								<table width="100%" border="0">
									<tr>
										<td>
										<table width="100%" cellspacing="2" cellpadding="1" id="tblList">
											<tr>
												<td class="bcTitleList fb fcTitleList">消込</td>
												<td width="180" class="bcTitleList fb fcTitleList">得意先</td>
												<td width="70" class="bcTitleList fb fcTitleList">日付</td>
												<td width="80" class="bcTitleList fb fcTitleList">データ<br>種別</td>
												<td width="90" class="bcTitleList fb fcTitleList">伝票番号</td>
												<td align="right" width="80" class="bcTitleList fb fcTitleList">請求金額</td>
												<td align="right" width="80" class="bcTitleList fb fcTitleList">消込金額</td>
												<nested:notEqual property="eraserCsmList[0].checkKbn" value="2">
												<td align="right" width="80" class="bcTitleList fb fcTitleList">前回<br>消込金額</td>
												</nested:notEqual>
												<td width="70" class="bcTitleList fb fcTitleList">消込完了<br>日付</td>
											</tr>
											<nested:iterate id="eraserCsmList" property="eraserCsmList" indexId="index">
												<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
													<tr class="bcList1">
												</app:modulo>
					
												<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
													<tr class="bcList2">
												</app:modulo>

												<%-- 消込 --%>
												<td>
													<%-- 未消込 --%>
<%--													<nested:equal property="checkKbn" value="0">
														<nested:checkbox property="checkFlg" styleId="checkFlg" onclick="<%="setKingaku(" + pageContext.findAttribute("index").toString() + ");SumAll();" %>"/>
													</nested:equal>
--%>
													<%-- 消込中 --%>
<%--													<nested:equal property="checkKbn" value="1">
														<nested:checkbox property="checkFlg" styleId="checkFlg" onclick="<%="setKingaku(" + pageContext.findAttribute("index").toString() + ");SumAll();" %>"/>
													</nested:equal>
--%>
													<%-- 消込済 --%>
<%--													<nested:equal property="checkKbn" value="2">
														<nested:checkbox disabled="true" property="checkFlg" styleId="checkFlg"/>
													</nested:equal>
--%>
													<nested:checkbox property="checkFlg" styleId="checkFlg" onclick="<%="setKingaku(" + pageContext.findAttribute("index").toString() + ");SumAll();" %>"/>
												</td>
												<%-- 得意先 --%>
												<td>
													<nested:write property="invoiceCd" />:
													<nested:write property="venderShortedName" />
												</td>
												<%-- 日付 --%>
												<td>
													<nested:write property="strProcessingDate" />
												</td>
												<%-- データ種別 --%>
												<td>
													<nested:write property="strDataType" />
												</td>
												<%-- 伝票番号 --%>
												<td>
													<nested:write property="slipNo" />
												</td>
												<%-- 請求金額 --%>
												<td align="right" id="strTotalSalesAmount">
													<nested:write property="strEraserObjectAmount" />
												</td>
												<%-- 消込金額 --%>
												<td align="right">
<%--													<nested:equal property="checkKbn" value="0">
														<nested:text property="strEraserAmount" maxlength="11" size="7" readonly="true" style="ime-mode:disabled" onchange="setDirtyFlg();" onblur="SumAll();" styleId="<%="strEraserAmount" + pageContext.findAttribute("index").toString() %>" />
													</nested:equal>
													<nested:equal property="checkKbn" value="1">
														<nested:text property="strEraserAmount" maxlength="11" size="7" style="ime-mode:disabled" onchange="setDirtyFlg();" onblur="SumAll();" styleId="<%="strEraserAmount" + pageContext.findAttribute("index").toString() %>" />
													</nested:equal>
													<nested:equal property="checkKbn" value="2">
														<nested:write property="strEraserAmount" />
													</nested:equal>
--%>
													<nested:text property="strEraserAmount" maxlength="11" size="7" style="ime-mode:disabled" onchange='<%="setCheck(" + pageContext.findAttribute("index").toString() + "); setDirtyFlg();"%>' onblur="SumAll();" styleId="<%="strEraserAmount" + pageContext.findAttribute("index").toString() %>" />
												</td>
												<nested:notEqual property="checkKbn" value="2">
													<%-- 前回消込金額 --%>
													<td align="right">
														<nested:write property="strLastEraserAmount" />
													</td>
												</nested:notEqual>
												<%-- 消込完了日付 --%>
												<td>
													<nested:write property="strEraserCompleteDate" />
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
<%--							<nested:equal property="approvalStatus" value="1">
								<nested:equal property="approvalRequestAuthority" value="true">
									<td class="alignRight">
										<div id="cssButton">
											<a href="#" onclick="if(!putApprovalRequestConfirm()){return false;}else{return form_submit('op', 'approvalRequest'); return false;}" class="cssButton">
												&nbsp;&nbsp;承認依頼&nbsp;&nbsp;
											</a>
										</div>
									</td>

									<td width="50"><br></td>
								</nested:equal>

								<nested:equal property="updateAuthority" value="true">
									<td class="alignLeft">
										<div id="cssButton">
											<a href="#" onclick="if (!(putConfirm())) {return false;}else{return form_submit('op', 'update'); return false;}" class="cssButton">
											&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
											</a>
										</div>
									</td>

									<td width="50"><br></td>
								</nested:equal>
							</nested:equal>
--%>
							<nested:equal property="updateAuthority" value="true">
								<td class="alignLeft">
									<div id="cssButton">
										<a href="#" onclick="if (!(putConfirm())) {return false;}else{return form_submit('op', 'update'); return false;}" class="cssButton">
										&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
										</a>
									</div>
								</td>

								<td width="50"><br></td>
							</nested:equal>

<%--							<nested:equal property="approvalStatus" value="2">
								<nested:equal property="approvalAuthority" value="true">
									<td class="alignRight">
										<div id="cssButton">
											<a href="#" onclick="if(!putApprovalConfirm()){return false;}else{return form_submit('op', 'approval'); return false;}" class="cssButton">
												&nbsp;&nbsp;承&nbsp;&nbsp;認&nbsp;&nbsp;
											</a>
										</div>
									</td>

									<td width="50"><br></td>
								</nested:equal>

								<nested:equal property="negationAuthority" value="true">
									<td class="alignRight">
										<div id="cssButton">
											<a href="#" onclick="if(!putNegationConfirm()){return false;}else{return form_submit('op', 'negation'); return false;}" class="cssButton">
												&nbsp;&nbsp;否&nbsp;&nbsp;認&nbsp;&nbsp;
											</a>
										</div>
									</td>

									<td width="50"><br></td>
								</nested:equal>
							</nested:equal>

							<nested:equal property="approvalStatus" value="3">
								<nested:equal property="approvalCancelAuthority" value="true">
									<td class="alignRight">
									<div id="cssButton"><a href="#"
										onclick="if(!putApprovalCancelConfirm()){return false;}else{return form_submit('op', 'approvalCancel'); return false;}"
										class="cssButton"> &nbsp;&nbsp;承認取消&nbsp;&nbsp; </a></div>
									</td>
	
									<td width="50"><br>
									</td>
								</nested:equal>
							</nested:equal>
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
