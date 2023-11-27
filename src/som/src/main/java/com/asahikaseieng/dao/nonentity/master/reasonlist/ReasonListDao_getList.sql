/*
 * 理由マスタ一覧用SQL
 *
 * entityName=ReasonList
 * packageName=reasonlist
 * methodName=getList
 *
 */

SELECT RY_CD, RY_DESCRIPTION, DEFAULT_FLG,

CASE DEFAULT_FLG
	WHEN 1 THEN '○'
	WHEN 0 THEN '×'
	ELSE NULL
END DEFAULT_FLG_NAME

FROM REASON
WHERE RY_CD IS NOT NULL

/*IF (condition.srhRyCd != null) && (condition.srhRyCd != "")*/
AND (RY_CD LIKE /*condition.srhRyCd*/'%' OR RY_DESCRIPTION LIKE /*condition.srhRyCd*/'%')
/*END*/

ORDER BY RY_CD


