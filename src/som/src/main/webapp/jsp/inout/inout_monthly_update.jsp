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
		defineAsRequiredField("srhStrInputYear");
		defineAsRequiredField("srhStrInputMonth");

		<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
		<%-- 対象外の例：検索条件部と明細のチェックボックス --%>
		storeInitValues(/^srh.*/);	

		refreshLabel();
	}, false);

	<%-- 確認メッセージ --%>
	function putExecuteConfirm() {
		<%-- 日付変換 --%>
		convDate();

		alertMsg = new Array();
		alertMsg[0] = "実行してよろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- 日付変換 --%>
	function convDate() {
		var strMonth = $("srhStrInputMonth").value;

		if ($("srhStrInputYear").value == "" && $("srhStrInputMonth").value == ""){
			$("srhStrInputDate").value = ""
		} else {
			if (strMonth.length > 1 ) {
				$("srhStrInputDate").value = $("srhStrInputYear").value + "/" + $("srhStrInputMonth").value + "/01";
		} else {
				$("srhStrInputDate").value = $("srhStrInputYear").value + "/0" + $("srhStrInputMonth").value + "/01";
			}
		}
	}
</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/InoutMonthlyUpdate" method="post" onsubmit="return false;" styleId="mainForm">	
	<%@ include file="/jsp/common/postjavascript.jsf" %>

	<nested:hidden property="op" styleId="op"/>
	<nested:hidden property="srhStrInputDate" styleId="srhStrInputDate"/>

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
									<td class="fcTitle fb" width="250">月次受払更新処理</td>
									<td>
										<table border="0" cellpadding="0" cellspacing="0">
											<tr>
												<td>
													<%-- メッセージ表示 --%>
													<%@ include file="/jsp/common/disp_info_message.jsf" %>
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
									<td height="5" colspan="2"></td>
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
							<!-- 検索部 -->
							<table width="100%" cellspacing="" cellpadding=""  border="0">
								<tr>
									<td>
										<table cellspacing="2" cellpadding="1"  border="0">
											<tr> 
												<td class="bcTitleSearch fb fcTitleSearch" width="130">処理年月</td>
												<td>
													<nested:text property="srhStrInputYear" maxlength="4" size="4" styleId="srhStrInputYear" style="ime-mode:disabled" styleClass="alignRight"/>年
												</td>

												<td>
													<nested:text property="srhStrInputMonth" maxlength="2" size="2" styleId="srhStrInputMonth" style="ime-mode:disabled" styleClass="alignRight"/>月
												</td>
											</tr>
										</table>
									</td>
								</tr>

								<nested:equal property="updateAuthority" value="true">
									<tr>
										<td class="alignRight">
											<table cellspacing="1" cellpadding="1"  border="0">
												<tr>
													<td>
														<div id="cssButton">
															<a href="#" onclick="if(!putExecuteConfirm()){return false;}else{return form_submit('op', 'update'); return false;}" class="cssButton">
																&nbsp;&nbsp;実&nbsp;&nbsp;行&nbsp;&nbsp;
															</a>
														</div>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</nested:equal>

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
