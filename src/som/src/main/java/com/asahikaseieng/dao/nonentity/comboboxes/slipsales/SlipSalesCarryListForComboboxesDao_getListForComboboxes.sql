/*
 * 運送会社コンボボックス用SQL
 *
 * entityName=SlipSalesCarryListForComboboxes
 * packageName=slipsales
 * methodName=getListForComboboxes
 *
 */
SELECT
    CRY.CARRY_CD,
    CRY.CARRY_NAME1,
    CRY.CARRY_NAME2
FROM
    CARRY CRY
ORDER BY
    CARRY_CD


