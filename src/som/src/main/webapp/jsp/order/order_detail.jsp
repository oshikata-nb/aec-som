<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taginclude.jsf"%>

<html:html>
<head>
<%@ include file="/jsp/common/head.jsf"%>
<%@ include file="/jsp/common/head_main.jsf"%>

<%-- ▼業務固有css ここから
<style type="text/css">
</style>
<%-- ▲業務固有css ここまで --%>

<%-- ▼業務固有javaScript ここから --%>
<script language="JavaScript" type="text/javascript">
	<%-- Load時の処理 --%>
	Event.observe(window, 'load', function() {
		<%-- フォーム設定の初期化 --%>
		initializeFormState();

		<%-- 必須フィールド定義 --%>
		defineAsRequiredField("strOrderDate");
		defineAsRequiredField("strScheduledShippingDate");
		defineAsRequiredField("strDeliveryExpectedDate");
		defineAsRequiredField("deliveryCd");

		//リスト
		if (tblList != null) {
			if (0 < tblList.rows.length) {
				for (i = 0; i < $("orderDetailListCount").value; i++) {
					if( $("itemCd" + i) != null){
						defineAsRequiredField("itemCd" + i);
					}
					if( $("strOrderQty" + i) != null){
						defineAsRequiredField("strOrderQty" + i);
					}
				}
			}
		}

		<%-- 日付型フィールド定義 --%>
		defineAsDateField("strOrderDate");
		defineAsDateField("strScheduledShippingDate");
		defineAsDateField("strSuggestedDeliverlimit");
		defineAsDateField("strDeliveryExpectedDate");

		<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
		storeInitValues('dirtyFlg');

		<%-- 数値型フィールド定義 --%>
		defineAsNumberField("strCarryFare");
		//リスト
		if (tblList != null) {
			if (0 < tblList.rows.length) {
				for (i = 0; i < $("orderDetailListCount").value; i++) {
					if( $("strOrderQty" + i) != null){
						defineAsNumberField("strOrderQty" + i);
					}
					if( $("strMatss" + i) != null){
						defineAsNumberField("strMatss" + i);
					}
				}
			}
		}

		<%-- 納入先のauto complete --%>
		autocompleteDeriveryCd();
		<%-- 品目のautocomplete --%>
		autocompleteItemCd();
		<%-- 他社コード1のautocomplete --%>
		autocompleteOtherCompanyCd1();

		<%-- カーソル位置セット --%>
		setActivate();

		<%-- 検索後入力された場合の不整合をただす --%>
	}, false);
	
	<%-- 納入先のauto complete 20200709--%>
	function autocompleteDeriveryCd(){
		new Ajax.Autocompleter(
			"deliveryCd",
			"autocomplete_delivery",
			"<%= request.getContextPath() %>/DeliveryDivisionForAutoComplete.do?op=searchDeliveryDivision",
			{
				parameters : 'deliveryDivision='+$("deliveryDivision").value,
				paramName : "code",
				afterUpdateElement : setDeliveryLabel
			}
		);
		Event.observe('deliveryCd', 'keypress', clearLabel.bindAsEventListener($('deliveryName1')), false);
		Event.observe('deliveryCd', 'keypress', clearLabel.bindAsEventListener($('eigyoTantoName')), false);
		Event.observe('deliveryCd', 'keypress', clearLabel.bindAsEventListener($('eigyoTantoCd')), false);
		Event.observe('deliveryCd', 'keypress', clearLabel.bindAsEventListener($('carryCd')), false);
		Event.observe('deliveryCd', 'keypress', clearText.bindAsEventListener($('strDeliveryExpectedDate')), false);
		Event.observe('deliveryCd', 'keypress', clearLabel.bindAsEventListener($('address')), false);
		Event.observe('deliveryCd', 'keypress', clearText.bindAsEventListener($('telNo')), false);
//		Event.observe('deliveryCd', 'keypress', clearLabel.bindAsEventListener($('strLeadTime')), false);
		Event.observe('deliveryCd', "keypress", clearCheckbox.bindAsEventListener($('carryInvoiceFlag')), false);
	}
	
	<%-- 品目のauto complete --%>
	function autocompleteItemCd(){
		var count = $F("orderDetailListCount");
		for(var i = 0 ; i < count ; i++){
			new Ajax.Autocompleter(
			  "itemCd" + i,
			  "autocomplete_selection",
			  "<%= request.getContextPath() %>/OrderItemForAutoComplete.do?op=searchItem",
			  {
			  	paramName : "code",
			  	callback : eval("itemCdCallBack"+ i),
				afterUpdateElement : eval("setInputItemLabel" + i)
			  }
			);
			Event.observe("itemCd"+i, "keypress", clearLabel.bindAsEventListener($("itemName"+i)), false);
			Event.observe("itemCd"+i, "keypress", clearText.bindAsEventListener($("otherCompanyCd1"+i)), false);
			Event.observe("itemCd"+i, "keypress", clearLabel.bindAsEventListener($("styleOfPacking"+i)), false);
			Event.observe("itemCd"+i, "keypress", clearText.bindAsEventListener($("strOrderQty"+i)), false);
			Event.observe("itemCd"+i, "keypress", clearDivLabel.bindAsEventListener($("strStandardUnitprice"+i)), false);
			Event.observe("itemCd"+i, "keypress", clearDivLabel.bindAsEventListener($("strStandardDiscount"+i)), false);
			Event.observe("itemCd"+i, "keypress", clearDivLabel.bindAsEventListener($("strSpecialDiscount"+i)), false);
			Event.observe("itemCd"+i, "keypress", clearDivLabel.bindAsEventListener($("strOrderAmount"+i)), false);
			Event.observe("itemCd"+i, "keypress", clearCheckbox.bindAsEventListener($("blnTmpUnitpriceFlg"+i)), false);
			Event.observe("itemCd"+i, "keypress", clearText.bindAsEventListener($("strMatss"+i)), false);
			Event.observe("itemCd"+i, "keypress", clearDivLabel.bindAsEventListener($("statusName"+i)), false);
		}
	}

	<%-- 他社コード１のauto complete --%>
	function autocompleteOtherCompanyCd1(){
		var count = $F("orderDetailListCount");
		for(var i = 0 ; i < count ; i++){
			new Ajax.Autocompleter(
			  "otherCompanyCd1" + i,
			  "autocomplete_selection",
			  "<%= request.getContextPath() %>/OrderItemForAutoComplete.do?op=searchItemOtherCompany",
			  {
			  	paramName : "code",
			  	callback : eval("itemCdCallBack" + i),
				afterUpdateElement : eval("setInputOtherCompanyLabel" + i)
			  }
			);
		}
	}
	<%-- 納入先コードオートコンプリート後の処理 20200709--%>
	function setDeliveryLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
	    $("deliveryName1").value = li.getElementsByTagName('span')[0].innerText;
	    $("lblDeliveryName1").update($F("deliveryName1"));
	    form_submit('op','getDeliveryRelatedData');
		refreshLabel();
		
	}

	<logic:iterate id="list" name="orderDetailForm" property="orderDetailList" indexId="index">
		<%-- 品目コードオートコンプリート検索用キー項目取得 --%>
		function itemCdCallBack<bean:write name='index'/>(editor,paramText){
			return paramText + '&deliveryCd=' + $('deliveryCd').value
							 + '&orderDivision=' + $('orderDivision').value
							 + '&balanceCd='+ $('balanceCd').value;
		}
	</logic:iterate>

	<logic:iterate id="list" name="orderDetailForm" property="orderDetailList" indexId="index">
		<%-- 品目コードauto completeの選択後処理 --%>
		function setInputItemLabel<bean:write name='index'/>(text, li) {
			i = <bean:write name='index'/>;

			$("otherCompanyCd1" + i).value = getHiddenValue(li,"otherCompanyCd1");
			$("unitOfOperationManagement" + i).value = getHiddenValue(li,"unitOfOperationManagement");

			$("lblItemName" + i).innerHTML = li.getElementsByTagName('span')[0].innerText;
			$("itemName" + i).value = li.getElementsByTagName('span')[0].innerText;
			$("lblStyleOfPacking" + i).innerHTML = getHiddenValue(li,"styleOfPacking");
			$("styleOfPacking" + i).value = getHiddenValue(li,"styleOfPacking");

			$("lblBalanceCd").innerHTML = getHiddenValue(li,"balanceCd");
			$("balanceCd").value = getHiddenValue(li,"balanceCd");
			$("varidUnitpriceRow").value = i;

			$("statusName" + i).innerHTML = "";
			setCursor('1');
			setCursorIndex(i);
			form_submit('op', 'getunitprice');
		}
	</logic:iterate>

	<logic:iterate id="list" name="orderDetailForm" property="orderDetailList" indexId="index">
		<%-- 他社コード１auto completeの選択後処理 --%>
		function setInputOtherCompanyLabel<bean:write name='index'/>(text, li) {
			i = <bean:write name='index'/>;
			$("itemCd" + i).value = getHiddenValue(li,"code");
			$("unitOfOperationManagement" + i).value = getHiddenValue(li,"unitOfOperationManagement");

			$("lblItemName" + i).innerHTML = li.getElementsByTagName('span')[0].innerText;
			$("itemName" + i).value = li.getElementsByTagName('span')[0].innerText;
			$("lblStyleOfPacking" + i).innerHTML = getHiddenValue(li,"styleOfPacking");
			$("styleOfPacking" + i).value = getHiddenValue(li,"styleOfPacking");

			$("lblBalanceCd").innerHTML = getHiddenValue(li,"balanceCd");
			$("balanceCd").value = getHiddenValue(li,"balanceCd");
			$("varidUnitpriceRow").value = i;

			$("statusName" + i).innerHTML = "";
			setCursor('2');
			setCursorIndex(i);

			form_submit('op', 'getunitprice');
		}
	</logic:iterate>


	<%-- 確認メッセージ --%>
	function putConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "登録してよろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- 確認メッセージ:行削除 --%>
	function putDelListConfirm() {
		if (!noCheck()) {
		  return false;
		}
		alertMsg = new Array();
		alertMsg[0] = "行を削除してよろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- チェックボックス選択無しチェック --%>
	function noCheck(){
		var count = $F("orderDetailListCount");
		var checkFlag = false;
		for (var i = 0; i < count; i++) {
			var check = $("checkline" + i).checked;
			if(check){
				checkFlag = true;
				break;
			}
		}
		if (!checkFlag) {
		    alert("削除対象がありません。");
		    return false;
		}
		return true;
	}

	<%-- 変更確認メッセージ --%>
	function putDirtyConfirm() {
		var flg = $("dirtyFlg").checked;
		if (flg) {
			/* 何か値が変更されている場合 */
			return continueConfirm();
		}else{
			return true;
		}
	}

	<%-- ファイルクリアメッセージ --%>
	function clearOrderPicture() {
		alertMsg = new Array();
		ret =confirm("注文書情報を削除します。よろしいですか？\n※登録処理を実行するまで削除されません。");
		if (ret){
			$("orderPicture").value = "";
		}
		return ret;
	}

	<%-- 変更フラグセット --%>
	function setDirtyFlg() {
		$("dirtyFlg").checked = true;
	}


	<%-- ポップアップ後の設定処理 --%>
	function setValues() {
		if (arguments[1]) {
			if ("OrderDeliverySearch" == arguments[0]) {
				<%-- 納入先検索後の設定処理 --%>
				var i=2;
				$("deliveryCd").value = arguments[i++];
				$("deliveryName1").value = arguments[i++];
				$("address").value = arguments[i++];
				$("telNo").value = arguments[i++];

				$("lblDeliveryName1").update($F("deliveryName1"));
				$("lblAddress").update($F("address"));

				var carryInFlag = arguments[i++];
				if (carryInFlag == 0) {
					$("carryInvoiceFlag").checked = false;
				} else {
					$("carryInvoiceFlag").checked = true;
				}
				$("eigyoTantoCd").value = arguments[i++];
				$("eigyoTantoName").value = arguments[i++];
				$("strLeadTime").value = arguments[i++];

				$("deliveryExpectedTime").value = arguments[i++];

				$("tempCarryCd").value = arguments[i];
				$("carryCd").value = arguments[i++];

				$("lblBalanceCd").innerHTML = "";
				$("balanceCd").value = "";

				$("organizationCd").innerHTML = "";
				$("organizationName").innerHTML = "";

				var count = $F("orderDetailVenderListCount");
				for(var i = 0 ; i < count ; i++){
					$("shopLevelName" + i).innerHTML = "";
					$("venderCd" + i).innerHTML = "";
					$("venderName1" + i).innerHTML = "";
				}

				//納入予定日自動計算と品目削除処理
				form_submit('op', 'afterInputDeliveryCd');
			}
		}
	}

	<%-- 増付数計算--%>
	function calcMatss(i) {
		var strOrderQty = $("strOrderQty" + i).value; //受注数量取得
		var estimateStandardAmount = $("strEstimateStandardAmount" + i).value;
		var estimateMatss = $("strEstimateMatss" + i).value;

		var strOrderQty2 = parseFloat(strOrderQty.replace(/,/g, "")); //カンマを取りフロートへ変換
		var estimateStandardAmount2 = parseFloat(estimateStandardAmount.replace(/,/g, "")); //カンマを取りフロートへ変換
		var estimateMatss2 = parseFloat(estimateMatss.replace(/,/g, "")); //カンマを取りフロートへ変換

		var matss;
		if( !isNaN(strOrderQty2)
			&& !isNaN(estimateStandardAmount2)
			&& !isNaN(estimateMatss2)){ //数値であれば

			if (estimateStandardAmount2 == 0){
				matss = 0;
			}else{
				matss = Math.floor(strOrderQty2 / estimateStandardAmount2) * estimateMatss2;
			}
		} else {
			matss = 0;
		}
		$("strMatss" + i).value = matss;
		formatMatss($("strMatss" + i),i);
	}
	<%-- 金額計算 --%>
	function calc(i) {

		var strOrderQty = $("strOrderQty" + i).value; //受注数量取得
		var strOrderUnitprice = $("strOrderUnitprice" + i).value; //受注単価

		var itemSmallnumLength = $("itemSmallnumLength" + i).value; //小数点桁数取得
		var itemRoundDivision = $("itemRoundDivision" + i).value; //端数区分取得

		var strOrderQty2 = parseFloat(strOrderQty.replace(/,/g, "")); //受注数量のカンマを取りフロートへ変換
		var strOrderUnitprice2 = parseFloat(strOrderUnitprice.replace(/,/g, "")); //特別値引のカンマを取りフロートへ変換

		if( !isNaN(strOrderQty2) ){ //数値であれば
			if(isNaN(strOrderUnitprice2)){
				strOrderUnitprice2 = 0.0;
			}

			<%-- var strOrderAmount2 = strOrderQty2 * strOrderUnitprice2; --%>
			var strOrderAmount2 = multiFloat(strOrderUnitprice,strOrderQty,$F("decimalPointUrTanka"));

			//フォーマット
			strOrderAmount2 = digitFormat($F("decimalPointUrKingaku"), $F("roundUrKingaku"), strOrderAmount2);

			$("strOrderAmount" + i).update(strOrderAmount2);
			$("orderDetailList[" + i + "].strOrderAmount").value = strOrderAmount2;

		} else {
			//数値以外が入力されたら、合計を０とする

			strOrderAmount2 = digitFormat($F("decimalPointUrKingaku"), $F("roundUrKingaku"),"0");

			$("strOrderAmount" + i).update(strOrderAmount2);
			$("orderDetailList[" + i + "].strOrderAmount").value = strOrderAmount2;
		}

		if ((strOrderQty != null) && (strOrderQty != "")) {
			<%-- 小数点桁数・端数区分で数値を再フォーマット --%>
		    var res = digitStringFormat($F("itemSmallnumLength" + i), $F("itemRoundDivision" + i), strOrderQty);
			if (res != "") {
				$("strOrderQty" + i).value = res;
			}
		}


		listSum();//一覧合計を再計算
	}

	//合計計算
	function listSum() {
		var sumOrderAmount = 0.0;

		if ( $("orderDetailListCount").value != null){
			for (i = 0; i < $("orderDetailListCount").value; i++) {
				if( $("strOrderAmount" + i) != null){
					var strOrderAmount = $("strOrderAmount" + i).innerHTML;
					var strOrderAmount2 = parseFloat(strOrderAmount.replace(/,/g, "")); //カンマを取りフロートへ変換
					if( !isNaN(strOrderAmount2)){
						sumOrderAmount = sumOrderAmount + strOrderAmount2;
					}
				}
			}
			sumOrderAmount = digitFormat($F("decimalPointUrKingaku"), $F("roundUrKingaku"), sumOrderAmount);
			$("strSumOrderAmount").update(sumOrderAmount);
		}else{
			sumOrderAmount = digitFormat($F("decimalPointUrKingaku"), $F("roundUrKingaku"), "0");
			$("strSumOrderAmount").update(sumOrderAmount);
		}
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

	<%-- 増付数のフォーマット --%>
	function formatMatss(obj,i){
		var value = obj.value;
		if (value != null && value != "") {
			<%-- 数値フォーマット --%>
			var res = digitStringFormat($F("itemSmallnumLength" + i), $F("itemRoundDivision" + i), value);
			if (res != "") {
				$("strMatss" + i).value = res;
			}
		}
	}

	<%-- リードタイムのフォーマット --%>
	function formatLeadTime(obj){
		var value = obj.value;
		if (value != null && value != "") {
			<%-- 数値フォーマット --%>
			var res = digitStringFormat($F("decimalPointSonota"), $F("roundSonota"), value);
			if (res != "") {
				$("strLeadTime").value = res;
			}
		}
	}

	<%-- カーソルセット --%>
	function setCursor(pos) {
		$("cursor").value = pos;
	}

	<%-- カーソルセット --%>
	function setCursorIndex(pos) {
		$("cursorIndex").value = pos;
	}

	<%-- カーソル位置セット --%>
	function setActivate() {
		var str = new String;
		if ($('cursor').value == '1') {
			Field.activate($("itemCd" + $("cursorIndex").value));
		}else if ($('cursor').value == '2') {
			Field.activate($("otherCompanyCd1" + $("cursorIndex").value));
		}else if ($('cursor').value == 'deliveryCd'){
			Field.activate($("deliveryCd"));
		}
		$("cursor").value = null;
	}

	<%-- 受注日変更時処理 --%>
	function changeOrderDate() {
		<%-- 新規の場合 --%>
		<logic:equal name="orderDetailForm" property="newFlg" value="1">
			// 日付が正しい場合のみ処理
			if(setDate("strOrderDate") == true){
				//出荷予定日・納入予定日更新と全ての品目の単価を取得
				return form_submit('op', 'afterChangeOrderDate');
			}
		</logic:equal>
	}

	<%-- 出荷予定日変更時処理 --%>
	function changeScheduledShippingDate() {
		<%-- 新規の場合 --%>
		<logic:equal name="orderDetailForm" property="newFlg" value="1">
			// 日付が正しい場合のみ処理
			if(setDate("strScheduledShippingDate") == true){
				//納入予定日更新と全ての品目の単価を取得
				return form_submit('op', 'afterChangeScheduledShippingDate');
			}
		</logic:equal>
	}

	<%-- 日付の計算 --%>
	function setDate(strDate) {
		var flag = false;

		str = $(strDate).value;

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
				$(strDate).value = Year + "/" + Month + "/" + Day;
				flag = true;
			}
		}
		return flag;
	}

	<%-- テキストのバイト数チェック --%>
	function checkTextByte(text, maxByte){
		var count = 0;
		var checkedString = "";
		for(var i = 0; i < text.length; i++){
			var str = text.substring(i, i+1);
			var chr = str.charCodeAt(0);
			if(chr < 256 || (chr >= 0Xff61 && chr <= 0xff9f)){
				<%-- 半角 --%>
				count +=1;
			}
			else{
				<%-- 全角 --%>
				count +=2;
			}

			<%-- 最大バイト数との比較 --%>
			if(count <= maxByte){
				checkedString += str
			}
			else{
				<%-- 最大バイト数以下の文字列だけを返す --%>
				$("lblDeliverySlipSummeryDisp").update(checkedString);
				$("deliverySlipSummeryDisp").value = checkedString;
			}
		}

		<%-- 最大バイト数以下の文字列だけを返す --%>
		$("lblDeliverySlipSummeryDisp").update(checkedString);
		$("deliverySlipSummeryDisp").value = checkedString;

	}

	<%-- 納入予定日手動変更時の警告メッセージ --%>
	function showDeliveryDateAlert() {
		alertMsg = new Array();
		alertMsg[0] = "手動で納入予定日が変更されました。納入予定日の確認をお願いします。";
		alert(alertMsg[0]);
	}
