<%-- 
 * 支払－支払入力（詳細情報）画面(カスタマイズ)
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

<%-- Style Sheet --%>
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
		initializeFormState();

		<%-- 必須フィールド定義 --%>
		defineAsRequiredField("organizationCd");
		defineAsRequiredField("strPaymentDate");
		defineAsRequiredField("customerCd");

		<%-- 日付型フィールド定義 --%><%-- 指定IDにfocus,blurイベントで日付フォーマット・解除を行っている。 --%>
		defineAsDateField("strPaymentDate");

		<logic:notEqual name="paymentCsmDetailForm" property="insertFlg" value="2"><%-- 新規入力・更新時－入力可 --%>

			var count = $F("detailCount");
			for(var i = 0 ; i < count ; i++){
				var prefix = "detailList[" + i + "].";
				<%-- 明細必須項目 --%>
				defineAsRequiredField(prefix + "categoryDivision");
				defineAsRequiredField(prefix + "paymentAmount");
				defineAsRequiredField(prefix + "creditTitleCd");
	
				<%-- 明細数値項目 --%>
				defineAsNumberField(prefix + "paymentAmount");

				<%-- 明細日付項目 --%>
				defineAsDateField(prefix + "drawalDateString");
				defineAsDateField(prefix + "noteDateString");

				<%-- 銀行+支店名称のauto complete --%>
<%--				autocompleteBank(i);	--%>
				<%-- 摘要のauto complete --%>
<%--				autocompleteSummary(i); --%>
			
				<%-- 科目コードの変更イベント --%>			
				Event.observe(prefix + "creditTitleCd",'change',setDirtyFlg,false);
				<%-- 支払金額の変更イベント(金額合計) --%>			
				Event.observe(prefix + "paymentAmount",'change',function(index){return function(){setDirtyFlg(); calcCreditAmount();}}(i),false);
				<%-- 支払区分の変更イベント --%>			
				Event.observe(prefix + "categoryDivision",'change',function(index){return function(){changeCategory(index);}}(i),false);
				<%-- 銀行本店＋支店の変更イベント --%>			
<%--				Event.observe(prefix + "bankCd",'change',setDirtyFlg,false);	--%>
				<%-- 預金種別の変更イベント --%>			
<%--				Event.observe(prefix + "accountDivision",'change',setDirtyFlg,false);	--%>
				<%-- 口座番号の変更イベント --%>			
<%--				Event.observe(prefix + "accountNo",'change',setDirtyFlg,false);	--%>
				<%-- 補助科目の変更イベント --%>			
<%--				Event.observe(prefix + "creditSubTitleCd",'change',setDirtyFlg,false); --%>
				<%-- 摘要コードの変更イベント --%>			
<%--				Event.observe(prefix + "summaryCd",'change',setDirtyFlg,false);　--%>
				<%-- 摘要名の変更イベント --%>			
				Event.observe(prefix + "summary",'change',setDirtyFlg,false);
				<%-- 手形種別の変更イベント --%>
				Event.observe(prefix + "noteDivisionString",'change',setDirtyFlg,false);
				<%-- 手形番号の変更イベント(振出日、満期日必須)) --%>
				Event.observe(prefix + "noteNo",'change',function(index){return function(){ changeNoteNoAction(index);}}(i),false);
				<%-- 振出日の変更イベント --%>			
				Event.observe(prefix + "drawalDateString",'change',setDirtyFlg,false);
				<%-- 満期日の変更イベント --%>			
				Event.observe(prefix + "noteDateString",'change',setDirtyFlg,false);
			}
			<%-- 数値項目 --%>
			defineAsNumberField("strSumAmount");
			defineAsNumberField("strBalanceCarriedForward");
		</logic:notEqual>

		<logic:equal name="paymentCsmDetailForm" property="insertFlg" value="1"><%-- 新規入力時－入力可 --%>
			<%-- 部署名称のauto complete --%>
			new Ajax.Autocompleter(
			  "organizationCd",
			  "autocomplete_selection",
			  "<%= request.getContextPath() %>/OrganizationForAutoComplete.do?op=searchOrganization",
			  {
			  	paramName : "code",
			    afterUpdateElement : setOrganizationLabel
			  }
		    );
	
			<%-- 支払先のauto complete --%>
			new Ajax.Autocompleter(
			  "customerCd",
			  "autocomplete_customer",
			  "<%= request.getContextPath() %>/PaymentVenderForAutoComplete.do?op=searchVender",
			  {
			  	paramName : "code",
			  	callback : paymentVenderCallBack,
			    afterUpdateElement : setCustomerCdLabel
			  }
			);

			<%-- 検索後入力された場合の不整合をただす --%>
			Event.observe('organizationCd', 'keypress', clearLabel.bindAsEventListener($('organizationName')), false);
			Event.observe('customerCd', 'keypress', clearLabel.bindAsEventListener($('customerName')), false);
			Event.observe('customerCd', 'keypress', clearLabel.bindAsEventListener($('holidayMsg')), false);

			Event.observe("organizationCd","change",setDirtyFlg,false);		<%-- 部署の変更イベント --%>
			Event.observe("strPaymentDate","change",setDirtyFlg,false);	<%-- 支払日付の変更イベント --%>
			Event.observe("customerCd","change",setDirtyFlg,false);		<%-- 請求先の変更イベント --%>
	    </logic:equal>
		<%-- 支払合計算出 --%>
		calcCreditAmount();
		<logic:notEqual name="paymentCsmDetailForm" property="insertFlg" value="2"><%-- 新規入力・更新時－入力可 --%>
			<%-- 支払分類により銀行必須入力設定 --%>
