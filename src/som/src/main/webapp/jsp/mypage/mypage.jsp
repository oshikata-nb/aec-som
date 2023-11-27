<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="org.apache.struts.Globals"%>
<%@ include file="/jsp/common/taginclude.jsf" %>

<html:html>
<head>
<%@ include file="/jsp/common/head.jsf"%>
<%@ include file="/jsp/common/head_main.jsf"%>

<!-- Style Sheet(mypage用) -->
<link href='<%= request.getContextPath() + "/stylesheets/mypage.css"%>' media="screen" rel="Stylesheet" type="text/css" />
<!-- Javascript(mypage用) -->
<script src='<%= request.getContextPath() + "/javascripts/minmax.js"%>' type="text/javascript"></script>

<%-- ▼業務固有css ここから
<style type="text/css">
</style>
<%-- ▲業務固有css ここまで --%>

<%-- ▼業務固有javaScript ここから --%>
<script language="JavaScript" type="text/javascript">
	<%-- Load時の処理 --%>
	Event.observe(window, 'load', function() {
		<%-- 左側 --%>
		Sortable.create("lane_001",
			{
				containment: ["lane_001","lane_002"],
				handle:	  'msg_handle',
				dropOnEmpty: true,
				tag:		 'div',
				constraint:  false,
				onUpdate:	function(lane) { regist(); }
			}
		);

		<%-- 右側 --%>
		Sortable.create("lane_002",
			{
				containment: ["lane_001","lane_002"],
				handle:	  'msg_handle',
				dropOnEmpty: true,
				tag:		 'div',
				constraint:  false,
				onUpdate:	function(lane) { regist(); }
			}
		);

		leftNum = getGadgetNum($("lane_001"));
		rightNum = getGadgetNum($("lane_002"));
  
		<%-- ajax --%>
		<%-- gadgetの内容を取得 --%>
		var url = null;
		var pars = null;
		<logic:iterate id="gadgetList" name="myPageForm" property="gadgetList" indexId="index">
			<nested:define id="identifier" name="gadgetList" property="identifier"/>
			<nested:define id="actionUrl" name="gadgetList" property="gadget.actionUrl"/>
			
			url = '<%= request.getContextPath() + "/" + actionUrl %>';
				pars = 'tantoCd=<nested:write name="gadgetList" property="tantoCd"/>&gadgetId=<nested:write name="gadgetList" property="gadgetId"/>';
				new Ajax.Updater(
				'<%= "msg_" + identifier + "_body"%>',
				url,
				{
					method: 'post',
					parameters: pars,
					evalScripts: true
				}
			);

			<%-- 最小化するガジェット --%>
			var slide = null;
			slide = '<nested:write name="gadgetList" property="slideStatus"/>'
			forceDispFlg = '<nested:write name="gadgetList" property="gadget.forceDispFlg"/>'
			dataCnt = '<nested:write name="gadgetList" property="dataCnt"/>'
			
			<%-- (表示状態[最小化]かつ強制表示[なし])　または　(強制表示[あり]かつデータ件数[ゼロ])の場合 --%>
			if((slide == '1' && forceDispFlg == '0') || (forceDispFlg == '1' && dataCnt == 0)){
				minimize('msg_<nested:write name="gadgetList" property="tantoCd"/>_<nested:write name="gadgetList" property="gadgetId"/>', '<nested:write name="gadgetList" property="laneNo"/>', '<nested:write name="gadgetList" property="verticalOrder"/>');
			}
		</logic:iterate>
	}, false);

	function enterMenu(e, url) {
		parent.frames[0].location.href = url;
	}
	
	function minimize(msgId, laneNo, index) {
		var body = $(msgId + "_body");
		new Effect.SlideUp(body, { 'duration': 0.3});
		var zippy = $(msgId + "_zippy");

		if (laneNo == "0") {
			if ($("firstLaneList[" + index + "].slideStatus") != null) {
				$("firstLaneList[" + index + "].slideStatus").value = "1";
			}
		}else{
			if ($("secondLaneList[" + index + "].slideStatus") != null) {
				$("secondLaneList[" + index + "].slideStatus").value = "1";
			}
		}

		zippy.style.backgroundImage = "url('images/plus-color.png')";
		zippy.onclick = function() {maximize(msgId, laneNo, index); regist();return false;};

		return false;
	}

	function maximize(msgId, laneNo, index) {
		var body = $(msgId + "_body");
		new Effect.SlideDown(body, { 'duration': 0.3});

		if (laneNo == "0") {
			if ($("firstLaneList[" + index + "].slideStatus") != null) {
				$("firstLaneList[" + index + "].slideStatus").value = "0";
			}
		}else{
			if ($("secondLaneList[" + index + "].slideStatus") != null) {
				$("secondLaneList[" + index + "].slideStatus").value = "0";
			}
		}

		var zippy = $(msgId + "_zippy");
		zippy.style.backgroundImage = "url('images/minus-color.png')";
		zippy.onclick = function() {minimize(msgId, laneNo, index); regist(); return false;};

		return false;
	}

	var leftNum = 0;
	var rightNum = 0;
	var registCounter = 0;

	<%-- 更新処理の実装 --%>
	function regist_impl() {
		<%-- ajaxで更新処理 --%>
		var leftIds = getIds($("lane_001"));
		var rightIds = getIds($("lane_002"));
		var leftMenuIds = getMenuIds($("lane_001"));
		var rightMenuIds = getMenuIds($("lane_002"));

		url = '<%= request.getContextPath() + "/MyPage.do?op=regist" %>';
		pars = "rightIds=" + rightIds + "&leftIds=" + leftIds + "&leftStatus=" + leftMenuIds + "&rightStatus=" + rightMenuIds;
		new Ajax.Updater(
			'dummy',
			url, 
			{
				method: 'post', 
				parameters: pars,
				evalScripts: true
			}
		);
	}

	<%-- 更新処理 --%>
	function regist() {
		var nowLNum = getGadgetNum($("lane_001"));
		var nowRNum = getGadgetNum($("lane_002"));
		
		if (leftNum == nowLNum && rightNum == nowRNum) {
			<%-- 同一レーン内での移動 --%>
			regist_impl();
		}else{
			<%-- 同一レーン内での移動
				２つのレーンでガジェットが移動した場合
				２度onUpdate()が呼ばれるため、待ち合わせをして
				サーバーへのリクエストを１度にしている --%>
			if (registCounter == 0) {
				registCounter = 1;
			}else if (registCounter == 1) {
				regist_impl();
				registCounter = 0;
				leftNum = nowLNum;
				rightNum = nowRNum;
			}else{
				alert("Can't reach here...");
			}
		}
	}

	<%-- ガジェットの数を取得 --%>
	function getGadgetNum(lane) {
		var i = 0;
		lane.getElementsByClassName('msg').each(function(e) {
			i = i + 1;
			}
		);

		return i;
	}

	<%-- ガジェットのIDを取得 --%>
	function getIds(lane) {
		var ids = "";
		lane.getElementsByClassName('msg').each(function(e) {
			ids = ids + e.id + ",";
			}
		);

		return ids;
	}

	<%-- ガジェットのメニューID、タブID、表示状態を取得 --%>
	function getMenuIds(lane) {
		var ids = "";
		var num = getGadgetNum($(lane));
		
		for (i = 0; i < num; i++) {
			ids = ids + lane.getElementsByClassName('ids')[i].children[0].value + '_'
				+ lane.getElementsByClassName('ids')[i].children[1].value + '_'
				+ lane.getElementsByClassName('ids')[i].children[2].value + '_' + ',';
		}

		return ids;
	}
