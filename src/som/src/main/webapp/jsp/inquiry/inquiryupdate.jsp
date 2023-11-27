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
		defineAsRequiredField("strSrhCountDate");
		defineAsRequiredField("strSrhUpdateDate");

		<%-- 日付型フィールド定義 --%>
		defineAsDateField("strSrhCountDate");
		defineAsDateField("strSrhUpdateDate");

		<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
		storeInitValues(/^srh.*/);	

		refreshLabel();
	}, false);

	<%-- 確認メッセージ --%>
	function putConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "登録してよろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- 確認メッセージ:行削除 --%>
	function putDelListConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "行を削除してよろしいですか？";
		return confirm(alertMsg[0]);
	}

	function open_window(theURL,winName,features) { //v2.0
		win = window.open(theURL,winName,features);
		win.focus();
		return false;
	}
</script>
<%-- ▲業務固有javaScriptここまで --%>

</head>

<body>

<nested:form action="/InquiryUpdate" method="post" onsubmit="return false;" styleId="mainForm">
	<%@ include file="/jsp/common/postjavascript.jsf" %>

	<nested:hidden property="op" styleId="op" />

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
									<td class="fcTitle fb" width="250">ロット別棚卸更新処理</td>
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
									<td class="bcTitleLine" colspan="3"></td>
								</tr>

								<tr>
									<td height="5" colspan="3"></td>
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
									<td class="bcTitleSearch fb fcTitleSearch" width="130">棚卸準備処理日</td>
									<td>
										<nested:text property="strSrhCountDate" maxlength="10" size="10" styleId="strSrhCountDate" style="ime-mode:disabled"/>
									</td>
								</tr>

								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">棚卸更新日</td>
									<td>
										<nested:text property="strSrhUpdateDate" maxlength="10" size="10" styleId="strSrhUpdateDate" style="ime-mode:disabled"/>
									</td>
								</tr>

								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">棚卸区分</td>
									<td>
										<nested:select property="srhCountDivision" >
											<nested:options property="srhCountDivisionValues" labelProperty="srhCountDivisionLabels" />
										</nested:select>
									</td>
								</tr>
							</table>

							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td height="5" colspan="2"></td>
								</tr>

								<tr>
									<td class="bcTitleLine" colspan="2"></td>
								</tr>

								<tr>
									<td class="alignCenter">
										<table cellpadding="0" cellspacing="0" width="450" border="0">
											<tr>
												<td colspan="2" height="2">
												</td>
											</tr>
			
											<tr>
												<td class="alignRight">
													<div id="cssButton">
														<a href="#" onclick="return form_submit('op', 'addlist'); return false;" class="cssButton">
															&nbsp;&nbsp;行&nbsp;追&nbsp;加&nbsp;&nbsp;
														</a>
													</div>
												</td>
			
												<td width="50">
													<br>
												</td>
			
												<td class="alignLeft">
													<div id="cssButton">
														<a href="#" onclick="if(!(putDelListConfirm())){return false;}else{return form_submit('op', 'dellist'); return false;}" class="cssButton">
															&nbsp;&nbsp;行&nbsp;削&nbsp;除&nbsp;&nbsp;
														</a>
													</div>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>

							<table width="100%" cellspacing="2" cellpadding="2" border="0" id="tblList">
								<tr class="bcTitleList fb fcTitleList">
									<td width="20"></td>
									<td width="100">棚卸区分</td>
									<td>棚卸区分名称</td>
								</tr>

								<nested:notEmpty property="searchList">
									<nested:iterate id="searchList" property="searchList" indexId="index">
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
											<tr class="bcList1">
										</app:modulo>

										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
											<tr class="bcList2">
										</app:modulo>

										<td><%-- 行追加行削除用チェックボックス --%>
											<nested:checkbox property="checked" styleId="<%="checked" + pageContext.findAttribute("index").toString() %>" />
										</td>

										<td>
											<%-- 棚卸区分 --%>
											<nested:write property="nameCd" />
										</td>

										<td>
											<%-- 棚卸区分名称 --%>
											<nested:write property="name01" />
										</td>
									</nested:iterate>
								</nested:notEmpty>
							</table>
						</td>
					</tr>

					<tr>
						<td class="alignCenter">
							<table cellpadding="0" cellspacing="0" width="450" border="0">
								<tr>
									<td colspan="2" height="2">
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
											<a href="#" onclick="return form_submit('op', 'clear'); return false;" class="cssButton">
												&nbsp;&nbsp;ク&nbsp;リ&nbsp;ア&nbsp;&nbsp;
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
