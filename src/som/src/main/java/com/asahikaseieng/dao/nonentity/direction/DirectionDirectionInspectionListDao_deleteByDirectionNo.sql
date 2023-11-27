/*
 * Created on 2009/03/06
 *
 * $copyright$
 *
*/

/**
 * 製造指図検査-指図区分、指図番号で削除用SQL
 *
 * @author tosco
 *
 * entityName=DirectionDirectionInspectionList
 * packageName=direction
 * methodName=deleteByDirectionNo
 *
 */
DELETE
FROM
	DIRECTION_INSPECTION
WHERE
    DIRECTION_DIVISION = '1'
AND DIRECTION_NO = /*directionNo*/'S0903060001'
