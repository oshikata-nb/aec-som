/*
 * カレンダー一括削除用SQL
 *
 * entityName=CalDelete
 * packageName=caldelete
 * methodName=deleteList
 */

DELETE
FROM CAL
WHERE CAL_CD = /*calCd*/'%'
AND CAL_YEAR = /*calYear*/2009
