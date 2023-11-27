/*
 * Created on 2009/02/27
 *
 * $copyright$
 *
*/

/**
 * 包装実績－製造指図プロシージャ－指図区分、指図番号で一括削除SQL
 *
 * @author tosco
 *
 * entityName=PkgRdirectionDirectionProcedureList
 * packageName=pkgrdirection
 * methodName=deleteByDirectionNo
 *
 */
DELETE 
FROM
	DIRECTION_PROCEDURE
WHERE
	DIRECTION_PROCEDURE.DIRECTION_DIVISION = /*directionDivision*/
AND	DIRECTION_PROCEDURE.DIRECTION_NO = /*directionNo*/
