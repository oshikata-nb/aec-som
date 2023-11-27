<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taginclude.jsf"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http//www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<%@ include file="/jsp/common/head.jsf"%>
<%@ include file="/jsp/common/head_main.jsf"%>
<%@ include file="/jsp/common/clearText.jsf" %>
<%@ include file="/jsp/common/searchNameForMaster.jsf" %>

<!-- Style Sheet -->
<link href='<%= request.getContextPath() + "/stylesheets/style.css"%>'
	media="screen" rel="Stylesheet" type="text/css" />

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

		<%-- 包装ラインのauto complete --%>
		if ($('directionDivision').value == "2") {
			new Ajax.Autocompleter(
				"packageLine",
				"autocomplete_selection",
				"<%= request.getContextPath() %>/PkgDirectionRecipeAsprovaForAutoComplete.do?op=searchRecipeAsprova",
				{
					paramName : "code",
					callback : packageLineCallBack,
					afterUpdateElement : setPackageLineLabel
				}
			);
		} else {	
			new Ajax.Autocompleter(
				"packageLine",
				"autocomplete_selection",
				"<%= request.getContextPath() %>/PkgDirectionRecipeResouceForAutoComplete.do?op=searchPackageLine",
				{
					paramName : "code",
					callback : packageLineCallBack,
					afterUpdateElement : setPackageLineLabel
				}
			);
		}
		Event.observe('packageLine', 'keypress', clearText.bindAsEventListener($('packageLineName')), false);
		Event.observe('packageLine', 'keypress', clearLabel.bindAsEventListener($('packageLineName')), false);

		<%-- 品目のauto complete --%>
		if ($('directionDivision').value == "2") {
		
			new Ajax.Autocompleter(
		  		"itemCd",
		  		"autocomplete_selection",
		  		"<%= request.getContextPath() %>/PkgDirectionItemForAutoComplete.do?op=searchDetailItem",
		  		{
		  			paramName : "code",
		    		afterUpdateElement : setItemLabel
		  		}
			);
		} else {
			new Ajax.Autocompleter(
		  		"itemCd",
		  		"autocomplete_selection",
		  		"<%= request.getContextPath() %>/PkgDirectionItemForAutoComplete.do?op=searchDetailItemRepack",
		  		{
		  			paramName : "code",
		    		afterUpdateElement : setItemLabel
		  		}
			);
		}
		Event.observe('itemCd', 'keypress', clearText.bindAsEventListener($('itemName')), false);
		Event.observe('itemCd', 'keypress', clearLabel.bindAsEventListener($('itemName')), false);
		Event.observe('itemCd', 'keypress', clearText.bindAsEventListener($('otherCompanyCd1')), false);
		Event.observe('itemCd', 'keypress', clearText.bindAsEventListener($('styleOfPacking')), false);
		Event.observe('itemCd', 'keypress', clearLabel.bindAsEventListener($('styleOfPacking')), false);
		Event.observe('itemCd', 'keypress', clearText.bindAsEventListener($('unitName')), false);
		Event.observe('itemCd', 'keypress', clearLabel.bindAsEventListener($('unitName')), false);
		Event.observe('itemCd', 'change', clearText.bindAsEventListener($('recipeCdVersion')), false);
		Event.observe('itemCd', 'change', clearText.bindAsEventListener($('recipeName')), false);
		Event.observe('itemCd', 'change', clearLabel.bindAsEventListener($('recipeName')), false);

		<%-- 他社コード１のauto complete --%>
		new Ajax.Autocompleter(
		  "otherCompanyCd1",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/PkgDirectionItemForAutoComplete.do?op=searchDetailItemOtherCompany",
		  {
		  	paramName : "code",
		    afterUpdateElement : setOtherCompany1Label
		  }
		);
		Event.observe('otherCompanyCd1', 'keypress', clearText.bindAsEventListener($('itemName')), false);
		Event.observe('otherCompanyCd1', 'keypress', clearLabel.bindAsEventListener($('itemName')), false);
		Event.observe('otherCompanyCd1', 'keypress', clearText.bindAsEventListener($('itemCd')), false);
		Event.observe('otherCompanyCd1', 'keypress', clearText.bindAsEventListener($('styleOfPacking')), false);
		Event.observe('otherCompanyCd1', 'keypress', clearLabel.bindAsEventListener($('styleOfPacking')), false);
		Event.observe('otherCompanyCd1', 'keypress', clearText.bindAsEventListener($('unitName')), false);
		Event.observe('otherCompanyCd1', 'keypress', clearLabel.bindAsEventListener($('unitName')), false);
		Event.observe('otherCompanyCd1', 'change', clearText.bindAsEventListener($('recipeCdVersion')), false);
		Event.observe('otherCompanyCd1', 'change', clearText.bindAsEventListener($('recipeName')), false);
		Event.observe('otherCompanyCd1', 'change', clearLabel.bindAsEventListener($('recipeName')), false);

		<%-- 基本処方コードのauto complete --%>
		new Ajax.Autocompleter(
		  "recipeCdVersion",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/PkgdirectionRecipeHeaderForAutoComplete.do?op=searchRecipeHeader",
		  {
		  	paramName : "code",
		  	callback : recipeCdVersionCallBack,
		    afterUpdateElement : setRecipeCdLabel
		  }
		);
		Event.observe('recipeCdVersion', 'keypress', clearText.bindAsEventListener($('recipeName')), false);
		Event.observe('recipeCdVersion', 'keypress', clearLabel.bindAsEventListener($('recipeName')), false);
		Event.observe('recipeCdVersion', 'keypress', clearText.bindAsEventListener($('recipeId')), false);
		
		<%-- 必須フィールド定義 --%>
		defineAsRequiredField("planedQty");
		defineAsRequiredField("itemCd");
		defineAsRequiredField("planedSDay");
		defineAsRequiredField("planedEDay");
		defineAsRequiredField("planedSTime");
		defineAsRequiredField("planedETime");
		if ($('directionDivision').value == "2") {
			defineAsRequiredField("recipeCd");
			defineAsRequiredField("packageLine");
		}

		<%-- 日付型フィールド定義 --%>
		defineAsDateField("planedSDay");
		defineAsDateField("planedEDay");

		<%-- 時間型フィールド定義 --%>
		defineAsTimeField("planedSTime");
		defineAsTimeField("planedETime");

		<%-- 数値型フィールド定義 --%>
		defineAsNumberField("planedQty");

