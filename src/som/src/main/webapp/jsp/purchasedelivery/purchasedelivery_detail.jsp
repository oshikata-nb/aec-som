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

		<%-- 日付型フィールド定義 --%>
		defineAsRequiredField("strSuggestedDeliverlimitDate");
		defineAsDateField("strSuggestedDeliverlimitDate");
		<%-- 時刻型フィールド定義 --%>
		defineAsRequiredField("strSuggestedDeliverlimitDateTime");
		defineAsTimeField("strSuggestedDeliverlimitDateTime");

		storeInitValues(/^srh.*/, 'dirtyFlg');
		refreshLabel();

	}, false);

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

	<%-- まとめ入力画面への遷移 --%>
	function toSumDetail(url) {
		if (!putDirtyConfirm()) {
			return false;
		} else {
		    if (!g_lock) {
		        if (continueConfirm()) {
		        	var orderSheetNo = $F("orderSheetNo");
		          	location.href = url + orderSheetNo;
		  			g_lock = true;
		    	}
	      	}
		}
	}

</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/PurchaseDeliveryDetail" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="dirtyFlg" />
	<nested:hidden property="unit" styleId="unit" />
	<nested:hidden property="orderSheetNo" styleId="orderSheetNo" />
	<nested:hidden property="buySubcontractOrderNo" styleId="buySubcontractOrderNo" />

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
							<td class="fcTitle fb" width="250">納期回答入力</td>
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
									<td class="fcTitleDetail fb bcTitleDetail">発注番号</td>
									<td><nested:write property="strBuySubcontractOrderNo" /></td>
									<td class="fcTitleDetail fb bcTitleDetail">発注書NO</td>
									<td><nested:write property="orderSheetNo" /></td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">発注日</td>
									<td><nested:write property="strOrderDate" /></td>
									<td class="fcTitleDetail fb bcTitleDetail">発注者</td>
									<td><nested:write property="tantoNm" /></td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">品目コード</td>
									<td><nested:write property="itemCd" /></td>
									<td class="fcTitleDetail fb bcTitleDetail">品目名称</td>
									<td><nested:write property="itemQueueName" /></td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">他社コード１</td>
									<td><nested:write property="otherCompanyCd1" /></td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">仕入先コード</td>
									<td><nested:write property="venderCd" /></td>
									<td class="fcTitleDetail fb bcTitleDetail">仕入先名称</td>
									<td><nested:write property="venderName" /></td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">数量</td>
									<td>
										<span style="width:120;" class="alignRight">
											<nested:write property="strOrderQuantity" />
										</span>
										　<nested:write property="unit" />
									</td>
									<td class="fcTitleDetail fb bcTitleDetail">荷姿</td>
									<td><nested:write property="styleOfPacking" /></td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">重量</td>
									<td>
										<span style="width:120;" class="alignRight">
											<nested:write property="strOrderConvertQuantity" />
										</span>
										　Kg
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">納入先</td>
									<td><nested:write property="locationCd" /></td>
									<td class="fcTitleDetail fb bcTitleDetail">納入先名称</td>
									<td><nested:write property="locationName" /></td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch" width="110">ステータス</td>
									<td>
										<%
											pageContext.setAttribute("selectPurchaseStatusDelivery",
											new com.asahikaseieng.app.comboboxes.SelectPurchaseStatusDelivery(false, true));
										%>
										<nested:select property="status" styleId="status">
											<nested:options name="selectPurchaseStatusDelivery" property="values" labelName="selectPurchaseStatusDelivery" labelProperty="labels" />
										</nested:select>
									</td>
									<td class="fcTitleDetail fb bcTitleDetail" width="120">仕入先受注番号</td>
									<td>
										<nested:text property="siOrderNo" maxlength="20" size="10" styleId="siOrderNo" onchange="setDirtyFlg();" />
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">担当部署コード</td>
									<td><nested:write property="chargeOrganizationCd" /></td>
									<td class="fcTitleDetail fb bcTitleDetail">担当部署名</td>
									<td><nested:write property="chargeOrganizationName" /></td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">部署コード</td>
									<td><nested:write property="organizationCd" /></td>
									<td class="fcTitleDetail fb bcTitleDetail">部署名</td>
									<td><nested:write property="organizationName" /></td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">納品希望日時</td>
									<td>
										<nested:text property="strSuggestedDeliverlimitDate" maxlength="8" size="8" styleId="strSuggestedDeliverlimitDate" style="ime-mode:disabled" onchange="setDirtyFlg();" />
										<nested:text property="strSuggestedDeliverlimitDateTime" maxlength="4" size="4" styleId="strSuggestedDeliverlimitDateTime" style="ime-mode:disabled" onchange="setDirtyFlg();" />
									</td>
									<td class="fcTitleDetail fb bcTitleDetail">分納有チェック</td>
									<td>
										<nested:checkbox property="replyContentsDivision" styleId="replyContentsDivision" />
									</td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td>
							<table border="0" cellspacing="2" cellpadding="1">
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">発注書備考</td>
									<td><nested:textarea property="orderSheetRemark" cols="70" rows="3" styleId="orderSheetRemark" /></td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">備考</td>
									<td><nested:textarea property="remark" cols="70" rows="3" styleId="remark" /></td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td class="alignCenter">
							<table>
								<tr>
									<%-- ステータスが 2:発注書発行済 ～ 4:納期確定の場合登録ボタンを表示 --%>
									<nested:greaterThan property="status" value="1">
										<nested:lessThan property="status" value="5">
	
											<nested:equal property="updateAuthority" value="true">
												<td>
													<div id="cssButton">
														<a href="#" onclick="if (!(putConfirm())) {return false;}else{return form_submit('op', 'update'); return false;}" class="cssButton">
														&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
														</a>
													</div>
												</td>
												<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												</td>
												<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												</td>
											</nested:equal>
										</nested:lessThan>
									</nested:greaterThan>
									
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
