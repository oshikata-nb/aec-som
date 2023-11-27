/*
 * カレンダーマスタ詳細検索用SQL
 *
 * entityName=CalDetail
 * packageName=caldetail
 * methodName=getEntity
 *
 */

SELECT CAL_CD
FROM CAL
WHERE CAL_CD = /*calCd*/'%'
GROUP BY CAL_CD


