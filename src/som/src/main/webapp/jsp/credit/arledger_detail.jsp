<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taginclude.jsf"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http//www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<%@ include file="/jsp/common/head.jsf"%>
<%@ include file="/jsp/common/head_main.jsf"%>
<%@ include file="/jsp/common/clearText.jsf" %>
<%@ include file="/jsp/common/searchNameForMaster.jsf" %>

<!-- Style Sheet -->
<link href='<%= request.getContextPath() + "/stylesheets/tab.css"%>'
	media="screen" rel="Stylesheet" type="text/css" />

<%-- ▼業務固有css ここから --%>
<style type="text/css">
</style>
<%-- ▲業務固有css ここまで --%>

<%-- ▼業務固有javaScript ここから --%>
<script language="JavaScript" type="text/javascript">

	<%-- 詳細画面への遷移 --%>
	function toDetail(url) {
    	location.href=url;
	}
	<%--onload時の処理--%>
	Event.observe(window, 'load', function() {

		<%-- フォーム設定の初期化 --%>
		initializeFormState();

		<%-- 必須フィールド定義 --%>
		<%-- 日付型フィールド定義 --%>
		<%-- 数値型フィールド定義 --%>
		<%-- 検索後入力された場合の不整合をただす --%>

		
	}, false);

</script>

<%-- ▲業務固有javaScriptここまで --%>

</head>

<body>

<nested:form action="/ArLedgerDetail" method="post" onsubmit="return false;" styleId="mainForm">

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
							<td class="fcTitle fb">売掛元帳</td>
							<td>
							<table border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td><%-- メッセージ表示 --%>
										<%@ include	file="/jsp/common/disp_info_message.jsf"%>
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
					<td><!-- 明細部(ヘッダ) -->
					<table width="" cellspacing="2" cellpadding="2" border="0">
						<tr>
							<td class="bcTitleList fb fcTitleList">部署</td>
							<td colspan="9">
								<nested:write property="sectionCd"/>:<nested:write property="sectionName"/>
							</td>
						</tr>
						<tr>
							<td class="bcTitleList fb fcTitleList">請求先</td>
							<td colspan="9">
								<nested:write property="venderCd"/>:<nested:write property="venderShortedName"/>
							</td>
						</tr>
						<tr class="bcTitleList fb fcTitleList">
							<td align="right" width="80">前月繰越</td>
							<td align="right" width="80">入金額</td>
							<td align="right" width="80">その他</td>
							<td align="right" width="80">相殺</td>
							<td align="right" width="80">売上高</td>
							<td align="right" width="80">返品</td>
							<td align="right" width="80">値引</td>
							<td align="right" width="80">その他</td>
							<td align="right" width="80">消費税</td>
							<td align="right" width="80">売掛残高</td>
						</tr>
						<tr>
							<!-- 前月繰越 -->
							<td align="right">
								<nested:write property="strBalanceForward"/>
							</td>
							<!-- 入金額 -->
							<td align="right">
								<nested:write property="strDepositAmount"/>
							</td>
							<!-- その他 -->
							<td align="right">
								<nested:write property="strOtherDepositAmount"/>
							</td>
							<!-- 相殺 -->
							<td align="right">
								<nested:write property="strOffsetAmount"/>
							</td>
							<!-- 売上高 -->
							<td align="right">
								<nested:write property="strSalesAmount"/>
							</td>
							<!-- 返品 -->
							<td align="right">
								<nested:write property="strReturnedAmount"/>
							</td>
							<!-- 値引 -->
							<td align="right">
								<nested:write property="strDiscountAmount"/>
							</td>
							<!-- その他 -->
							<td align="right">
								<nested:write property="strOtherSalesAmount"/>
							</td>
							<!-- 消費税 -->
							<td align="right">
								<nested:write property="strTaxAmount"/>
							</td>
							<!-- 売掛残高 -->
							<td align="right">
								<nested:write property="strCreditAmount"/>
							</td>
						</tr>
					</table>
					<table width="" cellspacing="2" cellpadding="2" border="0">
						<tr>
							<td width="80"></td>
							<td width="80"></td>
							<td width="80"></td>
							<td width="80"></td>
							<td width="80"></td>
							<td width="80"></td>
							<td width="80"></td>
							<td class="bcTitleList fb fcTitleList" align="center" width="115">(内訳)売掛金</td>
							<td class="bcTitleList fb fcTitleList" align="center" width="115">(内訳)未収金</td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<!-- (内訳)売掛金 -->
							<td align="right">
								<nested:write property="strCreditSalesBreakdown"/>
							</td>
							<!-- (内訳)未収金 -->
							<td align="right">
								<nested:write property="strAccruedDebitBreakdown"/>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td height="10"></td>
				</tr>
				<tr>
					<td><!-- 明細部(明細) -->
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<tr>
								<td>
								<table width="" border="0">
									<tr>
										<td class="alignCenter">
										<table cellspacing="2" cellpadding="1" id="tblList">
											<tr class="bcTitleList fb fcTitleList">
												<td width="80">日付</td>
												<td width="130">分類</td>
												<td width="100">伝票番号</td>
												<td width="30" align="right">行</td>
												<td width="250">品目／摘要</td>
												<td width="80" align="right">借方<br>売上金額</td>
												<td width="80" align="right">貸方<br>入金金額</td>
												<td width="80" align="right">残高</td>
											</tr>

											<nested:notEmpty property="detailList">
												<nested:iterate id="detailList" property="detailList" indexId="index">
													<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
														<tr class="bcList1">
													</app:modulo>
						
													<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
														<tr class="bcList2">
													</app:modulo>
													
													<nested:hidden property="tranDivi"></nested:hidden>
