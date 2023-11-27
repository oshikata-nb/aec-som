/*
 * Created on 2009/02/03
 *
 * $copyright$
 *
*/

/**
 * 包装実績－工程順序コンボボックス取得用SQL
 *
 * @author tosco
 *
 * entityName=PkgRdirectionProcedureSetpNoForComboboxes
 * packageName=pkgrdirection
 * methodName=findByDirectionNo
 *
 */
SELECT
	DIRECTION_PROCEDURE.DIRECTION_DIVISION
,	DIRECTION_PROCEDURE.DIRECTION_NO
,	DIRECTION_PROCEDURE.STEP_NO
,	DIRECTION_PROCEDURE.SEQ
FROM
	DIRECTION_PROCEDURE
WHERE
	DIRECTION_PROCEDURE.DIRECTION_DIVISION = /*directionDivision*/
AND	DIRECTION_PROCEDURE.DIRECTION_NO = /*directionNo*/
ORDER BY
	DIRECTION_PROCEDURE.SEQ
