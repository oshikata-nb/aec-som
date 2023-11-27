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

		var count = $F("detailCount");
		for(var i = 0 ; i < count; i++){
			var prefix = "detailList[" + i + "].";

			<%-- 必須・日付・時刻型フィールド定義 --%>
			<%-- 納品希望日 --%>
			defineAsRequiredField(prefix + "strSuggestedDeliverlimitDate");
			defineAsDateField(prefix + "strSuggestedDeliverlimitDate");
			<%-- 納品希望時刻 --%>
			defineAsTimeField(prefix + "strSuggestedDeliverlimitDateTime");
		}

		storeInitValues(/^srh.*/, 'dirtyFlg');
		refreshLabel();

	}, false);

	<%-- 詳細画面への遷移 --%>
	function toDetail(url) {
	    if (!g_lock) {
	        if (continueConfirm()) {
	          location.href=url;
			  g_lock = true;
	    	}
      	}
	}

	<%-- 確認メッセージ --%>
	function putConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "登録してよろしいですか？";

		return confirm(alertMsg[0]);
	}

	<%-- 変更フラグセット --%>
	function setDirtyFlg() {
		$("dirtyFlg").value = "true";
	}
	
	<%-- 変更有の確認メッセージ --%>
	function putDirtyConfirm() {
		var flg = $("dirtyFlg").value;
		if (flg == "true") {
			// 何か値が変更されている場合
			return continueConfirm();
		}else{
			return true;
		}
	}

</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/PurchaseDeliverySumDetail" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="dirtyFlg" />
	<nested:hidden property="detailCount" styleId="detailCount" />

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
							<td class="fcTitle fb" width="250">納期回答まとめ入力</td>
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
					<table border="0" cellspacing="" cellpadding="" width="100%">
						<tr>
							<td>
							<table border="0" cellspacing="2" cellpadding="1">
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="80">発注書NO</td>
									<td><nested:write property="orderSheetNo" /></td>
									<td class="fcTitleDetail fb bcTitleDetail" width="80">発注日</td>
									<td><nested:write property="strOrderDate" /></td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="80">仕入先</td>
									<td><nested:write property="venderName" /></td>
									<td class="fcTitleDetail fb bcTitleDetail" width="80">納入先</td>
									<td><nested:write property="locationName" /></td>
								</tr>
							</table>
							<table border="0" cellspacing="2" cellpadding="1">
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">全オーダー件数</td>
									<td width="30" class="alignRight"><nested:write property="strOrderCount" /></td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">発注書発行済件数</td>
									<td width="30" class="alignRight"><nested:write property="strIssuedCount" /></td>
									<td width="10"></td>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">納期調整中件数</td>
									<td width="30" class="alignRight"><nested:write property="strAdjustCount" /></td>
									<td width="10"></td>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">納期確定件数</td>
									<td width="30" class="alignRight"><nested:write property="strFixedCount" /></td>
									<td width="10"></td>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">入荷・受入済件数</td>
									<td width="30" class="alignRight"><nested:write property="strArrivedAcceptedCount" /></td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td height="10"></td>
						</tr>
						<tr>
							<td><%-- 複数明細部 --%>
							<table cellspacing="2" cellpadding="1" id="tblList">
								<tr class="bcTitleList fb fcTitleList">
									<td>発注番号</td>
									<td>品目</td>
									<td>数量</td>
									<td>単位</td>
									<td>荷姿</td>
									<td>重量</td>
									<td>納品<br>希望日</td>
									<td>納品<br>希望時刻</td>
									<td>仕入先<br>受注番号</td>
									<td>ｽﾃｰﾀｽ</td>
									<td>分納</td>
								</tr>
								<%-- 明細部 >>>>> --%>
								<nested:iterate id="detailList" property="detailList" indexId="index">
									<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
										<tr class="bcList1">
									</app:modulo>
									<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
										<tr class="bcList2">
									</app:modulo>

									<nested:define id="pno" property="purchaseNo" />
									<td><%-- 発注番号 --%>
										<html:link href="#" onclick='<%="toDetail('" + request.getContextPath() + "/PurchaseDeliveryDetail.do?op=init&purchaseNo=" + pageContext.findAttribute("pno").toString() + "'); return false;"%>'>
											<nested:write property="strBuySubcontractOrderNo" />
										</html:link>
									</td>
									<td><%-- 品目 --%>
										<nested:write property="itemQueueName" />
									</td>
									<td class="alignRight"><%-- 数量 --%>
										<nested:write property="strOrderQuantity" />
									</td>
									<td><%-- 単位 --%>
										<nested:write property="unit" />
									</td>
									<td><%-- 荷姿 --%>
										<nested:write property="styleOfPacking" />
									</td>
									<td class="alignRight"><%-- 重量 --%>
										<nested:write property="strOrderConvertQuantity" />
									</td>
									<td><%-- 納品希望日 --%>
										<nested:text property="strSuggestedDeliverlimitDate" maxlength="8" size="8" styleId="strSuggestedDeliverlimitDate" style="ime-mode:disabled" onchange="setDirtyFlg();" />
									</td>
									<td><%-- 納品希望時刻 --%>
										<nested:text property="strSuggestedDeliverlimitDateTime" maxlength="4" size="4" styleId="strSuggestedDeliverlimitDateTime" style="ime-mode:disabled" onchange="setDirtyFlg();" />
									</td>
									<td><%-- 仕入先受注番号 --%>
										<nested:text property="siOrderNo" maxlength="20" size="10" styleId="siOrderNo" onchange="setDirtyFlg();" />
									</td>
									<td><%-- ｽﾃｰﾀｽ --%>
										<%
											pageContext.setAttribute("selectPurchaseStatusDelivery",
											new com.asahikaseieng.app.comboboxes.SelectPurchaseStatusDelivery(false, true));
										%>
										<nested:select property="status" styleId="status" onchange="setDirtyFlg();">
											<nested:options name="selectPurchaseStatusDelivery" property="values" labelName="selectPurchaseStatusDelivery" labelProperty="labels" />
										</nested:select>
										
									</td>
									<td><%-- 分納 --%>
										<nested:write property="strReplyContentsDivision" />
									</td>
								</tr>
								</nested:iterate>
							</table>
							</td>
						</tr>
						<tr>
							<td class="alignCenter">
							<table>
								<tr>
									<nested:equal property="updateAuthority" value="true">
										<td>
										<div id="cssButton">
											<a href="#" onclick="if (!(putConfirm())) {return false;}else{return form_submit('op', 'update'); return false;}" class="cssButton">
											&nbsp;&nbsp;納期回答登録&nbsp;&nbsp;
											</a>
										</div>
										</td>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
									</nested:equal>
									<td class="allRight">
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
			</td>
		</tr>
	</table>

</nested:form>
</body>
</html:html>
