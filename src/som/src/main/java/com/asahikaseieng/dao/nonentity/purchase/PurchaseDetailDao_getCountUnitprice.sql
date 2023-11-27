/*
 * Created on 2009/03/05
 *
 * $copyright$
 *
*/

/**
 * 登録時、仕入先別単価マスタ存在チェックSQL
 *
 * @author tosco
 *
 * entityName=PurchaseDetail
 * packageName=purchase
 * methodName=getCountUnitprice
 *
 */
SELECT
	 COUNT(*) AS CNT
FROM	UNITPRICE
WHERE	VENDER_DIVISION = 'SI'	--発注　'SI'固定
AND		VENDER_CD = /*venderCd*/
AND		ITEM_CD = /*itemCd*/
AND		CONSECUTIVE_NO = '1'
