/*
 * Created on 2009/02/05
 *
 * $copyright$
 *
*/

/**
 * 製造実績検査-指図区分、指図番号、STEP_NOで削除用SQL
 *
 * @author tosco
 *
 * entityName=RdirectionDirectionInspectionList
 * packageName=rdirection
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

