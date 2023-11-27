/*
 * 消込入力詳細画面用 支払トランザクション更新SQL
 *
 * entityName=EraserDetail
 * packageName=claim.eraser
 * methodName=updatePayment
 *
 */
UPDATE PAYMENT
SET ERASER_COMPLETE_DIVISION = /*bean.eraserCompleteDivision*/		--消込完了フラグ
,	ERASER_COMPLETE_DATE = /*bean.eraserCompleteDate*/				--消込完了日
,	UPDATE_DATE = /*bean.updateDate*/
,	UPDATOR_CD = /*bean.updatorCd*/
WHERE SLIP_NO = /*bean.paySlipNo*/
AND   ROW_NO = /*bean.payRowNo*/
