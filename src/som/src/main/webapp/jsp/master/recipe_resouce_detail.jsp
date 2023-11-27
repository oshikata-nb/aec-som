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
        defineAsRequiredField("resouceCd");

		<%-- 数値型フィールド定義 --%>
		defineAsNumberField("strCostMachine");
		defineAsNumberField("strCost");

		<%-- 前後空白除去定義 --%>
		if ($('newFlg').value == 'true'){
        	defineAsKeyField("resouceCd");
       	};

		<%-- 明細部 --%>
		var tblList = $('tblList');

		if (tblList != null) {
			if (0 < tblList.rows.length) {
				for (i = 0; null != $("itemCd" + i); i++) {
					<%-- 必須フィールド定義 --%>
					defineAsRequiredField("prevResouceCd" + i);
				}
			}
		}

		<%-- 前工程設備コードのautocomplete --%>
		if (tblList != null) {
			if (0 < tblList.rows.length) {
				for (i = 0; null != $("prevResouceCd" + i); i++) {
					new Ajax.Autocompleter(
						"searchRecipePegResouceDetailList[" + i + "].prevResouceCd",
						"autocomplete_prevresoucecd" + i,
						"<%= request.getContextPath() %>/RecipeResouceForAutoComplete.do?op=searchRecipeResouce",
						{
							paramName : "code",
							afterUpdateElement : eval("setPrevResouceLabel" + i)
						}
					);
				}
			}
		}

		<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
		storeInitValues('dirtyFlg', /^checked.*/);

		<%-- 設備グループのautocomplete --%>
		new Ajax.Autocompleter(
			"resouceGroupCd",
			"autocomplete_choices",
			"<%= request.getContextPath() %>/RecipeResouceGroupForAutoComplete.do?op=searchRecipeResouceGroup",
			{
				paramName : "code",
				afterUpdateElement : setRecipeResouceGroupLabel
			}
		);

		<%-- 検索後入力された場合の不整合をただす --%>
		Event.observe('resouceGroupCd', 'keypress', clearLabel.bindAsEventListener($('resouceGroupName')), false);
	}, false);

	<%-- ajax --%>
	function setRecipeResouceGroupLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("resouceGroupName").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
		setDirtyFlg();
	}

	<logic:iterate id="list" name="recipeResouceDetailForm" property="searchRecipePegResouceDetailList" indexId="index">
		function setPrevResouceLabel<bean:write name='index'/>(text, li) {
			$("searchRecipePegResouceDetailList[<bean:write name='index'/>].ResouceName").value = li.getElementsByTagName('span')[0].innerText;
			$("lblResouceName<bean:write name='index'/>").innerHTML = li.getElementsByTagName('span')[0].innerText;
			setDirtyFlg();
		}
	</logic:iterate>

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

	<%-- 変更確認メッセージ --%>
	function putDirtyConfirm() {
		var flg = $("dirtyFlg").value;
		if (flg) {
			/* 何か値が変更されている場合 */
			<%-- return continueConfirm(); --%>
			return confirm("<bean:message key="message.confirm.under.edit" />");
		}else{
			return true;
		}
	}
	
	<%-- 変更フラグセット --%>
	function setDirtyFlg() {
		$("dirtyFlg").value = "true";
	}
