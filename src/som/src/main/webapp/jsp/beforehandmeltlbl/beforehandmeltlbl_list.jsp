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
		<logic:equal name="beforehandMeltLblListForm" property="searchList" value="false">
			initializeFormState();
		</logic:equal>

		<%-- 必須フィールド定義 --%>

		<%-- 日付型フィールド定義 --%>
		defineAsDateField("srhPlanedSDayFrom");
		defineAsDateField("srhPlanedSDayTo");
		defineAsDateField("srhPlanedEDayFrom");
		defineAsDateField("srhPlanedEDayTo");
		<%-- 時間型フィールド定義 --%>
		defineAsTimeField("srhPlanedSTimeFrom");
		defineAsTimeField("srhPlanedSTimeTo");
		defineAsTimeField("srhPlanedETimeFrom");
		defineAsTimeField("srhPlanedETimeTo");

		<%-- 明細部 --%>
		var tblList = $('tblList');
		if (tblList != null) {
			<%-- 数値型フィールド定義 --%>
			<%-- 必須フィールド定義 --%>
			<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
			<%-- 対象外の例：検索条件部と明細のチェックボックス --%>
			storeInitValues(/^srh.*/, 'srhPostId');
		}

		<%-- 検索後入力された場合の不整合をただす --%>
		storeInitValues(/^srh.*/, 'dirtyFlg');
		refreshLabel();
		
		<%-- 品目名称のauto complete --%>
		new Ajax.Autocompleter(
		  "srhItemCd",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/ItemQueueForAutoComplete.do?op=searchItemQueue",
		  {
		  	paramName : "code",
		    afterUpdateElement : setItemQueueLabel
		  }
		);
		<%-- 他社コード１のauto complete --%>
		new Ajax.Autocompleter(
		  "srhOtherCompanyCd1",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/ItemQueueForAutoComplete.do?op=searchItemQueueOtherCompany",
		  {
		  	paramName : "code",
		    afterUpdateElement : setOtherCompany1Label
		  }
		);
		
		<%-- 名称のクリアauto complete --%>
		Event.observe('srhItemCd', 'keypress', clearText.bindAsEventListener($('srhItemName')), false);
		Event.observe('srhItemCd', 'keypress', clearText.bindAsEventListener($('srhOtherCompanyCd1')), false);
		
		errMsgPopup();

	}, false);

	<%-- 確認メッセージ --%>
	function issueAlert() {
		if (!noCheck(1)) {
		  return false;
		}

		alertMsg = new Array();
		alertMsg[0] = "予備溶解ラベルを発行しよろしいですか";
		return confirm(alertMsg[0]);
	}
	
	<%-- チェックボックス選択無しチェック --%>
	function noCheck(index){
	  var count = 0;
	  var item = document.beforehandMeltLblListForm.beforehandMeltLblCheckBox;
	  var len = item.length;

	  <%-- 明細１件のみの場合 --%>
	  if (len == undefined) {
	    if(item.disabled == false){
	      if(item.checked){
		    count = count + 1;
          }
		}
	  } else {
	    for (var i = 0; i < len; i++) {
          if(item[i].checked){
		    count = count + 1;
		    break;
		  }
	    }
	  }
	  if (count == 0) {
        alert("予備溶解ラベル発行対象が選択されていません。");
	    return false;
	  }
	  return true;
	}
	
	<%-- 品目名称auto completeの選択後処理 --%>
	function setItemQueueLabel(text, li) {
	    $("srhItemName").value = li.getElementsByTagName('span')[0].innerText;
	    $("srhOtherCompanyCd1").value = getHiddenValue(li,"otherCompanyCd1");
	}
	
	<%-- 他社コード１auto completeの選択後処理 --%>
	function setOtherCompany1Label(text, li) {
	    $("srhItemName").value = li.getElementsByTagName('span')[0].innerText;
	    $("srhItemCd").value = getHiddenValue(li,"code");
	}

	function errMsgPopup() {
		var errMsg = $("errMsg").value;
		if (errMsg != "") {
			alert(errMsg);
			$("errMsg").value = "";
			form_submit('op', 'reSearch');
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

<nested:form action="/BeforehandMeltLblList" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="errMsg" styleId="errMsg" />

	<table cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td class="valignTop alignLeft">
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td><%-- ヘッダー部 --%>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td height="10" colspan="2"></td>
						</tr>
						<tr>
							<td class="fcTitle fb" width="250">予備溶解ラベル</td>
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
							<td height= 5  colspan= 2 ></td>
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
							<table cellspacing="1" cellpadding="1" border="0">
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch" width="130">製造指図番号</td>
									<td width="130">
										<nested:text property="srhDirectionNo" maxlength="20" size="20" styleId="srhDirectionNo" style="ime-mode:disabled" />
									</td>
									<td class="bcTitleSearch fb fcTitleSearch" width="130">生産工場</td>
									<td width="100">
										<nested:select property="srhProductionLine" style="margin: 0;padding: 0" styleId="srhProductionLine" >
											<nested:optionsCollection property="lineCombo" label="labales" value="values" />
										</nested:select>
									</td>
								</tr>
							</table>
							<table cellspacing="1" cellpadding="1" border="0">
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch" width="130">品目</td>
									<td>
										<nested:text property="srhItemCd" maxlength="20" size="20" styleId="srhItemCd" />
										<nested:text property="srhItemName" maxlength="40" size="22" readonly="true" styleId="srhItemName" styleClass="noborderAl" onchange="setDirtyFlg();" tabindex="-1"/>
										<div id="autocomplete_selection" class="autocomplete"></div>
									</td>
									<td class="bcTitleSearch fb fcTitleSearch" width="100">他社コード１</td>
									<td>
										<nested:text property="srhOtherCompanyCd1" maxlength="20" size="20" styleId="srhOtherCompanyCd1" style="ime-mode:disabled" />
									</td>
								</tr>
							</table>
							<table cellspacing="1" cellpadding="1" border="0">
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch" width="130">製造開始予定日時</td>
									<td>
										<nested:text property="srhPlanedSDayFrom" maxlength="8" size="12" styleId="srhPlanedSDayFrom" style="ime-mode:disabled" />&nbsp;<nested:text property="srhPlanedSTimeFrom" maxlength="4" size="7" styleId="srhPlanedSTimeFrom" style="ime-mode:disabled" />～<nested:text property="srhPlanedSDayTo" maxlength="8" size="12" styleId="srhPlanedSDayTo" style="ime-mode:disabled" />&nbsp;<nested:text property="srhPlanedSTimeTo" maxlength="4" size="7" styleId="srhPlanedSTimeTo" style="ime-mode:disabled" />
									</td>
								</tr>
							</table>
							<table cellspacing="1" cellpadding="1" border="0">
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch" width="130">製造終了予定日時</td>
									<td>
										<nested:text property="srhPlanedEDayFrom" maxlength="8" size="12" styleId="srhPlanedEDayFrom" style="ime-mode:disabled" />&nbsp;<nested:text property="srhPlanedETimeFrom" maxlength="4" size="7" styleId="srhPlanedETimeFrom" style="ime-mode:disabled" />～<nested:text property="srhPlanedEDayTo" maxlength="8" size="12" styleId="srhPlanedEDayTo" style="ime-mode:disabled" />&nbsp;<nested:text property="srhPlanedETimeTo" maxlength="4" size="7" styleId="srhPlanedETimeTo" style="ime-mode:disabled" />
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

				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td>
							<table width="100%" cellspacing="0" cellpadding="0">
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
							</table>
						</td>
					</tr>
					<tr>
						<td><%-- 明細部 --%>
						<table width="" border="0">
							<tr>
								<td class="alignCenter">
								<table cellspacing="2" cellpadding="1" id="tblList">
									<tr class="bcTitleList fb fcTitleList">
										<td width="20">&nbsp;</td>
										<td width="100">製造指図番号</td>
										<td width="80">製造予定日</td>
										<td width="80">品目コード</td>
										<td width="220">品目名称</td>
										<td width="90">調合タンクNo</td>
										<td width="130">生産工場</td>
										<td width="50">予備　溶解数</td>
										<td width="20">発行</td>
									</tr>
									<nested:iterate id="searchList" property="searchList"
										indexId="index">
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
											<tr class="bcList1">
										</app:modulo>

										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
											<tr class="bcList2">
										</app:modulo>

										<nested:define id="oid" property="directionDivision" />

										<%--チェックボックス --%>
										<td>
											<nested:checkbox property="beforehandMeltLblCheckBox" styleId="beforehandMeltLblCheckBox"/>
										</td>
										<%-- 指図番号 --%>
										<td>
											<nested:write property="directionNo" />
										</td>
										<%-- 製造予定日時 --%>
										<td>
											<nested:write property="strPlanedSdate" />
										</td>
										<%-- 主要製品コード --%>
										<td>
											<nested:write property="itemCd" />
										</td>
										<%-- 品目名称 --%>
										<td>
											<nested:write property="itemName" />
										</td>
										<%-- 調合タンクNO --%>
										<td>
											<nested:write property="compoundTankNo" />
										</td>
										<%-- 生産工場 --%>
										<td>
											<nested:write property="productionLineName" />
										</td>
										<%-- 予備溶解数 --%>
										<td class="alignRight">
											<nested:write property="mainStreamCount" />
										</td>
										<%-- 予備溶解ラベル発行フラグ --%>
										<td>
											<nested:write property="strStockdissLabelFlag" />
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
				<table cellpadding="0" cellspacing="0" width="100%" border="0">
					<tr>
						<td class="alignCenter">
							<div id="cssButton">
								<a href="#" onclick="if (!(issueAlert())) {return false;}else{return form_submit('op', 'issue'); return false;}" class="cssButton">
								&nbsp;&nbsp;ラベル発行&nbsp;&nbsp;
								</a>
							</div>
						</td>
					</tr>
				</table>
			</nested:notEmpty>
		<%-- Excelダウンロード処理 --%>
		<nested:equal property="excelReportDownloadFlg" value="true">
			<META HTTP-EQUIV="REFRESH" CONTENT="0; URL=<%= request.getContextPath() %>/FileDownload/">
		</nested:equal>
		<%-- Excelダウンロード処理 ここまで --%>

		<%-- Excelダウンロード処理 --%>
		<nested:equal property="excelDownloadFlg" value="true">
			<META HTTP-EQUIV="REFRESH" CONTENT="0; URL=<%= request.getContextPath() %>/FileDownload/">
		</nested:equal>
		<%-- Excelダウンロード処理 ここまで --%>
	</table>
</nested:form>
</body>
</html:html>
