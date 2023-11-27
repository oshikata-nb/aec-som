/*
 * 伝票発行フラグ更新用SQL
 *
 * entityName=DepositCredit
 * packageName=deposit
 * methodName=updateIssuedDivision
 *
 */

UPDATE CREDIT
SET ISSUE_DATE = SYSDATE
, ISSUED_DIVISION = /*issuedDivision*/1
, UPDATE_DATE = SYSDATE
, UPDATOR_CD = /*tantoCd*/'%'
WHERE CREDIT_NO = /*creditNo*/'%'
