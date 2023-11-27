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

<%-- ▼業務固有css ここから
<style type="text/css">
</style>
<%-- ▲業務固有css ここまで --%>

<%-- ▼業務固有javaScript ここから --%>
<script language="JavaScript" type="text/javascript">

	<%--onload時の処理--%>
	Event.observe(window, 'load', function() {

		<%-- フォーム設定の初期化 --%>
		initializeFormState();

		<%-- 必須フィールド定義 --%>
		defineAsRequiredField("strCarryFare");

		<%-- 数値型フィールド定義 --%>
		defineAsNumberField("strCarryFare");

		<%-- 明細部のフィールド定義 --%>
		var detailCount = $F("detailCount");
		for(var i = 0 ; i < detailCount; i++){
			<%-- 必須フィールド定義 --%>
			defineAsRequiredField("strShippingResultQuantity" + i);
			defineAsRequiredField("strShippingResultDate" + i);

			<%-- 日付型フィールド定義 --%>
			defineAsDateField("strShippingResultDate" + i);

			<%-- 数値型フィールド定義 --%>
			defineAsNumberField("strShippingResultQuantity" + i);
		}

		<%-- 累計の計算 --%>
		calcShippingInstructionSum(i);
		calcShippingResultQuantitySum(i);

		<%-- 検索後入力された場合の不整合をただす --%>
		storeInitValues(/^srh.*/, 'dirtyFlg');
		refreshLabel();

	}, false);

	<%-- 数値変換(不正な数値の場合、defを返す) --%>
	function strToNum(str, def) {
		var val = parseFloat(str.replace(/,/g, ""));
		if(isNaN(val)){
			val = def;
		}
		return val;
	}

	<%-- 指図量累計の計算 --%>
	function calcShippingInstructionSum(){
		var count = $F("detailCount");
		if (count < 1) {
			$("strShippingInstructionSum").value = "";
			return;
		}
		var sum = 0;
		for(var i = 0 ; i < count ; i++ ){
			sum += strToNum($F("strShippingInstruction" + i), 0);
		}
		<%-- 数値フォーマット --%>
		var res = digitFormat($F("decimalPoint"), $F("round"), sum);
		$("strShippingInstructionSum").value = res;
		$("lblStrShippingInstructionSum").update(res);
	}

	<%-- 実績量累計の計算 --%>
	function calcShippingResultQuantitySum(){
		var count = $F("detailCount");
		if (count < 1) {
			$("strShippingResultQuantitySum").value = "";
			return;
		}
		var sum = 0;
		for(var i = 0 ; i < count ; i++ ){
			sum += strToNum($F("strShippingResultQuantity" + i), 0);
		}

		<%-- 数値フォーマット --%>
		var res = digitFormat($F("decimalPoint"), $F("round"), sum);
		$("strShippingResultQuantitySum").value = res;
		$("lblStrShippingResultQuantitySum").update(res);
	}

	<%-- 変更フラグセット --%>
	function setDirtyFlg() {
		$("dirtyFlg").value = "true";
	}

	<%-- 確認メッセージ --%>
	function putConfirm() {
		var compFlag = true;
		if ($F("shippingStatus") != "5") {
			<%-- 出荷完了以外の場合、全て完納かどうかのチェックをする --%>
			var count = $F("detailCount");
			for(var i = 0 ; i < count ; i++){
				var chk = $F("detailList[" + i + "].deliveryCompFlag");
				if(!chk){
					compFlag = false;
					break;
				}
			}
		} else {
			<%-- 通常のメッセージとするため、未完納ありとする --%>
			compFlag = false;
		}

		alertMsg = new Array();
		if (compFlag) {
			alertMsg[0] = "出荷完了しますがよろしいですか？";
		} else {
			alertMsg[0] = "登録してよろしいですか？";
		}

		return confirm(alertMsg[0]);
	}

	<%-- 行削除確認メッセージ --%>
	function putDelListConfirm() {
		var count = $F("detailCount");

		var flag = false;
		for(var i = 0 ; i < count ; i++){
			var chk = $F("detailList[" + i + "].checkFlg");
			if(chk){
				flag = true;
				break;
			}
		}
		if(flag){
			alertMsg = new Array();
			alertMsg[0] = "行を削除してよろしいですか？";
			if (confirm(alertMsg[0])) {
				return true;
			} else {
				return false;
			}
		}else{
			alertMsg = new Array();
			alertMsg[0] = "行が選択されていません。";
			alert(alertMsg[0]);
			return false;
		}

	}

	<%-- 削除確認メッセージ --%>
	function deleteAlert() {
		alertMsg = new Array();
		alertMsg[0] = "削除してよろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- 変更フラグセット --%>
		function putDirtyConfirm() {
		var flg = $("dirtyFlg").value;
		if (flg == "true") {
			// 何か値が変更されている場合
			return continueConfirm();
		}else{
			return true;
		}
	}

	<%-- チェックを入れる --%>
	function setLine(lineIndex) {
		$("lineIndex").value = lineIndex;
	}

	<%-- 20211209 Asclab Kouchi EdgeIE対応 --%>
	<%-- ポップアップ後の設定処理 --%>
	function setShippingResultLotValues(shippingResultLotFlg, locationCd, locationName, lotNo, strInventoryQty) {
		if (shippingResultLotFlg) {
			var lineIndex = eval($("lineIndex").value)
			var i=6;
			<%-- ロット検索後の設定処理 --%>
			$("locationCd" + lineIndex).value = locationCd;
			$("locationName" + lineIndex).value = locationName;
			$("lotNo" + lineIndex).value = lotNo;
			$("strInventoryQty" + lineIndex).value = strInventoryQty;

			$("lblLocationCd" + lineIndex).update($F("locationCd" + lineIndex));
			$("lblLocationName" + lineIndex).update($F("locationName" + lineIndex));
			$("lblLotNo" + lineIndex).update($F('lotNo' + lineIndex));

			<%-- 指図量、実績量クリア --%>
			$("strShippingInstruction" + lineIndex).value = digitStringFormat($F("decimalPoint"), $F("round"), "0");
			$("lblShippingInstruction" + lineIndex).update($F("strShippingInstruction" + lineIndex));
			$("strShippingResultQuantity" + lineIndex).disabled = false;
			$("strShippingResultQuantity" + lineIndex).value = "";
			$("strShippingResultQuantityPrev" + lineIndex).value = "";
			$("strShippingResultDate" + lineIndex).disabled = false;
			$("deliveryCompFlag" + lineIndex).checked = false;

			<%-- 累計の計算 --%>
			calcShippingInstructionSum(i);
			calcShippingResultQuantitySum(i);
		}
	}

	<%-- 実績量変更時処理 --%>
	function changeShippingResultQuantity(obj, lineIndex){
		var value = obj.value;
		if (value != null && value != "") {
			<%-- 数値フォーマット --%>
			var res = digitStringFormat($F("decimalPoint"), $F("round"), value);
			if (res != "") {
				$("strShippingResultQuantity" + lineIndex).value = res;
			}
		}

		<%-- 完了チェックボックス状態設定 --%>
		var instruction = strToNum($F("strShippingInstruction" + lineIndex), 0);
		<%-- 新規追加行はチェックしない(指図量が0の行を新規追加行とする) --%>
		if (instruction > 0) {
			var resultQuantity = strToNum($F("strShippingResultQuantity" + lineIndex), 0);
			<%-- 指図量<=実績量となった場合完了チェックボックスをONとする --%>
			$("deliveryCompFlag" + lineIndex).checked = (instruction <= resultQuantity);
		}

		<%-- 実績量累計計算 --%>
		calcShippingResultQuantitySum();
	}

	<%-- 運賃のフォーマット --%>
	function formatCarryFare(obj){
		var value = obj.value;
		if (value != null && value != "") {
			<%-- 数値フォーマット --%>
			var res = digitStringFormat($F("decimalPointUntin"), $F("roundUntin"), value);
			if (res != "") {
				$("strCarryFare").value = res;
			}
		}
	}
</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="ShippingResultDetail" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="dirtyFlg" />
	<nested:hidden property="venderCd" styleId="venderCd" />
	<nested:hidden property="unitDivision" styleId="unitDivision" />
	<nested:hidden property="decimalPoint" styleId="decimalPoint" />
	<nested:hidden property="round" styleId="round" />
	<nested:hidden property="unitDivisionUntin" styleId="unitDivisionUntin" />
	<nested:hidden property="decimalPointUntin" styleId="decimalPointUntin" />
	<nested:hidden property="roundUntin" styleId="roundUntin" />
	<nested:hidden property="lineIndex" styleId="lineIndex" />
	<nested:hidden property="shippingStatus" styleId="shippingStatus" />

	<table cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td class="valignTop alignLeft">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td><!-- ヘッダー部 -->
							<table border="0" cellpadding="0" cellspacing="0" width="100%">
								<tr>
									<td height="10" colspan="2"></td>
								</tr>
								<tr>
									<td class="fcTitle fb" width="250">出荷実績詳細</td>
									<td>
										<table border="0" cellpadding="0" cellspacing="0">
											<tr>
												<td>
													<%-- メッセージ表示 --%>
													<%@ include file="/jsp/common/disp_info_message.jsf"%>
													<%-- メッセージ表示 ここまで --%>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td class="bcTitleLine" colspan="2"></td>
								</tr>
								<tr>
									<td height= 5  colspan= 2 ></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<%-- メッセージ表示 --%>
							<%@ include file="/jsp/common/disp_error_message.jsf"%>
							<%-- メッセージ表示 ここまで --%>
						</td>
					</tr>
					<tr>
						<td><!-- 明細部 -->
							<table border="0" cellspacing="2" cellpadding="2" width="100%">
								<tr>
									<td>
										<table border="0" cellspacing="2" cellpadding="1">
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail">出荷番号</td>
												<td colspan="4">
													<nested:write property="shippingNo" />
												</td>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail">受注番号</td>
												<td colspan="4">
													<nested:write property="orderNo" />
												</td>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail">受注行番号</td>
												<td colspan="4">
													<nested:write property="orderRowNo" />
												</td>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail">ステータス</td>
												<td colspan="4">
													<nested:write property="shippingStatusName" />
												</td>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail">出荷指図日</td>
												<td colspan="4">
													<nested:write property="strShippingInstructDate" />
												</td>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail">納入先</td>
												<td width = "30px">
													<nested:write property="deliveryCd" />
												</td>
												<td>
													<nested:write property="searchKana" />
												</td>
												<nested:notEmpty property="orderNo">
													<td class="fcTitleDetail fb bcTitleDetail">納入先宛先</td>
													<td><%-- 納入先宛先 --%>
														<nested:write property="deliveryAddress" />
													</td>
												</nested:notEmpty>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail">納入先住所</td>
												<td colspan = "4">
													<nested:write property="deliveryAllAddress"/>
												</td>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail">品目</td>
												<td width = "30px"><%-- 品目コード --%>
													<nested:write property="itemCd" />
													<nested:hidden property="itemCd" styleId="itemCd" />
												</td>
												<td width = "250px"><%-- 品目名称 --%>
													<nested:write property="itemName" />
												</td>
												<td class="fcTitleDetail fb bcTitleDetail" width="90">荷姿</td>
												<td><%-- 荷姿 --%>
													<nested:write property="styleOfPacking" />
												</td>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail">他社コード1</td>
												<td colspan="4"><%-- 他社コード --%>
													<nested:write property="otherCompanyCd1" />
												</td>
											</tr>
											<tr><%-- 帳合コード --%>
												<td class="fcTitleDetail fb bcTitleDetail">帳合コード</td>
												<td colspan="4">
													<nested:write property="balanceCd" />
												</td>
											</tr>

											<nested:notEqual name="shippingResultDetailForm" property="strDeliveryExpectedDate" value="">

												<tr>
													<td class="fcTitleDetail fb bcTitleDetail">納入予定日</td>
													<td colspan="4">
														<nested:write property="strDeliveryExpectedDate" />
													</td>
												</tr>
											</nested:notEqual>
											<nested:equal name="shippingResultDetailForm" property="strDeliveryExpectedDate" value="">

												<tr>
													<td class="fcTitleDetail fb bcTitleDetail">希望納期</td>
													<td colspan="4">
														<nested:write property="strSuggestedDeliverlimit" />
													</td>
												</tr>
											</nested:equal>

											<tr>
												<td class="fcTitleDetail fb bcTitleDetail">出荷予定日</td>
												<td colspan="4">
													<nested:write property="strScheduledShippingDate" />
												</td>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail">運送会社</td>
												<td colspan="4">
													<nested:write property="carryName" />
												</td>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail">運賃</td>
												<td colspan="4">
													<nested:text property="strCarryFare" maxlength="22" size="15" styleId="strCarryFare"
													 onchange="setDirtyFlg(); formatCarryFare(this);"
													 />円
												</td>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail">出荷完了日</td>
												<td colspan="4">
													<nested:write property="strShippingResultDate" />
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td class="bcTitleLine" colspan="2"></td>
								</tr>
								<tr>
									<td>
										<%-- 明細部 --%>
										<nested:hidden property="detailCount" styleId="detailCount" />
										<nested:lessThan name="shippingResultDetailForm" property="shippingStatus" value="5">
											<table cellspacing="0" cellpadding="1" border="0" >
												<tr>
													<td></td>
												</tr>
												<tr>
													<td>
														<div id="cssButton">
															<a href="#"
																onclick="return form_submit('op', 'addRow'); return false;"
																class="cssButton">&nbsp;行&nbsp;追&nbsp;加&nbsp;</a>
                        	        	    	            &nbsp;&nbsp;&nbsp;&nbsp;
															<a href="#"
																onclick="if (!(putDelListConfirm())) {return false;}else{return form_submit('op', 'delRow'); return false;}"
																class="cssButton">&nbsp;行&nbsp;削&nbsp;除&nbsp;</a>
														</div>
													</td>
												</tr>
												<tr>
													<td></td>
												</tr>
											</table>
										</nested:lessThan>
										<nested:notEmpty property="detailList">
											<table cellspacing="2" cellpadding="1" id="detailTblList">
												<tr class="bcTitleList fb fcTitleList">
													<nested:lessThan name="shippingResultDetailForm" property="shippingStatus" value="5">
														<td></td>
														<td>検索</td>
													</nested:lessThan>
													<td>ロケーション</td>
													<td>名称</td>
													<td>ロット番号</td>
													<td>指図量</td>
													<td>実績量</td>
													<td>出荷日</td>
													<td>完了</td>
												</tr>
												<%-- 明細部 >>>>> --%>
												<nested:iterate id="detailList" property="detailList" indexId="index">
													<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
														<tr class="bcList1">
													</app:modulo>
													<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
														<tr class="bcList2">
													</app:modulo>
															<nested:lessThan name="shippingResultDetailForm" property="shippingStatus" value="5">
																<td><%-- 削除チェックボックス --%>
																	<nested:checkbox property="checkFlg" styleId="checkFlg" />
																</td>
																<td class="alignCenter"><%-- 検索ボタン--%>
																	<div id="cssButton">
																		<!-- 20211209 Asclab Kouchi EdgeIE対応 -->
																		<a href="#"  class="cssButton"
																		   onclick="<%="setLine(" + pageContext.findAttribute("index").toString() + ");open_modal_popup_edge(730, 600,'ShippingResultLotSearch', '', 'srhItemCd', $('itemCd').value, 'srhLocationCd', $('locationCd" + pageContext.findAttribute("index").toString() + "').value, 'srhLocationName', $('locationName" + pageContext.findAttribute("index").toString() + "').value, 'venderDivision', 'TS', 'venderCd', $('venderCd').value); setDirtyFlg(); return false;"%>">
																			選
																		</a>
																	</div>
																</td>
															</nested:lessThan>
															<td><%-- ロケーションコード --%>
																<div id="<%="lblLocationCd" + pageContext.findAttribute("index").toString() %>">
																	<nested:write property="locationCd" />
																</div>
																<nested:hidden property="locationCd" styleId="<%="locationCd" + pageContext.findAttribute("index").toString() %>" />
															</td>
															<td width="150"><%-- ロケーション名称 --%>
																<div id="<%="lblLocationName" + pageContext.findAttribute("index").toString() %>">
																	<nested:write property="locationName" />
																</div>
																<nested:hidden property="locationName" styleId="<%="locationName" + pageContext.findAttribute("index").toString() %>" />
															</td>
															<td><%-- ロット番号 --%>
																<div id="<%="lblLotNo" + pageContext.findAttribute("index").toString() %>">
																	<nested:write property="lotNo" />
																</div>
																<nested:hidden property="lotNo" styleId="<%="lotNo" + pageContext.findAttribute("index").toString() %>" />
															</td>
															<td class="alignRight"><%-- 指図量 --%>
																<div id="<%="lblShippingInstruction" + pageContext.findAttribute("index").toString() %>">
																	<nested:write property="strShippingInstruction" />
																</div>
																<nested:hidden property="strShippingInstruction" styleId="<%="strShippingInstruction" + pageContext.findAttribute("index").toString() %>" />
															</td>
															<td class="alignRight"><%-- 実績量 --%>
																<nested:lessThan name="shippingResultDetailForm" property="shippingStatus" value="5">
																	<nested:notEmpty property="locationCd">
																		<nested:text property="strShippingResultQuantity" maxlength="22" size="7"
																		styleId="<%="strShippingResultQuantity" + pageContext.findAttribute("index").toString() %>"
																		onchange='<%="setDirtyFlg(); changeShippingResultQuantity(this," + pageContext.findAttribute("index").toString() + ");"%>'
																		style="ime-mode:disabled" />
																	</nested:notEmpty>
																	<nested:empty property="locationCd">
																		<nested:text property="strShippingResultQuantity" maxlength="22" size="7"
																		styleId="<%="strShippingResultQuantity" + pageContext.findAttribute("index").toString() %>"
																		onchange='<%="setDirtyFlg(); changeShippingResultQuantity(this," + pageContext.findAttribute("index").toString() + ");"%>'
																		style="ime-mode:disabled" disabled="true" />
																	</nested:empty>
																</nested:lessThan>
																<nested:equal name="shippingResultDetailForm" property="shippingStatus" value="5">
																	<nested:write property="strShippingResultQuantity" />
																	<nested:hidden property="strShippingResultQuantity" styleId="<%="strShippingResultQuantity" + pageContext.findAttribute("index").toString() %>" />
																</nested:equal>
																<nested:hidden property="strShippingResultQuantityPrev" styleId="<%="strShippingResultQuantityPrev" + pageContext.findAttribute("index").toString() %>" />
																<nested:hidden property="strInventoryQty" styleId="<%="strInventoryQty" + pageContext.findAttribute("index").toString() %>" />
															</td>
															<td><%-- 出荷日 --%>
																<nested:lessThan name="shippingResultDetailForm" property="shippingStatus" value="5">
																	<nested:notEmpty property="locationCd">
																		<nested:text property="strShippingResultDate" maxlength="10" size="8" styleId="<%="strShippingResultDate" + pageContext.findAttribute("index").toString() %>" style="ime-mode:disabled" onchange="setDirtyFlg()"/>
																	</nested:notEmpty>
																	<nested:empty property="locationCd">
																		<nested:text property="strShippingResultDate" maxlength="10" size="8" styleId="<%="strShippingResultDate" + pageContext.findAttribute("index").toString() %>" style="ime-mode:disabled" onchange="setDirtyFlg()" disabled="true" />
																	</nested:empty>
																</nested:lessThan>
																<nested:equal name="shippingResultDetailForm" property="shippingStatus" value="5">
																	<nested:write property="strShippingResultDate" />
																	<nested:hidden property="strShippingResultDate" styleId="<%="strShippingResultDate" + pageContext.findAttribute("index").toString() %>" />
																</nested:equal>
															</td>
															<td><%-- 完了 --%>
																<nested:lessThan name="shippingResultDetailForm" property="shippingStatus" value="5">
																	<nested:checkbox property="deliveryCompFlag" styleId="<%="deliveryCompFlag" + pageContext.findAttribute("index").toString() %>" onchange="setDirtyFlg()"/>
																</nested:lessThan>
																<nested:equal name="shippingResultDetailForm" property="shippingStatus" value="5">
																	<nested:checkbox property="deliveryCompFlag" styleId="<%="deliveryCompFlag" + pageContext.findAttribute("index").toString() %>" disabled="true" />
																</nested:equal>
															</td>
														</tr>
												</nested:iterate>
											</table>
										</nested:notEmpty>
									</td>
								</tr>
								<tr>
									<td height="5"></td>
								</tr>
								<tr>
									<td><%-- 明細部3 --%>
										<table cellspacing="2" cellpadding="2" border="0">
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail">受注量</td>
												<td class="alignRight" width="60px">
													<div id="lblOrderQty">
														<nested:write property="strOrderQty" />
													</div>
													<nested:hidden property="strOrderQty" styleId="strOrderQty" />
												</td>
												<td class="fcTitleDetail fb bcTitleDetail">増付数</td>
												<td class="alignRight" width="60px">
													<div id="lblMatss">
														<nested:write property="strMatss" />
													</div>
													<nested:hidden property="strMatss" styleId="strMatss" />
												</td>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail">指図量累計</td>
												<td class="alignRight">
													<div id="lblStrShippingInstructionSum">
														<nested:write property="strShippingInstructionSum" />
													</div>
													<nested:hidden property="strShippingInstructionSum" styleId="strShippingInstructionSum" />
												</td>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail">実績量累計</td>
												<td class="alignRight">
													<div id="lblStrShippingResultQuantitySum">
														<nested:write property="strShippingResultQuantitySum" />
													</div>
													<nested:hidden property="strShippingResultQuantitySum" styleId="strShippingResultQuantitySum" />
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td height="5"></td>
								</tr>
								<tr>
									<td>
										<table>
											<tr>
												<td class="alignLeft">
													<div id="cssButton">
														<nested:equal property="updateAuthority" value="true">
															<a href="#" onclick="if (!(putConfirm())) {return false;}else{return form_submit('op', 'update'); return false;}" class="cssButton">
																&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
															</a>
															&nbsp;&nbsp;&nbsp;&nbsp;
														</nested:equal>
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
			</td>
		</tr>
	</table>

</nested:form>
</body>
</html:html>
