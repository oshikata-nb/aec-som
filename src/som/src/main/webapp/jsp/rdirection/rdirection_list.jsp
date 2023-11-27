<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taginclude.jsf"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http//www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<%@ include file="/jsp/common/head.jsf"%>
<%@ include file="/jsp/common/head_main.jsf"%>
<%@ include file="/jsp/common/clearText.jsf" %>
<%@ include file="/jsp/common/searchNameForMaster.jsf" %>

<%-- ▼業務固有css ここから　--%>
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


		<%-- 日付型フィールド定義 --%>
		defineAsDateField("resultSdateDayFrom");
		defineAsDateField("resultEdateDayFrom");
		defineAsDateField("resultSdateDayTo");
		defineAsDateField("resultEdateDayTo");
		defineAsTimeField("resultSdateTimeFrom");
		defineAsTimeField("resultEdateTimeFrom");
		defineAsTimeField("resultSdateTimeTo");
		defineAsTimeField("resultEdateTimeTo");


		<%-- フォーム設定の初期化 --%>
		<logic:equal name="rdirectionListForm" property="searchList" value="false">
			initializeFormState();
		</logic:equal>

		<%-- 品目名称のauto complete --%>
		new Ajax.Autocompleter(
		  "itemCd",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/ItemQueueForAutoComplete.do?op=searchItemQueue",
		  {
		  	paramName : "code",
		    afterUpdateElement : setItemQueueLabel
		  }
		);
		<%-- 他社コード１のauto complete --%>
		new Ajax.Autocompleter(
		  "otherCompanyCd1",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/ItemQueueForAutoComplete.do?op=searchItemQueueOtherCompany",
		  {
		  	paramName : "code",
		    afterUpdateElement : setOtherCompany1Label
		  }
		);
		
		// 完了状態のステータスから元に戻す。
		if($('reCheckNum').value == '2'){
			$('reCheckNum').value　= '0';
		}
		
		//20230207 確認メッセージ追加 S.Fujimaki
		if($("op").value == 'issuance' && $('reCheckNum').value == '1'){
			if(confirm("製造終了実績日付が過去になりますが、製造記録発行してよろしいですか？")){
				$('reCheckNum').value　= '2';
				form_submit('op', 'issuance');
				return; 
			}else{
				//　キャンセルした場合再表示されないように数値を初期化する。
				$('reCheckNum').value　= '0';
			}
		}
		//20230207 確認メッセージ追加 S.Fujimaki

		refreshLabel();

		Event.observe("itemCd", 'keypress', clearText.bindAsEventListener($('itemName')), false);
		Event.observe("itemCd", 'keypress', clearText.bindAsEventListener($('otherCompanyCd1')), false);
		Event.observe("otherCompanyCd1", 'keypress', clearText.bindAsEventListener($('itemName')), false);

	}, false);

	<%-- 品目名称auto completeの選択後処理 --%>
	function setItemQueueLabel(text, li) {
	    $("itemName").value = li.getElementsByTagName('span')[0].innerText;
	    $("otherCompanyCd1").value = getHiddenValue(li,"otherCompanyCd1");
	}
	<%-- 他社コード１auto completeの選択後処理 --%>
	function setOtherCompany1Label(text, li) {
	    $("itemName").value = li.getElementsByTagName('span')[0].innerText;
	    $("itemCd").value = getHiddenValue(li,"code");
	}
	<%-- 全チェック --%>
	function allCheck(checked){
		var count = $F("count");
		for(var i = 0 ; i < count ; i++){
			var target = $("searchList[" + i + "].selectedCheck");
			if(target != null){
				target.checked = checked;
			}
		}
		return false;
	}
	<%-- 製造記録発行確認メッセージ --%>
	function putConfirmIssuance() {
		if (!noCheck('Iss')) {
		  return false;
		}
		alertMsg = new Array();
		alertMsg[0] = "製造記録を発行してよろしいですか？";
		return confirm(alertMsg[0]);
	}
	<%-- 一括完了確認メッセージ --%>
	function putConfirmComplete() {
		if (!noCheck('Com')) {
		  return false;
		}
		alertMsg = new Array();
		alertMsg[0] = "一括完了してよろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- チェックボックス選択無しチェック --%>
	function noCheck(strFlag){
		var count = $F("count");
		var checkFlag = false;
		for (var i = 0; i < count; i++) {
			var target = $("searchList[" + i + "].selectedCheck");
			if(target != null && target.checked){
				checkFlag = true;
				break;
			}
		}
		if (!checkFlag) {
			if(strFlag == 'Iss') {
			    alert("製造記録発行対象が選択されていません。");
			} else {
			    alert("一括完了対象が選択されていません。");
			}
		    return false;
		}
		return true;
	}
	<%-- 確認メッセージ --%>
	function reportConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "帳票(EXCEL)出力してもよろしいですか？";

		return confirm(alertMsg[0]);
	}

	<%-- ポップアップ後の設定処理 --%>
	// ファイル取込ボタン押下時の警告避け
	function setRdirectionFileImportValues(rdirectionFileImportFlg) {
		if (rdirectionFileImportFlg) {

		}
	}

