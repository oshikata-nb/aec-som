/*
 * 包装指図－品目オートコンプリート検索画面用（他社コード１で検索）SQL
 *
 * entityName=PkgDirectionItemListForAutoComplete
 * packageName=pkgdirection
 * methodName=getOtherCompany1SearchList
 *
*/

SELECT *
FROM   (SELECT ITEM.ITEM_CD                      AS ITEM_CD
			  ,ITEM.ITEM_NAME                    AS ITEM_NAME
			  ,ITEM.OTHER_COMPANY_CD1            AS OTHER_COMPANY_CD1
			  ,ITEM.UNIT_OF_OPERATION_MANAGEMENT AS UNIT_OF_OPERATION_MANAGEMENT
		FROM   ITEM
		WHERE  ITEM.OTHER_COMPANY_CD1 LIKE /*otherCompany1*/'%'
		AND    ITEM.PRODUCT_DIVISION <> 0 -- 製造品に該当するもの
		ORDER  BY ITEM.OTHER_COMPANY_CD1, ITEM.ITEM_CD)

/*IF (rowlimit != null) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/'50'
/*END*/
