/*
 * 消込入力詳細画面用(カスタマイズ) グループ間相殺トランザクション更新SQL
 *
 * entityName=EraserCsmDetail
 * packageName=claim.eraser
 * methodName=updateOffsetGroup
 *
 */
UPDATE OFFSET_GROUP_DATA
SET ERASER_COMPLETE_DIVISION = /*bean.eraserCompleteDivision*/		--消込完了フラグ
,	ERASER_COMPLETE_DATE = /*bean.eraserCompleteDate*/				--消込完了日
,	UPDATE_DATE = /*bean.updateDate*/
,	UPDATOR_CD = /*bean.updatorCd*/
WHERE OFFSET_NO = /*bean.offsetNo*/
AND   VENDER_CD = /*bean.venderCd*/
AND   VENDER_DIVISION = 'TS'
AND   DATA_TOTAL_DIVISION = '1'		--1:相殺