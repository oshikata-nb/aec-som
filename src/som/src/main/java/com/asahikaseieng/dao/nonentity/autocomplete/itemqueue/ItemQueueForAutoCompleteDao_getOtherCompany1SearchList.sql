/*
 * 品目マスタ検索一覧用（他社コード１で検索）SQL
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
			  ,ITEM.UNIT_OF_OPERATION_MANAGEMENT AS UNIT_OF_OPERATION_MANAGEMENT
		FROM   VALID_ITEM_QUEUE ITEM
		WHERE  ITEM.OTHER_COMPANY_CD1 LIKE /*otherCompanyCd1*/'%'
		ORDER  BY ITEM.OTHER_COMPANY_CD1, ITEM.ITEM_CD)

/*IF (rowlimit != null) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/'50'
/*END*/
