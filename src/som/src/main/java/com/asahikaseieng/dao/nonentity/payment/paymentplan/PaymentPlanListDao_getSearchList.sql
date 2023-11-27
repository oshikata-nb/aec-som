/*
* 支払予定一覧用SQL
*
* entityName=PaymentPlan
* packageName=paymentplan
* methodName=getSearchList
*
*/
SELECT PYM_HED.PAYMENT_NO --支払番号
	  ,PYM_HED.ORGANIZATION_CD --部署コード
	  ,ORGANIZATION.ORGANIZATION_NAME --部署名称
	  ,PYM_HED.SUPPLIER_CD --支払先コード
	  ,PYM_HED.VENDER_NAME1 --支払先名称
	  ,PYM_HED.VENDER_SHORTED_NAME --支払先略称
	  ,TO_CHAR(PYM_HED.CREDIT_SCHEDULED_DATE, 'YYYY/MM/DD') AS CREDIT_SCHEDULED_DATE --支払予定日
	  ,PYM_HED.CREDIT_DIVISION --支払分類
	  ,CLS.CATEGORY_NAME --分類名称
	  ,PYM_HED.NOTE_SIGHT --手形サイト
	  ,PYM_HED.PAYABLE_AMOUNT  AS PAYABLE_AMOUNT_BALANCE --支払額(支払残高)

	  ,(CASE CLS.BANK_DIVISION
		   WHEN 0 THEN
			NULL
		   WHEN 1 THEN
			BANK.BANK_CD || BANK.BRANCH_CD
	   END) AS BANK_CD --銀行＋支店コード

	  ,(CASE CLS.BANK_DIVISION
		   WHEN 0 THEN
			NULL
		   WHEN 1 THEN
			BANK.BANK_NAME || '　' || BANK.BRANCH_NAME
	   END) AS BANK_NAME --銀行＋支店名称

	  ,(CASE CLS.BANK_DIVISION
		   WHEN 0 THEN
			''
		   WHEN 1 THEN
			(CASE TO_CHAR(PYM_HED.ACCOUNT_DIVISION)
				WHEN '1' THEN
				 '1:普通'
				WHEN '2' THEN
				 '2:当座'
				WHEN '3' THEN
				 '3:その他'
			END)
		   ELSE
			''
	   END) AS ACCOUNT_DIVISION --口座区分

	  ,(CASE CLS.BANK_DIVISION
		   WHEN 1 THEN
			PYM_HED.ACCOUNT_NO
	   END) AS ACCOUNT_NO --口座番号
FROM   (SELECT PAYMENT_HEADER.PAYMENT_NO --支払番号
			  ,PAYMENT_HEADER.ORGANIZATION_CD --部署コード
			  ,PAYMENT_HEADER.SUPPLIER_CD --支払先コード
			  ,PAYMENT_HEADER.CREDIT_SCHEDULED_DATE --支払予定日
			  ,PAYMENT_HEADER.CREDIT_DIVISION --支払分類
			  ,PAYMENT_HEADER.NOTE_SIGHT --手形サイト
			  ,PAYMENT_HEADER.PAYABLE_AMOUNT --支払残高
			  ,PAYMENT_HEADER.BALANCE_FORWARD --差引繰越額
			  ,PAYMENT_HEADER.INPUTOR_CD --登録者ID
			  ,VENDER.VENDER_DIVISION
			  ,VENDER.VENDER_NAME1                  AS VENDER_NAME1
			  ,VENDER.VENDER_SHORTED_NAME           AS VENDER_SHORTED_NAME
			  ,VENDER.BANK_CD
			  ,VENDER.ACCOUNT_DIVISION
			  ,VENDER.ACCOUNT_NO
		FROM   PAYMENT_HEADER PAYMENT_HEADER
		LEFT   JOIN VENDER VENDER
		ON     VENDER.VENDER_DIVISION = 'SI'
		AND    PAYMENT_HEADER.SUPPLIER_CD = VENDER.VENDER_CD) PYM_HED