</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/RdirectionList" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="count" styleId="count" />
	<nested:hidden property="reCheckNum" styleId="reCheckNum" />

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
							<td class="fcTitle fb">製造実績</td>
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
							<td height="5" colspan="2" ></td>
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
									<td class="bcTitleSearch fb fcTitleSearch">製造指図番号</td>
									<td>
										<nested:text property="directionNo" maxlength="20" size="20" styleId="directionNo" style="ime-mode:disabled"/>
									</td>
									<td class="bcTitleSearch fb fcTitleSearch">生産工場</td>
									<td>
										<nested:select property="productionLine" style="margin: 0;padding: 0" styleId="productionLine">
											<nested:optionsCollection property="lineCombo" label="labales" value="values" />
										</nested:select>
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">調合タンクNo</td>
									<td>
										<nested:text property="compoundTankNo" maxlength="20" size="20" styleId="compoundTankNo" style="ime-mode:disabled"/>
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">品目</td>
									<td>
										<nested:text property="itemCd" maxlength="40" size="20" styleId="itemCd" />
										<div id="autocomplete_selection" class="autocomplete"></div>
									</td>
									<td colspan="2">
										<nested:text property="itemName" size="30" readonly="true" styleId="itemName" styleClass="noborderAl" />
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">ステータス</td>
									<td>
										<%
											pageContext.setAttribute( "selectDirectionStatus",new com.asahikaseieng.app.comboboxes.SelectRdirectionListStatus(true, false));
										%>
										<nested:select property="directionStatus" style="margin: 0;padding: 0" styleId="directionStatus">
											<nested:options name="selectDirectionStatus" property="values" labelName="selectDirectionStatus" labelProperty="labels" />
										</nested:select>
									</td>
									<td class="bcTitleSearch fb fcTitleSearch">他社コード１</td>
									<td>
										<nested:text property="otherCompanyCd1" maxlength="10" size="20" styleId="otherCompanyCd1" style="ime-mode:disabled"/>
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">製造開始実績日時</td>
									<td colspan="3">
										<nested:text property="resultSdateDayFrom" maxlength="10" size="12" styleId="resultSdateDayFrom" style="ime-mode:disabled"/>
										<nested:text property="resultSdateTimeFrom" maxlength="5" size="7" styleId="resultSdateTimeFrom" style="ime-mode:disabled"/>
										～
										<nested:text property="resultSdateDayTo" maxlength="10" size="12" styleId="resultSdateDayTo" style="ime-mode:disabled"/>
										<nested:text property="resultSdateTimeTo" maxlength="5" size="7" styleId="resultSdateTimeTo" style="ime-mode:disabled"/>
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">製造終了実績日時</td>
									<td colspan="3">
										<nested:text property="resultEdateDayFrom" maxlength="10" size="12" styleId="resultEdateDayFrom" style="ime-mode:disabled"/>
										<nested:text property="resultEdateTimeFrom" maxlength="5" size="7" styleId="resultEdateTimeFrom" style="ime-mode:disabled"/>
										～
										<nested:text property="resultEdateDayTo" maxlength="10" size="12" styleId="resultEdateDayTo" style="ime-mode:disabled"/>
										<nested:text property="resultEdateTimeTo" maxlength="5" size="7" styleId="resultEdateTimeTo" style="ime-mode:disabled"/>
									</td>
								</tr>
								<tr>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td class="alignRight">
							<table cellspacing="1" cellpadding="1" border="0">
								<tr>
									<td>
										<nested:equal property="updateAuthority" value="true">
										<div id="cssButton">
											<a href="#" onclick="open_modal_popup_edge(800, 450, 'RdirectionFileImport', '', 'scrollbars', 'yes'); return false;" class="cssButton">
												&nbsp;ファイル取込&nbsp;
											</a>
										</div>
										</nested:equal>
									</td>
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
						<td>
							<table width="100%" cellspacing="0" cellpadding="0">
								<tr>
									<td class="alignLeft">
									<table cellspacing="1" cellpadding="1" border="0">
										<tr>
											<td>
												<div id="cssButton">
													<a href="#" onclick="return allCheck(true);" class="cssButton">
														&nbsp;&nbsp;全チェック&nbsp;&nbsp;
													</a>
												</div>
											</td>
											<td>
												<div id="cssButton">
													<a href='#' onclick="return allCheck(false);" class="cssButton">
														&nbsp;&nbsp;全チェック解除&nbsp;&nbsp;
													</a>
												</div>
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
						<td><%-- 明細部 --%>
						<table width="" border="0">
							<tr>
								<td class="alignCenter">
								<table cellspacing="2" cellpadding="1" id="tblList">
									<tr class="bcTitleList fb fcTitleList">
										<td width="20" rowspan="2">&nbsp;</td>
										<td width="100" colspan="2">製造指図番号</td>
										<td width="130">製造開始実績日時</td>
										<td width="70">品目ｺｰﾄﾞ</td>
										<td width="200">生産工場</td>
										<td width="120">仕込み予定数量</td>
										<td width="30" rowspan="2" >単位</td>
									</tr>
									<tr class="bcTitleList fb fcTitleList">
										<td width="90">ｽﾃｰﾀｽ</td>
										<td width="50" >タンク</td>
										<td width="130">製造終了実績日時</td>
										<td width="300" colspan="2">品目名称</td>
										<td width="120">仕込み実績数量</td>
									</tr>
