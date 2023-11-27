<%@ page language="java" contentType="text/xml; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taginclude.jsf"%>
<nested:root name="paymentCsmDetailForm">
	<nested:notEmpty property="result">
		<ul class="contacts">
			<nested:iterate id="list" property="result" indexId="index">
				<li>
					<creditTitleCd><nested:write property="creditTitleCd" /></creditTitleCd>
					<noteDiv><nested:write property="noteDiv" /></noteDiv>
					<bankCd><nested:write property="bankCd" /></bankCd>
					<accountDivision><nested:write property="accountDivision" /></accountDivision>
					<accountNo><nested:write property="accountNo" /></accountNo>
					<bankDivision><nested:write property="bankDivision" /></bankDivision>
					<feeFlag><nested:write property="feeFlag" /></feeFlag>
					<drawalDateString><nested:write property="drawalDateString" /></drawalDateString>
					<noteDateString><nested:write property="noteDateString" /></noteDateString>
				</li>
			</nested:iterate>
		</ul>
	</nested:notEmpty>
</nested:root>
