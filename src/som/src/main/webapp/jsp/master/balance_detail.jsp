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
	<%-- 20211208 Asclab Kouchi EdgeIE対応 --%>
	<%-- 検索画面からCallされるメソッド
	(postjavascript.jsfのopen_modal_popup_edgeと対応) --%>
	function setBalanceValues(upperBalanceCd, balanceTypeName) {
		$('upperBalanceCd').value = upperBalanceCd;
		form_submit('op', 'getUpperBalance');
		setDirtyFlg();
	}

	<%-- Load時の処理 --%>
	Event.observe(window, 'load', function() {
		<%-- フォーム設定の初期化 --%>
		initializeFormState();

		<%-- 必須フィールド定義 --%>
        defineAsRequiredField("balanceCd");
        defineAsRequiredField("venderCd");

		<%-- 前後空白除去定義 --%>
		if ($('newFlg').value == 'true'){
        	defineAsKeyField("balanceCd");
       	};

		<%-- 得意先のautocomplete --%>
		new Ajax.Autocompleter(
			"venderCd",
			"autocomplete_choices",
			"<%= request.getContextPath() %>/VenderForAutoComplete.do?op=searchVender",
			{
				parameters : 'venderDivision='+$("venderDivision").value,
				paramName : "code",
				afterUpdateElement : setVenderLabel
			}
		);

		<%-- 検索後入力された場合の不整合をただす --%>
		Event.observe('venderCd', 'keypress', clearLabel.bindAsEventListener($('venderName1')), false);

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

	<%-- 確認メッセージ --%>
	function putConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "登録してよろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- 削除確認メッセージ --%>
	function deleteLevelConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "最下位レベルではありませんが、削除してよろしいですか？";
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

	<%-- レベル確認メッセージ --%>
	function checkLevel() {
		var flg = $("lastLevel").value;

		if (flg=="true") {
			return false;
		}else{
			return true;
		}
	}

	<%-- 変更フラグセット --%>
	function setDirtyFlg() {
		$("dirtyFlg").checked = true;
	}

	function open_window(theURL,winName,features) { //v2.0
		win = window.open(theURL,winName,features);
		win.focus();
		return false;
	}
</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/BalanceDetail" method="post" onsubmit="return false;" styleId="mainForm">
	<%@ include file="/jsp/common/postjavascript.jsf" %>

	<nested:hidden property="op" styleId="op"/>
	<nested:hidden property="venderDivision" styleId="venderDivision"/>
	<nested:hidden property="lastLevel" styleId="lastLevel"/>
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
									<td class="fcTitle fb">帳合マスタ</td>
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
									<td class="fcTitleDetail fb bcTitleDetail" width="130">帳合コード</td>
									<td colspan="3">
										<nested:equal property="newFlg" value="true">
											<nested:text property="balanceCd" maxlength="10" size="10" style="ime-mode:disabled" styleId="balanceCd" onchange="setDirtyFlg();" />
										</nested:equal>

										<nested:notEqual property="newFlg" value="true">
											<nested:write property="balanceCd"/>
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
									<td class="fcTitleDetail fb bcTitleDetail">得意先コード</td>
									<td>
										<nested:text property="venderCd" maxlength="15" size="15" styleId="venderCd" onchange="setDirtyFlg();" />
										<div id="autocomplete_choices" class="autocomplete"></div>
									</td>

									<td width="500">
										<div id="lblVenderName1">
											<nested:write property="venderName1" />
										</div>
										<nested:hidden property="venderName1" styleId="venderName1"/>
									</td>
								</tr>

								<nested:equal property="shopLevel" value="1">
									<tr>
										<td class="fcTitleDetail fb bcTitleDetail">請求先コード</td>
										<td>
											<nested:write property="paymentInvoiceCd" />
										</td>

										<td width="500">
											<nested:write property="paymentInvoiceName" />
										</td>
									</tr>
								</nested:equal>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">上位帳合コード</td>
									<td colspan="3">
										<nested:text property="upperBalanceCd" maxlength="10" size="10" styleId="upperBalanceCd" onchange="form_submit('op', 'getUpperBalance'); setDirtyFlg();" />
										<!-- 20211208 Asclab Kouchi EdgeIE対応 -->
										<a href ="#" onclick="open_modal_popup_edge(620, 470,'BalanceSearch', '', 'srhVenderCd', $('venderCd').value, 'srhVenderName1', $('venderName1').value, 'srhBalanceCd', $('upperBalanceCd').value); return false;" class="cssButton">
											翻訳
										</a>
									</td>
								</tr>

								<!-- 次店 -->
								<tr class="bcTitleList fb fcTitleList">
									<td width="130">次店</td>
									<td width="130">得意先コード</td>
									<td>得意先名称</td>
								</tr>

								<nested:notEmpty property="searchBalanceList">
									<nested:iterate id="searchBalanceList" property="searchBalanceList" indexId="index">
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
											<tr class="bcList1">
										</app:modulo>

										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
											<tr class="bcList2">
										</app:modulo>

										<td>
											<%-- 次店 --%>
											<nested:write property="shopLevelName"/>
										</td>

										<td>
											<%-- 得意先コード --%>
											<nested:write property="venderCd"/>
										</td>

										<td>
											<%-- 得意先名称 --%>
											<nested:write property="venderName1"/>
										</td>
									</nested:iterate>
								</nested:notEmpty>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">帳合</td>
									<td colspan="3">
										<nested:write property="shopLevelName"/>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">区分</td>
									<td colspan="3">
										<% pageContext.setAttribute("SelectBalanceType", new com.asahikaseieng.app.comboboxes.SelectBalanceType(false, false)); %>
										<nested:select property="balanceType" onchange="setDirtyFlg();" >
											<nested:options name="SelectBalanceType" property="values" labelName="SelectBalanceType" labelProperty="labels"/>
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
													<a href="#" onclick="if(!checkLevel()){if(!deleteConfirm()){return false;}else{return form_submit('op', 'delete'); return false;}}else{if(!deleteLevelConfirm()){return false;}else{return form_submit('op', 'delete'); return false;}}" class="cssButton">
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
