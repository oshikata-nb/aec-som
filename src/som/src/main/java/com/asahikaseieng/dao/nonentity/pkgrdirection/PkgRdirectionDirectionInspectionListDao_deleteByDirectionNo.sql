/*
 * Created on 2009/02/27
 *
 * $copyright$
 *
*/

/**
 * 包装実績検査－指図区分、指図番号で一括削除SQL
 *
 * @author tosco
 *
 * entityName=PkgRdirectionDirectionInspectionLis
 * packageName=pkgrdirection
 * methodName=deleteByDirectionNo
 *
 */
DELETE 
FROM
	DIRECTION_INSPECTION
WHERE
	DIRECTION_INSPECTION.DIRECTION_DIVISION = /*directionDivision*/
AND	DIRECTION_INSPECTION.DIRECTION_NO = /*directionNo*/
