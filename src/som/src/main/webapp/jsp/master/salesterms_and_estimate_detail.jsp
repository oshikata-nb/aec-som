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
		defineAsRequiredField("itemCdFrom");
		<%-- 処理区分が「4:見積単価コピー」または「5:販売条件コピー」または「6:見積単価・販売条件コピー」の場合はコピー先品目に関する項目を表示 --%>
		var div = $F("processDivision");
		if (div == 4 || div == 5 || div == 6) {
			$("divItemCdTo").style.display = "block";
			defineAsRequiredField("itemCdTo");
		} else {
			$("divItemCdTo").style.display = "none";
		}
	
		<%-- コピー元・削除品目のautocomplete --%>
		new Ajax.Autocompleter(
			"itemCdFrom",
			"autocomplete_itemcd_from",
			"<%= request.getContextPath() %>/ItemQueueForAutoComplete.do?op=searchDetailItemQueue",
			{
				paramName : "code",
				afterUpdateElement : setItemQueueLabelFrom
			}
		);
		
		<%-- コピー先品目のautocomplete --%>
		new Ajax.Autocompleter(
			"itemCdTo",
			"autocomplete_itemcd_to",
			"<%= request.getContextPath() %>/ItemQueueForAutoComplete.do?op=searchDetailItemQueue",
			{
				paramName : "code",
				afterUpdateElement : setItemQueueLabelTo
			}
		);
	
		<%-- 検索後入力された場合の不整合をただす --%>
		Event.observe('itemCdFrom', 'keypress', clearLabel.bindAsEventListener($('itemNameFrom')), false);
		Event.observe('itemCdFrom', 'keypress', clearLabel.bindAsEventListener($('otherCompanyCd1From')), false);
		Event.observe('itemCdFrom', 'keypress', clearLabel.bindAsEventListener($('styleOfPackingFrom')), false);
		Event.observe('itemCdTo', 'keypress', clearLabel.bindAsEventListener($('itemNameTo')), false);
		Event.observe('itemCdTo', 'keypress', clearLabel.bindAsEventListener($('otherCompanyCd1To')), false);
		Event.observe('itemCdTo', 'keypress', clearLabel.bindAsEventListener($('styleOfPackingTo')), false);
		
		if (tblList != null) {
			<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
			<%-- 対象外の例：検索条件部と明細のチェックボックス --%>
			storeInitValues(/^srh.*/);	
		}
	
		refreshLabel();
	}, false);
	
	<%-- ajax --%>
	function setItemQueueLabelFrom(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("itemNameFrom").value = li.getElementsByTagName('span')[0].innerText;
	    $("otherCompanyCd1From").value = getHiddenValue(li,"otherCompanyCd1");
	    $("styleOfPackingFrom").value = getHiddenValue(li,"styleOfPacking");
		refreshLabel();
	}
	
	function setItemQueueLabelTo(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("itemNameTo").value = li.getElementsByTagName('span')[0].innerText;
	    $("otherCompanyCd1To").value = getHiddenValue(li,"otherCompanyCd1");
	    $("styleOfPackingTo").value = getHiddenValue(li,"styleOfPacking");
		refreshLabel();
	}

	function open_window(theURL,winName,features) { //v2.0
		win = window.open(theURL,winName,features);
		win.focus();
		return false;
	}
	
	<%-- 登録確認メッセージ --%>
	function putUpdate() {
		alertMsg = new Array();
		alertMsg[0] = "登録してよろしいですか？";
		return confirm(alertMsg[0]);
	}
	
	<%-- 削除確認メッセージ --%>
	function deleteAlert() {
		alertMsg = new Array();
		alertMsg[0] = "削除してよろしいですか？";
		return confirm(alertMsg[0]);
	}
	
	<%-- 確定確認メッセージ --%>
	function putConfirm() {
		var flg = $("dirtyFlg").checked;
		<%-- カーソルセット --%>
		alertMsg = new Array();
		
		if (flg) {
			/* 何か値が変更されている場合 */
			alertMsg[0] = "画面の編集内容を破棄して確定処理します。よろしいですか？";
		} else {
			alertMsg[0] = "確定してよろしいですか？";
		}		
		
		return confirm(alertMsg[0]);
	}

	<%-- 確定取消メッセージ --%>
	function putConfirmCancel() {
		<%-- カーソルセット --%>
		alertMsg = new Array();
		alertMsg[0] = "確定取消してよろしいですか？";
		return confirm(alertMsg[0]);
	}
	
	<%-- 変更確認メッセージ --%>
	function putDirtyConfirm() {
		var flg = $("dirtyFlg").checked;
		if (flg) {
			/* 何か値が変更されている場合 */
			return confirm("<bean:message key="message.confirm.under.edit" />");
		}else{
			return true;
		}
	}
	
	<%-- 変更フラグセット --%>
	function setDirtyFlg() {
		$("dirtyFlg").checked = "true";
	}
	
	<%-- 処理区分変更時処理 --%>
	function changeProcessDivision() {
		<%-- 処理区分が「4:見積単価コピー」または「5:販売条件コピー」または「6:見積単価・販売条件コピー」の場合はコピー先品目に関する項目を表示 --%>
		var div = $F("processDivision");
		if (div == 4 || div == 5 || div == 6) {
			$("divItemCdTo").style.display = "block";
			defineAsRequiredField("itemCdTo");
		} else {
			$("divItemCdTo").style.display = "none";
		}
	}

