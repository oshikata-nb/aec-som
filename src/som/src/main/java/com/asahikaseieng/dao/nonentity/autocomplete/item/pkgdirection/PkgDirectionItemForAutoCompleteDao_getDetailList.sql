/*
 * 包装指図－品目オートコンプリート詳細画面用SQL
 *
 * entityName=PkgDirectionItemListForAutoComplete
 * packageName=pkgdirection
 * methodName=getDetailList
 *
*/

SELECT *
FROM   (SELECT ITEM.ITEM_CD                      AS ITEM_CD
			  ,ITEM.ITEM_NAME                    AS ITEM_NAME
			  ,ITEM.OTHER_COMPANY_CD1            AS OTHER_COMPANY_CD1
			  ,ITEM.STYLE_OF_PACKING             AS STYLE_OF_PACKING
			  ,ITEM.UNIT_OF_OPERATION_MANAGEMENT AS UNIT_OF_OPERATION_MANAGEMENT
			  ,NAME.NAME01                       AS NAME01
		FROM   NAMES NAME, ITEM
		WHERE
			(ITEM.ITEM_CD LIKE /*itemCd*/'%' OR		UTL_I18N.TRANSLITERATE(TO_MULTI_BYTE(item_name),'kana_fwkatakana') like FUN_RET_ITEM_STRING_USE_AC( /*itemCd*/'' ))
		AND    (ITEM.PRODUCT_DIVISION <> 0 -- 製造品に該当するもの

/*IF (repack != null) && (repack != "")*/
			  OR ITEM.TYPE_DIVISION = 5 OR ITEM.TYPE_DIVISION = 6 OR
			  ITEM.TYPE_DIVISION = 7
/*END*/
			  )
		AND    ITEM.UNIT_OF_OPERATION_MANAGEMENT = NAME.NAME_CD(+)
		AND    NAME.NAME_DIVISION(+) = 'UNIT'
		ORDER  BY ITEM.ITEM_CD)

/*IF (rowlimit != null) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/'50'
/*END*/
