/*
 * 見積/単価承認ステータス更新用SQL
 *
 * entityName=EstimateStatusUpdate
 * packageName=estimatestatusupdate
 * methodName=updateStatus
 */

UPDATE ESTIMATE
SET ESTIMATE_STATUS = /*estimateStatus*/1
, UPDATE_DATE = /*updateDate*/'2009/09/01'
, UPDATOR_CD = /*updatorCd*/'%'
WHERE ESTIMATE_NO = /*estimateNo*/'%'
