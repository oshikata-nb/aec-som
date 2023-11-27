/*
* 支払予定帳票用SQL
*
* entityName=PaymentPlanListForReport
* packageName=paymentplanlistforreport
* methodName=getListForReport
*
*/
SELECT PAY_HED.*
	  ,ORGANIZATION.ORGANIZATION_NAME
	  ,CLS.CATEGORY_NAME
	  ,NVL(PAY_HED.PAYABLE_AMOUNT, 0) - NVL(PAY_HED.BALANCE_FORWARD, 0) PAYABLE_AMOUNT_BALANCE --支払額(支払残高-差引繰越額)
	   
	  ,CASE CLS.BANK_DIVISION
		   WHEN 0 THEN
			NULL
		   WHEN 1 THEN
			BANK.BANK_MASTER_CD
	   END BANK_MASTER_CD
	   
	  ,CASE CLS.BANK_DIVISION
		   WHEN 0 THEN
			NULL
		   WHEN 1 THEN
			BANK.BANK_MASTER_NAME
	   END BANK_MASTER_NAME
	   
	  ,CASE CLS.BANK_DIVISION
		   WHEN 0 THEN
			NULL
		   WHEN 1 THEN
			CASE TO_CHAR(PAY_HED.ACCOUNT_DIVISION)
				WHEN '1' THEN
				 '1:普通'
				WHEN '2' THEN
				 '2:当座'
				WHEN '3' THEN
				 '3:その他'
			END
		   ELSE
			NULL
	   END USE_ACCOUNT_DIVISION
	   
	  ,CASE CLS.BANK_DIVISION
		   WHEN 1 THEN
			PAY_HED.ACCOUNT_NO
		   ELSE
			NULL
	   END USE_ACCOUNT_NO

FROM   (SELECT PAYMENT_HEADER.*
			  ,VENDER.VENDER_DIVISION
			  ,VENDER.VENDER_NAME1        AS VENDER_NAME1
			  ,VENDER.VENDER_SHORTED_NAME AS VENDER_SHORTED_NAME
			  ,VENDER.BANK_CD
			  ,VENDER.ACCOUNT_DIVISION
			  ,VENDER.ACCOUNT_NO
			  ,INPUTOR.TANTO_NM           INPUTOR_NAME
			  ,UPDATOR.TANTO_NM           UPDATOR_NAME
		
		FROM   PAYMENT_HEADER
			  ,VENDER
			  ,LOGIN          INPUTOR
			  ,LOGIN          UPDATOR
		
		WHERE  VENDER.VENDER_DIVISION(+) = 'SI'
		AND    PAYMENT_HEADER.SUPPLIER_CD = VENDER.VENDER_CD(+)
		AND    PAYMENT_HEADER.INPUTOR_CD = INPUTOR.TANTO_CD(+)
		AND    PAYMENT_HEADER.UPDATOR_CD = UPDATOR.TANTO_CD(+)) PAY_HED
	   
	  ,ORGANIZATION
	  ,BANK
	  ,CLASSIFICATION CLS
	  ,LOGIN

WHERE  PAY_HED.ORGANIZATION_CD IS NOT NULL
AND    PAY_HED.PAYABLE_AMOUNT <> 0
	  
	  /*IF (condition.srhOrganizationCd != null && condition.srhOrganizationCd != "")*/
AND    (PAY_HED.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/'%'
	  OR ORGANIZATION.ORGANIZATION_NAME LIKE /*condition.srhOrganizationCd*/'%')
	  /*END*/
	  
	  /*IF (condition.srhTantoCd != null && condition.srhTantoCd != "")*/
AND    (PAY_HED.INPUTOR_CD LIKE /*condition.srhTantoCd*/'%'
	  OR LOGIN.TANTO_NM LIKE /*condition.srhTantoCd*/'%')
	  /*END*/
	  
	  /*IF (condition.srhVenderCd != null && condition.srhVenderCd != "")*/
AND    (PAY_HED.SUPPLIER_CD LIKE /*condition.srhVenderCd*/'%'
	  OR PAY_HED.VENDER_NAME1 LIKE FUN_RET_MASTER_STRING_USE_AC(/*condition.srhVenderCd*/'%'))
	  /*END*/
	  
	  /*IF (condition.srhPaymentDateFrom != null && condition.srhPaymentDateFrom != "")*/
AND    PAY_HED.CREDIT_SCHEDULED_DATE >= /*condition.srhPaymentDateFrom*/'2009/07/01'
	  /*END*/
	  
	  /*IF (condition.srhPaymentDateTo != null && condition.srhPaymentDateTo != "")*/
AND    PAY_HED.CREDIT_SCHEDULED_DATE <= /*condition.srhPaymentDateTo*/'2009/07/31'
	  /*END*/
	  
	  /*IF (condition.srhBankCd != null && condition.srhBankCd != "")*/
AND    (BANK.BANK_MASTER_CD LIKE /*condition.srhBankCd*/'%'
	  OR BANK.BANK_MASTER_NAME LIKE /*condition.srhBankCd*/'%')
	  /*END*/
	  
	  /*IF (condition.srhPaymentDivision != null && condition.srhPaymentDivision != "")*/
AND    PAY_HED.CREDIT_DIVISION = /*condition.srhPaymentDivision*/'1'
	  /*END*/
	  
AND    PAY_HED.ORGANIZATION_CD = ORGANIZATION.ORGANIZATION_CD(+)
AND    PAY_HED.BANK_CD = BANK.BANK_MASTER_CD(+)
AND    CLS.DATA_TYPE(+) = 4
AND    PAY_HED.CREDIT_DIVISION = CLS.CATEGORY_DIVISION(+)
AND    PAY_HED.INPUTOR_CD = LOGIN.TANTO_CD(+)

--支払予定日別
/*IF (condition.srhOutputDivision == "1")*/
ORDER  BY PAY_HED.CREDIT_SCHEDULED_DATE
		 ,PAY_HED.SUPPLIER_CD
/*END*/

--支払先別
/*IF (condition.srhOutputDivision == "2")*/
ORDER  BY PAY_HED.SUPPLIER_CD
		 ,PAY_HED.CREDIT_SCHEDULED_DATE
/*END*/

--支払方法別
/*IF (condition.srhOutputDivision == "3")*/
ORDER  BY PAY_HED.CREDIT_DIVISION
		 ,PAY_HED.CREDIT_SCHEDULED_DATE
		 ,PAY_HED.SUPPLIER_CD
/*END*/

--銀行別
/*IF (condition.srhOutputDivision == "4")*/
ORDER  BY PAY_HED.BANK_CD
		 ,PAY_HED.CREDIT_SCHEDULED_DATE
		 ,PAY_HED.SUPPLIER_CD
/*END*/
