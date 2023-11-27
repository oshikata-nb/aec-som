/*
 * COMPONENT SQL
 *
 * entityName=ComponentList
 * packageName=componentlist
 * methodName=getList
 */

SELECT COMPONENT_CD, COMPONENT_NAME, SUBSTRB(COMPONENT_NAME, 0, 80) SHORT_COMPONENT_NAME
FROM COMPONENT
WHERE COMPONENT_CD IS NOT NULL

/*IF (condition.srhComponentCd != null) && (condition.srhComponentCd != "")*/
AND (COMPONENT_CD LIKE /*condition.srhComponentCd*/'%' OR COMPONENT_NAME LIKE /*condition.srhComponentCd*/'%')
/*END*/

ORDER BY COMPONENT_CD