</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/OrderDetail" method="post"
	onsubmit="return false;" styleId="mainForm" enctype="multipart/form-data" >
	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="dirtyFlg" styleId="dirtyFlg" />
	<nested:hidden property="newFlg" styleId="newFlg" />
	<nested:hidden property="orderDetailListCount" styleId="orderDetailListCount" />
	<nested:hidden property="orderDetailVenderListCount" styleId="orderDetailVenderListCount" />
	<nested:hidden property="varidUnitpriceRow" styleId="varidUnitpriceRow" />
	<nested:hidden property="venderCd" styleId="venderCd" />
	<nested:hidden property="unitDivisionUntin" styleId="unitDivisionUntin" />
	<nested:hidden property="roundUntin" styleId="roundUntin" />
	<nested:hidden property="decimalPointUntin" styleId="decimalPointUntin" />
	<nested:hidden property="decimalPointUrKingaku" styleId="decimalPointUrKingaku" />
	<nested:hidden property="roundUrKingaku" styleId="roundUrKingaku" />
	<nested:hidden property="decimalPointSonota" styleId="decimalPointSonota" />
	<nested:hidden property="roundSonota" styleId="roundSonota" />
	<nested:hidden property="tempCarryCd" styleId="tempCarryCd"  />
	<nested:hidden property="cursor" styleId="cursor" />
	<nested:hidden property="cursorIndex" styleId="cursorIndex" />
    <nested:hidden property="decimalPointUrTanka" styleId="decimalPointUrTanka" />
    <nested:hidden property="deliverySlipSummeryDisp" styleId="deliverySlipSummeryDisp" />
	<nested:hidden property="deliveryDivision" styleId="deliveryDivision" />
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
							<td class="fcTitle fb">受注入力</td>
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
					<td><!-- 明細部 -->
					<table width="100%" cellspacing="2" cellpadding="2" border="0">
						<tr>
							<td class="fcTitleDetail fb bcTitleDetail" width="120">受注番号</td>
							<td colspan="2">
								<nested:equal property="newFlg" value="0">
									<nested:write property="orderNo" />
								</nested:equal>
							</td>
						</tr>

						<tr>
							<td class="fcTitleDetail fb bcTitleDetail">受注日</td>
							<td colspan="2">
								<nested:text property="strOrderDate" maxlength="8" size="20" styleId="strOrderDate" onchange="setDirtyFlg(); changeOrderDate();"  style="ime-mode:disabled"/>
							</td>
							<td class="fcTitleDetail fb bcTitleDetail">受注区分</td>
							<td colspan="2">

								<%-- 新規の場合 (変更可能)--%>
								<nested:equal property="newFlg" value="1">
									<nested:select property="orderDivision" style="margin: 0;padding: 0" onchange="setDirtyFlg();">
										<nested:optionsCollection property="orderDivisionCombo" label="labales" value="values" />
									</nested:select>
								</nested:equal>
								<%-- 更新の場合(変更不可) --%>
								<nested:equal property="newFlg" value="0">
									<nested:select property="orderDivision" style="margin: 0;padding: 0" onchange="setDirtyFlg();" disabled="true">
										<nested:optionsCollection property="orderDivisionCombo" label="labales" value="values" />
									</nested:select>
									<nested:equal property="orderDivision" value="2">
										<nested:equal property="updateFlg" value="1">
											生産計画確定
										</nested:equal>
										<nested:equal property="updateFlg" value="0">
											生産計画未確定
										</nested:equal>
									</nested:equal>
								</nested:equal>
							</td>
						</tr>
						<tr>
							<td class="fcTitleDetail fb bcTitleDetail">納入先コード</td>
							<td>
								<nested:text property="deliveryCd" maxlength="10" size="12" styleId="deliveryCd" onchange="setDirtyFlg();"/>
								<div id="autocomplete_delivery" class="autocomplete" ></div>
							</td>
							<td>
								<span id="cssButton">
									<a href="#"  class="cssButton"
									   onclick="<%="open_modal_popup(765, 600,'OrderDeliverySearch', '', 'srhDivision', '1'); setDirtyFlg(); return false;"%>">
										選
									</a>&nbsp;
								</span>
								<nested:hidden property="deliveryCd" />
								<div id="lblDeliveryName1"><nested:write property="deliveryName1" /></div>
								<nested:hidden property="deliveryName1" styleId="deliveryName1" />
							</td>
							<td class="fcTitleDetail fb bcTitleDetail">納入先住所</td>
							<td colspan="2">
								<div id="lblAddress">
									<nested:write property="address" />
								</div>
								<nested:hidden property="address" styleId="address" />
							</td>
						</tr>
						<tr>
							<td class="fcTitleDetail fb bcTitleDetail">納入先宛先</td>
							<td colspan="2">
								<nested:text property="deliveryAddress" maxlength="60" size="20" styleId="deliveryAddress" onchange="setDirtyFlg();" />
							</td>
							<td class="fcTitleDetail fb bcTitleDetail">電話番号</td>
							<td colspan="2">
									<nested:text property="telNo" maxlength="13" size="15" styleId="telNo" onchange="setDirtyFlg();"  style="ime-mode:disabled"/>
							</td>
						</tr>
						<tr>
							<td class="fcTitleDetail fb bcTitleDetail">営業担当者</td>
							<td>
								<div id="lblEigyoTantoCd">
									<nested:write property="eigyoTantoCd" />
								</div>
								<nested:hidden property="eigyoTantoCd" styleId="eigyoTantoCd" />
							</td>
							<td>
								<div id="lblEigyoTantoName">
									<nested:write property="eigyoTantoName"/>
								</div>
								<nested:hidden property="eigyoTantoName" styleId="eigyoTantoName" />
							</td>
						</tr>

						<tr>
							<td class="fcTitleDetail fb bcTitleDetail">希望納期</td>
							<td colspan="2">
								<nested:text property="strSuggestedDeliverlimit" maxlength="8" size="20" styleId="strSuggestedDeliverlimit" onchange="setDirtyFlg();" style="ime-mode:disabled" />
							</td>
						</tr>
						<tr>
							<td class="fcTitleDetail fb bcTitleDetail">出荷予定日</td>
							<td colspan="2">
								<nested:text property="strScheduledShippingDate" maxlength="8" size="20" styleId="strScheduledShippingDate" onchange="setDirtyFlg();changeScheduledShippingDate();"  style="ime-mode:disabled"/>
							</td>
							<td class="fcTitleDetail fb bcTitleDetail">リードタイム</td>
							<td colspan="2">
								<div id="lblStrLeadTime">
									<nested:write property="strLeadTime"/>
									<nested:hidden property="strLeadTime" styleId="strLeadTime" />
								</div>
							</td>
						</tr>

						<tr>
							<td class="fcTitleDetail fb bcTitleDetail">運送会社</td>
							<td colspan="2">
								<nested:select property="carryCd" style="margin: 0;padding: 0" onchange="setDirtyFlg();">
									<nested:optionsCollection property="carryCombo" label="labales" value="values" />
								</nested:select>
							</td>
						</tr>

						<tr>
							<td class="fcTitleDetail fb bcTitleDetail">納入予定日</td>
							<td colspan="2">
								<nested:text property="strDeliveryExpectedDate" maxlength="8" size="20" styleId="strDeliveryExpectedDate" onchange="setDirtyFlg();showDeliveryDateAlert();"  style="ime-mode:disabled"/>
							</td>
							<td class="fcTitleDetail fb bcTitleDetail">納入時刻</td>
							<td colspan="2">
								<nested:text property="deliveryExpectedTime" maxlength="10" size="20" styleId="deliveryExpectedTime" onchange="setDirtyFlg();" />
							</td>
						</tr>

					</table>

					<table width="100%" cellspacing="2" cellpadding="2" border="0">
						<tr>
							<td colspan="6" align="left">
								<div id="cssButton">
									<a href="#" onclick="return form_submit('op', 'addlist'); return false;" class="cssButton">
										&nbsp;&nbsp;行&nbsp;追&nbsp;加&nbsp;&nbsp;
									</a>