</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/MyPage" method="post" onsubmit="return false;">
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
									<td class="fcTitle fb">マイページ</td>
									<td class="fcTitle fb alignRight">Ver.1.6.1</td>
									<td>
										<table border="0" cellpadding="0" cellspacing="0">
											<tr>
												<td>
													<%-- メッセージ表示 --%>
													<div id="information" class="cssInformation guide_message"></div>
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
				</table>
			</td>
		</tr>
	</table>
	
	<div id="contents">
		<div id="canvas">
			<div class="wrapper">
				<%-- 左側 --%>
				<div id="lane_001" class="lane first">
					<bean:define id="laneNo" value="0" />
					<nested:iterate id="firstLaneList" property="firstLaneList" indexId="index">
						<nested:define id="identifier" name="firstLaneList" property="identifier"/>
						<nested:define id="titleUrl"   name="firstLaneList" property="gadget.titleUrl"/>
						<nested:define id="dataCnt" name="firstLaneList" property="dataCnt"/>
						<%@ include file="/jsp/mypage/gadget.jsf"%>
					</nested:iterate>
		   		</div>

				<%-- 右側 --%>
				<div id="lane_002" class="lane">
					<bean:define id="laneNo" value="1" />
					<nested:iterate id="secondLaneList" property="secondLaneList" indexId="index">
						<nested:define id="identifier" name="secondLaneList" property="identifier"/>
						<nested:define id="titleUrl"   name="secondLaneList" property="gadget.titleUrl"/>
						<nested:define id="dataCnt" name="secondLaneList" property="dataCnt"/>
						<%@ include file="/jsp/mypage/gadget.jsf"%>
					</nested:iterate>
				</div>
			</div><%-- wrapper --%>
		</div><%-- canvas --%>
	</div><%-- content --%>
</nested:form>

<div id="dummy"></div>

</body>
</html:html>
