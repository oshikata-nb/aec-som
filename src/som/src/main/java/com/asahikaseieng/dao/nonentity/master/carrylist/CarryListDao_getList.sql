/*
 * 運送会社マスタ一覧用SQL
 *
 * entityName=CarryList
 * packageName=carrylist
 * methodName=getList
 *
 */

SELECT CARRY_CD, CARRY_NAME1, SUBSTRB(CARRY_NAME1, 0, 28) SHORT_CARRY_NAME1, CARRY_NAME2, SUBSTRB(CARRY_NAME2, 0, 28) SHORT_CARRY_NAME2, ABBREVIATION, SUBSTRB(ABBREVIATION, 0, 28) SHORT_ABBREVIATION
FROM CARRY CRY
WHERE CARRY_CD IS NOT NULL

/*IF (condition.srhCarryCd != null) && (condition.srhCarryCd != "")*/
AND (CARRY_CD LIKE /*condition.srhCarryCd*/'%' OR CARRY_NAME1 LIKE /*condition.srhCarryCd*/'%')
/*END*/

ORDER BY CARRY_CD


