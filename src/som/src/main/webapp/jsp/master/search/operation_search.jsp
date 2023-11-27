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
	function selectClose(operationCd, operationName){
		window.opener.setOperationValues(operationCd, operationName, true);

		//モーダルダイアログの終了
		window.close();
	}

	<%-- 20211209 Asclab Kouchi EdgeIE対応 --%>
	<%-- キャンセルして終了 --%>
	function selectCancel(){
		window.opener.setOperationValues("", "", false);

		//モーダルダイアログの終了
		window.close();
	}

	<%-- Load時の処理 --%>
	Event.observe(window, 'load', function() {
		<%-- フォーム設定の初期化 --%>
		initializeFormState();

		<%-- 工程名称のauto complete --%>
		new Ajax.Autocompleter(
		  "srhOperationCd",
		  "autocomplete_operation",
		  "<%= request.getContextPath() %>/OperationForAutoComplete.do?op=searchOperation",
		  {
		  	paramName : "code",
		  	parameters : 'recipeUse='+ $('srhRecipeUse').value,
		    afterUpdateElement : setOperationLabel
		  }
		);
		<%-- 名称のクリアauto complete --%>
		Event.observe('srhOperationCd', 'keypress', clearLabel.bindAsEventListener($('srhOperationName')), false);

	}, false);

	<%-- 工程名称auto completeの選択後処理 --%>
	function setOperationLabel(text, li) {
	    $("srhOperationName").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
	}


</script>
<%-- ▲業務固有javaScript ここまで --%>

<base target="_self"><%-- ←重要(subumit時に新規windowが開かないようにするおまじない) --%>
</head>

<body>

<nested:form action="/OperationSearch" method="post" onsubmit="return false;" styleId="mainForm">
	<%@ include file="/jsp/common/postjavascript.jsf" %>
	<nested:hidden property="op"/>
	<nested:hidden property="srhRecipeUse" styleId="srhRecipeUse"/>

	<table cellpadding="0" cellspacing="5" border="0">
		<tr>
			<td class="valignTop alignLeft">
				<table border="0" cellpadding="0" cellspacing="0" width="430">
					<tr>
						<td><!-- ヘッダー部 -->
							<table border="0" cellpadding="0" cellspacing="0" width="100%">
								<tr>
									<td height="5" colspan="2"></td>
								</tr>
								<tr>
									<td class="fcTitle fb" nowrap>工程検索</td>
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
												<td class="bcTitleSearch fb fcTitleSearch" width="120">工程コード</td>
												<td width="130">
													<div id="autocomplete_operation" class="autocomplete"></div>
													<nested:text property="srhOperationCd" maxlength="10" size="10" styleId="srhOperationCd"/>
												</td>
												<nested:notEmpty property="srhRecipeUse">
													<td class="bcTitleSearch fb fcTitleSearch" width="60">用途</td>
													<td>
														<nested:write property="recipeUseName" />
														<nested:hidden property="srhRecipeUse"/>
													</td>
												</nested:notEmpty>
											</tr>
											<tr>
												<td class="bcTitleSearch fb fcTitleSearch">工程名称</td>
												<td colspan="3">
													<div id="lblSrhOperationName">
														<nested:write property="srhOperationName" />
													</div>
													<nested:hidden property="srhOperationName" styleId="srhOperationName"/>
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
				<table border="0" cellpadding="0" cellspacing="0" width="430">
					<tr>
						<td height="3"></td>
					</tr>
					<tr>
						<td><!-- 明細部 -->
							<table cellspacing="2" cellpadding="2" width="100%">
								<tr class="bcTitleList fb fcTitleList">
									<td width="10"></td>
									<td width="100">工程コード</td>
									<td width="235">工程名称</td>
								</tr>
								<nested:iterate id="searchList" property="searchList" indexId="index">
								<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
									<tr class="bcList1">
								</app:modulo>
								<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
									<tr class="bcList2">
								</app:modulo>
									<td>
										<nested:define id="ocd" property="operationCd"/>
										<nested:define id="onm" property="operationName"/>
										<div id="cssButton">
											<a href="#" class="cssButton"
												onClick="<%="selectClose('"
													 + pageContext.findAttribute("ocd").toString()
													 + "', '"
													 + pageContext.findAttribute("onm").toString()
												   + "')" %>">
												選
     										</a>
										</div>
									</td>
									<td>
										<nested:write property="operationCd" />
									</td>
									<td>
										<nested:write property="operationName" />
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
				<table border="0" cellpadding="2" cellspacing="2" width="430">
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