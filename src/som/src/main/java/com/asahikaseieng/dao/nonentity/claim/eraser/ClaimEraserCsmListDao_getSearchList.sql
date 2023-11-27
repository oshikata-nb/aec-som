/*
* 消込入力一覧用SQL(カスタマイズ)
*
* entityName=EraserCsmList
* packageName=claim.eraser
* methodName=getSearchList
*
*/
SELECT INVOICE_CD --請求先コード
	  ,KBN --区分
	  ,VENDER_NAME1 --請求先名称
	  ,VENDER_SHORTED_NAME --請求先略称
	  ,ERASER_COMPLETE_DATE --消込完了日付
	  ,ERASER_OBJECT_AMOUNT --消込対象額
	  ,ERASER_AMOUNT --消込金額
	  ,ERASER_BALANCE_AMOUNT CREDIT_ERASER_AMOUNT --消込残
	  ,ORGANIZATION_CD --部署コード
	  ,ORGANIZATION_NAME --部署名称
	  ,TANTO_CD --担当者コード
	  ,TANTO_NM --担当者名
	  ,APPROVAL_STATUS --承認ステータス
	  ,INVOICE_UPDATE_DATE --請求締日
	  ,CLAIM_NO --請求番号
FROM   (
		--消込状態='新規消込'
		/*IF ( condition.srhStatusNew )*/
		SELECT '1' AS KBN --区分(新規消込)
			   ,ERASER_CSM.INVOICE_CD --請求先コード
			   ,ERASER_CSM.VENDER_NAME1 --請求先名称
			   ,ERASER_CSM.VENDER_SHORTED_NAME --請求先略称
			   ,ERASER_CSM.ERASER_COMPLETE_DATE --消込完了日付
			   ,ERASER_CSM.ERASER_OBJECT_AMOUNT --消込対象額
			   ,ERASER_CSM.ERASER_AMOUNT --消込金額
			   ,ERASER_CSM.ERASER_BALANCE_AMOUNT --消込残
			   ,CREDIT.CREDIT_ERASER_AMOUNT --入金消込残
			   ,ERASER_CSM.ORGANIZATION_CD --部署コード
			   ,ERASER_CSM.ORGANIZATION_NAME --部署名称
			   ,ERASER_CSM.TANTO_CD --担当者コード
			   ,ERASER_CSM.TANTO_NM --担当者名
			   ,ERASER_CSM.APPROVAL_STATUS --承認ステータス
			   ,ERASER_CSM.INVOICE_UPDATE_DATE --請求締日
			   ,ERASER_CSM.CLAIM_NO --請求番号
		FROM   (SELECT ERASER_CSM.INVOICE_CD
					   ,MAX(ERASER_CSM.ORGANIZATION_CD) AS ORGANIZATION_CD
					   ,MAX(ORGANIZATION.ORGANIZATION_NAME) AS ORGANIZATION_NAME
					   ,MAX(VENDER.VENDER_NAME1) AS VENDER_NAME1
					   ,MAX(VENDER.VENDER_SHORTED_NAME) AS VENDER_SHORTED_NAME
					   ,MAX(ERASER_CSM.INPUTOR_CD) AS TANTO_CD
					   ,MAX(LOGIN.TANTO_NM) AS TANTO_NM
					   ,MAX(ERASER_CSM.ERASER_COMPLETE_DATE) AS ERASER_COMPLETE_DATE --消込完了日付
					   ,SUM(ERASER_CSM.ERASER_OBJECT_AMOUNT) AS ERASER_OBJECT_AMOUNT --消込対象額
					   ,SUM(ERASER_CSM.ERASER_AMOUNT) AS ERASER_AMOUNT --消込金額
					   ,SUM(NVL(ERASER_CSM.ERASER_BALANCE_AMOUNT, 0)) ERASER_BALANCE_AMOUNT --消込残
					   ,APPROVAL_STATUS --承認ステータス
					   ,INVOICE_UPDATE_DATE --請求締日
					   ,CLAIM_NO --請求番号
				 FROM   ERASER_CSM
				 LEFT   JOIN ORGANIZATION
				 ON     ERASER_CSM.ORGANIZATION_CD = ORGANIZATION.ORGANIZATION_CD
				 LEFT   JOIN LOGIN
				 ON     ERASER_CSM.INPUTOR_CD = LOGIN.TANTO_CD
				 LEFT   JOIN VENDER
				 ON     ERASER_CSM.INVOICE_CD = VENDER.VENDER_CD
				 AND    VENDER.VENDER_DIVISION = 'TS' --得意先
				 WHERE  ERASER_CSM.ORGANIZATION_CD IS NOT NULL
				 AND    (ERASER_CSM.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/'%'
					   OR ORGANIZATION.ORGANIZATION_NAME LIKE /*condition.srhOrganizationCd*/'%')
					   
					   /*IF (( condition.srhTantoCd != null ) && ( condition.srhTantoCd != "" ))*/
				 AND    (ERASER_CSM.INPUTOR_CD LIKE /*condition.srhTantoCd*/'%'
					   OR LOGIN.TANTO_NM LIKE /*condition.srhTantoCd*/'%')
					   /*END*/
					   
					   /*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
				 AND    (ERASER_CSM.INVOICE_CD LIKE /*condition.srhVenderCd*/'%'
					   OR VENDER.VENDER_NAME1 LIKE FUN_RET_MASTER_STRING_USE_AC(/*condition.srhVenderCd*/'%'))
					   /*END*/
					   
				 AND    ERASER_CSM.ERASER_COMPLETE_DIVISION = 0 --消込完了フラグ(未処理)
				 GROUP  BY ERASER_CSM.INVOICE_CD
						  ,INVOICE_UPDATE_DATE
						  ,APPROVAL_STATUS
						  ,CLAIM_NO) ERASER_CSM
		LEFT   JOIN (SELECT ORGANIZATION_CD
						   ,VENDER_CD
						   ,SUM(NVL(CREDIT_ERASER_AMOUNT, 0)) AS CREDIT_ERASER_AMOUNT --入金消込残
					 FROM   CREDIT
					 WHERE  (CREDIT.DATA_TOTAL_DIVISION = '1' OR CREDIT.DATA_TOTAL_DIVISION = '9') --データ集計区分(1:入金、9:その他)
					 AND    CREDIT.CLAIM_TARGET_DIVISION <> '9' --請求対象
						   --           AND   CREDIT.CLAIM_UPDATE_DIVISION = '1'          --請求更新フラグ
						   --           AND   CREDIT.INVOICE_UPDATE_DATE IS NOT NULL        --請求締め日
					 AND    CREDIT.CREDIT_ERASER_AMOUNT <> 0 --入金消込残<>０
					 GROUP  BY ORGANIZATION_CD
							  ,VENDER_CD) CREDIT
		ON     ERASER_CSM.ORGANIZATION_CD = CREDIT.ORGANIZATION_CD
		AND    ERASER_CSM.INVOICE_CD = CREDIT.VENDER_CD
		/*END*/
		
		/*IF ( condition.srhStatusNew && condition.srhStatusApprove )*/
		UNION
		/*END*/
		
		--消込状態='承認処理'
		/*IF ( condition.srhStatusApprove )*/
		SELECT '2' AS KBN --区分(承認処理)
			  ,ERASER_CSM.INVOICE_CD --請求先コード
			  ,ERASER_CSM.VENDER_NAME1 --請求先名称
			  ,ERASER_CSM.VENDER_SHORTED_NAME --請求先略称
			  ,ERASER_CSM.ERASER_COMPLETE_DATE --消込完了日付
			  ,ERASER_CSM.ERASER_OBJECT_AMOUNT --消込対象額
			  ,ERASER_CSM.ERASER_AMOUNT --消込金額
			  ,ERASER_CSM.ERASER_BALANCE_AMOUNT --消込残
			  ,CREDIT.CREDIT_ERASER_AMOUNT --入金消込残
			  ,ERASER_CSM.ORGANIZATION_CD --部署コード
			  ,ERASER_CSM.ORGANIZATION_NAME --部署名称
			  ,ERASER_CSM.TANTO_CD --担当者コード
			  ,ERASER_CSM.TANTO_NM --担当者名
			  ,ERASER_CSM.APPROVAL_STATUS --承認ステータス
			  ,ERASER_CSM.INVOICE_UPDATE_DATE --請求締日
			  ,ERASER_CSM.CLAIM_NO --請求番号
		FROM   (SELECT ERASER_CSM.INVOICE_CD
					  ,MAX(ERASER_CSM.ORGANIZATION_CD) AS ORGANIZATION_CD
					  ,MAX(ORGANIZATION.ORGANIZATION_NAME) AS ORGANIZATION_NAME
					  ,MAX(VENDER.VENDER_NAME1) AS VENDER_NAME1
					  ,MAX(VENDER.VENDER_SHORTED_NAME) AS VENDER_SHORTED_NAME
					  ,MAX(ERASER_CSM.INPUTOR_CD) AS TANTO_CD
					  ,MAX(LOGIN.TANTO_NM) AS TANTO_NM
					  ,MAX(ERASER_CSM.ERASER_COMPLETE_DATE) AS ERASER_COMPLETE_DATE --消込完了日付
					  ,SUM(ERASER_CSM.ERASER_OBJECT_AMOUNT) AS ERASER_OBJECT_AMOUNT --消込対象額
					  ,SUM(ERASER_CSM.ERASER_AMOUNT) AS ERASER_AMOUNT --消込金額
					  ,SUM(NVL(ERASER_CSM.ERASER_BALANCE_AMOUNT, 0)) ERASER_BALANCE_AMOUNT --消込残
					  ,APPROVAL_STATUS --承認ステータス
					  ,INVOICE_UPDATE_DATE --請求締日
					  ,CLAIM_NO --請求番号
				FROM   ERASER_CSM
				LEFT   JOIN ORGANIZATION
				ON     ERASER_CSM.ORGANIZATION_CD = ORGANIZATION.ORGANIZATION_CD
				LEFT   JOIN LOGIN
				ON     ERASER_CSM.ERASEROR_CD = LOGIN.TANTO_CD
				LEFT   JOIN VENDER
				ON     ERASER_CSM.INVOICE_CD = VENDER.VENDER_CD
				AND    VENDER.VENDER_DIVISION = 'TS' --得意先
				WHERE  ERASER_CSM.ORGANIZATION_CD IS NOT NULL
				AND    (ERASER_CSM.ORGANIZATION_CD LIKE /*condition.srhorganizationCd*/'%'
					  OR ORGANIZATION.ORGANIZATION_NAME LIKE /*condition.srhOrganizationCd*/'%')
					  
					  /*IF (( condition.srhTantoCd != null ) && ( condition.srhTantoCd != "" ))*/
				AND    (ERASER_CSM.ERASEROR_CD LIKE /*condition.srhTantoCd*/'%'
					  OR LOGIN.TANTO_NM LIKE /*condition.srhTantoCd*/'%')
					  /*END*/
					  
					  /*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
				AND    (ERASER_CSM.INVOICE_CD LIKE /*condition.srhVenderCd*/'%'
					  OR VENDER.VENDER_NAME1 LIKE FUN_RET_MASTER_STRING_USE_AC(/*condition.srhVenderCd*/'%'))
					  /*END*/
					  
				AND    ERASER_CSM.ERASER_COMPLETE_DIVISION = 1 --消込完了フラグ(処理中)
				GROUP  BY ERASER_CSM.INVOICE_CD
						 ,INVOICE_UPDATE_DATE
						 ,APPROVAL_STATUS
						 ,CLAIM_NO) ERASER_CSM
		LEFT   JOIN (SELECT ORGANIZATION_CD
						   ,VENDER_CD
						   ,SUM(NVL(CREDIT_ERASER_AMOUNT, 0)) AS CREDIT_ERASER_AMOUNT --入金消込残
					 FROM   CREDIT
					 WHERE  (CREDIT.DATA_TOTAL_DIVISION = '1' OR CREDIT.DATA_TOTAL_DIVISION = '9') --データ集計区分(1:入金、9:その他)
					 AND    CREDIT.CLAIM_TARGET_DIVISION <> '9' --請求対象
					 AND    CREDIT.CREDIT_ERASER_AMOUNT <> 0 --入金消込残<>０
					 GROUP  BY ORGANIZATION_CD
							  ,VENDER_CD) CREDIT
		ON     ERASER_CSM.ORGANIZATION_CD = CREDIT.ORGANIZATION_CD
		AND    ERASER_CSM.INVOICE_CD = CREDIT.VENDER_CD
		/*END*/
		
		/*IF ( (condition.srhStatusNew || condition.srhStatusApprove) && condition.srhStatusEraser )*/
		UNION
		/*END*/
		
		--消込状態='消込完了'
		/*IF ( condition.srhStatusEraser )*/
		SELECT '3' AS KBN --区分(消込完了)
			  ,ERASER_CSM.INVOICE_CD --請求先コード
			  ,ERASER_CSM.VENDER_NAME1 --請求先名称
			  ,ERASER_CSM.VENDER_SHORTED_NAME --請求先略称
			  ,ERASER_CSM.ERASER_COMPLETE_DATE --消込完了日付
			  ,ERASER_CSM.ERASER_OBJECT_AMOUNT --消込残
			  ,ERASER_CSM.ERASER_AMOUNT --消込対象額
			  ,ERASER_CSM.ERASER_BALANCE_AMOUNT --消込残
			  ,NULL AS CREDIT_ERASER_AMOUNT --入金消込残
			  ,ERASER_CSM.ORGANIZATION_CD --部署コード
			  ,ERASER_CSM.ORGANIZATION_NAME --部署名称
			  ,ERASER_CSM.TANTO_CD --担当者コード
			  ,ERASER_CSM.TANTO_NM --担当者名
			  ,ERASER_CSM.APPROVAL_STATUS --承認ステータス
			  ,ERASER_CSM.INVOICE_UPDATE_DATE --請求締日
			  ,ERASER_CSM.CLAIM_NO --請求番号
		FROM   (SELECT ERASER_CSM.INVOICE_CD
					  ,MAX(ERASER_CSM.ORGANIZATION_CD) AS ORGANIZATION_CD
					  ,MAX(ORGANIZATION.ORGANIZATION_NAME) AS ORGANIZATION_NAME
					  ,MAX(VENDER.VENDER_NAME1) AS VENDER_NAME1
					  ,MAX(VENDER.VENDER_SHORTED_NAME) AS VENDER_SHORTED_NAME
					  ,MAX(ERASER_CSM.INPUTOR_CD) AS TANTO_CD
					  ,MAX(LOGIN.TANTO_NM) AS TANTO_NM
					  ,MAX(ERASER_CSM.ERASER_COMPLETE_DATE) AS ERASER_COMPLETE_DATE --消込完了日付
					  ,SUM(ERASER_CSM.ERASER_OBJECT_AMOUNT) AS ERASER_OBJECT_AMOUNT --消込対象額
					  ,SUM(ERASER_CSM.ERASER_AMOUNT) AS ERASER_AMOUNT --消込金額
					  ,SUM(NVL(ERASER_CSM.ERASER_BALANCE_AMOUNT, 0)) ERASER_BALANCE_AMOUNT --消込残
					  ,APPROVAL_STATUS --承認ステータス
					  ,INVOICE_UPDATE_DATE --請求締日
					  ,CLAIM_NO --請求番号
				FROM   ERASER_CSM
				LEFT   JOIN ORGANIZATION
				ON     ERASER_CSM.ORGANIZATION_CD = ORGANIZATION.ORGANIZATION_CD
				LEFT   JOIN LOGIN
				ON     ERASER_CSM.ERASEROR_CD = LOGIN.TANTO_CD
				LEFT   JOIN VENDER
				ON     ERASER_CSM.INVOICE_CD = VENDER.VENDER_CD
				AND    VENDER.VENDER_DIVISION = 'TS' --得意先
				WHERE  ERASER_CSM.ORGANIZATION_CD IS NOT NULL
				AND    (ERASER_CSM.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/'%'
					  OR ORGANIZATION.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/'%')
					  
					  /*IF (( condition.srhTantoCd != null ) && ( condition.srhTantoCd != "" ))*/
				AND    (ERASER_CSM.ERASEROR_CD LIKE /*condition.srhTantoCd*/'%'
					  OR LOGIN.TANTO_NM LIKE /*condition.srhTantoCd*/'%')
					  /*END*/
					  
					  /*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
				AND    (ERASER_CSM.INVOICE_CD LIKE /*condition.srhVenderCd*/'%'
					  OR VENDER.VENDER_NAME1 LIKE FUN_RET_MASTER_STRING_USE_AC(/*conditino.srhVenderCd*/'%'))
					  /*END*/
					  
					  /*IF (( condition.srhEraserCompleteDateFrom != null ) && ( condition.srhEraserCompleteDateFrom != "" ))*/
				AND    ERASER_CSM.ERASER_COMPLETE_DATE >= /*condition.srhEraserCompleteDateFrom*/'2010/01/01'
					  /*END*/
					  
					  /*IF (( condition.srhEraserCompleteDateTo != null ) && ( condition.srhEraserCompleteDateTo != "" ))*/
				AND    ERASER_CSM.ERASER_COMPLETE_DATE <= /*condition.srhEraserCompleteDateTo*/'2010/12/31'
					  /*END*/
					  
				AND    ERASER_CSM.ERASER_COMPLETE_DIVISION = 2 --消込完了フラグ(処理済)
				GROUP  BY ERASER_CSM.INVOICE_CD
						 ,INVOICE_UPDATE_DATE
						 ,APPROVAL_STATUS
						 ,CLAIM_NO) ERASER_CSM
		/*END*/
		)

/*IF (condition.srhOutputDivision == "1")*/ --出力区分：消込残高有のみ（消込残高が0以外）
WHERE  ERASER_BALANCE_AMOUNT IS NOT NULL
AND    ERASER_BALANCE_AMOUNT != 0
/*END*/
ORDER  BY INVOICE_CD
		 ,INVOICE_UPDATE_DATE
		 ,KBN
