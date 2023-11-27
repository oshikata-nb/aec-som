/*
 * 受払情報件数取得用SQL
 *
 * entityName=InoutMonthlyUpdate
 * packageName=inoutmonthlyupdate
 * methodName=getInoutCount
 *
 */

SELECT COUNT(*) CNT
FROM INOUT_RECORD IR
WHERE TO_CHAR(IR.INOUT_DATE, 'YYYY/MM/DD') BETWEEN /*inputDateFrom*/'2009/07/01'
AND /*inputDateTo*/'2009/07/31'
