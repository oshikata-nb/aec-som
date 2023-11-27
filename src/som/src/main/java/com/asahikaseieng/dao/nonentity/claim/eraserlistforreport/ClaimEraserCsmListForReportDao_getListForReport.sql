/*
* 消込入力帳票用SQL(カスタマイズ)
*
* entityName=ClaimEraserCsmListForReport
* packageName=eraserlistforreport
* methodName=getListForReport
*
*/
SELECT *
FROM   (
		--消込状態='新規消込'
		/*IF (condition.srhStatusNew)*/
		SELECT '1' KBN --区分(新規消込)
			   ,ERASER_CSM.*
			   ,CREDIT.CREDIT_ERASER_AMOUNT
				
			   ,CASE ERASER_COMPLETE_DIVISION
					WHEN 0 THEN
					 '新規消込'
					WHEN 1 THEN
					 '処理中'
					WHEN 2 THEN
					 '消込完了'
					ELSE
					 NULL
				END APPROVAL_STATUS_NAME
		
		FROM   (SELECT ERASER_CSM.*
					   ,ORGANIZATION.ORGANIZATION_NAME
					   ,VENDER.VENDER_NAME1
					   ,VENDER.VENDER_SHORTED_NAME
					   ,APPROVOR.TANTO_NM              APPROVOR_NAME
					   ,ERASEROR.TANTO_NM              ERASEROR_NAME
					   ,INPUTOR.TANTO_NM               INPUTOR_NAME
					   ,UPDATOR.TANTO_NM               UPDATOR_NAME
				 
				 FROM   ERASER_CSM
					   ,ORGANIZATION
					   ,VENDER
					   ,LOGIN        APPROVOR
					   ,LOGIN        ERASEROR
					   ,LOGIN        INPUTOR
					   ,LOGIN        UPDATOR
				 
				 WHERE  ERASER_CSM.ORGANIZATION_CD IS NOT NULL
				 AND    (ERASER_CSM.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/'%'
					   OR ORGANIZATION.ORGANIZATION_NAME LIKE /*condition.srhOrganizationCd*/'%')
					   
					   /*IF (condition.srhTantoCd != null && condition.srhTantoCd != "")*/
				 AND    (ERASER_CSM.INPUTOR_CD LIKE /*condition.srhTantoCd*/'%'
					   OR INPUTOR.TANTO_NM LIKE /*condition.srhTantoCd*/'%')
					   /*END*/
					   
					   /*IF (condition.srhVenderCd != null && condition.srhVenderCd != "")*/
				 AND    (ERASER_CSM.INVOICE_CD LIKE /*condition.srhVenderCd*/'%'
					   OR VENDER.VENDER_NAME1 LIKE FUN_RET_MASTER_STRING_USE_AC(/*condition.srhVenderCd*/'%'))
					   /*END*/
					   
				 AND    ERASER_CSM.ERASER_COMPLETE_DIVISION = 0 --消込完了フラグ(未処理)
				 AND    ERASER_CSM.ORGANIZATION_CD = ORGANIZATION.ORGANIZATION_CD(+)
				 AND    VENDER.VENDER_DIVISION(+) = 'TS'
				 AND    ERASER_CSM.INVOICE_CD = VENDER.VENDER_CD(+)
				 AND    ERASER_CSM.APPROVEDBY = APPROVOR.TANTO_CD(+)
				 AND    ERASER_CSM.ERASEROR_CD = ERASEROR.TANTO_CD(+)
				 AND    ERASER_CSM.INPUTOR_CD = INPUTOR.TANTO_CD(+)
				 AND    ERASER_CSM.UPDATOR_CD = UPDATOR.TANTO_CD(+)) ERASER_CSM
				
			   ,(SELECT ORGANIZATION_CD
					   ,VENDER_CD
					   ,SUM(NVL(CREDIT_ERASER_AMOUNT, 0)) CREDIT_ERASER_AMOUNT
				 
				 FROM   CREDIT
				 WHERE  (CREDIT.DATA_TOTAL_DIVISION = 1 OR CREDIT.DATA_TOTAL_DIVISION = 9) --データ集計区分(1:入金、9:その他)
				 AND    CREDIT.CLAIM_TARGET_DIVISION <> 9 --請求対象
				 AND    CREDIT.CREDIT_ERASER_AMOUNT <> 0
				 GROUP  BY ORGANIZATION_CD
						  ,VENDER_CD) CREDIT
		
		WHERE  ERASER_CSM.ORGANIZATION_CD = CREDIT.ORGANIZATION_CD(+)
		AND    ERASER_CSM.INVOICE_CD = CREDIT.VENDER_CD(+)
		/*END*/
		
		/*IF (condition.srhStatusNew && condition.srhStatusApprove)*/
		UNION
		/*END*/
		
		--消込状態='承認処理'
		/*IF (condition.srhStatusApprove)*/
		SELECT '2' KBN --区分(承認処理)
			  ,ERASER_CSM.*
			  ,CREDIT.CREDIT_ERASER_AMOUNT
			   
			  ,CASE ERASER_COMPLETE_DIVISION
				   WHEN 0 THEN
					'新規消込'
				   WHEN 1 THEN
					'処理中'
				   WHEN 2 THEN
					'消込完了'
				   ELSE
					NULL
			   END APPROVAL_STATUS_NAME
		
		FROM   (SELECT ERASER_CSM.*
					  ,ORGANIZATION.ORGANIZATION_NAME
					  ,VENDER.VENDER_NAME1
					  ,VENDER.VENDER_SHORTED_NAME
					  ,APPROVOR.TANTO_NM              APPROVOR_NAME
					  ,ERASEROR.TANTO_NM              ERASEROR_NAME
					  ,INPUTOR.TANTO_NM               INPUTOR_NAME
					  ,UPDATOR.TANTO_NM               UPDATOR_NAME
				
				FROM   ERASER_CSM
					  ,ORGANIZATION
					  ,VENDER
					  ,LOGIN        APPROVOR
					  ,LOGIN        ERASEROR
					  ,LOGIN        INPUTOR
					  ,LOGIN        UPDATOR
				
				WHERE  ERASER_CSM.ORGANIZATION_CD IS NOT NULL
				AND    (ERASER_CSM.ORGANIZATION_CD LIKE /*condition.srhorganizationCd*/'%'
					  OR ORGANIZATION.ORGANIZATION_NAME LIKE /*condition.srhOrganizationCd*/'%')
					  
					  /*IF (condition.srhTantoCd != null && condition.srhTantoCd != "")*/
				AND    (ERASER_CSM.ERASEROR_CD LIKE /*condition.srhTantoCd*/'%'
					  OR INPUTOR.TANTO_NM LIKE /*condition.srhTantoCd*/'%')
					  /*END*/
					  
					  /*IF (condition.srhVenderCd != null && condition.srhVenderCd != "")*/
				AND    (ERASER_CSM.INVOICE_CD LIKE /*condition.srhVenderCd*/'%'
					  OR VENDER.VENDER_NAME1 LIKE FUN_RET_MASTER_STRING_USE_AC(/*condition.srhVenderCd*/'%'))
					  /*END*/
					  
				AND    ERASER_CSM.ERASER_COMPLETE_DIVISION = 1 --消込完了フラグ(処理中)
				AND    ERASER_CSM.ORGANIZATION_CD = ORGANIZATION.ORGANIZATION_CD(+)
				AND    VENDER.VENDER_DIVISION(+) = 'TS'
				AND    ERASER_CSM.INVOICE_CD = VENDER.VENDER_CD(+)
				AND    ERASER_CSM.APPROVEDBY = APPROVOR.TANTO_CD(+)
				AND    ERASER_CSM.ERASEROR_CD = ERASEROR.TANTO_CD(+)
				AND    ERASER_CSM.INPUTOR_CD = INPUTOR.TANTO_CD(+)
				AND    ERASER_CSM.UPDATOR_CD = UPDATOR.TANTO_CD(+)) ERASER_CSM
			   
			  ,(SELECT ORGANIZATION_CD
					  ,VENDER_CD
					  ,SUM(NVL(CREDIT_ERASER_AMOUNT, 0)) CREDIT_ERASER_AMOUNT
				
				FROM   CREDIT
				WHERE  (CREDIT.DATA_TOTAL_DIVISION = 1 OR CREDIT.DATA_TOTAL_DIVISION = 9) --データ集計区分(1:入金、9:その他)
				AND    CREDIT.CLAIM_TARGET_DIVISION <> 9 --請求対象
				AND    CREDIT.CREDIT_ERASER_AMOUNT <> 0
				GROUP  BY ORGANIZATION_CD
						 ,VENDER_CD) CREDIT
		
		WHERE  ERASER_CSM.ORGANIZATION_CD = CREDIT.ORGANIZATION_CD(+)
		AND    ERASER_CSM.INVOICE_CD = CREDIT.VENDER_CD(+)
		/*END*/
		
		/*IF ((condition.srhStatusNew || condition.srhStatusApprove) && condition.srhStatusEraser)*/
		UNION
		/*END*/
		
		--消込状態='消込完了'
		/*IF (condition.srhStatusEraser)*/
		SELECT '3' KBN --区分(消込完了)
			  ,ERASER_CSM.*
			  ,0 CREDIT_ERASER_AMOUNT
			   
			  ,CASE ERASER_COMPLETE_DIVISION
				   WHEN 0 THEN
					'新規消込'
				   WHEN 1 THEN
					'処理中'
				   WHEN 2 THEN
					'消込完了'
				   ELSE
					NULL
			   END APPROVAL_STATUS_NAME
		
		FROM   (SELECT ERASER_CSM.*
					  ,ORGANIZATION.ORGANIZATION_NAME
					  ,VENDER.VENDER_NAME1
					  ,VENDER.VENDER_SHORTED_NAME
					  ,APPROVOR.TANTO_NM              APPROVOR_NAME
					  ,ERASEROR.TANTO_NM              ERASEROR_NAME
					  ,INPUTOR.TANTO_NM               INPUTOR_NAME
					  ,UPDATOR.TANTO_NM               UPDATOR_NAME
				
				FROM   ERASER_CSM
					  ,ORGANIZATION
					  ,VENDER
					  ,LOGIN        APPROVOR
					  ,LOGIN        ERASEROR
					  ,LOGIN        INPUTOR
					  ,LOGIN        UPDATOR
				
				WHERE  ERASER_CSM.ORGANIZATION_CD IS NOT NULL
				AND    (ERASER_CSM.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/'%'
					  OR ORGANIZATION.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/'%')
					  
					  /*IF (condition.srhTantoCd != null && condition.srhTantoCd != "")*/
				AND    (ERASER_CSM.ERASEROR_CD LIKE /*condition.srhTantoCd*/'%'
					  OR INPUTOR.TANTO_NM LIKE /*condition.srhTantoCd*/'%')
					  /*END*/
					  
					  /*IF (condition.srhVenderCd != null && condition.srhVenderCd != "")*/
				AND    (ERASER_CSM.INVOICE_CD LIKE /*condition.srhVenderCd*/'%'
					  OR VENDER.VENDER_NAME1 LIKE FUN_RET_MASTER_STRING_USE_AC(/*conditino.srhVenderCd*/'%'))
					  /*END*/
					  
					  /*IF (condition.srhEraserCompleteDateFrom != null && condition.srhEraserCompleteDateFrom != "")*/
				AND    ERASER_CSM.ERASER_COMPLETE_DATE >= /*condition.srhEraserCompleteDateFrom*/'2010/01/01'
					  /*END*/
					  
					  /*IF (condition.srhEraserCompleteDateTo != null && condition.srhEraserCompleteDateTo != "")*/
				AND    ERASER_CSM.ERASER_COMPLETE_DATE <= /*condition.srhEraserCompleteDateTo*/'2010/12/31'
					  /*END*/
					  
				AND    ERASER_CSM.ERASER_COMPLETE_DIVISION = 2 --消込完了フラグ(消込完了)
				AND    ERASER_CSM.ORGANIZATION_CD = ORGANIZATION.ORGANIZATION_CD(+)
				AND    VENDER.VENDER_DIVISION(+) = 'TS'
				AND    ERASER_CSM.INVOICE_CD = VENDER.VENDER_CD(+)
				AND    ERASER_CSM.APPROVEDBY = APPROVOR.TANTO_CD(+)
				AND    ERASER_CSM.ERASEROR_CD = ERASEROR.TANTO_CD(+)
				AND    ERASER_CSM.INPUTOR_CD = INPUTOR.TANTO_CD(+)
				AND    ERASER_CSM.UPDATOR_CD = UPDATOR.TANTO_CD(+)) ERASER_CSM
		/*END*/
		)

/*IF (condition.srhOutputDivision == "1")*/ --出力区分：消込残高有のみ（消込残高が0以外）
WHERE  ERASER_BALANCE_AMOUNT IS NOT NULL
AND    ERASER_BALANCE_AMOUNT != 0
/*END*/
ORDER  BY INVOICE_CD
		 ,INVOICE_UPDATE_DATE
		 ,KBN
