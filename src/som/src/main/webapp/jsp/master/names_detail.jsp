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
        defineAsRequiredField("nameCd");

		<%-- 前後空白除去定義 --%>
		if ($('newFlg').value == 'true'){
        	defineAsKeyField("nameCd");
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

<nested:form action="/NamesDetail" method="post" onsubmit="return false;" styleId="mainForm">	
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
									<td class="fcTitle fb">名称マスタ</td>
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
									<td class="fcTitleDetail fb bcTitleDetail" width="130">名称区分</td>
									<td>
										<nested:equal property="newFlg" value="true">
											<% pageContext.setAttribute("SelectNameDivision", new com.asahikaseieng.app.comboboxes.SelectNameDivision(false, false)); %>
											<nested:select property="nameDivision" onchange="setDirtyFlg();">
												<nested:options name="SelectNameDivision" property="values" labelName="SelectNameDivision" labelProperty="labels"/>
											</nested:select>
										</nested:equal>

										<nested:notEqual property="newFlg" value="true">
											<nested:write property="nameDivisionName"/>
										</nested:notEqual>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">名称コード</td>
									<td>
										<nested:equal property="newFlg" value="true">
											<nested:text property="nameCd" maxlength="10" size="10" style="ime-mode:disabled" styleId="nameCd" onchange="setDirtyFlg();" />
										</nested:equal>

										<nested:notEqual property="newFlg" value="true">
											<nested:write property="nameCd"/>
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
									<td class="fcTitleDetail fb bcTitleDetail">名称1</td>
									<td>
										<nested:text property="name01" maxlength="128" size="75" styleId="name01" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">名称2</td>
									<td>
										<nested:text property="name02" maxlength="128" size="75" styleId="name02" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">名称3</td>
									<td>
										<nested:text property="name03" maxlength="128" size="75" styleId="name03" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<nested:equal property="newFlg" value="true">
									<tr>
										<td class="fcTitleDetail fb bcTitleDetail">数量端数区分</td>
										<td>
											<% pageContext.setAttribute("SelectQuantityRoundup", new com.asahikaseieng.app.comboboxes.SelectQuantityRoundup(false, false)); %>
											<nested:select property="quantityRoundup" onchange="setDirtyFlg();" >
												<nested:options name="SelectQuantityRoundup" property="values" labelName="SelectQuantityRoundup" labelProperty="labels"/>
											</nested:select>
										</td>
									</tr>
	
									<tr>
										<td class="fcTitleDetail fb bcTitleDetail">数量端数単位</td>
										<td>
											<% pageContext.setAttribute("SelectQuantityRoundupUnit", new com.asahikaseieng.app.comboboxes.SelectQuantityRoundupUnit(false, false)); %>
											<nested:select property="quantityRoundupUnit" onchange="setDirtyFlg();" >
												<nested:options name="SelectQuantityRoundupUnit" property="values" labelName="SelectQuantityRoundupUnit" labelProperty="labels"/>
											</nested:select>
										</td>
									</tr>
								</nested:equal>

								<nested:notEqual property="newFlg" value="true">
									<nested:equal property="nameDivision" value="UNIT">
										<tr>
											<td class="fcTitleDetail fb bcTitleDetail">数量端数区分</td>
											<td>
												<% pageContext.setAttribute("SelectQuantityRoundup", new com.asahikaseieng.app.comboboxes.SelectQuantityRoundup(false, false)); %>
												<nested:select property="quantityRoundup" onchange="setDirtyFlg();" >
													<nested:options name="SelectQuantityRoundup" property="values" labelName="SelectQuantityRoundup" labelProperty="labels"/>
												</nested:select>
											</td>
										</tr>
		
										<tr>
											<td class="fcTitleDetail fb bcTitleDetail">数量端数単位</td>
											<td>
												<% pageContext.setAttribute("SelectQuantityRoundupUnit", new com.asahikaseieng.app.comboboxes.SelectQuantityRoundupUnit(false, false)); %>
												<nested:select property="quantityRoundupUnit" onchange="setDirtyFlg();" >
													<nested:options name="SelectQuantityRoundupUnit" property="values" labelName="SelectQuantityRoundupUnit" labelProperty="labels"/>
												</nested:select>
											</td>
										</tr>
									</nested:equal>
								</nested:notEqual>
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
