/*
 * 製造・包装指図作成　オーダコードに紐付く作業を　取得用SQL
 *
 * entityName=AspImportAspOperation
 * packageName=aspimport
 * methodName=getEntityByOrderCd
 *
 */

SELECT
	ASP_OPERATION.*
FROM
	ASP_OPERATION
WHERE
	ASP_OPERATION.ORDER_CD = /*orderCd*/
ORDER BY
	ASP_OPERATION.OPERATION_CD