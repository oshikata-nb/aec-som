/*
 * Created on 2009/03/24
 *
 * $copyright$
 *
*/

/*
 * 製造実績－フォーミュラ 最終SEQ 取得SQL
 *
 * entityName=RdirectionDirectionFormulaList
 * packageName=rdirection
 * methodName=getLastSeq
 *
 */

SELECT NVL(MAX(SEQ), 0) + 1 LAST_SEQ
FROM DIRECTION_FORMULA
WHERE 
    LINE_TYPE = -1
    AND DIRECTION_DIVISION = 1
    AND DIRECTION_NO = /*directionNo*/1
    AND STEP_NO = /*stepNo*/
