/*
 * 入金予定一覧用SQL
 *
 * entityName=DepositPlanList
 * packageName=depositplan
 * methodName=getSearchList
 *
 */
SELECT CLM_HED.CLAIM_NO						--請求番号
,      CLM_HED.ORGANIZATION_CD				--部署コード
,      ORGANIZATION.ORGANIZATION_NAME		--部署名称
,      CLM_HED.VENDER_CD					--請求先コード
,      CLM_HED.VENDER_NAME1					--請求先名称
,      CLM_HED.VENDER_SHORTED_NAME            --請求先略称
,      TO_CHAR(CLM_HED.CREDIT_DATE,'YYYY/MM/DD') AS CREDIT_DATE
											--請求日付
,      TO_CHAR(CLM_HED.CREDIT_SCHEDULED_DATE,'YYYY/MM/DD') AS CREDIT_SCHEDULED_DATE
											--入金予定日
,      CLM_HED.CLAIM_AMOUNT - CLM_HED.BALANCE_FORWARD AS CLAIM_AMOUNT_BALANCE
											--請求額(今回請求額-差引繰越額)
,      CLM_HED.CREDIT_DIVISION				--入金分類
,      CLM_HED.NOTE_SIGHT					--手形サイト
,      CLS.CATEGORY_NAME			--入金名称
,      (
		CASE CLS.BANK_DIVISION
		WHEN 0 THEN NULL
		WHEN 1 THEN CLM_HED.BANK_MASTER_CD
 		END
	   ) AS BANK_MASTER_CD                    --銀行マスタコード
,      (
		CASE CLS.BANK_DIVISION
		WHEN 0 THEN NULL
		WHEN 1 THEN CLM_HED.BANK_MASTER_NAME
 		END
	   ) AS BANK_MASTER_NAME                  --銀行マスタ名称
,      (
		CASE CLS.BANK_DIVISION
        WHEN 0 THEN ''
		WHEN 1 THEN (	CASE TO_CHAR(CLM_HED.ACCOUNT_DIVISION)
						WHEN '1' THEN '1:普通'
						WHEN '2' THEN '2:当座'
						WHEN '3' THEN '3:その他'
						END
					  )
        ELSE ''
 		END
		) AS ACCOUNT_DIVISION                --口座区分
,      (
		CASE CLS.BANK_DIVISION
		WHEN 1 THEN CLM_HED.ACCOUNT_NO 
 		END
	   ) AS ACCOUNT_NO                       --口座番号
FROM   (
        SELECT CLAIM_HEADER.CLAIM_NO             　       --請求番号
        ,      CLAIM_HEADER.ORGANIZATION_CD               --部署コード
        ,      CLAIM_HEADER.VENDER_CD                     --請求先コード
        ,      CLAIM_HEADER.CREDIT_DATE                   --請求日付
        ,      CLAIM_HEADER.CREDIT_SCHEDULED_DATE       　--入金予定日
        ,      CLAIM_HEADER.CREDIT_DIVISION               --入金分類
        ,      CLAIM_HEADER.NOTE_SIGHT                    --手形サイト
        ,      CLAIM_HEADER.SALES_AMOUNT                  --今回売上額
        ,      CLAIM_HEADER.CLAIM_AMOUNT                  --今回請求額
        ,      CLAIM_HEADER.BALANCE_FORWARD               --差引繰越額
        ,      CLAIM_HEADER.INPUTOR_CD                    --登録者ID
        ,      VENDER.VENDER_DIVISION
        ,      VENDER.VENDER_NAME1
        ,      VENDER.VENDER_SHORTED_NAME
        ,      VENDER.BANK_CD BANK_MASTER_CD
        ,      BANK.BANK_MASTER_NAME
        ,      VENDER.ACCOUNT_DIVISION
        ,      VENDER.ACCOUNT_NO
        ,      ADVANCE_DIVISION
        FROM CLAIM_HEADER CLAIM_HEADER
	    LEFT JOIN VENDER VENDER
	    ON   VENDER.VENDER_DIVISION = 'TS'
	    AND  CLAIM_HEADER.VENDER_CD = VENDER.VENDER_CD
		LEFT JOIN BANK BANK
		ON VENDER.BANK_CD = BANK.BANK_MASTER_CD
	   ) CLM_HED
	   LEFT JOIN ORGANIZATION
	   ON CLM_HED.ORGANIZATION_CD =　ORGANIZATION.ORGANIZATION_CD
	   LEFT JOIN LOGIN LOGIN
	   ON CLM_HED.INPUTOR_CD = LOGIN.TANTO_CD
	   LEFT JOIN CLASSIFICATION CLS
	   ON CLM_HED.CREDIT_DIVISION = CLS.CATEGORY_DIVISION
	   AND CLS.DATA_TYPE = '2'
