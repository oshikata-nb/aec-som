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
		<logic:equal name="balanceListForm" property="searchList" value="false">
			initializeFormState();
		</logic:equal>

		<%-- 必須フィールド定義 --%>
		defineAsRequiredField("srhTargetMonth");

		<%-- 日付型フィールド定義 --%>
		defineAsYMField("srhTargetMonth");

		<%-- 明細部 --%>
		var tblList = $('tblList');
		if (tblList != null) {
			<%-- 数値型フィールド定義 --%>
			<%-- 必須フィールド定義 --%>
			<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
			<%-- 対象外の例：検索条件部と明細のチェックボックス --%>
		}

		<%-- 部門名称のauto complete --%>
		new Ajax.Autocompleter(
		  "srhSectionCd",
		  "autocomplete_section",
		  "<%= request.getContextPath() %>/AutoComplete.do?op=autoSrhSectionCd",
		  {
		    afterUpdateElement : setSrhSectionCdLabel
		  }
	    );

		<%-- 担当者名称のauto complete --%>
		new Ajax.Autocompleter(
		  "srhTantoCd",
		  "autocomplete_tanto",
		  "<%= request.getContextPath() %>/AutoComplete.do?op=autoLogin",
		  {
		    afterUpdateElement : setTantoNmLabel
		  }
	    );

		<%-- 請求先名称のauto complete --%>
		new Ajax.Autocompleter(
		  "srhVenderCd",
		  "autocomplete_vender",
		  "<%= request.getContextPath() %>/AutoComplete.do?op=autoClaimer",
		  {
		    afterUpdateElement : setVenderLabel1
		  }
	    );

		<%-- 検索後入力された場合の不整合をただす --%>
		storeInitValues(/^srh.*/, 'dirtyFlg');
		refreshLabel();

	}, false);

	<%-- 日付型フィールド定義 --%>
	var dateElements = new Array();
	/* 日付型 ON_FOCUSの動作を設定 */
	function dateFocusListener() {
		this.value = unformatDate(this.value);
		Field.select(this);
	}
	
	/* 日付型 OFF_FOCUSの動作を設定 */
	function dateBlurListener() {
		this.value = formatDate(this.value);
	}
	
	function defineAsYMField(id) {
	
		var o = $(id);
		Event.observe(o, 'focus', dateFocusListener.bind(o), false);
		Event.observe(o, 'blur', dateBlurListener.bind(o), false);
	
		if (o.style.imeMode) {
			o.style.imeMode = 'disabled';
		}
		o.style.textAlign = 'left';
		
		dateElements[o.id] = o;
	}
	function formatDate(org) {
	
		if (org == null) {
			return null;
		}
	
		if(!org.match(/^(-?)[0-9]{4,6}$/)){
			return org;
		}
	
		var tmp = org;
		
		if (tmp.length == 4) {
			tmp = "20" + tmp;
		}
	
		if (tmp.length == 6) {
	
			var year  = parseInt(tmp.substring(0, 4), 10);
			var month = parseInt(tmp.substring(4, 6), 10)-1;
			var day   = parseInt('01', 10);
	
			if (isNaN(year) || isNaN(month)) {
				return org;
			}
				
			var d = new Date(year, month, day);
	
			if (month != d.getMonth()) {
				return org;
			}
				
			if (day != d.getDate()) {
				return org;
			}
	
			return tmp.substring(0, 4) + "/" + tmp.substring(4, 6);
		}
	
		return org;
	}

</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/BalanceList" method="post" onsubmit="return false;" styleId="mainForm">

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
							<td class="fcTitle fb">請求残高一覧</td>
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
									<td class="bcTitleSearch fb fcTitleSearch">部門</td>
									<td>
										<nested:text property="srhSectionCd" maxlength="10" size="15" styleId="srhSectionCd" />
										<div id="autocomplete_section" class="autocomplete"></div>
										<nested:text property="srhSectionName" maxlength="30" size="40" styleId="srhSectionName" />
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">担当者</td>
									<td>
										<nested:text property="srhTantoCd" maxlength="10" size="15" styleId="srhTantoCd" />
										<div id="autocomplete_tanto" class="autocomplete"></div>
										<nested:text property="srhTantoNm" maxlength="30" size="40" styleId="srhTantoNm" />
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">請求先</td>
									<td>
										<nested:text property="srhVenderCd" maxlength="15" size="15" styleId="srhVenderCd" />
										<div id="autocomplete_vender" class="autocomplete"></div>
										<nested:text property="srhVenderName" maxlength="30" size="40" styleId="srhVenderName" />
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">対象年月</td>
									<td>
										<nested:text property="srhTargetMonth" maxlength="6" size="15" styleId="srhTargetMonth" style="ime-mode:disabled"/>
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">出力区分</td>
									<td>
										<nested:checkbox property="srhOutputDivision" styleId="srhOutputDivision"/>
										残高有のみ
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">対象区分</td>
									<td>
										<nested:checkbox property="srhNormalFlg" styleId="srhNormalFlg" />
										通常処理分
										<nested:checkbox property="srhTempClosingFlg" styleId="srhTempClosingFlg"/>
										仮締処理分
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
											onclick="return check_form_submit('op', 'search'); return false;"
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
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td><!-- 明細部 -->
						<table width="" border="0">
							<tr>
								<td class="alignRight">
								<table cellspacing="1" cellpadding="1" border="0">
									<tr>
										<td>
											<div id="cssButton">
												<a href="#" onclick="return form_submit('op', 'report'); return false;"	class="cssButton">請求残高一覧表(EXCEL)</a>
											</div>
										</td>
									</tr>
								</table>
								</td>
							</tr>
							<tr>
								<td class="alignCenter">
								<table cellspacing="2" cellpadding="1" id="tblList">
									<tr class="bcTitleList fb fcTitleList">
										<td>請求先</td>
										<td align="right">前回請求額</td>
										<td align="right">入金・<br>その他計</td>
										<td align="right">今回売上額</td>
										<td align="right">その他計</td>
										<td align="right">消費税</td>
										<td align="right">請求残高</td>
									</tr>
									<nested:iterate id="searchList" property="searchList" indexId="index">
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
											<tr class="bcList1">
										</app:modulo>
			
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
											<tr class="bcList2">
										</app:modulo>
			
										<%-- 請求先 --%>
										<td width="250">
											<nested:write property="venderCd"/>:<nested:write property="venderName"/>
										</td>
										<%-- 前回請求額 --%>
										<td width="90" align="right">
											<nested:write property="strClaimAmountForward"/>
										</td>
										<%-- 入金・その他計 --%>
										<td width="90" align="right">
											<nested:write property="strOtherCredit"/>
										</td>
										<%-- 今回売上額 --%>
										<td width="90" align="right">
											<nested:write property="strSalesAmount"/>
										</td>
										<%-- その他計 --%>
										<td width="90" align="right">
											<nested:write property="strOtherSales"/>
										</td>
										<%-- 消費税 --%>
										<td width="90" align="right">
											<nested:write property="strTaxAmount"/>
										</td>
										<%-- 請求残高 --%>
										<td width="90" align="right">
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
	</table>
</nested:form>
</body>
</html:html>
