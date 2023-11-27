/*
 * 品目マスタ検索一覧用（他社コード１で検索）SQL
 *
 * entityName=ItemListForAutoComplete
 * packageName=item
 * methodName=getOtherCompany1SearchList
 *
*/

SELECT *
FROM   (SELECT ITEM.ITEM_CD                      AS ITEM_CD
			  ,ITEM.ITEM_NAME                    AS ITEM_NAME
			  ,ITEM.OTHER_COMPANY_CD1            AS OTHER_COMPANY_CD1
			  ,ITEM.UNIT_OF_OPERATION_MANAGEMENT AS UNIT_OF_OPERATION_MANAGEMENT
		FROM   ITEM
			  ,(SELECT ITEM_CD, MAX(VERSION) VERSION
				FROM   ITEM
				WHERE  OTHER_COMPANY_CD1 LIKE /*otherCompany1*/'%'
				GROUP  BY ITEM_CD
				
				) MITM
		WHERE  ITEM.ITEM_CD = MITM.ITEM_CD
		AND    ITEM.VERSION = MITM.VERSION
		ORDER  BY ITEM.OTHER_COMPANY_CD1, ITEM.ITEM_CD)

/*IF (rowlimit != null) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/'50'
/*END*/
