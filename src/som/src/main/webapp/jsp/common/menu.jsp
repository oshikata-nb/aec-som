<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taginclude.jsf"%>
<html:html>
<head>
<%@ include file="/jsp/common/head.jsf"%>
<%@ include file="/jsp/common/head_main.jsf"%>
<link href='<%= request.getContextPath() + "/stylesheets/tree.css"%>'
	media="screen" rel="Stylesheet" type="text/css" />
<script 
    src='<%= request.getContextPath() + "/javascripts/Tree-optimized.js"%>'
    type="text/javascript"></script>

<%-- ここにページ固有のcssを書く --%>
<style type="text/css">
</style>
 
<%-- ここにページ固有のjavascriptを書く --%>
<script language="JavaScript" type="text/javascript">

	function enterMenu(e, url) {
		var str = "&";
		if (url.indexOf("?") < 0) {
			str = "?";
		}
  		parent.frames[0].location.href = url + str + "menuId=" + e.getId();
	}
	
	function printContents() {
		parent.frames[0].focus();
		parent.print();
	}

	<bean:size id="listSize" name="menuForm" property="menus"/>
	<logic:notEqual name="listSize" value="0">

		var g_tree = null;
		function TafelTreeInit() {
				<bean:write name="menuForm" property="js" filter="false"/>
			g_tree = new TafelTree('treeSpace', struct, {
				'generate' : true,
				'imgBase' : 'images/',
				'defaultImgOpen' : 'folderopen.gif',
				'defaultImgClose' : 'folder.gif',
				'onDrop' : function(){return true;},
				'copyDrag' : false,
				'behaviourDrop' : 'child',
				'cookies' : false,
				'onMouseOver': function(menu) { menu.addClass('mouseOver');    },
				'onMouseOut':  function(menu) { menu.removeClass('mouseOver'); }
			});
				
		    Event.observe('openTreeAll',  'click', function() { this.expend(); return false; }.bind(g_tree), false);
		    Event.observe('closeTreeAll', 'click', function() { this.collapse(); return false; }.bind(g_tree), false);
		}
	</logic:notEqual>

</script>
</head>
<body>
<nested:form action="/Menu" method="post" enctype="multipart/form-data">
	<nested:hidden property="op"/>
	<table cellpadding="0" cellspacing="0" width="100%" height="100%" border="0">
		<tr>
			<td height="100%" class="valignTop" nowrap>
				<logic:notEqual name="listSize" value="0">
					<a id="openTreeAll" href="#">open all</a> | <a id="closeTreeAll" href="#">close all</a>
					<div id="treeSpace" style="{height: 100%; width: 100%;}"></div>
				</logic:notEqual>
				<logic:equal name="listSize" value="0">
					<br>
					<div class="errorMenu" id="errorMenu">
						<bean:message key="errors.menu"/>
					</div>
				</logic:equal>
			</td>
		</tr>
	</table>
</nested:form>
</body>
</html:html>
