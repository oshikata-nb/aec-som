<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taginclude.jsf"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http//www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.asahikaseieng.app.comboboxes.SelectOutputDivision"%>
<html:html>
<head>
<%@ include file="/jsp/common/head.jsf"%>
<%@ include file="/jsp/common/head_main.jsf"%>
<%@ include file="/jsp/common/clearText.jsf" %>
<%@ include file="/jsp/common/searchNameForMaster.jsf" %>

<%-- ▼業務固有css ここから
<style type="text/css">
</style>
<%-- ▲業務固有css ここまで --%>

<%-- ▼業務固有javaScript ここから --%>
<script language="JavaScript" type="text/javascript">

	<%--onload時の処理--%>
	Event.observe(window, 'load', function() {

		<%-- フォーム設定の初期化 --%>
		initializeFormState();

		<%-- 必須フィールド定義 --%>

		<%-- 日付型フィールド定義 --%>
		defineAsDateField("srhCreditScheduledDateFrom");
		defineAsDateField("srhCreditScheduledDateTo");
		defineAsDateField("srhCreditDateFrom");
		defineAsDateField("srhCreditDateTo");

		<%-- 明細部 --%>
		var tblList = $('tblList');
		if (tblList != null) {
			<%-- 数値型フィールド定義 --%>
			<%-- 必須フィールド定義 --%>
			<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
			<%-- 対象外の例：検索条件部と明細のチェックボックス --%>
		}

		<%-- 部署名称のauto complete --%>
		new Ajax.Autocompleter(
			"srhOrganizationCd",
			"autocomplete_organization",
			"<%= request.getContextPath() %>/OrganizationForAutoComplete.do?op=searchOrganization",
			{
				paramName : "code",
				afterUpdateElement : setOrganizationLabel
			}
		);

		<%-- 担当者名称のauto complete --%>
		new Ajax.Autocompleter(
			"srhTantoCd",
			"autocomplete_tanto",
			"<%= request.getContextPath() %>/LoginForAutoComplete.do?op=searchLogin",
			{
				paramName : "code",
				afterUpdateElement : setLoginLabel
			}
		);

		<%-- 請求先名称のauto complete --%>
		new Ajax.Autocompleter(
			"srhVenderCd",
			"autocomplete_vender",
			"<%= request.getContextPath() %>/VenderForAutoComplete.do?op=searchVender",
			{
			    autoParams: ['venderDivision'],
				paramName : "code",
				afterUpdateElement : setVenderLabel
			}
		);

		<%-- 検索後入力された場合の不整合をただす --%>
		Event.observe('srhOrganizationCd', 'keypress', clearLabel.bindAsEventListener($('srhOrganizationName')), false);
		Event.observe('srhTantoCd', 'keypress', clearLabel.bindAsEventListener($('srhTantoNm')), false);
		Event.observe('srhVenderCd', 'keypress', clearLabel.bindAsEventListener($('srhVenderName1')), false);

<!--		<%-- 検索後入力された場合の不整合をただす --%>-->
		storeInitValues(/^srh.*/, 'dirtyFlg');
		refreshLabel();

	}, false);

	<%-- ajax --%>
	function setOrganizationLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("srhOrganizationName").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
	}

	function setLoginLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("srhTantoNm").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
	}

	function setVenderLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("srhVenderName1").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
	}

	<%-- 確認メッセージ --%>
	function reportConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "帳票(EXCEL)出力してもよろしいですか？";

		return confirm(alertMsg[0]);
	}
