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
	<%-- 選択して終了 --%>
	function selectClose(index){
		window.opener.setSalesDeliveryValues(true
			, $F("deliveryCd" + index)
			, $F("searchKana" + index)
			, $F("address" + index)
			, $F("telNo" + index)
		);

		//モーダルダイアログの終了
		window.close();
	}

	<%-- 20211209 Asclab Kouchi EdgeIE対応 --%>
	<%-- キャンセルして終了 --%>
	function selectCancel(){
		window.opener.setSalesDeliveryValues(false, "", "", "", "");

		//モーダルダイアログの終了
		window.close();
	}

	<%-- Load時の処理 --%>
	Event.observe(window, 'load', function() {
		<%-- フォーム設定の初期化 --%>
		initializeFormState();

		<%-- 納入先のauto complete --%>
		new Ajax.Autocompleter(
		  "srhDeliveryCd",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/DeliveryDivisionForAutoComplete.do?op=searchDeliveryDivision",
		  {
			parameters : 'deliveryDivision='+$("srhDivision").value,
		  	paramName : "code",
		    afterUpdateElement : setDeliveryCdLabel
		  }
		);
		Event.observe('srhDeliveryCd',  'keypress', clearText.bindAsEventListener($('srhDeliveryName1')), false);


	}, false);

	<%-- 納入先auto completeの選択後処理 --%>
	function setDeliveryCdLabel(text, li) {
	    $("srhDeliveryName1").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
	}
	function setDeliveryDivision() {
		form_submit('op', 'setDeliveryDivision');
	}

</script>
<%-- ▲業務固有javaScript ここまで --%>

<base target="_self"><%-- ←重要(subumit時に新規windowが開かないようにするおまじない) --%>
</head>

<body>

<nested:form action="/SalesDeliverySearch" method="post" onsubmit="return false;" styleId="mainForm">
	<%@ include file="/jsp/common/postjavascript.jsf" %>
	<nested:hidden property="op"/>
	<table cellpadding="0" cellspacing="5" border="0">
		<tr>
			<td class="valignTop alignLeft">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td><!-- ヘッダー部 -->
							<table border="0" cellpadding="0" cellspacing="0" width="100%">
								<tr>
									<td height="5" colspan="2"></td>
								</tr>
								<tr>
									<td class="fcTitle fb" nowrap>納入先検索</td>
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
							<!-- 検索部 -->
							<table width="100%" cellspacing="" cellpadding=""  border="0">
								<tr>
									<td>
										<table cellspacing="2" cellpadding="0" border="0">
											<tr>
												<td class="bcTitleSearch fb fcTitleSearch">納入先</td>
												<td>
													<nested:text property="srhDeliveryCd" maxlength="10" size="12" styleId="srhDeliveryCd" />
													<div id="autocomplete_selection" class="autocomplete"></div>
												</td>
												<td width="250">
													<nested:text property="srhDeliveryName1" size="18" readonly="true" styleId="srhDeliveryName1" styleClass="noborderAl" tabindex="-1"/>
												</td>
												<td class="fcTitleDetail fb bcTitleDetail">区分</td>
												<td>
													<%
														pageContext.setAttribute("selectSalesDeliveryDivision",
														new com.asahikaseieng.app.comboboxes.SelectSalesDeliveryDivision(false, false));
													%>
													<nested:select property="srhDivision" styleId="srhDivision" onchange="setDeliveryDivision();">
														<nested:options name="selectSalesDeliveryDivision" property="values" labelName="selectSalesDeliveryDivision" labelProperty="labels" />
													</nested:select>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td class="alignRight valignMiddle">
										<div id="cssButton">
											<a href="#" onclick="return form_submit('op', 'search'); return false;" class="cssButton">
												&nbsp;&nbsp;検&nbsp;&nbsp;索&nbsp;&nbsp;
											</a>
										</div>
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
						<td height="3"></td>
					</tr>
					<tr>
						<td><%-- 明細部 --%>
							<table cellspacing="2" cellpadding="2" width="100%">
								<tr class="bcTitleList fb fcTitleList">
									<td></td>
									<td>納入先コード</td>
									<td>納入先略称</td>
									<td>納入先名称1</td>
									<td>納入先名称2</td>
									<td>住所</td>
									<td>電話番号</td>
								</tr>
								<nested:iterate id="searchList" property="searchList" indexId="index">
								<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
									<tr class="bcList1">
								</app:modulo>
								<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
									<tr class="bcList2">
								</app:modulo>
									<td width="20">
										<div id="cssButton">
											<a href="#" class="cssButton"
											   onClick="<%="selectClose('" + pageContext.findAttribute("index").toString() + "')" %>">
												選
     										</a>
										</div>
									</td>
									<%-- 納入先コード --%>
									<td width="90">
										<nested:write property="deliveryCd" />
										<nested:hidden property="deliveryCd" styleId="<%="deliveryCd" + pageContext.findAttribute("index").toString() %>" />
									</td>
									<%-- 納入先略称 --%>
									<td width="80">
										<nested:write property="searchKana" />
										<nested:hidden property="searchKana" styleId="<%="searchKana" + pageContext.findAttribute("index").toString() %>" />
									</td>
									<%-- 納入先名称1 --%>
									<td width="140">
										<nested:write property="deliveryName1" />
										<nested:hidden property="deliveryName1" styleId="<%="deliveryName1" + pageContext.findAttribute("index").toString() %>" />
									</td>
									<%-- 納入先名称2 --%>
									<td width="90">
										<nested:write property="deliveryName2" />
										<nested:hidden property="deliveryName2" styleId="<%="deliveryName2" + pageContext.findAttribute("index").toString() %>" />
									</td>
									<%-- 住所 --%>
									<td>
										<nested:write property="address" />
										<nested:hidden property="address" styleId="<%="address" + pageContext.findAttribute("index").toString() %>" />
									</td>
									<%-- 電話番号 --%>
									<td width="80">
										<nested:write property="telNo" />
										<nested:hidden property="telNo" styleId="<%="telNo" + pageContext.findAttribute("index").toString() %>" />
									</td>
						</nested:iterate>
							</table>
						</td>
					</tr>
					<tr>
						<td height="3"></td>
					</tr>
					<tr>
						<td class="alignCenter">
							<%-- ページング --%>
							<%@ include file="/jsp/common/pager/pager.jsf" %>
							<%-- ページング ここまで --%>
						</td>
					</tr>
					<tr>
						<td height="5"></td>
					</tr>
				</table>
			</nested:notEmpty>
				<table border="0" cellpadding="2" cellspacing="2" width="100%">
					<tr>
						<td class="alignCenter">
							<div id="cssButton">
								<a href="#" onClick="<%="selectCancel()" %>" class="cssButton">
									&nbsp;キャンセル&nbsp;
								</a>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</nested:form>
</body>
</html:html>