<%-- 1行目 --%>
									<nested:iterate id="searchList" property="searchList" indexId="index">
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
											<tr class="bcList1">
										</app:modulo>
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
											<tr class="bcList2">
										</app:modulo>

										<%-- 選択チェックボックス --%>
										<td rowspan="2">
												<nested:checkbox property="selectedCheck" />
										</td>
										<%-- 製造指図番号 --%>
										<td colspan="2" >
											<nested:define id="oid" property="directionNo" />
											<html:link href="#" onclick='<%="toDetail('" + request.getContextPath() + "/RdirectionHeader.do?op=init&directionNo=" + pageContext.findAttribute("oid").toString() + "'); return false;"%>'>
												<nested:write property="directionNo" />
											</html:link>
										</td>
										<%-- 製造開始実績日時 --%>
										<td class="alignRight">
											<nested:write property="resultSdate" format="yyyy/MM/dd HH:mm" />
										</td>
										<%-- 品目コード --%>
										<td>
											<nested:write property="itemCd" />
										</td>
										<%-- 生産工場 --%>
										<td>
											<nested:write property="productionLineName" />
										</td>
										<%-- 仕込み予定数量 --%>
										<td class="alignRight">
											<nested:write property="plandQtyString" />
										</td>
										<%-- 単位 --%>
										<td rowspan="2" >
											<nested:write property="unitName" />
										</td>
									</tr>
<%-- 2行目 --%>
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
											<tr class="bcList1">
										</app:modulo>
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
											<tr class="bcList2">
										</app:modulo>
										<%-- ｽﾃｰﾀｽ --%>
										<td>
											<nested:write property="statusName" />
										</td>
										<%-- 調合タンク --%>
										<td >
											<nested:write property="compoundTankNo" />
										</td>
										<%-- 製造終了実績日時 --%>
										<td class="alignRight">
											<nested:write property="resultEdate" format="yyyy/MM/dd HH:mm" />
										</td>
										<%-- 品目名称 --%>
										<td  colspan="2">
											<nested:write property="itemName" />
										</td>
										<%-- 仕込み実績数量 --%>
										<td class="alignRight">
											<nested:write property="resultQtyString" />
										</td>
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
						<td>
							<table border="0" cellpadding="2" cellspacing="5" width="750">
								<tr class="alignCenter">
									<td class="alignCenter">
										<div id="cssButton">
											<a href="javascript:void();" class="cssButton" onclick="if(putConfirmIssuance()){return form_submit('op', 'issuance')}; return false;">
												&nbsp;製造記録発行&nbsp;</a>
										</div>
									</td>
									<td class="alignCenter">
										<div id="cssButton">
											<a href="javascript:void();" class="cssButton" onclick="if(putConfirmComplete()){return form_submit('op', 'complete')}; return false;">
												&nbsp;一括完了&nbsp;</a>
										</div>
									</td>
								</tr>
							</table>
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
