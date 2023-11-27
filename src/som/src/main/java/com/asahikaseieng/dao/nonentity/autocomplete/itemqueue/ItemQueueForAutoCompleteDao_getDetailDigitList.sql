/*
 * 品目マスタ詳細画面用-小数点桁数・端数区分付きSQL
 * 数値桁数マスタを品目マスタキューの単位コードで代表設定を取得する。
 * entityName=ItemQueueListForAutoComplete
 * packageName=itemqueue
 * methodName=getDetailDigitList
 *
*/

SELECT *
FROM   (SELECT ITEM.ITEM_CD                      AS ITEM_CD
			  ,ITEM.ITEM_NAME                    AS ITEM_NAME
			  ,ITEM.OTHER_COMPANY_CD1            AS OTHER_COMPANY_CD1
			  ,ITEM.STYLE_OF_PACKING             AS STYLE_OF_PACKING
			  ,ITEM.UNIT_OF_OPERATION_MANAGEMENT AS UNIT_OF_OPERATION_MANAGEMENT
			  ,NAME.NAME01                       AS NAME01
			  ,DISI.SMALLNUM_LENGTH              AS SMALLNUM_LENGTH
			  ,DISI.ROUND_DIVISION               AS ROUND_DIVISION
		FROM   NAMES NAME, NUMBER_CHKDISIT DISI, VALID_ITEM_QUEUE ITEM
		WHERE  (ITEM.ITEM_CD LIKE /*itemCd*/'%' OR		UTL_I18N.TRANSLITERATE(TO_MULTI_BYTE(item_name),'kana_fwkatakana') like FUN_RET_ITEM_STRING_USE_AC( /*itemCd*/'' ))
		AND    NAME.NAME_DIVISION(+) = 'UNIT'
		AND    NAME.NAME_CD(+) = ITEM.UNIT_OF_OPERATION_MANAGEMENT
		AND    DISI.UNIT_DIVISION(+) = ITEM.UNIT_OF_OPERATION_MANAGEMENT
		AND    DISI.VENDER_DIVISION(+) = ' '
		AND    DISI.VENDER_CD(+) = ' '
		
		ORDER  BY ITEM.ITEM_CD)

/*IF (rowlimit != null) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/'50'
/*END*/
