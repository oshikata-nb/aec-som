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

	<%--onload時の処理--%>
	Event.observe(window, 'load', function() {


		<%-- 必須フィールド定義 --%>
		defineAsRequiredField("srhDateFrom");
		defineAsRequiredField("srhDateTo");
		
		<%-- 日付型フィールド定義 --%>
		defineAsYMField("srhDateFrom");
		defineAsYMField("srhDateTo");

		<%-- 明細部 --%>

		refreshLabel();

	}, false);
		
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

	<%-- 発行メッセージ --%>
	function reportAlert() {
		alertMsg = new Array();
		alertMsg[0] = "原材料別入出庫一覧表を発行してよろしいですか？";
		return confirm(alertMsg[0]);
	}

</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/InoutMaterial" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />

	<table cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td class="valignTop alignLeft">
			<table border="0" cellpadding="0" cellspacing="0" width="750">
			
					<tr>
						<td>
							<!-- ヘッダー部 -->
							<table border="0" cellpadding="0" cellspacing="0" width="750">
								<tr>
									<td height="10" colspan="2"></td>
								</tr>

								<tr>
									<td class="fcTitle fb" width="250">原材料別入出庫一覧表</td>
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
									<td class="bcTitleLine" colspan="3"></td>
								</tr>

								<tr>
									<td height="5" colspan="3"></td>
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
							<table width="100%" cellspacing="" cellpadding="" border="0">
								<tr>
									<td>
										<table cellspacing="2" cellpadding="1" border="0">
											<tr>
												<td class="bcTitleSearch fb fcTitleSearch" width="130">化成品・油脂区分</td>
												<td>
													<% pageContext.setAttribute("selectInoutMaterialDeliveryDivision", new com.asahikaseieng.app.comboboxes.SelectInoutMaterialDeliveryDivision(true, false)); %>
													<nested:select property="srhBalanceType" style="margin: 0;padding: 0" styleId="srhBalanceType" >
														<nested:options name="selectInoutMaterialDeliveryDivision" property="values" labelName="selectInoutMaterialDeliveryDivision" labelProperty="labels"/>
													</nested:select>
												</td>
											</tr>
											<tr>
												<td class="bcTitleSearch fb fcTitleSearch" width="130">原料・包材区分</td>
												<td>
													<% pageContext.setAttribute("selectInoutMaterialDirectionDivision", new com.asahikaseieng.app.comboboxes.SelectInoutMaterialDirectionDivision(true, false)); %>
													<nested:select property="srhDirectionDivision" style="margin: 0;padding: 0" styleId="srhDirectionDivision">
														<nested:options name="selectInoutMaterialDirectionDivision" property="values" labelName="selectInoutMaterialDirectionDivision" labelProperty="labels"/>
													</nested:select>
												</td>
											</tr>
											<tr>
												<!-- 検索入力：荷主 -->
			                        			<td class="bcTitleSearch fb fcTitleSearch">荷主</td>
						                        <td>
						                        	<%
														pageContext.setAttribute( "selectInoutMaterialShipperDivision",new com.asahikaseieng.app.comboboxes.SelectInoutMaterialShipperDivision(true, false));
													%>
													<nested:select property="srhShipperDivision" style="margin: 0;padding: 0" styleId="srhShipperDivision">
														<nested:options name="selectInoutMaterialShipperDivision" property="values" labelName="selectInoutMaterialShipperDivision" labelProperty="labels" />
													</nested:select>
			                        			</td>
			                       				
			                        		</tr>
											<tr>
												<td class="bcTitleSearch fb fcTitleSearch" width="80">期間</td>
												<td>
													<nested:text property="srhDateFrom" maxlength="6" size="8" styleId="srhDateFrom"  style="ime-mode:disabled"/>
												</td>
												<td width="20">～</td>
												<td>
													<nested:text property="srhDateTo" maxlength="6" size="8" styleId="srhDateTo"  style="ime-mode:disabled"/>
												</td>
											</tr>
											<tr>
												<td class="bcTitleSearch fb fcTitleSearch" width="80">荷姿</td>
												<td>
													<nested:text property="srhStyleOfPacking" maxlength="11" size="8" styleId="srhStyleOfPacking"/>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td class="alignRight">
										<table cellspacing="1" cellpadding="1"  border="0">
											<tr>
												<td>
													<div id="cssButton">
														<a href="#"	onclick="if (!(reportAlert())) {return false;}else{return form_submit('op', 'report'); return false;}" class="cssButton">
															原材料別入出庫一覧表
														</a>
													</div>
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
								<tr>

									<td>

										<table border="0" cellpadding="0" cellspacing="0" class="alignCenter fcTitle fb">
											<tr>
												<td>
													<div align=left>													
　<font color=red>【注意】</font><br><br>
　　抽出期間によっては、原材料別入出庫一覧表(Excel)の出力時間が多くかかります。<br><br>
　　　・実行中に他のメニューを押すと原材料別入出庫一覧表(Excel)が正しく出力されません。<br>
　　　・実行中は他のメニューを押さないで下さい。<br><br>
　　原材料別入出庫一覧表(Excel)出力までしばらくお待ち下さい。。。<br><br>
													</div>
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
				</tr>
			</table>
			</td>
		</tr>
		<%-- Excelダウンロード処理 --%>
		<nested:equal property="excelDownloadFlg" value="true">
			<META HTTP-EQUIV="REFRESH" CONTENT="0; URL=<%= request.getContextPath() %>/FileDownload/">
		</nested:equal>
		<%-- Excelダウンロード処理 ここまで --%>

	</table>
</nested:form>
</body>
</html:html>
