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
		<%-- 組織のauto complete --%>	    
		<%-- 検索後入力された場合の不整合をただす --%>

		storeInitValues(/^srh.*/, 'dirtyFlg');
		refreshLabel();

	}, false);

</script>

<%-- ▲業務固有javaScriptここまで --%>

</head>

<body>

<nested:form action="/ApLedgerDetail" method="post" onsubmit="return false;" styleId="mainForm">

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
							<td class="fcTitle fb">買掛元帳</td>
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
					<td><!-- 明細部(ヘッダ) -->
					<table width="" cellspacing="2" cellpadding="2" border="0">
						<tr>
							<td class="bcTitleList fb fcTitleList">部署</td>
							<td colspan="9">
								<nested:write property="organizationCd"/>:<nested:write property="organizationName"/>
							</td>
						</tr>
						<tr>
							<td class="bcTitleList fb fcTitleList">支払先</td>
							<td colspan="9">
								<nested:write property="venderCd"/>:<nested:write property="venderShortedName"/>
							</td>
						</tr>
						<tr class="bcTitleList fb fcTitleList">
							<td align="right" width="80">前月繰越</td>
							<td align="right" width="80">支払額</td>
							<td align="right" width="80">その他</td>
							<td align="right" width="80">仕入高</td>
							<td align="right" width="80">返品</td>
							<td align="right" width="80">値引</td>
							<td align="right" width="80">その他</td>
							<td align="right" width="80">相殺</td>
							<td align="right" width="80">消費税</td>
							<td align="right" width="80">買掛残高</td>
						</tr>
						<tr>
							<!-- 前月繰越 -->
							<td align="right">
								<nested:write property="strBalanceForward"/>
							</td>
							<!-- 支払額 -->
							<td align="right">
								<nested:write property="strPaymentAmount"/>
							</td>
							<!-- その他 -->
							<td align="right">
								<nested:write property="strOtherPaymentAmount"/>
							</td>
							<!-- 仕入高 -->
							<td align="right">
								<nested:write property="strStockingAmount"/>
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
								<nested:write property="strOtherStockingAmount"/>
							</td>
							<!-- 相殺 -->
							<td align="right">
								<nested:write property="strOffsetAmount"/>
							</td>
							<!-- 消費税 -->
							<td align="right">
								<nested:write property="strTaxAmount"/>
							</td>
							<!-- 買掛残高 -->
							<td align="right">
								<nested:write property="strPayableAmount"/>
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
							<td class="bcTitleList fb fcTitleList" align="center" width="115">(内訳)買掛金</td>
							<td class="bcTitleList fb fcTitleList" align="center" width="115">(内訳)未払金</td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<!-- (内訳)買掛金 -->
							<td align="right">
								<nested:write property="strAccountPayableBreakdown"/>
							</td>
							<!-- (内訳)未払金 -->
							<td align="right">
								<nested:write property="strArrearageBreakdown"/>
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
												<td width="75">日付</td>
												<td width="130">分類</td>
												<td width="80">伝票番号</td>
												<td width="20" align="right">行</td>
												<td width="250">品目／摘要</td>
												<td width="82" align="right">借方<br>支払金額</td>
												<td width="80" align="right">貸方<br>仕入金額</td>
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
													<nested:define id="sln" property="slipNo"/>
													
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
														<%-- 仕入データ --%>
														<nested:equal value="3" property="tranDivi">
<%--															<html:link href="#"
															<html:link href="#">
																<nested:write property="slipNo" />
															</html:link>
--%>
															<nested:write property="slipNo" />
														</nested:equal>
														<%-- 支払データ --%>
														<nested:equal value="4" property="tranDivi">
<%--															<html:link href="#"
																onclick='<%=
																	"toDetail('" + request.getContextPath()
																	+ "/PaymentCsmDetail.do?op=initApLedger&slipNo="
																	+ pageContext.findAttribute("sln").toString() 
																	+ "'); return false;"
																	%>'>
																<nested:write property="slipNo" />
															</html:link>
--%>
															<nested:write property="slipNo" />
														</nested:equal>
														<%-- 入金データ --%>
														<nested:equal value="2" property="tranDivi">
<%--															<html:link href="#"
																onclick='<%=
																	"toDetail('" + request.getContextPath()
																	+ "/DepositDetail.do?op=initApLedger&creditNo="
																	+ pageContext.findAttribute("sln").toString() 
																	+ "'); return false;"
																	%>'>
																<nested:write property="slipNo" />
															</html:link>
--%>
															<nested:write property="slipNo" />
														</nested:equal>
														<%-- 相殺データ --%>
														<nested:equal value="5" property="tranDivi">
<%--															<html:link href="#"
																onclick='<%=
																	"toDetail('" + request.getContextPath()
																	+ "/OffsetDetail.do?op=initApLedger&offsetNo="
																	+ pageContext.findAttribute("sln").toString() 
																	+ "'); return false;"
																	%>'>
																<nested:write property="slipNo" />
															</html:link>
--%>
															<nested:write property="slipNo" />
														</nested:equal>
													</td>
													<%-- 行番号 --%>
													<td align="right">
														<nested:notEqual property="strRowNo" value="0">
															<nested:write property="strRowNo" />
														</nested:notEqual>
													</td>
													<%-- 品目／摘要 --%>
													<td>
														<nested:write property="itemName" />
													</td>
													<%-- 支払金額 --%>
													<td align="right">
														<nested:write property="strPayment" />
													</td>
													<%-- 仕入金額 --%>
													<td align="right">
														<nested:write property="strStocking" />
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
