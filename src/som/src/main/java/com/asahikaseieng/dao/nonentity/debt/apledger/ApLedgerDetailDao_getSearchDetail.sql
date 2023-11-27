/*
 * 買掛元帳詳細（上段）用SQL
 *
 * entityName=ApBalanceDetail
 * packageName=debt.apledger
 * methodName=getSearchDetail
 *
 */
/*IF (targetKbn == "0" || targetKbn == "2")*/
SELECT PYH_HED.PAYABLE_NO                --買掛番号
,      PYH_HED.ORGANIZATION_CD           --部署コード
,      ORGANIZATION.ORGANIZATION_NAME    --部署名称
,      PYH_HED.SUPPLIER_CD               --仕入先コード
,      VENDER.VENDER_NAME1               --仕入先名称
,      VENDER.VENDER_SHORTED_NAME        --仕入先略称
,      PYH_HED.BALANCE_FORWARD           --前月買掛残
,      PYH_HED.STOCKING_AMOUNT           --仕入金額
,      PYH_HED.PAYMENT_AMOUNT            --支払金額
,      PYH_HED.OTHER_PAYMENT_AMOUNT      --その他支払金額
,      PYH_HED.STOCKING_RETURNED_AMOUNT  --返品金額
,      PYH_HED.STOCKING_DISCOUNT_AMOUNT  --値引金額
,      PYH_HED.OTHER_STOCKING_AMOUNT     --その他仕入金額
,      PYH_HED.OFFSET_AMOUNT             --相殺金額
,      PYH_HED.TAX_AMOUNT                --消費税額
,      PYH_HED.PAYABLE_AMOUNT            --買掛残
,      PYH_HED.ACCOUNT_PAYABLE_BREAKDOWN --買掛金(内訳)
,      PYH_HED.ARREARAGE_BREAKDOWN       --未払金(内訳)
,      PYH_HED.EXCEPT_BREAKDOWN          --以外(内訳)
FROM   PAYABLE_HEADER PYH_HED
	   LEFT JOIN ORGANIZATION ORGANIZATION
	   ON PYH_HED.ORGANIZATION_CD = ORGANIZATION.ORGANIZATION_CD
	   LEFT JOIN VENDER VENDER
	   ON   VENDER.VENDER_DIVISION = 'SI'
	   AND  PYH_HED.SUPPLIER_CD = VENDER.VENDER_CD
WHERE  PYH_HED.ORGANIZATION_CD IS NOT NULL

AND	PYH_HED.PAYABLE_NO = /*payableNo*/'%'
/*END*/

/*IF (targetKbn == "2")*/
	UNION
/*END*/

/*IF (targetKbn == "1" || targetKbn == "2")*/
SELECT TMP_PYH_HED.PAYABLE_NO                --買掛番号
,      TMP_PYH_HED.ORGANIZATION_CD           --部署コード
,      ORGANIZATION.ORGANIZATION_NAME    --部署名称
,      TMP_PYH_HED.SUPPLIER_CD               --仕入先コード
,      VENDER.VENDER_NAME1               --仕入先名称
,      TMP_PYH_HED.BALANCE_FORWARD           --前月買掛残
,      TMP_PYH_HED.STOCKING_AMOUNT           --仕入金額
,      TMP_PYH_HED.PAYMENT_AMOUNT            --支払金額
,      TMP_PYH_HED.OTHER_PAYMENT_AMOUNT      --その他支払金額
,      TMP_PYH_HED.STOCKING_RETURNED_AMOUNT  --返品金額
,      TMP_PYH_HED.STOCKING_DISCOUNT_AMOUNT  --値引金額
,      TMP_PYH_HED.OTHER_STOCKING_AMOUNT     --その他仕入金額
,      TMP_PYH_HED.OFFSET_AMOUNT             --相殺金額
,      TMP_PYH_HED.TAX_AMOUNT                --消費税額
,      TMP_PYH_HED.PAYABLE_AMOUNT            --買掛残
,      TMP_PYH_HED.ACCOUNT_PAYABLE_BREAKDOWN --買掛金(内訳)
,      TMP_PYH_HED.ARREARAGE_BREAKDOWN       --未払金(内訳)
,      TMP_PYH_HED.EXCEPT_BREAKDOWN          --以外(内訳)
FROM   TEMPORARY_PAYABLE_HEADER TMP_PYH_HED
	   LEFT JOIN ORGANIZATION ORGANIZATION
	   ON TMP_PYH_HED.ORGANIZATION_CD = ORGANIZATION.ORGANIZATION_CD
	   LEFT JOIN VENDER VENDER
	   ON   VENDER.VENDER_DIVISION = 'SI'
	   AND  TMP_PYH_HED.SUPPLIER_CD = VENDER.VENDER_CD
WHERE  TMP_PYH_HED.ORGANIZATION_CD IS NOT NULL

AND	TMP_PYH_HED.PAYABLE_NO = /*payableNo*/'%'
/*END*/
