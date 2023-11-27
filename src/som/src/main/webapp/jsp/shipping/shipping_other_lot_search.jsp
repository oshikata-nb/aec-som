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
		window.opener.setShippingOtherLotValues(true
			, $F("locationCd" + index)
			, $F("locationName" + index)
			, $F("lotNo" + index)
			, $F("strInventoryQty" + index)
			, $F("strBackorderQty" + index)
			, $F("strInspectionQty" + index)
			, $F("strAssignQty" + index)
			, $F("strValidInventory" + index)
		);

		//モーダルダイアログの終了
		window.close();
	}

	<%-- 20211209 Asclab Kouchi EdgeIE対応 --%>
	<%-- キャンセルして終了 --%>
	function selectCancel(){
		window.opener.setShippingOtherLotValues(false, "", "", "", "", "", "", "", "");

		//モーダルダイアログの終了
		window.close();
	}

	<%-- Load時の処理 --%>
	Event.observe(window, 'load', function() {
		<%-- フォーム設定の初期化 --%>
		initializeFormState();
		<%-- 最初の項目がreadonly入力フィールドでうまくフォーカスがセットされないので --%>
		$("srhLocationCd").focus();

		<%-- ロケーションのauto complete --%>
		new Ajax.Autocompleter(
		  "srhLocationCd",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/ShippingLocationForAutoComplete.do?op=searchLocation",
		  {
		  	paramName : "code",
		  	parameters : 'itemCd='+ $('srhItemCd').value,
		    afterUpdateElement : setLocationLabel
		  }
		);
		Event.observe('srhLocationCd',  'keypress', clearText.bindAsEventListener($('srhLocationName')), false);

	}, false);

	<%-- ロケーションauto completeの選択後処理 --%>
	function setLocationLabel(text, li) {
	    $("srhLocationName").value = li.getElementsByTagName('span')[0].innerText;
	}

</script>
<%-- ▲業務固有javaScript ここまで --%>

<base target="_self"><%-- ←重要(subumit時に新規windowが開かないようにするおまじない) --%>
</head>

<body>

