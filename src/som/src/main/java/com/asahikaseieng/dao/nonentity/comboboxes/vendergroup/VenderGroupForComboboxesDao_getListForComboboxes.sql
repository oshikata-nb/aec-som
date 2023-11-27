/*
 * 得意先グループコンボボックス用SQL
 *
 * entityName=VenderGroupForComboboxes
 * packageName=vendergroup
 * methodName=getListForComboboxes
 *
 */
SELECT
    VENDER_GROUP_CD
,	VENDER_GROUP_NAME
FROM
    ORDER_VENDER_MASTER
GROUP BY
	VENDER_GROUP_CD
,	VENDER_GROUP_NAME
ORDER BY
    VENDER_GROUP_CD
