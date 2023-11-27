<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taginclude.jsf"%>
<nested:root name="generalParameterForm">
	<nested:notEmpty property="result">
		<ul class="contacts">
			<nested:iterate id="list" property="result" indexId="index">
				<li class="contact">
					<div class="name"><span class="informal"><nested:write property="name" /></span></div>
					<div class="code"><nested:write property="code" /></div>
					<div><nested:hidden property="otherCompanyCd1" styleClass="otherCompanyCd1"/><nested:hidden property="styleOfPacking" styleClass="styleOfPacking"/><nested:hidden property="unitOfOperationManagement" styleClass="unitOfOperationManagement"/><nested:hidden property="name01" styleClass="name01"/><nested:hidden property="lotDivision" styleClass="lotDivision"/></div>
				</li>
			</nested:iterate>
		</ul>
	</nested:notEmpty>
</nested:root>