</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/DepositPlanList" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="venderDivision" styleId="venderDivision"/>

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
							<td class="fcTitle fb">入金予定一覧</td>
							<td>
							<table border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td>
										<%-- メッセージ表示 --%>
										<%@ include file="/jsp/common/disp_info_message.jsf"%>
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
						<%@ include file="/jsp/common/disp_error_message.jsf"%>
						<%-- メッセージ表示 ここまで --%>
					</td>
				</tr>

				<tr>
					<td><!-- 検索部 -->
					<table border="0" cellspacing="" cellpadding="" width="100%">
						<tr>
							<td>
							<table border="0" cellspacing="2" cellpadding="1">
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch" width="130">部署</td>
									<td>
										<nested:text property="srhOrganizationCd" maxlength="10" size="15" styleId="srhOrganizationCd" />
										<div id="autocomplete_organization" class="autocomplete"></div>
									</td>

									<td width="500">
										<div id="lblSrhOrganizationName">
											<nested:write property="srhOrganizationName" />
										</div>
										<nested:hidden property="srhOrganizationName" styleId="srhOrganizationName"/>
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">担当者</td>
									<td>
										<nested:text property="srhTantoCd" maxlength="10" size="15" styleId="srhTantoCd" />
										<div id="autocomplete_tanto" class="autocomplete"></div>
									</td>

									<td width="500">
										<div id="lblSrhTantoNm">
											<nested:write property="srhTantoNm" />
										</div>
										<nested:hidden property="srhTantoNm" styleId="srhTantoNm"/>
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">出力区分</td>
									<td colspan="2">
										<% pageContext.setAttribute("SelectOutputDivision", new SelectOutputDivision(false, false)); %>
										<nested:select property="srhOutputDivision">
											<nested:options name="SelectOutputDivision" property="values" labelName="SelectOutputDivision" labelProperty="labels" />
										</nested:select>
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">請求先</td>
									<td>
										<nested:text property="srhVenderCd" maxlength="15" size="15" styleId="srhVenderCd"/>
										<div id="autocomplete_vender" class="autocomplete"></div>
									</td>

									<td width="500">
										<div id="lblSrhVenderName1">
											<nested:write property="srhVenderName1" />
										</div>
										<nested:hidden property="srhVenderName1" styleId="srhVenderName1"/>
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">請求日付</td>
									<td colspan="2">
										<nested:text property="srhCreditDateFrom" maxlength="8" size="15" style="ime-mode:disabled" styleId="srhCreditDateFrom"/>　～

										<nested:text property="srhCreditDateTo" maxlength="8" size="15" style="ime-mode:disabled" styleId="srhCreditDateTo"/>
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">入金予定日</td>
									<td colspan="2">
										<nested:text property="srhCreditScheduledDateFrom" maxlength="8" size="15" style="ime-mode:disabled" styleId="srhCreditScheduledDateFrom"/>　～

										<nested:text property="srhCreditScheduledDateTo" maxlength="8" size="15" style="ime-mode:disabled" styleId="srhCreditScheduledDateTo"/>
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">入金分類</td>
									<td colspan="2">
										<nested:select property="srhCreditDivision">
											<nested:optionsCollection name="depositPlanListForm" property="srhCreditList" value="values" label="labales"/>
										</nested:select>
									</td>
									<td>
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
						<table width="100%" border="0">
							<tr>
								<td class="alignRight">
								<table cellspacing="1" cellpadding="1" border="0">
									<tr>
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
								<td class="alignCenter">
								<nested:equal property="srhOutputDivision" value="1">
									<table width="100%" cellspacing="2" cellpadding="1" id="tblList">
										<tr class="bcTitleList fb fcTitleList">
											<td width="80" align="right">入金予定日</td>
											<td>請求先</td>
											<td align="right">請求額</td>
											<td width="60">入金分類</td>
											<td>銀行</td>
											<td>預金種別</td>
											<td>口座番号</td>
										</tr>
										<nested:iterate id="searchList" property="searchList" indexId="index">
											<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
												<tr class="bcList1">
											</app:modulo>
				
											<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
												<tr class="bcList2">
											</app:modulo>
				
											<%-- 入金予定日 --%>
											<td align="right">
												<nested:write property="creditScheduledDate"/>
											</td>
											<%-- 請求先 --%>
											<td>
												<nested:write property="venderCd"/>:<nested:write property="venderShortedName"/>
											</td>
											<%-- 請求額 --%>
											<td width="70" align="right">
												<nested:write property="strClaimAmountBalance"/>
											</td>
											<%-- 入金分類 --%>
											<td>
												<nested:write property="categoryName"/>
											</td>
											<%-- 銀行 --%>
											<td>
												<nested:notEmpty property="bankMasterCd">
													<nested:write property="bankMasterCd"/>:<nested:write property="bankMasterName"/>
												</nested:notEmpty>
											</td>
											<%-- 預金種別 --%>
											<td width="65">
												<nested:write property="accountDivision"/>
											</td>
											<%-- 口座番号 --%>
											<td width="65">
												<nested:write property="accountNo"/>
											</td>
										</nested:iterate>
									</table>
								</nested:equal>
								<nested:equal property="srhOutputDivision" value="2">
									<table width="100%" cellspacing="2" cellpadding="1" id="tblList">
										<tr class="bcTitleList fb fcTitleList">
											<td>請求先</td>
											<td width="80" align="right">入金予定日</td>
											<td align="right">請求額</td>
											<td width="60">入金分類</td>
											<td>銀行</td>
											<td>預金種別</td>
											<td>口座番号</td>
										</tr>
										<nested:iterate id="searchList" property="searchList" indexId="index">
											<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
												<tr class="bcList1">
											</app:modulo>
				
											<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
												<tr class="bcList2">
											</app:modulo>
				
											<%-- 請求先 --%>
											<td>
												<nested:write property="venderCd"/>:<nested:write property="venderShortedName"/>
											</td>
											<%-- 入金予定日 --%>
											<td align="right">
												<nested:write property="creditScheduledDate"/>
											</td>
											<%-- 請求額 --%>
											<td width="70" align="right">
												<nested:write property="strClaimAmountBalance"/>
											</td>
											<%-- 入金分類 --%>
											<td>
												<nested:write property="categoryName"/>
											</td>
											<%-- 銀行 --%>
											<td>
												<nested:notEmpty property="bankMasterCd">
													<nested:write property="bankMasterCd"/>:<nested:write property="bankMasterName"/>
												</nested:notEmpty>
											</td>
											<%-- 預金種別 --%>
											<td width="65">
												<nested:write property="accountDivision"/>
											</td>
											<%-- 口座番号 --%>
											<td width="65">
												<nested:write property="accountNo"/>
											</td>
										</nested:iterate>
									</table>
								</nested:equal>
								<nested:equal property="srhOutputDivision" value="3">
									<table width="100%" cellspacing="2" cellpadding="1" id="tblList">
										<tr class="bcTitleList fb fcTitleList">
											<td width="60">入金分類</td>
											<td width="80" align="right">入金予定日</td>
											<td>請求先</td>
											<td align="right">請求額</td>
											<td>銀行</td>
											<td>預金種別</td>
											<td>口座番号</td>
										</tr>
										<nested:iterate id="searchList" property="searchList" indexId="index">
											<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
												<tr class="bcList1">
											</app:modulo>
				
											<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
												<tr class="bcList2">
											</app:modulo>
				
											<%-- 入金分類 --%>
											<td>
												<nested:write property="categoryName"/>
											</td>
											<%-- 入金予定日 --%>
											<td align="right">
												<nested:write property="creditScheduledDate"/>
											</td>
											<%-- 請求先 --%>
											<td>
												<nested:write property="venderCd"/>:<nested:write property="venderShortedName"/>
											</td>
											<%-- 請求額 --%>
											<td width="70" align="right">
												<nested:write property="strClaimAmountBalance"/>
											</td>
											<%-- 銀行 --%>
											<td>
												<nested:notEmpty property="bankMasterCd">
													<nested:write property="bankMasterCd"/>:<nested:write property="bankMasterName"/>
												</nested:notEmpty>
											</td>
											<%-- 預金種別 --%>
											<td width="65">
												<nested:write property="accountDivision"/>
											</td>
											<%-- 口座番号 --%>
											<td width="65">
												<nested:write property="accountNo"/>
											</td>
										</nested:iterate>
									</table>
								</nested:equal>
								<nested:equal property="srhOutputDivision" value="4">
									<table width="100%" cellspacing="2" cellpadding="1" id="tblList">
										<tr class="bcTitleList fb fcTitleList">
											<td>銀行</td>
											<td width="80" align="right">入金予定日</td>
											<td>請求先</td>
											<td align="right">請求額</td>
											<td width="60">入金分類</td>
											<td>預金種別</td>
											<td>口座番号</td>
										</tr>
										<nested:iterate id="searchList" property="searchList" indexId="index">
											<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
												<tr class="bcList1">
											</app:modulo>
				
											<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
												<tr class="bcList2">
											</app:modulo>
				
											<%-- 銀行 --%>
											<td>
												<nested:notEmpty property="bankMasterCd">
													<nested:write property="bankMasterCd"/>:<nested:write property="bankMasterName"/>
												</nested:notEmpty>
											</td>
											<%-- 入金予定日 --%>
											<td align="right">
												<nested:write property="creditScheduledDate"/>
											</td>
											<%-- 請求先 --%>
											<td>
												<nested:write property="venderCd"/>:<nested:write property="venderShortedName"/>
											</td>
											<%-- 請求額 --%>
											<td width="70" align="right">
												<nested:write property="strClaimAmountBalance"/>
											</td>
											<%-- 入金分類 --%>
											<td>
												<nested:write property="categoryName"/>
											</td>
											<%-- 預金種別 --%>
											<td width="65">
												<nested:write property="accountDivision"/>
											</td>
											<%-- 口座番号 --%>
											<td width="65">
												<nested:write property="accountNo"/>
											</td>
										</nested:iterate>
									</table>
								</nested:equal>
								</td>
							</tr>
						</table>
						</td>
					<tr>
						<td class="alignCenter">
							<%-- ページング --%>
							<%@ include file="/jsp/common/pager/pager.jsf"%>
							<%-- ページング ここまで --%>
						</td>
					</tr>
				</table>
			</nested:notEmpty>

		<%-- Excelダウンロード処理 --%>
		<nested:equal property="excelDownloadFlg" value="true">
			<META HTTP-EQUIV="REFRESH" CONTENT="0; URL=<%= request.getContextPath() %>/FileDownload/">
		</nested:equal>
	</table>
</nested:form>
</body>
</html:html>
