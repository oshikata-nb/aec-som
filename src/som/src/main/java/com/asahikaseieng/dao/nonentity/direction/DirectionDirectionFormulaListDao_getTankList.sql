/*
 * Created on 2009/02/24
 *
 * $copyright$
 *
*/

/**
 * 製造指図フォーミュラ一覧用SQL
 *
 * @author tosco
 *
 * entityName=DirectionFormulaDetail
 * packageName=direction
 * methodName=getTankList
 *
 */
SELECT DISTINCT
    FORMULA.STEP_NO AS STEP_NO,
    FORMULA.LINE_NO AS LINE_NO,
    UNI.KIND AS KIND,
    UNI.ISSUE_DATE AS ISSUE_DATE,
    UNI.LOT_NO AS LOT_NO,
    UNI.LOCATION_CD AS LOCATION_CD
FROM
    (
        SELECT
            '1' AS KIND,
            FML.STEP_NO AS STEP_NO,
            FML.LINE_NO AS LINE_NO,
            LOT.ISSUE_DATE AS ISSUE_DATE,
            LOT.LOT_NO AS LOT_NO,
            LOT.LOCATION_CD AS LOCATION_CD
        FROM
            LOT_INVENTORY LOT,
            DIRECTION_FORMULA FML
        WHERE
            FML.DIRECTION_DIVISION = 1 AND
            FML.DIRECTION_NO = /*directionNo*/'S0909220001' AND
            LOT.ITEM_CD = FML.ITEM_CD AND
            (LOT.BACKORDER_QTY > 0 OR LOT.INVENTORY_QTY > 0)
        UNION ALL
        SELECT
            '2' AS KIND,
            FML.STEP_NO AS STEP_NO,
            FML.LINE_NO AS LINE_NO,
            LOT.ISSUE_DATE AS ISSUE_DATE,
            LOT.LOT_NO AS LOT_NO,
            LOT.LOCATION_CD AS LOCATION_CD
        FROM
            LOT_INVENTORY LOT,
            (
                SELECT
                    ITEM_CD AS ITEM_CD,
                    MAX(VERSION) AS VERSION
                FROM
                    ITEM
                GROUP BY
                    ITEM_CD
            ) MITM,
            ITEM ITM,
            DIRECTION_FORMULA FML
        WHERE
            FML.DIRECTION_DIVISION = 1 AND
            FML.DIRECTION_NO = /*directionNo*/'S0909220001' AND
            MITM.ITEM_CD = FML.ITEM_CD AND
            ITM.ITEM_CD = MITM.ITEM_CD AND
            ITM.VERSION = MITM.VERSION AND
            LOT.PARENT_ITEM_CD = ITM.PARENT_ITEM_CD AND
            FML.ITEM_CD != LOT.ITEM_CD AND
            (LOT.BACKORDER_QTY > 0 OR LOT.INVENTORY_QTY > 0)
    ) UNI,
    DIRECTION_FORMULA FORMULA,
    DIRECTION_PROCEDURE PROC
WHERE
    FORMULA.DIRECTION_DIVISION = 1 AND
    FORMULA.DIRECTION_NO = /*directionNo*/'S0909220001' AND
    PROC.DIRECTION_DIVISION = 1 AND
    FORMULA.DIRECTION_NO = PROC.DIRECTION_NO AND
    FORMULA.STEP_NO = PROC.STEP_NO AND
    PROC.OPERATION_CD IN (21, 22, 24, 26) AND
    FORMULA.STEP_NO = UNI.STEP_NO(+) AND
    FORMULA.LINE_NO = UNI.LINE_NO(+)
ORDER BY
    STEP_NO,
    LINE_NO,
    KIND,
    ISSUE_DATE,
    LOT_NO,
    LOCATION_CD
