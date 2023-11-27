<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taginclude.jsf"%>
<nested:root name="mgrecipeRecipeHeaderForAutoCompleteForm">
	<nested:notEmpty property="result">
		<ul class="contacts">
			<nested:iterate id="list" property="result" indexId="index">
				<li class="contact">
					<div class="recipeCd"><span class="informal"><nested:write property="recipeCd"/></span></div>
					<div>
						<nested:hidden property="code" styleClass="code"/>
						<nested:hidden property="recipeCd" styleClass="recipeCd"/>
					</div>
				</li>
			</nested:iterate>
		</ul>
	</nested:notEmpty>
</nested:root>