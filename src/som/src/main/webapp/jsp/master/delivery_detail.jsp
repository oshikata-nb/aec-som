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
        defineAsRequiredField("deliveryCd");

		<%-- 数値型フィールド定義 --%>
		defineAsNumberField("strLeadTime");

		<%-- 前後空白除去定義 --%>
		if ($('newFlg').value == 'true'){
        	defineAsKeyField("deliveryCd");
       	};

		<%-- 運送会社のautocomplete --%>
		new Ajax.Autocompleter(
			"carryCd",
			"autocomplete_choices",
			"<%= request.getContextPath() %>/CarryForAutoComplete.do?op=searchCarry",
			{
				paramName : "code",
				afterUpdateElement : setCarryLabel
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

		<%-- 検索後入力された場合の不整合をただす --%>
		Event.observe('carryCd', 'keypress', clearLabel.bindAsEventListener($('carryName1')), false);
		Event.observe('tantoCd', 'keypress', clearLabel.bindAsEventListener($('tantoNm')), false);

		<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
		storeInitValues('dirtyFlg');
	}, false);

	<%-- ajax --%>
	function setCarryLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("carryName1").value = li.getElementsByTagName('span')[0].innerText;
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

	<%-- テキストのバイト数チェック --%>
	function checkTextByte(text, maxByte){
		var count = 0;
		var checkedString = "";

		for(var i = 0; i < text.length; i++) {
			var str = text.substring(i, i+1);
			var chr = str.charCodeAt(0);

			if(chr < 256 || (chr >= 0Xff61 && chr <= 0xff9f)) {
				<%-- 半角 --%>
				count+=1;
			} else {
				<%-- 全角 --%>
				count+=2;
			}
			
			<%-- 最大バイト数との比較 --%>
			if(count <= maxByte) {
				checkedString+=str
			} else {
				<%-- 最大バイト数以下の文字列だけを返す --%>
				return checkedString;
			}
		}
		
		<%-- 最大バイト数以下の文字列だけを返す --%>
		return checkedString;
	}
