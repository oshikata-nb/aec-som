/*
* 品目マスタキュー検索(ポップアップ)一覧用SQL
*
* entityName=ItemQueueSearch
* packageName=itemqueue
* methodName=getSearchList
*
*/

SELECT ITEM_QUEUE.ITEM_CD -- 品目コード
	  ,ITEM_QUEUE.VERSION -- バージョン
	  ,NVL(ITEM_QUEUE.ITEM_NAME, ' ') ITEM_NAME -- 品目名称
	  ,ITEM_QUEUE.OTHER_COMPANY_CD1 -- 他社コード1
	  ,NVL(ITEM_QUEUE.UNIT_OF_OPERATION_MANAGEMENT, ' ') UNIT_DIV -- 運用管理単位
	  ,NVL(NAMES.NAME01, ' ') ITEM_UNIT -- 単位
FROM   VALID_ITEM_QUEUE ITEM_QUEUE
	  ,(SELECT NAME_CD
			  ,NAME01
		FROM   NAMES
		WHERE  NAME_DIVISION = 'UNIT') NAMES
WHERE  ITEM_QUEUE.ITEM_CD IS NOT NULL
	  
	  /*IF (condition.itemCd != null) && (condition.itemCd != "")*/
AND    (ITEM_QUEUE.ITEM_CD LIKE /*condition.itemCd*/'%'
	  OR ITEM_QUEUE.ITEM_NAME LIKE /*condition.itemCd*/'%')
	  /*END*/
	  
	  /*IF (condition.otherCompanyCd1 != null) && (condition.otherCompanyCd1 != "")*/
AND    OTHER_COMPANY_CD1 LIKE /*condition.otherCompanyCd1*/'%'
	  /*END*/
	  
AND    ITEM_QUEUE.UNIT_OF_OPERATION_MANAGEMENT = NAMES.NAME_CD(+)
ORDER  BY ITEM_QUEUE.ITEM_CD
