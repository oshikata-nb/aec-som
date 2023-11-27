/*
 * 見積/単価マスタ詳細一覧用SQL
 *
 * entityName=EstimateDetailList
 * packageName=estimatedetaillist
 * methodName=getList
 *
 */

SELECT ESTIMATE_NO, CONSECUTIVE_NO, ESTIMATE_STATUS, ESTIMATE_INPUT_DATE, ESTIMATE.BALANCE_CD, BALANCE.VENDER_CD, VENDER_NAME1, ESTIMATE.ITEM_CD, OTHER_COMPANY_CD1, ITEM_NAME, STYLE_OF_PACKING, UNIT_OF_OPERATION_MANAGEMENT, STANDARD_UNIT_PRICE, STANDARD_DISCOUNT, SPECIAL_DISCOUNT, UNITPRICE, STANDARD_AMOUNT, MATSS, ESTIMATE_VALID_DATE_FROM, ESTIMATE_VALID_DATE_TO, REMARK, UNIT_OF_STOCK_CONTROL,

CASE ESTIMATE_STATUS
	WHEN 1 THEN '見積登録'
	WHEN 2 THEN '見積承認依頼中'
	WHEN 3 THEN '見積承認済'
	ELSE NULL
END ESTIMATE_STATUS_NAME,

'URTANKA' UNIT_DIVISION, ESTIMATE.UPDATE_DATE, SMALLNUM_LENGTH, ROUND_DIVISION
FROM ESTIMATE, BALANCE, VENDER, ITEM, NUMBER_CHKDISIT,

(SELECT ITEM_CD, MAX(VERSION) VERSION
FROM ITEM
GROUP BY ITEM_CD) MAX_ITEM

WHERE ESTIMATE.ESTIMATE_NO = /*estimateNo*/'%'
AND ESTIMATE.BALANCE_CD = BALANCE.BALANCE_CD(+)
AND VENDER.VENDER_DIVISION(+) = 'TS'
AND BALANCE.VENDER_CD = VENDER.VENDER_CD(+)
AND ESTIMATE.ITEM_CD = MAX_ITEM.ITEM_CD(+)
AND MAX_ITEM.ITEM_CD = ITEM.ITEM_CD(+)
AND MAX_ITEM.VERSION = ITEM.VERSION(+)
AND NUMBER_CHKDISIT.UNIT_DIVISION(+) = 'URTANKA'
AND NUMBER_CHKDISIT.VENDER_DIVISION(+) = ' '
AND NUMBER_CHKDISIT.VENDER_CD(+) = ' '
ORDER BY ITEM_CD