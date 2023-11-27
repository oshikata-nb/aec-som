/*
 * 品目マスタ検索詳細画面用（他社コード１で検索）SQL
 *
 * entityName=ItemQueueListForAutoComplete
 * packageName=itemqueue
 * methodName=getOtherCompany1SearchList
 *
*/

SELECT *
FROM   (SELECT ITEM.ITEM_CD                      AS ITEM_CD
			  ,ITEM.ITEM_NAME                    AS ITEM_NAME
			  ,ITEM.OTHER_COMPANY_CD1            AS OTHER_COMPANY_CD1
			  ,ITEM.STYLE_OF_PACKING             AS STYLE_OF_PACKING
			  ,ITEM.UNIT_OF_OPERATION_MANAGEMENT AS UNIT_OF_OPERATION_MANAGEMENT
			  ,NAME.NAME01                       AS NAME01
			  ,LOT_DIVISION
		FROM   NAMES NAME, VALID_ITEM_QUEUE ITEM
		WHERE  ITEM.OTHER_COMPANY_CD1 LIKE /*otherCompanyCd1*/'%'
		AND    NAME.NAME_DIVISION(+) = 'UNIT'
		AND    NAME.NAME_CD(+) = ITEM.UNIT_OF_OPERATION_MANAGEMENT
		ORDER  BY ITEM.OTHER_COMPANY_CD1, ITEM.ITEM_CD)

/*IF (rowlimit != null) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/'50'
/*END*/
