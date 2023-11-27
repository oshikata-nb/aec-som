/*
 * 成分マスタ用SQL
 *
 * entityName=ComponentDetail
 * packageName=componentdetail
 * methodName=getEntity
 *
 */

SELECT COMPONENT_CD, COMPONENT_NAME, UPDATE_DATE
FROM COMPONENT
WHERE COMPONENT_CD = /*componentCd*/'%'


