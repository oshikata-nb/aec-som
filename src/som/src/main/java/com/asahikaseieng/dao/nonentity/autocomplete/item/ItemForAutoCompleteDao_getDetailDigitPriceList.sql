/*
 * 品目マスタ標準販売単価取得用SQL
 *
 * entityName=ItemForAutoComplete
 * packageName=item
 * methodName=getDetailDigitPriceList
 *
*/

SELECT *
FROM   (SELECT ITEM.ITEM_CD AS ITEM_CD
			  ,ITEM.ITEM_NAME AS ITEM_NAME
			  ,ITEM.OTHER_COMPANY_CD1 AS OTHER_COMPANY_CD1
			  ,ITEM.STYLE_OF_PACKING AS STYLE_OF_PACKING
			  ,ITEM.UNIT_OF_OPERATION_MANAGEMENT AS UNIT_OF_OPERATION_MANAGEMENT
			  ,NVL(ARTICLE_ATTRIBUTE_QUEUE.SELLING_PRICE, 0) SELLING_PRICE
			  ,SMALLNUM_LENGTH
			  ,ROUND_DIVISION
		FROM   ITEM
			  ,ARTICLE_ATTRIBUTE_QUEUE
			  ,NUMBER_CHKDISIT
			  ,(SELECT ITEM_CD, MAX(VERSION) VERSION
				FROM   ITEM
				WHERE  (ITEM.ITEM_CD LIKE /*itemCd*/'%' OR		UTL_I18N.TRANSLITERATE(TO_MULTI_BYTE(item_name),'kana_fwkatakana') like FUN_RET_ITEM_STRING_USE_AC( /*itemCd*/'' ))
				GROUP  BY ITEM_CD
				
				) MITM
		WHERE  ITEM.ITEM_CD = MITM.ITEM_CD
		AND    ITEM.VERSION = MITM.VERSION
		AND    ITEM.ITEM_CD = ARTICLE_ATTRIBUTE_QUEUE.ITEM_CD(+)
		AND    ITEM.VERSION = ARTICLE_ATTRIBUTE_QUEUE.VERSION(+)
		AND    UNIT_DIVISION(+) = 'URTANKA'
		AND    VENDER_DIVISION(+) = ' '
		AND    VENDER_CD(+) = ' '
		ORDER  BY ITEM.ITEM_CD)

/*IF (rowlimit != null) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/'50'
/*END*/
