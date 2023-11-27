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

		if ($('newFlg').value == 'true') {
			<%-- 取引先のautocomplete --%>
			new Ajax.Autocompleter(
				"venderCd",
				"autocomplete_choices",
				"<%= request.getContextPath() %>/VenderForAutoComplete.do?op=searchVender",
				{
				    autoParams: ['venderDivision'],
					paramName : "code",
					afterUpdateElement : setVenderLabel
				}
			);
	
			<%-- 納入先のautocomplete --%>
			new Ajax.Autocompleter(
				"deliveryCd",
				"autocomplete_deliverycd",
				"<%= request.getContextPath() %>/DeliveryForAutoComplete.do?op=searchDelivery",
				{
					paramName : "code",
					afterUpdateElement : setDeliveryLabel
				}
			);
	
			<%-- 品目のautocomplete --%>
			new Ajax.Autocompleter(
				"itemCd",
				"autocomplete_itemcd",
				"<%= request.getContextPath() %>/ItemQueueForAutoComplete.do?op=searchDetailItemQueue",
				{
					paramName : "code",
					afterUpdateElement : setItemQueueLabel
				}
			);
	
			<%-- 他社コードのautocomplete --%>
			new Ajax.Autocompleter(
				"otherCompanyCd1",
				"autocomplete_othercompanycd",
				"<%= request.getContextPath() %>/ItemQueueForAutoComplete.do?op=searchDetailItemQueueOtherCompany",
				{
					paramName : "code",
					afterUpdateElement : setOtherCompanyLabel
				}
			);

			<%-- 検索後入力された場合の不整合をただす --%>
			Event.observe('venderCd', 'keypress', clearLabel.bindAsEventListener($('venderName1')), false);
			Event.observe('deliveryCd', 'keypress', clearLabel.bindAsEventListener($('deliveryName1')), false);
			Event.observe('itemCd', 'keypress', clearLabel.bindAsEventListener($('itemName')), false);
			Event.observe('itemCd', 'keypress', clearText.bindAsEventListener($('otherCompanyCd1')), false);
			Event.observe('otherCompanyCd1', 'keypress', clearLabel.bindAsEventListener($('itemName')), false);
		}

		<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
		storeInitValues('dirtyFlg');
	}, false);

	<%-- ajax --%>
	function setVenderLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("venderName1").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
		setDirtyFlg();
	}

	function setDeliveryLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("deliveryName1").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
		setDirtyFlg();
	}

	function setItemQueueLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("itemName").value = li.getElementsByTagName('span')[0].innerText;
		$("otherCompanyCd1").value = getHiddenValue(li,"otherCompanyCd1");
		refreshLabel();
		setDirtyFlg();
	}

	function setOtherCompanyLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("itemName").value = li.getElementsByTagName('span')[0].innerText;
		$("itemCd").value = getHiddenValue(li,"code");
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

<nested:form action="/RemarkDetail" method="post" onsubmit="return false;" styleId="mainForm">	
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
									<td class="fcTitle fb">備考マスタ</td>
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
									<td class="fcTitleDetail fb bcTitleDetail" width="130">取引先区分</td>
									<td colspan="2">
										<nested:equal property="newFlg" value="true">
											<% pageContext.setAttribute("SelectVenderDivision", new com.asahikaseieng.app.comboboxes.SelectVenderDivision(false, false)); %>
											<nested:select property="venderDivision" onchange="setDirtyFlg();">
												<nested:options name="SelectVenderDivision" property="values" labelName="SelectVenderDivision" labelProperty="labels"/>
											</nested:select>
										</nested:equal>

										<nested:notEqual property="newFlg" value="true">
											<% pageContext.setAttribute("SelectVenderDivision", new com.asahikaseieng.app.comboboxes.SelectVenderDivision(false, false)); %>
											<nested:select property="venderDivision" disabled="true" onchange="setDirtyFlg();">
												<nested:options name="SelectVenderDivision" property="values" labelName="SelectVenderDivision" labelProperty="labels"/>
											</nested:select>
										</nested:notEqual>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">取引先コード</td>
									<td>
										<nested:equal property="newFlg" value="true">
											<nested:text property="venderCd" maxlength="15" size="20" styleId="venderCd" onchange="setDirtyFlg();" />
											<div id="autocomplete_choices" class="autocomplete"></div>
										</nested:equal>

										<nested:notEqual property="newFlg" value="true">
											<nested:write property="venderCd"/>
										</nested:notEqual>
									</td>

									<td width="450">
										<div id="lblVenderName1">
											<nested:write property="venderName1" />
										</div>
										<nested:hidden property="venderName1" styleId="venderName1"/>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">納入先コード</td>
									<td>
										<nested:equal property="newFlg" value="true">
											<nested:text property="deliveryCd" maxlength="15" size="20" styleId="deliveryCd" onchange="setDirtyFlg();" />
											<div id="autocomplete_deliverycd" class="autocomplete"></div>
										</nested:equal>

										<nested:notEqual property="newFlg" value="true">
											<nested:write property="deliveryCd"/>
										</nested:notEqual>
									</td>

									<td width="450">
										<div id="lblDeliveryName1">
											<nested:write property="deliveryName1" />
										</div>
										<nested:hidden property="deliveryName1" styleId="deliveryName1"/>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">品目コード</td>
									<td>
										<nested:equal property="newFlg" value="true">
											<nested:text property="itemCd" maxlength="20" size="20" styleId="itemCd" onchange="setDirtyFlg();" />
											<div id="autocomplete_itemcd" class="autocomplete"></div>
										</nested:equal>

										<nested:notEqual property="newFlg" value="true">
											<nested:write property="itemCd"/>
										</nested:notEqual>
									</td>

									<td width="450">
										<div id="lblItemName">
											<nested:write property="itemName" />
										</div>
										<nested:hidden property="itemName" styleId="itemName"/>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">他社コード1</td>
									<td colspan="2">
										<nested:equal property="newFlg" value="true">
											<nested:text property="otherCompanyCd1" maxlength="20" size="20" styleId="otherCompanyCd1" style="ime-mode:disabled" onchange="setDirtyFlg();" />
											<div id="autocomplete_othercompanycd" class="autocomplete"></div>
										</nested:equal>

										<nested:notEqual property="newFlg" value="true">
											<nested:write property="otherCompanyCd1"/>
										</nested:notEqual>
									</td>
								</tr>

								<tr>
									<td height="5" colspan="3"></td>
								</tr>

								<tr>
									<td class="bcTitleLine" colspan="3"></td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">受注入力時参照用</td>
									<td colspan="2">
										<nested:textarea property="remark01" rows="3" cols="65" styleId="remark01" onchange="setDirtyFlg();" />
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">返信時注意事項</td>
									<td colspan="2">
										<nested:textarea property="remark15" rows="3" cols="65" styleId="remark15" onchange="setDirtyFlg();" />
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">受注時自動表示備考</td>
									<td colspan="2">
										<nested:textarea property="remark16" rows="3" cols="65" styleId="remark16" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">発注時備考</td>
									<td colspan="2">
										<nested:textarea property="remark12" rows="3" cols="65" styleId="remark12" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">発注書備考</td>
									<td colspan="2">
										<nested:textarea property="remark13" rows="3" cols="65" styleId="remark13" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">出荷時備考</td>
									<td colspan="2">
										<nested:textarea property="remark11" rows="3" cols="65" styleId="remark11" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">出荷指図書備考</td>
									<td colspan="2">
										<nested:textarea property="remark06" rows="3" cols="65" styleId="remark06" onchange="setDirtyFlg();" />
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
