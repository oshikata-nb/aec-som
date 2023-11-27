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
        defineAsRequiredField("accountsCd");

		<%-- 前後空白除去定義 --%>
		if ($('newFlg').value == 'true'){
        	defineAsKeyField("accountsCd");
       	};

		<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
		storeInitValues('dirtyFlg');
	}, false);

	<%-- 確認メッセージ --%>
	function putConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "登録してよろしいですか？";
		return confirm(alertMsg[0]);
	}
	
	<%-- 変更確認メッセージ --%>
	function putDirtyConfirm() {
		var flg = $("dirtyFlg").checked;
		if (flg) {
			/* 何か値が変更されている場合 */
			return continueConfirm();
		}else{
			return true;
		}
	}
	
	<%-- 変更フラグセット --%>
	function setDirtyFlg() {
		$("dirtyFlg").checked = true;
	}
</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/AccountsDetail" method="post" onsubmit="return false;" styleId="mainForm">	
	<%@ include file="/jsp/common/postjavascript.jsf" %>

	<nested:hidden property="op" styleId="op"/>
	<nested:hidden property="dirtyFlg" styleId="dirtyFlg"/>
	<nested:hidden property="newFlg" styleId="newFlg"/>

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
									<td class="fcTitle fb">勘定科目マスタ</td>
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
							<!-- 明細部 -->
							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">勘定科目コード</td>
									<td>
										<nested:equal property="newFlg" value="true">
											<nested:text property="accountsCd" maxlength="10" size="10" style="ime-mode:disabled" styleId="accountsCd" onchange="setDirtyFlg();" />
										</nested:equal>

										<nested:notEqual property="newFlg" value="true">
											<nested:write property="accountsCd"/>
										</nested:notEqual>
									</td>
								</tr>

								<tr>
									<td height="5" colspan="2"></td>
								</tr>

								<tr>
									<td class="bcTitleLine" colspan="2"></td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">勘定科目名称</td>
									<td>
										<nested:text property="accountsName" maxlength="40" size="75" styleId="accountsName" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">課税区分</td>
									<td>
										<nested:text property="taxationDivision" maxlength="4" size="4" style="ime-mode:disabled" styleId="taxationDivision" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">仕入科目区分</td>
									<td>
										<% pageContext.setAttribute("SelectPurchaseAccounts", new com.asahikaseieng.app.comboboxes.SelectPurchaseAccounts(false, false)); %>
										<nested:select property="purchaseAccounts" onchange="setDirtyFlg();" >
											<nested:options name="SelectPurchaseAccounts" property="values" labelName="SelectPurchaseAccounts" labelProperty="labels"/>
										</nested:select>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">売上科目区分</td>
									<td>
										<% pageContext.setAttribute("SelectArticleAccounts", new com.asahikaseieng.app.comboboxes.SelectArticleAccounts(false, false)); %>
										<nested:select property="articleAccounts" onchange="setDirtyFlg();" >
											<nested:options name="SelectArticleAccounts" property="values" labelName="SelectArticleAccounts" labelProperty="labels"/>
										</nested:select>
									</td>
								</tr>
							</table>
						</td>
					</tr>

					<tr>
						<td class="alignCenter">
							<table cellpadding="0" cellspacing="0" width="450" border="0">
								<tr>
									<td colspan="3" height="2">
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

									<nested:notEqual property="newFlg" value="true">
										<nested:equal property="deleteAuthority" value="true">
											<td class="alignCenter">
												<div id="cssButton">
													<a href="#" onclick="if(!deleteConfirm()){return false;}else{return form_submit('op', 'delete'); return false;}" class="cssButton">
														&nbsp;&nbsp;削&nbsp;&nbsp;除&nbsp;&nbsp;
													</a>
												</div>
											</td>
		
											<td width="50">
												<br>
											</td>
										</nested:equal>
									</nested:notEqual>

									<td class="alignLeft">
										<div id="cssButton">
											<a href="#" onclick="if(!putDirtyConfirm()){return false;}else{return form_submit('op', 'back'); return false;}" class="cssButton">
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