<%--			categoryAllSetNesessary($F("detailCount"));	--%>
			<%-- 手形番号により払出日、満期日の必須入力設定 --%>
			noteNoAllSetNesessary(<bean:write name='paymentCsmDetailForm' property="detailCount"/>);
			<%-- 手形分類により手形・振出日・満期日　必須入力設定 --%>
			noteAllSetNesessary(<bean:write name='paymentCsmDetailForm' property="detailCount"/>);
	    </logic:notEqual>
		storeInitValues(/^srh.*/, 'dirtyFlg');	<%-- 初期値を保持している、引数は除外するＩＤ名 --%>

		<%-- カーソル位置セット --%>
		setActivate();

		refreshLabel();	<%-- //hiddenの値をラベルに反映する（初期値の復活用、lbl＋hiddenのＩＤ名のオブジェクトにhiddenの値を設定する) --%>
<%--		Form.focusFirstElement("paymentCsmDetailForm"); --%>

	}, false);

	<logic:iterate id="detailList" name="paymentCsmDetailForm" property="detailList" indexId="index">
		<%-- 銀行auto completeの選択後処理(銀行名に選択された名称を設定) --%>
<%--
		function setBankCdLabel<bean:write name='index'/>(text, li) {
		    $("detailList[<bean:write name='index'/>].bankName").value = li.getElementsByTagName('span')[0].innerText;
		}
--%>
		<%-- 摘要auto completeの選択後処理(選択された名称を設定) --%>
<%--
		function setSummaryCdLabel<bean:write name='index'/>(text, li) {
		    $("detailList[<bean:write name='index'/>].summary").value = li.getElementsByTagName('span')[0].innerText;
		}
