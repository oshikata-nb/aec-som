<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taginclude.jsf"%>

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
	<%-- 20211209 Asclab Kouchi EdgeIE対応 --%>
	<%-- 検索画面からCallされるメソッド
	(postjavascript.jsfのopen_modal_popup_edgeと対応) --%>
	function setBalanceValues(balanceCd, balanceTypeName) {
		$('balanceCd').value = balanceCd;
		form_submit('op', 'getBalance');
		setDirtyFlg();
	}

	<%-- Load時の処理 --%>
	Event.observe(window, 'load', function() {
		<%-- フォーム設定の初期化 --%>
		initializeFormState();

		<%-- 必須フィールド定義 --%>
        defineAsRequiredField("strEstimateInputDate");
        defineAsRequiredField("balanceCd");
        defineAsRequiredField("strEstimateValidDateFrom");
        defineAsRequiredField("strEstimateValidDateTo");

		<%-- 日付型フィールド定義 --%>
		defineAsDateField("strEstimateInputDate");
		defineAsDateField("strEstimateValidDateFrom");
		defineAsDateField("strEstimateValidDateTo");

		<%-- 明細部 --%>
		var tblList = $('tblList');

		if (tblList != null) {
			if (0 < tblList.rows.length) {
				for (i = 0; null != $("strStandardUnitPrice" + i); i++) {
					<%-- 必須フィールド定義 --%>
			        defineAsRequiredField("itemCd" + i);
			        defineAsRequiredField("strStandardUnitPrice" + i);

					<%-- 数値型フィールド定義 --%>
					defineAsNumberField("strStandardUnitPrice" + i);
					defineAsNumberField("strStandardDiscount" + i);
					defineAsNumberField("strSpecialDiscount" + i);
					defineAsNumberField("strStandardAmount" + i);
					defineAsNumberField("strMatss" + i);
				}
			}
		}

		<%-- 品目のautocomplete --%>
		if (tblList != null) {
			if (0 < tblList.rows.length) {
				for (i = 0; null != $("itemCd" + i); i++) {
					new Ajax.Autocompleter(
						"searchEstimateDetailList[" + i + "].itemCd",
						"autocomplete_itemcd" + i,
						"<%= request.getContextPath() %>/ItemForAutoComplete.do?op=searchDetailDigitItemPrice",
						{
							paramName : "code",
							afterUpdateElement : eval("setItemLabel" + i)
						}
					);
					Event.observe("itemCd"+i, "keypress", clearLabel.bindAsEventListener($("itemName"+i)), false);
					Event.observe("itemCd"+i, "keypress", clearText.bindAsEventListener($("otherCompanyCd1"+i)), false);
					Event.observe("itemCd"+i, "keypress", clearLabel.bindAsEventListener($("styleOfPacking"+i)), false);
					Event.observe("itemCd"+i, "keypress", clearText.bindAsEventListener($("strStandardUnitprice"+i)), false);
				}
			}
		}

		<%-- 他社コード1のautocomplete --%>
		if (tblList != null) {
			if (0 < tblList.rows.length) {
				for (i = 0; null != $("otherCompanyCd1" + i); i++) {
					new Ajax.Autocompleter(
						"searchEstimateDetailList[" + i + "].otherCompanyCd1",
						"autocomplete_othercompanycd" + i,
						"<%= request.getContextPath() %>/ItemForAutoComplete.do?op=searchDetailDigitItemOtherCompanyPrice",
						{
							paramName : "code",
							afterUpdateElement : eval("setOtherCompanyLabel" + i)
						}
					);
					Event.observe("otherCompanyCd1"+i, "keypress", clearLabel.bindAsEventListener($("itemName"+i)), false);
					Event.observe("otherCompanyCd1"+i, "keypress", clearText.bindAsEventListener($("itemCd"+i)), false);
					Event.observe("otherCompanyCd1"+i, "keypress", clearLabel.bindAsEventListener($("styleOfPacking"+i)), false);
					Event.observe("otherCompanyCd1"+i, "keypress", clearText.bindAsEventListener($("strStandardUnitprice"+i)), false);
				}
			}
		}

		<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
		storeInitValues('dirtyFlg', /^checked.*/);

		refreshLabel();
	}, false);

	<%-- ajax --%>
	<logic:iterate id="list" name="estimateDetailForm" property="searchEstimateDetailList" indexId="index">
		function setItemLabel<bean:write name='index'/>(text, li) {
			$("searchEstimateDetailList[<bean:write name='index'/>].otherCompanyCd1").value = getHiddenValue(li,"otherCompanyCd1");
			$("lblItemName<bean:write name='index'/>").innerHTML = li.getElementsByTagName('span')[0].innerText;
			$("searchEstimateDetailList[<bean:write name='index'/>].itemName").value = li.getElementsByTagName('span')[0].innerText;
			$("lblStyleOfPacking<bean:write name='index'/>").innerHTML = getHiddenValue(li,"styleOfPacking");
			$("searchEstimateDetailList[<bean:write name='index'/>].styleOfPacking").value = getHiddenValue(li,"styleOfPacking");
			$("searchEstimateDetailList[<bean:write name='index'/>].strStandardUnitPrice").value = getHiddenValue(li,"sellingPrice");
			$("searchEstimateDetailList[<bean:write name='index'/>].smallnumLength").value = getHiddenValue(li,"smallnumLength");
			$("searchEstimateDetailList[<bean:write name='index'/>].roundDivision").value = getHiddenValue(li,"roundDivision");

			setCursor('1');
			calcUnitprice(<bean:write name='index'/>);
			setDirtyFlg();
		}
	</logic:iterate>

	<logic:iterate id="list" name="estimateDetailForm" property="searchEstimateDetailList" indexId="index">
		function setOtherCompanyLabel<bean:write name='index'/>(text, li) {
			$("searchEstimateDetailList[<bean:write name='index'/>].itemCd").value = getHiddenValue(li,"code");
			$("lblItemName<bean:write name='index'/>").innerHTML = li.getElementsByTagName('span')[0].innerText;
			$("searchEstimateDetailList[<bean:write name='index'/>].itemName").value = li.getElementsByTagName('span')[0].innerText;
			$("lblStyleOfPacking<bean:write name='index'/>").innerHTML = getHiddenValue(li,"styleOfPacking");
			$("searchEstimateDetailList[<bean:write name='index'/>].styleOfPacking").value = getHiddenValue(li,"styleOfPacking");
			$("searchEstimateDetailList[<bean:write name='index'/>].strStandardUnitPrice").value = getHiddenValue(li,"sellingPrice");
			$("searchEstimateDetailList[<bean:write name='index'/>].smallnumLength").value = getHiddenValue(li,"smallnumLength");
			$("searchEstimateDetailList[<bean:write name='index'/>].roundDivision").value = getHiddenValue(li,"roundDivision");

			setCursor('2');
			calcUnitprice(<bean:write name='index'/>);
			setDirtyFlg();
		}
	</logic:iterate>

	<%-- 確認メッセージ --%>
	function putConfirm() {
		<%-- カーソルセット --%>
		setCursor(null);

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

	<%-- 承認依頼メッセージ --%>
	function putApprovalRequestConfirm() {
		var flg = $("dirtyFlg").value;
		alertMsg = new Array();

		if (flg) {
			/* 何か値が変更されている場合 */
			alertMsg[0] = "画面の編集内容は破棄して承認依頼されます。よろしいですか？";
		} else {
			alertMsg[0] = "承認依頼を行います。よろしいですか？";
		}

		return confirm(alertMsg[0]);
	}

	<%-- 承認メッセージ --%>
	function putApprovalConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "承認します。よろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- 否認メッセージ --%>
	function putNegationConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "否認します。よろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- 承認取消メッセージ --%>
	function putApprovalCancelConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "承認取消します。よろしいですか？";
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

	<%-- カーソルセット --%>
	function setCursor(pos) {
		$("cursor").value = pos;
	}

	<%-- 数値変換(不正な数値の場合defを返す) --%>
	function strToNum(str, def) {
		var val = parseFloat(str.replace(/,/g, ""));

		if(isNaN(val)){
			val = def;
		}

		return val;
	}

	<%-- 単価計算 --%>
	function calcUnitprice(index) {
//		var value = strToNum($F("strStandardUnitPrice" + index), 0) - strToNum($F("strStandardDiscount" + index), 0) - strToNum($F("strSpecialDiscount" + index), 0);
		var standardUnitPrice = digitFormat($F("smallnumLength" + index), $F("roundDivision" + index), strToNum($F("strStandardUnitPrice" + index), 0)) + "";
		var standardDiscount = digitFormat($F("smallnumLength" + index), $F("roundDivision" + index), strToNum($F("strStandardDiscount" + index), 0)) + "";
		var specialDiscount = digitFormat($F("smallnumLength" + index), $F("roundDivision" + index), strToNum($F("strSpecialDiscount" + index), 0)) + "";
		var value = parseFloat(standardUnitPrice.replace(/,/g, "").replace(/\./g,"")) - parseFloat(standardDiscount.replace(/,/g, "").replace(/\./g,"")) - parseFloat(specialDiscount.replace(/,/g, "").replace(/\./g,""));
		var weight = Math.pow(10, $F("smallnumLength" + index));
		value = value / weight;

		$("lblStrUnitprice" + index).innerHTML = digitFormat($F("smallnumLength" + index), $F("roundDivision" + index), value);
		$("searchEstimateDetailList[" + index + "].strUnitprice").value = digitFormat($F("smallnumLength" + index), $F("roundDivision" + index), value);
		$("searchEstimateDetailList[" + index + "].strStandardUnitPrice").value = digitFormat($F("smallnumLength" + index), $F("roundDivision" + index), strToNum($("searchEstimateDetailList[" + index + "].strStandardUnitPrice").value, 0));
		$("searchEstimateDetailList[" + index + "].strStandardDiscount").value = digitFormat($F("smallnumLength" + index), $F("roundDivision" + index), strToNum($("searchEstimateDetailList[" + index + "].strStandardDiscount").value, 0));
		$("searchEstimateDetailList[" + index + "].strSpecialDiscount").value = digitFormat($F("smallnumLength" + index), $F("roundDivision" + index), strToNum($("searchEstimateDetailList[" + index + "].strSpecialDiscount").value, 0));
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

<nested:form action="/EstimateDetail" method="post"
	onsubmit="return false;" styleId="mainForm">
	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="venderCd" styleId="venderCd" />
	<nested:hidden property="venderName1" styleId="venderName1" />
	<nested:hidden property="cursor" styleId="cursor" />
	<nested:hidden property="dirtyFlg" styleId="dirtyFlg" />
	<nested:hidden property="newFlg" styleId="newFlg" />
	<nested:hidden property="estimateStatus" styleId="estimateStatus" />

	<table cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td class="valignTop alignLeft">
			<table border="0" cellpadding="0" cellspacing="0" width="750">
				<tr>
					<td><!-- ヘッダー部 -->
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td height="10" colspan="2"></td>
						</tr>

						<tr>
							<td class="fcTitle fb">見積/単価マスタ</td>
							<td>
							<table border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td><%-- メッセージ表示 --%> <%@ include
										file="/jsp/common/disp_info_message.jsf"%>
									<%-- メッセージ表示 ここまで --%></td>
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
					<td><%-- メッセージ表示 --%> <%@ include
						file="/jsp/common/disp_error_message.jsf"%>
					<%-- メッセージ表示 ここまで --%></td>
				</tr>

				<tr>
					<td><!-- 明細部 -->
					<table width="100%" cellspacing="2" cellpadding="2" border="0">
						<tr>
							<td class="fcTitleDetail fb bcTitleDetail" width="130">見積番号</td>
							<td width="140"><nested:write property="estimateNo" /></td>

							<td class="fcTitleDetail fb bcTitleDetail" width="110">見積有効期限</td>
							<td><nested:text property="strEstimateValidDateFrom"
								maxlength="10" size="10" styleId="strEstimateValidDateFrom"
								style="ime-mode:disabled" onchange="setDirtyFlg();" /> ～ <nested:text
								property="strEstimateValidDateTo" maxlength="10" size="10"
								styleId="strEstimateValidDateTo" style="ime-mode:disabled"
								onchange="setDirtyFlg();" /></td>
						</tr>

						<tr>
							<td class="fcTitleDetail fb bcTitleDetail">ステータス</td>
							<td><nested:write property="estimateStatusName" />
							</td>
							<td rowspan=3 class="fcTitleDetail fb bcTitleDetail">備考</td>
							<td rowspan=3><nested:textarea property="remark" rows="4"
								cols="40" styleId="remark" onchange="setDirtyFlg();" /></td>
						</tr>

						<tr>
							<td class="fcTitleDetail fb bcTitleDetail">見積入力日</td>
							<td><nested:text property="strEstimateInputDate"
								maxlength="10" size="10" styleId="strEstimateInputDate"
								style="ime-mode:disabled" onchange="setDirtyFlg();" /></td>
						</tr>

						<tr>
							<td class="fcTitleDetail fb bcTitleDetail">帳合コード</td>
							<td><nested:equal property="newFlg" value="true">
								<nested:text property="balanceCd" maxlength="10" size="10"
									styleId="balanceCd" onchange="form_submit('op', 'getBalance'); setDirtyFlg();" />
								<!-- 20211209 Asclab Kouchi EdgeIE対応 -->
								<a href="#"
									onclick="open_modal_popup_edge(620, 470,'BalanceSearch', '', 'srhVenderCd', $('venderCd').value, 'srhVenderName1', $('venderName1').value, 'srhBalanceCd', $('balanceCd').value); return false;"
									class="cssButton"> 翻訳 </a>
							</nested:equal> <nested:notEqual property="newFlg" value="true">
								<nested:write property="balanceCd" />
							</nested:notEqual></td>
						</tr>

						<!-- 次店 -->
						<tr class="bcTitleList fb fcTitleList">
							<td width="130">次店</td>
							<td width="130">得意先コード</td>
							<td colspan="2">得意先名称</td>
						</tr>

						<nested:notEmpty property="searchEstimateBalanceList">
							<nested:iterate id="searchEstimateBalanceList"
								property="searchEstimateBalanceList" indexId="index">
								<app:modulo
									numerator='<%=pageContext.findAttribute("index").toString()%>'
									denominator="2" zero="false">
									<tr class="bcList1">
								</app:modulo>

								<app:modulo
									numerator='<%=pageContext.findAttribute("index").toString()%>'
									denominator="2" zero="true">
									<tr class="bcList2">
								</app:modulo>

								<td><%-- 次店 --%> <nested:write property="shopLevelName" />
								</td>

								<td><%-- 得意先コード --%> <nested:write property="venderCd" /></td>

								<td colspan="2"><%-- 得意先名 --%> <nested:write property="venderName1" />
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
							<table cellpadding="0" cellspacing="0" width="550" border="0">
								<tr>
									<td colspan="4" height="2"></td>
								</tr>

								<tr>
									<nested:empty property="estimateStatus">
										<td class="alignRight">
											<div id="cssButton">
												<a href="#" onclick="setDirtyFlg(); return form_submit('op', 'addlist'); return false;" class="cssButton">
													&nbsp;&nbsp;品目追加&nbsp;&nbsp;
												</a>
											</div>
										</td>

										<td width="50"><br>
										</td>

										<nested:equal property="newFlg" value="true">
											<td class="alignLeft">
												<div id="cssButton">
													<a href="#" onclick="if(!(putDelListConfirm())){return false;}else{setDirtyFlg(); return form_submit('op', 'dellist'); return false;}" class="cssButton">
														&nbsp;&nbsp;品目削除&nbsp;&nbsp;
													</a>
												</div>
											</td>
										</nested:equal>
									</nested:empty>

									<nested:equal property="estimateStatus" value="1">
										<td class="alignRight">
											<div id="cssButton">
												<a href="#" onclick="setDirtyFlg(); return form_submit('op', 'addlist'); return false;" class="cssButton">
													&nbsp;&nbsp;品目追加&nbsp;&nbsp;
												</a>
											</div>
										</td>

										<td width="50"><br>
										</td>

										<nested:equal property="newFlg" value="true">
											<td class="alignLeft">
												<div id="cssButton">
													<a href="#" onclick="if(!(putDelListConfirm())){return false;}else{setDirtyFlg(); return form_submit('op', 'dellist'); return false;}" class="cssButton">
														&nbsp;&nbsp;品目削除&nbsp;&nbsp;
													</a>
												</div>
											</td>
										</nested:equal>
									</nested:equal>

									<nested:equal property="estimateStatus" value="0">
										<td class="alignRight">
											<div id="cssButton">
												<a href="#" onclick="setDirtyFlg(); return form_submit('op', 'addlist'); return false;" class="cssButton">
													&nbsp;&nbsp;品目追加&nbsp;&nbsp;
												</a>
											</div>
										</td>

										<td width="50"><br>
										</td>

										<nested:equal property="newFlg" value="true">
											<td class="alignLeft">
												<div id="cssButton">
													<a href="#" onclick="if(!(putDelListConfirm())){return false;}else{setDirtyFlg(); return form_submit('op', 'dellist'); return false;}" class="cssButton">
														&nbsp;&nbsp;品目削除&nbsp;&nbsp;
													</a>
												</div>
											</td>
										</nested:equal>
									</nested:equal>

									<nested:notEqual property="newFlg" value="true">
										<nested:empty property="estimateStatus">
											<td class="alignRight">
												<div id="cssButton">
													<a href="#" onclick="if(!(putDelListConfirm())){return false;}else{setDirtyFlg(); return form_submit('op', 'dellist'); return false;}" class="cssButton">
														&nbsp;&nbsp;品目削除&nbsp;&nbsp;
													</a>
												</div>
											</td>

											<td width="50"><br>
											</td>
										</nested:empty>

										<nested:equal property="estimateStatus" value="1">
											<td class="alignRight">
												<div id="cssButton">
													<a href="#" onclick="if(!(putDelListConfirm())){return false;}else{setDirtyFlg(); return form_submit('op', 'dellist'); return false;}" class="cssButton">
														&nbsp;&nbsp;品目削除&nbsp;&nbsp;
													</a>
												</div>
											</td>

											<td width="50"><br>
											</td>
										</nested:equal>

										<nested:equal property="estimateStatus" value="0">
											<td class="alignRight">
												<div id="cssButton">
													<a href="#" onclick="if(!(putDelListConfirm())){return false;}else{setDirtyFlg(); return form_submit('op', 'dellist'); return false;}" class="cssButton">
														&nbsp;&nbsp;品目削除&nbsp;&nbsp;
													</a>
												</div>
											</td>

											<td width="50"><br>
											</td>
										</nested:equal>

										<td class="alignLeft">
											<div id="cssButton">
												<a href="#" onclick="return form_submit('op', 'copy'); return false;" class="cssButton">
													&nbsp;見積コピー&nbsp;
												</a>
											</div>
										</td>

										<td width="50"><br>
										</td>

										<td class="alignLeft">
											<div id="cssButton">
												<a href="#" onclick="return form_submit('op', 'report'); return false;" class="cssButton">
													&nbsp;見積書発行&nbsp;
												</a>
											</div>
										</td>
									</nested:notEqual>
								</tr>
							</table>
							</td>
						</tr>
					</table>

					<table width="100%" cellspacing="2" cellpadding="2" border="0"
						id="tblList">
						<!-- 単価 -->
						<tr class="bcTitleList fb fcTitleList">
							<td rowspan="2" width="20"></td>
							<td rowspan="2" width="20">行</td>
							<td width="100">品目コード</td>
							<td width="100">他社コード1</td>
							<td colspan="6">品目名称</td>
							<td colspan="2">荷姿</td>
						</tr>

						<tr class="bcTitleList fb fcTitleList">
							<td colspan="2" width="100">標準単価</td>
							<td colspan="2" width="100">標準値引</td>
							<td colspan="2" width="100">特別値引</td>
							<td colspan="2" width="100">単価</td>
							<td width="100">基準数量</td>
							<td width="100">増付数</td>
						</tr>

						<nested:notEmpty property="searchEstimateDetailList">
							<nested:iterate id="searchEstimateDetailList"
								property="searchEstimateDetailList" indexId="index">
								<app:modulo
									numerator='<%=pageContext.findAttribute("index").toString()%>'
									denominator="2" zero="false">
									<tr class="bcList1">
								</app:modulo>

								<app:modulo
									numerator='<%=pageContext.findAttribute("index").toString()%>'
									denominator="2" zero="true">
									<tr class="bcList2">
								</app:modulo>

								<nested:hidden property="smallnumLength"
									styleId="<%="smallnumLength" + pageContext.findAttribute("index").toString() %>" />
								<nested:hidden property="roundDivision"
									styleId="<%="roundDivision" + pageContext.findAttribute("index").toString() %>" />

								<td rowspan="2"><%-- 行追加行削除用チェックボックス --%> <nested:checkbox
									property="checked"
									styleId="<%="checked" + pageContext.findAttribute("index").toString() %>" />
								</td>

								<td rowspan="2" class="alignRight"><%-- 行 --%>
									<nested:write property="seq" />
								</td>

								<td><%-- 品目コード --%> <nested:text property="itemCd"
									maxlength="20" size="10"
									styleId="<%="itemCd" + pageContext.findAttribute("index").toString() %>"
									onchange='<%="setCursor('1');calcUnitprice(" + pageContext.findAttribute("index").toString() + ");setDirtyFlg();"%>' />
								<div
									id="autocomplete_itemcd<%= pageContext.findAttribute("index").toString() %>"
									class="autocomplete"></div>
								</td>

								<td><%-- 他社コード1 --%> <nested:text
									property="otherCompanyCd1" maxlength="20" size="10"
									styleId="<%="otherCompanyCd1" + pageContext.findAttribute("index").toString() %>"
									style="ime-mode:disabled"
									onchange='<%="setCursor('2');calcUnitprice(" + pageContext.findAttribute("index").toString() + ");setDirtyFlg();"%>' />
								<div
									id="autocomplete_othercompanycd<%= pageContext.findAttribute("index").toString() %>"
									class="autocomplete"></div>
								</td>

								<td colspan="6"><%-- 品目名称 --%>
								<div
									id="<%="lblItemName" + pageContext.findAttribute("index").toString() %>">
								<nested:write property="itemName" /></div>
								<nested:hidden property="itemName"
									styleId="<%="itemName" + pageContext.findAttribute("index").toString() %>" />
								</td>

								<td colspan="2"><%-- 荷姿 --%>
								<div
									id="<%="lblStyleOfPacking" + pageContext.findAttribute("index").toString() %>">
								<nested:write property="styleOfPacking" /></div>
								<nested:hidden property="styleOfPacking"
									styleId="<%="styleOfPacking" + pageContext.findAttribute("index").toString() %>" />
								</td>

								<app:modulo
									numerator='<%=pageContext.findAttribute("index").toString()%>'
									denominator="2" zero="false">
									<tr class="bcList1">
								</app:modulo>

								<app:modulo
									numerator='<%=pageContext.findAttribute("index").toString()%>'
									denominator="2" zero="true">
									<tr class="bcList2">
								</app:modulo>

								<td><%-- 標準単価 --%> <nested:text
									property="strStandardUnitPrice" maxlength="22" size="10"
									styleClass="alignRight"
									styleId="<%="strStandardUnitPrice" + pageContext.findAttribute("index").toString() %>"
									style="ime-mode:disabled"
									onchange='<%="setCursor('3');calcUnitprice(" + pageContext.findAttribute("index").toString() + ");setDirtyFlg();"%>' />
								</td>

								<td>円</td>

								<td><%-- 標準値引 --%> <nested:text
									property="strStandardDiscount" maxlength="22" size="8"
									styleClass="alignRight"
									styleId="<%="strStandardDiscount" + pageContext.findAttribute("index").toString() %>"
									style="ime-mode:disabled"
									onchange='<%="setCursor('4');calcUnitprice(" + pageContext.findAttribute("index").toString() + ");setDirtyFlg();"%>' />
								</td>

								<td>円</td>

								<td><%-- 特別値引 --%> <nested:text
									property="strSpecialDiscount" maxlength="22" size="8"
									styleClass="alignRight"
									styleId="<%="strSpecialDiscount" + pageContext.findAttribute("index").toString() %>"
									style="ime-mode:disabled"
									onchange='<%="setCursor('5');calcUnitprice(" + pageContext.findAttribute("index").toString() + ");setDirtyFlg();"%>' />
								</td>

								<td>円</td>

								<td class="alignRight"><%-- 単価 --%>
								<div
									id="<%="lblStrUnitprice" + pageContext.findAttribute("index").toString() %>">
								<nested:write property="strUnitprice" /></div>
								<nested:hidden property="strUnitprice"
									styleId="<%="strUnitprice" + pageContext.findAttribute("index").toString() %>" />
								</td>

								<td>円</td>

								<td><%-- 基準数量 --%> <nested:text
									property="strStandardAmount" maxlength="22" size="10"
									styleClass="alignRight"
									styleId="<%="strStandardAmount" + pageContext.findAttribute("index").toString() %>"
									style="ime-mode:disabled" onchange="setDirtyFlg();" /></td>

								<td><%-- 増付数 --%> <nested:text property="strMatss"
									maxlength="22" size="10" styleClass="alignRight"
									styleId="<%="strMatss" + pageContext.findAttribute("index").toString() %>"
									style="ime-mode:disabled" onchange="setDirtyFlg();" /></td>
							</nested:iterate>
						</nested:notEmpty>
					</table>
					</td>
				</tr>

				<tr>
					<td height="5"></td>
				</tr>

				<tr>
					<td class="alignCenter">
					<table cellpadding="0" cellspacing="0" width="550" border="0">
						<tr>
							<td colspan="4" height="2"></td>
						</tr>

						<tr>
							<nested:equal property="estimateStatus" value="1">
								<nested:equal property="approvalRequestAuthority" value="true">
									<td class="alignRight">
									<div id="cssButton"><a href="#"
										onclick="if(!putApprovalRequestConfirm()){return false;}else{return form_submit('op', 'approvalRequest'); return false;}"
										class="cssButton"> &nbsp;&nbsp;承認依頼&nbsp;&nbsp; </a></div>
									</td>

									<td width="50"><br>
									</td>
								</nested:equal>

								<nested:equal property="updateAuthority" value="true">
									<td class="alignRight">
									<div id="cssButton"><a href="#"
										onclick="if(!putConfirm()){return false;}else{return form_submit('op', 'update'); return false;}"
										class="cssButton"> &nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp; </a>
									</div>
									</td>

									<td width="50"><br>
									</td>
								</nested:equal>

								<nested:equal property="deleteAuthority" value="true">
									<td class="alignLeft">
									<div id="cssButton"><a href="#"
										onclick="if(!deleteConfirm()){return false;}else{return form_submit('op', 'delete'); return false;}"
										class="cssButton"> &nbsp;&nbsp;全件削除&nbsp;&nbsp; </a>
									</div>
									</td>

									<td width="50"><br>
									</td>
								</nested:equal>
							</nested:equal>

							<nested:equal property="estimateStatus" value="2">
								<nested:equal property="approvalAuthority" value="true">
									<td class="alignRight">
									<div id="cssButton"><a href="#"
										onclick="if(!putApprovalConfirm()){return false;}else{return form_submit('op', 'approval'); return false;}"
										class="cssButton"> &nbsp;&nbsp;承&nbsp;&nbsp;認&nbsp;&nbsp; </a>
									</div>
									</td>

									<td width="50"><br>
									</td>
								</nested:equal>

								<nested:equal property="negationAuthority" value="true">
									<td class="alignCenter">
									<div id="cssButton"><a href="#"
										onclick="if(!putNegationConfirm()){return false;}else{return form_submit('op', 'negation'); return false;}"
										class="cssButton"> &nbsp;&nbsp;否&nbsp;&nbsp;認&nbsp;&nbsp; </a>
									</div>
									</td>

									<td width="50"><br>
									</td>
								</nested:equal>
							</nested:equal>

							<nested:equal property="estimateStatus" value="3">
								<nested:equal property="approvalCancelAuthority" value="true">
									<td class="alignRight">
									<div id="cssButton"><a href="#"
										onclick="if(!putApprovalCancelConfirm()){return false;}else{return form_submit('op', 'approvalCancel'); return false;}"
										class="cssButton"> &nbsp;&nbsp;承認取消&nbsp;&nbsp; </a></div>
									</td>

									<td width="50"><br>
									</td>
								</nested:equal>
							</nested:equal>

							<nested:equal property="newFlg" value="true">
								<nested:equal property="updateAuthority" value="true">
									<td class="alignRight">
									<div id="cssButton"><a href="#"
										onclick="if(!putConfirm()){return false;}else{return form_submit('op', 'update'); return false;}"
										class="cssButton"> &nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp; </a>
									</div>
									</td>

									<td width="50"><br>
									</td>
								</nested:equal>
							</nested:equal>

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
		<%-- Excelダウンロード処理 --%>
		<nested:equal property="excelDownloadFlg" value="true">
			<META HTTP-EQUIV="REFRESH"
				CONTENT="0; URL=<%= request.getContextPath() %>/FileDownload/">
		</nested:equal>
		<%-- Excelダウンロード処理 ここまで --%>
	</table>
</nested:form>

</body>
</html:html>
