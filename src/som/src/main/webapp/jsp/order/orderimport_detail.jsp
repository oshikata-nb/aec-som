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

		initFocus();

		<%-- 必須フィールド定義 --%>
		defineAsRequiredField("orderDivision");
		defineAsRequiredField("orderDate");
		defineAsRequiredField("deliveryExpectedDate");
		defineAsRequiredField("scheduledShippingDate");
		if(1 == $("insertFlg").value){
			defineAsRequiredField("deliveryCd");
		}
		//リスト
		if (tblList != null) {
			if (0 < tblList.rows.length) {
				for (i = 0; i < $("orderImportDetailListCount").value; i++) {
					if( $("itemCd" + i) != null){
						defineAsRequiredField("itemCd" + i);
					}
					if( $("orderQty" + i) != null){
						defineAsRequiredField("orderQty" + i);
					}
				}
			}
		}

		<%-- 日付型フィールド定義 --%>
		defineAsDateField("orderDate");
		defineAsDateField("scheduledShippingDate");
		defineAsDateField("deliveryExpectedDate");
		defineAsDateField("suggestedDeliverlimit");

		<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
		storeInitValues('dirtyFlg');

		<%-- 数値型フィールド定義 --%>
		defineAsNumberField("carryFare");

		//リスト
		if (tblList != null) {
			if (0 < tblList.rows.length) {
				for (i = 0; i < $("orderImportDetailListCount").value; i++) {
					if( $("orderQty" + i) != null){
						defineAsNumberField("orderQty" + i);
					}
					if( $("matss" + i) != null){
						defineAsNumberField("matss" + i);
					}
					if($("orderQty" + i) != null && $("matss" + i) != null){
						calc(i);
					}
				}
			}
		}
		<%-- 納入先のauto complete --%>
		if(1 == $("insertFlg").value){
			autocompleteDeliveryCd();			
		}
		
		<%-- 品目のautocomplete --%>
		autocompleteItemCd();
		<%-- 他社コード1のautocomplete --%>
		autocompleteOtherCompanyCd1();

		<%-- 検索後入力された場合の不整合をただす --%>

		if (tblList != null) {
			if (0 < $("orderImportDetailListCount").value && 0 < $("scrollFlg").value) {
				var i = 0;

				if(1 == $("scrollFlg").value){
					i = $("orderImportDetailListCount").value - 1;
					var content = document.getElementById("itemCd" + i);
					var content_position = content.getBoundingClientRect();
					window.scrollTo( 0, content_position.top);
				}else if(2 == $("scrollFlg").value){
					var content = document.getElementById("addRowTop");
					var content_position = content.getBoundingClientRect();
					window.scrollTo( 0, content_position.top);

				}else if(3 == $("scrollFlg").value){
					i = $("orderImportDetailListCount").value - 1;
					var content = document.getElementById("itemCd" + i);
					content.focus();
					var content_position = content.getBoundingClientRect();
					window.scrollTo( 0, content_position.top);
				}
			}
		}
	}, false);

	<%-- 納入先のauto complete 20200709--%>
	function autocompleteDeliveryCd(){

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

		Event.observe('deliveryCd', 'keypress', clearLabel.bindAsEventListener($('deliveryName')), false);
		Event.observe('deliveryCd', 'keypress', clearLabel.bindAsEventListener($('carryCd')), false);
		Event.observe('deliveryCd', 'keypress', clearText.bindAsEventListener($('deliveryExpectedDate')), false);
		Event.observe('deliveryCd', 'keypress', clearLabel.bindAsEventListener($('address')), false);
		<%-- 20210526 add S.Fujimaki 担当者名追加 --%>
		Event.observe('deliveryCd', 'keypress', clearLabel.bindAsEventListener($('salesTantoName')), false);
		Event.observe('deliveryCd', 'keypress', clearText.bindAsEventListener($('deliveryTelNo')), false);
		Event.observe('deliveryCd', "keypress", clearCheckbox.bindAsEventListener($('carryInvoiceFlag')), false);
		Event.observe('deliveryCd', 'keypress', clearText.bindAsEventListener($('carryFare')), false);

		Event.observe('deliveryCd', 'keypress', clearVenderGroup, false);
		Event.observe('deliveryCd', 'keypress', clearCalcCarryFare, false);

	}

	<%-- 品目のauto complete --%>
	function autocompleteItemCd(){
		var count = $F("orderImportDetailListCount");
		for(var i = 0 ; i < count ; i++){

			var showData = $("showData" + i).value; // 重量

			if( showData != null && showData != 0 ){

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

				Event.observe("itemCd"+i, "keypress", clearText.bindAsEventListener($("otherCompanyCd1"+i)), false);
				Event.observe("itemCd"+i, "keypress", clearLabel.bindAsEventListener($("itemName"+i)), false);
				Event.observe("itemCd"+i, "keypress", clearLabel.bindAsEventListener($("styleOfPacking"+i)), false);
				Event.observe("itemCd"+i, "keypress", clearText.bindAsEventListener($("orderQty"+i)), false);
				Event.observe("itemCd"+i, "keypress", clearText.bindAsEventListener($("matss"+i)), false);
				Event.observe("itemCd"+i, "keypress", clearDivLabel.bindAsEventListener($("standardUnitprice"+i)), false);
				Event.observe("itemCd"+i, "keypress", clearDivLabel.bindAsEventListener($("standardDiscount"+i)), false);
				Event.observe("itemCd"+i, "keypress", clearDivLabel.bindAsEventListener($("specialDiscount"+i)), false);
				Event.observe("itemCd"+i, "keypress", clearCheckbox.bindAsEventListener($("blnTmpUnitpriceFlg"+i)), false);
				Event.observe("itemCd"+i, "keypress", clearDivLabel.bindAsEventListener($("orderStatusName"+i)), false);
				Event.observe("itemCd"+i, "keypress", clearDivLabel.bindAsEventListener($("orderAmount"+i)), false);
			}

		}
	}

	<%-- 他社コード１のauto complete --%>
	function autocompleteOtherCompanyCd1(){
		var count = $F("orderImportDetailListCount");
		for(var i = 0 ; i < count ; i++){
			var showData = $("showData" + i).value; // 重量

			if( showData != null && showData != 0 ){

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
	}
	<%-- 納入先コードオートコンプリート後の処理 20200709--%>
	function setDeliveryLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("deliveryName").value = li.getElementsByTagName('span')[0].innerText;
		$("lblDeliveryName").update($F("deliveryName"));
		form_submit('op','getDeliveryRelatedData');
		refreshLabel();

	}

	<logic:iterate id="list" name="orderImportDetailForm" property="orderImportDetailList" indexId="index">
		<%-- 品目コードオートコンプリート検索用キー項目取得 --%>
		function itemCdCallBack<bean:write name='index'/>(editor,paramText){
			return paramText + '&deliveryCd=' + $('deliveryCd').value
							 + '&orderDivision=' + $('orderDivision').value
							 + '&balanceCd='+ $('balanceCd').value;
		}
	</logic:iterate>

	<logic:iterate id="list" name="orderImportDetailForm" property="orderImportDetailList" indexId="index">
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
			$("orderStatusName" + i).innerHTML = "";

			setCursor('1');

			form_submit('op', 'getRemark');
		}
	</logic:iterate>

	<logic:iterate id="list" name="orderImportDetailForm" property="orderImportDetailList" indexId="index">
		<%-- AP他社１auto completeの選択後処理 --%>
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

			$("orderStatusName" + i).innerHTML = "";

			setCursor('2');

			form_submit('op', 'getRemark');
		}
	</logic:iterate>

	<%-- 確認メッセージ --%>
	function putConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "登録してよろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- 確認メッセージ --%>
	function putFixConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "確定してよろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- 確認メッセージ --%>
	function putApprovalConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "入力承認してよろしいですか？";
		return confirm(alertMsg[0]);
	}


	<%-- 確認メッセージ --%>
	function putCancelConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "確定取消してよろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- 確認メッセージ:行削除 --%>
	function deleteConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "削除してよろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- 確認メッセージ:入力 --%>
	function purFareCalcFonfirm() {
		alertMsg = new Array();
		alertMsg[0] = "画面の編集内容は破棄されます、よろしいですか？";
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

	<%-- 確認メッセージ:行削除 --%>
	function putCancelListConfirm() {
		if (!noCheck()) {
		  return false;
		}
		alertMsg = new Array();
		alertMsg[0] = "行をキャンセルしてよろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- 確認メッセージ:仮単価 --%>
	function putChangeUnitpriceConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "仮単価状態を変更してよろしいですか？？";
		return confirm(alertMsg[0]);
	}

	<%-- 確認メッセージ --%>
	function putInvisibleConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "非表示にしてよろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- チェックボックス選択無しチェック --%>
	function noCheck(){
		var count = $F("orderImportDetailListCount");
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
		var flg = $("dirtyFlg").value;
		var calcflg = $("fareCalcFlg").value;
		if( calcflg == "true" ){
			return purFareCalcFonfirm();
		}else if (flg == "true") {
			/* 何か値が変更されている場合 */
			return continueConfirm();
		}else{
			return true;
		}
	}

	<%-- 確認メッセージ --%>
	function putAlert() {
		alertMsg = new Array();
		alertMsg[0] = "確定済ではありません。";
		alert(alertMsg[0]);

		return false;

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

	<%-- 確定時得意先変更確認メッセージ--%>
	function putChangeVenderConfirm(){
		if(($("dbBalanceCd").value != $("balanceCd").value) || ($("dbVenderCd").value != $("venderCd").value)){
			alertMsg = new Array();
			alertMsg[0] = "帳合の変更により得意先が変更されていますが確定してよろしいですか？";
			return confirm(alertMsg[0]);
		}
		return true;
	}

	<%-- 数量・金額チェック--%>
	function putQtyAmountConfirm(){
		var checkQtyFlg = false;
		var checkAmountFlg = false;
		for(var i = 0; i < $("beforeOrderImportDetailListCount").value; i++){

			var qty = parseFloat($("beforeOrderQty" + i).value.replace(/,/g, "")) + parseFloat($("beforeMatss" + i).value.replace(/,/g, ""));
			var orderAmount = parseFloat($("beforeOrderQty" + i).value.replace(/,/g, "")) * $("tmpOrderUnitprice" + i).value;

			if( qty != null && qty >= $("checkQty").value){
				checkQtyFlg = true;
			}
			if( orderAmount != null && orderAmount >= $("checkOrderAmount").value){
				checkAmountFlg = true;
			}
			alertMsg = new Array();
			alertMsg[0] = (i + 1) + "行目　数量が" + $("checkQty").value + "個を超えていますが本当に登録してよろしいですか？";
			alertMsg[1] = (i + 1) + "行目　金額が" + $("checkOrderAmount").value + "円を超えていますが本当に登録してよろしいですか？";
			if(checkQtyFlg){
				if(!confirm(alertMsg[0])){
					return false;
				}
				checkQtyFlg = false;
			}
			if(checkAmountFlg){
				if(!confirm(alertMsg[1])){
					return false;
				}
				checkAmountFlg = false;
			}
		}
		return true;
	}

	<%-- 変更フラグセット --%>
	function setDirtyFlg() {
		$("dirtyFlg").value = "true";
	}

	function clearVenderGroup(){
		$("venderGroupCd").value = "99999";
	}

	function clearCalcCarryFare(){
		$("calcCarryFare").value = "0";
	}

	<%-- 20211209 Asclab Kouchi EdgeIE対応 --%>
	<%-- ポップアップ後の設定処理 --%>
	function setOrderDeliveryValues(orderDeliverySearchFlg, deliveryCd, searchKana, address, telNo, fareClaimExistence, eigyoTantoCd, eigyoTantoName, strLeadTime, deliveryTime, carryCd) {
		if (orderDeliverySearchFlg) {
			<%-- 納入先検索後の設定処理 --%>
			$("deliveryCd").value = deliveryCd;
			$("deliveryName").value = searchKana;
			$("address").value = address;
			$("deliveryTelNo").value = telNo;

			$("lblDeliveryName").update($F("deliveryName"));
			$("lblAddress").update($F("address"));

			var carryInFlag = fareClaimExistence;
			if (carryInFlag == 0) {
				$("blnCarryInvoiceFlag").checked = false;
			} else {
				$("blnCarryInvoiceFlag").checked = true;
			}
			$("salesTantoCd").value = eigyoTantoCd;
			// 営業担当者名取得分スキップ
			$("leadTime").value = strLeadTime;
			$("deliveryExpectedTime").value = deliveryTime;

			$("carryCd").value = carryCd;

			$("lblBalanceCd").innerHTML = "";
			$("balanceCd").value = "";

			$("organizationCd").value = "";
			$("organizationName").value = "";

			var count = $F("orderDetailVenderListCount");
			for(var i = 0 ; i < count ; i++){
				$("shopLevelName" + i).innerHTML = "";
				$("venderCd" + i).innerHTML = "";
				$("venderName1" + i).innerHTML = "";
			}

			//納入予定日自動計算と品目削除処理

			form_submit('op', 'getDeliveryRelatedData');
		}
	}

	<%-- 増付数計算--%>
	function calcMatss(i) {
		var orderQty = $("orderQty" + i).value; //受注数量取得
		var estimateStandardAmount = $("estimateStandardAmount" + i).value;
		var estimateMatss = $("estimateMatss" + i).value;

		var orderQty2 = parseFloat(orderQty.replace(/,/g, "")); //カンマを取りフロートへ変換
		var estimateStandardAmount2 = parseFloat(estimateStandardAmount.replace(/,/g, "")); //カンマを取りフロートへ変換
		var estimateMatss2 = parseFloat(estimateMatss.replace(/,/g, "")); //カンマを取りフロートへ変換

		var matss;
		if( !isNaN(orderQty2)
			&& !isNaN(estimateStandardAmount2)
			&& !isNaN(estimateMatss2)){ //数値であれば

			if (estimateStandardAmount2 == 0){
				matss = 0;
			}else{
				matss = Math.floor(orderQty2 / estimateStandardAmount2) * estimateMatss2;
			}
		} else {
			matss = 0;
		}
		$("matss" + i).value = matss;
		formatMatss($("matss" + i),i);
	}

	<%-- 金額・個数・重量計算 --%>
	function calc(i) {

		var itemCd = $("itemCd" + i).value; //品目コード
		var orderQty =  $("orderQty" + i).value.replace(/,/g, ""); //受注数量
		var orderUnitprice = $("orderUnitprice" + i).value; //受注単価
		var matss = $("matss" + i).value.replace(/,/g, ""); // 増付数
		var allUpWeight = $("allUpWeight" + i).value.replace(/,/g, ""); // 重量
		var delFlg = $("delFlg" + i).value; // 重量
		var cancelFlg = $("cancelFlg" + i).value; // 重量

		//itemCdが空なら0
		if( itemCd == null || itemCd == "" || delFlg == 1 || cancelFlg == 1){
			$("orderAmount" + i).update("0");
			$("orderImportDetailList[" + i + "].orderAmount").value = "0";
			$("orderImportDetailList[" + i + "].qty").value = "0";
			$("orderImportDetailList[" + i + "].weight").value = "0";
		} else if( !isNaN(orderQty) && orderQty > 0){ //数値かつ正数であれば
			var orderAmount = 0.0;
			var qty = 0.0;
			var weight = 0.0;
			//行合計金額
			if(!isNaN(orderUnitprice)){
				orderAmount = multiFloat(orderUnitprice, orderQty, $F("smallnumLengthOrderAmount" + i));
				//フォーマット
				orderAmount = digitFormat($F("smallnumLengthOrderAmount" + i), $F("roundDivisionOrderAmount" + i), orderAmount);
			} else{
				orderAmount = "NaN";
			}
			//行合計個数
			if(matss == ""){
				qty = orderQty;
			} else if(!isNaN(matss) && matss >= 0){
				qty =  addFloat(orderQty, matss, $F("smallnumLengthNum" + i));
			} else{
				qty = "NaN";
			}

			// 行合計重量
			if(!isNaN(allUpWeight) && allUpWeight >= 0){
				var weight = calcSubtract(qty , allUpWeight);
			}　else{
				weight = "NaN";
			}
			$("orderAmount" + i).update(orderAmount);
			$("orderImportDetailList[" + i + "].orderAmount").value = orderAmount;
			$("orderImportDetailList[" + i + "].qty").value = qty;
			$("orderImportDetailList[" + i + "].weight").value = weight;

		} else if(orderQty == 0){
			var orderAmount = 0.0;
			var qty = 0.0;
			var weight = 0.0;
			//行合計個数
			if(matss == "" || matss == 0){
				qty = 0.0;
			} else if(!isNaN(matss) && matss > 0){
				qty =  digitFormat($("smallnumLengthNum" + i), $F("roundDivisionNum" + i), matss);
			} else{
				qty = "NaN";
			}

			// 行合計重量
			if(!isNaN(allUpWeight) && allUpWeight >= 0){
				var weight = calcSubtract(qty , allUpWeight);
			}　else{
				weight = "NaN";
			}
			$("orderAmount" + i).update(orderAmount);
			$("orderImportDetailList[" + i + "].orderAmount").value = orderAmount;
			$("orderImportDetailList[" + i + "].qty").value = qty;
			$("orderImportDetailList[" + i + "].weight").value = weight;
		} else {
			// 正数以外が入力されたら、合計をNaNとする

			$("orderAmount" + i).update("NaN");
			$("orderImportDetailList[" + i + "].orderAmount").value = "NaN";
			$("orderImportDetailList[" + i + "].qty").value = "NaN";
			$("orderImportDetailList[" + i + "].weight").value = "NaN";
		}

		if ((orderQty != null) && (orderQty != "")) {
			<%-- 小数点桁数・端数区分で数値を再フォーマット --%>
		    var res = digitStringFormat($F("smallnumLengthNum" + i), $F("roundDivisionNum" + i), orderQty);
			if (res != "") {
				$("orderQty" + i).value = res;
			}
		}
		listSum();//一覧合計を再計算
	}

	function getDotPosition(value){

		　// 数値のままだと操作できないので文字列化する
		　var strVal = String(value);
		　var dotPosition = 0;

		  //　小数点が存在するか確認
		  if(strVal.lastIndexOf('.') != -1){
		    // 小数点があったら位置を取得
		    dotPosition = (strVal.length-1) - strVal.lastIndexOf('.');
		  }

		  return dotPosition;
	}

	function filterInt(value) {
		  if (/^[-+0]?(\d+|Infinity)$/.test(value)) {
		    return Number(value)
		  } else {
		    return NaN
		  }
	}

	function calcSubtract(value1,value2){

		 // それぞれの小数点の位置を取得
		 var dotPosition1 = getDotPosition(value1);
		 var dotPosition2 = getDotPosition(value2);

		 // 位置の値が大きい方（小数点以下の位が多い方）の位置を取得
		 var max = Math.max(dotPosition1,dotPosition2);

		 // 大きい方に小数の桁を合わせて文字列化、
		 // 小数点を除いて整数の値にする
		 var intValue1 = filterInt((parseFloat(value1).toFixed(max) + '').replace('.', ''));
		 var intValue2 = filterInt((parseFloat(value2).toFixed(max) + '').replace('.', ''));

		 // 10^N の値を計算
		 var power = Math.pow(10,max);

		 // 整数値で掛け算した後に10^Nで割る
		 var ret = Number(intValue1*intValue2 / power / power);
		 return ret;

		}

	//合計計算
	function listSum() {
		var totalOrderAmount = 0.0;
		var totalQty = 0.0;
		var totalWeight = 0.0;

		if ( $("orderImportDetailListCount").value != null){
			for (i = 0; i < $("orderImportDetailListCount").value; i++) {

				if( $("itemCd" + i) == null){
					continue;
				}

				var itemCd = $("itemCd" + i).value; //品目コード
				var orderAmount = parseFloat($("orderAmount" + i).innerHTML.replace(/,/g, "")); //カンマを取りフロートへ変換
				var delFlg = $("delFlg" + i).value; // 重量
				var cancelFlg = $("cancelFlg" + i).value; // 重量


				if(itemCd == null || itemCd == "" || delFlg == 1 || cancelFlg == 1){
					orderAmount = 0.0;
				}
				if( $("orderAmount" + i) != null && !isNaN(orderAmount)){
					totalOrderAmount = totalOrderAmount + orderAmount;
				} else{
					totalOrderAmount = "NaN";
				}

				var qty = parseFloat($("qty" + i).value);
				if(itemCd == null || itemCd == "" || delFlg == 1 || cancelFlg == 1){
					qty = 0.0;
				}
				if( $("qty" + i) != null && !isNaN(qty)){
					totalQty = totalQty + qty;

				} else{
					totalQty = "NaN";
				}

				var weight = parseFloat($("weight" + i).value);
				if(itemCd == null || itemCd == "" || delFlg == 1 || cancelFlg == 1){
					weight = 0.0;
				}
				if( $("weight" + i) != null && !isNaN(weight)){
					totalWeight = totalWeight + weight;

				} else{
					totalWeight = "NaN";
				}
			}

			if(!isNaN(totalOrderAmount)){
				totalOrderAmount = digitFormat($F("smallnumLengthUrKingaku"), $F("roundDivisionUrKingaku"), totalOrderAmount);
			} else {
				totalOrderAmount = "NaN";
			}
			$("totalOrderAmount").update(totalOrderAmount);
			if(!isNaN(totalQty)){
				totalQty = digitFormat($F("smallnumLengthTotalNum"), $F("roundDivisionTotalNum"), totalQty);
			} else {
				totalQty = "NaN";
			}
			$("totalQty").update(totalQty);
			if(!isNaN(totalWeight)){
				totalWeight = digitFormat($F("smallnumLengthTotalWeight"), $F("roundDivisionTotalWeight"), totalWeight);
			} else {
				totalWeight = "NaN";
			}
			$("totalWeight").update(totalWeight);
		}else{
			totalOrderAmount = "NaN";
			$("totalOrderAmount").update(totalOrderAmount);
			totalQty = "NaN";
			$("totalQty").update(totalQty);
			totalWeight = "NaN";
			$("totalWeight").update(totalWeight);
		}
	}

	<%-- 運賃のフォーマット --%>
	function formatCarryFare(obj){
		var value = obj.value;
		if (value != null && value != "") {
			<%-- 数値フォーマット --%>
			var res = digitStringFormat($F("smallnumLengthCarryFare"), $F("roundDivisionCarryFare"), value);
			if (res != "") {
				$("carryFare").value = res;
			}
		}
	}

	<%-- 増付数のフォーマット --%>
	function formatMatss(obj,i){
		var value = obj.value;
		if (value != null && value != "") {
			<%-- 数値フォーマット --%>
			var res = digitStringFormat($F("smallnumLengthNum" + i), $F("roundDivisionNum" + i), value);
			if (res != "") {
				$("matss" + i).value = res;
			}
		}
	}

	<%-- リードタイムのフォーマット --%>
	function formatLeadTime(obj){
		var value = obj.value;
		if (value != null && value != "") {
			<%-- 数値フォーマット --%>
			var res = digitStringFormat($F("smallnumLengthSonota"), $F("roundDivisionSonota"), value);
			if (res != "") {
				$("leadTime").value = res;
			}
		}
	}

	<%-- 初期フォーカス位置をセット --%>
	function initFocus(){
		var id = $F("cursor");

		if (id != null && id != "" && $(id) != null) {
			$(id).focus();
		}

		$("cursor").value = "";
	}

	<%-- カーソルセット --%>
	function setCursor(pos) {
		$("cursor").value = pos;
	}

	<%-- チェックを入れる --%>
	function setLine(line) {
		$("line").value = line;
	}

	<%-- 受注日変更時処理 --%>
	function changeOrderDate() {
		<%-- 新規の場合 --%>
		<logic:equal name="orderImportDetailForm" property="insertFlg" value="1">
			// 日付が正しい場合のみ処理
			if(setDate("orderDate") == true){
				//出荷予定日・納入予定日更新と全ての品目の単価を取得
				return form_submit('op', 'afterChangeOrderDate');
			}
		</logic:equal>
	}

	<%-- 出荷予定日変更時処理 --%>
	function changeScheduledShippingDate() {
		<%-- 新規の場合 --%>
		<%--		<logic:equal name="orderImportDetailForm" property="insertFlg" value="1">--%>
			// 日付が正しい場合のみ処理
			if(setDate("scheduledShippingDate") == true){
				//納入予定日更新と全ての品目の単価を取得
				return form_submit('op', 'afterChangeScheduledShippingDate');
			}
		<%--		</logic:equal>--%>
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

	<%-- 仮単価化処理--%>
	function changeTmpUnitprice(i){

		form_submit('op', 'changeTmpUnitprice');

		//金額再計算
		calc(i);
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

<nested:form action="/OrderImportDetail" method="post"
	onsubmit="return false;" styleId="mainForm" enctype="multipart/form-data" >
	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="line" styleId="line" />
	<nested:hidden property="dirtyFlg" styleId="dirtyFlg" />
	<nested:hidden property="insertFlg" styleId="insertFlg" />
	<nested:hidden property="orderCancelButtonEnableFlg" styleId="orderCancelButtonEnableFlg" />
	<nested:hidden property="orderImportDetailListCount" styleId="orderImportDetailListCount" />
	<nested:hidden property="beforeOrderImportDetailListCount" styleId="beforeOrderImportDetailListCount" />
	<nested:hidden property="orderDetailVenderListCount" styleId="orderDetailVenderListCount" />
	<nested:hidden property="varidUnitpriceRow" styleId="varidUnitpriceRow" />
	<nested:hidden property="venderCd" styleId="venderCd" />
	<nested:hidden property="salesTantoCd" styleId="salesTantoCd"/>
	<nested:hidden property="smallnumLengthUrTanka" styleId="smallnumLengthUrTanka" />
	<nested:hidden property="roundDivisionUrTanka" styleId="roundDivisionUrTanka" />
	<nested:hidden property="smallnumLengthCarryFare" styleId="smallnumLengthCarryFare" />
	<nested:hidden property="roundDivisionCarryFare" styleId="roundDivisionCarryFare" />
	<nested:hidden property="unitDivisionCarryFare" styleId="unitDivisionCarryFare" />
	<nested:hidden property="smallnumLengthUrKingaku" styleId="smallnumLengthUrKingaku" />
	<nested:hidden property="roundDivisionUrKingaku" styleId="roundDivisionUrKingaku" />
	<nested:hidden property="smallnumLengthTotalNum" styleId="smallnumLengthTotalNum" />
	<nested:hidden property="roundDivisionTotalNum" styleId="roundDivisionTotalNum" />
	<nested:hidden property="smallnumLengthTotalWeight" styleId="smallnumLengthTotalWeight" />
	<nested:hidden property="roundDivisionTotalWeight" styleId="roundDivisionTotalWeight" />
	<nested:hidden property="smallnumLengthSonota" styleId="smallnumLengthSonota" />
	<nested:hidden property="roundDivisionSonota" styleId="roundDivisionSonota" />
	<nested:hidden property="cursor" styleId="cursor" />
	<nested:hidden property="deliveryDivision" styleId="deliveryDivision" />
	<nested:hidden property="difCarryFareFlg" styleId="difCarryFareFlg" />
	<nested:hidden property="difRemarkFlg" styleId="difRemarkFlg" />
	<nested:hidden property="dbBalanceCd" styleId="dbBalanceCd" />
	<nested:hidden property="dbVenderCd" styleId="dbVenderCd" />
	<nested:hidden property="checkOrderAmount" styleId="checkOrderAmount" />
	<nested:hidden property="checkQty" styleId="checkQty" />
	<nested:hidden property="fixedFlg" styleId="fixedFlg" />
	<nested:hidden property="fareCalcFlg" styleId="fareCalcFlg" />
	<nested:hidden property="scrollFlg" styleId="scrollFlg" />

	<table cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td class="valignTop alignLeft">
			<table border="0" cellpadding="0" cellspacing="0" width="1280">
				<tr>
					<td><!-- ヘッダー部 -->
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td height="10" colspan="2"></td>
						</tr>

						<tr>
							<td class="fcTitle fb" height="20">受注取込詳細</td>
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
							<td class="fcTitleDetail fb bcTitleDetail" width="120">先付受注番号</td>
							<td>
								<nested:write property="frstOrderNo" />
							</td>
							<td class="fcTitleDetail fb bcTitleDetail" width="100">受注番号</td>
							<td>
								<nested:write property="orderNo" />
							</td>
							<td width="100">
							<td class="bcTitleSearch fb fcTitleSearch" width="120">得意先グループ</td>
								<td>
									<nested:select property="venderGroupCd" style="margin: 0;padding: 0" styleId="venderGroupCd" disabled="true">
										<nested:optionsCollection property="venderGroupCombo" label="labales" value="values" />
									</nested:select>
								</td>
							</td>
							<td class="fcTitleDetail fb bcTitleDetail">データ取込日</td>
							<td>
								<nested:write property="importDate"/>
							</td>

						</tr>
						<tr>
							<td class="fcTitleDetail fb bcTitleDetail">受注日</td>
							<td>
								<nested:text property="orderDate" maxlength="8" size="10" styleId="orderDate" onchange="setDirtyFlg(); changeOrderDate();"  style="ime-mode:disabled"/>
							</td>
							<td class="bcTitleSearch fb fcTitleSearch" width="100">受注区分</td>
							<td>
								<%
									pageContext.setAttribute("selectOrderDivisionCode",
									new com.asahikaseieng.app.comboboxes.SelectOrderDivisionCode(false, false));
								%>
								<nested:select property="orderDivision" styleId="orderDivision" onchange="setDirtyFlg();">
									<nested:options name="selectOrderDivisionCode" property="values" labelName="selectOrderDivisionCode" labelProperty="labels"/>
								</nested:select>
							</td>
							<td colspan="1">
							<td class="fcTitleDetail fb bcTitleDetail">客先注文日</td>
							<td>
								<nested:write property="ctmOrderDate" />
							</td>
						</tr>
						<tr>
							<nested:equal property="insertFlg" value="1">
								<td class="fcTitleDetail fb bcTitleDetail">納入先</td>
								<td>
									<nested:text property="deliveryCd" maxlength="10" size="10" styleId="deliveryCd" onchange="setDirtyFlg();"/>
									<div id="autocomplete_delivery" class="autocomplete" ></div>
									<span id="cssButton">
										<a href="#"  class="cssButton"
										   onclick="<%="open_modal_popup_edge(765, 600,'OrderDeliverySearch', '', 'srhDivision', '1'); setDirtyFlg(); return false;"%>">
											選
										</a>&nbsp;
									</span>
								</td>
							</nested:equal>
							
							<nested:notEqual property="insertFlg" value="1">
								<td class="fcTitleDetail fb bcTitleDetail">納入先</td>
								<td>
									<nested:text property="deliveryCd" maxlength="10" size="10" styleId="deliveryCd" readonly="true" onchange="setDirtyFlg();"/>
								</td>
							</nested:notEqual>
							<td colspan="3" width="300">
								<div id="lblDeliveryName"><nested:write property="deliveryName" /></div>
								<nested:hidden property="deliveryName" styleId="deliveryName" />
							</td>
							<td class="fcTitleDetail fb bcTitleDetail">客先納入先</td>
							<td>
								<nested:write property="ctmDeliveryCd" />
							</td>
							<td colspan="2">
								<div id="lblCtmDeliveryName"><nested:write property="ctmDeliveryName" /></div>
								<nested:hidden property="ctmDeliveryName" styleId="ctmDeliveryName" />
							</td>
						</tr>
						<tr>
<!-- 営業担当者追加 20210525 S.Fujimaki-->
							<td class="fcTitleDetail fb bcTitleDetail" height="28">納入先住所</td>
							<!-- <td colspan="3" width="700"> -->
							<td colspan="1" width="300">
								<div id="lblAddress">
									<nested:write property="address" />
								</div>
								<nested:hidden property="address" styleId="address" />
							</td>
							<td class="fcTitleDetail fb bcTitleDetail" height="28">営業担当者</td>
							<td colspan="2">
								<div id="lblSalesTantoName">
									<nested:write property="salesTantoName" />
								</div>
								<nested:hidden property="salesTantoName" styleId="salesTantoName" />
							</td>
							<td class="fcTitleDetail fb bcTitleDetail">客先納入先住所</td>
							<!--  <td colspan="4"> -->
							<td colspan="3">
								<div id="lblCtmAddress">
									<nested:write property="ctmAddress" />
								</div>
								<nested:hidden property="ctmAddress" styleId="ctmAddress" />
							</td>
<!-- 営業担当者追加 20210525 S.Fujimaki -->
						</tr>
						<tr>
							<td class="fcTitleDetail fb bcTitleDetail">納入先宛先</td>
							<td>
								<nested:text property="deliveryAddress" maxlength="60" size="35" styleId="deliveryAddress" onchange="setDirtyFlg();"/>
							</td>
							<td class="fcTitleDetail fb bcTitleDetail">納入先電話</td>
							<td colspan="2">
								<nested:text property="deliveryTelNo" maxlength="13" size="12" styleId="deliveryTelNo" onchange="setDirtyFlg();"  style="ime-mode:disabled"/>
							</td>
							<td class="fcTitleDetail fb bcTitleDetail">客先納入先宛先</td>
							<td>
								<nested:write property="ctmDeliveryAddress" />
							</td>
						</tr>
						<tr>
							<td class="fcTitleDetail fb bcTitleDetail">出荷予定日</td>
							<td>
								<nested:text property="scheduledShippingDate" maxlength="13" size="10" styleId="scheduledShippingDate" onchange="setDirtyFlg();changeScheduledShippingDate();"  style="ime-mode:disabled"/>
							</td>
							<td class="fcTitleDetail fb bcTitleDetail" >リードタイム</td>
							<td colspan="2">
								<nested:write property="leadTime" />
								<nested:hidden property="leadTime" styleId="leadTime" />
							</td>
							<td class="fcTitleDetail fb bcTitleDetail">客先納入先電話</td>
							<td>
								<nested:write property="ctmDeliveryTelNo" />
							</td>
						</tr>
						<tr>
							<td class="fcTitleDetail fb bcTitleDetail">客先希望納期</td>
							<td colspan="1">
									<nested:text property="suggestedDeliverlimit" maxlength="8" size="10" styleId="suggestedDeliverlimit" style="ime-mode:disabled" />
							</td>
							<td class="fcTitleDetail fb bcTitleDetail">運送会社</td>
							<td colspan="5">
								<nested:select property="carryCd" style="margin: 0;padding: 0" onchange="setDirtyFlg();">
									<nested:optionsCollection property="carryCombo" label="labales" value="values" />
								</nested:select>
							</td>
						</tr>
						<tr>
							<td class="fcTitleDetail fb bcTitleDetail">納入予定日</td>
							<td>
								<nested:text property="deliveryExpectedDate" maxlength="8" size="10" styleId="deliveryExpectedDate" onchange="setDirtyFlg();showDeliveryDateAlert();"  style="ime-mode:disabled"/>
							</td>
							<td class="fcTitleDetail fb bcTitleDetail">納入時刻</td>
							<td colspan="2">
								<nested:text property="deliveryExpectedTime" maxlength="10" size="30" styleId="deliveryExpectedTime" onchange="setDirtyFlg();" />
							</td>
							<td class="fcTitleDetail fb bcTitleDetail">客先注文番号</td>
							<td colspan="2">
									<nested:text property="headCtmOrderNo" maxlength="30" size="30" styleId="headCtmOrderNo" style="ime-mode:active" />
							</td>

						</tr>
					</table>

					<table width="100%" cellspacing="2" cellpadding="2" border="0">
						<tr>
							<td colspan="6" align="left">
								<div id="cssButton">
									<a href="#" styleId="addRowTop" onclick="setDirtyFlg(); return form_submit('op', 'addlist'); return false;" class="cssButton">
										&nbsp;&nbsp;行&nbsp;追&nbsp;加&nbsp;&nbsp;
									</a>
&nbsp;&nbsp;&nbsp;&nbsp;
									<a href="#" onclick="if (!(putDelListConfirm())) {return false;}else{setDirtyFlg(); return form_submit('op', 'dellist'); return false;}" class="cssButton">
										&nbsp;&nbsp;行&nbsp;削&nbsp;除&nbsp;&nbsp;
									</a>
			<td width="120">
									<a href="#" onclick="if (!(putCancelListConfirm())) {return false;}else{setDirtyFlg(); return form_submit('op', 'cancellist'); return false;}" class="cssButton">
										&nbsp;&nbsp;行&nbsp;キ&nbsp;ャ&nbsp;ン&nbsp;セ&nbsp;ル&nbsp;&nbsp;
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
									<td width="20" rowspan="2"></td>
									<td width="100">AP品目</td>
									<td width="100">AP他社1</td>
									<td width="200">AP品目名称</td>
									<td width="80">AP荷姿</td>
									<td width="90">AP数量</td>
									<td width="100">AP増付数</td>
									<td width="100">AP標準単価</td>
									<td width="100">AP標準値引</td>
									<td width="100">AP特別値引</td>
									<td width="80">AP金額</td>
									<td width="70" rowspan="2">仮単価</td>
									<td width="45" rowspan="2">入力ﾁｪｯｸ</td>
									<td width="45" rowspan="2">納期連絡</td>
								<%-- <td width="100" rowspan="2">客先備考</td>--%>
								</tr>
								<tr class="bcTitleList fb fcTitleList">
									<td width="100">客先品目</td>
									<td width="100">客先注文番号</td>
									<td width="200">客先品目名称</td>
									<td width="80">客先荷姿</td>
									<td width="90">ﾁｪｯｸ済数量</td>
									<td width="100">ﾁｪｯｸ済増付数</td>
									<td width="100">客先単価</td>
									<td width="80">客先数量</td>
									<td width="100">APｽﾃｰﾀｽ</td>
									<td width="80">AP仮単価</td>
								</tr>
								<div id="autocomplete_selection" class="autocomplete"></div>
								<nested:iterate id="orderImportDetailList" property="orderImportDetailList" indexId="index">
									<%-- Itemごとの　運用管理単位、端数区分、小数点桁数を設定 --%>

									<nested:hidden property="unitOfOperationManagement" styleId="<%="unitOfOperationManagement" + pageContext.findAttribute("index").toString() %>" />
									<nested:hidden property="estimateStandardAmount" styleId="<%="estimateStandardAmount" + pageContext.findAttribute("index").toString() %>"/>
									<nested:hidden property="estimateMatss" styleId="<%="estimateMatss" + pageContext.findAttribute("index").toString() %>"/>
									<nested:hidden property="venderCd"/>
									<nested:hidden property="rowNo" styleId="<%="rowNo" + pageContext.findAttribute("index").toString() %>" />
									<nested:hidden property="orderUnitprice" styleId="<%="orderUnitprice" + pageContext.findAttribute("index").toString() %>"/>
									<nested:hidden property="allUpWeight" styleId="<%="allUpWeight" + pageContext.findAttribute("index").toString() %>"/>
									<nested:hidden property="smallnumLengthUnitprice" styleId="<%="smallnumLengthUnitprice" + pageContext.findAttribute("index").toString() %>" />
									<nested:hidden property="roundDivisionUnitprice" styleId="<%="roundDivisionUnitprice" + pageContext.findAttribute("index").toString() %>" />
									<nested:hidden property="smallnumLengthOrderAmount" styleId="<%="smallnumLengthOrderAmount" + pageContext.findAttribute("index").toString() %>" />
									<nested:hidden property="roundDivisionOrderAmount" styleId="<%="roundDivisionOrderAmount" + pageContext.findAttribute("index").toString() %>" />
									<nested:hidden property="smallnumLengthNum" styleId="<%="smallnumLengthNum" + pageContext.findAttribute("index").toString() %>" />
									<nested:hidden property="roundDivisionNum" styleId="<%="roundDivisionNum" + pageContext.findAttribute("index").toString() %>" />
									<nested:hidden property="smallnumLengthWeight" styleId="<%="smallnumLengthWeight" + pageContext.findAttribute("index").toString() %>" />
									<nested:hidden property="roundDivisionWeight" styleId="<%="roundDivisionWeight" + pageContext.findAttribute("index").toString() %>" />
									<nested:hidden property="qty" styleId="<%="qty" + pageContext.findAttribute("index").toString() %>" />
									<nested:hidden property="weight" styleId="<%="weight" + pageContext.findAttribute("index").toString() %>" />
									<nested:hidden property="tmpOrderUnitprice" styleId="<%="tmpOrderUnitprice" + pageContext.findAttribute("index").toString() %>" />
									<nested:hidden property="beforeOrderQty" styleId="<%="beforeOrderQty" + pageContext.findAttribute("index").toString() %>" />
									<nested:hidden property="beforeMatss" styleId="<%="beforeMatss" + pageContext.findAttribute("index").toString() %>" />
									<nested:hidden property="delFlg" styleId="<%="delFlg" + pageContext.findAttribute("index").toString() %>"/>
									<nested:hidden property="showData" styleId="<%="showData" + pageContext.findAttribute("index").toString() %>"/>
									<nested:hidden property="cancelFlg" styleId="<%="cancelFlg" + pageContext.findAttribute("index").toString() %>"/>

									<nested:equal property="cancelFlg" value="1">
										<tr class=bcTitleCanceld>
									</nested:equal>
									<nested:notEqual property="cancelFlg" value="1">
										<nested:equal property="conflictLine" value="true">
											<tr class="bcTitleYellowCard">
										</nested:equal>
										<nested:notEqual property="conflictLine"  value="true">
											<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
												<tr class="bcList1">
											</app:modulo>

											<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
												<tr class="bcList2">
											</app:modulo>
										</nested:notEqual>
									</nested:notEqual>

									<nested:equal property="showData" value="0">
											<nested:hidden property="checkline" styleId="<%="checkline" + pageContext.findAttribute("index").toString() %>"/>
									</nested:equal>
									<nested:notEqual property="showData" value="0">

										<td rowspan="2"><%-- 行追加行削除用チェックボックス --%>
											<nested:checkbox property="checkline" styleId="<%="checkline" + pageContext.findAttribute("index").toString() %>"/>
										</td>
										<td><%-- AP品目 --%>
											<nested:text property="itemCd" maxlength="20" size="10" onfocus="this.select();" styleId="<%="itemCd" + pageContext.findAttribute("index").toString() %>" onchange="setCursor('1');setDirtyFlg();"/>
										</td>
										<td><%-- AP他社１ --%>
											<nested:text property="otherCompanyCd1" maxlength="20" size="10" onfocus="this.select();"  styleId="<%="otherCompanyCd1" + pageContext.findAttribute("index").toString() %>" onchange="setCursor('2');setDirtyFlg();"/>
										</td>
										<td width="150"><%-- AP品目名称 --%>
											<!-- 20211209 Asclab Kouchi EdgeIE対応 -->
											<html:link href="#" onclick='<%="open_modal_popup_edge(730, 600,'OrderLotSearch', '', 'srhItemCd', $('itemCd" + pageContext.findAttribute("index").toString() + "').value); return false;"%>'>
												<div id="<%="lblItemName" + pageContext.findAttribute("index").toString() %>">
													<nested:write property="itemName" />
												</div>
											</html:link>
											<nested:hidden property="itemName" styleId="<%="itemName" + pageContext.findAttribute("index").toString() %>" />
										</td>
										<td><%-- AP荷姿 --%>
											<div id="<%="lblStyleOfPacking" + pageContext.findAttribute("index").toString() %>">
												<nested:write property="styleOfPacking" />
											</div>
											<nested:hidden property="styleOfPacking" styleId="<%="styleOfPacking" + pageContext.findAttribute("index").toString() %>"/>
										</td>
										<nested:equal property="validLine"  value="true">
											<td align="right"><%-- AP数量 --%>
												<nested:text property="orderQty" onfocus="this.select();" onblur='<%="calcMatss("+ pageContext.findAttribute("index").toString() + "); " + "calc(" + pageContext.findAttribute("index").toString() + ");"%>'
													maxlength="6" size="10" styleClass="alignRight" styleId="<%="orderQty" + pageContext.findAttribute("index").toString() %>" onchange="setDirtyFlg();" style="ime-mode:disabled"/>
											</td>
											<td><%-- AP増付数 --%>
												<nested:text property="matss" onfocus="this.select();" styleId="<%="matss" + pageContext.findAttribute("index").toString() %>" maxlength="5" size="12" styleClass="alignRight" onchange="setDirtyFlg();" onblur="<%="formatMatss(this," + pageContext.findAttribute("index").toString() + ");" + "calc(" + pageContext.findAttribute("index").toString() + ");"%>" style="ime-mode:disabled"/>
											</td>
										</nested:equal>
										<nested:notEqual property="validLine"  value="true">
											<td align="right"><%-- AP数量 --%>
												<nested:text property="orderQty" onfocus="this.select();" onblur='<%="calcMatss("+ pageContext.findAttribute("index").toString() + "); " + "calc(" + pageContext.findAttribute("index").toString() + ");"%>'
													maxlength="6" size="10" styleClass="alignRight" styleId="<%="orderQty" + pageContext.findAttribute("index").toString() %>" onchange="setDirtyFlg();" style="ime-mode:disabled" disabled="true" />
											</td>
											<td><%-- AP増付数 --%>
												<nested:text property="matss" onfocus="this.select();" styleId="<%="matss" + pageContext.findAttribute("index").toString() %>" maxlength="5" size="12" styleClass="alignRight" onchange="setDirtyFlg();" onblur="<%="formatMatss(this," + pageContext.findAttribute("index").toString() + ");" + "calc(" + pageContext.findAttribute("index").toString() + ");"%>" style="ime-mode:disabled" disabled="true"/>
											</td>
										</nested:notEqual>
										<td align="right"><%-- AP標準単価 --%>
											<div id="<%="standardUnitprice" + pageContext.findAttribute("index").toString() %>">
												<nested:write property="standardUnitprice" />
											</div>
											<nested:hidden property="standardUnitprice" styleId="<%="standardUnitprice" + pageContext.findAttribute("index").toString() %>"/>
										</td>

										<td align="right"><%-- AP標準値引 --%>
											<div id="<%="standardDiscount" + pageContext.findAttribute("index").toString() %>">
												<nested:write property="standardDiscount" />
											</div>
											<nested:hidden property="standardDiscount" styleId="<%="standardDiscount" + pageContext.findAttribute("index").toString() %>"/>
										</td>
										<td align="right"><%-- AP特別値引 --%>
											<div id="<%="specialDiscount" + pageContext.findAttribute("index").toString() %>">
												<nested:write property="specialDiscount" />
											</div>
											<nested:hidden property="specialDiscount" styleId="<%="specialDiscount" + pageContext.findAttribute("index").toString() %>"/>
										</td>
										<td align="right"><%-- AP金額 --%>
											<div id="<%="orderAmount" + pageContext.findAttribute("index").toString() %>">
												<nested:write property="orderAmount" />
											</div>
											<nested:hidden property="orderAmount" styleId="<%="orderAmount" + pageContext.findAttribute("index").toString() %>"/>
										</td>
										<td class="alignCenter" rowspan="2"><%-- 仮単価化 --%>
											<div id="cssButton"><a href="#"
												onclick="if(!putChangeUnitpriceConfirm()){return false;}else{setLine('<%=pageContext.findAttribute("index").toString()%>');return form_submit('op', 'changeTmpUnitprice'); return false;} return false;"
												class="cssButton"> 変更 </a>
											</div>
										</td>
										<td class="alignCenter" rowspan="2"><%-- 入力ﾁｪｯｸ --%>
											<nested:checkbox property="inputApprovaledCheck"  styleId="<%="inputApprovaledCheck" + pageContext.findAttribute("index").toString()%>" disabled="true"/>
										</td>
										<td class="alignCenter" rowspan="2"><%-- 納期連絡 --%>
											<nested:checkbox property="delDateApprovaledCheck"  styleId="<%="delDateApprovaledCheck" + pageContext.findAttribute("index").toString()%>" disabled="true"/>
										</td>

										<%--
										<td class="alignLeft" rowspan="2"><%-- 客先備考 --%><%--
											<div id="<%="lblCtmRemark" + pageContext.findAttribute("index").toString() %>">
												<nested:notEmpty property="ctmRemark01">
												<nested:write property="ctmRemark01"/>
													<nested:notEmpty property="ctmRemark02">
														<br><nested:write property="ctmRemark02" />
														<nested:notEmpty property="ctmRemark03">
															<br><nested:write property="ctmRemark03"/>
														</nested:notEmpty>
													</nested:notEmpty>
												</nested:notEmpty>
											</div>
										</td>
										--%>
										</tr>

										<nested:equal property="cancelFlg" value="1">
											<tr class=bcTitleCanceld>
										</nested:equal>

										<nested:equal property="conflictLine" value="true">
											<tr class="bcTitleYellowCard">
										</nested:equal>
										<nested:notEqual property="cancelFlg" value="1">
											<nested:notEqual property="conflictLine"  value="true">
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
											</nested:notEqual>
										</nested:notEqual>


										<td><%-- 客先品目 --%>
											<div id="<%="lblCtmItemCd" + pageContext.findAttribute("index").toString() %>">
												<nested:write property="ctmItemCds" />
											</div>
											<nested:hidden property="ctmItemCd01" styleId="<%="ctmItemCd01" + pageContext.findAttribute("index").toString() %>"/>
											<%-- 行追加のときに列が潰れるの防止--%>
											<nested:empty property="ctmItemCd01">
												&nbsp;
											</nested:empty>
										</td>
										<td><%-- 客先注文番号 --%>
											<nested:text property="ctmOrderNo" onfocus="this.select();" maxlength="30" size="10" styleId="<%="ctmOrderNo" + pageContext.findAttribute("index").toString() %>" onchange="setDirtyFlg();" style="ime-mode:disabled" />
										</td>
										<td width="200"><%-- 客先品目名称 --%>
											<div id="<%="lblCtmItemName" + pageContext.findAttribute("index").toString() %>">
													<nested:write property="ctmItemNames" />
											</div>
											<nested:hidden property="ctmItemName01" styleId="<%="ctmItemName01" + pageContext.findAttribute("index").toString() %>"/>
										</td>
										<td><%-- 客先荷姿 --%>
											<div id="<%="ctmStyleOfPacking" + pageContext.findAttribute("index").toString() %>">
												<nested:write property="ctmStyleOfPacking" />
											</div>
											<nested:hidden property="ctmStyleOfPacking" styleId="<%="ctmStyleOfPacking" + pageContext.findAttribute("index").toString() %>"/>
										</td>

										<td align="right"><%-- ﾁｪｯｸ済数量 --%>
												<nested:write property="checkedOrderQty" />
										</td>
										<td align="right"><%-- ﾁｪｯｸ済増付数 --%>
												<nested:write property="checkedMatss" />
										</td>

										<td align="right"><%-- 客先単価 --%>
											<div id="<%="ctmOrderUnitprice" + pageContext.findAttribute("index").toString() %>">
												<nested:write property="ctmOrderUnitprice" />
											</div>
											<nested:hidden property="ctmOrderUnitprice" styleId="<%="ctmOrderUnitprice" + pageContext.findAttribute("index").toString() %>"/>
										</td>


										<td align="right"><%-- 客先数量 --%>
											<div id="<%="ctmOrderQty" + pageContext.findAttribute("index").toString() %>">
												<nested:write property="ctmOrderQty" />
											</div>
											<nested:hidden property="ctmOrderQty" styleId="<%="ctmOrderQty" + pageContext.findAttribute("index").toString() %>"/>
										</td>
										<td><%-- APステータス --%>
											<div id="<%="orderStatusName" + pageContext.findAttribute("index").toString() %>">
												<nested:write property="orderStatusName" />
											</div>
											<nested:hidden property="orderStatusName" styleId="<%="orderStatusName" + pageContext.findAttribute("index").toString() %>"/>
										</td>


										<td class="alignCenter"><%-- AP仮単価 --%>
											<nested:checkbox property="blnTmpUnitpriceFlg"  disabled="true" styleId="<%="blnTmpUnitpriceFlg" + pageContext.findAttribute("index").toString() %>"/>
										</td>
									</nested:notEqual>
								</tr>
								</nested:iterate>
							</table>
							</td>
						</tr>
					</table>
					<table width="100%" cellspacing="2" cellpadding="2" border="0">
						<tr>
							<td colspan="6" align="left">
								<div id="cssButton">
									<a href="#" onclick="setDirtyFlg(); return form_submit('op', 'addlist'); return false;" class="cssButton">
										&nbsp;&nbsp;行&nbsp;追&nbsp;加&nbsp;&nbsp;
									</a>
&nbsp;&nbsp;&nbsp;&nbsp;
									<a href="#" onclick="if (!(putDelListConfirm())) {return false;}else{setDirtyFlg(); return form_submit('op', 'dellist'); return false;}" class="cssButton">
										&nbsp;&nbsp;行&nbsp;削&nbsp;除&nbsp;&nbsp;
									</a>
									<td width="120">
									<a href="#" onclick="if (!(putCancelListConfirm())) {return false;}else{setDirtyFlg(); return form_submit('op', 'cancellist'); return false;}" class="cssButton">
										&nbsp;&nbsp;行&nbsp;キ&nbsp;ャ&nbsp;ン&nbsp;セ&nbsp;ル&nbsp;&nbsp;
									</a>
								</div>
							</td>
						</tr>
					</table>
					<table cellspacing="2" cellpadding="2" border="0">
						<tr>
							<td class="fcTitleDetail fb bcTitleDetail">運賃計算</td>
							<td width="70" align="right">
								<nested:write property="calcCarryFare"/>
								<nested:hidden property="calcCarryFare" styleId="calcCarryFare" />
							</td>
							<td width="30">円</td>
							<td width="90">
								<div id="cssButton">
									<a href="#" onclick="setDirtyFlg(); return form_submit('op', 'calcCarryFare');" class="cssButton">
									運賃計算
									</a>
								</div>
							</td>
							<td class="fcTitleDetail fb bcTitleDetail">運賃</td>
							<td>
								<nested:text property="carryFare" maxlength="10" size="10" styleId="carryFare" onchange="setDirtyFlg(); formatCarryFare(this);"  style="ime-mode:disabled"/>
							</td>
							<td width="80" align="left">円
							<td class="fcTitleDetail fb bcTitleDetail" width="120">運賃請求フラグ</td>
							<td>
								<nested:checkbox property="blnCarryInvoiceFlag" styleId="blnCarryInvoiceFlag" onchange="setDirtyFlg();" />
								<nested:hidden property="blnCarryInvoiceFlag" styleId="blnCarryInvoiceFlag" value="false" />
							</td>
						</tr>
						<tr>
							<td class="fcTitleDetail fb bcTitleDetail" width="80">合計金額</td>
							<td width="70" align="right">
								<div id="totalOrderAmount">
									<nested:write property="totalOrderAmount"/>
								</div>
								<nested:hidden property="totalOrderAmount" styleId="totalOrderAmount" />
							</td>
							<td colspan="2" width="120">円</td>
							<td class="fcTitleDetail fb bcTitleDetail" width="80">合計個数</td>
							<td align="right">
								<div id="totalQty">
									<nested:write property="totalQty"/>
								</div>
								<nested:hidden property="totalQty" styleId="totalQty" />
							</td>
							<td width="80" align="left">個</td>
							<td class="fcTitleDetail fb bcTitleDetail" width="80">合計重量</td>
							<td width="70" align="right">
								<div id="totalWeight">
									<nested:write property="totalWeight"/>
								</div>
								<nested:hidden property="totalWeight" styleId="totalWeight" />
							</td>
							<td width="80">kg</td>
						</tr>
						<tr>
							<td class="fcTitleDetail fb bcTitleDetail" width="80">帳合コード</td>
							<td width="150" colspan="3">
								<div id="lblBalanceCd">
									<nested:write property="balanceCd" />
								</div>
								<nested:hidden property="balanceCd" styleId="balanceCd" />
							</td>
							<td class="fcTitleDetail fb bcTitleDetail" width="80">担当部署</td>
							<td width="80">
								<div id="organizationCd">
									<nested:write property="organizationCd" />
								</div>
								<nested:hidden property="organizationCd" styleId="organizationCd" />
							</td>
							<td colspan="3">
								<div id="organizationName">
									<nested:write property="organizationName" />
								</div>
							</td>
							<nested:hidden property="organizationName" styleId="organizationName" />
						</tr>
					</table>
					<table cellspacing="2" cellpadding="2" border="0">
						<tr>
							<td colspan="3" valign="Top">
								<table border="0" cellspacing="2" cellpadding="1" >
									<tr class="bcTitleList fb fcTitleList">
										<td width="50"></td>
										<td width="110">得意先コード</td>
										<td width="250">得意先名</td>
										<td width="110">電話番号</td>
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
													<td width="220">
														<div id="<%="venderName1" + pageContext.findAttribute("index").toString() %>">
															<nested:write property="venderName1" />
														</div>
													</td>
													<td width="110">
														<div id="<%="telNo" + pageContext.findAttribute("index").toString() %>">
															<nested:write property="telNo" />
														</div>
													</td>
												</tr>
										</nested:iterate>
									</nested:notEmpty>
								</table>
							</td>
							<td width="50">
							<td class="fcTitleDetail fb bcTitleDetail" width="90" >客先得意先</td>
							<td>
								<div id="ctmVenderName">
									<nested:write property="ctmVenderName" />
								</div>
							</td>
						</tr>
						<tr>
							<td class="alignLeft">
								<div id="cssButton">
									<a href="#" onclick="return form_submit('op', 'getRemarkList');" class="cssButton">
									備考取得
									</a>
								</div>
							</td>
						</tr>
					</table>
					<table width="100%" cellspacing="2" cellpadding="2" border="0">
						<tr>
							<td class="fcTitleDetail fb bcTitleDetail">備考(印字用)</td>
							<td colspan="5">
								<nested:textarea property="printSummery" cols="75" rows="3" styleId="printSummery" style="ime-mode:active" />
							</td>
						</tr>

						<tr>
							<td class="fcTitleDetail fb bcTitleDetail">自動表示　備考</td>
							<td colspan="5">
								<nested:textarea property="deliverySlipSummery" cols="75" rows="3" styleId="deliverySlipSummery"  onchange="setDirtyFlg();" style="ime-mode:active"/>
							</td>
						</tr>
						<tr>
							<td class="fcTitleDetail fb bcTitleDetail">参照</td>
							<td colspan="5">
								<nested:textarea property="orderSummery"  cols="75" rows="3" styleId="orderSummery"  onchange="setDirtyFlg();" style="ime-mode:active" />
							</td>
						</tr>
						<!-- 20210906 Asclab Saita 納期連絡表専用備考追加対応 -->
						<tr>
							<td class="fcTitleDetail fb bcTitleDetail">客先連絡事項</td>
							<td colspan="5">
								<nested:textarea property="deliverydateContactSummery"  cols="75" rows="3" styleId="deliverydateContactSummery"  onchange="setDirtyFlg();" style="ime-mode:active" />
							</td>
						</tr>
						<nested:notEqual property="insertFlg" value="1">
						<tr>
							<td class="fcTitleDetail fb bcTitleDetail">客先備考</td>
							<td colspan="5">
								<nested:textarea property="ctmRemark"  cols="75" rows="5" styleId="ctmRemark" readonly="true" />
							</td>
						</tr>
						</nested:notEqual>
					</table>
					</td>
				</tr>

				<tr>
					<td class="alignCenter">
					<table cellpadding="0" cellspacing="0" width="800" border="0">
						<tr>
							<td colspan="3" height="2"></td>
						</tr>
						<tr>
							<%-- 取込ステータスが削除の時は戻るしか表示しない --%>
							<nested:notEqual property="importStatus" value="90">
								<%-- 取込ステータスが確定でないときは登録・削除ボタン表示--%>
								<nested:notEqual property="importStatus" value="30">
									<%-- 更新時のみ確定ボタン表示 --%>
									<nested:equal property="updateAuthority" value="true">
										<nested:notEqual property="conflicted"  value="true">
											<nested:equal property="insertFlg" value="0">
												<td class="alignRight">
													<div id="cssButton"><a href="#"
														onclick="if(!putDirtyConfirm()){return false;} else{ if(!putChangeVenderConfirm()) {return false;} else{if(!putQtyAmountConfirm()) {return false;} else{if(!putApprovalConfirm()) {return false;} else{return form_submit('op', 'approval');}}}}"
														class="cssButton"> &nbsp;&nbsp;入&nbsp;力&nbsp;チ&nbsp;ェ&nbsp;ッ&nbsp;ク&nbsp;&nbsp; </a>
													</div>
												</td>
												<td width="20">
												<td class="alignRight">
													<div id="cssButton"><a href="#"
														onclick="if(!putDirtyConfirm()){return false;}else{ if(!putChangeVenderConfirm()) {return false;} else{if(!putQtyAmountConfirm()) {return false;} else{if(!putFixConfirm()) {return false;} else{return form_submit('op', 'fix');}}}}"
														class="cssButton"> &nbsp;&nbsp;在&nbsp;庫&nbsp;引&nbsp;当&nbsp;&nbsp; </a>
													</div>
												</td>
												<td width="50">
													<br>
												</td>
											</nested:equal>
											<%-- 非表示フラグが0:変更可の時のみ確定取消ボタンを表示 --%>
											<nested:equal property="orderInvisibleButtonEnableFlg" value="1">
												<td class="alignRight">
													<div id="cssButton"><a href="#"
														onclick="if(!putInvisibleConfirm()){return false;}else{return form_submit('op', 'invisible'); return false;}"
														class="cssButton"> &nbsp;&nbsp;非&nbsp;表&nbsp;示&nbsp;&nbsp; </a>
													</div>
												</td>
												<td width="50">
											</nested:equal>
										</nested:notEqual>
									</nested:equal>
									<nested:equal property="updateAuthority" value="true">
										<td class="alignRight">
											<div id="cssButton"><a href="#"
												onclick="if(!putConfirm()){return false;}else{return form_submit('op', 'regist'); return false;}"
												class="cssButton"> &nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp; </a>
											</div>
										</td>
										<td width="50">
											<br>
										</td>
									</nested:equal>
									<nested:equal property="insertFlg" value="0">
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
								</nested:notEqual>
								<%-- 取込ステータスが確定のときは確定取消ボタン表示--%>
								<nested:equal property="importStatus" value="30">
									<nested:equal property="updateAuthority" value="true">
										<%-- 変更可フラグが0:変更可の時のみ確定取消ボタンを表示 --%>
										<nested:equal property="orderCancelButtonEnableFlg" value="1">
											<td class="alignRight">
												<div id="cssButton"><a href="#"
													onclick="if(!putDirtyConfirm()){return false;} else{ if(!putChangeVenderConfirm()) {return false;} else{if(!putQtyAmountConfirm()) {return false;} else{if(!putApprovalConfirm()) {return false;} else{return form_submit('op', 'approval');}}}}"
													class="cssButton"> &nbsp;&nbsp;入&nbsp;力&nbsp;チ&nbsp;ェ&nbsp;ッ&nbsp;ク&nbsp;&nbsp; </a>
												</div>
											</td>
											<td width="20">
											<td class="alignRight">
												<div id="cssButton"><a href="#"
													onclick="if(!putConfirm()){return false;}else{return form_submit('op', 'fixedOrderUpdate'); return false;}"
													class="cssButton"> &nbsp;&nbsp;受&nbsp;注&nbsp;更&nbsp;新&nbsp;&nbsp; </a>
												</div>
											</td>
											<td width="50">
											<td class="alignRight">
												<div id="cssButton"><a href="#"
													onclick="if(!putCancelConfirm()){return false;}else{return form_submit('op', 'cancel'); return false;}"
													class="cssButton"> &nbsp;&nbsp;引&nbsp;当&nbsp;取&nbsp;消&nbsp;&nbsp; </a>
												</div>
											</td>
											<td width="50">
												<br>
											</td>
										</nested:equal>
										<%-- 非表示フラグが0:変更可の時のみ確定取消ボタンを表示 --%>
										<nested:equal property="orderInvisibleButtonEnableFlg" value="1">
											<td class="alignRight">
												<div id="cssButton"><a href="#"
													onclick="if(!putInvisibleConfirm()){return false;}else{return form_submit('op', 'invisible'); return false;}"
													class="cssButton"> &nbsp;&nbsp;非&nbsp;表&nbsp;示&nbsp;&nbsp; </a>
												</div>
											</td>
											<td width="50">
										</nested:equal>


									</nested:equal>
								</nested:equal>
							</nested:notEqual>
							<%-- 戻るボタンは常に表示 --%>
							<td class="alignLeft">
							<div id="cssButton"><a href="#"
								onclick="if(!putDirtyConfirm()){return false;}else{return form_submit('op', 'back'); return false;}"
								class="cssButton"> &nbsp;&nbsp;戻&nbsp;&nbsp;る&nbsp;&nbsp; </a>
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
	<nested:equal property="downloadFlg" value="true">
		<META HTTP-EQUIV="REFRESH" CONTENT="0; URL=<%= request.getContextPath() %>/FileDownload/">
	</nested:equal>
	<%-- 受注混雑不具合の為設定 --%>
	<input type="hidden" name="_dummy">
</nested:form>



</body>
</html:html>