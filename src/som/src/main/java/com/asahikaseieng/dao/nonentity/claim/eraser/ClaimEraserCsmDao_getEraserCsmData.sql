/*
* 消込入力詳細画面(下段)用SQL
*
* entityName=EraserCsm
* packageName=claim.eraser
* methodName=getEraserCsmData
*
*/
SELECT
/*IF (approvalStatus != null && approvalStatus == 1)*/
 CASE
	 WHEN ERASER_AMOUNT <> 0 THEN
	  '1'
	 ELSE
	  '0'
 END AS CHECK_KBN --チェック区分
 /*END*/
 /*IF (approvalStatus != null &&  (approvalStatus == 2|| approvalStatus == 3))*/
 '2' AS CHECK_KBN --チェック区分
 /*END*/
,INVOICE_CD --得意先コード
,VENDER_NAME1 --得意先名称1
,VENDER_SHORTED_NAME --得意先略称
,PROCESSING_DATE --処理日付
,DATA_TYPE --データ種別
,SLIP_NO --伝票番号
,ERASER_OBJECT_AMOUNT --消込対象額
,CASE
	 WHEN ERASER_AMOUNT <> 0 THEN
	  ERASER_AMOUNT
	 ELSE
	  0
 END AS ERASER_AMOUNT --消込金額
,ERASER_AMOUNT AS LAST_ERASER_AMOUNT --前回消込金額
,ERASER_COMPLETE_DATE --消込完了日
FROM   (
		--消込(Csm)データ
		SELECT ERASER_CSM.INVOICE_CD
			   ,VENDER.VENDER_NAME1
			   ,VENDER.VENDER_SHORTED_NAME
			   ,ERASER_CSM.PROCESSING_DATE
			   ,ERASER_CSM.DATA_TYPE
			   ,ERASER_CSM.SLIP_NO
			   ,ERASER_CSM.ERASER_OBJECT_AMOUNT --消込対象額
			   ,ERASER_CSM.ERASER_AMOUNT --消込金額
			   ,ERASER_CSM.ERASER_COMPLETE_DATE --消込完了日
		FROM   ERASER_CSM
		LEFT   JOIN LOGIN
		ON     ERASER_CSM.INPUTOR_CD = LOGIN.TANTO_CD
		LEFT   JOIN VENDER
		ON     ERASER_CSM.INVOICE_CD = VENDER.VENDER_CD
		AND    VENDER.VENDER_DIVISION = 'TS' --得意先
		WHERE  ERASER_CSM.ORGANIZATION_CD IS NOT NULL
		AND    ERASER_CSM.ORGANIZATION_CD = /*organizationCd*/'%'
		AND    ERASER_CSM.CLAIM_NO = /*claimNo*/'%'
			  
			  /*IF (( tantoCd != null ) && ( tantoCd != "" ))*/
		AND    ERASER_CSM.INPUTOR_CD LIKE /*tantoCd*/'%'
			  /*END*/
			  
			  /*IF (( venderCd != null ) && ( venderCd != "" ))*/
		AND    ERASER_CSM.INVOICE_CD = /*venderCd*/'%'
			  /*END*/
			  
			  /*IF (( eraserCompleteDateFrom != null ) && ( eraserCompleteDateFrom != "" ))*/
		AND    ERASER_CSM.ERASER_COMPLETE_DATE >= /*eraserCompleteDateFrom*/'2010/01/01'
			  /*END*/
			  
			  /*IF (( eraserCompleteDateTo != null ) && ( eraserCompleteDateTo != "" ))*/
		AND    ERASER_CSM.ERASER_COMPLETE_DATE <= /*eraserCompleteDateTo*/'2010/12/31'
			  /*END*/
			  
			  /*IF ( kbn != null && kbn == "2" )*/
		AND    ERASER_CSM.ERASER_COMPLETE_DIVISION = 1 --処理中のみ
			  /*END*/
			  
		AND    ERASER_CSM.APPROVAL_STATUS = /*approvalStatus*/1
			   ) ERASER_CSM
ORDER  BY INVOICE_CD
		 ,PROCESSING_DATE
		 ,DATA_TYPE
		 ,SLIP_NO
