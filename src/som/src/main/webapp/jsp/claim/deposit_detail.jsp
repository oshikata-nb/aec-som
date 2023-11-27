<%-- 
 * 請求－入金入力（詳細情報）画面
 * @auther tosco
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taginclude.jsf"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http//www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.asahikaseieng.Constants"%>
<%@ page import="com.asahikaseieng.app.comboboxes.SelectAccountDivision"%>
<html:html>
<head>
<%@ include file="/jsp/common/head.jsf"%>
<%@ include file="/jsp/common/head_main.jsf"%>
<%@ include file="/jsp/common/clearText.jsf" %>

<!-- Style Sheet -->
<link href='<%= request.getContextPath() + "/stylesheets/tab.css"%>'
	media="screen" rel="Stylesheet" type="text/css" />

<%-- ▼業務固有css ここから --%>
<style type="text/css">
</style>
<%-- ▲業務固有css ここまで --%>

<%-- ▼業務固有javaScript ここから --%>
<script language="JavaScript" type="text/javascript">

	<%--onload時の処理--%>
	Event.observe(window, 'load', function() {

		<%-- フォーム設定の初期化 --%>
		<%-- フォーカス初期設定 --%>
		initializeFormState();

		<%-- 必須フィールド定義 --%><%-- スタイルシートに"cssRequired"を指定してるのみ --%>
		defineAsRequiredField("organizationCd");
<%--		defineAsRequiredField("sectionName");	--%>
		defineAsRequiredField("strCreditDate");
		defineAsRequiredField("venderCd");

		<%-- 入力補助用項目 --%>
		defineAsNumberField("realDepositAmount");
		defineAsNumberField("accSysAmount");
		defineAsNumberField("claimAmount");
		defineAsNumberField("inputPtn_No1to7and9");
		defineAsNumberField("inputPtn_No8");
		defineAsNumberField("inputPtn_No10");
		defineAsNumberField("inputPtn_No11to19");
		defineAsNumberField("inputPtn_No20");
		defineAsNumberField("inputPtnNo21to29");
		defineAsNumberField("inputPtn_AccSys");
		
		<%-- 日付型フィールド定義 --%><%-- 指定IDにfocus,blurイベントで日付フォーマット・解除を行っている。 --%>
		defineAsDateField("strCreditDate");

		var tblList = $('tblList');

		<%-- 数値型フィールド定義 --%><%-- 指定IDにfocus,blurイベントで数値フォーマット・解除を行っている。 --%>
		<%-- defineAsNumberField(id) --%>
		<logic:notEqual name="depositDetailForm" property="insertFlg" value="2"><%-- 新規入力・更新時－入力可 --%>
			var count = $F("detailCount");
			for(var i = 0 ; i < count ; i++){
				var prefix = "detailList[" + i + "].";

				<%-- name属性でもＯＫ --%>
				<%-- 明細必須項目 --%>
				defineAsRequiredField(prefix + "categoryDivision");
				defineAsRequiredField(prefix + "creditAmount");
				defineAsRequiredField(prefix + "debitTitleCd");
	
				<%-- 明細数値項目 --%>
				defineAsNumberField(prefix + "creditAmount");
				
				<%-- 明細日付項目 --%>
				defineAsDateField(prefix + "drawalDateString");
				defineAsDateField(prefix + "noteDateString");
				
				<%-- 科目コードの変更イベント --%>			
<%--				Event.observe(prefix + "debitTitleCd",'change',function(index){return function(){debitCodeAction(index);}}(i),false); --%>
				Event.observe(prefix + "debitTitleCd",'change',function(index){return function(){debitCodeAction(index);}}(i),false);
				<%-- 入金金額の変更イベント(金額合計) --%>			
				Event.observe(prefix + "creditAmount",'change',function(){setDirtyFlg(); return calcCreditAmount();},false);
				<%-- 手形種別の変更イベント(振出日、満期日必須)) --%>
<%--				Event.observe(prefix + "noteDivision",'change',function(index){return function(){ changeNoteNoAction(index);}}(i),false); --%>
				<%-- 手形番号の変更イベント(振出日、満期日必須)) --%>
<%--				Event.observe(prefix + "noteNo",'change',function(index){return function(){ changeNoteNoAction(index);}}(i),false); --%>
				<%-- 入金分類の変更イベント --%>			
				Event.observe(prefix + "categoryDivision",'change',function(index){return function(){ changeCategory(index);}}(i),false);
				<%-- 銀行の変更イベント --%>			
				Event.observe(prefix + "bankCd",'change',function(index){return function(){ getBankInfo(index);}}(i),false);
				<%-- 預金種別の変更イベント --%>			
				Event.observe(prefix + "accountDivision",'change',setDirtyFlg,false);
				<%-- 口座番号の変更イベント --%>			
				Event.observe(prefix + "accountNo",'change',setDirtyFlg,false);
				<%-- 摘要名の変更イベント --%>			
				Event.observe(prefix + "summary",'change',setDirtyFlg,false);
				<%-- 振出日の変更イベント --%>			
				Event.observe(prefix + "drawalDateString",'change',setDirtyFlg,false);
				<%-- 満期日の変更イベント --%>			
				Event.observe(prefix + "noteDateString",'change',setDirtyFlg,false);
				
			}
		</logic:notEqual>
		
		<logic:equal name="depositDetailForm" property="insertFlg" value="1"><%-- 新規入力時－入力可 --%>
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

			<%-- 請求先名称のauto complete --%>
			new Ajax.Autocompleter(
				"venderCd",
				"autocomplete_vender",
				"<%= request.getContextPath() %>/DepositVenderForAutoComplete.do?op=searchVender",
				{
				    autoParams: ['venderDivision'],
					paramName : "code",
					afterUpdateElement : setVenderLabel
				}
			);

			Event.observe("organizationCd","change",setDirtyFlg,false);		<%-- 部門の変更イベント --%>
			Event.observe("strCreditDate","change",setDirtyFlg,false);	<%-- 入金日付の変更イベント --%>
			Event.observe("venderCd","change",setDirtyFlg,false);		<%-- 請求先の変更イベント --%>

			Event.observe('organizationCd', 'keypress', clearLabel.bindAsEventListener($('organizationName')), false);
			Event.observe('venderCd', 'keypress', clearLabel.bindAsEventListener($('venderShortedName')), false);
			Event.observe('venderCd', 'keypress', clearLabel.bindAsEventListener($('holidayMsg')), false);
	    </logic:equal>

		<%-- 入金合計算出 --%>
		calcCreditAmount();
		<logic:notEqual name="depositDetailForm" property="insertFlg" value="2"><%-- 新規入力・更新時－入力可 --%>
			<%-- 手形番号により払出日、満期日の必須入力設定 --%>
