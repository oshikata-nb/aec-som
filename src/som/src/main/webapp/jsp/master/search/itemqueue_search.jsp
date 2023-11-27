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
	function selectClose(itemCd, itemName, itemUnit, unitDiv, smallnumLength, roundDivision){
		window.opener.setItemQueueValues(itemCd, itemName, itemUnit, unitDiv, smallnumLength, roundDivision, true);
		//モーダルダイアログの終了
		window.close();
	}

	<%-- 20211209 Asclab Kouchi EdgeIE対応 --%>
	<%-- キャンセルして終了 --%>
	function selectCancel(){
		window.opener.setItemQueueValues("", "", "", "", "", "", false);
		//モーダルダイアログの終了
		window.close();
	}

	<%-- Load時の処理 --%>
	Event.observe(window, 'load', function() {
		<%-- フォーム設定の初期化 --%>
		initializeFormState();

		<%-- 品目名称のauto complete --%>
		new Ajax.Autocompleter(
		  "srhItemCd",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/ItemQueueForAutoComplete.do?op=searchDetailItemQueue",
		  {
		  	paramName : "code",
		    afterUpdateElement : setItemQueueLabel
		  }
		);

		<%-- 他社コードのautocomplete --%>
		new Ajax.Autocompleter(
			"srhOtherCompanyCd1",
			"autocomplete_selection",
			"<%= request.getContextPath() %>/ItemQueueForAutoComplete.do?op=searchDetailItemQueueOtherCompany",
			{
				paramName : "code",
				afterUpdateElement : setOtherCompanyLabel
			}
		);

		<%-- 名称のクリアauto complete --%>
		Event.observe('srhItemCd', 'keypress', clearLabel.bindAsEventListener($('srhItemName')), false);
		Event.observe('srhItemCd', 'keypress', clearText.bindAsEventListener($('srhOtherCompanyCd1')), false);
		Event.observe('srhOtherCompanyCd1', 'keypress', clearLabel.bindAsEventListener($('srhItemName')), false);

	}, false);

	<%-- 品目名称auto completeの選択後処理 --%>
	function setItemQueueLabel(text, li) {
	    $("srhItemName").value = li.getElementsByTagName('span')[0].innerText;
	    $("srhOtherCompanyCd1").value = getHiddenValue(li,"otherCompanyCd1");
		refreshLabel();
	}

	function setOtherCompanyLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("srhItemName").value = li.getElementsByTagName('span')[0].innerText;
		$("srhItemCd").value = getHiddenValue(li,"code");
		refreshLabel();
	}

</script>
<%-- ▲業務固有javaScript ここまで --%>
<base target="_self"><%-- ←重要(subumit時に新規windowが開かないようにするおまじない) --%>
</head>
<body>
<nested:form action="/ItemQueueSearch" method="post" onsubmit="return false;" styleId="mainForm">
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
									<td class="fcTitle fb" nowrap>品目検索</td>
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
													<nested:text property="srhItemCd" maxlength="20" size="20" styleId="srhItemCd"/>
													<div id="autocomplete_selection" class="autocomplete"></div>
												</td>
											</tr>
											<tr>
												<td class="bcTitleSearch fb fcTitleSearch">品目名称</td>
												<td>
													<div id="lblSrhItemName">
														<nested:write property="srhItemName" />
													</div>
													<nested:hidden property="srhItemName" styleId="srhItemName"/>
												</td>
											</tr>
											<tr>
												<td class="bcTitleSearch fb fcTitleSearch">他社コード</td>
												<td>
													<nested:text property="srhOtherCompanyCd1" maxlength="20" size="20" styleId="srhOtherCompanyCd1"/>
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
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td height="3"></td>
					</tr>
					<tr>
						<td><!-- 明細部 -->
							<table cellspacing="2" cellpadding="2" width="100%">
								<tr class="bcTitleList fb fcTitleList">
									<td width="10"></td>
									<td width="100">品目コード</td>
									<td width="200">品目名称</td>
									<td width="100">他社コード</td>
								</tr>
								<nested:iterate id="searchList" property="searchList" indexId="index">
									<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
										<tr class="bcList1">
									</app:modulo>
									<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
										<tr class="bcList2">
									</app:modulo>
									<td>
  										<nested:define id="icd" property="itemCd"/>
  										<nested:define id="inm" property="itemName"/>
  										<nested:define id="unit" property="itemUnit"/>
  										<nested:define id="unitDiv" property="unitDiv"/>
  										<nested:define id="samllLen" property="smallnumLength"/>
  										<nested:define id="roundDiv" property="roundDivision"/>
										<div id="cssButton">
											<a href="#" class="cssButton"
												onClick="<%="selectClose('"
													   + pageContext.findAttribute("icd").toString()
													   + "', '"
													   + pageContext.findAttribute("inm").toString()
													   + "', '"
													   + pageContext.findAttribute("unit").toString()
													   + "', '"
													   + pageContext.findAttribute("unitDiv").toString()
													   + "', '"
													   + pageContext.findAttribute("samllLen").toString()
													   + "', '"
													   + pageContext.findAttribute("roundDiv").toString()
													   + "', '"
												   + "')" %>">
												選
											</a>
										</div>
									</td>
									<td>
										<nested:write property="itemCd" />
									</td>
									<td>
										<nested:write property="itemName" />
									</td>
									<td>
										<nested:write property="otherCompanyCd1" />
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