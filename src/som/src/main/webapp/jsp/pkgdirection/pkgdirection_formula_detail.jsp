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

		<%-- 数値型フィールド定義 --%>
		defineAsNumberField("qty");

		<%-- 必須フィールド定義 --%>
		defineAsRequiredField("formulaItemCd");
		defineAsRequiredField("qty");

		storeInitValues('dirtyFlg');

		<%-- 品目名称のauto complete --%>
		new Ajax.Autocompleter(
		  "formulaItemCd",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/ItemForAutoComplete.do?op=searchDetailItem",
		  {
		  	paramName : "code",
		    afterUpdateElement : setItemLabel
		  }
		);
		<%-- 名称のクリアauto complete --%>
		Event.observe('formulaItemCd', 'keypress', clearLabel.bindAsEventListener($('formulaItemName')), false);
		Event.observe("formulaItemCd", 'keypress', clearText.bindAsEventListener($('formulaItemName')), false);
		Event.observe("formulaItemCd", 'keypress', clearDivLabel.bindAsEventListener($('lblQtyUnitName')), false);
		Event.observe("formulaItemCd", 'keypress', clearText.bindAsEventListener($('qtyUnitName')), false);

	}, false);

	<%-- 品目名称auto completeの選択後処理 --%>
	function setItemLabel(text, li) {
	    $("lblFormulaItemName").update(li.getElementsByTagName('span')[0].innerText);
	    $("lblQtyUnitName").update(getHiddenValue(li, "name01"));
	    $("formulaItemName").value = li.getElementsByTagName('span')[0].innerText;
	    $("lblQtyUnitName").value = getHiddenValue(li, "name01");

	    formatQty();
	}
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

	<%-- 予定数量のフォーマット--%>
	function formatQty(){
		var qty = $F("qty");
		if (qty != null) {
		    var decPoint = $('qtyDecimalPoint').value;
		    var round = $('qtyRoundDivision').value;
			$("qty").value = digitStringFormat(decPoint,round,qty);
		}
	}

</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>
<nested:form action="/PkgDirectionFormulaDetail" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="dirtyFlg" />
	<nested:hidden property="directionDivision" styleId="directionDivision" />
	<nested:hidden property="directionNo" styleId="directionNo" />
	<nested:hidden property="stepNo" styleId="stepNo" />
	<nested:hidden property="lineNo" styleId="lineNo" />
	<nested:hidden property="formulaItemName" styleId="formulaItemName" />
	<nested:hidden property="unitName" styleId="unitName" />
	<nested:hidden property="qtyDecimalPoint" styleId="qtyDecimalPoint" />
	<nested:hidden property="qtyRoundDivision" styleId="qtyRoundDivision" />

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
									<td class="fcTitle fb" width="250">包装指図入力配合詳細</td>
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
									<td class="fcTitleDetail fb bcTitleDetail" width="120" >指図番号</td>
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
									<td class="fcTitleDetail fb bcTitleDetail" width="120">生産予定数量</td>
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
							<table width="100%" cellspacing="2" cellpadding="1" border="0">
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="160">品目コード</td>
									<td width="120">
										<nested:text property="formulaItemCd" styleId="formulaItemCd" maxlength="20" size="20" onchange="setDirtyFlg();" style="ime-mode:disabled"/>
										<div id="autocomplete_selection" class="autocomplete"></div>
									</td>
									<td class="alignLeft">
										<div id="lblFormulaItemName">
											<nested:write property="formulaItemName" />
										</div>
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="160">予定数量</td>
									<td width="120">
										<nested:text property="qty" styleId="qty" maxlength="22" size="20" onblur="formatQty();" onchange="setDirtyFlg();" style="ime-mode:disabled"/>
									</td>
									<td>
										<div id="lblQtyUnitName">
										<nested:write property="qtyUnitName" />
										</div>
									</td>
								</tr>
							</table>
							<table width="100%" cellspacing="2" cellpadding="1" border="0">
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="160">包装条件</td>
									<td>
										<nested:textarea property="stepCondition" rows="5" cols="60" styleId="stepCondition" onchange="setDirtyFlg();" />
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="160">備考</td>
									<td>
										<nested:textarea property="remark" rows="5" cols="60" styleId="remark" onchange="setDirtyFlg();" />
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="160">注釈</td>
									<td>
										<nested:textarea property="notes" rows="5" cols="60" styleId="notes" onchange="setDirtyFlg();" />
									</td>
								</tr>
							</table>

						</td>
					</tr>

					<tr>
						<td class="alignCenter">
							<table cellpadding="0" cellspacing="0" width="80%" border="0">
								<tr>
									<td colspan="3" height="2"></td>
								</tr>

								<tr>
									<nested:equal property="jissekiFlag" value="0">
										<nested:equal property="updateAuthority" value="true">
											<td class="alignCenter">
												<div id="cssButton">
													<a href="#" onclick="if (!(putUpdate())) {return false;}else{return form_submit('op', 'update'); return false;}" class="cssButton">
														&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
													</a>
												</div>
											</td>
										</nested:equal>
									</nested:equal>
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