--%>
	</logic:iterate>

	<%-- 部署auto completeの選択後処理(部署名に選択された名称を設定) --%>
	function setOrganizationLabel(text, li) {
	    $("organizationName").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
	}

	<%-- 支払先auto completeの選択後処理 --%>
	function setCustomerCdLabel(text, li) {
	    $("customerName").value = li.getElementsByTagName('span')[0].innerText;

		<%-- カーソルセット --%>
		setCursor('2');

		<%-- 支払予定セット --%>
		form_submit('op', 'setScheduled');

	    <%-- 繰越残の設定 --%>
	    var value1 = $("strPaymentAmount").value;
	    var value2 = $("strSumAmount").value;
	    var buf = value1;
	    if( !blank(buf)){
			var value1 = parseFloat(buf.replace(/,/g, ""));
			if(isNaN(value1) ){
				value1 = 0;
			}
		} else {
				value1 = 0;
		}
	    buf = value2;
	    if( !blank(buf)){
			var value2 = parseFloat(buf.replace(/,/g, ""));
			if(isNaN(value2) ){
				value2 = 0;
			}
		} else {
				value2 = 0;
		}

		<%-- 支払合計がゼロの場合は繰越残もゼロにする --%>		
		if (value1 == 0) {
			$("strBalanceCarriedForward").value = 0;
		} else {
			$("strBalanceCarriedForward").value = (formatNumber(String(value1 - value2)));
		}

		<%-- 自動仕訳セット --%>
<%--		form_submit('op', 'setJournal'); --%>

		<%-- 休日チェック --%>
<%--		form_submit('op', 'checkHoliday'); --%>

		refreshLabel();
	}

	<%-- 支払先のオートコンプリート実行時検索キー項目取得 --%>
	function paymentVenderCallBack(editor,paramText){
		return paramText + '&organizationCd=' + $('organizationCd').value;
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
			Field.activate('strPaymentDate');
		}else if ($('cursor').value == '2') {
			Field.activate('customerCd');
		}else if ($('cursor').value == '3') {
			Field.activate('categoryDivision' + $F('index'));
		}
	}

	<%-- 手形情報入力フィールドの必須設定 --%>	
	function setBillInfoRequired(index) {
		if ($F("detailList[" + index + "].noteDiv") == "1") {
				defineAsRequiredField("detailList[" + index + "].noteDivisionString");
				defineAsRequiredField("detailList[" + index + "].noteNo");
				defineAsRequiredField("detailList[" + index + "].drawalDateString");
				defineAsRequiredField("detailList[" + index + "].noteDateString");
		} else {
			if(blank($F("detailList[" + index + "].noteNo"))) {
				$("detailList[" + index + "].noteDivisionString").removeClassName("cssRequired");
				$("detailList[" + index + "].noteNo").removeClassName("cssRequired");
				$("detailList[" + index + "].drawalDateString").removeClassName("cssRequired");
				$("detailList[" + index + "].noteDateString").removeClassName("cssRequired");
			} else {
				defineAsRequiredField("detailList[" + index + "].noteDivisionString");
				defineAsRequiredField("detailList[" + index + "].noteNo");
				defineAsRequiredField("detailList[" + index + "].drawalDateString");
				defineAsRequiredField("detailList[" + index + "].noteDateString");
			}
		}
	}
	
	<%-- 手形番号変更イベント処理(手形番号入力時、振出日・満期日は必須入力) --%>	
	function changeNoteNoAction(index){
		setDirtyFlg();
		setBillInfoRequired(index);
	}
	<%-- 手形番号必須設定一括処理(手形番号入力時、振出日・満期日は必須入力) --%>	
	function noteNoAllSetNesessary(count){
		for(var i = 0 ; i < count ; i++){
			setBillInfoRequired(i);
		}
	}

	<%-- 銀行+支店名称のauto complete --%>
<%--
	function autocompleteBank(index){
		new Ajax.Autocompleter(
		  "detailList[" + index + "].bankCd",
		  "autocomplete_bankbranch" + index,
		  "<%= request.getContextPath() %>/AutoComplete.do?op=autoBankBranch",
		  {
		    afterUpdateElement : eval("setBankCdLabel" + index)
		  }
	    );
	}
--%>
	<%-- 摘要のauto complete --%>
<%--
		function autocompleteSummary(index){
		new Ajax.Autocompleter(
		  "detailList[" + index + "].summaryCd",
		  "autocomplete_summary" + index,
		  "<%= request.getContextPath() %>/AutoComplete.do?op=autoSummary",
		  {
		  	paramName : "srhSummaryCd",
		    afterUpdateElement : eval("setSummaryCdLabel" + index)
		  }
	    );
	}
