/*
 * Created on 2009/05/08
 *
 * $copyright$
 *
*/

/**
 * 在庫引落情報削除用SQL
 *
 * @author tosco
 *
 * entityName=MaterialRinputDetailList
 * packageName=materialrinput
 * methodName=deleteStockpdInfo
 *
 */
DELETE FROM　PURCHASE_MATE_INJECTION
WHERE	BUY_SUBCONTRACT_ORDER_NO =/*buySubcontractOrderNo*/
AND		RECIPE_ID = /*recipeId*/
AND		STEP_NO = /*stepNo*/
AND		LINE_NO = /*lineNo*/
AND		SEQ > 0