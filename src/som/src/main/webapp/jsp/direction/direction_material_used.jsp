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


		<%-- 必須フィールド定義 --%>
		defineAsRequiredField("srhDateFrom");
		defineAsRequiredField("srhDateTo");
		
		<%-- 日付型フィールド定義 --%>
		defineAsYMField("srhDateFrom");
		defineAsYMField("srhDateTo");

		<%-- 品目のautocomplete --%>
		new Ajax.Autocompleter(
			"srhItemCd",
			"autocomplete_choices",
			"<%= request.getContextPath() %>/ItemForAutoComplete.do?op=searchItem",
			{
				paramName : "code",
				afterUpdateElement : setItemLabel
			}
		);

		<%-- 他社コードのautocomplete --%>
		new Ajax.Autocompleter(
			"srhOtherCompanyCd1",
			"autocomplete_choices",
			"<%= request.getContextPath() %>/ItemForAutoComplete.do?op=searchDetailItemOtherCompany",
			{
				paramName : "code",
				afterUpdateElement : setOtherCompanyLabel
			}
		);

		<%-- 検索後入力された場合の不整合をただす --%>
		Event.observe('srhItemCd', 'keypress', clearLabel.bindAsEventListener($('srhItemName')), false);
		Event.observe('srhItemCd', 'keypress', clearText.bindAsEventListener($('srhOtherCompanyCd1')), false);
		Event.observe('srhOtherCompanyCd1', 'keypress', clearLabel.bindAsEventListener($('srhItemName')), false);

		<%-- 明細部 --%>

		refreshLabel();

	}, false);

	<%-- ajax --%>
	function setItemLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("srhItemName").value = li.getElementsByTagName('span')[0].innerText;
		$("srhOtherCompanyCd1").value = getHiddenValue(li,"otherCompanyCd1");
		refreshLabel();
	}

	function setOtherCompanyLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("srhItemCd").value = getHiddenValue(li,"code");
		$("srhItemName").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
	}
	
	<%-- 日付型フィールド定義 --%>
	var dateElementsYYYYMM = new Array();
	/* 日付型 ON_FOCUSの動作を設定 */
	function dateFocusListenerYYYYMM() {
		this.value = unformatDate(this.value);
		Field.select(this);
	}
	
	<%--  日付型 OFF_FOCUSの動作を設定 --%>
	function dateBlurListenerYYYYMM() {
		this.value = formatDateYYYYMM(this.value);
	}

	function defineAsYMField(id) {
	
		var o = $(id);
		Event.observe(o, 'focus', dateFocusListenerYYYYMM.bind(o), false);
		Event.observe(o, 'blur', dateBlurListenerYYYYMM.bind(o), false);
	
		if (o.style.imeMode) {
			o.style.imeMode = 'disabled';
		}
		o.style.textAlign = 'left';
		
		dateElementsYYYYMM[o.id] = o;
	}
	function formatDateYYYYMM(org) {
	
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

	<%-- 製品別原材料消費量リスト発行メッセージ --%>
	function reportAlert() {
		alertMsg = new Array();
		alertMsg[0] = "製品別原材料消費量リストを発行してよろしいですか？";
		return confirm(alertMsg[0]);
	}

</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/DirectionMaterialUsed" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />

	<table cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td class="valignTop alignLeft">
			<table border="0" cellpadding="0" cellspacing="0" width="750">
			
				<tr>
					<td>
						<!-- ヘッダー部 -->
						<table border="0" cellpadding="0" cellspacing="0" width="750">
							<tr>
								<td height="10" colspan="2"></td>
							</tr>

							<tr>
								<td class="fcTitle fb" width="250">製品別原材料消費量リスト</td>
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
								<td class="bcTitleLine" colspan="3"></td>
							</tr>

							<tr>
								<td height="5" colspan="3"></td>
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
						<table width="100%" cellspacing="" cellpadding="" border="0">
							<tr>
								<td>
									<table cellspacing="2" cellpadding="1" border="0">
										<tr>
											<td class="bcTitleSearch fb fcTitleSearch" width="130">油脂・化成品区分</td>
											<td colspan=3>
												<% pageContext.setAttribute("selectDirectionMaterialUsedDeliveryDivision", new com.asahikaseieng.app.comboboxes.SelectDirectionMaterialUsedDeliveryDivision(true, false)); %>
												<nested:select property="srhBalanceType" >
													<nested:options name="selectDirectionMaterialUsedDeliveryDivision" property="values" labelName="selectDirectionMaterialUsedDeliveryDivision" labelProperty="labels"/>
												</nested:select>
											</td>
										</tr>
										<tr>
											<td class="bcTitleSearch fb fcTitleSearch" width="130">製造工程区分</td>
											<td colspan=3>
												<% pageContext.setAttribute("selectDirectionMaterialUsedDirectionDivision", new com.asahikaseieng.app.comboboxes.SelectDirectionMaterialUsedDirectionDivision(true, false)); %>
												<nested:select property="srhDirectionDivision" >
													<nested:options name="selectDirectionMaterialUsedDirectionDivision" property="values" labelName="selectDirectionMaterialUsedDirectionDivision" labelProperty="labels"/>
												</nested:select>
											</td>
										</tr>
										<tr>
											<td class="bcTitleSearch fb fcTitleSearch" width="130">品目コード</td>
											<td colspan="2">
												<nested:text property="srhItemCd" maxlength="20" size="20" styleId="srhItemCd"/>
												<div id="autocomplete_choices" class="autocomplete"></div>
											</td>

											<td width="450">
												<div id="lblSrhItemName">
													<nested:write property="srhItemName" />
												</div>
												<nested:hidden property="srhItemName" styleId="srhItemName"/>
											</td>
										</tr>
										<tr>
											<td class="bcTitleSearch fb fcTitleSearch">他社コード1</td>
											<td colspan="3">
												<nested:text property="srhOtherCompanyCd1" maxlength="20" size="20" style="ime-mode:disabled" styleId="srhOtherCompanyCd1"/>
											</td>
										</tr>
										<tr>
											<td class="bcTitleSearch fb fcTitleSearch" width="80">抽出年月</td>
											<td colspan=3>
												<nested:text property="srhDateFrom" maxlength="6" size="8" styleId="srhDateFrom"  style="ime-mode:disabled"/>
												～
												<nested:text property="srhDateTo" maxlength="6" size="8" styleId="srhDateTo"  style="ime-mode:disabled"/>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td class="alignRight">
									<table cellspacing="1" cellpadding="1"  border="0">
										<tr>
											<td>
												<div id="cssButton">
													<a href="#"	onclick="if (!(reportAlert())) {return false;}else{return form_submit('op', 'report'); return false;}" class="cssButton">
														製品別原材料消費量リスト
													</a>
												</div>
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
			</td>
		</tr>

		<%-- Excelダウンロード処理 --%>
		<nested:equal property="excelDownloadFlg" value="true">
			<META HTTP-EQUIV="REFRESH" CONTENT="0; URL=<%= request.getContextPath() %>/FileDownload/">
		</nested:equal>
		<%-- Excelダウンロード処理 ここまで --%>

	</table>
</nested:form>
</body>
</html:html>
