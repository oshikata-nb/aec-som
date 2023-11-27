/*
 * グループ間相殺トランザクション＆ヘッダー情報取得用SQL
 *
 * entityName=OffsetTranData
 * packageName=payment.offset
 * methodName=getDetailData
 *
 */
SELECT TRN.OFFSET_NO
,      BELONG.ORGANIZATION_CD
,      ORG.ORGANIZATION_NAME
,      TRN.OFFSET_GROUP_CD
,      OSG.OFFSET_GROUP_NAME
,      TRN.CATEGORY_DIVISION
,      CLA.CATEGORY_NAME
,      CLA.DEBIT_ACCOUNTS_CD
,      CLA.DEBIT_ACCOUNTS_CD_NAME
,      CLA.CREDIT_ACCOUNTS_CD
,      CLA.CREDIT_ACCOUNTS_CD_NAME
,      TRN.OFFSET_DATE
,      TRN.VENDER_DIVISION
,      TRN.VENDER_CD
,      VND.VENDER_NAME1
,      HDR.SUMMARY_CD
,      HDR.SUMMARY
,      HDR.OFFSET_AMOUNT
,      TRN.PAYABLE_OFFSET_AMOUNT
,      TRN.DEPOSIT_OFFSET_AMOUNT
,      HDR.APPROVAL_STATUS
,      TRN.DEPOSIT_UPDATE_DIVISION
,      TRN.DELIVERY_UPDATE_DATE
,      TRN.CLAIM_UPDATE_DIVISION
,      TRN.PAYABLE_UPDATE_DIVISION
,      TRN.PAYABLE_UPDATE_DATE
,      TRN.PAYMENT_UPDATE_DIVISION
,      TRN.ERASER_COMPLETE_DIVISION
,      HDR.TRANSMISSION_DATE
,      HDR.INPUT_DATE
,      HDR.INPUTOR_CD
,      HDR.UPDATE_DATE
,      HDR.UPDATOR_CD

FROM   OFFSET_GROUP_DATA TRN
	   LEFT JOIN
	       OFFSET_GROUP_HEADER HDR
	       ON TRN.OFFSET_NO = HDR.OFFSET_NO
       LEFT JOIN
           BELONG
           ON TRN.INPUTOR_CD = BELONG.TANTO_CD
           AND NVL(BELONG.BELONG_KBN, 1) = 1 --主所属
 	   LEFT JOIN
 	       ORGANIZATION ORG
	       ON BELONG.ORGANIZATION_CD = ORG.ORGANIZATION_CD
       LEFT JOIN
           VENDER VND
           ON  TRN.VENDER_CD = VND.VENDER_CD
           AND TRN.VENDER_DIVISION = VND.VENDER_DIVISION
	   LEFT JOIN
	       (SELECT DISTINCT OFFSET_GROUP_CD
	        ,               OFFSET_GROUP_NAME
	        FROM   OFFSET_GROUP
	       ) OSG
	   ON TRN.OFFSET_GROUP_CD = OSG.OFFSET_GROUP_CD       
	   LEFT JOIN
	       (SELECT  CLA.CATEGORY_DIVISION
	        ,       CLA.CATEGORY_NAME
	        ,       CLA.DEBIT_ACCOUNTS_CD
	        ,       (SELECT ACCOUNTS_NAME
					   FROM ACCOUNTS
					  WHERE ACCOUNTS.ACCOUNTS_CD = CLA.DEBIT_ACCOUNTS_CD
					) DEBIT_ACCOUNTS_CD_NAME
	        ,       CLA.CREDIT_ACCOUNTS_CD
            ,       (SELECT ACCOUNTS_NAME
				       FROM ACCOUNTS
				      WHERE ACCOUNTS.ACCOUNTS_CD = CLA.CREDIT_ACCOUNTS_CD
					) CREDIT_ACCOUNTS_CD_NAME
	        FROM  CLASSIFICATION CLA
	        WHERE CLA.DATA_TYPE = '5'
	       ) CLA
	       ON TRN.CATEGORY_DIVISION = TO_CHAR(CLA.CATEGORY_DIVISION)
WHERE  TRN.OFFSET_NO = /*offsetNo*/'GRP2000004'
AND    TRN.VENDER_CD IN (SELECT VENDER_CD
                         FROM   OFFSET_GROUP
                         WHERE  OFFSET_GROUP_CD = (SELECT OFFSET_GROUP_CD
                                                   FROM   OFFSET_GROUP_HEADER
                                                   WHERE  OFFSET_NO = /*offsetNo*/'GRP2000004'
                                                  )
                        )
ORDER BY TRN.VENDER_CD


