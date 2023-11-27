/*
 * Created on 2009/02/03
 *
 * $copyright$
 *
*/

/**
 * 出荷指図－運送会社一覧取得用SQL
 *
 * @author tosco
 *
 * entityName=ShippingCarryForComboboxes
 * packageName=shipping
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
