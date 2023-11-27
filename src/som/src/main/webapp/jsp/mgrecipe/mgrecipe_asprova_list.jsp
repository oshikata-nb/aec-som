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

		<%-- 数値型フィールド定義 --%>
		if ($F("recipeUse") == "4") {
			if ($F("upperCount") > 0) {
				defineAsNumberField("upperYouinsu");
			}
			if ($F("lowerCount") > 0) {
				defineAsNumberField("lowerYouinsu");
			}
		}

		storeInitValues(/^srh.*/, 'dirtyFlg');
		refreshLabel();

	}, false);

	function checkCheskFlg() {
		if (!noCheck()) {
		  return false;
		}
		return true;
	}

	<%-- チェックボックス選択無しチェック --%>
	function noCheck(){
		var checkFlag = false;	  

		var upperCount = $F("upperCount");
		for (var i = 0; i < upperCount; i++) {
			var item = $("upperSearchList[" + i + "].checkFlg");
			if(item.checked){
				checkFlag = true;
				break;
			}
		}

		if (!checkFlag) {
			var lowerCount = $F("lowerCount");
			for (var i = 0; i < lowerCount; i++) {
				var item = $("lowerSearchList[" + i + "].checkFlg");
				if(item.checked){
					checkFlag = true;
					break;
				}
			}
		}

		if (!checkFlag) {
		    alert("更新対象がありません。");
		    return false;
		}
		return true;
	}

	<%-- 変更フラグセット --%>
	function setDirtyFlg() {
		$("dirtyFlg").value = true;
	}

	<%-- asprova詳細画面への遷移 --%>
	function toDetail(url) {
		if (dirtyConfirm()) {
			location.href=url;
			g_lock = true;
		}
	}

</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/MgrecipeAsprovaList" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="recipeUse" styleId="recipeUse" />
	<nested:hidden property="upperCount" styleId="upperCount" />
	<nested:hidden property="lowerCount" styleId="lowerCount" />
	<nested:hidden property="registFlg" styleId="registFlg" />
	<nested:hidden property="srhLink" styleId="srhLink" />

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
							<td class="fcTitle fb">基本処方</td>
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

				<%-- 共通項目部・タブ>>>>> --%>
				<%@ include file="/jsp/mgrecipe/mgrecipe_common.jsp"%>
				<%-- 共通項目部・タブ<<<<< --%>

