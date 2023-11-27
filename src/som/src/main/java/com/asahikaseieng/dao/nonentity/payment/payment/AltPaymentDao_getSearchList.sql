/*
 * 支払入力（リスト)一覧検索用SQL
 *
 * entityName=Payment
 * packageName=payment.payment
 * methodName=getSearchList
 *
 */
SELECT
    PAY.PAYMENT_DATE AS PAYMENT_DATE,				--支払日付
    PAY.SLIP_NO AS SLIP_NO,							--支払番号
    PAY.ROW_NO AS ROW_NO,							--行番号
    PAY.SUPPLIER_CD AS SUPPLIER_CD,					--支払先コード
    PAY.CATEGORY_DIVISION AS CATEGORY_DIVISION,		--分類コード
    PAY.PAYMENT_AMOUNT AS PAYMENT_AMOUNT,			--支払金額
    PAY.APPROVAL_STATUS AS APPROVAL_STATUS,			--承認ステータス
	PAY.TRANSMISSION_DATE,							--データ転送日時
    CLS.CATEGORY_NAME AS CATEGORY_NAME,				--支払分類名
    VEN.VENDER_NAME1 AS VENDER_NAME,				--支払先名称
    VEN.VENDER_SHORTED_NAME AS VENDER_SHORTED_NAME,	--支払先略称
	DEPOSIT_UPDATE_DIVISION,						--売掛更新フラグ
	CLAIM_UPDATE_DIVISION,							--請求更新フラグ
	PAYABLE_UPDATE_DIVISION,						--買掛更新フラグ
	PAYMENT_UPDATE_DIVISION,						--支払更新フラグ
	ERASER_COMPLETE_DIVISION						--消込完了フラグ
FROM
    CLASSIFICATION CLS,
    VENDER VEN,
    PAYMENT PAY,
    ORGANIZATION ORG,
    LOGIN LGN
WHERE
    PAY.DATA_TYPE = /*condition.dataType*/4
    AND VEN.VENDER_DIVISION(+) = 'SI'			--SI仕入れ先
    AND VEN.VENDER_CD(+) = PAY.SUPPLIER_CD
    AND CLS.DATA_TYPE(+) = 4					--4支払
    AND CLS.CATEGORY_DIVISION(+) = PAY.CATEGORY_DIVISION
    AND ORG.ORGANIZATION_CD(+) = PAY.ORGANIZATION_CD
    AND LGN.TANTO_CD(+) = PAY.INPUTOR_CD
    
/*IF (( condition.organizationCd != null ) && ( condition.organizationCd != "" ))*/
    AND (PAY.ORGANIZATION_CD LIKE /*condition.organizationCd*/'%' OR ORG.ORGANIZATION_NAME LIKE /*condition.organizationCd*/'%')
/*END*/

/*IF (( condition.tantoCd != null ) && ( condition.tantoCd != "" ))*/
    AND (PAY.INPUTOR_CD LIKE /*condition.tantoCd*/'%' OR LGN.TANTO_NM LIKE /*condition.tantoCd*/'%')
/*END*/

/*IF (( condition.paymentDateFrom != null ) && ( condition.paymentDateFrom != "" ))*/
    AND PAY.PAYMENT_DATE >= TO_DATE(/*condition.paymentDateFrom*/'2009/07/01','YYYY/MM/DD')    --FROM
/*END*/

/*IF (( condition.paymentDateTo != null ) && ( condition.paymentDateTo != "" ))*/
    AND PAY.PAYMENT_DATE <= TO_DATE(/*condition.paymentDateTo*/'2009/07/31','YYYY/MM/DD')    --TO
/*END*/

/*IF (( condition.supplierCd != null ) && ( condition.supplierCd != "" ))*/
    AND (PAY.SUPPLIER_CD LIKE /*condition.supplierCd*/'%' OR VEN.VENDER_NAME1 LIKE FUN_RET_MASTER_STRING_USE_AC(/*condition.supplierCd*/'%'))
/*END*/

/*IF (( condition.slipNoFrom != null ) && ( condition.slipNoFrom != "" ))*/
    AND PAY.SLIP_NO >= /*condition.slipNoFrom*/'%'   --FROM
/*END*/

/*IF (( condition.slipNoTo != null ) && ( condition.slipNoTo != "" ))*/
    AND PAY.SLIP_NO <= /*condition.slipNoTo*/'%'   --TO
/*END*/

/*IF (( condition.categoryDivision != null ) && ( condition.categoryDivision != "" ))*/
    AND PAY.CATEGORY_DIVISION = /*condition.categoryDivision*/'%'
/*END*/

/*IF (( condition.approvalStatus != null ) && ( condition.approvalStatus != "0" ))*/
    AND PAY.APPROVAL_STATUS = /*condition.approvalStatus*/1
/*END*/

/*IF (( condition.issuedDivision != null ) && ( condition.issuedDivision != "" ))*/
    AND PAY.ISSUED_DIVISION = /*condition.issuedDivision*/'%'
/*END*/

ORDER BY
	PAYMENT_DATE,
	SLIP_NO,
	ROW_NO
