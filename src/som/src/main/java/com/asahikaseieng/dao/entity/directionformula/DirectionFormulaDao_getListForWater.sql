/*
 * Created on 2009/07/22
 *
 * $copyright$
 *
*/

/**
 * 水関係製造配合取得SQL
 *
 * @author eto
 *
 * entityName=DirectionFormula
 * packageName=directionformula
 * methodName=geListForWater
 *
 */
SELECT * 
FROM DIRECTION_FORMULA
WHERE DIRECTION_DIVISION= 1 
AND DIRECTION_NO= /*directionNo*/'SS'
AND STEP_NO = /*stepNo*/'1'
AND ITEM_CD IN /*itemCodes*/('54','55','56') 

