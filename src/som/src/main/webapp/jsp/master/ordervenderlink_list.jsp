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

	<%-- Load時の処理 --%>
	Event.observe(window, 'load', function() {
		<%-- フォーム設定の初期化 --%>
		initializeFormState();

		<%-- 明細部 --%>
		var tblList = $('tblList');

		if (tblList != null) {
			<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
			<%-- 対象外の例：検索条件部と明細のチェックボックス --%>
			storeInitValues(/^srh.*/);	
		}
		
		<%--初期化--%>
		$("checkRowNo").value = 0;
		
		refreshLabel();
	}, false);
	
	function setVenderGroup() {
		$("venderGroupCd").value = $("selectVenderGroup").value;
	}
	
	function setDeliveryConf() {
		$("deliveryConf").value = $("selectDeliveryConf").value;
	}
	
	function setItemConf() {
		$("itemConf").value = $("selectItemConf").value;
	}
	
	function setCheckRowNo(){
	    var radioGroup = document.forms[0].rdoBtn;
	    
	    // リストがあり、かつ選択してしていない場合、なぜか長さが取得できない。（ラジオボタンはグループではないとだめだから？）やりたくないが文字列で判定。
	    if(radioGroup.length == "undefined"){
		    $("checkRowNo").value = 0;
		    return;
	    }
	    
	    for (i = 0; i < radioGroup.length; i++) {
	    	if(radioGroup[i].checked == true){
	            $("checkRowNo").value = i + 1;
	            return;
	    	}
	    }
	    $("checkRowNo").value = 1;
	}
	
	<%-- 確認メッセージ --%>
	function reportInputConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "アップロードしてもよろしいですか？";
		
		if(confirm(alertMsg[0])) {
			form_submit('op', 'upload');
		} else {
			var clnObj = $('spanUploadFile').innerHTML;
			$('spanUploadFile').innerHTML = clnObj;
			return false;
		}
	}
	
	<%-- 確認メッセージ --%>
	function reportOutputConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "帳票(EXCEL)出力してもよろしいですか？";
		return confirm(alertMsg[0]);
	}
	

	
	<%-- 入力制限--%>
	function lockInput(){
		return false;
	}

	function open_window(theURL,winName,features) { //v2.0
		win = window.open(theURL,winName,features);
		win.focus();
		return false;
	}