<nested:form action="/ShippingOtherLotSearch" method="post" onsubmit="return false;" styleId="mainForm">
	<%@ include file="/jsp/common/postjavascript.jsf" %>
	<nested:hidden property="op"/>
	<table cellpadding="0" cellspacing="5" border="0">
		<tr>
			<td class="valignTop alignLeft">
				<table border="0" cellpadding="0" cellspacing="0" width="710">
					<tr>
						<td><!-- ヘッダー部 -->
							<table border="0" cellpadding="0" cellspacing="0" width="100%">
								<tr>
									<td height="5" colspan="2"></td>
								</tr>
								<tr>
									<td class="fcTitle fb" width="240">ロット検索</td>
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
										<table cellspacing="2" cellpadding="2"  border="0">
											<tr>
												<td class="bcTitleSearch fb fcTitleSearch">品目コード</td>
												<td>
													<nested:text property="srhItemCd" size="10" readonly="true" styleId="srhItemCd" styleClass="noborderAl" tabindex="-1"/>
												</td>
												<td colspan="3">
													<nested:text property="srhItemName" size="35" readonly="true" styleId="srhItemName" styleClass="noborderAl" tabindex="-1"/>
												</td>
											</tr>
											<tr>
												<td class="bcTitleSearch fb fcTitleSearch">荷姿</td>
												<td>
													<nested:text property="srhStyleOfPacking" size="10" readonly="true" styleId="srhStyleOfPacking" styleClass="noborderAl" tabindex="-1"/>
												</td>
												<td></td>
												<td class="bcTitleSearch fb fcTitleSearch">他社コード1</td>
												<td>
													<nested:text property="srhOtherCompanyCd1" size="10" readonly="true" styleId="srhOtherCompanyCd1" styleClass="noborderAl" tabindex="-1"/>
												</td>
											</tr>
											<tr>
												<td class="bcTitleSearch fb fcTitleSearch">ロケーション</td>
												<td>
													<nested:text property="srhLocationCd" maxlength="20" size="12" styleId="srhLocationCd"/>
													<div id="autocomplete_selection" class="autocomplete"></div>
												</td>
												<td>
													<nested:text property="srhLocationName" size="18" readonly="true" styleId="srhLocationName" styleClass="noborderAl" tabindex="-1"/>
												</td>
												<td class="bcTitleSearch fb fcTitleSearch">ロット</td>
												<td>
													<nested:text property="srhLotNo" maxlength="20" size="12" styleId="srhLotNo" style="ime-mode:disabled" />
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
				<table border="0" cellpadding="0" cellspacing="0" width="710">
					<tr>
						<td height="3"></td>
					</tr>
					<tr>
						<td><!-- 明細部 -->
							<table cellspacing="2" cellpadding="2" width="100%">
								<tr class="bcTitleList fb fcTitleList">
									<td width="10"></td>
									<td width="100">ロケーション</td>
									<td width="100">名称</td>
									<td>ロット</td>
									<td>在庫量</td>
									<td>発注残</td>
									<td>検査待</td>
									<td>引当残</td>
									<td>有効在庫</td>
									<td>完成日</td>
								</tr>
								<nested:iterate id="searchList" property="searchList" indexId="index">
								<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
									<tr class="bcList1">
								</app:modulo>
								<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
									<tr class="bcList2">
								</app:modulo>
									<td>
										<div id="cssButton">
											<a href="#" class="cssButton"
											   onClick="<%="selectClose('" + pageContext.findAttribute("index").toString() + "')" %>">
												選
     										</a>
										</div>
									</td>
									<%-- ロケーション --%>
									<td>
										<nested:write property="locationCd" />
										<nested:hidden property="locationCd" styleId="<%="locationCd" + pageContext.findAttribute("index").toString() %>" />
									</td>
									<%-- ロケーション名称 --%>
									<td>
										<nested:write property="locationName" />
										<nested:hidden property="locationName" styleId="<%="locationName" + pageContext.findAttribute("index").toString() %>" />
									</td>
									<%-- ロット --%>
									<td>
										<nested:write property="aliasLotNo" />
										<nested:hidden property="lotNo" styleId="<%="lotNo" + pageContext.findAttribute("index").toString() %>" />
									</td>
									<%-- 在庫量 --%>
									<td class="alignRight">
										<nested:write property="strInventoryQty" />
										<nested:hidden property="strInventoryQty" styleId="<%="strInventoryQty" + pageContext.findAttribute("index").toString() %>" />
									</td>
									<%-- 発注残 --%>
									<td class="alignRight">
										<nested:write property="strBackorderQty" />
										<nested:hidden property="strBackorderQty" styleId="<%="strBackorderQty" + pageContext.findAttribute("index").toString() %>" />
									</td>
									<%-- 検査待 --%>
									<td class="alignRight">
										<nested:write property="strInspectionQty" />
										<nested:hidden property="strInspectionQty" styleId="<%="strInspectionQty" + pageContext.findAttribute("index").toString() %>" />
									</td>
									<%-- 引当残 --%>
									<td class="alignRight">
										<nested:write property="strAssignQty" />
										<nested:hidden property="strAssignQty" styleId="<%="strAssignQty" + pageContext.findAttribute("index").toString() %>" />
									</td>
									<%-- 有効在庫 --%>
									<td class="alignRight">
										<nested:write property="strValidInventory" />
										<nested:hidden property="strValidInventory" styleId="<%="strValidInventory" + pageContext.findAttribute("index").toString() %>" />
									</td>
									<%-- 完成日 --%>
									<td class="alignRight">
										<nested:write property="strIssueDate" />
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
				<table border="0" cellpadding="2" cellspacing="2" width="710">
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