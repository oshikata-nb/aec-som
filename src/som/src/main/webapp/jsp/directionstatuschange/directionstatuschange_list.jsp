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
		<logic:equal name="directionStatusChangeListForm" property="searchList" value="false">
			initializeFormState();
		</logic:equal>

		<%-- 必須フィールド定義 --%>
		<%-- 日付型フィールド定義 --%>
		<%-- 時間型フィールド定義 --%>

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
		
	}, false);
	
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

	<%-- チェックボックス選択無しチェック --%>
	function noCheck(){
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
				alert("ステータス変更対象が選択されていません。");
		    return false;
		}
		return true;
	}

	<%-- ステータス変更確認メッセージ --%>
	function putConfirmChange() {
		if (!noCheck()) {
		  return false;
		}
		alertMsg = new Array();
		alertMsg[0] = "選択行のステータスを以下のように変更します。よろしいですか？"
		            + "\n"
		            + "\n　【 指図書発行済 】　→　【 中間品最終検査待 】"
		            + "\n"
		            + "\n　【 製造開始 】　→　【 中間品最終検査待 】"
		            + "\n"
		            + "\n　【 中間品最終検査済 】　→　【 FA受信 】";
		return confirm(alertMsg[0]);
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

<nested:form action="/DirectionStatusChangeList" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="dirtyFlg" />
	<nested:hidden property="count" styleId="count" />

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
							<td class="fcTitle fb" width="250">ステータス変更</td>
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
										<nested:text property="srhItemName" maxlength="40" size="22" readonly="true" styleId="srhItemName" styleClass="noborderAl" tabindex="-1" />
										<div id="autocomplete_selection" class="autocomplete"></div>
									</td>
								</tr>
							</table>
							<table cellspacing="1" cellpadding="1" border="0">
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch" width="130">他社コード１</td>
									<td>
										<nested:text property="srhOtherCompanyCd1" maxlength="20" size="20" styleId="srhOtherCompanyCd1" style="ime-mode:disabled" />
									</td>
								</tr>
							</table>
							<table cellspacing="1" cellpadding="1" border="0">
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">ステータス</td>
									<td>
										<%
											pageContext.setAttribute("selectDirectionStatusChangeDirectionStatus",
											new com.asahikaseieng.app.comboboxes.SelectDirectionStatusChangeDirectionStatus(true, false));
										%>
										<nested:select property="srhDirectionStatus" styleId="srhDirectionStatus">
											<nested:options name="selectDirectionStatusChangeDirectionStatus" property="values" labelName="selectDirectionStatusChangeDirectionStatus" labelProperty="labels"/>
										</nested:select>
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
										<td width="180">製造指図番号</td>
										<td width="80">品目コード</td>
										<td width="500" colspan="2">品目名称</td>
									</tr>
									<tr class="bcTitleList fb fcTitleList">
										<td width="200" colspan="2">ステータス</td>
										<td width="200">生産工場</td>
										<td width="200">現工程</td>
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
											<td rowspan="2">
												<nested:checkbox property="selectedCheck" styleId="selectedCheck"/>
											</td>
											<%-- 指図番号 --%>
											<td>
												<nested:write property="directionNo" />
											</td>
											<%-- 主要製品コード --%>
											<td>
												<nested:write property="itemCd" />
											</td>
											<%-- 品目名称 --%>
											<td colspan="3">
												<nested:write property="itemName" />
											</td>
										</tr>
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
											<tr class="bcList1">
										</app:modulo>

										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
											<tr class="bcList2">
										</app:modulo>
											<%-- ステータス --%>
											<td colspan="2">
												<nested:write property="strDirectionStatus" />
											</td>
											<%-- 生産工場 --%>
											<td>
												<nested:write property="productionLineName" />
											</td>
											<%-- 現工程 --%>
											<td>
												<nested:write property="operationName" />
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
				</table>
				<nested:equal property="updateAuthority" value="true">
					<table cellpadding="0" cellspacing="0" width="100%" border="0">
						<tr>
							<td class="alignCenter">
								<div id="cssButton">
									<a href="#" onclick="if (!(putConfirmChange())) {return false;}else{return form_submit('op', 'change'); return false;}" class="cssButton">
									&nbsp;&nbsp;ステータス変更&nbsp;&nbsp;
									</a>
								</div>
							</td>
						</tr>
					</table>
				</nested:equal>
			</nested:notEmpty>
		<%-- Excelダウンロード処理 --%>
		<nested:equal property="excelReportDownloadFlg" value="true">
			<META HTTP-EQUIV="REFRESH" CONTENT="0; URL=<%= request.getContextPath() %>/FileDownload/">
		</nested:equal>
		<%-- Excelダウンロード処理 ここまで --%>
	</table>
</nested:form>
</body>
</html:html>
