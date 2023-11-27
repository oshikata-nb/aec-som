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
        defineAsRequiredField("organizationCd");

		<%-- 前後空白除去定義 --%>
		if ($('newFlg').value == 'true'){
        	defineAsKeyField("organizationCd");
       	};

		<%-- 親部署のautocomplete --%>
		new Ajax.Autocompleter(
			"parentOrganizationCd",
			"autocomplete_choices",
			"<%= request.getContextPath() %>/OrganizationForAutoComplete.do?op=searchOrganization",
			{
				paramName : "code",
				afterUpdateElement : setOrganizationLabel
			}
		);

		<%-- 担当者のautocomplete --%>
		new Ajax.Autocompleter(
			"tantoCd",
			"autocomplete_choices",
			"<%= request.getContextPath() %>/LoginForAutoComplete.do?op=searchLogin",
			{
				paramName : "code",
				afterUpdateElement : setLoginLabel
			}
		);

		Event.observe('parentOrganizationCd', 'keypress', clearLabel.bindAsEventListener($('parentOrganizationName')), false);
		Event.observe('tantoCd', 'keypress', clearLabel.bindAsEventListener($('tantoNm')), false);

		<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
		storeInitValues('dirtyFlg');
	}, false);

	<%-- ajax --%>
	function setOrganizationLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("parentOrganizationName").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
		setDirtyFlg();
	}

	function setLoginLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("tantoNm").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
		setDirtyFlg();
	}

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

<nested:form action="/OrganizationDetail" method="post" onsubmit="return false;" styleId="mainForm">	
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
									<td class="fcTitle fb">部署マスタ</td>
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
									<td class="fcTitleDetail fb bcTitleDetail" width="170">部署コード</td>
									<td colspan="3">
										<nested:equal property="newFlg" value="true">
											<nested:text property="organizationCd" maxlength="10" size="10" style="ime-mode:disabled" styleId="organizationCd" onchange="setDirtyFlg();" />
										</nested:equal>

										<nested:notEqual property="newFlg" value="true">
											<nested:write property="organizationCd"/>
										</nested:notEqual>
									</td>
								</tr>

								<tr>
									<td height="5" colspan="4"></td>
								</tr>

								<tr>
									<td class="bcTitleLine" colspan="4"></td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">部署名称</td>
									<td colspan="3">
										<nested:text property="organizationName" maxlength="60" size="75" styleId="organizationName" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">親部署コード</td>
									<td>
										<nested:text property="parentOrganizationCd" maxlength="10" size="13" styleId="parentOrganizationCd" onchange="setDirtyFlg();" />
										<div id="autocomplete_choices" class="autocomplete"></div>
									</td>

									<td colspan="2" width="500">
										<div id="lblParentOrganizationName">
											<nested:write property="parentOrganizationName" />
										</div>
										<nested:hidden property="parentOrganizationName" styleId="parentOrganizationName"/>
									</td>
								</tr>

								<tr>
									<td height="5" colspan="4"></td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">請求書記載事項</td>
									<td colspan="3">
										<nested:checkbox property="billDescriptionMatter" value="1" styleId="billDescriptionMatter" onchange="setDirtyFlg();" />表示する
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">郵便番号</td>
									<td colspan="3">
										<nested:text property="zipcodeNo" maxlength="9" size="9" style="ime-mode:disabled" styleId="zipcodeNo" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">住所1</td>
									<td colspan="3">
										<nested:text property="address1" maxlength="60" size="75" styleId="address1" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">住所2</td>
									<td colspan="3">
										<nested:text property="address2" maxlength="60" size="75" styleId="address2" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">住所3</td>
									<td colspan="3">
										<nested:text property="address3" maxlength="60" size="75" styleId="address3" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">電話番号</td>
									<td>
										<nested:text property="telNo" maxlength="13" size="13" style="ime-mode:disabled" styleId="telNo" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">FAX番号</td>
									<td>
										<nested:text property="faxNo" maxlength="13" size="13" style="ime-mode:disabled" styleId="faxNo" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">担当者コード</td>
									<td colspan="2">
										<nested:text property="tantoCd" maxlength="10" size="13" styleId="tantoCd" onchange="setDirtyFlg();" />
									</td>

									<td width="500">
										<div id="lblTantoNm">
											<nested:write property="tantoNm" />
										</div>
										<nested:hidden property="tantoNm" styleId="tantoNm"/>
									</td>
								</tr>
								
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">Fromメールアドレス</td>
									<td colspan="3">
										<nested:text property="fromMailAddress1" maxlength="254" size="75" style="ime-mode:disabled" styleId="fromMailAddress1" onchange="setDirtyFlg();" />
									</td>
								</tr>
<%--
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">Fromメールアドレス2</td>
									<td colspan="3">
										<nested:text property="fromMailAddress2" maxlength="254" size="75" style="ime-mode:disabled" styleId="fromMailAddress2" onchange="setDirtyFlg();" />
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">Fromメールアドレス3</td>
									<td colspan="3">
										<nested:text property="fromMailAddress3" maxlength="254" size="75" style="ime-mode:disabled" styleId="fromMailAddress3" onchange="setDirtyFlg();" />
									</td>
								</tr>
--%>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">BCC送付</td>
									<td colspan="3">
										<nested:checkbox property="bccSendFlg" value="1" styleId="bccSendFlg" onchange="setDirtyFlg();" />送付する
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
