/*
 * Created on 2009/03/18
 *
 * $copyright$
 *
*/

/**
 * 出荷実績－運送会社一覧取得用SQL
 *
 * @author tosco
 *
 * entityName=ShippingResultCarryForComboboxes
 * packageName=shippingresult
 * methodName=getCarryList
 *
 */
SELECT
	 CARRY_CD AS            CARRY_CD
	,CARRY_NAME1 AS         CARRY_NAME1
	,CARRY_NAME2 AS         CARRY_NAME2
FROM
    CARRY
ORDER BY
    CARRY_CD
