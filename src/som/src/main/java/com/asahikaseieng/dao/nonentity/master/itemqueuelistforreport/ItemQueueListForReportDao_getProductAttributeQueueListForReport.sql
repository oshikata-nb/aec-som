/*
* 品目マスタ在庫単価製造品検索帳票用SQL
*
* entityName=ItemQueueListForReport
* packageName=itemqueuelistforreport
* methodName=getProductAttributeQueueListForReport
*
*/

SELECT PRODUCT.*
	   
	  ,CASE PRODUCT.STATUS
		   WHEN 1 THEN
			'入力中'
		   WHEN 2 THEN
			'承認依頼中'
		   WHEN 3 THEN
			'承認済み'
		   WHEN 4 THEN
			'研究用'
		   ELSE
			NULL
	   END STATUS_NAME
	   
	  ,LINE.PRODUCTION_LINE_NAME
	  ,BUMON.SECTION_NAME
	  ,INPUTOR.TANTO_NM          INPUTOR_NAME
	  ,UPDATOR.TANTO_NM          UPDATOR_NAME

FROM   (SELECT ITEM_QUEUE.ITEM_CD
			  ,ITEM_QUEUE.VERSION
			  ,ITEM_QUEUE.ACTIVE_DATE
		
		FROM   (SELECT ITEM_QUEUE.ITEM_CD
					  ,ITEM_QUEUE.VERSION
					  ,ITEM_NAME
					  ,STATUS
					  ,ACTIVE_DATE
					  ,OTHER_COMPANY_CD1
					  ,PARENT_ITEM_CD
					  ,
					   
					   (SELECT ITEM_NAME
						FROM   ITEM_QUEUE PARENT_ITEM
							  ,
							   
							   (SELECT ITEM_CD
									  ,MAX(VERSION) VERSION
								FROM   ITEM_QUEUE
								GROUP  BY ITEM_CD) MAX_ITEM
						
						WHERE  PARENT_ITEM.ITEM_CD = MAX_ITEM.ITEM_CD
						AND    PARENT_ITEM.VERSION = MAX_ITEM.VERSION
						AND    PARENT_ITEM.ITEM_CD = ITEM_QUEUE.PARENT_ITEM_CD) PARENT_ITEM_NAME
				
				FROM   ITEM_QUEUE) ITEM_QUEUE
			  ,ITEM
			  ,ARTICLE_ATTRIBUTE_QUEUE ARTICLE
			  ,PRODUCT_ATTRIBUTE_QUEUE PRODUCT
			  ,PURCHASE_ATTRIBUTE_QUEUE PURCHASE
		
		WHERE  ITEM_QUEUE.ITEM_CD IS NOT NULL
			  
			  /*IF (condition.srhItemCd != null) && (condition.srhItemCd != "")*/
		AND    (ITEM_QUEUE.ITEM_CD LIKE /*condition.srhItemCd*/'%'
			  OR ITEM_QUEUE.ITEM_NAME LIKE /*condition.srhItemCd*/'%')
			  /*END*/
			  
			  /*IF (condition.srhParentItemCd != null) && (condition.srhParentItemCd != "")*/
		AND    (ITEM_QUEUE.PARENT_ITEM_CD LIKE /*condition.srhParentItemCd*/'%'
			  OR PARENT_ITEM_NAME LIKE /*condition.srhParentItemCd*/'%')
			  /*END*/
			  
			  /*IF (condition.srhOtherCompanyCd1 != null) && (condition.srhOtherCompanyCd1 != "")*/
		AND    ITEM_QUEUE.OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/'%'
			  /*END*/
			  
			  /*IF (condition.srhStatus != 0)*/
		AND    ITEM_QUEUE.STATUS = /*condition.srhStatus*/1
			  /*END*/
			  
			  /*IF (condition.srhDetailStatus != 0)*/
		AND    (ARTICLE.STATUS = /*condition.srhDetailStatus*/1
			  OR PRODUCT.STATUS = /*condition.srhDetailStatus*/1
			  OR PURCHASE.STATUS = /*condition.srhDetailStatus*/1)
			  /*END*/
			  
			  /*IF (condition.strSrhActiveDateFrom != null) && (condition.strSrhActiveDateFrom != "")*/
		AND    TO_CHAR(ITEM_QUEUE.ACTIVE_DATE, 'YYYY/MM/DD') >= /*condition.strSrhActiveDateFrom*/'2009/01/01'
			  /*END*/
			  
			  /*IF (condition.strSrhActiveDateTo != null) && (condition.strSrhActiveDateTo != "")*/
		AND    TO_CHAR(ITEM_QUEUE.ACTIVE_DATE, 'YYYY/MM/DD') <= /*condition.strSrhActiveDateTo*/'2010/12/31'
			  /*END*/
			  
		AND    ITEM_QUEUE.ITEM_CD = ITEM.ITEM_CD(+)
		AND    ITEM_QUEUE.ITEM_CD = ARTICLE.ITEM_CD(+)
		AND    ITEM_QUEUE.VERSION = ARTICLE.VERSION(+)
		AND    ITEM_QUEUE.ITEM_CD = PRODUCT.ITEM_CD(+)
		AND    ITEM_QUEUE.VERSION = PRODUCT.VERSION(+)
		AND    ITEM_QUEUE.ITEM_CD = PURCHASE.ITEM_CD(+)
		AND    ITEM_QUEUE.VERSION = PURCHASE.VERSION(+)) ITEM_QUEUE
	  ,ITEM
	  ,PRODUCT_ATTRIBUTE_QUEUE PRODUCT
	  ,LOGIN INPUTOR
	  ,LOGIN UPDATOR
	  ,LINE
	  ,BUMON

WHERE  ITEM_QUEUE.ITEM_CD = PRODUCT.ITEM_CD
AND    ITEM_QUEUE.VERSION = PRODUCT.VERSION
AND    PRODUCT.PRODUCTION_LINE = LINE.PRODUCTION_LINE(+)
AND    PRODUCT.SECTION_CD = BUMON.SECTION_CD(+)
AND    PRODUCT.INPUTOR_CD = INPUTOR.TANTO_CD(+)
AND    PRODUCT.UPDATOR_CD = UPDATOR.TANTO_CD(+)
AND    ITEM_QUEUE.ITEM_CD = ITEM.ITEM_CD(+)
AND    ITEM_QUEUE.VERSION = ITEM.VERSION(+)

ORDER  BY PRODUCT.ITEM_CD
		 ,DECODE(ITEM_QUEUE.VERSION, ITEM.VERSION, 1, 0) DESC
		 ,ITEM_QUEUE.ACTIVE_DATE DESC
		 ,PRODUCT.VERSION
