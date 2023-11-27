/*
 * 消込入力詳細画面用 入金トランザクション更新SQL
 *
 * entityName=EraserDetail
 * packageName=claim.eraser
 * methodName=updateCredit
 *
 */
UPDATE CREDIT
SET ERASER_AMOUNT = /*bean.crEraserAmount*/							--消込額
,	CREDIT_ERASER_AMOUNT = /*bean.creditEraserAmount*/				--入金消込残
,	ERASER_COMPLETE_DIVISION = /*bean.eraserCompleteDivision*/		--消込完了フラグ
,	ERASER_COMPLETE_DATE = /*bean.eraserCompleteDate*/				--消込完了日
,	UPDATE_DATE = /*bean.updateDate*/
,	UPDATOR_CD = /*bean.updatorCd*/
WHERE CREDIT_NO = /*bean.creditNo*/
AND   ROW_NO = /*bean.rowNo*/
