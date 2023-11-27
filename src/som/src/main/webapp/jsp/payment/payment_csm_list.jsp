<%-- 
 * 支払－支払入力（リスト）画面(カスタマイズ)
 * @auther tosco
--%>
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
		initializeFormState();	<%-- 初期focusの設定 --%>

		<%-- 必須フィールド定義 --%>
		<%-- 日付型フィールド定義 --%>
		defineAsDateField("srhPaymentDateFrom");
		defineAsDateField("srhPaymentDateTo");

		<%-- 部署名称のauto complete --%>
		new Ajax.Autocompleter(
		  "srhOrganizationCd",
		  "autocomplete_selection",
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

		<%-- 支払先名称のauto complete --%>
		new Ajax.Autocompleter(
		  "srhCustomerCd",
		  "autocomplete_vender",
		  "<%= request.getContextPath() %>/VenderForAutoComplete.do?op=searchVender",
		  {
		  	paramName : "code",
		  	parameters : 'venderDivision=SI',
		    afterUpdateElement : setCustomerCdLabel
		  }
	    );

		<%-- 検索後入力された場合の不整合をただす --%>
		Event.observe('srhOrganizationCd', 'keypress', clearLabel.bindAsEventListener($('srhOrganizationName')), false);
		Event.observe('srhTantoCd', 'keypress', clearLabel.bindAsEventListener($('srhTantoNm')), false);
		Event.observe('srhCustomerCd', 'keypress', clearLabel.bindAsEventListener($('srhCustomerName')), false);
	}, false);

	<%-- 支払先auto completeの選択後処理(支払先名に選択された名称を設定) --%>
	function setCustomerCdLabel(text, li) {
	    $("srhCustomerName").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
	}
	<%-- 部署auto completeの選択後処理 --%>
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

	<%-- 全部チェックを入れる・外す --%>
	function setTargetCheckAll(bol) {
		var tblList = $('tblList');

		if (tblList != null) {
			if (0 < tblList.rows.length) {
				for (i = 0; null != $("checked" + i);　i++) {
					$("checked" + i).checked = bol;
				}
			}
		}
		return false;
	}

	<%-- 承認依頼メッセージ --%>
	function putApprovalRequestConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "承認依頼を行います。よろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- 承認メッセージ --%>
	function putApprovalConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "承認します。よろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- 否認メッセージ --%>
	function putNegationConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "否認します。よろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- 承認取消メッセージ --%>
	function putApprovalCancelConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "承認取消します。よろしいですか？";
		return confirm(alertMsg[0]);
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

