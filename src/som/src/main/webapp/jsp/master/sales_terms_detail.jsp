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
	<%-- 20211209 Asclab Kouchi EdgeIE対応 --%>
	<%-- 検索画面からCallされるメソッド
	(postjavascript.jsfのopen_modal_popup_edgeと対応) --%>
	function setBalanceValues(balanceCd, balanceTypeName) {
		$('balanceCd').value = balanceCd;
		$('balanceTypeName').value = balanceTypeName;
		form_submit('op', 'getBalance');
		setDirtyFlg();
	}

	<%-- Load時の処理 --%>
	Event.observe(window, 'load', function() {
		<%-- フォーム設定の初期化 --%>
		initializeFormState();

		<%-- 必須フィールド定義 --%>
        defineAsRequiredField("deliveryCd");
        defineAsRequiredField("balanceCd");

		<%-- 明細部 --%>
		var tblList = $('tblList');

		if (tblList != null) {
			if (0 < tblList.rows.length) {
				for (i = 0; null != $("itemCd" + i); i++) {
					<%-- 必須フィールド定義 --%>
					defineAsRequiredField("itemCd" + i);
				}
			}
		}

		if ($('newFlg').value == 'true') {
			<%-- 納入先のautocomplete --%>
			new Ajax.Autocompleter(
				"deliveryCd",
				"autocomplete_choices",
				"<%= request.getContextPath() %>/DeliveryForAutoComplete.do?op=searchDelivery",
				{
					paramName : "code",
					afterUpdateElement : setDeliveryLabel
				}
			);
		}

		<%-- 品目のautocomplete --%>
		if (tblList != null) {
			if (0 < tblList.rows.length) {
				for (i = 0; null != $("itemCd" + i); i++) {
					new Ajax.Autocompleter(
						"searchSalesTermsDetailList[" + i + "].itemCd",
						"autocomplete_itemcd" + i,
						"<%= request.getContextPath() %>/ItemQueueForAutoComplete.do?op=searchItemQueue",
						{
							paramName : "code",
							afterUpdateElement : eval("setItemLabel" + i)
						}
					);

				}
			}
		}

		<%-- 他社コード1のautocomplete --%>
		if (tblList != null) {
			if (0 < tblList.rows.length) {
				for (i = 0; null != $("otherCompanyCd1" + i); i++) {
					new Ajax.Autocompleter(
						"searchSalesTermsDetailList[" + i + "].otherCompanyCd1",
						"autocomplete_othercompanycd" + i,
						"<%= request.getContextPath() %>/ItemQueueForAutoComplete.do?op=searchDetailItemQueueOtherCompany",
						{
							paramName : "code",
							afterUpdateElement : eval("setOtherCompanyLabel" + i)
						}
					);
				}
			}
		}

		<%-- 担当者のautocomplete 2011/5/25 担当者を納入先から表示のみに変更対応
		new Ajax.Autocompleter(
			"tantoCd",
			"autocomplete_tantocd",
			"<%= request.getContextPath() %>/LoginForAutoComplete.do?op=searchLogin",
			{
				paramName : "code",
				afterUpdateElement : setLoginLabel
			}
		);
 --%>
		<%-- 検索後入力された場合の不整合をただす --%>
		if ($('newFlg').value == 'true') {
			Event.observe('deliveryCd', 'keypress', clearLabel.bindAsEventListener($('deliveryName1')), false);
		}

		<%-- 担当者のautocomplete 2011/5/25 担当者を納入先から表示のみに変更対応
		<%-- Event.observe('tantoCd', 'keypress', clearLabel.bindAsEventListener($('tantoNm')), false); --%>

		<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
		storeInitValues('dirtyFlg', /^checked.*/);
	}, false);

	<%-- ajax --%>
	function setDeliveryLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("deliveryName1").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
		setDirtyFlg();
		form_submit('op', 'getRemarkList');
	}

	<logic:iterate id="list" name="salesTermsDetailForm" property="searchSalesTermsDetailList" indexId="index">
		function setItemLabel<bean:write name='index'/>(text, li) {
			$("searchSalesTermsDetailList[<bean:write name='index'/>].otherCompanyCd1").value = getHiddenValue(li,"otherCompanyCd1");
			$("lblItemName<bean:write name='index'/>").innerHTML = li.getElementsByTagName('span')[0].innerText;
			$("searchSalesTermsDetailList[<bean:write name='index'/>].itemName").value = li.getElementsByTagName('span')[0].innerText;
			$("lblStyleOfPacking<bean:write name='index'/>").innerHTML = getHiddenValue(li,"styleOfPacking");
			$("searchSalesTermsDetailList[<bean:write name='index'/>].styleOfPacking").value = getHiddenValue(li,"styleOfPacking");
			setCursor('1');
			form_submit('op', 'getRemarkList');
			setDirtyFlg();
		}
	</logic:iterate>

	<logic:iterate id="list" name="salesTermsDetailForm" property="searchSalesTermsDetailList" indexId="index">
		function setOtherCompanyLabel<bean:write name='index'/>(text, li) {
			$("searchSalesTermsDetailList[<bean:write name='index'/>].itemCd").value = getHiddenValue(li,"code");
			$("lblItemName<bean:write name='index'/>").innerHTML = li.getElementsByTagName('span')[0].innerText;
			$("searchSalesTermsDetailList[<bean:write name='index'/>].itemName").value = li.getElementsByTagName('span')[0].innerText;
			$("lblStyleOfPacking<bean:write name='index'/>").innerHTML = getHiddenValue(li,"styleOfPacking");
			$("searchSalesTermsDetailList[<bean:write name='index'/>].styleOfPacking").value = getHiddenValue(li,"styleOfPacking");
			setCursor('2');
			form_submit('op', 'getRemarkList');
			setDirtyFlg();
		}
	</logic:iterate>

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

	function open_window(theURL,winName,features) { //v2.0
		win = window.open(theURL,winName,features);
		win.focus();
		return false;
	}
