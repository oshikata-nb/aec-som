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
        defineAsRequiredField("venderCd");
        defineAsRequiredField("itemCd");
        defineAsRequiredField("strValidDate");

		<%-- 日付型フィールド定義 --%>
		defineAsDateField("strValidDate");

		<%-- 明細部 --%>
		var tblList = $('tblList');

		if (tblList != null) {
			if (0 < tblList.rows.length) {
				for (i = 0; null != $("consecutiveNo" + i); i++) {
					<%-- 必須フィールド定義 --%>
			        defineAsRequiredField("strQuantityFrom" + i);
			        defineAsRequiredField("strQuantityTo" + i);
			        defineAsRequiredField("strUnitprice" + i);

					<%-- 数値型フィールド定義 --%>
					defineAsNumberField("strQuantityFrom" + i);
					defineAsNumberField("strQuantityTo" + i);
					defineAsNumberField("strUnitprice" + i);
				}
			}
		}

		if($("newFlg").value == "true"){
			<%-- 仕入先のautocomplete --%>
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
	
			<%-- 品目のautocomplete --%>
			new Ajax.Autocompleter(
				"itemCd",
				"autocomplete_choices",
				"<%= request.getContextPath() %>/ItemQueueForAutoComplete.do?op=searchDetailDigitItemQueue",
				{
					paramName : "code",
					afterUpdateElement : setItemQueueLabel
				}
			);
	
			<%-- 他社コードのautocomplete --%>
			new Ajax.Autocompleter(
				"otherCompanyCd1",
				"autocomplete_choices",
				"<%= request.getContextPath() %>/ItemQueueForAutoComplete.do?op=searchDetailDigitItemQueueOtherCompany",
				{
					paramName : "code",
					afterUpdateElement : setOtherCompanyLabel
				}
			);

			<%-- 検索後入力された場合の不整合をただす --%>
			Event.observe('venderCd', 'keypress', clearLabel.bindAsEventListener($('venderName1')), false);
			Event.observe('itemCd', 'keypress', clearLabel.bindAsEventListener($('itemName')), false);
			Event.observe('itemCd', 'keypress', clearLabel.bindAsEventListener($('styleOfPacking')), false);
			Event.observe('itemCd', 'keypress', clearText.bindAsEventListener($('otherCompanyCd1')), false);
			Event.observe('itemCd', 'keypress', clearText.bindAsEventListener($('smallnumLength')), false);
			Event.observe('itemCd', 'keypress', clearText.bindAsEventListener($('roundDivision')), false);
			Event.observe('itemCd', 'keypress', clearText.bindAsEventListener($('unitOfOperationManagement')), false);
			Event.observe('otherCompanyCd1', 'keypress', clearLabel.bindAsEventListener($('itemName')), false);
			Event.observe('otherCompanyCd1', 'keypress', clearLabel.bindAsEventListener($('styleOfPacking')), false);
			Event.observe('otherCompanyCd1', 'keypress', clearText.bindAsEventListener($('smallnumLength')), false);
			Event.observe('otherCompanyCd1', 'keypress', clearText.bindAsEventListener($('roundDivision')), false);
			Event.observe('otherCompanyCd1', 'keypress', clearText.bindAsEventListener($('unitOfOperationManagement')), false);
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

	function setItemQueueLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("itemName").value = li.getElementsByTagName('span')[0].innerText;
		$("otherCompanyCd1").value = getHiddenValue(li,"otherCompanyCd1");
		$("styleOfPacking").value = getHiddenValue(li,"styleOfPacking");
		$("smallnumLength").value = getHiddenValue(li,"smallnumLength");
		$("roundDivision").value = getHiddenValue(li,"roundDivision");
		$("unitOfOperationManagement").value = getHiddenValue(li,"unitOfOperationManagement");
		refreshLabel();
		setDirtyFlg();
	}

	function setOtherCompanyLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("itemCd").value = getHiddenValue(li,"code");
		$("itemName").value = li.getElementsByTagName('span')[0].innerText;
		$("styleOfPacking").value = getHiddenValue(li,"styleOfPacking");
		$("smallnumLength").value = getHiddenValue(li,"smallnumLength");
		$("roundDivision").value = getHiddenValue(li,"roundDivision");
		$("unitOfOperationManagement").value = getHiddenValue(li,"unitOfOperationManagement");
		refreshLabel();
		setDirtyFlg();
	}

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

	<%-- 単位名称切り替え --%>
	function setChangeUnitName() {
		var tblList = $('tblList');

		if (tblList != null) {
			if (0 < tblList.rows.length) {
				for (i = 0; null != $("consecutiveNo" + i); i++) {
					if($("unitpriceDivision").value == "1"){
						$("lblUnitpriceDivisionName" + i).innerHTML = "円/個";
						$("searchUnitpriceDetailList[" + i + "].unitpriceDivisionName").value = "円/個";
					} else if($("unitpriceDivision").value == "2"){
						$("lblUnitpriceDivisionName" + i).innerHTML = "円/Kg";
						$("searchUnitpriceDetailList[" + i + "].unitpriceDivisionName").value = "円/Kg";
					} else {
						$("lblUnitpriceDivisionName" + i).innerHTML = "";
					}
				}
			}
		}
	}

	<%-- 数量(FROM)セット --%>
	function setQtyFrom(index) {
		var qtyto = strToNum($F("strQuantityTo" + index), 0);
		
		if ($("strQuantityFrom" + (index + 1)) == null) {
			return;
		}

		$("searchUnitpriceDetailList[" + (index + 1) + "].strQuantityFrom").value = digitFormat($F("smallnumLength"), $F("roundDivision"), qtyto);
	}

	<%-- 数値変換(不正な数値の場合defを返す) --%>
	function strToNum(str, def) {
		var val = parseFloat(str.replace(/,/g, ""));

		if(isNaN(val)){
			val = def;
		}

		return val;
	}
