/*
 * 消込入力詳細画面用 売上トランザクション更新SQL
 *
 * entityName=EraserSales
 * packageName=claim.eraser
 * methodName=update
 *
 */
UPDATE SALES
SET ERASER_COMPLETE_DIVISION = /*bean.eraserCompleteDivision*/		--消込完了フラグ
,	ERASER_COMPLETE_DATE = /*bean.eraserCompleteDate*/				--消込完了日
,	UPDATE_DATE = /*bean.updateDate*/
,	UPDATOR_CD = /*bean.updatorCd*/
WHERE SLIP_NO = /*bean.slipNo*/
AND   ROW_NO = /*bean.rowNo*/
