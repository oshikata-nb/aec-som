<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/jsp/common/taginclude.jsf" %>

<html:html>
<head>
<style type="text/css">
<!--
-->
</style>
<%@ include file="/jsp/common/head.jsf"%>
<%--@ include file="/jsp/common/head_main.jsf"--%>

<%-- ▼業務固有css ここから --%>
<style type="text/css">
<!--
	html, body { overflow-y:scroll; }
	input.R    { text-align:right;  background-color:white; font-weight:normal; font-size:9pt; border: 1px solid #7f9db9; ime-mode:disabled; }
	input.LBLL { text-align:left;   background-color:transparent; font-weight:normal; font-size:9pt; border-width: 0px; border-style: solid;}
	input.LBLR { text-align:right;  background-color:transparent; font-weight:normal; font-size:9pt; border-width: 0px; border-style: solid;}
-->
</style>
<%-- ▲業務固有css ここまで --%>

<%-- ▼業務固有javaScript ここから --%>
<script language="JavaScript" type="text/javascript">

	<%-- Load時の処理 --%>
	Event.observe(window, 'load', function() {
		<%-- フォーム設定の初期化 --%>
		initializeFormState();
       
		<%-- 数値型フィールド定義 --%>
		if( $("detailListPage[0].directionNo")!=null ) {
			try {
				//for ( row = 0; row < maxrow; row++ ) {
				for ( row = 0; true; row++ ) {
					var line = "detailListPage[" + row.toString() + "]";
					for (i=0; i<5; i++) {
						defineAsTimeField_DaylyrRport(line + ".sagyoTimeList[" + i.toString() + "].jobTimeHhmm");
					}
					defineAsTimeField_DaylyrRport(line + ".subTotal1Hhmm");
					defineAsTimeField_DaylyrRport(line + ".subTotal2Hhmm");
					defineAsTimeField_DaylyrRport(line + ".totalHhmm");
				}
			} catch (ex) {
			}
		}

		<%-- 明細部 --%>
		var tblList = $('tblList');

		<%-- autocomplete --%>

		<%-- 検索後入力された場合の不整合をただす --%>

		if (tblList != null) {
			<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
			<%-- 対象外の例：検索条件部と明細のチェックボックス --%>
			//storeInitValues(/^srh.*/);	
		}

		//refreshLabel();
	}, false);

	function open_window(theURL,winName,features) { //v2.0
		win = window.open(theURL,winName,features);
		win.focus();
		return false;
	}
	
	<%-- 変更フラグセット --%>
	function setDirtyFlg() {
		$("dirtyFlg").checked = true;
	}
</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/DailyReportDetailList" method="post" onsubmit="return false;" styleId="mainForm">	
	<%@ include file="/jsp/common/postjavascript.jsf" %>

	<nested:hidden property="op" styleId="op"/>
	<nested:hidden property="dirtyFlg" styleId="dirtyFlg"/>
	<nested:hidden property="nextCmd" styleId="nextCmd"/>


	<nested:notEmpty property="detailList">
		<table border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td class="valignTop alignLeft">
					<%-- 明細部 --%>
					<table width="728" cellspacing="2" cellpadding="1" id="tblList">
						<nested:iterate id="detailListPage" property="detailListPage" indexId="index">
							<%-- 明細 --%>
							<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
								<tr class="bcList1" style="height:24px;">
							</app:modulo>
						
							<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
								<tr class="bcList2" style="height:24px;">
							</app:modulo>
							
								<td nowrap width="90">
									<%-- 指図番号 --%>
									<nested:equal property="areaFlg" value="true">
										<nested:text property="directionNo" styleClass="LBLL fb fcTitleList" style="width:88px;font-weight:bold;" onfocus="this.select();" readonly="True"/>
									</nested:equal>
									<nested:equal property="areaFlg" value="false">
										<nested:text property="directionNo" styleClass="LBLL fcTitleList" style="width:88px;" onfocus="this.select();" readonly="True"/>
									</nested:equal>
								</td>
								
									<%-- 品目名称 --%>
									<nested:equal property="areaFlg" value="true">
										<td nowrap width="140" class="LBLL fb fcTitleList" style="width:138px;font-weight:bold;">
											<nested:write property="itemNm" />
										</td>
									</nested:equal>
									<nested:equal property="areaFlg" value="false">
										<td nowrap width="140" class="LBLL fcTitleList" style="width:138px;">
											<nested:write property="itemNm" />
										</td>
									</nested:equal>
								
								<nested:iterate id="sagyoTimeList" property="sagyoTimeList" indexId="index">
									<td nowrap width="60">
										<%-- 作業時間 --%>
										<nested:text property="jobTimeHhmm" maxlength="6" styleClass="R" style="width:58px;" onfocus="this.select();" onchange="setDirtyFlg();"/>
									</td>
								</nested:iterate>
								<td nowrap width="60">
									<%-- 合計１ --%>
									<nested:text property="subTotal1Hhmm" styleClass="LBLR fcTitleList" style="width:58px;" onfocus="this.select();" readonly="True"/>
								</td>
								<td nowrap width="60">
									<%-- 合計２ --%>
									<nested:text property="subTotal2Hhmm" styleClass="LBLR fcTitleList" style="width:58px;" onfocus="this.select();" readonly="True"/>
								</td>
								<td nowrap width="60">
									<%-- 総合計 --%>
									<nested:text property="totalHhmm" styleClass="LBLR fcTitleList" style="width:58px;" onfocus="this.select();" readonly="True"/>
								</td>
							</tr>
						</nested:iterate>
					</table>
				</td>
			</tr>
		</table>
	</nested:notEmpty>
	<nested:notEmpty property="nextCmd">
		<script language="JavaScript" type="text/javascript">
		<!--
			<%--  --%>
			var tmp = '<nested:write property="nextCmd"/>';
			var array = tmp.split("=");
			parent.putDETAIL_OK( array[0], array[1] );
		-->
		</script>
	</nested:notEmpty>
</nested:form>

</body>
</html:html>
