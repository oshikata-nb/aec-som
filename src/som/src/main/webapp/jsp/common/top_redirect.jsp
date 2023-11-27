<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- フレームを利用しているので、全画面で更新をかける為に必要 --%>
<html><head>
<script language="JavaScript" type="text/javascript">
function _submit() {document.forms[0].submit();}
</script></head>
<body onload="_submit();">
<form method="post" action="<%= request.getContextPath() + "/" + request.getParameter("to") %>" target="_top">
</form></body></html>
