/*
 * 品目マスタ標準販売単価取得用（他社コード１で検索）SQL
 *
 * entityName=ItemForAutoComplete
 * packageName=item
 * methodName=getOtherCompany1DetailDigitPriceList
 *
*/

SELECT *
FROM   (SELECT ITEM.ITEM_CD                      AS ITEM_CD
			  ,ITEM.ITEM_NAME                    AS ITEM_NAME
			  ,ITEM.OTHER_COMPANY_CD1            AS OTHER_COMPANY_CD1
			  ,ITEM.STYLE_OF_PACKING             AS STYLE_OF_PACKING
			  ,ITEM.UNIT_OF_OPERATION_MANAGEMENT AS UNIT_OF_OPERATION_MANAGEMENT
			  ,ITEM.UNIT_OF_FRACTION_MANAGEMENT
			  ,ITEM.KG_OF_FRACTION_MANAGEMENT
			  ,ITEM.KG_CONVERSION_COEFFICIENT
			  ,ITEM.LOT_DIVISION
			  ,SMALLNUM_LENGTH
			  ,ROUND_DIVISION
		FROM   ITEM
			  ,ARTICLE_ATTRIBUTE_QUEUE
			  ,NUMBER_CHKDISIT
			  ,(SELECT ITEM_CD, MAX(VERSION) VERSION
				FROM   ITEM
				WHERE  OTHER_COMPANY_CD1 LIKE /*otherCompany1*/'%'
				GROUP  BY ITEM_CD
				
				) MITM
		WHERE  ITEM.ITEM_CD = MITM.ITEM_CD
		AND    ITEM.VERSION = MITM.VERSION
		AND    ITEM.ITEM_CD = ARTICLE_ATTRIBUTE_QUEUE.ITEM_CD(+)
		AND    ITEM.VERSION = ARTICLE_ATTRIBUTE_QUEUE.VERSION(+)
		AND    UNIT_DIVISION(+) = 'URTANKA'
		AND    VENDER_DIVISION(+) = ' '
		AND    VENDER_CD(+) = ' '
		ORDER  BY ITEM.OTHER_COMPANY_CD1, ITEM.ITEM_CD)

/*IF (rowlimit != null) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/'50'
/*END*/
