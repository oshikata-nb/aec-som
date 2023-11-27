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
		defineAsRequiredField("venderCd");
		defineAsRequiredField("itemCd");
		defineAsRequiredField("otherCompanyCd1");
		defineAsRequiredField("strSalesQuantity");
		defineAsRequiredField("strStandardUnitprice");
		defineAsRequiredField("accountDebitSectionCd");
		defineAsRequiredField("accountCreditSectionCd");
		defineAsRequiredField("debitTitleCd");
		defineAsRequiredField("creditTitleCd");

		<%-- 日付型フィールド定義 --%>
		defineAsDateField("strSalesDate");
		defineAsYMField("strAccountYears");

		<%-- 数値型フィールド定義 --%>
		defineAsNumberField("strSalesQuantity");
		defineAsNumberField("strStandardUnitprice");
		defineAsNumberField("strStandardDiscount");
		defineAsNumberField("strSpecialDiscount");

		<%-- 新規の場合のみ設定する --%>
		if ($F("insertFlg") == "1") {
			<%-- 得意先のauto complete --%>
			new Ajax.Autocompleter(
			  "venderCd",
			  "autocomplete_selection",
			  "<%= request.getContextPath() %>/VenderForAutoComplete.do?op=searchVender",
			  {
			  	paramName : "code",
			  	parameters : 'venderDivision=TS',
			    afterUpdateElement : setVenderLabel
			  }
			);
			<%-- 名称のクリアauto complete --%>
			Event.observe('venderCd', 'keypress', clearText.bindAsEventListener($('venderShortedName')), false);
			Event.observe('venderCd', 'keypress', clearText.bindAsEventListener($('chargeOrganizationCd')), false);
			Event.observe('venderCd', 'keypress', clearText.bindAsEventListener($('chargeOrganizationName')), false);
			Event.observe('venderCd', 'keypress', clearLabel.bindAsEventListener($('chargeOrganizationCd')), false);
			Event.observe('venderCd', 'keypress', clearLabel.bindAsEventListener($('chargeOrganizationName')), false);

			<%-- 品目のauto complete --%>
			new Ajax.Autocompleter(
			  "itemCd",
			  "autocomplete_selection",
			  "<%= request.getContextPath() %>/ItemForAutoComplete.do?op=searchDetailItem",
			  {
			  	paramName : "code",
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
			Event.observe('itemCd', 'keypress', clearCheckbox.bindAsEventListener($('tmpUnitpriceFlg')), false);
			Event.observe('itemCd', 'keypress', clearText.bindAsEventListener($('unitDivision')), false);
			Event.observe('itemCd', 'keypress', clearText.bindAsEventListener($('decimalPoint')), false);
			Event.observe('itemCd', 'keypress', clearText.bindAsEventListener($('round')), false);
			Event.observe('itemCd', 'keypress', clearText.bindAsEventListener($('itemCheckedFlag')), false);

			<%-- 他社コード１のauto complete --%>
			new Ajax.Autocompleter(
			  "otherCompanyCd1",
			  "autocomplete_selection",
			  "<%= request.getContextPath() %>/ItemForAutoComplete.do?op=searchDetailItemOtherCompany",
			  {
			  	paramName : "code",
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

		<%-- 返品情報表示状態変更 --%>
		changeReturnsField();

		refreshLabel();

	}, false);

	<%-- 得意先auto completeの選択後処理 --%>
	function setVenderLabel(text, li) {
	    $("venderShortedName").value = li.getElementsByTagName('span')[0].innerText;

	    $("focusId").value = "venderCd";

		<%-- 新規の場合 --%>
		<logic:equal name="salesDetailStandardForm" property="insertFlg" value="1">
			return form_submit('op', 'getSalesByVender');
		</logic:equal>

		<%-- 新規では無い場合 --%>
		<logic:equal name="salesDetailStandardForm" property="insertFlg" value="0">
		 	<%-- 月次更新後 --%>
			<logic:equal name="salesDetailStandardForm" property="monthlyUpdateDivision" value="1">
			 	<%-- 取消元データ以外 --%>
				<logic:equal name="salesDetailStandardForm" property="cancelOriginFlag" value="0">
					return form_submit('op', 'getSalesByVender');
				</logic:equal>
			</logic:equal>
		</logic:equal>

	}
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
<%--	    $("unitDivision").value = getHiddenValue(li,"unitDivision");--%>
	    $("unitDivision").value = getHiddenValue(li,"unitOfOperationManagement");
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


	<%-- 確認メッセージ --%>
	function putConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "登録してよろしいですか？";

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

	<%-- 売上日変更時処理 --%>
	function changeSalesDate() {

		<%-- 新規の場合 --%>
		<logic:equal name="salesDetailStandardForm" property="insertFlg" value="1">
			<logic:notEmpty name="salesDetailStandardForm" property="venderCd">
				form_submit('op', 'getSalesBySalesDate');
			</logic:notEmpty>
		</logic:equal>

		<%-- 新規では無い場合 --%>
		<logic:equal name="salesDetailStandardForm" property="insertFlg" value="0">
		 	<%-- 月次更新後 --%>
			<logic:equal name="salesDetailStandardForm" property="monthlyUpdateDivision" value="1">
			 	<%-- 取消元データ以外 --%>
				<logic:equal name="salesDetailStandardForm" property="cancelOriginFlag" value="0">
					form_submit('op', 'getSalesBySalesDate');
				</logic:equal>
			</logic:equal>
		</logic:equal>
<%-- 2014/1/28 新消費税対応--%>
		form_submit('op', 'getTaxRatio');
<%--2014/1/28 新消費税対応　--%>
	}

	<%-- 売上区分変更時処理 --%>
	function changeReturnsField() {
		var div = $F("categoryDivision");
		if (div == $F("categoryDevisionReturns") || div == $F("categoryDevisionReturnsAdvance")) {
			$("divReturns").style.display = "block";
		} else {
			$("divReturns").style.display = "none";
		}
		if (div == $F("categoryDevisionSales") || div == $F("categoryDevisionSalesAdvance")) {
			$("divInout").style.display = "block";
		} else {
			$("divInout").style.display = "none";

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
			$("lblDeliveryCd").update($F("deliveryCd"));
			$("lblSearchKana").update($F("searchKana"));
		}
	}
	function setSalesInoutValues(salesInoutFlg, inoutNo, lotNo, strInoutQty, strInoutWeight, inoutDate, ryName, inputorName) {
		if (salesInoutFlg) {
			$("inoutNo").value = inoutNo;
			$("inoutLotNo").value = lotNo;
			$("inoutQty").value = strInoutQty;
			$("inoutWeight").value = strInoutWeight;
			$("inoutDate").value = inoutDate;
			$("ryName").value = ryName;
			$("inputorName").value = inputorName;
			$("lblInoutQty").update($("inoutQty").value);
			$("lblInoutWeight").update($("inoutWeight").value);
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
		<%--		var uprice = $F("strSalesUnitprice"); --%>
		<%--		var quantity = strToNum($F("strSalesQuantity")); --%>

		<%-- var amount = uprice * quantity;  --%>
		<%--		var amount = multiFloat(uprice,quantity,$F("decimalPointUrTanka")); --%>

		<%-- 数値フォーマット --%>
		<%--		var res = digitFormat($F("decimalPointUrKingaku"), $F("roundUrKingaku"), amount); --%>
		<%--		$("lblStrSalesAmount").update(res); --%>
		<%--		$("strSalesAmount").value = res ; --%>
		<%--		$("lblStrSalesAmount2").update(res); --%>
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


	<%-- 仕訳反転処理 --%>
	function categoryDivisionAction(combo){
		var value = combo.value;
		var index = 0;
		while(true){
			var categoryDivisionList = $("categoryDivisionList" + index);
			if(categoryDivisionList == null){
				break;
			}else{
				var cvalue = categoryDivisionList.value;
				if(value == cvalue){
					var reversalFlg = $F("reversalFlgList" + index);	// 仕訳反転フラグ
					var preReversalFlg = $F("preReversalFlg");			// 仕訳反転フラグ前回値
					$("preReversalFlg").value = reversalFlg;

					//仕訳反転処理
					if((reversalFlg == "1" && (preReversalFlg == "" || preReversalFlg == "0"))
					   || (reversalFlg == "0" && (preReversalFlg == "1"))) {

						//会計部門
						var accountDebitSectionCd = $F("accountDebitSectionCd");		// 借方
						var accountDebitSectionName = $F("accountDebitSectionName");	// 借方
						var accountCreditSectionCd = $F("accountCreditSectionCd");		// 貸方
						var accountCreditSectionName = $F("accountCreditSectionName");	// 貸方
						//勘定科目
						var debitTitleCd = $F("debitTitleCd");			// 借方
						var debitTitleName = $F("debitTitleName");		// 借方
						var creditTitleCd = $F("creditTitleCd");		// 貸方
						var creditTitleName = $F("creditTitleName");	// 貸方

						//会計部門を逆転
						$("accountDebitSectionCd").value = accountCreditSectionCd;
						$("accountDebitSectionName").value = accountCreditSectionName;
						$("lblAccountDebitSectionName").update(accountCreditSectionName);
						$("accountCreditSectionCd").value = accountDebitSectionCd;
						$("accountCreditSectionName").value = accountDebitSectionName;
						$("lblAccountCreditSectionName").update(accountDebitSectionName);
						//勘定科目を逆転
						$("debitTitleCd").value = creditTitleCd;
						$("debitTitleName").value = creditTitleName;
						$("lblDebitTitleName").update(creditTitleName);
						$("creditTitleCd").value = debitTitleCd;
						$("creditTitleName").value = debitTitleName;
						$("lblCreditTitleName").update(debitTitleName);
					}
					break;
				}
			}
			index++;
		}
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

<nested:form action="/SalesDetailStandard" method="post" onsubmit="return false;" styleId="mainForm">

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
	<nested:hidden property="categoryDevisionReturns" styleId="categoryDevisionReturns" />
	<nested:hidden property="categoryDevisionSales" styleId="categoryDevisionSales" />
	<nested:hidden property="categoryDevisionReturnsAdvance" styleId="categoryDevisionReturnsAdvance" />
	<nested:hidden property="categoryDevisionSalesAdvance" styleId="categoryDevisionSalesAdvance" />
	<nested:hidden property="insertFlg" styleId="insertFlg" />
	<nested:hidden property="inoutNo" />
		<nested:hidden property="taxCdChangeFlg" />
	<%-- 分類コードリスト --%>
	<nested:notEmpty property="categoryDivisionList">
		<nested:iterate id="item" property="categoryDivisionList" indexId="index">
			<input type="hidden" id="categoryDivisionList<bean:write name='index'/>" value="<bean:write name='item'/>" >
		</nested:iterate>
	</nested:notEmpty>
	<%-- 仕分反転フラグリスト --%>
	<nested:notEmpty property="reversalFlgList">
		<nested:iterate id="item" property="reversalFlgList" indexId="index">
			<input type="hidden" id="reversalFlgList<bean:write name='index'/>" value="<bean:write name='item'/>" >
		</nested:iterate>
	</nested:notEmpty>
	<%-- 仕訳反転フラグ前回値 --%>
	<nested:hidden property="preReversalFlg" styleId="preReversalFlg" />

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
									<td class="fcTitle fb">売上入力</td>
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
										<table cellspacing="2" cellpadding="1" width="" border="0">
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail" width="70">売上番号</td>
												<td width="100">
													<nested:write property="salesNo" />
												</td>
												<td class="bcTitleSearch fb fcTitleSearch">発行済</td>
												<td >
													<nested:write property="slipPublishCompName" />
												</td>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail" width="70">売上日</td>
												<td>
													<nested:text property="strSalesDate" maxlength="10" size="10" styleId="strSalesDate" style="ime-mode:disabled" onchange="setDirtyFlg();changeSalesDate();calcSalesAmount();" />
												</td>
												<td class="fcTitleDetail fb bcTitleDetail" width="70">勘定年月</td>
												<td >
													<nested:text property="strAccountYears" maxlength="7" size="10" styleId="strAccountYears" style="ime-mode:disabled" onchange="setDirtyFlg();" />
												</td>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail" width="70">売上区分</td>
												<td colspan="4">
													<nested:equal property="insertFlg" value="1">
														<nested:select property="categoryDivision" style="margin: 0;padding: 0" styleId="categoryDivision" onchange="changeReturnsField(); categoryDivisionAction(this); setDirtyFlg()" >
															<nested:optionsCollection property="categoryCombo" label="labales" value="values" />
														</nested:select>
													</nested:equal>
													<nested:notEqual property="insertFlg" value="1">
														<nested:hidden property="categoryDivision" styleId="categoryDivision" />
														<nested:write property="categoryName" />
													</nested:notEqual>
												</td>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail" width="70">得意先　</td>
												<td width="100">
													<nested:equal property="insertFlg" value="1">
														<nested:text property="venderCd" maxlength="15" size="12" styleId="venderCd" />
													</nested:equal>
													<nested:notEqual property="insertFlg" value="1">
														<nested:write property="venderCd" />
														<nested:hidden property="venderCd" styleId="venderCd" />
													</nested:notEqual>
													<div id="autocomplete_selection" class="autocomplete"></div>
												</td>
												<td width="200" colspan="2">
													<nested:text property="venderShortedName" size="22" readonly="true" styleId="venderShortedName" styleClass="noborderAl" tabindex="-1"/>
												</td>
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
										<table width="" cellspacing="2" cellpadding="1" border="0">
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
											</tr>
											<tr class="bcList2">
												<td><%-- 品目コード --%>
													<nested:equal property="insertFlg" value="1">
														<nested:text property="itemCd" maxlength="20" size="6" styleId="itemCd" onchange="setDirtyFlg()" />
													</nested:equal>
													<nested:notEqual property="insertFlg" value="1">
														<nested:write property="itemCd" />
														<nested:hidden property="itemCd" styleId="itemCd" />
													</nested:notEqual>
												</td>
												<td><%-- 他社コード1 --%>
													<nested:equal property="insertFlg" value="1">
														<nested:text property="otherCompanyCd1" maxlength="20" size="6" styleId="otherCompanyCd1" onchange="setDirtyFlg()" />
													</nested:equal>
													<nested:notEqual property="insertFlg" value="1">
														<nested:write property="otherCompanyCd1" />
													</nested:notEqual>
												</td>
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
														<nested:text property="strSalesQuantity" maxlength="22" size="2" styleId="strSalesQuantity" onchange="setFocusPosition('strSalesQuantity');formatSalesQuantity(this); calcSalesAmount(); setDirtyFlg();" style="ime-mode:disabled" disabled="true"/>
													</td>
												</nested:notEqual>
<%-- 返品の場合(標準単価) --%>
											 	<%-- 月次更新後 --%>
												<nested:equal property="monthlyUpdateDivision" value="1">
													<td>
														<nested:text property="strStandardUnitprice" maxlength="" size="2" styleId="strStandardUnitprice" onchange="setFocusPosition('strStandardDiscount');formatUrTanka(this); setDirtyFlg()" style="ime-mode:disabled" disabled="true"/>
													</td>
												</nested:equal>
											 	<%-- 月次更新前 --%>
												<nested:notEqual property="monthlyUpdateDivision" value="1">
											 		<%-- 区分が取消の場合 --%>
													<nested:equal property="categoryDivision" value="2">
														<td>
															<nested:text property="strStandardUnitprice" maxlength="" size="2" styleId="strStandardUnitprice" onchange="setFocusPosition('strStandardDiscount');formatUrTanka(this); setDirtyFlg()" style="ime-mode:disabled"/>
														</td>
													</nested:equal>
													<nested:equal property="categoryDivision" value="12">
														<td>
															<nested:text property="strStandardUnitprice" maxlength="" size="2" styleId="strStandardUnitprice" onchange="setFocusPosition('strStandardDiscount');formatUrTanka(this); setDirtyFlg()" style="ime-mode:disabled"/>
														</td>
													</nested:equal>

											 		<%-- 区分が取消以外の場合 --%>
													<nested:notEqual property="categoryDivision" value="2">
														<nested:notEqual property="categoryDivision" value="12">

															<nested:equal property="insertFlg" value="1">
																<td>
																	<nested:text property="strStandardUnitprice" maxlength="" size="2" styleId="strStandardUnitprice" onchange="setFocusPosition('strStandardDiscount');formatUrTanka(this); setDirtyFlg()" style="ime-mode:disabled"/>
																</td>
															</nested:equal>

															<nested:notEqual property="insertFlg" value="1">
																<td>
																	<nested:text property="strStandardUnitprice" maxlength="" size="2" styleId="strStandardUnitprice" onchange="setFocusPosition('strStandardDiscount');formatUrTanka(this); setDirtyFlg()" style="ime-mode:disabled" disabled="true"/>
																</td>
															</nested:notEqual>
														</nested:notEqual>
													</nested:notEqual>
												</nested:notEqual>


<%-- 返品の場合(標準値引) --%>
											 	<%-- 月次更新後 --%>
												<nested:equal property="monthlyUpdateDivision" value="1">
													<td>
														<nested:text property="strStandardDiscount" maxlength="" size="2" styleId="strStandardDiscount" onchange="setFocusPosition('strSpecialDiscount');formatUrTanka(this); setDirtyFlg()" style="ime-mode:disabled" disabled="true"/>
													</td>
												</nested:equal>
											 	<%-- 月次更新前 --%>
												<nested:notEqual property="monthlyUpdateDivision" value="1">
											 		<%-- 区分が取消の場合 --%>
													<nested:equal property="categoryDivision" value="2">
														<td>
															<nested:text property="strStandardDiscount" maxlength="" size="2" styleId="strStandardDiscount" onchange="setFocusPosition('strSpecialDiscount');formatUrTanka(this); setDirtyFlg()" style="ime-mode:disabled"/>
														</td>
													</nested:equal>
													<nested:equal property="categoryDivision" value="12">
														<td>
															<nested:text property="strStandardDiscount" maxlength="" size="2" styleId="strStandardDiscount" onchange="setFocusPosition('strSpecialDiscount');formatUrTanka(this); setDirtyFlg()" style="ime-mode:disabled"/>
														</td>
													</nested:equal>

											 		<%-- 区分が取消以外の場合 --%>
													<nested:notEqual property="categoryDivision" value="2">
														<nested:notEqual property="categoryDivision" value="12">

															<nested:equal property="insertFlg" value="1">
																<td>
																	<nested:text property="strStandardDiscount" maxlength="" size="2" styleId="strStandardDiscount" onchange="setFocusPosition('strSpecialDiscount');formatUrTanka(this); setDirtyFlg()" style="ime-mode:disabled"/>
																</td>
															</nested:equal>

															<nested:notEqual property="insertFlg" value="1">
																<td>
																	<nested:text property="strStandardDiscount" maxlength="" size="2" styleId="strStandardDiscount" onchange="setFocusPosition('strSpecialDiscount');formatUrTanka(this); setDirtyFlg()" style="ime-mode:disabled" disabled="true"/>
																</td>
															</nested:notEqual>
														</nested:notEqual>
													</nested:notEqual>
												</nested:notEqual>

<%-- 返品の場合(特別値引) --%>
											 	<%-- 月次更新後 --%>
												<nested:equal property="monthlyUpdateDivision" value="1">
													<td>
														<nested:text property="strSpecialDiscount" maxlength="" size="2" styleId="strSpecialDiscount" onchange="setFocusPosition('tmpUnitpriceFlg');formatUrTanka(this); setDirtyFlg()" style="ime-mode:disabled" disabled="true"/>
													</td>
												</nested:equal>
											 	<%-- 月次更新前 --%>
												<nested:notEqual property="monthlyUpdateDivision" value="1">
											 		<%-- 区分が取消の場合 --%>
													<nested:equal property="categoryDivision" value="2">
														<td>
															<nested:text property="strSpecialDiscount" maxlength="" size="2" styleId="strSpecialDiscount" onchange="setFocusPosition('tmpUnitpriceFlg');formatUrTanka(this); setDirtyFlg()" style="ime-mode:disabled"/>
														</td>
													</nested:equal>
													<nested:equal property="categoryDivision" value="12">
														<td>
															<nested:text property="strSpecialDiscount" maxlength="" size="2" styleId="strSpecialDiscount" onchange="setFocusPosition('tmpUnitpriceFlg');formatUrTanka(this); setDirtyFlg()" style="ime-mode:disabled"/>
														</td>
													</nested:equal>

											 		<%-- 区分が取消以外の場合 --%>
													<nested:notEqual property="categoryDivision" value="2">
														<nested:notEqual property="categoryDivision" value="12">

															<nested:equal property="insertFlg" value="1">
																<td>
																	<nested:text property="strSpecialDiscount" maxlength="" size="2" styleId="strSpecialDiscount" onchange="setFocusPosition('tmpUnitpriceFlg');formatUrTanka(this); setDirtyFlg()" style="ime-mode:disabled"/>
																</td>
															</nested:equal>

															<nested:notEqual property="insertFlg" value="1">
																<td>
																	<nested:text property="strSpecialDiscount" maxlength="" size="2" styleId="strSpecialDiscount" onchange="setFocusPosition('tmpUnitpriceFlg');formatUrTanka(this); setDirtyFlg()" style="ime-mode:disabled" disabled="true"/>
																</td>
															</nested:notEqual>
														</nested:notEqual>
													</nested:notEqual>
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
											</tr>
										</table>
										<table border="0" cellpadding="2" cellspacing="1">
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail" width="70">合計金額</td>
												<td width="100px" class="alignRight">
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
												<td>
													<%-- <nested:select property="strTaxRatio" style="margin: 0;padding: 0" onchange="setFocusPosition('strTaxRatio');setDirtyFlg();calcSalesAmount();">
														<nested:optionsCollection property="taxCombo" label="labales" value="values" /> --%>
													<!-- 軽減税率対応 -->
													<nested:select property="taxCd" style="margin: 0;padding: 0" onchange="setFocusPosition('taxCd');setDirtyFlg();setTaxCdChangeFlg();calcSalesAmount();">
														<nested:options property="taxValues" labelProperty="taxLabels"/>
													</nested:select>
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
												<td class="fcTitleDetail fb bcTitleDetail" width="70">納入先</td>
												<td>
													<nested:equal property="insertFlg" value="1">
														<span id="cssButton">
															<!-- 20211209 Asclab Kouchi EdgeIE対応 -->
															<a href="#"  class="cssButton" onclick="<%="open_modal_popup_edge(765, 400,'SalesDeliverySearch', '', 'srhDivision', '1'); setDirtyFlg(); return false;"%>">
																選
															</a>&nbsp;
														</span>
													</nested:equal>
													<span id="lblDeliveryCd">
														<nested:write property="deliveryCd" />
													</span>
													<nested:hidden property="deliveryCd" styleId="deliveryCd" />
												</td>
												<td colspan="3">
													<div id="lblSearchKana">
														<nested:write property="searchKana" />
													</div>
													<nested:hidden property="searchKana" styleId="searchKana" />
												</td>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail" width="70">住所</td>
												<td>
													<nested:text property="address" size="22" readonly="true" styleId="address" styleClass="noborderAl" tabindex="-1"/>
												</td>
												<td class="fcTitleDetail fb bcTitleDetail" width="70">電話番号</td>
												<td>
													<nested:text property="telNo" size="22" readonly="true" styleId="telNo" styleClass="noborderAl" tabindex="-1"/>
												</td>
											</tr>
										</table>
										<table cellspacing="2" cellpadding="1" id="tblList" border="0" >
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail">
													仕訳
												</td>
												<td width="200"></td>
											</tr>
											<tr>
												<td colspan="2" valign="Top">
													<table cellspacing="2" cellpadding="1" id="tblList" border="1" >
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
																<nested:text property="accountDebitSectionCd" maxlength="10" size="10" styleId="accountDebitSectionCd"/>
															</td>
															<td width="80px">
																<div id="lblAccountDebitSectionName">
																	<nested:write property="accountDebitSectionName" />
																</div>
																<nested:hidden property="accountDebitSectionName" styleId="accountDebitSectionName" />
															</td>
															<td><%-- 会計部門貸方コード --%>
																<nested:text property="accountCreditSectionCd" maxlength="10" size="10" styleId="accountCreditSectionCd"/>
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
																<nested:text property="debitTitleCd" maxlength="10" size="10" styleId="debitTitleCd"/>
															</td>
															<td width="80px">
																<div id="lblDebitTitleName">
																	<nested:write property="debitTitleName" />
																</div>
																<nested:hidden property="debitTitleName" styleId="debitTitleName" />
															</td>
															<td><%-- 貸方科目コード< --%>
																<nested:text property="creditTitleCd" maxlength="10" size="10" styleId="creditTitleCd"/>
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
										<div id="divReturns" style="display: none;" >
											<table cellspacing="2" cellpadding="1"  border="0">
												<tr>
													<td height="5"></td>
												</tr>
												<tr>
													<td class="fcTitleDetail fb bcTitleDetail" width="120">入庫ロケーション</td>
													<td colspan="3">
														<nested:select property="housingLocationCd" style="margin: 0;padding: 0" styleId="housingLocationCd">
															<nested:optionsCollection property="housingLocationCombo" label="labales" value="values" />
														</nested:select>
													</td>
												</tr>
												<tr>
													<td class="fcTitleDetail fb bcTitleDetail">包装指図番号</td>
													<td>
														<nested:text property="packageDirectionNo" maxlength="11" size="12" styleId="packageDirectionNo" style="ime-mode:disabled"/>
													</td>
													<td class="fcTitleDetail fb bcTitleDetail">製品ロット番号</td>
													<td>
														<nested:text property="productLotno" maxlength="20" size="12" styleId="productLotno" />
													</td>
												</tr>
											</table>
										</div>

										<div id="divInout" style="display: none;" >
										<table cellspacing="2" cellpadding="1"  border="0">

											<tr>
												<td height="5"></td>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail" width="120">受払選択</td>

												<td colspan="2">
													<span id="cssButton">
														<!-- 20211209 Asclab Kouchi EdgeIE対応 -->
														<a href="#"  class="cssButton" onclick="<%="open_modal_popup_edge(765, 600,'SalesInoutSearch', '','srhItemCd', $('itemCd').value,'srhVenderCd', $('venderCd').value,'srhSalesDate',$('strSalesDate').value); setDirtyFlg(); return false;"%>">
															選
														</a>&nbsp;
													</span>
												</td>
											</tr>
											<tr>

												<table border="0" cellpadding="0" cellspacing="0" width="750">
													<tr>
														<td>
															<table width="100%" cellspacing="2" cellpadding="1" id="tblList">
																<tr class="bcTitleList fb fcTitleList">
																	<td>ロット番号</td>
																	<td>数量</td>
																	<td>重量</td>
																	<td>受払日付</td>
																	<td>理由</td>
																	<td>登録者</td>
																</tr>

																<tr>
																	<nested:hidden property="inoutQty" />
																	<nested:hidden property="inoutWeight" />
																	<%-- ロット番号 --%>
																	<td class="alignLeft">
																		<nested:text property="inoutLotNo" maxlength="9" size="8" readonly="true" styleId="inoutLotNo" styleClass="noborderAl" tabindex="-1"/>
																	</td>

																	<%-- 数量 --%>
																	<td class="alignRight">
																		<span id="lblInoutQty"><nested:write property="inoutQty"/>
																	</td>

																	<%-- 重量 --%>
																	<td class="alignRight">
																		<span id="lblInoutWeight"><nested:write property="inoutWeight"/>
																	</td>

																	<%-- 受払日付 --%>
																	<td class="alignLeft">
																		<nested:text property="inoutDate" maxlength="9" size="8" readonly="true" styleId="inoutDate" styleClass="noborderAl" tabindex="-1"/>
																	</td>

																	<%-- 理由 --%>
																	<td class="alignLeft">
																		<nested:text property="ryName" maxlength="25" size="25" readonly="true" styleId="ryName" styleClass="noborderAl" tabindex="-1"/>
																	</td>

																	<%-- 登録者 --%>
																	<td class="alignLeft">
																		<nested:text property="inputorName" maxlength="10" size="10" readonly="true" styleId="inputorName" styleClass="noborderAl" tabindex="-1"/>
																	</td>

																</tr>
															</table>
														</td>
													</tr>
												</table>

											</tr>
										</table>
										</div>

										<table cellspacing="2" cellpadding="1"  border="0">
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail" width="120">理由コード</td>
												<td colspan="3">
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
												<td colspan="3">
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
													 	<%-- 新規 --%>
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
																		<a href="#" onclick="if (!(putConfirm())) {return false;}else{return form_submit('op', 'update'); return false;}" class="cssButton">
																			&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
																		</a>
																		&nbsp;&nbsp;&nbsp;&nbsp;
																	</nested:equal>
																	<nested:equal property="deleteAuthority" value="true">
																		<a href="#" onclick="if (!(deleteAlert())) {return false;}else{return form_submit('op', 'delete'); return false;}" class="cssButton">
																			&nbsp;&nbsp;削&nbsp;&nbsp;除&nbsp;&nbsp;
																		</a>
																		&nbsp;&nbsp;&nbsp;&nbsp;
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
															 	<%-- 月次更新前 --%>
																<nested:equal property="monthlyUpdateDivision" value="0">
																	<nested:equal property="deleteAuthority" value="true">
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