</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/RecipeResouceDetail" method="post" onsubmit="return false;" styleId="mainForm">	
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
									<td class="fcTitle fb">設備マスタ</td>
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
									<td class="fcTitleDetail fb bcTitleDetail" width="150">設備コード</td>
									<td colspan="2">
										<nested:equal property="newFlg" value="true">
											<nested:text property="resouceCd" maxlength="20" size="20" style="ime-mode:disabled" styleId="resouceCd" onchange="setDirtyFlg();" />
										</nested:equal>

										<nested:notEqual property="newFlg" value="true">
											<nested:write property="resouceCd"/>
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
									<td class="fcTitleDetail fb bcTitleDetail">設備名称</td>
									<td colspan="2">
										<nested:text property="resouceName" maxlength="40" size="75" styleId="resouceName" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">略称</td>
									<td colspan="2">
										<nested:text property="shortName" maxlength="20" size="35" styleId="shortName" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">作業時間単価(機械)</td>
									<td colspan="2">
										<nested:text property="strCostMachine" maxlength="17" size="17" style="ime-mode:disabled" styleId="costMachine" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">作業時間単価(一律)</td>
									<td colspan="2">
										<nested:text property="strCost" maxlength="17" size="17" style="ime-mode:disabled" styleId="cost" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">設備グループ</td>
									<td>
										<nested:text property="resouceGroupCd" maxlength="10" size="10" styleId="resouceGroupCd" onchange="setDirtyFlg();" />
										<div id="autocomplete_choices" class="autocomplete"></div>
									</td>

									<td width="500">
										<div id="lblResouceGroupName">
											<nested:write property="resouceGroupName" />
										</div>
										<nested:hidden property="resouceGroupName" styleId="resouceGroupName"/>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">生産ライン</td>
									<td colspan="2">
										<nested:select property="productionLine" onblur="setDirtyFlg();" >
											<nested:options property="productionLineValues" labelProperty="productionLineLabels" />
										</nested:select>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">指図書発行有無</td>
									<td colspan="2">
										<% pageContext.setAttribute("SelectOrderPublishFlg", new com.asahikaseieng.app.comboboxes.SelectOrderPublishFlg(false, false)); %>
										<nested:select property="orderPublishFlg" onchange="setDirtyFlg();" >
											<nested:options name="SelectOrderPublishFlg" property="values" labelName="SelectOrderPublishFlg" labelProperty="labels"/>
										</nested:select>
									</td>
								</tr>
							</table>

							<table width="100%" cellspacing="2" cellpadding="2" border="0" id="tblList">
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" colspan="3">前工程設備</td>
								</tr>

								<!-- 前工程設備 -->
								<tr class="bcTitleList fb fcTitleList">
									<td width="20"></td>
									<td width="130">前工程設備コード</td>
									<td>前工程設備名称</td>
								</tr>

								<nested:notEmpty property="searchRecipePegResouceDetailList">
									<nested:iterate id="searchRecipePegResouceDetailList" property="searchRecipePegResouceDetailList" indexId="index">
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
											<%-- 前工程設備コード --%>
											<nested:text property="prevResouceCd" maxlength="20" size="20" styleId="<%="prevResouceCd" + pageContext.findAttribute("index").toString() %>" onchange="setDirtyFlg();"/>
											<div id="autocomplete_prevresoucecd<%= pageContext.findAttribute("index").toString() %>" class="autocomplete" ></div>
										</td>

										<td>
											<%-- 前工程設備名称 --%>
											<div id="<%="lblResouceName" + pageContext.findAttribute("index").toString() %>">
												<nested:write property="resouceName" />
											</div>
											<nested:hidden property="resouceName" styleId="<%="resouceName" + pageContext.findAttribute("index").toString() %>" />
										</td>
									</nested:iterate>
								</nested:notEmpty>
							</table>
						</td>
					</tr>

					<tr>
						<td class="alignCenter">
							<table cellpadding="0" cellspacing="0" width="600" border="0">
								<tr>
									<td colspan="3" height="2">
									</td>
								</tr>

								<tr>
									<td class="alignRight">
										<div id="cssButton">
											<a href="#" onclick="setDirtyFlg(); return form_submit('op', 'addlist'); return false;" class="cssButton">
												&nbsp;&nbsp;行&nbsp;追&nbsp;加&nbsp;&nbsp;
											</a>
										</div>
									</td>

									<td width="50">
										<br>
									</td>

									<td class="alignRight">
										<div id="cssButton">
											<a href="#" onclick="if(!(putDelListConfirm())){return false;}else{setDirtyFlg(); return form_submit('op', 'dellist'); return false;}" class="cssButton">
												&nbsp;&nbsp;行&nbsp;削&nbsp;除&nbsp;&nbsp;
											</a>
										</div>
									</td>

									<td width="50">
										<br>
									</td>

									<nested:equal property="updateAuthority" value="true">
										<td class="alignCenter">
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
											<td class="alignLeft">
												<div id="cssButton">
													<a href="#" onclick="if(!deleteConfirm()){return false;}else{return form_submit('op', 'delete'); return false;}" class="cssButton">
														&nbsp;&nbsp;全件削除&nbsp;&nbsp;
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
