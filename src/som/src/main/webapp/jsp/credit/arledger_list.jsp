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

	<%-- 詳細画面への遷移 --%>
	function toDetail(url) {
      if (!g_lock) {
        if (continueConfirm()) {
          location.href=url;
    	  g_lock = true;
	    }
      }
	}

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
	
	<%-- 日付型フィールド定義 --%>
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

	<%-- 日付をフォーマット --%>
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

	<%-- 対象年月フォーマット --%>
	function setTargetMonth() {
		// 対象年月フォーマット
		$("srhTargetMonth").value = formatDate($("srhTargetMonth").value);

	}

	<%-- 確認メッセージ --%>
	function reportConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "売掛元帳(EXCEL)出力してもよろしいですか？";

		return confirm(alertMsg[0]);
	}
</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/ArLedgerList" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:define id="tkb" property="targetKbn" />

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
										<nested:text property="srhSectionCd" maxlength="10" size="15" styleId="srhSectionCd" />
										<div id="autocomplete_section" class="autocomplete"></div>
									</td>

									<td colspan="2" width="450">
										<div id="lblSrhSectionName">
											<nested:write property="srhSectionName" />
										</div>
										<nested:hidden property="srhSectionName" styleId="srhSectionName"/>
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">担当者</td>
									<td>
										<nested:text property="srhTantoCd" maxlength="10" size="15" styleId="srhTantoCd" />
										<div id="autocomplete_tanto" class="autocomplete"></div>
									</td>

									<td colspan="2" width="450">
										<div id="lblSrhTantoNm">
											<nested:write property="srhTantoNm" />
										</div>
										<nested:hidden property="srhTantoNm" styleId="srhTantoNm"/>
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">請求先</td>
									<td>
										<nested:text property="srhVenderCd" maxlength="15" size="15" styleId="srhVenderCd" />
										<div id="autocomplete_vender" class="autocomplete"></div>
									</td>

									<td colspan="2" width="450">
										<div id="lblSrhVenderName">
											<nested:write property="srhVenderName" />
										</div>
										<nested:hidden property="srhVenderName" styleId="srhVenderName"/>
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">対象年月</td>
									<td colspan="2">
										<nested:text property="srhTargetMonth" maxlength="6" size="15" styleId="srhTargetMonth" style="ime-mode:disabled"/>
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">対象区分</td>
									<td colspan="2">
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
											onclick="setTargetMonth(); return check_form_submit('op', 'search'); return false;"
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
								<td class="alignRight">
								<table cellspacing="1" cellpadding="1" border="0">
									<tr>
										<td>
											<div id="cssButton">
												<a href="#" onclick="if (!(reportConfirm())) {return false;}else{return form_submit('op', 'report'); return false;}" class="cssButton">
													売掛元帳(EXCEL)
												</a>
											</div>
										</td>
									</tr>
								</table>
								</td>
							</tr>
							<tr>
								<td class="alignCenter">
								<table width="100%" cellspacing="2" cellpadding="1" id="tblList">
									<tr class="bcTitleList fb fcTitleList">
										<td>請求先</td>
										<td align="right">前月残</td>
										<td align="right">入金・<br>その他計</td>
										<td align="right">当月<br>売上高</td>
										<td align="right">その他計</td>
										<td align="right">消費税</td>
										<td align="right">売掛<br>残高</td>
										<td align="right">未収金<br>残高</td>
										<td align="right">当月残</td>
									</tr>
									<nested:iterate id="searchList" property="searchList" indexId="index">
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
											<tr class="bcList1">
										</app:modulo>
			
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
											<tr class="bcList2">
										</app:modulo>
				
										<nested:hidden property="depositNo" styleId="depositNo"/>
										<nested:define id="vcd" property="venderCd" />
										<nested:define id="dpn" property="depositNo" />

										<%-- 請求先 --%>
										<td width="180">
											<html:link href="#"
												onclick='<%=
													"toDetail('" + request.getContextPath()
													+ "/ArLedgerDetail.do?op=search&venderCd="
													+ pageContext.findAttribute("vcd").toString() 
													+ "&depositNo=" 
													+ pageContext.findAttribute("dpn").toString() 
													+ "&targetKbn=" 
													+ pageContext.findAttribute("tkb").toString() 
													+ "'); return false;"
													%>'>
												<nested:write property="venderCd"/>:<nested:write property="venderShortedName"/>
											</html:link>
										</td>
										<%-- 前月売掛残 --%>
										<td width="67" align="right">
											<nested:write property="strBalanceForward"/>
										</td>
										<%-- 入金・その他計 --%>
										<td width="67" align="right">
											<nested:write property="strOtherDeposit"/>
										</td>
										<%-- 当月売上高 --%>
										<td width="67" align="right">
											<nested:write property="strSalesAmount"/>
										</td>
										<%-- その他計 --%>
										<td width="67" align="right">
											<nested:write property="strOtherSales"/>
										</td>
										<%-- 消費税 --%>
										<td width="60" align="right">
											<nested:write property="strTaxAmount"/>
										</td>
										<%-- 売掛残高 --%>
										<td width="67" align="right">
											<nested:write property="strCreditSalesBreakdown"/>
										</td>
										<%-- 未収金残高 --%>
										<td width="67" align="right">
											<nested:write property="strAccruedDebitBreakdown"/>
										</td>
										<%-- 当月残 --%>
										<td width="67" align="right">
											<nested:write property="strCreditAmount"/>
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
