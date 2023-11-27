/*
 * Created on 2009/04/08
 *
 * $copyright$
 *
*/

/**
 * 生産計画入力　明細画面　明細一覧取得用SQL
 *
 * @author tosco
 *
 * entityName=ProductionDetailList
 * packageName=production
 * methodName=getEntity
 *
 */
SELECT	ASP_PRODUCTION.ORDER_CD 
,	ASP_PRODUCTION.ITEM_CD
,	ASP_PRODUCTION.ORDER_LET
,	ASP_PRODUCTION.ORDER_EXPECT_QTY
,	ASP_PRODUCTION.ORDER_ACCEPT_QTY
,	ASP_PRODUCTION.ORDER_COMMENT
,	ASP_PRODUCTION.ORDER_NO
,	ASP_PRODUCTION.ORDER_ROW_NO
,	ASP_PRODUCTION.INPUT_DATE
,	ASP_PRODUCTION.INPUTOR_CD
,	ASP_PRODUCTION.UPDATE_DATE
,	ASP_PRODUCTION.UPDATOR_CD

,	CAL.CAL_DATE

--,	ASP_ORDER.WORK_STATUS
	--ASP_ORDERにない場合でも指図ステータスが2以上ならば確定とする
,	CASE WHEN ASP_ORDER.WORK_STATUS IS NOT NULL
		THEN TO_CHAR(ASP_ORDER.WORK_STATUS)
		ELSE
			CASE WHEN ASP_PRODUCTION.ORDER_CD IS NOT NULL THEN

				CASE WHEN 
	
					(SELECT NVL(MAX(DIRECTION_STATUS),0) FROM DIRECTION_HEADER
					WHERE ASP_ORDER_NO LIKE ASP_PRODUCTION.ORDER_CD || '%') > 1 THEN 'D'
				ELSE ''
				END

			ELSE ''
			END
	END AS WORK_STATUS

,	(NVL(ASP_PRODUCTION.ORDER_EXPECT_QTY,0) + NVL(ASP_PRODUCTION.ORDER_ACCEPT_QTY,0)) as SUM_ORDER_QTY

,	CAL.CAL_HOLIDAY as CAL_HOLIDAY_FLAG
,	CAL.CAL_WEEK as CAL_WEEK
FROM	ASP_PRODUCTION
,		CAL
,		ASP_ORDER

WHERE	CAL.CAL_DATE = ASP_PRODUCTION.ORDER_LET(+)

AND	CAL.CAL_CD = '1100' --工場:1100

AND	ASP_PRODUCTION.ORDER_CD = ASP_ORDER.ORDER_CD(+)
AND	ASP_PRODUCTION.ITEM_CD	= ASP_ORDER.ITEM_CD(+)

/*IF (( orderLet != null ) && ( orderLet != "" ))*/
	AND	TO_CHAR(CAL.CAL_DATE,'yyyy/mm') = /*orderLet*/
/*END*/

/*IF (( itemCd != null ) && ( itemCd != "" ))*/
	AND	ASP_PRODUCTION.ITEM_CD(+) = /*itemCd*/
/*END*/

ORDER BY CAL.CAL_DATE