WHERE  CLM_HED.ORGANIZATION_CD IS NOT NULL
--AND    (CLM_HED.CLAIM_AMOUNT - CLM_HED.BALANCE_FORWARD) > 0
AND    ADVANCE_DIVISION = 1 -- 前受金区分 = 対象でない

/*IF (( condition.srhOrganizationCd != null ) && ( condition.srhOrganizationCd != "" ))*/
	AND	(CLM_HED.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/'%' OR ORGANIZATION.ORGANIZATION_NAME LIKE /*condition.srhOrganizationCd*/'%')
/*END*/

/*IF (( condition.srhTantoCd != null ) && ( condition.srhTantoCd != "" ))*/
	AND	(CLM_HED.INPUTOR_CD LIKE /*condition.srhTantoCd*/'%' OR LOGIN.TANTO_NM LIKE /*condition.srhTantoCd*/'%')
/*END*/

/*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
	AND	(CLM_HED.VENDER_CD LIKE /*condition.srhVenderCd*/'%' OR CLM_HED.VENDER_NAME1 LIKE FUN_RET_MASTER_STRING_USE_AC(/*condition.srhVenderCd*/'%'))
/*END*/

/*IF (( condition.srhCreditDateFrom != null ) && ( condition.srhCreditDateFrom != "" ))*/
	AND	CLM_HED.CREDIT_DATE >= /*condition.srhCreditDateFrom*/'20090601'
/*END*/

/*IF (( condition.srhCreditDateTo != null ) && ( condition.srhCreditDateTo != "" ))*/
	AND	CLM_HED.CREDIT_DATE <= /*condition.srhCreditDateTo*/'20090630'
/*END*/

/*IF (( condition.srhCreditScheduledDateFrom != null ) && ( condition.srhCreditScheduledDateFrom != "" ))*/
	AND	CLM_HED.CREDIT_SCHEDULED_DATE >= /*condition.srhCreditScheduledDateFrom*/'20090601'
/*END*/

/*IF (( condition.srhCreditScheduledDateTo != null ) && ( condition.srhCreditScheduledDateTo != "" ))*/
	AND	CLM_HED.CREDIT_SCHEDULED_DATE <= /*condition.srhCreditScheduledDateTo*/'20090630'
/*END*/

/*IF (( condition.srhBankMasterCd != null ) && ( condition.srhBankMasterCd != "" ))*/
	AND	(CLM_HED.BANK_MASTER_CD LIKE /*condition.srhBankMasterCd*/'%' OR CLM_HED.BANK_MASTER_NAME LIKE /*condition.srhBankMasterName*/'%')
/*END*/

/*IF (( condition.srhCreditDivision != null ) && ( condition.srhCreditDivision != "" ))*/
	AND	CLM_HED.CREDIT_DIVISION = /*condition.srhCreditDivision*/'%'
/*END*/


/*IF ( condition.srhOutputDivision == "1" )*/
	ORDER BY CLM_HED.CREDIT_SCHEDULED_DATE           --入金予定日別
    ,        CLM_HED.VENDER_CD
/*END*/

/*IF ( condition.srhOutputDivision == "2" )*/
	ORDER BY CLM_HED.VENDER_CD                     --取引先別
    ,        CLM_HED.CREDIT_SCHEDULED_DATE
/*END*/

/*IF ( condition.srhOutputDivision == "3" )*/
	ORDER BY CLM_HED.CREDIT_DIVISION                 --入金方法別
    ,        CLM_HED.CREDIT_SCHEDULED_DATE
    ,        CLM_HED.VENDER_CD
/*END*/

/*IF ( condition.srhOutputDivision == "4" )*/
	ORDER BY BANK_MASTER_CD                          --銀行別
	,        CLM_HED.CREDIT_SCHEDULED_DATE
    ,        CLM_HED.VENDER_CD
/*END*/
