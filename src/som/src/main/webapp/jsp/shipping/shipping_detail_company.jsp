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
		defineAsRequiredField("strShippingInstructDate");
		defineAsRequiredField("deliveryCd");
		defineAsRequiredField("scheduledShippingDate");

		<%-- 日付型フィールド定義 --%>
		defineAsDateField("strShippingInstructDate");
		defineAsDateField("strSuggestedDeliverlimit");
		defineAsDateField("strScheduledShippingDate");

		<%-- 数値型フィールド定義 --%>
		defineAsNumberField("strOrderQty");
		defineAsNumberField("strMatss");
		defineAsNumberField("strShippingInstructionSum");

		<%-- 明細部のフィールド定義 --%>
		var detailCount = $F("detailCount");
		for(var i = 0 ; i < detailCount; i++){
			<%-- 必須フィールド定義 --%>
			defineAsRequiredField("strShippingInstruction" + i);

			<%-- 数値型フィールド定義 --%>
			defineAsNumberField("strShippingInstruction" + i);
		}

		<%-- 受注選択ボタン制御 --%>
		setAttributeOrderSelectButton();
		<%-- 最初の項目がreadonly入力フィールドでうまくフォーカスがセットされないので --%>
		if ($("btnOrder").disabled) {
			$("strShippingInstructDate").focus();
		} else {
			$("btnOrder").focus();
		}

		<%-- 指図量累計の計算 --%>
		calcSum(i);

		<%-- 検索後入力された場合の不整合をただす --%>
		storeInitValues(/^srh.*/, 'dirtyFlg');
		refreshLabel();

	}, false);

	<%-- 20211209 Asclab Kouchi EdgeIE対応 --%>
	<%-- 受注選択ボタン制御 --%>
	function setAttributeOrderSelectButton(){
		var obj = $("btnOrder");
		if ($F("detailCount") < 1) {
			obj.setAttribute("onclick",
			new Function("open_modal_popup_edge(730, 600,'ShippingOrderSearch', '','srhOrderNo', $('srhOrderNo').value,'srhOrderDivision', $('srhOrderDivision').value,'srhScheduledShippingDateFrom', $('srhScheduledShippingDateFrom').value,'srhScheduledShippingDateTo', $('srhScheduledShippingDateTo').value,'srhDeliveryCd', $('srhDeliveryCd').value,'srhDeliveryName1', $('srhDeliveryName1').value,'srhVenderCd', $('srhVenderCd').value,'srhVenderName1', $('srhVenderName1').value,'srhItemCd', $('srhItemCd').value,'srhItemName', $('srhItemName').value,'srhOtherCompanyCd1', $('srhOtherCompanyCd1').value,'srhStyleOfPacking', $('srhStyleOfPacking').value); setDirtyFlg(); return false;")
			);
			obj.disabled = false;
		} else {
			obj.setAttribute("onclick", new Function("return false;"));
			obj.disabled = true;
		}
	}

	<%-- 数値変換(不正な数値の場合、defを返す) --%>
	function strToNum(str, def) {
		var val = parseFloat(str.replace(/,/g, ""));
		if(isNaN(val)){
			val = def;
		}
		return val;
	}

	<%-- 指図量累計の計算 --%>
	function calcSum(){
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
	}

	<%-- 変更フラグセット --%>
	function setDirtyFlg() {
		$("dirtyFlg").value = "true";
	}

	<%-- 確認メッセージ --%>
	function putConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "登録してよろしいですか？";

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
	function setShippingLotValues(shippingLotFlg, locationCd, locationName, lotNo, strInventoryQty, strBackorderQty, strInspectionQty, strAssignQty, strValidInventory) {
		if (shippingLotFlg) {
			var lineIndex = eval($("lineIndex").value)
			<%-- ロット検索後の設定処理 --%>
			$('locationCd' + lineIndex).value = locationCd;
			$('locationName' + lineIndex).value = locationName;
			$('lotNo' + lineIndex).value = lotNo;
			$('strInventoryQty' + lineIndex).value = strInventoryQty;
			$('strBackorderQty' + lineIndex).value = strBackorderQty;
			$('strInspectionQty' + lineIndex).value = strInspectionQty;
			$('strAssignQty' + lineIndex).value = strAssignQty;
			$('strValidInventory' + lineIndex).value = strValidInventory;

			$('lblLocationCd' + lineIndex).update($F('locationCd' + lineIndex));
			$('lblLocationName' + lineIndex).update($F('locationName' + lineIndex));
			$('lblLotNo' + lineIndex).update($F('lotNo' + lineIndex));
			$('lblInventoryQty' + lineIndex).update($F('strInventoryQty' + lineIndex));
			$('lblBackorderQty' + lineIndex).update($F('strBackorderQty' + lineIndex));
			$('lblInspectionQty' + lineIndex).update($F('strInspectionQty' + lineIndex));
			$('lblAssignQty' + lineIndex).update($F('strAssignQty' + lineIndex));
			$('lblValidInventory' + lineIndex).update($F('strValidInventory' + lineIndex));

			<%-- 指図量クリア --%>
			$('strShippingInstruction' + lineIndex).disabled = false;
			$('strShippingInstruction' + lineIndex).value = "";
			$('strShippingInstructionPrev' + lineIndex).value = "";
		}
	}
	function setShippingOrderValues(shippingOrderFlg,
			orderNo, rowNo, deliveryCd, deliveryName1, deliveryAddress,
			venderCd, itemCd, itemName, otherCompanyCd1, styleOfPacking,
			unitDivision, decimalPoint, round, balanceCd, strSuggestedDeliverlimit,
			strScheduledShippingDate, carryCd, strCarryFare, strOrderQty, strMatss,
			updateDate, updateDateDetail, srhOrderNo, srhOrderDivision, srhScheduledShippingDateFrom,
			srhScheduledShippingDateTo, srhDeliveryCd, srhDeliveryName1, srhVenderCd, srhVenderName1,
			srhItemCd, srhItemName, srhOtherCompanyCd1, srhStyleOfPacking, strDeliveryExpectedDate) {
		if (shippingOrderFlg) {
			var lineIndex = eval($("lineIndex").value)
			<%-- 受注検索後の設定処理 --%>
			$('orderNo').value = orderNo;
			$('orderRowNo').value = rowNo;
			$('deliveryCd').value = deliveryCd;
			$('deliveryName1').value = deliveryName1;
			$('deliveryAddress').value = deliveryAddress;

			$('venderCd').value = venderCd;
			$('itemCd').value = itemCd;
			$('itemName').value = itemName;
			$('otherCompanyCd1').value = otherCompanyCd1;
			$('styleOfPacking').value = styleOfPacking;

			$('unitDivision').value = unitDivision;
			$('decimalPoint').value = decimalPoint;
			$('round').value = round;
			$('balanceCd').value = balanceCd;
			$('strSuggestedDeliverlimit').value = strSuggestedDeliverlimit;

			$('strScheduledShippingDate').value = strScheduledShippingDate;
			$('carryCd').value = carryCd;
			$('strCarryFare').value = strCarryFare;
			$('strOrderQty').value = strOrderQty;
			$('strMatss').value = strMatss;

			$('strUpdateDateOrderHead').value = updateDate;
			$('strUpdateDateOrderDetail').value = updateDateDetail;
			$('srhOrderNo').value = srhOrderNo;
			$('srhOrderDivision').value = srhOrderDivision;
			$('srhScheduledShippingDateFrom').value = srhScheduledShippingDateFrom;

			$('srhScheduledShippingDateTo').value = srhScheduledShippingDateTo;
			$('srhDeliveryCd').value = srhDeliveryCd;
			$('srhDeliveryName1').value = srhDeliveryName1;
			$('srhVenderCd').value = srhVenderCd;
			$('srhVenderName1').value = srhVenderName1;

			$('srhItemCd').value = srhItemCd;
			$('srhItemName').value = srhItemName;
			$('srhOtherCompanyCd1').value = srhOtherCompanyCd1;
			$('srhStyleOfPacking').value = srhStyleOfPacking;
			$('strDeliveryExpectedDate').value = strDeliveryExpectedDate;

			<%-- 受注選択ボタン制御 --%>
			setAttributeOrderSelectButton();
			calcSum();

			form_submit('op', 'addRow');
		}
	}

	<%-- 指図量のフォーマット--%>
	function formatShippingInstruction(obj, lineIndex){
		var value = obj.value;
		if (value == null || value == "") {
			return;
		}
		<%-- 数値フォーマット --%>
		var res = digitStringFormat($F("decimalPoint"), $F("round"), value);
		if (res != "") {
			$("strShippingInstruction" + lineIndex).value = res;
		}
	}

