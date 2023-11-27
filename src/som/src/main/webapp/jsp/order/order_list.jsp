<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taginclude.jsf"%>

<html:html>
<head>
<%@ include file="/jsp/common/head.jsf"%>
<%@ include file="/jsp/common/head_main.jsf"%>

<%-- ▼業務固有css  ここから
<style type="text/css">
</style>
<%-- ▲業務固有css ここまで --%>

<%-- ▼業務固有javaScript ここから --%>
<script language="JavaScript" type="text/javascript">
	<%-- 詳細画面への遷移 --%>
	function toDetail(url) {
		if (!g_lock) {
			if (continueConfirm()) {
				location.href=url;
				g_lock = true;
			}
		}
	}

	<%-- Load時の処理 --%>
	Event.observe(window, 'load', function() {
		<%-- フォーム設定の初期化 --%>
		initializeFormState();

		<%-- 明細部 --%>
		var tblList = $('tblList');

		<%-- 日付型フィールド定義 --%>
		defineAsDateField("srhOrderDateFrom");
		defineAsDateField("srhOrderDateTo");
		defineAsDateField("srhScheduledShippingDateFrom");
		defineAsDateField("srhScheduledShippingDateTo");
		
		<%-- 納入先のautocomplete --%>
		new Ajax.Autocompleter(
			"srhDeliveryCd",
			"autocomplete_choices",
			"<%= request.getContextPath() %>/DeliveryForAutoComplete.do?op=searchDelivery",
			{
				paramName : "code",
				afterUpdateElement : setDeliveryLabel
			}
		);
		
		<%-- 得意先のauto complete --%>
		new Ajax.Autocompleter(
		  "srhVenderCd",
		  "autocomplete_choices",
		  "<%= request.getContextPath() %>/VenderForAutoComplete.do?op=searchVender",
		  {
		  	paramName : "code",
		  	parameters : 'venderDivision=TS',
		    afterUpdateElement : setVenderLabel
		  }
		);
		
		<%-- 部署のautocomplete --%>
		new Ajax.Autocompleter(
			"srhOrganizationCd",
			"autocomplete_choices",
			"<%= request.getContextPath() %>/OrganizationForAutoComplete.do?op=searchOrganization",
			{
				paramName : "code",
				afterUpdateElement : setOrganizationLabel
			}
		);

		<%-- 営業担当者のautocomplete --%>
		new Ajax.Autocompleter(
			"srhSalesTantoCd",
			"autocomplete_choices",
			"<%= request.getContextPath() %>/LoginForAutoComplete.do?op=searchLogin",
			{
				paramName : "code",
				afterUpdateElement : setSalesLoginLabel
			}
		);
		
		<%-- 入力担当者のautocomplete --%>
		new Ajax.Autocompleter(
			"srhInputTantoCd",
			"autocomplete_choices",
			"<%= request.getContextPath() %>/LoginForAutoComplete.do?op=searchLogin",
			{
				paramName : "code",
				afterUpdateElement : setInputLoginLabel
			}
		);

		<%-- 品目のautocomplete --%>
		new Ajax.Autocompleter(
			"srhItemCd",
			"autocomplete_choices",
			"<%= request.getContextPath() %>/ItemQueueForAutoComplete.do?op=searchItemQueue",
			{
				paramName : "code",
				afterUpdateElement : setInputItemLabel
			}
		);

		<%-- 他社コードのautocomplete --%>
		new Ajax.Autocompleter(
			"srhOtherCompanyCd1",
			"autocomplete_choices",
			"<%= request.getContextPath() %>/ItemQueueForAutoComplete.do?op=searchItemQueueOtherCompany",
			{
				paramName : "code",
				afterUpdateElement : setOtherCompanyItemLabel
			}
		);

		<%-- 納入先が検索後入力された場合の不整合をただす --%>
		Event.observe('srhDeliveryCd', 'keypress', clearLabel.bindAsEventListener($('srhDeliveryName1')), false);
		Event.observe('srhVenderCd', 'keypress', clearLabel.bindAsEventListener($('srhVenderName1')), false);
		Event.observe('srhOrganizationCd', 'keypress', clearLabel.bindAsEventListener($('srhOrganizationName')), false);
		Event.observe('srhSalesTantoCd', 'keypress', clearLabel.bindAsEventListener($('srhSalesTantoName')), false);
		Event.observe('srhInputTantoCd', 'keypress', clearLabel.bindAsEventListener($('srhInputTantoName')), false);
		Event.observe('srhItemCd', 'keypress', clearLabel.bindAsEventListener($('srhItemName')), false);
		Event.observe('srhItemCd',  'keypress', clearText.bindAsEventListener($('srhOtherCompanyCd1')), false);

		if (tblList != null) {
			<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
			<%-- 対象外の例：検索条件部と明細のチェックボックス --%>
			storeInitValues(/^srh.*/);	
		}

		refreshLabel();
	}, false);

	<%-- ajax --%>
	function setDeliveryLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("srhDeliveryName1").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
	}
	
	<%-- 得意先auto completeの選択後処理 --%>
	function setVenderLabel(text, li) {
	    $("srhVenderName1").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
	}

	<%-- ajax --%>
	function setOrganizationLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("srhOrganizationName").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
	}

	function setSalesLoginLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("srhSalesTantoName").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
	}

	function setInputLoginLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("srhInputTantoName").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
	}

	function setInputItemLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("srhItemName").value = li.getElementsByTagName('span')[0].innerText;
		$("srhOtherCompanyCd1").value = getHiddenValue(li,"otherCompanyCd1");
		refreshLabel();
	}

	function setOtherCompanyItemLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("srhItemName").value = li.getElementsByTagName('span')[0].innerText;
		$("srhItemCd").value = getHiddenValue(li,"code");
		refreshLabel();
	}
	
	function open_window(theURL,winName,features) { //v2.0
		win = window.open(theURL,winName,features);
		win.focus();
		return false;
	}
	<%-- 確認メッセージ --%>
	function reportConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "帳票(EXCEL)出力してもよろしいですか？";

		return confirm(alertMsg[0]);
	}
	<%-- 確認メッセージ --%>
	function orderReportConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "受注一覧表出力してもよろしいですか？";

		return confirm(alertMsg[0]);
	}

