<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taginclude.jsf"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http//www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<%@ include file="/jsp/common/head.jsf"%>
<%@ include file="/jsp/common/head_main.jsf"%>
<%@ include file="/jsp/common/clearText.jsf" %>
<%@ include file="/jsp/common/searchNameForMaster.jsf" %>

<%-- ▼業務固有css ここから --%>
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
		defineAsRequiredField("strPayableDate");

		<%-- 日付型フィールド定義 --%>
		defineAsDateField("strPayableDate");

		<%-- 数値型フィールド定義 --%>

		<%-- 部署名称のauto complete --%>
		new Ajax.Autocompleter(
		  "organizationCd",
		  "autocomplete_organization",
		  "<%= request.getContextPath() %>/OrganizationForAutoComplete.do?op=searchOrganization",
		  {
		  	paramName : "code",
		    afterUpdateElement : setOrganizationLabel
		  }
	    );
	
		<%-- 支払先名称のauto complete --%>
		new Ajax.Autocompleter(
		  "venderCd",
		  "autocomplete_vender",
		  "<%= request.getContextPath() %>/VenderForAutoComplete.do?op=searchVender",
		  {
		  	paramName : "code",
		  	parameters : 'venderDivision=SI',
		    afterUpdateElement : setVenderLabel
		  }
	    );

		<%-- 未来日警告Msg --%>
		if ($("checkDateFlg").value == "1") {
			dateAlert();
		}

		<%-- 検索後入力された場合の不整合をただす --%>
		Event.observe('organizationCd', 'keypress', clearLabel.bindAsEventListener($('organizationName')), false);
		Event.observe('venderCd', 'keypress', clearLabel.bindAsEventListener($('venderName')), false);
	}, false);

	<%-- 部署auto completeの選択後処理 --%>
	function setOrganizationLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
	    $("organizationName").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
	}
	function setVenderLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("venderName").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
	}

	/* ---------- 日付型 ---------- */
	var g_dateElements = new Array();
	
	/* 日付型 ON_FOCUSの動作を設定 */
	function _dateFocusListener() {
		this.value = unformatDate(this.value);
		Field.select(this);
	}
	
	/* 日付型 OFF_FOCUSの動作を設定 */
	function _dateBlurListener() {
		this.value = formatDate(this.value);
	}
	
	/**
	 * idのテキストフィールドを日付型として定義する.
	 * @param id id 
	 */
	function defineAsDateField(id) {
	
		var o = $(id);
		Event.observe(o, 'focus', _dateFocusListener.bind(o), false);
		Event.observe(o, 'blur', _dateBlurListener.bind(o), false);
	
		if (o.style.imeMode) {
			o.style.imeMode = 'disabled';
		}
		o.style.textAlign = 'left';
		
		g_dateElements[o.id] = o;
	}

	/**
	 * 日付のスラッシュ編集用文字列操作関数.
	 * @param  org 元の文字列
	 * @return 編集後の文字列
	 */
	function formatDate(org) {
	
		if (org == null) {
			return null;
		}
	
		if(!org.match(/^(-?)[0-9]{6,8}$/)){
			return org;
		}
	
		var tmp = org;
		
		if (tmp.length == 6) {
			tmp = "20" + tmp;
		}
	
		if (tmp.length == 8) {
	
			var year  = parseInt(tmp.substring(0, 4), 10);
			var month = parseInt(tmp.substring(4, 6), 10) - 1;
			var day   = parseInt(tmp.substring(6, 8), 10);
	
			if (isNaN(year) || isNaN(month) || isNaN(day)) {
				return org;
			}
				
			// 月末指定(99)の場合は除く

			if (day == 99) {
				return tmp.substring(0, 4) + "/" + tmp.substring(4, 6) + "/" + tmp.substring(6, 8);
			}
	
			var d = new Date(year, month, day);
	
			if (month != d.getMonth()) {
				return org;
			}

			if (day != d.getDate()) {
				return org;
			}
	
			return tmp.substring(0, 4) + "/" + tmp.substring(4, 6) + "/" + tmp.substring(6, 8);
		}
	
		return org;
	}

	<%-- 確認メッセージ --%>
	function putConfirm() {
		if ($("organizationCd").value == "") {
			// 部署名称クリア
			$("organizationName").value = "";
		}

		// フォーマット
		$("strPayableDate").value = formatDate($("strPayableDate").value);

		alertMsg = new Array();
		alertMsg[0] = "実行してよろしいですか？";
	
		return confirm(alertMsg[0]);
	}

	<%-- 画面締め年月日がシステム日付より未来の場合は警告 --%>
	function dateAlert() {

		alertMsg = new Array();
		alertMsg[0] = "締め年月日に未来が指定されていますがよろしいですか？";
	
		var res = confirm(alertMsg[0]);
		if (res == true) {
			return form_submit('op', 'rollback');
		} else {
			$("checkDateFlg").value = "0";
			return false;
		}
	}

</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/PaymentRollback" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="checkDateFlg" styleId="checkDateFlg" />
	<nested:hidden property="venderPayableDate" styleId="venderPayableDate" />

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
							<td class="fcTitle fb">支払更新ロールバック処理</td>
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
										<nested:text property="organizationCd" maxlength="10" size="20" styleId="organizationCd" />
										<div id="autocomplete_organization" class="autocomplete"></div>
									</td>

									<td>
										<div id="lblOrganizationName">
											<nested:write property="organizationName" />
										</div>
										<nested:hidden property="organizationName" styleId="organizationName"/>
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">支払先</td>
									<td>
										<nested:text property="venderCd" maxlength="15" size="20" styleId="venderCd" />
										<div id="autocomplete_vender" class="autocomplete"></div>
									</td>

									<td>
										<div id="lblVenderName">
											<nested:write property="venderName" />
										</div>
										<nested:hidden property="venderName" styleId="venderName"/>
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">締日</td>
									<td>
										<nested:text property="strPayableDate" maxlength="10" size="20" styleId="strPayableDate" />
									</td>
								</tr>
							</table>
							</td>
						</tr>

						<nested:equal property="updateAuthority" value="true">
							<tr>
								<td class="alignRight">
								<table cellspacing="1" cellpadding="1" border="0">
									<tr>
										<td>
										<div id="cssButton">
											<a href="#" onclick="if (!(putConfirm())) {return false;}else{return form_submit('op', 'rollback'); return false;}" class="cssButton">
											&nbsp;&nbsp;実&nbsp;&nbsp;行&nbsp;&nbsp;
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
