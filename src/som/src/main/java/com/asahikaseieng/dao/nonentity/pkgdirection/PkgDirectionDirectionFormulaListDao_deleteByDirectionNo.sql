/*
 * Created on 2009/01/26
 *
 * $copyright$
 *
*/

/**
 * 包装指図－製造指図フォーミュラ－指図区分、指図番号で一括削除SQL
 *
 * @author tosco
 *
 * entityName=PkgDirectionDirectionFormulaList
 * packageName=pkgdirection
 * methodName=deleteByDirectionNo
 *
 */
DELETE 
FROM
	DIRECTION_FORMULA
WHERE
	DIRECTION_FORMULA.DIRECTION_DIVISION = /*directionDivision*/
AND	DIRECTION_FORMULA.DIRECTION_NO = /*directionNo*/
