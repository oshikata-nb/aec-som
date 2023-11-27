/*
 * 取引先一覧用SQL
 *
 * entityName=VenderList
 * packageName=venderlist
 * methodName=getList
 *
 */

SELECT VENDER_DIVISION, VENDER_CD, VENDER_SHORTED_NAME AS VENDER_NAME1, ORGANIZATION_NAME, VENDER.ADDRESS1, VENDER.TEL_NO
FROM VENDER, ORGANIZATION
WHERE VENDER_DIVISION = /*condition.srhVenderDivision*/'%'

/*IF (condition.srhVenderCd != null) && (condition.srhVenderCd != "")*/
AND	(VENDER_CD LIKE /*condition.srhVenderCd*/'%'
OR VENDER_NAME1 LIKE FUN_RET_MASTER_STRING_USE_AC(/*condition.srhVenderCd*/'%')
OR VENDER_NAME2 LIKE FUN_RET_MASTER_STRING_USE_AC(/*condition.srhVenderCd*/'%'))
/*END*/

/*IF (condition.srhOrganizationCd != null) && (condition.srhOrganizationCd != "")*/
AND	(VENDER.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/'%' OR ORGANIZATION_NAME LIKE /*condition.srhOrganizationCd*/'%')
/*END*/

AND VENDER.ORGANIZATION_CD = ORGANIZATION.ORGANIZATION_CD(+)
ORDER BY VENDER_CD