</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/SalestermsAndEstimateDetail" method="post" onsubmit="return false;" styleId="mainForm">	
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
									<td class="fcTitle fb" width="300">販売条件・見積単価　コピー作成・削除詳細</td>
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
									<td class="bcTitleLine" colspan="4"></td>
								</tr>

								<tr>
									<td height="5" colspan="4"></td>
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
							<table width="100%" cellspacing="" cellpadding=""  border="0">
								<tr>
									<td>
										<table cellspacing="2" cellpadding="1"  border="0">
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail" width="125">入力日</td>
												<td>
													<nested:write property="strInputDate" />
												</td>
												<td width="100"></td>
												<td class="fcTitleDetail fb bcTitleDetail" width="100">入力者</td>
												<td>
													<nested:write property="tantoName" />
												</td>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail">ステータス</td>
												<td>
													<nested:notEqual property="newFlg" value="true">
														<nested:write property="statusName"/>
													</nested:notEqual>
												</td>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail">処理区分</td>
												<td>
													<nested:select property="processDivision" styleId="processDivision" onchange="changeProcessDivision(); setDirtyFlg();" >
														<nested:options property="processDivisionValues" labelProperty="processDivisionLabels" />
													</nested:select>
												</td>
											</tr>
										</table>
										<table cellspacing="2" cellpadding="1"  border="0">
 											<tr> 
												<td class="fcTitleDetail fb bcTitleDetail">コピー元・削除品目</td>
												<td>
													<nested:text property="itemCdFrom" maxlength="20" size="10" styleId="itemCdFrom" onchange="setDirtyFlg();" />
													<div id="autocomplete_itemcd_from" class="autocomplete"></div>
												</td>

												<td width="245">
													<div id="lblItemNameFrom">
														<nested:write property="itemNameFrom" />
													</div>
													<nested:hidden property="itemNameFrom" styleId="itemNameFrom"/>
												</td>
															
 												<td class="fcTitleDetail fb bcTitleDetail">荷姿</td>
												<td width="85">
													<div id="lblStyleOfPackingFrom">
														<nested:write property="styleOfPackingFrom" />
													</div>
													<nested:hidden property="styleOfPackingFrom" styleId="styleOfPackingFrom"/>
												</td>
												
												<td class="fcTitleDetail fb bcTitleDetail">他社コード1</td>
												<td width="50">
													<div id="lblOtherCompanyCd1From">
														<nested:write property="otherCompanyCd1From" />
													</div>
													<nested:hidden property="otherCompanyCd1From" styleId="otherCompanyCd1From"/>
												</td>											
											</tr> 
										</table>
										<%-- 処理区分が「4:見積単価コピー」または「5:販売条件コピー」または「6:見積単価・販売条件コピー」の場合 --%>
										<div id="divItemCdTo" style="display: none;" >
											<table cellspacing="2" cellpadding="1"  border="0">											
	   											<tr> 
													<td class="fcTitleDetail fb bcTitleDetail" width="125">コピー先品目</td>
													<td>
														<nested:text property="itemCdTo" maxlength="20" size="10" styleId="itemCdTo" onchange="setDirtyFlg();" />
														<div id="autocomplete_itemcd_to" class="autocomplete"></div>
													</td>
	
	  												<td width="245">
														<div id="lblItemNameTo">
															<nested:write property="itemNameTo" />
														</div>
														<nested:hidden property="itemNameTo" styleId="itemNameTo"/>
													</td>
	 
													<td class="fcTitleDetail fb bcTitleDetail">荷姿</td>
													<td width="85">
														<div id="lblStyleOfPackingTo">
															<nested:write property="styleOfPackingTo" />
														</div>
														<nested:hidden property="styleOfPackingTo" styleId="styleOfPackingTo"/>
													</td>
													
													<td class="fcTitleDetail fb bcTitleDetail">他社コード1</td>
													<td width="50">
														<div id="lblOtherCompanyCd1To">
															<nested:write property="otherCompanyCd1To" />
														</div>
														<nested:hidden property="otherCompanyCd1To" styleId="otherCompanyCd1To"/>
													</td>													
												</tr>
											</table>
										</div>
									</td>
								</tr>
								<tr>
									<td class="alignCenter">
									<table cellpadding="0" cellspacing="0" width="450" border="0">
										<tr>
											<td colspan="3" height="30"></td>
										</tr>
										<tr>
											<%-- 新規ボタン押下時 --%>
											<nested:equal property="newFlg" value="true">
												<nested:equal property="updateAuthority" value="true">
													<td class="alignRight">
														<div id="cssButton">
															<a href="#" onclick="if(!putUpdate()){return false;}else{return form_submit('op', 'update'); return false;}" class="cssButton">
																&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
															</a>
														</div>
													</td>
				
													<td width="50">
														<br>
													</td>
												</nested:equal>
											</nested:equal>
											<%-- ステータスが「登録」となっている場合 --%>
											<nested:equal property="status" value="1">
												<nested:equal property="confirmAuthority" value="true">
													<td class="alignRight">
														<div id="cssButton">
															<a href="#" onclick="if(!putConfirm()){return false;}else{return form_submit('op', 'confirm'); return false;}" class="cssButton">
																&nbsp;&nbsp;確&nbsp;&nbsp;定&nbsp;&nbsp;
															</a>
														</div>
													</td>
				
													<td width="50">
														<br>
													</td>
												</nested:equal>
											</nested:equal>
											
											<nested:equal property="status" value="1">
												<nested:equal property="updateAuthority" value="true">
													<td class="alignRight">
														<div id="cssButton">
															<a href="#" onclick="if(!putUpdate()){return false;}else{return form_submit('op', 'update'); return false;}" class="cssButton">
																&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
															</a>
														</div>
													</td>
				
													<td width="50">
														<br>
													</td>
												</nested:equal>
											</nested:equal>
											
											<nested:equal property="status" value="1">
												<nested:equal property="deleteAuthority" value="true">
													<td class="alignRight">
														<div id="cssButton">
															<a href="#" onclick="if(!deleteAlert()){return false;}else{return form_submit('op', 'delete'); return false;}" class="cssButton">
																&nbsp;&nbsp;削&nbsp;&nbsp;除&nbsp;&nbsp;
															</a>
														</div>
													</td>
				
													<td width="50">
														<br>
													</td>
												</nested:equal>
											</nested:equal>
											<%-- ステータスが「確定」となっている場合 --%>
											<nested:equal property="status" value="2">
												<nested:equal property="confirmCancelAuthority" value="true">
													<td class="alignRight">
														<div id="cssButton">
															<a href="#" onclick="if(!putConfirmCancel()){return false;}else{return form_submit('op', 'confirmCancel'); return false;}" class="cssButton">
																&nbsp;&nbsp;確定取消&nbsp;&nbsp;
															</a>
														</div>
													</td>
				
													<td width="50">
														<br>
													</td>
												</nested:equal>
											</nested:equal>																														
										
											<%-- 戻るボタンは常に表示 --%>
											<td class="alignLeft">
											<div id="cssButton"><a href="#"
												onclick="if(!putDirtyConfirm()){return false;}else{return form_submit('op', 'back'); return false;}"
												class="cssButton"> &nbsp;&nbsp;戻&nbsp;&nbsp;る&nbsp;&nbsp; </a></div>
											</td>
										</tr>
									</table>
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
