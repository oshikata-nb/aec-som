/*
 * 見積検索SQL
 *
 * entityName=EstimateList
 * packageName=estimatelist
 * methodName=getList
 *
 */

SELECT ESTIMATE.ESTIMATE_NO, ESTIMATE.VENDER_CD, VENDER.VENDER_NAME1, VENDER.VENDER_NAME2, VENDER.VENDER_SHORTED_NAME, ESTIMATE.ESTIMATE_VALID_DATE_TO, ESTIMATE.ESTIMATE_INPUT_DATE, ESTIMATE.ESTIMATE_STATUS,
CASE ESTIMATE_STATUS
	WHEN 1 THEN '見積登録'
	WHEN 2 THEN '見積承認依頼中'
	WHEN 3 THEN '見積承認済'
	ELSE NULL
END ESTIMATE_STATUS_NAME
FROM ESTIMATE, BALANCE, VENDER, ITEM,

(SELECT ITEM_CD, MAX(VERSION) VERSION
FROM ITEM
GROUP BY ITEM_CD) MAX_ITEM

WHERE ESTIMATE.ESTIMATE_NO IS NOT NULL

/*IF(condition.srhEstimateNo != null) && (condition.srhEstimateNo != "")*/
AND ESTIMATE.ESTIMATE_NO LIKE /*condition.srhEstimateNo*/'%'
/*END*/

/*IF(condition.strSrhEstimateInputDateFrom != null) && (condition.strSrhEstimateInputDateFrom != "")*/
AND	TO_CHAR(ESTIMATE.ESTIMATE_INPUT_DATE, 'YYYY/MM/DD') >= /*condition.strSrhEstimateInputDateFrom*/'2000/01/01'
/*END*/

/*IF(condition.strSrhEstimateInputDateTo != null) && (condition.strSrhEstimateInputDateTo != "")*/
AND	TO_CHAR(ESTIMATE.ESTIMATE_INPUT_DATE, 'YYYY/MM/DD') <= /*condition.strSrhEstimateInputDateTo*/'2010/12/31'
/*END*/

/*IF(condition.srhVenderCd != null) && (condition.srhVenderCd != "")*/
AND	(BALANCE.VENDER_CD LIKE /*condition.srhVenderCd*/'%' OR VENDER_NAME1 LIKE FUN_RET_MASTER_STRING_USE_AC(/*condition.srhVenderCd*/'%'))
/*END*/

/*IF(condition.srhItemCd != null) && (condition.srhItemCd != "")*/
AND	(ESTIMATE.ITEM_CD LIKE /*condition.srhItemCd*/'%' OR ITEM_NAME LIKE /*condition.srhItemCd*/'%')
/*END*/

/*IF(condition.srhOtherCompanyCd1 != null) && (condition.srhOtherCompanyCd1 != "")*/
AND	OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/'%'
/*END*/

/*IF(condition.strSrhEstimateValidDateFrom != null) && (condition.strSrhEstimateValidDateFrom != "")*/
AND	TO_CHAR(ESTIMATE.ESTIMATE_VALID_DATE_FROM, 'YYYY/MM/DD') >= /*condition.strSrhEstimateValidDateFrom*/'2000/01/01'
/*END*/

/*IF(condition.strSrhEstimateValidDateTo != null) && (condition.strSrhEstimateValidDateTo != "")*/
AND	TO_CHAR(ESTIMATE.ESTIMATE_VALID_DATE_TO, 'YYYY/MM/DD') <= /*condition.strSrhEstimateValidDateTo*/'2000/01/01'
/*END*/

/*IF(condition.srhEstimateStatus != 0)*/
AND	ESTIMATE.ESTIMATE_STATUS = /*condition.srhEstimateStatus*/'1'
/*END*/

AND ESTIMATE.BALANCE_CD = BALANCE.BALANCE_CD(+)
AND VENDER_DIVISION(+) = 'TS'
AND BALANCE.VENDER_CD = VENDER.VENDER_CD(+)
AND ESTIMATE.ITEM_CD = MAX_ITEM.ITEM_CD(+)
AND MAX_ITEM.ITEM_CD = ITEM.ITEM_CD(+)
AND MAX_ITEM.VERSION = ITEM.VERSION(+)
GROUP BY ESTIMATE.ESTIMATE_NO, ESTIMATE.VENDER_CD, VENDER.VENDER_NAME1, VENDER.VENDER_NAME2, VENDER.VENDER_SHORTED_NAME, ESTIMATE.ESTIMATE_VALID_DATE_TO, ESTIMATE.ESTIMATE_INPUT_DATE, ESTIMATE.ESTIMATE_STATUS
ORDER BY ESTIMATE.ESTIMATE_NO DESC
