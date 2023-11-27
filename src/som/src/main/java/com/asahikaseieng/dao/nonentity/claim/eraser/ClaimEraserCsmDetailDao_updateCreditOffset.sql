/*
 * 消込入力詳細画面用(カスタマイズ) 入金トランザクション(相殺データ)更新SQL
 *
 * entityName=EraserCsmDetail
 * packageName=claim.eraser
 * methodName=updateCreditOffset
 *
 */
UPDATE CREDIT
SET ERASER_COMPLETE_DIVISION = /*bean.eraserCompleteDivision*/1		--消込完了フラグ
,	ERASER_COMPLETE_DATE = /*bean.eraserCompleteDate*/'2009/06/25'				--消込完了日
,	UPDATE_DATE = /*bean.updateDate*/'2009/06/25'
,	UPDATOR_CD = /*bean.updatorCd*/'%'
WHERE CREDIT_NO = /*bean.creditNo*/'%'
AND   DATA_TOTAL_DIVISION = '2'		--2:相殺
