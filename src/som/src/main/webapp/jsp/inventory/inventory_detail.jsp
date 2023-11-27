<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/jsp/common/taginclude.jsf" %>

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
		defineAsRequiredField("strLastInDate");
		defineAsRequiredField("itemCd");
		defineAsRequiredField("locationCd");
		defineAsRequiredField("strPackQty");
		defineAsRequiredField("strFraction");
		defineAsRequiredField("strInventoryCost");
		defineAsRequiredField("strLabelCount");
		defineAsRequiredField("ryCd");

		<%-- 数値型フィールド定義 --%>
		defineAsNumberField("strPackQty");
		defineAsNumberField("strFraction");
		defineAsNumberField("strInventoryCost");
		defineAsNumberField("strLabelCount");

		<%-- 日付型フィールド定義 --%>
		defineAsDateField("strLastInDate");

		<%-- 品目のautocomplete --%>
		new Ajax.Autocompleter(
			"itemCd",
			"autocomplete_choices",
			"<%= request.getContextPath() %>/ItemForAutoComplete.do?op=searchDetailDigitItem",
			{
				paramName : "code",
				afterUpdateElement : setItemLabel
			}
		);

		<%-- 他社コードのautocomplete --%>
		new Ajax.Autocompleter(
			"otherCompanyCd1",
			"autocomplete_choices",
			"<%= request.getContextPath() %>/ItemForAutoComplete.do?op=searchDetailDigitItemOtherCompany",
			{
				paramName : "code",
				afterUpdateElement : setOtherCompanyLabel
			}
		);

		<%-- ロケーションのautocomplete --%>
		new Ajax.Autocompleter(
			"locationCd",
			"autocomplete_choices",
			"<%= request.getContextPath() %>/LocationForAutoComplete.do?op=searchAvailableLocation",
			{
				paramName : "code",
				afterUpdateElement : setLocationLabel
			}
		);

		<%-- 理由のautocomplete --%>
		new Ajax.Autocompleter(
			"ryCd",
			"autocomplete_choices",
			"<%= request.getContextPath() %>/ReasonForAutoComplete.do?op=searchReason",
			{
				paramName : "code",
				afterUpdateElement : setReasonLabel
			}
		);

		<%-- 検索後入力された場合の不整合をただす --%>
		Event.observe('itemCd', 'keypress', clearLabel.bindAsEventListener($('itemName')), false);
		Event.observe('itemCd', 'keypress', clearText.bindAsEventListener($('otherCompanyCd1')), false);
		Event.observe('itemCd', 'keypress', clearLabel.bindAsEventListener($('styleOfPacking')), false);
		Event.observe('itemCd', 'keypress', clearText.bindAsEventListener($('lotNo')), false);
		Event.observe('itemCd', 'keypress', clearText.bindAsEventListener($('aliasLotNo')), false);
		Event.observe('itemCd', 'keypress', clearText.bindAsEventListener($('kgOfFractionManagemtnt')), false);
		Event.observe('itemCd', 'keypress', clearText.bindAsEventListener($('kgConversionCoefficient')), false);
		Event.observe('itemCd', 'keypress', clearText.bindAsEventListener($('unitOfOperationManagement')), false);
		Event.observe('itemCd', 'keypress', clearText.bindAsEventListener($('unitOfFractionManagement')), false);
		Event.observe('itemCd', 'keypress', clearText.bindAsEventListener($('smallnumLength')), false);
		Event.observe('itemCd', 'keypress', clearText.bindAsEventListener($('roundDivision')), false);
		Event.observe('otherCompanyCd1', 'keypress', clearLabel.bindAsEventListener($('itemName')), false);
		Event.observe('otherCompanyCd1', 'keypress', clearLabel.bindAsEventListener($('styleOfPacking')), false);
		Event.observe('otherCompanyCd1', 'keypress', clearText.bindAsEventListener($('lotNo')), false);
		Event.observe('otherCompanyCd1', 'keypress', clearText.bindAsEventListener($('aliasLotNo')), false);
		Event.observe('locationCd', 'keypress', clearLabel.bindAsEventListener($('locationName')), false);
		Event.observe('ryCd', 'keypress', clearLabel.bindAsEventListener($('ryDescription')), false);

		<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
		<%-- 対象外の例：検索条件部と明細のチェックボックス --%>
		storeInitValues(/^srh.*/);	

		<%-- カーソル位置セット --%>
		setActivate();

		refreshLabel();
	}, false);

	<%-- 確認メッセージ --%>
	function putConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "登録してよろしいですか？";
		return confirm(alertMsg[0]);
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
	
	<%-- カーソルセット --%>
	function setCursor(pos) {
		$("cursor").value = pos;
	}

	<%-- カーソル位置セット --%>
	function setActivate() {
		if ($('cursor').value == '1') {
			Field.activate('strPackQty');
		}else if ($('cursor').value == '2') {
			Field.activate('strFraction');
		}else if ($('cursor').value == '3') {
			Field.activate('itemCd');
		}else if ($('cursor').value == '4') {
			Field.activate('otherCompanyCd1');
		}
	}

	<%-- 変更フラグセット --%>
	function setDirtyFlg() {
		$("dirtyFlg").checked = true;
	}

	<%-- ロット管理チェック --%>
	function chkLot(flg) {
		if (flg == 1) {
			<%-- ロット管理しない品目 --%>
			$("lotNo").value = "999999";
			$("aliasLotNo").value = "";
			$("lotNo").disabled = true;
			$("aliasLotNo").disabled = true;
		} else if (flg == 2) {
			<%-- ロット管理する品目 --%>
			$("lotNo").value = "";
			$("aliasLotNo").value = "";
			$("lotNo").disabled = false;
			$("aliasLotNo").disabled = false;
		}

		refreshLabel();
	}

	<%-- ajax --%>
	function setItemLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("itemName").value = li.getElementsByTagName('span')[0].innerText;
		$("otherCompanyCd1").value = getHiddenValue(li,"otherCompanyCd1");
		$("styleOfPacking").value = getHiddenValue(li,"styleOfPacking");
		$("smallnumLength").value = getHiddenValue(li,"smallnumLength");
		$("roundDivision").value = getHiddenValue(li,"roundDivision");
		$("kgOfFractionManagement").value = getHiddenValue(li,"kgOfFractionManagement");
		$("kgConversionCoefficient").value = getHiddenValue(li,"kgConversionCoefficient");
		$("unitOfOperationManagement").value = getHiddenValue(li,"unitOfOperationManagement");
		$("unitOfFractionManagement").value = getHiddenValue(li,"unitOfFractionManagement");
		var flg = getHiddenValue(li,"lotDivision");

		<%-- カーソルセット --%>
		setCursor('3');

		<%-- ロット管理チェック --%>