</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/SalesTermsDetail" method="post" onsubmit="return false;" styleId="mainForm">
	<%@ include file="/jsp/common/postjavascript.jsf" %>

	<nested:hidden property="op" styleId="op"/>
	<nested:hidden property="venderCd" styleId="venderCd"/>
	<nested:hidden property="venderName1" styleId="venderName1"/>
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
									<td class="fcTitle fb">販売条件マスタ</td>
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
									<td class="fcTitleDetail fb bcTitleDetail" width="130">納入先コード</td>
									<td>
										<nested:equal property="newFlg" value="true">
											<nested:text property="deliveryCd" maxlength="15" size="15" styleId="deliveryCd" onchange="setDirtyFlg();" />
											<div id="autocomplete_choices" class="autocomplete"></div>
										</nested:equal>

										<nested:notEqual property="newFlg" value="true">
											<nested:write property="deliveryCd"/>
										</nested:notEqual>
									</td>

									<td>
										<div id="lblDeliveryName1">
											<nested:write property="deliveryName1" />
										</div>
										<nested:hidden property="deliveryName1" styleId="deliveryName1"/>
									</td>
									<td class="fcTitleDetail fb bcTitleDetail">運送会社</td>
									<td>
										<nested:write property="carryName"/>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">帳合コード</td>
									<td colspan="2">
										<nested:equal property="newFlg" value="true">
											<nested:text property="balanceCd" maxlength="10" size="10" styleId="balanceCd" onchange="form_submit('op', 'getBalance'); setDirtyFlg();" />
											<!-- 20211209 Asclab Kouchi EdgeIE対応 -->
											<a href ="#" onclick="open_modal_popup_edge(620, 470,'BalanceSearch', '', 'srhVenderCd', $('venderCd').value, 'srhVenderName1', $('venderName1').value, 'srhBalanceCd', $('balanceCd').value); return false;" class="cssButton">
												翻訳
											</a>
										</nested:equal>

										<nested:notEqual property="newFlg" value="true">
											<nested:write property="balanceCd"/>
										</nested:notEqual>
									</td>

									<td class="fcTitleDetail fb bcTitleDetail">リードタイム</td>
									<td>
										<nested:write property="leadTime"/>
									</td>


								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">区分</td>
									<td colspan="2">
										<div id="lblBalanceTypeName">
											<nested:write property="balanceTypeName" />
										</div>
										<nested:hidden property="balanceTypeName" styleId="balanceTypeName"/>
									</td>
									<td class="fcTitleDetail fb bcTitleDetail">指定納入時刻</td>
									<td>
										<nested:write property="deliveryTime"/>
									</td>
								</tr>

								<!-- 次店 -->
								<tr class="bcTitleList fb fcTitleList">
									<td width="130">次店</td>
									<td width="80">得意先ｺｰﾄﾞ</td>
									<td>得意先名称</td>
									<td colspan="2">住所１</td>
									<td>電話番号</td>
								</tr>

								<nested:notEmpty property="searchSalesTermsBalanceList">
									<nested:iterate id="searchSalesTermsBalanceList" property="searchSalesTermsBalanceList" indexId="index">
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
											<%-- 得意先名 --%>
											<nested:write property="venderName1"/>
										</td>

										<td colspan="2">
											<%-- 住所１ --%>
											<nested:write property="address1"/>
										</td>

										<td>
											<%-- 電話番号 --%>
											<nested:write property="telNo"/>
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

							<table width="100%" cellspacing="2" cellpadding="2" border="0" id="tblList">
								<!-- 品目 -->
								<tr class="bcTitleList fb fcTitleList">
									<td width="20"></td>
									<td width="20">行</td>
									<td width="100">品目コード</td>
									<td width="100">他社コード1</td>
									<td>品目名称</td>
									<td>荷姿</td>
								</tr>

								<nested:notEmpty property="searchSalesTermsDetailList">
									<nested:iterate id="searchSalesTermsDetailList" property="searchSalesTermsDetailList" indexId="index">
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
											<tr class="bcList1">
										</app:modulo>

										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
											<tr class="bcList2">
										</app:modulo>

										<td><%-- 行追加行削除用チェックボックス --%>
											<nested:checkbox property="checked" styleId="<%="checked" + pageContext.findAttribute("index").toString() %>" />
										</td>

										<td class="alignRight"><%-- 行 --%>
											<nested:write property="seq" />
										</td>

										<td>
											<%-- 品目コード --%>
											<nested:text property="itemCd" maxlength="20" size="20" styleId="<%="itemCd" + pageContext.findAttribute("index").toString() %>" onchange="setDirtyFlg();"/>
											<div id="autocomplete_itemcd<%= pageContext.findAttribute("index").toString() %>" class="autocomplete" ></div>
										</td>

										<td>
											<%-- 他社コード1 --%>
											<nested:text property="otherCompanyCd1" maxlength="20" size="20" style="ime-mode:disabled" styleId="<%="otherCompanyCd1" + pageContext.findAttribute("index").toString() %>" onchange="setDirtyFlg();"/>
											<div id="autocomplete_othercompanycd<%= pageContext.findAttribute("index").toString() %>" class="autocomplete" ></div>
										</td>

										<td>
											<%-- 品目名称 AECS 佐藤 2020/01/14  受注入力画面における品目名称のリンクを参照 --%>
										<!-- 20211209 Asclab Kouchi EdgeIE対応 -->
										<html:link href="#" onclick='<%="open_modal_popup_edge(730, 600,'OrderLotSearch', '', 'srhItemCd', $('itemCd" + pageContext.findAttribute("index").toString() + "').value); return false;"%>'>
											<div id="<%="lblItemName" + pageContext.findAttribute("index").toString() %>">
												<nested:write property="itemName" />
											</div>
										</html:link>
											<nested:hidden property="itemName" styleId="<%="itemName" + pageContext.findAttribute("index").toString() %>" />
										</td>

										<td>
											<%-- 荷姿 --%>
											<div id="<%="lblStyleOfPacking" + pageContext.findAttribute("index").toString() %>">
												<nested:write property="styleOfPacking" />
											</div>
											<nested:hidden property="styleOfPacking" styleId="<%="styleOfPacking" + pageContext.findAttribute("index").toString() %>" />
										</td>
									</nested:iterate>
								</nested:notEmpty>
							</table>

							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td height="5" colspan="4"></td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">担当者コード</td>
									<td>
										<nested:write property="tantoCd"/>
									</td>

									<td width="500">
										<div id="lblTantoNm">
											<nested:write property="tantoNm" />
										</div>
										<nested:hidden property="tantoNm" styleId="tantoNm"/>
									</td>
								</tr>
							</table>

							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">返信時注意事項</td>
									<td colspan="3">
										<nested:textarea property="orderAutoRemark" cols="73" rows="5" styleId="orderAutoRemark" readonly="true" tabindex="-1"/>
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">受注入力時参照用</td>
									<td colspan="3">
										<nested:textarea property="remark" cols="73" rows="5" styleId="remark" readonly="true" tabindex="-1"/>
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
