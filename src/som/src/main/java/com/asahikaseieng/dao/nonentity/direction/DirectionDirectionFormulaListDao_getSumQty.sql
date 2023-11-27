/*
 * 指図量の合計値取得SQL
 *
 * entityName=DirectionDirectionFormulaList
 * packageName=direction
 * methodName=getSumQty
 *
 */
SELECT
    NVL(SUM(QTY),0)     AS SUM_QTY
FROM
    DIRECTION_FORMULA
WHERE
    DIRECTION_DIVISION = 1
    AND DIRECTION_NO = /*directionNo*/'HAMA000001'
/*IF ( procStepNo != 0 ) */
    AND STEP_NO = /*procStepNo*/1
/*END*/
    AND LINE_TYPE = -1
