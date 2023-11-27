<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taginclude.jsf"%>
<nested:root name="materialRinputRecipeHeaderForAutoCompleteForm">
	<nested:notEmpty property="result">
		<ul class="contacts">
			<nested:iterate id="list" property="result" indexId="index">
				<li class="contact">
					<div class="name"><span class="informal"><nested:write property="name" /></span></div>
					<div class="code"><nested:write property="code" /></div>
					<div>
						<nested:hidden property="code" styleClass="code"/>
						<nested:hidden property="recipeId" styleClass="recipeId"/>
						<nested:hidden property="recipeCd" styleClass="recipeCd"/>
						<nested:hidden property="recipeVersion" styleClass="recipeVersion"/>
					</div>
				</li>
			</nested:iterate>
		</ul>
	</nested:notEmpty>
</nested:root>