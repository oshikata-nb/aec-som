/*
 * 相殺入力：売掛残リスト表示用SQL(検索ボタン押下時：新規)
 *
 * entityName=OffsetPayable
 * packageName=payment.offset
 * methodName=getPayableNew
 *
 */
SELECT DPS_HDR.DEPOSIT_NO
,      DPS_HDR.ORGANIZATION_CD
,      DPS_HDR.VENDER_CD CUSTOMER_CD
,      VND.VENDER_NAME1 AS CUSTOMER_NAME
,      DPS_HDR.CREDIT_DATE
,      DPS_HDR.OFFSET_AMOUNT
,      DPS_HDR.CREDIT_AMOUNT
,      SUM(DPS_HDR.CREDIT_AMOUNT)
         OVER(ORDER BY DPS_HDR.VENDER_CD)
           AS TOTAL_CREDIT_AMOUNT

FROM (	SELECT DPH.DEPOSIT_NO
		,      DPH.ORGANIZATION_CD
		,      DPH.VENDER_CD
		,      DPH.CREDIT_DATE
		,      DPH.OFFSET_AMOUNT
/*IF creditFlg == 10 */
		,      DPH.CREDIT_SALES_BREAKDOWN AS CREDIT_AMOUNT
/*END*/
/*IF creditFlg == 11 */
		,      DPH.ACCRUED_DEBIT_BREAKDOWN AS CREDIT_AMOUNT
/*END*/
		FROM DEPOSIT_HEADER DPH,
		     (	
		     	SELECT ORGANIZATION_CD
				,      VENDER_CD
				,      MAX(CREDIT_DATE) AS CREDIT_DATE
				FROM   DEPOSIT_HEADER
				GROUP BY ORGANIZATION_CD
				,        VENDER_CD 
		     ) DPH_MAX
		WHERE DPH.ORGANIZATION_CD  = DPH_MAX.ORGANIZATION_CD
		AND   DPH.VENDER_CD = DPH_MAX.VENDER_CD
		AND   DPH.CREDIT_DATE = DPH_MAX.CREDIT_DATE
/*IF creditFlg == 10 */
		AND   ( DPH.CREDIT_SALES_BREAKDOWN > 0  )
/*END*/
/*IF creditFlg == 11 */
		AND   ( DPH.ACCRUED_DEBIT_BREAKDOWN > 0  )
/*END*/
	 ) DPS_HDR
	 LEFT JOIN VENDER VND
     ON   VND.VENDER_DIVISION = 'TS'
	 AND  DPS_HDR.VENDER_CD = VND.VENDER_CD
       
WHERE DPS_HDR.DEPOSIT_NO IS NOT NULL
  AND DPS_HDR.VENDER_CD IN  (	SELECT VENDER_CD
								FROM   OFFSET_GROUP
								WHERE  OFFSET_GROUP_CD = /*offsetGroupCd*/'%'
							  )
ORDER BY DPS_HDR.VENDER_CD
