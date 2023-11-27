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
		<logic:equal name="PkgRdirectionListForm" property="searchList" value="false">
			initializeFormState();
		</logic:equal>

		<%-- 品目名称のauto complete --%>
		new Ajax.Autocompleter(
		  "itemCd",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/ItemForAutoComplete.do?op=searchItem",
		  {
		  	paramName : "code",
		    afterUpdateElement : setItemQueueLabel
		  }
		);

		<%-- 他社コード１のauto complete --%>
		new Ajax.Autocompleter(
		  "otherCompanyCd1",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/ItemForAutoComplete.do?op=searchItemOtherCompany",
		  {
		  	paramName : "code",
		    afterUpdateElement : setOtherCompany1Label
		  }
		);

		<%-- 必須フィールド定義 --%>
		<%-- 日付型フィールド定義 --%>
		defineAsDateField("resultSDayFrom");
		defineAsDateField("resultSDayTo");
		defineAsDateField("resultEDayFrom");
		defineAsDateField("resultEDayTo");
		<%-- 時間型フィールド定義 --%>
		defineAsTimeField("resultSTimeFrom");
		defineAsTimeField("resultSTimeTo");
		defineAsTimeField("resultETimeFrom");
		defineAsTimeField("resultETimeTo");

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
		
		// 完了状態のステータスから元に戻す。
		if($('reCheckNum').value == '2'){
			$('reCheckNum').value　= '0';
		}
		
		//20230207 確認メッセージ追加 S.Fujimaki
		if($("op").value == 'issueDoc' && $('reCheckNum').value == '1'){
			if(confirm("包装終了実績日付が過去になりますが、製造記録発行してよろしいですか？")){
				$('reCheckNum').value　= '2';
				form_submit('op', 'issueDoc');
				return; 
			}else{
				//　キャンセルした場合再表示されないように数値を初期化する。
				$('reCheckNum').value　= '0';
			}
		}
		//20230207 確認メッセージ追加 S.Fujimaki
		
		refreshLabel();

		Event.observe('itemCd', 'keypress', clearText.bindAsEventListener($('srhItemNameHidden')), false);
	}, false);

	<%-- チェックボックス選択無しチェック --%>
	function noCheck(index){
		var count = 0;
		var item = document.PkgRdirectionListForm.checkFlg;
		if (item != undefined) {
	  		var len = item.length;

			// 明細１件のみの場合
			if (len == undefined) {
				if(item.disabled == false){
					if(item != null && item.checked){
			    		count = count + 1;
	          		}
				}
		  	} else {
		    	for (var i = 0; i < len; i++) {
	          		if(item[i] != null && item[i].checked){
			    		count = count + 1;
			    		break;
			 	 	}
		    	}
		  	}
		}
		if (count == 0) {
	    	if (index == 1) {
	      	alert("包装記録発行対象が選択されていません。");
	    	} else {
	    		if (index == 2) {
	      			alert("ラベル発行対象が選択されていません。");
	    		} else {
	    			alert("一括完了対象が選択されていません。");
	    		}
	    	}
	    	return false;
	  	}
	  	return true;
	}

	<%-- 包装記録発行メッセージ --%>
	function issueDocAlert() {
		if (!noCheck(1)) {
		  return false;
		}

		alertMsg = new Array();
		alertMsg[0] = "包装記録を発行してよろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- ラベル発行確認メッセージ --%>
	function issueLabelAlert() {
		if (!noCheck(2)) {
		  return false;
		}
		var labelCount = 0;
		var item = document.PkgRdirectionListForm.checkFlg;
		var len = item.length;

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
        	if ($("searchList[" + i + "].checkFlg") != null) {
        		$("searchList[" + i + "].checkFlg").checked = flg;
        	}
        }
	}
	<%-- 確認メッセージ --%>
	function reportConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "帳票(EXCEL)出力してもよろしいですか？";

		return confirm(alertMsg[0]);
	}
	<%-- 一括完了確認メッセージ --%>
	function putConfirmComplete() {
		if (!noCheck(3)) {
		  return false;
		}
		alertMsg = new Array();
		alertMsg[0] = "一括完了してよろしいですか？";
		return confirm(alertMsg[0]);
	}

