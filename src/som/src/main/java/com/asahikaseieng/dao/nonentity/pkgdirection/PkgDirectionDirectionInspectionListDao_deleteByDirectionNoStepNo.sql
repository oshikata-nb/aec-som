/*
 * Created on 2009/02/05
 *
 * $copyright$
 *
*/

/**
 * 包装指図検査-指図区分、指図番号、STEP_NOで削除用SQL
 *
 * @author tosco
 *
 * entityName=PkgDirectionDirectionInspectionList
 * packageName=pkgdirection
 * methodName=deleteByDirectionNoStepNo
 *
 */
DELETE 
FROM
	DIRECTION_INSPECTION
WHERE
    DIRECTION_INSPECTION.DIRECTION_DIVISION = /*directionDivision*/
AND	DIRECTION_INSPECTION.DIRECTION_NO = /*directionNo*/
AND DIRECTION_INSPECTION.STEP_NO = /*stepNo*/

