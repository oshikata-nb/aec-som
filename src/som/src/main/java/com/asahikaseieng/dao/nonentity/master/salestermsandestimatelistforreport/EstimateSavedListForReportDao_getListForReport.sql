/*
 * 見積単価の保存テーブルのデータを取得
 *
 * entityName=EstimateSavedListForReport
 * packageName=EstimateSavedListForReport
 * methodName=getListForReport
 *
 */
SELECT
	ESTIMATE_SAVED.PK_NO
,	ESTIMATE_SAVED.PROC_TYPE
,	ESTIMATE_SAVED.ESTIMATE_NO
,	ESTIMATE_SAVED.ESTIMATE_INPUT_DATE
,	ESTIMATE_SAVED.BALANCE_CD
,	ESTIMATE_SAVED.VENDER_CD
,	CONCAT(VENDER.VENDER_NAME1,VENDER.VENDER_NAME2) AS VENDER_NAME
,	ESTIMATE_SAVED.ITEM_CD
,	ITEM.ITEM_NAME
,	ESTIMATE_SAVED.CONSECUTIVE_NO
,	ESTIMATE_SAVED.STANDARD_UNIT_PRICE
,	ESTIMATE_SAVED.STANDARD_DISCOUNT
,	ESTIMATE_SAVED.SPECIAL_DISCOUNT
,	ESTIMATE_SAVED.UNITPRICE
,	ESTIMATE_SAVED.STANDARD_AMOUNT
,	ESTIMATE_SAVED.MATSS
,	ESTIMATE_SAVED.ESTIMATE_VALID_DATE_FROM
,	ESTIMATE_SAVED.ESTIMATE_VALID_DATE_TO
,	ESTIMATE_SAVED.REMARK
,	ESTIMATE_SAVED.INPUT_DATE
,	ESTIMATE_SAVED.INPUTOR_CD
,	INPUTOR.TANTO_NM AS INPUTOR_NAME
,	ESTIMATE_SAVED.UPDATE_DATE
,	ESTIMATE_SAVED.UPDATOR_CD
,	UPDATOR.TANTO_NM AS UPDATOR_NAME

,	ESTIMATE_SAVED.ESTIMATE_STATUS
FROM
	ESTIMATE_SAVED
LEFT JOIN VENDER ON VENDER.VENDER_DIVISION = 'TS' AND VENDER.VENDER_CD = ESTIMATE_SAVED.VENDER_CD
LEFT JOIN ITEM ON ITEM.ITEM_CD = ESTIMATE_SAVED.ITEM_CD
LEFT JOIN LOGIN UPDATOR ON UPDATOR.TANTO_CD = ESTIMATE_SAVED.UPDATOR_CD
LEFT JOIN LOGIN INPUTOR ON INPUTOR.TANTO_CD = ESTIMATE_SAVED.INPUTOR_CD

WHERE
	ESTIMATE_SAVED.PK_NO = /*pkNo*/''
