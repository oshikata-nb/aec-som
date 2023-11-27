/*
 * 受注取込詳細用SQL 
 *
 * entityName=OrderImportDetailEntity
 * packageName=orderImportDetailEntity
 * methodName=getMaxFrstOrderRow
 *
 */
SELECT
    MAX(FRST_ORDER_ROW_NO)
FROM FRST_ORDER_DETAIL
WHERE FRST_ORDER_NO = /*frstOrderNo*/'PK000000001'
GROUP BY FRST_ORDER_NO
UNION ALL
SELECT 0 FROM DUAL WHERE NOT EXISTS 
(SELECT
    MAX(FRST_ORDER_ROW_NO)
FROM FRST_ORDER_DETAIL
WHERE FRST_ORDER_NO = /*frstOrderNo*/'PK000000001'
GROUP BY FRST_ORDER_NO)

