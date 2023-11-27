/*
 * Created on 2009/02/05
 *
 * $copyright$
 *
*/

/**
 * 製造指図検査-指図区分、指図番号、STEP_NOで削除用SQL
 *
 * @author tosco
 *
 * entityName=DirectionDirectionInspectionList
 * packageName=direction
 * methodName=deleteByDirectionNoStepNo
 *
 */
DELETE 
FROM
	DIRECTION_INSPECTION
WHERE
    DIRECTION_INSPECTION.DIRECTION_DIVISION = '1'
AND	DIRECTION_INSPECTION.DIRECTION_NO = /*directionNo*/
AND DIRECTION_INSPECTION.STEP_NO = /*stepNo*/

