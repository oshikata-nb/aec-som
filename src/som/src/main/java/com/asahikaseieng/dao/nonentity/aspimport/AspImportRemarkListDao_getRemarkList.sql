/*
 * Created on 2009/02/23
 *
 * $copyright$
 *
*/

/**
 * 備考取得用SQL
 *
 * @author tosco
 *
 * entityName=AspImportRemarkList
 * packageName=aspimport
 * methodName=getRemarkList
 *
 */
SELECT
	REM.REMARK12 AS REMARK12,
	REM.REMARK13 AS REMARK13
FROM
	(
		SELECT
		'1'   AS  PATTERN,
		REMARK12,
		REMARK13
		FROM	
			REMARK
		WHERE
				VENDER_CD IS NULL
			AND DELIVERY_CD IS NULL
			AND ITEM_CD = /*itemCd*/
	UNION
		SELECT
		'2'   AS  PATTERN,
		REMARK12,
		REMARK13
		FROM	
			REMARK
		WHERE
				VENDER_DIVISION = 'SI'
			AND VENDER_CD = /*venderCd*/
			AND DELIVERY_CD IS NULL
			AND ITEM_CD IS NULL
	UNION
		SELECT
		'3'   AS  PATTERN,
		REMARK12,
		REMARK13
		FROM	
			REMARK
		WHERE
			    VENDER_CD IS NULL
			AND DELIVERY_CD = /*deliveryCd*/
			AND ITEM_CD IS NULL
	UNION
		SELECT
		'4'   AS  PATTERN,
		REMARK12,
		REMARK13
		FROM	
			REMARK
		WHERE
				VENDER_DIVISION = 'SI'
			AND VENDER_CD = /*venderCd*/
			AND DELIVERY_CD IS NULL
			AND ITEM_CD = /*itemCd*/
	UNION
		SELECT
		'5'   AS  PATTERN,
		REMARK12,
		REMARK13
		FROM	
			REMARK
		WHERE
			    VENDER_CD IS NULL
			AND DELIVERY_CD = /*deliveryCd*/
			AND ITEM_CD = /*itemCd*/
	UNION
		SELECT
		'6'   AS  PATTERN,
		REMARK12,
		REMARK13
		FROM	
			REMARK
		WHERE
				VENDER_DIVISION = 'SI'
			AND VENDER_CD = /*venderCd*/
			AND DELIVERY_CD = /*deliveryCd*/
			AND ITEM_CD IS NULL
	UNION
		SELECT
		'7'   AS  PATTERN,
		REMARK12,
		REMARK13
		FROM	
			REMARK
		WHERE
				VENDER_DIVISION = 'SI'
			AND VENDER_CD = /*venderCd*/
			AND DELIVERY_CD = /*deliveryCd*/
			AND ITEM_CD = /*itemCd*/
	) REM
ORDER BY REM.PATTERN
