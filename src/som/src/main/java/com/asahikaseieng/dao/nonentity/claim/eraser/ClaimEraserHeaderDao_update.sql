/*
 * 消込入力詳細画面用 消込ヘッダー更新SQL
 *
 * entityName=EraserHeader
 * packageName=claim.eraser
 * methodName=update
 *
 */
UPDATE ERASER_HEADER
SET ERASER_DATE = /*bean.eraserDate*/					--消込額
,	ERASER_AMOUNT = /*bean.eraserAmount*/				--入金消込残
,	UPDATE_DATE = /*bean.updateDate*/
,	UPDATOR_CD = /*bean.updatorCd*/
WHERE ERASER_NO = /*bean.eraserNo*/
