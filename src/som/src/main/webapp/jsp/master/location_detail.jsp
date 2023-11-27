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
        defineAsRequiredField("locationCd");

		<%-- 数値型フィールド定義 --%>
		defineAsNumberField("strPossibleWeight");

		<%-- 前後空白除去定義 --%>
		if ($('newFlg').value == 'true'){
        	defineAsKeyField("locationCd");
       	};

		<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
		storeInitValues('dirtyFlg');

		<%-- 上位ロケーションのautocomplete --%>
		new Ajax.Autocompleter(
			"upperLocationCd",
			"autocomplete_choices",
			"<%= request.getContextPath() %>/LocationForAutoComplete.do?op=searchLocation",
			{
				paramName : "code",
				afterUpdateElement : setUpperLocationLabel
			}
		);

		<%-- 営業担当者のautocomplete --%>
		new Ajax.Autocompleter(
			"tantoCd",
			"autocomplete_choices",
			"<%= request.getContextPath() %>/LoginForAutoComplete.do?op=searchLogin",
			{
				paramName : "code",
				afterUpdateElement : setLoginLabel
			}
		);

		<%-- 会計部門のautocomplete --%>
		new Ajax.Autocompleter(
			"sectionCd",
			"autocomplete_choices",
			"<%= request.getContextPath() %>/BumonForAutoComplete.do?op=searchBumon",
			{
				paramName : "code",
				afterUpdateElement : setBumonLabel
			}
		);

		<%-- ローリー原料品目のautocomplete --%>
		new Ajax.Autocompleter(
			"itemCd",
			"autocomplete_choices",
			"<%= request.getContextPath() %>/ItemQueueForAutoComplete.do?op=searchItemQueue",
			{
				paramName : "code",
				afterUpdateElement : setItemQueueLabel
			}
		);

		<%-- 検索後入力された場合の不整合をただす --%>
		Event.observe('upperLocationCd', 'keypress', clearLabel.bindAsEventListener($('upperLocationName')), false);
		Event.observe('tantoCd', 'keypress', clearLabel.bindAsEventListener($('tantoNm')), false);
		Event.observe('sectionCd', 'keypress', clearLabel.bindAsEventListener($('sectionName')), false);
		Event.observe('itemCd', 'keypress', clearLabel.bindAsEventListener($('itemName')), false);
	}, false);

	<%-- ajax --%>
	function setUpperLocationLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("upperLocationName").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
		setDirtyFlg();
	}

	function setLoginLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("tantoNm").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
		setDirtyFlg();
	}

	function setBumonLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("sectionName").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
		setDirtyFlg();
	}

	function setItemQueueLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("itemName").value = li.getElementsByTagName('span')[0].innerText;
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

<nested:form action="/LocationDetail" method="post" onsubmit="return false;" styleId="mainForm">	
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
									<td class="fcTitle fb">ロケーションマスタ</td>
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
									<td class="fcTitleDetail fb bcTitleDetail" width="150">ロケーションコード</td>
									<td colspan="3">
										<nested:equal property="newFlg" value="true">
											<nested:text property="locationCd" maxlength="10" size="10" style="ime-mode:disabled" styleId="locationCd" onchange="setDirtyFlg();" />
										</nested:equal>

										<nested:notEqual property="newFlg" value="true">
											<nested:write property="locationCd"/>
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
									<td class="fcTitleDetail fb bcTitleDetail">ロケーション名称</td>
									<td colspan="3">
										<nested:text property="locationName" maxlength="30" size="55" styleId="locationName" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">上位ロケーション</td>
									<td>
										<nested:text property="upperLocationCd" maxlength="20" size="20" styleId="upperLocationCd" onchange="setDirtyFlg();" />
										<div id="autocomplete_choices" class="autocomplete"></div>
									</td>

									<td width="500">
										<div id="lblUpperLocationName">
											<nested:write property="upperLocationName" />
										</div>
										<nested:hidden property="upperLocationName" styleId="upperLocationName"/>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">ロケーションレベル</td>
									<td colspan="3">
										<nested:write property="locationLevel"/>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">可能重量</td>
									<td colspan="3">
										<nested:text property="strPossibleWeight" maxlength="22" size="22" style="ime-mode:disabled" styleClass="alignRight" styleId="possibleWeight" onchange="setDirtyFlg();" />
										Kg
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">MRP対象フラグ</td>
									<td colspan="3">
										<% pageContext.setAttribute("SelectMrpTargetFlg", new com.asahikaseieng.app.comboboxes.SelectMrpTargetFlg(false, false)); %>
										<nested:select property="mrpTargetFlg" onchange="setDirtyFlg();" >
											<nested:options name="SelectMrpTargetFlg" property="values" labelName="SelectMrpTargetFlg" labelProperty="labels"/>
										</nested:select>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">在庫可能フラグ</td>
									<td colspan="3">
										<nested:notEqual property="newFlg" value="true">
											<nested:equal property="availableFlg" value="0">不可能</nested:equal>
											<nested:equal property="availableFlg" value="1">可能</nested:equal>
										</nested:notEqual>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">営業担当者</td>
									<td>
										<nested:text property="tantoCd" maxlength="10" size="20" styleId="tantoCd" onchange="setDirtyFlg();" />
									</td>

									<td width="500">
										<div id="lblTantoNm">
											<nested:write property="tantoNm" />
										</div>
										<nested:hidden property="tantoNm" styleId="tantoNm"/>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">会計部門</td>
									<td>
										<nested:text property="sectionCd" maxlength="10" size="20" styleId="sectionCd" onchange="setDirtyFlg();" />
									</td>

									<td width="500">
										<div id="lblSectionName">
											<nested:write property="sectionName" />
										</div>
										<nested:hidden property="sectionName" styleId="sectionName"/>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">ローリー原料品目</td>
									<td>
										<nested:text property="itemCd" maxlength="20" size="20" styleId="itemCd" onchange="setDirtyFlg();"/>
									</td>

									<td width="500">
										<div id="lblItemName">
											<nested:write property="itemName" />
										</div>
										<nested:hidden property="itemName" styleId="itemName"/>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">棚卸区分</td>
									<td colspan="3">
										<nested:select property="countDivision" onblur="setDirtyFlg();" >
											<nested:options property="countDivisionValues" labelProperty="countDivisionLabels" />
										</nested:select>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">在庫区分</td>
									<td colspan="3">
										<% pageContext.setAttribute("SelectStockDivision", new com.asahikaseieng.app.comboboxes.SelectStockDivision(false, false)); %>
										<nested:select property="stockDivision" onchange="setDirtyFlg();" >
											<nested:options name="SelectStockDivision" property="values" labelName="SelectStockDivision" labelProperty="labels"/>
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
