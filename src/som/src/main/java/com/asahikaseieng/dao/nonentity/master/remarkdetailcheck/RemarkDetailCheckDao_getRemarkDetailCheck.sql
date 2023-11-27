/*
 * 備考詳細用SQL
 *
 * entityName=RemarkDetailCheck
 * packageName=remarkdetailcheck
 * methodName=getRemarkDetailCheck
 *
 */

SELECT	*
FROM	REMARK
WHERE	REMARK_NO IS NOT NULL

/*IF (venderCd != null) && (venderCd != "")*/
	AND VENDER_DIVISION = /*venderDivision*/''
    AND VENDER_CD = /*venderCd*/'%'
/*END*/

/*IF (venderCd == null) || (venderCd == "")*/
    AND VENDER_CD IS NULL
/*END*/

/*IF (deliveryCd != null) && (deliveryCd != "")*/
    AND DELIVERY_CD = /*deliveryCd*/'%'
/*END*/

/*IF (deliveryCd == null) || (deliveryCd == "")*/
    AND DELIVERY_CD IS NULL
/*END*/

/*IF (itemCd != null) && (itemCd != "")*/
    AND ITEM_CD = /*itemCd*/'%'
/*END*/

/*IF (itemCd == null) || (itemCd == "")*/
    AND ITEM_CD IS NULL
/*END*/
