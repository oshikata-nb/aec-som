/*
 * 
 *
 * entityName=RepArledgerHeader
 * packageName=credit.arledgerlistforreport
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
		DEP_HED.ORGANIZATION_CD
	,	ORG.ORGANIZATION_NAME
	FROM
		DEPOSIT_HEADER DEP_HED
	,	ORGANIZATION ORG
	WHERE
		DEP_HED.ORGANIZATION_CD = ORG.ORGANIZATION_CD
	AND	DEP_HED.DEPOSIT_NO IN 

	(
		SELECT DEP_HED.DEPOSIT_NO              --売掛番号
		FROM   DEPOSIT_HEADER DEP_HED
			   LEFT JOIN ORGANIZATION
			   ON DEP_HED.ORGANIZATION_CD = ORGANIZATION.ORGANIZATION_CD
			   LEFT JOIN LOGIN LOGIN
			   ON DEP_HED.INPUTOR_CD = LOGIN.TANTO_CD
			   LEFT JOIN VENDER VENDER
			   ON  VENDER.VENDER_DIVISION = 'TS'
			   AND DEP_HED.VENDER_CD = VENDER.VENDER_CD
		WHERE  DEP_HED.ORGANIZATION_CD IS NOT NULL
		AND  TO_CHAR(DEP_HED.CREDIT_DATE,'YYYY/MM') = /*condition.srhTargetMonth*/'2009/07'
		
		/*IF (( condition.srhSectionCd != null ) && ( condition.srhSectionCd != "" ))*/
			AND	(DEP_HED.ORGANIZATION_CD LIKE /*condition.srhSectionCd*/'%' OR ORGANIZATION.ORGANIZATION_NAME LIKE /*condition.srhSectionCd*/'%')
		/*END*/
		
		/*IF (( condition.srhTantoCd != null ) && ( condition.srhTantoCd != "" ))*/
			AND	(DEP_HED.INPUTOR_CD LIKE /*condition.srhTantoCd*/'%' OR LOGIN.TANTO_NM LIKE /*condition.srhTantoCd*/'%')
		/*END*/
		
		/*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
			AND	(DEP_HED.VENDER_CD LIKE /*condition.srhVenderCd*/'%' OR VENDER.VENDER_NAME1 LIKE FUN_RET_MASTER_STRING_USE_AC(/*condition.srhVenderCd*/'%'))
		/*END*/
	)

/*END*/

/*IF ( targetKbn == "2" ) */
	UNION ALL
/*END*/

/*IF ( targetKbn == "1" || targetKbn == "2" ) */

	SELECT
		TDEP_HED.ORGANIZATION_CD
	,	ORG.ORGANIZATION_NAME
	FROM
		TEMPORARY_DEPOSIT_HEADER TDEP_HED
	,	ORGANIZATION ORG
	WHERE
		TDEP_HED.ORGANIZATION_CD = ORG.ORGANIZATION_CD
	AND	TDEP_HED.DEPOSIT_NO IN 
	(
		SELECT TMP_DEP_HED.DEPOSIT_NO              --売掛番号
		FROM   TEMPORARY_DEPOSIT_HEADER TMP_DEP_HED
			   LEFT JOIN ORGANIZATION
			   ON TMP_DEP_HED.ORGANIZATION_CD = ORGANIZATION.ORGANIZATION_CD
			   LEFT JOIN LOGIN LOGIN
			   ON TMP_DEP_HED.INPUTOR_CD = LOGIN.TANTO_CD
			   LEFT JOIN VENDER VENDER
			   ON   VENDER.VENDER_DIVISION = 'TS'
			   AND   TMP_DEP_HED.VENDER_CD = VENDER.VENDER_CD
		WHERE  TMP_DEP_HED.ORGANIZATION_CD IS NOT NULL
		AND  TO_CHAR(TMP_DEP_HED.CREDIT_DATE,'YYYY/MM') = /*condition.srhTargetMonth*/'2009/07'
		
		/*IF (( condition.srhSectionCd != null ) && ( condition.srhSectionCd != "" ))*/
			AND	(TMP_DEP_HED.ORGANIZATION_CD LIKE /*condition.srhSectionCd*/'%' OR ORGANIZATION.ORGANIZATION_NAME LIKE /*condition.srhSectionCd*/'%')
		/*END*/
		
		/*IF (( condition.srhTantoCd != null ) && ( condition.srhTantoCd != "" ))*/
			AND	(TMP_DEP_HED.INPUTOR_CD LIKE /*condition.srhTantoCd*/'%' OR LOGIN.TANTO_NM LIKE /*condition.srhTantoCd*/'%')
		/*END*/
		
		/*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
			AND	(TMP_DEP_HED.VENDER_CD LIKE /*condition.srhVenderCd*/'%' OR VENDER.VENDER_NAME1 LIKE FUN_RET_MASTER_STRING_USE_AC(/*condition.srhVenderCd*/'%'))
		/*END*/
	)

/*END*/

	)

ORDER BY
	ORGANIZATION_CD ASC


