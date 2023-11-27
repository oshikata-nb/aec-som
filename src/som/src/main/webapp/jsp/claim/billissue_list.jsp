<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taginclude.jsf"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http//www.w3.org/TR/html4/loose.dtd">
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
		defineAsDateField("srhClosingDate");

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
		  "srhSectionCd",
		  "autocomplete_section",
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
		  	paramName : "code",
		  	parameters : 'venderDivision=TS',
		    afterUpdateElement : setVenderLabel
		  }
		);

		<%-- 検索後入力された場合の不整合をただす --%>
		Event.observe('srhSectionCd', 'keypress', clearLabel.bindAsEventListener($('srhSectionName')), false);
		Event.observe('srhTantoCd', 'keypress', clearLabel.bindAsEventListener($('srhTantoNm')), false);
		Event.observe('srhVenderCd', 'keypress', clearLabel.bindAsEventListener($('srhVenderName')), false);

		<%-- 検索後入力された場合の不整合をただす --%>
		storeInitValues(/^srh.*/, 'dirtyFlg');
		refreshLabel();

	}, false);

	<%-- 部署auto completeの選択後処理 --%>
	function setOrganizationLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("srhSectionName").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
	}
	function setLoginLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("srhTantoNm").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
	}
	function setVenderLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("srhVenderName").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
	}

	<%-- 請求書発行フラグ一括チェック --%>
	function checkOn() {
		var list = $('tblList');
		if (list != null) {
			if (0 < list.rows.length) {
				for (i = 0; null != $("billIssueFlg" + i);　i++) {
					var flg = $("billIssueFlg" + i);
					flg.checked = true;
				}
			}
		}
	}

	<%-- 請求書発行フラグ一括オフ --%>
	function checkOff() {
		var list = $('tblList');
		if (list != null) {
			if (0 < list.rows.length) {
				for (i = 0; null != $("billIssueFlg" + i);　i++) {
					var flg = $("billIssueFlg" + i);
					flg.checked = false;
				}
			}
		}
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

<nested:form action="/BillIssueList" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op"/>

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
							<td class="fcTitle fb">請求書発行</td>
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
									<td colspan="2">
										<nested:text property="srhSectionCd" maxlength="10" size="15" styleId="srhSectionCd" />
										<div id="autocomplete_section" class="autocomplete"></div>
									</td>

									<td width="500">
										<div id="lblSrhSectionName">
											<nested:write property="srhSectionName" />
										</div>
										<nested:hidden property="srhSectionName" styleId="srhSectionName"/>
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">担当者</td>
									<td colspan="2">
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
									<td class="bcTitleSearch fb fcTitleSearch">請求先</td>
									<td colspan="2">
										<nested:text property="srhVenderCd" maxlength="15" size="15" styleId="srhVenderCd" />
										<div id="autocomplete_vender" class="autocomplete"></div>
									</td>

									<td width="500">
										<div id="lblSrhVenderName">
											<nested:write property="srhVenderName" />
										</div>
										<nested:hidden property="srhVenderName" styleId="srhVenderName"/>
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">請求締め日</td>
									<td colspan="3">
										<nested:text property="srhClosingDate" maxlength="10" size="15" styleId="srhClosingDate" />
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">請求番号</td>
									<td colspan="3">
										<nested:text property="srhClaimNoFrom" maxlength="10" size="15" styleId="srhClaimNoFrom" />　～
										<nested:text property="srhClaimNoTo" maxlength="10" size="15" styleId="srhClaimNoTo" />
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">請求書発行</td>
									<td colspan="3">
										<nested:checkbox property="srhBillIssueFlg" styleId="srhBillIssueFlg"/>
										未発行のみ
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">出力区分</td>
									<td colspan="3">
										<nested:checkbox property="srhDealingDivision" styleId="srhDealingDivision"/>
										取引があるもの
										<nested:checkbox property="srhBalanceDivision" styleId="srhBalanceDivision"/>
										請求残高があるもの
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">対象区分</td>
									<td colspan="3">
										<% pageContext.setAttribute("radioNormalTemp",	new com.asahikaseieng.app.radiobuttons.RadioNormalTemp());	%>
										<logic:iterate id="mp" name="radioNormalTemp" property="map">
											<nested:radio idName="mp" property="srhNormalTemp" styleId="srhNormalTemp" value="key" />
											<bean:write name="mp" property="value" />
										</logic:iterate>
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
						<table width="" border="0">
							<tr>
								<td class="alignLeft">
								<table cellspacing="1" cellpadding="1" border="0">
									<tr>
										<td>
											<div id="cssButton">
												<a href="#" onclick="checkOn(); return false;" class="cssButton">一括チェック</a>
											</div>
										</td>
										<td>
											<div id="cssButton">
												<a href="#" onclick="checkOff(); return false;" class="cssButton">一括クリア</a>
											</div>
										</td>
									</tr>
								</table>
								</td>
								<td class="alignRight">
								<table cellspacing="1" cellpadding="1" border="0">
									<tr>
										<td>
											<div id="cssButton">
												<a href="#" onclick="return form_submit('op', 'report'); return false;"	class="cssButton">
													請求書
												</a>
											</div>
										</td>
										<td>
											<div id="cssButton">
												<a href="#" onclick="if (!(reportConfirm())) {return false;}else{return form_submit('op', 'reportList'); return false;}" class="cssButton">
													帳票(EXCEL)
												</a>
											</div>
										</td>
									</tr>
								</table>
								</td>
							</tr>
							<tr>
								<td class="alignCenter" colspan="2">
								<table cellspacing="2" cellpadding="1" id="tblList">
									<tr class="bcTitleList fb fcTitleList">
										<td>請求先</td>
										<td align="right">請求<br>締め日</td>
										<td>請求番号</td>
										<td align="right">前回<br>請求額</td>
										<td align="right">入金・<br>その他計</td>
										<td align="right">今回<br>請求額</td>
										<td align="right">その他計</td>
										<td align="right">消費税</td>
										<td align="right">請求合計</td>
									</tr>
									<nested:iterate id="searchList" property="searchList" indexId="index">
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
											<tr class="bcList1">
										</app:modulo>
			
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
											<tr class="bcList2">
										</app:modulo>
			
										<%-- 請求先 --%>
										<td width="175">
											<nested:checkbox property="billIssueFlg" styleId='<%= "billIssueFlg" + index %>'/>
											<nested:write property="venderCd"/>:<nested:write property="venderShortedName"/>
										</td>
										<%-- 締め日 --%>
										<td width="65" align="right">
											<nested:write property="creditDate"/>
										</td>
										<%-- 請求番号 --%>
										<td width="70">
											<nested:write property="claimNo"/>
										</td>
										<%-- 前回請求額 --%>
										<td width="70" align="right">
											<nested:write property="strClaimAmountForward"/>
										</td>
										<%-- 入金・その他計 --%>
										<td width="70" align="right">
											<nested:write property="strOtherCreditAmount"/>
										</td>
										<%-- 今回売上額 --%>
										<td width="70" align="right">
											<nested:write property="strSalesAmount"/>
										</td>
										<%-- その他計 --%>
										<td width="70" align="right">
											<nested:write property="strOtherSales"/>
										</td>
										<%-- 消費税 --%>
										<td width="48" align="right">
											<nested:write property="strTaxAmount"/>
										</td>
										<%-- 請求合計 --%>
										<td width="70" align="right">
											<nested:write property="strClaimAmount"/>
										</td>
									</nested:iterate>
								</table>
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
		<%-- Excelダウンロード処理 ここまで --%>
	</table>
</nested:form>
</body>
</html:html>
