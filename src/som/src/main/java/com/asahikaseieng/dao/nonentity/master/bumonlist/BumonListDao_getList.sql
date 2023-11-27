/*
 * 部門マスタ一覧用SQL
 *
 * entityName=BumonList
 * packageName=bumonlist
 * methodName=getList
 *
 */
SELECT SECTION_CD, SECTION_NAME, SUBSTR(SECTION_NAME, 0, 22) SHORT_SECTION_NAME, SECTION_SHORTED_NAME, SUBSTR(SECTION_SHORTED_NAME, 0, 22) SHORT_SECTION_SHORTED_NAME
FROM BUMON
WHERE SECTION_CD IS NOT NULL

/*IF (condition.srhSectionCd != null) && (condition.srhSectionCd != "")*/
AND (SECTION_CD LIKE /*condition.srhSectionCd*/'%' OR SECTION_NAME LIKE /*condition.srhSectionCd*/'%')
/*END*/

ORDER BY SECTION_CD


