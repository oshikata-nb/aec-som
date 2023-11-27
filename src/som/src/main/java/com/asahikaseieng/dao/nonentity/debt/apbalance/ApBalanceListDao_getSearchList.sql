/*
 * 買掛残高一覧用SQL
 *
 * entityName=ArBalanceList
 * packageName=credit.arbalance
 * methodName=getSearchList
 *
 */
SELECT PYB_HED.PAYABLE_NO					--買掛番号
,      PYB_HED.ORGANIZATION_CD				--部署コード
,      ORGANIZATION.ORGANIZATION_NAME		--部署名称
,      PYB_HED.SUPPLIER_CD					--支払先コード
,      VENDER.VENDER_NAME1					--支払先名称
,      VENDER.VENDER_SHORTED_NAME			--支払先略称
,      PYB_HED.PAYABLE_DATE					--買掛締め日
,      PYB_HED.BALANCE_FORWARD				--前月繰越
,      PYB_HED.STOCKING_AMOUNT				--仕入金額
,      PYB_HED.PAYMENT_AMOUNT				--支払金額
,      PYB_HED.OTHER_PAYMENT_AMOUNT   		--その他支払金額
,      PYB_HED.STOCKING_RETURNED_AMOUNT		--返品金額
,      PYB_HED.STOCKING_DISCOUNT_AMOUNT		--値引金額
,      PYB_HED.OTHER_STOCKING_AMOUNT		--その他仕入金額
,      PYB_HED.OFFSET_AMOUNT				--相殺金額
,      PYB_HED.TAX_AMOUNT					--消費税額
,      PYB_HED.ACCOUNT_PAYABLE_BREAKDOWN	--買掛金(内訳)
,      PYB_HED.ARREARAGE_BREAKDOWN			--未払金(内訳)
,      PYB_HED.PAYABLE_AMOUNT				--買掛残
,      (PYB_HED.PAYMENT_AMOUNT
        +PYB_HED.OTHER_PAYMENT_AMOUNT) AS TOTAL_PAYMENT
	                                  --画面用.支払金額
,      ((PYB_HED.OTHER_STOCKING_AMOUNT + PYB_HED.OFFSET_AMOUNT)
         - (PYB_HED.STOCKING_RETURNED_AMOUNT + PYB_HED.STOCKING_DISCOUNT_AMOUNT)
       ) AS OTHER_STOCKING
       --画面用.その他計((その他仕入金額+相殺金額)-(返品金額+値引金額))
FROM   PAYABLE_HEADER PYB_HED
	   LEFT JOIN ORGANIZATION ORGANIZATION
	   ON PYB_HED.ORGANIZATION_CD = ORGANIZATION.ORGANIZATION_CD
	   LEFT JOIN LOGIN LOGIN
	   ON PYB_HED.INPUTOR_CD = LOGIN.TANTO_CD
	   LEFT JOIN VENDER VENDER
	   ON   VENDER.VENDER_DIVISION = 'SI'
	   AND  PYB_HED.SUPPLIER_CD = VENDER.VENDER_CD
WHERE  PYB_HED.ORGANIZATION_CD IS NOT NULL
AND  TO_CHAR(PYB_HED.PAYABLE_DATE,'YYYY/MM') = /*condition.srhTargetMonth*/'2009/07'

/*IF (condition.srhAccountPayableDivi)*/ --出力区分：買掛残高有のみ（買掛金(内訳)が0以外）
	AND	PYB_HED.ACCOUNT_PAYABLE_BREAKDOWN IS NOT NULL
	AND PYB_HED.ACCOUNT_PAYABLE_BREAKDOWN != '0'
/*END*/

/*IF (condition.srhArrearageDivi)*/ --出力区分：未払金残高有のみ（未払金(内訳)が0以外）
	AND	PYB_HED.ARREARAGE_BREAKDOWN IS NOT NULL
	AND PYB_HED.ARREARAGE_BREAKDOWN != '0'
/*END*/

/*IF (( condition.srhOrganizationCd != null ) && ( condition.srhOrganizationCd != "" ))*/
	AND	(PYB_HED.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/'%' OR ORGANIZATION.ORGANIZATION_NAME LIKE /*condition.srhOrganizationCd*/'%')
/*END*/

/*IF (( condition.srhTantoCd != null ) && ( condition.srhTantoCd != "" ))*/
	AND	(PYB_HED.INPUTOR_CD LIKE /*condition.srhTantoCd*/'%' OR LOGIN.TANTO_NM LIKE /*condition.srhTantoCd*/'%')
/*END*/

/*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
	AND	(PYB_HED.SUPPLIER_CD LIKE /*condition.srhVenderCd*/'%' OR VENDER.VENDER_NAME1 LIKE FUN_RET_MASTER_STRING_USE_AC(/*condition.srhVenderCd*/'%'))
/*END*/

ORDER BY PYB_HED.SUPPLIER_CD
