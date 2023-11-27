/*
 * 品目マスタ検索詳細画面用-小数点桁数・端数区分付き（他社コード１で検索）SQL
 *
 * entityName=ItemQueueListForAutoComplete
 * pakageName=itemqueue
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
			  ,DISI.SMALLNUM_LENGTH              AS SMALLNUM_LENGTH
			  ,DISI.ROUND_DIVISION               AS ROUND_DIVISION
		FROM   NAMES NAME, NUMBER_CHKDISIT DISI, VALID_ITEM_QUEUE ITEM
		WHERE  ITEM.OTHER_COMPANY_CD1 LIKE /*otherCompanyCd1*/'%'
		AND    NAME.NAME_DIVISION(+) = 'UNIT'
		AND    NAME.NAME_CD(+) = ITEM.UNIT_OF_OPERATION_MANAGEMENT
		AND    DISI.UNIT_DIVISION(+) = ITEM.UNIT_OF_OPERATION_MANAGEMENT
		AND    DISI.VENDER_DIVISION(+) = ' '
		AND    DISI.VENDER_CD(+) = ' '
		ORDER  BY ITEM.OTHER_COMPANY_CD1, ITEM.ITEM_CD)

/*IF (rowlimit != NULL) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/'50'
/*END*/
