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
		defineAsRequiredField("inputResultSdateDay");
		defineAsRequiredField("inputResultSdateTime");
		defineAsRequiredField("inputResultEdateDay");
		defineAsRequiredField("inputResultEdateTime");

		<%-- 日付型フィールド定義 --%>
		defineAsDateField("inputResultSdateDay");
		defineAsTimeField("inputResultSdateTime");
		defineAsDateField("inputSteritSdateDay");
		defineAsTimeField("inputSteritSdateTime");
		defineAsDateField("inputResultEdateDay");
		defineAsTimeField("inputResultEdateTime");
		defineAsDateField("inputSteritEdateDay");
		defineAsTimeField("inputSteritEdateTime");

		storeInitValues(/^srh.*/, 'dirtyFlg');
		refreshLabel();

	}, false);

	<%-- 確認メッセージ --%>
	function putProcConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "登録してよろしいですか？";
		if ($('inputDirectionStatus') != null) {
			var selValue = $('inputDirectionStatus').options[$('inputDirectionStatus').selectedIndex].value;
			if (selValue == "8") {
				alertMsg[0] = "完了しますか？";
			}
		}
		return confirm(alertMsg[0]);
	}

</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/RdirectionHeader" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

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
							<td class="fcTitle fb">製造実績ヘッダー情報</td>
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
				<%@ include file="/jsp/rdirection/rdirection_common.jsp"%>
				<%-- 共通項目部・タブ<<<<< --%>

<%-- 詳細内容 --%>
				<tr>
					<td>
					<span id="slidetabs" style="clear: left;">
						<table border="0" cellspacing="2" cellpadding="2" width="100%">
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="130">仕込み実績数量</td>
								<td>
									<nested:write property="resultQty" />
									<nested:notEmpty property="resultQty">
										<nested:write property="unitName" />
									</nested:notEmpty>
								</td>
								<nested:notEqual property="directionStatus" value="7">
									<td colspan="2"></td>
								</nested:notEqual>
								<nested:equal property="directionStatus" value="7">
									<td class="fcTitleDetail fb bcTitleDetail" width="130">ｽﾃｰﾀｽ</td>
									<td>
										<%
											pageContext.setAttribute( "selectDirectionHeaderStatus",new com.asahikaseieng.app.comboboxes.SelectRdirectionHeaderListStatus(false, true));
										%>
										<nested:select property="inputDirectionStatus" style="margin: 0;padding: 0" styleId="inputDirectionStatus">
											<nested:options name="selectDirectionHeaderStatus" property="values" labelName="selectDirectionHeaderStatus" labelProperty="labels" />
										</nested:select>
									</td>
								</nested:equal>
							</tr>
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="130">工程ロット番号</td>
								<td colspan="3">
									<nested:text property="lotNo" maxlength="20" size="20" styleId="lotNo" onchange="setDirtyFlg();" style="ime-mode:disabled" styleClass="alignLeft"/>
								</td>
							</tr>
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="130">製造開始実績日時</td>
								<td>
									<nested:text property="inputResultSdateDay" maxlength="10" size="12" styleId="inputResultSdateDay" style="ime-mode:disabled" onchange="setDirtyFlg();" />
									<nested:text property="inputResultSdateTime" maxlength="5" size="7" styleId="inputResultSdateTime" style="ime-mode:disabled" onchange="setDirtyFlg();" />
								</td>
								<td class="fcTitleDetail fb bcTitleDetail" width="130">滅菌作業開始時間</td>
								<td>
									<nested:text property="inputSteritSdateDay" maxlength="10" size="12" styleId="inputSteritSdateDay" style="ime-mode:disabled" onchange="setDirtyFlg();" />
									<nested:text property="inputSteritSdateTime" maxlength="5" size="7" styleId="inputSteritSdateTime" style="ime-mode:disabled" onchange="setDirtyFlg();" />
								</td>
							</tr>
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="130">製造終了実績日時</td>
								<td>
									<nested:text property="inputResultEdateDay" maxlength="10" size="12" styleId="inputResultEdateDay" style="ime-mode:disabled" onchange="setDirtyFlg();" />
									<nested:text property="inputResultEdateTime" maxlength="5" size="7" styleId="inputResultEdateTime" style="ime-mode:disabled" onchange="setDirtyFlg();" />
								</td>
								<td class="fcTitleDetail fb bcTitleDetail" width="130">滅菌作業終了時間</td>
								<td>
									<nested:text property="inputSteritEdateDay" maxlength="10" size="12" styleId="inputSteritEdateDay" style="ime-mode:disabled" onchange="setDirtyFlg();" />
									<nested:text property="inputSteritEdateTime" maxlength="5" size="7" styleId="inputSteritEdateTime" style="ime-mode:disabled" onchange="setDirtyFlg();" />
								</td>
							</tr>
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="130">備考</td>
								<td colspan="3">
									<nested:textarea property="remark" cols="80" rows="4" onchange="setDirtyFlg();" styleId="remark" />
								</td>

							</tr>
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="130">注釈</td>
								<td colspan="3">
									<nested:textarea property="notes" cols="80" rows="4" onchange="setDirtyFlg();" styleId="notes" />
								</td>
							</tr>
						</table>
					</span>
					</td>
				</tr>
				<tr>
					<td align="center">
					<table>
						<tr>
							<nested:lessThan property="directionStatus" value="8">
								<nested:equal property="updateAuthority" value="true">
									<td class="alignLeft">
										<div id="cssButton">
											<a href="#" onclick="if (!(putProcConfirm())) {return false;}else{return form_submit('op', 'update'); return false;}" class="cssButton">
											&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
											</a>
										</div>
									</td>
								</nested:equal>
							</nested:lessThan>
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

</nested:form>
</body>
</html:html>