<%-- 詳細内容 --%>
			<%-- 上段 --%>
				<tr>
					<td>
						<table width="750" cellspacing="0" cellpadding="0" border="0">
							<tr>
								<td>
									<span id="slidetabs" style="clear: left;">
										<table border="0" cellspacing="2" cellpadding="2" width="100%">
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail" width="120" colspan="2">工程グループ</td>
												<td width="100" colspan="2">
													<nested:write property="upperOpeGrpNm"/>
												</td>
												<td class="fcTitleDetail fb bcTitleDetail" width="120" colspan="2">設備グループ</td>
												<td width="100" colspan="2">
													<nested:select property="upperResGrpCd" style="margin: 0;padding: 0" styleId="upperResGrpCd" >
														<nested:optionsCollection property="upperResGrpCombo" label="labales" value="values" />
													</nested:select>
												</td>
												<td class="alignRight valignMiddle" colspan="2">
													<div id="cssButton">
														<a href="#" onclick="return form_submit('op', 'searchUpperList'); return false;" 
															class="cssButton">&nbsp;&nbsp;検&nbsp;&nbsp;索&nbsp;&nbsp;
														</a>
													</div>
												</td>
											</tr>
											
										<nested:notEmpty property="upperSearchList">
											<nested:equal property="recipeUse" value="4">
												<tr>
													<td class="fcTitleDetail fb bcTitleDetail" width="60">要員数</td>
													<td width="10">
														<nested:text property="upperYouinsu" styleId="upperYouinsu" size="5" onchange="setDirtyFlg();" style="ime-mode:disabled"></nested:text>
													</td>
													<td colspan="8"></td>
												</tr>
											</nested:equal>
										</nested:notEmpty>
										</table>

										<nested:notEmpty property="upperSearchList">

										<table border="0" cellspacing="2" cellpadding="2" width="100%">

											<tr>
												<td colspan="5">
												<table cellspacing="2" cellpadding="1" width="100%" border="0" id="upperTblList">
													<tr class="bcTitleList fb fcTitleList">
														<td width="7"></td>
														<td width="150">設備コード</td>
														<td width="360">設備名称</td>
														<td width="50">優先度</td>
														<td width="120">標準生産量当り工程時間</td>
														<td width="8">詳細</td>
													</tr>
													<nested:iterate id="upperSearchList" property="upperSearchList" indexId="index">
														<app:modulo
															numerator='<%=pageContext.findAttribute("index").toString()%>'
															denominator="2" zero="false">
															<tr class="bcList1">
														</app:modulo>
				
														<app:modulo
															numerator='<%=pageContext.findAttribute("index").toString()%>'
															denominator="2" zero="true">
															<tr class="bcList2">
														</app:modulo>
													
														<%-- チェックボックス --%>
														<td>
															<nested:checkbox property="checkFlg" styleId="<%="checkFlg" + pageContext.findAttribute("index").toString() %>" />
														</td>
				
														<%-- 設備コード --%>
														<td>
															<nested:write property="resouceCd" />
														</td>
														
														<%-- 設備名称 --%>
														<td>
															<nested:write property="resouceName" />
														</td>
														
														<%-- 優先度 --%>
														<td class="alignRight">
															<nested:notEmpty property="recipeId">
																<nested:write property="strRecipePriority" />
															</nested:notEmpty>
														</td>
				
														<%-- 標準生産量当り工程時間 --%>
														<td>
															<nested:notEmpty property="recipeId">
																<nested:write property="strProcessWorkTime1" />
																	x + 
																<nested:write property="strProcessWorkTime2" />
															</nested:notEmpty>
														</td>
				
														<%-- 詳細 --%>
														<nested:empty property="recipeId">
															<td>
																	選
															</td>
														</nested:empty>
														<nested:notEmpty property="recipeId">
															<nested:define id="oid1" property="recipeId" />
															<nested:define id="oid2" property="resouceGroupCd" />
															<nested:define id="oid3" property="operationGroupCd" />
															<nested:define id="oid4" property="resouceCd" />
															<nested:define id="oid5" property="srhLink" />
															<td>
																<html:link href="#" onclick='<%="toDetail('" + request.getContextPath() + "/MgrecipeAsprovaDetail.do?op=init&recipeId=" + pageContext.findAttribute("oid1").toString() + "&resouceGroupCd=" + pageContext.findAttribute("oid2").toString() + "&operationGroupCd=" + pageContext.findAttribute("oid3").toString() + "&resouceCd=" + pageContext.findAttribute("oid4").toString() + "&srhLink=" + pageContext.findAttribute("oid5").toString() + "'); return false;"%>'>
																	選
																</html:link>
															</td>
														</nested:notEmpty>
													</nested:iterate>
												</table>
												</td>
											</tr>
										</table>
										</nested:notEmpty>
									</span>
								</td>
							</tr>
						</table>
					</td>
				</tr>

		<%-- 下段 --%>
			<nested:equal property="dispLowerListFlg" value="1">
				<tr>
					<td>
						<table width="750" cellspacing="0" cellpadding="0" border="0">
							<tr>
								<td>
									<span id="slidetabs" style="clear: left;">
										<table border="0" cellspacing="2" cellpadding="2" width="100%">
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail" width="120" colspan="2">工程グループ</td>
												<td width="100" colspan="2">
													<nested:write property="lowerOpeGrpNm"/>
												</td>
												<td class="fcTitleDetail fb bcTitleDetail" width="120" colspan="2">設備グループ</td>
												<td width="100" colspan="2">
													<nested:select property="lowerResGrpCd" style="margin: 0;padding: 0" styleId="lowerResGrpCd" >
														<nested:optionsCollection property="lowerResGrpCombo" label="labales" value="values" />
													</nested:select>
												</td>
												<td class="alignRight valignMiddle" colspan="2">
													<div id="cssButton">
														<a href="#" onclick="return form_submit('op', 'searchLowerList'); return false;" 
															class="cssButton">&nbsp;&nbsp;検&nbsp;&nbsp;索&nbsp;&nbsp;
														</a>
													</div>
												</td>
											</tr>
											<nested:notEmpty property="lowerSearchList">
												<tr>
													<td class="fcTitleDetail fb bcTitleDetail" width="60">要員数</td>
													<td width="10">
														<nested:text property="lowerYouinsu" styleId="lowerYouinsu" size="5" onchange="setDirtyFlg();" style="ime-mode:disabled"></nested:text>
													</td>
												</tr>
											</nested:notEmpty>

										</table>

										<nested:notEmpty property="lowerSearchList">

										<table border="0" cellspacing="2" cellpadding="2" width="100%">

											<tr>
												<td colspan="5">
												<table cellspacing="2" cellpadding="1" width="100%" border="0" id="lowerTblList">
													<tr class="bcTitleList fb fcTitleList">
														<td width="7"></td>
														<td width="150">設備コード</td>
														<td width="360">設備名称</td>
														<td width="50">優先度</td>
														<td width="120">標準生産量当り工程時間</td>
														<td width="8">詳細</td>
													</tr>
													<nested:iterate id="lowerSearchList" property="lowerSearchList" indexId="index">
														<app:modulo
															numerator='<%=pageContext.findAttribute("index").toString()%>'
															denominator="2" zero="false">
															<tr class="bcList1">
														</app:modulo>
				
														<app:modulo
															numerator='<%=pageContext.findAttribute("index").toString()%>'
															denominator="2" zero="true">
															<tr class="bcList2">
														</app:modulo>
													
														<%-- チェックボックス --%>
														<td>
															<nested:checkbox property="checkFlg" styleId="<%="checkFlg" + pageContext.findAttribute("index").toString() %>" />
														</td>
				
														<%-- 設備コード --%>
														<td>
															<nested:write property="resouceCd" />
														</td>
														
														<%-- 設備名称 --%>
														<td>
															<nested:write property="resouceName" />
														</td>
														
														<%-- 優先度 --%>
														<td class="alignRight">
															<nested:notEmpty property="recipeId">
																<nested:write property="strRecipePriority" />
															</nested:notEmpty>
														</td>
				
														<%-- 標準生産量当り工程時間 --%>
														<td>
															<nested:notEmpty property="recipeId">
																<nested:write property="strProcessWorkTime1" />
																	x + 
																<nested:write property="strProcessWorkTime2" />
															</nested:notEmpty>
														</td>
				
														<%-- 詳細 --%>
														<nested:empty property="recipeId">
															<td>
																	選
															</td>
														</nested:empty>
														<nested:notEmpty property="recipeId">
															<nested:define id="oid1" property="recipeId" />
															<nested:define id="oid2" property="resouceGroupCd" />
															<nested:define id="oid3" property="operationGroupCd" />
															<nested:define id="oid4" property="resouceCd" />
															<nested:define id="oid5" property="srhLink" />
															<td>
																<html:link href="#" onclick='<%="toDetail('" + request.getContextPath() + "/MgrecipeAsprovaDetail.do?op=init&recipeId=" + pageContext.findAttribute("oid1").toString() + "&resouceGroupCd=" + pageContext.findAttribute("oid2").toString() + "&operationGroupCd=" + pageContext.findAttribute("oid3").toString() + "&resouceCd=" + pageContext.findAttribute("oid4").toString() + "&srhLink=" + pageContext.findAttribute("oid5").toString() + "'); return false;"%>'>
																	選
																</html:link>
															</td>
														</nested:notEmpty>
													</nested:iterate>
												</table>
												</td>
											</tr>
										</table>
										</nested:notEmpty>
									</span>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</nested:equal>

				<tr>
					<td class="alignCenter">
						<table cellpadding="0" cellspacing="0" width="450" border="0">
							<tr>
								<nested:notEqual property="srhLink" value="1">
									<nested:equal property="approvalStatus" value="1">
									<nested:notEqual property="registFlg" value="0">
										<nested:equal property="updateAuthority" value="true">
											<td class="alignCenter">
												<div id="cssButton">
													<a href="#" onclick="if (!(putConfirm())) {return false;}else{if(!(noCheck())) {return false;}else{return form_submit('op', 'regist'); return false;}}" class="cssButton">
														&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
													</a>
												</div>
											</td>
			
											<td width="50"><br></td>
										</nested:equal>
	
									</nested:notEqual>
									</nested:equal>
	
									<td class="alignCenter">
										<div id="cssButton">
											<a href="#" onclick="if (!(putDirtyConfirm())) {return false;}else{return form_submit('op', 'back'); return false;}" class="cssButton">
												&nbsp;&nbsp;戻&nbsp;&nbsp;る&nbsp;&nbsp;
											</a>
										</div>
									</td>
								</nested:notEqual>
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
