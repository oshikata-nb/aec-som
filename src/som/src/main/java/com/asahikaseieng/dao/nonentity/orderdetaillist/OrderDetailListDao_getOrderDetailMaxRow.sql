/*
 * 受注詳細品目リスト 最大行番号取得用SQL
 *
 * entityName=OrderDetailList
 * packageName=orderdetaillist
 * methodName=getDetailMaxRow
 *
 */
SELECT
	NVL(MAX(ROW_NO),0)
FROM
	ORDER_DETAIL
WHERE
	ORDER_NO = /*orderNo*/
