/*
 * Created on 2009/07/22
 *
 * $copyright$
 *
*/

/**
 * 水関係製造工程取得SQL
 *
 * @author eto
 *
 * entityName=DirectionProcedure
 * packageName=directionprocedure
 * methodName=geListForWater
 *
 */
SELECT * 
FROM DIRECTION_PROCEDURE
WHERE DIRECTION_DIVISION= 1 
AND DIRECTION_NO= /*directionNo*/'SS'
AND OPERATION_CD IN /*operationCd*/('54','55','56') 
ORDER BY SEQ