<%--													<nested:define id="sln" property="slipNo"/>
--%>
													
													<%-- 日付 --%>
													<td>
														<nested:write property="tranDate" />
													</td>
													<%-- 分類 --%>
													<td>
														<nested:write property="categoryName" />
													</td>
													<%-- 伝票番号 --%>
													<td>
<%-- オーダー番号のリンクをコメントアウト 2009/07/15
														<nested:equal value="1" property="tranDivi">
															<html:link href="#">
																<nested:write property="slipNo" />
															</html:link>
														</nested:equal>
														<nested:equal value="2" property="tranDivi">
															<html:link href="#"
																onclick='<%=
																	"toDetail('" + request.getContextPath()
																	+ "/DepositDetail.do?op=initArLedger&creditNo="
																	+ pageContext.findAttribute("sln").toString() 
																	+ "'); return false;"
																	%>'>
																<nested:write property="slipNo" />
															</html:link>
														</nested:equal>
														<nested:equal value="4" property="tranDivi">
															<html:link href="#"
																onclick='<%=
																	"toDetail('" + request.getContextPath()
																	+ "/PaymentDetail.do?op=initArLedger&slipNo="
																	+ pageContext.findAttribute("sln").toString() 
																	+ "'); return false;"
																	%>'>
																<nested:write property="slipNo" />
															</html:link>
														</nested:equal>
														<nested:equal value="5" property="tranDivi">
															<html:link href="#"
																onclick='<%=
																	"toDetail('" + request.getContextPath()
																	+ "/OffsetDetail.do?op=initArLedger&offsetNo="
																	+ pageContext.findAttribute("sln").toString() 
																	+ "'); return false;"
																	%>'>
																<nested:write property="slipNo" />
															</html:link>
															<nested:write property="slipNo" />
														</nested:equal>
--%>
														<nested:write property="slipNo" />
													</td>
													<%-- 行番号 --%>
													<td align="right">
														<nested:write property="strRowNo" />
													</td>
													<%-- 品目／摘要 --%>
													<td>
														<nested:write property="itemName" />
													</td>
													<%-- 売上金額 --%>
													<td align="right">
														<nested:write property="strSales" />
													</td>
													<%-- 入金金額 --%>
													<td align="right">
														<nested:write property="strCredit" />
													</td>
													<%-- 残高 --%>
													<td align="right">
														<nested:write property="strBalance" />
													</td>
												</nested:iterate>
											</nested:notEmpty>
										</table>
										</td>
									</tr>
								</table>
								</td>
							</tr>
							<tr>
								<td class="alignCenter">
									<%-- ページング --%>
									<%@ include file="/jsp/common/pager/pager.jsf"%>
									<%-- ページング ここまで --%>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
					<table>
						<tr>
							<td class="alignRight">
								<div id="cssButton">
									<a href="#" onclick="return form_submit('op', 'back'); return false;" class="cssButton">
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