LEFT   JOIN ORGANIZATION ORGANIZATION
ON     PYM_HED.ORGANIZATION_CD = ORGANIZATION.ORGANIZATION_CD
LEFT   JOIN LOGIN LOGIN
ON     PYM_HED.INPUTOR_CD = LOGIN.TANTO_CD
LEFT   JOIN BANK BANK
ON     PYM_HED.BANK_CD = (BANK.BANK_CD || BANK.BRANCH_CD)
LEFT   JOIN CLASSIFICATION CLS
ON     PYM_HED.CREDIT_DIVISION = CLS.CATEGORY_DIVISION
AND    CLS.DATA_TYPE = '4'
WHERE  PYM_HED.ORGANIZATION_CD IS NOT NULL
AND    PYM_HED.PAYABLE_AMOUNT <> 0
	  
	  /*IF (( condition.srhOrganizationCd != null ) && ( condition.srhOrganizationCd != "" ))*/
AND    (PYM_HED.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/'%'
	  OR ORGANIZATION.ORGANIZATION_NAME LIKE /*condition.srhOrganizationCd*/'%')
	  /*END*/
	  
	  /*IF (( condition.srhTantoCd != null ) && ( condition.srhTantoCd != "" ))*/
AND    (PYM_HED.INPUTOR_CD LIKE /*condition.srhTantoCd*/'%'
	  OR LOGIN.TANTO_NM LIKE /*condition.srhTantoCd*/'%')
	  /*END*/
	  
	  /*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
AND    (PYM_HED.SUPPLIER_CD LIKE /*condition.srhVenderCd*/'%'
	  OR PYM_HED.VENDER_NAME1 LIKE FUN_RET_MASTER_STRING_USE_AC(/*condition.srhVenderCd*/'%'))
	  /*END*/
	  
	  /*IF (( condition.srhPaymentDateFrom != null ) && ( condition.srhPaymentDateFrom != "" ))*/
AND    PYM_HED.CREDIT_SCHEDULED_DATE >= /*condition.srhPaymentDateFrom*/'2009/07/01'
	  /*END*/
	  
	  /*IF (( condition.srhPaymentDateTo != null ) && ( condition.srhPaymentDateTo != "" ))*/
AND    PYM_HED.CREDIT_SCHEDULED_DATE <= /*condition.srhPaymentDateTo*/'2009/07/31'
	  /*END*/
	  
	  /*IF (( condition.srhBankCd != null ) && ( condition.srhBankCd != "" ))*/
AND    (BANK.BANK_MASTER_CD LIKE /*condition.srhBankCd*/'%'
	  OR BANK.BANK_MASTER_NAME LIKE /*condition.srhBankCd*/'%')
	  /*END*/
	  
	  /*IF (( condition.srhPaymentDivision != null ) && ( condition.srhPaymentDivision != "" ))*/
AND    PYM_HED.CREDIT_DIVISION = /*condition.srhPaymentDivision*/'1'
/*END*/

/*IF ( condition.srhOutputDivision == "1" )*/
ORDER  BY PYM_HED.CREDIT_SCHEDULED_DATE --支払予定日別
		 ,PYM_HED.SUPPLIER_CD
/*END*/

/*IF ( condition.srhOutputDivision == "2" )*/
ORDER  BY PYM_HED.SUPPLIER_CD --支払先別
		 ,PYM_HED.CREDIT_SCHEDULED_DATE
/*END*/

/*IF ( condition.srhOutputDivision == "3" )*/
ORDER  BY PYM_HED.CREDIT_DIVISION --支払方法別
		 ,PYM_HED.CREDIT_SCHEDULED_DATE
		 ,PYM_HED.SUPPLIER_CD
/*END*/

/*IF ( condition.srhOutputDivision == "4" )*/
ORDER  BY BANK_CD --銀行別
		 ,PYM_HED.CREDIT_SCHEDULED_DATE
		 ,PYM_HED.SUPPLIER_CD
/*END*/
