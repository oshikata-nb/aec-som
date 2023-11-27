/*
 * (本締め)買掛残高帳票用SQL
 *
 * entityName=ApBalanceListForReport
 * packageName=apbalanceforreport
 * methodName=getListForReport
 *
 */
SELECT PYB_HED.*
, ORGANIZATION.ORGANIZATION_NAME
, VENDER.VENDER_NAME1
, VENDER.VENDER_SHORTED_NAME

, NVL(PYB_HED.PAYMENT_AMOUNT, 0)
+ NVL(PYB_HED.OTHER_PAYMENT_AMOUNT, 0) TOTAL_PAYMENT --画面用.支払金額

--画面用.その他計((その他仕入金額+相殺金額)-(返品金額+値引金額))
, (NVL(PYB_HED.OTHER_STOCKING_AMOUNT, 0)
+ NVL(PYB_HED.OFFSET_AMOUNT, 0))
- (NVL(PYB_HED.STOCKING_RETURNED_AMOUNT, 0)
+ NVL(PYB_HED.STOCKING_DISCOUNT_AMOUNT, 0)) OTHER_STOCKING

, INPUTOR.TANTO_NM INPUTOR_NAME
, UPDATOR.TANTO_NM UPDATOR_NAME

FROM PAYABLE_HEADER PYB_HED
, ORGANIZATION
, VENDER
, LOGIN INPUTOR
, LOGIN UPDATOR

WHERE PYB_HED.ORGANIZATION_CD IS NOT NULL
AND TO_CHAR(PYB_HED.PAYABLE_DATE,'YYYY/MM') = /*condition.srhTargetMonth*/'2009/07'

/*IF (condition.srhAccountPayableDivi)*/ --出力区分：買掛残高有のみ（買掛金(内訳)が0以外）
AND	PYB_HED.ACCOUNT_PAYABLE_BREAKDOWN IS NOT NULL
AND PYB_HED.ACCOUNT_PAYABLE_BREAKDOWN != 0
/*END*/

/*IF (condition.srhArrearageDivi)*/ --出力区分：未払金残高有のみ（未払金(内訳)が0以外）
AND	PYB_HED.ARREARAGE_BREAKDOWN IS NOT NULL
AND PYB_HED.ARREARAGE_BREAKDOWN != 0
/*END*/

/*IF (condition.srhOrganizationCd != null && condition.srhOrganizationCd != "")*/
AND	(PYB_HED.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/'%' OR ORGANIZATION.ORGANIZATION_NAME LIKE /*condition.srhOrganizationCd*/'%')
/*END*/

/*IF (condition.srhTantoCd != null && condition.srhTantoCd != "")*/
AND	(PYB_HED.INPUTOR_CD LIKE /*condition.srhTantoCd*/'%' OR INPUTOR.TANTO_NM LIKE /*condition.srhTantoCd*/'%')
/*END*/

/*IF (condition.srhVenderCd != null && condition.srhVenderCd != "")*/
AND	(PYB_HED.SUPPLIER_CD LIKE /*condition.srhVenderCd*/'%' OR VENDER.VENDER_NAME1 LIKE FUN_RET_MASTER_STRING_USE_AC(/*condition.srhVenderCd*/'%'))
/*END*/

AND PYB_HED.ORGANIZATION_CD = ORGANIZATION.ORGANIZATION_CD(+)
AND VENDER.VENDER_DIVISION(+) = 'SI'
AND PYB_HED.SUPPLIER_CD = VENDER.VENDER_CD(+)
AND PYB_HED.INPUTOR_CD = INPUTOR.TANTO_CD(+)
AND PYB_HED.UPDATOR_CD = UPDATOR.TANTO_CD(+)

ORDER BY PYB_HED.SUPPLIER_CD
