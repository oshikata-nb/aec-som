/*
 * 
 *
 * entityName=RepApledgerHeader
 * packageName=debt.apledgerlistforreport
 * methodName=getListForReport
 *
 */
SELECT DISTINCT
	ORGANIZATION_CD
,	ORGANIZATION_NAME

FROM
	(
/*IF ( targetKbn == "0" || targetKbn == "2" ) */

	SELECT
		PYH_HED.ORGANIZATION_CD
	,	ORG.ORGANIZATION_NAME
	FROM
		PAYABLE_HEADER PYH_HED
	,	ORGANIZATION ORG
	WHERE
		PYH_HED.ORGANIZATION_CD = ORG.ORGANIZATION_CD
	AND	PYH_HED.PAYABLE_NO IN 
	(
		SELECT PYH_HED.PAYABLE_NO                --買掛番号
		
		FROM   PAYABLE_HEADER PYH_HED
			   LEFT JOIN ORGANIZATION ORGANIZATION
			   ON PYH_HED.ORGANIZATION_CD = ORGANIZATION.ORGANIZATION_CD
			   LEFT JOIN LOGIN LOGIN
			   ON PYH_HED.INPUTOR_CD = LOGIN.TANTO_CD
			   LEFT JOIN VENDER VENDER
			   ON   VENDER.VENDER_DIVISION = 'SI'
			   AND  PYH_HED.SUPPLIER_CD = VENDER.VENDER_CD
		WHERE  PYH_HED.ORGANIZATION_CD IS NOT NULL
		AND  TO_CHAR(PYH_HED.PAYABLE_DATE,'YYYY/MM') = /*condition.srhTargetMonth*/'2009/07'
		
		/*IF (( condition.srhOrganizationCd != null ) && ( condition.srhOrganizationCd != "" ))*/
			AND	(PYH_HED.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/'%' OR ORGANIZATION_NAME LIKE /*condition.srhOrganizationCd*/'%')
		/*END*/
		
		/*IF (( condition.srhTantoCd != null ) && ( condition.srhTantoCd != "" ))*/
			AND	(PYH_HED.INPUTOR_CD LIKE /*condition.srhTantoCd*/'%' OR LOGIN.TANTO_NM LIKE /*condition.srhTantoCd*/'%')
		/*END*/
		
		/*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
			AND	(PYH_HED.SUPPLIER_CD LIKE /*condition.srhVenderCd*/'%' OR VENDER.VENDER_NAME1 LIKE FUN_RET_MASTER_STRING_USE_AC(/*condition.srhVenderCd*/'%'))
		/*END*/
		
		AND (BALANCE_FORWARD <> 0
		OR STOCKING_AMOUNT <> 0
		OR PAYMENT_AMOUNT <> 0
		OR OTHER_PAYMENT_AMOUNT <> 0
		OR STOCKING_RETURNED_AMOUNT <> 0
		OR STOCKING_DISCOUNT_AMOUNT <> 0
		OR OTHER_STOCKING_AMOUNT <> 0
		OR OFFSET_AMOUNT <> 0
		OR TAX_AMOUNT <> 0
		OR ACCOUNT_PAYABLE_BREAKDOWN <> 0
		OR ARREARAGE_BREAKDOWN <> 0
		OR PAYABLE_AMOUNT <> 0)
	)

/*END*/

/*IF ( targetKbn == "2" ) */
	UNION ALL
/*END*/

/*IF ( targetKbn == "1" || targetKbn == "2" ) */

	SELECT
		TPYH_HED.ORGANIZATION_CD
	,	ORG.ORGANIZATION_NAME
	FROM
		TEMPORARY_PAYABLE_HEADER TPYH_HED
	,	ORGANIZATION ORG
	WHERE
		TPYH_HED.ORGANIZATION_CD = ORG.ORGANIZATION_CD
	AND	TPYH_HED.PAYABLE_NO IN 

	(
		SELECT TMP_PYH_HED.PAYABLE_NO                --買掛番号
		
		FROM   TEMPORARY_PAYABLE_HEADER TMP_PYH_HED
			   LEFT JOIN ORGANIZATION ORGANIZATION
			   ON TMP_PYH_HED.ORGANIZATION_CD = ORGANIZATION.ORGANIZATION_CD
			   LEFT JOIN LOGIN LOGIN
			   ON TMP_PYH_HED.INPUTOR_CD = LOGIN.TANTO_CD
			   LEFT JOIN VENDER VENDER
			   ON   VENDER.VENDER_DIVISION = 'SI'
			   AND  TMP_PYH_HED.SUPPLIER_CD = VENDER.VENDER_CD
		WHERE  TMP_PYH_HED.ORGANIZATION_CD IS NOT NULL
		AND  TO_CHAR(TMP_PYH_HED.PAYABLE_DATE,'YYYY/MM') = /*condition.srhTargetMonth*/'2009/07'
		
		/*IF (( condition.srhOrganizationCd != null ) && ( condition.srhOrganizationCd != "" ))*/
			AND	(TMP_PYH_HED.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/'%' OR ORGANIZATION_NAME LIKE /*condition.srhOrganizationCd*/'%')
		/*END*/
		
		/*IF (( condition.srhTantoCd != null ) && ( condition.srhTantoCd != "" ))*/
			AND	(TMP_PYH_HED.INPUTOR_CD LIKE /*condition.srhTantoCd*/'%' OR LOGIN.TANTO_NM LIKE /*condition.srhTantoCd*/'%')
		/*END*/
		
		/*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
			AND	(TMP_PYH_HED.SUPPLIER_CD LIKE /*condition.srhVenderCd*/'%' OR VENDER.VENDER_NAME1 LIKE FUN_RET_MASTER_STRING_USE_AC(/*condition.srhVenderCd*/'%'))
		/*END*/
		
		AND (BALANCE_FORWARD <> 0
		OR STOCKING_AMOUNT <> 0
		OR PAYMENT_AMOUNT <> 0
		OR OTHER_PAYMENT_AMOUNT <> 0
		OR STOCKING_RETURNED_AMOUNT <> 0
		OR STOCKING_DISCOUNT_AMOUNT <> 0
		OR OTHER_STOCKING_AMOUNT <> 0
		OR OFFSET_AMOUNT <> 0
		OR TAX_AMOUNT <> 0
		OR ACCOUNT_PAYABLE_BREAKDOWN <> 0
		OR ARREARAGE_BREAKDOWN <> 0
		OR PAYABLE_AMOUNT <> 0)
	)

/*END*/

	)

ORDER BY
	ORGANIZATION_CD ASC
