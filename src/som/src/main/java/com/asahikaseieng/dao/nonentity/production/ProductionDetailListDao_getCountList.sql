/*
 * Created on 2009/04/08
 *
 * $copyright$
 *
*/

/**
 * 生産計画入力　明細画面　明細一覧件数取得用SQL
 *
 * @author tosco
 *
 * entityName=ProductionDetailList
 * packageName=production
 * methodName=getCountList
 *
 */
SELECT	COUNT(*) AS CNT

FROM ASP_PRODUCTION

WHERE	TO_CHAR(ASP_PRODUCTION.ORDER_LET,'yyyy/mm') = /*orderLet*/
AND		ASP_PRODUCTION.ITEM_CD = /*itemCd*/

GROUP BY ITEM_CD,TO_CHAR(ORDER_LET,'yyyy/mm')
