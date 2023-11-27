/*
 * 月次更新作成用SQL
 *
 * entityName=InoutMonthlyUpdate
 * packageName=inoutmonthlyupdate
 * methodName=insertMonthly
 *
 */

INSERT INTO MONTHLY_INOUT_RECORD MR
(
	LOCATION_CD
,	ITEM_CD
,	LOT_NO
,	TARGET_MONTH
,	LM_INVENTORY_QTY
,	LM_INVENTORY_COST
,	LM_INVENTORY_AMOUNT
,	IN_QTY
,	IN_AMOUNT
,	OUT_QTY
,	OUT_AMOUNT
,	NM_INVENTORY_QTY
,	NM_INVENTORY_COST		   
,	NM_INVENTORY_AMOUNT	
,	INPUT_DATE
,	INPUTOR_CD
,	UPDATE_DATE
,	UPDATOR_CD
)
SELECT IR.LOCATION_CD
,	IR.ITEM_CD
,	IR.LOT_NO
,	/*inputDate*/'200907'
,	MRL.NM_INVENTORY_QTY
,	MRL.NM_INVENTORY_COST
,	MRL.NM_INVENTORY_AMOUNT
,	IR.IN_QTY
,	IR.IN_COST
,	IR.OUT_QTY
,	IR.OUT_COST
,	(NVL(MRL.NM_INVENTORY_QTY,0) + NVL(IR.IN_QTY,0) + NVL(IR.OUT_QTY,0))
,	II.INVENTORY_COST
,	(NVL(MRL.NM_INVENTORY_COST,0) + NVL(IR.IN_COST,0) + NVL(IR.OUT_COST,0))
,	SYSDATE INPUT_DATE
,	/*tantoCd*/'%' INPUTOR_CD
,	SYSDATE UPPUT_DATE
,	/*tantoCd*/'%' UPDATOR_CD
FROM (SELECT IR.LOCATION_CD
			,IR.ITEM_CD
			,IR.LOT_NO
			,SUM(CASE WHEN IR.INOUT_QTY > 0 THEN IR.INOUT_QTY ELSE 0 END) IN_QTY		-- 入庫数
			,SUM(CASE WHEN IR.INOUT_QTY < 0 THEN IR.INOUT_QTY ELSE 0 END) OUT_QTY		-- 出庫数
			,SUM(CASE WHEN IR.INOUT_COST > 0 THEN IR.INOUT_COST ELSE 0 END) IN_COST		-- 入庫金額
			,SUM(CASE WHEN IR.INOUT_COST < 0 THEN IR.INOUT_COST ELSE 0 END) OUT_COST	-- 出庫金額
		FROM INOUT_RECORD IR
		WHERE TO_CHAR(IR.INOUT_DATE, 'YYYY/MM/DD') BETWEEN /*inputDateFrom*/'2009/07/01'
		AND /*inputDateTo*/'2009/07/31'
		GROUP BY IR.LOCATION_CD
				,IR.ITEM_CD
				,IR.LOT_NO
	) IR
	,(SELECT MRL.LOCATION_CD
			,MRL.ITEM_CD
			,MRL.LOT_NO
			,MRL.TARGET_MONTH
			,MRL.NM_INVENTORY_QTY
			,MRL.NM_INVENTORY_COST
			,MRL.NM_INVENTORY_AMOUNT
		FROM MONTHLY_INOUT_RECORD MRL
		WHERE MRL.TARGET_MONTH
			= (SELECT MAX(TARGET_MONTH)
				FROM MONTHLY_INOUT_RECORD
				WHERE TARGET_MONTH < /*inputDate*/'200907')
	) MRL
	,ITEM_INVENTORY II
WHERE MRL.LOCATION_CD (+) = IR.LOCATION_CD
AND MRL.ITEM_CD(+) = IR.ITEM_CD
AND MRL.LOT_NO(+) = IR.LOT_NO
AND II.ITEM_CD(+) = IR.ITEM_CD
ORDER BY IR.LOCATION_CD
		,IR.ITEM_CD
		,IR.LOT_NO