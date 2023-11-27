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
		<%-- フォーカスセット --%>
		initFocus();

		<%-- 必須フィールド定義 --%>
		defineAsRequiredField("strShippingInstructDate");
		defineAsRequiredField("deliveryCd");
		defineAsRequiredField("strScheduledShippingDate");

		<%-- 日付型フィールド定義 --%>
		defineAsDateField("strShippingInstructDate");
		defineAsDateField("strSuggestedDeliverlimit");
		defineAsDateField("strScheduledShippingDate");

		<%-- 数値型フィールド定義 --%>

		<%-- 明細部のフィールド定義 --%>
		var itemCount = $F("itemCount");
		for(var i = 0 ; i < itemCount; i++){
			var prefixItem = "itemList[" + i + "].";

			<%-- 必須フィールド定義 --%>
			defineAsRequiredField("itemCd" + i);

			var detailCount = $F(prefixItem + "detailCount");
			for(var j = 0 ; j < detailCount; j++){
				<%-- 必須フィールド定義 --%>
				defineAsRequiredField("strShippingInstruction" + i + "_" + j);

				<%-- 数値型フィールド定義 --%>
				defineAsNumberField("strShippingInstruction" + i + "_" + j);
			}

			<%-- 指図量累計の計算 --%>
			calcSum(i);

			<%-- 品目コードのauto complete --%>
			autocompleteItemCd(i);
			Event.observe('itemCd' + i, 'keypress', clearLabel.bindAsEventListener($('itemName' + i)), false);
			Event.observe('itemCd' + i, 'keypress', clearText.bindAsEventListener($('otherCompanyCd1' + i)), false);
			Event.observe('itemCd' + i, 'keypress', clearLabel.bindAsEventListener($('StyleOfPacking' + i)), false);

			<%-- 他社コード1のauto complete --%>
			autocompleteOtherCompanyCd1(i);
		}

		<%-- 納入先のauto complete --%>
		new Ajax.Autocompleter(
		  "deliveryCd",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>//ShippingDeliveryForAutoComplete.do?op=searchShippingDelivery",
		  {
		  	paramName : "code",
		    afterUpdateElement : setDeliveryCdLabel
		  }
		);
		Event.observe('deliveryCd',  'keypress', clearText.bindAsEventListener($('deliveryName')), false);

		<%-- 検索後入力された場合の不整合をただす --%>
		storeInitValues(/^srh.*/, 'dirtyFlg');
		refreshLabel();

	}, false);

	<%-- 初期フォーカス位置をセット --%>
	function initFocus(){
		var id = $F("focusId");
		if (id != null && id != "" && $(id) != null) {
			$(id).focus();
		} else {
			$("strShippingInstructDate").focus();
		}
		$("focusId").value = "";
	}

	<%-- 品目のauto complete --%>
	function autocompleteItemCd(index){
		<%-- 品目名称のauto complete --%>
		new Ajax.Autocompleter(
		  "itemCd" + index,
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/ShippingItemForAutoComplete.do?op=searchDetailDigitItem",
		  {
		  	paramName : "code",
		    afterUpdateElement : eval("setItemLabel" + index)
		  }
		);
	}

	<%-- 他社コード1のauto complete --%>
	function autocompleteOtherCompanyCd1(index){
		<%-- 品目名称のauto complete --%>
		new Ajax.Autocompleter(
		  "otherCompanyCd1" + index,
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/ShippingItemForAutoComplete.do?op=searchDetailDigitItemOtherCompany",
		  {
		  	paramName : "code",
		    afterUpdateElement : eval("setOtherCompanyCdLabel" + index)
		  }
		);
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
	function calcSum(index){
		var prefix = "itemList[" + index + "].";
		var count = $F(prefix + "detailCount");
		if (count < 1) {
			$("strShippingInstructionSum" + index).value = "";
			return;
		}
		var sum = 0;
		for(var i = 0 ; i < count ; i++ ){
			sum += strToNum($F("strShippingInstruction" + index + "_" + i), 0);
		}

		<%-- 数値フォーマット --%>
		var res = digitFormat($F("decimalPoint" + index), $F("round" + index), sum);
		$("shippingInstructionSumDisp" + index).innerText = res;
		$("strShippingInstructionSum" + index).value = res
	}

	<%-- 変更フラグセット --%>
	function setDirtyFlg() {
		$("dirtyFlg").value = "true";
	}

	<%-- 空白チェック（null,全てスペースも空白と判断する) --%>
	function blank(s){
		var reg = new RegExp(/^\s*$/);
		return reg.test(s);
	}

	<%-- 確認メッセージ --%>
	function putConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "登録してよろしいですか？";

		return confirm(alertMsg[0]);
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

	<%-- 品目削除確認メッセージ --%>
	function putDelItemListConfirm() {
		var count = $F("itemCount");
		var flag = false;
		for(var i = 0 ; i < count ; i++){
			var chk = $F("itemList[" + i + "].checkFlg");
			if(chk){
				flag = true;
				break;
			}
		}
		if(flag){
			alertMsg = new Array();
			alertMsg[0] = "品目を削除してよろしいですか？";
			return confirm(alertMsg[0]);
		}else{
			alertMsg = new Array();
			alertMsg[0] = "品目が選択されていません。";
			alert(alertMsg[0]);
			return false;
		}

	}

	<%-- 品目インデックスセット --%>
	function setItemIndex(index) {
		$("itemIndex").value = index;
		return form_submit('op', 'addRow');
	}

	<%-- 行追加 --%>
	function addRowDetail(index) {
		$("itemIndex").value = index;
		return form_submit('op', 'addRow');
	}

	<%-- 行削除確認メッセージ --%>
	function putDelListConfirm(index) {
		var prefixItem = "itemList[" + index + "].";
		var count = $F(prefixItem + "detailCount");

		var flag = false;
		for(var i = 0 ; i < count ; i++){
			var chk = $F(prefixItem + "detailList[" + i + "].checkFlg");
			if(chk){
				flag = true;
				break;
			}
		}
		if(flag){
			alertMsg = new Array();
			alertMsg[0] = "行を削除してよろしいですか？";
			if (confirm(alertMsg[0])) {
				$("itemIndex").value = index;
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

	<%-- チェックを入れる --%>
	function setLine(itemIndex, lineIndex) {
		$("itemIndex").value = itemIndex;
		$("lineIndex").value = lineIndex;
	}

	<%-- 20211209 Asclab Kouchi EdgeIE対応 --%>
	<%-- ロット検索後の設定処理 --%>
	function setShippingOtherLotValues(shippingOtherLotFlg, locationCd, locationName, lotNo, strInventoryQty, strBackorderQty, strInspectionQty, strAssignQty, strValidInventory) {
		if (shippingOtherLotFlg) {
			var itemIndex = eval($("itemIndex").value)
			var lineIndex = eval($("lineIndex").value)
			$('locationCd' + itemIndex + '_' + lineIndex).value = locationCd;
			$('locationName' + itemIndex + '_' + lineIndex).value = locationName;
			$('lotNo' + itemIndex + '_' + lineIndex).value = lotNo;
			$('strInventoryQty' + itemIndex + '_' + lineIndex).value = strInventoryQty;
			$('strBackorderQty' + itemIndex + '_' + lineIndex).value = strBackorderQty;
			$('strInspectionQty' + itemIndex + '_' + lineIndex).value = strInspectionQty;
			$('strAssignQty' + itemIndex + '_' + lineIndex).value = strAssignQty;
			$('strValidInventory' + itemIndex + '_' + lineIndex).value = strValidInventory;

			$('lblLocationCd' + itemIndex + '_' + lineIndex).update($F('locationCd' + itemIndex + '_' + lineIndex));
			$('lblLocationName' + itemIndex + '_' + lineIndex).update($F('locationName' + itemIndex + '_' + lineIndex));
			$('lblLotNo' + itemIndex + '_' + lineIndex).update($F('lotNo' + itemIndex + '_' + lineIndex));
			$('lblInventoryQty' + itemIndex + '_' + lineIndex).update($F('strInventoryQty' + itemIndex + '_' + lineIndex));
			$('lblBackorderQty' + itemIndex + '_' + lineIndex).update($F('strBackorderQty' + itemIndex + '_' + lineIndex));
			$('lblInspectionQty' + itemIndex + '_' + lineIndex).update($F('strInspectionQty' + itemIndex + '_' + lineIndex));
			$('lblAssignQty' + itemIndex + '_' + lineIndex).update($F('strAssignQty' + itemIndex + '_' + lineIndex));
			$('lblValidInventory' + itemIndex + '_' + lineIndex).update($F('strValidInventory' + itemIndex + '_' + lineIndex));

			<%-- 指図量クリア --%>
			$('strShippingInstruction' + itemIndex + '_' + lineIndex).disabled = false;
			$('strShippingInstruction' + itemIndex + '_' + lineIndex).value = "";
			$('strShippingInstructionPrev' + itemIndex + '_' + lineIndex).value = "";
		}
	}

	<%-- 納入先auto completeの選択後処理 --%>
	function setDeliveryCdLabel(text, li) {
	    $("deliveryName1").value = li.getElementsByTagName('span')[0].innerText;
		$("carryCd").value = getHiddenValue(li,"carryCd");
		$("tempCarryCd").value = getHiddenValue(li,"carryCd");
		return form_submit('op', 'selectedDelivery');
	}

	<logic:iterate id="itemList" name="shippingDetailOtherForm" property="itemList" indexId="index">
		<%-- 品目auto completeの選択後処理(品目名称,他社コード1に選択された情報を設定) --%>
		function setItemLabel<bean:write name='index'/>(text, li) {
	    	$("otherCompanyCd1<bean:write name='index'/>").value = getHiddenValue(li,"otherCompanyCd1");
		    $("lblItemName<bean:write name='index'/>").update(li.getElementsByTagName('span')[0].innerText);
		    $("lblStyleOfPacking<bean:write name='index'/>").update(getHiddenValue(li,"styleOfPacking"));

			<%-- hidden項目 --%>
	    	$("itemName<bean:write name='index'/>").value = li.getElementsByTagName('span')[0].innerText;
		    $("styleOfPacking<bean:write name='index'/>").value = getHiddenValue(li,"styleOfPacking");
		    $("unitDivision<bean:write name='index'/>").value = getHiddenValue(li,"unitOfOperationManagement");

		    calcSum(<bean:write name='index'/>);

			<%-- 行追加 --%>
			$("focusId").value = "btnSearchLocation" + <bean:write name='index'/> + "_" + "0";
			addRowDetail(<bean:write name='index'/>);
		}

		<%-- 他社コードauto completeの選択後処理(品目コード,品目名称に選択された情報を設定) --%>
		function setOtherCompanyCdLabel<bean:write name='index'/>(text, li) {
	    	$("itemCd<bean:write name='index'/>").value = getHiddenValue(li,"code");
		    $("lblItemName<bean:write name='index'/>").update(li.getElementsByTagName('span')[0].innerText);
		    $("lblStyleOfPacking<bean:write name='index'/>").update(getHiddenValue(li,"styleOfPacking"));

			<%-- hidden項目 --%>
	    	$("itemName<bean:write name='index'/>").value = li.getElementsByTagName('span')[0].innerText;
		    $("styleOfPacking<bean:write name='index'/>").value = getHiddenValue(li,"styleOfPacking");

		    calcSum(<bean:write name='index'/>);

			<%-- 行追加 --%>
			$("focusId").value = "btnSearchLocation" + <bean:write name='index'/> + "_" + "0";
			addRowDetail(<bean:write name='index'/>);
		}
	</logic:iterate>
	<%-- 指図量のフォーマット--%>
	function formatShippingInstruction(obj, itemIndex, lineIndex){
		var value = obj.value;
		if (value == null || value == "") {
			return;
		}

		<%-- 数値フォーマット --%>
		var res = digitStringFormat($F("decimalPoint" + itemIndex), $F("round" + itemIndex), value);
		if (res != "") {
			$("strShippingInstruction" + itemIndex + "_" + lineIndex).value = res;
		}
	}

</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/ShippingDetailOther" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="dirtyFlg" />
	<nested:hidden property="itemIndex" styleId="itemIndex" />
	<nested:hidden property="itemCount" styleId="itemCount" />
	<nested:hidden property="lineIndex" styleId="lineIndex" />
	<nested:hidden property="focusId" styleId="focusId" />
	<nested:hidden property="tempCarryCd" styleId="tempCarryCd" />

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
					<td><%-- 明細部 --%>
						<table border="0" cellspacing="" cellpadding="" width="100%">
							<tr>
								<td>
									<table border="0" cellspacing="2" cellpadding="1">
										<tr>
											<td class="bcTitleSearch fb fcTitleSearch">出荷番号</td>
											<td colspan="3">
												<nested:text property="shippingNo" readonly="true" size="12" styleId="shippingNo" styleClass="noborderAl" tabindex="-1"/>
											</td>
										</tr>
										<tr>
											<td class="bcTitleSearch fb fcTitleSearch">受注番号</td>
											<td colspan="3">
												<nested:text property="orderNo" readonly="true" size="12" styleId="orderNo" styleClass="noborderAl" tabindex="-1"/>
											</td>
										</tr>
										<tr>
											<td class="bcTitleSearch fb fcTitleSearch">受注行番号</td>
											<td colspan="3">
												<nested:text property="orderRowNo" readonly="true" size="12" styleId="orderRowNo" styleClass="noborderAl" tabindex="-1"/>
											</td>
										</tr>
										<tr>
											<td class="bcTitleSearch fb fcTitleSearch">ステータス</td>
											<td colspan="3">
												<nested:text property="shippingStatusName" readonly="true" size="12" styleId="shippingStatusName" styleClass="noborderAl" tabindex="-1"/>
											</td>
										</tr>
										<tr>
											<td class="bcTitleSearch fb fcTitleSearch">出荷指図日</td>
											<td><nested:text property="strShippingInstructDate" maxlength="10" size="12" styleId="strShippingInstructDate" onchange="setDirtyFlg();" style="ime-mode:disabled" /></td>
										</tr>
										<tr>
											<td class="bcTitleSearch fb fcTitleSearch">納入先</td>
											<td>
												<nested:text property="deliveryCd" maxlength="10" size="12" styleId="deliveryCd" onchange="setDirtyFlg();" />
												<div id="autocomplete_selection" class="autocomplete"></div>
											</td>
											<td>
												<nested:text property="deliveryName1" size="50" readonly="true" styleId="deliveryName1" styleClass="noborderAl" tabindex="-1"/>
											</td>
										</tr>
										<tr>
											<td class="bcTitleSearch fb fcTitleSearch">希望納期</td>
											<td><nested:text property="strSuggestedDeliverlimit" maxlength="10" size="12" styleId="strSuggestedDeliverlimit" onchange="setDirtyFlg();" style="ime-mode:disabled" /></td>
										</tr>
										<tr>
											<td class="bcTitleSearch fb fcTitleSearch">出荷予定日</td>
											<td><nested:text property="strScheduledShippingDate" maxlength="10" size="12" styleId="strScheduledShippingDate" onchange="setDirtyFlg();" style="ime-mode:disabled" /></td>
										</tr>
										<tr>
											<td class="bcTitleSearch fb fcTitleSearch">運送会社コード</td>
											<td colspan="3">
												<nested:select property="carryCd" style="margin: 0;padding: 0" styleId="carryCd" onchange="setDirtyFlg();" >
													<nested:optionsCollection property="carryCombo" label="labales" value="values" />
												</nested:select>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<nested:notEqual property="insertFlg" value="0">
								<tr>
									<td>
										<div id="cssButton">
											<a href="#"
												onclick="return form_submit('op', 'addItemRow'); return false;"
												class="cssButton">品目追加</a>
											&nbsp;&nbsp;&nbsp;&nbsp;
											<a href="#"
												onclick="if (!(putDelItemListConfirm())) {return false;}else{return form_submit('op', 'delItemRow'); return false;}"
												class="cssButton">品目削除</a>
										</div>
									</td>
								</tr>
							</nested:notEqual>
							<tr height="2">
								<td></td>
							</tr>
							<tr>
								<td class="bcTitleLine" colspan="2"></td>
							</tr>
							<tr>
								<td><%-- 品目明細部 --%>
									<nested:iterate id="itemList" property="itemList" indexId="index">
										<table width="100%" cellspacing="0" cellpadding="1" border="0" >
											<tr height="5">
												<td></td>
											</tr>
										</table>
										<table cellspacing="2" cellpadding="1" id="itemTblList">
											<tr class="bcTitleList fb fcTitleList">
												<td></td>
												<td>品目ｺｰﾄﾞ</td>
												<td>他社ｺｰﾄﾞ</td>
												<td>品目名称</td>
												<td>荷姿</td>
												<td>指図量累計</td>
											</tr>
											<%-- 明細部 >>>>> --%>
											<tr class="bcList2">
												<td><%-- 削除チェックボックス --%>
													<nested:checkbox property="checkFlg" styleId="checkFlg" />
												</td>
												<nested:greaterThan property="detailCount" value="0">
													<td><%-- 品目コード --%>
														<nested:text property="itemCd" maxlength="20" size="10" styleId="<%="itemCd" + pageContext.findAttribute("index").toString() %>" disabled="true" />
													</td>
													<td width="100"><%-- 他社コード --%>
														<nested:text property="otherCompanyCd1" maxlength="20" size="10" styleId="<%="otherCompanyCd1" + pageContext.findAttribute("index").toString() %>" disabled="true" />
													</td>
												</nested:greaterThan>
												<nested:equal property="detailCount" value="0">
													<td><%-- 品目コード --%>
														<nested:text property="itemCd" maxlength="20" size="10" styleId="<%="itemCd" + pageContext.findAttribute("index").toString() %>" onchange="setDirtyFlg();" />
													</td>
													<td width="100"><%-- 他社コード --%>
														<nested:text property="otherCompanyCd1" maxlength="20" size="10" styleId="<%="otherCompanyCd1" + pageContext.findAttribute("index").toString() %>" onchange="setDirtyFlg();" />
													</td>
												</nested:equal>
												<td width="200"><%-- 品目名称 --%>
													<div id="<%="lblItemName" + pageContext.findAttribute("index").toString() %>" >
														<nested:write property="itemName" />
													</div>
													<nested:hidden property="itemName" styleId='<%="itemName" + pageContext.findAttribute("index").toString() %>' />
												</td>
												<td width="150"><%-- 荷姿 --%>
													<div id="<%="lblStyleOfPacking" + pageContext.findAttribute("index").toString() %>" >
														<nested:write property="styleOfPacking" />
													</div>
													<nested:hidden property="styleOfPacking" styleId='<%="styleOfPacking" + pageContext.findAttribute("index").toString() %>' />
												</td>
												<td class="alignRight" id="<%="shippingInstructionSumDisp" + pageContext.findAttribute("index").toString() %>" ><%-- 指図量累計 --%>
													<nested:write property="strShippingInstructionSum" />
												</td>
											</tr>
										</table>
										<nested:hidden property="strShippingInstructionSum" styleId='<%="strShippingInstructionSum" + pageContext.findAttribute("index").toString() %>' />
										<nested:hidden property="unitDivision" styleId='<%="unitDivision" + pageContext.findAttribute("index").toString() %>' />
										<nested:hidden property="decimalPoint" styleId='<%="decimalPoint" + pageContext.findAttribute("index").toString() %>' />
										<nested:hidden property="round" styleId='<%="round" + pageContext.findAttribute("index").toString() %>' />
										<%-- 明細部 --%>
										<nested:hidden property="detailCount" styleId="detailCount" />
										<nested:lessEqual name="shippingDetailOtherForm" property="shippingStatus" value="1">
											<table cellspacing="0" cellpadding="1" border="0" >
												<tr>
													<td></td>
												</tr>
												<tr>
													<td>
														<div id="cssButton">
															&nbsp;&nbsp;&nbsp;&nbsp;
															<a href="#"
																onclick="addRowDetail(<%=pageContext.findAttribute("index").toString()%>); return false;"
																class="cssButton">&nbsp;行&nbsp;追&nbsp;加&nbsp;</a>
                                	    		            &nbsp;&nbsp;&nbsp;&nbsp;
															<a href="#"
																onclick="if (!(putDelListConfirm(<%=pageContext.findAttribute("index").toString()%>))) {return false;}else{return form_submit('op', 'delRow'); return false;}"
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
																	   id="<%="btnSearchLocation" + pageContext.findAttribute("index").toString() + "_" + pageContext.findAttribute("detailIndex").toString() %>"
																	   onclick="<%="setLine(" + pageContext.findAttribute("index").toString() + "," + pageContext.findAttribute("detailIndex").toString() + ");open_modal_popup_edge(730, 600,'ShippingOtherLotSearch', '', 'srhItemCd', $('itemCd" + pageContext.findAttribute("index").toString() + "').value, 'srhLocationCd', $('locationCd" + pageContext.findAttribute("index").toString()  + "_" + pageContext.findAttribute("detailIndex").toString() + "').value, 'srhLocationName', $('locationName" + pageContext.findAttribute("index").toString()  + "_" + pageContext.findAttribute("detailIndex").toString() + "').value, 'venderDivision', '', 'venderCd', ''); setDirtyFlg(); return false;"%>">
																		選
																	</a>
																</div>
															</td>
															<td><%-- ロケーションコード --%>
																<div id="<%="lblLocationCd" + pageContext.findAttribute("index").toString() + "_" + pageContext.findAttribute("detailIndex").toString() %>">
																	<nested:write property="locationCd" />
																</div>
																<nested:hidden property="locationCd" styleId="<%="locationCd" + pageContext.findAttribute("index").toString() + "_" + pageContext.findAttribute("detailIndex").toString() %>" />
															</td>
															<td width="150"><%-- ロケーション名称 --%>
																<div id="<%="lblLocationName" + pageContext.findAttribute("index").toString() + "_" + pageContext.findAttribute("detailIndex").toString() %>">
																	<nested:write property="locationName" />
																</div>
																<nested:hidden property="locationName" styleId="<%="locationName" + pageContext.findAttribute("index").toString() + "_" + pageContext.findAttribute("detailIndex").toString() %>" />
															</td>
															<td><%-- ロット番号 --%>
																<div id="<%="lblLotNo" + pageContext.findAttribute("index").toString() + "_" + pageContext.findAttribute("detailIndex").toString() %>">
																	<nested:write property="lotNo" />
																</div>
																<nested:hidden property="lotNo" styleId="<%="lotNo" + pageContext.findAttribute("index").toString() + "_" + pageContext.findAttribute("detailIndex").toString() %>" />
															</td>
															<td class="alignRight"><%-- 指図量 --%>
																<nested:notEmpty property="locationCd">
																	<nested:text property="strShippingInstruction" maxlength="22" size="5"
																	styleId="<%="strShippingInstruction" + pageContext.findAttribute("index").toString() + "_" + pageContext.findAttribute("detailIndex").toString() %>"
																	onchange="<%="setDirtyFlg(); formatShippingInstruction(this," + pageContext.findAttribute("index").toString() + "," + pageContext.findAttribute("detailIndex").toString() +"); calcSum(" + pageContext.findAttribute("index").toString() + ");"%>"
																	style="ime-mode:disabled" />
																</nested:notEmpty>
																<nested:empty property="locationCd">
																	<nested:text property="strShippingInstruction" maxlength="22" size="5"
																	styleId="<%="strShippingInstruction" + pageContext.findAttribute("index").toString() + "_" + pageContext.findAttribute("detailIndex").toString() %>"
																	onchange="<%="setDirtyFlg(); formatShippingInstruction(this," + pageContext.findAttribute("index").toString() + "," + pageContext.findAttribute("detailIndex").toString() +"); calcSum(" + pageContext.findAttribute("index").toString() + ");"%>"
																	disabled="true" style="ime-mode:disabled" />
																</nested:empty>
															</td>
															<td class="alignRight"><%-- 在庫量 --%>
																<div id="<%="lblInventoryQty" + pageContext.findAttribute("index").toString() + "_" + pageContext.findAttribute("detailIndex").toString() %>">
																	<nested:write property="strInventoryQty" />
																</div>
																<nested:hidden property="strInventoryQty" styleId="<%="strInventoryQty" + pageContext.findAttribute("index").toString() + "_" + pageContext.findAttribute("detailIndex").toString() %>" />
															</td>
															<td class="alignRight"><%-- 発注残 --%>
																<div id="<%="lblBackorderQty" + pageContext.findAttribute("index").toString() + "_" + pageContext.findAttribute("detailIndex").toString() %>">
																	<nested:write property="strBackorderQty" />
																</div>
																<nested:hidden property="strBackorderQty" styleId="<%="strBackorderQty" + pageContext.findAttribute("index").toString() + "_" + pageContext.findAttribute("detailIndex").toString() %>" />
															</td>
															<td class="alignRight"><%-- 検査待 --%>
																<div id="<%="lblInspectionQty" + pageContext.findAttribute("index").toString() + "_" + pageContext.findAttribute("detailIndex").toString() %>">
																	<nested:write property="strInspectionQty" />
																</div>
																<nested:hidden property="strInspectionQty" styleId="<%="strInspectionQty" + pageContext.findAttribute("index").toString() + "_" + pageContext.findAttribute("detailIndex").toString() %>" />
															</td>
															<td class="alignRight"><%-- 引当残 --%>
																<div id="<%="lblAssignQty" + pageContext.findAttribute("index").toString() + "_" + pageContext.findAttribute("detailIndex").toString() %>">
																	<nested:write property="strAssignQty" />
																</div>
																<nested:hidden property="strAssignQty" styleId="<%="strAssignQty" + pageContext.findAttribute("index").toString() + "_" + pageContext.findAttribute("detailIndex").toString() %>" />
															</td>
															<td class="alignRight"><%-- 有効在庫 --%>
																<div id="<%="lblValidInventory" + pageContext.findAttribute("index").toString() + "_" + pageContext.findAttribute("detailIndex").toString() %>">
																	<nested:write property="strValidInventory" />
																</div>
																<nested:hidden property="strValidInventory" styleId="<%="strValidInventory" + pageContext.findAttribute("index").toString() + "_" + pageContext.findAttribute("detailIndex").toString() %>" />
																<nested:hidden property="strShippingInstructionPrev" styleId="<%="strShippingInstructionPrev" + pageContext.findAttribute("index").toString() + "_" + pageContext.findAttribute("detailIndex").toString() %>" />
															</td>
														</tr>
												</nested:iterate>
											</table>
											<table width="100%" cellspacing="0" cellpadding="1" border="0" >
												<tr height="5">
													<td></td>
												</tr>
											</table>
										</nested:notEmpty>
										<table width="100%" cellspacing="0" cellpadding="1" border="0" >
											<tr height="3">
												<td></td>
											</tr>
										</table>
										<table width="100%" cellspacing="0" cellpadding="1" border="0" >
											<tr>
												<td class="bcTitleLine"></td>
											</tr>
										</table>
									</nested:iterate>
								</td>
							</tr>
						</table>
					</td>
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

</nested:form>
</body>
</html:html>
