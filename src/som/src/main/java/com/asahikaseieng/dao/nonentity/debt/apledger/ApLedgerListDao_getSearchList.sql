/*
 * 買掛元帳用SQL
 *
 * entityName=ApBalanceList
 * packageName=debt.apledger
 * methodName=getSearchList
 *
 */
/*IF (condition.srhNormalFlg)*/
SELECT PYH_HED.PAYABLE_NO                --買掛番号
,      PYH_HED.ORGANIZATION_CD           --部署コード
,      ORGANIZATION.ORGANIZATION_NAME    --部署名称
,      PYH_HED.SUPPLIER_CD               --仕入先コード
,      VENDER.VENDER_NAME1               --仕入先名称
,      VENDER.VENDER_SHORTED_NAME        --仕入先名称略称
,      PYH_HED.BALANCE_FORWARD           --前月買掛残
,      PYH_HED.STOCKING_AMOUNT           --仕入金額
,      PYH_HED.PAYMENT_AMOUNT            --支払金額
,      PYH_HED.OTHER_PAYMENT_AMOUNT      --その他支払金額
,      PYH_HED.STOCKING_RETURNED_AMOUNT  --返品金額
,      PYH_HED.STOCKING_DISCOUNT_AMOUNT  --値引金額
,      PYH_HED.OTHER_STOCKING_AMOUNT     --その他仕入金額
,      PYH_HED.OFFSET_AMOUNT             --相殺金額
,      PYH_HED.TAX_AMOUNT                --消費税額
,      PYH_HED.ACCOUNT_PAYABLE_BREAKDOWN --買掛金(内訳)
,      PYH_HED.ARREARAGE_BREAKDOWN		 --未払金(内訳)
,      PYH_HED.PAYABLE_AMOUNT            --買掛残
,      (PYH_HED.PAYMENT_AMOUNT
        +PYH_HED.OTHER_PAYMENT_AMOUNT) AS OTHER_PAYMENT
	                                  --画面用.支払・その他計
,      ((PYH_HED.OTHER_STOCKING_AMOUNT + PYH_HED.OFFSET_AMOUNT)
         - (PYH_HED.STOCKING_RETURNED_AMOUNT + PYH_HED.STOCKING_DISCOUNT_AMOUNT)
       ) AS OTHER_STOCKING
       --画面用.その他計((その他仕入金額+相殺金額)-(返品金額+値引金額))
FROM   PAYABLE_HEADER PYH_HED
	   LEFT JOIN ORGANIZATION ORGANIZATION
	   ON PYH_HED.ORGANIZATION_CD = ORGANIZATION.ORGANIZATION_CD
	   LEFT JOIN LOGIN LOGIN
	   ON PYH_HED.INPUTOR_CD = LOGIN.TANTO_CD
	   LEFT JOIN VENDER VENDER
	   ON   VENDER.VENDER_DIVISION = 'SI'
	   AND  PYH_HED.SUPPLIER_CD = VENDER.VENDER_CD
WHERE  PYH_HED.ORGANIZATION_CD IS NOT NULL
AND  TO_CHAR(PYH_HED.PAYABLE_DATE,'YYYY/MM') = /*condition.srhTargetMonth*/'2009/07'

/*IF (( condition.srhOrganizationCd != null ) && ( condition.srhOrganizationCd != "" ))*/
	AND	(PYH_HED.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/'%' OR ORGANIZATION_NAME LIKE /*condition.srhOrganizationCd*/'%')
/*END*/

/*IF (( condition.srhTantoCd != null ) && ( condition.srhTantoCd != "" ))*/
	AND	(PYH_HED.INPUTOR_CD LIKE /*condition.srhTantoCd*/'%' OR LOGIN.TANTO_NM LIKE /*condition.srhTantoCd*/'%')
/*END*/

/*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
	AND	(PYH_HED.SUPPLIER_CD LIKE /*condition.srhVenderCd*/'%' OR VENDER.VENDER_NAME1 LIKE FUN_RET_MASTER_STRING_USE_AC(/*condition.srhVenderCd*/'%'))
/*END*/

AND (BALANCE_FORWARD <> 0
OR STOCKING_AMOUNT <> 0
OR PAYMENT_AMOUNT <> 0
OR OTHER_PAYMENT_AMOUNT <> 0
OR STOCKING_RETURNED_AMOUNT <> 0
OR STOCKING_DISCOUNT_AMOUNT <> 0
OR OTHER_STOCKING_AMOUNT <> 0
OR OFFSET_AMOUNT <> 0
OR TAX_AMOUNT <> 0
OR ACCOUNT_PAYABLE_BREAKDOWN <> 0
OR ARREARAGE_BREAKDOWN <> 0
OR PAYABLE_AMOUNT <> 0)

/*IF (!condition.srhTempClosingFlg)*/
ORDER BY PYH_HED.SUPPLIER_CD
/*END*/
/*END*/

