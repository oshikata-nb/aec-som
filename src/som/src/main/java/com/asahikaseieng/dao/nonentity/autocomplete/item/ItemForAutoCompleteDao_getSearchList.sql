/*
 * 品目マスタ検索一覧用SQL
 *
 * entityName=ItemListForAutoComplete
 * packageName=item
 * methodName=getSearchList
 *
*/

SELECT *
FROM   (SELECT ITEM.ITEM_CD                      AS ITEM_CD
			  ,ITEM.ITEM_NAME                    AS ITEM_NAME
			  ,ITEM.OTHER_COMPANY_CD1            AS OTHER_COMPANY_CD1
			  ,ITEM.STYLE_OF_PACKING             AS STYLE_OF_PACKING
			  ,ITEM.UNIT_OF_OPERATION_MANAGEMENT AS UNIT_OF_OPERATION_MANAGEMENT
		FROM   ITEM
			  ,(SELECT ITEM_CD, MAX(VERSION) VERSION
				FROM   ITEM
				WHERE  ITEM_CD LIKE /*itemCd*/'%'
				OR		UTL_I18N.TRANSLITERATE(TO_MULTI_BYTE(item_name),'kana_fwkatakana') like FUN_RET_ITEM_STRING_USE_AC( /*itemCd*/'' )
				GROUP  BY ITEM_CD
				
				) MITM
		WHERE  ITEM.ITEM_CD = MITM.ITEM_CD
		AND    ITEM.VERSION = MITM.VERSION
		ORDER  BY ITEM.ITEM_CD)

/*IF (rowlimit != null) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/'50'
/*END*/
