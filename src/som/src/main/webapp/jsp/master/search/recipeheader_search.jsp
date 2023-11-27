<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/jsp/common/taginclude.jsf" %>

<html:html>
<head>
<%@ include file="/jsp/common/head.jsf"%>
<%@ include file="/jsp/common/head_main.jsf"%>

<%-- ▼業務固有css ここから
<style type="text/css">
</style>
<%-- ▲業務固有css ここまで --%>

<%-- ▼業務固有javaScript ここから --%>
<script language="JavaScript" type="text/javascript">
	<%-- 20211209 Asclab Kouchi EdgeIE対応 --%>
	<%-- 選択して終了 --%>
	function selectClose(index){
		var prefix = "searchList[" + index + "].";
		var select = new Array();
		select["recipeId"] = $F(prefix + "recipeId");
		select["recipeCd"] = $F(prefix + "recipeCd");
		select["recipeVersion"] = $F(prefix + "recipeVersion");
		select["recipeName"] = $F(prefix + "recipeName");
		select["itemCd"] = $F(prefix + "itemCd");
		select["itemName"] = $F(prefix + "itemName");
		select["shipperDivisionName"] = $F(prefix + "shipperDivisionName");
		select["otherCompanyCd1"] = $F(prefix + "otherCompanyCd1");
		select["productionLine"] = $F(prefix + "productionLine");
		select["productionLineName"] = $F(prefix + "productionLineName");
		select["unitName"] = $F(prefix + "unitName");
		select["unitDivision"] = $F(prefix + "unitDivision");
		select["recipeDescription"] = $F(prefix + "recipeDescription");
		select["recipeMemo"] = $F(prefix + "recipeMemo");

		window.opener.setRecipeValues(select);
		//モーダルダイアログの終了
		window.close();
	}

	<%-- キャンセルして終了 --%>
	function selectCancel(){
		//モーダルダイアログの終了
		window.close();
	}

	<%-- Load時の処理 --%>
	Event.observe(window, 'load', function() {
		<%-- フォーム設定の初期化 --%>
		initializeFormState();

		<%-- 品目名称のauto complete --%>
		new Ajax.Autocompleter(
		  "itemCd",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/ItemQueueForAutoComplete.do?op=searchDetailItemQueue",
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
		<%-- 名称のクリアauto complete --%>
		Event.observe('itemCd', 'keypress', clearText.bindAsEventListener($('itemName')), false);
		Event.observe('itemCd', 'keypress', clearText.bindAsEventListener($('otherCompanyCd1')), false);
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


</script>
<%-- ▲業務固有javaScript ここまで --%>
<base target="_self"><%-- ←重要(subumit時に新規windowが開かないようにするおまじない) --%>
</head>
<body>
<nested:form action="/RecipeHeaderSearch" method="post" onsubmit="return false;" styleId="mainForm">
	<%@ include file="/jsp/common/postjavascript.jsf" %>
	<nested:hidden property="op"/>
	<table cellpadding="0" cellspacing="5" border="0">
		<tr>
			<td class="valignTop alignLeft">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td><!-- ヘッダー部 -->
							<table border="0" cellpadding="0" cellspacing="0" width="100%">
								<tr>
									<td height="5" colspan="2"></td>
								</tr>
								<tr>
									<td class="fcTitle fb" nowrap>基本処方検索</td>
									<td>
										<table border="0" cellpadding="0" cellspacing="0">
											<tr>
												<td>
													<%-- メッセージ表示 --%>
													<%@ include file="/jsp/common/disp_info_message.jsf" %>
													<%-- メッセージ表示 ここまで --%>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td class="bcTitleLine" colspan="2"></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<%-- メッセージ表示 --%>
							<%@ include file="/jsp/common/disp_error_message.jsf" %>
							<%-- メッセージ表示 ここまで --%>
						</td>
					</tr>
					<tr>
						<td>
							<!-- 検索部 -->
							<table width="100%" cellspacing="" cellpadding=""  border="0">
								<tr>
									<td>
										<table cellspacing="2" cellpadding="2"  border="0">
											<tr>
												<td class="bcTitleSearch fb fcTitleSearch" width="150">基本処方コード</td>
												<td>
													<nested:text property="recipeCd" maxlength="20" size="20" styleId="recipeCd" styleClass="alignLeft"  style="ime-mode:disabled"/>
												</td>
												<td class="bcTitleSearch fb fcTitleSearch" width="150">基本処方名称</td>
												<td>
													<nested:text property="recipeName" maxlength="40" size="20" styleId="recipeName" styleClass="alignLeft"/>
												</td>
											</tr>
											<tr>
												<td class="bcTitleSearch fb fcTitleSearch" width="150">品目名</td>
												<td>
													<nested:text property="itemCd" maxlength="20" size="20" styleId="itemCd"/>
													<div id="autocomplete_selection" class="autocomplete"></div>
												</td>
												<td class="bcTitleSearch fb fcTitleSearch">品目名称</td>
												<td>
													<nested:text property="itemName" maxlength="40" size="40" styleId="itemName" readonly="true" styleClass="alignLeft"/>
												</td>
											</tr>
											<tr>
												<td class="bcTitleSearch fb fcTitleSearch" width="150">自社／花王区分</td>
												<td>
													<%
														pageContext.setAttribute( "selectShipperDivision", new com.asahikaseieng.app.comboboxes.SelectDirectionShipperDivision(false, false));
													%>
													<nested:select property="shipperDivision" style="margin: 0;padding: 0" styleId="shipperDivision">
														<nested:options name="selectShipperDivision" property="values" labelName="selectShipperDivision" labelProperty="labels" />
													</nested:select>
												</td>
											</tr>
											<tr>
												<td class="bcTitleSearch fb fcTitleSearch" width="130">他社コード１</td>
												<td colspan="3">
													<nested:text property="otherCompanyCd1" maxlength="20" size="20" styleId="otherCompanyCd1" styleClass="alignLeft"  style="ime-mode:disabled" />
												</td>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail" width="130">生産工場</td>
												<td>
													<nested:select property="productLine" style="margin: 0;padding: 0" styleId="productLine">
														<nested:optionsCollection property="lineCombo" label="labales" value="values" />
													</nested:select>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td class="alignRight valignMiddle">
										<div id="cssButton">
											<a href="#" onclick="return form_submit('op', 'search'); return false;" class="cssButton">
												&nbsp;&nbsp;検&nbsp;&nbsp;索&nbsp;&nbsp;
											</a>
										</div>
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
						<td height="3"></td>
					</tr>
					<tr>
						<td><!-- 明細部 -->
							<table cellspacing="2" cellpadding="2" width="670">
								<tr class="bcTitleList fb fcTitleList">
									<td width="10"></td>
									<td width="220">基本処方コード</td>
									<td width="340">基本処方名称</td>
									<td width="100">バージョン</td>
								</tr>
								<nested:iterate id="searchList" property="searchList" indexId="index">
									<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
										<tr class="bcList1">
									</app:modulo>
									<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
										<tr class="bcList2">
									</app:modulo>
									<td>
										<div id="cssButton">
											<a href="#" class="cssButton"
												onClick="<%="selectClose('"
													   + pageContext.findAttribute("index").toString()
												   + "')" %>">
												選
											</a>
										</div>
									</td>
									<td>
										<nested:write property="recipeCd" />
									</td>
									<td>
										<nested:write property="recipeName" />
									</td>
									<td>
										<nested:write property="recipeVersion" />
									</td>
									<nested:hidden property="recipeId"/>
									<nested:hidden property="recipeCd"/>
									<nested:hidden property="recipeVersion"/>
									<nested:hidden property="recipeName"/>
									<nested:hidden property="itemCd"/>
									<nested:hidden property="itemName"/>
									<nested:hidden property="shipperDivisionName" />
									<nested:hidden property="otherCompanyCd1" />
									<nested:hidden property="productionLine" />
									<nested:hidden property="productionLineName" />
									<nested:hidden property="unitName" />
									<nested:hidden property="unitDivision" />
									<nested:hidden property="recipeDescription" />
									<nested:hidden property="recipeMemo" />
								</nested:iterate>
							</table>
						</td>
					</tr>
					<tr>
						<td height="3"></td>
					</tr>
					<tr>
						<td class="alignCenter">
							<%-- ページング --%>
							<%@ include file="/jsp/common/pager/pager.jsf" %>
							<%-- ページング ここまで --%>
						</td>
					</tr>
					<tr>
						<td height="5"></td>
					</tr>
				</table>
			</nested:notEmpty>
				<table border="0" cellpadding="2" cellspacing="2" width="100%">
					<tr>
						<td class="alignCenter">
							<div id="cssButton">
								<a href="#" onClick="<%="selectCancel()" %>" class="cssButton">
									&nbsp;キャンセル&nbsp;
								</a>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</nested:form>
</body>
</html:html>