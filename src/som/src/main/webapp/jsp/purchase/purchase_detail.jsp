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

		<%-- フォーカス設定(品目、他社コード、仕入先、数量、入力時のPOSTから帰ってきたとき) --%>
		if ( !blank($('focusPosition').value) ) {
			document.forms[0].elements[$F('focusPosition')].focus();
			$('focusPosition').value = "";
			mulOrderConvertQuantity();
		}
		<%-- 仕入先のauto complete --%>
		new Ajax.Autocompleter(
		  "venderCd",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/VenderForAutoComplete.do?op=searchVender",
		  {
		  	paramName : "code",
		  	parameters : 'venderDivision=SI',
		    afterUpdateElement : setVenderLabel
		  }
		);
		<%-- 名称のクリアauto complete --%>
		Event.observe('venderCd', 'keypress', clearDivLabel.bindAsEventListener($('supplierName')), false);
		Event.observe('venderCd', 'keypress', clearText.bindAsEventListener($('chargeOrganizationCd')), false);
		Event.observe('venderCd', 'keypress', clearDivLabel.bindAsEventListener($('tantoSectionNm')), false);
		Event.observe('venderCd', 'keypress', clearDivLabel.bindAsEventListener($('unitPriceUnit')), false);
	if($("insertFlg").value == "1"){
		<%-- 品目名称のauto complete --%>
		new Ajax.Autocompleter(
		  "itemCd",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/ItemForAutoComplete.do?op=searchItem",
		  {
		  	paramName : "code",
		    afterUpdateElement : setItemQueueLabel
		  }
		);
		<%-- 名称のクリアauto complete --%>
		Event.observe('itemCd', 'keypress', clearText.bindAsEventListener($('itemName')), false);
		Event.observe('itemCd', 'keypress', clearText.bindAsEventListener($('otherCompanyCd')), false);
		Event.observe('itemCd', 'keypress', clearDivLabel.bindAsEventListener($('styleOfPacking')), false);
		Event.observe('itemCd', 'keypress', clearDivLabel.bindAsEventListener($('orderUnit')), false);

		<%-- 他社コード１のauto complete --%>
		new Ajax.Autocompleter(
		  "otherCompanyCd",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/ItemForAutoComplete.do?op=searchItemOtherCompany",
		  {
		  	paramName : "code",
		    afterUpdateElement : setOtherCompany1Label
		  }
		);
		Event.observe('otherCompanyCd', 'keypress', clearText.bindAsEventListener($('itemName')), false);
		Event.observe('otherCompanyCd', 'keypress', clearText.bindAsEventListener($('itemCd')), false);
		Event.observe('otherCompanyCd', 'keypress', clearDivLabel.bindAsEventListener($('styleOfPacking')), false);
		Event.observe('otherCompanyCd', 'keypress', clearDivLabel.bindAsEventListener($('orderUnit')), false);
	}

		<%-- 担当部署のauto complete --%>
		new Ajax.Autocompleter(
		  "chargeOrganizationCd",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/OrganizationForAutoComplete.do?op=searchOrganization",
		  {
		  	paramName : "code",
		    afterUpdateElement : setChargeOrganizationLabel
		  }
		);
		<%-- 名称のクリアauto complete --%>
		Event.observe('chargeOrganizationCd', 'keypress', clearDivLabel.bindAsEventListener($('tantoSectionNm')), false);

		<%-- 納入先のauto complete --%>
		new Ajax.Autocompleter(
		  "locationCd",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/PurchaseDeliveryForAutoComplete.do?op=searchPurchaseDeliveryDetail",
		  {
		  	paramName : "code",
		    afterUpdateElement : setLocationLabel
		  }
		);
		<%-- 名称のクリアauto complete --%>
		Event.observe('locationCd', 'keypress', clearDivLabel.bindAsEventListener($('deliveryName')), false);

		<%-- 部署のauto complete --%>
		new Ajax.Autocompleter(
		  "organizationCd",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/OrganizationForAutoComplete.do?op=searchOrganization",
		  {
		  	paramName : "code",
		    afterUpdateElement : setOrganizationLabel
		  }
		);
		<%-- 名称のクリアauto complete --%>
		Event.observe('organizationCd', 'keypress', clearDivLabel.bindAsEventListener($('sectionName')), false);

		<%-- 発注者のauto complete --%>
		new Ajax.Autocompleter(
		  "tantoCd",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/LoginForAutoComplete.do?op=searchLogin",
		  {
		  	paramName : "code",
		    afterUpdateElement : setLoginLabel
		  }
		);
		<%-- 名称のクリアauto complete --%>
		Event.observe('tantoCd', 'keypress', clearText.bindAsEventListener($('tantoNm')), false);


		<%-- 必須フィールド定義 --%>
		defineAsRequiredField("strOrderDate");
	if($("insertFlg").value == "1"){
		defineAsRequiredField("itemCd");
	}
		defineAsRequiredField("venderCd");
		defineAsRequiredField("strOrderQuantity");
		defineAsRequiredField("chargeOrganizationCd");
		defineAsRequiredField("strSuggestedDeliverlimitDate");
		defineAsRequiredField("locationCd");
		defineAsRequiredField("organizationCd");
		defineAsRequiredField("tantoCd");
		defineAsRequiredField("strOrderUnitprice");

		<%-- 日付型フィールド定義 --%>
		defineAsDateField("strOrderDate");
		defineAsDateField("strSuggestedDeliverlimitDate");
		defineAsTimeField("strSuggestedDeliverlimitTime");

		<%-- 数値型フィールド定義 --%>
		defineAsNumberField("strOrderQuantity");
		defineAsNumberField("strOrderUnitprice");

		<%-- 検索後入力された場合の不整合をただす --%>

		storeInitValues(/^srh.*/, 'dirtyFlg');
		refreshLabel();

	}, false);



	<%-- 品目名称auto completeの選択後処理 --%>
	function setItemQueueLabel(text, li) {
	    $("itemName").value = li.getElementsByTagName('span')[0].innerText;
	    $("otherCompanyCd").value = getHiddenValue(li,"otherCompanyCd1");
	    <%-- 品目を変更した時に連動して変わるデータを画面にセット --%>
	    setFocusPosition("itemCd");
	    form_submit('op', 'getItemRelatedData');
	}
	<%-- 他社コード１auto completeの選択後処理 --%>
	function setOtherCompany1Label(text, li) {
	    $("itemName").value = li.getElementsByTagName('span')[0].innerText;
	    $("itemCd").value = getHiddenValue(li,"code");
	    <%-- 品目を変更した時に連動して変わるデータを画面にセット --%>
		setFocusPosition("otherCompanyCd");
	    form_submit('op', 'getItemRelatedData');
	}
	<%-- 仕入先auto completeの選択後処理 --%>
	function setVenderLabel(text, li) {
	    $("supplierName").update(li.getElementsByTagName('span')[0].innerText);
	    <%-- 仕入先を変更した時に連動して変わるデータを画面にセット --%>
	    setFocusPosition("venderCd");
	    form_submit('op', 'getVenderRelatedData');
	}
	<%-- 担当部署auto completeの選択後処理 --%>
	function setChargeOrganizationLabel(text, li) {
	    $("tantoSectionNm").update(li.getElementsByTagName('span')[0].innerText);
	}
	<%-- 納入先auto completeの選択後処理 --%>
	function setLocationLabel(text, li) {
	    $("deliveryName").update( li.getElementsByTagName('span')[0].innerText);
	}
	<%-- 部署auto completeの選択後処理 --%>
	function setOrganizationLabel(text, li) {
	    $("sectionName").update( li.getElementsByTagName('span')[0].innerText);
	}
	<%-- 発注者auto completeの選択後処理 --%>
	function setLoginLabel(text, li) {
	    $("tantoNm").value = li.getElementsByTagName('span')[0].innerText;
	}

	<%-- 確認メッセージ --%>
	function putConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "登録してよろしいですか？";

		return confirm(alertMsg[0]);
	}

	function deleteAlert() {
		alertMsg = new Array();
		alertMsg[0] = "削除してよろしいですか？";

		return confirm(alertMsg[0]);
	}

	<%-- 変更フラグセット --%>
	function setDirtyFlg() {
		$("dirtyFlg").value = "true";
	}



	<%-- 変更フラグセット --%>
