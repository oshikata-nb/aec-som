/*
 * 取引先部署取得用SQL
 *
 * entityName=OffsetTranData
 * packageName=offset
 * methodName=getVenderOrganization
 *
 */

SELECT ORGANIZATION_CD, SECTION_CD
FROM VENDER
WHERE VENDER_DIVISION = /*venderDivision*/'%'
AND VENDER_CD = /*venderCd*/'%'
