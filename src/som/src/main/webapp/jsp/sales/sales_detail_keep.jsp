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
		initFocus();

		<%-- 必須フィールド定義 --%>
		defineAsRequiredField("strSalesDate");
		defineAsRequiredField("strAccountYears");
		defineAsRequiredField("itemCd");
		defineAsRequiredField("otherCompanyCd1");
		defineAsRequiredField("strSalesQuantity");
		defineAsRequiredField("strStandardUnitprice");
		defineAsRequiredField("accountDebitSectionCd");
		defineAsRequiredField("accountCreditSectionCd");
		defineAsRequiredField("debitTitleCd");
		defineAsRequiredField("creditTitleCd");
		defineAsRequiredField("packageDirectionNo");
		defineAsRequiredField("productLotno");

		<%-- 日付型フィールド定義 --%>
		defineAsDateField("strSalesDate");
		defineAsYMField("strAccountYears");

		<%-- 数値型フィールド定義 --%>
		defineAsNumberField("strSalesQuantity");
		defineAsNumberField("strStandardUnitprice");
		defineAsNumberField("strStandardDiscount");
		defineAsNumberField("strSpecialDiscount");

		<%-- 明細部のフィールド定義 --%>
		var locationCount = $F("locationCount");
		for(var i = 0 ; i < locationCount; i++){
			<%-- 必須フィールド定義 --%>
			defineAsRequiredField("strQty" + i);

			<%-- 数値型フィールド定義 --%>
			defineAsNumberField("strQty" + i);
		}

		<%-- 新規の場合のみ設定する --%>
		if ($F("insertFlg") == "1") {
			<%-- 品目のauto complete --%>
			new Ajax.Autocompleter(
			  "itemCd",
			  "autocomplete_selection",
			  "<%= request.getContextPath() %>/SalesItemForAutoComplete.do?op=searchItem",
			  {
			  	paramName : "code",
			  	parameters : 'deliveryCd='+ $('deliveryCd').value,
			    afterUpdateElement : setItemLabel
			  }
			);
			Event.observe('itemCd', 'keypress', clearLabel.bindAsEventListener($('itemName')), false);
			Event.observe('itemCd', 'keypress', clearText.bindAsEventListener($('otherCompanyCd1')), false);
			Event.observe('itemCd', 'keypress', clearLabel.bindAsEventListener($('styleOfPacking')), false);
			Event.observe('itemCd', 'keypress', clearText.bindAsEventListener($('strSalesQuantity')), false);
			Event.observe('itemCd', 'keypress', clearText.bindAsEventListener($('strStandardUnitprice')), false);
			Event.observe('itemCd', 'keypress', clearText.bindAsEventListener($('strStandardDiscount')), false);
			Event.observe('itemCd', 'keypress', clearText.bindAsEventListener($('strSpecialDiscount')), false);
			Event.observe('itemCd', 'keypress', clearLabel.bindAsEventListener($('strSalesAmount')), false);
			Event.observe('itemCd', 'keypress', clearLabel.bindAsEventListener($('strSalesAmount')), false);
			Event.observe('itemCd', 'keypress', clearCheckbox.bindAsEventListener($('tmpUnitpriceFlg')), false);
			Event.observe('itemCd', 'keypress', clearText.bindAsEventListener($('unitDivision')), false);
			Event.observe('itemCd', 'keypress', clearText.bindAsEventListener($('decimalPoint')), false);
			Event.observe('itemCd', 'keypress', clearText.bindAsEventListener($('round')), false);
			Event.observe('itemCd', 'keypress', clearText.bindAsEventListener($('itemCheckedFlag')), false);

			<%-- 他社コード１のauto complete --%>
			new Ajax.Autocompleter(
			  "otherCompanyCd1",
			  "autocomplete_selection",
			  "<%= request.getContextPath() %>/SalesItemForAutoComplete.do?op=searchItemOtherCompany",
			  {
			  	paramName : "code",
			  	parameters : 'deliveryCd='+ $('deliveryCd').value,
			    afterUpdateElement : setOtherCompany1Label
			  }
			);
		}

		<%-- 会計部門借方のauto complete --%>
		new Ajax.Autocompleter(
		  "accountDebitSectionCd",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/BumonForAutoComplete.do?op=searchBumon",
		  {
		  	paramName : "code",
		    afterUpdateElement : setBumonDebitLabel
		  }
		);
		<%-- 名称のクリアauto complete --%>
		Event.observe('accountDebitSectionCd', 'keypress', clearLabel.bindAsEventListener($('accountDebitSectionName')), false);

		<%-- 会計部門貸方のauto complete --%>
		new Ajax.Autocompleter(
		  "accountCreditSectionCd",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/BumonForAutoComplete.do?op=searchBumon",
		  {
		  	paramName : "code",
		    afterUpdateElement : setBumonCreditLabel
		  }
		);
		<%-- 名称のクリアauto complete --%>
		Event.observe('accountCreditSectionCd', 'keypress', clearLabel.bindAsEventListener($('accountCreditSectionName')), false);

		<%-- 勘定科目借方のauto complete --%>
		new Ajax.Autocompleter(
		  "debitTitleCd",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/AccountsForAutoComplete.do?op=searchAccounts",
		  {
		  	paramName : "code",
		    afterUpdateElement : setAccountsDebitLabel
		  }
		);
		<%-- 名称のクリアauto complete --%>
		Event.observe('debitTitleCd', 'keypress', clearLabel.bindAsEventListener($('debitTitleName')), false);

		<%-- 勘定科目貸方のauto complete --%>
		new Ajax.Autocompleter(
		  "creditTitleCd",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/AccountsForAutoComplete.do?op=searchAccounts",
		  {
		  	paramName : "code",
		    afterUpdateElement : setAccountsCreditLabel
		  }
		);
		<%-- 名称のクリアauto complete --%>
		Event.observe('creditTitleCd', 'keypress', clearLabel.bindAsEventListener($('creditTitleName')), false);

		<%-- 理由のauto complete --%>
		new Ajax.Autocompleter(
		  "ryCd",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/ReasonForAutoComplete.do?op=searchReason",
		  {
		  	paramName : "code",
		    afterUpdateElement : setReasonCreditLabel
		  }
		);
		<%-- 名称のクリアauto complete --%>
		Event.observe('ryCd', 'keypress', clearText.bindAsEventListener($('ryDescription')), false);

		storeInitValues(/^srh.*/, 'dirtyFlg');

		refreshLabel();
	}, false);

	<%-- 品目auto completeの選択後処理 --%>
	function setItemLabel(text, li) {
	    $("lblItemName").innerText = li.getElementsByTagName('span')[0].innerText;
	    $("itemName").value = li.getElementsByTagName('span')[0].innerText;
	    $("otherCompanyCd1").value = getHiddenValue(li,"otherCompanyCd1");
	    $("lblStyleOfPacking").innerText = getHiddenValue(li,"styleOfPacking");
	    $("styleOfPacking").value = getHiddenValue(li,"styleOfPacking");
	    $("unitDivision").value = getHiddenValue(li,"unitOfOperationManagement");

	    $("focusId").value = "itemCd";
		return form_submit('op', 'getSalesByItem');
	}
	<%-- 他社コード１auto completeの選択後処理 --%>
	function setOtherCompany1Label(text, li) {
	    $("lblItemName").innerText = li.getElementsByTagName('span')[0].innerText;
	    $("itemName").value = li.getElementsByTagName('span')[0].innerText;
	    $("itemCd").value = getHiddenValue(li,"code");
	    $("lblStyleOfPacking").innerText = getHiddenValue(li,"styleOfPacking");
	    $("styleOfPacking").value = getHiddenValue(li,"styleOfPacking");
	    $("unitDivision").value = getHiddenValue(li,"unitDivision");
	    $("focusId").value = "otherCompanyCd1";
		return form_submit('op', 'getSalesByItem');
	}
	<%-- 会計部門借方auto completeの選択後処理 --%>
	function setBumonDebitLabel(text, li) {
	    $("lblAccountDebitSectionName").innerText = li.getElementsByTagName('span')[0].innerText;
	    $("accountDebitSectionName").value = li.getElementsByTagName('span')[0].innerText;
	}
	<%-- 会計部門貸方auto completeの選択後処理 --%>
	function setBumonCreditLabel(text, li) {
	    $("lblAccountCreditSectionName").innerText = li.getElementsByTagName('span')[0].innerText;
	    $("accountCreditSectionName").value = li.getElementsByTagName('span')[0].innerText;
	}
	<%--  勘定科目借方auto completeの選択後処理 --%>
	function setAccountsDebitLabel(text, li) {
	    $("lblDebitTitleName").innerText = li.getElementsByTagName('span')[0].innerText;
	    $("debitTitleName").value = li.getElementsByTagName('span')[0].innerText;
	}
	<%--  勘定科目貸方auto completeの選択後処理 --%>
	function setAccountsCreditLabel(text, li) {
	    $("lblCreditTitleName").innerText = li.getElementsByTagName('span')[0].innerText;
	    $("creditTitleName").value = li.getElementsByTagName('span')[0].innerText;
	}
	<%--  理由auto completeの選択後処理 --%>
	function setReasonCreditLabel(text, li) {
	    $("ryDescription").value = li.getElementsByTagName('span')[0].innerText;
	}

	<%-- 初期フォーカス位置をセット --%>
	function initFocus(){
		var id = $F("focusId");
		if (id != null && id != "" && $(id) != null) {
			$(id).focus();
		}
		$("focusId").value = "";
	}

	<%-- 変更フラグセット --%>
	function setDirtyFlg() {
		$("dirtyFlg").value = "true";
	}

	<%-- 変更フラグセット --%>
	function setTaxCdChangeFlg() {
		$("taxCdChangeFlg").value = "true";
	}

	<%-- 変更フラグセット --%>
	function reSetTaxCdChangeFlg() {
		$("taxCdChangeFlg").value = "false";
	}


	<%-- 確認メッセージ --%>
	function putConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "登録してよろしいですか？";

		return confirm(alertMsg[0]);
	}

	<%-- 確認メッセージ --%>
	function putTmpFlagConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "仮単価フラグの成立をしてよろしいですか？";

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

	<%-- 20211209 Asclab Kouchi EdgeIE対応 --%>
	<%-- ポップアップ後の設定処理 --%>
	function setSalesDeliveryValues(salesDeliveryFlg, deliveryCd, searchKana, address, telNo) {
		if (salesDeliveryFlg) {
			<%-- 納入先検索後の設定処理 --%>
			$("deliveryCd").value = deliveryCd;
			$("searchKana").value = searchKana;
			$("address").value = address;
			$("telNo").value = telNo;

		    $("focusId").value = "btnDelivery";
			return form_submit('op', 'getSalesByDelivery');
		}
	}
	function setSalesLotValues(salesLotFlg, locationCd, locationName, lotNo, strInventoryQty, strInspectionQty) {
		if (salesLotFlg) {
			<%-- ロット検索後の設定処理 --%>
			var lineIndex = eval($("lineIndex").value)
			$('locationCd' + lineIndex).value = locationCd;
			$('locationName' + lineIndex).value = locationName;
			$('lotNo' + lineIndex).value = lotNo;
			$('strInventoryQty' + lineIndex).value = strInventoryQty;
			$('strInspectionQty' + lineIndex).value = strInspectionQty;

			$('lblLocationCd' + lineIndex).update($F('locationCd' + lineIndex));
			$('lblLocationName' + lineIndex).update($F('locationName' + lineIndex));
			$('lblLotNo' + lineIndex).update($F('lotNo' + lineIndex));

			<%-- 数量クリア --%>
			$('strQty' + lineIndex).disabled = false;
			$('strQty' + lineIndex).value = "";
		}
	}

	<%-- 単価計算 --%>
	function calcUnitprice() {
		//売上単価 = 標準単価- 標準値引- 特別値引
		var sUnitprice = strToNum($F("strStandardUnitprice"));
		var stDiscount = strToNum($F("strStandardDiscount"));
		var spDiscount = strToNum($F("strSpecialDiscount"));
		var unitprice = sUnitprice - stDiscount - spDiscount;

		<%-- 数値フォーマット --%>
		var res = digitFormat($F("decimalPointUrTanka"), $F("roundUrTanka"), unitprice);
		$("strSalesUnitprice").value = res;

		calcSalesAmount();
	}
