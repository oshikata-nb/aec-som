<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taginclude.jsf"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http//www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<%@ include file="/jsp/common/head.jsf"%>
<%@ include file="/jsp/common/head_main.jsf"%>
<%@ include file="/jsp/common/clearText.jsf" %>
<%@ include file="/jsp/common/searchNameForMaster.jsf" %>

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
		defineAsRequiredField("srhScheduledShippingDateFrom");
		defineAsRequiredField("srhScheduledShippingDateTo");

		<%-- 日付型フィールド定義 --%>
		defineAsDateField("srhScheduledShippingDateFrom");
		defineAsDateField("srhScheduledShippingDateTo");

		<%-- 検索後入力された場合の不整合をただす --%>
		storeInitValues(/^srh.*/, 'dirtyFlg');

		refreshLabel();

	}, false);

	<%-- 指図自動作成確認メッセージ --%>
	function autoMakeConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "指図自動作成してもよろしいですか？";
		return confirm(alertMsg[0]);
	}
	
</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/ShippingAutoMake" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />

	<table cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td class="valignTop alignLeft">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td><%-- ヘッダー部 --%>
							<table border="0" cellpadding="0" cellspacing="0" width="100%">
								<tr>
									<td height="10" colspan="2"></td>
								</tr>
								<tr>
									<td class="fcTitle fb" width="250">出荷指図自動作成</td>
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
									<td height= 5 colspan="2"></td>
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
						<td><%-- 検索部 --%>
							<table cellspacing="1" cellpadding="1" width="750" border="0">
								<tr>
									<td><%-- ヘッダー部 --%>
										<table border="0" cellspacing="0" cellpadding="2">
											<tr>
												<td class="bcTitleSearch fb fcTitleSearch">出荷予定日</td>
												<td>
													<nested:text property="scheduledShippingDateFrom" maxlength="10" size="12" styleId="srhScheduledShippingDateFrom" style="ime-mode:disabled" /> ～													<nested:text property="scheduledShippingDateTo" maxlength="10" size="12" styleId="srhScheduledShippingDateTo" style="ime-mode:disabled" />
												</td>
											</tr>
										</table>
										<table border="0" cellspacing="0" cellpadding="2">
											<tr>
												<td>出荷指図データ</td>
												<td>
													<nested:text property="procNum" styleClass="alignRight" maxlength="2" size="2" styleId="procNum" disabled="true"/>
												</td>
												<td>件作成しました。</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td class="alignRight">
										<table cellspacing="1" cellpadding="1" border="0">
											<tr>
												<td>
													<div id="cssButton">
														<a href="#" onclick="if (!(autoMakeConfirm())) {return false;}else{return form_submit('op', 'autoMake'); return false;}" class="cssButton">
															&nbsp;&nbsp;指図自動作成&nbsp;&nbsp;
														</a>
														&nbsp;&nbsp;&nbsp;&nbsp;
														<a href="#" onclick="return form_submit('op', 'back'); return false;" class="cssButton">
															&nbsp;&nbsp;戻&nbsp;&nbsp;る&nbsp;&nbsp;
														</a>
													</div>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr height="5">
									<td></td>
								</tr>
								<tr>
									<td class="bcTitleLine"></td>
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
