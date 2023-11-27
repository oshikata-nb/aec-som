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
		<logic:equal name="tankLockListForm" property="searchList" value="false">
			initializeFormState();
		</logic:equal>

		<%-- 必須フィールド定義 --%>

		<%-- 日付型フィールド定義 --%>

		<%-- 時間型フィールド定義 --%>


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
		
		<%-- 名称のクリアauto complete --%>
		Event.observe('srhItemCd', 'keypress', clearText.bindAsEventListener($('srhItemName')), false);
		Event.observe('srhItemCd', 'keypress', clearText.bindAsEventListener($('srhOtherCompanyCd1')), false);
		
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
	  var item = document.tankLockListForm.tankLockCheckBox;
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
          if(item[i].checked){
		    count = count + 1;
		    break;
		  }
	    }
	  }
	  if (count == 0) {
        alert("インターロック解除/再設定対象が選択されていません。");
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
	
	<%-- ラジオボタンを選択した際にチェックボックスを有効化 --%>
	function enableCheckBox(chkbox) {
		var name = chkbox.name;
		var splitName = name.split(/[\[ \]]/);
		$("searchList[" + splitName[1] + "].tankLockCheckBox").disabled = false;
		$("searchList[" + splitName[1] + "].tankLockCheckBox").checked = true;
		
	}
	
	<%-- 同一タンクNoの物に値を値を設定 --%>
	function sameCompoundTankNo(chkbox, flg) {
		var splitName = chkbox.name.split(/[\[ \]]/);
		var changeCompoundTankNo = $("searchList[" + splitName[1] + "].compoundTankNo")
		var changeInterlock = document.getElementsByName("searchList[" + splitName[1] + "].interlock");
		var selectInterlockNo
				
		for(i = 0; i < changeInterlock.length; i++){
			if(changeInterlock[i].checked){
				selectInterlockNo = i;
			}
	    }


		//データ件数分ループ
        for (var i=0; i < $("searchListLength").value; i++) {
        	//選択した物と同じタンクNoを持つものを検索
        	if( $("searchList[" + i + "].compoundTankNo").value == changeCompoundTankNo.value){
        		//同じタンクNoのラジオボタンオブジェクトを取得し、選択する
        		var sameInterlock = document.getElementsByName("searchList[" + i + "].interlock");
        		sameInterlock[selectInterlockNo].checked = true;
        		
        		//チェックボックスを有効にし、チェックを入れる
        		$("searchList[" + i + "].tankLockCheckBox").disabled = false;
				$("searchList[" + i + "].tankLockCheckBox").checked = flg;
        	}
        }
	}
	
	<%-- チェックボックスをクリックした場合、同一タンクNoのもの全てに値を反映する --%>
	function clickCheckBox(chkbox){
		var splitName = chkbox.name.split(/[\[ \]]/);
		var flg = $("searchList[" + splitName[1] + "].tankLockCheckBox").checked;

		sameCompoundTankNo(chkbox, flg);
	
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

<nested:form action="/TankLockList" method="post" onsubmit="return false;" styleId="mainForm">

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
							<td class="fcTitle fb" width="300">調合タンク底弁インターロック解除/再設定</td>
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
										<td class="bcTitleSearch fb fcTitleSearch" width="130">生産工場</td>
										<td width="100">
											<nested:select property="srhProductionLine" style="margin: 0;padding: 0" styleId="srhProductionLine" >
												<nested:optionsCollection property="lineCombo" label="labales" value="values" />
											</nested:select>
										</td>
										<td class="bcTitleSearch fb fcTitleSearch" width="130">タンクNo</td>
										<td width="130">
											<nested:text property="srhChogotankno" maxlength="20" size="20" styleId="srhChogotankno" style="ime-mode:disabled" />
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
										<td class="bcTitleSearch fb fcTitleSearch" width="100">他社コード１</td>
										<td>
											<nested:text property="srhOtherCompanyCd1" maxlength="20" size="20" styleId="srhOtherCompanyCd1" style="ime-mode:disabled" />
										</td>
									</tr>
								</table>
								<table cellspacing="1" cellpadding="1" border="0">
									<tr>
										<td class="bcTitleSearch fb fcTitleSearch" width="130">ステータス</td>
										<td>
											<%
												pageContext.setAttribute("selectTankLockDirectionStatus",
												new com.asahikaseieng.app.comboboxes.SelectTankLockDirectionStatus(true, false));
											%>
											<nested:select property="srhDirectionStatus" styleId="srhDirectionStatus">
												<nested:options name="selectTankLockDirectionStatus" property="values" labelName="selectTankLockDirectionStatus" labelProperty="labels"/>
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
				<%-- 1ページあたりの検索結果件数 --%>
				<nested:hidden property="searchListLength" />

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
								<table cellspacing="2" cellpadding="1" id="tblList">
									<tr class="bcTitleList fb fcTitleList">
										<td width="20" rowspan="2">&nbsp;</td>
										<td width="100">製造指図番号</td>
										<td width="80">製造日</td>
										<td width="80">品目コード</td>
										<td width="500" colspan="4">品目名称</td>
									</tr>
									<tr class="bcTitleList fb fcTitleList">
										<td width="200" colspan="2">生産工場</td>
										<td width="80">タンクNo</td>
										<td width="90">現工程</td>
										<td width="120">解除・再設定</td>
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
												<nested:empty property="interlock">
													<nested:checkbox property="tankLockCheckBox" styleId="tankLockCheckBox" onclick="clickCheckBox(this);" disabled="true"/>	
												</nested:empty>
												<nested:notEmpty property="interlock">
													<nested:checkbox property="tankLockCheckBox" styleId="tankLockCheckBox" onclick="clickCheckBox(this);" disabled="false"/>
												</nested:notEmpty>
											</td>
											<%-- 指図番号 --%>
											<td>
												<nested:write property="directionNo" />
											</td>
											<%-- 開始実績日 --%>
											<td>
												<nested:write property="strResultSdate" />
											</td>
											<%-- 主要製品コード --%>
											<td>
												<nested:write property="itemCd" />
											</td>
											<%-- 品目名称 --%>
											<td colspan="4">
												<nested:write property="itemName" />
											</td>
										</tr>
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
											<tr class="bcList1">
										</app:modulo>

										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
											<tr class="bcList2">
										</app:modulo>
											<%-- 生産工場 --%>
											<td colspan="2">
												<nested:write property="productionLineName" />
											</td>
											<%-- 調合タンクNO --%>
											<td>
												<nested:write property="compoundTankNo" />
												<nested:hidden property="compoundTankNo" />
											</td>
											<%-- 現工程 --%>
											<td>
												<nested:write property="operationName" />
											</td>
											<%-- 解除・再設定 --%>
											<td>
												<label><nested:radio property="interlock" value="2" onclick="sameCompoundTankNo(this, true);"/>解除</label>
												<label><nested:radio property="interlock" value="1" onclick="sameCompoundTankNo(this, true);"/>再設定</label>
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
