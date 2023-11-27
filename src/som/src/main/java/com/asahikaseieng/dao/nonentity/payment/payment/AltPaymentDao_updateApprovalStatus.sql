/*
 * 支払承認ステータス更新用SQL
 *
 * entityName=AltPayment
 * packageName=payment
 * methodName=updateApprovalStatus
 *
 */

UPDATE PAYMENT
SET APPROVAL_STATUS = /*approvalStatus*/1
, APPROVEDBY = /*approvedby*/'%'
, APPROVALDATE = /*approvaldate*/'2009/07/01'
WHERE SLIP_NO = /*slipNo*/'%'
