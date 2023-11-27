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
		<logic:equal name="stockBookingListForm" property="searchList" value="false">
			initializeFormState();
		</logic:equal>

		<%-- 必須フィールド定義 --%>
		
		<%-- 日付型フィールド定義 --%>
		defineAsDateField("srhResultEdateFrom");
		defineAsDateField("srhResultEdateTo");

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
		
		<%-- 数値型フィールド定義 --%>
		if (tblList != null) {
			if (0 < tblList.rows.length) {
				<%-- 物流入庫実績 --%>
				for (i = 0; $("strSumSuuryou" + i) != null; i++) {
					defineAsNumberField("strSumSuuryou" + i);
				}
			}
		}
		

		<%-- 検索後入力された場合の不整合をただす --%>
		storeInitValues(/^srh.*/, 'dirtyFlg');
		refreshLabel();
		
		errMsgPopup();

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
	  var item = document.stockBookingListForm.stockBookingCheckBox;
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
        alert("登録対象が選択されていません。");
	    return false;
	  }
	  return true;
	}
	
	<%-- 物流-生産(差分)を計算 --%>
	function quantitySubtract(chkbox) {
		var splitName = chkbox.name.split(/[\[ \]]/);
		var prefix = "searchList[" + splitName[1] + "].";
		
		var strSumSuuryou = $(prefix + "strSumSuuryou").value; //物流実績取得
		var strResultQty =$(prefix + "strResultQty").value; //生産実績取得
		var quantityDecimalPoint =$(prefix + "quantityDecimalPoint").value; //小数点桁数取得
		var quantityRound =$(prefix + "quantityRound").value; //端数区分取得
		
		
		if( !blank(strSumSuuryou)  &&  !blank(strResultQty)){ //ブランクではなかったら
			var strSumSuuryou2 = parseFloat(strSumSuuryou.replace(/,/g, "")); //物流実績のカンマを取りフロートへ変換
			var strResultQty2 = parseFloat(strResultQty.replace(/,/g, "")); //生産実績のカンマを取りフロートへ変換
			
			if( !isNaN(strSumSuuryou2) && !isNaN(strResultQty2) ){ //数値であれば
				//物流実績-生産実績の計算
				var strSubtractionQty = strSumSuuryou2 - strResultQty2;
				
				//計算結果の差が0であれば
				if(strSubtractionQty == 0){
					//チェックボックスにチェックを入れる
					$(prefix + "stockBookingCheckBox").checked = true;
				}
				
				//物流実績のフォーマット変換
				strSumSuuryou2 = digitFormat(quantityDecimalPoint,quantityRound,strSumSuuryou2);
				//差のフォーマット変換
				strSubtractionQty = digitFormat(quantityDecimalPoint,quantityRound,strSubtractionQty);
				
				$(prefix + "strSumSuuryou").value = strSumSuuryou2;
				$("strSubtractionQty" + splitName[1]).update(strSubtractionQty);
				$(prefix + "strSubtractionQty").value = strSubtractionQty;
				
			}	
		}
	}

	function errMsgPopup() {
		var errMsg = $("errMsg").value;
		if (errMsg != "") {
			alert(errMsg);
			$("errMsg").value = "";
			form_submit('op', 'reSearch');
		}
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

<nested:form action="/StockBookingList" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="dirtyFlg" />
	<nested:hidden property="errMsg" styleId="errMsg" />

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
							<td class="fcTitle fb" width="250">検査待ち在庫計上</td>
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
								<table width="100%" cellspacing="1" cellpadding="1" border="0">
									<tr>
										<td class="bcTitleSearch fb fcTitleSearch" width="100">包装日付</td>
										<td>
											<nested:text property="srhResultEdateFrom" maxlength="8" size="8" styleId="srhResultEdateFrom" style="ime-mode:disabled" />
											～
											<nested:text property="srhResultEdateTo" maxlength="8" size="8" styleId="srhResultEdateTo" style="ime-mode:disabled" />
										</td>
									</tr>
								</table>
								<table width="80%" cellspacing="1" cellpadding="1" border="0">
									<tr>
										<td class="bcTitleSearch fb fcTitleSearch" width="100">生産工場</td>
										<td>
											<nested:select property="srhProductionLine" style="margin: 0;padding: 0" styleId="srhProductionLine" >
												<nested:optionsCollection property="lineCombo" label="labales" value="values" />
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
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td><%-- 明細部 --%>
						<table width="" border="0">
							<tr>
								<td>
									<table cellspacing="1" cellpadding="1"  border="0" width="100%">
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
								<td class="alignCenter">
								<table cellspacing="2" cellpadding="1" id="tblList">
									<tr class="bcTitleList fb fcTitleList">
											<td width="20" rowspan="2"></td>
											<td width="100">製品コード</td>
											<td width="200"colspan="2">製品名</td>
											<td width="70"colspan="2">包装日</td>
											<td width="150">ロット番号</td>
										</tr>
										<tr class="bcTitleList fb fcTitleList">
											<td width="120">包装指図番号</td>
											<td width="100">製品荷姿</td>
											<td width="100">物流入庫実績</td>
											<td width="100">生産入庫実績</td>
											<td width="40">単位</td>
											<td width="120">物流-生産(差分)</td>
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
											
											<%-- 小数点桁数 --%>
											<nested:hidden property="quantityDecimalPoint" />
											<%-- 端数区分 --%>
											<nested:hidden property="quantityRound" />

											<%-- チェックボックス --%>
											<td rowspan="2">
												<nested:checkbox property="stockBookingCheckBox" styleId="stockBookingCheckBox" onclick="" />
											</td>
											<%-- 主要製品コード --%>
											<td>
												<nested:write property="itemCd" />
											</td>
											<%-- 品目名称 --%>
											<td colspan="2">
												<nested:write property="itemName" />
											</td>
											<%-- 包装実績日(開始) --%>
											<td class="alignRight"colspan="2">
												<nested:write property="strResultEdate" />
											</td>
											<%-- ロットNo --%>
											<td>
												<nested:write property="lotNo" />
											</td>
											
										</tr>
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
											<tr class="bcList1">
										</app:modulo>

										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
											<tr class="bcList2">
										</app:modulo>
											<%-- 指図番号 --%>
											<td>
												<nested:write property="directionNo" />
											</td>
											<%-- 荷姿 --%>
											<td>
												<nested:write property="styleOfPacking" />
											</td>
											<%-- 物流入庫数量 --%>
											<td class="alignRight">
												<nested:text property="strSumSuuryou" maxlength="22" size="12" onblur="quantitySubtract(this)" styleId="<%="strSumSuuryou" + pageContext.findAttribute("index").toString() %>" style="ime-mode:disabled" />
											</td>
											<%-- 実績生産量 --%>
											<td class="alignRight">
												<nested:write property="strResultQty" />
												<nested:hidden property="strResultQty" />
											</td>
											<%-- 単位 --%>
											<td>
												<nested:write property="unitName" />
											</td>
											<%-- 物流-生産(差分) --%>
											<td class="alignRight">
												<div id="<%="strSubtractionQty" + pageContext.findAttribute("index").toString() %>"><nested:write property="strSubtractionQty" /></div>
												<nested:hidden property="strSubtractionQty" />
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