//		if (flg == '1') {
//			chkLot(1);
//		} else if (flg == '2') {
//			chkLot(2);
//		}

		$("lotDivision").value = flg;

//		form_submit('op', 'calcTotalPlus');

		<%-- カーソル位置セット --%>
//		setActivate();

		<%-- ロット管理チェック --%>
		chkLot(flg);

		<%-- 総量計算 --%>
		calcInventory();

		refreshLabel();
	}

	function setOtherCompanyLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("itemName").value = li.getElementsByTagName('span')[0].innerText;
		$("itemCd").value = getHiddenValue(li,"code");
		$("styleOfPacking").value = getHiddenValue(li,"styleOfPacking");
		$("smallnumLength").value = getHiddenValue(li,"smallnumLength");
		$("roundDivision").value = getHiddenValue(li,"roundDivision");
		$("kgOfFractionManagement").value = getHiddenValue(li,"kgOfFractionManagement");
		$("kgConversionCoefficient").value = getHiddenValue(li,"kgConversionCoefficient");
		$("unitOfOperationManagement").value = getHiddenValue(li,"unitOfOperationManagement");
		$("unitOfFractionManagement").value = getHiddenValue(li,"unitOfFractionManagement");
		var flg = getHiddenValue(li,"lotDivision");

		<%-- カーソルセット --%>
		setCursor('4');

		<%-- ロット管理チェック --%>