<%-- これは使わない
		function putDirtyConfirm() {
		var flg = $("dirtyFlg").value;
		if (flg == "true") {
			// 何か値が変更されている場合
			return continueConfirm();
		}else{
			return true;
		}
	}--%>

	<%-- 変更有の確認メッセージ --%>
	function putDirtyConfirmIssue() {
		var flg = $("dirtyFlg").value;
		if (flg == "true" && isEdited()) {
			// 何か値が変更されている場合
			alertMsg = new Array();
			alertMsg[0] = "画面の編集内容は反映されません、よろしいですか？";
			return confirm(alertMsg[0]);
		}else{
			return true;
		}
	}

	<%-- 購買ステータスが1:登録済みが選択されているか、チェック --%>
		function updateStatusCheck() {
		var sts = $("cboStatus").value;
		if (sts == "7") {
			<%-- 購買ステータス更新する --%>
			return true;
		}else{
			return false;
		}
	}


	<%-- 重量を計算(発注数量*KG換算係数（在庫）) --%>
		function mulOrderConvertQuantity(){
		mulOrderQuantity();	<%-- 数量のフォーマット編集 --%>
		var strOrderQuantity = $("strOrderQuantity").value;
		var kgOfFractionManagement = $("kgOfFractionManagement").value;

		if( !blank(strOrderQuantity)  &&  !blank(kgOfFractionManagement)){
			var strOrderQuantity2 = parseFloat(strOrderQuantity.replace(/,/g, ""));
			var kgOfFractionManagement2 = parseFloat(kgOfFractionManagement.replace(/,/g, ""));

			if( !isNaN(strOrderQuantity2) && !isNaN(kgOfFractionManagement2) ){
				var sum = strOrderQuantity2 * kgOfFractionManagement2;

				<%-- 桁数丸め部品呼び出し：重量 --%>
				var buf1 = digitFormat($F("strOrderConvertQuantityDecimalPoint"),$F("strOrderConvertQuantityRound"),sum);
				$("strOrderConvertQuantity").update(formatNumber(String(buf1)));
			}
		}
	}


		<%-- 金額を計算 --%>
		<%-- 個あたり:単価*数量 --%>
		<%-- Kgあたり:単価*数量*(品目M)Kg換算係数(在庫) --%>
		function mulSupplierOrdAmount(){
		mulOrderUnitprice();	<%-- 単価のフォーマット編集 --%>
		var unitpriceDefineunit = $("unitpriceDefineunit").value;	<%-- 単価区分 --%>
		var strOrderQuantity = $("strOrderQuantity").value;			<%-- 数量 --%>
		var strOrderUnitprice = $("strOrderUnitprice").value;		<%-- 単価 --%>
		var kgOfFractionManagement = $("kgOfFractionManagement").value;		<%-- (品目M)Kg換算係数(在庫) --%>


		<%-- 個あたり:1 --%>
		if (unitpriceDefineunit == "1" || unitpriceDefineunit == "") {
			if( !blank(strOrderQuantity)  &&  !blank(strOrderUnitprice)){
				var strOrderQuantity2 = parseFloat(strOrderQuantity.replace(/,/g, ""));
				var strOrderUnitprice2 = parseFloat(strOrderUnitprice.replace(/,/g, ""));
				if( !isNaN(strOrderQuantity2) && !isNaN(strOrderUnitprice2)){
					var sum = multiFloat(strOrderUnitprice,strOrderQuantity2,$F("strOrderUnitpriceDecimalPoint"));
					<%-- 桁数丸め部品呼び出し：金額 --%>
					var buf1 = digitFormat($F("strSupplierOrdAmountDecimalPoint"),$F("strSupplierOrdAmountRound"),sum);
					$("labelSupplierOrdAmount").update(formatNumber(String(buf1)));
					$("strSupplierOrdAmount").value = formatNumber(String(buf1));
				}
			}
		}
		<%-- Kgあたり:2 --%>
		if (unitpriceDefineunit == "2") {
			if( !blank(strOrderQuantity)  &&  !blank(strOrderUnitprice) &&  !blank(kgOfFractionManagement)){
				var strOrderQuantity2 = parseFloat(strOrderQuantity.replace(/,/g, ""));
				var strOrderUnitprice2 = parseFloat(strOrderUnitprice.replace(/,/g, ""));
				var kgOfFractionManagement2 = parseFloat(kgOfFractionManagement.replace(/,/g, ""));
				if( !isNaN(strOrderQuantity2) && !isNaN(strOrderUnitprice2) ){
					var tf = strOrderQuantity2 * kgOfFractionManagement2;
					var sum = multiFloat(strOrderUnitprice,tf,$F("strOrderUnitpriceDecimalPoint"));
					<%-- 桁数丸め部品呼び出し：金額 --%>
					var buf1 = digitFormat($F("strSupplierOrdAmountDecimalPoint"),$F("strSupplierOrdAmountRound"),sum);
					$("labelSupplierOrdAmount").update(formatNumber(String(buf1)));
					$("strSupplierOrdAmount").value = formatNumber(String(buf1));
				}
			}
		}
	}
		<%-- 数量を数値フォーマットにする --%>
		function mulOrderQuantity(){
		var strOrderQuantity = $("strOrderQuantity").value;
		if( !blank(strOrderQuantity) ) {
			var strOrderQuantity2 = parseFloat(strOrderQuantity.replace(/,/g, ""));
			<%-- 桁数丸め部品呼び出し：数量 --%>
			var buf1 = digitFormat($F("strOrderQuantityDecimalPoint"),$F("strOrderQuantityRound"),strOrderQuantity2);
			$("strOrderQuantity").value = buf1;
		}
	}
		<%-- 単価を数値フォーマットにする --%>
		function mulOrderUnitprice(){
		var strOrderUnitprice = $("strOrderUnitprice").value;
		if( !blank(strOrderUnitprice) ) {
			var strOrderUnitprice2 = parseFloat(strOrderUnitprice.replace(/,/g, ""));
			<%-- 桁数丸め部品呼び出し：数量 --%>
			var buf1 = digitFormat($F("strOrderUnitpriceDecimalPoint"),$F("strOrderUnitpriceRound"),strOrderUnitprice2);
			$("strOrderUnitprice").value = buf1;
		}
	}

		<%-- フォーカス位置の保存 --%>
		function setFocusPosition(pos){
		$("focusPosition").value = pos;
	}

	<%-- 空白チェック（null,全てスペースも空白と判断する) --%>
	function blank(s){
		var reg = new RegExp(/^\s*$/);
		return reg.test(s);
	}


