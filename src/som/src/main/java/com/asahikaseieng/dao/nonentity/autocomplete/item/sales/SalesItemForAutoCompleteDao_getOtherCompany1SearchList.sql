/*
 * 売上(預り品)用品目マスタ詳細画面用SQL
 * 預かり品の品目のみ取得する。
 * entityName=SalesItemListForAutoComplete
 * packageName=shipping
 * methodName=getOtherCompany1SearchList
 *
*/

SELECT *
FROM   (SELECT ITEM.ITEM_CD                      AS ITEM_CD
			  ,ITEM.ITEM_NAME                    AS ITEM_NAME
			  ,ITEM.OTHER_COMPANY_CD1            AS OTHER_COMPANY_CD1
			  ,ITEM.STYLE_OF_PACKING             AS STYLE_OF_PACKING
			  ,ITEM.UNIT_OF_OPERATION_MANAGEMENT AS UNIT_OF_OPERATION_MANAGEMENT
		FROM   ITEM ITEM, ARTICLE_ATTRIBUTE_QUEUE ATTR
		WHERE  ITEM.OTHER_COMPANY_CD1 LIKE /*otherCompany1*/'%'
		AND    ITEM.ITEM_CD = ATTR.ITEM_CD
		AND    ITEM.VERSION = ATTR.VERSION
		AND    ATTR.KEEP_DIVISION = 2
		AND    EXISTS (SELECT 'X'
				FROM   SALES_TERMS
				WHERE  DELIVERY_CD = /*deliveryCd*/'%'
				AND    ITEM_CD = ITEM.ITEM_CD)
		ORDER  BY ITEM_CD)

/*IF (rowlimit != null) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/'50'
/*END*/