<%--			noteNoAllSetNesessary(<bean:write name='depositDetailForm' property="detailCount"/>); --%>
			<%-- 入金分類により銀行必須入力設定 --%>
			categoryAllSetNesessary(<bean:write name='depositDetailForm' property="detailCount"/>);
			<%-- 手形分類により手形・振出日・満期日　必須入力設定 --%>
			noteAllSetNesessary(<bean:write name='depositDetailForm' property="detailCount"/>);
	    </logic:notEqual>
		
		storeInitValues(/^srh.*/, 'dirtyFlg', /^checked.*/);	<%-- 初期値を保持している、引数は除外するＩＤ名 --%>
<%--		refreshLabel();	<%-- //hiddenの値をラベルに反映する（初期値の復活用、lbl＋hiddenのＩＤ名のオブジェクトにhiddenの値を設定する) --%>
<%--		Form.focusFirstElement("depositDetailForm"); --%>
		if ($("cursor").value == null || $("cursor").value == "") {
			$("dirtyFlg").value = "";
		}

		<%-- カーソル位置セット --%>
		setActivate();

		refreshLabel();
	}, false);

	<%-- ajax --%>
	function setOrganizationLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("organizationName").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
	}

	function setVenderLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("venderName1").value = li.getElementsByTagName('span')[0].innerText;
		$("venderShortedName").value = li.getElementsByTagName('span')[0].innerText;
		$("organizationCd").value = getHiddenValue(li, "organizationCd");
		$("organizationName").value = getHiddenValue(li, "organizationName");
		$("advanceDivision").value = getHiddenValue(li, "advanceDivision");

		<%-- カーソルセット --%>
		setCursor('2');

		<%-- 休日チェック --%>
		form_submit('op', 'checkHoliday');

		refreshLabel();
	}

	<%-- 科目コード変更イベント処理 --%>
	function debitCodeAction(index){
		setDirtyFlg();
<%--		form_submit('op', 'subdebit','index',index); --%>
		setCursor('4');
		form_submit('op', 'getAccountsName', 'index', index);
		return false;
	}
	<%-- 入金金額の合計 --%>
	function calcCreditAmount(){
		var count = $F("detailCount");
		var sum = 0;
		for(var i = 0 ; i < count ; i++ ){
			var buf =  $F("detailList[" + i + "].creditAmount");
			if( !blank(buf)){
				var buf2 = parseFloat(buf.replace(/,/g, ""));
				if( !isNaN(buf2) ){
					sum += buf2;
				}
			}
		}
		$("sumAmount").update(formatNumber(String(sum)));
	}
	<%-- 空白チェック（null,全てスペースも空白と判断する) --%>
	function blank(s){
		var reg = new RegExp(/^\s*$/);
		return reg.test(s);	
	}
	<%-- 変更フラグセット --%>
	function setDirtyFlg() {
		$("dirtyFlg").value = "true";
	}
	<%-- カーソルセット --%>
	function setCursor(pos) {
		$("cursor").value = pos;
	}
	<%-- カーソル位置セット --%>
	function setActivate() {
		if ($('cursor').value == '1') {
			Field.activate('strCreditDate');
		}else if ($('cursor').value == '2') {
			Field.activate('venderCd');
		}else if ($('cursor').value == '3') {
			Field.activate('categoryDivision' + $F('index'));
		}else if ($('cursor').value == '4') {
			Field.activate('debitTitleCd' + $F('index'));
		}
	}
	<%-- 手形番号変更イベント処理(手形番号入力時、振出日・満期日は必須入力) --%>	
	function changeNoteNoAction(index){
		if($F("detailList[" + index + "].checkNoteDivision") == "1"){
				defineAsRequiredField("detailList[" + index + "].noteNo");
				defineAsRequiredField("detailList[" + index + "].drawalDateString");
				defineAsRequiredField("detailList[" + index + "].noteDateString");
		}else{
			if(blank($F("detailList[" + index + "].noteNo"))){
				$("detailList[" + index + "].noteNo").removeClassName("cssRequired");
				$("detailList[" + index + "].drawalDateString").removeClassName("cssRequired");
				$("detailList[" + index + "].noteDateString").removeClassName("cssRequired");
			}else{
				defineAsRequiredField("detailList[" + index + "].noteNo");
				defineAsRequiredField("detailList[" + index + "].drawalDateString");
				defineAsRequiredField("detailList[" + index + "].noteDateString");
			}
		}
	}
	<%-- 手形番号必須設定一括処理(手形番号入力時、振出日・満期日は必須入力) --%>	
	function noteNoAllSetNesessary(count){
		for(var i = 0 ; i < count ; i++){
			changeNoteNoAction(i);
		}
	}
	<%-- 入金分類の変更イベント処理 --%>
	function changeCategory(index){
		setDirtyFlg();
		setCursor('3');
		form_submit('op', 'changeCategory', 'index', index);
		refreshLabel();
	}
	<%-- 入金分類で必須項目設定処理 --%>
	function categoryAllSetNesessary(count){
		for(var i = 0 ; i < count ; i++){
			if($F("detailList[" + i + "].bankDivision") == "1"){
				defineAsRequiredField("detailList[" + i + "].bankCd");
				defineAsRequiredField("detailList[" + i + "].accountDivision");
				defineAsRequiredField("detailList[" + i + "].accountNo");
			} else {
				$("detailList[" + i + "].bankCd").value = "";
				$("detailList[" + i + "].accountDivision").value = "";
				$("detailList[" + i + "].accountNo").value = "";
			}
		}
	}
	<%-- 手形分類で必須項目設定処理 --%>
	function noteAllSetNesessary(count){
		for(var i = 0 ; i < count ; i++){
			if($F("detailList[" + i + "].checkNoteDivision") == "1"){
				defineAsRequiredField("detailList[" + i + "].noteNo");
				defineAsRequiredField("detailList[" + i + "].drawalDateString");
				defineAsRequiredField("detailList[" + i + "].noteDateString");
			}
		}
	}

	<%-- 変更確認メッセージ --%>
	function putDirtyConfirm() {
		var flg = $("dirtyFlg").value;
		if (flg) {
			<%-- 何か値が変更されている場合 --%>
			<%-- return continueConfirm2(); --%>
			return confirm("<bean:message key="message.confirm.under.edit" />");
		}else{
			return true;
		}
	}
	<%-- 編集内容破棄時に、処理を実行するかの判断を戻す --%>
	function continueConfirm2() {
		<%-- 画面の編集内容は破棄されます、よろしいですか？ --%>
		var flag = confirm("<bean:message key="message.confirm.under.edit" />");
		if (!flag) {
			return false;
		}
		return true;
	}
	<%-- 行削除確認メッセージ --%>
	function putDelListConfirm() {
		var count = $F("detailCount");

		var flag = false;
		for(var i = 0 ; i < count ; i++){
			var chk = $F("detailList[" + i + "].deleteFlag");
			if(chk){
				flag = true;
				break;
			}
		}
		if(flag){
			alertMsg = new Array();
			alertMsg[0] = "削除してよろしいですか？";
			return confirm(alertMsg[0]);
		}else{
			alertMsg = new Array();
			alertMsg[0] = "行が選択されていません。";
			alert(alertMsg[0]);
			return false;
		}
		
	}
	<%-- 確認メッセージ --%>
	function putConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "登録してよろしいですか？";
	
		return confirm(alertMsg[0]);
	}
	<%-- 確認メッセージ --%>
	function deleteAlert() {
		alertMsg = new Array();
		alertMsg[0] = "削除してよろしいですか？";

		return confirm(alertMsg[0]);
	}

	<%-- 摘要出力 --%>
	function setRemark() {
		str = $("strCreditDate").value;
		
		<%-- 日付の書式設定 --%>
		str = formatDate(str);
		
		if (str == null || str == "") {
			$("remark").value = "";
		} else {
			str = str.replace(/\//g, "");

			if (str.length == 8) {
				$("remark").value = str.substring(2, 4) + "年" + str.substring(4, 6) + "月入金";
			} else if (str.length == 6) {
				$("remark").value = str.substring(0, 2) + "年" + str.substring(2, 4) + "月入金";
			}
		}
	}

	<%-- 銀行情報取得 --%>
	function getBankInfo(index) {
		if ($("bankCd" + index).value == "" || $("bankCd" + index).value == null) {
			$("detailList[" + index + "].accountDivision").value = "";
			$("detailList[" + index + "].accountNo").value = "";
		} else if ($("bankCd" + index).value == $("bankMasterCd1").value) {
			$("detailList[" + index + "].accountDivision").value = $("accountDivision1").value;
			$("detailList[" + index + "].accountNo").value = $("accountNo1").value;
		} else if ($("bankCd" + index).value == $("bankMasterCd2").value) {
			$("detailList[" + index + "].accountDivision").value = $("accountDivision2").value;
			$("detailList[" + index + "].accountNo").value = $("accountNo2").value;
		} else if ($("bankCd" + index).value == $("bankMasterCd3").value) {
			$("detailList[" + index + "].accountDivision").value = $("accountDivision3").value;
			$("detailList[" + index + "].accountNo").value = $("accountNo3").value;
		} else if ($("bankCd" + index).value == $("bankMasterCd4").value) {
			$("detailList[" + index + "].accountDivision").value = $("accountDivision4").value;
			$("detailList[" + index + "].accountNo").value = $("accountNo4").value;
		}
	}
</script>

<%-- ▲業務固有javaScriptここまで --%>

</head>

<body>

<nested:form action="/DepositDetail" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="dirtyFlg" />
	<nested:hidden property="insertFlg" styleId="insertFlg" />
	<nested:hidden property="venderDivision" styleId="venderDivision" />
	<nested:hidden property="index" styleId="index" />
	<nested:hidden property="detailCount" styleId="detailCount" />
	<nested:hidden property="cursor" styleId="cursor" />
	<nested:hidden property="bankMasterCd1" styleId="bankMasterCd1" />
	<nested:hidden property="accountDivision1" styleId="accountDivision1" />
	<nested:hidden property="accountNo1" styleId="accountNo1" />
	<nested:hidden property="bankMasterCd2" styleId="bankMasterCd2" />
	<nested:hidden property="accountDivision2" styleId="accountDivision2" />
	<nested:hidden property="accountNo2" styleId="accountNo2" />
	<nested:hidden property="bankMasterCd3" styleId="bankMasterCd3" />
	<nested:hidden property="accountDivision3" styleId="accountDivision3" />
	<nested:hidden property="accountNo3" styleId="accountNo3" />
	<nested:hidden property="bankMasterCd4" styleId="bankMasterCd4" />
	<nested:hidden property="accountDivision4" styleId="accountDivision4" />
	<nested:hidden property="accountNo4" styleId="accountNo4" />
	<nested:hidden property="advanceDivision" styleId="advanceDivision" />

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
							<td class="fcTitle fb">入金入力</td>
							<td>
							<table border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td><%-- メッセージ表示 --%> <%@ include
										file="/jsp/common/disp_info_message.jsf"%>
									<%-- メッセージ表示 ここまで --%></td>
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
					<td><%-- メッセージ表示 --%> <%@ include
						file="/jsp/common/disp_error_message.jsf"%>
					<%-- メッセージ表示 ここまで --%></td>
				</tr>
				<tr>
					<td><%-- ヘッダ部 --%>
					<table width="" cellspacing="2" cellpadding="2" border="0">
						<tr>
							<td class="fcTitleDetail fb bcTitleDetail">部署</td>
							<nested:equal property="insertFlg" value="1"><%-- 新規入力時－入力可 --%>
								<td>
									<nested:text property="organizationCd" maxlength="10" size="15" styleId="organizationCd" onchange="setDirtyFlg();"/>
									<div id="autocomplete_organization" class="autocomplete"></div>
								</td>

								<td>
									<div id="lblOrganizationName">
										<nested:write property="organizationName" />
									</div>
									<nested:hidden property="organizationName" styleId="organizationName"/>
								</td>
							</nested:equal>

							<nested:notEqual property="insertFlg" value="1"><%-- 更新・参照入力時－入力不可 --%>
								<td>
									<nested:text property="organizationCd" readonly="true" size="15" styleClass="noborderAl" />
									<nested:write property="organizationName" />
								</td>
							</nested:notEqual>

							<td class="fcTitleDetail fb bcTitleDetail">入金番号</td>
							<td><nested:write property="creditNo"/></td>
						</tr>
						<tr>
							<td class="fcTitleDetail fb bcTitleDetail">入金日付</td>
							<td>
								<nested:equal property="insertFlg" value="1"><%-- 新規入力時－入力可 --%>
									<nested:text property="strCreditDate" styleId="strCreditDate" maxlength="10" size="15" style="ime-mode:disabled" onblur="setRemark();" onchange="setCursor('1'); form_submit('op', 'checkHoliday'); setDirtyFlg();"/>
								</nested:equal>
								<nested:notEqual property="insertFlg" value="1"><%-- 更新・参照入力時－入力不可 --%>
									<nested:text property="strCreditDate" readonly="true" size="15" styleClass="noborderAl" />
								</nested:notEqual>
							</td>

							<td class="fcAlert fb" colspan="2">
								<div id="lblHolidayMsg">
									<nested:write property="holidayMsg" />
								</div>
								<nested:hidden property="holidayMsg" styleId="holidayMsg"/>
							</td>
						</tr>
						<tr>
							<td class="fcTitleDetail fb bcTitleDetail">請求先</td>
							<nested:equal property="insertFlg" value="1"><%-- 新規入力時－入力可 --%>
								<td>
									<nested:text property="venderCd" styleId="venderCd" maxlength="15" size="15" onchange="setCursor('2'); setDirtyFlg();"/>
									<div id="autocomplete_vender" class="autocomplete"></div>
								</td>

								<td colspan="2">
									<div id="lblVenderShortedName">
										<nested:write property="venderShortedName" />
									</div>
									<nested:hidden property="venderName1" styleId="venderName1"/>
									<nested:hidden property="venderShortedName" styleId="venderShortedName"/>
								</td>
							</nested:equal>

							<nested:notEqual property="insertFlg" value="1"><%-- 更新・参照入力時－入力不可 --%>
								<td colspan="3">
									<nested:text property="venderCd" styleId="venderCd" readonly="true" size="15" styleClass="noborderAl"/>
									<nested:write property="venderShortedName" />
								</td>
							</nested:notEqual>
						</tr>
<%-- 入力補助 --%>
						<tr>
							<td class="fcTitleDetail fb bcTitleDetail">実際の入金額</td>
							<td>
								<nested:text property="realDepositAmount" maxlength="10" size="15" styleId="realDepositAmount" styleClass="alignRight" onchange="form_submit('op', 'calcInputAmount');"/>
							</td>
						</tr>
						<tr>
							<td class="fcTitleDetail fb bcTitleDetail">経理SYS連携額</td>
							<td>
								<nested:text property="accSysAmount" size="15" styleId="accSysAmount" styleClass="alignRight" readonly="true"/>
							</td>							
							<td class="fcTitleDetail fb bcTitleDetail">客先請求額</td>
							<td>
								<nested:text property="claimAmount" size="15" styleId="claimAmount" styleClass="alignRight" readonly="true"/>
							</td>							
						</tr>
						<tr>
							<td class="fcTitleDetail fb bcTitleDetail">①～⑦、⑨</td>
							<td>
								<nested:text property="inputPtn_No1to7and9" size="15" styleId="inputPtn_No1to7and9" styleClass="alignRight" readonly="true"/>
							</td>	
							<td class="fcTitleDetail fb bcTitleDetail">⑧調整額</td>
							<td>
								<nested:text property="inputPtn_No8" size="15" styleId="inputPtn_No8" styleClass="alignRight" readonly="true"/>
							</td>	
							<td class="fcTitleDetail fb bcTitleDetail">⑩売上＞請求</td>
							<td>
								<nested:text property="inputPtn_No10" size="15" styleId="inputPtn_No10" styleClass="alignRight" readonly="true"/>
							</td>	
						</tr>
						<tr>
							<td class="fcTitleDetail fb bcTitleDetail">⑪～⑲</td>
							<td>
								<nested:text property="inputPtn_No11to19" size="15" styleId="inputPtn_No11to19" styleClass="alignRight" readonly="true"/>
							</td>	
							<td class="fcTitleDetail fb bcTitleDetail">⑳売上＜請求</td>
							<td>
								<nested:text property="inputPtn_No20" size="15" styleId="inputPtn_No20" styleClass="alignRight" readonly="true"/>
							</td>	
							<td class="fcTitleDetail fb bcTitleDetail" >㉑～㉙</td>
							<td>
								<nested:text property="inputPtnNo21to29" size="15" styleId="inputPtnNo21to29" styleClass="alignRight" readonly="true"/>
							</td>	
						</tr>
						<tr>
							<td class="fcTitleDetail fb bcTitleDetail">経理システム</td>
							<td>
								<nested:text property="inputPtn_AccSys" size="15" styleId="inputPtn_AccSys" styleClass="alignRight" readonly="true"/>
							</td>	
						</tr>
					</table>
					</td>
				</tr>
				

				
				<tr>
					<td height="10"></td>
				</tr>
				<tr>
					<td class="alignLeft" colspan="4">
						<nested:notEqual property="approvalStatus" value="2">
							<nested:notEqual property="approvalStatus" value="3">
								<nested:notEqual property="insertFlg" value="2"><%-- 新規入力・更新時－入力可 --%>
									<table cellspacing="1" cellpadding="1" border="0">
										<tr>
											<td><div id="cssButton"><a href="#"
													onclick="return form_submit('op', 'addRow'); return false;"
													class="cssButton">行&nbsp;&nbsp;追&nbsp;&nbsp;加</a></div></td>
											<td><div id="cssButton"><a href="#"
													onclick="if (!(putDelListConfirm())) {return false;}else{return form_submit('op', 'delRow'); return false;}"
													class="cssButton">行&nbsp;&nbsp;削&nbsp;&nbsp;除</a></div></td>
										</tr>
									</table>
								</nested:notEqual>
							</nested:notEqual>
						</nested:notEqual>
					</td>
				</tr>
				<tr>
					<td><%-- 複数明細部 --%>
					<table width="100%" cellspacing="2" cellpadding="1" id="tblList">
						<tr class="bcTitleList fb fcTitleList">
							<nested:notEqual property="insertFlg" value="2"><%-- 新規入力・更新時－入力可 --%>
								<td rowspan="3"></td>
							</nested:notEqual>
							<td align="right" rowspan="3">行</td>
							<td>入金分類</td>
							<td align="right">入金金額</td>
							<td>銀行</td>
							<td>預金種別</td>
							<td>口座番号</td>
						</tr>
						<tr class="bcTitleList fb fcTitleList">
							<td colspan="2">勘定科目</td>
							<td></td>
							<td colspan="2">摘要</td>
						</tr>
						<tr class="bcTitleList fb fcTitleList">
							<td>手形種別</td>
							<td>手形番号</td>
							<td>振出日</td>
							<td colspan="2">満期日</td>
						</tr>
						<%-- 明細部 >>>>> --%>
						<nested:iterate id="detailList" property="detailList" indexId="index">
							<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
								<tr class="bcList1">
							</app:modulo>
							<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
								<tr class="bcList2">
							</app:modulo>
							<nested:hidden property="advanceDivision" styleId="<%="advanceDivision" + pageContext.findAttribute("index").toString() %>" />
							<logic:notEqual name="depositDetailForm" property="insertFlg" value="2"><%-- 新規入力・更新時－入力可 --%>
								<td rowspan="4"><%-- 削除チェックボックス --%>
									<nested:checkbox property="deleteFlag" styleId="deleteFlag" value="<%= Constants.FLG_ON %>" />
								</td>
							</logic:notEqual>
							<td align="right" rowspan="4"><%-- 行番号 --%>
								<div id="<%="lblRowNo" + pageContext.findAttribute("index").toString() %>">
									<nested:write property="rowNo" />
								</div>
								<nested:hidden property="rowNo" styleId="<%="rowNo" + pageContext.findAttribute("index").toString() %>" />
							</td>

							<td><%-- 入金分類 --%>
								<logic:notEqual name="depositDetailForm" property="insertFlg" value="2"><%-- 新規入力・更新時－入力可 --%>
									<%-- 前受金対象でない --%>
									<nested:equal property="advanceDivision" value="1">
										<nested:select property="categoryDivision" styleId="<%="categoryDivision" + pageContext.findAttribute("index").toString() %>" >
											<nested:optionsCollection name="depositDetailForm" property="notCategoryList" label="labales" value="values" />
										</nested:select>
									</nested:equal>

									<%-- 前受金対象 --%>
									<nested:equal property="advanceDivision" value="2">
										<nested:select property="categoryDivision" styleId="<%="categoryDivision" + pageContext.findAttribute("index").toString() %>" >
											<nested:optionsCollection name="depositDetailForm" property="categoryList" label="labales" value="values" />
										</nested:select>
									</nested:equal>
								</logic:notEqual>
								<logic:equal name="depositDetailForm" property="insertFlg" value="2"><%-- 参照時－入力不可 --%>
									<nested:text property="viewCategoryDivision" styleId="viewCategoryDivision" size="15" readonly="true"/>
								</logic:equal>
							</td>
							<td align="right"><%-- 入金金額 --%>
								<logic:notEqual name="depositDetailForm" property="insertFlg" value="2"><%-- 新規入力・更新時－入力可 --%>
									<nested:text property="creditAmount" styleId="creditAmount" maxlength="21" size="8" styleClass="alignRight" onchange="setDirtyFlg();"/>
								</logic:notEqual>
								<logic:equal name="depositDetailForm" property="insertFlg" value="2"><%-- 参照時－入力不可 --%>
									<nested:text property="creditAmount" styleId="creditAmount" maxlength="21" size="8" styleClass="alignRight" readonly="true"/>
								</logic:equal>
							</td>
							<nested:equal property="bankDivision" value="1">
								<logic:notEqual name="depositDetailForm" property="insertFlg" value="2"><%-- 新規入力・更新時－入力可 --%>
									<td><%-- 銀行 --%>
										<nested:select property="bankCd" styleId="<%="bankCd" + pageContext.findAttribute("index").toString() %>" onchange="setDirtyFlg();" >
											<nested:options property="bankCdValues" labelProperty="bankCdLabels" />
										</nested:select>
									</td>
								</logic:notEqual>
								<logic:equal name="depositDetailForm" property="insertFlg" value="2"><%-- 参照時－入力不可 --%>
									<td>
										<nested:select property="bankCd" styleId="<%="bankCd" + pageContext.findAttribute("index").toString() %>" disabled="true" >
											<nested:options property="bankCdValues" labelProperty="bankCdLabels" />
										</nested:select>
									</td>
								</logic:equal>
							</nested:equal>

							<nested:notEqual property="bankDivision" value="1">
								<logic:notEqual name="depositDetailForm" property="insertFlg" value="2"><%-- 新規入力・更新時－入力可 --%>
									<td><%-- 銀行 --%>
										<nested:select property="bankCd" styleId="<%="bankCd" + pageContext.findAttribute("index").toString() %>" onchange="setDirtyFlg();" disabled="true" >
											<nested:options property="bankCdValues" labelProperty="bankCdLabels" />
										</nested:select>
									</td>
								</logic:notEqual>
								<logic:equal name="depositDetailForm" property="insertFlg" value="2"><%-- 参照時－入力不可 --%>
									<td>
										<nested:select property="bankCd" styleId="<%="bankCd" + pageContext.findAttribute("index").toString() %>" disabled="true" >
											<nested:options property="bankCdValues" labelProperty="bankCdLabels" />
										</nested:select>
									</td>
								</logic:equal>
							</nested:notEqual>

							<td><%-- 預金種別 --%>
 								<% pageContext.setAttribute("SelectAccountDivision", new SelectAccountDivision(false, false)); %>
								<logic:notEqual name="depositDetailForm" property="insertFlg" value="2"><%-- 新規入力・更新時－入力可 --%>
									<nested:select property="accountDivision" styleId="<%="accountDivision" + pageContext.findAttribute("index").toString() %>" onchange="setDirtyFlg();" disabled="true" >
										<html:option value=""></html:option>
										<nested:options name="SelectAccountDivision" property="values" labelName="SelectAccountDivision" labelProperty="labels" />
									</nested:select>
								</logic:notEqual>
								<logic:equal name="depositDetailForm" property="insertFlg" value="2"><%-- 参照時－入力不可 --%>
									<nested:text property="viewAccountDivision" styleId="viewAccountDivision" size="8" readonly="true"/>
								</logic:equal>
							<td><%-- 口座番号 --%>
								<nested:text property="accountNo" styleId="accountNo" maxlength="10" size="8"  readonly="true"/>
							</td>
						<%-- 2行目 --%>
						<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
							<tr class="bcList1">
						</app:modulo>
						<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
							<tr class="bcList2">
						</app:modulo>
							<td colspan="2"><%-- 勘定科目 --%>
								<logic:notEqual name="depositDetailForm" property="insertFlg" value="2"><%-- 新規入力・更新時－入力可 --%>
									<nested:select property="debitTitleCd" styleId="<%="debitTitleCd" + pageContext.findAttribute("index").toString() %>" disabled="true">
										<nested:optionsCollection name="depositDetailForm" property="accountList" label="labales" value="values" />
									</nested:select>
								</logic:notEqual>
								<logic:equal name="depositDetailForm" property="insertFlg" value="2"><%-- 参照時－入力不可 --%>
									<nested:text property="viewDebitTitle" styleId="viewDebitTitle" size="20" readonly="true"/>
								</logic:equal>
							</td>
							<td><%-- 科目内容 --%>
								<nested:write property="accountsCdName" />
							</td>
							<logic:notEqual name="depositDetailForm" property="insertFlg" value="2"><%-- 新規入力・更新時－入力可 --%>
								<td colspan="2">
									<%-- 適用名称 --%>
									<nested:text property="summary" styleId="<%="summary" + pageContext.findAttribute("index").toString() %>" maxlength="255" size="18" onchange="setDirtyFlg();"/>
								</td>
							</logic:notEqual>
							<logic:equal name="depositDetailForm" property="insertFlg" value="2"><%-- 参照時－入力不可 --%>
								<td colspan="2">
									<nested:text property="summary" styleId="<%="summary" + pageContext.findAttribute("index").toString() %>" maxlength="255" size="18" readonly="true" />
								</td>
							</logic:equal>
						<tr><%-- 横線 --%>
							<td class="bcTitleLine" colspan="5"></td>
						</tr>
						<%-- 3行目 --%>
						<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
							<tr class="bcList1">
						</app:modulo>
						<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
							<tr class="bcList2">
						</app:modulo>
							<td><%-- 手形種別 --%>
								<logic:notEqual name="depositDetailForm" property="insertFlg" value="2"><%-- 新規入力・更新時－入力可 --%>
									<% pageContext.setAttribute("SelectNoteDivision", new com.asahikaseieng.app.comboboxes.SelectNoteDivision(false, false)); %>
									<nested:select property="noteDivision" styleId="<%="noteDivision" + pageContext.findAttribute("index").toString() %>" onchange="setDirtyFlg();" >
 										<nested:options name="SelectNoteDivision" property="values" labelName="SelectNoteDivision" labelProperty="labels"/>
									</nested:select>
								</logic:notEqual>
								<logic:equal name="depositDetailForm" property="insertFlg" value="2"><%-- 参照時－入力不可 --%>
									<% pageContext.setAttribute("SelectNoteDivision", new com.asahikaseieng.app.comboboxes.SelectNoteDivision(false, false)); %>
									<nested:select property="noteDivision" disabled="true" onchange="setDirtyFlg();" >
										<nested:options name="SelectNoteDivision" property="values" labelName="SelectNoteDivision" labelProperty="labels"/>
									</nested:select>
								</logic:equal>
							</td>
							<td><%-- 手形番号 --%>
								<logic:notEqual name="depositDetailForm" property="insertFlg" value="2"><%-- 新規入力・更新時－入力可 --%>
									<nested:text property="noteNo" styleId="noteNo" maxlength="20" size="20" onchange="setDirtyFlg();"/>
								</logic:notEqual>
								<logic:equal name="depositDetailForm" property="insertFlg" value="2"><%-- 参照時－入力不可 --%>
									<nested:text property="noteNo" styleId="noteNo" maxlength="20" size="20" readonly="true"/>
								</logic:equal>
							</td>
							<td><%-- 振出日 --%>
								<logic:notEqual name="depositDetailForm" property="insertFlg" value="2"><%-- 新規入力・更新時－入力可 --%>
									<nested:text property="drawalDateString" styleId="drawalDateString" maxlength="10" size="10" onchange="setDirtyFlg();"/>
								</logic:notEqual>
								<logic:equal name="depositDetailForm" property="insertFlg" value="2"><%-- 参照時－入力不可 --%>
									<nested:text property="drawalDateString" styleId="drawalDateString" maxlength="10" size="10" readonly="true"/>
								</logic:equal>
							</td>
							<td colspan="2"><%-- 満期日 --%>
								<logic:notEqual name="depositDetailForm" property="insertFlg" value="2"><%-- 新規入力・更新時－入力可 --%>
									<nested:text property="noteDateString" styleId="noteDateString" maxlength="10" size="10" onchange="setDirtyFlg();"/>
								</logic:notEqual>
								<logic:equal name="depositDetailForm" property="insertFlg" value="2"><%-- 参照時－入力不可 --%>
									<nested:text property="noteDateString" styleId="noteDateString" maxlength="10" size="10" readonly="true"/>
								</logic:equal>
							</td>
							<nested:hidden property="bankDivision" styleId="bankDivision" />
							<nested:hidden property="checkNoteDivision" styleId="checkNoteDivision" />
						</nested:iterate>

						<tr>
							<logic:notEqual name="depositDetailForm" property="insertFlg" value="2"><%-- 新規入力・更新時－入力可 --%>
								<td align="right" colspan="3" class="fcTitleDetail fb bcTitleDetail">入金合計</td>
								<td align="right">
									<div id="sumAmount"></div>
								</td>
							</logic:notEqual>

							<logic:equal name="depositDetailForm" property="insertFlg" value="2"><%-- 参照時－入力不可 --%>
								<td align="right" colspan="2" class="fcTitleDetail fb bcTitleDetail">入金合計</td>
								<td align="right">
									<div id="sumAmount"></div>
								</td>
							</logic:equal>
						</tr>
					</table>

					<table width="100%" cellspacing="2" cellpadding="1">
						<tr>
							<td class="fcTitleDetail fb bcTitleDetail">摘要</td>
							<td colspan="3">
								<nested:text property="remark" maxlength="255" size="75" styleId="remark" onchange="setDirtyFlg();" />
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td>
					<table>
						<tr>
							<nested:equal property="approvalStatus" value="1">
								<nested:equal property="updateAuthority" value="true">
									<nested:equal property="insertFlg" value="0">
										<td class="alignRight">
											<div id="cssButton">
												<a href="#" onclick="if (!(putConfirm())) {return false;}else{return form_submit('op', 'update'); return false;}" class="cssButton">
													&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
												</a>
											</div>
										</td>

										<td width="50"><br></td>
									</nested:equal>

									<nested:equal property="insertFlg" value="1">
										<td class="alignRight">
											<div id="cssButton">
												<a href="#" onclick="if (!(putConfirm())) {return false;}else{return form_submit('op', 'insert'); return false;}" class="cssButton">
													&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
												</a>
											</div>
										</td>

										<td width="50"><br></td>
									</nested:equal>
								</nested:equal>

								<nested:equal property="deleteAuthority" value="true">
									<nested:equal property="insertFlg" value="0">
										<td class="alignLeft">
											<div id="cssButton">
												<a href="#" onclick="if (!(deleteAlert())) {return false;}else{return form_submit('op', 'delete'); return false;}" class="cssButton">
													&nbsp;&nbsp;全件削除&nbsp;&nbsp;
												</a>
											</div>
										</td>
		
										<td width="50"><br></td>
									</nested:equal>
								</nested:equal>
							</nested:equal>

							<nested:equal property="insertFlg" value="1">
								<nested:equal property="updateAuthority" value="true">
									<td class="alignRight">
										<div id="cssButton">
											<a href="#" onclick="if (!(putConfirm())) {return false;}else{return form_submit('op', 'insert'); return false;}" class="cssButton">
												&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
											</a>
										</div>
									</td>
	
									<td width="50"><br></td>
								</nested:equal>
							</nested:equal>

							<td class="alignLeft">
								<div id="cssButton">
									<a href="#" onclick="if (!(putDirtyConfirm())) {return false;}else{return form_submit('op', 'back'); return false;}" class="cssButton">
										&nbsp;&nbsp;戻&nbsp;&nbsp;る&nbsp;&nbsp;
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
	</table>

</nested:form>
</body>
</html:html>
