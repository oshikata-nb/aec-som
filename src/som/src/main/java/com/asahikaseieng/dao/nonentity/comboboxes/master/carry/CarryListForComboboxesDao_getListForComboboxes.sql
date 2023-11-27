/*
 * 運送会社マスタコンボボックス用SQL
 *
 * entityName=CarryListForComboboxes
 * packageName=carry
 * methodName=getListForComboboxes
 *
 */
SELECT
    CRY.CARRY_CD,
    CRY.CARRY_NAME1,
    CRY.CARRY_NAME2,
    CRY.TRANCEPORT_LEAD_TIME,
    CRY.COMPANY_CD,
    CRY.INPUTOR_CD,
    CRY.UPDATOR_CD,
    CRY.INPUT_DATE,
    CRY.UPDATE_DATE,
    CRY.LABEL_CD,
    LBL.LABEL_NAME,
    LBL.LABEL_PATH
FROM
    LABEL LBL,
    CARRY CRY
WHERE
    LBL.LABEL_CD(+) = CRY.LABEL_CD
ORDER BY
    CARRY_CD


