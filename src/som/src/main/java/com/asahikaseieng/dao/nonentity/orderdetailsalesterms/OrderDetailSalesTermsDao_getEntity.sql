/*
 * 販売条件取得用SQL
 *
 * entityName=OrderDetailSalesTerms
 * packageName=orderdetailsalesterms
 * methodName=getEntity
 *
 */
SELECT	DELIVERY_CD
,	BALANCE_CD
,	ITEM_CD
,	SEQ
,	TANTO_CD
,	INPUT_DATE
,	INPUTOR_CD
,	UPDATE_DATE
,	UPDATOR_CD
FROM	SALES_TERMS
WHERE	DELIVERY_CD = /*deliveryCd*/
AND	BALANCE_CD = /*balanceCd*/
AND	ITEM_CD = /*itemCd*/

