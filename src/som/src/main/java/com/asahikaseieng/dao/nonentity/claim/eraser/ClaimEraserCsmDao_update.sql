/*
 * 消込入力詳細画面用(カスタマイズ) 消込トランザクション(Csm)更新SQL
 *
 * entityName=EraserCsm
 * packageName=claim.eraser
 * methodName=update
 *
 */
UPDATE ERASER_CSM
SET ERASER_AMOUNT = /*bean.eraserAmount*/							--消込額
,	ERASER_BALANCE_AMOUNT = /*bean.eraserBalanceAmount*/			--消込残
,	ERASER_COMPLETE_DIVISION = /*bean.eraserCompleteDivision*/		--消込完了フラグ
,	ERASER_COMPLETE_DATE = /*bean.eraserCompleteDate*/				--消込完了日
,	APPROVAL_STATUS = /*bean.approvalStatus*/						--承認ステータス
,	ERASER_DATE = /*bean.eraserDate*/								--消込日付
,	ERASER_UPDATE_DATE = /*bean.eraserUpdateDate*/					--消込更新日時
,	ERASEROR_CD = /*bean.eraserorCd*/								--消込担当者ＩＤ
,	UPDATE_DATE = /*bean.updateDate*/								--更新日時
,	UPDATOR_CD = /*bean.updatorCd*/									--更新者ＩＤ
WHERE DATA_TYPE = /*bean.dataType*/
AND   SLIP_NO = /*bean.slipNo*/
AND   INVOICE_CD = /*bean.invoiceCd*/
