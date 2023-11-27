<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taginclude.jsf"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http//www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.asahikaseieng.Constants"%>
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

	<%-- 詳細画面への遷移 --%>
	function toDetail(url) {
      if (!g_lock) {
        if (continueConfirm()) {
          location.href=url;
    	  g_lock = true;
	    }
      }
	}

	<%--onload時の処理--%>
	Event.observe(window, 'load', function() {

		<%-- フォーム設定の初期化 --%>
		<logic:equal name="eraserListForm" property="searchList" value="false">
			initializeFormState();
		</logic:equal>

		<%-- 必須フィールド定義 --%>
		defineAsRequiredField("srhSectionCd");

		<%-- 日付型フィールド定義 --%>
		defineAsDateField("srhEraserDateFrom");
		defineAsDateField("srhEraserDateTo");

		<%-- 明細部 --%>
		var tblList = $('tblList');
		if (tblList != null) {
			<%-- 数値型フィールド定義 --%>
			<%-- 必須フィールド定義 --%>
			<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
			<%-- 対象外の例：検索条件部と明細のチェックボックス --%>
			storeInitValues(/^srh.*/, 'srhBelongId');
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
		    afterUpdateElement : setCustomerLabel1
		  }
	    );

		<%-- 検索後入力された場合の不整合をただす --%>
		storeInitValues(/^srh.*/, 'dirtyFlg');
		refreshLabel();

	}, false);

</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/EraserList" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />

	<table cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td class="valignTop alignLeft">
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td><!-- ヘッダー部 -->
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td height="10" colspan="2"></td>
						</tr>
						<tr>
							<td class="fcTitle fb">消込入力</td>
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
										<nested:text property="srhVenderName" maxlength="30" size="55" styleId="srhVenderName" />
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">データ区分</td>
									<td>
										<nested:checkbox property="srhDataDivNew" styleId="srhDataDivNew" value="<%= Constants.FLG_ON %>" />
										新規消込
										<nested:checkbox property="srhDataDivEraser" styleId="srhDataDivEraser" value="<%= Constants.FLG_ON %>" />
										消込結果
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">消込日付</td>
									<td>
										<nested:text property="srhEraserDateFrom" maxlength="10" size="15" styleId="srhEraserDateFrom" />　～
										<nested:text property="srhEraserDateTo" maxlength="10" size="15" styleId="srhEraserDateTo" />
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch" width="140">出力区分</td>
									<td>
										<% pageContext.setAttribute("radioApprovalDivision",
												new com.asahikaseieng.app.radiobuttons.RadioApprovalDivision()); %>
										<logic:iterate id="mp" name="radioApprovalDivision" property="map">
											<nested:radio idName="mp" property="srhOutputDivision" 
												styleId="srhOutputDivision" value="key" />
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
												<a href="#" onclick="return false;"	class="cssButton">消込状況一覧表(EXCEL)</a>
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
										<td width="220">請求先</td>
										<td width="75">消込日付</td>
										<td width="80">消込番号</td>
										<td align="right" width="90">消込残</td>
										<td align="right" width="90">消込金額</td>
										<td align="right" width="120">入金消込残合計</td>
										<td width="50">状態</td>
									</tr>
									<nested:iterate id="searchList" property="searchList" indexId="index">
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
											<tr class="bcList1">
										</app:modulo>
			
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
											<tr class="bcList2">
										</app:modulo>
			
										<nested:define id="venderCd" property="venderCd" />
										<nested:define id="sectionCd" name="eraserListForm" property="srhSectionCd" />
										<nested:define id="tantoCd" name="eraserListForm" property="srhTantoCd" />
										<nested:define id="tantoNm" name="eraserListForm" property="srhTantoNm" />

										<%-- 請求先 --%>
										<td>
											<nested:write property="venderCd" />:
											<nested:write property="venderName" />
										</td>
										<%-- 消込日付 --%>
										<td>
											<nested:write property="strEraserDate" />
										</td>
										<%-- 消込番号 --%>
										<nested:empty property="eraserNo">
											<td>
												<html:link href="#" 
													onclick='<%=
														"toDetail('" + request.getContextPath() 
														+ "/EraserDetail.do?op=init&venderCd=" 
														+ pageContext.findAttribute("venderCd").toString() 
														+ "&sectionCd=" 
														+ pageContext.findAttribute("sectionCd").toString() 
														+ "&tantoCd=" 
														+ pageContext.findAttribute("tantoCd").toString() 
														+ "&tantoNm=" 
														+ pageContext.findAttribute("tantoNm").toString() 
														+ "'); return false;"
														%>'>
													新規消込
												</html:link>
											</td>
										</nested:empty>
										<nested:notEmpty property="eraserNo">
											<nested:define id="eraserNo" property="eraserNo" />
											<td>
												<html:link href="#" 
													onclick='<%=
														"toDetail('" + request.getContextPath() 
														+ "/EraserDetail.do?op=init&eraserNo=" 
														+ pageContext.findAttribute("eraserNo").toString() 
														+ "&venderCd=" 
														+ pageContext.findAttribute("venderCd").toString() 
														+ "&sectionCd=" 
														+ pageContext.findAttribute("sectionCd").toString() 
														+ "&tantoCd=" 
														+ pageContext.findAttribute("tantoCd").toString() 
														+ "&tantoNm=" 
														+ pageContext.findAttribute("tantoNm").toString() 
														+ "'); return false;"
														%>'>
													<nested:write property="eraserNo" />
												</html:link>
											</td>
										</nested:notEmpty>
										<%-- 消込残合計 --%>
										<td align="right">
											<nested:write property="strEraserBalanceAmount" />
										</td>
										<%-- 消込金額 --%>
										<td align="right">
											<nested:write property="strEraserAmount" />
										</td>
										<%-- 入金消込残合計 --%>
										<td align="right">
											<nested:write property="strCreditEraserAmount" />
										</td>
										<%-- 状態 --%>
										<td>
											<nested:write property="strApprovalStatus" />
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
