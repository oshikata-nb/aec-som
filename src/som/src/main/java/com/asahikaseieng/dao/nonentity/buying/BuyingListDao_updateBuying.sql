/**
 * 仕入伝票発行時発行フラグと発行日時更新用SQL
 *
 * @author tosco
 *
 * entityName=BuyingList
 * packageName=buying
 * methodName=updateBuying
 *
 */
UPDATE 
	PURCHASE_SUBCONTRACT
SET
	PURCHASE_SUBCONTRACT.UPDATE_DATE = SYSDATE
,	PURCHASE_SUBCONTRACT.UPDATOR_CD = /*tantoCd*/
,	PURCHASE_SUBCONTRACT.SLIP_ISSUE_DIVISION = 1
,	PURCHASE_SUBCONTRACT.SLIP_ISSUE_DATE = SYSDATE
WHERE
	PURCHASE_SUBCONTRACT.SLIP_NO = /*slipNo*/