<%-- 2014/2/4 新消費税対応 -->
	<%-- 金額計算 --%>
	function calcSalesAmount() {

		return form_submit('op', 'calcSalesAmountAndTax');

		<%-- 金額 = 売上単価 * 数量 --%>
		<%-- var uprice = $F("strSalesUnitprice");--%>
		<%-- var quantity = strToNum($F("strSalesQuantity"));--%>

		<%-- var amount = uprice * quantity; --%>
		<%-- var amount = multiFloat(uprice,quantity,$F("decimalPointUrTanka"));--%>


		<%-- 数値フォーマット --%>
		<%-- var res = digitFormat($F("decimalPointUrKingaku"), $F("roundUrKingaku"), amount);--%>
		<%-- $("lblStrSalesAmount").update(res);--%>
		<%-- $("strSalesAmount").value = res ;--%>
		<%-- $("lblStrSalesAmount2").update(res);--%>
	}
<%-- 2014/2/4 新消費税対応 -->
<%-- 2014/3/18 新消費税対応 --%>
	<%-- フォーカス位置の保存 --%>
	function setFocusPosition(pos){
		$("focusId").value = pos;
	}
<%-- 2014/3/18 新消費税対応 --%>
	<%-- 金額計算 --%>
	function strToNum(str) {
		var val = parseFloat(str.replace(/,/g, ""));
		if(isNaN(val)){
			val = 0;
		}
		return val;
	}

	<%-- 数値のフォーマット--%>
	function formatSalesQuantity(obj){
		var value = obj.value;
		if (value == null || value == "") {
			return;
		}

		<%-- 数値フォーマット --%>
		var res = digitStringFormat($F("decimalPoint"), $F("round"), value);
		if (res != "") {
			obj.value = res;
		}
	}

	<%-- 単位区分(URTANKA)のフォーマット--%>
	function formatUrTanka(obj){
		var value = obj.value;
		if (value != null && value != "") {
			<%-- 数値フォーマット --%>
			var res = digitStringFormat($F("decimalPointUrTanka"), $F("roundUrTanka"), value);
			if (res != "") {
				obj.value = res;
			}
		}
		calcUnitprice();
	}

	<%-- 行削除確認メッセージ --%>
	function putDelListConfirm() {
		var count = $F("locationCount");
		var flag = false;
		for(var i = 0 ; i < count ; i++){
			var chk = $F("locationList[" + i + "].checkFlg");
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

	<%-- チェックを入れる --%>
	function setLine(lineIndex) {
		$("lineIndex").value = lineIndex;
	}

	<%-- 数量量のフォーマット--%>
	function formatInoutQty(obj, lineIndex){
		var value = obj.value;
		if (value == null || value == "") {
			return;
		}
		<%-- 数値フォーマット --%>
		var res = digitStringFormat($F("decimalPoint"), $F("round"), value);
		if (res != "") {
			$("strQty" + lineIndex).value = res;
		}
	}

	<%-- 削除確認メッセージ --%>
	function deleteAlert() {
		alertMsg = new Array();
		alertMsg[0] = "削除してよろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- 取消確認メッセージ --%>
	function cancelAlert() {
		alertMsg = new Array();
		alertMsg[0] = "勘定年月:" + $("strAccountYears").value + " で売上取消データを作成します。" + "\n取消してよろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- 売上日変更時処理 --%>
	function changeSalesDate() {
		var inpFlag = false;
		<%-- 新規の場合 --%>
		<logic:equal name="salesDetailKeepForm" property="insertFlg" value="1">
			<logic:notEmpty name="salesDetailKeepForm" property="balanceCd">
				<logic:notEmpty name="salesDetailKeepForm" property="itemCd">
					<logic:notEqual name="salesDetailKeepForm" property="itemCd" value="">
						inpFlag = true;
						// 日付が正しい場合のみ処理
						if(setSalesDate() == true){
							form_submit('op', 'getSalesBySalesDate');
						}else{	// 日付が正しくない場合勘定年月をクリア
							$("strAccountYears").value = "";
						}
					</logic:notEqual>
				</logic:notEmpty>
			</logic:notEmpty>
		</logic:equal>

		<%-- 新規では無い場合 --%>
		<logic:equal name="salesDetailKeepForm" property="insertFlg" value="0">
		 	<%-- 月次更新後 --%>
			<logic:equal name="salesDetailKeepForm" property="monthlyUpdateDivision" value="1">
			 	<%-- 取消元データ以外 --%>
				<logic:equal name="salesDetailKeepForm" property="cancelOriginFlag" value="0">
					form_submit('op', 'getSalesBySalesDate');
				</logic:equal>
			</logic:equal>
		</logic:equal>
		<%-- ココより下は不要 有効単価取得処理はgetSalesBySalesDate内で処理 --%>

<%-- 2014/1/28 新消費税対応--%>
		form_submit('op', 'getTaxRatio');
<%--2014/1/28 新消費税対応　--%>

		return;

		<%-- 修正 --%>
		<logic:equal name="salesDetailKeepForm" property="insertFlg" value="0">
			<%-- 売上区分 > 0 (取消ではない場合) --%>
			<logic:greaterThan name="salesDetailKeepForm" property="categoryDivision" value="0">
			 	<%-- 月次更新前 --%>
				<logic:equal name="salesDetailKeepForm" property="monthlyUpdateDivision" value="0">
					inpFlag = true;
				</logic:equal>
			</logic:greaterThan>
		</logic:equal>

		if (!inpFlag) {
			return;
		}

		if ($F("deliveryCd") == "" || $F("itemCd") == "") {
			<%-- 納入先または品目が未入力の場合何もしない --%>
			return;
		}

	    if ($F("orderNo") == "") {
			<%-- 受注と紐づかない売上の場合のみ有効単価を取得する --%>
		    $("focusId").value = "strAccountYears";
			form_submit('op', 'changeValidUnitpriceBySalesDate');
			return;
	    }
	}

	<%-- 売上日の計算 --%>
	function setSalesDate() {
		var flag = false;

		str = $("strSalesDate").value;

		<%-- 6桁、8桁以外の場合は処理しない --%>
		if(str.length == 6 || str.length == 8){
			str = str.replace(/\//g, "");
			// 6桁入力した場合頭に20を付ける
			if(str.length == 6){
				str = "20" + str;
			}
			temp = new Date(str.substring(0, 4) + "/" + str.substring(4, 6) + "/" + str.substring(6, 8));
			Year = temp.getYear();
			Month = temp.getMonth() + parseInt(1);
			Day = temp.getDate();

			if(isNaN(Year) || isNaN(Month) || isNaN(Day)){
				null;
			} else {
				if(Month < 10 && Month > 0){
					Month = '0' + Month
				}
				if(Day < 10 && Day > 0){
					Day = '0' + Day
				}
				$("strSalesDate").value = Year + "/" + Month + "/" + Day;
				flag = true;
			}
		}
		return flag;
	}

	<%-- 日付型フィールド定義 --%>
	var dateElementsYYYYMM = new Array();
	/* 日付型 ON_FOCUSの動作を設定 */
	function dateFocusListenerYYYYMM() {
		this.value = unformatDate(this.value);
		Field.select(this);
	}

	<%--  日付型 OFF_FOCUSの動作を設定 --%>
	function dateBlurListenerYYYYMM() {
		this.value = formatDateYYYYMM(this.value);
	}

	function defineAsYMField(id) {

		var o = $(id);
		Event.observe(o, 'focus', dateFocusListenerYYYYMM.bind(o), false);
		Event.observe(o, 'blur', dateBlurListenerYYYYMM.bind(o), false);

		if (o.style.imeMode) {
			o.style.imeMode = 'disabled';
		}
		o.style.textAlign = 'left';

		dateElementsYYYYMM[o.id] = o;
	}
	function formatDateYYYYMM(org) {

		if (org == null) {
			return null;
		}

		if(!org.match(/^(-?)[0-9]{4,6}$/)){
			return org;
		}

		var tmp = org;

		if (tmp.length == 4) {
			tmp = "20" + tmp;
		}

		if (tmp.length == 6) {

			var year  = parseInt(tmp.substring(0, 4), 10);
			var month = parseInt(tmp.substring(4, 6), 10)-1;
			var day   = parseInt('01', 10);

			if (isNaN(year) || isNaN(month)) {
				return org;
			}

			var d = new Date(year, month, day);

			if (month != d.getMonth()) {
				return org;
			}

			if (day != d.getDate()) {
				return org;
			}

			return tmp.substring(0, 4) + "/" + tmp.substring(4, 6);
		}

		return org;
	}

</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/SalesDetailKeep" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="dirtyFlg" />
	<nested:hidden property="unitDivision" styleId="unitDivision" />
	<nested:hidden property="decimalPoint" styleId="decimalPoint" />
	<nested:hidden property="round" styleId="round" />
	<nested:hidden property="unitDivisionUrTanka" styleId="unitDivisionUrTanka" />
	<nested:hidden property="decimalPointUrTanka" styleId="decimalPointUrTanka" />
	<nested:hidden property="roundUrTanka" styleId="roundUrTanka" />
	<nested:hidden property="unitDivisionUrKingaku" styleId="unitDivisionUrKingaku" />
	<nested:hidden property="decimalPointUrKingaku" styleId="decimalPointUrKingaku" />
	<nested:hidden property="roundUrKingaku" styleId="roundUrKingaku" />
	<nested:hidden property="focusId" styleId="focusId" />
	<nested:hidden property="itemCheckedFlag" styleId="itemCheckedFlag" />
	<nested:hidden property="strSalesUnitprice" styleId="strSalesUnitprice" />
	<nested:hidden property="locationCount" styleId="locationCount" />
	<nested:hidden property="lineIndex" styleId="lineIndex" />
	<nested:hidden property="venderCd" styleId="venderCd" />
	<nested:hidden property="insertFlg" styleId="insertFlg" />
	<nested:hidden property="orderNo" styleId="orderNo" />
	<nested:hidden property="taxCdChangeFlg" />

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
									<nested:equal property="keepFlag" value="2">
										<td class="fcTitle fb">売上入力(預り品)</td>
									</nested:equal>
									<nested:notEqual property="keepFlag" value="2">
										<td class="fcTitle fb">売上入力</td>
									</nested:notEqual>
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
												<td class="fcTitleDetail fb bcTitleDetail" width="70">売上番号</td>
												<td width="100">
													<nested:write property="salesNo" />
												</td>
												<td class="bcTitleSearch fb fcTitleSearch">発行済</td>
												<td colspan="2">
													<nested:write property="slipPublishCompName" />
												</td>
											</tr>
											<nested:notEmpty property="orderNo">
												<tr>
													<td class="fcTitleDetail fb bcTitleDetail" width="70">受注番号</td>
													<td colspan="6">
														<nested:write property="orderNo" />
													</td>
												</tr>
											</nested:notEmpty>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail" width="70">売上日</td>
												<td>
													<nested:text property="strSalesDate" maxlength="10" size="10" styleId="strSalesDate" style="ime-mode:disabled"
													 onchange="setDirtyFlg(); changeSalesDate();　reSetTaxCdChangeFlg(); calcSalesAmount();" />
												</td>
												<td class="fcTitleDetail fb bcTitleDetail" width="70">勘定年月</td>
												<td colspan="2">
													<nested:text property="strAccountYears" maxlength="7" size="10" styleId="strAccountYears" style="ime-mode:disabled" onchange="setDirtyFlg();" />
												</td>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail" width="70">売上区分</td>
												<td colspan="4">
													<nested:write property="categoryName" />
												</td>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail" width="70">納入先</td>
												<td colspan="2">
													<nested:equal property="insertFlg" value="1">
														<span id="cssButton">
															<nested:greaterThan property="locationCount" value="0">
																<a href="#" class="cssButton" id="btnDelivery" onclick="return false;" disabled="true">
																	選
																</a>
															</nested:greaterThan>
															<nested:equal property="locationCount" value="0">
																<!-- 20211209 Asclab Kouchi EdgeIE対応 -->
																<a href="#" class="cssButton" id="btnDelivery"
																   onclick="<%="open_modal_popup_edge(765, 600,'SalesDeliverySearch', '', 'srhDivision', '1'); setDirtyFlg(); return false;"%>">
																	選
																</a>
															</nested:equal>
															&nbsp;
														</span>
													</nested:equal>
													<span id="lblDeliveryCd">
														<nested:write property="deliveryCd" />
													</span>
													<nested:hidden property="deliveryCd" styleId="deliveryCd" />
												</td>
												<nested:notEmpty property="orderNo">
													<td colspan="2"><%-- 納入先名称 --%>
														<nested:text property="searchKana" size="21" readonly="true" styleId="searchKana" styleClass="noborderAl" tabindex="-1"/>
													</td>
													<td class="fcTitleDetail fb bcTitleDetail">納入先宛先</td>
													<td>
														<nested:text property="deliveryAddress" size="15" readonly="true" styleId="deliveryAddress" styleClass="noborderAl" tabindex="-1"/>
													</td>
												</nested:notEmpty>
												<nested:empty property="orderNo">
													<td colspan="4"><%-- 納入先名称 --%>
														<nested:text property="searchKana" size="53" readonly="true" styleId="searchKana" styleClass="noborderAl" tabindex="-1"/>
													</td>
												</nested:empty>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail" width="70">住所</td>
												<td colspan="2">
													<nested:text property="address" size="23" readonly="true" styleId="address" styleClass="noborderAl" tabindex="-1"/>
												</td>
												<td class="fcTitleDetail fb bcTitleDetail" width="70">電話番号</td>
												<td width="100">
													<nested:text property="telNo" size="12" readonly="true" styleId="telNo" styleClass="noborderAl" tabindex="-1"/>
												</td>
												<td width="90"></td>
												<td width="50"></td>
											</tr>
										</table>
										<table border="0" cellspacing="2" cellpadding="1">
											<tr class="bcTitleList fb fcTitleList">
												<td width="70px">品目ｺｰﾄﾞ</td>
												<td width="75px">他社ｺｰﾄﾞ1</td>
												<td width="200px">品目名称</td>
												<td width="50px">荷姿</td>
												<td>数量</td>
												<td width="40px">標準単価</td>
												<td width="40px">標準値引</td>
												<td width="40px">特別値引</td>
												<td>金額</td>
												<td>仮単価</td>
												<nested:notEmpty property="orderNo">
													<td>増付数</td>
												</nested:notEmpty>
											</tr>
											<tr class="bcList2">
												<nested:equal property="insertFlg" value="1">
													<nested:greaterThan property="locationCount" value="0">
														<td><%-- 品目コード --%>
															<nested:text property="itemCd" maxlength="20" size="6" styleId="itemCd" disabled="true"/>
														</td>
														<td><%-- 他社コード1 --%>
															<nested:text property="otherCompanyCd1" maxlength="20" size="6" styleId="otherCompanyCd1" disabled="true" />
														</td>
													</nested:greaterThan>
													<nested:equal property="locationCount" value="0">
														<td><%-- 品目コード --%>
															<nested:text property="itemCd" maxlength="20" size="6" styleId="itemCd" onchange="setDirtyFlg()" />
														</td>
														<td><%-- 他社コード1 --%>
															<nested:text property="otherCompanyCd1" maxlength="20" size="6" styleId="otherCompanyCd1" onchange="setDirtyFlg()" />
														</td>
													</nested:equal>
												</nested:equal>
												<nested:notEqual property="insertFlg" value="1">
													<td width="20px"><%-- 品目コード --%>
														<nested:write property="itemCd" />
														<nested:hidden property="itemCd" styleId="itemCd" />
													</td>
													<td width="20px"><%-- 他社コード1 --%>
														<nested:write property="otherCompanyCd1" />
													</td>
												</nested:notEqual>
												<div id="autocomplete_selection" class="autocomplete"></div>
												<td width="200px"><%-- 品目名称 --%>
													<div id="lblItemName">
														<nested:write property="itemName" />
													</div>
													<nested:hidden property="itemName" styleId="itemName" />
												</td>
												<td><%-- 荷姿 --%>
													<div id="lblStyleOfPacking">
														<nested:write property="styleOfPacking" />
													</div>
													<nested:hidden property="styleOfPacking" styleId="styleOfPacking" />
												</td>
												<nested:equal property="insertFlg" value="1">
													<td><%-- 数量 --%>
														<nested:text property="strSalesQuantity" maxlength="22" size="2" styleId="strSalesQuantity" onchange="setFocusPosition('strStandardUnitprice');formatSalesQuantity(this); calcSalesAmount(); setDirtyFlg();" style="ime-mode:disabled"/>
													</td>
												</nested:equal>
												<nested:notEqual property="insertFlg" value="1">
													<td><%-- 数量 --%>
														<nested:text property="strSalesQuantity" maxlength="22" size="2" styleId="strSalesQuantity" onchange="setFocusPosition('strStandardUnitprice');formatSalesQuantity(this); calcSalesAmount(); setDirtyFlg();" style="ime-mode:disabled" disabled="true"/>
													</td>
												</nested:notEqual>

												<nested:equal property="insertFlg" value="1">
													<td><%-- 標準単価 --%>
														<nested:text property="strStandardUnitprice" maxlength="" size="2" styleId="strStandardUnitprice" onchange="setFocusPosition('strStandardDiscount');formatUrTanka(this); setDirtyFlg()" style="ime-mode:disabled"/>
													</td>
												</nested:equal>
												<nested:notEqual property="insertFlg" value="1">
													<td><%-- 標準単価 --%>
														<nested:text property="strStandardUnitprice" maxlength="" size="2" styleId="strStandardUnitprice" onchange="setFocusPosition('strStandardDiscount');formatUrTanka(this); setDirtyFlg()" style="ime-mode:disabled" disabled="true"/>
													</td>
												</nested:notEqual>

												<nested:equal property="insertFlg" value="1">
													<td><%-- 標準値引 --%>
														<nested:text property="strStandardDiscount" maxlength="22" size="2" styleId="strStandardDiscount" onchange="setFocusPosition('strSpecialDiscount');formatUrTanka(this); setDirtyFlg()" style="ime-mode:disabled"/>
													</td>
												</nested:equal>
												<nested:notEqual property="insertFlg" value="1">
													<td><%-- 標準値引 --%>
														<nested:text property="strStandardDiscount" maxlength="22" size="2" styleId="strStandardDiscount" onchange="setFocusPosition('strSpecialDiscount');formatUrTanka(this); setDirtyFlg()" style="ime-mode:disabled" disabled="true"/>
													</td>
												</nested:notEqual>

												<nested:equal property="insertFlg" value="1">
													<td><%-- 特別値引 --%>
														<nested:text property="strSpecialDiscount" maxlength="22" size="2" styleId="strSpecialDiscount" onchange="setFocusPosition('tmpUnitpriceFlg');formatUrTanka(this); setDirtyFlg()" style="ime-mode:disabled"/>
													</td>
												</nested:equal>
												<nested:notEqual property="insertFlg" value="1">
													<td><%-- 特別値引 --%>
														<nested:text property="strSpecialDiscount" maxlength="22" size="2" styleId="strSpecialDiscount" onchange="setFocusPosition('tmpUnitpriceFlg');formatUrTanka(this); setDirtyFlg()" style="ime-mode:disabled" disabled="true"/>
													</td>
												</nested:notEqual>


												<td width="50px" class="alignRight"><%-- 金額 --%>
													<div id="lblStrSalesAmount">
														<nested:write property="strSalesAmount" />
													</div>
													<nested:hidden property="strSalesAmount" styleId="strSalesAmount" />
												</td>
												<td align="center"><%-- 仮単価 --%>
													<nested:checkbox property="tmpUnitpriceFlg" styleId="tmpUnitpriceFlg" />
												</td>
												<nested:notEmpty property="orderNo">
													<td class="alignRight"><%-- 増付数 --%>
														<nested:write property="strMatss" />
													</td>
												</nested:notEmpty>
											</tr>
										</table>
										<table border="0" cellspacing="2" cellpadding="1">
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail" width="70">合計金額</td>
												<td width="140px" colspan="3" class="alignRight">
														<span id="lblStrSalesAmount2">
															<nested:write property="strSalesAmount" />
														</span>円
												</td>

<!-- 2014/1/28 新消費税対応 -->
												<td class="fcTitleDetail fb bcTitleDetail" width="70">消費税額</td>
												<td width=100px" class="alignRight">
														<span id="lblStrTaxAmount">
															<nested:write property="strTaxAmount" />
														</span>円
												</td>
												<td class="fcTitleDetail fb bcTitleDetail" width="70">消費税率</td>
<%-- 												<td>
													<nested:select property="strTaxRatio" style="margin: 0;padding: 0" onchange="setFocusPosition('strTaxRatio');setDirtyFlg();calcSalesAmount();">
														<nested:optionsCollection property="taxCombo" label="labales" value="values" />
													</nested:select>%
												</td> --%>
										        <td>
												<%-- <nested:select property="taxRatio"  style="margin: 0;padding: 0" onchange="setFocusPosition('taxRatio');setDirtyFlg();calcSalesAmount();"> --%>
												<nested:select property="taxCd" style="margin: 0;padding: 0" onchange="setFocusPosition('taxCd');setDirtyFlg();setTaxCdChangeFlg();calcSalesAmount();reSetTaxCdChangeFlg();">
														<nested:options property="taxValues" labelProperty="taxLabels"/>
														<%-- <nested:options property="taxLabels" labelProperty="taxValues" /> --%>
													</nested:select>
													<%-- <nested:hidden property="taxCd" styleId="taxCd" /> --%>
												</td>

													<td>
														<%
														pageContext.setAttribute("SelectNewTaxDivision", new com.asahikaseieng.app.comboboxes.SelectNewTaxDivision(false, false));
														%>
														<nested:select property="strTaxDivision" onchange="setFocusPosition('strTaxDivision');setDirtyFlg();calcSalesAmount();">
															<nested:options name="SelectNewTaxDivision" property="values" labelName="SelectNewTaxDivision" labelProperty="labels"/>
														</nested:select>
													</td>
<!-- 2014/1/28 新消費税対応 -->

											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail" width="70">担当部署</td>
												<td width="30">
													<div id="lblChargeOrganizationCd">
														<nested:write property="chargeOrganizationCd" />
													</div>
													<nested:hidden property="chargeOrganizationCd" styleId="chargeOrganizationCd" />
												</td>
												<td>
													<div id="lblchargeOrganizationName">
														<nested:write property="chargeOrganizationName" />
													</div>
													<nested:hidden property="chargeOrganizationName" styleId="chargeOrganizationName" />
												</td>
											</tr>
										</table>
										<table border="0" cellspacing="2" cellpadding="1" id="tblList" >
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail">
													帳合コード
												</td>
												<td width="200">
													<nested:write property="balanceCd" />
												</td>
												<td class="fcTitleDetail fb bcTitleDetail"">
													仕訳
												</td>
												<td width="200"></td>
											</tr>
											<tr>
												<td colspan="2" valign="Top">
													<table border="1" cellspacing="2" cellpadding="1" >
														<tr class="bcTitleList fb fcTitleList">
															<td width="50px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
															<td width="90px">得意先コード</td>
															<td width="150px">得意先名</td>
														</tr>
														<nested:iterate id="venderList" property="venderList" indexId="index">
															<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
																<tr class="bcList1">
															</app:modulo>
															<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
																<tr class="bcList2">
															</app:modulo>
																	<td class="alignCenter">
																		<nested:write property="shopLevelName" /><br>
																	</td>
																	<td>
																		<nested:write property="venderCd" /><br>
																	<td width="120px">
																		<nested:write property="venderShortedName" /><br>
																	</td>
																</tr>
														</nested:iterate>
													</table>
												</td>
												<td colspan="2" valign="Top">
													<table border="1" cellspacing="2" cellpadding="1" >
														<tr class="bcTitleList fb fcTitleList">
															<td>&nbsp;</td>
															<td colspan="2">借方</td>
															<td colspan="2">貸方</td>
														</tr>
														<tr class="bcList1">
															<td class="bcTitleList fb fcTitleList">
																会計部門
															</td>
															<td><%-- 会計部門借方コード --%>
																<nested:text property="accountDebitSectionCd" maxlength="10" size="8" styleId="accountDebitSectionCd"/>
															</td>
															<td width="80px">
																<div id="lblAccountDebitSectionName">
																	<nested:write property="accountDebitSectionName" />
																</div>
																<nested:hidden property="accountDebitSectionName" styleId="accountDebitSectionName" />
															</td>
															<td><%-- 会計部門貸方コード --%>
																<nested:text property="accountCreditSectionCd" maxlength="10" size="8" styleId="accountCreditSectionCd"/>
															</td>
															<td width="80px">
																<div id="lblAccountCreditSectionName">
																	<nested:write property="accountCreditSectionName" />
																</div>
																<nested:hidden property="accountCreditSectionName" styleId="accountCreditSectionName" />
															</td>
														</tr>
														<tr class="bcList2">
															<td class="bcTitleList fb fcTitleList">
																勘定科目
															</td>
															<td><%-- 借方科目コード< --%>
																<nested:text property="debitTitleCd" maxlength="10" size="8" styleId="debitTitleCd"/>
															</td>
															<td width="80px">
																<div id="lblDebitTitleName">
																	<nested:write property="debitTitleName" />
																</div>
																<nested:hidden property="debitTitleName" styleId="debitTitleName" />
															</td>
															<td><%-- 貸方科目コード< --%>
																<nested:text property="creditTitleCd" maxlength="10" size="8" styleId="creditTitleCd"/>
															</td>
															<td width="80px">
																<div id="lblCreditTitleName">
																	<nested:write property="creditTitleName" />
																</div>
																<nested:hidden property="creditTitleName" styleId="creditTitleName" />
															</td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td height="2"></td>
											</tr>
										</table>
										<%-- 受注番号がない場合のみ表示する --%>
										<nested:empty property="orderNo">
											<table border="0" cellspacing="2" cellpadding="1" width="400">
												<tr>
													<td height="10"></td>
												</tr>
												<tr>
													<td class="fcTitleDetail fb bcTitleDetail" width="120">出庫ロケーション</td>
													<td align="right">
														<%-- 売上区分 > 0 (取消ではない場合) かつ 月次更新前の場合のみ表示する --%>
														<nested:greaterThan property="categoryDivision" value="0">
															<nested:notEqual property="monthlyUpdateDivision" value="1">
																<div id="cssButton">
																	<a href="#"
																		onclick="return form_submit('op', 'addRow'); return false;"
																		class="cssButton">&nbsp;行&nbsp;追&nbsp;加&nbsp;</a>
																	&nbsp;&nbsp;&nbsp;&nbsp;
																	<a href="#"
																		onclick="if (!(putDelListConfirm())) {return false;}else{return form_submit('op', 'delRow'); return false;}"
																		class="cssButton">&nbsp;行&nbsp;削&nbsp;除&nbsp;</a>
																</div>
															</nested:notEqual>
														</nested:greaterThan>
													</td>
												</tr>
											</table>
											<nested:notEmpty property="locationList">
												<table border="0" cellspacing="2" cellpadding="1" id="detailTblList">
													<tr class="bcTitleList fb fcTitleList">
														<td></td>
														<td>検索</td>
														<td>ロケーション</td>
														<td>名称</td>
														<td>ロット番号</td>
														<td>数量</td>
													</tr>
													<%-- 明細部 >>>>> --%>
													<nested:iterate id="locationList" property="locationList" indexId="index">
														<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
															<tr class="bcList1">
														</app:modulo>
														<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
															<tr class="bcList2">
														</app:modulo>
																<td><%-- 削除チェックボックス --%>
																	<nested:checkbox property="checkFlg" styleId="checkFlg" />
																</td>
																<td class="alignCenter"><%-- 検索ボタン--%>
																	<div id="cssButton">
																		<!-- 20211209 Asclab Kouchi EdgeIE対応 -->
																		<a href="#"  class="cssButton"
																		   onclick="<%="setLine(" + pageContext.findAttribute("index").toString() + ");open_modal_popup_edge(730, 600,'SalesLotSearch', '', 'srhItemCd', $('itemCd').value, 'srhLocationCd', $('locationCd" + pageContext.findAttribute("index").toString() + "').value, 'srhLocationName', $('locationName" + pageContext.findAttribute("index").toString() + "').value, 'venderDivision', 'TS', 'venderCd', $('venderCd').value); setDirtyFlg(); return false;"%>">
																			選
																		</a>
																	</div>
																</td>
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
																<td class="alignRight"><%-- 数量 --%>
																	<nested:notEmpty property="locationCd">
																		<nested:text property="strQty" maxlength="22" size="5"
																		styleId="<%="strQty" + pageContext.findAttribute("index").toString() %>"
																		onchange='<%="setDirtyFlg(); formatInoutQty(this," + pageContext.findAttribute("index").toString() + ");"%>'
																		style="ime-mode:disabled" />
																	</nested:notEmpty>
																	<nested:empty property="locationCd">
																		<nested:text property="strQty" maxlength="22" size="5"
																		styleId="<%="strQty" + pageContext.findAttribute("index").toString() %>"
																		onchange='<%="setDirtyFlg(); formatInoutQty(this," + pageContext.findAttribute("index").toString() + ");"%>'
																		style="ime-mode:disabled" disabled="true" />
																	</nested:empty>
																	<nested:hidden property="strInventoryQty" styleId="<%="strInventoryQty" + pageContext.findAttribute("index").toString() %>" />
																	<nested:hidden property="strInspectionQty" styleId="<%="strInspectionQty" + pageContext.findAttribute("index").toString() %>" />
																</td>
															</tr>
													</nested:iterate>
												</table>
											</nested:notEmpty>
										</nested:empty>
										<table cellspacing="2" cellpadding="1" border="0" width="100%">
											<tr>
												<td height="5"></td>
											</tr>
											<%-- 受注番号がない場合のみ表示する --%>
											<nested:empty property="orderNo">
												<tr>
													<td class="fcTitleDetail fb bcTitleDetail" width="120">入庫ロケーション</td>
													<td>
														<nested:select property="housingLocationCd" style="margin: 0;padding: 0" styleId="housingLocationCd">
															<nested:optionsCollection property="housingLocationCombo" label="labales" value="values" />
														</nested:select>
													</td>
												</tr>
											</nested:empty>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail" width="120">理由コード</td>
												<td>
													<nested:text property="ryCd" maxlength="8" size="8" styleId="ryCd" onchange="setDirtyFlg();"/>
												</td>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail" width="120">理由</td>
												<td colspan="3">
													<nested:textarea property="ryDescription" cols="60" rows="3" styleId="ryDescription" readonly="true" tabindex="-1"/>
												</td>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail" width="120">備考</td>
												<td>
													<nested:textarea property="remark" cols="60" rows="3" styleId="remark" onchange="setDirtyFlg();"/>
												</td>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail" width="120">摘要</td>
												<td>
													<nested:textarea property="summary" cols="60" rows="3" styleId="summary" onchange="setDirtyFlg();"/>
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
														<%-- 新規登録 --%>
														<nested:equal property="insertFlg" value="1">
															<nested:equal property="updateAuthority" value="true">
																<a href="#" onclick="if (!(putConfirm())) {return false;}else{return form_submit('op', 'insert'); return false;}" class="cssButton">
																	&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
																</a>
																&nbsp;&nbsp;&nbsp;&nbsp;
															</nested:equal>
														</nested:equal>
														<%-- 更新 --%>
														<nested:equal property="insertFlg" value="0">
															<%-- 売上区分 > 0 (取消ではない場合) --%>
															<nested:greaterThan property="categoryDivision" value="0">
															 	<%-- 月次更新前 --%>
																<nested:notEqual property="monthlyUpdateDivision" value="1">
																	<nested:equal property="updateAuthority" value="true">
																		<%-- 仮単価ボタンを削除 20230817 訪問時に梅澤様依頼のためインボイス課題管理表(移行) No25 運用メンバー了承済み --%>
																		<%-- 預かり品の場合 --%>
																		<a href="#" onclick="if (!(putConfirm())) {return false;}else{return form_submit('op', 'update'); return false;}" class="cssButton">
																			&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
																		</a>
																		&nbsp;&nbsp;&nbsp;&nbsp;
																	</nested:equal>
																	<%-- 入力区分が売上の場合 --%>
																	<nested:equal property="inputDivision" value="0">
																		<nested:equal property="deleteAuthority" value="true">
																			<a href="#" onclick="if (!(deleteAlert())) {return false;}else{return form_submit('op', 'delete'); return false;}" class="cssButton">
																				&nbsp;&nbsp;削&nbsp;&nbsp;除&nbsp;&nbsp;
																			</a>
																			&nbsp;&nbsp;&nbsp;&nbsp;
																		</nested:equal>
																	</nested:equal>
																	<%-- 入力区分が出荷の場合（受注有） --%>
																	<nested:equal property="inputDivision" value="1">
																		<nested:equal property="deleteAuthority" value="true">
																			<a href="#" onclick="if (!(deleteAlert())) {return false;}else{return form_submit('op', 'delete'); return false;}" class="cssButton">
																				&nbsp;&nbsp;削&nbsp;&nbsp;除&nbsp;&nbsp;
																			</a>
																			&nbsp;&nbsp;&nbsp;&nbsp;
																		</nested:equal>
																	</nested:equal>
																</nested:notEqual>
															 	<%-- 月次更新後 --%>
																<nested:equal property="monthlyUpdateDivision" value="1">
																 	<%-- 取消元データ以外 --%>
																	<nested:equal property="cancelOriginFlag" value="0">
																		<a href="#" onclick="if (!(cancelAlert())) {return false;}else{return form_submit('op', 'cancel'); return false;}" class="cssButton">
																		&nbsp;&nbsp;取&nbsp;&nbsp;消&nbsp;&nbsp;
																		</a>
																		&nbsp;&nbsp;&nbsp;&nbsp;
																	</nested:equal>
																</nested:equal>
															</nested:greaterThan>
															<%-- 売上区分 < 0 (取消の場合) --%>
															<nested:lessThan property="categoryDivision" value="0">
																<nested:equal property="deleteAuthority" value="true">
																 	<%-- 月次更新前 --%>
																	<nested:equal property="monthlyUpdateDivision" value="0">
																		<a href="#" onclick="if (!(deleteAlert())) {return false;}else{return form_submit('op', 'delete'); return false;}" class="cssButton">
																			&nbsp;&nbsp;削&nbsp;&nbsp;除&nbsp;&nbsp;
																		</a>
																		&nbsp;&nbsp;&nbsp;&nbsp;
																	</nested:equal>
																</nested:equal>
															</nested:lessThan>
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
