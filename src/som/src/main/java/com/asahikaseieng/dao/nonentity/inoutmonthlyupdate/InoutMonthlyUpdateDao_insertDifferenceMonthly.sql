/*
 * 月次更新未作成分作成用SQL
 *
 * entityName=InoutMonthlyUpdate
 * packageName=inoutmonthlyupdate
 * methodName=insertMonthly
 *
 */

INSERT INTO MONTHLY_INOUT_RECORD
(LOCATION_CD
, ITEM_CD
, LOT_NO
, TARGET_MONTH
, LM_INVENTORY_QTY
, LM_INVENTORY_COST
, LM_INVENTORY_AMOUNT
, IN_QTY
, IN_AMOUNT
, OUT_QTY
, OUT_AMOUNT
, NM_INVENTORY_QTY
, NM_INVENTORY_COST
, NM_INVENTORY_AMOUNT
, INPUT_DATE
, INPUTOR_CD
, UPDATE_DATE
, UPDATOR_CD
)

SELECT MONTHLY_RECORD.LOCATION_CD
, MONTHLY_RECORD.ITEM_CD
, MONTHLY_RECORD.LOT_NO
, /*inputDate*/200910 TARGET_MONTH
, MONTHLY_RECORD.NM_INVENTORY_QTY
, 0 NM_INVENTORY_COST
, 0 NM_INVENTORY_AMOUNT
, 0 IN_QTY
, 0 IN_AMOUNT
, 0 OUT_QTY
, 0 OUT_AMOUNT
, MONTHLY_RECORD.NM_INVENTORY_QTY
, 0 NM_INVENTORY_COST
, 0 NM_INVENTORY_AMOUNT
, SYSDATE INPUT_DATE
, /*tantoCd*/'%' INPUTOR_CD
, SYSDATE UPDATE_DATE
, /*tantoCd*/'%' UPDATOR_CD

FROM

(SELECT PREV_MONTH.LOCATION_CD
, PREV_MONTH.ITEM_CD
, PREV_MONTH.LOT_NO
, MAX(PREV_MONTH.TARGET_MONTH) PREV_MONTH
, TARGET_MONTH.TARGET_MONTH
FROM

(SELECT *
FROM MONTHLY_INOUT_RECORD
WHERE TARGET_MONTH = /*inputDate*/200910) TARGET_MONTH

,(SELECT *
FROM MONTHLY_INOUT_RECORD
WHERE TARGET_MONTH < /*inputDate*/200910) PREV_MONTH

WHERE TARGET_MONTH.LOCATION_CD(+) = PREV_MONTH.LOCATION_CD
AND TARGET_MONTH.ITEM_CD(+) = PREV_MONTH.ITEM_CD
AND TARGET_MONTH.LOT_NO(+) = PREV_MONTH.LOT_NO
GROUP BY PREV_MONTH.LOCATION_CD
, PREV_MONTH.ITEM_CD
, PREV_MONTH.LOT_NO
, TARGET_MONTH.TARGET_MONTH) TARGET_RECORD

, MONTHLY_INOUT_RECORD MONTHLY_RECORD

WHERE TARGET_RECORD.TARGET_MONTH IS NULL
AND MONTHLY_RECORD.NM_INVENTORY_QTY <> 0
AND MONTHLY_RECORD.LOCATION_CD = TARGET_RECORD.LOCATION_CD
AND MONTHLY_RECORD.ITEM_CD = TARGET_RECORD.ITEM_CD
AND MONTHLY_RECORD.LOT_NO = TARGET_RECORD.LOT_NO
AND MONTHLY_RECORD.TARGET_MONTH = TARGET_RECORD.PREV_MONTH