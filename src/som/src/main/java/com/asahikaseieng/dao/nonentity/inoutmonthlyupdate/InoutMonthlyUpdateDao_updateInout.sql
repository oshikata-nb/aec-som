/*
 * 受払更新用SQL
 *
 * entityName=InoutMonthlyUpdate
 * packageName=inoutmonthlyupdate
 * methodName=updateInout
 *
 */

UPDATE INOUT_RECORD IR
SET INOUT_UPDATE_DATE = SYSDATE,
UPDATE_DATE = SYSDATE,
UPDATOR_CD = /*tantoCd*/'%'
WHERE TO_CHAR(IR.INOUT_DATE, 'YYYY/MM/DD') BETWEEN /*inputDateFrom*/'2009/07/01'
AND /*inputDateTo*/'2009/07/31'
