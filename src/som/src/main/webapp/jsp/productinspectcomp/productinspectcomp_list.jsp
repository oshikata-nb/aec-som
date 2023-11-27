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
		<logic:equal name="productInspectCompListForm" property="searchList" value="false">
			initializeFormState();
		</logic:equal>

		<%-- 必須フィールド定義 --%>

		<%-- 日付型フィールド定義 --%>
		defineAsDateField("srhResultSDayFrom");
		defineAsDateField("srhResultSDayTo");
		defineAsDateField("srhResultEDayFrom");
		defineAsDateField("srhResultEDayTo");
		
		defineAsDateField("srhCertificationDate");
		<%-- 時間型フィールド定義 --%>
		defineAsTimeField("srhResultSTimeFrom");
		defineAsTimeField("srhResultSTimeTo");
		defineAsTimeField("srhResultETimeFrom");
		defineAsTimeField("srhResultETimeTo");

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

		<%-- 包装ラインのauto complete --%>
		new Ajax.Autocompleter(
		  "srhPackageLine",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/RecipeResouceForAutoComplete.do?op=searchRecipeResouce",
		  {
		  	paramName : "code",
		    afterUpdateElement : setPackageLineLabel
		  }
		);
		
		<%-- 名称のクリアauto complete --%>
		Event.observe('srhItemCd', 'keypress', clearText.bindAsEventListener($('srhItemName')), false);
		Event.observe('srhItemCd', 'keypress', clearText.bindAsEventListener($('srhOtherCompanyCd1')), false);
		Event.observe('srhPackageLine', 'keypress', clearText.bindAsEventListener($('srhPackageLineName')), false);
		
	}, false);
	
	<%-- 確認メッセージ --%>
	function putConfirm() {
		if (!noCheck(1)) {
		  return false;
		}

		alertMsg = new Array();
		alertMsg[0] = "登録してもよろしいですか？";

		return confirm(alertMsg[0]);
	}

	<%-- チェックボックス選択無しチェック --%>
	function noCheck(index){
	  var count = 0;
	  var item = document.productInspectCompListForm.productInspectCompCheckBox;
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
	      if(item[i].disabled == false){
            if(item[i].checked){
		      count = count + 1;
		      break;
		    }
		  }
	    }
	  }
	  if (count == 0) {
        alert("登録対象が選択されていません。");
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
	
	<%-- 包装ラインauto completeの選択後処理 --%>
	function setPackageLineLabel(text, li) {
	    $("srhPackageLineName").value = li.getElementsByTagName('span')[0].innerText;
	}
	
	<%-- ラジオボタンを選択した際にチェックボックスを有効化 --%>
	function enableCheckBox(chkbox) {
		var name = chkbox.name;
		var splitName = name.split(/[\[ \]]/);
		$("searchList[" + splitName[1] + "].productInspectCompCheckBox").disabled = false;
		$("searchList[" + splitName[1] + "].productInspectCompCheckBox").checked = true;
		
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

<nested:form action="/ProductInspectCompList" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="dirtyFlg" />

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
							<td class="fcTitle fb" width="250">製品検査完了入力</td>
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
									<td class="bcTitleSearch fb fcTitleSearch" width="100">包装指図番号</td>
									<td width="160">
										<nested:text property="srhDirectionNo" maxlength="20" size="20" styleId="srhDirectionNo" style="ime-mode:disabled" />
									</td>
									<td class="bcTitleSearch fb fcTitleSearch" width="80">生産工場</td>
									<td width="100">
										<nested:select property="srhProductionLine" style="margin: 0;padding: 0" styleId="srhProductionLine" >
											<nested:optionsCollection property="lineCombo" label="labales" value="values" />
										</nested:select>
									</td>
								</tr>
							</table>
							<table cellspacing="1" cellpadding="1" border="0">
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch" width="100">品目</td>
									<td colspan="3">
										<nested:text property="srhItemCd" maxlength="20" size="20" styleId="srhItemCd" />
										<nested:text property="srhItemName" maxlength="40" size="22" readonly="true" styleId="srhItemName" styleClass="noborderAl" tabindex="-1" />
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
									<td class="bcTitleSearch fb fcTitleSearch" width="100">開始実績日時</td>
									<td colspan="3">
										<nested:text property="srhResultSDayFrom" maxlength="8" size="12" styleId="srhResultSDayFrom" style="ime-mode:disabled" />&nbsp;<nested:text property="srhResultSTimeFrom" maxlength="4" size="7" styleId="srhResultSTimeFrom" style="ime-mode:disabled" />～<nested:text property="srhResultSDayTo" maxlength="8" size="12" styleId="srhResultSDayTo" style="ime-mode:disabled" />&nbsp;<nested:text property="srhResultSTimeTo" maxlength="4" size="7" styleId="srhResultSTimeTo" style="ime-mode:disabled" />
									</td>
								</tr>
							</table>
							<table cellspacing="1" cellpadding="1" border="0">
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch" width="100">終了実績日時</td>
									<td colspan="3">
										<nested:text property="srhResultEDayFrom" maxlength="8" size="12" styleId="srhResultEDayFrom" style="ime-mode:disabled" />&nbsp;<nested:text property="srhResultETimeFrom" maxlength="4" size="7" styleId="srhResultETimeFrom" style="ime-mode:disabled" />～<nested:text property="srhResultEDayTo" maxlength="8" size="12" styleId="srhResultEDayTo" style="ime-mode:disabled" />&nbsp;<nested:text property="srhResultETimeTo" maxlength="4" size="7" styleId="srhResultETimeTo" style="ime-mode:disabled" />
									</td>
								</tr>
							</table>
							<table cellspacing="1" cellpadding="1" border="0">
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch" width="100">ステータス</td>
									<td width="160">
										<%
											pageContext.setAttribute("selectProductInspectCompDirectionStatus",
											new com.asahikaseieng.app.comboboxes.SelectProductInspectCompDirectionStatus(true, false));
										%>
										<nested:select property="srhDirectionStatus" styleId="srhDirectionStatus">
											<nested:options name="selectProductInspectCompDirectionStatus" property="values" labelName="selectProductInspectCompDirectionStatus" labelProperty="labels"/>
										</nested:select>
									</td>
									<td class="bcTitleSearch fb fcTitleSearch" width="80">包装ライン</td>
									<td colspan="2">
										<nested:text property="srhPackageLine" maxlength="20" size="20" styleId="srhPackageLine" />
										<nested:text property="srhPackageLineName" maxlength="40" size="22" readonly="true" styleId="srhPackageLineName" styleClass="noborderAl" tabindex="-1" />
									</td>
								</tr>
							</table>
							<table cellspacing="1" cellpadding="1" border="0">
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch" width="100">検査合格日</td>
									<td width="160">
										<nested:text property="srhCertificationDate" maxlength="8" size="12" styleId="srhCertificationDate" style="ime-mode:disabled" />
									</td>
									<td class="bcTitleSearch fb fcTitleSearch" width="80">ロット番号</td>
									<td colspan="2">
										<nested:text property="srhLotNo" maxlength="20" size="20" styleId="srhLotNo" style="ime-mode:disabled" />
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
						<td><%-- 明細部 --%>
						<table width="" border="0">
							<tr>
								<td>
									<table cellspacing="1" cellpadding="1"  border="0" width="100%">
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
								<td class="alignCenter">
								<table cellspacing="2" cellpadding="1" id="tblList">
									<tr class="bcTitleList fb fcTitleList">
										<td width="20" rowspan="2">&nbsp;</td>
										<td width="100">包装指図番号</td>
										<td width="80">包装日</td>
										<td width="80">品目コード</td>
										<td width="250" colspan="3">品目名称</td>
										<td width="110">検査合格日</td>
									</tr>
									<tr class="bcTitleList fb fcTitleList">
										<td width="100">生産工場</td>
										<td width="80">ロット番号</td>
										<td width="80">包装ﾗｲﾝ</td>
										<td width="100">在庫数量</td>
										<td width="40">単位</td>
										<td width="100">合格・不合格</td>
										<td width="110">ｽﾃｰﾀｽ</td>
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
												<nested:equal property="directionStatus" value="6">
													<nested:checkbox property="productInspectCompCheckBox" styleId="productInspectCompCheckBox" disabled="true"/>
												</nested:equal>
												<nested:equal property="directionStatus" value="5">
													<nested:empty property="certificationFlag">
														<nested:checkbox property="productInspectCompCheckBox" styleId="productInspectCompCheckBox" disabled="true"/>	
													</nested:empty>
													<nested:notEmpty property="certificationFlag">
														<nested:checkbox property="productInspectCompCheckBox" styleId="productInspectCompCheckBox" disabled="false"/>
													</nested:notEmpty>
												</nested:equal>
											</td>
											<%-- 指図番号 --%>
											<td>
												<nested:write property="directionNo" />
											</td>
											<%-- 包装日 --%>
											<td>
												<nested:write property="strResultEdate" />
											</td>
											<%-- 主要製品コード --%>
											<td>
												<nested:write property="itemCd" />
											</td>
											<%-- 品目名称 --%>
											<td colspan="3">
												<nested:write property="itemName" />
											</td>
											<%-- 検査合格日 --%>
											<td>
												<nested:write property="strCertificationDate" />
											</td>
										</tr>
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
											<tr class="bcList1">
										</app:modulo>

										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
											<tr class="bcList2">
										</app:modulo>
											<%-- 生産ライン名称 --%>
											<td>
												<nested:write property="lineName" />
											</td>
											<%-- ロット番号 --%>
											<td>
												<nested:write property="lotNo" />
											</td>
											<%-- 使用資源名 --%>
											<td>
												<nested:write property="packageLine" />
											</td>
											<%-- 実績在庫量 --%>
											<td class="alignRight">
												<nested:write property="strLotInventoryQty" />
											</td>
											<%-- 単位 --%>
											<td>
												<nested:write property="unitName" />
											</td>
											<%-- 合格・不合格 --%>
											<td>
												<nested:equal property="directionStatus" value="6">
													<nested:equal property="certificationFlag" value="1">
														<input type="radio" readonly="readonly" checked="checked"/>合格
														<input type="radio" readonly="readonly" />不合格
													</nested:equal>
													<nested:equal property="certificationFlag" value="0">
														<input type="radio" readonly="readonly" />合格
														<input type="radio" readonly="readonly" checked="checked"/>不合格
													</nested:equal>
												</nested:equal>
												<nested:equal property="directionStatus" value="5">
													<label><nested:radio property="certificationFlag" value="1" onclick="enableCheckBox(this)"/>合格</label>
													<label><nested:radio property="certificationFlag" value="0" onclick="enableCheckBox(this)"/>不合格</label>
												</nested:equal>
											</td>
											<%-- ステータス --%>
											<td>
												<nested:write property="strDirectionStatus" />
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
									<a href="#" onclick="if (!(putConfirm())) {return false;}else{return form_submit('op', 'update'); return false;}" class="cssButton">
									&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
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
