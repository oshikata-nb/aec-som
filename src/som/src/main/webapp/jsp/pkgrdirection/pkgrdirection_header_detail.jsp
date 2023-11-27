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

		<%-- 必須フィールド定義 --%>
		defineAsRequiredField("resultSDay");
		defineAsRequiredField("resultEDay");
		defineAsRequiredField("resultSTime");
		defineAsRequiredField("resultETime");

		<%-- 日付型フィールド定義 --%>
		defineAsDateField("resultSDay");
		defineAsDateField("resultEDay");
		defineAsDateField("nextSDay");
		defineAsDateField("nextEDay");

		<%-- 時間型フィールド定義 --%>
		defineAsTimeField("resultSTime");
		defineAsTimeField("resultETime");
		defineAsTimeField("nextSTime");
		defineAsTimeField("nextETime");

		<%-- 数値型フィールド定義 --%>
		defineAsNumberField("nextPlanedQty");

		storeInitValues(/^srh.*/, 'dirtyFlg');
		refreshLabel();

	}, false);

	<%-- 変更フラグセット --%>
	function setDirtyFlg() {
		$("dirtyFlg").value = true;
	}

	<%-- 予定数量のフォーマット--%>
	function formatNextPlanedQty(){
		var qty = $F("nextPlanedQty");
		if (qty != null) {
		    var decPoint = $('qtyDecimalPoint').value;
		    var round = $('qtyRoundDivision').value;
			$("nextPlanedQty").value = digitStringFormat(decPoint,round,qty);
		}
	}

	<%-- 確認メッセージ --%>
	function putProcConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "登録してよろしいですか？";
		if ($('divideFlag').checked == true) {
			alertMsg[0] = "分納処理を実行してよろしいですか？";
		} else {
			if ($('srhDirectionStatus') != null) {
				var selValue = $('srhDirectionStatus').options[$('srhDirectionStatus').selectedIndex].value;
				if (selValue == "7") {
					alertMsg[0] = "完了しますか？";
				}
			}
		}
		return confirm(alertMsg[0]);
	}


</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/PkgRdirectionHeaderDetail" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

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
							<td class="fcTitle fb" width="250" >包装実績ヘッダー情報</td>
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

				<%-- 共通項目部・タブ>>>>> --%>
				<%@ include file="/jsp/pkgrdirection/pkgrdirection_common.jsp"%>
				<%-- 共通項目部・タブ<<<<< --%>

