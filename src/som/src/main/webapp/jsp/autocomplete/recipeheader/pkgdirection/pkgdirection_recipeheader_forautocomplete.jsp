<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taginclude.jsf"%>
<nested:root name="pkgDirectionRecipeHeaderForAutoCompleteForm">
	<nested:notEmpty property="result">
		<ul class="contacts">
			<nested:iterate id="list" property="result" indexId="index">
				<li class="contact">
					<div class="name"><span class="informal"><nested:write property="name" /></span></div>
					<div class="code"><nested:write property="code" /></div>
					<div>
						<nested:hidden property="code" styleClass="code"/>
						<nested:hidden property="itemCd" styleClass="itemCd"/>
						<nested:hidden property="itemName" styleClass="itemName"/>
						<nested:hidden property="otherCompanyCd1" styleClass="otherCompanyCd1"/>
						<nested:hidden property="styleOfPacking" styleClass="styleOfPacking"/>
						<nested:hidden property="recipeId" styleClass="recipeId"/>
						<nested:hidden property="name01" styleClass="name01"/>
					</div>
				</li>
			</nested:iterate>
		</ul>
	</nested:notEmpty>
</nested:root>