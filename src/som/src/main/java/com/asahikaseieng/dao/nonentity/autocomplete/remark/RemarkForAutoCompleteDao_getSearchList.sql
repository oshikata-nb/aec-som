/*
 * 備考一覧取得SQL
 *
 * entityName=RemarkForAutoComplete
 * packageName=remark
 * methodName=getSearchList
 *
*/

SELECT *
FROM   (SELECT DISTINCT REMARK.DELIVERY_CD
					   ,DELIVERY.DELIVERY_NAME1 AS DELIVERY_NAME
					   ,REMARK.ITEM_CD
					   ,ITEM.ITEM_NAME
		
		FROM   REMARK, DELIVERY, ITEM
		
		WHERE  REMARK.ITEM_CD = ITEM.ITEM_CD
		AND    REMARK.DELIVERY_CD = DELIVERY.DELIVERY_CD
		AND    REMARK.DELIVERY_CD IS NOT NULL
		AND    REMARK.VENDER_DIVISION = /*venderDivision*/'TS'
			  
/*IF (venderCd != null) && (venderCd != "")*/
		AND    REMARK.VENDER_CD = /*venderCd*/'%'
/*END*/
			  
/*IF (deliveryCd != null) && (deliveryCd != "")*/
		AND    REMARK.DELIVERY_CD LIKE /*deliveryCd*/'%'
/*END*/
			  
/*IF (itemCd != null) && (itemCd != "")*/
		AND    REMARK.ITEM_CD LIKE /*itemCd*/'%'
/*END*/
		
		ORDER  BY REMARK.DELIVERY_CD, REMARK.ITEM_CD)

/*IF (rowlimit != null) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/'50'
/*END*/
