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
		<logic:equal name="carryShippingListForm" property="searchList" value="false">
			initializeFormState();
		</logic:equal>

		<%-- 必須フィールド定義 --%>
		defineAsRequiredField("srhScheduledShippingDate");

		<%-- 日付型フィールド定義 --%>
		defineAsDateField("srhScheduledShippingDate");
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
		
	}, false);

	<%-- 確認メッセージ --%>
	function putConfirm() {
		if (!noCheck(1)) {
		  return false;
		}

		alertMsg = new Array();
		alertMsg[0] = "指図送信してもよろしいですか？";

		return confirm(alertMsg[0]);
	}

	<%-- チェックボックス選択無しチェック --%>
	function noCheck(index){
	  var count = 0;
	  var item = document.carryShippingListForm.carryShippingCheckBox;
	  var len = item.length;

	  <%-- 明細１件のみの場合 --%>
	  if (len == undefined) {
	    if(!item.disabled){
	      if(item.checked){
		    count = count + 1;
          }
		}
	  } else {
	    for (var i = 0; i < len; i++) {
	      if(!item[i].disabled){
            if(item[i].checked){
		      count = count + 1;
		      break;
		    }
		  }
	    }
	  }
	  if (count == 0) {
        alert("指図送信対象が選択されていません。");
	    return false;
	  }
	  return true;
	}

	<%-- チェックボックス　全設定 --%>
	function allCheck(flg) {
		//リスト件数分ループ
        for (var i=0; i < $("carryShippingListLength").value; i++) {
        	//チェックボックスが非活性ではないもののみ設定
        	if (! $("searchList[" + i + "].carryShippingCheckBox").disabled){
        		$("searchList[" + i + "].carryShippingCheckBox").checked = flg;
        	}
        }
	}
	<%-- 確認メッセージ --%>
	function reportConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "帳票(EXCEL)出力してもよろしいですか？";

		return confirm(alertMsg[0]);
	}

	<%-- 確認メッセージ --%>
	function carryReportConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "運送店別出荷一覧表を出力してもよろしいですか？";

		return confirm(alertMsg[0]);
	}

</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/CarryShippingList" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="dirtyFlg" />

	<table cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td class="valignTop alignLeft">
			<table border="0" cellpadding="0" cellspacing="0" width="750px">
				<tr>
					<td><%-- ヘッダー部 --%>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td height="10" colspan="2"></td>
						</tr>
						<tr>
							<td class="fcTitle fb" width="250">運送店別出荷指図</td>
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
									<td class="bcTitleSearch fb fcTitleSearch" width="130">出荷予定日</td>
									<td width="130">
										<%-- <nested:text property="srhShippingInstructDate" maxlength="8" size="8" styleId="srhShippingInstructDate" style="ime-mode:disabled" /> --%>
										<nested:text property="srhScheduledShippingDate" maxlength="8" size="8" styleId="srhScheduledShippingDate" style="ime-mode:disabled" />
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
										<div id="cssButton">
											<a href="#" onclick="if (!(carryReportConfirm())) {return false;}else{return form_submit('op', 'carryReport'); return false;}" class="cssButton">
												運送店別出荷一覧表 
											</a>
										</div>
									</td>
									<td>
										<div id="cssButton"><a href="#"
											onclick="return form_submit('op', 'orderMake'); return false;"
											class="cssButton"> &nbsp;&nbsp;指&nbsp;図&nbsp;作&nbsp;成&nbsp;&nbsp;
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
				<nested:hidden property="carryShippingListLength" />
				<table cellspacing="1" cellpadding="1" bdelivery="0">
					<tr class="alignLeft">
						<td>
							<div id="cssButton"><a href="#" onclick="allCheck(true); return false;" class="cssButton"> &nbsp;&nbsp;全チェック&nbsp;&nbsp;
								</a>
							</div>
						</td>
						<td>
							<div id="cssButton"><a href="#" onclick="allCheck(false); return false;" class="cssButton"> &nbsp;&nbsp;全チェック解除&nbsp;&nbsp;
								</a>
							</div>
						</td>
						<td>
							<div id="cssButton">
								<a href="#" onclick="if (!(reportConfirm())) {return false;}else{return form_submit('op', 'report'); return false;}" class="cssButton">
									帳票(EXCEL) 
								</a>
							</div>
						</td>
						<td>
							<div id="cssButton">
								<a href="#" onclick="if (!(putConfirm())) {return false;}else{return form_submit('op', 'directionSubmit'); return false;}" class="cssButton">
								&nbsp;指&nbsp;図&nbsp;送&nbsp;信&nbsp;
								</a>
							</div>
						</td>
					</tr>
				</table>
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td><%-- 明細部 --%>
						<table width="" border="0">
							<tr>
								<td class="alignCenter">
								<table cellspacing="2" cellpadding="1" id="tblList">
									<tr class="bcTitleList fb fcTitleList">
										<td width=>発行</td>
										<td>積出ナンバー</td>
										<td width=300>運送店名・工場名</td>
										<td>発行済</td>
									</tr>

									<nested:iterate id="searchList" property="searchList"
										indexId="index">
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
											<tr class="bcList1">
										</app:modulo>

										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
											<tr class="bcList2">
										</app:modulo>
											<%--チェックボックス --%>
											<td>
												<%-- 未発行 --%>
												<nested:equal property="shippingOrderSendCompFlag" value="0">
													<nested:checkbox property="carryShippingCheckBox" styleId="carryShippingCheckBox" />
												</nested:equal>
												<%-- 発行済み --%>
												<nested:equal property="shippingOrderSendCompFlag" value="1">
													<nested:checkbox property="carryShippingCheckBox" styleId="carryShippingCheckBox" disabled="true"/>
												</nested:equal>
											</td>
											<%-- 析出ナンバー --%>
											<td>
												<nested:write property="sendingOffNumber" />
											</td>
											<%-- 運送店名・工場名 --%>
											<td>
												<nested:write property="carryName" />
											</td>
											<%-- 発行済 --%>
											<td>
												<nested:write property="strShippingOrderSendCompFlag" />
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
		<nested:equal property="excelDownloadFlg" value="true">
			<META HTTP-EQUIV="REFRESH" CONTENT="0; URL=<%= request.getContextPath() %>/FileDownload/">
		</nested:equal>
		<%-- Excelダウンロード処理 ここまで --%>
		<%-- Excelダウンロード処理 --%>
		<nested:equal property="carryExcelDownloadFlg" value="true">
			<META HTTP-EQUIV="REFRESH" CONTENT="0; URL=<%= request.getContextPath() %>/FileDownload/">
		</nested:equal>
		<%-- Excelダウンロード処理 ここまで --%>
	</table>
</nested:form>
</body>
</html:html>