<nested:form action="/PaymentCsmList" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />

	<table cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td class="valignTop alignLeft">
			<table border="0" cellpadding="0" cellspacing="0" width="750">
				<tr>
					<td><%-- ヘッダー部 --%>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td height="10" colspan="2"></td>
						</tr>
						<tr>
							<td class="fcTitle fb">支払入力</td>
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
					<td><%-- 検索部 --%>
					<table border="0" cellspacing="" cellpadding="" width="100%">
						<tr>
							<td>
							<table border="0" cellspacing="2" cellpadding="1">
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch" width="130">部署</td>
									<td>
										<nested:text property="srhOrganizationCd" maxlength="10" size="15" styleId="srhOrganizationCd" />
										<div id="autocomplete_selection" class="autocomplete"></div>
									</td>

									<td colspan="2" width="450">
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

									<td colspan="2" width="450">
										<div id="lblSrhTantoNm">
											<nested:write property="srhTantoNm" />
										</div>
										<nested:hidden property="srhTantoNm" styleId="srhTantoNm"/>
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">支払日付</td>
									<td colspan="3">
										<nested:text property="srhPaymentDateFrom" maxlength="10" size="15" styleId="srhCreditDateFrom" />　～
										<nested:text property="srhPaymentDateTo" maxlength="10" size="15" styleId="srhCreditDateTo" />
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">支払先</td>
									<td>
										<nested:text property="srhCustomerCd" maxlength="10" size="15" styleId="srhCustomerCd" />
										<div id="autocomplete_vender" class="autocomplete"></div>
									</td>

									<td colspan="2" width="450">
										<div id="lblSrhCustomerName">
											<nested:write property="srhCustomerName" />
										</div>
										<nested:hidden property="srhCustomerName" styleId="srhCustomerName"/>
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">支払番号</td>
									<td colspan="3">
										<nested:text property="srhSlipNoFrom" maxlength="20" size="15" styleId="srhSlipNoFrom" />　～
										<nested:text property="srhSlipNoTo" maxlength="20" size="15" styleId="srhSlipNoTo" />
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">支払分類</td>
									<td colspan="3">
										<nested:select property="srhPaymentDivision" >
											<nested:optionsCollection property="categoryList" label="labales" value="values" />
										</nested:select>
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch" width="140">出力区分</td>
									<td colspan="3">
										<% pageContext.setAttribute("radioApprovalDivision",
												new com.asahikaseieng.app.radiobuttons.RadioApprovalDivision()); %>
										<logic:iterate id="mp" name="radioApprovalDivision" property="map">
											<nested:radio idName="mp" property="srhOutputDivision" 
												styleId="srhOutputDivision" value="key" />
											<bean:write name="mp" property="value" />
										</logic:iterate>
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch" width="140">伝票発行</td>
									<td colspan="3">
										<nested:checkbox property="srhIssuedCheck" styleId="srhIssuedCheck" value="<%= Constants.FLG_ON %>" />
										未発行のみ
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
									<td>
									<td>
										<div id="cssButton">
										<a href='<%= request.getContextPath() + "/PaymentCsmDetail.do?op=initNew"%>'
											class="cssButton"> &nbsp;&nbsp;新&nbsp;&nbsp;規&nbsp;&nbsp;
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
						<td><%-- 明細部 --%>
						<table width="" border="0">
							<tr>
								<td class="alignRight">
								<table cellspacing="1" cellpadding="1" border="0" width="100%">
									<tr>
										<td class="alignLeft">
											<div id="cssButton">
												<a href="#" onClick="setTargetCheckAll(true);return false;" class="cssButton">全選択</a>
												<a href="#" onClick="setTargetCheckAll(false);return false;" class="cssButton">全解除</a>
											</div>
										</td>
										<td class="alignRight">
											<div id="cssButton">
												<a href="#" onclick="return form_submit('op', 'report'); return false;"	class="cssButton">支払伝票</a>
												<a href="#" onclick="return form_submit('op', 'reportTegata'); return false;"	class="cssButton">手形振出指示書</a>
												<a href="#" onclick="return form_submit('op', 'reportPaymentInform'); return false;"	class="cssButton">お支払通知書</a>

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
								<td class="alignCenter">
								<table cellspacing="2" cellpadding="1" id="tblList">
									<tr class="bcTitleList fb fcTitleList">
										<td width="20"></td>
										<td width="80">支払日付</td>
										<td width="100">支払番号</td>
										<td>行</td>
										<td width="250">支払先</td>
										<td width="90">支払分類</td>
										<td width="85" align="right">支払額</td>
										<td width="80">状態</td>
									</tr>
									<nested:iterate id="searchList" property="searchList" indexId="index">
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
											<tr class="bcList1">
										</app:modulo>
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
											<tr class="bcList2">
										</app:modulo>
			
										<td><%-- 承認用チェックボックス --%>
											<nested:checkbox property="checked" styleId="<%="checked" + pageContext.findAttribute("index").toString() %>" />
										</td>

										<%-- 支払日付 --%>
										<td>
											<nested:write property="paymentDate" format="yyyy/MM/dd"/>
										</td>
										<%-- 支払番号 --%>
										<td>
											<nested:link href='<%= request.getContextPath() + "/PaymentCsmDetail.do?op=initUpdate" %>' paramId="slipNo" paramProperty="slipNo">
												<nested:write property="slipNo"/>
											</nested:link>
										</td>
										<%-- 行番号 --%>
										<td align="right">
											<nested:write property="rowNo"/>
										</td>
										<%-- 支払先 --%>
										<td>
											<nested:write property="supplierCd"/>:<nested:write property="venderShortedName"/>
										</td>
										<%-- 支払分類 --%>
										<td>
											<nested:write property="categoryDivision"/>:<nested:write property="categoryName"/>
										</td>
										<%-- 支払額 --%>
										<td align="right">
											<nested:write property="paymentAmount"/>
										</td>
										<%-- 状態 --%>
										<td>
											<nested:write property="approvalStatusLabel"/>
										</td>
									</nested:iterate>
								</table>
								</td>
							</tr>
						</table>
						</td>
					</tr>

					<tr>
						<td class="alignCenter">
							<table cellpadding="0" cellspacing="0" width="550" border="0">
								<tr>
									<td colspan="3" height="2">
									</td>
								</tr>

								<nested:equal property="approvalRequestAuthority" value="true">
									<td class="alignRight">
										<div id="cssButton">
											<a href="#" onclick="if(!putApprovalRequestConfirm()){return false;}else{return form_submit('op', 'approvalRequest'); return false;}" class="cssButton">
												&nbsp;&nbsp;承認依頼&nbsp;&nbsp;
											</a>
										</div>
									</td>
	
									<td width="50"><br></td>
								</nested:equal>
	
								<nested:equal property="approvalAuthority" value="true">
									<td class="alignRight">
										<div id="cssButton">
											<a href="#" onclick="if(!putApprovalConfirm()){return false;}else{return form_submit('op', 'approval'); return false;}" class="cssButton">
												&nbsp;&nbsp;承&nbsp;&nbsp;認&nbsp;&nbsp;
											</a>
										</div>
									</td>
	
									<td width="50"><br></td>
								</nested:equal>
	
								<nested:equal property="negationAuthority" value="true">
									<td class="alignLeft">
										<div id="cssButton">
											<a href="#" onclick="if(!putNegationConfirm()){return false;}else{return form_submit('op', 'negation'); return false;}" class="cssButton">
												&nbsp;&nbsp;否&nbsp;&nbsp;認&nbsp;&nbsp;
											</a>
										</div>
									</td>
	
									<td width="50"><br></td>
								</nested:equal>
	
								<nested:equal property="approvalCancelAuthority" value="true">
									<td class="alignLeft">
										<div id="cssButton">
											<a href="#" onclick="if(!putApprovalCancelConfirm()){return false;}else{return form_submit('op', 'approvalCancel'); return false;}" class="cssButton">
												&nbsp;&nbsp;承認取消&nbsp;&nbsp;
											</a>
										</div>
									</td>
	
									<td width="50"><br></td>
								</nested:equal>

								<tr>
									<td colspan="3" height="2">
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
