/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
*/

/**
 * 製造指図プロシージャ-指図区分、指図番号で削除用SQL
 *
 * @author tosco
 *
 * entityName=RdirectionDirectionProcedureList
 * packageName=rdirection
 * methodName=deleteByDirectionDivisionDirectionNo
 *
 */
DELETE 
FROM
	DIRECTION_PROCEDURE
WHERE
    DIRECTION_DIVISION = 1
AND DIRECTION_NO = /*directionNo*/
