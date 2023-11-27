/*
 * 納入先マスタ検索一覧用SQL
 *
 * entityName=PurchaseDeliveryForAutoComplete
 * packageName=purchase
 * methodName=getSearchList
 *
*/

SELECT *
FROM   (SELECT DELIVERY_CD, SEARCH_KANA
		FROM   (SELECT DELI.DELIVERY_CD AS DELIVERY_CD
					  ,DELI.SEARCH_KANA AS SEARCH_KANA
				FROM   DELIVERY DELI
				WHERE  DELI.DELIVERY_CD LIKE /*deliveryCd*/'%'
				OR     DELI.SEARCH_KANA LIKE FUN_RET_MASTER_STRING_USE_AC(/*deliveryCd*/'%')
				UNION ALL
				SELECT LOC.LOCATION_CD   AS DELIVERY_CD
					  ,LOC.LOCATION_NAME AS DELIVERY_NAME1
				FROM   LOCATION LOC
				WHERE  LOC.LOCATION_CD LIKE /*deliveryCd*/'%'
				OR     LOC.LOCATION_NAME LIKE /*deliveryCd*/'%') TEMP
		ORDER  BY TEMP.DELIVERY_CD)

/*IF (rowlimit != null) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/'50'
/*END*/
