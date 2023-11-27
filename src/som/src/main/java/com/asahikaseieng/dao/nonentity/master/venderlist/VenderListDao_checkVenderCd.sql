/*
 * 取引先チェック用SQL
 *
 * entityName=VenderList
 * packageName=master.venderList
 * methodName=checkVenderCd
 *
 */
SELECT 	VENDER_CD
,	    VENDER_NAME1
,	    VENDER_SHORTED_NAME
,		SECTION_CD
,		ACCOUNTS_CD
,		ADVANCE_DIVISION

FROM 	VENDER
WHERE   VENDER_CD IS NOT NULL
AND		((PAYMENT_INVOICE_CD IS NULL)
        OR
         (PAYMENT_INVOICE_CD = VENDER_CD))

/*IF (( venderDivision != null ) && ( venderDivision != "" ))*/
AND     VENDER_DIVISION = /*venderDivision*/'%'
/*END*/

/*IF (( venderCd != null ) && ( venderCd != "" ))*/
AND     VENDER_CD = /*venderCd*/'%'
/*END*/

ORDER BY VENDER_CD
