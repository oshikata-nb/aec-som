<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taginclude.jsf"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html:html>
<head>
<%@ include file="/jsp/common/head.jsf"%>

<%-- ここにページ固有のcssを書く
<style type="text/css">
</style>
 --%>
 
<script language="JavaScript" type="text/javascript">

function getShowHeight() {
  if(window.innerHeight)
    return window.innerHeight;
  if(document.body.parentNode.clientHeight)
    return document.body.parentNode.clientHeight;
  if(document.body.clientHeight)
    return document.body.clientHeight;
}

<%-- スクロールバー分のマージン --%>
var MAGIC_NUMBER = 45;

function resizeItems() {
  var showHeight = getShowHeight();
  $('container').style.height = showHeight + "px";
  $('verticalwrapper').style.height = showHeight + "px"; 
  $('navigation').style.height = showHeight + "px"; 
  $('tree_iframe').style.height = (showHeight - MAGIC_NUMBER)+ "px"; 
  
  $('wrapper').style.height = showHeight + "px"; 
  $('content').style.height = showHeight + "px"; 
  $('main_iframe').style.height = (showHeight - MAGIC_NUMBER)+ "px"; 
}

Event.observe(window, 'load', function() {
  resizeItems(); 
}, false);

Event.observe(window, 'resize', function() {
  resizeItems(); 
}, false);

</script>
 
</head>

<body>

<div id="container"><%-- container --%>
	<div id="verticalwrapper"><%-- verticalwrapper --%>
		<div id="wrapper">
			<%-- メイン(※位置がメニューと逆転している) --%>
			<div id="content">
			<iframe src='<%= request.getContextPath() + "/MyPage.do?op=init" %>'
					frameborder="0" height="100%" width="100%" id="main_iframe" scrolling="auto">
				この部分は iframe 対応のブラウザで見てください。
			</iframe>
			</div>
		</div>
		<%-- メニュー(※位置がメインと逆転している) --%>
		<div id="navigation">
			<iframe src='<%= request.getContextPath() + "/Menu.do?op=init" %>'
					frameborder="0" height="100%" width="100%" id="tree_iframe" scrolling="auto">
				この部分は iframe 対応のブラウザで見てください。
			</iframe>
		</div>
	</div><%-- verticalwrapper --%>
	<div id="header"><!-- header -->
		<%-- ヘッダー(※位置が下部に来ている) --%>
		<iframe src='<%= request.getContextPath() + "/Header.do?op=init" %>'
				frameborder="0" height="100%" width="100%" id="header_iframe"
				scrolling="no">
			この部分は iframe 対応のブラウザで見てください。
		</iframe>
	</div><%-- header --%>
</div><%-- container --%>

</body>

</html:html>
