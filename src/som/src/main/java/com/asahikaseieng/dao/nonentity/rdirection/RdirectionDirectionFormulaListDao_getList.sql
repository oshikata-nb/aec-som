/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
*/

/**
 * 製造指図フォーミュラSQL
 *
 * @author tosco
 *
 * entityName=RdirectionFormulaList
 * packageName=rdirection
 * methodName=getList
 *
 */
SELECT *
FROM
    DIRECTION_FORMULA FORMULA
WHERE
    FORMULA.LINE_TYPE = -1
AND FORMULA.DIRECTION_DIVISION = 1

/*IF ((directionNo != null) && (directionNo != ""))*/
AND FORMULA.DIRECTION_NO = /*directionNo*/
/*END*/
/*IF ((stepNo != null) && (stepNo != ""))*/
AND FORMULA.STEP_NO = /*stepNo*/
/*END*/

ORDER BY
    DIRECTION_DIVISION,
    DIRECTION_NO,
    STEP_NO,
    LINE_NO
