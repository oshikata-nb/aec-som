/*
 * Created on 2009/02/03
 *
 * $copyright$
 *
*/

/**
 * 製造実績－工程順序コンボボックス用SQL
 *
 * @author tosco
 *
 * entityName=RdirectionProcedureSetpNoForComboboxes
 * packageName=rdirection
 * methodName=findByDirectionNo
 *
 */
SELECT
	DIRECTION_PROCEDURE.DIRECTION_NO
,	DIRECTION_PROCEDURE.STEP_NO
,	DIRECTION_PROCEDURE.SEQ
FROM
	DIRECTION_PROCEDURE
WHERE
	DIRECTION_PROCEDURE.DIRECTION_NO = /*directionNo*/
ORDER BY
	DIRECTION_PROCEDURE.SEQ
