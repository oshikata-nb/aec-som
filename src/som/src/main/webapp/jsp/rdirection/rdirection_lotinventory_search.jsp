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
	
	<%-- 画面を閉じる --%>
	function closeWindow(){
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
<nested:form action="/RdirectionLotInventorySearch" method="post" onsubmit="return false;" styleId="mainForm">
	<%@ include file="/jsp/common/postjavascript.jsf" %>
	<nested:hidden property="op"/>
	<table cellpadding="0" cellspacing="5" border="0" Width="695">
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
									<td class="fcTitle fb" nowrap>ロット検索</td>
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
												<td class="bcTitleSearch fb fcTitleSearch" width="90">品目コード</td>
												<td width="90">
													<nested:write property="srhItemCd" />
												</td>
											</tr>
											<tr> 
												<td class="bcTitleSearch fb fcTitleSearch">品目名称</td>
												<td>
													<nested:write property="srhItemName" />
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
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td height="3"></td>
					</tr>
					<tr>
						<td><!-- 明細部 -->
							<table cellspacing="2" cellpadding="2" width="100%">
								<tr class="bcTitleList fb fcTitleList">
									<td width="100">ロケーション</td>
									<td>名称</td>
									<td width="100">メーカーロット</td>
									<td width="100">入荷ロット</td>
									<td>荷姿</td>
									<td class="alignRight">荷姿数</td>
									<td class="alignRight">端数</td>
									<td class="alignRight">在庫量</td>
									<td>単位</td>
								</tr>
								<nested:iterate id="searchList" property="searchList" indexId="index">
									<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
										<tr class="bcList1">
									</app:modulo>
									<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
										<tr class="bcList2">
									</app:modulo>
									<%-- ロケーション --%>
									<td>
										<nested:write property="locationCd" />
									</td>
									<%-- 名称 --%>
									<td>
										<nested:write property="locationName" />
									</td>
									<%-- メーカーロット --%>
									<td>
										<nested:write property="aliasLotNo" />
									</td>
									<%-- 入荷ロット --%>
									<td>
										<nested:write property="lotNo" />
									</td>
									<%-- 荷姿 --%>
									<td>
										<nested:write property="styleOfPacking" />
									</td>
									<%-- 荷姿数 --%>
									<td class="alignRight">
										<nested:write property="strInoutQty" />
									</td>
									<%-- 端数 --%>
									<td class="alignRight">
										<nested:write property="strFraction" />
									</td>
									<%-- 在庫量 --%>
									<td class="alignRight">
										<nested:write property="strInventoryQty" />
									</td>
									<%-- 単位 --%>
									<td>
										<nested:write property="unitName" />
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
								<a href="#" onClick="<%="closeWindow()" %>" class="cssButton">
									&nbsp;戻&nbsp;&nbsp;る&nbsp;
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