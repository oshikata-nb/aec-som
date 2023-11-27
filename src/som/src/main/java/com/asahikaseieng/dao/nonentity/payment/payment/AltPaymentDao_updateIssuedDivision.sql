/*
 * 伝票発行フラグ更新用SQL
 *
 * entityName=AltPayment
 * packageName=payment
 * methodName=updateIssuedDivision
 *
 */

UPDATE PAYMENT
SET ISSUE_DATE = SYSDATE
, ISSUED_DIVISION = /*issuedDivision*/1
, UPDATE_DATE = SYSDATE
, UPDATOR_CD = /*tantoCd*/'%'
WHERE SLIP_NO = /*slipNo*/'%'
