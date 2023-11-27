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
        defineAsRequiredField("carryCd");
        
		<%-- 数値型フィールド定義 --%>
		defineAsNumberField("strReportOutputNum");

		<%-- 前後空白除去定義 --%>
		if ($('newFlg').value == 'true'){
        	defineAsKeyField("carryCd");
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

<nested:form action="/CarryDetail" method="post" onsubmit="return false;" styleId="mainForm">	
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
									<td class="fcTitle fb">運送会社マスタ</td>
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
									<td class="fcTitleDetail fb bcTitleDetail" width="130">運送会社コード</td>
									<td colspan="3">
										<nested:equal property="newFlg" value="true">
											<nested:text property="carryCd" maxlength="8" size="8" style="ime-mode:disabled" styleId="carryCd" onchange="setDirtyFlg();" />
										</nested:equal>

										<nested:notEqual property="newFlg" value="true">
											<nested:write property="carryCd"/>
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
									<td class="fcTitleDetail fb bcTitleDetail">運送会社名称1</td>
									<td colspan="3">
										<nested:text property="carryName1" maxlength="45" size="85" styleId="carryName1" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">運送会社名称2</td>
									<td colspan="3">
										<nested:text property="carryName2" maxlength="45" size="85" styleId="carryName2" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">帳票出力順</td>
									<td colspan="3">
										<nested:text property="strReportOutputNum" maxlength="3" size="9" styleId="strReportOutputNum" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">運送会社略称</td>
									<td colspan="3">
										<nested:text property="abbreviation" maxlength="45" size="85" styleId="abbreviation" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">郵便番号</td>
									<td colspan="3">
										<nested:text property="zipcodeNo" maxlength="9" size="9" style="ime-mode:disabled" styleId="zipcodeNo" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">電話番号</td>
									<td>
										<nested:text property="telNo" maxlength="13" size="13" style="ime-mode:disabled" styleId="telNo" onchange="setDirtyFlg();" />
									</td>

									<td class="fcTitleDetail fb bcTitleDetail">FAX番号</td>
									<td>
										<nested:text property="faxNo" maxlength="13" size="13" style="ime-mode:disabled" styleId="faxNo" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">住所1</td>
									<td colspan="3">
										<nested:text property="address1" maxlength="30" size="55" styleId="address1" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">住所2</td>
									<td colspan="3">
										<nested:text property="address2" maxlength="30" size="55" styleId="address2" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">住所3</td>
									<td colspan="3">
										<nested:text property="address3" maxlength="30" size="55" styleId="address3" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">荷札発行</td>
									<td colspan="3">
										<% pageContext.setAttribute("SelectLabelPublish", new com.asahikaseieng.app.comboboxes.SelectLabelPublish(false, false)); %>
										<nested:select property="labelPublish" onchange="setDirtyFlg();" >
											<nested:options name="SelectLabelPublish" property="values" labelName="SelectLabelPublish" labelProperty="labels"/>
										</nested:select>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">FLAN運送店コード</td>
									<td colspan="3">
										<nested:text property="flanTransShopCd" maxlength="2" size="2" style="ime-mode:disabled" styleId="flanTransShopCd" onchange="setDirtyFlg();" />
									</td>
								</tr>
								
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" colspan="5">運送会社バーコード用</td>
								</tr>
								
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">バーコード発行区分</td>
									<td colspan="3">
										<% pageContext.setAttribute("SelectBcPublishDivision", new com.asahikaseieng.app.comboboxes.SelectBcPublishDivision(false, false)); %>
										<nested:select property="bcPublishDivision" onchange="setDirtyFlg();" >
											<nested:options name="SelectBcPublishDivision" property="values" labelName="SelectBcPublishDivision" labelProperty="labels"/>
										</nested:select>
									</td>
								</tr>
								
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">バーコードヘッダ</td>
									<td colspan="2">
										<nested:text property="bcHeader" maxlength="10" size="30" style="ime-mode:disabled" styleId="bcHeader" onchange="setDirtyFlg();" />
									</td>
									<td class="fcTitleDetail fb bcTitleDetail" colspan="1">※@=チェックデジット(7DR)</td>
								</tr>
								
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">バーコードフッタ</td>
									<td colspan="2">
										<nested:text property="bcFooter" maxlength="10" size="30" style="ime-mode:disabled" styleId="bcFooter" onchange="setDirtyFlg();" />
									</td>
									<td class="fcTitleDetail fb bcTitleDetail" colspan="1">※@=チェックデジット(7DR)</td>
								</tr>
								
								<nested:equal property="bcPublishDivisionInit" value="1">
									<tr>
										<td class="fcTitleDetail fb bcTitleDetail">バーコード連番(開始)</td>
										<td colspan="3">
											<nested:write property="bcNumStart"/>
										</td>
									</tr>
		
									<tr>
										<td class="fcTitleDetail fb bcTitleDetail">バーコード連番(終了)</td>
										<td colspan="3">
											<nested:write property="bcNumEnd"/>
										</td>
									</tr>
									
									<tr>
										<td class="fcTitleDetail fb bcTitleDetail">バーコード連番(現在値)</td>
										<td colspan="3">
											<nested:write property="bcNumPresent"/>
										</td>
									</tr>
								</nested:equal>		
								<nested:notEqual property="bcPublishDivisionInit" value="1">
									<tr>
										<td class="fcTitleDetail fb bcTitleDetail">バーコード連番(開始)</td>
										<td colspan="3">
											<nested:text property="bcNumStart" maxlength="50" size="30" style="ime-mode:disabled" styleId="bcFooter" onchange="setDirtyFlg();" />
										</td>
									</tr>
		
									<tr>
										<td class="fcTitleDetail fb bcTitleDetail">バーコード連番(終了)</td>
										<td colspan="3">
											<nested:text property="bcNumEnd" maxlength="50" size="30" style="ime-mode:disabled" styleId="bcNumEnd" onchange="setDirtyFlg();" />
										</td>
									</tr>
									
									<tr>
										<td class="fcTitleDetail fb bcTitleDetail">バーコード連番(現在値)</td>
										<td colspan="3">
											<nested:text property="bcNumPresent" maxlength="50" size="30" style="ime-mode:disabled" styleId="bcNumPresent" onchange="setDirtyFlg();" />
										</td>
									</tr>
								</nested:notEqual>		
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">バーコード連番桁数</td>
									<td colspan="3">
										<nested:text property="bcNumberOfDigit" maxlength="2" size="10" style="ime-mode:disabled" styleId="bcNumberOfDigit" onchange="setDirtyFlg();" />
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">チェックデジット開始桁</td>
									<td colspan="3">
										<nested:text property="bcNumCheckDigitStart" maxlength="2" size="10" style="ime-mode:disabled" styleId="bcNumCheckDigitStart" onchange="setDirtyFlg();" />
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">チェックデジット終了桁</td>
									<td colspan="3">
										<nested:text property="bcNumCheckDigitEnd" maxlength="2" size="10" style="ime-mode:disabled" styleId="bcNumCheckDigitEnd" onchange="setDirtyFlg();" />
									</td>
								</tr>
								
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">次回発行バーコード</td>
									<td colspan="3">
										<nested:write property="nextPublishBc" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">備考</td>
									<td colspan="3">
										<nested:textarea property="remarks" rows="3" cols="65" styleId="remarks" onchange="setDirtyFlg();" />
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
