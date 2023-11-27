/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
*/

/**
 * 製造指図検査SQL
 *
 * @author tosco
 *
 * entityName=DirectionInspectionList
 * packageName=direction
 * methodName=getList
 *
 */
SELECT *
FROM
    DIRECTION_INSPECTION INSPECTION
WHERE
    INSPECTION.DIRECTION_DIVISION = 1
/*IF (directionNo != null && directionNo != "")*/
    AND INSPECTION.DIRECTION_NO = /*directionNo*/
/*END*/
/*IF (stepNo != null && stepNo != "")*/
    AND INSPECTION.STEP_NO = /*stepNo*/
/*END*/

ORDER BY
    DIRECTION_NO,
    STEP_NO,
    LINE_NO