</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/PurchaseDetail" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="dirtyFlg" />
	<nested:hidden property="insertFlg" />
	<%-- KG換算係数（在庫） --%><nested:hidden property="kgOfFractionManagement" styleId="kgOfFractionManagement" />
	<%-- 運用管理単位  validation-xmlで使用--%><nested:hidden property="unitOfOperationManagement" styleId="unitOfOperationManagement" />
	<%-- 購買ステータス --%><nested:hidden property="status" styleId="status" />
	<%-- 単価単位  (仕入別単価マスタの  金額計算で必要)--%><nested:hidden property="unitpriceDefineunit" styleId="unitpriceDefineunit" />
	<%-- スポット品区分 --%><nested:hidden property="spotDivision" styleId="spotDivision" />
	<%-- 小数点位置：発注数量 --%><nested:hidden property="strOrderQuantityDecimalPoint" styleId="strOrderQuantityDecimalPoint" />
	<%-- 小数点位置：重量 --%><nested:hidden property="strOrderConvertQuantityDecimalPoint" styleId="strOrderConvertQuantityDecimalPoint" />
	<%-- 小数点位置：単価 --%><nested:hidden property="strOrderUnitpriceDecimalPoint" styleId="strOrderUnitpriceDecimalPoint" />
	<%-- 小数点位置：金額 --%><nested:hidden property="strSupplierOrdAmountDecimalPoint" styleId="strSupplierOrdAmountDecimalPoint" />
	<%-- 端数区分：発注数量 --%><nested:hidden property="strOrderQuantityRound" styleId="strOrderQuantityRound" />
	<%-- 端数区分：重量 --%><nested:hidden property="strOrderConvertQuantityRound" styleId="strOrderConvertQuantityRound" />
	<%-- 端数区分：単価 --%><nested:hidden property="strOrderUnitpriceRound" styleId="strOrderUnitpriceRound" />
	<%-- 端数区分：金額 --%><nested:hidden property="strSupplierOrdAmountRound" styleId="strSupplierOrdAmountRound" />
	<%-- フォーカス位置保存 --%><nested:hidden property="focusPosition" styleId="focusPosition" />
	<%-- 金額 --%><nested:hidden property="strSupplierOrdAmount" styleId="strSupplierOrdAmount" />

	<table cellpadding="0" cellspacing="0" border="0" width="750">
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
									<td class="fcTitle fb" width="250">
										<nested:equal property="insertFlg" value="1">
										発注新規入力
										</nested:equal>
										<nested:equal property="insertFlg" value="0">
										発注（参照・修正・削除）
										</nested:equal>
									</td>
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
							<table border="0" cellspacing="2" cellpadding="1" width="100%">
								<tr>
									<td>
										<table border="0" cellspacing="1" cellpadding="1">
											<nested:notEqual property="insertFlg" value="0">
												<tr>
													<td class="fcTitleDetail fb bcTitleDetail" width="105">発注番号</td>
													<td colspan="5">
														<nested:write property="buySubcontractOrderNo" />
													</td>
												</tr>
												<tr>
													<td class="fcTitleDetail fb bcTitleDetail" width="105">発注日</td>
													<td colspan="2"><nested:text property="strOrderDate" maxlength="8" size="15" styleId="strOrderDate" style="ime-mode:disabled" /></td>
												</tr>
											</nested:notEqual>
											<nested:notEqual property="insertFlg" value="1">
									 			<tr height="10">
													<td class="fcTitleDetail fb bcTitleDetail" width="105">発注番号</td>
													<td colspan="2">
														<nested:write property="buySubcontractOrderNo" />
													</td>
													<td class="fcTitleDetail fb bcTitleDetail" width="105">発注日</td>
													<td colspan="2"><nested:text property="strOrderDate" maxlength="8" size="12" styleId="strOrderDate" style="ime-mode:disabled" /></td>
													<td></td>
												</tr>
												<tr>
													<td class="fcTitleDetail fb bcTitleDetail">
														発注書Ｎｏ
													</td>
													<td colspan="5">
														<nested:write property="orderSheetNo" />
													</td>
												</tr>
											</nested:notEqual>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail">品目</td>
												<nested:equal property="insertFlg" value="1">
												<td><nested:text property="itemCd" maxlength="20" size="15" styleId="itemCd" onchange="setDirtyFlg();" /></td>
												<td colspan="4">
													<nested:equal property="spotDivision" value="2">
														<%-- スポット区分＝２、品目名称入力可 --%>
														<nested:text property="itemName" maxlength="40" size="56" styleId="itemName" onchange="setDirtyFlg();" />
													</nested:equal>
													<nested:notEqual property="spotDivision" value="2">
														<%-- スポット区分！＝２、品目名称入力不可 --%>
														<nested:text property="itemName" maxlength="40" size="56" readonly="true" styleId="itemName" styleClass="noborderAl" tabindex="-1"/>
													</nested:notEqual>
												</td>
												</nested:equal>
												<nested:notEqual property="insertFlg" value="1">
												<td><nested:text property="itemCd" maxlength="20" size="15" styleId="itemCd" readonly="true" /></td>
												<td colspan="4">
														<%-- スポット区分！＝２、品目名称入力不可 --%>
														<nested:text property="itemName" maxlength="40" size="56" readonly="true" styleId="itemName" styleClass="noborderAl" tabindex="-1"/>
												</td>
												</nested:notEqual>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail">他社コード１</td>
												<nested:equal property="insertFlg" value="1">
												<td><nested:text property="otherCompanyCd" maxlength="8" size="15" styleId="otherCompanyCd" onchange="setDirtyFlg();" /></td>
												</nested:equal>
												<nested:notEqual property="insertFlg" value="1">
												<td><nested:text property="otherCompanyCd" maxlength="8" size="15" styleId="otherCompanyCd" readonly="true" /></td>
												</nested:notEqual>
											</tr>

											<tr>
												<td class="fcTitleDetail fb bcTitleDetail">仕入先コード</td>
												<td><nested:text property="venderCd" maxlength="10" size="15" styleId="venderCd" onchange="setDirtyFlg();" /></td>

												<td colspan = 3>
													<!-- 仕入先名称 -->
													<div id = "supplierName">
													<nested:equal property="reducedTaxTargetFlg" value="1"><font color=red>【未申請業者等】</font></nested:equal>
													<nested:write property="supplierName" /></div>
													<%-- <nested:text property="supplierName" maxlength="40" size="10" readonly="true" styleId="supplierName" styleClass="noborderAl" tabindex="-1" /> --%>
													<div id="autocomplete_selection" class="autocomplete"></div>
												</td>
											</tr>

											<tr>
												<td class="fcTitleDetail fb bcTitleDetail">担当部署</td>
												<td><nested:text property="chargeOrganizationCd" maxlength="10" size="15" styleId="chargeOrganizationCd" onchange="setDirtyFlg();"  /></td>
												<td>
													<%--担当部署名称 --%>
													<div id="tantoSectionNm"><nested:write property="tantoSectionNm" /></div>
													<!--  nested:text property="tantoSectionNm" maxlength="40" size="10" readonly="true" styleId="tantoSectionNm" styleClass="noborderAl" tabindex="-1" / -->
												</td>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail">複数社購買</td>
												<td colspan="2">
													<div id="strMultiSupplierDivision">
														<nested:write property="strMultiSupplierDivision" />
													</div>
												</td>
												<td class="fcTitleDetail fb bcTitleDetail">支給品区分</td>
												<td colspan="2">
													<nested:write property="strSuppliedDoodsDivision" />
												</td>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail">発注数量</td>
												<td colspan="2">
													<nested:text property="strOrderQuantity" maxlength="22" size="15" styleId="strOrderQuantity" style="ime-mode:disabled" onchange="setDirtyFlg();setFocusPosition('strOrderUnitprice');form_submit('op', 'getOrderQuantityRelatedData');" />
													<%--単位--%>
													<span id="orderUnit"><nested:write property="orderUnit" /></span>
												</td>
												<td class="fcTitleDetail fb bcTitleDetail">荷姿</td>
												<td colspan="2">
													<div id="styleOfPacking"><nested:write property="styleOfPacking" /></div>
												</td>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail">発注重量</td>
												<td align="right">