<!--		<%-- 検索後入力された場合の不整合をただす --%>-->
		storeInitValues(/^srh.*/, 'dirtyFlg');
		refreshLabel();

	}, false);

	<%-- 包装ラインのオートコンプリート実行時検索キー取得取得 --%>
	function packageLineCallBack(editor,paramText){
        return paramText + '&recipeId=' + $('recipeId').value
        				 + '&productionLine='+ $('productionLine').value;
    }

	<%-- 基本処方のオートコンプリート実行時検索キー項目取得 --%>
	function recipeCdVersionCallBack(editor,paramText){
		return paramText + '&srhProductionLine=' + $('productionLine').value
		                 + '&srhItemCd=' + $('itemCd').value
		                 + '&srhOtherCompanyCd1='+ $('otherCompanyCd1').value;
	}

	<%-- 確認メッセージ --%>
	function putConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "登録してよろしいですか？";

		return confirm(alertMsg[0]);
	}

	<%-- 変更フラグセット --%>
		function putDirtyConfirm() {
		var flg = $("dirtyFlg").value;
		if (flg == "true") {
			// 何か値が変更されている場合

			return continueConfirm();
		}else{
			return true;
		}
	}

	<%-- 変更フラグセット --%>
	function setDirtyFlg() {
		$("dirtyFlg").value = true;
	}

	<%-- 包装ラインauto completeの選択後処理 --%>
	function setPackageLineLabel(text, li) {
	    $("lblPackageLineName").update(li.getElementsByTagName('span')[0].innerText);
	    $("packageLineName").value = li.getElementsByTagName('span')[0].innerText;
	}

	<%-- 品目名称auto completeの選択後処理 --%>
	function setItemLabel(text, li) {
	    $("lblItemName").update(li.getElementsByTagName('span')[0].innerText);
	    $("itemName").value = li.getElementsByTagName('span')[0].innerText;
	    $("otherCompanyCd1").value = getHiddenValue(li,"otherCompanyCd1");
	    $("lblStyleOfPacking").update(getHiddenValue(li,"styleOfPacking"));
	    $("styleOfPacking").value = getHiddenValue(li,"styleOfPacking");
	    $("lblUnitName").update(getHiddenValue(li, "name01"));
	    $("unitName").value = getHiddenValue(li, "name01");
	}
	
	<%-- 他社コード１auto completeの選択後処理 --%>
	function setOtherCompany1Label(text, li) {
	    $("lblItemName").update(li.getElementsByTagName('span')[0].innerText);
	    $("itemName").value = li.getElementsByTagName('span')[0].innerText;
	    $("itemCd").value = getHiddenValue(li,"code");
	    $("lblStyleOfPacking").update(getHiddenValue(li,"styleOfPacking"));
	    $("styleOfPacking").value = getHiddenValue(li,"styleOfPacking");
	    $("lblUnitName").update(getHiddenValue(li, "name01"));
	    $("unitName").value = getHiddenValue(li, "name01");
	}

	<%-- 基本処方コードauto completeの選択後処理 --%>
	function setRecipeCdLabel(text, li) {
	    $("lblRecipeName").update(li.getElementsByTagName('span')[0].innerText);
	    $("recipeName").value = (li.getElementsByTagName('span')[0].innerText);
	    $("recipeCdVersion").value = getHiddenValue(li,"code");
	    $("itemCd").value = getHiddenValue(li,"itemCd");
	    $("lblItemName").update(getHiddenValue(li,"itemName"));
	    $("itemName").value = getHiddenValue(li,"itemName");
	    $("otherCompanyCd1").value = getHiddenValue(li,"otherCompanyCd1");
	    $("lblStyleOfPacking").update(getHiddenValue(li,"styleOfPacking"));
	    $("styleOfPacking").value = getHiddenValue(li,"styleOfPacking");
	    $("recipeId").value = getHiddenValue(li,"recipeId");
	    $("lblUnitName").update(getHiddenValue(li, "name01"));
	    $("unitName").value = getHiddenValue(li, "name01");
	}
	
	<%-- 行追加・行削除 --%>
	function changeLine(msg) {
		if (msg == "del" && !noCheck()) {
		  return false;
		}
		setDirtyFlg();
		return true;
	}
	
	function reDraw() {
		return form_submit('op', 'reDraw');
	}

	<%-- planedQtyのフォーマット --%>
	function formatPlanedQty(obj) {
		var value = obj.value;
		if (value != null) {
			var decimalPoint = $("decimalPoint").value;
			var roundDivision = $("roundDivision").value;
			obj.value = digitStringFormat(decimalPoint,roundDivision,value);
		}
	}

	<%-- チェックボックス選択無しチェック --%>
	function noCheck(){
		var count = $F("count");
		var checkFlag = false;	  
		for (var i = 0; i < count; i++) {
			var item = $("directionDetailList[" + i + "].checkFlg");
			if(item.checked){
				checkFlag = true;
				break;
			}
		}
		if (!checkFlag) {
		    alert("削除対象がありません。");
		    return false;
		}
		return true;
	}


