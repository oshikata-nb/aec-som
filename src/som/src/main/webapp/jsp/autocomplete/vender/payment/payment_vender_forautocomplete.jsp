<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taginclude.jsf"%>
<nested:root name="paymentVenderForAutoCompleteForm">
	<nested:notEmpty property="result">
		<ul class="contacts">
			<nested:iterate id="list" property="result" indexId="index">
				<li class="contact">
					<div class="name"><span class="informal"><nested:write property="name" /></span></div>
					<div class="code"><nested:write property="code" /></div>
					<div><nested:hidden property="code" styleClass="code"/><nested:hidden property="categoryDivision" styleClass="categoryDivision"/><nested:hidden property="paymentMethod" styleClass="paymentMethod"/><nested:hidden property="strPaymentScheduledDate" styleClass="strPaymentScheduledDate"/><nested:hidden property="strCarryoverAmount" styleClass="strCarryoverAmount"/><nested:hidden property="strAccountPayableSum" styleClass="strAccountPayableSum"/><nested:hidden property="strOffsetAmount" styleClass="strOffsetAmount"/><nested:hidden property="strPaymentAmountSum" styleClass="strPaymentAmountSum"/><nested:hidden property="strPurchaseDiscountAmount" styleClass="strPurchaseDiscountAmount"/><nested:hidden property="strCommission" styleClass="strCommission"/><nested:hidden property="strPaymentScheduledAmount" styleClass="strPaymentScheduledAmount"/></div>
				</li>
			</nested:iterate>
		</ul>
	</nested:notEmpty>
</nested:root>