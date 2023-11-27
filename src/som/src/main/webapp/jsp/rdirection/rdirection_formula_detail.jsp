<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taginclude.jsf"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http//www.w3.org/TR/html4/loose.dtd">
<%@page import="com.asahikaseieng.app.radiobuttons.RadioAutoOrHand"%>
<html:html>
<head>
<%@ include file="/jsp/common/head.jsf"%>
<%@ include file="/jsp/common/head_main.jsf"%>

<!-- Style Sheet(Item用) -->
<link href='<%= request.getContextPath() + "/stylesheets/tab.css"%>'
	media="screen" rel="Stylesheet" type="text/css" />

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
		defineAsRequiredField("resultQty");
		if ($("strLotDivision").value != "3") {
			if ($("strLotDivision").value == "2") {
				defineAsRequiredField("lotNo");
			}
		}

		<%-- 数値型フィールド定義 --%>
		defineAsNumberField("resultQty");
		defineAsNumberField("lossQty");
		defineAsNumberField("adjustQty");
		defineAsNumberField("tonyuSokudo");

		storeInitValues('dirtyFlg');

	}, false);

	<%-- 変更確認 --%>
	function putDirtyConfirm() {
		var flg = $("dirtyFlg").value;
		if (flg == "true") {
			// 何か値が変更されている場合
			return continueConfirm();
		}else{
			return true;
		}
	}
	<%-- 変更フラグセット --%>
	function setDirtyFlg() {
		$("dirtyFlg").value = true;
	}

	<%-- 確認メッセージ --%>
	function putUpdate() {
		alertMsg = new Array();
		alertMsg[0] = "登録を行います。よろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- フォーマット--%>
	function formatHaigo(obj){
		var value = obj.value;
		if (value != null) {
		    var decPoint = $('decimalPointHaigo').value;
		    var round = $('roundDivisionHaigo').value;
			obj.value = digitStringFormat(decPoint,round,value);
		}
	}

	<%-- フォーマット--%>
	function formatAdj(obj){
		var value = obj.value;
		if (value != null) {
		    var decPoint = $('decimalPointAdj').value;
		    var round = $('roundDivisionAdj').value;
			obj.value = digitStringFormat(decPoint,round,value);
		}
	}

	<%-- 投入速度のフォーマット--%>
	function tonyuSokudoQty(){
		var tonyuSokudo = $F("tonyuSokudo");
		if (tonyuSokudo != null) {
		    var decPoint = $('tonyuSokudoDP').value;
		    var round = $('tonyuSokudoRD').value;
			$("tonyuSokudo").value = digitStringFormat(decPoint,round,tonyuSokudo);
		}
	}

</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>
<nested:form action="/RdirectionFormulaDetail" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="dirtyFlg" />
	<nested:hidden property="lineNo" styleId="lineNo" />
	<nested:hidden property="formulaItemName" styleId="formulaItemName" />
	<nested:hidden property="formulaUnitName" styleId="formulaUnitName" />
	<nested:hidden property="decimalPointHaigo" styleId="decimalPointHaigo" />
	<nested:hidden property="roundDivisionHaigo" styleId="roundDivisionHaigo" />
	<nested:hidden property="decimalPointAdj" styleId="decimalPointAdj" />
	<nested:hidden property="roundDivisionAdj" styleId="roundDivisionAdj" />
	<nested:hidden property="tonyuSokudoDP" styleId="tonyuSokudoDP" />
	<nested:hidden property="tonyuSokudoRD" styleId="tonyuSokudoRD" />
	<nested:hidden property="strLotDivision" styleId="strLotDivision" />
	<nested:hidden property="formulaItemCd" styleId="formulaItemCd" />
	<nested:hidden property="strStockDivision" styleId="strStockDivision" />

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
									<td class="fcTitle fb">製造実績配合詳細</td>
									<td>
										<table cellspacing="0" cellpadding="0" border="0">
											<tr>
												<td>
													<%@ include file="/jsp/common/disp_info_message.jsf"%>
													<%-- メッセージ表示は、共通のメッセージ表示jsfをincludeする --%>
												</td>
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
						<td>
							<%-- メッセージ表示 --%> <%@ include file="/jsp/common/disp_error_message.jsf"%>
							<%-- メッセージ表示 ここまで --%>
						</td>
					</tr>

					<%-- ヘッダー部 --%>
					<tr>
						<td>
							<table cellspacing="2" cellpadding="2" border="0" width="100%">
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="120" >製造指図番号</td>
									<td width="150">
										<nested:write property="directionNo" />
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="120">品目コード</td>
									<td width="155">
										<nested:write property="itemCd" />
									</td>

									<td class="fcTitleDetail fb bcTitleDetail" width="120">品目名称</td>
									<td>
										<nested:write property="itemName" />
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="120">工程順序</td>
									<td>
										<nested:write property="stepNo" />
									</td>
									<td class="fcTitleDetail fb bcTitleDetail" width="120">仕込み予定数量</td>
									<td>
										<nested:write property="planedQty" />
										<nested:write property="unitName" />
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="120">工程コード</td>
									<td width="155">
										<nested:write property="operationCd" />
									</td>
									<td class="fcTitleDetail fb bcTitleDetail" width="120">工程名称</td>
									<td>
										<nested:write property="operationName" />
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="120">サブステップ</td>
									<td width="155">
										<nested:write property="seq" />
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td class="bcTitleLine"></td>
					</tr>
					<tr>
						<td height="5" colspan="2"></td>
					</tr>

					<%-- 明細部 --%>
					<tr>
						<td>
							<table width="750" cellspacing="2" cellpadding="1" border="0">
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">品目コード</td>
									<td>
										<nested:write property="formulaItemCd" />
									</td>
									<td>
										<nested:write property="formulaItemName" />
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">指図量</td>
									<td width="150" class="alignRight">
										<nested:write property="qty"/>
									</td>
									<td>
										kg
									<%-- 単位はkg固定とするがコメントとして残す --%>
									<%--
										<span id="lblFormulaUnitName"><nested:write property="formulaUnitName" /></span>
									--%>
									</td>
								</tr>
							</table>
							<table width="750" cellspacing="2" cellpadding="1" border="0">
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">投入方法</td>
									<td>
										<% pageContext.setAttribute("radioAutoOrHand",	new RadioAutoOrHand());	%>
										<logic:iterate id="mp" name="radioAutoOrHand" property="map">
											<nested:radio idName="mp" property="tonyu" onchange="setDirtyFlg();" styleId="tonyu" value="key" />
											<bean:write name="mp" property="value" />
										</logic:iterate>
									</td>
									<td></td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">データ読取</td>
									<td>
										<% pageContext.setAttribute("radioAutoOrHand",	new RadioAutoOrHand());	%>
										<logic:iterate id="mp" name="radioAutoOrHand" property="map">
											<nested:radio idName="mp" property="dataRead" onchange="setDirtyFlg();" styleId="dataRead" value="key" />
											<bean:write name="mp" property="value" />
										</logic:iterate>
									</td>
									<td></td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">投入速度</td>
									<td>
										<nested:text property="tonyuSokudo" maxlength="22" styleId="tonyuSokudo" onblur="tonyuSokudoQty();" onchange="setDirtyFlg();" />
										Hz
									</td>
									<td></td>
								</tr>
							</table>
							<table width="750" cellspacing="2" cellpadding="1" border="0">
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">在庫引落量</td>
									<td width="150" class="alignRight">
										<nested:write property="stockpdQty"/>
									</td>
									<td width="40">
										&nbsp;kg
										<%-- 単位はkg固定とするがコメントとして残す --%>
										<%--
										<span id="lblFormulaUnitName"><nested:write property="formulaUnitName" /></span>
										--%>
									</td>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">在庫量</td>
									<td class="alignLeft">
										<div id="cssButton">
											<!-- 20211209 Asclab Kouchi EdgeIE対応 -->
											<a href="#" class="cssButton"
												onclick="open_modal_popup_edge(700, 470,'RdirectionLotInventorySearch', '', 'srhItemCd',  $('formulaItemCd').value, 'srhItemName', $('formulaItemName').value); return false;">
											選</a>
										</div>
									</td>
								</tr>
							</table>
							<table width="750" cellspacing="2" cellpadding="1" border="0">
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">実績数量</td>
									<td >
										<nested:text property="resultQty" maxlength="22" styleId="resultQty" onblur="formatHaigo(this);" onchange="setDirtyFlg();" style="ime-mode:disabled"/>
										kg
										<%-- 単位はkg固定とするがコメントとして残す --%>
										<%--
										<span id="lblFormulaUnitName"><nested:write property="formulaUnitName" /></span>
										--%>
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">ロス数量</td>
									<td>
										<nested:text property="lossQty" maxlength="22" styleId="lossQty" onblur="formatHaigo(this);" onchange="setDirtyFlg();" style="ime-mode:disabled"/>
										kg
										<%-- 単位はkg固定とするがコメントとして残す --%>
										<%--
										<span id="lblFormulaUnitName"><nested:write property="formulaUnitName" /></span>
										--%>
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">調整数量</td>
									<td>
										<nested:text property="adjustQty" maxlength="22" styleId="adjustQty" onblur="formatAdj(this);" onchange="setDirtyFlg();" style="ime-mode:disabled"/>
										kg
										<%-- 単位はkg固定とするがコメントとして残す --%>
										<%--
										<span id="lblFormulaUnitName"><nested:write property="formulaUnitName" /></span>
										--%>
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">入荷ロット</td>
									<td>
									<nested:equal property="strStockDivision" value="3">
											<nested:write property="lotNo"/>
									</nested:equal>
									<nested:notEqual property="strStockDivision" value="3">
										<nested:equal property="strLotDivision" value="2">
											<nested:text property="lotNo" styleId="lotNo" onchange="setDirtyFlg();" style="ime-mode:disabled"/>
										</nested:equal>
										<nested:notEqual property="strLotDivision" value="2">
											<nested:write property="lotNo"/>
										</nested:notEqual>
									</nested:notEqual>
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">投入条件</td>
									<td>
										<nested:textarea property="stepCondition" rows="5" cols="60" styleId="stepCondition" onchange="setDirtyFlg();" />
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">備考</td>
									<td>
										<nested:textarea property="remark" rows="5" cols="60" styleId="remark" onchange="setDirtyFlg();" />
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">注釈</td>
									<td>
										<nested:textarea property="notes" rows="5" cols="60" styleId="notes" onchange="setDirtyFlg();" />
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
									<nested:lessThan property="directionStatus" value="8">
										<nested:equal property="updateAuthority" value="true">
											<td class="alignCenter">
												<div id="cssButton">
													<a href="#" onclick="if (!(putUpdate())) {return false;}else{return form_submit('op', 'update'); return false;}" class="cssButton">
														&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
													</a>
												</div>
											</td>
										</nested:equal>
									</nested:lessThan>
									<td class="alignCenter">
										<div id="cssButton">
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
