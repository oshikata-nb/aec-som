/*
 * 買掛元帳帳票明細用SQL
 *
 * entityName=RepApledgerDetail
 * packageName=debt.apledgerlistforreport
 * methodName=getListForReport
 *
 */
SELECT
	*
FROM 
	REP_APLEDGER_DETAIL
WHERE 
	PAYABLE_NO IN
	(
		/*IF (condition.srhNormalFlg)*/
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
		
		/*END*/
		
		/*IF (condition.srhNormalFlg && condition.srhTempClosingFlg)*/
			UNION
		/*END*/
		
		/*IF (condition.srhTempClosingFlg)*/
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
		
		/*END*/
	)

	/*IF ( TARGETKBN == "0" ) */
	AND SIME_KBN = '1'
	/*END*/

	/*IF ( TARGETKBN == "1" ) */
	AND SIME_KBN = '2'
	/*END*/

	/*IF ( TARGETKBN == "2" ) */
	AND SIME_KBN IN ('1', '2') 
	/*END*/

ORDER BY
	ORGANIZATION_CD
,	PAYABLE_NO
,	SUPPLIER_CD
,	ORDER_FLG
,	TRAN_DATE
,	TRAN_DIVI
,	SLIP_NO
,	ROW_NO ASC




