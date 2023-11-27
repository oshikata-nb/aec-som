/*
 * 相殺入力：売掛残リスト表示用SQL(リンク遷移時：変更)
 *
 * entityName=OffsetDeposit
 * packageName=payment.offset
 * methodName=getDeposit
 *
 */
SELECT DPH.DEPOSIT_NO
,      DPH.ORGANIZATION_CD
,      DPH.VENDER_CD CUSTOMER_CD
,      VND.VENDER_NAME1 AS CUSTOMER_NAME
,      DPH.CREDIT_DATE
,      DPH.OFFSET_AMOUNT
/*IF (creditFlg == 10)*/
,      DPH.CREDIT_SALES_BREAKDOWN AS CREDIT_AMOUNT
/*END*/
/*IF (creditFlg == 11)*/
,      DPH.ACCRUED_DEBIT_BREAKDOWN AS CREDIT_AMOUNT
/*END*/
FROM DEPOSIT_HEADER DPH
     LEFT JOIN VENDER VND
     ON   VND.VENDER_DIVISION = 'TS'
     AND  DPH.VENDER_CD = VND.VENDER_CD
     ,
     (SELECT ORGANIZATION_CD
      ,      VENDER_CD
      ,      MAX(CREDIT_DATE) AS CREDIT_DATE
      FROM   DEPOSIT_HEADER
      WHERE    VENDER_CD = /*venderCd*/'%'
/*IF ((depositDate != null ) && (depositDate != "" ))*/
      AND    CREDIT_DATE <= /*depositDate*/'2009/06/19'
/*END*/
      GROUP BY ORGANIZATION_CD
      ,        VENDER_CD
     ) DPH_MAX
WHERE DPH.ORGANIZATION_CD  = DPH_MAX.ORGANIZATION_CD
AND   DPH.VENDER_CD = DPH_MAX.VENDER_CD
AND   DPH.CREDIT_DATE = DPH_MAX.CREDIT_DATE
/*IF (creditFlg == 10)*/
      AND    ( DPH.CREDIT_SALES_BREAKDOWN > 0 OR DPH.OFFSET_AMOUNT > 0 )
/*END*/
/*IF (creditFlg == 11)*/
      AND    ( DPH.ACCRUED_DEBIT_BREAKDOWN > 0 OR DPH.OFFSET_AMOUNT > 0 )
/*END*/
      
ORDER BY DPH.VENDER_CD