&nbsp;&nbsp;&nbsp;&nbsp;
									<a href="#" onclick="if (!(putDelListConfirm())) {return false;}else{return form_submit('op', 'dellist'); return false;}" class="cssButton">
										&nbsp;&nbsp;行&nbsp;削&nbsp;除&nbsp;&nbsp;
									</a>
								</div>
							</td>
						</tr>
					</table>
					<table width="100%" cellspacing="2" cellpadding="2" border="0">
						<tr>
							<td>
							<table cellspacing="2" cellpadding="1" id="tblList">
								<tr class="bcTitleList fb fcTitleList">
									<td width="20"></td>
									<td width="45">品目 ｺｰﾄﾞ</td>
									<td width="45">他社 ｺｰﾄﾞ1</td>
									<td width="120">品目名称</td>
									<td width="50">荷姿</td>
									<td width="40">数量</td>
									<td width="20">増付数</td>
									<td width="40">標準単価</td>
									<td width="40">標準値引</td>
									<td width="40">特別値引</td>
									<td width="40">金額</td>
									<td width="20">仮単価</td>
									<td width="100">ステータス</td>
								</tr>
								<div id="autocomplete_selection" class="autocomplete"></div>
								<nested:iterate id="orderDetailList" property="orderDetailList" indexId="index">
									<app:modulo
										numerator='<%=pageContext.findAttribute("index").toString()%>'
										denominator="2" zero="false">
										<tr class="bcList1">
									</app:modulo>

									<app:modulo
										numerator='<%=pageContext.findAttribute("index").toString()%>'
										denominator="2" zero="true">
										<tr class="bcList2">
									</app:modulo>

									<%-- Itemごとの　運用管理単位、端数区分、小数点桁数を設定 --%>
									<nested:hidden property="unitOfOperationManagement" styleId="<%="unitOfOperationManagement" + pageContext.findAttribute("index").toString() %>" />
									<nested:hidden property="itemRoundDivision" styleId="<%="itemRoundDivision" + pageContext.findAttribute("index").toString() %>" />
									<nested:hidden property="itemSmallnumLength" styleId="<%="itemSmallnumLength" + pageContext.findAttribute("index").toString() %>" />
									<nested:hidden property="venderCd"/>
									<nested:hidden property="strEstimateStandardAmount" styleId="<%="strEstimateStandardAmount" + pageContext.findAttribute("index").toString() %>"/>
									<nested:hidden property="strEstimateMatss" styleId="<%="strEstimateMatss" + pageContext.findAttribute("index").toString() %>"/>
									<nested:hidden property="rowNo" styleId="<%="rowNo" + pageContext.findAttribute("index").toString() %>" />
									<nested:hidden property="strOrderUnitprice" styleId="<%="strOrderUnitprice" + pageContext.findAttribute("index").toString() %>"/>

									<td><%-- 行追加行削除用チェックボックス --%>
										<nested:checkbox property="checkline" styleId="<%="checkline" + pageContext.findAttribute("index").toString() %>"/>
									</td>
									<td><%-- 品目コード --%>
										<nested:text property="itemCd" maxlength="20" size="5" onfocus="this.select();" styleId="<%="itemCd" + pageContext.findAttribute("index").toString() %>" onchange="setCursor('1');setDirtyFlg();"/>
									</td>
									<td><%-- 他社コード１ --%>
										<nested:text property="otherCompanyCd1" maxlength="20" onfocus="this.select();" size="3" styleId="<%="otherCompanyCd1" + pageContext.findAttribute("index").toString() %>" onchange="setCursor('2');setDirtyFlg();"/>
									</td>
									<td><%-- 品目名称 --%>
										<html:link href="#" onclick='<%="open_modal_popup(730, 600,'OrderLotSearch', '', 'srhItemCd', $('itemCd" + pageContext.findAttribute("index").toString() + "').value); return false;"%>'>
											<div id="<%="lblItemName" + pageContext.findAttribute("index").toString() %>">
												<nested:write property="itemName" />
											</div>
										</html:link>
										<nested:hidden property="itemName" styleId="<%="itemName" + pageContext.findAttribute("index").toString() %>" />
									</td>
									<td><%-- 荷姿 --%>
										<div id="<%="lblStyleOfPacking" + pageContext.findAttribute("index").toString() %>">
											<nested:write property="styleOfPacking" />
										</div>
										<nested:hidden property="styleOfPacking" styleId="<%="styleOfPacking" + pageContext.findAttribute("index").toString() %>"/>
									</td>

									<td align="right"><%-- 受注数量 --%>
										<nested:text property="strOrderQty" onfocus="this.select();" onblur='<%="calcMatss("+ pageContext.findAttribute("index").toString() + "); " + "calc(" + pageContext.findAttribute("index").toString() + ");"%>' maxlength="20" size="1" styleClass="alignRight" styleId="<%="strOrderQty" + pageContext.findAttribute("index").toString() %>" onchange="setDirtyFlg();" style="ime-mode:disabled"/>
									</td>
									<td><%-- 増付数量 --%>
										<nested:text property="strMatss" onfocus="this.select();" styleId="<%="strMatss" + pageContext.findAttribute("index").toString() %>" maxlength="22" size="1" styleClass="alignRight" onchange="setDirtyFlg();" onblur="<%="formatMatss(this," + pageContext.findAttribute("index").toString() + ");"%>" style="ime-mode:disabled"/>
									</td>

									<td align="right"><%-- 標準単価 --%>
										<div id="<%="strStandardUnitprice" + pageContext.findAttribute("index").toString() %>">
											<nested:write property="strStandardUnitprice" />
										</div>
										<nested:hidden property="strStandardUnitprice" styleId="<%="strStandardUnitprice" + pageContext.findAttribute("index").toString() %>"/>
									</td>

									<td align="right"><%-- 標準値引 --%>
										<div id="<%="strStandardDiscount" + pageContext.findAttribute("index").toString() %>">
											<nested:write property="strStandardDiscount" />
										</div>
										<nested:hidden property="strStandardDiscount" styleId="<%="strStandardDiscount" + pageContext.findAttribute("index").toString() %>"/>
									</td>
									<td align="right"><%-- 特別値引 --%>
										<div id="<%="strSpecialDiscount" + pageContext.findAttribute("index").toString() %>">
											<nested:write property="strSpecialDiscount" />
										</div>
										<nested:hidden property="strSpecialDiscount" styleId="<%="strSpecialDiscount" + pageContext.findAttribute("index").toString() %>"/>
									</td>
									<td align="right"><%-- 受注金額 --%>
										<div id="<%="strOrderAmount" + pageContext.findAttribute("index").toString() %>">
											<nested:write property="strOrderAmount" />
										</div>
										<nested:hidden property="strOrderAmount" styleId="<%="strOrderAmount" + pageContext.findAttribute("index").toString() %>"/>
									</td>
									<td><%-- 仮単価フラグ --%>
										<nested:checkbox property="blnTmpUnitpriceFlg"  disabled="true" styleId="<%="blnTmpUnitpriceFlg" + pageContext.findAttribute("index").toString() %>"/>
									</td>
									<td><%-- ステータス --%>
										<div id="<%="statusName" + pageContext.findAttribute("index").toString() %>">
											<nested:write property="statusName" />
										</div>
										<nested:hidden property="statusName" />
									</td>
								</nested:iterate>
							</table>
							</td>
						</tr>
					</table>
					<table width="100%" cellspacing="2" cellpadding="2" border="0">
						<tr>
							<td class="fcTitleDetail fb bcTitleDetail" width="90">合計金額</td>
							<td colspan="1" align="right">
								<div id="strSumOrderAmount">
									<nested:write property="strSumOrderAmount" />
								</div>
							</td>
						</tr>

						<tr>
							<td class="fcTitleDetail fb bcTitleDetail" width="90">担当部署</td>
							<td colspan="1">
								<div id="organizationCd">
									<nested:write property="organizationCd" />
								</div>
							</td>
							<td colspan="1">
								<div id="organizationName">
									<nested:write property="organizationName" />
								</div>
							</td>
						</tr>

						<tr>
							<td class="fcTitleDetail fb bcTitleDetail" width="90">帳合コード</td>
							<td colspan="3">
								<div id="lblBalanceCd">
									<nested:write property="balanceCd" />
								</div>
								<nested:hidden property="balanceCd" styleId="balanceCd" />
							</td>
						</tr>
					</table>
					<table width="100%" cellspacing="2" cellpadding="2" border="0">
						<tr>
							<td colspan="3" valign="Top">
								<table border="1" cellspacing="2" cellpadding="1" >
									<tr class="bcTitleList fb fcTitleList">
										<td width="50px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
										<td width="110px">得意先コード</td>
										<td width="250px">得意先名</td>
										<td width="110px">電話番号</td>
									</tr>
									<nested:notEmpty property="orderDetailVenderList">
										<nested:iterate property="orderDetailVenderList" indexId="index">
											<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
												<tr class="bcList1">
											</app:modulo>
											<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
												<tr class="bcList2">
											</app:modulo>
													<td class="alignCenter">
														<div id="<%="shopLevelName" + pageContext.findAttribute("index").toString() %>">
															<nested:write property="shopLevelName" />
														</div>
													</td>
													<td>
														<div id="<%="venderCd" + pageContext.findAttribute("index").toString() %>">
															<nested:write property="venderCd" />
														</div>
													</td>
													<td width="220px">
														<div id="<%="venderName1" + pageContext.findAttribute("index").toString() %>">
															<nested:write property="venderName1" />
														</div>
													</td>
													<td width="110px">
														<div id="<%="telNo" + pageContext.findAttribute("index").toString() %>">
															<nested:write property="telNo" />
														</div>
													</td>
												</tr>
										</nested:iterate>
									</nested:notEmpty>
								</table>
							</td>
						</tr>
					</table>
					<table width="100%" cellspacing="2" cellpadding="2" border="0">

						<tr>
							<td class="fcTitleDetail fb bcTitleDetail">運賃</td>
							<td colspan="2">
								<nested:text property="strCarryFare" maxlength="22" size="20" styleId="strCarryFare" onchange="setDirtyFlg();　formatCarryFare(this);"  style="ime-mode:disabled"/>円
							</td>
							<td class="fcTitleDetail fb bcTitleDetail">運賃請求フラグ</td>
							<td colspan="2">
								<nested:checkbox property="carryInvoiceFlag" styleId="carryInvoiceFlag" onchange="setDirtyFlg();" />
							</td>
						</tr>

						<tr>
							<td class="fcTitleDetail fb bcTitleDetail">注文番号</td>
							<td colspan="2">
								<nested:text property="customerOrderNo" maxlength="30" size="20" styleId="customerOrderNo" onchange="setDirtyFlg();"  style="ime-mode:disabled"/>
							</td>
							<td colspan="2">
								<div id="cssButton">
									<a href="#" onclick="return form_submit('op', 'getRemarkList');" class="cssButton">
									参照取得
									</a>
								</div>
							</td>
						</tr>

						<tr>
							<td class="fcTitleDetail fb bcTitleDetail">備考(印字用)</td>
							<td colspan="5">
								<nested:textarea property="printSummery" cols="75" rows="3" styleId="printSummery" />
							</td>
						</tr>

						<tr>
							<td class="fcTitleDetail fb bcTitleDetail">自動表示　備考</td>
							<td colspan="5">
								<nested:textarea property="deliverySlipSummery" cols="75" rows="3" styleId="deliverySlipSummery"  onchange="setDirtyFlg();checkTextByte(this.value,42);"/>
							</td>
						</tr>

						<tr>
							<td class="fcTitleDetail fb bcTitleDetail">印字文字数チェック用</td>

							<td>
								<span id="lblDeliverySlipSummeryDisp">
									<nested:write property="deliverySlipSummeryDisp" />
								</span>
							</td>

						</tr>
						<tr>
							<td class="fcTitleDetail fb bcTitleDetail">参照</td>
							<td colspan="5">
								<nested:textarea property="orderSummery"  cols="75" rows="3" styleId="orderSummery"  onchange="setDirtyFlg();" />
							</td>
						</tr>

						<tr>
							<td class="fcTitleDetail fb bcTitleDetail">注文書画像</td>
							<td colspan="7">
								<nested:file property="uploadFile" size="20" onchange="setDirtyFlg();"/>
							</td>
						</tr>
						<tr>
							<td class="fcTitleDetail fb bcTitleDetail" width="80">注文書画像</td>
							<td colspan="6">
								<div id="cssButton">
								<nested:text property="orderPicture" styleId="orderPicture" readonly="true" size="40"/>
