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
		window.opener.setSalesInoutValues(true
			, $F("inoutNo" + index)
			, $F("lotNo" + index)
			, $F("strInoutQty" + index)
			, $F("strInoutWeight" + index)
			, $F("inoutDate" + index)
			, $F("ryName" + index)
			, $F("inputorName" + index)
		);

		//モーダルダイアログの終了
		window.close();
	}

	<%-- 20211209 Asclab Kouchi EdgeIE対応 --%>
	<%-- キャンセルして終了 --%>
	function selectCancel(){
		window.opener.setSalesInoutValues(false, "", "", "", "", "", "", "");

		//モーダルダイアログの終了
		window.close();
	}

	<%-- Load時の処理 --%>
	Event.observe(window, 'load', function() {
		<%-- フォーム設定の初期化 --%>
		initializeFormState();



	}, false);

</script>
<%-- ▲業務固有javaScript ここまで --%>

<base target="_self"><%-- ←重要(subumit時に新規windowが開かないようにするおまじない) --%>
</head>

<body>

<nested:form action="/SalesInoutSearch" method="post" onsubmit="return false;" styleId="mainForm">
	<nested:hidden property="srhSalesDate" styleId="srhSalesDate" />
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
									<td class="fcTitle fb" nowrap>受払選択</td>
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
												<td class="bcTitleSearch fb fcTitleSearch" width="100">品目コード</td>
												<td width="120">
													<nested:write property="srhItemCd" />
												</td>
												<td class="bcTitleSearch fb fcTitleSearch "width="100">品目名称</td>
												<td width="350">
													<nested:write property="srhItemName" />
												</td>
											</tr>
											<tr>
												<td class="bcTitleSearch fb fcTitleSearch" width="100">他社コード</td>
												<td width="120">
													<nested:write property="srhOtherCompanyCd1" />
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
						<td height="3"></td>
					</tr>
					<tr>
						<td><%-- 明細部 --%>
							<table cellspacing="2" cellpadding="2" width="100%">
								<tr class="bcTitleList fb fcTitleList">
									<td width="20"></td>
									<td width="70">ロット番号</td>
									<td width="70">数量</td>
									<td width="70">重量</td>
									<td width="70">受払日付</td>
									<td width="200">理由</td>
									<td width="100">登録者</td>
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
									<%-- ロットNo --%>
									<td width="60">
										<nested:write property="lotNo" />
										<nested:hidden property="lotNo" styleId="<%="lotNo" + pageContext.findAttribute("index").toString() %>" />
									</td>
									<%-- 数量 --%>
									<td width="100" align="right">
										<nested:write property="strInoutQty" />
										<nested:hidden property="strInoutQty" styleId="<%="strInoutQty" + pageContext.findAttribute("index").toString() %>" />
									</td>
									<%-- 重量 --%>
									<td width="100" align="right">
										<nested:write property="strInoutWeight" />
										<nested:hidden property="strInoutWeight" styleId="<%="strInoutWeight" + pageContext.findAttribute("index").toString() %>" />
									</td>
									<%-- 受払日付 --%>
									<td width="60">
										<nested:write property="inoutDate" />
										<nested:hidden property="inoutDate" styleId="<%="inoutDate" + pageContext.findAttribute("index").toString() %>" />
									</td>
									<%-- 理由 --%>
									<td width="200">
										<nested:write property="ryName" />
										<nested:hidden property="ryName" styleId="<%="ryName" + pageContext.findAttribute("index").toString() %>" />
									</td>
									<%-- 登録者 --%>
									<td width="100">
										<nested:write property="inputorName" />
										<nested:hidden property="inputorName" styleId="<%="inputorName" + pageContext.findAttribute("index").toString() %>" />
									</td>
									<%-- 受払番号 --%>
									<nested:hidden property="inoutNo" styleId="<%="inoutNo" + pageContext.findAttribute("index").toString() %>"/>
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