--%>

	<%-- 科目コード変更イベント処理 --%>
	function debitCodeAction(index){
		setDirtyFlg();
		form_submit('op', 'changeCreditTitle','index',index);
		return false;
	}
	<%-- 支払金額の合計 --%>
	function calcCreditAmount(){
		var count = $F("detailCount");
		var sum = 0;
		for(var i = 0 ; i < count ; i++ ){
			var buf =  $F("detailList[" + i + "].paymentAmount");
			if( !blank(buf)){
				var buf2 = parseFloat(buf.replace(/,/g, ""));
				if( !isNaN(buf2) ){
					sum += buf2;
				}
			}
		}
		<%-- 繰越残の設定 --%>
		$("strSumAmount").value = (formatNumber(String(sum)));
	    var value = $("strPaymentAmount").value;
	    var buf = value;
	    if( !blank(buf)){
			var value = parseFloat(buf.replace(/,/g, ""));
			if(isNaN(value) ){
				value = 0;
			}
		} else {
				value = 0;
		}
		
		<%-- 支払合計がゼロの場合は繰越残もゼロにする --%>		
		if (value == 0) {
			$("strBalanceCarriedForward").value = 0;
		} else {
			$("strBalanceCarriedForward").value = (formatNumber(String(value - sum)));
		}
	}
	<%-- 支払分類の変更イベント処理 --%>
	function changeCategory(index){
		setDirtyFlg();
		setCursor('3');
		form_submit('op', 'changeCategory','index',index);
		return false;
	}
	<%-- 支払金額の手数料計算処理 --%>
	function calcFee(index){
		var res = true;
		var count = $F("detailCount");
		var feeFlag = $F("detailList[" + index + "].feeFlag");
		if( (feeFlag == "1") && (count == 1) ){
			var amount =  $F("detailList[" + index + "].paymentAmount");
			if( !blank(amount)){
				var buf2 = parseFloat(amount.replace(/,/g, ""));
				if( !isNaN(buf2) ){
					if(buf2 > 0){
						form_submit('op', 'calcFee','index',index);
						res = false;
					}
				}
			}
		}
		return res;
	}
	
	<%-- 支払分類で必須項目設定処理 --%>
<%--
	function categoryAllSetNesessary(count){
		for(var i = 0 ; i < count ; i++){
			if($F("detailList[" + i + "].bankDivision") == "1"){
				defineAsRequiredField("detailList[" + i + "].bankCd");
				defineAsRequiredField("detailList[" + i + "].accountDivision");
				defineAsRequiredField("detailList[" + i + "].accountNo");
			}
		}
	}
