/*
 * 部門マスタ用SQL
 *
 * entityName=BumonDetail
 * packageName=bumondetail
 * methodName=getEntity
 *
 */
SELECT SECTION_CD, SECTION_NAME, SECTION_SHORTED_NAME, ACCOUNT_CD, ACCOUNT_SECTION_CD, UPDATE_DATE
FROM BUMON
WHERE SECTION_CD = /*sectionCd*/'%'


