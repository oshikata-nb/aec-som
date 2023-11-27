/*
 * 消込入力詳細画面用 入金トランザクション(相殺)更新SQL
 *
 * entityName=ClaimEraserDetail
 * packageName=eraser
 * methodName=updateOffsetGroupData
 *
 */
UPDATE OFFSET_GROUP_DATA
SET ERASER_AMOUNT = /*bean.crEraserAmount*/0						--消込額
,	ERASER_COMPLETE_DATE = /*bean.eraserCompleteDate*/'2009/09/01'	--消込完了日
,	UPDATE_DATE = /*bean.updateDate*/'2009/09/01'
,	UPDATOR_CD = /*bean.updatorCd*/'%'
WHERE OFFSET_NO = /*bean.creditNo*/'%'
AND VENDER_DIVISION = /*bean.venderDivision*/'%'
AND VENDER_CD = /*bean.venderCd*/'%'