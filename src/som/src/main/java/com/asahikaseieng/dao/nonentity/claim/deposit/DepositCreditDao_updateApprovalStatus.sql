/*
 * 入金承認ステータス更新用SQL
 *
 * entityName=DepositCredit
 * packageName=deposit
 * methodName=updateApprovalStatus
 *
 */

UPDATE CREDIT
SET APPROVAL_STATUS = /*approvalStatus*/1
, APPROVEDBY = /*approvedby*/'%'
, APPROVALDATE = /*approvaldate*/'2009/07/01'
WHERE CREDIT_NO = /*creditNo*/'%'