/*IF (condition.srhNormalFlg && condition.srhTempClosingFlg)*/
	UNION
/*END*/

/*IF (condition.srhTempClosingFlg)*/
SELECT TMP_PYH_HED.PAYABLE_NO                --買掛番号
,      TMP_PYH_HED.ORGANIZATION_CD           --部署コード
,      ORGANIZATION.ORGANIZATION_NAME    --部署名称
,      TMP_PYH_HED.SUPPLIER_CD               --仕入先コード
,      VENDER.VENDER_NAME1               --仕入先名称
,      VENDER.VENDER_SHORTED_NAME        --仕入先名称略称
,      TMP_PYH_HED.BALANCE_FORWARD           --前月買掛残
,      TMP_PYH_HED.STOCKING_AMOUNT           --仕入金額
,      TMP_PYH_HED.PAYMENT_AMOUNT            --支払金額
,      TMP_PYH_HED.OTHER_PAYMENT_AMOUNT      --その他支払金額
,      TMP_PYH_HED.STOCKING_RETURNED_AMOUNT  --返品金額
,      TMP_PYH_HED.STOCKING_DISCOUNT_AMOUNT  --値引金額
,      TMP_PYH_HED.OTHER_STOCKING_AMOUNT     --その他仕入金額
,      TMP_PYH_HED.OFFSET_AMOUNT             --相殺金額
,      TMP_PYH_HED.TAX_AMOUNT                --消費税額
,      TMP_PYH_HED.ACCOUNT_PAYABLE_BREAKDOWN --買掛金(内訳)
,      TMP_PYH_HED.ARREARAGE_BREAKDOWN		 --未払金(内訳)
,      TMP_PYH_HED.PAYABLE_AMOUNT            --買掛残
,      (TMP_PYH_HED.PAYMENT_AMOUNT
        +TMP_PYH_HED.OTHER_PAYMENT_AMOUNT) AS OTHER_PAYMENT
	                                  --画面用.支払・その他計
,      ((TMP_PYH_HED.OTHER_STOCKING_AMOUNT + TMP_PYH_HED.OFFSET_AMOUNT)
         - (TMP_PYH_HED.STOCKING_RETURNED_AMOUNT + TMP_PYH_HED.STOCKING_DISCOUNT_AMOUNT)
       ) AS OTHER_STOCKING
       --画面用.その他計((その他仕入金額+相殺金額)-(返品金額+値引金額))
FROM   TEMPORARY_PAYABLE_HEADER TMP_PYH_HED
	   LEFT JOIN ORGANIZATION ORGANIZATION
	   ON TMP_PYH_HED.ORGANIZATION_CD = ORGANIZATION.ORGANIZATION_CD
	   LEFT JOIN LOGIN LOGIN
	   ON TMP_PYH_HED.INPUTOR_CD = LOGIN.TANTO_CD
	   LEFT JOIN VENDER VENDER
	   ON   VENDER.VENDER_DIVISION = 'SI'
	   AND  TMP_PYH_HED.SUPPLIER_CD = VENDER.VENDER_CD
WHERE  TMP_PYH_HED.ORGANIZATION_CD IS NOT NULL
AND  TO_CHAR(TMP_PYH_HED.PAYABLE_DATE,'YYYY/MM') = /*condition.srhTargetMonth*/'2009/07'

/*IF (( condition.srhOrganizationCd != null ) && ( condition.srhOrganizationCd != "" ))*/
	AND	(TMP_PYH_HED.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/'%' OR ORGANIZATION_NAME LIKE /*condition.srhOrganizationCd*/'%')
/*END*/

/*IF (( condition.srhTantoCd != null ) && ( condition.srhTantoCd != "" ))*/
	AND	(TMP_PYH_HED.INPUTOR_CD LIKE /*condition.srhTantoCd*/'%' OR LOGIN.TANTO_NM LIKE /*condition.srhTantoCd*/'%')
/*END*/

/*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
	AND	(TMP_PYH_HED.SUPPLIER_CD LIKE /*condition.srhVenderCd*/'%' OR VENDER.VENDER_NAME1 LIKE FUN_RET_MASTER_STRING_USE_AC(/*condition.srhVenderCd*/'%'))
/*END*/

AND (BALANCE_FORWARD <> 0
OR STOCKING_AMOUNT <> 0
OR PAYMENT_AMOUNT <> 0
OR OTHER_PAYMENT_AMOUNT <> 0
OR STOCKING_RETURNED_AMOUNT <> 0
OR STOCKING_DISCOUNT_AMOUNT <> 0
OR OTHER_STOCKING_AMOUNT <> 0
OR OFFSET_AMOUNT <> 0
OR TAX_AMOUNT <> 0
OR ACCOUNT_PAYABLE_BREAKDOWN <> 0
OR ARREARAGE_BREAKDOWN <> 0
OR PAYABLE_AMOUNT <> 0)

ORDER BY SUPPLIER_CD
/*END*/
