<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taginclude.jsf"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http//www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<%@ include file="/jsp/common/head.jsf"%>
<%@ include file="/jsp/common/head_main.jsf"%>
<%@ include file="/jsp/common/clearText.jsf" %>
<%@ include file="/jsp/common/searchNameForMaster.jsf" %>

<!-- Style Sheet -->
<link href='<%= request.getContextPath() + "/stylesheets/tab.css"%>'
	media="screen" rel="Stylesheet" type="text/css" />

<%-- ▼業務固有css ここから --%>
<style type="text/css">
</style>
<%-- ▲業務固有css ここまで --%>

<%-- ▼業務固有javaScript ここから --%>
<script language="JavaScript" type="text/javascript">

	<%--onload時の処理--%>
	Event.observe(window, 'load', function() {

		<%-- フォーム設定の初期化 --%>
		initializeFormState();

		<%-- 必須フィールド定義 --%>
		defineAsRequiredField("strOffsetDate");

		<%-- 数値型フィールド定義 --%>
		defineAsNumberField("balanceAmount");

		<%-- 日付型フィールド定義 --%>
		defineAsDateField("strOffsetDate");

		<%-- 明細部 --%>
		var payList = $('payableList');
		if (payList != null) {
			if (0 < payList.rows.length) {
				<%-- 数値型フィールド定義 --%>
				for (i = 0; null != $("strPayableOffset" + i);　i++) {
					defineAsNumberField("strPayableOffset" + i);
				}
			}
		}
		var depList = $('depositList');
		if (depList != null) {
			if (0 < depList.rows.length) {
				<%-- 数値型フィールド定義 --%>
				for (i = 0; null != $("strDepositOffset" + i);　i++) {
					defineAsNumberField("strDepositOffset" + i);
				}
			}
		}

		<%-- 検索後入力された場合の不整合をただす --%>

		storeInitValues(/^srh.*/, 'dirtyFlg');
		refreshLabel();
	}, false);

	<%-- 確認メッセージ --%>
	function putConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "登録してよろしいですか？";
	
		return confirm(alertMsg[0]);
	}

	<%-- 変更フラグセット --%>
	function setDirtyFlg() {
		$("dirtyFlg").value = "true";
	}
	
	<%-- 変更確認メッセージ --%>
	function putDirtyConfirm() {
		var flg = $("dirtyFlg").value;
		if (flg == "true") {
			// 何か値が変更されている場合
			return continueConfirm();
		}else{
			return true;
		}
	}

	<%-- 確認メッセージ --%>
	function deleteAlert() {
		alertMsg = new Array();
		alertMsg[0] = "削除してよろしいですか？";

		return confirm(alertMsg[0]);
	}

	<%-- 差額の計算 --%>
	function subtractOffset(obj){
		var deposit = 0;
		var payable = 0;
		var subtract = 0;
		
		deposit = sumDepositOffset();
		payable = sumPayableOffset();
		
		subtract = payable - deposit;
		
		$("balanceAmount").innerText = addComma(String(subtract));
	}
	
	<%-- 売掛側の相殺合計値計算 --%>
	function sumDepositOffset(){
		var total = 0;
		var atai = 0;
		var payList = $('depositList');
		if (payList != null) {
			if (0 < payList.rows.length) {
				for (i = 0; null != $("strDepositOffset" + i);　i++) {
					var offset = $("strDepositOffset" + i).value;
					if (offset != ""){
						offset = removeComma(offset);
						if(isNumeric(offset)) {
							atai = eval(offset);
							total = total + atai;
						}
					}
				}
			}
		}
		if ($("hiddenDepositOffset") != null) {
			$("hiddenDepositOffset").value = total;
			if ($("strTotalDepositOffset") != null) {
				$("strTotalDepositOffset").innerText = addComma(String(total));
			}
		}
		return total;
	}
	
	<%-- 買掛側の相殺合計値の計算 --%>
	function sumPayableOffset(){
		var total = 0;
		var atai = 0;
		var payList = $('payableList');
		if (payList != null) {
			if (0 < payList.rows.length) {
				for (i = 0; null != $("strPayableOffset" + i);　i++) {
					var offset = $("strPayableOffset" + i).value;
					if (offset != ""){
						offset = removeComma(offset);
						if(isNumeric(offset)) {
							atai = eval(offset);
							total = total + atai;
						}
					}
				}
			}
		}
		if ($("hiddenPayableOffset") != null) {
			$("hiddenPayableOffset").value = total;
			if ($("strTotalPayableOffset") != null) {
				$("strTotalPayableOffset").innerText = addComma(String(total));
			}
		}
		return total;
	}
	
	<%-- カンマ編集 --%>
	function addComma(value){
	    var i;
	    for(i = 0; i < value.length/3; i++){
	        value = value.replace(/^([+-]?\d+)(\d\d\d)/,"$1,$2");
	    }
	    return value;
	}
	
	<%-- カンマ編集解除 --%>
	function removeComma(value) {
	    return value.split(",").join("")
	}

	<%-- 数値のチェック --%>
	function isNumeric(num){
	    if (num.match(/^[+-]?\d+$/)) {
	        return true;
	    }
	    return false;
	}

	<%-- 承認依頼メッセージ --%>
	function putApprovalRequestConfirm() {
		var flg = $("dirtyFlg").value;
		alertMsg = new Array();

		if (flg == "true") {
			/* 何か値が変更されている場合 */
			alertMsg[0] = "画面の編集内容は破棄して承認依頼されます。よろしいですか？";
		} else {
			alertMsg[0] = "承認依頼を行います。よろしいですか？";
		}

		return confirm(alertMsg[0]);
	}

	<%-- 承認メッセージ --%>
	function putApprovalConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "承認します。よろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- 否認メッセージ --%>
	function putNegationConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "否認します。よろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- 承認取消メッセージ --%>
	function putApprovalCancelConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "承認取消します。よろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- 摘要出力 --%>
	function setRemark() {
		if ($("referFlg").value == "0") {
			str = $("strOffsetDate").value;
			
			<%-- 日付の書式設定 --%>
			str = formatDate(str);
			
			if (str == null || str == "") {
				$("srhSummary").value = "";
			} else {
				str = str.replace(/\//g, "");
	
				if (str.length == 8) {
					$("srhSummary").value = str.substring(2, 4) + "年" + str.substring(4, 6) + "月相殺";
				} else if (str.length == 6) {
					$("srhSummary").value = str.substring(0, 2) + "年" + str.substring(2, 4) + "月相殺";
				}
			}
		}
	}
</script>

<%-- ▲業務固有javaScriptここまで --%>

</head>

<body>

<nested:form action="/OffsetDetail" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op"/>
	<nested:hidden property="dirtyFlg" />
	<nested:hidden property="insertFlg" styleId="insertFlg"/>
	<nested:hidden property="searchFlg" styleId="searchFlg"/>
	<nested:hidden property="referFlg" styleId="referFlg"/>
	<nested:hidden property="buttonFlg" styleId="buttonFlg"/>
	<nested:hidden property="hiddenPayableOffset" styleId="hiddenPayableOffset"/>
	<nested:hidden property="hiddenDepositOffset" styleId="hiddenDepositOffset"/>

	<table cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td class="valignTop alignLeft">
				<table border="0" cellpadding="0" cellspacing="0" width="750">
					<tr>
						<td><!-- ヘッダー部 -->
							<table border="0" cellpadding="0" cellspacing="0" width="100%">
								<tr>
									<td height="10" colspan="2"></td>
								</tr>

								<tr>
									<td class="fcTitle fb">グループ間相殺入力</td>
									<td>
										<table border="0" cellpadding="0" cellspacing="0">
											<tr>
												<td><%-- メッセージ表示 --%> <%@ include
													file="/jsp/common/disp_info_message.jsf"%>
												<%-- メッセージ表示 ここまで --%></td>
											</tr>
										</table>
									</td>
								</tr>

								<tr>
									<td class="bcTitleLine" colspan="2"></td>
								</tr>

								<tr>
									<td height="5" colspan="2"></td>
								</tr>
							</table>
						</td>
					</tr>

					<tr>
						<td><%-- メッセージ表示 --%> <%@ include
							file="/jsp/common/disp_error_message.jsf"%>
							<%-- メッセージ表示 ここまで --%>
						</td>
					</tr>

					<tr>
						<td><!-- 明細部 -->
							<table>
								<tr>
									<td width="75%">
										<table cellspacing="2" cellpadding="2" border="0">
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail" width="130">部署</td>
												<td colspan="3">
													<nested:write property="organizationCd"/>:<nested:write property="srhOrganizationName"/>
												</td>
											</tr>

											<tr>
												<td class="fcTitleDetail fb bcTitleDetail">相殺グループ</td>
												<td>
													<nested:equal property="insertFlg" value="0">
														<nested:write property="offsetGroupCd"/>:<nested:write property="offsetGroupName"/>
													</nested:equal>

													<nested:equal property="insertFlg" value="1">
														<nested:equal property="searchFlg" value="0">
															<nested:select property="offsetGroupCd" styleId="offsetGroupCd">
																<nested:optionsCollection name="offsetDetailForm" property="offsetGroupCdList" value="values" label="labales"/>
															</nested:select>
														</nested:equal>

														<nested:equal property="searchFlg" value="1">
															<nested:write property="offsetGroupCd"/>:<nested:write property="offsetGroupName"/>
														</nested:equal>
													</nested:equal>
												</td>

												<td></td>
												<td></td>
											</tr>

											<tr>
												<td class="bcTitleSearch fb fcTitleSearch">分類</td>
												<td>
													<nested:equal property="insertFlg" value="0">
														<nested:write property="categoryDivi"/>:<nested:write property="categoryName"/>
													</nested:equal>

													<nested:equal property="insertFlg" value="1">
														<nested:equal property="searchFlg" value="0">
															<nested:select property="cassification" styleId="cassification">
																<nested:optionsCollection property="categoryList" label="labales" value="values" />
															</nested:select>
														</nested:equal>

														<nested:equal property="searchFlg" value="1">
															<nested:write property="categoryDivi"/>:<nested:write property="categoryName"/>
														</nested:equal>
													</nested:equal>
												</td>
											</tr>

											<tr>
												<td class="fcTitleDetail fb bcTitleDetail">相殺日付</td>
												<td>
													<nested:text property="strOffsetDate" maxlength="10" size="10" styleId="strOffsetDate" style="ime-mode:disabled" onblur="setRemark();" onchange="setDirtyFlg();" />
												</td>


												<td align="left">
													<div id="cssButton">
														<nested:equal property="insertFlg" value="1">
															<nested:equal property="searchFlg" value="0">
																<a href="#" onclick="return form_submit('op', 'search');" class="cssButton">
																	&nbsp;&nbsp;検&nbsp;&nbsp;索&nbsp;&nbsp;
																</a>
															</nested:equal>

															<nested:equal property="searchFlg" value="1">
																<a href="#" onclick="if (!(putDirtyConfirm())) {return false;}else{return form_submit('op', 'clear'); return false;}" class="cssButton">
																	&nbsp;&nbsp;クリア&nbsp;&nbsp;
																</a>
															</nested:equal>
														</nested:equal>
													</div>
												</td>
											</tr>
										</table>
									</td>

									<td align="right" width="25%" valign="top">
										<table>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail" width="94">相殺番号</td>
												<td>
													<nested:hidden property="offsetNo"/>
													<nested:write property="offsetNo"/>
												</td>
											</tr>

											<tr>
												<td align="right" class="fcTitleDetail fb bcTitleDetail">差額</td>
												<td align="right" axis="1">
													<nested:text property="balanceAmount" size="9" readonly="true"/>
												</td>
											</tr>

											<tr><td>&nbsp;</td></tr>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>

					<tr>
						<td height="10"></td>
					</tr>

					<tr>
						<td>
							<table>
								<tr>
									<nested:equal property="searchFlg" value="0">
										<td>
											<table cellspacing="2" cellpadding="1" id="payableList">
												<tr class="bcTitleList fb fcTitleList">
													<td width="370">買掛取引先一覧</td>
												</tr>
											</table>
										</td>
	
										<td>
											<table cellspacing="2" cellpadding="1" id="depositList">
												<tr class="bcTitleList fb fcTitleList">
													<td width="370">売掛取引先一覧</td>
												</tr>
											</table>
										</td>
									</nested:equal>

									<nested:equal property="searchFlg" value="1">
										<td>
											<table cellspacing="2" cellpadding="1" id="payableList">
												<tr class="bcTitleList fb fcTitleList">
													<td width="370"><nested:write property="titlePayable"/>取引先一覧</td>
												</tr>
											</table>
										</td>

										<td>
											<table cellspacing="2" cellpadding="1" id="depositList">
												<tr class="bcTitleList fb fcTitleList">
													<td width="370"><nested:write property="titleCredit"/>取引先一覧</td>
												</tr>
											</table>
										</td>
									</nested:equal>
								</tr>

								<tr>
									<td valign="top">
										<nested:notEmpty property="payableList">
											<!-- 買掛取引先一覧 -->
											<table cellspacing="2" cellpadding="1" id="payableList">
												<tr class="bcTitleList fb fcTitleList">
													<td width="200">支払先</td>
													<td width="80" align="right"><nested:write property="titlePayable"/></td>
													<td align="right">相殺金額</td>
												</tr>

												<nested:iterate id="payableList" property="payableList" indexId="index">
													<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
														<tr class="bcList1">
													</app:modulo>
							
													<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
														<tr class="bcList2">
													</app:modulo>
				
													<nested:hidden property="payableNo"></nested:hidden>
			
													<!-- 支払先 -->
													<td>
														<nested:write property="supplierCd" />:<nested:write property="supplierName"/>
													</td>

													<!-- 買掛残 -->
													<td align="right">
														<nested:write property="strPayableAmount"/>
													</td>

													<!-- 相殺金額 -->
													<nested:equal property="referFlg" name="offsetDetailForm" value="0">
														<td align="right">
															<nested:text property="strPayableOffset" size="8" styleId='<%= "strPayableOffset" + index %>'
																		 onblur="return subtractOffset(this);" style="ime-mode:disabled;text-align: right;"></nested:text>
														</td>
													</nested:equal>

													<nested:equal property="referFlg" name="offsetDetailForm" value="1">
														<td align="right" width="80">
															<nested:write property="strPayableOffset"/>
															<nested:hidden property="strPayableOffset"/>
														</td>
													</nested:equal>
												</nested:iterate>
											</table>
										</nested:notEmpty>
									</td>

									<td valign="top">
										<nested:notEmpty property="depositList">
											<!-- 売掛取引先一覧 -->
											<table cellspacing="2" cellpadding="1" id="depositList">
												<tr class="bcTitleList fb fcTitleList">
													<td width="200">請求先</td>
													<td width="80" align="right"><nested:write property="titleCredit"/></td>
													<td align="right">相殺金額</td>
												</tr>

												<nested:iterate id="depositList" property="depositList" indexId="index">
													<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
														<tr class="bcList1">
													</app:modulo>
							
													<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
														<tr class="bcList2">
													</app:modulo>
													
													<nested:hidden property="depositNo"></nested:hidden>
				
													<!-- 請求先 -->
													<td>
														<nested:write property="customerCd" />:<nested:write property="customerName"/>
													</td>

													<!-- 売掛残 -->
													<td align="right">
														<nested:write property="strCreditAmount"/>
													</td>

													<!-- 相殺金額 -->
													<nested:equal property="referFlg" name="offsetDetailForm" value="0">
														<td align="right">
															<nested:text property="strDepositOffset" size="8" styleId='<%= "strDepositOffset" + index %>'
																		 style="ime-mode:disabled;text-align: right;" onblur="return subtractOffset(this);"></nested:text>
														</td>
													</nested:equal>

													<nested:equal property="referFlg" name="offsetDetailForm" value="1">
														<td align="right" width="80">
															<nested:write property="strDepositOffset"/>
															<nested:hidden property="strDepositOffset"/>
														</td>
													</nested:equal>
												</nested:iterate>
											</table>
										</nested:notEmpty>
									</td>
								</tr>

								<tr>
									<td>
										<nested:notEmpty property="payableList">
											<!-- 買掛合計覧 -->
											<table cellspacing="2" cellpadding="1" id="tblList">
												<tr>
													<td width="190"></td>

													<td width="83" align="right" class="fcTitleDetail fb bcTitleDetail">
														<nested:write property="titlePayable"/>合計
													</td>

													<td width="83" align="right" class="fcTitleDetail fb bcTitleDetail">相殺金額計</td>
												</tr>

												<tr>
													<td></td>

													<td align="right">
														<nested:write property="strTotalPayableAmount"/>
													</td>

													<td align="right" id="strTotalPayableOffset">
														<nested:write property="strTotalPayableOffset"/>
														<nested:hidden property="strTotalPayableOffset"/>
													</td>
												</tr>
											</table>
										</nested:notEmpty>
									</td>

									<td>
										<nested:notEmpty property="depositList">
											<!-- 売掛合計覧 -->
											<table cellspacing="2" cellpadding="1" id="tblList">
												<tr>
													<td width="190"></td>

													<td width="83" align="right" class="fcTitleDetail fb bcTitleDetail">
														<nested:write property="titleCredit"/>合計
													</td>

													<td width="83" align="right" class="fcTitleDetail fb bcTitleDetail">相殺金額計</td>
												</tr>
												<tr>
													<td></td>

													<td align="right">
														<nested:write property="strTotalCreditAmount"/>
													</td>

													<td align="right" id="strTotalDepositOffset">
														<nested:write property="strTotalDepositOffset"/>
														<nested:hidden property="strTotalDepositOffset"/>
													</td>
												</tr>
											</table>
										</nested:notEmpty>
									</td>
								</tr>
							</table>
						</td>
					</tr>

					<tr>
						<td>
							<table width="" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="94">摘要</td>
									<td>
										<nested:equal property="referFlg" value="0">
											<nested:text property="srhSummary" maxlength="255" size="40" styleId="srhSummary" onchange="setDirtyFlg();"/>
										</nested:equal>

										<nested:equal property="referFlg" value="1">
											<nested:write property="srhSummary"/>
										</nested:equal>
									</td>
								</tr>
							</table>
						</td>
					</tr>

					<tr>
						<td>
							<table>
								<tr>
									<nested:equal property="referFlg" value="0">
										<nested:equal property="approvalStatus" value="1">
											<nested:equal property="approvalRequestAuthority" value="true">
												<td class="alignRight">
													<div id="cssButton">
														<a href="#" onclick="if(!putApprovalRequestConfirm()){return false;}else{return form_submit('op', 'approvalRequest'); return false;}" class="cssButton">
															&nbsp;&nbsp;承認依頼&nbsp;&nbsp;
														</a>
													</div>
												</td>
				
												<td width="50"><br></td>
											</nested:equal>
										</nested:equal>
			
										<nested:equal property="approvalStatus" value="1">
											<nested:equal property="updateAuthority" value="true">
												<nested:equal property="insertFlg" value="0">
													<td class="alignLeft">
														<div id="cssButton">
															<a href="#" onclick="if (!(putConfirm())) {return false;}else{return form_submit('op', 'update'); return false;}" class="cssButton">
																&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
															</a>
														</div>
													</td>
	
													<td width="50"><br></td>
												</nested:equal>
				
												<nested:equal property="insertFlg" value="1">
													<td class="alignLeft">
														<div id="cssButton">
															<nested:equal property="buttonFlg" value="1">
																<a href="#" onclick="if (!(putConfirm())) {return false;}else{return form_submit('op', 'insert'); return false;}" class="cssButton">
																	&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
																</a>
															</nested:equal>
														</div>
													</td>
	
													<td width="50"><br></td>
												</nested:equal>
											</nested:equal>
										</nested:equal>
			
										<nested:equal property="approvalStatus" value="0">
											<nested:equal property="updateAuthority" value="true">
												<nested:equal property="insertFlg" value="0">
													<td class="alignLeft">
														<div id="cssButton">
															<a href="#" onclick="if (!(putConfirm())) {return false;}else{return form_submit('op', 'update'); return false;}" class="cssButton">
																&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
															</a>
														</div>
													</td>
	
													<td width="50"><br></td>
												</nested:equal>
				
												<nested:equal property="insertFlg" value="1">
													<td class="alignLeft">
														<div id="cssButton">
															<nested:equal property="buttonFlg" value="1">
																<a href="#" onclick="if (!(putConfirm())) {return false;}else{return form_submit('op', 'insert'); return false;}" class="cssButton">
																	&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
																</a>
															</nested:equal>
														</div>
													</td>
	
													<td width="50"><br></td>
												</nested:equal>
											</nested:equal>
										</nested:equal>

										<nested:equal property="approvalStatus" value="2">
											<nested:equal property="referFlg" value="0">
												<nested:equal property="approvalAuthority" value="true">
													<td class="alignRight">
														<div id="cssButton">
															<a href="#" onclick="if(!putApprovalConfirm()){return false;}else{return form_submit('op', 'approval'); return false;}" class="cssButton">
																&nbsp;&nbsp;承&nbsp;&nbsp;認&nbsp;&nbsp;
															</a>
														</div>
													</td>
				
													<td width="50"><br></td>
												</nested:equal>
			
												<nested:equal property="negationAuthority" value="true">
													<td class="alignRight">
														<div id="cssButton">
															<a href="#" onclick="if(!putNegationConfirm()){return false;}else{return form_submit('op', 'negation'); return false;}" class="cssButton">
																&nbsp;&nbsp;否&nbsp;&nbsp;認&nbsp;&nbsp;
															</a>
														</div>
													</td>
				
													<td width="50"><br></td>
												</nested:equal>
											</nested:equal>
										</nested:equal>
			
										<nested:equal property="approvalStatus" value="3">
											<nested:equal property="referFlg" value="0">
												<nested:equal property="approvalCancelAuthority" value="true">
													<td class="alignRight">
													<div id="cssButton"><a href="#"
														onclick="if(!putApprovalCancelConfirm()){return false;}else{return form_submit('op', 'approvalCancel'); return false;}"
														class="cssButton"> &nbsp;&nbsp;承認取消&nbsp;&nbsp; </a></div>
													</td>
					
													<td width="50"><br>
													</td>
												</nested:equal>
											</nested:equal>
										</nested:equal>
			
										<nested:equal property="approvalStatus" value="1">
											<nested:equal property="deleteAuthority" value="true">
												<nested:equal property="insertFlg" value="0">
													<td class="alignCenter">
														<div id="cssButton">
															<a href="#" onclick="if (!(deleteAlert())) {return false;}else{return form_submit('op', 'delete'); return false;}" class="cssButton">
																&nbsp;&nbsp;削&nbsp;&nbsp;除&nbsp;&nbsp;
															</a>
														</div>
													</td>
	
													<td width="50"><br></td>
												</nested:equal>
											</nested:equal>
										</nested:equal>
									</nested:equal>

									<td class="alignRight">
										<div id="cssButton">
											<a href="#" onclick="if (!(putDirtyConfirm())) {return false;}else{return form_submit('op', 'back'); return false;}" class="cssButton">
												&nbsp;&nbsp;戻&nbsp;&nbsp;る&nbsp;&nbsp;
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
	</table>

</nested:form>
</body>
</html:html>
