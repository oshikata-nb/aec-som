/*
 * 理由デフォルト詳細用SQL
 *
 * entityName=ReasonDefaultDetail
 * packageName=reasondefaultdetail
 * methodName=getEntity
 *
 */

SELECT RY_CD, RY_DESCRIPTION
FROM REASON
WHERE DEFAULT_FLG = 1


