/*
 * Created on 2009/03/03
 *
 * $copyright$
 *
*/

/**
 * 得意先会計情報取得SQL
 *
 * @author tosco
 *
 * entityName=SalesDetailEntity
 * packageName=sales
 * methodName=getSalesByVender
 *
 */
SELECT
    VENDER.ORGANIZATION_CD         AS CHARGE_ORGANIZATION_CD
,   VENDER.SECTION_CD              AS ACCOUNT_DEBIT_SECTION_CD
,   VENDER.ACCOUNTS_CD             AS DEBIT_TITLE_CD
,   ORGANIZATION.ORGANIZATION_NAME AS CHARGE_ORGANIZATION_NAME
,   BUMON.SECTION_NAME             AS ACCOUNTS_DEBIT_SECTION_NAME
,   ACCOUNTS.ACCOUNTS_NAME         AS DEBIT_TITLE_NAME
FROM
	(SELECT VEN.VENDER_CD
	,		VEN.VENDER_DIVISION
	,		DECODE(VEN.PAYMENT_INVOICE_CD, NULL, VEN.SECTION_CD, INVOICE.SECTION_CD) AS SECTION_CD			-- 会計部門コード(得意先or請求先)
	,		DECODE(VEN.PAYMENT_INVOICE_CD, NULL, VEN.ACCOUNTS_CD, INVOICE.ACCOUNTS_CD) AS ACCOUNTS_CD		-- 勘定科目コード(得意先or請求先)
	,		VEN.ORGANIZATION_CD		-- 部署コード
	 FROM VENDER VEN, VENDER INVOICE
	 WHERE VEN.VENDER_DIVISION = 'TS'
	 AND   VEN.PAYMENT_INVOICE_CD = INVOICE.VENDER_CD(+)
	 AND   INVOICE.VENDER_DIVISION(+) = 'TS'
	) VENDER
,   ORGANIZATION    
,   BUMON
,   (SELECT ACCOUNTS_CD, ACCOUNTS_NAME
     FROM ACCOUNTS
     GROUP BY ACCOUNTS_CD, ACCOUNTS_NAME
    ) ACCOUNTS
WHERE
    VENDER.VENDER_CD  = /*venderCd*/'02642044'
AND VENDER.VENDER_DIVISION = 'TS'
AND VENDER.ORGANIZATION_CD = ORGANIZATION.ORGANIZATION_CD(+)
AND VENDER.SECTION_CD = BUMON.SECTION_CD(+)
AND VENDER.ACCOUNTS_CD = ACCOUNTS.ACCOUNTS_CD(+)