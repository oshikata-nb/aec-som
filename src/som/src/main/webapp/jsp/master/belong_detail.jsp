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
		defineAsRequiredField("tantoCd");
        defineAsRequiredField("postId");

<%-- 部署のオートコンプリートを新規の場合以外でも行う様修正 --%>
		<%-- 部署のautocomplete --%>
		new Ajax.Autocompleter(
			"organizationCd",
			"autocomplete_organization",
			"<%= request.getContextPath() %>/OrganizationForAutoComplete.do?op=searchOrganization",
			{
				paramName : "code",
				afterUpdateElement : setOrganizationLabel
			}
		);


		if ($('newFlg').value == 'true') {
	
			<%-- 担当者のautocomplete --%>
			new Ajax.Autocompleter(
				"tantoCd",
				"autocomplete_login",
				"<%= request.getContextPath() %>/LoginForAutoComplete.do?op=searchLogin",
				{
					paramName : "code",
					afterUpdateElement : setLoginLabel
				}
			);
		}

		<%-- 役職のautocomplete --%>
		new Ajax.Autocompleter(
			"postId",
			"autocomplete_post",
			"<%= request.getContextPath() %>/PostForAutoComplete.do?op=searchPost",
			{
				paramName : "code",
				afterUpdateElement : setPostLabel
			}
		);

		<%-- 部署のオートコンプリートを新規の場合以外でも行う様修正 --%>
		Event.observe('organizationCd', 'keypress', clearLabel.bindAsEventListener($('organizationName')), false);

		<%-- 検索後入力された場合の不整合をただす --%>
		if ($('newFlg').value == 'true') {
			Event.observe('tantoCd', 'keypress', clearLabel.bindAsEventListener($('tantoNm')), false);
		}

		Event.observe('postId', 'keypress', clearLabel.bindAsEventListener($('postName')), false);

		<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
		storeInitValues('dirtyFlg');
	}, false);

	<%-- ajax --%>
	function setOrganizationLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("organizationName").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
		setDirtyFlg();
	}

	function setLoginLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("tantoNm").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
		setDirtyFlg();
	}

	function setPostLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("postName").value = li.getElementsByTagName('span')[0].innerText;
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

<nested:form action="/BelongDetail" method="post" onsubmit="return false;" styleId="mainForm">	
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
									<td class="fcTitle fb">所属マスタ</td>
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
									<td class="fcTitleDetail fb bcTitleDetail" width="130">部署コード</td>
									<td>
<!-- 部署コード修正可能対応 2013/06/21 
										<nested:equal property="newFlg" value="true">
											<nested:text property="organizationCd" maxlength="10" size="10" styleId="organizationCd" onchange="setDirtyFlg();" />
											<div id="autocomplete_organization" class="autocomplete"></div>
										</nested:equal>
										<nested:notEqual property="newFlg" value="true">
											<nested:write property="organizationCd"/>
										</nested:notEqual>
-->
										<nested:text property="organizationCd" maxlength="10" size="10" styleId="organizationCd" onchange="setDirtyFlg();" />
										<div id="autocomplete_organization" class="autocomplete"></div>
									</td>

									<td width="500" colspan="2">
										<div id="lblOrganizationName">
											<nested:write property="organizationName" />
										</div>
										<nested:hidden property="organizationName" styleId="organizationName"/>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">担当者コード</td>
									<td>
										<nested:equal property="newFlg" value="true">
											<nested:text property="tantoCd" maxlength="10" size="10" styleId="tantoCd" onchange="setDirtyFlg();" />
											<div id="autocomplete_login" class="autocomplete"></div>
										</nested:equal>

										<nested:notEqual property="newFlg" value="true">
											<nested:write property="tantoCd"/>
										</nested:notEqual>
									</td>

									<td width="500" colspan="2">
										<div id="lblTantoNm">
											<nested:write property="tantoNm" />
										</div>
										<nested:hidden property="tantoNm" styleId="tantoNm"/>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">役職コード</td>
									<td>
										<nested:text property="postId" maxlength="10" size="10" styleId="postId" onchange="setDirtyFlg();" />
										<div id="autocomplete_post" class="autocomplete"></div>
									</td>

									<td width="500" colspan="2">
										<div id="lblPostName">
											<nested:write property="postName" />
										</div>
										<nested:hidden property="postName" styleId="postName"/>
									</td>
								</tr>

								<tr>
									<td height="5" colspan="3"></td>
								</tr>

								<tr>
									<td class="bcTitleLine" colspan="5"></td>
								</tr>
							</table>
							
							<table width="100%" cellspacing="2" cellpadding="4" border="0">
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">所属区分</td>
									<td >
										<% pageContext.setAttribute("SelectBelongKbn", new com.asahikaseieng.app.comboboxes.SelectBelongKbn(false, false)); %>
										<nested:select property="belongKbn" onchange="setDirtyFlg();" >
											<nested:options name="SelectBelongKbn" property="values" labelName="SelectBelongKbn" labelProperty="labels"/>
										</nested:select>
									</td>
									<!-- AECS佐藤 受注納入先区分追加 2020/01/21 -->
									<td class="fcTitleDetail fb bcTitleDetail" width="130">受注納入先区分</td>
									<td >
										<% pageContext.setAttribute("SelectOrderDeliveryKbn", new com.asahikaseieng.app.comboboxes.SelectOrderDeliveryKbn(false, false)); %>
										<nested:select property="orderDeliveryKbn" onchange="setDirtyFlg();" >
											<nested:options name="SelectOrderDeliveryKbn" property="values" labelName="SelectOrderDeliveryKbn" labelProperty="labels"/>
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
