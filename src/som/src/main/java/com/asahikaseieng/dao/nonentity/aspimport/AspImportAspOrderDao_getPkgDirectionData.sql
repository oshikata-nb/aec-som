/*
 * 製造・包装指図作成処理　製造データ取得用SQL
 *
 * entityName=AspImportAspOrder
 * packageName=aspimport
 * methodName=getDirectionData
 *
 */
SELECT
	ASP_ORDER.*
FROM
	ASP_ORDER
,	(SELECT ASP_ORDER.ORDER_CD,DIRECTION_HEADER.DIRECTION_NO
	FROM	ASP_ORDER,DIRECTION_HEADER
	WHERE	ASP_ORDER.ORDER_CD = DIRECTION_HEADER.ASP_ORDER_NO(+)
	)DIRECTION_HEADER
,	ITEM

WHERE
	ASP_ORDER.ORDER_TYPE = 'M'
AND	(ASP_ORDER.WORK_STATUS = 'D' OR ASP_ORDER.WORK_STATUS = 'B')
AND	(ASP_ORDER.DISABLED <> '1'  OR ASP_ORDER.DISABLED IS NULL)
AND	ASP_ORDER.ISREPLENISHMENT = '0'

AND	ASP_ORDER.ITEM_CD = ITEM.ITEM_CD(+)
AND	ITEM.TYPE_DIVISION NOT IN ('6','7')

AND	DIRECTION_HEADER.ORDER_CD = ASP_ORDER.ORDER_CD
AND	DIRECTION_HEADER.DIRECTION_NO IS NULL
