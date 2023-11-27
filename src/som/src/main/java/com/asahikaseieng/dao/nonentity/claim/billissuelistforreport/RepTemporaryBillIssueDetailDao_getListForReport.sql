/*
*
*
* entityName=RepBillIssueDetail
* packageName=claim.billissuelistforreport
* methodName=getListForReport
*
*/
SELECT *
FROM   REP_TEMPORARY_BILLISSUE_DETAIL

WHERE  CLAIM_NO IN /*claimNo*/('%')

ORDER  BY HEAD_VENDER_CD
         ,CREDIT_DATE
         ,TRAN_DATE         NULLS FIRST
         ,TRAN_VENDER_CD
         ,SALES_SLIP_NO
         ,SALES_SLIP_ROW_NO ASC