//		if (flg == '1') {
//			chkLot(1);
//		} else if (flg == '2') {
//			chkLot(2);
//		}

		$("lotDivision").value = flg;

//		form_submit('op', 'calcTotalPlus');

		<%-- カーソル位置セット --%>
//		setActivate();

		<%-- ロット管理チェック --%>
		chkLot(flg);

		<%-- 総量計算 --%>
		calcInventory();
		
		refreshLabel();
	}

	<%-- 総量計算 --%>
	function calcInventory() {
		var packQty = strToNum($F("strPackQty"), 0) + "";
		var fraction = strToNum($F("strFraction"), 0) + "";
		var kgOfFractionManagement = strToNum($F("kgOfFractionManagement"), 0) + "";
		var kgConversionCoefficient = strToNum($F("kgConversionCoefficient"), 0) + "";
//		var inventoryQty = packQty * strToNum($F("kgOfFractionManagement"), 0)
//						+ fraction * strToNum($F("kgConversionCoefficient"), 0);

		<%-- 小数点位置を取得 --%>
		var pos1 = packQty.lastIndexOf(".");
		var pos2 = fraction.lastIndexOf(".");
		var pos3 = kgOfFractionManagement.lastIndexOf(".");
		var pos4 = kgConversionCoefficient.lastIndexOf(".");
		var pos = 0;

		<%-- 数値文字長を取得 --%>
		var len1 = packQty.length;
		var len2 = fraction.length;
		var len3 = kgOfFractionManagement.length;
		var len4 = kgConversionCoefficient.length;

		<%-- 小数点を取る --%>
		packQty = parseFloat(packQty.replace(/\./g,""));
		kgOfFractionManagement = parseFloat(kgOfFractionManagement.replace(/\./g,""));
		fraction = parseFloat(fraction.replace(/\./g,""));
		kgConversionCoefficient = parseFloat(kgConversionCoefficient.replace(/\./g,""));
		
		<%-- 小数点位置を訂正 --%>
		if (pos1 == -1) {
			pos1 = 0;
		} else {
			pos1 = len1 - pos1 - 1;
		}
		
		if (pos2 == -1) {
			pos2 = 0;
		} else {
			pos2 = len2 - pos2 - 1;
		}

		if (pos3 == -1) {
			pos3 = 0;
		} else {
			pos3 = len3 - pos3 - 1;
		}

		if (pos4 == -1) {
			pos4 = 0;
		} else {
			pos4 = len4 - pos4 - 1;
		}

		var weight = 0;

		packQty = packQty * kgOfFractionManagement;
		weight = Math.pow(10, pos1 + pos3);
		packQty = packQty / weight + "";

		fraction = fraction * kgConversionCoefficient;
		weight = Math.pow(10, pos2 + pos4);
		fraction = fraction / weight + "";

		<%-- 小数点位置を取得 --%>
		pos1 = packQty.lastIndexOf(".");
		pos2 = fraction.lastIndexOf(".");

		<%-- 数値文字長を取得 --%>
		len1 = packQty.length;
		len2 = fraction.length;

		<%-- 小数点を取る --%>
		packQty = parseFloat(packQty.replace(/\./g,"")) + "";
		fraction = parseFloat(fraction.replace(/\./g,"")) + "";

		<%-- 小数点位置を訂正 --%>
		if (pos1 == -1) {
			pos1 = 0;
		} else {
			pos1 = len1 - pos1 - 1;
		}
		
		if (pos2 == -1) {
			pos2 = 0;
		} else {
			pos2 = len2 - pos2 - 1;
		}

		<%-- 最大小数点桁数を取得 --%>
		pos = pos1;

		if (pos < pos2) {
			<%-- 桁合わせ --%>
			for (i = 0; i <= pos2 - pos - 1; i++) {
				packQty = packQty + "0";
			}

			pos = pos2;
		} else {
			<%-- 桁合わせ --%>
			for (i = 0; i <= pos - pos2 - 1; i++) {
				fraction = fraction + "0";
			}
		}

		inventoryQty = strToNum(packQty, 0) + strToNum(fraction, 0);
		weight = Math.pow(10, pos);
		inventoryQty = inventoryQty / weight;
		
		$("inventoryQty").value = inventoryQty;
//		$("strInventoryQty").value = digitFormat($F("smallnumLength"), $F("roundDivision"), inventoryQty);
		$("strInventoryQty").value = formatNumber(String(inventoryQty));

		refreshLabel();
	}

	<%-- 数値変換(不正な数値の場合defを返す) --%>
	function strToNum(str, def) {
		var val = parseFloat(str.replace(/,/g, ""));

		if(isNaN(val)){
			val = def;
		}

		return val;
	}

	function setLocationLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("locationName").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
	}

	function setReasonLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("ryDescription").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
	}