</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/UnitpriceDetail" method="post" onsubmit="return false;" styleId="mainForm">	
	<%@ include file="/jsp/common/postjavascript.jsf" %>

	<nested:hidden property="op" styleId="op"/>
	<nested:hidden property="dirtyFlg" styleId="dirtyFlg"/>
	<nested:hidden property="newFlg" styleId="newFlg"/>
	<nested:hidden property="venderDivision" styleId="venderDivision"/>
	<nested:hidden property="smallnumLength" styleId="smallnumLength"/>
	<nested:hidden property="roundDivision" styleId="roundDivision"/>
	<nested:hidden property="unitOfOperationManagement" styleId="unitOfOperationManagement"/>

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
									<td class="fcTitle fb">仕入先別単価マスタ</td>
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
									<td class="bcTitleLine" colspan="3"></td>
								</tr>

								<tr>
									<td height="5" colspan="3"></td>
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
									<td class="fcTitleDetail fb bcTitleDetail" width="130">仕入先コード</td>
									<td>
										<nested:equal property="newFlg" value="true">
											<nested:text property="venderCd" maxlength="15" size="15" styleId="venderCd" onchange="setDirtyFlg();" />
											<div id="autocomplete_choices" class="autocomplete"></div>
										</nested:equal>

										<nested:notEqual property="newFlg" value="true">
											<nested:write property="venderCd"/>
										</nested:notEqual>
									</td>

									<td width="500">
										<div id="lblVenderName1">
											<nested:write property="venderName1" />
										</div>
										<nested:hidden property="venderName1" styleId="venderName1"/>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">品目コード</td>
									<td>
										<nested:equal property="newFlg" value="true">
											<nested:text property="itemCd" maxlength="20" size="20" styleId="itemCd" onchange="setDirtyFlg();" />
										</nested:equal>

										<nested:notEqual property="newFlg" value="true">
											<nested:write property="itemCd"/>
										</nested:notEqual>
									</td>

									<td width="500">
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
											<nested:text property="otherCompanyCd1" maxlength="20" size="20" style="ime-mode:disabled" styleId="otherCompanyCd1" onchange="setDirtyFlg();" />
										</nested:equal>

										<nested:notEqual property="newFlg" value="true">
											<nested:write property="otherCompanyCd1"/>
										</nested:notEqual>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">荷姿</td>
									<td colspan="2">
										<div id="lblStyleOfPacking">
											<nested:write property="styleOfPacking" />
										</div>
										<nested:hidden property="styleOfPacking" styleId="styleOfPacking"/>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">メーカー名</td>
									<td colspan="2">
										<nested:write property="materialMakerName" />
									</td>
								</tr>

								<tr>
									<td height="5" colspan="3"></td>
								</tr>

								<tr>
									<td class="bcTitleLine" colspan="3"></td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">単価区分</td>
									<td colspan="2">
										<% pageContext.setAttribute("SelectUnitpriceDivision", new com.asahikaseieng.app.comboboxes.SelectUnitpriceDivision(false, false)); %>
										<nested:select property="unitpriceDivision" onchange="setChangeUnitName();setDirtyFlg();" >
											<nested:options name="SelectUnitpriceDivision" property="values" labelName="SelectUnitpriceDivision" labelProperty="labels"/>
										</nested:select>
									</td>
								</tr>
							</table>

							<table width="100%" cellspacing="2" cellpadding="2" border="0" id="tblList">
								<!-- 品目 -->
								<tr class="bcTitleList fb fcTitleList">
									<td>No.</td>
									<td>数量(FROM)</td>
									<td>数量(TO)</td>
									<td>単価</td>
									<td>単位</td>
								</tr>

								<nested:notEmpty property="searchUnitpriceDetailList">
									<nested:iterate id="searchUnitpriceDetailList" property="searchUnitpriceDetailList" indexId="index">
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
											<tr class="bcList1">
										</app:modulo>

										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
											<tr class="bcList2">
										</app:modulo>

										<td class="Right"><%-- 行番 --%>
											<div id="<%="lblConsecutiveNo" + pageContext.findAttribute("index").toString() %>">
												<nested:write property="consecutiveNo" />
											</div>
											<nested:hidden property="consecutiveNo" styleId="<%="consecutiveNo" + pageContext.findAttribute("index").toString() %>" />
										</td>

										<td>
											<%-- 数量(FROM) --%>
											<nested:text property="strQuantityFrom" maxlength="22" size="22" styleClass="alignRight" styleId="<%="strQuantityFrom" + pageContext.findAttribute("index").toString() %>" style="ime-mode:disabled" readonly="true" onchange="setDirtyFlg();"/>
										</td>

										<td>
											<%-- 数量(TO) --%>
											<nested:text property="strQuantityTo" maxlength="22" size="22" styleClass="alignRight" styleId="<%="strQuantityTo" + pageContext.findAttribute("index").toString() %>" style="ime-mode:disabled" onchange='<%="setQtyFrom(" + pageContext.findAttribute("index").toString() + ");setDirtyFlg();"%>'/>
										</td>

										<td>
											<%-- 単価 --%>
											<nested:text property="strUnitprice" maxlength="22" size="22" styleClass="alignRight" styleId="<%="strUnitprice" + pageContext.findAttribute("index").toString() %>" style="ime-mode:disabled" onchange="setDirtyFlg();"/>
										</td>

										<td>
											<%-- 単位 --%>
											<div id="<%="lblUnitpriceDivisionName" + pageContext.findAttribute("index").toString() %>">
												<nested:write property="unitpriceDivisionName" />
											</div>
											<nested:hidden property="unitpriceDivisionName" styleId="<%="unitpriceDivisionName" + pageContext.findAttribute("index").toString() %>" />
										</td>
									</nested:iterate>
								</nested:notEmpty>
							</table>

							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td height="5" colspan="4"></td>
								</tr>

								<tr>
									<td class="bcTitleLine" colspan="4"></td>
								</tr>

								<tr>
									<td class="alignCenter">
										<table cellpadding="0" cellspacing="0" width="450" border="0">
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
			
												<td class="alignLeft">
													<div id="cssButton">
														<a href="#" onclick="if(!(putDelListConfirm())){return false;}else{setDirtyFlg(); return form_submit('op', 'dellist'); return false;}" class="cssButton">
															&nbsp;&nbsp;行&nbsp;削&nbsp;除&nbsp;&nbsp;
														</a>
													</div>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>

							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td height="5" colspan="2"></td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">有効開始日</td>
									<td>
										<nested:text property="strValidDate" maxlength="10" size="10" style="ime-mode:disabled" styleId="strValidDate" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">備考</td>
									<td>
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