</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/OrderVenderLinkList" method="post" onsubmit="return false;" styleId="mainForm" enctype="multipart/form-data">	
	<%@ include file="/jsp/common/postjavascript.jsf" %>

	<nested:hidden property="op" styleId="op"/>

	<table cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td class="valignTop alignLeft">
				<table border="0" cellpadding="0" cellspacing="0" width="750">
					<tr>
						<td>
							<!-- ヘッダー部 -->
							<table border="0" cellpadding="0" cellspacing="0" width="100%">
								<tr>
									<td height="10" colspan="2"></td>
								</tr>

								<tr>
									<td class="fcTitle fb" width="250">受注データ得意先連携マスタ</td>
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

								<tr>
									<td height="5" colspan="2"></td>
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
										<table cellspacing="2" cellpadding="1"  border="0">
											<tr>
												<td class="bcTitleSearch fb fcTitleSearch" width="140">得意先グループ</td>
												<td>
													<nested:select property="srhVenderGroupCd" style="margin: 0;padding: 0" styleId="srhVenderGroupCd">
														<nested:optionsCollection property="venderGroupCombo" label="labales" value="values" />
													</nested:select>
												</td>		
											</tr>
										</table>
									</td>
								</tr>
								
								<tr>
									<td>
										<table cellspacing="2" cellpadding="1"  border="0">
											<tr>
												<td class="bcTitleSearch fb fcTitleSearch" width="140">納入先設定</td>
												<td>
												<% pageContext.setAttribute("SelectDeliveryConf", new com.asahikaseieng.app.comboboxes.SelectDeliveryConf(true, false)); %>
													<nested:select property="srhDeliveryConf" onchange="setDeliveryConf();">
														<nested:options name="SelectDeliveryConf" property="values" labelName="SelectDeliveryConf" labelProperty="labels"/>
													</nested:select>
												</td>
											</tr>
										</table>
									</td>
								</tr>

								<tr>
									<td>
										<table cellspacing="2" cellpadding="1"  border="0">
											<tr>
												<td class="bcTitleSearch fb fcTitleSearch" width="140">品目設定</td>
												<td>
												<% pageContext.setAttribute("SelectItemConf", new com.asahikaseieng.app.comboboxes.SelectItemConf(true, false)); %>
													<nested:select property="srhItemConf" onchange="setItemConf();">
														<nested:options name="SelectItemConf" property="values" labelName="SelectItemConf" labelProperty="labels"/>
													</nested:select>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								
								<tr>
									<td>
										<table cellspacing="2" cellpadding="1"  border="0">
											<tr>
												<td class="bcTitleSearch fb fcTitleSearch" width="140">アップロードファイル</td>
													<td colspan="3"><span id="spanUploadFile">
														<nested:file property="uploadFile" styleId="uploadFile" size="55"
															onkeypress="return lockInput();"
															onchange="reportInputConfirm(); return false;" />
													</span></td>
											</tr>
										</table>
									</td>
								</tr>
								
								<tr>
									<td class="alignRight">
										<table cellspacing="1" cellpadding="1"  border="0">
											<tr><%--
												<td>
													<div id="cssButton">
														<a href="#" onclick="return form_submit('op', 'download'); return false;" class="cssButton">
															アップロード用EXCELダウンロード
														</a>
													</div>
												</td>--%>
												<td>
													<div id="cssButton">
														<a href="#" onclick="return form_submit('op', 'search'); return false;" class="cssButton">
															&nbsp;&nbsp;検&nbsp;&nbsp;索&nbsp;&nbsp;
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
							</table>
						</td>
					</tr>
				</table>

				<nested:notEmpty property="searchList">
					<table border="0" cellpadding="0" cellspacing="0" width="750">
						<tr>
							<td>
								<!-- 明細部 -->
								<table width="100%" border="0">
									<tr>
										<td class="alignRight">
											<table cellspacing="1" cellpadding="1" border="0">
												<tr>
													<td>
														<div id="cssButton">
															<a href="#" onclick="if (!(reportOutputConfirm())) {return false;}else{return form_submit('op', 'report'); return false;}" class="cssButton">
																帳票(EXCEL)
															</a>
														</div>
													</td>
												</tr>
											</table>
										</td>
									</tr>

									<tr>
										<td>
											<table width="100%" cellspacing="2" cellpadding="1" id="tblList">
												<tr class="bcTitleList fb fcTitleList">
													<td></td>
													<td width="120">得意先グループ</td>
													<td>得意先グループ名</td>
													<td width="100">納入先未設定</td>
													<td width="100">品目未設定</td>
												</tr>
												<nested:iterate id="searchList" property="searchList" indexId="index">
													<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
														<tr class="bcList1">
													</app:modulo>
													
													<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
														<tr class="bcList2">
													</app:modulo>
													
													<nested:hidden property="checkRowNo" styleId="checkRowNo" />
													
													<td class="alignCenter" width="20">
														<nested:radio name="searchList" property="rdoBtn" value="<%= pageContext.findAttribute("index").toString() %>" onblur="setCheckRowNo();"/>
													</td>
													<td>
														<%-- 得意先グループコード --%>
														<nested:write property="venderGroupCd"/>
													</td>

													<td>
														<%-- 得意先グループ名 --%>
														<nested:write property="venderGroupName"/>
													</td>
													
													<td>
														<%-- 納入先設定 --%>
														<nested:write property="deliveryConfName"/>
													</td>
													
													<td>
														<%-- 品目設定 --%>
														<nested:write property="itemConfName"/>
													</td>

												</nested:iterate>

											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>

						<tr>
							<td class="alignCenter">
								<%-- ページング --%>
								<%@ include file="/jsp/common/pager/pager.jsf" %>
								<%-- ページング ここまで --%>
							</td>
						</tr>
					</table>
				</nested:notEmpty>
			</td>
		</tr>

		<%-- Excelダウンロード処理 --%>
		<nested:equal property="excelDownloadFlg" value="true">
			<META HTTP-EQUIV="REFRESH" CONTENT="0; URL=<%= request.getContextPath() %>/FileDownload/">
		</nested:equal>
	</table>
</nested:form>

</body>
</html:html>
