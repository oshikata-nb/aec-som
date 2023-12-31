/*
 * 相殺入力：買掛残リスト表示用SQL(リンク遷移時：変更)
 *
 * entityName=OffsetPayable
 * packageName=payment.offset
 * methodName=getPayable
 *
 */
SELECT PYH.PAYABLE_NO
,      PYH.ORGANIZATION_CD
,      PYH.SUPPLIER_CD
,      VND.VENDER_NAME1 AS SUPPLIER_NAME
,      PYH.PAYABLE_DATE
,      PYH.OFFSET_AMOUNT
/*IF payableFlg == 1 */
,      PYH.ACCOUNT_PAYABLE_BREAKDOWN AS PAYABLE_AMOUNT
/*END*/
/*IF payableFlg == 2 */
,      PYH.ARREARAGE_BREAKDOWN AS PAYABLE_AMOUNT
/*END*/
FROM PAYABLE_HEADER PYH
     LEFT JOIN VENDER VND
     ON   VND.VENDER_DIVISION = 'SI'
     AND  PYH.SUPPLIER_CD = VND.VENDER_CD
     ,
     (SELECT ORGANIZATION_CD
      ,      SUPPLIER_CD
	  ,      MAX(PAYABLE_DATE) AS PAYABLE_DATE
	  FROM   PAYABLE_HEADER
      WHERE    SUPPLIER_CD = /*venderCd*/'%'
/*IF ((payableDate != null ) && (payableDate != "" ))*/
      AND    PAYABLE_DATE <= /*payableDate*/'2009/06/19'
/*END*/
	  GROUP BY ORGANIZATION_CD
      ,        SUPPLIER_CD
     ) PYH_MAX
WHERE PYH.ORGANIZATION_CD   = PYH_MAX.ORGANIZATION_CD
AND   PYH.SUPPLIER_CD  = PYH_MAX.SUPPLIER_CD
AND   PYH.PAYABLE_DATE = PYH_MAX.PAYABLE_DATE
/*IF payableFlg == 1 */
	  AND    ( PYH.ACCOUNT_PAYABLE_BREAKDOWN > 0  OR PYH.OFFSET_AMOUNT > 0 )
/*END*/
/*IF payableFlg == 2 */
	  AND    ( PYH.ARREARAGE_BREAKDOWN > 0  OR PYH.OFFSET_AMOUNT > 0 )
/*END*/

ORDER BY PYH.SUPPLIER_CD
