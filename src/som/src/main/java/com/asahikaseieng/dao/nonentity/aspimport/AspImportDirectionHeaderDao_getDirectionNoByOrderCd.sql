/*
 * オーダコードから製造指図番号取得用　取得用SQL
 *
 * entityName=AspImportDirectionHeader
 * packageName=aspimport
 * methodName=getDirectionNoByOrderCd
 *
 */

SELECT
	DIRECTION_HEADER.*
FROM
	ASP_ORDER
,	DIRECTION_HEADER

WHERE
	DIRECTION_HEADER.ASP_ORDER_NO LIKE ASP_ORDER.ORDER_CD || '%'
AND	ASP_ORDER.ORDER_CD IN /*orderCd*/('M003288')