</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="ShippingDetailCompany" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="dirtyFlg" />
	<nested:hidden property="venderCd" styleId="venderCd" />
	<nested:hidden property="strCarryFare" styleId="strCarryFare" />
	<nested:hidden property="unitDivision" styleId="unitDivision" />
	<nested:hidden property="decimalPoint" styleId="decimalPoint" />
	<nested:hidden property="round" styleId="round" />
	<nested:hidden property="lineIndex" styleId="lineIndex" />
	<nested:hidden property="strUpdateDateOrderHead" styleId="strUpdateDateOrderHead" />
	<nested:hidden property="strUpdateDateOrderDetail" styleId="strUpdateDateOrderDetail" />

	<nested:hidden property="srhOrderNo" styleId="srhOrderNo" />
	<nested:hidden property="srhOrderDivision" styleId="srhOrderDivision" />
	<nested:hidden property="srhScheduledShippingDateFrom" styleId="srhScheduledShippingDateFrom" />
	<nested:hidden property="srhScheduledShippingDateTo" styleId="srhScheduledShippingDateTo" />
	<nested:hidden property="srhDeliveryCd" styleId="srhDeliveryCd" />
	<nested:hidden property="srhDeliveryName1" styleId="srhDeliveryName1" />
	<nested:hidden property="srhVenderCd" styleId="srhVenderCd" />
	<nested:hidden property="srhVenderName1" styleId="srhVenderName1" />
	<nested:hidden property="srhItemCd" styleId="srhItemCd" />
	<nested:hidden property="srhItemName" styleId="srhItemName" />
	<nested:hidden property="srhOtherCompanyCd1" styleId="srhOtherCompanyCd1" />
	<nested:hidden property="srhStyleOfPacking" styleId="srhStyleOfPacking" />
	<nested:hidden property="strSuggestedDeliverlimit" styleId="strSuggestedDeliverlimit" />


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
									<td class="fcTitle fb" width="250">出荷指図詳細</td>
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
													<nested:text property="shippingNo" readonly="true" size="12" styleId="shippingNo" styleClass="noborderAl" tabindex="-1"/>
												</td>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail">受注番号</td>
												<td colspan="4">
													<a href="#" class="cssButton" id="btnOrder">
														&nbsp;選&nbsp;
													</a>
													&nbsp;
													<nested:text property="orderNo" readonly="true" size="12" styleId="orderNo" styleClass="noborderAl" tabindex="-1"/>
												</td>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail">受注行番号</td>
												<td colspan="4">
													<nested:text property="orderRowNo" readonly="true" size="12" styleId="orderRowNo" styleClass="noborderAl" tabindex="-1"/>
												</td>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail">ステータス</td>
												<td colspan="4">
													<nested:text property="shippingStatusName" size="20" readonly="true" styleId="shippingStatusName" styleClass="noborderAl" tabindex="-1"/>
												</td>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail">出荷指図日</td>
												<td colspan="4"><nested:text property="strShippingInstructDate" maxlength="10" size="12" styleId="strShippingInstructDate" onchange="setDirtyFlg();" style="ime-mode:disabled" /></td>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail">納入先</td>
												<td>
													<nested:text property="deliveryCd" size="12" readonly="true" styleId="deliveryCd" styleClass="noborderAl" tabindex="-1"/>
												</td>
												<td>
													<nested:text property="deliveryName1" size="20" readonly="true" styleId="deliveryName1" styleClass="noborderAl" tabindex="-1"/>
												</td>
												<td class="fcTitleDetail fb bcTitleDetail">納入先宛先</td>
												<td><%-- 納入先宛先 --%>
													<nested:text property="deliveryAddress" readonly="true" size="12" styleId="deliveryAddress" styleClass="noborderAl" tabindex="-1"/>
												</td>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail">納入先住所</td>
												<td  height="30" colspan = "4">
													<nested:write property="deliveryAllAddress"/>
												</td>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail">品目</td>
												<td><%-- 品目コード --%>
													<nested:text property="itemCd" readonly="true" size="12" styleId="itemCd" styleClass="noborderAl" tabindex="-1"/>
												</td>
												<td><%-- 品目名称 --%>
													<nested:text property="itemName" readonly="true" size="20" styleId="itemName" styleClass="noborderAl" tabindex="-1"/>
												</td>
												<td class="fcTitleDetail fb bcTitleDetail" width="90">荷姿</td>
												<td><%-- 荷姿 --%>
													<nested:text property="styleOfPacking" readonly="true" size="12" styleId="styleOfPacking" styleClass="noborderAl" tabindex="-1"/>
												</td>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail">他社コード1</td>
												<td colspan="4"><%-- 他社コード --%>
													<nested:text property="otherCompanyCd1" readonly="true" size="20" styleId="otherCompanyCd1" styleClass="noborderAl" tabindex="-1"/>
												</td>
											</tr>
											<tr><%-- 帳合コード --%>
												<td class="fcTitleDetail fb bcTitleDetail">帳合コード</td>
												<td colspan="4">
													<nested:text property="balanceCd" readonly="true" size="12" styleId="balanceCd" styleClass="noborderAl" tabindex="-1"/>
												</td>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail">納入予定日</td>
												<td colspan="4"><nested:text property="strDeliveryExpectedDate" readonly="true" size="12" styleId="strSuggestedDeliverlimit" styleClass="noborderAl" tabindex="-1"/></td>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail">出荷予定日</td>
												<td colspan="4"><nested:text property="strScheduledShippingDate" readonly="true" size="12" styleId="strScheduledShippingDate" styleClass="noborderAl" tabindex="-1"/></td>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail">運送会社</td>
												<td colspan="4">
													<nested:select property="carryCd" style="margin: 0;padding: 0" styleId="carryCd" onchange="setDirtyFlg();" >
														<nested:optionsCollection property="carryCombo" label="labales" value="values" />
													</nested:select>
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
										<nested:lessEqual property="shippingStatus" value="1">
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
										</nested:lessEqual>
										<nested:notEmpty property="detailList">
											<table cellspacing="2" cellpadding="1" id="detailTblList">
												<tr class="bcTitleList fb fcTitleList">
													<td></td>
													<td>検索</td>
													<td>ロケーション</td>
													<td>名称</td>
													<td>ロット番号</td>
													<td>指図量</td>
													<td>在庫量</td>
													<td>発注残</td>
													<td>検査待</td>
													<td>引当残</td>
													<td>有効在庫</td>
												</tr>
												<%-- 明細部 >>>>> --%>
												<nested:iterate id="detailList" property="detailList" indexId="detailIndex">
													<app:modulo numerator='<%=pageContext.findAttribute("detailIndex").toString()%>' denominator="2" zero="false">
														<tr class="bcList1">
													</app:modulo>
													<app:modulo numerator='<%=pageContext.findAttribute("detailIndex").toString()%>' denominator="2" zero="true">
														<tr class="bcList2">
													</app:modulo>
															<td><%-- 削除チェックボックス --%>
																<nested:checkbox property="checkFlg" styleId="checkFlg" />
															</td>
															<td class="alignCenter"><%-- 検索ボタン--%>
																<div id="cssButton">
																	<!-- 20211209 Asclab Kouchi EdgeIE対応 -->
																	<a href="#"  class="cssButton"
																	   onclick="<%="setLine(" + pageContext.findAttribute("detailIndex").toString() + ");open_modal_popup_edge(730, 600,'ShippingLotSearch', '', 'srhItemCd', $('itemCd').value, 'srhLocationCd', $('locationCd" + pageContext.findAttribute("detailIndex").toString() + "').value, 'srhLocationName', $('locationName" + pageContext.findAttribute("detailIndex").toString() + "').value, 'venderDivision', 'TS', 'venderCd', $('venderCd').value); setDirtyFlg(); return false;"%>">
																		選
																	</a>
																</div>
															</td>
															<td><%-- ロケーションコード --%>
																<div id="<%="lblLocationCd" + pageContext.findAttribute("detailIndex").toString() %>">
																	<nested:write property="locationCd" />
																</div>
																<nested:hidden property="locationCd" styleId="<%="locationCd" + pageContext.findAttribute("detailIndex").toString() %>" />
															</td>
															<td width="150"><%-- ロケーション名称 --%>
																<div id="<%="lblLocationName" + pageContext.findAttribute("detailIndex").toString() %>">
																	<nested:write property="locationName" />
																</div>
																<nested:hidden property="locationName" styleId="<%="locationName" + pageContext.findAttribute("detailIndex").toString() %>" />
															</td>
															<td><%-- ロット番号 --%>
																<div id="<%="lblLotNo" + pageContext.findAttribute("detailIndex").toString() %>">
																	<nested:write property="lotNo" />
																</div>
																<nested:hidden property="lotNo" styleId="<%="lotNo" + pageContext.findAttribute("detailIndex").toString() %>" />
															</td>
															<td class="alignRight"><%-- 指図量 --%>
																<nested:notEmpty property="locationCd">
																	<nested:text property="strShippingInstruction" maxlength="22" size="5"
																	styleId="<%="strShippingInstruction" + pageContext.findAttribute("detailIndex").toString() %>"
																	onchange='<%="setDirtyFlg(); formatShippingInstruction(this," + pageContext.findAttribute("detailIndex").toString() + "); calcSum();"%>'
																	style="ime-mode:disabled" />
																</nested:notEmpty>
																<nested:empty property="locationCd">
																	<nested:text property="strShippingInstruction" maxlength="22" size="5"
																	styleId="<%="strShippingInstruction" + pageContext.findAttribute("detailIndex").toString() %>"
																	onchange='<%="setDirtyFlg(); formatShippingInstruction(this," + pageContext.findAttribute("detailIndex").toString() + "); calcSum();"%>'
																	style="ime-mode:disabled" disabled="true" />
																</nested:empty>
															</td>
															<td class="alignRight"><%-- 在庫量 --%>
																<div id="<%="lblInventoryQty" + pageContext.findAttribute("detailIndex").toString() %>">
																	<nested:write property="strInventoryQty" />
																</div>
																<nested:hidden property="strInventoryQty" styleId="<%="strInventoryQty" + pageContext.findAttribute("detailIndex").toString() %>" />
															</td>
															<td class="alignRight"><%-- 発注残 --%>
																<div id="<%="lblBackorderQty" + pageContext.findAttribute("detailIndex").toString() %>">
																	<nested:write property="strBackorderQty" />
																</div>
																<nested:hidden property="strBackorderQty" styleId="<%="strBackorderQty" + pageContext.findAttribute("detailIndex").toString() %>" />
															</td>
															<td class="alignRight"><%-- 検査待 --%>
																<div id="<%="lblInspectionQty" + pageContext.findAttribute("detailIndex").toString() %>">
																	<nested:write property="strInspectionQty" />
																</div>
																<nested:hidden property="strInspectionQty" styleId="<%="strInspectionQty" + pageContext.findAttribute("detailIndex").toString() %>" />
															</td>
															<td class="alignRight"><%-- 引当残 --%>
																<div id="<%="lblAssignQty" + pageContext.findAttribute("detailIndex").toString() %>">
																	<nested:write property="strAssignQty" />
																</div>
																<nested:hidden property="strAssignQty" styleId="<%="strAssignQty" + pageContext.findAttribute("detailIndex").toString() %>" />
															</td>
															<td class="alignRight"><%-- 有効在庫 --%>
																<div id="<%="lblValidInventory" + pageContext.findAttribute("detailIndex").toString() %>">
																	<nested:write property="strValidInventory" />
																</div>
																<nested:hidden property="strValidInventory" styleId="<%="strValidInventory" + pageContext.findAttribute("detailIndex").toString() %>" />
																<nested:hidden property="strShippingInstructionPrev" styleId="<%="strShippingInstructionPrev" + pageContext.findAttribute("detailIndex").toString() %>" />
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
												<td class="alignRight">
													<nested:text property="strOrderQty" readonly="true" size="7" styleId="strOrderQty" styleClass="noborderAl" tabindex="-1"/>
												</td>
												<td class="fcTitleDetail fb bcTitleDetail">増付数</td>
												<td class="alignRight">
													<nested:text property="strMatss" readonly="true" size="7" styleId="strMatss" styleClass="noborderAl" tabindex="-1"/>
												</td>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail">指図量累計</td>
												<td>
													<nested:text property="strShippingInstructionSum" readonly="true" size="7" styleId="strShippingInstructionSum" styleClass="noborderAl" tabindex="-1"/>
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
														<nested:lessEqual property="shippingStatus" value="1">
															<nested:equal property="insertFlg" value="0">
																<nested:equal property="updateAuthority" value="true">
																	<a href="#" onclick="if (!(putConfirm())) {return false;}else{return form_submit('op', 'update'); return false;}" class="cssButton">
																		&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
																	</a>
																</nested:equal>
															</nested:equal>
															<nested:equal property="insertFlg" value="1">
																<nested:equal property="updateAuthority" value="true">
																	<a href="#" onclick="if (!(putConfirm())) {return false;}else{return form_submit('op', 'insert'); return false;}" class="cssButton">
																		&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
																	</a>
																</nested:equal>
															</nested:equal>
															<nested:equal property="insertFlg" value="0">
																<nested:equal property="deleteAuthority" value="true">
																	&nbsp;&nbsp;&nbsp;&nbsp;
																	<a href="#" onclick="if (!(deleteAlert())) {return false;}else{return form_submit('op', 'delete'); return false;}" class="cssButton">
																		&nbsp;&nbsp;削&nbsp;&nbsp;除&nbsp;&nbsp;
																	</a>
																</nested:equal>
															</nested:equal>
															&nbsp;&nbsp;&nbsp;&nbsp;
														</nested:lessEqual>
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
