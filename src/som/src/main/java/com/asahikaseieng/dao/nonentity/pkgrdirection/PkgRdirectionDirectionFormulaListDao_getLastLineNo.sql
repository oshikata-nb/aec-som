/*
 * 製造指図フォーミュラ 最終LINE_NO 取得SQL
 *
 * entityName=PkgRdirectionDirectionFormulaList
 * packageName=pkgrdirection
 * methodName=getLastLineNo
 *
 */

SELECT
	NVL(MAX(LINE_NO), 0) + 1 LAST_LINE_NO
FROM
	DIRECTION_FORMULA
WHERE 
	LINE_TYPE = -1
AND	DIRECTION_DIVISION = /*directionDivision*/2
AND	DIRECTION_NO = /*directionNo*/'H0903060001'
AND	STEP_NO = /*stepNo*/1
