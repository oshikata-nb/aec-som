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
	<%-- Load時の処理 --%>
	Event.observe(window, 'load', function() {
		<%-- フォーム設定の初期化 --%>
		initializeFormState();

		<%-- 必須フィールド定義 --%>
		defineAsRequiredField("srhStrInputDate");

		<%-- 日付型フィールド定義 --%>
		defineAsYMField("srhStrInputDate");

		<%-- 部署のautocomplete --%>
		new Ajax.Autocompleter(
			"srhOrganizationCd",
			"autocomplete_choices",
			"<%= request.getContextPath() %>/OrganizationForAutoComplete.do?op=searchOrganization",
			{
				paramName : "code",
				afterUpdateElement : setOrganizationLabel
			}
		);

		<%-- CSVデータダウンロード処理 --%>
		downloadCsv();

		<%-- 検索後入力された場合の不整合をただす --%>
		Event.observe('srhOrganizationCd', 'keypress', clearLabel.bindAsEventListener($('srhOrganizationName')), false);

		<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
		<%-- 対象外の例：検索条件部と明細のチェックボックス --%>
		storeInitValues(/^srh.*/);	

		refreshLabel();
	}, false);

	<%-- ajax --%>
	function setOrganizationLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("srhOrganizationName").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
	}

	<%-- CSVデータダウンロード --%>
	function downloadCsv() {
		if ($('csvDownloadFlg').value == "true") { 
			$('mainForm').target = "hideDownLoadFrame";
			$('op').value = "downLoadCsvData";
			$('mainForm').submit();
			$('mainForm').target = "";
			$('csvDownloadFlg').value = false;
		}
	}

	<%-- 確認メッセージ --%>
	function putExecuteConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "登録してよろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- 作成確認メッセージ --%>
	function putCreateConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "作成してよろしいですか？";
		return confirm(alertMsg[0]);
	}

	var dateElements = new Array();

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

	/* 日付型 ON_FOCUSの動作を設定 */
	function dateFocusListener() {
		this.value = unformatDate(this.value);
		Field.select(this);
	}

	/* 日付型 OFF_FOCUSの動作を設定 */
	function dateBlurListener() {
		this.value = formatDate(this.value);
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

<!-- 隠し表示エリア -->
<IFRAME name="hideDownLoadFrame" style="display:none" ></IFRAME>

<nested:form action="/ApTrans" method="post" onsubmit="return false;" styleId="mainForm">	
	<%@ include file="/jsp/common/postjavascript.jsf" %>

	<nested:hidden property="op" styleId="op"/>
	<nested:hidden property="csvDownloadFlg" styleId="csvDownloadFlg" />	

	<table cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td class="valignTop alignLeft">
				<table border="0" cellpadding="0" cellspacing="0" width="750">
					<tr>
						<td>
							<!-- ヘッダー部 -->
							<table border="0" cellpadding="0" cellspacing="0" width="100%">
								<tr>
									<td height="10" colspan="2"></td>
								</tr>

								<tr>
									<td class="fcTitle fb" width="250">会計送信</td>
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

								<tr>
									<td height="5" colspan="2"></td>
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
										<table cellspacing="2" cellpadding="1"  border="0">
											<tr> 
												<td class="bcTitleSearch fb fcTitleSearch" width="130">部署</td>
												<td>
													<nested:text property="srhOrganizationCd" maxlength="10" size="10" styleId="srhOrganizationCd"/>
													<div id="autocomplete_choices" class="autocomplete"></div>
												</td>

												<td>
													<div id="lblSrhOrganizationName">
														<nested:write property="srhOrganizationName" />
													</div>
													<nested:hidden property="srhOrganizationName" styleId="srhOrganizationName"/>
												</td>
											</tr>

											<tr> 
												<td class="bcTitleSearch fb fcTitleSearch">対象年月</td>
												<td>
													<nested:text property="srhStrInputDate" maxlength="10" size="10" styleId="srhStrInputDate" style="ime-mode:disabled"/>
												</td>
											</tr>
										</table>
									</td>
								</tr>

								<nested:equal property="updateAuthority" value="true">
									<tr>
										<td class="alignRight">
											<table cellspacing="1" cellpadding="1"  border="0">
												<tr>
													<td>
														<div id="cssButton">
															<a href="#" onclick="if(!putExecuteConfirm()){return false;}else{return form_submit('op', 'insert'); return false;}" class="cssButton">
																&nbsp;&nbsp;実&nbsp;&nbsp;行&nbsp;&nbsp;
															</a>
														</div>
													</td>
	
													<td>
														<div id="cssButton">
															<a href="#" onclick="if(!putCreateConfirm()){return false;}else{return form_submit('op', 'create'); return false;}" class="cssButton">
																&nbsp;&nbsp;作&nbsp;&nbsp;成&nbsp;&nbsp;
															</a>
														</div>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</nested:equal>

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
	</table>
</nested:form>

</body>
</html:html>
