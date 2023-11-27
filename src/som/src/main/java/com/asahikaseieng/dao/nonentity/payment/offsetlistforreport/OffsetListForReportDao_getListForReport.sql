/*
 * グループ間相殺入力帳票用SQL
 *
 * entityName=OffsetListForReport
 * packageName=offsetlistforreport
 * methodName=getListForReport
 *
 */
SELECT OGH.*
, ORG.ORGANIZATION_NAME
, OSG.OFFSET_GROUP_NAME
, OGD.CATEGORY_DIVISION
, OGD.CATEGORY_NAME

, CASE OGH.APPROVAL_STATUS
	WHEN 1 THEN '入力中'
	WHEN 2 THEN '承認依頼中'
	WHEN 3 THEN '承認済'
	ELSE NULL
END APPROVAL_STATUS_NAME

, APPROVOR.TANTO_NM APPROVOR_NAME
, INPUTOR.TANTO_NM INPUTOR_NAME
, UPDATOR.TANTO_NM UPDATOR_NAME

FROM OFFSET_GROUP_HEADER OGH
, BELONG
, ORGANIZATION ORG
, LOGIN APPROVOR
, LOGIN INPUTOR
, LOGIN UPDATOR

, (SELECT DISTINCT OFFSET_GROUP_CD, OFFSET_GROUP_NAME
FROM OFFSET_GROUP) OSG

, (SELECT DISTINCT OFF.OFFSET_NO, OFF.CATEGORY_DIVISION, CLA.CATEGORY_NAME
FROM OFFSET_GROUP_DATA OFF
, CLASSIFICATION CLA
WHERE CLA.DATA_TYPE = 5
AND OFF.CATEGORY_DIVISION = TO_CHAR(CLA.CATEGORY_DIVISION)
) OGD

WHERE OGH.OFFSET_NO IS NOT NULL

/*IF (condition.srhOrganizationCd != null && condition.srhOrganizationCd != "")*/
AND	(BELONG.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/'%' OR ORG.ORGANIZATION_NAME LIKE /*condition.srhOrganizationCd*/'%')
/*END*/

/*IF (condition.srhTantoCd != null && condition.srhTantoCd != "")*/
AND	(OGH.INPUTOR_CD LIKE /*condition.srhTantoCd*/'%' OR INPUTOR.TANTO_NM LIKE /*condition.srhTantoCd*/'%')
/*END*/

/*IF (condition.srhOffsetGrp != null && condition.srhOffsetGrp != "")*/
AND	OGH.OFFSET_GROUP_CD = /*condition.srhOffsetGrp*/'%'
/*END*/

/*IF (condition.srhOffsetDateFrom != null && condition.srhOffsetDateFrom != "")*/
AND	OGH.OFFSET_DATE >= /*condition.srhOffsetDateFrom*/'2009/07/01'
/*END*/

/*IF (condition.srhOffsetDateTo != null && condition.srhOffsetDateTo != "")*/
AND	OGH.OFFSET_DATE <= /*condition.srhOffsetDateTo*/'2009/07/31'
/*END*/

/*IF (condition.srhCassification != null && condition.srhCassification != "") */
AND	OGD.CATEGORY_DIVISION = /*condition.srhCassification*/'%'
/*END*/

/*IF condition.srhOutputDivision != 0 */
AND	OGH.APPROVAL_STATUS = /*condition.srhOutputDivision*/1
/*END*/

AND NVL(BELONG.BELONG_KBN, 1) = 1 --主所属
AND OGH.INPUTOR_CD = BELONG.TANTO_CD(+)
AND BELONG.ORGANIZATION_CD = ORG.ORGANIZATION_CD(+)
AND OGH.OFFSET_GROUP_CD = OSG.OFFSET_GROUP_CD(+)
AND OGH.OFFSET_NO = OGD.OFFSET_NO(+)
AND OGH.APPROVEDBY = APPROVOR.TANTO_CD(+)
AND OGH.INPUTOR_CD = INPUTOR.TANTO_CD(+)
AND OGH.UPDATOR_CD = UPDATOR.TANTO_CD(+)

ORDER BY OGH.OFFSET_NO, OGH.OFFSET_GROUP_CD, OGH.OFFSET_DATE


