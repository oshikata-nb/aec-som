/*
 * 製造指図フォーミュラ 最終SEQ 取得SQL
 *
 * entityName=PkgRdirectionDirectionFormulaList
 * packageName=pkgrdirection
 * methodName=getLastSeq
 *
 */

SELECT
	NVL(MAX(SEQ), 0) + 1 LAST_SEQ
FROM
	DIRECTION_FORMULA
WHERE 
	LINE_TYPE = -1
AND	DIRECTION_DIVISION = /*directionDivision*/2
AND	DIRECTION_NO = /*directionNo*/'H0903060001'
AND	STEP_NO = /*stepNo*/1
