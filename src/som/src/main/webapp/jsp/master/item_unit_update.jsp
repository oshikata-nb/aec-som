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
	<%-- 20211209 Asclab Kouchi EdgeIE対応 --%>
	<%-- 選択して終了 --%>
	function selectOK(){
		<%-- 日付の / を取る --%>
		var d = unformatDate($("strInputDate").value);

		<%-- 日付チェック --%>
		var retDate = checkDate(d);

		if (!retDate) {
			alert("正しい見積変更開始基準日を入力して下さい。");
		} else {
			alertMsg = new Array();
			alertMsg[0] = "見積の標準単価を一括更新します。よろしいですか？";
			var ret = confirm(alertMsg[0]);

			if (ret) {
				window.opener.setInputDate($("strInputDate").value, "true");

				<%-- モーダルダイアログの終了 --%>
				window.close();
			}
		}
	}

	<%-- 20211209 Asclab Kouchi EdgeIE対応 --%>
	<%-- キャンセルして終了 --%>
	function selectCancel(){
		window.opener.setInputDate($("strInputDate").value, "false");
		window.close();
	}

	<%-- Load時の処理 --%>
	Event.observe(window, 'load', function() {
		<%-- フォーム設定の初期化 --%>
		initializeFormState();

		<%-- 必須フィールド定義 --%>
        defineAsRequiredField("strInputDate");

		<%-- 日付型フィールド定義 --%>
		defineAsDateField("strInputDate");
	}, false);
</script>
<%-- ▲業務固有javaScript ここまで --%>

<base target="_self"><%-- ←重要(subumit時に新規windowが開かないようにするおまじない) --%>
</head>

<body>

<nested:form action="/ItemUnitUpdate" method="post" onsubmit="return false;" styleId="mainForm">
	<%@ include file="/jsp/common/postjavascript.jsf" %>

	<nested:hidden property="op" styleId="op"/>

	<table cellpadding="0" cellspacing="5" border="0">
		<tr>
			<td class="valignTop alignLeft">
				<table border="0" cellpadding="0" cellspacing="0" width="400">
					<tr>
						<td>
							<!-- ヘッダー部 -->
							<table border="0" cellpadding="0" cellspacing="0" width="100%">
								<tr>
									<td height="5" colspan="2"></td>
								</tr>

								<tr>
									<td class="fcTitle fb" nowrap>見積単価更新</td>
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
										<table cellspacing="2" cellpadding="2"  border="0">
											<tr>
												<td class="bcTitleSearch fb fcTitleSearch" width="150">見積変更開始基準日</td>
												<td>
													<nested:text property="strInputDate" maxlength="10" size="10" styleId="strInputDate" style="ime-mode:disabled" />
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>

							<table width="100%" cellspacing="" cellpadding=""  border="0">
								<tr>
									<td colspan="1" height="50">
									</td>

									<td>
										<table width="100%" cellspacing="2" cellpadding="2"  border="0">
											<tr>
												<td class="alignCenter">開始基準日以降(含む)に有効な見積の標準単価を
												</td>
											</tr>

											<tr>
												<td class="alignCenter">一括で変更します。
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>

				<table border="0" cellpadding="2" cellspacing="0" width="100%">
					<tr>
						<td colspan="3" height="50">
						</td>
					</tr>

					<tr>
						<td class="alignRight">
							<div id="cssButton">
								<a href="#" onclick="<%="selectOK()" %>" class="cssButton">
									&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
								</a>
							</div>
						</td>

						<td width="50">
							<br>
						</td>

						<td class="alignLeft">
							<div id="cssButton">
								<a href="#" onclick="<%="selectCancel()" %>" class="cssButton">
									&nbsp;&nbsp;キャンセル&nbsp;&nbsp;
								</a>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</nested:form>

</body>
</html:html>