</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/PkgRdirectionList" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="reCheckNum" styleId="reCheckNum" />

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
							<td class="fcTitle fb" width="250" >包装実績</td>
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
										<td class="bcTitleSearch fb fcTitleSearch" width="100">指図区分</td>
										<td width="150">
											<%
												pageContext.setAttribute( "selectPkgRdirectionDirectionDivision",
												new com.asahikaseieng.app.comboboxes.SelectPkgRdirectionDirectionDivision(false, false));
											%>
											<nested:select property="directionDivision" styleId="srhDirectionDivision">
												<nested:options name="selectPkgRdirectionDirectionDivision" property="values" labelName="selectPkgRdirectionDirectionDivision" labelProperty="labels" />
											</nested:select>
										</td>
										<td class="bcTitleSearch fb fcTitleSearch" width="100">包装指図番号</td>
										<td width="103">
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
										<td class="bcTitleSearch fb fcTitleSearch" width="100" >品目</td>
										<td >
											<nested:text property="itemCd" maxlength="40" size="20" styleId="srhItemCd" />
											<div id="autocomplete_selection" class="autocomplete"></div>
										</td>
										<td colspan="5">
											<nested:text property="itemName" maxlength="40" size="60" styleId="srhItemNameHidden" readonly="true" styleClass="noborderAl" tabindex="-1" />
										</td>
									</tr>
									<tr>
										<td class="bcTitleSearch fb fcTitleSearch" width="100">ステータス</td>
										<td width="100">
											<%
												pageContext.setAttribute( "selectPkgRdirectionDirectionStatus",
												new com.asahikaseieng.app.comboboxes.SelectPkgRdirectionDirectionStatus(true, false));
											%>
											<nested:select property="directionStatus" styleId="srhDirectionStatus">
												<nested:options name="selectPkgRdirectionDirectionStatus" property="values" labelName="selectPkgRdirectionDirectionStatus" labelProperty="labels" />
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
										<td class="bcTitleSearch fb fcTitleSearch" width="100">開始実績日時</td>
										<td>
											<nested:text property="resultSDayFrom" maxlength="10" size="12" styleId="srhResultSDayFrom" style="ime-mode:disabled" />
											<nested:text property="resultSTimeFrom" maxlength="5" size="7" styleId="srhResultSTimeFrom" style="ime-mode:disabled" />～
											<nested:text property="resultSDayTo" maxlength="10" size="12" styleId="srhResultSDayTo" style="ime-mode:disabled" />
											<nested:text property="resultSTimeTo" maxlength="5" size="7" styleId="srhResultSTimeTo" style="ime-mode:disabled" />
										</td>
									</tr>
									<tr>
										<td class="bcTitleSearch fb fcTitleSearch" width="100">終了実績日時</td>
										<td>
											<nested:text property="resultEDayFrom" maxlength="10" size="12" styleId="srhResultEDayFrom" style="ime-mode:disabled" />
											<nested:text property="resultETimeFrom" maxlength="5" size="7" styleId="srhResultETimeFrom" style="ime-mode:disabled" />～
											<nested:text property="resultEDayTo" maxlength="10" size="12" styleId="srhResultEDayTo" style="ime-mode:disabled" />
											<nested:text property="resultETimeTo" maxlength="5" size="7" styleId="srhResultETimeTo" style="ime-mode:disabled" />
										</td>
									</tr>
								</table>
								<table border="0" cellspacing="1" cellpadding="1">
										<td class="bcTitleSearch fb fcTitleSearch" width="100" >ロット番号</td>
										<td width="130" >
											<nested:text property="lotNo" maxlength="20" size="20" styleId="srhLotNo" style="ime-mode:disabled" />
										</td>
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
							<table cellspacing="1" cellpadding="1"  border="0"  width="100%">
								<tr>
									<td class="alignLeft">
									<table cellspacing="1" cellpadding="1" border="0">
										<tr>
											<td>
												<div id="cssButton">
													<a href="#" onclick="allCheck(true); return false;" class="cssButton"> &nbsp;&nbsp;全チェック&nbsp;&nbsp;</a>
												</div>
											</td>

											<td>
												<div id="cssButton">
													<a href="#" onclick="allCheck(false); return false;" class="cssButton"> &nbsp;&nbsp;全チェック解除&nbsp;&nbsp;</a>
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
						<td><!-- 明細部 -->
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<tr>
								<td class="alignCenter">
								<table cellspacing="2" cellpadding="1" id="tblList">
									<tr class="bcTitleList fb fcTitleList">
										<td width="20" rowspan="2"></td>
										<td width="100">包装指図番号</td>
										<td width="140">包装開始実績日時</td>
										<td width="80">品目コード</td>
										<td colspan="3">品目名称</td>
										<td width="120" >生産工場</td>
										<td width="120" >包装ライン</td>
									</tr>
									<tr class="bcTitleList fb fcTitleList">
										<td width="100">製造指図番号</td>
										<td width="140">包装終了実績日時</td>
										<td width="80">ロット番号</td>
										<td width="120">生産予定数量</td>
										<td width="40">単位</td>
										<td width="120">荷姿</td>
										<td width="80">ラベル枚数</td>
										<td width="120">ｽﾃｰﾀｽ</td>
									</tr>
									<nested:iterate id="searchList" property="searchList"
										indexId="index">
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

												<td rowspan="2">
														<nested:checkbox property="checkFlg" styleId="checkFlg" />
												</td>
												<%-- 包装指図番号 --%>
												<td>
													<html:link href="#" onclick='<%="toDetail('" + request.getContextPath() + "/PkgRdirectionHeaderDetail.do?op=init&directionNo=" + pageContext.findAttribute("dirNo").toString()
															                                     + "&directionDivision=" + pageContext.findAttribute("dirDiv").toString() + "'); return false;"%>'>
														<nested:write property="directionNo" />
													</html:link>
												</td>
												<%-- 包装開始実績日時 --%>
												<td>
													<nested:write property="packResultSdate" />
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
												<td >
													<nested:write property="productionLineName" />
												</td>
												<%-- 包装ライン --%>
												<td >
													<nested:write property="packageLine" />
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
												<%-- 包装終了実績日時 --%>
												<td>
													<nested:write property="packResultEdate" />
												</td>
												<%-- ロット番号 --%>
												<td class="alignLeft">
													<nested:lessThan property="jissekiFlag" value="1">
														<nested:lessThan property="directionStatus" value="5">
															<nested:text property="lotNo" maxlength="20" size="7" styleId="<%="lotNo" + pageContext.findAttribute("index").toString() %>" style="ime-mode:disabled" />
														</nested:lessThan>
														<nested:equal property="directionStatus" value="5">
															<nested:write property="lotNo" />
														</nested:equal>
														<nested:equal property="directionStatus" value="6">
															<nested:write property="lotNo" />
														</nested:equal>
														<nested:equal property="directionStatus" value="7">
															<nested:write property="lotNo" />
														</nested:equal>
													</nested:lessThan>
													<nested:equal property="jissekiFlag" value="1">
														<nested:write property="lotNo" />
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
												<%-- ステータス --%>
												<td>
													<nested:write property="statusName" />
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
						<td height="5"></td>
					</tr>
					<tr>
						<td class="alignCenter"><!-- ボタン -->
							<div id="cssButton">
								<a href="#" onclick="if (!(issueDocAlert())) {return false;}else{return form_submit('op', 'issueDoc'); return false;}" class="cssButton">
								&nbsp;&nbsp;包装記録発行&nbsp;&nbsp;
								</a>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="#" onclick="if (!(issueLabelAlert())) {return false;}else{return form_submit('op', 'issueLabel'); return false;}" class="cssButton">
								&nbsp;&nbsp;製品ラベル発行&nbsp;&nbsp;
								</a>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="#" class="cssButton" onclick="if(putConfirmComplete()){return form_submit('op', 'complete')}; return false;">
								&nbsp;一括完了&nbsp;</a>
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