</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/DeliveryDetail" method="post" onsubmit="return false;" styleId="mainForm">	
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
									<td class="fcTitle fb">納入先マスタ</td>
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
									<td class="fcTitleDetail fb bcTitleDetail" width="190">納入先コード</td>
									<td colspan="9">
										<nested:equal property="newFlg" value="true">
											<nested:text property="deliveryCd" maxlength="15" size="15" style="ime-mode:disabled" styleId="deliveryCd" onchange="setDirtyFlg();" />
										</nested:equal>

										<nested:notEqual property="newFlg" value="true">
											<nested:write property="deliveryCd"/>
										</nested:notEqual>
									</td>
								</tr>

								<tr>
									<td height="5" colspan="9"></td>
								</tr>

								<tr>
									<td class="bcTitleLine" colspan="9"></td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">納入先名称1</td>
									<td colspan="8">
										<nested:text property="deliveryName1" maxlength="30" size="55" styleId="deliveryName1" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">納入先名称2</td>
									<td colspan="8">
										<nested:text property="deliveryName2" maxlength="30" size="55" styleId="deliveryName2" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">納入先略称</td>
									<td colspan="8">
										<nested:text property="searchKana" maxlength="45" size="65" styleId="searchKana" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">郵便番号</td>
									<td colspan="5">
										<nested:text property="zipcodeNo" maxlength="14" size="14" styleId="zipcodeNo" style="ime-mode:disabled" onchange="setDirtyFlg();" />
									</td>

									<td class="fcTitleDetail fb bcTitleDetail" width="130">市町村コード</td>
									<td colspan="2">
										<nested:text property="cityCd" maxlength="5" size="5" styleId="cityCd" style="ime-mode:disabled" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">電話番号</td>
									<td colspan="5">
										<nested:text property="telNo" maxlength="20" size="20" styleId="telNo" style="ime-mode:disabled" onchange="setDirtyFlg();" />
									</td>

									<td class="fcTitleDetail fb bcTitleDetail">FAX番号</td>
									<td colspan="2">
										<nested:text property="faxNo" maxlength="20" size="20" styleId="faxNo" style="ime-mode:disabled" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">住所1</td>
									<td colspan="8">
										<nested:text property="address1" maxlength="60" size="75" styleId="address1" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">住所2</td>
									<td colspan="8">
										<nested:text property="address2" maxlength="60" size="75" styleId="address2" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">住所3</td>
									<td colspan="8">
										<nested:text property="address3" maxlength="60" size="75" styleId="address3" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">納入先地区</td>
									<td colspan="2">
										<% pageContext.setAttribute("SelectCarryFareCd", new com.asahikaseieng.app.comboboxes.SelectCarryFareCd(false, false)); %>
										<nested:select property="carryFareCd" onchange="setDirtyFlg();" >
											<nested:options name="SelectCarryFareCd" property="values" labelName="SelectCarryFareCd" labelProperty="labels"/>
										</nested:select>
									</td>
									<td class="fcTitleDetail fb bcTitleDetail">運送会社</td>
									<td colspan="3">
										<nested:text property="carryCd" maxlength="15" size="15" styleId="carryCd" onchange="setDirtyFlg();" />
										<div id="autocomplete_choices" class="autocomplete"></div>
									</td>

									<td colspan="5">
										<div id="lblCarryName1">
											<nested:write property="carryName1" />
										</div>
										<nested:hidden property="carryName1" styleId="carryName1"/>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">リードタイム</td>
									<td colspan="5">
										<nested:text property="strLeadTime" maxlength="22" size="22" styleId="strLeadTime" styleClass="alignRight" style="ime-mode:disabled" onchange="setDirtyFlg();" />
									</td>

									<td class="fcTitleDetail fb bcTitleDetail">指定納入時刻</td>
									<td colspan="2">
										<nested:text property="deliveryTime" maxlength="10" size="20" styleId="deliveryTime" onchange="setDirtyFlg(); value=checkTextByte(this.value, 30);"/>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">運賃請求有無</td>
									<td colspan="5">
										<nested:checkbox property="fareClaimExistence" styleId="fareClaimExistence" onchange="setDirtyFlg();" />
									</td>

									<td class="fcTitleDetail fb bcTitleDetail">区分</td>
									<td colspan="2">
										<% pageContext.setAttribute("SelectBalanceType", new com.asahikaseieng.app.comboboxes.SelectBalanceType(false, false)); %>
										<nested:select property="division" onchange="setDirtyFlg();" >
											<nested:options name="SelectBalanceType" property="values" labelName="SelectBalanceType" labelProperty="labels"/>
										</nested:select>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">営業担当者</td>
									<td colspan="2">
										<nested:text property="tantoCd" maxlength="10" size="10" styleId="tantoCd" onchange="setDirtyFlg();" />
									</td>

									<td colspan="6">
										<div id="lblTantoNm">
											<nested:write property="tantoNm" />
										</div>
										<nested:hidden property="tantoNm" styleId="tantoNm"/>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">納入条件</td>
									<td colspan="8">
										<nested:textarea property="deliveryCondition" rows="3" cols="65" styleId="deliveryCondition" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">備考</td>
									<td colspan="8">
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
											<nested:notEqual property="copyFlg" value="1">
												<a href="#" onclick="if(!putDirtyConfirm()){return false;}else{return form_submit('op', 'back'); return false;}" class="cssButton">
													&nbsp;&nbsp;戻&nbsp;&nbsp;る&nbsp;&nbsp;
												</a>
											</nested:notEqual>

											<nested:equal property="copyFlg" value="1">
												<a href="#" onclick="if(!putDirtyConfirm()){return false;}else{return form_submit('op', 'backVender'); return false;}" class="cssButton">
													&nbsp;&nbsp;戻&nbsp;&nbsp;る&nbsp;&nbsp;
												</a>
											</nested:equal>
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
