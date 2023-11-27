/**
 * 販売条件詳細画面備考取得用（品目含まず）SQL
 * @author t1344224
 *
 * entityName=SalesTermsRemarkList
 * packageName=master.salestermsremarklist
 * methodName=getRemarkListNoItem
 *
 */
SELECT
	REM.REMARK01 AS REMARK01
,	REM.REMARK15 AS REMARK15
FROM
	(
		SELECT
		'1'   AS  PATTERN,
		REMARK01
,		REMARK15
		FROM	
			REMARK
		WHERE
				VENDER_DIVISION = 'TS'
			AND VENDER_CD = /*venderCd*/''
			AND DELIVERY_CD IS NULL
			AND ITEM_CD IS NULL
	UNION
		SELECT
		'2'   AS  PATTERN,
		REMARK01
,		REMARK15
		FROM	
			REMARK
		WHERE
			    VENDER_CD IS NULL
			AND DELIVERY_CD = /*deliveryCd*/''
			AND ITEM_CD IS NULL
	UNION
		SELECT
		'3'   AS  PATTERN,
		REMARK01
,		REMARK15
		FROM	
			REMARK
		WHERE
				VENDER_DIVISION = 'TS'
			AND VENDER_CD = /*venderCd*/''
			AND DELIVERY_CD = /*deliveryCd*/''
			AND ITEM_CD IS NULL
	) REM
ORDER BY REM.PATTERN


