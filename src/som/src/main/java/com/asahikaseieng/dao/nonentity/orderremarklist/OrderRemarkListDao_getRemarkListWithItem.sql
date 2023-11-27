/*
 * Created on 2009/02/23
 *
 * $copyright$
 *
*/

/**
 * 備考取得用(品目を含む)SQL
 *
 * @author tosco
 *
 * entityName=OrderRemarkList
 * packageName=orderremarklist
 * methodName=getRemarkListWithItem
 *
 */
SELECT
	REM.REMARK01 AS REMARK01
,	REM.REMARK15 AS REMARK15
,	REM.REMARK16 AS REMARK16
FROM
	(
		SELECT
		'1'   AS  PATTERN,
		REMARK01
,		REMARK15
,		REMARK16
		FROM	
			REMARK
		WHERE
				VENDER_CD IS NULL
			AND DELIVERY_CD IS NULL
			AND ITEM_CD = /*itemCd*/
	UNION
		SELECT
		'2'   AS  PATTERN,
		REMARK01
,		REMARK15
,		REMARK16
		FROM	
			REMARK
		WHERE
				VENDER_DIVISION = 'TS'
			AND VENDER_CD = /*venderCd*/
			AND DELIVERY_CD IS NULL
			AND ITEM_CD = /*itemCd*/
	UNION
		SELECT
		'3'   AS  PATTERN,
		REMARK01
,		REMARK15
,		REMARK16
		FROM	
			REMARK
		WHERE
			    VENDER_CD IS NULL
			AND DELIVERY_CD = /*deliveryCd*/
			AND ITEM_CD = /*itemCd*/
	UNION
		SELECT
		'4'   AS  PATTERN,
		REMARK01
,		REMARK15
,		REMARK16
		FROM	
			REMARK
		WHERE
				VENDER_DIVISION = 'TS'
			AND VENDER_CD = /*venderCd*/
			AND DELIVERY_CD = /*deliveryCd*/
			AND ITEM_CD = /*itemCd*/
	) REM
ORDER BY REM.PATTERN
