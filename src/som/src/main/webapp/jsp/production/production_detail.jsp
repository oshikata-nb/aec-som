<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taginclude.jsf"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http//www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<%@ include file="/jsp/common/head.jsf"%>
<%@ include file="/jsp/common/head_main.jsf"%>
<%@ include file="/jsp/common/clearText.jsf" %>
<%@ include file="/jsp/common/searchNameForMaster.jsf" %>

<%-- Style Sheet --%>
<link href='<%= request.getContextPath() + "/stylesheets/tab.css"%>'
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
		var tblList = $('tblList');
		
		<%-- 必須フィールド定義 --%>
			<%-- 新規の場合 --%>
			if ( $("insertFlg").value == "1" && tblList == null){
				defineAsRequiredField("itemCd");
				defineAsRequiredField("strOrderLet");
			}
		
		<%-- 日付型フィールド定義 --%>
			<%-- 新規の場合 --%>
			if ( $("insertFlg").value == "1" && tblList == null){
				defineAsYMField("strOrderLet");
			}
		
		<%-- 数値型フィールド定義 --%>
		if (tblList != null) {
			if (0 < tblList.rows.length) {
				for (i = 0; i < $("detailListLength").value; i++) {
					if( $("strOrderExpectQty" + i) != null){
						defineAsNumberField("strOrderExpectQty" + i);
					}
				}
			}
		}

		<%-- 検索後入力された場合の不整合をただす --%>
		storeInitValues(/^srh.*/, 'dirtyFlg');
		refreshLabel();
		
		<%-- 新規の場合 --%>
		if ( $("insertFlg").value == "1" && tblList == null){
			<%-- 品目名称のauto complete --%>
			new Ajax.Autocompleter(
			  "itemCd",
			  "autocomplete_selection",
			  "<%= request.getContextPath() %>/ItemForAutoComplete.do?op=searchItem",
			  {
			  	paramName : "code",
			    afterUpdateElement : setItemLabel
			  }
			);
			<%-- 名称のクリアauto complete --%>
			Event.observe('itemCd', 'keypress', clearText.bindAsEventListener($('itemName')), false);
			Event.observe('itemCd', 'keypress', clearText.bindAsEventListener($('otherCompanyCd1')), false);
			Event.observe('itemCd', 'keypress', clearLabel.bindAsEventListener($('styleOfPacking')), false);
	
			<%-- 他社コード１のauto complete --%>
			new Ajax.Autocompleter(
			  "otherCompanyCd1",
			  "autocomplete_selection",
			  "<%= request.getContextPath() %>/ItemForAutoComplete.do?op=searchDetailItemOtherCompany",
			  {
			  	paramName : "code",
			    afterUpdateElement : setOtherCompany1Label
			  }
			);
			Event.observe('otherCompanyCd1', 'keypress', clearText.bindAsEventListener($('itemName')), false);
			Event.observe('otherCompanyCd1', 'keypress', clearText.bindAsEventListener($('itemCd')), false);
			Event.observe('otherCompanyCd1', 'keypress', clearLabel.bindAsEventListener($('styleOfPacking')), false);
			
		}

	}, false);

	<%-- 確認メッセージ --%>
	function putConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "登録してもよろしいですか？";

		return confirm(alertMsg[0]);
	}

	<%-- 変更フラグセット --%>
	function setDirtyFlg() {
		$("dirtyFlg").value = "true";
	}
	
	<%-- 変更有の確認メッセージ --%>
	function putDirtyConfirmIssue() {
		var flg = $("dirtyFlg").value;
		if (flg == "true" && isEdited()) {
			// 何か値が変更されている場合
			alertMsg = new Array();
			alertMsg[0] = "画面の編集内容は反映されません、よろしいですか？";
			return confirm(alertMsg[0]);
		}else{
			return true;
		}
	}
	
	<%-- 品目名称auto completeの選択後処理 --%>
	function setItemLabel(text, li) {
	    $("itemName").value = li.getElementsByTagName('span')[0].innerText;
	    $("otherCompanyCd1").value = getHiddenValue(li,"otherCompanyCd1");
	    $("styleOfPacking").value = getHiddenValue(li,"styleOfPacking");
		refreshLabel();
	}
	<%-- 他社コード１auto completeの選択後処理 --%>
	function setOtherCompany1Label(text, li) {
	    $("itemName").value = li.getElementsByTagName('span')[0].innerText;
	    $("itemCd").value = getHiddenValue(li,"code");
	    $("styleOfPacking").value = getHiddenValue(li,"styleOfPacking");
		refreshLabel();
	}
	
	<%-- 合計を計算、フォーマット --%>
	function calcSum(chkbox) {
		var splitName = chkbox.name.split(/[\[ \]]/);
		var rec = splitName[1];
		var prefix = "detailList[" + splitName[1] + "].";
		var expectQtyBlankFlag = false; //見込み数量がブランクかどうか
		
		var strOrderAcceptQty = $("strOrderAcceptQty" + rec).value; //受注数量取得
		var strOrderExpectQty =$("strOrderExpectQty" + rec).value; //見込み数量取得
	
		var quantityDecimalPoint =$(prefix + "quantityDecimalPoint").value; //小数点桁数取得
		var quantityRound =$(prefix + "quantityRound").value; //端数区分取得
		
		if( blank(strOrderAcceptQty)){ //受注数量がブランクであれば0として計算
			strOrderAcceptQty = "0"
		}
		if( blank(strOrderExpectQty)){　//見込み数量がブランクであれば0として計算
			strOrderExpectQty = "0"
			expectQtyBlankFlag = true;
		}
		
		var strOrderAcceptQty2 = parseFloat(strOrderAcceptQty.replace(/,/g, "")); //受注数量のカンマを取りフロートへ変換
		var strOrderExpectQty2 = parseFloat(strOrderExpectQty.replace(/,/g, "")); //見込み数量のカンマを取りフロートへ変換
		
		if( !isNaN(strOrderAcceptQty2) && !isNaN(strOrderExpectQty2) ){ //数値であれば
			//合計の計算
			var strSumOrderQty = strOrderAcceptQty2 + strOrderExpectQty2;
			
			//見込み数量のフォーマット変換
			strOrderExpectQty2 = digitFormat(quantityDecimalPoint,quantityRound,strOrderExpectQty2);
			//合計のフォーマット変換
			strSumOrderQty = digitFormat(quantityDecimalPoint,quantityRound,strSumOrderQty);
			
			if(!expectQtyBlankFlag){
				$("strOrderExpectQty" + rec).value = strOrderExpectQty2;
			}
			$("strSumOrderQty" + rec).update(strSumOrderQty);
		}
		
		listSum();//一覧合計を再計算
	}

	<%-- 一覧合計を計算、フォーマット --%>
	function listSum() {
		var sumOrderExpectQty = 0.0;
		var sumSumOrderQty = 0.0;
	
		var quantityDecimalPoint = $("detailList[0]." + "quantityDecimalPoint").value; //小数点桁数取得
		var quantityRound = $("detailList[0]." + "quantityRound").value; //端数区分取得
		
		for (i = 0; i < $("detailListLength").value; i++) {
			if( $("strOrderExpectQty" + i) != null){
				var strOrderExpectQty = $("strOrderExpectQty" + i).value; //見込み取得
				if( !blank(strOrderExpectQty)){　//ブランクでなければ
					var strOrderExpectQty2 = parseFloat(strOrderExpectQty.replace(/,/g, "")); //カンマを取りフロートへ変換
					if( !isNaN(strOrderExpectQty2)){
						sumOrderExpectQty = sumOrderExpectQty + strOrderExpectQty2;
					}
				}
			}
			if( $("strSumOrderQty" + i) != null){
				var strSumOrderQty = $("strSumOrderQty" + i).innerText; //合計
				if( !blank(strSumOrderQty)){　//ブランクでなければ
					var strSumOrderQty2 = parseFloat(strSumOrderQty.replace(/,/g, "")); //カンマを取りフロートへ変換
					if( !isNaN(strSumOrderQty2)){
						sumSumOrderQty = sumSumOrderQty + strSumOrderQty2;
					}
				}
			}
		}
		
		//見込み数量合計のフォーマット変換
		sumOrderExpectQty = digitFormat(quantityDecimalPoint,quantityRound,sumOrderExpectQty);
		//リスト全体合計のフォーマット変換
		sumSumOrderQty = digitFormat(quantityDecimalPoint,quantityRound,sumSumOrderQty);
			
		$("sumExpectQty").update(sumOrderExpectQty);
		$("sumAllOrderQty").update(sumSumOrderQty);
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

</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/ProductionDetail" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="dirtyFlg" />
	<nested:hidden property="insertFlg" />
	
	<nested:hidden property="detailListLength" styleId="detailListLength"/>

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
							<td height="5"  colspan="2" ></td>
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
					<td><%-- 明細部 --%>
						<table cellspacing="2" cellpadding="1" width="" border="0" >
							<tr>
								<td>
								<%-- 更新の場合 --%>
								<nested:equal property="insertFlg" value="0">
									<table cellspacing="2" cellpadding="1"  border="0">
									 	<tr>
									 		<td class="fcTitleDetail fb bcTitleDetail" >荷主</td>
									 		<td colspan="2">
									 			<nested:write property="strShipperDivision" />
									 		</td>
					                    </tr>
					                    <tr>
					                        <td class="fcTitleDetail fb bcTitleDetail">社内製造品/外注品区分</td>
					                        <td colspan="2">
					                        	<nested:write property="strTypeDivision" />
					                        </td>
					                    </tr>
					                    <tr>
					                        <td class="fcTitleDetail fb bcTitleDetail">工場名</td>
					                        <td colspan="2">
					                        	<nested:write property="productionLineName" />
					                        </td>
					                    </tr>
					                    <tr>
					                        <td class="fcTitleDetail fb bcTitleDetail" width="140">品目コード</td>
					                        <td width="100">
					                        	<nested:write property="itemCd" />
					                        </td>
					                        <td width="300">
					                        	<nested:write property="itemName" />
											</td>
											<td class="fcTitleDetail fb bcTitleDetail" width="150">他社コード１</td>
											<td width="280">
												<nested:write property="otherCompanyCd1" />
											</td>
					                    </tr>
					                    <tr>
					                        <td class="fcTitleDetail fb bcTitleDetail" width="210">生産計画年月(YYYY/MM)</td>
					                        <td colspan="2">
					                        	<nested:write property="strOrderLet" />
					                        </td>
					                        <td class="fcTitleDetail fb bcTitleDetail" width="150">荷姿</td>
					                        <td colspan="2">
					                        	<nested:write property="styleOfPacking" />
					                        </td>
					                    </tr>
					            	</table>
					            </nested:equal>
					            <%-- 新規の場合 --%>
					            <nested:equal property="insertFlg" value="1">
					            	<nested:equal property="detailListLength" value="0">
						            	<table border="0" cellspacing="2" cellpadding="1">
							            	<tr>
			                        			<%-- 検索入力：品目コード --%>							
												<td class="bcTitleSearch fb fcTitleSearch">品目コード</td>
												<td width="280" nowrap>
													<nested:text property="itemCd" maxlength="20" size="10" styleId="itemCd" />
													<div id="autocomplete_selection" class="autocomplete"></div>
													<nested:text property="itemName" maxlength="40" size="16" readonly="true" styleId="itemName" styleClass="noborderAl" onchange="setDirtyFlg();" tabindex="-1" />
												</td>
												
												<%-- 他社コード１ --%>		
												<td class="bcTitleSearch fb fcTitleSearch" width="150">他社コード１</td>
												<td >
													<nested:text property="otherCompanyCd1" maxlength="20" size="10" styleId="otherCompanyCd1" />
												</td>
											</tr>
											<tr>
												<!-- 検索入力：生産計画年月 -->
			                        			<td class="bcTitleSearch fb fcTitleSearch" width="180" nowrap>生産計画年月(YYYY/MM)</td>
			                        			<td>
			                        				<nested:text property="strOrderLet" maxlength="6" size="10" styleId="strOrderLet" style="ime-mode:disabled"/>
			                        			</td>
						                        <td class="fcTitleDetail fb bcTitleDetail" width="150">荷姿</td>
						                        <td colspan="2">
												<div id="lblStyleOfPacking">
													<nested:write property="styleOfPacking" />
												</div>
												<nested:hidden property="styleOfPacking" styleId="styleOfPacking"/>
						                        </td>
											</tr>
										</table>
									</nested:equal>
									<nested:notEqual property="detailListLength" value="0">
						            	<table border="0" cellspacing="2" cellpadding="1">
							            	<tr>
			                        			<%-- 検索入力：品目コード --%>							
												<td class="bcTitleSearch fb fcTitleSearch">品目コード</td>
												<td width="280" nowrap>
													<nested:write property="itemCd" />
													<nested:write property="itemName" />
												</td>
												
												<%-- 他社コード１ --%>		
												<td class="bcTitleSearch fb fcTitleSearch" width="150">他社コード１</td>
												<td >
													<nested:write property="otherCompanyCd1" />
												</td>
											</tr>
											<tr>
												<!-- 検索入力：生産計画年月 -->
			                        			<td class="bcTitleSearch fb fcTitleSearch" width="180" nowrap>生産計画年月(YYYY/MM)</td>
			                        			<td>
			                        				<nested:write property="strOrderLet" />
			                        			</td>
						                        <td class="fcTitleDetail fb bcTitleDetail" width="150">荷姿</td>
						                        <td colspan="2">
						                        	<nested:write property="styleOfPacking" />
						                        </td>
											</tr>
										</table>
									</nested:notEqual>
					            </nested:equal>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				
				<%-- 更新の場合  更新の場合は空行表示--%>
				<nested:equal property="insertFlg" value="0">
					<tr>
						<td class="alignRight">
							<table cellspacing="1" cellpadding="1"  border="0">
								<tr>
			                        <td class="alignRight valignMiddle" height="30"></td>
	                        		<td></td>
	                      		</tr>
	                      	</table>
	                	</td>
	                </tr>
                </nested:equal>
                <%-- 新規の場合  ボタン表示--%>
				<nested:equal property="insertFlg" value="1">
					<nested:equal property="detailListLength" value="0">
						<tr><td align="right">
							<table>
								<tr>
									<td class="alignRight">
										<div id="cssButton">
											<a href="#" onclick="if (!(putDirtyConfirmIssue())) {return false;}else{return form_submit('op', 'initNewInputed'); return false;}" class="cssButton">
												&nbsp;&nbsp;Ｏ&nbsp;&nbsp;Ｋ&nbsp;&nbsp;
											</a>
										</div>
									</td>
									<td>
										<div id="cssButton">
											<a href="#" onclick="if (!(putDirtyConfirmIssue())) {return false;}else{return form_submit('op', 'back'); return false;}" class="cssButton">
												&nbsp;&nbsp;戻&nbsp;&nbsp;る&nbsp;&nbsp;
											</a>
										</div>
									</td>
								</tr>
							</table>
						</td></tr>
					</nested:equal>
				</nested:equal>
				
				<%-- ライン表示 --%>
		      	<tr height="5">
                	<td></td>
                	<td></td>
                </tr>            
               	<tr>
                	<td class="bcTitleLine"></td>
                	<td class="bcTitleLine"></td>
                </tr>
                
                <%-- 明細　リストはここから　 --%>
                <nested:notEmpty property="detailList">
                <tr>
                	<td>
                		<table cellspacing="2" cellpadding="1" id="tblList">
                			<tr class="bcTitleList fb fcTitleList">
                				<td align="center" width="100" nowrap><B>日付</B></td>
                				<td align="center" width="80" nowrap><B>受注数量</B></td>
                				<td width="80" align="center" nowrap><B>見込数量</B></td>
                				<td width="80" align="center" nowrap><B>合計</B></td>
                				<td width="40" align="center" nowrap valign="middle"><B>単位</B></td>
                				<td align="center"><B>備考</B></td>
                				<td align="center"><B>ｽﾃｰﾀｽ</B></td>
                			</tr>
                		
                			<nested:iterate id="detailList" property="detailList"
										indexId="index">
								
								<%-- 小数点桁数 --%>
								<nested:hidden property="quantityDecimalPoint" />
								<%-- 端数区分 --%>
								<nested:hidden property="quantityRound" />
								
								<%-- 1段目 --%>
								<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
									<tr class="bcList1">
								</app:modulo>
	
								<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
									<tr class="bcList2">
								</app:modulo>
								
									<%-- 日付 --%>
									<td>
										<nested:equal property="calHolidayFlag" value="1">
											<font color="red">
												<nested:write property="strCalDate" />
											</font>
										</nested:equal>
										<nested:notEqual property="calHolidayFlag" value="1">
											<nested:write property="strCalDate" />
										</nested:notEqual>
									</td>
									<%-- 受注数量 --%>
									<td align="right">
										<nested:write property="strOrderAcceptQty" />
										<nested:hidden property="strOrderAcceptQty" styleId="<%="strOrderAcceptQty" + pageContext.findAttribute("index").toString() %>"/>
									</td>
									<%-- 見込数量 --%>
									<td align="right">
										<nested:equal property="status" value="1">
											<nested:write property="strOrderExpectQty" />
											<nested:hidden property="strOrderExpectQty" styleId="<%="strOrderExpectQty" + pageContext.findAttribute("index").toString() %>"/>
										</nested:equal>
										<nested:notEqual property="status" value="1">
											<nested:text property="strOrderExpectQty" onblur="calcSum(this)" onchange="setDirtyFlg();" style="ime-mode:disabled" maxlength="22" size="10" styleId="<%="strOrderExpectQty" + pageContext.findAttribute("index").toString()%>"/>
										</nested:notEqual>
									</td>
									<%-- 合計 --%>
									<td align="right">
										<div id="<%="strSumOrderQty" + pageContext.findAttribute("index").toString() %>"><nested:write property="strSumOrderQty" /></div>
										<nested:hidden property="strSumOrderQty" />
									</td>
									<%-- 単位 --%>
									<td align="center">
										<nested:write property="unit" />
									</td>
									<%-- 備考 --%>
									<td>
										<nested:equal property="status" value="1">
											<nested:write property="orderComment" />
											<nested:hidden property="orderComment" />
										</nested:equal>
										<nested:notEqual property="status" value="1">
											<nested:text property="orderComment" onchange="setDirtyFlg();" maxlength="50" size="40" styleId="orderComment" />
										</nested:notEqual>
									</td>
									<%-- ステータス --%>
									<td>
										<nested:equal property="status" value="1">
											確定
										</nested:equal>
										<nested:notEqual property="status" value="1">
											未確定
										</nested:notEqual>
									</td>
								
								
								</tr>
							</nested:iterate>
							
							<%-- 1段目 --%>
							<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
								<tr class="bcList1">
							</app:modulo>

							<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
								<tr class="bcList2">
							</app:modulo>
								<td align="right">
									<b>合計</b>
								</td>
								<td align="right">
										<div id="sumAcceptQty"><nested:write property="sumAcceptQty" /></div>
								</td>
								<td align="right">
										<div id="sumExpectQty"><nested:write property="sumExpectQty" /></div>
								</td>
								<td align="right">
										<div id="sumAllOrderQty"><nested:write property="sumAllOrderQty" /></div>
								</td>
							</tr>	
                		</table>
                	</td>
                </tr>		
				<tr>
					<td class="alignCenter">
						<table>
							<tr>
								<%-- ボタン類はここから --%>
								<%-- 更新の場合 --%>
								<nested:equal property="insertFlg" value="0">
									<nested:equal property="updateAuthority" value="true">
										<td class="alignCenter">
											<div id="cssButton">
												<a href="#" onclick="if (!(putConfirm())) {return false;}else{return form_submit('op', 'update'); return false;}" class="cssButton">
												&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
												</a>
											</div>
										</td>
										<td width="50"><br></td>
									</nested:equal>
									<td class="alignCenter">
										<div id="cssButton">
											<a href="#" onclick="if (!(putDirtyConfirmIssue())) {return false;}else{return form_submit('op', 'back'); return false;}" class="cssButton">
												&nbsp;&nbsp;戻&nbsp;&nbsp;る&nbsp;&nbsp;
											</a>
										</div>
									</td>
								</nested:equal>
								<%-- 更新の場合 --%>
								<nested:equal property="insertFlg" value="1">
									<nested:notEqual property="detailListLength" value="0">
										<nested:equal property="updateAuthority" value="true">
											<td class="alignCenter">
												<div id="cssButton">
													<a href="#" onclick="if (!(putConfirm())) {return false;}else{return form_submit('op', 'update'); return false;}" class="cssButton">
													&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
													</a>
												</div>
											</td>
											<td width="50"><br></td>
										</nested:equal>
										<td class="alignCenter">
											<div id="cssButton">
												<a href="#" onclick="if (!(putDirtyConfirmIssue())) {return false;}else{return form_submit('op', 'back'); return false;}" class="cssButton">
													&nbsp;&nbsp;戻&nbsp;&nbsp;る&nbsp;&nbsp;
												</a>
											</div>
										</td>
									</nested:notEqual>
								</nested:equal>
							</tr>
						</table>
					</td>
				</tr>
				</nested:notEmpty>
				<%-- 明細終わり --%>
			</table>
			</td>
		</tr>
</nested:form>
</body>
</html:html>