</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/PkgDirectionHeader" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="dirtyFlg" />
	<nested:hidden property="count" styleId="count" />
	<nested:hidden property="recipeId" styleId="recipeId"/>
	<nested:hidden property="decimalPoint" styleId="decimalPoint" />
	<nested:hidden property="roundDivision" styleId="roundDivision" />

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
							<td class="fcTitle fb" width="250">包装指図新規入力</td>
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
					<td><!-- 明細部 -->
					<table border="0" cellspacing="" cellpadding="" width="750">
						<tr>
							<td>
							<table border="0" cellspacing="2" cellpadding="1">
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">指図区分</td>
									<td width="130">
										<%
											pageContext.setAttribute( "selectPkgDirectionDirectionDivision",
											new com.asahikaseieng.app.comboboxes.SelectPkgDirectionDirectionDivision(false, false));
										%>
										<nested:select property="directionDivision" onchange="reDraw();" styleId="directionDivision">
											<nested:options name="selectPkgDirectionDirectionDivision" property="values" labelName="selectPkgDirectionDirectionDivision" labelProperty="labels" />
										</nested:select>
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">包装指図番号</td>
									<td>
										<div id="directionNo">
										</div>
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">生産工場</td>
									<td width="130">
										<nested:select property="productionLine" style="margin: 0;padding: 0" styleId="productionLine" onchange="setDirtyFlg();" >
											<nested:optionsCollection property="lineCombo" label="labales" value="values" />
										</nested:select>
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">包装ライン</td>
									<td><nested:text property="packageLine" maxlength="10" size="20" styleId="packageLine" onchange="setDirtyFlg();" style="ime-mode:disabled" />
										<span id="lblPackageLineName" >
											<nested:write property="packageLineName" />
										</span>
										<nested:hidden property="packageLineName" styleId="packageLineName" />
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">品目コード</td>
									<td><nested:text property="itemCd" maxlength="20" size="20" styleId="itemCd" onchange="setDirtyFlg();" />
										<div id="autocomplete_selection" class="autocomplete"></div>
										<span id="lblItemName" >
											<nested:write property="itemName" />
										</span>
										<nested:hidden property="itemName" styleId="itemName" />
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">他社コード１</td>
									<td><nested:text property="otherCompanyCd1" maxlength="20" size="20" styleId="otherCompanyCd1" onchange="setDirtyFlg();" /></td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">基本処方コード</td>
									<td><nested:text property="recipeCdVersion" maxlength="20" size="20" styleId="recipeCd" onchange="setDirtyFlg();" />
										<span id="lblRecipeName" >
											<nested:write property="recipeName" />
										</span>
										<nested:hidden property="recipeName" styleId="recipeName" />
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">生産予定数量</td>
									<td><nested:text property="planedQty" maxlength="22" size="20" styleId="planedQty" onchange="setDirtyFlg();" onblur="return formatPlanedQty(this);" style="ime-mode:disabled" />
										<span id="lblUnitName" >
											<nested:write property="unitName" />
										</span>
										<nested:hidden property="unitName" styleId="unitName" />
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">荷姿</td>
									<td>
										<div id="lblStyleOfPacking" >
											<nested:write property="styleOfPacking" />
										</div>
										<nested:hidden property="styleOfPacking" styleId="styleOfPacking" />
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">包装開始予定日時</td>
									<td>
										<nested:text property="planedSDay" maxlength="10" size="10" styleId="planedSDay" onchange="setDirtyFlg();" style="ime-mode:disabled" />
										<nested:text property="planedSTime" maxlength="5" size="10" styleId="planedSTime" onchange="setDirtyFlg();" style="ime-mode:disabled" />
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">包装終了予定日時</td>
									<td>
										<nested:text property="planedEDay" maxlength="10" size="10" styleId="planedEDay" onchange="setDirtyFlg();" style="ime-mode:disabled" />
										<nested:text property="planedETime" maxlength="5" size="10" styleId="planedETime" onchange="setDirtyFlg();" style="ime-mode:disabled" />
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">ロット番号</td>
									<td><nested:text property="lotNo" maxlength="20" size="20" styleId="lotNo" onchange="setDirtyFlg();" style="ime-mode:disabled" /></td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">製造指図番号</td>
									<td></td>
								</tr>
							</table>
							</td>
						</tr>

						<table cellspacing="2" cellpadding="1"  border="0" width="300">
							<tr class="bcTitleList fb fcTitleList">
								<td width="20"><br></td>
								<td width="105">行番号</td>
								<td>製造指図番号</td>
							</tr>
							<nested:notEmpty property="directionDetailList">
								<nested:iterate id="directionDetailList" property="directionDetailList" indexId="index">
									<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
										<tr class="bcList1">
									</app:modulo>
									<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
										<tr class="bcList2">
									</app:modulo>

									<%-- チェックボックス --%>
									<td>
										<nested:checkbox property="checkFlg" styleId="<%="checkFlg" + pageContext.findAttribute("index").toString() %>" />
									</td>
									<td class="alignRight">
										<nested:write property="rowNo" />
									</td>
									<td>
										<nested:text property="directionNo" maxlength="20" size="20" styleId="<%="directionNo" + pageContext.findAttribute("index").toString() %>" style="ime-mode:disabled" onchange="setDirtyFlg();" />
									</td>
									</tr>
								</nested:iterate>
							</nested:notEmpty>
						</table>                                                                                                                                                                                          
						<table cellspacing="2" cellpadding="1"  border="0" width="300">
							<tr>
								<td class="alignCenter">
									<table cellpadding="0" cellspacing="0" width="80%" border="0">
										<tr>
											<td class="alignLeft">
											<div id="cssButton"><a href="#" onclick="if (!(changeLine('add'))) {return false;}else{return form_submit('op', 'addlist'); return false;}"
												class="cssButton">&nbsp;&nbsp;行追加&nbsp;&nbsp;</a></div>
											</td>
											<td class="alignRight">
											<div id="cssButton"><a href="#" onclick="if (!(changeLine('del'))) {return false;}else{return form_submit('op', 'dellist'); return false;}"
												class="cssButton">&nbsp;&nbsp;行削除&nbsp;&nbsp;</a></div>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>

						<tr><td><br></td></tr>

						<table cellspacing="2" cellpadding="1"  border="0" width="750">
							<tr>
								<td>
								<span style="clear:left;">
										<table cellspacing="2" cellpadding="1" border="0">
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail" width="130" >備考</td>
												<td>
													<nested:textarea property="remark" cols="70" rows="4" styleId="remark" onchange="setDirtyFlg();" />
												</td>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail" width="130" >注釈</td>
												<td>
													<nested:textarea property="notes" cols="70" rows="4" styleId="notes" onchange="setDirtyFlg();" />
												</td>
											</tr>
										</table>
								</span>
								</td>
							</tr>
						</table>

						<tr>
							<td class="alignCenter">
								<table border="0" cellspacing="0" cellpadding="0" width="100%">
									<nested:equal property="updateAuthority" value="true">
										<td class="alignRight">
											<div id="cssButton">
												<a href="#" onclick="if (!(putConfirm())) {return false;}else{return form_submit('op', 'insert'); return false;}" class="cssButton">
												&nbsp;&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;録&nbsp;&nbsp;
												</a>
											</div>
										</td>
									</nested:equal>
									<td class="alignLeft">
										<div id="cssButton">
											<a href="#" onclick="if (!(putDirtyConfirm())) {return false;}else{return form_submit('op', 'back'); return false;}" class="cssButton">
												&nbsp;&nbsp;戻&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;る&nbsp;&nbsp;
											</a>
										</div>
									</td>
								</table>
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
