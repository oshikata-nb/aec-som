/*
 * 支払残高一覧用SQL
 *
 * entityName=PaymentBalanceList
 * packageName=payment.paymentbalance
 * methodName=getSearchList
 *
 */
SELECT PYM_HED.PAYMENT_NO					--支払番号
,      PYM_HED.ORGANIZATION_CD				--部署コード
,      ORGANIZATION.ORGANIZATION_NAME		--部署名称
,      PYM_HED.SUPPLIER_CD					--支払先コード
,      VENDER.VENDER_NAME1 AS VENDER_NAME1	--支払先名称
,      VENDER.VENDER_SHORTED_NAME AS VENDER_SHORTED_NAME	--支払先略称
,      PYM_HED.CLAIM_AMOUNT_FORWARD			--前回支払予定額
,      PYM_HED.CREDIT_AMOUNT_FORWARD		--前回支払額
,      PYM_HED.OTHER_CREDIT_AMOUNT_FORWARD	--前回その他支払額
,      PYM_HED.BALANCE_FORWARD   			--差引繰越額
,      PYM_HED.STOCKING_AMOUNT				--今回仕入額
,      PYM_HED.STOCKING_RETURNED_AMOUNT		--返品金額
,      PYM_HED.STOCKING_DISCOUNT_AMOUNT		--値引金額
,      PYM_HED.OTHER_STOCKING_AMOUNT		--その他仕入金額
,      PYM_HED.OFFSET_AMOUNT				--相殺
,      PYM_HED.TAX_AMOUNT					--消費税額
,      PYM_HED.PAYABLE_AMOUNT				--支払残高
,      (PYM_HED.CREDIT_AMOUNT_FORWARD
        +PYM_HED.OTHER_CREDIT_AMOUNT_FORWARD) AS CREDIT_AMOUNT
                                            --画面用.支払・その他計
,      ((PYM_HED.OTHER_STOCKING_AMOUNT + PYM_HED.OFFSET_AMOUNT)
         - (PYM_HED.STOCKING_RETURNED_AMOUNT + PYM_HED.STOCKING_DISCOUNT_AMOUNT)
       ) AS OTHER_TOTAL
                                            --画面用.その他計
FROM   PAYMENT_HEADER PYM_HED
	   LEFT JOIN ORGANIZATION ORGANIZATION
	   ON PYM_HED.ORGANIZATION_CD = ORGANIZATION.ORGANIZATION_CD
	   LEFT JOIN LOGIN LOGIN
	   ON PYM_HED.INPUTOR_CD = LOGIN.TANTO_CD
	   LEFT JOIN VENDER VENDER
       ON   VENDER.VENDER_DIVISION = 'SI'
	   AND  PYM_HED.SUPPLIER_CD = VENDER.VENDER_CD
WHERE  PYM_HED.ORGANIZATION_CD IS NOT NULL
AND  TO_CHAR(PYM_HED.PAYABLE_DATE,'YYYY/MM') = /*condition.srhTargetMonth*/'2009/07'

/*IF (condition.srhOutputDivision)*/ --出力区分：残高有のみ
	AND	PYM_HED.PAYABLE_AMOUNT IS NOT NULL
	AND PYM_HED.PAYABLE_AMOUNT != '0'
/*END*/

/*IF (( condition.srhOrganizationCd != null ) && ( condition.srhOrganizationCd != "" ))*/
	AND	(PYM_HED.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/'%' OR ORGANIZATION.ORGANIZATION_NAME LIKE /*condition.srhOrganizationCd*/'%')
/*END*/

/*IF (( condition.srhTantoCd != null ) && ( condition.srhTantoCd != "" ))*/
	AND	(PYM_HED.INPUTOR_CD LIKE /*condition.srhTantoCd*/'%' OR LOGIN.TANTO_NM LIKE /*condition.srhTantoCd*/'%')
/*END*/

/*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
	AND	(PYM_HED.SUPPLIER_CD LIKE /*condition.srhVenderCd*/'%' OR VENDER.VENDER_NAME1 LIKE FUN_RET_MASTER_STRING_USE_AC(/*condition.srhVenderCd*/'%'))
/*END*/

ORDER BY PYM_HED.SUPPLIER_CD
