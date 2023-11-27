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
		<logic:equal name="productionListForm" property="searchList" value="false">
			initializeFormState();
		</logic:equal>

		<%-- 品目名称のauto complete --%>
		new Ajax.Autocompleter(
		  "srhItemCd",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/ItemForAutoComplete.do?op=searchItem",
		  {
		  	paramName : "code",
		    afterUpdateElement : setItemLabel
		  }
		);
		<%-- 名称のクリアauto complete --%>
		Event.observe('srhItemCd', 'keypress', clearText.bindAsEventListener($('srhItemName')), false);
		Event.observe('srhItemCd', 'keypress', clearText.bindAsEventListener($('srhOtherCompanyCd1')), false);

		<%-- 他社コード１のauto complete --%>
		new Ajax.Autocompleter(
		  "srhOtherCompanyCd1",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/ItemForAutoComplete.do?op=searchItemOtherCompany",
		  {
		  	paramName : "code",
		    afterUpdateElement : setOtherCompany1Label
		  }
		);
		

		<%-- 必須フィールド定義 --%>
		<%-- 日付型フィールド定義 --%>
		defineAsYMField("srhOrderLet");

		<%-- 明細部 --%>
		var tblList = $('tblList');
		if (tblList != null) {
			<%-- 数値型フィールド定義 --%>
			<%-- 必須フィールド定義 --%>
			<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
		}

		refreshLabel();

	}, false);
		
	<%-- 品目名称auto completeの選択後処理 --%>
	function setItemLabel(text, li) {
	    $("srhItemName").value = li.getElementsByTagName('span')[0].innerText;
	    $("srhOtherCompanyCd1").value = getHiddenValue(li,"otherCompanyCd1");
	}
	<%-- 他社コード１auto completeの選択後処理 --%>
	function setOtherCompany1Label(text, li) {
	    $("srhItemName").value = li.getElementsByTagName('span')[0].innerText;
	    $("srhItemCd").value = getHiddenValue(li,"code");
	}

	<%-- 日付型フィールド定義 --%>
	var dateElementsYYYYMM = new Array();
	/* 日付型 ON_FOCUSの動作を設定 */
	function dateFocusListenerYYYYMM() {
		this.value = unformatDate(this.value);
		Field.select(this);
	}
	
	<%--  日付型 OFF_FOCUSの動作を設定 --%>
	function dateBlurListenerYYYYMM() {
		this.value = formatDateYYYYMM(this.value);
	}

	function defineAsYMField(id) {
	
		var o = $(id);
		Event.observe(o, 'focus', dateFocusListenerYYYYMM.bind(o), false);
		Event.observe(o, 'blur', dateBlurListenerYYYYMM.bind(o), false);
	
		if (o.style.imeMode) {
			o.style.imeMode = 'disabled';
		}
		o.style.textAlign = 'left';
		
		dateElementsYYYYMM[o.id] = o;
	}
	function formatDateYYYYMM(org) {
	
		if (org == null) {
			return null;
		}
	
		if(!org.match(/^(-?)[0-9]{4,6}$/)){
			return org;
		}
	
		var tmp = org;
		
		if (tmp.length == 4) {
			tmp = "20" + tmp;
		}
	
		if (tmp.length == 6) {
	
			var year  = parseInt(tmp.substring(0, 4), 10);
			var month = parseInt(tmp.substring(4, 6), 10)-1;
			var day   = parseInt('01', 10);
	
			if (isNaN(year) || isNaN(month)) {
				return org;
			}
				
			var d = new Date(year, month, day);
	
			if (month != d.getMonth()) {
				return org;
			}
				
			if (day != d.getDate()) {
				return org;
			}
	
			return tmp.substring(0, 4) + "/" + tmp.substring(4, 6);
		}
	
		return org;
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

<nested:form action="/ProductionList" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />

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
							<td class="fcTitle fb" width="250">生産計画入力</td>
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
							<table border="0" cellspacing="2" cellpadding="1">
								<tr>
									<!-- 検索入力：荷主 -->
                        			<td class="bcTitleSearch fb fcTitleSearch">荷主</td>
			                        <td>
			                        	<%
											pageContext.setAttribute( "selectProductionShipperDivision",new com.asahikaseieng.app.comboboxes.SelectProductionShipperDivision(true, false));
										%>
										<nested:select property="srhShipperDivision" style="margin: 0;padding: 0" styleId="srhShipperDivision">
											<nested:options name="selectProductionShipperDivision" property="values" labelName="selectProductionShipperDivision" labelProperty="labels" />
										</nested:select>
                        			</td>
                       				
                        		</tr>
			                    <tr>
			                    	<!-- 検索入力：社内製造品/外注品区分 -->
			                    	<td class="bcTitleSearch fb fcTitleSearch">社内製造品/外注品区分</td>
			                        <td>
			                        	<%
											pageContext.setAttribute( "selectProductionTypeDivision",new com.asahikaseieng.app.comboboxes.SelectProductionTypeDivision(true, false));
										%>
										<nested:select property="srhTypeDivision" style="margin: 0;padding: 0" styleId="srhTypeDivision">
											<nested:options name="selectProductionTypeDivision" property="values" labelName="selectProductionTypeDivision" labelProperty="labels" />
										</nested:select>
			                        </td>
			                    </tr>
			                    <tr>
			                    	<!-- 検索入力：工場名 -->
		                        	<td class="bcTitleSearch fb fcTitleSearch">工場名</td>
		                        	<td>
		                        		<nested:select property="srhProductionLine" style="margin: 0;padding: 0" >
											<nested:optionsCollection property="productionLineCombo" label="labales" value="values" />
										</nested:select>
		                        	</td>
		                        </tr>
		                        <tr>
                        			<%-- 検索入力：品目コード --%>							
									<td class="bcTitleSearch fb fcTitleSearch">品目コード</td>
									<td width="280">
										<nested:text property="srhItemCd" maxlength="20" size="10" styleId="srhItemCd" />
										<div id="autocomplete_selection" class="autocomplete"></div>
										<nested:text property="srhItemName" maxlength="40" size="15" readonly="true" styleId="srhItemName" styleClass="noborderAl" onchange="setDirtyFlg();" tabindex="-1" />
									</td>
									
									<%-- 他社コード１ --%>		
									<td class="bcTitleSearch fb fcTitleSearch" width="150">他社コード１</td>
									<td width="280">
										<nested:text property="srhOtherCompanyCd1" maxlength="20" size="10" styleId="srhOtherCompanyCd1" />
									</td>
								</tr>
								<tr>
									<!-- 検索入力：生産計画年月 -->
                        			<td class="bcTitleSearch fb fcTitleSearch" width="180" nowrap>生産計画年月(YYYY/MM)</td>
                        			<td width="280">
                        				<nested:text property="srhOrderLet" maxlength="6" size="10" styleId="srhOrderLet" style="ime-mode:disabled"/>
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
									<td>
										<div id="cssButton">
										<a href='<%= request.getContextPath() + "/ProductionDetail.do?op=initNew"%>'
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
								<table cellspacing="1" cellpadding="1" id="tblList">
									<tr class="bcTitleList fb fcTitleList">
										<td align="center" nowrap>荷主</td>
                                    	<td align="center" nowrap>社内製造品<BR>/外注品区分</td>
                                    	<td align="center" width="135" nowrap>　工場名　</td>
                                    	<td align="center" width="75" nowrap>品目コード</td>
                                    	<td align="center" width="200" nowrap>品目名称</td>
                                    	<td align="center" width="40" nowrap>荷姿</td>
                                    	<td align="center" width="70" nowrap>生産計画<BR>年月</td>
                                    	<td align="center" width="90" nowrap>生産予定量<BR>合計</td>
                                    	<td align="center" width="60" nowrap>単位</td>
									</tr>
									<nested:iterate id="searchList" property="searchList"
										indexId="index">
										
										<%-- 1段目 --%>
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
											<tr class="bcList1">
										</app:modulo>

										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
											<tr class="bcList2">
										</app:modulo>

											<%-- 荷主 --%>
											<td>
												<nested:write property="strShipperDivision" />
											</td>
											
											<%-- 社内製造品/外注品区分 --%>
											<td>
												<nested:write property="strTypeDivision" />
											</td>
											
											<%-- 工場名 --%>
											<td>
												<nested:write property="productionLineName" />
											</td>
					
											<%-- 品目コード --%>
											<td>
												<nested:define id="iCd" property="itemCd" />
												<nested:define id="OrLet" property="strOrderLet" />
												<html:link href="#" onclick='<%="toDetail('" + request.getContextPath() + "/ProductionDetail.do?op=init&srhItemCd=" + pageContext.findAttribute("iCd").toString() + "&srhOrderLet=" + pageContext.findAttribute("OrLet").toString() + "'); return false;"%>'>
													<nested:write property="itemCd" />
												</html:link>
											</td>
											
											<%-- 品目名称 --%>
											<td>
												<nested:write property="itemName" />
											</td>

											<%-- 荷姿 --%>
											<td>
												<nested:write property="styleOfPacking" />
											</td>
											
											<%-- 生産計画年月 --%>
											<td align="right">
												<nested:write property="strOrderLet" />
											</td>
											
											<%-- 生産予定量合計 --%>
											<td align="right">
												<nested:write property="strSumOrderQty" />
											</td>
											
											<%-- 単位 --%>
											<td align="center">
												<nested:write property="unitName" />
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