--%>

	<%-- 手形必須区分で必須項目を設定 --%>
	function noteAllSetNesessary(count){
		for(var i = 0 ; i < count ; i++){
			setBillInfoRequired(i);
<%--
			if($F("detailList[" + i + "].noteDiv") == "1"){
				defineAsRequiredField("detailList[" + i + "].noteDivisionString");
				defineAsRequiredField("detailList[" + i + "].noteNo");
				defineAsRequiredField("detailList[" + i + "].drawalDateString");
				defineAsRequiredField("detailList[" + i + "].noteDateString");
			}
--%>
		}
	}

	<%-- 変更確認メッセージ --%>
	function putDirtyConfirm() {
		var flg = $("dirtyFlg").value;
		if (flg == "true") {
			<%-- 何か値が変更されている場合 --%>
			return continueConfirm2();
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
		buf1 = $("strPaymentDate");
		buf2 = $("creditScheduledDate");
		if(buf1==null || buf2 == null || buf1.value == buf2.value){
 			alertMsg[0] = "登録してよろしいですか？";
		}
		else {
 			alertMsg[0] = "支払日付と支払予定日が一致しませんが\n登録してよろしいですか？";
		}
		return confirm(alertMsg[0]);
	}
	<%-- 確認メッセージ --%>
	function deleteAlert() {
		alertMsg = new Array();
		alertMsg[0] = "削除してよろしいですか？";

		return confirm(alertMsg[0]);
	}
	
	<%-- 摘要明細1行目デフォルト設定 --%>
	function setSummary() {
		var buf = $("summary0").value;
		if (!blank(buf)) return false;
		buf = $("strPaymentDate").value;
		if (blank(buf)) return false;
		if(! buf.match(/^(\d{4})(\d{2})(\d{2})$/)) return false;
		var sYear = String(RegExp.$1);
		var sMonth = String(RegExp.$2);
		buf = sYear.slice(-2) + "年" + sMonth + "月支払";
		$("summary0").value = buf;
	}

	<%-- 摘要出力 --%>
	function setRemark() {
		str = $("strPaymentDate").value;
		
		<%-- 日付の書式設定 --%>
		str = formatDate(str);
		
		if (str == null || str == "") {
			return;
		} else {
			str = str.replace(/\//g, "");

			if (str.length == 8) {
				str = str.substring(2, 4) + "年" + str.substring(4, 6) + "月支払";
			} else if (str.length == 6) {
				str = str.substring(0, 2) + "年" + str.substring(2, 4) + "月支払";
			}
		}
		var tblList = $('tblList');

		if (tblList != null) {
			if (0 < tblList.rows.length) {
				for (i = 0; null != $("rowNo" + i); i++) {
					if ($("summary" + i).value == null || $("summary" + i).value == "") {
						$("summary" + i).value = str;
					}
				}
			}
		}
	}
</script>

<%-- ▲業務固有javaScriptここまで --%>

</head>

<body>

<nested:form action="/PaymentCsmDetail" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="dirtyFlg" />
	<nested:hidden property="insertFlg" styleId="insertFlg" /><%-- 登録・更新フラグ --%>
	<nested:hidden property="index" styleId="index" /><%-- イベント発生行番号 --%>
	<nested:hidden property="detailCount" styleId="detailCount" /><%-- 明細行数 --%>
	<nested:hidden property="cursor" styleId="cursor" />
<%--
	<nested:hidden property="categoryDivision" styleId="categoryDivision" />
--%>

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
							<td class="fcTitle fb">支払入力</td>
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
									<nested:text property="organizationCd" maxlength="10" size="15" styleId="organizationCd"/>
									<div id="autocomplete_selection" class="autocomplete"></div>
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
									<nested:text property="organizationCd" readonly="true" size="15" styleClass="noborderAl" tabindex="-1"/>
									<nested:write property="organizationName" />
								</td>
							</nested:notEqual>

							<td class="fcTitleDetail fb bcTitleDetail" width="65">支払番号</td>
							<td><nested:write property="slipNo"/></td>
						</tr>
						<tr>
							<td class="fcTitleDetail fb bcTitleDetail">支払日付</td>
							<td>
								<nested:equal property="insertFlg" value="1"><%-- 新規入力時－入力可 --%>
									<nested:text property="strPaymentDate" styleId="strPaymentDate" maxlength="10" size="15" style="ime-mode:disabled" onblur="setRemark();" onchange="setCursor('1'); form_submit('op', 'setScheduled'); setDirtyFlg();"/>
								</nested:equal>
								<nested:notEqual property="insertFlg" value="1"><%-- 更新・参照入力時－入力不可 --%>
									<nested:text property="strPaymentDate" readonly="true" size="15" styleClass="noborderAl" tabindex="-1" />
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
							<td class="fcTitleDetail fb bcTitleDetail">支払先</td>
							<nested:equal property="insertFlg" value="1"><%-- 新規入力時－入力可 --%>
								<td>
									<nested:text property="customerCd" styleId="customerCd" maxlength="10" size="15" onchange="setCursor('2'); setDirtyFlg();"/>
									<div id="autocomplete_customer" class="autocomplete"></div>
								</td>

								<td colspan="2">
									<div id="lblCustomerName">
										<nested:write property="customerName" />
									</div>
									<nested:hidden property="customerName" styleId="customerName"/>
								</td>
							</nested:equal>

							<nested:notEqual property="insertFlg" value="1"><%-- 更新・参照入力時－入力不可 --%>
								<td colspan="3">
									<nested:text property="customerCd" styleId="customerCd" readonly="true" size="15" styleClass="noborderAl" tabindex="-1" />
									<nested:write property="customerName" />
								</td>
							</nested:notEqual>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td height="10"></td>
				</tr>
				<tr>
					<table border="0" cellpadding="0" cellspacing="0" width="750">
						<tr>
							<td>
								<!-- 明細部 -->
								<table width="100%" border="0">
									<tr>
										<td>
											<table width="100%" cellspacing="2" cellpadding="1" id="tblList">
												<tr class="bcTitleList fb fcTitleList">
													<td width="100px">支払予定日</td>
													<td width="100px">支払区分</td>
													<td align="right">支払予定額</td>
												</tr>

												<nested:iterate id="headerList" property="headerList" indexId="index">
													<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
														<tr class="bcList1">
													</app:modulo>

													<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
														<tr class="bcList2">
													</app:modulo>

													<td><%-- 支払予定日 --%>
														<nested:write property="strCreditScheduledDate" />
														<nested:hidden property="strCreditScheduledDate" styleId="creditScheduledDate" />
													</td>

													<td><%-- 支払区分 --%>
														<nested:write property="categoryName" />
													</td>

													<td class="alignRight"><%-- 支払予定額 --%>
														<nested:write property="strPayableAmount" />
													</td>
												</nested:iterate>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>

					<table width="750" cellspacing="2" cellpadding="1" border="0">
						<tr class="bcTitleList fb fcTitleList">
							<td class="alignRight" width="100px">支払残高</td>
							<td class="alignRight" width="100px">支払済</td>
							<td class="alignRight" width="100px">相殺額</td>
							<td class="alignRight" width="100px">支払合計</td>
							<td class="alignRight" width="100px">仕入割引</td>
							<td class="alignRight" width="100px">手数料</td>
							<td class="alignRight" width="150px">今回支払予定額</td>
						</tr>
						<tr class="bcList2">
							<td class="alignRight"><%-- 支払残高 --%>
								<nested:write property="strBalanceForward" />
							</td>
							<td class="alignRight"><%-- 支払済 --%>
								<nested:write property="strPaidAmount" />
							</td>
							<td class="alignRight"><%-- 相殺額 --%>
								<nested:write property="strOffsetAmount" />
							</td>
							<td class="alignRight"><%-- 支払合計 --%>
								<div id="lblStrPaymentAmount">
									<nested:write property="strPaymentAmount" />
								</div>
								<nested:hidden property="strPaymentAmount" styleId="strPaymentAmount" />
							</td>
							<td class="alignRight"><%-- 仕入割引 --%>
								<div id="lblPurchaseDiscountAmount">
									<nested:write property="strPurchaseDiscountAmount" />
								</div>
								<nested:hidden property="strPurchaseDiscountAmount" styleId="strPurchaseDiscountAmount" />
							</td>
							<td class="alignRight"><%-- 手数料 --%>
								<div id="lblCommission">
									<nested:write property="strCommission" />
								</div>
								<nested:hidden property="strCommission" styleId="strCommission" />
							</td>
							<td  class="alignRight"><%-- 今回支払予定額 --%>
								<div id="lblPaymentScheduledAmount">
									<nested:write property="strPaymentScheduledAmount" />
								</div>
								<nested:hidden property="strPaymentScheduledAmount" styleId="strPaymentScheduledAmount" />
							</td>
						</tr>
					</table>
				</tr>
				<tr>
					<td height="10"></td>
				</tr>
				<tr>
					<td class="alignLeft" colspan="4">
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
					</td>
				</tr>
				<tr>
					<td><%-- 複数明細部 --%>
					<table width="750" cellspacing="2" cellpadding="1" id="tblList">
						<tr class="bcTitleList fb fcTitleList">
							<nested:notEqual property="insertFlg" value="2"><%-- 新規入力・更新時－入力可 --%>
								<td rowspan="2"></td>
							</nested:notEqual>
							<td align="right" rowspan="2">行</td>
							<td>支払区分</td>
							<td align="right">支払金額</td>
							<td>科目</td>
							<td colspan="2">摘要</td>
						</tr>
						<tr class="bcTitleList fb fcTitleList">
							<td>手形種別</td>
							<td>手形番号</td>
							<td>振出日</td>
							<td colspan="2">満期日</td>
						</tr>
						<%-- 明細部>>>>> --%>
						<nested:iterate id="detailList" property="detailList" indexId="index">
							<nested:hidden property="feeFlag" styleId="feeFlag" /><%-- 振込手数料計算フラグ --%>
							<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
								<tr class="bcList1">
							</app:modulo>
							<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
								<tr class="bcList2">
							</app:modulo>
							<logic:notEqual name="paymentCsmDetailForm" property="insertFlg" value="2"><%-- 新規入力・更新時－入力可 --%>
								<td rowspan="2"><%-- 削除チェックボックス --%>
									<nested:checkbox property="deleteFlag" styleId="deleteFlag" value="<%= Constants.FLG_ON %>" />
								</td>
							</logic:notEqual>
							<td align="right" rowspan="2"><%-- 行番号 --%>
								<div id="<%="lblRowNo" + pageContext.findAttribute("index").toString() %>">
									<nested:write property="rowNo" />
								</div>
								<nested:hidden property="rowNo" styleId="<%="rowNo" + pageContext.findAttribute("index").toString() %>" />
							</td>
							<td><%-- 支払区分 --%>
								<logic:notEqual name="paymentCsmDetailForm" property="insertFlg" value="2"><%-- 新規入力・更新時－入力可 --%>
									<nested:select property="categoryDivision" styleId="<%="categoryDivision" + pageContext.findAttribute("index").toString() %>">
										<nested:optionsCollection name="paymentCsmDetailForm" property="categoryList" label="labales" value="values" />
									</nested:select>
								</logic:notEqual>
								<logic:equal name="paymentCsmDetailForm" property="insertFlg" value="2"><%-- 参照時－入力不可 --%>
									<nested:text property="viewCategoryDivision" styleId="viewCategoryDivision" size="15" readonly="true" tabindex="-1" />
								</logic:equal>
							</td>
							<td class="alignRight"><%-- 支払金額 --%>
								<logic:notEqual name="paymentCsmDetailForm" property="insertFlg" value="2"><%-- 新規入力・更新時－入力可 --%>
									<nested:text property="paymentAmount" styleId="<%="paymentAmount" + pageContext.findAttribute("index").toString() %>" maxlength="21" size="10" style="ime-mode:disabled"/>
								</logic:notEqual>
								<logic:equal name="paymentCsmDetailForm" property="insertFlg" value="2"><%-- 参照時－入力不可 --%>
									<nested:text property="paymentAmount" styleId="<%="paymentAmount" + pageContext.findAttribute("index").toString() %>" maxlength="21" size="10" readonly="true" style="text-align:right" tabindex="-1" />
								</logic:equal>
							</td>
							<td><%-- 科目 --%>
								<logic:notEqual name="paymentCsmDetailForm" property="insertFlg" value="2"><%-- 新規入力・更新時－入力可 --%>
									<nested:select property="creditTitleCd" styleId="creditTitleCd">
										<nested:optionsCollection name="paymentCsmDetailForm" property="accountList" label="labales" value="values" />
									</nested:select>
								</logic:notEqual>
								<logic:equal name="paymentCsmDetailForm" property="insertFlg" value="2"><%-- 参照時－入力不可 --%>
									<nested:text property="viewCreditTitleCd" styleId="viewCreditTitleCd" size="20" readonly="true" tabindex="-1" />
								</logic:equal>
							</td>
							<td colspan="2"><%-- 適用 --%>
								<logic:notEqual name="paymentCsmDetailForm" property="insertFlg" value="2"><%-- 新規入力・更新時－入力可 --%>
									<nested:text property="summary" styleId="<%="summary" + pageContext.findAttribute("index").toString() %>" maxlength="255" size="18" />
								</logic:notEqual>
								<logic:equal name="paymentCsmDetailForm" property="insertFlg" value="2"><%-- 参照時－入力不可 --%>
									<nested:text property="summary" styleId="summary" maxlength="255" size="18" readonly="true" tabindex="-1" />
								</logic:equal>
							</td>
						</tr>
							<nested:hidden property="noteDiv" styleId="<%="noteDiv" + pageContext.findAttribute("index").toString() %>" />
							<nested:hidden property="bankDivision" styleId="bankDivision" />
						<%-- 2行目 --%>
						<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
							<tr class="bcList1">
						</app:modulo>
						<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
							<tr class="bcList2">
						</app:modulo>
							<td><!--   手形種別  -->
								<logic:notEqual name="paymentCsmDetailForm" property="insertFlg" value="2"><%-- 新規入力・更新時－入力可 --%>
									<%
										pageContext.setAttribute("selectPaymentNoteDivision",
										new com.asahikaseieng.app.comboboxes.SelectPaymentNoteDivision(false, false));
									%>
									<nested:select property="noteDivisionString" styleId="noteDivisionString">
										<nested:options name="selectPaymentNoteDivision" property="values" labelName="selectPaymentNoteDivision" labelProperty="labels" />
									</nested:select>
								</logic:notEqual>
								<logic:equal name="paymentCsmDetailForm" property="insertFlg" value="2"><%-- 参照時－入力不可 --%>
									<nested:text property="viewPaymentNoteDivision" styleId="viewPaymentNoteDivision" size="15" readonly="true" tabindex="-1"/>
								</logic:equal>
							</td>
							<td><%-- 手形番号 --%>
								<logic:notEqual name="paymentCsmDetailForm" property="insertFlg" value="2"><%-- 新規入力・更新時－入力可 --%>
									<nested:text property="noteNo" styleId="noteNo" maxlength="20" size="20" />
								</logic:notEqual>
								<logic:equal name="paymentCsmDetailForm" property="insertFlg" value="2"><%-- 参照時－入力不可 --%>
									<nested:text property="noteNo" styleId="noteNo" maxlength="20" size="20" readonly="true" tabindex="-1" />
								</logic:equal>
							</td>
							<td><%-- 振出日 --%>
								<logic:notEqual name="paymentCsmDetailForm" property="insertFlg" value="2"><%-- 新規入力・更新時－入力可 --%>
									<nested:text property="drawalDateString" styleId="drawalDateString" maxlength="10" size="10" />
								</logic:notEqual>
								<logic:equal name="paymentCsmDetailForm" property="insertFlg" value="2"><%-- 参照時－入力不可 --%>
									<nested:text property="drawalDateString" styleId="drawalDateString" maxlength="10" size="10" readonly="true" tabindex="-1" />
								</logic:equal>
							</td>
							<td><%-- 満期日 --%>
								<logic:notEqual name="paymentCsmDetailForm" property="insertFlg" value="2"><%-- 新規入力・更新時－入力可 --%>
									<nested:text property="noteDateString" styleId="noteDateString" maxlength="10" size="10" />
								</logic:notEqual>
								<logic:equal name="paymentCsmDetailForm" property="insertFlg" value="2"><%-- 参照時－入力不可 --%>
									<nested:text property="noteDateString" styleId="noteDateString" maxlength="10" size="10" readonly="true" tabindex="-1"/>
								</logic:equal>
							</td>
							<td><%-- 手形サイト --%>
								<logic:equal name="detailList" property="noteDiv" value="1">
									<nested:write name="paymentCsmDetailForm" property="noteSight" />日
									<nested:hidden name="paymentCsmDetailForm" property="noteSight" styleId="<%="noteSight" + pageContext.findAttribute("index").toString() %>" />
								</logic:equal>
							</td>
						</tr>
						</nested:iterate>
						<tr>
							<logic:notEqual name="paymentCsmDetailForm" property="insertFlg" value="2"><%-- 新規入力・更新時－入力可 --%>
								<td></td>
							</logic:notEqual>
							<td></td>
							<td align="right" class="fcTitleDetail fb bcTitleDetail">支払合計</td>
							<td align="right">
								<nested:text name="paymentCsmDetailForm" property="strSumAmount" styleId="strSumAmount" size="10" readonly="true" tabindex="-1" style="text-align:right"/>
							</td>
							<td align="right" class="fcTitleDetail fb bcTitleDetail">繰越残</td>
							<td>
								<nested:text name="paymentCsmDetailForm" property="strBalanceCarriedForward" styleId="strBalanceCarriedForward" size="10" readonly="true" tabindex="-1" style="text-align:right"/>
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
													&nbsp;&nbsp;削&nbsp;&nbsp;除&nbsp;&nbsp;
												</a>
											</div>
										</td>
									</nested:equal>
	
									<td width="50"><br></td>
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
