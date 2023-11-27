<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taginclude.jsf"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http//www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<%@ include file="/jsp/common/head.jsf"%>
<%@ include file="/jsp/common/head_main.jsf"%>
<%@ include file="/jsp/common/clearText.jsf" %>
<%@ include file="/jsp/common/searchNameForMaster.jsf" %>

<link href='<%= request.getContextPath() + "/stylesheets/style.css"%>'
	media="screen" rel="Stylesheet" type="text/css" />

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
		<logic:equal name="pkgDirectionListForm" property="searchList" value="false">
			initializeFormState();
		</logic:equal>

		<%-- 品目名称のauto complete --%>
		if ($('directionDivision').value == "2") {

			new Ajax.Autocompleter(
		  	"itemCd",
		  	"autocomplete_selection",
		  	"<%= request.getContextPath() %>/PkgDirectionItemForAutoComplete.do?op=searchItem",
		  {
		  	paramName : "code",
		    afterUpdateElement : setItemQueueLabel
		  }
		);
		} else {
			new Ajax.Autocompleter(
		  		"itemCd",
		  		"autocomplete_selection",
		  		"<%= request.getContextPath() %>/PkgDirectionItemForAutoComplete.do?op=searchItemRepack",
		  		{
		  			paramName : "code",
		    afterUpdateElement : setItemQueueLabel
		  		}
			);
		}

		<%-- 他社コード１のauto complete --%>
		new Ajax.Autocompleter(
		  "otherCompanyCd1",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/PkgDirectionItemForAutoComplete.do?op=searchItemOtherCompany",
		  {
		  	paramName : "code",
		    afterUpdateElement : setOtherCompany1Label
		  }
		);

		<%-- 必須フィールド定義 --%>
		<%-- 日付型フィールド定義 --%>
		defineAsDateField("planedSDayFrom");
		defineAsDateField("planedSDayTo");
		defineAsDateField("planedEDayFrom");
		defineAsDateField("planedEDayTo");
		<%-- 時間型フィールド定義 --%>
		defineAsTimeField("planedSTimeFrom");
		defineAsTimeField("planedSTimeTo");
		defineAsTimeField("planedETimeFrom");
		defineAsTimeField("planedETimeTo");

		<%-- 明細部 --%>
		var tblList = $('tblList');

		<%-- 数値型フィールド定義 --%>
		if (tblList != null) {
			if (0 < tblList.rows.length) {
				<%-- ラベル数 --%>
				for (i = 0; null != $("labelCount" + i); i++) {
					defineAsNumberField("labelCount" + i);
				}
			}
		}

		<%-- 検索後入力された場合の不整合をただす --%>
		storeInitValues(/^srh.*/, 'dirtyFlg');
		refreshLabel();

		Event.observe('itemCd', 'keypress', clearText.bindAsEventListener($('srhItemNameHidden')), false);

		errMsgPopup();

	}, false);

	<%-- チェックボックス選択無しチェック --%>
	function noCheck(index){
	  var count = 0;
	  var item = document.pkgDirectionListForm.checkFlg;
	  var len = item.length;

	  // 明細１件のみの場合
	  if (len == undefined) {
	    //if(item.disabled == false){
	      if(item.checked){
		    count = count + 1;
          }
		//}
	  } else {
	    for (var i = 0; i < len; i++) {
          if(item[i].checked){
		    count = count + 1;
		    break;
		  }
	    }
	  }

	  if (count == 0) {
	    if (index == 1) {
	      alert("包装指図書発行対象が選択されていません。");
	    } else if (index == 2) {
	      alert("ラベル発行対象が選択されていません。");
	    } else {
	      alert("原材料出庫指図書発行対象が選択されていません。");
		}
	    return false;
	  }
	  return true;
	}

	<%-- 包装指図書発行メッセージ --%>
	function issueDirectionAlert() {
		if (!noCheck(1)) {
		  return false;
		}

		alertMsg = new Array();
		alertMsg[0] = "包装指図書を発行してよろしいですか？";
		return confirm(alertMsg[0]);
	}
	<%-- 包装指図書発行メッセージ --%>
	function issueDirectionAlert1() {
		if (!noCheck(1)) {
		  return false;
		}

		alertMsg = new Array();
		alertMsg[0] = "包装指図書発行を強行してよろしいですか？";
		return confirm(alertMsg[0]);
	}
	<%-- 包装指図書発行メッセージ --%>
	function issueDirectionAlert2() {
		if (!noCheck(1)) {
		  return false;
		}

		alertMsg = new Array();
		alertMsg[0] = "包装指図書発行を中止してよろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- ラベル発行確認メッセージ --%>
	function issueLabelAlert() {
		var labelCount = 0;
		var item = document.pkgDirectionListForm.checkFlg;
		var len = item.length;

		if (!noCheck(2)) {
		  return false;
		}
	  	if (len == undefined) {
			if(item.checked){
				var buf =  $F("labelCount0");
				if( !blank(buf)){
					var buf2 = parseFloat(buf.replace(/,/g, ""));
					if( !isNaN(buf2) ){
						labelCount = labelCount + buf2;
					}
				}
			}
		} else {
			for (var i = 0; i < len; i++) {
				if(item[i].checked){
					var buf =  $F("labelCount" + i);
					if( !blank(buf)){
						var buf2 = parseFloat(buf.replace(/,/g, ""));
						if( !isNaN(buf2) ){
							labelCount = labelCount + buf2;
						}
					}
				}
			}
		}

		alertMsg = new Array();
		alertMsg[0] = "ラベル枚数" + labelCount + "枚発行してもよろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- 原材料出庫指図書発行確認メッセージ --%>
	function issueMaterialAlert() {
		if (!noCheck(3)) {
		  return false;
		}
		alertMsg = new Array();
		alertMsg[0] = "原材料出庫指図書を発行してよろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- 品目名称auto completeの選択後処理 --%>
	function setItemQueueLabel(text, li) {
	    $("srhItemNameHidden").value = li.getElementsByTagName('span')[0].innerText;
	    $("otherCompanyCd1").value = getHiddenValue(li,"otherCompanyCd1");
	}

	<%-- 他社コード１auto completeの選択後処理 --%>
	function setOtherCompany1Label(text, li) {
	    $("srhItemNameHidden").value = li.getElementsByTagName('span')[0].innerText;
	    $("itemCd").value = getHiddenValue(li,"code");
	}

	<%-- チェックボックス　全設定 --%>
	function allCheck(flg) {
        for (var i=0; i < $("count").value; i++) {
        	$("searchList[" + i + "].checkFlg").checked = flg;
        }
	}

	function reDraw() {
		return form_submit('op', 'reDraw');
	}

	function errMsgPopup() {
		var errMsg = $("errMsg").value;
		if (errMsg != "") {
			alert(errMsg);
			$("errMsg").value = "";
			form_submit('op', 'reFresh');
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

<nested:form action="/PkgDirectionList" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="errMsg" styleId="errMsg" />
	<nested:hidden property="insufficientFlag" styleId="insufficientFlag" />
	<nested:define id="flag" property="insufficientFlag" />

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
							<td class="fcTitle fb" width="250" >包装指図</td>
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
					<td><!-- 検索部 -->
					<table border="0" cellspacing="0" cellpadding="0" width="100%">
						<tr>
							<td>
								<table border="0" cellspacing="1" cellpadding="1">
									<tr>
										<td class="bcTitleSearch fb fcTitleSearch" width="120">指図区分</td>
										<td width="150">
											<%
												pageContext.setAttribute( "selectPkgDirectionDirectionDivision",
												new com.asahikaseieng.app.comboboxes.SelectPkgDirectionDirectionDivision(false, false));
											%>
											<nested:select property="directionDivision" styleId="srhDirectionDivision"  >
												<nested:options name="selectPkgDirectionDirectionDivision" property="values" labelName="selectPkgDirectionDirectionDivision" labelProperty="labels" />
											</nested:select>
										</td>
										<td class="bcTitleSearch fb fcTitleSearch" width="100">包装指図番号</td>
										<td width="50">
											<nested:text property="directionNo" maxlength="20" size="20" styleId="srhDirectionNo" style="ime-mode:disabled" />
										</td>
										<td class="bcTitleSearch fb fcTitleSearch" width="100">生産工場</td>
										<td width="100">
											<nested:select property="productionLine" style="margin: 0;padding: 0" styleId="srhProductionLine" >
												<nested:optionsCollection property="lineCombo" label="labales" value="values" />
											</nested:select>
										</td>
									</tr>
									<tr>
										<td class="bcTitleSearch fb fcTitleSearch" width="120" >品目</td>
										<td >
											<nested:text property="itemCd" maxlength="20" size="20" styleId="srhItemCd" />
											<div id="autocomplete_selection" class="autocomplete"></div>
										</td>
										<td colspan="3">
											<nested:text property="itemName" maxlength="40" size="40" styleId="srhItemNameHidden" readonly="true" styleClass="noborderAl" tabindex="-1" />
										</td>
									</tr>
									<tr>
										<td class="bcTitleSearch fb fcTitleSearch" width="100">ステータス</td>
										<td width="50">
											<%
												pageContext.setAttribute( "selectPkgDirectionDirectionStatus",
												new com.asahikaseieng.app.comboboxes.SelectPkgDirectionDirectionStatus(true, false));
											%>
											<nested:select property="directionStatus" styleId="srhDirectionStatus">
												<nested:options name="selectPkgDirectionDirectionStatus" property="values" labelName="selectPkgDirectionDirectionStatus" labelProperty="labels" />
											</nested:select>
										</td>
										<td class="bcTitleSearch fb fcTitleSearch">他社コード１</td>
										<td>
											<nested:text property="otherCompanyCd1" maxlength="10" size="20" styleId="srhOtherCompanyCd1" style="ime-mode:disabled" />
										</td>
										<td class="bcTitleSearch fb fcTitleSearch" width="80">包装ライン</td>
										<td>
											<nested:text property="packageLine" maxlength="20" size="20" styleId="srhPackageLine" style="ime-mode:disabled"/>
										</td>
									</tr>
								</table>
								<table border="0" cellspacing="1" cellpadding="1">
									<tr>
										<td class="bcTitleSearch fb fcTitleSearch" width="140">包装開始予定日時</td>
										<td>
											<nested:text property="planedSDayFrom" maxlength="10" size="12" styleId="srhPlanedSDayFrom" style="ime-mode:disabled" />
											<nested:text property="planedSTimeFrom" maxlength="5" size="7" styleId="srhPlanedSTimeFrom" style="ime-mode:disabled" />～
											<nested:text property="planedSDayTo" maxlength="10" size="12" styleId="srhPlanedSDayTo" style="ime-mode:disabled" />
											<nested:text property="planedSTimeTo" maxlength="5" size="7" styleId="srhPlanedSTimeTo" style="ime-mode:disabled" />
										</td>
									</tr>
									<tr>
										<td class="bcTitleSearch fb fcTitleSearch" width="140">包装終了予定日時</td>
										<td>
											<nested:text property="planedEDayFrom" maxlength="10" size="12" styleId="srhPlanedEDayFrom" style="ime-mode:disabled" />
											<nested:text property="planedETimeFrom" maxlength="5" size="7" styleId="srhPlanedETimeFrom" style="ime-mode:disabled" />～
											<nested:text property="planedEDayTo" maxlength="10" size="12" styleId="srhPlanedEDayTo" style="ime-mode:disabled" />
											<nested:text property="planedETimeTo" maxlength="5" size="7" styleId="srhPlanedETimeTo" style="ime-mode:disabled" />
										</td>
									</tr>
									<tr>
										<td class="bcTitleSearch fb fcTitleSearch" width="140">ASPオーダーコード</td>
										<td colspan="3">
											<nested:text property="aspOrderNo" maxlength="50" size="50" styleId="srhAspOrderNo" style="ime-mode:disabled"/>
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
											<div id="cssButton">
											<a href='<%= request.getContextPath() + "/PkgDirectionHeader.do?op=init"%>'
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
				<nested:hidden property="count" />
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td>
							<table cellspacing="1" cellpadding="1"  border="0" width="100%">
								<tr>
									<td class="alignLeft">
									<table cellspacing="1" cellpadding="1" border="0">
										<tr>
											<td>
												<nested:notEqual property="insufficientFlag" value="true">
												<div id="cssButton"><a href="#" onclick="allCheck(true); return false;" class="cssButton"> &nbsp;&nbsp;全チェック&nbsp;&nbsp;
												</a></div>
												</nested:notEqual>
											</td>
											<td>
												<nested:notEqual property="insufficientFlag" value="true">
												<div id="cssButton"><a href="#" onclick="allCheck(false); return false;" class="cssButton"> &nbsp;&nbsp;全チェック解除&nbsp;&nbsp;
												</a></div>
												</nested:notEqual>
											</td>
										</tr>
									</table>
									</td>
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
						<td><!-- 明細部 -->
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<tr>
								<td class="alignCenter">
								<table cellspacing="2" cellpadding="1" id="tblList">
									<tr class="bcTitleList fb fcTitleList">
										<td rowspan="3"></td>
										<td width="100">包装指図番号</td>
										<td width="140">包装開始予定日時</td>
										<td width="80">品目コード</td>
										<td colspan="3">品目名称</td>
										<td colspan="3">生産工場</td>
									</tr>
									<tr class="bcTitleList fb fcTitleList">
										<td width="100">製造指図番号</td>
										<td width="140">包装終了予定日時</td>
										<td width="80">ロット番号</td>
										<td width="120">生産予定数量</td>
										<td width="40">単位</td>
										<td width="80">荷姿</td>
										<td width="80">ラベル枚数</td>
										<td width="40">発行</td>
										<td width="50">ｽﾃｰﾀｽ</td>
									</tr>
									<tr class="bcTitleList fb fcTitleList">

										<td width="170" colspan="2">ASPオーダーコード</td>
										<td width="50" >包装ライン</td>
										<td width="540" colspan="6"></td>
									</tr>
									<nested:iterate id="searchList" property="searchList" indexId="index">
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
											<tr class="bcList1">
										</app:modulo>

										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
											<tr class="bcList2">
										</app:modulo>
												<nested:define id="dirNo" property="directionNo" />
												<nested:define id="dirDiv" property="directionDivision" />
												<nested:hidden property="directionStatus" styleId='<%="directionStatus" + pageContext.findAttribute("index").toString() %>' />
												<nested:hidden property="directionNo" styleId='<%="directionNo" + pageContext.findAttribute("index").toString() %>' />
												<nested:hidden property="directionDivision" styleId='<%="directionDivision" + pageContext.findAttribute("index").toString() %>' />
												<nested:hidden property="itemName" styleId='<%="itemName" + pageContext.findAttribute("index").toString() %>' />

												<td rowspan="3">
													<nested:equal  name="flag" value="true">
														<nested:checkbox property="checkFlg" styleId="checkFlg" disabled="true" />
													</nested:equal>
													<nested:notEqual  name="flag" value="true">
														<nested:checkbox property="checkFlg" styleId="checkFlg" />
													</nested:notEqual>
												</td>
												<%-- 包装指図番号 --%>
												<td>
													<html:link href="#" onclick='<%="toDetail('" + request.getContextPath() + "/PkgDirectionHeaderDetail.do?op=init&directionNo=" + pageContext.findAttribute("dirNo").toString()
															                                     + "&directionDivision=" + pageContext.findAttribute("dirDiv").toString() + "'); return false;"%>'>
														<nested:write property="directionNo" />
													</html:link>
												</td>
												<%-- 包装開始予定日時 --%>
												<td>
													<nested:write property="packPlanSdate" />
												</td>
												<%-- 品目コード --%>
												<td>
													<nested:write property="itemCd" />
												</td>
												<%-- 品目名称 --%>
												<td colspan="3">
													<nested:write property="itemName" />
												</td>
												<%-- 生産工場 --%>
												<td colspan="3">
													<nested:write property="productionLineName" />
												</td>
											</tr>
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
											<tr class="bcList1">
										</app:modulo>

										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
											<tr class="bcList2">
										</app:modulo>

												<%-- 製造指図詳細 --%>
												<td>
													<!-- 20211209 Asclab Kouchi EdgeIE対応 -->
													<html:link href ="#" onclick='<%="open_modal_popup_edge(600, 600,'PkgDirectionDirectionDetailSearch', '',"
																		+ "'pkgDirectionNo',$('directionNo" + pageContext.findAttribute("index").toString() + "').value,"
																		+ "'directionDivision',$('directionDivision" + pageContext.findAttribute("index").toString() + "').value,"
																		+ "'itemName',$('itemName" + pageContext.findAttribute("index").toString() + "').value); return false;"%>'>
													製造指図詳細
													</html:link>
												</td>
												<%-- 包装終了予定日時 --%>
												<td>
													<nested:write property="packPlanEdate" />
												</td>
												<%-- ロット番号 --%>
												<td class="alignLeft">
													<nested:lessThan property="jissekiFlag" value="1">
														<%-- nested:text property="lotNo" maxlength="20" size="7" styleId="<%="lotNo" + pageContext.findAttribute("index").toString() %>" style="ime-mode:disabled" / --%>
														<nested:text property="lotNo" maxlength="20" size="7" styleId='<%="lotNo" + pageContext.findAttribute("index").toString() %>' style="ime-mode:disabled" />
													</nested:lessThan>
													<nested:equal property="jissekiFlag" value="1">
														<nested:text property="lotNo" maxlength="20" size="7" styleId='<%="lotNo" + pageContext.findAttribute("index").toString() %>' readonly="true" />
													</nested:equal>
												</td>
												<%-- 生産予定数量 --%>
												<td class="alignRight">
													<nested:write property="strPlanedQty" />
												</td>
												<%-- 単位名 --%>
												<td>
													<nested:write property="unitName" />
												</td>
												<%-- 荷姿 --%>
												<td>
													<nested:write property="styleOfPacking" />
												</td>
												<%-- ラベル数 --%>
												<td>
													<nested:text property="labelCount" maxlength="4" size="5"
													 styleId="<%="labelCount" + pageContext.findAttribute("index").toString() %>" style="ime-mode:disabled" />
												</td>
												<%-- 発行 --%>
												<td>
													<nested:write property="labeIssueStatus" />
												</td>
												<%-- ステータス --%>
												<td width="50">
													<nested:write property="statusName" />
												</td>
											</tr>
<%-- 3行目 --%>
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
											<tr class="bcList1">
										</app:modulo>
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
											<tr class="bcList2">
										</app:modulo>
												<%-- ASPオーダーコード --%>
												<td colspan="2">
													<nested:write property="aspOrderNo" />
												</td>
												<%-- 包装ライン --%>
												<td>
													<nested:write property="packageLine" />
												</td>
												<td  colspan="6">　</td>
											</tr>
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
					<tr>
						<td height="5"></td>
					</tr>
					<tr>
						<td class="alignCenter"><!-- ボタン -->
							<nested:equal property="insufficientFlag" value="true">
							<div id="cssButton">
								<a href="#" onclick="if (!(issueDirectionAlert1())) {return false;}else{return form_submit('op', 'issueDirection'); return false;}" class="cssButton">
								&nbsp;&nbsp;<font color="red">包装指図書発行（強行）</font>&nbsp;&nbsp;
								</a>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="javascript:void();" class="cssButton" onclick="if(issueDirectionAlert2()){return form_submit('op', 'reFresh')}; return false;">
									&nbsp;<font color="red">キャンセル</font>&nbsp;
								</a>
							</div>
							</nested:equal>
							<nested:notEqual property="insufficientFlag" value="true">
							<div id="cssButton">
								<a href="#" onclick="if (!(issueDirectionAlert())) {return false;}else{return form_submit('op', 'preIssuance'); return false;}" class="cssButton">
								&nbsp;&nbsp;包装指図書発行&nbsp;&nbsp;
								</a>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="javascript:void();" class="cssButton" onclick="if(issueMaterialAlert()){return form_submit('op', 'issueMaterial')}; return false;">
									&nbsp;原材料出庫指図書出力&nbsp;
								</a>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="#" onclick="if (!(issueLabelAlert())) {return false;}else{return form_submit('op', 'issueLabel'); return false;}" class="cssButton">
								&nbsp;&nbsp;製品ラベル発行&nbsp;&nbsp;
								</a>
							</div>
							</nested:notEqual>
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