&nbsp;&nbsp;&nbsp;&nbsp;
									<a href="#" onclick="clearOrderPicture();return false;" class="cssButton">
										&nbsp;ク&nbsp;リ&nbsp;ア&nbsp;
									</a>
&nbsp;&nbsp;&nbsp;&nbsp;
									<a href="#" onclick="return form_submit('op', 'filedownload'); return false;" class="cssButton">
										&nbsp;DOWN&nbsp;
									</a>
								</div>
							</td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td class="alignCenter">
					<table cellpadding="0" cellspacing="0" width="450" border="0">
						<tr>
							<td colspan="3" height="2"></td>
						</tr>

						<tr>
							<%-- 新規の場合 --%>
							<nested:equal property="newFlg" value="1">
								<nested:equal property="updateAuthority" value="true">
									<td class="alignRight">
									<div id="cssButton"><a href="#"
										onclick="if(!putConfirm()){return false;}else{return form_submit('op', 'insert'); return false;}"
										class="cssButton"> &nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp; </a></div>
									</td>
									<td width="50"><br>
									</td>
								</nested:equal>
							</nested:equal>

							<%-- 更新の場合 --%>
							<nested:equal property="newFlg" value="0">
								<%-- 変更可  --%>
								<nested:equal property="updateFlg" value="0">
									<nested:equal property="updateAuthority" value="true">
										<td class="alignRight">
											<div id="cssButton"><a href="#"
												onclick="if(!putConfirm()){return false;}else{return form_submit('op', 'update'); return false;}"
												class="cssButton"> &nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp; </a>
											</div>
										</td>
										<td width="50">
											<br>
										</td>
									</nested:equal>
									<nested:equal property="deleteAuthority" value="true">
										<td class="alignCenter">
											<div id="cssButton"><a href="#"
												onclick="if(!deleteConfirm()){return false;}else{return form_submit('op', 'delete'); return false;}"
												class="cssButton"> &nbsp;&nbsp;削&nbsp;&nbsp;除&nbsp;&nbsp; </a>
											</div>
										</td>
										<td width="50"><br>
										</td>
									</nested:equal>
								</nested:equal>
								<%-- 変更不可　--%>
								<nested:equal property="updateFlg" value="1">

								</nested:equal>
							</nested:equal>

							<%-- 戻るボタンは常に表示 --%>
							<td class="alignLeft">
							<div id="cssButton"><a href="#"
								onclick="if(!putDirtyConfirm()){return false;}else{return form_submit('op', 'back'); return false;}"
								class="cssButton"> &nbsp;&nbsp;戻&nbsp;&nbsp;る&nbsp;&nbsp; </a></div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<nested:equal property="downloadFlg" value="true">
		<META HTTP-EQUIV="REFRESH" CONTENT="0; URL=<%= request.getContextPath() %>/FileDownload/">
	</nested:equal>
	<%-- 受注混雑不具合の為設定 --%>
	<input type="hidden" name="_dummy">
</nested:form>



</body>
</html:html>
