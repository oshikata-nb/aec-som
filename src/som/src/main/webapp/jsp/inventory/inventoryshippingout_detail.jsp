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
		defineAsRequiredField("strPackQty");
		defineAsRequiredField("strFraction");
		defineAsRequiredField("ryCd");

		<%-- 数値型フィールド定義 --%>
		defineAsNumberField("strPackQty");
		defineAsNumberField("strFraction");

		<%-- 日付型フィールド定義 --%>
		defineAsDateField("strLastInDate");

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
		Event.observe('ryCd', 'keypress', clearLabel.bindAsEventListener($('ryDescription')), false);

		<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
		<%-- 対象外の例：検索条件部と明細のチェックボックス --%>
		storeInitValues('dirtyFlg');

		if ($('cursor').value == '1') {
			Field.activate('strPackQty');
		}else if ($('cursor').value == '2') {
			Field.activate('strFraction');
		}

		refreshLabel();
	}, false);

	<%-- 確認メッセージ --%>
	function putConfirm() {
		<%-- カーソルセット --%>
		setCursor(null);

		alertMsg = new Array();
		alertMsg[0] = "登録してよろしいですか？";
		return confirm(alertMsg[0]);
	}
	
	<%-- 変更確認メッセージ --%>
	function putDirtyConfirm() {
		var flg = $("dirtyFlg").value;
		if (flg == "true") {
			/* 何か値が変更されている場合 */
			<%-- return continueConfirm(); --%>
			return confirm("<bean:message key="message.confirm.under.edit" />");
		}else{
			return true;
		}
	}
	
	<%-- 変更フラグセット --%>
	function setDirtyFlg() {
		$("dirtyFlg").value = "true";
	}

	<%-- カーソルセット --%>
	function setCursor(pos) {
		$("cursor").value = pos;
	}

	<%-- ajax --%>
	function setReasonLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("ryDescription").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
		setDirtyFlg();
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
</script>
<%-- ▲業務固有javaScriptここまで --%>

</head>

<body>

<nested:form action="/InventoryShippingoutDetail" method="post" onsubmit="return false;" styleId="mainForm">
	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="cursor" styleId="cursor" />
	<nested:hidden property="dirtyFlg" styleId="dirtyFlg" />
	<nested:hidden property="kgOfFractionManagement" styleId="kgOfFractionManagement" />
	<nested:hidden property="kgConversionCoefficient" styleId="kgConversionCoefficient" />
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
									<td class="fcTitle fb">在庫出庫入力</td>
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
									<td class="bcTitleLine" colspan="6"></td>
								</tr>

								<tr>
									<td height="5" colspan="6"></td>
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
									<td colspan="5">
										<nested:text property="strLastInDate" maxlength="10" size="10" style="ime-mode:disabled" styleId="strLastInDate"/>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">品目コード</td>
									<td>
										<nested:write property="itemCd"/>
									</td>

									<td colspan="4">
										<nested:write property="itemName"/>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">他社コード1</td>
									<td colspan="5">
										<nested:write property="otherCompanyCd1"/>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">荷姿</td>
									<td colspan="5">
										<nested:write property="styleOfPacking" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">出庫ロケーション</td>
									<td>
										<nested:write property="locationCd" />
									</td>

									<td colspan="4">
										<nested:write property="locationName" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">入荷ロット番号<BR>包装指図番号</td>
									<td>
										<nested:write property="lotNo" />
									</td>

									<td class="fcTitleDetail fb bcTitleDetail" width="130">原料ロット番号<BR>製品ロット番号</td>
									<td colspan="2">
										<nested:write property="aliasLotNo" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">荷姿在庫数</td>
									<td>
										<nested:write property="strDispPackQty" />
										<nested:write property="packUnit" />
									</td>

									<td class="fcTitleDetail fb bcTitleDetail" width="130">荷姿数</td>
									<td>
										<nested:text property="strPackQty" maxlength="22" size="10" style="ime-mode:disabled" styleClass="alignRight" styleId="strPackQty" onchange="setCursor('1');setDirtyFlg();calcInventory();" />
										<nested:write property="packUnit" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">端数在庫数</td>
									<td>
										<nested:write property="strDispFraction" />
										<nested:write property="fractionUnit" />
									</td>

									<td class="fcTitleDetail fb bcTitleDetail">端数</td>
									<td>
										<nested:text property="strFraction" maxlength="22" size="10" style="ime-mode:disabled" styleClass="alignRight" styleId="strFraction" onchange="setCursor('2');setDirtyFlg();calcInventory();" />
										<nested:write property="fractionUnit" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">総量(Kg)</td>
									<td colspan="5">
										<div id="lblStrInventoryQty">
											<nested:write property="strInventoryQty"/>
										</div>
										<nested:hidden property="strInventoryQty" styleId="strInventoryQty" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">理由コード</td>
									<td colspan="5">
										<nested:text property="ryCd" maxlength="4" size="4" styleId="ryCd" onchange="setDirtyFlg();" />
										<div id="autocomplete_choices" class="autocomplete"></div>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">理由</td>
									<td colspan="5">
										<div id="lblRyDescription">
											<nested:textarea property="ryDescription" rows="3" cols="65" readonly="true" styleClass="noborderAl" styleId="ryDescription" onchange="setDirtyFlg();" />
											<nested:write property="ryDescription"/>
										</div>
										<nested:hidden property="ryDescription" styleId="ryDescription" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">摘要</td>
									<td colspan="5">
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
											<a href="#" onclick="if(!putDirtyConfirm()){return false;}else{return form_submit('op', 'back'); return false;}" class="cssButton">
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