</script>
<%-- ▲業務固有javaScriptここまで --%>

</head>

<body>

<nested:form action="/Inventory" method="post" onsubmit="return false;" styleId="mainForm">	
	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="cursor" styleId="cursor" />
	<nested:hidden property="lotDivision" styleId="lotDivision" />
	<nested:hidden property="dirtyFlg" styleId="dirtyFlg" />
	<nested:hidden property="kgOfFractionManagement" styleId="kgOfFractionManagement" />
	<nested:hidden property="kgConversionCoefficient" styleId="kgConversionCoefficient" />
	<nested:hidden property="unitOfOperationManagement" styleId="unitOfOperationManagement" />
	<nested:hidden property="unitOfFractionManagement" styleId="unitOfFractionManagement" />
	<nested:hidden property="inventoryQty" styleId="inventoryQty" />
	<nested:hidden property="smallnumLength" styleId="smallnumLength" />
	<nested:hidden property="roundDivision" styleId="roundDivision" />

	<table cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td class="valignTop alignLeft">
				<table border="0" cellpadding="0" cellspacing="0" width="750">
					<tr>
						<td>
							<!-- ヘッダー部 -->
							<table border="0" cellpadding="0" cellspacing="0" width="100%">
								<tr>
									<td height="10" colspan="2"></td>
								</tr>

								<tr>
									<td class="fcTitle fb">在庫入庫入力</td>
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
									<td class="bcTitleLine" colspan="4"></td>
								</tr>

								<tr>
									<td height="5" colspan="4"></td>
								</tr>
							</table>
						</td>
					</tr>

					<tr>
						<td>
							<%-- メッセージ表示 --%>
							<%@ include file="/jsp/common/disp_error_message.jsf" %>
							<%-- メッセージ表示 ここまで --%>
						</td>
					</tr>

					<tr>
						<td>
							<!-- 明細部 -->
							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">日付</td>
									<td colspan="3">
										<nested:text property="strLastInDate" maxlength="10" size="10" style="ime-mode:disabled" styleId="strLastInDate" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">品目コード</td>
									<td>
										<nested:text property="itemCd" maxlength="20" size="20" styleId="itemCd" onchange="setDirtyFlg();" />
										<div id="autocomplete_choices" class="autocomplete"></div>
									</td>

									<td colspan="2" width="500">
										<div id="lblItemName">
											<nested:write property="itemName"/>
										</div>
										<nested:hidden property="itemName" styleId="itemName" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">他社コード1</td>
									<td colspan="3">
										<nested:text property="otherCompanyCd1" maxlength="20" size="20" styleId="otherCompanyCd1" style="ime-mode:disabled" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">荷姿</td>
									<td colspan="3">
										<div id="lblStyleOfPacking">
											<nested:write property="styleOfPacking" />
										</div>
										<nested:hidden property="styleOfPacking" styleId="styleOfPacking"/>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">入庫ロケーション</td>
									<td>
										<nested:text property="locationCd" maxlength="20" size="20" styleId="locationCd" onchange="setDirtyFlg();" />
									</td>

									<td colspan="2" width="500">
										<div id="lblLocationName">
											<nested:write property="locationName"/>
										</div>
										<nested:hidden property="locationName" styleId="locationName" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">入荷ロット番号<BR>包装指図番号</td>
									<td>
										<nested:equal property="lotDivision" value="1">
											<nested:text property="lotNo" maxlength="11" size="11" style="ime-mode:disabled" styleId="lotNo" disabled="true" onchange="setDirtyFlg();" />
										</nested:equal>

										<nested:notEqual property="lotDivision" value="1">
											<nested:text property="lotNo" maxlength="11" size="11" style="ime-mode:disabled" styleId="lotNo" onchange="setDirtyFlg();" />
										</nested:notEqual>
									</td>

									<td class="fcTitleDetail fb bcTitleDetail">原料ロット番号<BR>製品ロット番号</td>
									<td>
										<nested:equal property="lotDivision" value="1">
											<nested:text property="aliasLotNo" maxlength="20" size="20" style="ime-mode:disabled" styleId="aliasLotNo" disabled="true" onchange="setDirtyFlg();" />
										</nested:equal>

										<nested:notEqual property="lotDivision" value="1">
											<nested:text property="aliasLotNo" maxlength="20" size="20" style="ime-mode:disabled" styleId="aliasLotNo" onchange="setDirtyFlg();" />
										</nested:notEqual>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">荷姿数</td>
									<td colspan="3">
										<nested:text property="strPackQty" maxlength="10" size="10" style="ime-mode:disabled" styleClass="alignRight" styleId="strPackQty" onchange="setCursor('1');calcInventory();setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">端数</td>
									<td colspan="3">
										<nested:text property="strFraction" maxlength="10" size="10" style="ime-mode:disabled" styleClass="alignRight" styleId="strFraction" onchange="setCursor('2');calcInventory();setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">総量(Kg)</td>
									<td colspan="3">
										<div id="lblStrInventoryQty">
											<nested:write property="strInventoryQty"/>
										</div>
										<nested:hidden property="strInventoryQty" styleId="strInventoryQty" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">単価</td>
									<td colspan="3">
										<nested:text property="strInventoryCost" maxlength="10" size="10" style="ime-mode:disabled" styleClass="alignRight" styleId="strInventoryCost" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">ラベル枚数</td>
									<td colspan="3">
										<nested:text property="strLabelCount" maxlength="5" size="5" style="ime-mode:disabled" styleClass="alignRight" styleId="strLabelCount" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">理由コード</td>
									<td colspan="3">
										<nested:text property="ryCd" maxlength="4" size="4" styleId="ryCd" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">理由</td>
									<td colspan="3">
										<div id="lblRyDescription">
											<nested:textarea property="ryDescription" rows="3" cols="65" readonly="true" styleClass="noborderAl" styleId="ryDescription" onchange="setDirtyFlg();" />
											<nested:write property="ryDescription"/>
										</div>
										<nested:hidden property="ryDescription" styleId="ryDescription" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">摘要</td>
									<td colspan="3">
										<nested:textarea property="remark" rows="3" cols="65" styleId="remark" onchange="setDirtyFlg();" />
									</td>
								</tr>
							</table>
						</td>
					</tr>

					<tr>
						<td class="alignCenter">
							<table cellpadding="0" cellspacing="0" width="450" border="0">
								<tr>
									<td colspan="3" height="2">
									</td>
								</tr>

								<tr>
									<nested:equal property="updateAuthority" value="true">
										<td class="alignRight">
											<div id="cssButton">
												<a href="#" onclick="if(!putConfirm()){return false;}else{return form_submit('op', 'update'); return false;}" class="cssButton">
													&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
												</a>
											</div>
										</td>
	
										<td width="50">
											<br>
										</td>
									</nested:equal>

									<td class="alignLeft">
										<div id="cssButton">
											<a href="#" onclick="return form_submit('op', 'report'); return false;" class="cssButton">
												&nbsp;&nbsp;ラベル発行&nbsp;&nbsp;
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
		<%-- Excelダウンロード処理 --%>
		<nested:equal property="excelDownloadFlg" value="true">
			<META HTTP-EQUIV="REFRESH" CONTENT="0; URL=<%= request.getContextPath() %>/FileDownload/">
		</nested:equal>
		<%-- Excelダウンロード処理 ここまで --%>
	</table>
</nested:form>

</body>
</html:html>