<%-- 													<div id="labelOrderConvertQuantity"><nested:write property="strOrderConvertQuantity" /></div>--%>
													<div id="strOrderConvertQuantity"><nested:write property="strOrderConvertQuantity" /></div>
												</td>
												<td >Kg</td>
												<td class="fcTitleDetail fb bcTitleDetail">不足重量</td>
												<td align="right">
<%-- 													<div id="labelOrderConvertQuantity"><nested:write property="strOrderConvertQuantity" /></div>--%>
													<div id="strCheckQuantity"><nested:write property="strCheckQuantity" /></div>
												</td>
												<td colspan="2">Kg</td>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail">単価</td>
												<td colspan="5">
													<nested:text property="strOrderUnitprice" maxlength="22" size="15" styleId="strOrderUnitprice" style="ime-mode:disabled" onchange="setDirtyFlg();" onblur="mulSupplierOrdAmount();"  />
													<span id="unitPriceUnit"><nested:write property="unitPriceUnit" /></span>
												</td>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail">金額</td>
												<td align="right">
 													<div id="labelSupplierOrdAmount"><nested:write property="strSupplierOrdAmount" /></div>
<%--													<div id="strSupplierOrdAmount"><nested:write property="strSupplierOrdAmount" /></div>--%>
												</td>
												<td>円</td>
												<td class="fcTitleDetail fb bcTitleDetail">発注まとめ場所</td>
												<td><nested:write property="orderLocation" /></td>
												<td><%-- 発注まとめ場所名称 --%>
													<nested:write property="orderLocationName" />
												</td>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail">納品希望日時</td>
												<td colspan="2">
													<nested:text property="strSuggestedDeliverlimitDate" maxlength="8" size="15" styleId="strSuggestedDeliverlimitDate" style="ime-mode:disabled"  onchange="setDirtyFlg();"  />
													<nested:text property="strSuggestedDeliverlimitTime" maxlength="4" size="5" styleId="strSuggestedDeliverlimitTime" style="ime-mode:disabled"  onchange="setDirtyFlg();"  />
												</td>
												<td class="fcTitleDetail fb bcTitleDetail">納入先</td>
												<td><nested:text property="locationCd" maxlength="10" size="15" styleId="locationCd" onchange="setDirtyFlg();"  /></td>
												<td>
													<%-- 納入先名称 --%>
													<div id = "deliveryName"><nested:write property="deliveryName" /></div>
													<!--  nested:text property="deliveryName" maxlength="40" size="10" readonly="true" styleId="deliveryName" styleClass="noborderAl" tabindex="-1"/ -->
												</td>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail">部署コード</td>
												<td><nested:text property="organizationCd" maxlength="10" size="15" styleId="organizationCd"  onchange="setDirtyFlg();"  /></td>
												<td>
												<%-- 部署名称 --%>
													<div id="sectionName"><nested:write property="sectionName" /></div>
													<!-- nested:text property="sectionName" maxlength="40" size="10" readonly="true" styleId="sectionName" styleClass="noborderAl" tabindex="-1" / -->
												</td>
												<td class="fcTitleDetail fb bcTitleDetail">発注者</td>
												<td><nested:text property="tantoCd" maxlength="10" size="15" styleId="tantoCd" onchange="setDirtyFlg();"  /></td>
												<%-- 発注者名称 --%>
												<td>
													<nested:text property="tantoNm" maxlength="40" size="10" readonly="true" styleId="tantoNm" styleClass="noborderAl" tabindex="-1" />
												</td>
											</tr>
											<tr>
												<nested:notEqual property="insertFlg" value="1">
													<td class="fcTitleDetail fb bcTitleDetail">購買ステータス</td>
													<td colspan="2">
														<div id="strStatus">
															<nested:write property="strStatus" />
														</div>
													</td>
												</nested:notEqual>
												<nested:equal property="status" value="2">
													<td class="fcTitleDetail fb bcTitleDetail">ステータス変更</td>
													<td>
														<%
																			pageContext.setAttribute("selectPurchaseStatus",
																			new com.asahikaseieng.app.comboboxes.SelectPurchaseStatusPurchase(false, true, "2"));
														%>
														<nested:select property="cboStatus">
														<nested:options name="selectPurchaseStatus" property="values" labelName="selectPurchaseStatus" labelProperty="labels" />
														</nested:select>
													<td><div id="cssButton"><a href="#" onclick="if (!(updateStatusCheck())) {return false;}else{return form_submit('op', 'updateStatus'); return false;}" class="cssButton">
													ステータス変更
													</a></div></td>
												</nested:equal>
												<nested:equal property="status" value="3">
													<td class="fcTitleDetail fb bcTitleDetail">ステータス変更</td>
													<td>
														<%
																			pageContext.setAttribute("selectPurchaseStatus",
																			new com.asahikaseieng.app.comboboxes.SelectPurchaseStatusPurchase(false, true, "3"));
														%>
														<nested:select property="cboStatus">
														<nested:options name="selectPurchaseStatus" property="values" labelName="selectPurchaseStatus" labelProperty="labels"/>
														</nested:select>
													<td><div id="cssButton"><a href="#" onclick="if (!(updateStatusCheck())) {return false;}else{return form_submit('op', 'updateStatus'); return false;}" class="cssButton">
													ステータス変更
													</a></div></td>
												</nested:equal>
												<nested:equal property="status" value="4">
													<td class="fcTitleDetail fb bcTitleDetail">ステータス変更</td>
													<td>
														<%
																			pageContext.setAttribute("selectPurchaseStatus",
																			new com.asahikaseieng.app.comboboxes.SelectPurchaseStatusPurchase(false, true, "4"));
														%>
														<nested:select property="cboStatus">
														<nested:options name="selectPurchaseStatus" property="values" labelName="selectPurchaseStatus" labelProperty="labels"/>
														</nested:select>
													<td><div id="cssButton"><a href="#" onclick="if (!(updateStatusCheck())) {return false;}else{return form_submit('op', 'updateStatus'); return false;}" class="cssButton">
													ステータス変更
													</a></div></td>
												</nested:equal>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail">備考マスタ</td>
												<td><div id="cssButton"><a href="#" onclick="return form_submit('op', 'getRemarkList');" class="cssButton">
												備考取得
												</a></div></td>
											</tr>
										</table>

										<table  border="0" cellspacing="1" cellpadding="1" width="100%">
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail" width="105">発注書備考</td>
												<td><nested:textarea property="orderSheetRemark" cols="75" rows="3" styleId="orderSheetRemark"  onchange="setDirtyFlg();"  /></td>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail" width="105">備考</td>
												<td><nested:textarea property="remark" cols="75" rows="3" styleId="remark"  onchange="setDirtyFlg();"  /></td>
											</tr>
										</table>

									</td>
								</tr>
							</table>
						<tr>
							<td class="alignCenter">
								<table cellpadding="0" cellspacing="0" width="450" border="0" >
									<tr>
										<td colspan="3" height="2">
										</td>
									</tr>

									<tr>
										<nested:equal property="updateAuthority" value="true">
											<td class="alignCenter">
											<div id="cssButton">
												<nested:equal property="insertFlg" value="1">
												<a href="#" onclick="if (!(putConfirm())) {return false;}else{return form_submit('op', 'insert'); return false;}" class="cssButton">
												&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
												</a></nested:equal>
												<nested:equal property="status" value="1">
												<a href="#" onclick="if (!(putConfirm())) {return false;}else{return form_submit('op', 'update'); return false;}" class="cssButton">
												&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
												</a></nested:equal>
												<nested:equal property="status" value="7">
												<a href="#" onclick="if (!(putConfirm())) {return false;}else{return form_submit('op', 'update'); return false;}" class="cssButton">
												&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
												</a></nested:equal>
											</div>
											</td>
										</nested:equal>

										<nested:equal property="deleteAuthority" value="true">
											<nested:notEqual property="strOrderDivision" value="5">
												<td class="alignCenter">
												<div id="cssButton">
													<nested:equal property="status" value="1">
													<a href="#" onclick="if (!(deleteAlert())) {return false;}else{return form_submit('op', 'delete'); return false;}" class="cssButton">
													&nbsp;&nbsp;削&nbsp;&nbsp;除&nbsp;&nbsp;
													</a></nested:equal>
													<nested:equal property="status" value="7">
													<a href="#" onclick="if (!(deleteAlert())) {return false;}else{return form_submit('op', 'delete'); return false;}" class="cssButton">
													&nbsp;&nbsp;削&nbsp;&nbsp;除&nbsp;&nbsp;
													</a></nested:equal>
												</div></td>
											</nested:notEqual>
										</nested:equal>

										<td class="alignCenter">
										<div id="cssButton">
										<a href="#" onclick="if (!(putDirtyConfirmIssue())) {return false;}else{return form_submit('op', 'back'); return false;}" class="cssButton">
											&nbsp;&nbsp;戻&nbsp;&nbsp;る&nbsp;&nbsp;
										</a></div></td>


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