</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/OrderList" method="post" onsubmit="return false;"
	styleId="mainForm">
	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />

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
							<td class="fcTitle fb" width="250">受注</td>
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
					<td><!-- 検索部 -->
					<table cellspacing="" cellpadding="" border="0" width="750">
						<tr>
							<td>
							<table cellspacing="2" cellpadding="1" border="0">
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch" width="80">受注番号</td>
									<td>
										<nested:text property="srhOrderNoFrom" maxlength="11" size="8" styleId="srhOrderNoFrom"  style="ime-mode:disabled"/>
									</td>
									<td width="20">～</td>
									<td>
										<nested:text property="srhOrderNoTo" maxlength="11" size="8" styleId="srhOrderNoTo"  style="ime-mode:disabled"/>
									</td>
									<td class="bcTitleSearch fb fcTitleSearch" width="80">受注区分</td>
									
									<td>
										<%
											pageContext.setAttribute("selectOrderDivisionCode",
											new com.asahikaseieng.app.comboboxes.SelectOrderDivisionCode(true, false));
										%>
										<nested:select property="srhOrderDivision" styleId="srhOrderDivision">
											<nested:options name="selectOrderDivisionCode" property="values" labelName="selectOrderDivisionCode" labelProperty="labels" />
										</nested:select>
									</td>



									<td class="bcTitleSearch fb fcTitleSearch" width="80">ステータス</td>
									<td>
										<nested:select property="srhStatus">
										<nested:options property="orderStatusValues" labelProperty="orderStatusLabels" /></nested:select>
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch" width="80">受注日</td>
									<td><nested:text property="srhOrderDateFrom"
										maxlength="10" size="6" styleId="srhOrderDateFrom"  style="ime-mode:disabled"/></td>
									<td width="20">～</td>
									<td><nested:text property="srhOrderDateTo" maxlength="10"
										size="6" styleId="srhOrderDateTo"  style="ime-mode:disabled"/></td>
									<td class="bcTitleSearch fb fcTitleSearch" width="80">出荷予定日</td>
									<td><nested:text property="srhScheduledShippingDateFrom"
										maxlength="10" size="6" styleId="srhScheduledShippingDateFrom"  style="ime-mode:disabled"/>
									</td>
									<td width="20">～</td>
									<td><nested:text property="srhScheduledShippingDateTo"
										maxlength="10" size="6" styleId="srhScheduledShippingDateTo"  style="ime-mode:disabled"/>
									</td>
								</tr>
							</table>
							<table cellspacing="2" cellpadding="1" border="0">
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch" width="80">納入先</td>
									<td>
										<nested:text property="srhDeliveryCd" maxlength="15" size="6" styleId="srhDeliveryCd" />
										<div id="autocomplete_choices" class="autocomplete"></div>
									</td>
									<td width="170">
										<div id="lblSrhDeliveryName1">
											<nested:write property="srhDeliveryName1" />
										</div>
										<nested:hidden property="srhDeliveryName1" styleId="srhDeliveryName1" />
									</td>
									<td class="bcTitleSearch fb fcTitleSearch" width="80">住所</td>
									<td>
										<nested:text property="srhDeliveryAddress" maxlength="30" size="20" styleId="srhDeliveryAddress" />
									</td>
									<td class="bcTitleSearch fb fcTitleSearch" width="80">電話番号</td>
									<td>
										<nested:text property="srhDeliveryTelNo" maxlength="13" size="15" styleId="srhDeliveryTelNo"  style="ime-mode:disabled"/>
									</td>
								</tr>
							</table>
							<table cellspacing="2" cellpadding="1" border="0">
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch" width="80">得意先</td>
									<td>
										<nested:text property="srhVenderCd" maxlength="15" size="6" styleId="srhVenderCd" />
									</td>
									<td width="170">
										<div id="lblSrhVenderName1">
											<nested:write property="srhVenderName1" />
										</div>
										<nested:hidden property="srhVenderName1" styleId="srhVenderName1" />
									</td>

									<td class="bcTitleSearch fb fcTitleSearch" width="80">担当部署</td>
									<td>
										<nested:text property="srhOrganizationCd" maxlength="10" size="6" styleId="srhOrganizationCd"/>
									</td>

									<td width="170">
										<div id="lblSrhOrganizationName">
											<nested:write property="srhOrganizationName" />
										</div>
										<nested:hidden property="srhOrganizationName" styleId="srhOrganizationName"/>
									</td>
								</tr>
							</table>
							<table cellspacing="2" cellpadding="1" border="0">
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch" width="80">営業担当者</td>
									<td>
										<nested:text property="srhSalesTantoCd" maxlength="10" size="6" styleId="srhSalesTantoCd" />
									</td>
									<td width="170">
										<div id="lblSrhSalesTantoName">
											<nested:write property="srhSalesTantoName" />
										</div>
										<nested:hidden property="srhSalesTantoName" styleId="srhSalesTantoName"/>
									</td>

									<td class="bcTitleSearch fb fcTitleSearch" width="80">入力担当者</td>
									<td>
										<nested:text property="srhInputTantoCd" maxlength="10" size="6" styleId="srhInputTantoCd" />
									</td>
									<td width="170">
										<div id="lblSrhInputTantoName">
											<nested:write property="srhInputTantoName" />
										</div>
										<nested:hidden property="srhInputTantoName" styleId="srhInputTantoName"/>
									</td>
									
								</tr>
							</table>
							<table cellspacing="2" cellpadding="1" border="0">
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch" width="80">品目</td>
									<td>
										<nested:text property="srhItemCd" maxlength="10" size="6" styleId="srhItemCd" />
									</td>
									<td width="408">
										<div id="lblSrhItemName">
											<nested:write property="srhItemName" />
										</div>
										<nested:hidden property="srhItemName" styleId="srhItemName"/>
									</td>
									
									<td class="bcTitleSearch fb fcTitleSearch" width="80">他社コード1</td>
									<td>
										<nested:text property="srhOtherCompanyCd1" maxlength="10" size="6" styleId="srhOtherCompanyCd1" />
									</td>
								</tr>


							</table>
							<table cellspacing="2" cellpadding="1" border="0">
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">運送会社コード</td>
									<td>
										<nested:select property="srhCarryCd" style="margin: 0;padding: 0" styleId="srhCarryCd">
											<nested:optionsCollection property="carryCombo" label="labales" value="values" />
										</nested:select>
									</td>
								</tr>


							</table>

							</td>
						</tr>

						<tr>
							<td class="alignRight">
							<table cellspacing="1" cellpadding="1" border="0">
								<tr>
									<td>
									<div id="cssButton"><a href="#"
										onclick="return form_submit('op', 'search'); return false;"
										class="cssButton"> &nbsp;&nbsp;検&nbsp;&nbsp;索&nbsp;&nbsp;
									</a></div>
									</td>

									<td>
										<div id="cssButton">
 										<a href="#"	onclick="return form_submit('op', 'newPage'); return false;" class="cssButton"> 
										&nbsp;&nbsp;新&nbsp;&nbsp;規&nbsp;&nbsp;
										</a>
										</div>
									</td>
								</tr>
							</table>
							</td>
						</tr>

						<tr height="5">
							<td></td>
						</tr>

						<tr>
							<td class="bcTitleLine"></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>

			<nested:notEmpty property="searchList">
				<table border="0" cellpadding="0" cellspacing="0" width="750">
					<tr>
						<td><!-- 明細部 -->
						<table border="0">
							<tr>
								<td class="alignRight">
								<table cellspacing="1" cellpadding="1" border="0">
									<tr>
										<td>
										<div id="cssButton">
											<a href="#" onclick="if (!(orderReportConfirm())) {return false;}else{return form_submit('op', 'orderReport'); return false;}" class="cssButton">
												受注一覧表
											</a>
										</div>
										</td>
										<td>
										<div id="cssButton">
											<a href="#" onclick="if (!(reportConfirm())) {return false;}else{return form_submit('op', 'report'); return false;}" class="cssButton">
												帳票(EXCEL) 
											</a>
										</div>
										</td>
									</tr>
								</table>
								</td>
							</tr>

							<tr>
								<td>
								<table cellspacing="2" cellpadding="1" id="tblList">
									<tr class="bcTitleList fb fcTitleList">
										<td width="80">受注番号</td>
										<td width="15">行</td>
										<td width="80">受注区分</td>
										<td width="90">納入先</td>
										<td width="90">得意先</td>
										<td width="110">品目</td>
										<td width="50">荷姿</td>
										<td width="80">受注日</td>
										<td width="100">ステータス</td>

									</tr>

									<nested:iterate id="searchList" property="searchList"
										indexId="index">
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

										<nested:define id="oid1" property="orderNo" />

										<td><%-- 受注番号 --%> <html:link href="#"
											onclick='<%="toDetail('" + request.getContextPath() + "/OrderDetail.do?op=init&orderNo=" + pageContext.findAttribute("oid1").toString() + "'); return false;"%>'>
											<nested:write property="orderNo" />
										</html:link></td>

										<td><%-- 行番号 --%> <nested:write property="rowNo" /></td>
										<td><%-- 受注区分 --%> <nested:write
											property="orderDivisionName" /></td>
										<td><%-- 納入先 --%> <nested:write property="dispDeliveryName" />
										</td>
										<td><%-- 得意先 --%> <nested:write property="dispVenderName" />
										</td>
										<td><%-- 品目 --%> <nested:write property="dispItemName" /></td>
										<td><%-- 荷姿 --%> <nested:write property="styleOfPacking" />
										</td>
										<td><%-- 受注日 --%> <nested:write property="orderDate"
											format="yyyy/MM/dd" /></td>
										<td><%-- ステータス --%> <nested:write property="statusName" />
										</td>
									</nested:iterate>
								</table>
								</td>
							</tr>
						</table>
						</td>
					</tr>

					<tr>
						<td class="alignCenter"><%-- ページング --%> <%@ include
							file="/jsp/common/pager/pager.jsf"%> <%-- ページング ここまで --%>
						</td>
					</tr>
				</table>
			</nested:notEmpty></td>
		</tr>

		<%-- Excelダウンロード処理 --%>
		<nested:equal property="excelDownloadFlg" value="true">
			<META HTTP-EQUIV="REFRESH"
				CONTENT="0; URL=<%= request.getContextPath() %>/FileDownload/">
		</nested:equal>

		<%-- Excelダウンロード処理 --%>
		<nested:equal property="excelSlipDownloadFlg" value="true">
			<META HTTP-EQUIV="REFRESH"
				CONTENT="0; URL=<%= request.getContextPath() %>/FileDownload/">
		</nested:equal>
	</table>
</nested:form>

</body>
</html:html>
