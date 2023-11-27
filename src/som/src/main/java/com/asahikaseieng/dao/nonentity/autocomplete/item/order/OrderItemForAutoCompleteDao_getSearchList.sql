/*
 * 受注用品目マスタ詳細画面用SQL
 * entityName=OrderItemListForAutoComplete
 * packageName=order
 * methodName=getSearchList
 *
*/

SELECT *
FROM   (SELECT SALES_TERMS.ITEM_CD
			  ,SALES_TERMS.BALANCE_CD --帳合コード
			  ,ITEM.ITEM_NAME
			  ,ITEM.OTHER_COMPANY_CD1
			  ,ITEM.STYLE_OF_PACKING
			  ,ITEM.UNIT_OF_OPERATION_MANAGEMENT
			  ,ITEM.TYPE_DIVISION
		FROM   SALES_TERMS, ITEM, ARTICLE_ATTRIBUTE_QUEUE
		WHERE  SALES_TERMS.ITEM_CD = ITEM.ITEM_CD
		AND    ITEM.ITEM_CD = ARTICLE_ATTRIBUTE_QUEUE.ITEM_CD
		AND    ITEM.VERSION = ARTICLE_ATTRIBUTE_QUEUE.VERSION
		AND    ARTICLE_ATTRIBUTE_QUEUE.KEEP_DIVISION <> 2
			  
		AND    SALES_TERMS.DELIVERY_CD = /*deliveryCd*/'%'
		AND    (ITEM.ITEM_CD LIKE /*itemCd*/'%' 
			OR		UTL_I18N.TRANSLITERATE(TO_MULTI_BYTE(item_name),'kana_fwkatakana') like FUN_RET_ITEM_STRING_USE_AC( /*itemCd*/'' )
			)
			  
/*IF (( orderDivision == "3" ))*/
		AND    ITEM.TYPE_DIVISION = 4
/*END*/

/*IF (( orderDivision != "3" ))*/
		AND    ITEM.TYPE_DIVISION <> 4
/*END*/
			  
/*IF (( balanceCd != null ) && ( balanceCd != "" ))*/
		AND    SALES_TERMS.BALANCE_CD = /*balanceCd*/'%'
/*END*/
		
		ORDER  BY ITEM.ITEM_CD)

/*IF (rowlimit != null) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/'50'
/*END*/