<%-- 詳細内容 --%>
				<tr>
					<td>
					<span id="slidetabs" style="clear: left;">
						<table cellspacing="2" cellpadding="1"  border="0">
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="130">ロット番号</td>
								<td>
									<nested:lessThan property="directionStatus" value="5">
										<nested:text property="lotNo" maxlength="20" size="18" styleId="lotNo" onchange="setDirtyFlg();" style="ime-mode:disabled" />
									</nested:lessThan>
									<nested:equal property="directionStatus" value="5">
										<nested:write property="lotNo" />
									</nested:equal>
									<nested:equal property="directionStatus" value="6">
										<nested:write property="lotNo" />
									</nested:equal>
									<nested:equal property="directionStatus" value="7">
										<nested:write property="lotNo" />
									</nested:equal>
								</td>
								<td></td>
								<%-- 4：包装記録済の場合 --%>
								<nested:equal property="directionStatus" value="4">
									<td class="fcTitleDetail fb bcTitleDetail" width="130">ステータス</td>
									<td width="130">
										<%
											pageContext.setAttribute( "selectPkgRdirectionDirectionStatus",
											new com.asahikaseieng.app.comboboxes.SelectPkgRdirectionDirectionStatus(false, true, "4"));
										%>
										<nested:select property="inputDirectionStatus" styleId="srhDirectionStatus">
											<nested:options name="selectPkgRdirectionDirectionStatus" property="values" labelName="selectPkgRdirectionDirectionStatus" labelProperty="labels" />
										</nested:select>
									</td>
								</nested:equal>
								<%-- 5：検査待ち在庫計上の場合 --%>
								<nested:equal property="directionStatus" value="5">
									<td class="fcTitleDetail fb bcTitleDetail" width="130">ステータス</td>
									<td width="130">
										<%
											pageContext.setAttribute( "selectPkgRdirectionDirectionStatus",
											new com.asahikaseieng.app.comboboxes.SelectPkgRdirectionDirectionStatus(false, true, "5"));
										%>
										<nested:select property="inputDirectionStatus" styleId="srhDirectionStatus">
											<nested:options name="selectPkgRdirectionDirectionStatus" property="values" labelName="selectPkgRdirectionDirectionStatus" labelProperty="labels" />
										</nested:select>
									</td>
								</nested:equal>
								<%-- 6：製品検査完了の場合 --%>
								<nested:equal property="directionStatus" value="6">
									<td class="fcTitleDetail fb bcTitleDetail" width="130">ステータス</td>
									<td width="130">
										<%
											pageContext.setAttribute( "selectPkgRdirectionDirectionStatus",
											new com.asahikaseieng.app.comboboxes.SelectPkgRdirectionDirectionStatus(false, true, "6"));
										%>
										<nested:select property="inputDirectionStatus" styleId="srhDirectionStatus">
											<nested:options name="selectPkgRdirectionDirectionStatus" property="values" labelName="selectPkgRdirectionDirectionStatus" labelProperty="labels" />
										</nested:select>
									</td>
								</nested:equal>
								<%-- 4：包装記録済より小さいステータスの場合は、ステータス選択なし --%>
								<nested:lessThan property="directionStatus" value="4">
									<nested:hidden property="inputDirectionStatus" styleId="inputDirectionStatus" />
								</nested:lessThan>
								<%-- 6：製品検査完了より大きいステータスの場合は、ステータス選択なし --%>
								<nested:greaterThan property="directionStatus" value="6">
									<nested:hidden property="inputDirectionStatus" styleId="inputDirectionStatus" />
								</nested:greaterThan>
							</tr>
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="130">包装開始実績日時</td>
								<td><nested:text property="resultSDay" maxlength="10" size="18" styleId="resultSDay" onchange="setDirtyFlg();" style="ime-mode:disabled" /></td>
								<td><nested:text property="resultSTime" maxlength="5" size="18" styleId="resultSTime" onchange="setDirtyFlg();" style="ime-mode:disabled" /></td>
							</tr>
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="130">包装終了実績日時</td>
								<td><nested:text property="resultEDay" maxlength="10" size="18" styleId="resultEDay" onchange="setDirtyFlg();" style="ime-mode:disabled" /></td>
								<td><nested:text property="resultETime" maxlength="5" size="18" styleId="resultETime" onchange="setDirtyFlg();" style="ime-mode:disabled" /></td>
							</tr>
					<nested:lessThan property="directionStatus" value="4">
							<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="130" rowspan="3" >分納フラグ</td>
									<td><nested:checkbox property="divideFlag" styleId="divideFlag"></nested:checkbox>分納</td>
									
									<td class="fcTitleDetail fb bcTitleDetail" width="130">次回予定数量</td>
									<td><nested:text property="nextPlanedQty" maxlength="22" size="20" styleId="nextPlanedQty" onchange="setDirtyFlg();" onblur="formatNextPlanedQty();" style="ime-mode:disabled" />
									</td>
							</tr>
							<tr>
								<td ></td>
								<td class="fcTitleDetail fb bcTitleDetail" width="130">次回包装開始日時</td>
								<td><nested:text property="nextSDay" maxlength="10" size="18" styleId="nextSDay" onchange="setDirtyFlg();" style="ime-mode:disabled" /></td>
								<td><nested:text property="nextSTime" maxlength="5" size="18" styleId="nextSTime" onchange="setDirtyFlg();" style="ime-mode:disabled" /></td>
							</tr>
							<tr>
								<td ></td>
								<td class="fcTitleDetail fb bcTitleDetail" width="130">次回包装終了日時</td>
								<td><nested:text property="nextEDay" maxlength="10" size="18" styleId="nextEDay" onchange="setDirtyFlg();" style="ime-mode:disabled" /></td>
								<td><nested:text property="nextETime" maxlength="5" size="18" styleId="nextETime" onchange="setDirtyFlg();" style="ime-mode:disabled" /></td>
							</tr>
					</nested:lessThan>
					<nested:greaterEqual property="directionStatus" value = "4">
							<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="130" rowspan="3">分納フラグ</td>
									<td><nested:checkbox property="divideFlag" styleId="divideFlag" disabled="true"></nested:checkbox>分納</td>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">次回予定数量</td>
									<td><nested:text property="nextPlanedQty" maxlength="22" size="20" styleId="nextPlanedQty" disabled="true" />
									</td>
							</tr>
							<tr>
								<td ></td>
								<td class="fcTitleDetail fb bcTitleDetail" width="130">次回包装開始日時</td>
								<td><nested:text property="nextSDay" maxlength="10" size="18" styleId="nextSDay" disabled="true"  /></td>
								<td><nested:text property="nextSTime" maxlength="5" size="18" styleId="nextSTime" disabled="true"  /></td>
							</tr>
							<tr>
								<td ></td>
								<td class="fcTitleDetail fb bcTitleDetail" width="130">次回包装終了日時</td>
								<td><nested:text property="nextEDay" maxlength="10" size="18" styleId="nextEDay" disabled="true"  /></td>
								<td><nested:text property="nextETime" maxlength="5" size="18" styleId="nextETime" disabled="true"  /></td>
							</tr>
					</nested:greaterEqual>
									
								
						</table>
						<table cellspacing="2" cellpadding="1"  border="0">
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="130" >備考</td>
								<td colspan="3">
									<nested:textarea property="remark" cols="70" rows="4" styleId="remark" onchange="setDirtyFlg();" />
								</td>
							</tr>
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="130" >注釈</td>
								<td colspan="3">
									<nested:textarea property="notes" cols="70" rows="4" styleId="notes" onchange="setDirtyFlg();" />
								</td>
							</tr>
						</table>
					</span>
					</td>
				</tr>
				<tr>
					<td class="alignCenter">
					<table border="0" cellspacing="0" cellpadding="0" width="100%">
						<nested:notEqual property="directionStatus" value="7">
							<nested:equal property="updateAuthority" value="true">
								<td class="alignRight">
								<div id="cssButton">
									<a href="#" onclick="if (!(putProcConfirm())) {return false;}else{return form_submit('op', 'update'); return false;}" class="cssButton">
									&nbsp;&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;録&nbsp;&nbsp;
									</a>
								</div>
								</td>
							</nested:equal>
						<td class="alignLeft">
							<div id="cssButton">
								<a href="#" onclick="if (!(putDirtyConfirm())) {return false;}else{return form_submit('op', 'back'); return false;}" class="cssButton">
									&nbsp;&nbsp;戻&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;る&nbsp;&nbsp;
								</a>
							</div>
						</td>
						</nested:notEqual>
						<nested:equal property="directionStatus" value="7">
						<td class="alignCenter">
							<div id="cssButton">
								<a href="#" onclick="if (!(putDirtyConfirm())) {return false;}else{return form_submit('op', 'back'); return false;}" class="cssButton">
									&nbsp;&nbsp;戻&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;る&nbsp;&nbsp;
								</a>
							</div>
						</td>
						</nested:equal>
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
